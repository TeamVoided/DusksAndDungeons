package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.AzaleaBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.sapling.SaplingGenerators
import org.teamvoided.dusks_and_dungeons.util.rotate

class OvergrowthBushBlock(settings: Properties) : AzaleaBlock(settings), SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(BlockStateProperties.FACING, Direction.DOWN)
                //.setValue(BlockStateProperties.WATERLOGGED, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        //builder.add(BlockStateProperties.WATERLOGGED)
        builder.add(BlockStateProperties.FACING)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val blockPos = pos.relative(state.getValue(BlockStateProperties.FACING))
        return this.mayPlaceOn(world.getBlockState(blockPos), world, blockPos)
    }

    override fun isValidBonemealTarget(world: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        return state.getValue(BlockStateProperties.FACING) != Direction.UP &&
                world.getBlockState(pos.relative(state.getValue(BlockStateProperties.FACING).opposite)).canBeReplaced()
    }

    override fun performBonemeal(world: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        (SaplingGenerators.OVERGROWTH[state.getValue(BlockStateProperties.FACING)] ?: SaplingGenerators.OVERGROWTH_DOWN)
            .growTree(world, world.chunkSource.generator, pos, state, random)
    }

    override fun getShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return BUSH_SHAPES[state.getValue(BlockStateProperties.FACING)] ?: Shapes.block()
    }

    //override fun updateShape(
    //    state: BlockState,
    //    direction: Direction,
    //    neighborState: BlockState,
    //    world: LevelAccessor,
    //    pos: BlockPos,
    //    neighborPos: BlockPos
    //): BlockState {
    //    if (state.getValue(BlockStateProperties.WATERLOGGED)) {
    //        world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
    //    }
    //    return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    //}

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        return super.getStateForPlacement(ctx)!!
            //.setValue(BlockStateProperties.WATERLOGGED, ctx.level.getFluidState(ctx.clickedPos).type == Fluids.WATER)
            .setValue(BlockStateProperties.FACING, ctx.clickedFace.opposite)
    }

    //override fun getFluidState(state: BlockState): FluidState {
    //    return if (state.getValue(BlockStateProperties.WATERLOGGED)) Fluids.WATER.getSource(false)
    //    else super.getFluidState(state)
    //}

    companion object {
        val SHAPE_DOWN: VoxelShape =
            Shapes.or(box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0), box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0))
        val SHAPE_UP: VoxelShape =
            Shapes.or(box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), box(6.0, 8.0, 6.0, 10.0, 16.0, 10.0))
        val SHAPE_SIDE: VoxelShape =
            Shapes.or(box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0), box(6.0, 6.0, 0.0, 10.0, 10.0, 8.0))

        val BUSH_SHAPES = Direction.entries.associateWith { dir ->
            when (dir.get3DDataValue()) {
                0 -> SHAPE_DOWN
                1 -> SHAPE_UP
                2 -> SHAPE_SIDE.rotate(2)
                3 -> SHAPE_SIDE.rotate(3)
                4 -> SHAPE_SIDE.rotate(1)
                5 -> SHAPE_SIDE.rotate(1)
                else -> SHAPE_DOWN
            }
        }

    }
}