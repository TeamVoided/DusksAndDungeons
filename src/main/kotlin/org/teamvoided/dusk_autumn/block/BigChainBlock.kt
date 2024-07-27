package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ChainBlock
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class BigChainBlock(settings: Settings) : ChainBlock(settings) {

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return when (state.get(AXIS) as Direction.Axis) {
            Direction.Axis.X -> X_SHAPE
            Direction.Axis.Z -> Z_SHAPE
            Direction.Axis.Y -> Y_SHAPE
            else -> ChainBlock.X_SHAPE
        }
    }
    companion object {
        protected const val SHAPE_MIN = 4.5
        protected const val SHAPE_MAX = 11.5
        val Y_SHAPE = Block.createCuboidShape(SHAPE_MIN, 0.0, SHAPE_MIN, SHAPE_MAX, 16.0, SHAPE_MAX);
        val Z_SHAPE = Block.createCuboidShape(SHAPE_MIN, SHAPE_MIN, 0.0, SHAPE_MAX, SHAPE_MAX, 16.0);
        val X_SHAPE = Block.createCuboidShape(0.0, SHAPE_MIN, SHAPE_MIN, 16.0, SHAPE_MAX, SHAPE_MAX);
    }
}