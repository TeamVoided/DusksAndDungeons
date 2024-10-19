package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.ShapeContext
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import org.teamvoided.dusk_autumn.util.rotate

open class CandelabraBlock(val candle: Block, settings: Settings) : HorizontalWaterloggedBlock(settings) {
    init {
        this.defaultState = stateManager.defaultState
            .with(WATERLOGGED, false)
            .with(FACING, Direction.NORTH)
            .with(CANDLES, 1)
    }


    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        return (!context.shouldCancelInteraction() && context.stack.item === asItem() && state.get(CANDLES) < 5) ||
                super.canReplace(state, context)
    }


    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val blockState = ctx.world.getBlockState(ctx.blockPos)
        if (blockState.isOf(this)) {
            return blockState.cycle(CANDLES)
        }
        return super.getPlacementState(ctx)
            .with(CANDLES, 1)
    }

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext):
            VoxelShape = when (state.get(CANDLES)) {
        1 -> SINGLE_SHAPE
        2 -> DOUBLE_SHAPE
        3 -> TRIPLE_SHAPE
        4 -> QUADRUPLE_SHAPE
        else -> QUINTUPLE_SHAPE
    }.rotate(state.get(HorizontalFacingBlock.FACING).horizontal)


    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(CANDLES)
    }

    companion object {
        val SINGLE_SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(6.0, 0.0, 6.0, 10.0, 8.0, 10.0),
            createCuboidShape(7.0, 8.0, 7.0, 9.0, 14.0, 9.0)
        )
        val DOUBLE_SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(6.0, 0.0, 6.0, 10.0, 8.0, 10.0),
            createCuboidShape(2.0, 5.0, 6.0, 14.0, 8.0, 10.0),
            createCuboidShape(3.0, 8.0, 7.0, 5.0, 14.0, 9.0),
            createCuboidShape(11.0, 8.0, 7.0, 13.0, 14.0, 9.0)
        )
        val TRIPLE_SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
            createCuboidShape(0.0, 5.0, 6.0, 16.0, 8.0, 10.0),
            createCuboidShape(1.0, 8.0, 7.0, 3.0, 14.0, 9.0),
            createCuboidShape(13.0, 8.0, 7.0, 15.0, 14.0, 9.0),
            createCuboidShape(7.0, 10.0, 7.0, 9.0, 16.0, 9.0)
        )
        val QUADRUPLE_SHAPE: VoxelShape = VoxelShapes.union(
            DOUBLE_SHAPE,
            createCuboidShape(6.0, 5.0, 2.0, 10.0, 8.0, 14.0),
            createCuboidShape(7.0, 8.0, 3.0, 9.0, 14.0, 5.0),
            createCuboidShape(7.0, 8.0, 11.0, 9.0, 14.0, 13.0)
        )
        val QUINTUPLE_SHAPE: VoxelShape = VoxelShapes.union(
            TRIPLE_SHAPE,
            createCuboidShape(6.0, 5.0, 0.0, 10.0, 8.0, 16.0),
            createCuboidShape(7.0, 8.0, 1.0, 9.0, 14.0, 3.0),
            createCuboidShape(7.0, 8.0, 13.0, 9.0, 14.0, 15.0),
        )
        val CANDLES: IntProperty = IntProperty.of("candles", 1, 5)
    }
}
