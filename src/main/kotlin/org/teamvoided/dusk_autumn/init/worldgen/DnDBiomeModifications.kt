package org.teamvoided.dusk_autumn.init.worldgen

import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.biome.v1.ModificationPhase
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.PlacedFeature
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.data.worldgen.DnDPlacedFeature


object DnDBiomeModifications {
    fun init() {
        addFeature(
            "add_shallow_stoney_ore",
            GenerationStep.Feature.UNDERGROUND_ORES,
            DnDPlacedFeature.ROCKY_ORE_UPPER,
            ConventionalBiomeTags.IS_OVERWORLD
        )
        addFeature(
            "add_deep_stoney_ore",
            GenerationStep.Feature.UNDERGROUND_ORES,
            DnDPlacedFeature.ROCKY_ORE_LOWER,
            ConventionalBiomeTags.IS_OVERWORLD
        )
        addFeature(
            "add_slated_ore",
            GenerationStep.Feature.UNDERGROUND_ORES,
            DnDPlacedFeature.SLATED_ORE,
            ConventionalBiomeTags.IS_OVERWORLD
        )
        addFeature(
            "add_blackstoned_ore",
            GenerationStep.Feature.UNDERGROUND_ORES,
            DnDPlacedFeature.BLACKSTONED_ORE,
            ConventionalBiomeTags.IS_NETHER
        )
    }

    private fun addFeature(
        id: String,
        generationStep: GenerationStep.Feature,
        placedFeature: RegistryKey<PlacedFeature>,
        biome: TagKey<Biome>
    ) {
        BiomeModifications.create(id(id)).add(
            ModificationPhase.ADDITIONS, BiomeSelectors.tag(biome)
        ) { context: BiomeModificationContext ->
            context.generationSettings.addFeature(generationStep, placedFeature)
        }
    }
}