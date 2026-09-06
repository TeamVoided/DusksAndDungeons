package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

class CandelabraItem(block: Block, val fullBlock: Block, properties: Properties) : BlockItem(block, properties) {
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

    override fun registerBlocks(map: MutableMap<Block, Item>, item: Item) {
        super.registerBlocks(map, item)
        map[fullBlock] = item
    }

}