package org.teamvoided.dusks_and_dungeons.block.big

import com.mojang.serialization.MapCodec
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import org.teamvoided.dusks_and_dungeons.block.SixWayFacingBlock
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxY
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxZ
import org.teamvoided.dusks_and_dungeons.util.rotate

open class BigLanternBlock(settings: Properties) : SixWayFacingBlock(settings), SimpleWaterloggedBlock {
    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(WATERLOGGED, false)
        )
    }

    public override fun codec(): MapCodec<BigLanternBlock> = CODEC

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)

        return super.getStateForPlacement(ctx)
            .setValue(WATERLOGGED, fluidState.type == Fluids.WATER)
            .setValue(FACING, if (ctx.player?.isCrouching ?: false) ctx.clickedFace.opposite else ctx.clickedFace)
    }

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape =
        SHAPES[state.getValue(FACING)] ?: Shapes.block()

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED)
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState =
        if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)

    override fun isPathfindable(state: BlockState, navigationType: PathComputationType): Boolean = false

    companion object {
        val CODEC: MapCodec<BigLanternBlock> = simpleCodec(::BigLanternBlock)
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        protected const val MIN_SIZE = 2.5
        protected const val MIN_SIZE_TOP = 4.5
        protected val SHAPE_UP: VoxelShape = Shapes.or(
            symmetricalBoxY(MIN_SIZE, 0.0, 13.0),
            symmetricalBoxY(MIN_SIZE_TOP, 13.0, 16.0)
        )
        protected val SHAPE_DOWN: VoxelShape = Shapes.or(
            symmetricalBoxY(MIN_SIZE, 3.0, 16.0),
            symmetricalBoxY(MIN_SIZE_TOP, 0.0, 3.0)
        )
        protected val NORTH_SHAPE: VoxelShape = Shapes.or(
            symmetricalBoxZ(MIN_SIZE, 3.0, 16.0),
            symmetricalBoxZ(MIN_SIZE_TOP, 0.0, 3.0)
        )

        val SHAPES = Direction.entries.associateWith { dir ->
            when (dir.get3DDataValue()) {
                0 -> SHAPE_DOWN
                1 -> SHAPE_UP
                2 -> NORTH_SHAPE
                3 -> NORTH_SHAPE.rotate(2)
                4 -> NORTH_SHAPE.rotate(3)
                5 -> NORTH_SHAPE.rotate(1)
                else -> SHAPE_DOWN
            }
        }
    }
}