package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.LeavesBlock
import net.minecraft.block.ShapeContext
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

open class LeafPileBlock(settings: Settings) : LeavesBlock(settings) {
    override fun getOpacity(state: BlockState?, world: BlockView?, pos: BlockPos?): Int {
        return 1
    }
    override fun getOutlineShape(
        state: BlockState, world: BlockView?, pos: BlockPos?, context: ShapeContext?
    ): VoxelShape {
        return SHAPE
    }
    companion object {
        val SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);
    }
    override fun isSideInvisible(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        return if (stateFrom.isOf(this)) true
        else super.isSideInvisible(state, stateFrom, direction)
    }

    override fun canPathfindThrough(state: BlockState?, world: BlockView?, pos: BlockPos?, type: NavigationType ): Boolean {
        return true
    }
}