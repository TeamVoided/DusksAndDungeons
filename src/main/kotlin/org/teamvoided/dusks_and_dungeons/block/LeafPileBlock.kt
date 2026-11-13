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
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import kotlin.math.min


open class LeafPileBlock(properties: Properties) : Block(properties), SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(HANGING, false)
                .setValue(PERSISTENT, true)
                .setValue(PILE_LAYERS, 1)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(HANGING, PERSISTENT, PILE_LAYERS, WATERLOGGED)
    }

    override fun getShape(
        state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext,
    ): VoxelShape {
        val shapeMap = if (state.getValue(HANGING)) HANGING_LAYERS_TO_SHAPE else DEFAULT_LAYERS_TO_SHAPE
        return shapeMap[state.getValue(PILE_LAYERS) - 1]
    }

    override fun getBlockSupportShape(state: BlockState, level: BlockGetter, pos: BlockPos): VoxelShape = Shapes.empty()

    override fun getCollisionShape(
        state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext,
    ): VoxelShape {
        return Shapes.empty()
    }


    override fun canBeReplaced(state: BlockState, ctx: BlockPlaceContext): Boolean {
        return ctx.itemInHand.`is`(asItem())
                && state.getValue(PILE_LAYERS) < MAX_LAYERS
                && (!ctx.replacingClickedOnBlock() || ctx.clickedFace == if (state.getValue(HANGING)) Direction.DOWN else Direction.UP)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val pos = ctx.clickedPos
        val oldState = ctx.level.getBlockState(pos)
        if (oldState.`is`(this))
            return oldState.setValue(PILE_LAYERS, addLayer(oldState.getValue(PILE_LAYERS)))

        val fluidState = ctx.level.getFluidState(pos)
        val state = super.getStateForPlacement(ctx)
            ?.setValue(HANGING, false)
            ?.setValue(WATERLOGGED, fluidState.type === Fluids.WATER)
            ?: return null
        val dir = ctx.clickedFace
        if (dir != Direction.DOWN && (dir == Direction.UP || !(ctx.clickLocation.y - pos.y.toDouble() > 0.5))) {
            return state
        }

        return state.setValue(HANGING, true)
    }

    override fun skipRendering(state: BlockState, stateFrom: BlockState, direction: Direction): Boolean {
        return stateFrom.`is`(this)
                && state.getValue(HANGING) == stateFrom.getValue(HANGING)
                && state.getValue(PILE_LAYERS) < MAX_LAYERS
                && state.getValue(PILE_LAYERS) <= stateFrom.getValue(PILE_LAYERS)
                || super.skipRendering(state, stateFrom, direction)
    }

    // TODO(end) ask dusk why this is commented out
    /* override fun canPathfindThrough(
         state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType
     ): Boolean = true*/

    override fun getLightBlock(state: BlockState, level: BlockGetter, pos: BlockPos): Int = 1

    override fun updateShape(
        state: BlockState, dir: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }

        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1)
        }

        return state
    }

    override fun tick(state: BlockState, level: ServerLevel, pos: BlockPos, randomSource: RandomSource) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true)
        }
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        if (state.getValue(PERSISTENT)) {
            return true
        }
        val offsetPos = if (state.getValue(HANGING)) pos.above() else pos.below()
        return !level.getBlockState(offsetPos).isAir
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun animateTick(state: BlockState, level: Level, pos: BlockPos, random: RandomSource) {
        if (level.isRainingAt(pos.above()) && random.nextInt(15) == 1) {
            val belowPos = pos.below()
            val belowState = level.getBlockState(belowPos)
            if (!belowState.canOcclude() || !belowState.isFaceSturdy(level, belowPos, Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, pos, random, ParticleTypes.DRIPPING_WATER)
            }
        }
    }

    fun defaultWorldState(): BlockState {
        return defaultBlockState().setValue(PERSISTENT, false)
    }

    companion object {

        const val MAX_LAYERS = 4

        val HANGING: BooleanProperty = BlockStateProperties.HANGING
        val PERSISTENT: BooleanProperty = BlockStateProperties.PERSISTENT
        val PILE_LAYERS: IntegerProperty = IntegerProperty.create("layers", 1, MAX_LAYERS)
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        val FULL_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)

        val DEFAULT_LAYERS_TO_SHAPE: Array<VoxelShape> = arrayOf(
            box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            FULL_SHAPE,
        )
        val HANGING_LAYERS_TO_SHAPE: Array<VoxelShape> = arrayOf(
            box(0.0, 12.0, 0.0, 16.0, 16.0, 16.0),
            box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
            box(0.0, 4.0, 0.0, 16.0, 16.0, 16.0),
            FULL_SHAPE,
        )

        fun addLayer(i: Int): Int = min(MAX_LAYERS, (i + 1))

    }
}