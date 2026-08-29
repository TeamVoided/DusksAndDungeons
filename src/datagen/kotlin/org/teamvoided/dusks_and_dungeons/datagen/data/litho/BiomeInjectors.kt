package org.teamvoided.dusks_and_dungeons.datagen.data.litho


import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter.DEPTH
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter.TEMPERATURE
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.tags.BiomeTags
import org.teamvoided.dusks_and_dungeons.datagen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDBiomes
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDDensityFunctions
import org.teamvoided.dusks_and_dungeons.data.litho.DnDBiomeInjectors as DBInject

object BiomeInjectors : RegistryBootstrapper<BiomeInjector> {

    override fun BootstrapContext<BiomeInjector>.init() {
        autumnBiomes()

        val cave = climateParam(DEPTH, 0.2, 0.9)
        replacePartially(
            DBInject.OVERGROWN_GROTTO,
            BiomeTags.IS_OVERWORLD,
            DnDBiomes.OVERGROWN_GROTTO,
            parameterMap(cave, dfParam(DnDDensityFunctions.OVERGROWN_GROTTO_REGION, 0.7, 2.0)),
            1000
        )
    }

    fun BootstrapContext<BiomeInjector>.autumnBiomes() {
        val temperature = climateParam(TEMPERATURE, -2.0, -0.15)
        val regionCascade = dfParam(DnDDensityFunctions.CASCADE_REGION, 0.5, 2.0)
        val regionSypia = dfParam(DnDDensityFunctions.SYPIA_REGION, 0.5, 2.0)

        val autumn = parameterMap(temperature, regionCascade)
        val golden = parameterMap(temperature, regionSypia)

        replacePartially(
            DBInject.AUTUMN_WOODS,
            DnDBiomeTags.AUTUMN_WOODS, DnDBiomes.AUTUMN_WOODS, autumn, 801
        )
        replacePartially(
            DBInject.AUTUMN_PASTURES,
            DnDBiomeTags.AUTUMN_PASTURES, DnDBiomes.AUTUMN_PASTURES, autumn, 801
        )
        replacePartially(
            DBInject.AUTUMN_CASCADES,
            DnDBiomeTags.AUTUMN_RIVERS, DnDBiomes.AUTUMN_CASCADES, autumn, 801
        )

        replacePartially(
            DBInject.GOLDEN_WOODS,
            DnDBiomeTags.AUTUMN_WOODS, DnDBiomes.GOLDEN_WOODS, golden
        )
        replacePartially(
            DBInject.GOLDEN_PASTURES,
            DnDBiomeTags.AUTUMN_PASTURES, DnDBiomes.GOLDEN_PASTURES, golden
        )
        replacePartially(
            DBInject.GOLDEN_CASCADES,
            DnDBiomeTags.AUTUMN_RIVERS, DnDBiomes.AUTUMN_CASCADES, golden
        )
    }
}