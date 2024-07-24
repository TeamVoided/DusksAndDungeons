package org.teamvoided.dusk_autumn.world.gen.configured_feature


import com.mojang.serialization.Codec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.FenceBlock
import net.minecraft.block.HorizontalConnectingBlock
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.VerticalSurfaceType
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FarmlandConfig
import java.util.function.Predicate
import kotlin.math.min


open class FarmlandFeature(codec: Codec<FarmlandConfig>) : Feature<FarmlandConfig>(codec) {

    override fun place(context: FeatureContext<FarmlandConfig>): Boolean {
        val worldAccess = context.world
        val config = context.config
        val random = context.random
        val pos = context.origin
        val replaceable = { state: BlockState -> state.isIn(config.farmlandReplaceable) }
        val widthX = random.range(3, config.farmWidth[random] + 1)
        val widthZ = random.range(3, config.farmWidth[random] + 1)

        val set = placeGroundAndGetPositions(worldAccess, config, random, pos, replaceable, widthX, widthZ)
        if (set.isNotEmpty()) {
            val fencePositions = generateFences(widthX, widthZ, config, random, worldAccess, pos)
            generateVegetation(context, worldAccess, config, random, set, pos)
            fencePositions.forEach { updateFence(it, worldAccess) }
            return true
        }
        return false
    }

    protected open fun placeGroundAndGetPositions(
        world: StructureWorldAccess,
        config: FarmlandConfig,
        random: RandomGenerator,
        pos: BlockPos,
        replaceable: Predicate<BlockState>,
        radiusX: Int,
        radiusZ: Int
    ): Set<BlockPos> {
        val mutable = pos.mutableCopy()
        val mutable2 = mutable.mutableCopy()
        val direction = VerticalSurfaceType.FLOOR.direction
        val direction2 = direction.opposite
        val set: MutableSet<BlockPos> = HashSet()

        for (i in -radiusX..radiusX) {
            val isEdgeX = i == -radiusX || i == radiusX
            loopBreak@ for (j in -radiusZ..radiusZ) {
                val isEdgeZ = j == -radiusZ || j == radiusZ
                val isEdge = isEdgeX || isEdgeZ
                val isCorner = isEdgeX && isEdgeZ
                val isEdgeNotCorner = isEdge && !isCorner


                if (isCorner) continue@loopBreak
                if (isEdgeNotCorner || (random.nextFloat() > 0.75f)) continue@loopBreak
                if (random.nextFloat() > config.farmlandChance) continue@loopBreak
                if (world.getBlockState(mutable).fluidState.isIn(FluidTags.WATER)) continue@loopBreak

                mutable[pos, i, 0] = j
                var k = 0
                while (world.testBlockState(mutable) { it.isIn(config.farmlandCanPlaceUnder) }) {
                    if (k >= config.farmVerticalRange) break
                    if (world.getBlockState(mutable).fluidState.isIn(FluidTags.WATER)) continue@loopBreak
                    mutable.move(direction)
                    ++k
                }
                k = 0
                while (world.testBlockState(mutable) { !it.isIn(config.farmlandCanPlaceUnder) }) {
                    if (k >= config.farmVerticalRange) break
                    mutable.move(direction2)
                    ++k
                }
                mutable2[mutable] = direction
                val blockState = world.getBlockState(mutable2)
                if ((world.getBlockState(mutable)
                        .isIn(config.farmlandCanPlaceUnder)) && blockState.isSideSolidFullSquare(
                        world, mutable2, direction2
                    )
                ) {
                    val blockPos = mutable2.toImmutable()
                    if (placeGround(world, config, random, mutable2, replaceable)) {
                        set.add(blockPos)
                    }
                }
            }
        }
        return set
    }

    private fun generateFences(
        radiusX: Int,
        radiusZ: Int,
        config: FarmlandConfig,
        random: RandomGenerator,
        world: StructureWorldAccess,
        pos: BlockPos
    ): List<BlockPos> {
        val fencePositions = mutableListOf<BlockPos>()
        val biggerRadX = radiusX + 1
        val biggerRadZ = radiusZ + 1
        val fenceLengthX = min(config.fenceLength[random], biggerRadX)
        val fenceLengthZ = min(config.fenceLength[random], biggerRadZ)
        for (i in -biggerRadX..biggerRadX) {
            val isEdgeX = i == -biggerRadX || i == biggerRadX
            loopBreak@ for (j in -biggerRadZ..biggerRadZ) {
                val isEdgeZ = j == -biggerRadZ || j == biggerRadZ

                val edgeZValid = isEdgeZ && (i <= (-biggerRadX + fenceLengthX) || i >= (biggerRadX - fenceLengthX))
                val edgeXValid = isEdgeX && (j <= (-biggerRadZ + fenceLengthZ) || j >= (biggerRadZ - fenceLengthZ))
                if (!(edgeZValid || edgeXValid)) continue@loopBreak
                if (random.nextFloat() >= config.fenceChance) continue@loopBreak

                val fencePos = pos.offset(Direction.Axis.X, i).offset(Direction.Axis.Z, j)
                placeFence(world, config, random, fencePos)?.let { fencePositions.add(it) }
            }
        }
        return fencePositions
    }

    private fun generateVegetation(
        context: FeatureContext<FarmlandConfig>,
        world: StructureWorldAccess,
        config: FarmlandConfig,
        random: RandomGenerator,
        positions: Set<BlockPos>,
        centerBlock: BlockPos
    ) {
        positions.forEach {
            if (config.cropFeatureChance > 0.0f && random.nextFloat() < config.cropFeatureChance && it != centerBlock) {
                generateCropFeature(world, config, context.generator, random, it.offset(Direction.UP))
            }
        }
        if (config.cropGuarantee) generateCropFeature(world, config, context.generator, random, centerBlock)
    }

    protected open fun generateCropFeature(
        world: StructureWorldAccess,
        config: FarmlandConfig,
        generator: ChunkGenerator,
        random: RandomGenerator,
        pos: BlockPos
    ) = config.cropFeature.value().place(world, generator, random, pos)

    private fun placeGround(
        world: StructureWorldAccess,
        config: FarmlandConfig,
        random: RandomGenerator,
        pos: BlockPos.Mutable,
        replaceable: Predicate<BlockState>
    ): Boolean {
        val farmBlock = config.farmlandBlock.getBlockState(random, pos)
        val waterBlock = config.waterBlock.getBlockState(random, pos)
        val worldBlock = world.getBlockState(pos)
        if (!farmBlock.isOf(worldBlock.block)) {
            if (!replaceable.test(worldBlock)) return false
            if (config.waterChance > 0.0f &&
                random.nextFloat() < config.cropFeatureChance &&
                canPlaceWater(world, pos)
            ) {
                world.setBlockState(pos, waterBlock, 2)
            } else {
                world.setBlockState(pos, farmBlock, 2)
            }
            pos.move(Direction.DOWN)
        }
        return true
    }

    private fun canPlaceWater(
        world: WorldAccess,
        pos: BlockPos
    ): Boolean {
        val directions = Direction.entries.toTypedArray()
        val directionsLeft = directions.size
        for (looper in 0 until directionsLeft) {
            val direction = directions[looper]
            val bl = world.getBlockState(pos.offset(direction)).isIn(BlockTags.REPLACEABLE)
            if (bl && direction != Direction.UP || !bl && direction == Direction.UP) {
                return false
            }
        }
        return true
    }

    // It does now :)
    //    this, in fact, does not update neighbors when placed with placed features
    private fun placeFence(
        world: StructureWorldAccess, config: FarmlandConfig, random: RandomGenerator, posIn: BlockPos
    ): BlockPos? {
        var pos = posIn
        var x = 0

        while (!dropFence(world, pos)) {
            pos = pos.up()
            x++
            if (x >= config.farmVerticalRange) return null
        }
        x = 0

        if (world.getBlockState(pos).fluidState.isIn(FluidTags.WATER)) return null

        while (dropFence(world, pos)) {
            pos = pos.down()
            if (world.getBlockState(pos).fluidState.isIn(FluidTags.WATER)) return null
            x++
            if (x >= config.farmVerticalRange) return null
        }

        val fenceBlock = config.fenceBlock.getBlockState(random, pos)
        world.setBlockState(pos, fenceBlock, Block.NOTIFY_ALL)
        return pos
    }

    fun dropFence(world: StructureWorldAccess, pos: BlockPos): Boolean {
        val block = world.getBlockState(pos.down())
        return (block.isIn(BlockTags.REPLACEABLE) || block.isIn(BlockTags.REPLACEABLE_BY_TREES))
    }

    private fun updateFence(pos: BlockPos, world: StructureWorldAccess) {
        val fenceBlock = world.getBlockState(pos)
        if (fenceBlock.block is FenceBlock) {
            val fence = fenceBlock.block as FenceBlock

            val north = pos.north()
            val east = pos.east()
            val south = pos.south()
            val west = pos.west()
            val state = world.getBlockState(north)
            val state2 = world.getBlockState(east)
            val state3 = world.getBlockState(south)
            val state4 = world.getBlockState(west)

            val updatedFence = fenceBlock
                .with(
                    HorizontalConnectingBlock.NORTH,
                    fence.canConnect(state, state.isSideSolidFullSquare(world, north, Direction.SOUTH), Direction.SOUTH)
                )
                .with(
                    HorizontalConnectingBlock.EAST,
                    fence.canConnect(state2, state2.isSideSolidFullSquare(world, east, Direction.WEST), Direction.WEST)
                )
                .with(
                    HorizontalConnectingBlock.SOUTH,
                    fence.canConnect(
                        state3, state3.isSideSolidFullSquare(world, south, Direction.NORTH), Direction.NORTH
                    )
                )
                .with(
                    HorizontalConnectingBlock.WEST,
                    fence.canConnect(state4, state4.isSideSolidFullSquare(world, west, Direction.EAST), Direction.EAST)
                )
            world.setBlockState(pos, updatedFence, Block.UPDATE_NONE)
        }
    }

    /*fun placeScarecrow(
        world: StructureWorldAccess,
        config: FarmlandConfig,
        random: RandomGenerator,
        pos: BlockPos
    ) {
        val scarecrow = Util.getRandom(config.scarecrow, random)
        world.spawnEntity(scarecrow)
    }*/
}