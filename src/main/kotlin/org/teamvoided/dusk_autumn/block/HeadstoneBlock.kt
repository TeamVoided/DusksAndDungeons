package org.teamvoided.dusk_autumn.block


import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import org.teamvoided.dusk_autumn.util.rotate

class HeadstoneBlock(settings: Settings) : GravestoneBlock(settings) {

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
        private val SHAPE: VoxelShape =  createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 2.0)
        private val CENTER_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 7.0, 16.0, 16.0, 9.0)
    }
}