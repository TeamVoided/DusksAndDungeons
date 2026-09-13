package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.not_blocks.BlockConnection
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxY
import org.teamvoided.dusks_and_dungeons.util.rotate

class SconceBlock(properties: Properties) : HorizontalWaterloggedBlock(properties), BlockConnection {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(HANGING, false)
                .setValue(WATERLOGGED, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(HANGING)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return SHAPES[state.getValue(FACING)]?.get(state.getValue(HANGING)) ?: Shapes.block()
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val state = super.getStateForPlacement(ctx) ?: return null
        val pos = ctx.clickedPos
        val dir = ctx.clickedFace
        if (dir != Direction.UP && (dir == Direction.DOWN || (ctx.clickLocation.y - pos.y > 0.5))) {
            return state
        }

        return state.setValue(HANGING, true)
    }

    override fun allConnect(state: BlockState, dir: Direction): Boolean = state.getValue(FACING) == dir.opposite

    companion object {

        val HANGING: BooleanProperty = BlockStateProperties.HANGING

        val SHAPE: VoxelShape = Shapes.or(
            box(6.0, 4.0, 14.0, 10.0, 16.0, 16.0),
            box(6.0, 14.0, 11.0, 10.0, 16.0, 14.0),
            symmetricalBoxY(5.0, 14.0, 16.0),
        )
        val HANGING_SHAPE: VoxelShape = Shapes.or(
            box(6.0, 0.0, 14.0, 10.0, 12.0, 16.0),
            box(6.0, 0.0, 11.0, 10.0, 2.0, 14.0),
            symmetricalBoxY(5.0, 0.0, 2.0),
        )

        val SHAPES = FACING.possibleValues.associateWith { dir ->
            HANGING.possibleValues.associateWith { hanging ->
                (if (hanging) HANGING_SHAPE else SHAPE).rotate(dir.opposite.get2DDataValue())
            }
        }
    }
}