package org.teamvoided.dusks_and_dungeons.item

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.RegistryFixedCodec
import net.minecraft.tags.TagKey
import net.minecraft.util.ExtraCodecs
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.item.EitherHolder
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys
import org.teamvoided.dusks_and_dungeons.util.tagKeyStreamCodec
import kotlin.jvm.optionals.getOrNull

data class ThrownItemDefinition(
    val items: TagKey<Item>,
    val damage: Int,
    val damageType: EitherHolder<DamageType>,
    val cooldown: Int,
    val blockBreakTag: TagKey<Block>,
) {

    companion object {

        val DIRECT_CODEC: Codec<ThrownItemDefinition> = RecordCodecBuilder.create { inst ->
            inst
                .group(
                    TagKey.hashedCodec(Registries.ITEM).fieldOf("items")
                        .forGetter(ThrownItemDefinition::items),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("damage")
                        .forGetter(ThrownItemDefinition::damage),
                    EitherHolder.codec(Registries.DAMAGE_TYPE, DamageType.CODEC).fieldOf("damage_type")
                        .forGetter(ThrownItemDefinition::damageType),
                    ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("cooldown", 0)
                        .forGetter(ThrownItemDefinition::cooldown),
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("block_break_tag")
                        .forGetter(ThrownItemDefinition::blockBreakTag)
                )
                .apply(inst, ::ThrownItemDefinition)
        }
        val DIRECT_STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ThrownItemDefinition> = StreamCodec.composite(
            tagKeyStreamCodec(Registries.ITEM), ThrownItemDefinition::items,
            ByteBufCodecs.INT, ThrownItemDefinition::damage,
            EitherHolder.streamCodec(Registries.DAMAGE_TYPE, DamageType.STREAM_CODEC),
            ThrownItemDefinition::damageType,
            ByteBufCodecs.INT, ThrownItemDefinition::cooldown,
            tagKeyStreamCodec(Registries.BLOCK), ThrownItemDefinition::blockBreakTag,
            ::ThrownItemDefinition
        )

        val CODEC: RegistryFixedCodec<ThrownItemDefinition> =
            RegistryFixedCodec.create(DnDRegistryKeys.THROWN_ITEM_DEFINITION)
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, Holder<ThrownItemDefinition>> =
            ByteBufCodecs.holder(DnDRegistryKeys.THROWN_ITEM_DEFINITION, DIRECT_STREAM_CODEC)


        fun getItemDefinition(stack: ItemStack): ThrownItemDefinition? {
            return CACHE_MAP[stack.item]
        }

        private var CACHE_MAP = mapOf<Item, ThrownItemDefinition>()

        internal fun refreshCache(lookup: HolderLookup.Provider) {
            println("Started Reload Cache")
            val newMap = mutableMapOf<Item, Holder<ThrownItemDefinition>>()
            for (thrownHolder in lookup.lookupOrThrow(DnDRegistryKeys.THROWN_ITEM_DEFINITION).listElements()) {
                println(thrownHolder.unwrapKey().getOrNull())
                val thrownId = thrownHolder.value()
                for (holder in BuiltInRegistries.ITEM.getTagOrEmpty(thrownId.items)) {
                    println(holder.unwrapKey())
                    val oldValue = newMap.put(holder.value(), thrownHolder)
                    if (oldValue != null) {
                        log.warn("Replaced items [${holder.value()}] from: ${oldValue.unwrapKey().getOrNull()}, to ${thrownHolder.unwrapKey().getOrNull()}")
                    }
                }
            }

            CACHE_MAP = newMap.mapValues { it.value.value() }

            println(CACHE_MAP.keys)
        }

    }
}