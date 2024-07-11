package org.teamvoided.dusk_autumn.block

import net.minecraft.block.*
import net.minecraft.client.util.ParticleUtil
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import java.util.*
import kotlin.math.min


@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
open class LeafPileBlock(settings: Settings) : Block(settings), Waterloggable {
    init {
        this.defaultState = stateManager.defaultState
            .with(DISTANCE, 6)
            .with(HANGING, false)
            .with(PILE_LAYERS, 1)
            .with(WATERLOGGED, false)
    }

    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        return if (context.stack.isOf(this.asItem()) && state.get(PILE_LAYERS) < MAX_LAYERS) {
            if (context.canReplaceExisting()) {
                context.side == if (state.get(HANGING)) Direction.DOWN else Direction.UP
            } else true
        } else false
    }


    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val blockPos = ctx.blockPos
        val oldState = ctx.world.getBlockState(blockPos)
        if (oldState.isOf(this))
            return oldState.with(PILE_LAYERS, addLayer(oldState.get(PILE_LAYERS)))

        val fluidState = ctx.world.getFluidState(blockPos)
        val state = (defaultState.with(HANGING, false)).with(WATERLOGGED, fluidState.fluid === Fluids.WATER)
        val direction = ctx.side
        if (direction != Direction.DOWN && (direction == Direction.UP || !(ctx.hitPos.y - blockPos.y.toDouble() > 0.5)))
            return state

        return state.with(HANGING, true)

    }

    override fun isSideInvisible(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        return if (stateFrom.isOf(this) &&
            state.get(HANGING) == stateFrom.get(HANGING) &&
            state.get(PILE_LAYERS) < MAX_LAYERS &&
            state.get(PILE_LAYERS) <= stateFrom.get(PILE_LAYERS)
        ) true
        else super.isSideInvisible(state, stateFrom, direction)
    }

   /* override fun canPathfindThrough(
        state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType
    ): Boolean = true*/

    override fun getSidesShape(state: BlockState, world: BlockView, pos: BlockPos): VoxelShape = VoxelShapes.empty()

    override fun getOutlineShape(
        state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext
    ): VoxelShape {
        return if (state.get(HANGING))
            HANGING_LAYERS_TO_SHAPE[state.get(PILE_LAYERS) - 1]
        else
            DEFAULT_LAYERS_TO_SHAPE[state.get(PILE_LAYERS) - 1]
    }

    override fun getCollisionShape(
        state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext
    ): VoxelShape = VoxelShapes.empty()


    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        world.setBlockState(pos, updateDistanceFromLogs(state, world, pos), NOTIFY_ALL)
    }

    override fun getOpacity(state: BlockState, world: BlockView, pos: BlockPos): Int = 1

    override fun getStateForNeighborUpdate(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: WorldAccess, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (state.get(LeavesBlock.WATERLOGGED))
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))

        val i = getDistanceFromLog(neighborState) + 1
        if (i != 1 || state.get(LeavesBlock.DISTANCE) as Int != i) {
            world.scheduleBlockTick(pos, this, 1)
        }

        return state
    }

    private fun updateDistanceFromLogs(state: BlockState, world: WorldAccess, pos: BlockPos): BlockState {
        var i = 7
        val mutable = BlockPos.Mutable()

        for (direction in Direction.entries) {
            mutable[pos] = direction
            i = min(i, (getDistanceFromLog(world.getBlockState(mutable)) + 1))
            if (i == 1) break
        }
        return state.with(LeavesBlock.DISTANCE, i)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        if (world.hasRain(pos.up())) {
            if (random.nextInt(15) == 1) {
                val blockPos = pos.down()
                val blockState = world.getBlockState(blockPos)
                if (!blockState.isOpaque || !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP)) {
                    ParticleUtil.spawnParticle(world, pos, random, ParticleTypes.DRIPPING_WATER)
                }
            }
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(DISTANCE, HANGING, PILE_LAYERS, WATERLOGGED)
    }


    companion object {
        val MAX_LAYERS = 4

        val PILE_LAYERS: IntProperty = IntProperty.of("layers", 1, MAX_LAYERS)
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        val DISTANCE: IntProperty = Properties.DISTANCE_1_7
        val HANGING: BooleanProperty = Properties.HANGING

        val FULL_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)

        val DEFAULT_LAYERS_TO_SHAPE: Array<VoxelShape> = arrayOf(
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            FULL_SHAPE,
        )
        val HANGING_LAYERS_TO_SHAPE: Array<VoxelShape> = arrayOf(
            createCuboidShape(0.0, 12.0, 0.0, 16.0, 16.0, 16.0),
            createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
            createCuboidShape(0.0, 4.0, 0.0, 16.0, 16.0, 16.0),
            FULL_SHAPE,
        )


        fun addLayer(i: Int): Int = min(MAX_LAYERS, (i + 1))

        private fun getDistanceFromLog(state: BlockState): Int = getOptionalDistanceFromLog(state).orElse(7)

        private fun getOptionalDistanceFromLog(state: BlockState): OptionalInt {
            return if (state.isIn(BlockTags.LOGS)) OptionalInt.of(0)
            else if (state.contains(DISTANCE)) OptionalInt.of((state.get(DISTANCE)))
            else OptionalInt.empty()
        }
    }
}