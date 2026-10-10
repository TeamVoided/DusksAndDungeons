/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.getCandelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.CandelabraContents
import org.teamvoided.dusks_and_dungeons.init.DnDDataComponents.CANDELABRA_CONTENTS

class CandelabraItem(
    emptyBlock: Block, val filledBlock: Block,
    val emptyWallBlock: Block, val filledWallBlock: Block,
    properties: Properties,
) : BlockItem(emptyBlock, properties) {

    override fun getPlacementState(ctx: BlockPlaceContext): BlockState? {
        val content = ctx.itemInHand.getOrDefault(CANDELABRA_CONTENTS, CandelabraContents.ONE).validate()
        val wallState = getWallPlacement(ctx, content)
        if (wallState != null) {
            return wallState
        }
        if (!content.isEmpty()) {
            val filled = filledBlock.getStateForPlacement(ctx)
            if (filled != null) {
                return if (canPlace(ctx, filled) && notStacking(ctx, filled)) filled else null
            }
        }
        return super.getPlacementState(ctx)
    }

    fun getWallPlacement(ctx: BlockPlaceContext, content: CandelabraContents): BlockState? {
        ctx.horizontalDirection

        if (!content.isEmpty()) {
            val filled = filledWallBlock.getStateForPlacement(ctx)
            if (filled != null) {
                return if (canPlace(ctx, filled)) filled else null
            }
        }

        val blockState = emptyWallBlock.getStateForPlacement(ctx)
        return if (blockState != null && canPlace(ctx, blockState)) blockState else null
    }

    fun notStacking(ctx: BlockPlaceContext, filled: BlockState): Boolean {
        return ctx.clickedFace != Direction.UP || !ctx.level.getBlockState(ctx.clickedPos.below()).`is`(filled.block)
    }

    override fun updateCustomBlockEntityTag(
        pos: BlockPos, level: Level, player: Player?, stack: ItemStack, state: BlockState,
    ): Boolean {
        val candelabra = level.getCandelabra(pos)
        if (candelabra != null && !candelabra.isEmpty()) {
            candelabra.addingCandles = true
        }
        return super.updateCustomBlockEntityTag(pos, level, player, stack, state)
    }

    override fun registerBlocks(map: MutableMap<Block, Item>, item: Item) {
        super.registerBlocks(map, item)
        map[filledBlock] = item
        map[emptyWallBlock] = item
        map[filledWallBlock] = item
    }

}