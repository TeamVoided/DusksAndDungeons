package org.teamvoided.dusk_autumn.block

import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView
import org.teamvoided.dusk_autumn.block.big.SoulCandleBlock
import org.teamvoided.dusk_autumn.util.rotate
import org.teamvoided.dusk_autumn.util.rotateFlat90
import org.teamvoided.dusk_autumn.util.spawnCandleParticles

open class CandelabraBlock(val candle: Block, settings: Settings) : HorizontalWaterloggedBlock(settings) {
    init {
        this.defaultState = stateManager.defaultState
            .with(WATERLOGGED, false)
            .with(FACING, Direction.NORTH)
            .with(CANDLES, 1)
            .with(LIT, false)
    }

    private fun getParticleOffsets(state: BlockState): Iterable<Vec3d> {
        return CANDLE_PARTICLE_OFFSETS[state.get(CANDLES) - 1].rotateFlat90(state.get(HorizontalFacingBlock.FACING).horizontal)
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: RandomGenerator) {
        if (state.get(AbstractCandleBlock.LIT)) getParticleOffsets(state).forEach {
            spawnParticles(world, it.add(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()), random)
        }
    }

    private fun spawnParticles(world: World, offset: Vec3d, random: RandomGenerator) {
        when (candle) {
            is SoulCandleBlock -> candle.spawnCandleParticles(world, offset, random)
            is CandleBlock -> spawnCandleParticles(world, offset, random)
            else -> return
        }
    }

    override fun canReplace(state: BlockState, context: ItemPlacementContext): Boolean {
        return (!context.shouldCancelInteraction() && context.stack.item === asItem() && state.get(CANDLES) < 5) ||
                super.canReplace(state, context)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean =
        sideCoversSmallSquare(world, pos.offset(Direction.DOWN), Direction.UP)

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val blockState = ctx.world.getBlockState(ctx.blockPos)
        if (blockState.isOf(this)) {
            return blockState.cycle(CANDLES)
        }
        return super.getPlacementState(ctx)
            .with(CANDLES, 1)
    }

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext):
            VoxelShape = when (state.get(CANDLES)) {
        1 -> SINGLE_SHAPE
        2 -> DOUBLE_SHAPE
        3 -> TRIPLE_SHAPE
        4 -> QUADRUPLE_SHAPE
        else -> QUINTUPLE_SHAPE
    }.rotate(state.get(HorizontalFacingBlock.FACING).horizontal)


    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(CANDLES)
        builder.add(LIT)
    }

    companion object {
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
                Vec3d(0.5, 1.125, 0.5),
                Vec3d(0.1875, 1.0, 0.5),
                Vec3d(0.8125, 1.0, 0.5),
                Vec3d(0.5, 1.0, 0.1875),
                Vec3d(0.5, 1.0, 0.8125)
            ),
        )
        val CANDLES: IntProperty = IntProperty.of("candles", 1, 5)
        val LIT: BooleanProperty = Properties.LIT
    }
}
