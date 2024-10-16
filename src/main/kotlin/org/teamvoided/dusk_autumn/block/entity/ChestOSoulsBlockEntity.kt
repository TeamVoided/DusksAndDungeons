package org.teamvoided.dusk_autumn.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.ChestLidAnimator
import net.minecraft.block.entity.LootableContainerBlockEntity
import net.minecraft.block.entity.ViewerCountManager
import net.minecraft.client.block.ChestAnimationProgress
import net.minecraft.entity.player.PlayerEntity
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
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.util.block

class ChestOSoulsBlockEntity(pos: BlockPos?, state: BlockState?) :
    LootableContainerBlockEntity(DnDBlockEntities.CHEST_O_SOULS, pos, state), ChestAnimationProgress {

    private var isOpen = false
    private var openTicks = 0
    private val lidAnimator = ChestLidAnimator()
    private var inventory = DefaultedList.ofSize(1, ItemStack.EMPTY)
    private val stateManager = object : ViewerCountManager() {
        override fun onContainerOpen(world: World?, pos: BlockPos?, state: BlockState?) {}

        override fun onContainerClose(world: World?, pos: BlockPos?, state: BlockState?) {}

        override fun onViewerCountUpdate(
            world: World,
            pos: BlockPos,
            state: BlockState,
            oldViewerCount: Int,
            newViewerCount: Int
        ) {
            world.addSyncedBlockEvent(pos, DnDBlocks.CHEST_O_SOULS, 1, newViewerCount)
        }

        override fun isPlayerViewing(player: PlayerEntity?): Boolean = isOpen
    }

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

    override fun writeNbt(nbt: NbtCompound, lookupProvider: HolderLookup.Provider?) {
        super.writeNbt(nbt, lookupProvider)
        nbt.putBoolean("isOpen", isOpen)
        nbt.putInt("openTicks", openTicks)
        Inventories.writeNbt(nbt, inventory, lookupProvider)
    }

    override fun method_11014(nbt: NbtCompound, lookupProvider: HolderLookup.Provider?) {
        super.method_11014(nbt, lookupProvider)
        isOpen = nbt.getBoolean("isOpen")
        openTicks = nbt.getInt("openTicks")
        Inventories.readNbt(nbt, inventory, lookupProvider)
    }

    override fun getAnimationProgress(tickDelta: Float): Float {
        return lidAnimator.getProgress(tickDelta)
    }

    fun open(player: PlayerEntity) {
        if (!removed && !player.isSpectator) {
            stateManager.openContainer(player, world, pos, cachedState)
        }
    }

    companion object {
        fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: ChestOSoulsBlockEntity) {
            if (world.isClient) {
                blockEntity.lidAnimator.step()
                return
            }

            if (blockEntity.openTicks > 0) {
                blockEntity.openTicks--
            }
        }
    }
}
