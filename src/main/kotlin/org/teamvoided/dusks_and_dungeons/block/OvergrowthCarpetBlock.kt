package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.block.state.properties.WallSide
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.util.block.symmetricalBox
import org.teamvoided.dusks_and_dungeons.util.rotate

class OvergrowthCarpetBlock(settings: Properties) : Block(settings), BonemealableBlock {

    init {
        this.registerDefaultState(
            stateDefinition.any()
                .setValue(BOTTOM, true)
                .setValue(NORTH, WallSide.NONE)
                .setValue(EAST, WallSide.NONE)
                .setValue(SOUTH, WallSide.NONE)
                .setValue(WEST, WallSide.NONE)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(BOTTOM, NORTH, EAST, SOUTH, WEST)
    }

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        var shape = getCollisionShape(state, world, pos, ctx)
        WALL_SIDES.forEachIndexed { idx, it ->
            val side = state.getValue(it)
            if (side == WallSide.LOW) {
                shape = Shapes.or(
                    shape,
                    WALL_SMALL_SHAPE.rotate(idx - 1)
                )
            } else if (side == WallSide.TALL) {
                shape = Shapes.or(
                    shape,
                    WALL_TALL_SHAPE.rotate(idx - 1)
                )
            }
        }
        return shape
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockGetter,
        pos: BlockPos,
        ctx: CollisionContext
    ): VoxelShape = if (state.getValue(BOTTOM)) CARPET_SHAPE else Shapes.empty()

    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean {
        val floorState = world.getBlockState(pos.below())
        return if (state.getValue(BOTTOM)) !floorState.isAir
        else floorState.`is`(this) && floorState.getValue(BOTTOM)
    }

    override fun isValidBonemealTarget(
        levelReader: LevelReader,
        blockPos: BlockPos,
        blockState: BlockState
    ): Boolean {
        return false
        TODO("Not yet implemented")
    }

    override fun isBonemealSuccess(
        level: Level,
        randomSource: RandomSource,
        blockPos: BlockPos,
        blockState: BlockState
    ): Boolean = true

    override fun performBonemeal(world: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        TODO("Not yet implemented")
    }

    override fun propagatesSkylightDown(state: BlockState, world: BlockGetter, pos: BlockPos): Boolean =
        true


    override fun rotate(blockState: BlockState, rotation: Rotation): BlockState {
        when (rotation) {
            Rotation.CLOCKWISE_180 -> return blockState
                .setValue(NORTH, blockState.getValue(SOUTH))
                .setValue(EAST, blockState.getValue(WEST))
                .setValue(SOUTH, blockState.getValue(NORTH))
                .setValue(WEST, blockState.getValue(EAST))

            Rotation.COUNTERCLOCKWISE_90 -> return blockState
                .setValue(NORTH, blockState.getValue(EAST))
                .setValue(EAST, blockState.getValue(SOUTH))
                .setValue(SOUTH, blockState.getValue(WEST))
                .setValue(WEST, blockState.getValue(NORTH))

            Rotation.CLOCKWISE_90 -> return blockState
                .setValue(NORTH, blockState.getValue(WEST))
                .setValue(EAST, blockState.getValue(NORTH))
                .setValue(SOUTH, blockState.getValue(EAST))
                .setValue(WEST, blockState.getValue(SOUTH))

            else -> return blockState

        }
    }

    override fun mirror(blockState: BlockState, mirror: Mirror): BlockState {
        when (mirror) {
            Mirror.LEFT_RIGHT -> return blockState
                .setValue(NORTH, blockState.getValue(SOUTH))
                .setValue(SOUTH, blockState.getValue(NORTH))

            Mirror.FRONT_BACK -> return blockState
                .setValue(EAST, blockState.getValue(WEST))
                .setValue(WEST, blockState.getValue(EAST))

            else -> return super.mirror(blockState, mirror)

        }
    }

    companion object {
        val BOTTOM = BlockStateProperties.BOTTOM
        val NORTH: EnumProperty<WallSide> = BlockStateProperties.NORTH_WALL
        val EAST: EnumProperty<WallSide> = BlockStateProperties.EAST_WALL
        val SOUTH: EnumProperty<WallSide> = BlockStateProperties.SOUTH_WALL
        val WEST: EnumProperty<WallSide> = BlockStateProperties.WEST_WALL
        val WALL_SIDES = listOf(NORTH, EAST, SOUTH, WEST)

        val CARPET_SHAPE = symmetricalBox(0.0, 0.0, 1.0)
        val WALL_SMALL_SHAPE = box(0.0, 0.0, 0.0, 16.0, 8.0, 1.0)
        val WALL_TALL_SHAPE = box(0.0, 0.0, 0.0, 16.0, 10.0, 1.0)
    }
}