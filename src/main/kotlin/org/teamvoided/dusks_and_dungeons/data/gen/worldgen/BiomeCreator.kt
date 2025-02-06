package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

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
import net.minecraft.world.gen.GenerationStep.Feature.*
import net.minecraft.world.gen.GenerationStep.Feature.LOCAL_MODIFICATIONS as lm2
import net.minecraft.world.gen.GenerationStep.Feature.SURFACE_STRUCTURES as ss4
import net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_ORES as uo6
import net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION as vd9
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.OceanPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes.AUTUMN_CASCADES
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes.AUTUMN_PASTURES
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes.AUTUMN_WETLANDS
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes.AUTUMN_WOODS
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes.GOLDEN_PASTURES
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes.GOLDEN_WOODS

object BiomeCreator {
    //        .grassColor(16366449)
    // (ender) this was by the biomes so IDK have it I guess

    fun boostrap(context: BootstrapContext<Biome>) {
        context.register(AUTUMN_WOODS, createAutumnForest(context))
        context.register(AUTUMN_PASTURES, createAutumnPlains(context))
        context.register(AUTUMN_CASCADES, createAutumnRiver(context))
        context.register(AUTUMN_WETLANDS, createAutumnWetlands(context))

        context.register(GOLDEN_WOODS, createAutumnForest(context, true))
        context.register(GOLDEN_PASTURES, createAutumnPlains(context, true))
    }

    //no access widener?
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

    private fun addAutumnFeatures(generationSettings: GenerationSettings.Builder, golden: Boolean = false) {
        generationSettings.feature(ss4, DnDPlacedFeature.AUTUMN_FARMLANDS)
        generationSettings.feature(uo6, DnDPlacedFeature.ORE_LAPIS_EXTRA)
        generationSettings.feature(lm2, DnDPlacedFeature.OVERGROWN_COBBLESTONE_BOULDER)
        generationSettings.feature(vd9, DnDPlacedFeature.PATCH_PUMPKIN_EXTRA)
        generationSettings.feature(vd9, DnDPlacedFeature.FLOWER_AUTUMN)
        generationSettings.feature(vd9, if (golden) DnDPlacedFeature.FAIRY_RING_RED else DnDPlacedFeature.BLUE_PETALS)
        generationSettings.feature(vd9, DnDPlacedFeature.CROPS_WILD_WHEAT)
        generationSettings.feature(uo6, DnDPlacedFeature.DISK_MUD)
    }

    private fun addAutumnSwampFeatures(generationSettings: GenerationSettings.Builder) {
        generationSettings.feature(vd9, VegetationPlacedFeatures.FLOWER_SWAMP)
        generationSettings.feature(vd9, VegetationPlacedFeatures.PATCH_GRASS_NORMAL)
        generationSettings.feature(vd9, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generationSettings.feature(vd9, VegetationPlacedFeatures.PATCH_WATERLILY)
        generationSettings.feature(vd9, VegetationPlacedFeatures.BROWN_MUSHROOM_SWAMP)
        generationSettings.feature(vd9, VegetationPlacedFeatures.RED_MUSHROOM_SWAMP)
    }


    fun createAutumnForest(c: BootstrapContext<Biome>, golden: Boolean = false): Biome {
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
        generationSettings.feature(uo6, DnDPlacedFeature.DISK_PODZOL)
        generationSettings.feature(
            vd9,
            if (golden) DnDPlacedFeature.GOLDEN_WOODS_VEGETATION else DnDPlacedFeature.AUTUMN_WOODS_VEGETATION
        )
        if (golden) DefaultBiomeFeatures.addForestGrass(generationSettings)
        generationSettings.feature(vd9, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generationSettings.feature(vd9, VegetationPlacedFeatures.BROWN_MUSHROOM_OLD_GROWTH)
        generationSettings.feature(vd9, VegetationPlacedFeatures.RED_MUSHROOM_OLD_GROWTH)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        addAutumnFeatures(generationSettings, golden)
        generationSettings.feature(vd9, DnDPlacedFeature.PATCH_ROSEBUSH)

        return Biome.Builder().temperature(0.25f).downfall(0.8f).effects(
            BiomeEffects.Builder()
                .waterColor(1392275)
                .waterFogColor(329011)
                .fogColor(11587327)
                .grassColor(if (golden) 0xF7C156 else 16224051)
                .foliageColor(if (golden) 0xF7C156 else 15097636)
                .skyColor(getSkyColor(0.25f))
                .moodSound(BiomeMoodSound.CAVE)
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FLOWER_FOREST))
                .build()
        ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }
//grass 16434531 15647087

    fun createAutumnPlains(c: BootstrapContext<Biome>, golden: Boolean = false): Biome {
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
            vd9,
            if (golden) DnDPlacedFeature.GOLDEN_PASTURES_VEGETATION else DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION
        )
        generationSettings.feature(vd9, DnDPlacedFeature.PATCH_TALL_GRASS_AUTUMN_PLAIN)
        generationSettings.feature(vd9, DnDPlacedFeature.PATCH_GRASS_AUTUMN_PLAIN)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        addAutumnFeatures(generationSettings, golden)

        return Biome.Builder().temperature(0.25f).downfall(0.8f).effects(
            BiomeEffects.Builder()
                .waterColor(1392275)
                .waterFogColor(329011)
                .fogColor(11587327)
                .grassColor(if (golden) 15647087 else 15768399)
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
        generationSettings.feature(uo6, DnDPlacedFeature.DISK_PODZOL)
        generationSettings.feature(vd9, DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION)
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettings)
        DefaultBiomeFeatures.addGiantTaigaGrass(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        addAutumnFeatures(generationSettings)
        generationSettings.feature(vd9, OceanPlacedFeatures.SEAGRASS_RIVER)

        return Biome.Builder().temperature(0.25f).downfall(0.8f).effects(
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
//            vegetal_decoration_9,
//            DnDPlacedFeature.AUTUMN_WETLANDS_VEGETATION
//        )
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        addAutumnFeatures(generationSettings)
        DefaultBiomeFeatures.addSwampVegetation(generationSettings)
        generationSettings.feature(vd9, OceanPlacedFeatures.SEAGRASS_SWAMP)
        return Biome.Builder().temperature(0.25f).downfall(0.9f).effects(
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