package org.teamvoided.dusks_and_dungeons.block.candelabra

import com.google.common.collect.Lists
import com.mojang.serialization.Codec
import net.minecraft.core.NonNullList
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import org.teamvoided.dusks_and_dungeons.util.emptyItemList
import java.util.List.copyOf


@Suppress("DEPRECATION")
data class CandelabraContents(val candles: NonNullList<ItemStack>) {

    val slots = candles.size
    val fullSlots = candles.sumOf { if (it.isEmpty) 0 else 1 }

    fun isEmpty(): Boolean = fullSlots <= 0

    fun validate(): CandelabraContents {
        return if (candles.size in 1..<6) this else ONE
    }

    fun getItems(): MutableList<ItemStack> {
        return copyOf(Lists.transform(candles, ItemStack::copy)).toMutableList()
    }

    override fun toString(): String = "CandelabraContents[candles=${candles}]"

    override fun equals(other: Any?): Boolean {
        return other is CandelabraContents && ItemStack.listMatches(candles, other.candles)
    }

    override fun hashCode(): Int {
        var result = fullSlots
        result = 31 * result + ItemStack.hashStackList(candles)
        return result
    }

    companion object {

        fun create(items: List<ItemStack>): CandelabraContents {
            require(items.size in 1..<6) { "Candelabra contents too large" }
            return CandelabraContents(copyOf(Lists.transform(items, ItemStack::copy)).toItemList())
        }

        fun Collection<ItemStack>.toItemList(): NonNullList<ItemStack> {
            return NonNullList.of(ItemStack.EMPTY, *toTypedArray())
        }

        fun of(candles: NonNullList<ItemStack>?, count: Int): CandelabraContents {
            return if (candles != null) create(candles.take(count)) else getStatic(count)
        }

        fun getStatic(value: Int): CandelabraContents {
            return when (value) {
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                else -> ONE
            }
        }

        val ONE = CandelabraContents(emptyItemList(1))
        val TWO = CandelabraContents(emptyItemList(2))
        val THREE = CandelabraContents(emptyItemList(3))
        val FOUR = CandelabraContents(emptyItemList(4))
        val FIVE = CandelabraContents(emptyItemList(5))

        val CODEC: Codec<CandelabraContents> =
            ItemStack.OPTIONAL_CODEC
                .listOf(1, 5)
                .xmap(CandelabraContents::create, CandelabraContents::candles)

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, CandelabraContents> =
            ItemStack.OPTIONAL_STREAM_CODEC
                .apply(ByteBufCodecs.list(5))
                .map(CandelabraContents::create, CandelabraContents::candles)

    }
}