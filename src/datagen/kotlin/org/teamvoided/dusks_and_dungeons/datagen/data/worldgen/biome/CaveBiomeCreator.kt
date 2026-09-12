package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome

import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.CavePlacements
import net.minecraft.sounds.Musics
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome.BiomeCreator.biomeBuild

object CaveBiomeCreator {

    fun overgrownGrotto(ctx: BootstrapContext<Biome>): Biome {
        val spawns = createSpawnSettings {
//            addSpawn(MobCategory.AXOLOTLS, SpawnerData(EntityType.AXOLOTL, 10, 4, 6))
//            addSpawn(MobCategory.WATER_AMBIENT, SpawnerData(EntityType.TROPICAL_FISH, 25, 8, 8))
            BiomeDefaultFeatures.commonSpawns(this)
        }
        val generation = ctx.createGenerationSettings {
            addGlobalOverworldGeneration()
            BiomeDefaultFeatures.addPlainGrass(this)
            BiomeDefaultFeatures.addDefaultOres(this)
            //BiomeDefaultFeatures.addLushCavesSpecialOres(this)
            BiomeDefaultFeatures.addDefaultSoftDisks(this)
            addOvergrowthCavesVegetationFeatures()
        }
        val music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES)

        // TODO For Dusk: extract colors as constants
        return biomeBuild(spawns, generation, music, 0.5f, 0.5f, 0x56C468, 0x17543c, 0x9abe4b)
        //Color(0x58DC6E)
        //Color(0x56C468)
        //original grass = 91DB60, water = 4CBF61
        //vibrant grass = A9FF70, water = 63F97A
        //halfway grass = 9DED6D, water = 58DC6E
    }


    fun BiomeGenerationSettings.Builder.addOvergrowthCavesVegetationFeatures() {
        add2LocalModifications(DnDPlacedFeature.OVERGROWN_CAVE_BOULDER)
        add9VegetalDecoration(DnDPlacedFeature.OVERGROWTH_CAVES_CEILING_VEGETATION)
        add9VegetalDecoration(DnDPlacedFeature.OVERGROWTH_HANGING)
        //builder.add9VegetalDecoration(CavePlacements.LUSH_CAVES_CLAY)
        add9VegetalDecoration(DnDPlacedFeature.OVERGROWTH_CAVES_FLOOR_VEGETATION)
        add9VegetalDecoration(DnDPlacedFeature.OVERGROWTH_TREE_ROOTED)
        add9VegetalDecoration(DnDPlacedFeature.OVERGROWTH_TREE_CAVE_1)
        add9VegetalDecoration(DnDPlacedFeature.OVERGROWTH_TREE_CAVE_2)
        add9VegetalDecoration(DnDPlacedFeature.OVERGROWTH_TREE_CAVE_3)
        //builder.add9VegetalDecoration(CavePlacements.SPORE_BLOSSOM)
        add9VegetalDecoration(CavePlacements.CLASSIC_VINES)
    }

}