package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.level.BlockGetter
import org.teamvoided.dusks_and_dungeons.util.rotateColumn

class HollowBambooBlock(settings: Properties) : HollowLogWithCuttingBlock(settings) {
    override val special1: Double = 0.125
    override val special2: Double = 0.875
    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ): VoxelShape {
        var shape = Shapes.empty()
        if (state.getValue(NORTH)) shape = Shapes.or(shape, NORTH_BAMBOO_SHAPE)
        if (state.getValue(SOUTH)) shape = Shapes.or(shape, SOUTH_BAMBOO_SHAPE)
        if (state.getValue(EAST)) shape = Shapes.or(shape, EAST_BAMBOO_SHAPE)
        if (state.getValue(WEST)) shape = Shapes.or(shape, WEST_BAMBOO_SHAPE)

        return shape.rotateColumn(state.getValue(AXIS))
    }

    companion object {
        val NORTH_BAMBOO_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 16.0, 4.0)
        val SOUTH_BAMBOO_SHAPE: VoxelShape = box(0.0, 0.0, 12.0, 16.0, 16.0, 16.0)
        val EAST_BAMBOO_SHAPE: VoxelShape = box(12.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        val WEST_BAMBOO_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 4.0, 16.0, 16.0)
    }
}