package org.teamvoided.dusk_autumn.util

import com.terraformersmc.biolith.api.biome.BiomePlacement
import net.minecraft.registry.RegistryKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.source.util.MultiNoiseUtil



data class Range(val min: Number, val max: Number) {
    constructor(value: Number) : this(value, value)

    fun min() = min.toFloat()
    fun max() = max.toFloat()
    fun toParameterRange(): MultiNoiseUtil.ParameterRange = MultiNoiseUtil.ParameterRange.of(min(), max())
}


fun addOverworld(
    biome: RegistryKey<Biome>, temperature: Range, humidity: Range,
    continentalness: Range, erosion: Range, weirdness: Range
) {
    BiomePlacement.addOverworld(
        biome,
        createNoise(
            temperature,        // Temperature
            humidity,            // Humidity
            continentalness,          // Continentalness
            erosion,         // Erosion
            Range(0.0),         // Depth
            weirdness,         // Weirdness
            0L                  // Offset
        )
    )
    BiomePlacement.addOverworld(
        biome,
        createNoise(
            temperature,        // Temperature
            humidity,            // Humidity
            continentalness,          // Continentalness
            erosion,         // Erosion
            Range(1),         // Depth
            weirdness,         // Weirdness
            0L                  // Offset
        )
    )
}
fun createNoise(
    temperature: Range, humidity: Range, continentalness: Range, erosion: Range,
    depth: Range, weirdness: Range, offset: Long
): MultiNoiseUtil.NoiseHypercube = MultiNoiseUtil.NoiseHypercube(
    temperature.toParameterRange(),
    humidity.toParameterRange(),
    continentalness.toParameterRange(),
    erosion.toParameterRange(),
    depth.toParameterRange(),
    weirdness.toParameterRange(),
    offset
)