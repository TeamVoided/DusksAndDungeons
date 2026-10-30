package org.teamvoided.dusks_and_dungeons.init.worldgen

import com.mojang.serialization.MapCodec
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.util.KeyDispatchDataCodec
import net.minecraft.world.level.levelgen.DensityFunction
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.level.levelgen.ConfigureDensityFunction
import org.teamvoided.dusks_and_dungeons.util.register

object DnDDensityFunctionTypes {

    val CONFIGURE_DENSITY_FUNCTION = register("configure_density", ConfigureDensityFunction.CODEC)

    fun init() = Unit

    fun <C : DensityFunction, F : KeyDispatchDataCodec<C>> register(id: String, densityFunction: F): MapCodec<C> {
        return BuiltInRegistries.DENSITY_FUNCTION_TYPE.register(id(id), densityFunction.codec())
    }

}