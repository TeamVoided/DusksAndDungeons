package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings.Builder
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep.Decoration
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_and_dungeons.mixin.datagen.OverworldBiomesAccessor

fun Builder.addGlobalOverworldGeneration() {
    OverworldBiomesAccessor.dnd_globalOverworldGeneration(this)
}

fun BootstrapContext<Biome>.createGenerationSettings(creator: Builder.() -> Unit = {}): Builder {
    val builder = Builder(
        lookup(Registries.PLACED_FEATURE), lookup(Registries.CONFIGURED_CARVER)
    )
    creator.invoke(builder)
    return builder
}

fun Builder.features(step: Decoration, key: ResourceKey<PlacedFeature>): Builder = addFeature(step, key)

fun Builder.add0RawGeneration(key: ResourceKey<PlacedFeature>) = features(Decoration.RAW_GENERATION, key)
fun Builder.add1Lakes(key: ResourceKey<PlacedFeature>) = features(Decoration.LAKES, key)
fun Builder.add2LocalModifications(key: ResourceKey<PlacedFeature>) = features(Decoration.LOCAL_MODIFICATIONS, key)
fun Builder.add3UndergroundStructures(key: ResourceKey<PlacedFeature>) = features(Decoration.UNDERGROUND_STRUCTURES, key)
fun Builder.add4SurfaceStructures(key: ResourceKey<PlacedFeature>) = features(Decoration.SURFACE_STRUCTURES, key)
fun Builder.add5Strongholds(key: ResourceKey<PlacedFeature>) = features(Decoration.STRONGHOLDS, key)
fun Builder.add6UndergroundOres(key: ResourceKey<PlacedFeature>) = features(Decoration.UNDERGROUND_ORES, key)
fun Builder.add7UndergroundDecoration(key: ResourceKey<PlacedFeature>) = features(Decoration.UNDERGROUND_DECORATION, key)
fun Builder.add8FluidSprings(key: ResourceKey<PlacedFeature>) = features(Decoration.FLUID_SPRINGS, key)
fun Builder.add9VegetalDecoration(key: ResourceKey<PlacedFeature>) = features(Decoration.VEGETAL_DECORATION, key)
fun Builder.add10TopLayerModification(key: ResourceKey<PlacedFeature>) = features(Decoration.TOP_LAYER_MODIFICATION, key)


fun createSpawnSettings(creator: MobSpawnSettings.Builder.() -> Unit = {}): MobSpawnSettings.Builder {
    val builder = MobSpawnSettings.Builder()
    creator.invoke(builder)
    return builder
}
