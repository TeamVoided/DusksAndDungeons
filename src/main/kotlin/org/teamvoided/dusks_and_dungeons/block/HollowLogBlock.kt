package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.rotateColumn

open class HollowLogBlock(settings: Properties) : RotatedPillarBlock(settings), SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(AXIS, Direction.Axis.X)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        return super.getStateForPlacement(ctx)
            ?.setValue(WATERLOGGED, ctx.level.getFluidState(ctx.clickedPos).type == Fluids.WATER)
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        return state
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED))
            Fluids.WATER.getSource(false)
        else
            super.getFluidState(state)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AXIS, WATERLOGGED)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return SHAPES[state.getValue(AXIS)] ?: Shapes.block()
    }

    override fun getInteractionShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape = Shapes.block()

    companion object {

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        val SHAPE: VoxelShape = Shapes.or(
            box(0.0, 0.0, 0.0, 2.0, 16.0, 16.0),
            box(14.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 16.0, 2.0),
            box(0.0, 0.0, 14.0, 16.0, 16.0, 16.0),
        )

        val SHAPES = Direction.Axis.entries.associateWith { SHAPE.rotateColumn(it) }

    }
}