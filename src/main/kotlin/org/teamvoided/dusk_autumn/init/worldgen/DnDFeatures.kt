package org.teamvoided.dusk_autumn.init.worldgen

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import org.teamvoided.dusk_autumn.DuskAutumns
import org.teamvoided.dusk_autumn.world.gen.configured_feature.FallenTreeFeature
import org.teamvoided.dusk_autumn.world.gen.configured_feature.FarmlandFeature
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FallenTreeConfig
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FarmlandConfig

object DnDFeatures {

    val FARMLAND = register("farmland", FarmlandFeature(FarmlandConfig.CODEC))
    val FALLEN_TREE = register("fallen_tree", FallenTreeFeature(FallenTreeConfig.CODEC))

    fun init() = Unit
    private fun <C : FeatureConfig, F : Feature<C>> register(name: String, feature: F): F =
        Registry.register(Registries.FEATURE, DuskAutumns.id(name), feature)
}