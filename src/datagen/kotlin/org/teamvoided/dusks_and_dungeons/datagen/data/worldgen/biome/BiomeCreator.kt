package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.biome

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.sounds.Music
import net.minecraft.util.Mth
import net.minecraft.world.level.biome.AmbientMoodSettings
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDBiomes

object BiomeCreator {

    fun boostrap(context: BootstrapContext<Biome>) {
        context.register(DnDBiomes.AUTUMN_WOODS, AutumnBiomeCreator.createAutumnForest(context))
        context.register(DnDBiomes.AUTUMN_PASTURES, AutumnBiomeCreator.createAutumnPlains(context))
        context.register(DnDBiomes.AUTUMN_CASCADES, AutumnBiomeCreator.createAutumnRiver(context))

        context.register(DnDBiomes.GOLDEN_WOODS, AutumnBiomeCreator.createAutumnForest(context, true))
        context.register(DnDBiomes.GOLDEN_PASTURES, AutumnBiomeCreator.createAutumnPlains(context, true))

        context.register(DnDBiomes.OVERGROWN_GROTTO, CaveBiomeCreator.overgrownGrotto(context))
    }

    //no access widener?
    fun getSkyColor(temperature: Float): Int {
        val f = Mth.clamp(temperature / 3f, -1f, 1f)
        return Mth.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1f)
    }

    fun biomeBuild(
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