package org.teamvoided.dusk_autumn.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.ChestLidAnimator
import net.minecraft.block.entity.LootableContainerBlockEntity
import net.minecraft.block.entity.ViewerCountManager
import net.minecraft.client.block.ChestAnimationProgress
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.HolderLookup
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.init.DnDBlockEntities

class ChestOSoulsBlockEntity(pos: BlockPos?, state: BlockState?) :
    LootableContainerBlockEntity(DnDBlockEntities.CHEST_O_SOULS, pos, state), ChestAnimationProgress {

    private val lidAnimator = ChestLidAnimator()
    private var inventory = DefaultedList.ofSize(1, ItemStack.EMPTY)
//    private val stateManager = ViewerCountManager()

    override fun size(): Int = inventory.size

    override fun getContainerName(): Text = Text.translatable("container.dusk_autumn.chest_o_souls")

    override fun method_11282(): DefaultedList<ItemStack> {
        return inventory
    }

    override fun method_11281(newInventory: DefaultedList<ItemStack>?) {
        inventory = newInventory
    }

    override fun createScreenHandler(syncId: Int, playerInventory: PlayerInventory?): ScreenHandler? {
        return null
    }

    override fun writeNbt(nbt: NbtCompound?, lookupProvider: HolderLookup.Provider?) {
        super.writeNbt(nbt, lookupProvider)
        Inventories.writeNbt(nbt, inventory, lookupProvider)
    }

    override fun method_11014(nbt: NbtCompound?, lookupProvider: HolderLookup.Provider?) {
        super.method_11014(nbt, lookupProvider)
        Inventories.readNbt(nbt, inventory, lookupProvider)
    }

    override fun getAnimationProgress(tickDelta: Float): Float {
        return lidAnimator.getProgress(tickDelta)
    }

    companion object {
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: ChestOSoulsBlockEntity) {
            blockEntity.lidAnimator.step()
        }
    }
}
