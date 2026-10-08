package org.teamvoided.dusks_and_dungeons.block

import net.fabricmc.fabric.api.block.BlockPickInteractionAware
import net.minecraft.ChatFormatting
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
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
                .setValue(UPPER_NORTH_EAST, true).setValue(UPPER_NORTH_WEST, true)
                .setValue(UPPER_SOUTH_EAST, true).setValue(UPPER_SOUTH_WEST, true)
                .setValue(LOWER_NORTH_EAST, true).setValue(LOWER_NORTH_WEST, true)
                .setValue(LOWER_SOUTH_EAST, true).setValue(LOWER_SOUTH_WEST, true)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(
            UPPER_NORTH_WEST, UPPER_NORTH_EAST,
            UPPER_SOUTH_WEST, UPPER_SOUTH_EAST,
            LOWER_NORTH_WEST, LOWER_NORTH_EAST,
            LOWER_SOUTH_WEST, LOWER_SOUTH_EAST
        )
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        var binaryKey = 0

        if (state.getValue(UPPER_NORTH_EAST)) binaryKey = binaryKey or 1
        if (state.getValue(UPPER_NORTH_WEST)) binaryKey = binaryKey or 0b10
        if (state.getValue(UPPER_SOUTH_EAST)) binaryKey = binaryKey or 0b100
        if (state.getValue(UPPER_SOUTH_WEST)) binaryKey = binaryKey or 0b1000

        if (state.getValue(LOWER_NORTH_EAST)) binaryKey = binaryKey or 0b10000
        if (state.getValue(LOWER_NORTH_WEST)) binaryKey = binaryKey or 0b100000
        if (state.getValue(LOWER_SOUTH_EAST)) binaryKey = binaryKey or 0b1000000
        if (state.getValue(LOWER_SOUTH_WEST)) binaryKey = binaryKey or 0b10000000

        return SHAPES[binaryKey]
    }

    open fun getCompositeItem(): Item = Items.HEAVY_CORE

    override fun useWithoutItem(
        state: BlockState, level: Level, pos: BlockPos, player: Player, hit: BlockHitResult,
    ): InteractionResult {
        val mainStack = player.getItemInHand(InteractionHand.MAIN_HAND)
        val offStack = player.getItemInHand(InteractionHand.OFF_HAND)
        if (player.isShiftKeyDown && mainStack.isEmpty && offStack.isEmpty && hit.type == HitResult.Type.BLOCK) {
            val corner = POS_TO_CORNER[getCornerPosition(hit)]
            if (corner != null && state.getValue(corner)) {
                val newState = state.setValue(corner, false)
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
        val cornerToBeAdded = POS_TO_CORNER[clickedPos] ?: return PASS_TO_DEFAULT_BLOCK_INTERACTION

        if (addToComposite(state, cornerToBeAdded, level, pos, player, stack)) {
            return ItemInteractionResult.SUCCESS
        }
        return PASS_TO_DEFAULT_BLOCK_INTERACTION
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        var state = super.getStateForPlacement(ctx) ?: return null
        ctx.itemInHand?.get(DataComponents.BLOCK_STATE)?.let {
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

        var data = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties(mapOf()))
        for (property in PROPS) {
            data = data.with(property, state)
        }
        stack.set(DataComponents.BLOCK_STATE, data)
        stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
        return stack
    }

    override fun appendHoverText(
        stack: ItemStack, ctx: Item.TooltipContext, tooltip: MutableList<Component>, flag: TooltipFlag,
    ) {
        super.appendHoverText(stack, ctx, tooltip, flag)
        stack.get(DataComponents.BLOCK_STATE)?.let {
            tooltip.add(Component.translatable(HEAVY_CUBE_TOOLTIP).withStyle(ChatFormatting.RED))
        }
    }

    companion object {

        val UPPER_NORTH_EAST: BooleanProperty = BooleanProperty.create("upper_north_east")
        val UPPER_NORTH_WEST: BooleanProperty = BooleanProperty.create("upper_north_west")
        val UPPER_SOUTH_EAST: BooleanProperty = BooleanProperty.create("upper_south_east")
        val UPPER_SOUTH_WEST: BooleanProperty = BooleanProperty.create("upper_south_west")

        val LOWER_NORTH_EAST: BooleanProperty = BooleanProperty.create("lower_north_east")
        val LOWER_NORTH_WEST: BooleanProperty = BooleanProperty.create("lower_north_west")
        val LOWER_SOUTH_EAST: BooleanProperty = BooleanProperty.create("lower_south_east")
        val LOWER_SOUTH_WEST: BooleanProperty = BooleanProperty.create("lower_south_west")

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED

        fun getCornerPosition(hit: BlockHitResult): Vec3 {
            return hit.location.add(hit.direction.getOffset())
                .map { it % 1 }
                .map { if (it < 0) 1 + it else it }
                .map { if (it < .5) .25 else .75 }
        }

        fun addToComposite(
            state: BlockState, cornerToBeAdded: BooleanProperty, level: Level,
            pos: BlockPos, player: Player, stack: ItemStack,
        ): Boolean {
            if (state.getValue(cornerToBeAdded)) {
                return false
            }

            val newState = state.setValue(cornerToBeAdded, true)
            pushEntitiesUp(state, newState, level, pos)
            level.setBlockAndUpdateFluid(pos, newState)
            stack.consume(1, player)
            level.playSound(null, pos, SoundEvents.HEAVY_CORE_BREAK, SoundSource.BLOCKS, 0.8f, 1.0f)
            return true
        }

        fun BlockState.hasAnyCorners(): Boolean {
            return block is CompositeBlock &&
                    getValue(UPPER_NORTH_EAST) || getValue(UPPER_NORTH_WEST)
                    || getValue(UPPER_SOUTH_EAST) || getValue(UPPER_SOUTH_WEST)
                    || getValue(LOWER_NORTH_EAST) || getValue(LOWER_NORTH_WEST)
                    || getValue(LOWER_SOUTH_EAST) || getValue(LOWER_SOUTH_WEST)
        }

        fun BlockState.isFull(): Boolean {
            return block is CompositeBlock &&
                    getValue(UPPER_NORTH_EAST) && getValue(UPPER_NORTH_WEST)
                    && getValue(UPPER_SOUTH_EAST) && getValue(UPPER_SOUTH_WEST)
                    && getValue(LOWER_NORTH_EAST) && getValue(LOWER_NORTH_WEST)
                    && getValue(LOWER_SOUTH_EAST) && getValue(LOWER_SOUTH_WEST)
        }

        val PROPS = setOf(
            UPPER_NORTH_EAST, UPPER_NORTH_WEST,
            UPPER_SOUTH_EAST, UPPER_SOUTH_WEST,
            LOWER_NORTH_EAST, LOWER_NORTH_WEST,
            LOWER_SOUTH_EAST, LOWER_SOUTH_WEST
        )

        fun Direction.getOffset() = when (this) {
            Direction.UP -> Vec3(0.0, -0.25, 0.0)
            Direction.DOWN -> Vec3(0.0, 0.25, 0.0)
            Direction.NORTH -> Vec3(0.0, 0.0, 0.25)
            Direction.SOUTH -> Vec3(0.0, 0.0, -0.25)
            Direction.WEST -> Vec3(0.25, 0.0, 0.0)
            Direction.EAST -> Vec3(-0.25, 0.0, 0.0)
        }

        val POS_TO_CORNER = mapOf(
            Vec3(0.25, 0.25, 0.25) to LOWER_NORTH_WEST,
            Vec3(0.75, 0.25, 0.25) to LOWER_NORTH_EAST,

            Vec3(0.25, 0.25, 0.75) to LOWER_SOUTH_WEST,
            Vec3(0.75, 0.25, 0.75) to LOWER_SOUTH_EAST,

            Vec3(0.25, 0.75, 0.25) to UPPER_NORTH_WEST,
            Vec3(0.75, 0.75, 0.25) to UPPER_NORTH_EAST,

            Vec3(0.25, 0.75, 0.75) to UPPER_SOUTH_WEST,
            Vec3(0.75, 0.75, 0.75) to UPPER_SOUTH_EAST,
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