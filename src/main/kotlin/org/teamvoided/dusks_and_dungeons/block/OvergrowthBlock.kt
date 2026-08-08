package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.features.CaveFeatures
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.MossBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature

class OvergrowthBlock(settings: Properties) : MossBlock(settings) {

    override fun isValidBonemealTarget(world: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        listOf(Direction.UP, Direction.DOWN).forEach { if (world.getBlockState(pos.relative(it)).isAir) return true }
        return false
    }

    override fun performBonemeal(world: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        //alternative idea is to have both above and below place features if air-ed
        val feature: ResourceKey<ConfiguredFeature<*, *>>
        val dir: Direction
        if (world.getBlockState(pos.above()).isAir) {
            feature = FEATURE_FLOOR
            dir = Direction.UP
        } else {
            feature = FEATURE_CEIL
            dir = Direction.DOWN
        }
        world.registryAccess()
            .registry(Registries.CONFIGURED_FEATURE)
            .flatMap { it.getHolder(feature) }
            .ifPresent { (it.value()).place(world, world.chunkSource.generator, random, pos.relative(dir)) }
    }

    companion object {
        private val FEATURE_FLOOR = DnDConfiguredFeature.OVERGROWTH_PATCH_FLOOR_B
        private val FEATURE_CEIL = DnDConfiguredFeature.OVERGROWTH_PATCH_CEILING_B
    }
}