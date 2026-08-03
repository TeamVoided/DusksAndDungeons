package org.teamvoided.dusks_and_dungeons.init.worldgen

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.biome.v1.ModificationPhase
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep.Decoration
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags as CTags


object DnDBiomeModifications {

    fun init() {
        addOres("shallow_stoney_ore", DnDPlacedFeature.ROCKY_ORE_UPPER, CTags.IS_OVERWORLD)
        addOres("deep_stoney_ore", DnDPlacedFeature.ROCKY_ORE_LOWER, CTags.IS_OVERWORLD)
        addOres("slated_ore", DnDPlacedFeature.SLATED_ORE, CTags.IS_OVERWORLD)
        addOres("blackstoned_ore", DnDPlacedFeature.BLACKSTONED_ORE, CTags.IS_NETHER)


        addVegetation("golden_mushrooms_normal", DnDPlacedFeature.GOLDEN_MUSHROOM_NORMAL, CTags.IS_FOREST)
        addVegetation("golden_mushrooms_common", DnDPlacedFeature.GOLDEN_MUSHROOM_COMMON, CTags.IS_SPOOKY)
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

}