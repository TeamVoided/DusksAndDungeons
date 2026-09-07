package org.teamvoided.dusks_and_dungeons.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.Tag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities.CANDELABRA
import kotlin.math.min


class CandelabraBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(CANDELABRA, pos, state) {

    internal val candles: NonNullList<ItemStack> = NonNullList.withSize(CANDLES, ItemStack.EMPTY)

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
        if (min(candles.size, getMaxCandles()) <= slot || !candles[slot].isEmpty) {
            return false
        }
        candles[slot] = candle.copyWithCount(1)
        updateStateCache()

        return true
    }

    fun getMaxCandles(): Int = blockState.getValue(CandelabraBlock.CANDLES)

    val stateCache: NonNullList<BlockState> = NonNullList.withSize(CANDLES, Blocks.AIR.defaultBlockState())
    var dynamicShape: VoxelShape = Shapes.empty()
    val offsets: Array<Vec3?> = arrayOfNulls(CANDLES)

    fun updateStateCache() {
        var updateShape = Shapes.empty()
        for ((idx, stack) in candles.withIndex()) {
            if (stack.isEmpty) {
                offsets[idx] = null
                continue
            }
            val item = stack.item
            if (item is BlockItem) {
                val state = item.block.defaultBlockState()
                if (state.isAir) {
                    continue
                }
                stateCache[idx] = state
                val offset = Candelabra.OFFSETS[getMaxCandles() - 1][idx]
                val shape = state.getShape(level, blockPos).move(offset.x, offset.y, offset.z)
                updateShape = Shapes.or(updateShape, shape)
                offsets[idx] = offset.add(0.0, shape.max(Direction.Axis.Y), 0.0)


            }
        }
        dynamicShape = updateShape.optimize()
        println("Shape code ${level?.isClientSide}, $candles")
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

        const val CANDLES = 5
        const val KEY_CANDLES = "candles"

    }
}