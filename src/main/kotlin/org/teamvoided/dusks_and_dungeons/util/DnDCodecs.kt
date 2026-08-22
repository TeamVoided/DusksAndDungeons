package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.item.EitherHolder

object DnDCodecs {

    val DAMAGE_TYPE_CODEC: StreamCodec<RegistryFriendlyByteBuf, EitherHolder<DamageType>> =
        EitherHolder.streamCodec(Registries.DAMAGE_TYPE, DamageType.STREAM_CODEC)

    val ITEM_TAG_STREAM_CODEC = tagKeyStreamCodec(Registries.ITEM)
    val BLOCK_TAG_STREAM_CODEC = tagKeyStreamCodec(Registries.BLOCK)

}