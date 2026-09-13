package org.teamvoided.dusks_and_dungeons.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.core.component.DataComponentMap
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.Tag
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraBlock
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraContents
import org.teamvoided.dusks_and_dungeons.init.DnDBlockEntities.CANDELABRA
import org.teamvoided.dusks_and_dungeons.init.DnDDataComponents.CANDELABRA_CONTENTS
import org.teamvoided.dusks_and_dungeons.util.rotate
import org.teamvoided.voidlib.helpers.mc.rotateFlat90
import kotlin.math.min


class CandelabraBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(CANDELABRA, pos, state) {

    private val candles: NonNullList<ItemStack> = NonNullList.withSize(MAX_CANDLES, ItemStack.EMPTY)

    var hasTicked = false
    var addingCandles = false

    fun getCandles() = candles

    fun isEmpty(): Boolean {
        for (item in candles) {
            if (!item.isEmpty) {
                return false
            }
        }
        return true
    }

    fun isSlotFull(slot: Int): Boolean {
        require(slot > -1 && slot < candles.size) { "Index[$slot] out of bonds in Candelabra" }
        return !candles[slot].isEmpty
    }

    fun tryAddCandle(candle: ItemStack, slot: Int): Boolean {
        if (slot < 0) {
            return false
        }
        if (min(candles.size, getMaxCandles()) <= slot || !candles[slot].isEmpty) {
            return false
        }
        candles[slot] = candle.copyWithCount(1)
        updateStateCache(level!!) //myb move level to param

        return true
    }

    override fun setChanged() {
        super.setChanged()
        level?.let(::updateStateCache)
    }

    fun getMaxCandles(): Int = blockState.getValue(CandelabraBlock.CANDLES)

    val internalBlockStates: NonNullList<BlockState> = NonNullList.withSize(MAX_CANDLES, Blocks.AIR.defaultBlockState())
    var dynamicShape: VoxelShape = Shapes.empty()
    var dynamicCollisionShape: VoxelShape = Shapes.empty()
    val particleOffsets: Array<Vec3?> = arrayOfNulls(MAX_CANDLES)

    fun updateStateCache(uLevel: Level) {
        val dir = blockState.getValue(CandelabraBlock.FACING).opposite.get2DDataValue()
        val baseShape = Candelabra.getBaseShape(blockState)

        var shape = baseShape
        var collisionShape = baseShape
        for ((idx, stack) in candles.withIndex()) {
            if (stack.isEmpty) {
                particleOffsets[idx] = null
                continue
            }
            val item = stack.item
            if (item is BlockItem) {
                var state = item.block.defaultBlockState()
                if (state.isAir) {
                    continue
                }
                val blockStateData = stack.get(DataComponents.BLOCK_STATE)
                if (blockStateData != null) {
                    state = blockStateData.apply(state)
                }
                internalBlockStates[idx] = state
                val offset = Candelabra.OFFSETS.getOrNull(getMaxCandles() - 1)?.getOrNull(idx) ?: Vec3.ZERO
                val bShape = state.getShape(uLevel, blockPos).move(offset.x, offset.y, offset.z).rotate(dir)
                val cShape = state.getCollisionShape(uLevel, blockPos).move(offset.x, offset.y, offset.z).rotate(dir)
                shape = Shapes.or(shape, bShape)
                collisionShape = Shapes.or(collisionShape, cShape)
                particleOffsets[idx] = offset
                    .add(
                        0.5,
                        bShape.max(Direction.Axis.Y) - bShape.min(Direction.Axis.Y) + Candelabra.PIXEL_SCALER * 2,
                        0.5,
                    )
                    .rotateFlat90(dir)
            }
        }
        dynamicShape = shape
        dynamicCollisionShape = collisionShape
    }

    override fun loadAdditional(nbt: CompoundTag, provider: HolderLookup.Provider) {
        super.loadAdditional(nbt, provider)
        if (nbt.contains(KEY_CANDLES)) {
            val list = nbt.getList(KEY_CANDLES, Tag.TAG_COMPOUND.toInt())
            for (index in list.indices) {
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

    override fun applyImplicitComponents(input: DataComponentInput) {
        super.applyImplicitComponents(input)
        val contents = input.get(CANDELABRA_CONTENTS)
        if (contents != null && contents.fullSlots > 0) {
            if (!addingCandles) {
                candles.clear()
                for ((idx, stack) in contents.candles.withIndex()) {
                    candles[idx] = stack.copy()
                }
            } else {
                val candlesLeft = contents.getItems().filterNot(ItemStack::isEmpty).toMutableList()
                for ((idx, stack) in candles.withIndex()) {
                    if (stack.isEmpty) {
                        candles[idx] = candlesLeft.removeFirst().copy()
                    }
                    if (candlesLeft.isEmpty()) {
                        break
                    }
                }
            }
        }
    }

    override fun collectImplicitComponents(builder: DataComponentMap.Builder) {
        super.collectImplicitComponents(builder)
        builder.set(
            CANDELABRA_CONTENTS,
            CandelabraContents.of(candles, blockState.getValue(CandelabraBlock.CANDLES))
        )
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun removeComponentsFromTag(compoundTag: CompoundTag) {
        super.removeComponentsFromTag(compoundTag)
        compoundTag.remove(KEY_CANDLES)
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket = ClientboundBlockEntityDataPacket.create(this)

    override fun getUpdateTag(provider: HolderLookup.Provider): CompoundTag = saveWithoutMetadata(provider)

    companion object {

        const val MAX_CANDLES = 5
        const val KEY_CANDLES = "candles"

        @Suppress("unused")
        fun tick(level: Level, pos: BlockPos, state: BlockState, candelabra: CandelabraBlockEntity) {
            if (!candelabra.hasTicked) {
                candelabra.updateStateCache(level)
                candelabra.hasTicked = true
            }
        }

    }
}