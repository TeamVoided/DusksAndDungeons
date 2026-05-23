package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.ItemLike
import net.minecraft.tags.BlockTags
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import org.teamvoided.dusks_and_dungeons.util.rotate
import kotlin.math.min

class DnDPumpkinStemBlock(private val gourdBlock: Block, settings: Properties) : CropBlock(settings) {

    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(AGE, 0)
                .setValue(FACING, Direction.UP)
        )
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: LevelAccessor, pos: BlockPos, neighborPos: BlockPos
    ): BlockState {
        val facing = state.getValue(FACING)
        return if (facing != Direction.UP && direction != Direction.DOWN) {
            if (world.getBlockState(pos.relative(facing)).`is`(gourdBlock)) state
            else state.setValue(FACING, Direction.UP)
        } else super.updateShape(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun getShape(
        state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext
    ): VoxelShape {
        val attached = state.getValue(FACING)
        return if (state.getValue(FACING) == Direction.UP) shape(state.getValue(AGE) + 1.0)
        else ATTACHED_SHAPE.rotate(attached.get2DDataValue())
    }


    override fun randomTick(state: BlockState, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (world.getRawBrightness(pos, 0) < 9) return

        val moisture = getGrowthSpeed(this, world, pos)
        if (random.nextInt((25.0f / moisture).toInt() + 1) == 0) {
            val age = state.getValue(AGE)
            if (age < maxAge) world.setBlock(pos, state.setValue(AGE, age + 1), UPDATE_CLIENTS)
            else {
                val direction = Direction.Plane.HORIZONTAL.getRandomDirection(random)
                val growPos = pos.relative(direction)
                if (canGrowOnBlock(world.getBlockState(growPos.below()), world, growPos)) {
                    world.setBlock(growPos, gourdBlock.defaultBlockState(), UPDATE_CLIENTS)
                    world.setBlock(pos, state.setValue(FACING, direction), UPDATE_CLIENTS)
                }
            }
        }
    }

    private fun canGrowOnBlock(floor: BlockState, world: ServerLevel, pos: BlockPos): Boolean =
        (floor.`is`(Blocks.FARMLAND) || floor.`is`(BlockTags.DIRT))
                && world.getBlockState(pos).isAir && hasSufficientLight(world, pos)

    override fun mayPlaceOn(floor: BlockState, world: BlockGetter, pos: BlockPos): Boolean =
        floor.`is`(Blocks.FARMLAND)

    override fun getBaseSeedId(): ItemLike = this.asItem()
    override fun isRandomlyTicking(state: BlockState): Boolean = state.getValue(FACING) == Direction.UP
    override fun performBonemeal(world: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        val newAge = min(7, (state.getValue(AGE) + Mth.nextInt(world.random, 2, 5)))
        val blockState = state.setValue(AGE, newAge)
        world.setBlock(pos, blockState, UPDATE_CLIENTS)
        if (newAge == maxAge) blockState.randomTick(world, pos, world.random)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(FACING)
    }

    companion object {
        val FACING: DirectionProperty = DirectionProperty.create("facing") { it != Direction.DOWN }
        val ATTACHED_SHAPE: VoxelShape = box(6.0, 0.0, 6.0, 10.0, 10.0, 16.0)
        fun shape(height: Double): VoxelShape = box(7.0, 0.0, 7.0, 9.0, height, 9.0)
    }
}