package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.ChainBlock
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.phys.shapes.Shapes
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxY

class BigChainBlock(settings: Properties) : ChainBlock(settings) {

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape =
        SHAPES[state.getValue(AXIS)] ?: Shapes.block()

    companion object {
        protected const val SHAPE_MIN = 4.5
        protected const val SHAPE_MAX = 16.0 - SHAPE_MIN
        val Y_SHAPE: VoxelShape = symmetricalBoxY(SHAPE_MIN, 0.0, 16.0)
        val Z_SHAPE: VoxelShape = box(SHAPE_MIN, SHAPE_MIN, 0.0, SHAPE_MAX, SHAPE_MAX, 16.0)
        val X_SHAPE: VoxelShape = box(0.0, SHAPE_MIN, SHAPE_MIN, 16.0, SHAPE_MAX, SHAPE_MAX)

        val SHAPES = Direction.Axis.entries.associateWith { dir ->
            when (dir) {
                Direction.Axis.Y -> Y_SHAPE
                Direction.Axis.X -> X_SHAPE
                Direction.Axis.Z -> Z_SHAPE
            }
        }
    }
}