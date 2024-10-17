package org.teamvoided.dusk_autumn.block.entity

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtList
import net.minecraft.nbt.NbtOps
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.ItemInteractionResult
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import org.teamvoided.dusk_autumn.init.DnDBlockEntities

class QuarterBlockPileBlockEntity(pos: BlockPos?, state: BlockState?) :
    BlockEntity(DnDBlockEntities.QUARTER_BLOCK_PILE, pos, state) {

    val blocks: DefaultedList<Block> = DefaultedList.ofSize(3, Blocks.AIR)

    fun place(block: Block): ItemInteractionResult {
//        if (!isEmpty()) {
//            return ItemInteractionResult.CONSUME
//        }

//        blocks.add(block)
//        for () {

//        }
        return ItemInteractionResult.SUCCESS
    }

    fun isEmpty(): Boolean {
        for (block in blocks) {
            if (block != null && !block.equals(Blocks.AIR)) {
                return false
            }
        }
        return true
    }

    override fun writeNbt(nbt: NbtCompound, lookupProvider: HolderLookup.Provider?) {
        super.writeNbt(nbt, lookupProvider)

        val list = NbtList()
        blocks.forEach { block ->
            val blockNbt = NbtCompound()
            Registries.BLOCK.codec.encode(block, NbtOps.INSTANCE, blockNbt)
            list.add(blockNbt)
        }
        nbt.put("blocks", list)
    }

    override fun method_11014(nbt: NbtCompound, lookupProvider: HolderLookup.Provider?) {
        super.method_11014(nbt, lookupProvider)

        val list = nbt.getList("blocks", NbtElement.STRING_TYPE.toInt())
        list.forEachIndexed { index, blockNbt ->
            blocks[index] = Registries.BLOCK.codec.parse(NbtOps.INSTANCE, blockNbt).resultOrPartial().orElse(Blocks.AIR)
        }
    }
}
