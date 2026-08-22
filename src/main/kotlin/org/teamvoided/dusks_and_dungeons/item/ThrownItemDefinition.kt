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
import org.teamvoided.dusks_and_dungeons.util.DnDCodecs
import kotlin.jvm.optionals.getOrNull

data class ThrownItemDefinition(
    val items: TagKey<Item>,
    val damage: Float,
    val damageType: EitherHolder<DamageType>,
    val power: Float,
    val uncertainty: Float,
    val cooldown: Int,
    val blockBreakTag: TagKey<Block>,
) {

    companion object {

        val DIRECT_CODEC: Codec<ThrownItemDefinition> = RecordCodecBuilder.create { inst ->
            inst
                .group(
                    TagKey.hashedCodec(Registries.ITEM).fieldOf("items").forGetter { it.items },
                    ExtraCodecs.POSITIVE_FLOAT.fieldOf("damage").forGetter { it.damage },
                    EitherHolder.codec(Registries.DAMAGE_TYPE, DamageType.CODEC)
                        .fieldOf("damage_type").forGetter { it.damageType },
                    ExtraCodecs.POSITIVE_FLOAT.fieldOf("power").forGetter { it.power },
                    ExtraCodecs.POSITIVE_FLOAT.fieldOf("uncertainty").forGetter { it.uncertainty },
                    ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("cooldown", 0).forGetter { it.cooldown },
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("block_break_tag").forGetter { it.blockBreakTag }
                )
                .apply(inst, ::ThrownItemDefinition)
        }

        val DIRECT_STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ThrownItemDefinition> =
            StreamCodec.of(::write, ::read)

        fun write(buf: RegistryFriendlyByteBuf, def: ThrownItemDefinition) {
            DnDCodecs.ITEM_TAG_STREAM_CODEC.encode(buf, def.items)
            buf.writeFloat(def.damage)
            DnDCodecs.DAMAGE_TYPE_CODEC.encode(buf, def.damageType)
            buf.writeFloat(def.power)
            buf.writeFloat(def.uncertainty)
            buf.writeInt(def.cooldown)
            DnDCodecs.BLOCK_TAG_STREAM_CODEC.encode(buf, def.blockBreakTag)
        }

        fun read(buf: RegistryFriendlyByteBuf): ThrownItemDefinition {
            return ThrownItemDefinition(
                DnDCodecs.ITEM_TAG_STREAM_CODEC.decode(buf),
                buf.readFloat(),
                DnDCodecs.DAMAGE_TYPE_CODEC.decode(buf),
                buf.readFloat(),
                buf.readFloat(),
                buf.readInt(),
                DnDCodecs.BLOCK_TAG_STREAM_CODEC.decode(buf),
            )
        }

        val CODEC: RegistryFixedCodec<ThrownItemDefinition> =
            RegistryFixedCodec.create(DnDRegistryKeys.THROWN_ITEM_DEFINITION)
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, Holder<ThrownItemDefinition>> =
            ByteBufCodecs.holder(DnDRegistryKeys.THROWN_ITEM_DEFINITION, DIRECT_STREAM_CODEC)


        fun getItemDefinition(stack: ItemStack): Holder<ThrownItemDefinition>? {
            return CACHE_MAP[stack.item]
        }

        private var CACHE_MAP = mapOf<Item, Holder<ThrownItemDefinition>>()

        internal fun refreshCache(lookup: HolderLookup.Provider) {
            val newMap = mutableMapOf<Item, Holder<ThrownItemDefinition>>()
            for (thrownHolder in lookup.lookupOrThrow(DnDRegistryKeys.THROWN_ITEM_DEFINITION).listElements()) {
                val thrownId = thrownHolder.value()
                for (holder in BuiltInRegistries.ITEM.getTagOrEmpty(thrownId.items)) {
                    val oldValue = newMap.put(holder.value(), thrownHolder)
                    if (oldValue != null) {
                        log.warn(
                            "Replaced items [${holder.value()}] from: ${
                                oldValue.unwrapKey().getOrNull()
                            }, to ${thrownHolder.unwrapKey().getOrNull()}"
                        )
                    }
                }
            }

            CACHE_MAP = newMap
        }

    }
}