package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.ShapeContext
import net.minecraft.block.Waterloggable
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import org.teamvoided.dusk_autumn.util.rotate

open class GravestoneBlock(settings: Settings) : HorizontalFacingBlock(settings), Waterloggable {
    init {
        this.defaultState = stateManager.defaultState
            .with(Properties.WATERLOGGED, false)
            .with(CENTERED, true)
            .with(FACING, Direction.NORTH)
    }

    public override fun getCodec(): MapCodec<GravestoneBlock> {
        return CODEC
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(Properties.WATERLOGGED)
        builder.add(CENTERED)
        builder.add(FACING)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(Properties.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(Properties.WATERLOGGED)) Fluids.WATER.getStill(false)
        else super.getFluidState(state)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        val player = ctx.player?.isSneaking ?: false
        return defaultState
            .with(Properties.WATERLOGGED, fluidState.isOf(Fluids.WATER))
            .with(CENTERED, !player)
            .with(FACING, ctx.playerFacing.opposite)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val rotations = state.get(FACING).horizontal
        val shape = if (state.get(CENTERED)) CENTER_SHAPE else SHAPE
        return shape.rotate(rotations)
    }

    override fun canPathfindThrough(state: BlockState, navigationType: NavigationType): Boolean {
        return false
    }

    companion object {
        val CODEC: MapCodec<GravestoneBlock> = createCodec(::GravestoneBlock)
        val CENTERED: BooleanProperty = BooleanProperty.of("centered")

        //private val SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 6.0)
        //private val CENTER_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 5.0, 16.0, 16.0, 11.0)
        private val SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(0.0, 0.0, 0.0, 2.0, 16.0, 6.0), //left
            createCuboidShape(14.0, 0.0, 0.0, 16.0, 16.0, 6.0), //right
            createCuboidShape(0.0, 13.0, 0.0, 16.0, 16.0, 6.0), //top
            createCuboidShape(2.0, 0.0, 1.0, 14.0, 13.0, 5.0) //center
        )
        private val CENTER_SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(0.0, 0.0, 5.0, 2.0, 16.0, 11.0), //left
            createCuboidShape(14.0, 0.0, 5.0, 16.0, 16.0, 11.0), //right
            createCuboidShape(0.0, 13.0, 5.0, 16.0, 16.0, 11.0), //top
            createCuboidShape(2.0, 0.0, 6.0, 14.0, 13.0, 10.0) //center
        )
    }
}