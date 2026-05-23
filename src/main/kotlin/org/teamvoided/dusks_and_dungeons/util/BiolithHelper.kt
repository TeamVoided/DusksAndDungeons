package org.teamvoided.dusks_and_dungeons.util

import com.terraformersmc.biolith.api.biome.BiomePlacement
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Climate


data class Range(val min: Number, val max: Number) {
    constructor(value: Number) : this(value, value)

    fun min() = min.toFloat()
    fun max() = max.toFloat()
    fun toParameterRange(): Climate.Parameter = Climate.Parameter.span(min(), max())
}


fun addOverworld(
    biome: ResourceKey<Biome>, temperature: Range, humidity: Range,
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
): Climate.ParameterPoint = Climate.ParameterPoint(
    temperature.toParameterRange(),
    humidity.toParameterRange(),
    continentalness.toParameterRange(),
    erosion.toParameterRange(),
    depth.toParameterRange(),
    weirdness.toParameterRange(),
    offset
)