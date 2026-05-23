package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import org.teamvoided.dusks_and_dungeons.util.nextHorizontalDirection
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FairyRingConfig

open class FairyRingFeature(codec: Codec<FairyRingConfig>) :
    Feature<FairyRingConfig>(codec) {
    override fun place(context: FeaturePlaceContext<FairyRingConfig>): Boolean {
        val blockPos = context.origin()
        val structureWorldAccess = context.level()
        val randomGenerator = context.random()
        val config = context.config() as FairyRingConfig

        if (blockPos.y <= structureWorldAccess.minBuildHeight + 1) {
            return false
        } else {
//            config.feature.value().place(structureWorldAccess, context.generator, randomGenerator, blockPos)
            placeRing(config, blockPos, structureWorldAccess, randomGenerator)
            return true
        }
    }

    fun placeBlock(
        pos: BlockPos,
        rotation: Direction,
        flowerbedCount: Int,
        config: FairyRingConfig,
        world: WorldGenLevel,
        random: RandomSource
    ) {
        var placePos = pos
        if (!world.getFluidState(pos).isEmpty) return
        val placeblock = config.block.getState(random, pos)
            .trySetValue(BlockStateProperties.HORIZONTAL_FACING, rotation)
            .trySetValue(BlockStateProperties.FLOWER_AMOUNT, flowerbedCount)
        if (config.verticalRange > 0) {
            var offset = 0
            while (world.getBlockState(placePos).`is`(config.replaceable) && config.verticalRange > offset) {
                placePos = placePos.below()
                offset++
            }
            offset = 0
            while (!world.getBlockState(placePos).`is`(config.replaceable) && config.verticalRange > offset) {
                placePos = placePos.above()
                offset++
            }
        }
        if (world.getBlockState(placePos).`is`(config.replaceable) && placeblock.canSurvive(world, placePos)) {
            world.setBlock(
                placePos,
                placeblock,
                3
            )
        }

    }

    open fun placeRing(
        config: FairyRingConfig,
        origin: BlockPos,
        world: WorldGenLevel,
        random: RandomSource
    ) {
        when (config.size.sample(random)) {
            1 -> ::placeRing1
            2 -> ::placeRing2
            3 -> ::placeRing3
            else -> ::placeRing1
        }.invoke(config, origin, world, random)
    }

    fun placeRing1(
        config: FairyRingConfig,
        origin: BlockPos,
        world: WorldGenLevel,
        random: RandomSource
    ) {
        Direction.Plane.HORIZONTAL.forEach { direction: Direction ->
            placeBlock(origin.relative(direction), nextHorizontalDirection(direction, 3), 2, config, world, random)
        }
    }

    fun placeRing2(
        config: FairyRingConfig,
        origin: BlockPos,
        world: WorldGenLevel,
        random: RandomSource
    ) {
        Direction.Plane.HORIZONTAL.forEach { direction: Direction ->
            var pos: BlockPos =
                origin
                    .relative(direction)
                    .relative(nextHorizontalDirection(direction, 3))
            var flowerFacing: Direction = direction
            placeBlock(pos, flowerFacing, 1, config, world, random)

            pos = pos.relative(direction)
            flowerFacing = nextHorizontalDirection(direction, 3)
            placeBlock(pos, flowerFacing, 2, config, world, random)

            pos = pos.relative(nextHorizontalDirection(direction))
            placeBlock(pos, flowerFacing.opposite, 4, config, world, random)

            pos = pos.relative(nextHorizontalDirection(direction))
            placeBlock(pos, flowerFacing, 2, config, world, random)
        }
    }

    fun placeRing3(
        config: FairyRingConfig,
        origin: BlockPos,
        world: WorldGenLevel,
        random: RandomSource
    ) {
        Direction.Plane.HORIZONTAL.forEach { direction: Direction ->
            var pos: BlockPos =
                origin
                    .relative(direction, 2)
                    .relative(nextHorizontalDirection(direction, 3), 2)
            var flowerFacing: Direction = nextHorizontalDirection(direction)
            placeBlock(pos, flowerFacing, 3, config, world, random)

            pos = pos.relative(direction)
            flowerFacing = direction.opposite
            placeBlock(pos, flowerFacing, 1, config, world, random)

            pos = pos.relative(nextHorizontalDirection(direction))
            flowerFacing = nextHorizontalDirection(direction, 3)
            placeBlock(pos, flowerFacing, 3, config, world, random)

            pos = pos.relative(nextHorizontalDirection(direction))
            flowerFacing = nextHorizontalDirection(direction)
            placeBlock(pos, flowerFacing, 2, config, world, random)

            pos = pos.relative(nextHorizontalDirection(direction))
            placeBlock(pos, direction, 3, config, world, random)

            pos = pos.relative(nextHorizontalDirection(direction))
            flowerFacing = nextHorizontalDirection(direction, 3)
            placeBlock(pos, flowerFacing, 1, config, world, random)
        }
    }
}