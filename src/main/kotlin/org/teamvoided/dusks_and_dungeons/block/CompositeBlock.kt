package org.teamvoided.dusks_and_dungeons.block

import net.fabricmc.fabric.api.block.BlockPickInteractionAware
import net.minecraft.ChatFormatting
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents.BLOCK_STATE
import net.minecraft.network.chat.Component
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.BlockItemStateProperties
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HeavyCoreBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.*

open class CompositeBlock(properties: Properties) : HeavyCoreBlock(properties), BlockPickInteractionAware {

    init {
        registerDefaultState(
            defaultBlockState()
                .setValue(SHAPE, 255)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(SHAPE)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return SHAPES[state.getValue(SHAPE)]
    }

    open fun getCompositeItem(): Item = Items.HEAVY_CORE

    override fun useWithoutItem(
        state: BlockState, level: Level, pos: BlockPos, player: Player, hit: BlockHitResult,
    ): InteractionResult {
        val mainStack = player.getItemInHand(InteractionHand.MAIN_HAND)
        val offStack = player.getItemInHand(InteractionHand.OFF_HAND)
        if (player.isShiftKeyDown && mainStack.isEmpty && offStack.isEmpty && hit.type == HitResult.Type.BLOCK) {
            val cornerMask = POS_TO_MASK[getCornerPosition(hit)]
            if (cornerMask != null && state.getValue(SHAPE) and cornerMask != 0) {
                val newState = state.setValue(SHAPE, state.getValue(SHAPE) and cornerMask.inv())
                level.setBlockAndUpdate(pos, newState)
                if (state.getValue(WATERLOGGED)) level.scheduleFluidTick(pos, state)
                if (!(player.isCreative && player.inventory.contains(getCompositeItem().defaultInstance))) {
                    player.giveItem(ItemStack(getCompositeItem()))
                }
                if (!newState.hasAnyCorners()) {
                    if (newState.getValue(WATERLOGGED))
                        level.setBlockAndUpdate(pos, newState.fluidState.createLegacyBlock())
                    else
                        level.removeBlock(pos, false)
                }
                level.playSound(null, pos, SoundEvents.HEAVY_CORE_BREAK, SoundSource.BLOCKS, 0.8f, 1.0f)
                return InteractionResult.SUCCESS
            }
        }
        return super.useWithoutItem(state, level, pos, player, hit)
    }

    override fun useItemOn(
        stack: ItemStack, state: BlockState, level: Level,
        pos: BlockPos, player: Player, hand: InteractionHand, hit: BlockHitResult,
    ): ItemInteractionResult {
        if (hit.type != HitResult.Type.BLOCK || !stack.`is`(getCompositeItem()) || state.isFull())
            return super.useItemOn(stack, state, level, pos, player, hand, hit)

        val clickedPos = getCornerPosition(hit).add(hit.direction.getOffset().map { it * -2 })
        val cornerToBeAdded = POS_TO_MASK[clickedPos] ?: return PASS_TO_DEFAULT_BLOCK_INTERACTION

        if (addToComposite(state, cornerToBeAdded, level, pos, player, stack)) {
            return ItemInteractionResult.SUCCESS
        }
        return PASS_TO_DEFAULT_BLOCK_INTERACTION
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        var state = super.getStateForPlacement(ctx) ?: return null
        ctx.itemInHand?.get(BLOCK_STATE)?.let {
            state = it.apply(state)
        }
        return state
    }

    override fun getPickedStack(
        state: BlockState, level: BlockGetter, pos: BlockPos, player: Player, hit: HitResult,
    ): ItemStack {
        val stack = state.block.asItem().defaultInstance
        if (state.block !is CompositeBlock || stack.isEmpty || !player.isCreative || !player.isShiftKeyDown || state.isFull()) {
            return stack
        }
        stack[BLOCK_STATE] = stack.getOrDefault(BLOCK_STATE, BlockItemStateProperties.EMPTY).with(SHAPE, state)
        return stack
    }

    override fun appendHoverText(
        stack: ItemStack, ctx: Item.TooltipContext, tooltip: MutableList<Component>, flag: TooltipFlag,
    ) {
        super.appendHoverText(stack, ctx, tooltip, flag)
        stack.get(BLOCK_STATE)?.let {
            tooltip.add(Component.translatable(HEAVY_CUBE_TOOLTIP).withStyle(ChatFormatting.RED))
        }
    }

    companion object {

        val SHAPE = DnDBlockStateProperties.COMPOSITE_SHAPE
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        fun getCornerPosition(hit: BlockHitResult): Vec3 {
            return hit.location
                .subtract(Vec3.atLowerCornerOf(hit.blockPos))
                .add(hit.direction.getOffset())
                .map { if (it < .5) .25 else .75 }
        }

        fun addToComposite(
            state: BlockState, newCornerMask: Int, level: Level,
            pos: BlockPos, player: Player, stack: ItemStack,
        ): Boolean {
            val currentShape = state.getValue(SHAPE)
            if (currentShape and newCornerMask != 0) {
                return false
            }

            val newState = state.setValue(SHAPE, currentShape or newCornerMask)
            pushEntitiesUp(state, newState, level, pos)
            level.setBlockAndUpdateFluid(pos, newState)
            stack.consume(1, player)
            level.playSound(null, pos, SoundEvents.HEAVY_CORE_BREAK, SoundSource.BLOCKS, 0.8f, 1.0f)
            return true
        }

        fun BlockState.hasAnyCorners(): Boolean = hasProperty(SHAPE) && getValue(SHAPE) > 0
        fun BlockState.isFull(): Boolean = hasProperty(SHAPE) && getValue(SHAPE) == 255

        fun Direction.getOffset() = when (this) {
            Direction.UP -> Vec3(0.0, -0.25, 0.0)
            Direction.DOWN -> Vec3(0.0, 0.25, 0.0)
            Direction.NORTH -> Vec3(0.0, 0.0, 0.25)
            Direction.SOUTH -> Vec3(0.0, 0.0, -0.25)
            Direction.WEST -> Vec3(0.25, 0.0, 0.0)
            Direction.EAST -> Vec3(-0.25, 0.0, 0.0)
        }

        val POS_TO_MASK = mapOf(
            Vec3(0.75, 0.75, 0.25) to 0b1,
            Vec3(0.25, 0.75, 0.25) to 0b10,
            Vec3(0.75, 0.75, 0.75) to 0b100,
            Vec3(0.25, 0.75, 0.75) to 0b1000,

            Vec3(0.75, 0.25, 0.25) to 0b10000,
            Vec3(0.25, 0.25, 0.25) to 0b100000,
            Vec3(0.75, 0.25, 0.75) to 0b1000000,
            Vec3(0.25, 0.25, 0.75) to 0b10000000,
        )

        val TOP_NE: VoxelShape = box(8.0, 8.0, 0.0, 16.0, 16.0, 8.0)
        val TOP_NW: VoxelShape = box(0.0, 8.0, 0.0, 8.0, 16.0, 8.0)
        val TOP_SE: VoxelShape = box(8.0, 8.0, 8.0, 16.0, 16.0, 16.0)
        val TOP_SW: VoxelShape = box(0.0, 8.0, 8.0, 8.0, 16.0, 16.0)

        val BOTTOM_NE: VoxelShape = box(8.0, 0.0, 0.0, 16.0, 8.0, 8.0)
        val BOTTOM_NW: VoxelShape = box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0)
        val BOTTOM_SE: VoxelShape = box(8.0, 0.0, 8.0, 16.0, 8.0, 16.0)
        val BOTTOM_SW: VoxelShape = box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0)

        val SHAPES = createShapeMap()

        fun createShapeMap(): Array<VoxelShape> {
            var shape: VoxelShape
            return Array(256) { idx ->
                shape = Shapes.empty()

                if ((idx and 1) == 1) shape = Shapes.or(shape, TOP_NE)
                if (((idx shr 1) and 1) == 1) shape = Shapes.or(shape, TOP_NW)
                if (((idx shr 2) and 1) == 1) shape = Shapes.or(shape, TOP_SE)
                if (((idx shr 3) and 1) == 1) shape = Shapes.or(shape, TOP_SW)

                if (((idx shr 4) and 1) == 1) shape = Shapes.or(shape, BOTTOM_NE)
                if (((idx shr 5) and 1) == 1) shape = Shapes.or(shape, BOTTOM_NW)
                if (((idx shr 6) and 1) == 1) shape = Shapes.or(shape, BOTTOM_SE)
                if (((idx shr 7) and 1) == 1) shape = Shapes.or(shape, BOTTOM_SW)

                if (idx == 0) Shapes.block() else shape
            }
        }

    }
}