@file:Suppress("unused")

package org.teamvoided.dusks_and_dungeons.data.gen.data.litho

import com.mojang.datafixers.util.Either
import com.mojang.datafixers.util.Pair
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.AddPoints
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.ForcePlacement
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.ReplacePartially
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.internal.ParameterMap
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.util.InclusiveRange
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Climate
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.DensityFunction
import org.teamvoided.dusks_and_dungeons.data.gen.data.worldgen.ModDensityFunctions.df
import java.util.*

fun BootstrapContext<BiomeInjector>.replacePartially(
    key: ResourceKey<BiomeInjector>,
    target: ResourceKey<Biome>,
    replacement: ResourceKey<Biome>,
    parameters: ParameterMap,
    priority: Int = 800,
) {
    val biomes = lookup(Registries.BIOME)
    register(
        key,
        ReplacePartially(
            Optional.empty(),
            LevelStem.OVERWORLD,
            priority,
            HolderSet.direct(biomes.getOrThrow(target)),
            biomes.getOrThrow(replacement),
            parameters
        )
    )
}

fun BootstrapContext<BiomeInjector>.replacePartially(
    key: ResourceKey<BiomeInjector>,
    target: TagKey<Biome>,
    replacement: ResourceKey<Biome>,
    parameters: ParameterMap,
    priority: Int = 800,
) {
    val biomes = lookup(Registries.BIOME)
    register(
        key,
        ReplacePartially(
            Optional.empty(),
            LevelStem.OVERWORLD,
            priority,
            biomes.getOrThrow(target),
            biomes.getOrThrow(replacement),
            parameters
        )
    )
}

fun BootstrapContext<BiomeInjector>.forcePlacement(
    key: ResourceKey<BiomeInjector>,
    biome: ResourceKey<Biome>,
    parameters: ParameterMap,
    priority: Int = 800,
) {
    val biomes = lookup(Registries.BIOME)
    register(
        key,
        ForcePlacement(
            Optional.empty(),
            LevelStem.OVERWORLD,
            priority,
            biomes.getOrThrow(biome),
            parameters
        )
    )
}

fun BootstrapContext<BiomeInjector>.addPoints(key: ResourceKey<BiomeInjector>, vararg points: BiomePoint) {
    return addPoints(key, points.toList())
}

fun BootstrapContext<BiomeInjector>.addPoints(
    key: ResourceKey<BiomeInjector>,
    points: List<BiomePoint>,
    priority: Int = 800,
) {
    register(
        key,
        AddPoints(
            Optional.empty(),
            LevelStem.OVERWORLD,
            priority,
            Climate.ParameterList(points)
        )
    )
}

typealias BiomePoint = Pair<Climate.ParameterPoint, Holder<Biome>>

fun BootstrapContext<BiomeInjector>.point(
    biome: ResourceKey<Biome>,
    temperature: Climate.Parameter,
    humidity: Climate.Parameter,
    continentalness: Climate.Parameter,
    erosion: Climate.Parameter,
    depth: Climate.Parameter,
    weirdness: Climate.Parameter,
    offset: Long,
): BiomePoint {
    val biomes = lookup(Registries.BIOME)
    return Pair(
        Climate.ParameterPoint(
            temperature,
            humidity,
            continentalness,
            erosion,
            depth,
            weirdness,
            offset
        ), biomes.getOrThrow(biome)
    )
}

fun param(value: Number) = param(value, value)

fun param(min: Number, max: Number): Climate.Parameter {
    return Climate.Parameter(Climate.quantizeCoord(min.toFloat()), Climate.quantizeCoord(max.toFloat()))
}

typealias WorldPlacement = kotlin.Pair<Either<ClimateParameter, DensityFunction>, InclusiveRange<Double>>

fun parameterMap(vararg param: WorldPlacement): ParameterMap = ParameterMap(param.toMap(), Optional.empty())

fun climateParam(param: ClimateParameter, min: Double, max: Double): WorldPlacement {
    return Either.left<ClimateParameter, DensityFunction>(param) to InclusiveRange(min, max)
}

fun BootstrapContext<BiomeInjector>.dfParam(
    key: ResourceKey<DensityFunction>, low: Double, high: Double,
): WorldPlacement {
    return dfParam(df(key), low, high)
}

fun dfParam(df: DensityFunction, min: Double, max: Double): WorldPlacement {
    return Either.right<ClimateParameter, DensityFunction>(df) to InclusiveRange(min, max)
}
