package org.teamvoided.dusks_and_dungeons.data.worldgen

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.synth.NormalNoise
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDNoises {

    val AUTUMN = create("biome_parameter/autumn")
    val OVERGROWN_GROTTO = create("biome_parameter/overgrown_grotto")

    // TODO(1.0) remove
    val GLACIER_ICE_PICKER_OLD = create("glacier_ice_picker_old")
    val GLACIER_ICE_PICKER = create("glacier_ice_picker")
    val GLACIER_JAGGEDNESS = create("glacier_jaggedness")
    val GLACIER_SNOW_SURFACE = create("glacier_snow_surface")
    val GLACIER_WATER_ROOF = create("glacier_water_roof")
    val GLACIER_BORDERS = create("glacier_borders")

    fun create(id: String): ResourceKey<NormalNoise.NoiseParameters> = Registries.NOISE.key(id(id))

}