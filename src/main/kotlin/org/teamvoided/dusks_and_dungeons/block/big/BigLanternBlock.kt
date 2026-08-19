package org.teamvoided.dusks_and_dungeons.block.big

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.SixWayFacingBlock
import org.teamvoided.dusks_and_dungeons.util.block.isCrouching
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxY
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxZ

open class BigLanternBlock(settings: Properties) : SixWayFacingBlock(settings), SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun codec(): MapCodec<BigLanternBlock> = CODEC

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)

        return super.getStateForPlacement(ctx)
            ?.setValue(WATERLOGGED, fluidState.type == Fluids.WATER)
            ?.setValue(FACING, if (ctx.isCrouching()) ctx.clickedFace.opposite else ctx.clickedFace)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return SHAPES[state.getValue(FACING)] ?: Shapes.block()
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED)
    }

    override fun updateShape(
        state: BlockState, dir: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }
        return super.updateShape(state, dir, neighborState, level, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean = false

    companion object {

        val CODEC: MapCodec<BigLanternBlock> = simpleCodec(::BigLanternBlock)
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        const val MIN_SIZE = 2.5
        const val MIN_SIZE_TOP = 4.5

        val SHAPE_DOWN: VoxelShape = Shapes.or(
            symmetricalBoxY(MIN_SIZE, 3.0, 16.0),
            symmetricalBoxY(MIN_SIZE_TOP, 0.0, 3.0)
        )
        val SHAPE_UP: VoxelShape = Shapes.or(
            symmetricalBoxY(MIN_SIZE, 0.0, 13.0),
            symmetricalBoxY(MIN_SIZE_TOP, 13.0, 16.0)
        )
        val SIDE_SHAPE: VoxelShape = Shapes.or(
            symmetricalBoxZ(MIN_SIZE, 3.0, 16.0),
            symmetricalBoxZ(MIN_SIZE_TOP, 0.0, 3.0)
        )

        val SHAPES = createShapeMap(SHAPE_DOWN, SHAPE_UP, SIDE_SHAPE)

    }
}