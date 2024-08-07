package net.minecraft.world.gen.feature.org.teamvoided.dusk_autumn.data.gen.worldgen

import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKey
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler
import org.teamvoided.dusk_autumn.data.worldgen.DnDNoise

object NoiseCreator {
    fun bootstrap(c: BootstrapContext<DoublePerlinNoiseSampler.NoiseParameters>) {
        c.register(DnDNoise.GLACIER_ICE_PICKER, -7, 1.0)
        c.register(DnDNoise.GLACIER_ROOF_OFFSET, -4, 1.0, 0.0, 0.0, 0.0)
        c.register(DnDNoise.GLACIER_SNOW_SURFACE, -5, 1.0, 1.0, 1.0, 1.0)
        c.register(DnDNoise.GLACIER_WATER_ROOF, -4, 2.0, 1.0, 0.0)
    }

    private fun BootstrapContext<DoublePerlinNoiseSampler.NoiseParameters>.register(
        key: RegistryKey<DoublePerlinNoiseSampler.NoiseParameters>,
        firstOctave: Int,
        firstAmplitude: Double,
        vararg amplitudes: Double
    ) {
        this.register(key, DoublePerlinNoiseSampler.NoiseParameters(firstOctave, firstAmplitude, *amplitudes))
    }
}