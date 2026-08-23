package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.DensityFunction
import net.minecraft.world.level.levelgen.DensityFunction.NoiseHolder
import net.minecraft.world.level.levelgen.DensityFunctions.HolderHolder
import net.minecraft.world.level.levelgen.DensityFunctions.noise
import net.minecraft.world.level.levelgen.synth.NormalNoise.NoiseParameters
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDDensityFunctions
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDNoise

object DensityFunctionCreator {

    fun bootstrap(c: BootstrapContext<DensityFunction>) {
        c.register(
            DnDDensityFunctions.CASCADE_REGION,
            noise(c.noiseHold(DnDNoise.AUTUMN), 0.12, 0.0)
        )
        c.register(
            DnDDensityFunctions.SYPIA_REGION,
            noise(c.noiseHold(DnDNoise.AUTUMN), -0.09, 0.0).abs(),
        )
        c.register(
            DnDDensityFunctions.OVERGROWN_GROTTO_REGION,
            noise(c.noiseHold(DnDNoise.OVERGROWN_GROTTO), 0.25, 0.0),
        )
    }
//    NoiseRouterData.class


    fun BootstrapContext<*>.noise(noi: ResourceKey<NoiseParameters>): NoiseHolder =
        NoiseHolder(this.noiseHold(noi))

    fun BootstrapContext<*>.noiseHold(noi: ResourceKey<NoiseParameters>): Holder.Reference<NoiseParameters> =
        this.lookup(Registries.NOISE).getOrThrow(noi)

    fun BootstrapContext<*>.dense(den: ResourceKey<DensityFunction>): DensityFunction =
        HolderHolder(this.denseHold(den))

    fun BootstrapContext<*>.denseHold(noi: ResourceKey<DensityFunction>): Holder.Reference<DensityFunction> =
        this.lookup(Registries.DENSITY_FUNCTION).getOrThrow(noi)


    fun BootstrapContext<DensityFunction>.registerAndWrap(
        registryKey: ResourceKey<DensityFunction>,
        den: DensityFunction
    ): DensityFunction {
        return HolderHolder(this.register(registryKey, den))
    }
}