package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.getCandelabra

class CandelabraItem(block: Block, val filledBlock: Block, properties: Properties) : BlockItem(block, properties) {
    /*
    val wallBlock: Block
    val attachmentDirection = Direction.DOWN

    fun canPlace(level: LevelReader, state: BlockState, pos: BlockPos): Boolean = state.canSurvive(level, pos)

    override fun getPlacementState(ctx: BlockPlaceContext): BlockState? {
        val wallState = wallBlock.getStateForPlacement(ctx)
        var placeState: BlockState? = null
        val level = ctx.level
        val pos = ctx.clickedPos

        for (direction in ctx.getNearestLookingDirections()) {
            if (direction != attachmentDirection.opposite) {
                val state = if (direction == attachmentDirection) block
                    .getStateForPlacement(ctx) else wallState
                if (state != null && canPlace(level, state, pos)) {
                    placeState = state
                    break
                }
            }
        }

        return if (placeState != null && level.isUnobstructed(placeState, pos, CollisionContext.empty()))
            placeState
        else
            null
    }*/

    override fun getPlacementState(ctx: BlockPlaceContext): BlockState? {
        val data = ctx.itemInHand.get(DataComponents.BLOCK_ENTITY_DATA)
        if (data != null) {
            val filled = filledBlock.getStateForPlacement(ctx)
            if (filled != null && canPlace(ctx, filled)) {
                return filled
            }
        }

        return super.getPlacementState(ctx)
    }

    override fun updateCustomBlockEntityTag(
        pos: BlockPos, level: Level, player: Player?, stack: ItemStack, state: BlockState,
    ): Boolean {
        val result = super.updateCustomBlockEntityTag(pos, level, player, stack, state)
        if (!result && level.isClientSide) {
            val data = stack.get(DataComponents.BLOCK_ENTITY_DATA) ?: return false
            level.getCandelabra(pos)?.let { be ->
                if (!be.onlyOpCanSetNbt() || player != null && player.canUseGameMasterBlocks()) {
                    return data.loadInto(be, level.registryAccess())
                }
            }
        }
        return result
    }


    override fun registerBlocks(map: MutableMap<Block, Item>, item: Item) {
        super.registerBlocks(map, item)
        map[filledBlock] = item
    }

}