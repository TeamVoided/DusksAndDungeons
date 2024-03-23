package org.teamvoided.dusk_autumn.block

import net.minecraft.block.*
import net.minecraft.client.util.ParticleUtil
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleEffect
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

open class LeafPileBlock(settings: Settings?) : Block(settings), Waterloggable {

    override fun isSideInvisible(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        return if (stateFrom.isOf(this)) true
        else super.isSideInvisible(state, stateFrom, direction)
    }

    override fun canPathfindThrough(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        type: NavigationType
    ): Boolean {
        return true
    }
    override fun getSidesShape(state: BlockState, world: BlockView, pos: BlockPos): VoxelShape {
        return VoxelShapes.empty()
    }

    override fun getOutlineShape(
        state: BlockState, world: BlockView?, pos: BlockPos?, context: ShapeContext?
    ): VoxelShape {
        return SHAPE
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return createCuboidShape(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    }

    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator?) {
        world.setBlockState(pos, updateDistanceFromLogs(state, world, pos), NOTIFY_ALL)
    }

    override fun getOpacity(state: BlockState?, world: BlockView?, pos: BlockPos?): Int {
        return 1
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction?,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos?,
        neighborPos: BlockPos?
    ): BlockState {
        if (state.get(LeavesBlock.WATERLOGGED) as Boolean) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        val i = getDistanceFromLog(neighborState) + 1
        if (i != 1 || state.get(LeavesBlock.DISTANCE) as Int != i) {
            world.scheduleBlockTick(pos, this, 1)
        }

        return state
    }

    private fun updateDistanceFromLogs(state: BlockState, world: WorldAccess, pos: BlockPos): BlockState {
        var i = 7
        val mutable = BlockPos.Mutable()
        val var5 = Direction.entries.toTypedArray()
        val var6 = var5.size

        for (var7 in 0 until var6) {
            val direction = var5[var7]
            mutable[pos] = direction as Direction
            i = min(i.toDouble(), (getDistanceFromLog(world.getBlockState(mutable)) + 1).toDouble())
                .toInt()
            if (i == 1) {
                break
            }
        }

        return state.with(LeavesBlock.DISTANCE, i) as BlockState
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED) as Boolean) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        if (world.hasRain(pos.up())) {
            if (random.nextInt(15) == 1) {
                val blockPos = pos.down()
                val blockState = world.getBlockState(blockPos)
                if (!blockState.isOpaque || !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP)) {
                    ParticleUtil.spawnParticle(world, pos, random, ParticleTypes.DRIPPING_WATER as ParticleEffect)
                }
            }
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(DISTANCE, WATERLOGGED)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        val blockState = (defaultState as BlockState).with(
            WATERLOGGED,
            fluidState.fluid === Fluids.WATER
        ) as BlockState
        return updateDistanceFromLogs(blockState, ctx.world, ctx.blockPos)
    }

    init {
        this.defaultState = (((stateManager.defaultState as BlockState).with(DISTANCE, 7) as BlockState).with(
            WATERLOGGED, false
        ) as BlockState)
    }

    companion object {
        val SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);
        const val MAX_DISTANCE: Int = 7
        val DISTANCE: IntProperty = Properties.DISTANCE_1_7
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        private const val TICK_DELAY = 1

        private fun getDistanceFromLog(state: BlockState): Int {
            return getOptionalDistanceFromLog(state).orElse(7)
        }

        fun getOptionalDistanceFromLog(state: BlockState): OptionalInt {
            return if (state.isIn(BlockTags.LOGS)) {
                OptionalInt.of(0)
            } else {
                if (state.contains(DISTANCE)) OptionalInt.of(
                    (state.get(DISTANCE) as Int)
                ) else OptionalInt.empty()
            }
        }
    }
}




//    override fun getStateForNeighborUpdate(
//        state: BlockState,
//        direction: Direction?,
//        neighborState: BlockState,
//        world: WorldAccess,
//        pos: BlockPos?,
//        neighborPos: BlockPos?
//    ): BlockState {
//        if (state.get(WATERLOGGED) as Boolean) {
//            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
//        }
//
//        val i = getDistanceFromLog(neighborState) + 1
//        if (i != 1 || state.get(DISTANCE) as Int != i) {
//            world.scheduleBlockTick(pos, this, 1)
//        }
//
//        return state
//    }
//
//    private fun updateDistanceFromLogs(state: BlockState, world: WorldAccess, pos: BlockPos): BlockState {
//        var i = 7
//        val mutable = BlockPos.Mutable()
//        val var5 = Direction.entries.toTypedArray()
//        val var6 = var5.size
//
//        for (var7 in 0 until var6) {
//            val direction = var5[var7]
//            mutable[pos] = direction
//            i = min(i.toDouble(), (getDistanceFromLog(world.getBlockState(mutable)) + 1).toDouble()).toInt()
//            if (i == 1) {
//                break
//            }
//        }
//
//        return state.with(DISTANCE, i) as BlockState
//    }
//
//    private fun getDistanceFromLog(state: BlockState): Int {
//        return getOptionalDistanceFromLog(state).orElse(7)
//    }
//
//    fun getOptionalDistanceFromLog(state: BlockState): OptionalInt {
//        return if (state.isIn(BlockTags.LOGS)) {
//            OptionalInt.of(0)
//        } else {
//            if (state.contains(DISTANCE)) OptionalInt.of((state.get(DISTANCE) as Int)) else OptionalInt.empty()
//        }
//    }
//
//    override fun getFluidState(state: BlockState): FluidState {
//        return if (state.get(WATERLOGGED) as Boolean) Fluids.WATER.getStill(false) else super.getFluidState(state)
//    }
//
//    override fun randomDisplayTick(state: BlockState?, world: World, pos: BlockPos, random: RandomGenerator) {
//        if (world.hasRain(pos.up())) {
//            if (random.nextInt(15) == 1) {
//                val blockPos = pos.down()
//                val blockState = world.getBlockState(blockPos)
//                if (!blockState.isOpaque || !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP)) {
//                    ParticleUtil.spawnParticle(world, pos, random, ParticleTypes.DRIPPING_WATER as ParticleEffect)
//                }
//            }
//        }
//    }
//
//    override fun appendProperties(builder: StateManager.Builder<Block?, BlockState?>) {
//        builder.add(DISTANCE, WATERLOGGED)
//    }
//
//    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
//        val fluidState = ctx.world.getFluidState(ctx.blockPos)
//        val blockState = (defaultState as BlockState).with(
//            WATERLOGGED,
//            fluidState.fluid === Fluids.WATER
//        ) as BlockState
//        return updateDistanceFromLogs(blockState, ctx.world, ctx.blockPos)
//    }
//
//
//
//    override fun isSideInvisible(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
//        return if (stateFrom.isOf(this)) true
//        else super.isSideInvisible(state, stateFrom, direction)
//    }
//
//    override fun canPathfindThrough(state: BlockState?, world: BlockView?, pos: BlockPos?, type: NavigationType): Boolean {
//        return true
//    }
//}