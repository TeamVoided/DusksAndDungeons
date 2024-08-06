package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.Waterloggable
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import org.teamvoided.dusk_autumn.util.rotate
import kotlin.math.min


@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
open class LogPileBlock(settings: Settings) : TwoWayFacingBlock(settings), Waterloggable {
    init {
        this.defaultState = stateManager.defaultState
            .with(HANGING, false)
            .with(PILE_LAYERS, 1)
            .with(AXIS, Direction.Axis.X)
            .with(WATERLOGGED, false)
    }

    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        return if (context.stack.isOf(this.asItem()) && state.get(PILE_LAYERS) < MAX_LAYERS) {
            if (context.canReplaceExisting()) {
                context.side == if (state.get(HANGING)) Direction.DOWN else Direction.UP
            } else true
        } else false
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val blockPos = ctx.blockPos
        val oldState = ctx.world.getBlockState(blockPos)
        val fluidState = ctx.world.getFluidState(blockPos)
        if (oldState.isOf(this))
            return oldState.with(PILE_LAYERS, addLayer(oldState.get(PILE_LAYERS)))
        val state = super.getPlacementState(ctx)
            .with(HANGING, false)
            .with(WATERLOGGED, fluidState.fluid === Fluids.WATER)
        val direction = ctx.side
        if (direction != Direction.DOWN && (direction == Direction.UP || !(ctx.hitPos.y - blockPos.y.toDouble() > 0.5)))
            return state
        return InvertAxis(state.with(HANGING, true), getPlayerHorizontalFacingAxis(ctx))
    }

    override fun isSideInvisible(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        return if (stateFrom.block is LogPileBlock &&
            state.get(HANGING) == stateFrom.get(HANGING) &&
            state.get(PILE_LAYERS) <= stateFrom.get(PILE_LAYERS) &&
            state.get(AXIS) == stateFrom.get(AXIS)
        ) true
        else super.isSideInvisible(state, stateFrom, direction)
    }

    override fun getOutlineShape(
        state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext
    ): VoxelShape {
        val rotations = if (state.get(AXIS) == Direction.Axis.Z) 1 else 0
        return (if (state.get(HANGING))
            HANGING_LAYERS_TO_SHAPE[state.get(PILE_LAYERS) - 1]
        else
            DEFAULT_LAYERS_TO_SHAPE[state.get(PILE_LAYERS) - 1]).rotate(rotations)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: WorldAccess, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED))
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        return state
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(PILE_LAYERS, HANGING, AXIS, WATERLOGGED)
    }


    companion object {
        val MAX_LAYERS = 4

        val PILE_LAYERS: IntProperty = IntProperty.of("layers", 1, MAX_LAYERS)
        val HANGING: BooleanProperty = Properties.HANGING
        val AXIS = Properties.HORIZONTAL_AXIS
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED

        val FULL_SHAPE: VoxelShape = twoBoxShapeBottom(16.0)

        val DEFAULT_LAYERS_TO_SHAPE: Array<VoxelShape> = arrayOf(
            createCuboidShape(0.0, 0.0, 2.0, 16.0, 4.0, 14.0),
            twoBoxShapeBottom(8.0),
            twoBoxShapeBottom(12.0),
            FULL_SHAPE,
        )
        val HANGING_LAYERS_TO_SHAPE: Array<VoxelShape> = arrayOf(
            createCuboidShape(2.0, 12.0, 0.0, 14.0, 16.0, 16.0),
            twoBoxShapeTop(8.0),
            twoBoxShapeTop(4.0),
            FULL_SHAPE,
        )

        fun twoBoxShapeBottom(height: Double): VoxelShape {
            return VoxelShapes.union(
                createCuboidShape(0.0, 0.0, 2.0, 16.0, height, 14.0),
                createCuboidShape(2.0, 0.0, 0.0, 14.0, height, 16.0)
            )
        }

        fun twoBoxShapeTop(height: Double): VoxelShape {
            return VoxelShapes.union(
                createCuboidShape(2.0, height, 0.0, 14.0, 16.0, 16.0),
                createCuboidShape(0.0, height, 2.0, 16.0, 16.0, 14.0)
            )
        }

        fun addLayer(i: Int): Int = min(MAX_LAYERS, (i + 1))
    }
}