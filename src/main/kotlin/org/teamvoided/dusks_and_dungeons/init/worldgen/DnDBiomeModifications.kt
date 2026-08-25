package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.biome.v1.ModificationPhase
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep.Decoration
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags as CTags


object DnDBiomeModifications {

    fun init() {
        addVegetation("golden_mushrooms_cave", DnDPlacedFeature.GOLDEN_MUSHROOM_CAVE, DnDBiomeTags.GOLD_MUSHROOMS_CAVE)
        addVegetation(
            "golden_mushrooms_surface",
            DnDPlacedFeature.GOLDEN_MUSHROOM_SURFACE,
            DnDBiomeTags.GOLD_MUSHROOMS_SURFACE
        )
        addVegetation(
            "golden_mushrooms_common",
            DnDPlacedFeature.GOLDEN_MUSHROOM_HUGE_PATCH,
            DnDBiomeTags.GOLD_MUSHROOMS_HUGE
        )

        replaceVegetation(
            "mosskin_pumpkins_extra",
            VegetationPlacements.PATCH_PUMPKIN,
            DnDPlacedFeature.PATCH_MOSSKIN_PUMPKIN_EXTRA,
            DnDBiomeTags.MOSSKIN_PUMPKINS_CAVE
        )
        replaceVegetation(
            "gloom_pumpkins_extra",
            VegetationPlacements.PATCH_PUMPKIN,
            DnDPlacedFeature.PATCH_GLOOM_PUMPKIN_EXTRA,
            DnDBiomeTags.GLOOM_PUMPKINS_EXTRA
        )


        addVegetation(
            "crimson_warts",
            DnDPlacedFeature.CRIMSON_WART,
            DnDBiomeTags.CRIMSON_WART
        )
        addVegetation(
            "warped_warts",
            DnDPlacedFeature.WARPED_WART,
            DnDBiomeTags.WARPED_WART
        )
    }

    internal fun addOres(id: String, placedFeature: ResourceKey<PlacedFeature>, biome: TagKey<Biome>) {
        addFeature(id, Decoration.UNDERGROUND_ORES, placedFeature, biome)
    }

    internal fun addVegetation(id: String, placedFeature: ResourceKey<PlacedFeature>, biome: TagKey<Biome>) {
        addFeature(id, Decoration.VEGETAL_DECORATION, placedFeature, biome)
    }

    internal fun addFeature(
        id: String, generationStep: Decoration, placedFeature: ResourceKey<PlacedFeature>, biome: TagKey<Biome>,
    ) {
        BiomeModifications.create(id("add_$id")).add(ModificationPhase.ADDITIONS, BiomeSelectors.tag(biome)) {
            it.generationSettings.addFeature(generationStep, placedFeature)
        }
    }


    internal fun replaceVegetation(
        id: String,
        oldPlacedFeature: ResourceKey<PlacedFeature>,
        newPlacedFeature: ResourceKey<PlacedFeature>,
        biome: TagKey<Biome>
    ) {
        replaceFeature(id, Decoration.VEGETAL_DECORATION, oldPlacedFeature, newPlacedFeature, biome)
    }

    internal fun replaceFeature(
        id: String,
        generationStep: Decoration,
        oldPlacedFeature: ResourceKey<PlacedFeature>,
        newPlacedFeature: ResourceKey<PlacedFeature>,
        biome: TagKey<Biome>,
    ) {
        BiomeModifications.create(id("replace_$id")).add(ModificationPhase.REPLACEMENTS, BiomeSelectors.tag(biome)) {
            it.generationSettings.removeFeature(generationStep, oldPlacedFeature)
            it.generationSettings.addFeature(generationStep, newPlacedFeature)
        }
    }
}