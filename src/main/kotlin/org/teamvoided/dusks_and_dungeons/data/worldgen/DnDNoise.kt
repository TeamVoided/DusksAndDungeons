package org.teamvoided.dusks_and_dungeons.data.worldgen

import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.synth.NormalNoise
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDNoise {
    val GLACIER_ICE_PICKER_OLD = create("glacier_ice_picker_old")
    val GLACIER_ICE_PICKER = create("glacier_ice_picker")
    val GLACIER_JAGGEDNESS = create("glacier_jaggedness")
    val GLACIER_SNOW_SURFACE = create("glacier_snow_surface")
    val GLACIER_WATER_ROOF = create("glacier_water_roof")
    val GLACIER_BORDERS = create("glacier_borders")


    fun create(id: String): ResourceKey<NormalNoise.NoiseParameters> =
        ResourceKey.create(Registries.NOISE, id(id))
}
