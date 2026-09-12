package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.fabricmc.fabric.api.biome.v1.BiomeModifications.create
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors.includeByKey
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors.tag
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


object DnDBiomeModifications {

    fun init() {
        addVegetation(DnDPlacedFeature.GOLDEN_MUSHROOM_CAVE, DnDBiomeTags.GOLD_MUSHROOMS_CAVE)
        addVegetation(DnDPlacedFeature.GOLDEN_MUSHROOM_SURFACE, DnDBiomeTags.GOLD_MUSHROOMS_SURFACE)
        addVegetation(DnDPlacedFeature.GOLDEN_MUSHROOM_HUGE_PATCH, DnDBiomeTags.GOLD_MUSHROOMS_HUGE)
        addVegetation(DnDPlacedFeature.MOSSKIN_PUMPKIN_EXTRA, DnDBiomeTags.MOSSKIN_PUMPKINS_CAVE)
        replaceVegetation(
            VegetationPlacements.PATCH_PUMPKIN,
            DnDPlacedFeature.GLOOM_PUMPKIN_EXTRA,
            DnDBiomeTags.GLOOM_PUMPKINS_EXTRA
        )
        addVegetation(DnDPlacedFeature.COLD_WILDFLOWER, DnDBiomeTags.COLD_WILDFLOWER)

        addVegetation(DnDPlacedFeature.CRIMSON_WART, DnDBiomeTags.CRIMSON_WART)
        addVegetation(DnDPlacedFeature.WARPED_WART, DnDBiomeTags.WARPED_WART)
    }

    internal fun addVegetation(feature: ResourceKey<PlacedFeature>, biome: TagKey<Biome>) {
        addFeature(Decoration.VEGETAL_DECORATION, feature, biome)
    }

    internal fun addFeature(step: Decoration, feature: ResourceKey<PlacedFeature>, tag: TagKey<Biome>) {
        val id = feature.location().path
        create(id("add_$id")).add(ModificationPhase.ADDITIONS, tag(tag)) {
            it.generationSettings.addFeature(step, feature)
        }
    }

    internal fun addFeature(feature: ResourceKey<PlacedFeature>, biome: ResourceKey<Biome>) {
        val id = feature.location().path
        create(id("add_$id")).add(ModificationPhase.ADDITIONS, includeByKey(biome)) {
            it.generationSettings.addFeature(Decoration.VEGETAL_DECORATION, feature)
        }
    }

    internal fun replaceVegetation(
        oldFeature: ResourceKey<PlacedFeature>,
        newFeature: ResourceKey<PlacedFeature>,
        tag: TagKey<Biome>,
    ) {
        val id = newFeature.location().path
        create(id("replace_$id")).add(ModificationPhase.REPLACEMENTS, tag(tag)) { ctx ->
            with(ctx.generationSettings) {
                removeFeature(Decoration.VEGETAL_DECORATION, oldFeature)
                addFeature(Decoration.VEGETAL_DECORATION, newFeature)
            }
        }
    }

}