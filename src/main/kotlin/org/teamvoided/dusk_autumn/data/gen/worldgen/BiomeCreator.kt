package org.teamvoided.dusk_autumn.data.gen.worldgen

import net.minecraft.client.sound.MusicType
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BiomeMoodSound
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.MathHelper
import net.minecraft.world.biome.*
import net.minecraft.world.biome.SpawnSettings.SpawnEntry
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.OceanPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusk_autumn.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusk_autumn.init.worldgen.DnDBiomes.AUTUMN_CASCADES
import org.teamvoided.dusk_autumn.init.worldgen.DnDBiomes.AUTUMN_PASTURES
import org.teamvoided.dusk_autumn.init.worldgen.DnDBiomes.AUTUMN_WETLANDS
import org.teamvoided.dusk_autumn.init.worldgen.DnDBiomes.AUTUMN_WOODS

object BiomeCreator {
    //        .grassColor(16366449)
    // (ender) this was by the biomes so IDK have it I guess

    fun boostrap(context: BootstrapContext<Biome>) {
        context.register(AUTUMN_WOODS, createAutumnForest(context))
        context.register(AUTUMN_PASTURES, createAutumnPlains(context))
        context.register(AUTUMN_CASCADES, createAutumnRiver(context))
        context.register(AUTUMN_WETLANDS, createAutumnWetlands(context))
    }

    private fun getSkyColor(temperature: Float): Int {
        val f = MathHelper.clamp(temperature / 3.0f, -1.0f, 1.0f)
        return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f)
    }

    private fun addAutumnAnimals(spawnSettings: SpawnSettings.Builder) {
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.SHEEP, 4, 4, 4))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.COW, 2, 4, 4))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.CHICKEN, 8, 4, 4))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 6, 2, 3))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 4, 2, 4))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.WOLF, 2, 2, 4))
    }

    private fun addAutumnFeatures(generationSettings: GenerationSettings.Builder) {
        generationSettings.feature(GenerationStep.Feature.SURFACE_STRUCTURES, DnDPlacedFeature.AUTUMN_FARMLANDS)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, DnDPlacedFeature.ORE_LAPIS_EXTRA)
        generationSettings.feature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            DnDPlacedFeature.OVERGROWN_COBBLESTONE_BOULDER
        )
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DnDPlacedFeature.PATCH_PUMPKIN_EXTRA)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DnDPlacedFeature.FLOWER_AUTUMN)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DnDPlacedFeature.BLUE_PETALS)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DnDPlacedFeature.CROPS_WILD_WHEAT)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, DnDPlacedFeature.DISK_MUD)
    }

    private fun addAutumnSwampFeatures(generationSettings: GenerationSettings.Builder) {
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_SWAMP)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.PATCH_GRASS_NORMAL
        )
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.BROWN_MUSHROOM_SWAMP
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.RED_MUSHROOM_SWAMP
        )
    }


    fun createAutumnForest(c: BootstrapContext<Biome>): Biome {
        val spawnSettings = SpawnSettings.Builder()
        addAutumnAnimals(spawnSettings)
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings)

        val generationSettings = GenerationSettings
            .Builder(
                c.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                c.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
            )
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, DnDPlacedFeature.DISK_PODZOL)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DnDPlacedFeature.AUTUMN_WOODS_VEGETATION
        )
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.BROWN_MUSHROOM_OLD_GROWTH
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.RED_MUSHROOM_OLD_GROWTH
        )
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        addAutumnFeatures(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DnDPlacedFeature.PATCH_ROSEBUSH)

        return Biome.Builder().hasPrecipitation(true).temperature(0.25f).downfall(0.8f).effects(
            BiomeEffects.Builder()
                .waterColor(1392275)
                .waterFogColor(329011)
                .fogColor(11587327)
                .grassColor(16224051)
                .foliageColor(15097636)
                .skyColor(getSkyColor(0.25f))
                .moodSound(BiomeMoodSound.CAVE)
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FLOWER_FOREST))
                .build()
        ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }
//grass 16434531 15647087

    fun createAutumnPlains(c: BootstrapContext<Biome>): Biome {
        val spawnSettings = SpawnSettings.Builder()
        addAutumnAnimals(spawnSettings)
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings)

        val generationSettings = GenerationSettings
            .Builder(
                c.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                c.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
            )
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DnDPlacedFeature.PATCH_TALL_GRASS_AUTUMN_PLAIN
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DnDPlacedFeature.PATCH_GRASS_AUTUMN_PLAIN
        )
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        addAutumnFeatures(generationSettings)

        return Biome.Builder().hasPrecipitation(true).temperature(0.25f).downfall(0.8f).effects(
            BiomeEffects.Builder()
                .waterColor(1392275)
                .waterFogColor(329011)
                .fogColor(11587327)
                .grassColor(15768399)
                .foliageColor(15097636)
                .skyColor(getSkyColor(0.25f))
                .moodSound(BiomeMoodSound.CAVE)
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FLOWER_FOREST))
                .build()
        ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }

    fun createAutumnRiver(context: BootstrapContext<Biome>): Biome {
        val spawnSettings = SpawnSettings.Builder()
        spawnSettings.spawn(SpawnGroup.WATER_CREATURE, SpawnEntry(EntityType.SQUID, 2, 1, 4))
            .spawn(SpawnGroup.WATER_AMBIENT, SpawnEntry(EntityType.SALMON, 5, 1, 5))
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings)
        spawnSettings.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.DROWNED, 100, 1, 1))

        val generationSettings = GenerationSettings
            .Builder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
            )
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, DnDPlacedFeature.DISK_PODZOL)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION
        )
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettings)
        DefaultBiomeFeatures.addGiantTaigaGrass(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        addAutumnFeatures(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_RIVER)

        return Biome.Builder().hasPrecipitation(true).temperature(0.25f).downfall(0.8f).effects(
            BiomeEffects.Builder()
                .waterColor(1392275)
                .waterFogColor(329011)
                .fogColor(11587327)
                .grassColor(15768399)
                .foliageColor(16081176)
                .skyColor(getSkyColor(0.25f))
                .moodSound(BiomeMoodSound.CAVE)
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FLOWER_FOREST))
                .build()
        ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }

    fun createAutumnWetlands(context: BootstrapContext<Biome>): Biome {
        val spawnSettings = SpawnSettings.Builder()
        addAutumnAnimals(spawnSettings)
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings)
        spawnSettings.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 1, 1, 1))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FROG, 10, 2, 5))
        val generationSettings = GenerationSettings
            .Builder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
            )
        DefaultBiomeFeatures.addFossils(generationSettings)
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addClayDisk(generationSettings)
        addAutumnSwampFeatures(generationSettings)
//        generationSettings.feature(
//            GenerationStep.Feature.VEGETAL_DECORATION,
//            DnDPlacedFeature.AUTUMN_WETLANDS_VEGETATION
//        )
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        addAutumnFeatures(generationSettings)
        DefaultBiomeFeatures.addSwampVegetation(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP)
        return Biome.Builder().hasPrecipitation(true).temperature(0.25f).downfall(0.9f).effects(
            BiomeEffects.Builder()
                .waterColor(4476844)
                .waterFogColor(1383204)
                .fogColor(12638463)
                .skyColor(OverworldBiomeCreator.getSkyColor(0.25f))
                .grassColor(16366449)
                .foliageColor(13533233)
//                .grassColorModifier(GrassColorModifier.SWAMP)
                .moodSound(BiomeMoodSound.CAVE)
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_SWAMP))
                .build()
        ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
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