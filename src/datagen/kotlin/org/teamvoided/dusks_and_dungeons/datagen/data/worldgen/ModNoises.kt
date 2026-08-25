package org.teamvoided.dusks_and_dungeons.datagen.data.worldgen

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.synth.NormalNoise
import org.teamvoided.dusks_and_dungeons.datagen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDNoises

object ModNoises : RegistryBootstrapper<NormalNoise.NoiseParameters> {

    override fun BootstrapContext<NormalNoise.NoiseParameters>.init() {
        register(DnDNoises.AUTUMN, -7, 1, 1)
        register(DnDNoises.OVERGROWN_GROTTO, -9, 1, 1, 0, 1)
        //TODO(1.0) remove
        register(DnDNoises.GLACIER_ICE_PICKER_OLD, -7, 1)
        register(DnDNoises.GLACIER_ICE_PICKER, -5, 15, 5, 20)
        register(DnDNoises.GLACIER_JAGGEDNESS, -15, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        register(DnDNoises.GLACIER_SNOW_SURFACE, -5, 1, 1, 1, 1)
        register(DnDNoises.GLACIER_WATER_ROOF, -4, 2, 1, 0)
        register(DnDNoises.GLACIER_BORDERS, -3, -2, 0, 0, 0, 0, 0, 0, 0)
    }

    fun BootstrapContext<NormalNoise.NoiseParameters>.register(
        key: ResourceKey<NormalNoise.NoiseParameters>,
        firstOctave: Int, firstAmplitude: Number, vararg amplitudes: Number,
    ) {
        register(
            key, NormalNoise.NoiseParameters(
                firstOctave, firstAmplitude.toDouble(), *amplitudes.map { it.toDouble() }.toDoubleArray()
            )
        )
    }

}