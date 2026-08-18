package org.teamvoided.dusks_and_dungeons.block.pumpkin

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
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.rotate

open class SmallPumpkinBlock(carvedBlock: Block, settings: Properties) : DnDPumpkinBlock(carvedBlock, settings),
    SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.UP)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED, FACING)
    }

    override fun updateShape(
        state: BlockState, dir: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }
        return super.updateShape(state, dir, neighborState, level, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        return defaultBlockState()
            .setValue(WATERLOGGED, ctx.level.getFluidState(ctx.clickedPos).`is`(Fluids.WATER))
            .setValue(FACING, ctx.clickedFace)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return SHAPES[state.getValue(FACING)] ?: BOTTOM_SHAPE
    }

    override fun isPathfindable(state: BlockState, navigationType: PathComputationType): Boolean = false

    companion object {

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val FACING: DirectionProperty = BlockStateProperties.FACING

        val BOTTOM_SHAPE: VoxelShape = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
        val TOP_SHAPE: VoxelShape = box(4.0, 8.0, 4.0, 12.0, 16.0, 12.0)
        val SIDE_SHAPE: VoxelShape = box(4.0, 4.0, 8.0, 12.0, 12.0, 16.0)
        val SHAPES = Direction.entries.associateWith {
            when (it) {
                Direction.DOWN -> TOP_SHAPE
                Direction.UP -> BOTTOM_SHAPE
                else -> SIDE_SHAPE.rotate(it.opposite.get2DDataValue())
            }
        }

    }
}