package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.MossBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature
import kotlin.jvm.optionals.getOrNull

class OvergrowthBlock(properties: Properties) : MossBlock(properties) {

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        if (level.getBlockState(pos.relative(Direction.UP)).isAir) {
            return true
        }
        if (level.getBlockState(pos.relative(Direction.DOWN)).isAir) {
            return true
        }
        return false
    }

    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        //alternative idea is to have both above and below place features if air-ed
        val feature: ResourceKey<ConfiguredFeature<*, *>>
        val dir: Direction
        if (level.getBlockState(pos.above()).isAir) {
            feature = FEATURE_FLOOR
            dir = Direction.UP
        } else {
            feature = FEATURE_CEIL
            dir = Direction.DOWN
        }
        level.registryAccess()
            .registry(Registries.CONFIGURED_FEATURE)
            .getOrNull()
            ?.getOptional(feature)
            ?.ifPresent { it.place(level, level.chunkSource.generator, random, pos.relative(dir)) }
    }

    companion object {

        val FEATURE_FLOOR = DnDConfiguredFeature.OVERGROWTH_PATCH_FLOOR_BONEMEAL
        val FEATURE_CEIL = DnDConfiguredFeature.OVERGROWTH_PATCH_CEILING_BONEMEAL

    }
}