package org.teamvoided.dusk_autumn.block

import net.minecraft.block.*
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import org.teamvoided.dusk_autumn.util.rotateColumn

open class HollowLogBlock(settings: Settings) : PillarBlock(settings), Waterloggable {
    init {
        this.defaultState = stateManager.defaultState
            .with(AXIS, Direction.Axis.X)
            .with(WATERLOGGED, false)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val waterlogged = ctx.world.getFluidState(ctx.blockPos).fluid == Fluids.WATER
        return super.getPlacementState(ctx)?.with(WATERLOGGED, waterlogged)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED))
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        return state
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(AXIS, WATERLOGGED)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE.rotateColumn(state.get(AXIS))
    }

    override fun getRaycastShape(state: BlockState, world: BlockView, pos: BlockPos): VoxelShape {
        return VoxelShapes.fullCube()
    }

    companion object {
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        val SHAPE = VoxelShapes.union(
            createCuboidShape(0.0, 0.0, 0.0, 2.0, 16.0, 16.0),
            createCuboidShape(14.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 2.0),
            createCuboidShape(0.0, 0.0, 14.0, 16.0, 16.0, 16.0),
        )
    }
}