package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.sounds.Musics
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.MobSpawnSettings
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome.BiomeCreator.biomeBuild
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.LOCAL_MODIFICATIONS as lm2
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.SURFACE_STRUCTURES as ss4
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES as uo6
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION as vd9

object AutumnBiomeCreator {
    //        .grassColor(16366449)
    // (ender) this was by the biomes so IDK have it I guess

    private const val AUTUMN_WATER = 0x164299
    private const val AUTUMN_WATER_FOG = 0x050533
    private const val GOLDEN_FOLIAGE = 0xFFC759
    private const val AUTUMN_PLAINS_GRASS = 0xF09B4F

    private fun addAutumnAnimals(spawnSettings: MobSpawnSettings.Builder) {
        spawnSettings.addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.SHEEP, 4, 4, 4))
        spawnSettings.addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.COW, 2, 4, 4))
        spawnSettings.addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 8, 4, 4))
        spawnSettings.addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.RABBIT, 6, 2, 3))
        spawnSettings.addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.FOX, 4, 2, 4))
        spawnSettings.addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 2, 4))
    }

    private fun addAutumnFeatures(gs: BiomeGenerationSettings.Builder, golden: Boolean = false) {
        gs.addFeature(uo6, DnDPlacedFeature.ORE_LAPIS_EXTRA)
        gs.addFeature(lm2, DnDPlacedFeature.OVERGROWN_BOULDER)
        gs.addFeature(vd9, if (golden) DnDPlacedFeature.LANTERN_PUMPKIN_EXTRA else DnDPlacedFeature.PUMPKIN_EXTRA)
        gs.addFeature(vd9, DnDPlacedFeature.FLOWER_AUTUMN)
        gs.addFeature(vd9, if (golden) DnDPlacedFeature.FAIRY_RING_RED else DnDPlacedFeature.ORANGE_PETALS)
    }

     fun createAutumnForest(c: BootstrapContext<Biome>, golden: Boolean = false): Biome {
        val ss = MobSpawnSettings.Builder()
        addAutumnAnimals(ss)
        BiomeDefaultFeatures.commonSpawns(ss)

        val gs =
            BiomeGenerationSettings.Builder(c.lookup(Registries.PLACED_FEATURE), c.lookup(Registries.CONFIGURED_CARVER))
        addGlobalOverworldGeneration(gs)
        BiomeDefaultFeatures.addDefaultOres(gs)
        BiomeDefaultFeatures.addDefaultSoftDisks(gs)
        gs.addFeature(
            vd9,
            if (golden) DnDPlacedFeature.GOLDEN_WOODS_VEGETATION else DnDPlacedFeature.AUTUMN_WOODS_VEGETATION
        )
        if (golden) BiomeDefaultFeatures.addForestGrass(gs)
        gs.addFeature(vd9, VegetationPlacements.PATCH_DEAD_BUSH)
        gs.addFeature(vd9, VegetationPlacements.BROWN_MUSHROOM_OLD_GROWTH)
        gs.addFeature(vd9, VegetationPlacements.RED_MUSHROOM_OLD_GROWTH)
        BiomeDefaultFeatures.addDefaultMushrooms(gs)
        if (golden) gs.addFeature(vd9, VegetationPlacements.PATCH_SUGAR_CANE)
        else BiomeDefaultFeatures.addDefaultExtraVegetation(gs)
        gs.addFeature(ss4, DnDPlacedFeature.AUTUMN_FARMLANDS)
        addAutumnFeatures(gs, golden)
        gs.addFeature(vd9, DnDPlacedFeature.PATCH_ROSEBUSH)

        val music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST)
        return biomeBuild(
            ss,
            gs,
            music,
            0.25f,
            0.8f,
            AUTUMN_WATER,
            AUTUMN_WATER_FOG,
            if (golden) GOLDEN_FOLIAGE else 16224051,
            if (golden) GOLDEN_FOLIAGE else 15097636
        )
    }
//grass 16434531 15647087

     fun createAutumnPlains(c: BootstrapContext<Biome>, golden: Boolean = false): Biome {
        val ss = MobSpawnSettings.Builder()
        addAutumnAnimals(ss)
        BiomeDefaultFeatures.commonSpawns(ss)

        val gs =
            BiomeGenerationSettings.Builder(c.lookup(Registries.PLACED_FEATURE), c.lookup(Registries.CONFIGURED_CARVER))
        addGlobalOverworldGeneration(gs)
        BiomeDefaultFeatures.addDefaultOres(gs)
        BiomeDefaultFeatures.addDefaultSoftDisks(gs)
        gs.addFeature(
            vd9,
            if (golden) DnDPlacedFeature.GOLDEN_PASTURES_VEGETATION else DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION
        )
        gs.addFeature(vd9, DnDPlacedFeature.PATCH_TALL_GRASS_AUTUMN_PLAIN)
        gs.addFeature(vd9, DnDPlacedFeature.PATCH_GRASS_AUTUMN_PLAIN)
        BiomeDefaultFeatures.addDefaultMushrooms(gs)
        if (golden) gs.addFeature(vd9, VegetationPlacements.PATCH_SUGAR_CANE)
        else BiomeDefaultFeatures.addDefaultExtraVegetation(gs)
        gs.addFeature(ss4, DnDPlacedFeature.AUTUMN_FARMLANDS)
        addAutumnFeatures(gs, golden)
        gs.addFeature(vd9, if (golden) DnDPlacedFeature.WILD_WHEAT_FIELD else DnDPlacedFeature.WILD_WHEAT)

        val music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST)
        return biomeBuild(
            ss,
            gs,
            music,
            0.25f,
            0.8f,
            AUTUMN_WATER,
            AUTUMN_WATER_FOG,
            if (golden) 15647087 else AUTUMN_PLAINS_GRASS,
            if (golden) GOLDEN_FOLIAGE else 15097636
        )
    }

     fun createAutumnRiver(context: BootstrapContext<Biome>): Biome {
        val ss = MobSpawnSettings.Builder()
            .addSpawn(MobCategory.WATER_CREATURE, MobSpawnSettings.SpawnerData(EntityType.SQUID, 2, 1, 4))
            .addSpawn(MobCategory.WATER_AMBIENT, MobSpawnSettings.SpawnerData(EntityType.SALMON, 5, 1, 5))
            .addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.DROWNED, 100, 1, 1))
        BiomeDefaultFeatures.commonSpawns(ss)

        val gs = BiomeGenerationSettings
            .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER))
        addGlobalOverworldGeneration(gs)
        BiomeDefaultFeatures.addDefaultOres(gs)
        BiomeDefaultFeatures.addDefaultSoftDisks(gs)
        gs.addFeature(vd9, DnDPlacedFeature.GOLDEN_PASTURES_VEGETATION)
        BiomeDefaultFeatures.addPlainGrass(gs)
        BiomeDefaultFeatures.addGiantTaigaVegetation(gs)
        BiomeDefaultFeatures.addDefaultMushrooms(gs)
        gs.addFeature(vd9, VegetationPlacements.PATCH_SUGAR_CANE)
        addAutumnFeatures(gs)
        gs.addFeature(uo6, DnDPlacedFeature.DISK_MUD)
        gs.addFeature(vd9, AquaticPlacements.SEAGRASS_RIVER)

        val music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST)
        return biomeBuild(
            ss,
            gs,
            music,
            0.25f,
            0.8f,
            AUTUMN_WATER,
            AUTUMN_WATER_FOG,
            0xF8BA54,
            0xCC833B //CC8F3B
        )
    }

}