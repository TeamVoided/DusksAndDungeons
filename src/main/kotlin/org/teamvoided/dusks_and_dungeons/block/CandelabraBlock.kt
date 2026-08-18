package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.block.big.SoulCandleBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.util.rotate
import org.teamvoided.dusks_and_dungeons.util.spawnCandleParticles
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootPlacer.Companion.invert
import org.teamvoided.voidlib.helpers.mc.rotateFlat90
import java.util.function.ToIntFunction

open class CandelabraBlock(val candle: Block, settings: Properties) : AbstractCandleBlock(settings),
    SimpleWaterloggedBlock {
    override fun codec(): MapCodec<out AbstractCandleBlock> = CODEC

    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(HORIZONTAL_AXIS, Direction.Axis.X)
                .setValue(CANDLES, 1)
                .setValue(LIT, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(WATERLOGGED, HORIZONTAL_AXIS, CANDLES, LIT)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return CANDELABRA_SHAPES[state.getValue(HORIZONTAL_AXIS)]?.get(state.getValue(CANDLES)) ?: Shapes.block()
    }

    // Particles
    override fun getParticleOffsets(state: BlockState): Iterable<Vec3> {
        return CANDELABRA_PARTICLE_OFFSETS[state.getValue(HORIZONTAL_AXIS)]?.get(state.getValue(CANDLES))
            ?: RAW_OFFSETS[0]
    }

    override fun animateTick(state: BlockState, world: Level, pos: BlockPos, random: RandomSource) {
        if (state.getValue(AbstractCandleBlock.LIT)) getParticleOffsets(state).forEach {
            spawnParticles(world, it.add(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()), random)
        }
    }

    private fun spawnParticles(world: Level, offset: Vec3, random: RandomSource): Unit = when (candle) {
        is SoulCandleBlock -> candle.spawnCandleParticles(world, offset, random)
        is CandleBlock -> world.spawnCandleParticles(offset, random)
        else -> Unit
    }

    // Waterlogging
    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world))
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false)
        else super.getFluidState(state)
    }

    override fun placeLiquid(
        world: LevelAccessor, pos: BlockPos, state: BlockState, fluidState: FluidState,
    ): Boolean {
        return if (!state.getValue(WATERLOGGED) && fluidState.type === Fluids.WATER) {
            val blockState = state.setValue(WATERLOGGED, true)
            if (state.getValue(LIT) as Boolean) extinguish(null, blockState, world, pos)
            else world.setBlock(pos, blockState, UPDATE_ALL)

            world.scheduleTick(pos, fluidState.type, fluidState.type.getTickDelay(world))
            true
        } else false
    }

    // Logic
    override fun canBeReplaced(state: BlockState, context: BlockPlaceContext): Boolean {
        return (!context.isSecondaryUseActive && context.itemInHand.item === asItem() && state.getValue(CANDLES) < 5) ||
                super.canBeReplaced(state, context)
    }

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean =
        canSupportCenter(world, pos.below(), Direction.UP) && !world.getBlockState(pos.below()).`is`(this)

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val blockState = ctx.level.getBlockState(ctx.clickedPos)
        if (blockState.`is`(this)) {
            return blockState.cycle(CANDLES)
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
            extinguish(entity, state, world, pos)
            ItemInteractionResult.sidedSuccess(world.isClientSide)
        } else super.useItemOn(stack, state, world, pos, entity, hand, hitResult)
    }

    override fun canBeLit(state: BlockState): Boolean = !state.getValue(WATERLOGGED) && super.canBeLit(state)

    companion object {
        val CODEC: MapCodec<CandelabraBlock> = simpleCodec { CandelabraBlock(Blocks.CANDLE, it) }

        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
        val HORIZONTAL_AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.HORIZONTAL_AXIS
        val CANDLES: IntegerProperty = IntegerProperty.create("candles", 1, 5)
        val LIT: BooleanProperty = BlockStateProperties.LIT
        val LUMINANCE = ToIntFunction<BlockState> { if (it.getValue(LIT)) 3 * it.getValue(CANDLES) as Int else 0 }

        val SINGLE_SHAPE: VoxelShape = Shapes.or(
            box(6.0, 0.0, 6.0, 10.0, 8.0, 10.0),
            box(7.0, 8.0, 7.0, 9.0, 14.0, 9.0)
        )
        val DOUBLE_SHAPE: VoxelShape = Shapes.or(
            box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
            box(2.0, 4.0, 6.0, 14.0, 8.0, 10.0),
            // Candles
            box(3.0, 8.0, 7.0, 5.0, 14.0, 9.0),
            box(11.0, 8.0, 7.0, 13.0, 14.0, 9.0),
        )
        val TRIPLE_SHAPE: VoxelShape = Shapes.or(
            box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
            box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
            // Candles
            box(2.0, 8.0, 7.0, 4.0, 14.0, 9.0),
            box(12.0, 8.0, 7.0, 14.0, 14.0, 9.0),
            box(7.0, 10.0, 7.0, 9.0, 16.0, 9.0),
        )
        val QUADRUPLE_SHAPE: VoxelShape = Shapes.or(
            box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0),
            box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
            box(6.0, 4.0, 1.0, 10.0, 8.0, 15.0),
            // Candles
            box(2.0, 8.0, 7.0, 4.0, 14.0, 9.0),
            box(12.0, 8.0, 7.0, 14.0, 14.0, 9.0),
            box(7.0, 8.0, 2.0, 9.0, 14.0, 4.0),
            box(7.0, 8.0, 12.0, 9.0, 14.0, 14.0),
        )
        val QUINTUPLE_SHAPE: VoxelShape = Shapes.or(
            box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0),
            box(1.0, 4.0, 6.0, 15.0, 8.0, 10.0),
            box(6.0, 4.0, 1.0, 10.0, 8.0, 15.0),
            // Candles
            box(2.0, 8.0, 7.0, 4.0, 14.0, 9.0),
            box(12.0, 8.0, 7.0, 14.0, 14.0, 9.0),
            box(7.0, 8.0, 2.0, 9.0, 14.0, 4.0),
            box(7.0, 8.0, 12.0, 9.0, 14.0, 14.0),
            box(7.0, 10.0, 7.0, 9.0, 16.0, 9.0),
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

        val RAW_OFFSETS = listOf(
            listOf(Vec3(0.5, 1.0, 0.5)),
            listOf(Vec3(0.25, 1.0, 0.5), Vec3(0.75, 1.0, 0.5)),
            listOf(Vec3(0.5, 1.125, 0.5), Vec3(0.1875, 1.0, 0.5), Vec3(0.8125, 1.0, 0.5)),
            listOf(Vec3(0.1875, 1.0, 0.5), Vec3(0.8125, 1.0, 0.5), Vec3(0.5, 1.0, 0.1875), Vec3(0.5, 1.0, 0.8125)),
            listOf(
                Vec3(0.1875, 1.0, 0.5), Vec3(0.8125, 1.0, 0.5),
                Vec3(0.5, 1.125, 0.5),
                Vec3(0.5, 1.0, 0.1875), Vec3(0.5, 1.0, 0.8125)
            )
        )

        val CANDELABRA_PARTICLE_OFFSETS = HORIZONTAL_AXIS.possibleValues.associateWith { dir ->
            CANDLES.possibleValues.associateWith { count -> RAW_OFFSETS[count - 1].rotateFlat90(dir.getRotations()) }
        }

        @JvmStatic
        fun canLiteCandelabra(state: BlockState): Boolean {
            return state.`is`(DnDBlockTags.CANDELABRAS) { it.hasProperty(LIT) && it.hasProperty(WATERLOGGED) }
                    && !state.getValue(LIT) && !state.getValue(WATERLOGGED)
        }

        fun Direction.Axis.getRotations(): Int = if (this == Direction.Axis.X) 0 else 1

    }
}
