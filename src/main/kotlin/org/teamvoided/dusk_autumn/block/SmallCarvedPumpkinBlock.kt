package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess

class SmallCarvedPumpkinBlock(settings: Settings) : HorizontalFacingBlock(settings) {
    init {
        this.defaultState = stateManager.defaultState
            .with(Properties.WATERLOGGED, false)
            .with(FACING, Direction.NORTH)
    }

    public override fun getCodec(): MapCodec<SmallCarvedPumpkinBlock> {
        return CODEC
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(Properties.WATERLOGGED)
        builder.add(FACING)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(Properties.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(Properties.WATERLOGGED)) Fluids.WATER.getStill(false)
        else super.getFluidState(state)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return defaultState
            .with(Properties.WATERLOGGED, fluidState.isOf(Fluids.WATER))
            .with(FACING, ctx.playerFacing.opposite);
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE
    }

    override fun canPathfindThrough(state: BlockState, navigationType: NavigationType): Boolean {
        return false
    }

    companion object {
        val CODEC: MapCodec<SmallCarvedPumpkinBlock> = createCodec(::SmallCarvedPumpkinBlock)
        private val SHAPE: VoxelShape = createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
    }
}