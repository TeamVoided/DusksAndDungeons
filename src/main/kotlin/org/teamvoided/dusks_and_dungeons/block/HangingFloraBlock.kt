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
import net.minecraft.world.level.block.state.properties.*
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxY

class HangingFloraBlock(properties: Properties) : Block(properties), BonemealableBlock, SimpleWaterloggedBlock {

    init {
        this.registerDefaultState(
            this.stateDefinition.any().setValue(TIP, true).setValue(WATERLOGGED, false)
        )
    }

    //public MapCodec<net.minecraft.world.level.block.BambooStalkBlock> codec() {
    //    return CODEC;
    //}

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(TIP, WATERLOGGED)
    }

    override fun propagatesSkylightDown(blockState: BlockState, blockGetter: BlockGetter, blockPos: BlockPos): Boolean =
        true

    override fun getShape(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        collisionContext: CollisionContext
    ): VoxelShape {
        val voxelShape: VoxelShape = if (blockState.getValue(TIP)) TIP_SHAPE else SHAPE
        val vec3 = blockState.getOffset(blockGetter, blockPos)
        return voxelShape.move(vec3.x, vec3.y, vec3.z)
    }

    override fun isPathfindable(blockState: BlockState, pathComputationType: PathComputationType): Boolean = false

    override fun isCollisionShapeFullBlock(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos
    ): Boolean = false

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        return defaultBlockState()
            .setValue(TIP, !ctx.level.getBlockState(ctx.clickedPos.below()).`is`(this))
            .setValue(WATERLOGGED, ctx.level.getFluidState(ctx.clickedPos).type == Fluids.WATER)
    }

    override fun canSurvive(blockState: BlockState, levelReader: LevelReader, blockPos: BlockPos): Boolean {
        val abovePos = blockPos.above()
        val aboveState = levelReader.getBlockState(abovePos)
        return aboveState.`is`(this) ||
                aboveState.`is`(BlockTags.LEAVES) ||
                aboveState.isFaceSturdy(levelReader, abovePos, Direction.DOWN, SupportType.FULL)
    }

    override fun tick( //onScheduledTick
        blockState: BlockState,
        serverLevel: ServerLevel,
        blockPos: BlockPos,
        randomSource: RandomSource
    ) {
        if (!blockState.canSurvive(serverLevel, blockPos))
            serverLevel.destroyBlock(blockPos, true)
    }

    override fun updateShape(
        blockState: BlockState,
        direction: Direction,
        blockState2: BlockState,
        level: LevelAccessor,
        blockPos: BlockPos,
        blockPos2: BlockPos
    ): BlockState {
        if (blockState.getValue(WATERLOGGED))
            level.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        if (!blockState.canSurvive(level, blockPos))
            level.scheduleTick(blockPos, this, 1)
        return super.updateShape(blockState, direction, blockState2, level, blockPos, blockPos2)
            .setValue(TIP, !level.getBlockState(blockPos.below()).`is`(this))
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false)
        else super.getFluidState(state)
    }

    //Valid means will consume item and display particles
    //Success means will run Perform

    override fun isValidBonemealTarget(levelReader: LevelReader, blockPos: BlockPos, blockState: BlockState): Boolean {
        val distToTop = this.getHeightAboveUpToMax(levelReader, blockPos)
        val distToBot = this.getHeightBelowUpToMax(levelReader, blockPos)
        return if (distToTop + distToBot + 1 < MAX_HEIGHT) false
        else levelReader.getBlockState(blockPos.below(distToBot)).isAir
    }

    override fun isBonemealSuccess(
        level: Level,
        randomSource: RandomSource,
        blockPos: BlockPos,
        blockState: BlockState
    ): Boolean = true

    override fun performBonemeal(
        serverLevel: ServerLevel,
        randomSource: RandomSource,
        blockPos: BlockPos,
        blockState: BlockState
    ) {
        val bottom = blockPos.below(this.getHeightBelowUpToMax(serverLevel, blockPos))
        serverLevel.setBlock(bottom, this.defaultBlockState(), UPDATE_CLIENTS)
    }

    fun getHeightAboveUpToMax(blockGetter: BlockGetter, blockPos: BlockPos): Int {
        var i = 0
        while (i < MAX_HEIGHT && blockGetter.getBlockState(blockPos.above(i + 1)).`is`(this)) {
            ++i
        }
        return i
    }

    fun getHeightBelowUpToMax(blockGetter: BlockGetter, blockPos: BlockPos): Int {
        var i = 0
        while (i < MAX_HEIGHT && blockGetter.getBlockState(blockPos.below(i + 1)).`is`(this)) {
            ++i
        }
        return i
    }


    companion object {
        val TIP_SHAPE: VoxelShape = symmetricalBoxY(2.0, 4.0, 16.0)
        val SHAPE: VoxelShape = symmetricalBoxY(2.0, 0.0, 16.0)
        const val MAX_HEIGHT: Int = 16

        val TIP: BooleanProperty = BlockStateProperties.BOTTOM
        val WATERLOGGED = BlockStateProperties.WATERLOGGED
    }
}
