package org.teamvoided.dusk_autumn.block.big

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.Waterloggable
import net.minecraft.entity.ai.pathing.NavigationType
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
import net.minecraft.world.WorldView

class BigLanternBlock(settings: Settings) : Block(settings), Waterloggable {
    public override fun getCodec(): MapCodec<BigLanternBlock> {
        return CODEC
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return defaultState.with(WATERLOGGED, fluidState.fluid === Fluids.WATER)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        return sideCoversSmallSquare(world, pos.offset(Direction.DOWN), Direction.UP) ||
                sideCoversSmallSquare(world, pos.offset(Direction.UP), Direction.DOWN)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun canPathfindThrough(state: BlockState, navigationType: NavigationType): Boolean {
        return false
    }

    init {
        this.defaultState = stateManager.defaultState.with(WATERLOGGED, false)
    }

    companion object {
        val CODEC: MapCodec<BigLanternBlock> = createCodec { settings: Settings ->
            BigLanternBlock(
                settings
            )
        }
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        val minSize = 2.5
        val maxSize = 13.5
        val minSizeTop = 4.5
        val maxSizeTop = 11.5
        protected val SHAPE: VoxelShape =
            VoxelShapes.union(
                createCuboidShape(minSize, 0.0, minSize, maxSize, 13.0, maxSize),
                createCuboidShape(minSizeTop, 13.0, minSizeTop, maxSizeTop, 16.0, maxSizeTop)
            )
    }
}