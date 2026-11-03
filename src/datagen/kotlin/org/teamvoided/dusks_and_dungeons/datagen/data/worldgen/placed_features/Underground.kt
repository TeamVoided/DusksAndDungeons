package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.placed_features

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.datagen.old.worldgen.PlacedFeatureCreator.register

object Underground {

    fun BootstrapContext<PlacedFeature>.underground() {
        this.register(
            DnDPlacedFeature.AZURINE_SAND_ORE,
            DnDConfiguredFeature.AZURINE_SAND_ORE,
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            InSquarePlacement.spread(),
            CountPlacement.of(32),
            BiomeFilter.biome()
        )
    }

}