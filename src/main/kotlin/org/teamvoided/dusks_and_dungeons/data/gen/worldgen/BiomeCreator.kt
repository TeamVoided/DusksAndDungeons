package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.biome.OverworldBiomes
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.sounds.Musics
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.LOCAL_MODIFICATIONS as lm2
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.SURFACE_STRUCTURES as ss4
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES as uo6
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION as vd9

object BiomeCreator {
    //        .grassColor(16366449)
    // (ender) this was by the biomes so IDK have it I guess

    fun boostrap(context: BootstrapContext<Biome>) {
        context.register(DnDBiomes.AUTUMN_WOODS, createAutumnForest(context))
        context.register(DnDBiomes.AUTUMN_PASTURES, createAutumnPlains(context))
        context.register(DnDBiomes.AUTUMN_CASCADES, createAutumnRiver(context))
        context.register(DnDBiomes.AUTUMN_WETLANDS, createAutumnWetlands(context))

        context.register(DnDBiomes.GOLDEN_WOODS, createAutumnForest(context, true))
        context.register(DnDBiomes.GOLDEN_PASTURES, createAutumnPlains(context, true))
    }

    //no access widener?
    private fun getSkyColor(temperature: Float): Int {
        val f = Mth.clamp(temperature / 3.0f, -1.0f, 1.0f)
        return Mth.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f)
    }

    private fun addAutumnAnimals(spawnSettings: MobSpawnSettings.Builder) {
        spawnSettings.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.SHEEP, 4, 4, 4))
        spawnSettings.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.COW, 2, 4, 4))
        spawnSettings.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.CHICKEN, 8, 4, 4))
        spawnSettings.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.RABBIT, 6, 2, 3))
        spawnSettings.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.FOX, 4, 2, 4))
        spawnSettings.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.WOLF, 2, 2, 4))
    }

    private fun addAutumnFeatures(generationSettings: BiomeGenerationSettings.Builder, golden: Boolean = false) {
        generationSettings.addFeature(ss4, DnDPlacedFeature.AUTUMN_FARMLANDS)
        generationSettings.addFeature(uo6, DnDPlacedFeature.ORE_LAPIS_EXTRA)
        generationSettings.addFeature(lm2, DnDPlacedFeature.OVERGROWN_COBBLESTONE_BOULDER)
        generationSettings.addFeature(vd9, DnDPlacedFeature.PATCH_PUMPKIN_EXTRA)
        generationSettings.addFeature(vd9, DnDPlacedFeature.FLOWER_AUTUMN)
        generationSettings.addFeature(
            vd9,
            if (golden) DnDPlacedFeature.FAIRY_RING_RED else DnDPlacedFeature.BLUE_PETALS
        )
        if (!golden) {
            generationSettings.addFeature(vd9, DnDPlacedFeature.CROPS_WILD_WHEAT)
            generationSettings.addFeature(uo6, DnDPlacedFeature.DISK_MUD)
        }
    }

    private fun addAutumnSwampFeatures(generationSettings: BiomeGenerationSettings.Builder) {
        generationSettings.addFeature(vd9, VegetationPlacements.FLOWER_SWAMP)
        generationSettings.addFeature(vd9, VegetationPlacements.PATCH_GRASS_NORMAL)
        generationSettings.addFeature(vd9, VegetationPlacements.PATCH_DEAD_BUSH)
        generationSettings.addFeature(vd9, VegetationPlacements.PATCH_WATERLILY)
        generationSettings.addFeature(vd9, VegetationPlacements.BROWN_MUSHROOM_SWAMP)
        generationSettings.addFeature(vd9, VegetationPlacements.RED_MUSHROOM_SWAMP)
    }


    fun createAutumnForest(c: BootstrapContext<Biome>, golden: Boolean = false): Biome {
        val spawnSettings = MobSpawnSettings.Builder()
        addAutumnAnimals(spawnSettings)
        BiomeDefaultFeatures.commonSpawns(spawnSettings)

        val generationSettings = BiomeGenerationSettings
            .Builder(
                c.lookup(Registries.PLACED_FEATURE),
                c.lookup(Registries.CONFIGURED_CARVER)
            )
        OverworldBiomes.globalOverworldGeneration(generationSettings)
        BiomeDefaultFeatures.addDefaultOres(generationSettings)
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings)
        generationSettings.addFeature(uo6, DnDPlacedFeature.DISK_PODZOL)
        generationSettings.addFeature(
            vd9,
            if (golden) DnDPlacedFeature.GOLDEN_WOODS_VEGETATION else DnDPlacedFeature.AUTUMN_WOODS_VEGETATION
        )
        if (golden) BiomeDefaultFeatures.addForestGrass(generationSettings)
        generationSettings.addFeature(vd9, VegetationPlacements.PATCH_DEAD_BUSH)
        generationSettings.addFeature(vd9, VegetationPlacements.BROWN_MUSHROOM_OLD_GROWTH)
        generationSettings.addFeature(vd9, VegetationPlacements.RED_MUSHROOM_OLD_GROWTH)
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings)
        addAutumnFeatures(generationSettings, golden)
        generationSettings.addFeature(vd9, DnDPlacedFeature.PATCH_ROSEBUSH)

        return Biome.BiomeBuilder().temperature(0.25f).downfall(0.8f).specialEffects(
            BiomeSpecialEffects.Builder()
                .waterColor(1392275)
                .waterFogColor(329011)
                .fogColor(11587327)
                .grassColorOverride(if (golden) 0xFFD859 else 16224051)
                .foliageColorOverride(if (golden) 0xFFD859 else 15097636)
                .skyColor(getSkyColor(0.25f))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST))
                .build()
        ).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }
//grass 16434531 15647087

    fun createAutumnPlains(c: BootstrapContext<Biome>, golden: Boolean = false): Biome {
        val spawnSettings = MobSpawnSettings.Builder()
        addAutumnAnimals(spawnSettings)
        BiomeDefaultFeatures.commonSpawns(spawnSettings)

        val generationSettings = BiomeGenerationSettings
            .Builder(
                c.lookup(Registries.PLACED_FEATURE),
                c.lookup(Registries.CONFIGURED_CARVER)
            )
        OverworldBiomes.globalOverworldGeneration(generationSettings)
        BiomeDefaultFeatures.addDefaultOres(generationSettings)
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings)
        generationSettings.addFeature(
            vd9,
            if (golden) DnDPlacedFeature.GOLDEN_PASTURES_VEGETATION else DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION
        )
        generationSettings.addFeature(vd9, DnDPlacedFeature.PATCH_TALL_GRASS_AUTUMN_PLAIN)
        generationSettings.addFeature(vd9, DnDPlacedFeature.PATCH_GRASS_AUTUMN_PLAIN)
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings)
        addAutumnFeatures(generationSettings, golden)

        return Biome.BiomeBuilder().temperature(0.25f).downfall(0.8f).specialEffects(
            BiomeSpecialEffects.Builder()
                .waterColor(1392275)
                .waterFogColor(329011)
                .fogColor(11587327)
                .grassColorOverride(if (golden) 15647087 else 15768399)
                .foliageColorOverride(15097636)
                .skyColor(getSkyColor(0.25f))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST))
                .build()
        ).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }

    fun createAutumnRiver(context: BootstrapContext<Biome>): Biome {
        val spawnSettings = MobSpawnSettings.Builder()
        spawnSettings.addSpawn(MobCategory.WATER_CREATURE, SpawnerData(EntityType.SQUID, 2, 1, 4))
            .addSpawn(MobCategory.WATER_AMBIENT, SpawnerData(EntityType.SALMON, 5, 1, 5))
        BiomeDefaultFeatures.commonSpawns(spawnSettings)
        spawnSettings.addSpawn(MobCategory.MONSTER, SpawnerData(EntityType.DROWNED, 100, 1, 1))

        val generationSettings = BiomeGenerationSettings
            .Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER)
            )
        OverworldBiomes.globalOverworldGeneration(generationSettings)
        BiomeDefaultFeatures.addDefaultOres(generationSettings)
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings)
        generationSettings.addFeature(uo6, DnDPlacedFeature.DISK_PODZOL)
        generationSettings.addFeature(vd9, DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION)
        BiomeDefaultFeatures.addPlainGrass(generationSettings)
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings)
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings)
        addAutumnFeatures(generationSettings)
        generationSettings.addFeature(vd9, AquaticPlacements.SEAGRASS_RIVER)

        return Biome.BiomeBuilder().temperature(0.25f).downfall(0.8f).specialEffects(
            BiomeSpecialEffects.Builder()
                .waterColor(1392275)
                .waterFogColor(329011)
                .fogColor(11587327)
                .grassColorOverride(15768399)
                .foliageColorOverride(16081176)
                .skyColor(getSkyColor(0.25f))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST))
                .build()
        ).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }

    fun createAutumnWetlands(context: BootstrapContext<Biome>): Biome {
        val spawnSettings = MobSpawnSettings.Builder()
        addAutumnAnimals(spawnSettings)
        BiomeDefaultFeatures.commonSpawns(spawnSettings)
        spawnSettings.addSpawn(MobCategory.MONSTER, SpawnerData(EntityType.SLIME, 1, 1, 1))
        spawnSettings.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.FROG, 10, 2, 5))
        val generationSettings = BiomeGenerationSettings
            .Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER)
            )
        BiomeDefaultFeatures.addFossilDecoration(generationSettings)
        OverworldBiomes.globalOverworldGeneration(generationSettings)
        BiomeDefaultFeatures.addDefaultOres(generationSettings)
        BiomeDefaultFeatures.addSwampClayDisk(generationSettings)
        addAutumnSwampFeatures(generationSettings)
//        generationSettings.feature(
//            vegetal_decoration_9,
//            DnDPlacedFeature.AUTUMN_WETLANDS_VEGETATION
//        )
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings)
        addAutumnFeatures(generationSettings)
        BiomeDefaultFeatures.addSwampExtraVegetation(generationSettings)
        generationSettings.addFeature(vd9, AquaticPlacements.SEAGRASS_SWAMP)
        return Biome.BiomeBuilder().temperature(0.25f).downfall(0.9f).specialEffects(
            BiomeSpecialEffects.Builder()
                .waterColor(4476844)
                .waterFogColor(1383204)
                .fogColor(12638463)
                .skyColor(OverworldBiomes.calculateSkyColor(0.25f))
                .grassColorOverride(16366449)
                .foliageColorOverride(13533233)
//                .grassColorModifier(GrassColorModifier.SWAMP)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP))
                .build()
        ).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }

    /*Generation Steps Reference:
      RAW_GENERATION
      LAKES
      LOCAL_MODIFICATIONS
      UNDERGROUND_STRUCTURES
      SURFACE_STRUCTURES
      STRONGHOLDS
      UNDERGROUND_ORES
      UNDERGROUND_DECORATION
      FLUID_SPRINGS
      VEGETAL_DECORATION
      TOP_LAYER_MODIFICATION
     */

}