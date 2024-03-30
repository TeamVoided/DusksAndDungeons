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

open class FarmlandFeature(codec: Codec<FarmlandConfig>) :
    Feature<FarmlandConfig>(codec) {
    override fun place(context: FeatureContext<FarmlandConfig>): Boolean {
        val structureWorldAccess = context.world
        val FarmlandConfig = context.config
        val randomGenerator = context.random
        val blockPos = context.origin
        val predicate =
            Predicate { state: BlockState -> state.isIn(FarmlandConfig.replaceable) }
        val maxSize = FarmlandConfig.farmSize + 1
        val radiusX = randomGenerator.nextInt(maxSize - 3)
        val radiusZ = maxSize / radiusX
        val set = this.placeGroundAndGetPositions(
            structureWorldAccess,
            FarmlandConfig,
            randomGenerator,
            blockPos,
            predicate,
            radiusX,
            radiusZ
        )
        this.generateVegetation(context, structureWorldAccess, FarmlandConfig, randomGenerator, set, radiusX, radiusZ)
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
        val direction = VerticalSurfaceType.FLOOR
        val direction2 = VerticalSurfaceType.CEILING
        val set: MutableSet<BlockPos> = HashSet()

        for (i in -radiusX..radiusX) {
            val bl = i == -radiusX || i == radiusX

            for (j in -radiusZ..radiusZ) {
                val bl2 = j == -radiusZ || j == radiusZ
                val bl3 = bl || bl2
                val bl4 = bl && bl2
                val bl5 = bl3 && !bl4
                if (!bl4 && (!bl5 || !(random.nextFloat() > 1.0f))) {
                    mutable[pos, i, 0] = j
                    var k = 0
                    while (world.testBlockState(mutable) { state: BlockState -> !state.isAir } && k < config.farmVerticalRange
                    ) {
                        mutable.move(direction.direction)
                        ++k
                    }

                    k = 0
                    while (world.testBlockState(mutable) { state: BlockState -> !state.isAir } && k < config.farmVerticalRange) {
                        mutable.move(direction2.direction)
                        ++k
                    }

                    mutable2[mutable] = Direction.DOWN
                    val blockState = world.getBlockState(mutable2)
                    if (world.isAir(mutable) && blockState.isSideSolidFullSquare(
                            world,
                            mutable2,
                            Direction.UP
                        )
                    ) {
                        val blockPos = mutable2.toImmutable()
                        val bl6 = this.placeGround(world, config, replaceable, random, mutable2)
                        if (bl6) {
                            set.add(blockPos)
                        }
                    }
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
        radiusX: Int,
        radiusZ: Int
    ) {
        val var8: Iterator<*> = positions.iterator()

        while (var8.hasNext()) {
            val blockPos = var8.next() as BlockPos
            if (config.cropFeatureChance > 0.0f && random.nextFloat() < config.cropFeatureChance) {
                this.generatecropFeature(world, config, context.generator, random, blockPos)
            }
        }
    }

    protected open fun generatecropFeature(
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
            pos.offset(Direction.UP)
        )
    }

    protected fun placeGround(
        world: StructureWorldAccess,
        config: FarmlandConfig,
        replaceable: Predicate<BlockState>,
        random: RandomGenerator,
        pos: BlockPos.Mutable
    ): Boolean {
        for (i in 0 until 1) {
            val blockState = config.farmlandBlock.getBlockState(random, pos)
            val blockState2 = world.getBlockState(pos)
            if (!blockState.isOf(blockState2.block)) {
                if (!replaceable.test(blockState2)) {
                    return i != 0
                }
                world.setBlockState(pos, blockState, 2)
                pos.move(Direction.DOWN)
            }
        }
        return true
    }
}