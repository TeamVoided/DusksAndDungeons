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
import net.minecraft.world.level.pathfinder.PathComputationType
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.not_blocks.BlockConnection
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags

open class GravestoneBlock(shape: VoxelShape, centerShape: VoxelShape, properties: Properties) :
    HorizontalWaterloggedBlock(properties), BlockConnection {

    val wallMap = createShapeMap(shape)
    val centerMap = createShapeMap(centerShape)

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(BlockStateProperties.WATERLOGGED, false)
                .setValue(CENTERED, true)
                .setValue(FACING, Direction.NORTH)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(CENTERED)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return ((if (state.getValue(CENTERED)) centerMap else wallMap)[state.getValue(FACING)]) ?: Shapes.block()
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val state = super.getStateForPlacement(ctx) ?: return null
        val face = ctx.clickedFace

        val centered: Boolean
        val dir: Direction
        if (face.axis.isHorizontal) {
            centered = ctx.player?.isShiftKeyDown == true
            dir = face
        } else {
            centered = ctx.player?.isShiftKeyDown != true
            dir = ctx.horizontalDirection.opposite
        }

        return state
            .setValue(CENTERED, centered)
            .setValue(FACING, dir)
    }


    override fun isPathfindable(state: BlockState, navigationType: PathComputationType): Boolean = false

    override fun allConnect(state: BlockState, dir: Direction): Boolean {
        if (state.`is`(DnDBlockTags.SMALL_GRAVESTONES)) return false
        val centered = state.getValue(CENTERED)
        val facing = state.getValue(FACING)
        return (centered && facing.axis === dir.clockWise.axis) || (!centered && facing.opposite == dir)
    }

    companion object {

        val CENTERED: BooleanProperty = BooleanProperty.create("centered")

        val WALL_SHAPE: VoxelShape = Shapes.or(
            box(0.0, 0.0, 0.0, 2.0, 16.0, 6.0), //left
            box(14.0, 0.0, 0.0, 16.0, 16.0, 6.0), //right
            box(0.0, 13.0, 0.0, 16.0, 16.0, 6.0), //top
            box(2.0, 0.0, 1.0, 14.0, 13.0, 5.0) //center
        )
        val CENTER_SHAPE: VoxelShape = Shapes.or(
            box(0.0, 0.0, 5.0, 2.0, 16.0, 11.0), //left
            box(14.0, 0.0, 5.0, 16.0, 16.0, 11.0), //right
            box(0.0, 13.0, 5.0, 16.0, 16.0, 11.0), //top
            box(2.0, 0.0, 6.0, 14.0, 13.0, 10.0) //center
        )

        val SMALL_WALL_SHAPE: VoxelShape = box(3.0, 0.0, 0.0, 13.0, 12.0, 2.0)
        val CENTER_CENTER_SHAPE: VoxelShape = box(3.0, 0.0, 7.0, 13.0, 12.0, 9.0)

        val HEADSTONE_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 16.0, 2.0)
        val CENTER_HEADSTONE_SHAPE: VoxelShape = box(0.0, 0.0, 7.0, 16.0, 16.0, 9.0)

        fun newGrave(properties: Properties) = GravestoneBlock(WALL_SHAPE, CENTER_SHAPE, properties)
        fun newSmallGrave(properties: Properties) = GravestoneBlock(SMALL_WALL_SHAPE, CENTER_CENTER_SHAPE, properties)
        fun newHeadstone(properties: Properties) = GravestoneBlock(HEADSTONE_SHAPE, CENTER_HEADSTONE_SHAPE, properties)
    }
}