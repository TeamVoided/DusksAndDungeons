package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.util.register
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.BoulderFeature
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.FairyRingFeature
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.FarmlandFeature
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.HugeGoldMushroomFeature
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.BoulderConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FairyRingConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FarmlandConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.MushroomFeatureConfig

object DnDFeatures {

    val FARMLAND = register("farmland", FarmlandFeature(FarmlandConfig.CODEC))
    val BOULDER = register("boulder", BoulderFeature(BoulderConfig.CODEC))
    val FAIRY_RING = register("fairy_ring", FairyRingFeature(FairyRingConfig.CODEC))
    val HUGE_GOLDEN_MUSHROOM = register("huge_golden_mushroom", HugeGoldMushroomFeature(MushroomFeatureConfig.CODEC))

    fun init() = Unit

    fun <C : FeatureConfiguration, F : Feature<C>> register(name: String, feature: F): F {
        return BuiltInRegistries.FEATURE.register(DusksAndDungeons.id(name), feature)
    }

}