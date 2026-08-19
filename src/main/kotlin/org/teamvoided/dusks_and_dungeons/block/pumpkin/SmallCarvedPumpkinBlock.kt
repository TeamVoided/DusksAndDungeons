package org.teamvoided.dusks_and_dungeons.block.pumpkin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.FrontAndTop
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.SixWayFacingBlock
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxY
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxZ

class SmallCarvedPumpkinBlock(settings: Properties) : Block(settings) {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(ORIENTATION, FrontAndTop.UP_NORTH)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, ORIENTATION)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(ORIENTATION, rotation.rotation().rotate(state.getValue(ORIENTATION)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.setValue(ORIENTATION, mirror.rotation().rotate(state.getValue(ORIENTATION)))
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

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val clickDir: Direction = ctx.clickedFace
        val verticalDirection = when (clickDir) {
            Direction.DOWN -> ctx.horizontalDirection.opposite
            Direction.UP -> ctx.horizontalDirection
            else -> Direction.UP
        }
        val orientation = FrontAndTop.fromFrontAndTop(clickDir, verticalDirection)

        return defaultBlockState()
            .setValue(WATERLOGGED, ctx.level.getFluidState(ctx.clickedPos).`is`(Fluids.WATER))
            .setValue(ORIENTATION, orientation ?: FrontAndTop.UP_NORTH)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return SHAPES[state.getValue(ORIENTATION).front()] ?: Shapes.block()
    }

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean = false

    companion object {

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val ORIENTATION: EnumProperty<FrontAndTop> = BlockStateProperties.ORIENTATION

        val DOWN_SHAPE = symmetricalBoxY(4.0, 8.0, 16.0)
        val UP_SHAPE = symmetricalBoxY(4.0, 0.0, 8.0)
        val SIDE_SHAPE = symmetricalBoxZ(4.0, 8.0, 16.0)
        val SHAPES = SixWayFacingBlock.createShapeMap(DOWN_SHAPE, UP_SHAPE, SIDE_SHAPE)

    }
}