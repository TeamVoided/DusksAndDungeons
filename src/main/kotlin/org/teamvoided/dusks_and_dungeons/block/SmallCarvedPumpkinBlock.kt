package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor

class SmallCarvedPumpkinBlock(settings: Properties) : HorizontalDirectionalBlock(settings) {
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(BlockStateProperties.WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH)
        )
    }

    public override fun codec(): MapCodec<SmallCarvedPumpkinBlock> {
        return CODEC
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(BlockStateProperties.WATERLOGGED)
        builder.add(FACING)
    }

    override fun updateShape(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: LevelAccessor,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(BlockStateProperties.WATERLOGGED)) Fluids.WATER.getSource(false)
        else super.getFluidState(state)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)
        return defaultBlockState()
            .setValue(BlockStateProperties.WATERLOGGED, fluidState.`is`(Fluids.WATER))
            .setValue(FACING, ctx.horizontalDirection.opposite)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape = SHAPE

    override fun isPathfindable(state: BlockState, navigationType: PathComputationType): Boolean = false

    companion object {
        val CODEC: MapCodec<SmallCarvedPumpkinBlock> = simpleCodec(::SmallCarvedPumpkinBlock)
        private val SHAPE: VoxelShape = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
    }
}