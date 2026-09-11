package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.ParticleUtils
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import kotlin.math.min


@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
open class LeafPileBlock(settings: Properties) : Block(settings), SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(HANGING, false)
                .setValue(PERSISTENT, true)
                .setValue(PILE_LAYERS, 1)
                .setValue(WATERLOGGED, false)
        )
    }

    fun defaultWorldState(): BlockState = this.defaultBlockState().setValue(PERSISTENT, false)

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
        if (oldState.`is`(this))
            return oldState.setValue(PILE_LAYERS, addLayer(oldState.getValue(PILE_LAYERS)))

        val fluidState = ctx.level.getFluidState(blockPos)
        val state =
            (defaultBlockState().setValue(HANGING, false)).setValue(WATERLOGGED, fluidState.type === Fluids.WATER)
        val direction = ctx.clickedFace
        if (direction != Direction.DOWN && (direction == Direction.UP || !(ctx.clickLocation.y - blockPos.y.toDouble() > 0.5)))
            return state

        return state.setValue(HANGING, true)

    }

    override fun skipRendering(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        return if (stateFrom.`is`(this) &&
            state.getValue(HANGING) == stateFrom.getValue(HANGING) &&
            state.getValue(PILE_LAYERS) < MAX_LAYERS &&
            state.getValue(PILE_LAYERS) <= stateFrom.getValue(PILE_LAYERS)
        ) true
        else super.skipRendering(state, stateFrom, direction)
    }

    /* override fun canPathfindThrough(
         state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType
     ): Boolean = true*/

    override fun getBlockSupportShape(state: BlockState, world: BlockGetter, pos: BlockPos): VoxelShape = Shapes.empty()

    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext,
    ): VoxelShape {
        return (
                if (state.getValue(HANGING)) HANGING_LAYERS_TO_SHAPE else DEFAULT_LAYERS_TO_SHAPE
                )[state.getValue(PILE_LAYERS) - 1]
    }

    override fun getCollisionShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext,
    ): VoxelShape = Shapes.empty()

    override fun getLightBlock(state: BlockState, world: BlockGetter, pos: BlockPos): Int = 1
    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED))
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))

        if (!state.canSurvive(world, pos)) {
            world.scheduleTick(pos, this, 1)
        }

        return state
    }

    override fun tick(
        blockState: BlockState,
        serverLevel: ServerLevel,
        blockPos: BlockPos,
        randomSource: RandomSource
    ) {
        if (!blockState.canSurvive(serverLevel, blockPos)) {
            serverLevel.destroyBlock(blockPos, true)
        }
    }


    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        if (state.getValue(PERSISTENT)) return true
        val offsetPos = if (state.getValue(HANGING)) pos.above() else pos.below()
        return !level.getBlockState(offsetPos).isAir
    }


    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        if (world.isRainingAt(pos.above())) {
            if (random.nextInt(15) == 1) {
                val blockPos = pos.below()
                val blockState = world.getBlockState(blockPos)
                if (!blockState.canOcclude() || !blockState.isFaceSturdy(world, blockPos, Direction.UP)) {
                    ParticleUtils.spawnParticleBelow(world, pos, random, ParticleTypes.DRIPPING_WATER)
                }
            }
        }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(HANGING, PERSISTENT, PILE_LAYERS, WATERLOGGED)
    }

    companion object {
        const val MAX_LAYERS = 4

        val HANGING = BlockStateProperties.HANGING
        val PERSISTENT = BlockStateProperties.PERSISTENT
        val PILE_LAYERS = IntegerProperty.create("layers", 1, MAX_LAYERS)
        val WATERLOGGED = BlockStateProperties.WATERLOGGED

        val FULL_SHAPE = box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)

        val DEFAULT_LAYERS_TO_SHAPE: List<VoxelShape> = listOf(
            box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            FULL_SHAPE,
        )
        val HANGING_LAYERS_TO_SHAPE: List<VoxelShape> = listOf(
            box(0.0, 12.0, 0.0, 16.0, 16.0, 16.0),
            box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
            box(0.0, 4.0, 0.0, 16.0, 16.0, 16.0),
            FULL_SHAPE,
        )


        fun addLayer(i: Int): Int = min(MAX_LAYERS, (i + 1))
    }
}