package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.CavePlacements
import net.minecraft.sounds.Musics
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.LOCAL_MODIFICATIONS
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome.BiomeCreator.biomeBuild
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.LOCAL_MODIFICATIONS as lm2
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION as vd9

object CaveBiomeCreator {

    fun overgrownGrotto(context: BootstrapContext<Biome>): Biome {
        val ss = MobSpawnSettings.Builder()
        //.addSpawn(MobCategory.AXOLOTLS, SpawnerData(EntityType.AXOLOTL, 10, 4, 6))
        //.addSpawn(MobCategory.WATER_AMBIENT, SpawnerData(EntityType.TROPICAL_FISH, 25, 8, 8))
        BiomeDefaultFeatures.commonSpawns(ss)
        val gs = BiomeGenerationSettings
            .Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER))
        addGlobalOverworldGeneration(gs)
        BiomeDefaultFeatures.addPlainGrass(gs)
        BiomeDefaultFeatures.addDefaultOres(gs)
        //BiomeDefaultFeatures.addLushCavesSpecialOres(gs)
        BiomeDefaultFeatures.addDefaultSoftDisks(gs)
        addOvergrowthCavesVegetationFeatures(gs)
        val music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES)

        return biomeBuild(ss, gs, music, 0.5f, 0.5f, 0x56C468, 0x17543c, 0x9abe4b)
        //Color(0x58DC6E)
        //Color(0x56C468)
        //original grass = 91DB60, water = 4CBF61
        //vibrant grass = A9FF70, water = 63F97A
        //halfway grass = 9DED6D, water = 58DC6E
    }


    private fun addOvergrowthCavesVegetationFeatures(builder: BiomeGenerationSettings.Builder) {
        builder.addFeature(lm2, DnDPlacedFeature.OVERGROWN_CAVE_BOULDER)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_CAVES_CEILING_VEGETATION)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_HANGING)
        //builder.addFeature(vd9, CavePlacements.LUSH_CAVES_CLAY)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_CAVES_FLOOR_VEGETATION)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_TREE_ROOTED)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_TREE_CAVE_1)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_TREE_CAVE_2)
        builder.addFeature(vd9, DnDPlacedFeature.OVERGROWTH_TREE_CAVE_3)
        //builder.addFeature(vd9, CavePlacements.SPORE_BLOSSOM)
        builder.addFeature(vd9, CavePlacements.CLASSIC_VINES)
    }
}