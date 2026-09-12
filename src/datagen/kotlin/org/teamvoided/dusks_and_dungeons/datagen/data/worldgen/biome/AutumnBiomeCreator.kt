package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome

import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.sounds.Music
import net.minecraft.sounds.Musics
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.MobSpawnSettings
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome.BiomeCreator.biomeBuild

object AutumnBiomeCreator {

    private const val AUTUMN_WATER = 0x164299
    private const val AUTUMN_WATER_FOG = 0x050533
    private const val GOLDEN_FOLIAGE = 0xFFC759
    private const val AUTUMN_PLAINS_GRASS = 0xF09B4F
    // TODO For Dusk: Make all biome colors be constants

    val MUSIC: Music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST)

    fun createAutumnForest(ctx: BootstrapContext<Biome>, golden: Boolean = false): Biome {
        val spawns = createSpawnSettings {
            addAutumnAnimals()
            BiomeDefaultFeatures.commonSpawns(this)
        }

        val generation = ctx.createGenerationSettings {
            addGlobalOverworldGeneration()
            BiomeDefaultFeatures.addDefaultOres(this)
            BiomeDefaultFeatures.addDefaultSoftDisks(this)
            add9VegetalDecoration(
                if (golden) DnDPlacedFeature.GOLDEN_WOODS_VEGETATION else DnDPlacedFeature.AUTUMN_WOODS_VEGETATION
            )
            if (golden)
                BiomeDefaultFeatures.addForestGrass(this)
            add9VegetalDecoration(VegetationPlacements.BROWN_MUSHROOM_OLD_GROWTH)
            add9VegetalDecoration(VegetationPlacements.RED_MUSHROOM_OLD_GROWTH)
            add9VegetalDecoration(VegetationPlacements.PATCH_DEAD_BUSH_2) //I LOVE FEATURE ORDER CYCLES!!!
            BiomeDefaultFeatures.addDefaultMushrooms(this)
            if (golden)
                add9VegetalDecoration(VegetationPlacements.PATCH_SUGAR_CANE)
            else
                BiomeDefaultFeatures.addDefaultExtraVegetation(this)
            add4SurfaceStructures(DnDPlacedFeature.AUTUMN_FARMLANDS)
            addAutumnFeatures(golden)
            add9VegetalDecoration(DnDPlacedFeature.PATCH_ROSEBUSH)
        }

        return biomeBuild(
            spawns, generation, MUSIC,
            0.25f, 0.8f,
            AUTUMN_WATER, AUTUMN_WATER_FOG,
            if (golden) GOLDEN_FOLIAGE else 16224051,
            if (golden) GOLDEN_FOLIAGE else 15097636
        )
    }
//grass 16434531 15647087

    fun createAutumnPlains(ctx: BootstrapContext<Biome>, golden: Boolean = false): Biome {
        val spawns = createSpawnSettings {
            addAutumnAnimals()
            BiomeDefaultFeatures.commonSpawns(this)
        }

        val generation = ctx.createGenerationSettings {
            addGlobalOverworldGeneration()
            BiomeDefaultFeatures.addDefaultOres(this)
            BiomeDefaultFeatures.addDefaultSoftDisks(this)
            add9VegetalDecoration(
                if (golden) DnDPlacedFeature.GOLDEN_PASTURES_VEGETATION else DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION
            )
            add9VegetalDecoration(DnDPlacedFeature.PATCH_TALL_GRASS_AUTUMN_PLAIN)
            add9VegetalDecoration(DnDPlacedFeature.PATCH_GRASS_AUTUMN_PLAIN)
            add9VegetalDecoration(VegetationPlacements.PATCH_DEAD_BUSH_2)
            BiomeDefaultFeatures.addDefaultMushrooms(this)
            if (golden)
                add9VegetalDecoration(VegetationPlacements.PATCH_SUGAR_CANE)
            else BiomeDefaultFeatures.addDefaultExtraVegetation(this)
            add4SurfaceStructures(DnDPlacedFeature.AUTUMN_FARMLANDS)
            addAutumnFeatures(golden)
            add9VegetalDecoration(if (golden) DnDPlacedFeature.WILD_WHEAT_FIELD else DnDPlacedFeature.WILD_WHEAT)
        }

        return biomeBuild(
            spawns, generation, MUSIC,
            0.25f, 0.8f,
            AUTUMN_WATER, AUTUMN_WATER_FOG,
            if (golden) 15647087 else AUTUMN_PLAINS_GRASS,
            if (golden) GOLDEN_FOLIAGE else 15097636
        )
    }

    fun createAutumnRiver(ctx: BootstrapContext<Biome>): Biome {
        val spawns = createSpawnSettings {
            addSpawn(MobCategory.WATER_CREATURE, MobSpawnSettings.SpawnerData(EntityType.SQUID, 2, 1, 4))
            addSpawn(MobCategory.WATER_AMBIENT, MobSpawnSettings.SpawnerData(EntityType.SALMON, 5, 1, 5))
            addSpawn(MobCategory.MONSTER, MobSpawnSettings.SpawnerData(EntityType.DROWNED, 100, 1, 1))
            BiomeDefaultFeatures.commonSpawns(this)
        }

        val generation = ctx.createGenerationSettings {
            addGlobalOverworldGeneration()
            BiomeDefaultFeatures.addDefaultOres(this)
            BiomeDefaultFeatures.addDefaultSoftDisks(this)
            add9VegetalDecoration(DnDPlacedFeature.GOLDEN_PASTURES_VEGETATION)
            BiomeDefaultFeatures.addPlainGrass(this)
            BiomeDefaultFeatures.addGiantTaigaVegetation(this)
            BiomeDefaultFeatures.addDefaultMushrooms(this)
            add9VegetalDecoration(VegetationPlacements.PATCH_SUGAR_CANE)
            addAutumnFeatures()
            add6UndergroundOres(DnDPlacedFeature.DISK_MUD)
            add9VegetalDecoration(AquaticPlacements.SEAGRASS_RIVER)
        }

        return biomeBuild(
            spawns, generation, MUSIC,
            0.25f, 0.8f,
            AUTUMN_WATER, AUTUMN_WATER_FOG,
            0xF8BA54, 0xCC833B //CC8F3B
        )
    }

    fun MobSpawnSettings.Builder.addAutumnAnimals() {
        addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.SHEEP, 4, 4, 4))
        addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.COW, 2, 4, 4))
        addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 8, 4, 4))
        addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.RABBIT, 6, 2, 3))
        addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.FOX, 4, 2, 4))
        addSpawn(MobCategory.CREATURE, MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 2, 4))
    }

    fun BiomeGenerationSettings.Builder.addAutumnFeatures(golden: Boolean = false) {
        add6UndergroundOres(DnDPlacedFeature.ORE_LAPIS_EXTRA)
        add2LocalModifications(DnDPlacedFeature.OVERGROWN_BOULDER)
        add9VegetalDecoration(if (golden) DnDPlacedFeature.LANTERN_PUMPKIN_EXTRA else DnDPlacedFeature.PUMPKIN_EXTRA)
        add9VegetalDecoration(DnDPlacedFeature.FLOWER_AUTUMN)
        add9VegetalDecoration(if (golden) DnDPlacedFeature.FAIRY_RING_RED else DnDPlacedFeature.ORANGE_PETALS)
    }

}