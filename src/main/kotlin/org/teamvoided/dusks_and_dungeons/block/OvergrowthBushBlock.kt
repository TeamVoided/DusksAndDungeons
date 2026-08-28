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
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.sapling.SaplingGenerators

class OvergrowthBushBlock(properties: Properties) : AzaleaBlock(properties) {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(FACING, Direction.DOWN)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val offsetPos = pos.relative(state.getValue(FACING))
        return mayPlaceOn(level.getBlockState(offsetPos), level, offsetPos)
    }

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        return state.getValue(FACING) != Direction.UP &&
                level.getBlockState(pos.relative(state.getValue(FACING).opposite)).canBeReplaced()
    }

    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        val treeGrower = SaplingGenerators.OVERGROWTH[state.getValue(FACING)] ?: SaplingGenerators.OVERGROWTH_DOWN
        treeGrower.growTree(level, level.chunkSource.generator, pos, state, random)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return SHAPES[state.getValue(FACING)] ?: Shapes.block()
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        return super.getStateForPlacement(ctx)?.setValue(FACING, ctx.clickedFace.opposite)
    }

    companion object {

        val FACING: DirectionProperty = BlockStateProperties.FACING

        val SHAPE_DOWN: VoxelShape =
            Shapes.or(box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0), box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0))
        val SHAPE_UP: VoxelShape = Shapes.or(box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), box(6.0, 8.0, 6.0, 10.0, 16.0, 10.0))
        val SHAPE_SIDE: VoxelShape =
            Shapes.or(box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0), box(6.0, 6.0, 0.0, 10.0, 10.0, 8.0))
        val SHAPES = SixWayFacingBlock.createShapeMap(SHAPE_DOWN, SHAPE_UP, SHAPE_SIDE)

    }
}