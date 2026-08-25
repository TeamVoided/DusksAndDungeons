package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen

import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.DensityFunction
import net.minecraft.world.level.levelgen.DensityFunction.NoiseHolder
import net.minecraft.world.level.levelgen.DensityFunctions.HolderHolder
import net.minecraft.world.level.levelgen.DensityFunctions.noise
import net.minecraft.world.level.levelgen.synth.NormalNoise.NoiseParameters
import org.teamvoided.dusks_and_dungeons.datagen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDDensityFunctions
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDNoises

object ModDensityFunctions : RegistryBootstrapper<DensityFunction> {

    override fun BootstrapContext<DensityFunction>.init() {
        register(
            DnDDensityFunctions.CASCADE_REGION,
            noise(noiseHolder(DnDNoises.AUTUMN), 0.12, 0.0)
        )
        register(
            DnDDensityFunctions.SYPIA_REGION,
            noise(noiseHolder(DnDNoises.AUTUMN), -0.09, 0.0).abs(),
        )
        register(
            DnDDensityFunctions.OVERGROWN_GROTTO_REGION,
            noise(noiseHolder(DnDNoises.OVERGROWN_GROTTO), 0.25, 0.0),
        )
    }
//    NoiseRouterData.class


    fun BootstrapContext<*>.noise(key: ResourceKey<NoiseParameters>): NoiseHolder = NoiseHolder(noiseHolder(key))

    fun BootstrapContext<*>.noiseHolder(key: ResourceKey<NoiseParameters>): Holder.Reference<NoiseParameters> {
        return lookup(Registries.NOISE).getOrThrow(key)
    }

    fun BootstrapContext<*>.df(key: ResourceKey<DensityFunction>): DensityFunction = HolderHolder(dfHolder(key))

    fun BootstrapContext<*>.dfHolder(key: ResourceKey<DensityFunction>): Holder.Reference<DensityFunction> {
        return lookup(Registries.DENSITY_FUNCTION).getOrThrow(key)
    }

}