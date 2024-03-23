package org.teamvoided.dusk_autumn.init.worldgen

import net.minecraft.client.sound.MusicType
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BiomeMoodSound
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.MathHelper
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.biome.SpawnSettings
import net.minecraft.world.biome.SpawnSettings.SpawnEntry
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.MiscPlacedFeatures
import net.minecraft.world.gen.feature.OceanPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskBiomes {
    val AUTUMN_WOODS = create("autumn_woods")
    val AUTUMN_PASTURES = create("autumn_pastures")
    val AUTUMN_CASCADES = create("autumn_cascades")

    private fun addBasicFeatures(generationSettings: GenerationSettings.Builder) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings)
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings)
        DefaultBiomeFeatures.addDungeons(generationSettings)
        DefaultBiomeFeatures.addUndergroundVariety(generationSettings)
        DefaultBiomeFeatures.addSprings(generationSettings)
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings)
    }

    private fun getSkyColor(temperature: Float): Int {
        var f = temperature / 3.0f
        f = MathHelper.clamp(f, -1.0f, 1.0f)
        return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f)
    }

    fun init() {}
    fun create(id: String): RegistryKey<Biome?> {
        return RegistryKey.of(RegistryKeys.BIOME, id(id))
    }

    fun boostrap(context: BootstrapContext<Biome?>) {
        context.register(AUTUMN_WOODS, createAutumnForestBiomes(context))
        context.register(AUTUMN_PASTURES, createAutumnPlainsBiomes(context))
        context.register(AUTUMN_CASCADES, createAutumnRiverBiomes(context))
    }

    fun createAutumnForestBiomes(context: BootstrapContext<Biome?>): Biome {
        val spawnSettings = SpawnSettings.Builder()
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 8, 2, 3))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 4, 2, 4))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.WOLF, 1, 2, 4))
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings)
        val generationSettings = GenerationSettings.Builder(
            context.lookup(RegistryKeys.PLACED_FEATURE),
            context.lookup(RegistryKeys.CONFIGURED_CARVER)
        )
        addBasicFeatures(generationSettings)
        generationSettings.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, DuskPlacedFeature.COBBLESTONE_ROCK_FOREST)
//        DefaultBiomeFeatures.addLargeFerns(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeature.AUTUMN_WOODS_VEGETATION)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeature.PATCH_ROSEBUSH)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeature.BLUE_PETALS)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, DuskPlacedFeature.ORE_LAPIS_EXTRA)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, DuskPlacedFeature.DISK_PODZOL)
//        DefaultBiomeFeatures.addGiantTaigaGrass(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
//        DefaultBiomeFeatures.addCommonBerries(generationSettings)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FLOWER_FOREST)
        return Biome.Builder().hasPrecipitation(true).temperature(0.25f).downfall(0.8f).effects(
            BiomeEffects.Builder().waterColor(1392275).waterFogColor(329011).fogColor(11587327).grassColor(16434531)
                .foliageColor(14772013)
                .skyColor(getSkyColor(0.25f)).moodSound(BiomeMoodSound.CAVE).music(musicSound).build()
        ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }
//grass 16434531 15647087

    fun createAutumnPlainsBiomes(context: BootstrapContext<Biome?>): Biome {
        val spawnSettings = SpawnSettings.Builder()
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 8, 2, 3))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 4, 2, 4))
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings)
        val generationSettings = GenerationSettings.Builder(
            context.lookup(RegistryKeys.PLACED_FEATURE),
            context.lookup(RegistryKeys.CONFIGURED_CARVER)
        )
        addBasicFeatures(generationSettings)
        generationSettings.feature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            DuskPlacedFeature.COBBLESTONE_ROCK_PLAINS
        )
        DefaultBiomeFeatures.addLargeFerns(generationSettings)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DuskPlacedFeature.AUTUMN_PASTURES_VEGETATION
        )
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeature.BLUE_PETALS)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, DuskPlacedFeature.ORE_LAPIS_EXTRA)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, DuskPlacedFeature.DISK_PODZOL)
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_PLAINS)
        DefaultBiomeFeatures.addGiantTaigaGrass(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        DefaultBiomeFeatures.addCommonBerries(generationSettings)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FLOWER_FOREST)
        return Biome.Builder().hasPrecipitation(true).temperature(0.25f).downfall(0.8f).effects(
            BiomeEffects.Builder().waterColor(1392275).waterFogColor(329011).fogColor(11587327).grassColor(15647087)
                .foliageColor(14906688)
                .skyColor(getSkyColor(0.25f)).moodSound(BiomeMoodSound.CAVE).music(musicSound).build()
        ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }

    fun createAutumnRiverBiomes(context: BootstrapContext<Biome?>): Biome {
        val spawnSettings = SpawnSettings.Builder()
        spawnSettings.spawn(SpawnGroup.WATER_CREATURE, SpawnEntry(EntityType.SQUID, 2, 1, 4))
            .spawn(SpawnGroup.WATER_AMBIENT, SpawnEntry(EntityType.SALMON, 5, 1, 5));
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);
        spawnSettings.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.DROWNED, 100, 1, 1));
        val generationSettings = GenerationSettings.Builder(
            context.lookup(RegistryKeys.PLACED_FEATURE),
            context.lookup(RegistryKeys.CONFIGURED_CARVER)
        )
        addBasicFeatures(generationSettings)
        generationSettings.feature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            DuskPlacedFeature.COBBLESTONE_ROCK_PLAINS
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DuskPlacedFeature.AUTUMN_PASTURES_VEGETATION
        )
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, DuskPlacedFeature.DISK_PODZOL)
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_PLAINS)
        DefaultBiomeFeatures.addGiantTaigaGrass(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        DefaultBiomeFeatures.addCommonBerries(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_RIVER)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FLOWER_FOREST)
        return Biome.Builder().hasPrecipitation(true).temperature(0.25f).downfall(0.8f).effects(
            BiomeEffects.Builder().waterColor(1392275).waterFogColor(329011).fogColor(11587327).grassColor(15647087)
                .foliageColor(14906688)
                .skyColor(getSkyColor(0.25f)).moodSound(BiomeMoodSound.CAVE).music(musicSound).build()
        ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
    }

//    Generation Steps Reference:
//      RAW_GENERATION
//      LAKES
//      LOCAL_MODIFICATIONS
//      UNDERGROUND_STRUCTURES
//      SURFACE_STRUCTURES
//      STRONGHOLDS
//      UNDERGROUND_ORES
//      UNDERGROUND_DECORATION
//      FLUID_SPRINGS
//      VEGETAL_DECORATION
//      TOP_LAYER_MODIFICATION

}