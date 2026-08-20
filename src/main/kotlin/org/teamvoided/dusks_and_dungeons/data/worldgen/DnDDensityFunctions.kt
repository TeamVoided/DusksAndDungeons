package org.teamvoided.dusks_and_dungeons.data.worldgen

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.DensityFunction
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDDensityFunctions {
    val CASCADE_REGION = create("parameters/cascade")
    val SYPIA_REGION = create("parameters/spypia")
    val OVERGROWN_GROTTO_REGION = create("parameters/overgrown_grotto")

    private fun param(id: String): ResourceKey<DensityFunction> =
        ResourceKey.create(Registries.DENSITY_FUNCTION, id("parameters/$id"))

    private fun create(id: String): ResourceKey<DensityFunction> = ResourceKey.create(Registries.DENSITY_FUNCTION, id(id))
}