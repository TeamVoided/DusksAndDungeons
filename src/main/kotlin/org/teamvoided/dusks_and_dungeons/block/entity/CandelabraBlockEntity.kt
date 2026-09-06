package org.teamvoided.dusks_and_dungeons.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.Tag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities.CANDELABRA
import kotlin.math.min

class CandelabraBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(CANDELABRA, pos, state) {

    internal val candles: NonNullList<ItemStack> = NonNullList.withSize(5, ItemStack.EMPTY)

    fun getCandles() = candles

    fun isEmpty(): Boolean {
        for (item in candles) {
            if (!item.isEmpty) {
                return false
            }
        }
        return true
    }

    fun tryAddCandle(candle: ItemStack, slot: Int): Boolean {
        if (min(candles.size, blockState.getValue(CandelabraBlock.CANDLES)) <= slot || !candles[slot].isEmpty) {
            return false
        }
        candles[slot] = candle.copyWithCount(1)
        updateCollisionShape()

        return true
    }

    fun updateCollisionShape() {
    }

    override fun loadAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(nbt, provider)
        if (nbt.contains(KEY_CANDLES)) {
            val list = nbt.getList(KEY_CANDLES, Tag.TAG_COMPOUND.toInt())
            repeat(list.size) { index ->
                candles[index] = ItemStack.parseOptional(provider, list.getCompound(index))
            }
        }
    }

    override fun saveAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.saveAdditional(nbt, provider)
        val list = ListTag()
        for (stack in candles) {
            list.add(stack.saveOptional(provider))
        }
        nbt.put(KEY_CANDLES, list)
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket = ClientboundBlockEntityDataPacket.create(this)

    override fun getUpdateTag(provider: HolderLookup.Provider): CompoundTag = saveCustomOnly(provider)

    companion object {

        const val KEY_CANDLES = "candles"

    }
}