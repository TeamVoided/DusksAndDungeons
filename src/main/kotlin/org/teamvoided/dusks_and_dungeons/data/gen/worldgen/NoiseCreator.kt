package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.synth.NormalNoise
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDNoise

object NoiseCreator {
    fun bootstrap(c: BootstrapContext<NormalNoise.NoiseParameters>) {
        c.register(DnDNoise.AUTUMN, -7, 1, 1)
        c.register(DnDNoise.OVERGROWN_GROTTO, -9, 1, 1, 0, 1)

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

    private fun BootstrapContext<NormalNoise.NoiseParameters>.register(
        key: ResourceKey<NormalNoise.NoiseParameters>,
        firstOctave: Int, firstAmplitude: Number, vararg amplitudes: Number
    ) {
        this.register(
            key,
            NormalNoise.NoiseParameters(
                firstOctave,
                firstAmplitude.toDouble(),
                *amplitudes.map { it.toDouble() }.toDoubleArray()
            )
        )
    }
}
