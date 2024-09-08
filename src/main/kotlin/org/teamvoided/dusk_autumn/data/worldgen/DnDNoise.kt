package org.teamvoided.dusk_autumn.data.worldgen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler
import org.teamvoided.dusk_autumn.DusksAndDungeons.id

object DnDNoise {
    val GLACIER_ICE_PICKER_OLD = create("glacier_ice_picker_old")
    val GLACIER_ICE_PICKER = create("glacier_ice_picker")
    val GLACIER_JAGGEDNESS = create("glacier_jaggedness")
    val GLACIER_SNOW_SURFACE = create("glacier_snow_surface")
    val GLACIER_WATER_ROOF = create("glacier_water_roof")
    val GLACIER_BORDERS = create("glacier_borders")


    fun create(id: String): RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> =
        RegistryKey.of(RegistryKeys.NOISE_PARAMETERS, id(id))
}
