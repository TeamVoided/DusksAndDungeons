package org.teamvoided.dusk_autumn.world.gen.configured_feature


import com.mojang.serialization.Codec
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.VerticalSurfaceType
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FarmlandConfig
import java.util.function.Predicate
import kotlin.math.min

open class FarmlandFeature(codec: Codec<FarmlandConfig>) :
    Feature<FarmlandConfig>(codec) {
    override fun place(context: FeatureContext<FarmlandConfig>): Boolean {
        val structureWorldAccess = context.world
        val farmlandConfig = context.config
        val randomGenerator = context.random
        val blockPos = context.origin
        val replaceable =
            Predicate { state: BlockState -> state.isIn(farmlandConfig.farmlandReplaceable) }
        val maxSize = farmlandConfig.farmWidth[randomGenerator]
        val widthX = randomGenerator.range(3, maxSize + 1)
        val widthZ = randomGenerator.range((widthX / 2), widthX + 1)

        val set = placeGroundAndGetPositions(
            structureWorldAccess,
            farmlandConfig,
            randomGenerator,
            blockPos,
            replaceable,
            widthX,
            widthZ
        )
        generateVegetation(
            context,
            structureWorldAccess,
            farmlandConfig,
            randomGenerator,
            set,
            blockPos
        )
        return set.isNotEmpty()
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
            for (j in -radiusZ..radiusZ) {
                val isEdgeZ = j == -radiusZ || j == radiusZ
                val isEdge = isEdgeX || isEdgeZ
                val isCorner = isEdgeX && isEdgeZ
                val isEdgeNotCorner = isEdge && !isCorner


                if ((!isCorner) && (!isEdgeNotCorner || !(random.nextFloat() > 0.75f)) && !(random.nextFloat() > config.farmlandChance)) {
                    mutable[pos, i, 0] = j
                    var k = 0
                    while (world.testBlockState(mutable) { it.isIn(config.farmlandCanPlaceUnder) } && k < config.farmVerticalRange) {
                        mutable.move(direction)
                        ++k
                    }
                    k = 0
                    while (world.testBlockState(mutable) { !it.isIn(config.farmlandCanPlaceUnder) } && k < config.farmVerticalRange) {
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
                        if (placeGround(world, config, replaceable, random, mutable2)) {
                            set.add(blockPos)
                        }
                    }
                }
            }
        }
        val biggerRadX = radiusX + 1
        val biggerRadZ = radiusZ + 1
        val fenceLengthX = min(config.fenceLength[random], biggerRadX)
        val fenceLengthZ = min(config.fenceLength[random], biggerRadZ)
        for (i in -biggerRadX..biggerRadX) {
            val isEdgeX = i == -biggerRadX || i == biggerRadX
            for (j in -biggerRadZ..biggerRadZ) {
                val isEdgeZ = j == -biggerRadZ || j == biggerRadZ
                if (((isEdgeZ && (i <= (-biggerRadX + fenceLengthX) || i >= (biggerRadX - fenceLengthX))) ||
                    (isEdgeX && (j <= (-biggerRadZ + fenceLengthZ) || j >= (biggerRadZ - fenceLengthZ)))) &&
                    (random.nextFloat() < config.fenceChance)
                ) {
                    placeFence(
                        world,
                        config,
                        random,
                        pos.offset(Direction.Axis.X, i).offset(Direction.Axis.Z, j)
                    )
                }
            }
        }
        return set
    }

    protected fun generateVegetation(
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
    ): Boolean {
        return config.cropFeature.value().place(
            world,
            generator,
            random,
            pos
        )
    }

    protected fun placeGround(
        world: StructureWorldAccess,
        config: FarmlandConfig,
        replaceable: Predicate<BlockState>,
        random: RandomGenerator,
        pos: BlockPos.Mutable
    ): Boolean {
        val blockState = config.farmlandBlock.getBlockState(random, pos)
        val blockState2 = world.getBlockState(pos)
        if (!blockState.isOf(blockState2.block)) {
            if (!replaceable.test(blockState2)) return false
            world.setBlockState(pos, blockState, 2)
            pos.move(Direction.DOWN)
        }
        return true
    }

//    this, in fact, does not update neighbors when placed with placed features
    protected fun placeFence(
        world: StructureWorldAccess,
        config: FarmlandConfig,
        random: RandomGenerator,
        pos: BlockPos
    ) {
        val blockState = config.fenceBlock.getBlockState(random, pos)
        val blockState2 = world.getBlockState(pos)
        if (blockState2.isIn(config.farmlandCanPlaceUnder))
            world.setBlockState(pos, blockState, 2)
    }
}