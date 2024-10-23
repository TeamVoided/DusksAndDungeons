package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.Hand
import net.minecraft.util.ItemInteractionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import org.teamvoided.dusk_autumn.block.big.SoulCandleBlock
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags
import org.teamvoided.dusk_autumn.util.FULL_CUBE
import org.teamvoided.dusk_autumn.util.rotate
import org.teamvoided.dusk_autumn.util.rotateFlat90
import org.teamvoided.dusk_autumn.util.spawnCandleParticles
import org.teamvoided.dusk_autumn.world.gen.root.CascadeRootPlacer.Companion.invert

open class CandelabraBlock(val candle: Block, settings: Settings) : AbstractCandleBlock(settings), Waterloggable {
    override fun getCodec(): MapCodec<out AbstractCandleBlock> = CODEC

    init {
        this.defaultState = stateManager.defaultState
            .with(WATERLOGGED, false)
            .with(HORIZONTAL_AXIS, Direction.Axis.X)
            .with(CANDLES, 1)
            .with(LIT, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(WATERLOGGED, HORIZONTAL_AXIS, CANDLES, LIT)
    }

    override fun getOutlineShape(
        state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext
    ): VoxelShape = when (state.get(CANDLES)) {
        1 -> SINGLE_SHAPE
        2 -> DOUBLE_SHAPE
        3 -> TRIPLE_SHAPE
        4 -> QUADRUPLE_SHAPE
        5 -> QUINTUPLE_SHAPE
        else -> FULL_CUBE
    }.rotate(state.getRotations())

    // Particles
    override fun getParticleOffsets(state: BlockState): Iterable<Vec3d> {
        return CANDLE_PARTICLE_OFFSETS[state.get(CANDLES) - 1].rotateFlat90(state.getRotations())
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        if (state.get(AbstractCandleBlock.LIT)) getParticleOffsets(state).forEach {
            spawnParticles(world, it.add(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()), random)
        }
    }

    private fun spawnParticles(world: World, offset: Vec3d, random: RandomGenerator): Unit = when (candle) {
        is SoulCandleBlock -> candle.spawnCandleParticles(world, offset, random)
        is CandleBlock -> spawnCandleParticles(world, offset, random)
        else -> Unit
    }

    // Waterlogging
    override fun getStateForNeighborUpdate(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: WorldAccess, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        if (state.get(HorizontalWaterloggedBlock.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(HorizontalWaterloggedBlock.WATERLOGGED)) Fluids.WATER.getStill(false)
        else super.getFluidState(state)
    }

    override fun tryFillWithFluid(
        world: WorldAccess, pos: BlockPos, state: BlockState, fluidState: FluidState
    ): Boolean {
        return if (!state.get(WATERLOGGED) && fluidState.fluid === Fluids.WATER) {
            val blockState = state.with(WATERLOGGED, true)
            if (state.get(LIT) as Boolean) extinguish(null, blockState, world, pos)
            else world.setBlockState(pos, blockState, NOTIFY_ALL)

            world.scheduleFluidTick(pos, fluidState.fluid, fluidState.fluid.getTickRate(world))
            true
        } else false
    }

    // Logic
    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        return (!context.shouldCancelInteraction() && context.stack.item === asItem() && state.get(CANDLES) < 5) ||
                super.canReplace(state, context)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean =
        sideCoversSmallSquare(world, pos.down(), Direction.UP) && !world.getBlockState(pos.down()).isOf(this)

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val blockState = ctx.world.getBlockState(ctx.blockPos)
        if (blockState.isOf(this)) {
            return blockState.cycle(CANDLES)
        }
        val waterlogged = ctx.world.getFluidState(ctx.blockPos).fluid === Fluids.WATER
        return super.getPlacementState(ctx)
            ?.with(CANDLES, 1)
            ?.with(HorizontalWaterloggedBlock.WATERLOGGED, waterlogged)
            ?.with(HORIZONTAL_AXIS, ctx.playerFacing.axis.invert())
    }

    override fun onInteract(
        stack: ItemStack, state: BlockState, world: World, pos: BlockPos,
        entity: PlayerEntity, hand: Hand, hitResult: BlockHitResult
    ): ItemInteractionResult {
        return if (stack.isEmpty && entity.abilities.allowModifyWorld && state.get(CandleBlock.LIT)) {
            extinguish(entity, state, world, pos)
            ItemInteractionResult.success(world.isClient)
        } else super.onInteract(stack, state, world, pos, entity, hand, hitResult)
    }

    override fun isNotLit(state: BlockState): Boolean = !state.get(CandleBlock.WATERLOGGED) && super.isNotLit(state)

    companion object {
        val CODEC: MapCodec<CandelabraBlock> = createCodec { CandelabraBlock(Blocks.CANDLE, it) }

        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        val HORIZONTAL_AXIS: EnumProperty<Direction.Axis> = Properties.HORIZONTAL_AXIS
        val CANDLES: IntProperty = IntProperty.of("candles", 1, 5)
        val LIT: BooleanProperty = Properties.LIT

        val SINGLE_SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(6.0, 0.0, 6.0, 10.0, 8.0, 10.0),
            createCuboidShape(7.0, 8.0, 7.0, 9.0, 14.0, 9.0)
        )
        val DOUBLE_SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(2.0, 0.0, 6.0, 14.0, 8.0, 10.0),
            createCuboidShape(3.0, 8.0, 7.0, 13.0, 14.0, 9.0)
        )
        val TRIPLE_SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(1.0, 0.0, 6.0, 15.0, 8.0, 10.0),
            createCuboidShape(6.0, 8.0, 6.0, 10.0, 10.0, 10.0),
            createCuboidShape(2.0, 8.0, 7.0, 14.0, 16.0, 9.0)
        )
        val QUADRUPLE_SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0),
        )
        val QUINTUPLE_SHAPE: VoxelShape = VoxelShapes.union(
            createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            createCuboidShape(6.0, 8.0, 6.0, 10.0, 10.0, 10.0),
        )
        val CANDLE_PARTICLE_OFFSETS = listOf(
            listOf(Vec3d(0.5, 1.0, 0.5)),
            listOf(Vec3d(0.25, 1.0, 0.5), Vec3d(0.75, 1.0, 0.5)),
            listOf(Vec3d(0.5, 1.125, 0.5), Vec3d(0.1875, 1.0, 0.5), Vec3d(0.8125, 1.0, 0.5)),
            listOf(Vec3d(0.1875, 1.0, 0.5), Vec3d(0.8125, 1.0, 0.5), Vec3d(0.5, 1.0, 0.1875), Vec3d(0.5, 1.0, 0.8125)),
            listOf(
                Vec3d(0.1875, 1.0, 0.5), Vec3d(0.8125, 1.0, 0.5),
                Vec3d(0.5, 1.125, 0.5),
                Vec3d(0.5, 1.0, 0.1875), Vec3d(0.5, 1.0, 0.8125)
            )
        )

        @JvmStatic
        fun canBeLit(state: BlockState): Boolean {
            return state.isInAndMatches(DnDBlockTags.CANDELABRAS) { it.contains(LIT) && it.contains(WATERLOGGED) }
                    && !state.get(LIT) && !state.get(WATERLOGGED)
        }

        fun BlockState.getRotations(): Int = if (this.get(HORIZONTAL_AXIS) == Direction.Axis.X) 0 else 1
    }
}
