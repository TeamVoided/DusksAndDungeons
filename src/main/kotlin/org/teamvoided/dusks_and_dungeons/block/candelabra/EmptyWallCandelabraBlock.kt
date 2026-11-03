package org.teamvoided.dusks_and_dungeons.block.candelabra

import net.minecraft.core.BlockPos
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class EmptyWallCandelabraBlock(properties: Properties, filled: CandelabraBlock) :
    EmptyCandelabraBlock(properties, filled) {

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return Candelabra.getWallShape(state)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        var state = super.getStateForPlacement(ctx)
        if (ctx.clickedFace.axis.isHorizontal) {
            state = state?.setValue(FACING, ctx.clickedFace)
        }
        return state
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val dir = state.getValue(FACING).opposite
        return canSupportCenter(level, pos.relative(dir), dir)
    }

}