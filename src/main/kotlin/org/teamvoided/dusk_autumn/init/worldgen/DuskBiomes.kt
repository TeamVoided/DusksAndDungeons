package org.teamvoided.dusk_autumn.init.worldgen

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets
import com.terraformersmc.biolith.api.biome.sub.Criterion
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder
import com.terraformersmc.biolith.api.surface.SurfaceGeneration
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import org.teamvoided.dusk_autumn.DuskAutumns.id

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object DuskBiomes {
    val AUTUMN_WOODS = create("autumn_woods")
    val AUTUMN_PASTURES = create("autumn_pastures")
    val AUTUMN_CASCADES = create("autumn_cascades")
    val AUTUMN_WETLANDS = create("autumn_wetlands")

    val temperature: Criterion = CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, -1f, -0.15f)
    val humidity: Criterion = CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, -1f, -0.35f)

    val replaceLandBiomes = listOf(
        Biomes.PLAINS,
        Biomes.MEADOW,
        Biomes.FOREST,
        Biomes.CHERRY_GROVE,
        Biomes.TAIGA
    )

    fun init() {
        replaceLandBiomes.forEach {
            createAutumnWoodsPlacement(it)
            createAutumnPasturesPlacement(it)
        }
        createAutumnCascadesPlacement(Biomes.RIVER)
//        addOverworld(
//            AUTUMN_PASTURES,
//            Range(-1, -0.45),        // Temperature
//            Range(-1, 1),            // Humidity
//            Range(-0.11, 1),          // Continentalness
//            Range(0.55, 1.0),         // Erosion
//            Range(-0.4, 0.4),         // Weirdness
//        )
//
//        BiomePlacement.addSubOverworld(
//            Biomes.FOREST, AUTUMN_WOODS,
//            CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, -1f, -0.35f),
//        )
//
        SurfaceGeneration.addOverworldSurfaceRules(id("rules/overworld"), DuskSurfaceRules.overworld())
    }

    fun createAutumnWoodsPlacement(biome: RegistryKey<Biome>) {
        BiomePlacement.addSubOverworld(
            biome, AUTUMN_WOODS, CriterionBuilder.allOf(
                temperature,
                humidity,
                CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, -2F, 0f),
            )
        )
    }

    fun createAutumnPasturesPlacement(biome: RegistryKey<Biome>) {
        BiomePlacement.addSubOverworld(
            biome, AUTUMN_PASTURES, CriterionBuilder.allOf(
                temperature,
                humidity,
                CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, 0f, 2f),
            )
        )
    }

    fun createAutumnCascadesPlacement(biome: RegistryKey<Biome>) {
        BiomePlacement.addSubOverworld(
            biome, AUTUMN_CASCADES, CriterionBuilder.allOf(
                temperature,
                humidity
            )
        )
    }

    fun create(id: String): RegistryKey<Biome> = RegistryKey.of(RegistryKeys.BIOME, id(id))
}
