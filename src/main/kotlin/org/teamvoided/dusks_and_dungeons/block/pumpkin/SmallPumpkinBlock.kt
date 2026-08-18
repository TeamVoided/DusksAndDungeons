package org.teamvoided.dusks_and_dungeons.block.pumpkin

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.level.block.SimpleWaterloggedBlock
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

open class SmallPumpkinBlock(carvedBlock: Block, settings: Properties) :
    DnDPumpkinBlock(carvedBlock, settings), SimpleWaterloggedBlock {
    override val seeds = 2

    init {
        this.registerDefaultState(stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, false))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(BlockStateProperties.WATERLOGGED)
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos
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
        return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, fluidState.`is`(Fluids.WATER))
    }

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext)
            : VoxelShape = SHAPE

    override fun isPathfindable(state: BlockState, navigationType: PathComputationType): Boolean = false

    companion object {
        private val SHAPE: VoxelShape = box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)
    }
}