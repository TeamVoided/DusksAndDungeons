package org.teamvoided.dusk_autumn.block

import net.minecraft.block.*
import net.minecraft.item.ItemConvertible
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.util.random.RandomGenerator
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import org.teamvoided.dusk_autumn.util.rotate
import kotlin.math.min

class DnDPumpkinStemBlock(
    private val gourdBlock: Block,
    private val smallGourdBlock: Block,
    settings: Settings
) : CropBlock(settings) {

    init {
        this.defaultState = stateManager.defaultState
            .with(AGE, 0)
            .with(FACING, Direction.UP)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        val facing = state.get(FACING)
        return if (facing != Direction.UP && direction != Direction.DOWN) {
            if (world.getBlockState(pos.offset(facing)).isOf(gourdBlock)) state
            else state.with(FACING, Direction.UP)
        } else super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val attached = state.get(FACING)
        return if (state.get(FACING) == Direction.UP) shape(state.get(AGE) + 1.0)
        else ATTACHED_SHAPE.rotate(attached.horizontal)
    }


    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        if (world.getBaseLightLevel(pos, 0) < 9) return

        val moisture = getAvailableMoisture(this, world, pos)
        if (random.nextInt((25.0f / moisture).toInt() + 1) == 0) {
            val age = state.get(AGE)
            if (age < maxAge) world.setBlockState(pos, state.with(AGE, age + 1), Block.NOTIFY_LISTENERS)
            else {
                val direction = Direction.Type.HORIZONTAL.random(random)
                val growPos = pos.offset(direction)
                if (canGrowOnBlock(world.getBlockState(growPos.down()), world, growPos)) {
                    world.setBlockState(growPos, gourdBlock.defaultState, Block.NOTIFY_LISTENERS)
                    world.setBlockState(pos, state.with(FACING, direction), Block.NOTIFY_LISTENERS)
                }
            }
        }
    }

    private fun canGrowOnBlock(floor: BlockState, world: ServerWorld, pos: BlockPos): Boolean =
        (floor.isOf(Blocks.FARMLAND) || floor.isIn(BlockTags.DIRT))
                && world.getBlockState(pos).isAir && hasEnoughLight(world, pos)

    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean =
        floor.isOf(Blocks.FARMLAND)

    override fun getSeedsItem(): ItemConvertible = this.asItem()
    override fun getRandomTicks(state: BlockState): Boolean = state.get(FACING) == Direction.UP
    override fun fertilize(world: ServerWorld, random: RandomGenerator, pos: BlockPos, state: BlockState) {
        val newAge = min(7, (state.get(AGE) + MathHelper.nextInt(world.random, 2, 5)))
        val blockState = state.with(AGE, newAge)
        world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS)
        if (newAge == maxAge) {
            blockState.randomTick(world, pos, world.random)
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(FACING)
    }

    companion object {
        val FACING: DirectionProperty = DirectionProperty.of("facing") { it != Direction.DOWN }
        val ATTACHED_SHAPE: VoxelShape = createCuboidShape(6.0, 0.0, 6.0, 10.0, 10.0, 16.0)
        fun shape(height: Double): VoxelShape {
            return createCuboidShape(7.0, 0.0, 7.0, 9.0, height, 9.0)
        }

    }
}