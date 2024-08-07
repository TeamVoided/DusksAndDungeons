package org.teamvoided.dusk_autumn.data.worldgen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DnDNoise {
    val GLACIER_ICE_PICKER = create("glacier_ice_picker")
    val GLACIER_ROOF_OFFSET = create("glacier_top_offset")
    val GLACIER_SNOW_SURFACE = create("glacier_snow_surface")
    val GLACIER_WATER_ROOF = create("glacier_water_roof")

    fun create(id: String): RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> =
        RegistryKey.of(RegistryKeys.NOISE_PARAMETERS, id(id))
}