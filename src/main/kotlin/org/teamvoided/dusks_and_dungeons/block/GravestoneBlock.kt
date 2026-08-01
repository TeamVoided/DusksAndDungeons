package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
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
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.block.centerGravestoneShape
import org.teamvoided.dusks_and_dungeons.util.block.gravestoneShape
import org.teamvoided.dusks_and_dungeons.util.rotate

open class GravestoneBlock(val shape: VoxelShape, val centerShape: VoxelShape, settings: Properties) :
    HorizontalDirectionalBlock(settings), SimpleWaterloggedBlock {

    val wallMap = FACING.possibleValues.associateWith { shape.rotate(it.get2DDataValue()) }
    val centerMap = FACING.possibleValues.associateWith { centerShape.rotate(it.get2DDataValue()) }

    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(BlockStateProperties.WATERLOGGED, false)
                .setValue(CENTERED, true)
                .setValue(FACING, Direction.NORTH)
        )
    }

    public override fun codec(): MapCodec<GravestoneBlock> = CODEC

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(BlockStateProperties.WATERLOGGED)
        builder.add(CENTERED)
        builder.add(FACING)
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(BlockStateProperties.WATERLOGGED)) Fluids.WATER.getSource(false)
        else super.getFluidState(state)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        val fluidState = ctx.level.getFluidState(ctx.clickedPos)
        val face = ctx.clickedFace

        val centered: Boolean
        val direction: Direction
        if (face.axis.isHorizontal) {
            centered = false
            direction = face
        } else {
            centered = ctx.player?.isShiftKeyDown != true
            direction = ctx.horizontalDirection.opposite
        }

        return defaultBlockState()
            .setValue(BlockStateProperties.WATERLOGGED, fluidState.`is`(Fluids.WATER))
            .setValue(CENTERED, centered)
            .setValue(FACING, direction)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return (if (state.getValue(CENTERED)) centerMap else wallMap)[state.getValue(FACING)]!!
    }

    override fun isPathfindable(state: BlockState, navigationType: PathComputationType): Boolean = false

    companion object {

        val CENTERED: BooleanProperty = BooleanProperty.create("centered")
        val CODEC: MapCodec<GravestoneBlock> =
            simpleCodec { GravestoneBlock(gravestoneShape, centerGravestoneShape, it) }

    }
}