package org.teamvoided.dusks_and_dungeons.init.worldgen

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets
import com.terraformersmc.biolith.api.biome.sub.Criterion
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder
import com.terraformersmc.biolith.api.surface.SurfaceGeneration
import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object DnDBiomes {
    val AUTUMN_WOODS = create("autumn_woods")
    val AUTUMN_PASTURES = create("autumn_pastures")
    val AUTUMN_CASCADES = create("autumn_cascades")

    val GOLDEN_WOODS = create("golden_woods")
    val GOLDEN_PASTURES = create("golden_pastures")


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
        SurfaceGeneration.addOverworldSurfaceRules(id("rules/overworld"), DnDSurfaceRules.overworld())
    }

    fun createAutumnWoodsPlacement(biome: ResourceKey<Biome>) {
        BiomePlacement.addSubOverworld(
            biome, AUTUMN_WOODS, CriterionBuilder.allOf(
                temperature,
                humidity,
                CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, -2F, 0f),
            )
        )
    }

    fun createAutumnPasturesPlacement(biome: ResourceKey<Biome>) {
        BiomePlacement.addSubOverworld(
            biome, AUTUMN_PASTURES, CriterionBuilder.allOf(
                temperature,
                humidity,
                CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, 0f, 2f),
            )
        )
    }

    fun createAutumnCascadesPlacement(biome: ResourceKey<Biome>) {
        BiomePlacement.addSubOverworld(
            biome, AUTUMN_CASCADES, CriterionBuilder.allOf(
                temperature,
                humidity
            )
        )
    }

    fun goldenPlacement() {
        BiomePlacement.replaceOverworld(AUTUMN_WOODS, GOLDEN_WOODS, 0.2)
        BiomePlacement.replaceOverworld(AUTUMN_PASTURES, GOLDEN_PASTURES, 0.2)
    }

    fun create(id: String): ResourceKey<Biome> = ResourceKey.create(Registries.BIOME, id(id))
}
