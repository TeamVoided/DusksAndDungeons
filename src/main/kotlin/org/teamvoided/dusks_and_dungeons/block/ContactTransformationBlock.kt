package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState

class ContactTransformationBlock(settings: Properties, val turnsInTo: Block, val contactBlock: Block) :
    Block(settings) {


    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        var state =  super.getStateForPlacement(ctx)
        //TODO check if sides connect to "contactBlock"
//        ctx
//        if ()

        return state
    }
    override fun neighborChanged(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        block: Block,
        pos2: BlockPos,
        bl: Boolean,
    ) {
        if (level.getBlockState(pos2).`is`(contactBlock)) {
            level.setBlock(pos, turnsInTo.defaultBlockState(), UPDATE_ALL)
        }
        super.neighborChanged(state, level, pos, block, pos2, bl)
    }

}
