package org.teamvoided.dusks_and_dungeons.util

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.tags.TagKey
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.item.EitherHolder
import java.util.function.Function

object DnDCodecs {

    val NON_NEGATIVE_FLOAT = floatRangeWithMessage(0f, Float.MAX_VALUE) { "Value must be non-negative: $it" }

    val DAMAGE_TYPE_CODEC: Codec<EitherHolder<DamageType>> =
        EitherHolder.codec(Registries.DAMAGE_TYPE, DamageType.CODEC)
    val DAMAGE_TYPE_STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, EitherHolder<DamageType>> =
        EitherHolder.streamCodec(Registries.DAMAGE_TYPE, DamageType.STREAM_CODEC)

    val DAMAGE_TYPE_TAG_CODEC = TagKey.codec(Registries.DAMAGE_TYPE)
    val DAMAGE_TYPE_TAG_STREAM_CODEC = tagKeyStreamCodec(Registries.DAMAGE_TYPE)

    val ITEM_TAG_STREAM_CODEC = tagKeyStreamCodec(Registries.ITEM)
    val BLOCK_TAG_STREAM_CODEC = tagKeyStreamCodec(Registries.BLOCK)

    fun floatRange(min: Float, max: Float): Codec<Float> {
        return floatRangeWithMessage(min, max) { "Value must be within range [$min;$max]: $it" }
    }

    fun floatRangeWithMessage(min: Float, max: Float, function: Function<Float, String>): Codec<Float> {
        return Codec.FLOAT.validate { float ->
            if (float in min..max) DataResult.success(float)
            else DataResult.error { function.apply(float) }
        }
    }

}