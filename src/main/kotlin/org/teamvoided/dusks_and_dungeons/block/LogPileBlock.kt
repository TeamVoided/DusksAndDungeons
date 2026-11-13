package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.rotate
import kotlin.math.min


open class LogPileBlock(properties: Properties) : TwoWayFacingBlock(properties), SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(HANGING, false)
                .setValue(PILE_LAYERS, 1)
                .setValue(AXIS, Direction.Axis.X)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(PILE_LAYERS, HANGING, WATERLOGGED)
    }

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        val axis = if (state.getValue(AXIS) == Direction.Axis.Z) 1 else 0
        val shapeMap = if (state.getValue(HANGING)) HANGING_SHAPES else DEFAULT_SHAPES
        return shapeMap[axis].getOrDefault(state.getValue(PILE_LAYERS), Shapes.block())
    }

    override fun canBeReplaced(state: BlockState, ctx: BlockPlaceContext): Boolean {
        return ctx.itemInHand.`is`(asItem())
                && state.getValue(PILE_LAYERS) < MAX_LAYERS
                && (!ctx.replacingClickedOnBlock() || ctx.clickedFace == if (state.getValue(HANGING)) Direction.DOWN else Direction.UP)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val pos = ctx.clickedPos
        val oldState = ctx.level.getBlockState(pos)
        val fluidState = ctx.level.getFluidState(pos)
        if (oldState.`is`(this)) {
            return oldState.setValue(PILE_LAYERS, addLayer(oldState.getValue(PILE_LAYERS)))
        }
        val state = super.getStateForPlacement(ctx)
            ?.setValue(WATERLOGGED, fluidState.type == Fluids.WATER)
            ?: return null
        val dir = ctx.clickedFace
        if (dir != Direction.DOWN && (dir == Direction.UP || !(ctx.clickLocation.y - pos.y.toDouble() > 0.5))) {
            return state
        }
        return rotate(state.setValue(HANGING, true))
    }

    override fun skipRendering(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        return direction.axis != Direction.Axis.Y &&
                stateFrom.block is LogPileBlock &&
                state.getValue(HANGING) == stateFrom.getValue(HANGING) &&
                state.getValue(PILE_LAYERS) <= stateFrom.getValue(PILE_LAYERS) &&
                state.getValue(AXIS) == stateFrom.getValue(AXIS)
                || super.skipRendering(state, stateFrom, direction)
    }

    override fun updateShape(
        state: BlockState, dir: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }
        return state
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    companion object {

        const val MAX_LAYERS = 4

        val PILE_LAYERS: IntegerProperty = IntegerProperty.create("layers", 1, MAX_LAYERS)
        val HANGING: BooleanProperty = BlockStateProperties.HANGING
        val AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.HORIZONTAL_AXIS
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        val LAYER_1 = layer(0.0)
        val LAYER_2 = layer(4.0, true)
        val LAYER_3 = layer(8.0)
        val LAYER_4 = layer(12.0, true)

        val DEFAULT_LAYERS_TO_SHAPE = mapOf(
            1 to LAYER_1,
            2 to Shapes.or(LAYER_1, LAYER_2),
            3 to Shapes.or(LAYER_1, LAYER_2, LAYER_3),
            4 to Shapes.or(LAYER_1, LAYER_2, LAYER_3, LAYER_4)
        )

        val HANGING_LAYERS_TO_SHAPE = mapOf(
            1 to LAYER_4,
            2 to Shapes.or(LAYER_4, LAYER_3),
            3 to Shapes.or(LAYER_4, LAYER_3, LAYER_2),
            4 to Shapes.or(LAYER_4, LAYER_3, LAYER_2, LAYER_1)
        )

        val DEFAULT_SHAPES = Array(2) { axis ->
            DEFAULT_LAYERS_TO_SHAPE.map { (i, shape) -> i to shape.rotate(axis) }.toMap()
        }

        val HANGING_SHAPES = Array(2) { axis ->
            HANGING_LAYERS_TO_SHAPE.map { (i, shape) -> i to shape.rotate(axis) }.toMap()
        }

        fun layer(height: Double, rotated: Boolean = false): VoxelShape {
            return if (rotated)
                box(2.0, height, 0.0, 14.0, height + 4, 16.0)
            else
                box(0.0, height, 2.0, 16.0, height + 4, 14.0)
        }

        fun addLayer(i: Int): Int = min(MAX_LAYERS, (i + 1))

    }
}