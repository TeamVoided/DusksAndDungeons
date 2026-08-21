package org.teamvoided.dusks_and_dungeons.block.pumpkin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.FrontAndTop
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
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
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.SixWayFacingBlock
import org.teamvoided.dusks_and_dungeons.util.block.getId
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxY
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBoxZ

open class SmallPumpkinBlock(val carvedBlock: Block, settings: Properties) : SixWayFacingBlock(settings),
    CarvableBlock, SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.UP)
        )
    }

    override fun getId(): ResourceLocation = getId(this)

    override fun getCarvedBlockState(
        stack: ItemStack, state: BlockState, clickedDir: Direction, hit: BlockHitResult,
    ): BlockState {
        val pumpkinDir = state.getValue(FACING)
        val orientation = FrontAndTop.fromFrontAndTop(
            pumpkinDir,
            if (pumpkinDir.axis != Direction.Axis.Y) Direction.UP
            else if (pumpkinDir == Direction.UP) clickedDir.opposite
            else clickedDir
        )

        return carvedBlock.defaultBlockState().setValue(SmallCarvedPumpkinBlock.ORIENTATION, orientation)
    }

    override fun useItemOn(
        stack: ItemStack, state: BlockState, level: Level, pos: BlockPos,
        player: Player, hand: InteractionHand, hit: BlockHitResult,
    ): ItemInteractionResult {
        return if (tryCarve(stack, state, level, pos, player, hand, hit))
            ItemInteractionResult.sidedSuccess(level.isClientSide)
        else
            super.useItemOn(stack, state, level, pos, player, hand, hit)
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

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
        return defaultBlockState()
            .setValue(WATERLOGGED, ctx.level.getFluidState(ctx.clickedPos).`is`(Fluids.WATER))
            .setValue(FACING, ctx.clickedFace)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return SHAPES[state.getValue(FACING)] ?: Shapes.block()
    }

    override fun isPathfindable(state: BlockState, type: PathComputationType): Boolean = false

    companion object {

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        val DOWN_SHAPE = symmetricalBoxY(4.0, 8.0, 16.0)
        val UP_SHAPE = symmetricalBoxY(4.0, 0.0, 8.0)
        val SIDE_SHAPE = symmetricalBoxZ(4.0, 8.0, 16.0)
        val SHAPES = createShapeMap(DOWN_SHAPE, UP_SHAPE, SIDE_SHAPE)

    }
}