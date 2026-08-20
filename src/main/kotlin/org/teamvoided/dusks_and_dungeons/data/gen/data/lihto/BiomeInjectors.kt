package org.teamvoided.dusks_and_dungeons.data.gen.data.lihto


import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter.*
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.biome.Biomes
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDBiomes
import org.teamvoided.dusks_and_dungeons.data.litho.DnDBiomeInjectors as DBInject

object BiomeInjectors {

    fun init(c: BootstrapContext<BiomeInjector>) = c.boostrap()

    fun BootstrapContext<BiomeInjector>.boostrap() {
        val temperature = climateParam(TEMPERATURE, -1.0, -0.15)
        val humidity = climateParam(HUMIDITY, -1.0, -0.35)

        val woodsRegion = parameterMap(temperature, humidity, climateParam(WEIRDNESS, -2.0, 0.0))
        val pasturesRegion = parameterMap(temperature, humidity, climateParam(WEIRDNESS, 0.0, 1.1))

        replacePartially(DBInject.AUTUMN_WOODS, Biomes.FOREST, DnDBiomes.AUTUMN_WOODS, woodsRegion)
        replacePartially(DBInject.AUTUMN_PASTURES, Biomes.PLAINS, DnDBiomes.AUTUMN_PASTURES, pasturesRegion)
        replacePartially(
            DBInject.AUTUMN_CASCADES, Biomes.RIVER, DnDBiomes.AUTUMN_CASCADES,
            parameterMap(temperature, humidity, climateParam(WEIRDNESS, -0.25, 2.0))
        )
    }

}
