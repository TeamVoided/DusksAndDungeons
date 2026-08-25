package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.biome.OverworldBiomes
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.data.worldgen.placement.CavePlacements
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.sounds.Music
import net.minecraft.sounds.Musics
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.GenerationStep
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

        context.register(DnDBiomes.GOLDEN_WOODS, createAutumnForest(context, true))
        context.register(DnDBiomes.GOLDEN_PASTURES, createAutumnPlains(context, true))

        context.register(DnDBiomes.OVERGROWN_GROTTO, overgrownGrotto(context))
    }

    //no access widener?
    private fun getSkyColor(temperature: Float): Int {
        val f = Mth.clamp(temperature / 3.0f, -1f, 1f)
        return Mth.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1f)
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
        generationSettings.addFeature(lm2, DnDPlacedFeature.OVERGROWN_BOULDER)
        generationSettings.addFeature(
            vd9,
            if (golden) DnDPlacedFeature.PATCH_LANTERN_PUMPKIN_EXTRA else DnDPlacedFeature.PATCH_PUMPKIN_EXTRA
        )
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

    private fun createAutumnForest(c: BootstrapContext<Biome>, golden: Boolean = false): Biome {
        val spawnSettings = MobSpawnSettings.Builder()
        addAutumnAnimals(spawnSettings)
        BiomeDefaultFeatures.commonSpawns(spawnSettings)

        val generationSettings = BiomeGenerationSettings
            .Builder(c.lookup(Registries.PLACED_FEATURE), c.lookup(Registries.CONFIGURED_CARVER))
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
        if (golden) generationSettings.addFeature(vd9, VegetationPlacements.PATCH_SUGAR_CANE)
        else BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings)
        addAutumnFeatures(generationSettings, golden)
        generationSettings.addFeature(vd9, DnDPlacedFeature.PATCH_ROSEBUSH)

        val music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST)
        return biomeBuild(
            spawnSettings,
            generationSettings,
            music,
            0.25f,
            0.8f,
            1392275,
            329011,
            if (golden) 0xFFD859 else 16224051,
            if (golden) 0xFFD859 else 15097636
        )
    }
//grass 16434531 15647087

    private fun createAutumnPlains(c: BootstrapContext<Biome>, golden: Boolean = false): Biome {
        val spawnSettings = MobSpawnSettings.Builder()
        addAutumnAnimals(spawnSettings)
        BiomeDefaultFeatures.commonSpawns(spawnSettings)

        val generationSettings = BiomeGenerationSettings
            .Builder(c.lookup(Registries.PLACED_FEATURE), c.lookup(Registries.CONFIGURED_CARVER))
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
        if (golden) generationSettings.addFeature(vd9, VegetationPlacements.PATCH_SUGAR_CANE)
        else BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings)
        addAutumnFeatures(generationSettings, golden)
        if (golden) generationSettings.addFeature(vd9, DnDPlacedFeature.WILD_WHEAT_FIELD)

        val music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST)
        return biomeBuild(
            spawnSettings,
            generationSettings,
            music,
            0.25f,
            0.8f,
            1392275,
            329011,
            if (golden) 15647087 else 15768399,
            if (golden) 0xFFD859 else 15097636
        )
    }

    private fun createAutumnRiver(context: BootstrapContext<Biome>): Biome {
        val spawnSettings = MobSpawnSettings.Builder()
            .addSpawn(MobCategory.WATER_CREATURE, SpawnerData(EntityType.SQUID, 2, 1, 4))
            .addSpawn(MobCategory.WATER_AMBIENT, SpawnerData(EntityType.SALMON, 5, 1, 5))
            .addSpawn(MobCategory.MONSTER, SpawnerData(EntityType.DROWNED, 100, 1, 1))
        BiomeDefaultFeatures.commonSpawns(spawnSettings)

        val generationSettings = BiomeGenerationSettings
            .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER))
        OverworldBiomes.globalOverworldGeneration(generationSettings)
        BiomeDefaultFeatures.addDefaultOres(generationSettings)
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings)
        generationSettings.addFeature(uo6, DnDPlacedFeature.DISK_PODZOL)
        generationSettings.addFeature(vd9, DnDPlacedFeature.GOLDEN_PASTURES_VEGETATION)
        BiomeDefaultFeatures.addPlainGrass(generationSettings)
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings)
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings)
        generationSettings.addFeature(vd9, VegetationPlacements.PATCH_SUGAR_CANE)
        addAutumnFeatures(generationSettings)
        generationSettings.addFeature(vd9, AquaticPlacements.SEAGRASS_RIVER)

        val music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST)
        return biomeBuild(spawnSettings, generationSettings, music, 0.25f, 0.8f, 1392275, 329011, 15768399, 16081176)
    }


    private fun overgrownGrotto(context: BootstrapContext<Biome>): Biome {
        val spawnSettings = MobSpawnSettings.Builder()
        //.addSpawn(MobCategory.AXOLOTLS, SpawnerData(EntityType.AXOLOTL, 10, 4, 6))
        //.addSpawn(MobCategory.WATER_AMBIENT, SpawnerData(EntityType.TROPICAL_FISH, 25, 8, 8))
        BiomeDefaultFeatures.commonSpawns(spawnSettings)
        val generationSettings = BiomeGenerationSettings
            .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER))
        OverworldBiomes.globalOverworldGeneration(generationSettings)
        BiomeDefaultFeatures.addPlainGrass(generationSettings)
        BiomeDefaultFeatures.addDefaultOres(generationSettings)
        //BiomeDefaultFeatures.addLushCavesSpecialOres(generationSettings)
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings)
        addOvergrowthCavesVegetationFeatures(generationSettings)
        val music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES)

        return biomeBuild(spawnSettings, generationSettings, music, 0.5f, 0.5f, 0x58DC6E, 0x17543c, 0x9abe4b)

        //original grass = 91DB60, water = 4CBF61
        //vibrant grass = A9FF70, water = 63F97A
        //halfway grass = 9DED6D, water = 58DC6E
    }

    private fun biomeBuild(
        ss: MobSpawnSettings.Builder,
        gs: BiomeGenerationSettings.Builder,
        music: Music,
        temperature: Float,
        downfall: Float,
        waterColor: Int,
        waterFogColor: Int,
        grassOveride: Int = -1,
        foliageOveride: Int = -1,
    ): Biome {
        val special = BiomeSpecialEffects.Builder()
            .waterColor(waterColor)
            .waterFogColor(waterFogColor)
            .fogColor(11587327)
            .skyColor(getSkyColor(temperature))
            .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
            .backgroundMusic(music)
        if (grassOveride >= 0) special.grassColorOverride(grassOveride)
        if (foliageOveride >= 0) special.foliageColorOverride(grassOveride)
        return Biome.BiomeBuilder().temperature(temperature).downfall(downfall).specialEffects(special.build())
            .mobSpawnSettings(ss.build()).generationSettings(gs.build()).build()
    }

    private fun addOvergrowthCavesVegetationFeatures(builder: BiomeGenerationSettings.Builder) {
        builder.addFeature(lm2, DnDPlacedFeature.OVERGROWN_CAVE_BOULDER)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_CAVES_CEILING_VEGETATION)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_HANGING)
        //builder.addFeature(vd9, CavePlacements.LUSH_CAVES_CLAY)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_CAVES_FLOOR_VEGETATION)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_TREE_ROOTED)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_CAVES_TREES)
        //builder.addFeature(vd9, CavePlacements.SPORE_BLOSSOM)
        builder.addFeature(vd9, CavePlacements.CLASSIC_VINES)
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