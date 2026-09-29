package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.configured_features

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature
import org.teamvoided.dusks_and_dungeons.datagen.old.worldgen.ConfiguredFeatureCreator.registerConfiguredFeature
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

object Underground {
    fun BootstrapContext<ConfiguredFeature<*, *>>.underground() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.AZURINE_SAND_ORE,
            Feature.ORE,
            ore(
                BlockTags.OVERWORLD_CARVER_REPLACEABLES,
                DnDBlocks.AZURINE_SAND,
                64,
            )
        )
    }

    private fun ore(tag: TagKey<Block>, block: Block, size: Int, discardOnAirChance: Float = 0f): OreConfiguration {
        return OreConfiguration(
            listOf(OreConfiguration.target(TagMatchTest(tag), block.defaultBlockState())),
            size,
            discardOnAirChance
        )
    }
}