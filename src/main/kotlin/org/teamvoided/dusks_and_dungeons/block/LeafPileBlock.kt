package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.ParticleUtils
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
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
import java.util.*
import kotlin.math.min


@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
open class LeafPileBlock(settings: Properties) : Block(settings), SimpleWaterloggedBlock {
    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(DISTANCE, 6)
                .setValue(HANGING, false)
                .setValue(PILE_LAYERS, 1)
                .setValue(WATERLOGGED, false)
        )
    }

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

    override fun tick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        world.setBlock(pos, updateDistanceFromLogs(state, world, pos), UPDATE_ALL)
    }

    override fun getLightBlock(state: BlockState, world: BlockGetter, pos: BlockPos): Int = 1
    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED))
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))

        val i = getDistanceFromLog(neighborState) + 1
        if (i != 1 || state.getValue(DISTANCE) != i) {
            world.scheduleTick(pos, this, 1)
        }

        return state
    }

    private fun updateDistanceFromLogs(state: BlockState, world: LevelAccessor, pos: BlockPos): BlockState {
        var i = 7
        val mutable = BlockPos.MutableBlockPos()

        for (direction in Direction.entries) {
            mutable.setWithOffset(pos, direction)
            i = min(i, (getDistanceFromLog(world.getBlockState(mutable)) + 1))
            if (i == 1) break
        }
        return state.setValue(DISTANCE, i)
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
        builder.add(DISTANCE, HANGING, PILE_LAYERS, WATERLOGGED)
    }

    companion object {
        const val MAX_LAYERS = 4

        val PILE_LAYERS = IntegerProperty.create("layers", 1, MAX_LAYERS)
        val WATERLOGGED = BlockStateProperties.WATERLOGGED
        val DISTANCE = BlockStateProperties.DISTANCE
        val HANGING = BlockStateProperties.HANGING

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
        private fun getDistanceFromLog(state: BlockState): Int = getOptionalDistanceFromLog(state).orElse(7)
        private fun getOptionalDistanceFromLog(state: BlockState): OptionalInt {
            return if (state.`is`(BlockTags.LOGS)) OptionalInt.of(0)
            else if (state.hasProperty(DISTANCE)) OptionalInt.of((state.getValue(DISTANCE)))
            else OptionalInt.empty()
        }
    }
}