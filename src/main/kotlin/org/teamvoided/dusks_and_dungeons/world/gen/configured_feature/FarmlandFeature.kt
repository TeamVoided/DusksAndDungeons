package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature


import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.BlockTags
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CrossCollisionBlock
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.placement.CaveSurface
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FarmlandConfig
import java.util.function.Predicate
import kotlin.math.min


open class FarmlandFeature(codec: Codec<FarmlandConfig>) : Feature<FarmlandConfig>(codec) {

    override fun place(context: FeaturePlaceContext<FarmlandConfig>): Boolean {
        val worldAccess = context.level()
        val config = context.config()
        val random = context.random()
        val pos = context.origin()
        val replaceable = { state: BlockState -> state.`is`(config.farmlandReplaceable) }
        val widthX = random.nextInt(3, config.farmWidth.sample(random) + 1)
        val widthZ = random.nextInt(3, config.farmWidth.sample(random) + 1)

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
        world: WorldGenLevel,
        config: FarmlandConfig,
        random: RandomSource,
        pos: BlockPos,
        replaceable: Predicate<BlockState>,
        radiusX: Int,
        radiusZ: Int,
    ): Set<BlockPos> {
        val mutable = pos.mutable()
        val mutable2 = mutable.mutable()
        val direction = CaveSurface.FLOOR.direction
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
                if (world.getBlockState(mutable).fluidState.`is`(FluidTags.WATER)) continue@loopBreak

                mutable.setWithOffset(pos, i, 0, j)
                var k = 0
                while (world.isStateAtPosition(mutable) { it.`is`(config.farmlandCanPlaceUnder) }) {
                    if (k >= config.farmVerticalRange) break
                    if (world.getBlockState(mutable).fluidState.`is`(FluidTags.WATER)) continue@loopBreak
                    mutable.move(direction)
                    ++k
                }
                k = 0
                while (world.isStateAtPosition(mutable) { !it.`is`(config.farmlandCanPlaceUnder) }) {
                    if (k >= config.farmVerticalRange) break
                    mutable.move(direction2)
                    ++k
                }
                mutable2.setWithOffset(mutable, direction)
                val blockState = world.getBlockState(mutable2)
                if ((world.getBlockState(mutable)
                        .`is`(config.farmlandCanPlaceUnder)) && blockState.isFaceSturdy(
                        world, mutable2, direction2
                    )
                ) {
                    val blockPos = mutable2.immutable()
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
        random: RandomSource,
        world: WorldGenLevel,
        pos: BlockPos,
    ): List<BlockPos> {
        val fencePositions = mutableListOf<BlockPos>()
        val biggerRadX = radiusX + 1
        val biggerRadZ = radiusZ + 1
        val fenceLengthX = min(config.fenceLength.sample(random), biggerRadX)
        val fenceLengthZ = min(config.fenceLength.sample(random), biggerRadZ)
        for (i in -biggerRadX..biggerRadX) {
            val isEdgeX = i == -biggerRadX || i == biggerRadX
            loopBreak@ for (j in -biggerRadZ..biggerRadZ) {
                val isEdgeZ = j == -biggerRadZ || j == biggerRadZ

                val edgeZValid = isEdgeZ && (i <= (-biggerRadX + fenceLengthX) || i >= (biggerRadX - fenceLengthX))
                val edgeXValid = isEdgeX && (j <= (-biggerRadZ + fenceLengthZ) || j >= (biggerRadZ - fenceLengthZ))
                if (!(edgeZValid || edgeXValid)) continue@loopBreak
                if (random.nextFloat() >= config.fenceChance) continue@loopBreak

                val fencePos = pos.relative(Direction.Axis.X, i).relative(Direction.Axis.Z, j)
                placeFence(world, config, random, fencePos)?.let { fencePositions.add(it) }
            }
        }
        return fencePositions
    }

    private fun generateVegetation(
        context: FeaturePlaceContext<FarmlandConfig>,
        world: WorldGenLevel,
        config: FarmlandConfig,
        random: RandomSource,
        positions: Set<BlockPos>,
        centerBlock: BlockPos,
    ) {
        positions.forEach {
            if (config.cropFeatureChance > 0.0f && random.nextFloat() < config.cropFeatureChance && it != centerBlock) {
                generateCropFeature(world, config, context.chunkGenerator(), random, it.relative(Direction.UP))
            }
        }
        if (config.cropGuarantee) generateCropFeature(world, config, context.chunkGenerator(), random, centerBlock)
    }

    protected open fun generateCropFeature(
        world: WorldGenLevel,
        config: FarmlandConfig,
        generator: ChunkGenerator,
        random: RandomSource,
        pos: BlockPos,
    ) = config.cropFeature.value().place(world, generator, random, pos)

    private fun placeGround(
        world: WorldGenLevel,
        config: FarmlandConfig,
        random: RandomSource,
        pos: BlockPos.MutableBlockPos,
        replaceable: Predicate<BlockState>,
    ): Boolean {
        val farmBlock = config.farmlandBlock.getState(random, pos)
        val waterBlock = config.waterBlock.getState(random, pos)
        val worldBlock = world.getBlockState(pos)
        if (!farmBlock.`is`(worldBlock.block)) {
            if (!replaceable.test(worldBlock)) return false
            if (config.waterChance > 0.0f &&
                random.nextFloat() < config.cropFeatureChance &&
                canPlaceWater(world, pos)
            ) {
                world.setBlock(pos, waterBlock, 2)
            } else {
                world.setBlock(pos, farmBlock, 2)
            }
            pos.move(Direction.DOWN)
        }
        return true
    }

    private fun canPlaceWater(
        world: LevelAccessor,
        pos: BlockPos,
    ): Boolean {
        val directions = Direction.entries.toTypedArray()
        val directionsLeft = directions.size
        for (looper in 0 until directionsLeft) {
            val direction = directions[looper]
            val bl = world.getBlockState(pos.relative(direction)).`is`(BlockTags.REPLACEABLE)
            if (bl && direction != Direction.UP || !bl && direction == Direction.UP) {
                return false
            }
        }
        return true
    }

    // It does now :)
    //    this, in fact, does not update neighbors when placed with placed features
    private fun placeFence(
        world: WorldGenLevel, config: FarmlandConfig, random: RandomSource, posIn: BlockPos,
    ): BlockPos? {
        var pos = posIn
        var x = 0

        while (!dropFence(world, pos)) {
            pos = pos.above()
            x++
            if (x >= config.farmVerticalRange) return null
        }
        x = 0

        if (world.getBlockState(pos).fluidState.`is`(FluidTags.WATER)) return null

        while (dropFence(world, pos)) {
            pos = pos.below()
            if (world.getBlockState(pos).fluidState.`is`(FluidTags.WATER)) return null
            x++
            if (x >= config.farmVerticalRange) return null
        }

        val fenceBlock = config.fenceBlock.getState(random, pos)
        world.setBlock(pos, fenceBlock, Block.UPDATE_ALL)
        return pos
    }

    fun dropFence(world: WorldGenLevel, pos: BlockPos): Boolean {
        val block = world.getBlockState(pos.below())
        return (block.`is`(BlockTags.REPLACEABLE) || block.`is`(BlockTags.REPLACEABLE_BY_TREES))
    }

    private fun updateFence(pos: BlockPos, world: WorldGenLevel) {
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
                .setValue(
                    CrossCollisionBlock.NORTH,
                    fence.connectsTo(state, state.isFaceSturdy(world, north, Direction.SOUTH), Direction.SOUTH)
                )
                .setValue(
                    CrossCollisionBlock.EAST,
                    fence.connectsTo(state2, state2.isFaceSturdy(world, east, Direction.WEST), Direction.WEST)
                )
                .setValue(
                    CrossCollisionBlock.SOUTH,
                    fence.connectsTo(
                        state3, state3.isFaceSturdy(world, south, Direction.NORTH), Direction.NORTH
                    )
                )
                .setValue(
                    CrossCollisionBlock.WEST,
                    fence.connectsTo(state4, state4.isFaceSturdy(world, west, Direction.EAST), Direction.EAST)
                )
            world.setBlock(pos, updatedFence, Block.UPDATE_NONE)
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