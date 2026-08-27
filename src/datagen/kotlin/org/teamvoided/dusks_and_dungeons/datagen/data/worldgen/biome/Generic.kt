package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome

import net.minecraft.world.level.biome.BiomeGenerationSettings
import org.teamvoided.dusks_and_dungeons.mixin.datagen.OverworldBiomesAccessor

fun addGlobalOverworldGeneration(generationSettings: BiomeGenerationSettings.Builder) {
    OverworldBiomesAccessor.dnd_globalOverworldGeneration(generationSettings)
}