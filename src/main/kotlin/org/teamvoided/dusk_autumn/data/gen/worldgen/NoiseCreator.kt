package org.teamvoided.dusk_autumn.data.gen.worldgen

import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKey
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler
import org.teamvoided.dusk_autumn.data.worldgen.DnDNoise

object NoiseCreator {
    fun bootstrap(c: BootstrapContext<DoublePerlinNoiseSampler.NoiseParameters>) {
        c.register(DnDNoise.GLACIER_ICE_PICKER_OLD, -7, 1)
        c.register(DnDNoise.GLACIER_ICE_PICKER, -5, 15, 5, 20)
        c.register(
            DnDNoise.GLACIER_JAGGEDNESS, -15,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        )
        c.register(DnDNoise.GLACIER_SNOW_SURFACE, -5, 1, 1, 1, 1)
        c.register(DnDNoise.GLACIER_WATER_ROOF, -4, 2, 1, 0)
        c.register(DnDNoise.GLACIER_BORDERS, -3, -2, 0, 0, 0, 0, 0, 0, 0)
    }

    private fun BootstrapContext<DoublePerlinNoiseSampler.NoiseParameters>.register(
        key: RegistryKey<DoublePerlinNoiseSampler.NoiseParameters>,
        firstOctave: Int, firstAmplitude: Number, vararg amplitudes: Number
    ) {
        this.register(
            key,
            DoublePerlinNoiseSampler.NoiseParameters(
                firstOctave,
                firstAmplitude.toDouble(),
                *amplitudes.map { it.toDouble() }.toDoubleArray()
            )
        )
    }
}
