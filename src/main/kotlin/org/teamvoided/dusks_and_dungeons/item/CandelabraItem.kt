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
        val content = ctx.itemInHand.getOrDefault(CANDELABRA_CONTENTS, CandelabraContents.ONE).validate()
        if (!content.isEmpty()) {
            val filled = filledBlock.getStateForPlacement(ctx)
            if (filled != null) {
                return if (canPlace(ctx, filled) && notStacking(ctx, filled)) filled else null
            }
        }
        return super.getPlacementState(ctx)
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
    }

}