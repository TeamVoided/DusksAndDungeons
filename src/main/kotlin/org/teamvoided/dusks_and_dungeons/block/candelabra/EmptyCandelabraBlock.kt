package org.teamvoided.dusks_and_dungeons.block.candelabra

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.BlockItemStateProperties
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.DnDBlockStateProperties
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.canAddToCandelabra
import org.teamvoided.dusks_and_dungeons.block.candelabra.Candelabra.tryAddToCandelabra

open class EmptyCandelabraBlock(properties: Properties, val filled: CandelabraBlock) : Block(properties),
    SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(CANDLES, 1)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, FACING, CANDLES)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return Candelabra.getBaseShape(state)
    }

    // Waterlogging
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
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false)
        else super.getFluidState(state)
    }

    override fun placeLiquid(level: LevelAccessor, pos: BlockPos, block: BlockState, fluid: FluidState): Boolean {
        return if (!block.getValue(WATERLOGGED) && fluid.type === Fluids.WATER) {
            val state = block.setValue(WATERLOGGED, true)
            level.setBlock(pos, state, UPDATE_ALL)
            level.scheduleTick(pos, fluid.type, fluid.type.getTickDelay(level))
            true
        } else false
    }

    // Logic
    override fun canBeReplaced(state: BlockState, ctx: BlockPlaceContext): Boolean {
        return (!ctx.isSecondaryUseActive && ctx.itemInHand.item === asItem() && state.getValue(CANDLES) < 5) ||
                super.canBeReplaced(state, ctx)
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return canSupportCenter(level, pos.below(), Direction.UP) && !level.getBlockState(pos.below()).`is`(this)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val pos = ctx.clickedPos
        val state = ctx.level.getBlockState(pos)
        if (state.`is`(this) || state.`is`(filled)) {
            return state.cycle(CANDLES)
        }

        if (ctx.itemInHand.get(DataComponents.BLOCK_ENTITY_DATA) != null) {
            return filled.getStateForPlacement(ctx)
        }

        val waterlogged = ctx.level.getFluidState(pos).type === Fluids.WATER
        return super.getStateForPlacement(ctx)
            ?.setValue(CANDLES, 1)
            ?.setValue(WATERLOGGED, waterlogged)
            ?.setValue(FACING, ctx.horizontalDirection.opposite)
    }

    override fun getCloneItemStack(level: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        val stack = super.getCloneItemStack(level, pos, state)
        if (stack.isEmpty) {
            return stack
        }
        if (state.getValue(CANDLES) > 1) {
            stack.set(
                DataComponents.BLOCK_STATE,
                stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties(mapOf())).with(CANDLES, state)
            )
        }
        return stack
    }

    override fun useItemOn(
        stack: ItemStack, state: BlockState, level: Level, pos: BlockPos,
        player: Player, hand: InteractionHand, hit: BlockHitResult,
    ): ItemInteractionResult {
        if (canAddToCandelabra(stack)) {
            level.setBlockAndUpdate(pos, filled.withPropertiesOf(state))
            if (tryAddToCandelabra(level, pos, stack, player)) {
                return ItemInteractionResult.sidedSuccess(level.isClientSide)
            }
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hit)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState {
        return state.setValue(CandelabraBlock.FACING, rotation.rotate(state.getValue(CandelabraBlock.FACING)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState {
        return state.rotate(mirror.getRotation(state.getValue(CandelabraBlock.FACING)))
    }

    companion object {

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val CANDLES = DnDBlockStateProperties.CANDLES
        val LIT: BooleanProperty = BlockStateProperties.LIT

    }
}