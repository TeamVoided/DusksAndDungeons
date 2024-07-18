package org.teamvoided.dusk_autumn.init.worldgen

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.util.Range
import org.teamvoided.dusk_autumn.util.addOverworld

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object DuskBiomes {
    val AUTUMN_WOODS = create("autumn_woods")
    val AUTUMN_PASTURES = create("autumn_pastures")
    val AUTUMN_CASCADES = create("autumn_cascades")
    val AUTUMN_WETLANDS = create("autumn_wetlands")


    fun init() {

        addOverworld(
            AUTUMN_WOODS,
            Range(-1, -0.45),        // Temperature
            Range(-1, 1),            // Humidity
            Range(-0.11, 1),          // Continentalness
            Range(0.55, 1.0),         // Erosion
            Range(-0.4, 0.4),         // Weirdness
        )

        BiomePlacement.addSubOverworld(
            Biomes.MUSHROOM_FIELDS, AUTUMN_PASTURES,
            CriterionBuilder.value(BiomeParameterTargets.HUMIDITY, -1f, -0.35f),
        )

    }

    fun create(id: String): RegistryKey<Biome> = RegistryKey.of(RegistryKeys.BIOME, id(id))
}
