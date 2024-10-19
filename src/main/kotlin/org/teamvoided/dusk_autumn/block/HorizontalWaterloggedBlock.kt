package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.WorldAccess

open class HorizontalWaterloggedBlock(settings: Settings) : HorizontalFacingBlock(settings), Waterloggable {
    override fun getCodec(): MapCodec<HorizontalWaterloggedBlock> = CODEC

    init {
        this.defaultState = stateManager.defaultState.with(FACING, Direction.NORTH).with(WATERLOGGED, false)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: WorldAccess, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val waterlogged = ctx.world.getFluidState(ctx.blockPos).fluid === Fluids.WATER
        return defaultState
            .with(FACING, ctx.playerFacing.opposite)
            .with(WATERLOGGED, waterlogged)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false)
        else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING, WATERLOGGED)
    }

    companion object {
        val CODEC: MapCodec<HorizontalWaterloggedBlock> = createCodec(::HorizontalWaterloggedBlock)
        val FACING: DirectionProperty = HorizontalFacingBlock.FACING
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
    }
}
