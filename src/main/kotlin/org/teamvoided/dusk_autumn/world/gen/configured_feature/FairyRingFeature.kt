package org.teamvoided.dusk_autumn.world.gen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import org.teamvoided.dusk_autumn.util.nextHorizontalDirection
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FairyRingConfig

open class FairyRingFeature(codec: Codec<FairyRingConfig>) :
    Feature<FairyRingConfig>(codec) {
    override fun place(context: FeatureContext<FairyRingConfig>): Boolean {
        val blockPos = context.origin
        val structureWorldAccess = context.world
        val randomGenerator = context.random
        val config = context.config as FairyRingConfig

        if (blockPos.y <= structureWorldAccess.bottomY + 1) {
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
        world: StructureWorldAccess,
        random: RandomGenerator
    ) {
        var placePos = pos
        val placeblock = config.block.getBlockState(random, pos)
            .withIfExists(Properties.HORIZONTAL_FACING, rotation)
            .withIfExists(Properties.FLOWER_AMOUNT, flowerbedCount)
        if (config.verticalRange > 0) {
            var offset = 0
            while (world.getBlockState(placePos).isIn(config.replaceable) && config.verticalRange > offset) {
                placePos = placePos.down()
                offset++
            }
            offset = 0
            while (!world.getBlockState(placePos).isIn(config.replaceable) && config.verticalRange > offset) {
                placePos = placePos.up()
                offset++
            }
        }
        if (world.getBlockState(placePos).isIn(config.replaceable) && placeblock.canPlaceAt(world, placePos)) {
            world.setBlockState(
                placePos,
                placeblock,
                3
            )
        }

    }

    open fun placeRing(
        config: FairyRingConfig,
        origin: BlockPos,
        world: StructureWorldAccess,
        random: RandomGenerator
    ) {
        when (config.size.get(random)) {
            1 -> ::placeRing1
            2 -> ::placeRing2
            3 -> ::placeRing3
            else -> ::placeRing1
        }.invoke(config, origin, world, random)
    }
    fun placeRing1(
        config: FairyRingConfig,
        origin: BlockPos,
        world: StructureWorldAccess,
        random: RandomGenerator
    ) {
        Direction.Type.HORIZONTAL.forEach { direction: Direction ->
            placeBlock(origin.offset(direction), nextHorizontalDirection(direction, 3), 2, config, world, random)
        }
    }
    fun placeRing2(
        config: FairyRingConfig,
        origin: BlockPos,
        world: StructureWorldAccess,
        random: RandomGenerator
    ) {
        Direction.Type.HORIZONTAL.forEach { direction: Direction ->
            var pos: BlockPos =
                origin
                    .offset(direction)
                    .offset(nextHorizontalDirection(direction, 3))
            var flowerFacing: Direction = direction
            placeBlock(pos, flowerFacing, 1, config, world, random)

            pos = pos.offset(direction)
            flowerFacing = nextHorizontalDirection(direction, 3)
            placeBlock(pos, flowerFacing, 2, config, world, random)

            pos = pos.offset(nextHorizontalDirection(direction))
            placeBlock(pos, flowerFacing.opposite, 4, config, world, random)

            pos = pos.offset(nextHorizontalDirection(direction))
            placeBlock(pos, flowerFacing, 2, config, world, random)
        }
    }
    fun placeRing3(
        config: FairyRingConfig,
        origin: BlockPos,
        world: StructureWorldAccess,
        random: RandomGenerator
    ) {
        Direction.Type.HORIZONTAL.forEach { direction: Direction ->
            var pos: BlockPos =
                origin
                    .offset(direction, 2)
                    .offset(nextHorizontalDirection(direction, 3), 2)
            var flowerFacing: Direction = nextHorizontalDirection(direction)
            placeBlock(pos, flowerFacing, 3, config, world, random)

            pos = pos.offset(direction)
            flowerFacing = direction.opposite
            placeBlock(pos, flowerFacing, 1, config, world, random)

            pos = pos.offset(nextHorizontalDirection(direction))
            flowerFacing = nextHorizontalDirection(direction, 3)
            placeBlock(pos, flowerFacing, 3, config, world, random)

            pos = pos.offset(nextHorizontalDirection(direction))
            flowerFacing = nextHorizontalDirection(direction)
            placeBlock(pos, flowerFacing, 2, config, world, random)

            pos = pos.offset(nextHorizontalDirection(direction))
            placeBlock(pos, direction, 3, config, world, random)

            pos = pos.offset(nextHorizontalDirection(direction))
            flowerFacing = nextHorizontalDirection(direction, 3)
            placeBlock(pos, flowerFacing, 1, config, world, random)
        }
    }
}