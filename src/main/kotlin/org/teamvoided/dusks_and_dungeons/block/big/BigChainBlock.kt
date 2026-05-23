package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.ChainBlock
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.level.BlockGetter

class BigChainBlock(settings: Properties) : ChainBlock(settings) {

    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ): VoxelShape = when (state.getValue(AXIS) as Direction.Axis) {
        Direction.Axis.Y -> Y_SHAPE
        Direction.Axis.Z -> Z_SHAPE
        Direction.Axis.X -> X_SHAPE
        else -> X_SHAPE
    }

    companion object {
        protected const val SHAPE_MIN = 4.5
        protected const val SHAPE_MAX = 11.5
        val Y_SHAPE = box(SHAPE_MIN, 0.0, SHAPE_MIN, SHAPE_MAX, 16.0, SHAPE_MAX)
        val Z_SHAPE = box(SHAPE_MIN, SHAPE_MIN, 0.0, SHAPE_MAX, SHAPE_MAX, 16.0)
        val X_SHAPE = box(0.0, SHAPE_MIN, SHAPE_MIN, 16.0, SHAPE_MAX, SHAPE_MAX)
    }
}