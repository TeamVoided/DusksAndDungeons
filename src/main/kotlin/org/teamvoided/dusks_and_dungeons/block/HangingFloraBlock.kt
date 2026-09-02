package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.SupportType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxY

class HangingFloraBlock(properties: Properties) : Block(properties), BonemealableBlock, SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(TIP, true)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(TIP, WATERLOGGED)
    }

    override fun isCollisionShapeFullBlock(blockState: BlockState, level: BlockGetter, pos: BlockPos): Boolean = false

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        val shape = if (state.getValue(TIP)) TIP_SHAPE else SHAPE
        val offset = state.getOffset(level, pos)
        return shape.move(offset.x, offset.y, offset.z)
    }

    override fun propagatesSkylightDown(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean = true

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean = false

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        return defaultBlockState()
            .setValue(TIP, !ctx.level.getBlockState(ctx.clickedPos.below()).`is`(this))
            .setValue(WATERLOGGED, ctx.level.getFluidState(ctx.clickedPos).type == Fluids.WATER)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val abovePos = pos.above()
        val aboveState = level.getBlockState(abovePos)
        return aboveState.`is`(this) ||
                aboveState.`is`(BlockTags.LEAVES) ||
                aboveState.isFaceSturdy(level, abovePos, Direction.DOWN, SupportType.FULL)
    }

    override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true)
        }
    }

    override fun updateShape(
        state: BlockState, dir: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED))
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        if (!state.canSurvive(level, pos))
            level.scheduleTick(pos, this, 1)
        return super.updateShape(state, dir, neighborState, level, pos, neighborPos)
            .setValue(TIP, !level.getBlockState(pos.below()).`is`(this))
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    //Valid means will consume item and display particles
    //Success means will run Perform

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        val distToTop = getHeightAboveUpToMax(level, pos)
        val distToBot = getHeightBelowUpToMax(level, pos)
        return (distToTop + distToBot + 1 < MAX_HEIGHT) && level.getBlockState(pos.below(distToBot + 1)).isAir
    }

    override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean = true

    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        val bellow = pos.below(getHeightBelowUpToMax(level, pos) + 1)
        level.setBlock(bellow, defaultBlockState(), UPDATE_ALL)
    }

    fun getHeightAboveUpToMax(level: BlockGetter, pos: BlockPos): Int {
        var up = 0
        while (up < MAX_HEIGHT && level.getBlockState(pos.above(up + 1)).`is`(this)) {
            ++up
        }
        return up
    }

    fun getHeightBelowUpToMax(level: BlockGetter, pos: BlockPos): Int {
        var down = 0
        while (down < MAX_HEIGHT && level.getBlockState(pos.below(down + 1)).`is`(this)) {
            ++down
        }
        return down
    }

    companion object {

        const val MAX_HEIGHT: Int = 16

        val TIP_SHAPE: VoxelShape = symmetricalBoxY(2.0, 4.0, 16.0)
        val SHAPE: VoxelShape = symmetricalBoxY(2.0, 0.0, 16.0)

        val TIP: BooleanProperty = BlockStateProperties.BOTTOM
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

    }
}