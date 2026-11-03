package org.teamvoided.dusks_and_dungeons.block.pumpkin

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.rotate
import kotlin.math.min

class DnDPumpkinStemBlock(val gourdBlock: Block, properties: Properties) : CropBlock(properties) {

    init {
        registerDefaultState(
            stateDefinition.any()
                .setValue(AGE, 0)
                .setValue(FACING, Direction.UP)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(FACING)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return if (state.getValue(FACING) == Direction.UP)
            SHAPE_BY_AGE[state.getValue(AGE)]
        else
            SHAPE_BY_FACING[state.getValue(FACING)]!!
    }

    override fun updateShape(
        state: BlockState, direction: Direction, neighborState: BlockState,
        level: LevelAccessor, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState {
        val facing = state.getValue(FACING)
        return if (facing != Direction.UP && direction != Direction.DOWN) {
            if (level.getBlockState(pos.relative(facing)).`is`(gourdBlock)) state
            else state.setValue(FACING, Direction.UP)
        } else super.updateShape(state, direction, neighborState, level, pos, neighborPos)
    }

    override fun randomTick(state: BlockState, level: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (level.getRawBrightness(pos, 0) < 9) {
            return
        }

        val moisture = getGrowthSpeed(this, level, pos)
        if (random.nextInt((25.0f / moisture).toInt() + 1) == 0) {
            val age = state.getValue(AGE)
            if (age < maxAge) level.setBlock(pos, state.setValue(AGE, age + 1), UPDATE_CLIENTS)
            else {
                val direction = Direction.Plane.HORIZONTAL.getRandomDirection(random)
                val growPos = pos.relative(direction)
                if (canGrowOnBlock(level.getBlockState(growPos.below()), level, growPos)) {
                    level.setBlock(growPos, gourdBlock.defaultBlockState(), UPDATE_CLIENTS)
                    level.setBlock(pos, state.setValue(FACING, direction), UPDATE_CLIENTS)
                }
            }
        }
    }

    private fun canGrowOnBlock(floor: BlockState, level: ServerLevel, pos: BlockPos): Boolean {
        return (floor.`is`(Blocks.FARMLAND) || floor.`is`(BlockTags.DIRT))
                && level.getBlockState(pos).isAir
                && hasSufficientLight(level, pos)
    }

    override fun mayPlaceOn(floor: BlockState, level: BlockGetter, pos: BlockPos): Boolean {
        return floor.`is`(Blocks.FARMLAND)
    }

    override fun getBaseSeedId(): ItemLike = asItem()

    override fun isRandomlyTicking(state: BlockState): Boolean = state.getValue(FACING) == Direction.UP

    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        val newAge = min(7, (state.getValue(AGE) + Mth.nextInt(level.random, 2, 5)))
        val blockState = state.setValue(AGE, newAge)
        level.setBlock(pos, blockState, UPDATE_CLIENTS)
        if (newAge == maxAge) {
            blockState.randomTick(level, pos, level.random)
        }
    }

    companion object {

        val FACING: DirectionProperty = DirectionProperty.create("facing") { it != Direction.DOWN }

        val ATTACHED_SHAPE: VoxelShape = box(6.0, 0.0, 6.0, 10.0, 10.0, 16.0)
        val SHAPE_BY_FACING = FACING.possibleValues.associateWith {
            if (it == Direction.UP) Shapes.block()
            else ATTACHED_SHAPE.rotate(it.get2DDataValue())
        }

        fun shape(height: Double): VoxelShape = box(7.0, 0.0, 7.0, 9.0, height, 9.0)

        // From StemBlock.class
        val SHAPE_BY_AGE = arrayOf(
            shape(2.0),
            shape(4.0),
            shape(6.0),
            shape(8.0),
            shape(10.0),
            shape(12.0),
            shape(14.0),
            shape(16.0)
        )

    }
}