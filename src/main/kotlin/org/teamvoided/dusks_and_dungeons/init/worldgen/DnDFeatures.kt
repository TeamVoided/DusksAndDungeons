package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.Registry
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.BoulderFeature
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.FairyRingFeature
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.FallenTreeFeature
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.FarmlandFeature
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.BoulderConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FairyRingConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FallenTreeConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FarmlandConfig

object DnDFeatures {

    val FARMLAND = register("farmland", FarmlandFeature(FarmlandConfig.CODEC))
    val FALLEN_TREE = register("fallen_tree", FallenTreeFeature(FallenTreeConfig.CODEC))
    val BOULDER = register("boulder", BoulderFeature(BoulderConfig.CODEC))
    val FAIRY_RING = register("fairy_ring", FairyRingFeature(FairyRingConfig.CODEC))

    fun init() = Unit
    private fun <C : FeatureConfiguration, F : Feature<C>> register(name: String, feature: F): F =
        Registry.register(BuiltInRegistries.FEATURE, DusksAndDungeons.id(name), feature)
}