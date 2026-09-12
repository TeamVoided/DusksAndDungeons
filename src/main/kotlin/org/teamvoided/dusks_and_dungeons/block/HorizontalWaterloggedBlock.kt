package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.rotate

open class HorizontalWaterloggedBlock(properties: Properties) : HorizontalDirectionalBlock(properties),
    SimpleWaterloggedBlock {

    override fun codec(): MapCodec<out HorizontalWaterloggedBlock> = CODEC

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING, WATERLOGGED)
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

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos).type === Fluids.WATER

        val face = ctx.clickedFace
        return super.getStateForPlacement(ctx)
            ?.setValue(WATERLOGGED, waterlogged)
            ?.setValue(FACING, if (face.axis != Direction.Axis.Y) face else ctx.horizontalDirection.opposite)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false)
        else super.getFluidState(state)
    }

    companion object {

        val CODEC: MapCodec<HorizontalWaterloggedBlock> = simpleCodec(::HorizontalWaterloggedBlock)

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        fun createShapeMap(south: VoxelShape): Map<Direction, VoxelShape> {
            return FACING.possibleValues.associateWith { dir -> south.rotate(dir.get2DDataValue()) }
        }

    }
}
