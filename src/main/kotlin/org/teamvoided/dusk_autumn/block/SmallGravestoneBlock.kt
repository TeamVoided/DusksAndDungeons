package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import org.teamvoided.dusk_autumn.util.rotate

class SmallGravestoneBlock(settings: Settings) : GravestoneBlock(settings) {

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val rotations = state.get(FACING).horizontal
        val shape = if (state.get(CENTERED)) CENTER_SHAPE else SHAPE
        return shape.rotate(rotations)
    }

    companion object {
        val SHAPE: VoxelShape =  createCuboidShape(3.0, 0.0, 0.0, 13.0, 12.0, 2.0)
        val CENTER_SHAPE: VoxelShape = createCuboidShape(3.0, 0.0, 7.0, 13.0, 12.0, 9.0)
    }
}