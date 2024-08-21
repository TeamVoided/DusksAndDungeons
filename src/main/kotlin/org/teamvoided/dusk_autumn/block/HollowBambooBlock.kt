package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import org.teamvoided.dusk_autumn.util.rotateColumn

class HollowBambooBlock(settings: Settings) : HollowLogBlockWithCutting(settings) {
    override val special1: Double = 0.125
    override val special2: Double = 0.875
    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        var shape = VoxelShapes.empty()
        if (state.get(NORTH)) shape = VoxelShapes.union(shape, NORTH_BAMBOO_SHAPE)
        if (state.get(SOUTH)) shape = VoxelShapes.union(shape, SOUTH_BAMBOO_SHAPE)
        if (state.get(EAST)) shape = VoxelShapes.union(shape, EAST_BAMBOO_SHAPE)
        if (state.get(WEST)) shape = VoxelShapes.union(shape, WEST_BAMBOO_SHAPE)

        return shape.rotateColumn(state.get(AXIS))
    }

    companion object {
        val NORTH_BAMBOO_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 4.0)
        val SOUTH_BAMBOO_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 12.0, 16.0, 16.0, 16.0)
        val EAST_BAMBOO_SHAPE: VoxelShape = createCuboidShape(12.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        val WEST_BAMBOO_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 4.0, 16.0, 16.0)
    }
}