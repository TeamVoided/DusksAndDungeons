package org.teamvoided.dusks_and_dungeons.data.worldgen

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.DensityFunction
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDDensityFunctions {

    val CASCADE_REGION = region("cascade")
    val SYPIA_REGION = region("sypia")
    val OVERGROWN_GROTTO_REGION = region("overgrown_grotto")

    fun region(id: String) = create("region/$id")

    fun create(id: String): ResourceKey<DensityFunction> = Registries.DENSITY_FUNCTION.key(id(id))

}