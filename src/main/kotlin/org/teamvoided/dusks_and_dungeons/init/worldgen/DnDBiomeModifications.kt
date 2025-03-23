package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.biome.v1.ModificationPhase
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep.Feature
import net.minecraft.world.gen.feature.PlacedFeature
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags as Tags


object DnDBiomeModifications {
    fun init() {
        addOres("shallow_stoney_ore", DnDPlacedFeature.ROCKY_ORE_UPPER, Tags.IS_OVERWORLD)
        addOres("deep_stoney_ore", DnDPlacedFeature.ROCKY_ORE_LOWER, Tags.IS_OVERWORLD)
        addOres("slated_ore", DnDPlacedFeature.SLATED_ORE, Tags.IS_OVERWORLD)
        addOres("blackstoned_ore", DnDPlacedFeature.BLACKSTONED_ORE, Tags.IS_NETHER)
    }

    private fun addOres(id: String, placedFeature: RegistryKey<PlacedFeature>, biome: TagKey<Biome>) =
        addFeature(id, Feature.UNDERGROUND_ORES, placedFeature, biome)

    @Suppress("SameParameterValue")
    private fun addFeature(
        id: String, generationStep: Feature, placedFeature: RegistryKey<PlacedFeature>, biome: TagKey<Biome>
    ) = BiomeModifications.create(id("add_$id")).add(ModificationPhase.ADDITIONS, BiomeSelectors.tag(biome)) { it ->
        it.generationSettings.addFeature(generationStep, placedFeature)
    }

}