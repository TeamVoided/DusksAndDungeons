package org.teamvoided.dusks_and_dungeons.block.candelabra

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CandleBlock
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.rotate
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootPlacer.Companion.invert

open class EmptyCandelabraBlock(properties: Properties) : Block(properties), SimpleWaterloggedBlock {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(HORIZONTAL_AXIS, Direction.Axis.X)
                .setValue(CANDLES, 1)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, HORIZONTAL_AXIS, CANDLES)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return CANDELABRA_SHAPES[state.getValue(HORIZONTAL_AXIS)]?.get(state.getValue(CANDLES)) ?: Shapes.block()
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

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        return canSupportCenter(world, pos.below(), Direction.UP) && !world.getBlockState(pos.below()).`is`(this)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val state = ctx.level.getBlockState(ctx.clickedPos)
        if (state.`is`(this)) {
            return state.cycle(CANDLES)
        }
        val waterlogged = ctx.level.getFluidState(ctx.clickedPos).type === Fluids.WATER
        return super.getStateForPlacement(ctx)
            ?.setValue(CANDLES, 1)
            ?.setValue(WATERLOGGED, waterlogged)
            ?.setValue(HORIZONTAL_AXIS, ctx.horizontalDirection.axis.invert())
    }

    override fun useItemOn(
        stack: ItemStack, state: BlockState, world: Level, pos: BlockPos,
        entity: Player, hand: InteractionHand, hitResult: BlockHitResult,
    ): ItemInteractionResult {
        return if (stack.isEmpty && entity.abilities.mayBuild && state.getValue(CandleBlock.LIT)) {
//            extinguish(entity, state, world, pos)
            ItemInteractionResult.sidedSuccess(world.isClientSide)
        } else super.useItemOn(stack, state, world, pos, entity, hand, hitResult)
    }

    companion object {

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val HORIZONTAL_AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.HORIZONTAL_AXIS
        val CANDLES: IntegerProperty = IntegerProperty.create("candles", 1, 5)
        val LIT: BooleanProperty = BlockStateProperties.LIT

        val SINGLE_SHAPE: VoxelShape = box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0)
        val DOUBLE_SHAPE: VoxelShape = Shapes.or(
            box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
            box(2.0, 4.0, 6.0, 14.0, 8.0, 10.0),
        )
        val TRIPLE_SHAPE: VoxelShape = Shapes.or(
            box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
            box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
        )
        val QUADRUPLE_SHAPE: VoxelShape = Shapes.or(
            box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
            box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
            box(6.0, 4.0, 1.0, 10.0, 8.0, 15.0),
        )
        val QUINTUPLE_SHAPE: VoxelShape = Shapes.or(
            box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
            box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
            box(6.0, 4.0, 1.0, 10.0, 8.0, 15.0),
        )

        val CANDELABRA_SHAPES = HORIZONTAL_AXIS.possibleValues.associateWith { dir ->
            CANDLES.possibleValues.associateWith { count ->
                when (count) {
                    1 -> SINGLE_SHAPE
                    2 -> DOUBLE_SHAPE
                    3 -> TRIPLE_SHAPE
                    4 -> QUADRUPLE_SHAPE
                    5 -> QUINTUPLE_SHAPE
                    else -> Shapes.block()
                }.rotate(dir.getRotations())
            }
        }

        fun Direction.Axis.getRotations(): Int = if (this == Direction.Axis.X) 0 else 1

    }
}