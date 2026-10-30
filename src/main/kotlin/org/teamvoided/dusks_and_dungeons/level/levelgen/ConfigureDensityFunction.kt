package org.teamvoided.dusks_and_dungeons.level.levelgen

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.KeyDispatchDataCodec
import net.minecraft.world.level.levelgen.DensityFunction
import net.minecraft.world.level.levelgen.DensityFunction.*
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.config
import org.teamvoided.dusks_and_dungeons.util.isDev
import java.util.*

class ConfigureDensityFunction(val configId: ResourceLocation) : SimpleFunction {

    private val _value: Double = getCfgValue()
    val value: Double get() = if (isDev()) getCfgValue() else _value

    private fun getCfgValue(): Double {
        val scale = config.densityConfig[configId] ?: 1.0
        return if (scale == 0.0) 0.0 else 1.0 / scale
    }

    override fun compute(context: FunctionContext): Double = value

    override fun fillArray(array: DoubleArray, context: ContextProvider) {
        Arrays.fill(array, value)
    }

    override fun minValue(): Double = value

    override fun maxValue(): Double = value

    override fun codec(): KeyDispatchDataCodec<out DensityFunction> = CODEC

    companion object {

        val DATA_CODEC: MapCodec<ConfigureDensityFunction> = RecordCodecBuilder.mapCodec { instance ->
            instance
                .group(ResourceLocation.CODEC.fieldOf("config_id").forGetter { it.configId })
                .apply(instance, ::ConfigureDensityFunction)
        }

        val CODEC: KeyDispatchDataCodec<ConfigureDensityFunction> = KeyDispatchDataCodec.of(DATA_CODEC)

    }
}