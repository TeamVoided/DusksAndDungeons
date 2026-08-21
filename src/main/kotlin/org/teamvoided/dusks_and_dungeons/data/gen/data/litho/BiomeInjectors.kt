package org.teamvoided.dusks_and_dungeons.data.gen.data.litho


import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter.*
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.levelgen.DensityFunction
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.DensityFunctionCreator.dense
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDDensityFunctions
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes
import org.teamvoided.dusks_and_dungeons.data.litho.DnDBiomeInjectors as DBInject

object BiomeInjectors {

    fun init(c: BootstrapContext<BiomeInjector>) = c.boostrap()

    fun BootstrapContext<BiomeInjector>.boostrap() {

        val temperature = climateParam(TEMPERATURE, -1.0, -0.15)
        val regionCascade = dfParam(DnDDensityFunctions.CASCADE_REGION, 0.5, 2.0)
        val regionSypia = dfParam(DnDDensityFunctions.SYPIA_REGION, 0.5, 2.0)
        val weirdPos = climateParam(WEIRDNESS, 0.0, 2.0)
        val weirdNeg = climateParam(WEIRDNESS, -2.0, 0.0)
        val cave = climateParam(DEPTH, -0.9, -0.2)

        val aWoodsRegion = parameterMap(temperature, regionCascade, weirdNeg)
        val aPasturesRegion = parameterMap(temperature, regionCascade, weirdPos)
        val gWoodsRegion = parameterMap(temperature, regionSypia, weirdNeg)
        val gPasturesRegion = parameterMap(temperature, regionSypia, weirdPos)

        replacePartially(DBInject.AUTUMN_WOODS, DnDBiomeTags.AUTUMN_LANDS, DnDBiomes.AUTUMN_WOODS, aWoodsRegion, 801)
        replacePartially(
            DBInject.AUTUMN_PASTURES,
            DnDBiomeTags.AUTUMN_LANDS,
            DnDBiomes.AUTUMN_PASTURES,
            aPasturesRegion,
            801
        )
        replacePartially(
            DBInject.AUTUMN_CASCADES,
            DnDBiomeTags.AUTUMN_RIVERS,
            DnDBiomes.AUTUMN_CASCADES,
            parameterMap(temperature, regionCascade),
            801
        )
        replacePartially(DBInject.GOLDEN_WOODS, DnDBiomeTags.AUTUMN_LANDS, DnDBiomes.GOLDEN_WOODS, gWoodsRegion)
        replacePartially(
            DBInject.GOLDEN_PASTURES,
            DnDBiomeTags.AUTUMN_LANDS,
            DnDBiomes.GOLDEN_PASTURES,
            gPasturesRegion
        )
        replacePartially(
            DBInject.GOLDEN_CASCADES,
            DnDBiomeTags.AUTUMN_RIVERS,
            DnDBiomes.AUTUMN_CASCADES,
            parameterMap(temperature, regionSypia)
        )


        replacePartially(
            DBInject.OVERGROWN_GROTTO,
            BiomeTags.IS_OVERWORLD,
            DnDBiomes.OVERGROWN_GROTTO,
            parameterMap(cave, dfParam(DnDDensityFunctions.OVERGROWN_GROTTO_REGION, 0.25, 2.0)),
            1000
        )

    }

    fun BootstrapContext<BiomeInjector>.dfParam(
        df: ResourceKey<DensityFunction>,
        low: Double,
        high: Double
    ): WorldPlacement =
        dfParam(this.dense(df), low, high)
}