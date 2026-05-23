package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import org.teamvoided.dusks_and_dungeons.util.rotate
import kotlin.math.min


@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
open class LogPileBlock(settings: Properties) : TwoWayFacingBlock(settings), SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(HANGING, false)
                .setValue(PILE_LAYERS, 1)
                .setValue(AXIS, Direction.Axis.X)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun canBeReplaced(state: BlockState, context: BlockPlaceContext): Boolean {
        return if (context.itemInHand.`is`(this.asItem()) && state.getValue(PILE_LAYERS) < MAX_LAYERS) {
            if (context.replacingClickedOnBlock()) {
                context.clickedFace == if (state.getValue(HANGING)) Direction.DOWN else Direction.UP
            } else true
        } else false
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val blockPos = ctx.clickedPos
        val oldState = ctx.level.getBlockState(blockPos)
        val fluidState = ctx.level.getFluidState(blockPos)
        if (oldState.`is`(this))
            return oldState.setValue(PILE_LAYERS, addLayer(oldState.getValue(PILE_LAYERS)))
        val state = super.getStateForPlacement(ctx)
            .setValue(WATERLOGGED, fluidState.type == Fluids.WATER)
        val direction = ctx.clickedFace
        if (direction != Direction.DOWN && (direction == Direction.UP || !(ctx.clickLocation.y - blockPos.y.toDouble() > 0.5)))
            return state
        return rotate(state.setValue(HANGING, true))
    }

    override fun skipRendering(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        return if (
            direction.axis != Direction.Axis.Y &&
            stateFrom.block is LogPileBlock &&
            state.getValue(HANGING) == stateFrom.getValue(HANGING) &&
            state.getValue(PILE_LAYERS) <= stateFrom.getValue(PILE_LAYERS) &&
            state.getValue(AXIS) == stateFrom.getValue(AXIS)
        ) true
        else super.skipRendering(state, stateFrom, direction)
    }

    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ): VoxelShape {
        val rotations = if (state.getValue(AXIS) == Direction.Axis.Z) 1 else 0
        return (if (state.getValue(HANGING)) HANGING_LAYERS_TO_SHAPE[state.getValue(PILE_LAYERS) - 1]
        else DEFAULT_LAYERS_TO_SHAPE[state.getValue(PILE_LAYERS) - 1]).rotate(rotations)
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        return state
    }

    override fun getFluidState(state: BlockState): FluidState =
        if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(PILE_LAYERS, HANGING, AXIS, WATERLOGGED)
    }

    companion object {
        val MAX_LAYERS = 4

        val PILE_LAYERS = IntegerProperty.create("layers", 1, MAX_LAYERS)
        val HANGING = BlockStateProperties.HANGING
        val AXIS = BlockStateProperties.HORIZONTAL_AXIS
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        val LAYER_1 = layer(0.0)
        val LAYER_2 = layer(4.0, true)
        val LAYER_3 = layer(8.0)
        val LAYER_4 = layer(12.0, true)

        val DEFAULT_LAYERS_TO_SHAPE: List<VoxelShape> = listOf(
            LAYER_1,
            Shapes.or(LAYER_1, LAYER_2),
            Shapes.or(LAYER_1, LAYER_2, LAYER_3),
            Shapes.or(LAYER_1, LAYER_2, LAYER_3, LAYER_4)
        )
        val HANGING_LAYERS_TO_SHAPE: List<VoxelShape> = listOf(
            LAYER_4,
            Shapes.or(LAYER_4, LAYER_3),
            Shapes.or(LAYER_4, LAYER_3, LAYER_2),
            Shapes.or(LAYER_4, LAYER_3, LAYER_2, LAYER_1)
        )

        fun layer(height: Double, z: Boolean = false): VoxelShape =
            if (z) box(2.0, height, 0.0, 14.0, height + 4, 16.0)
            else box(0.0, height, 2.0, 16.0, height + 4, 14.0)

        fun addLayer(i: Int): Int = min(MAX_LAYERS, (i + 1))
    }
}