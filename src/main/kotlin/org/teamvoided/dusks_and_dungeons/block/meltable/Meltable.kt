package org.teamvoided.dusks_and_dungeons.block.meltable

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.EnchantmentTags
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.level.Level
import net.minecraft.world.level.LightLayer
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.Half
import net.minecraft.world.level.block.state.properties.SlabType
import net.minecraft.world.level.block.state.properties.StairsShape
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.util.counterClockWise

object Meltable {

    // region Melting Logic
    val waterState: BlockState get() = Blocks.WATER.defaultBlockState()

    fun meltAfterBreak(level: Level, pos: BlockPos, stack: ItemStack) {
        if (!EnchantmentHelper.hasTag(stack, EnchantmentTags.PREVENTS_ICE_MELTING)) {
            meltWithCheck(level, pos)
        }
    }

    fun meltFromLight(state: BlockState, level: Level, pos: BlockPos) {
        if (level.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(level, pos)) {
            meltWithAlwaysWater(level, pos)
        }
    }

    @Suppress("DEPRECATION")
    fun meltWithCheck(level: Level, pos: BlockPos) {
        if (level.dimensionType().ultraWarm()) {
            level.removeBlock(pos, false)
            return
        }
        val state = level.getBlockState(pos.below())
        if (state.blocksMotion() || state.liquid()) {
            level.setBlockAndUpdate(pos, waterState)
        }
    }

    fun meltWithAlwaysWater(level: Level, pos: BlockPos) {
        if (level.dimensionType().ultraWarm()) {
            level.removeBlock(pos, false)
        } else {
            level.setBlockAndUpdate(pos, waterState)
            level.neighborChanged(pos, waterState.block, pos)
        }
    }
    // endregion

    @JvmStatic
    fun shouldCullFace(state: BlockState, neighborState: BlockState, direction: Direction): Boolean {
        if (!neighborState.`is`(DnDBlockTags.ICE_BLOCK_TRANSLUCENT)) {
            return false
        }
        val (shape, mask) = getFaceShape(state, direction)
        val (neighborShape, neighborMask) = getFaceShape(neighborState, direction.opposite)

        if (shape == FaceShape.WALL) {
            return (neighborMask == FULL_SHAPE.second) || (neighborShape == FaceShape.WALL &&
                    wallCheck(state, direction, neighborState))
        }

        return (flipMask(neighborMask) and mask) == mask
    }

    fun flipMask(neighborMask: Int): Int {
        return ((neighborMask and 0b1010) shr 1) or ((neighborMask and 0b0101) shl 1)
    }

    enum class FaceShape {
        BIT_SHAPE, WALL, UNKNOW
    }

    val EMPTY_SHAPE = FaceShape.BIT_SHAPE to 0b0000
    val FULL_SHAPE = FaceShape.BIT_SHAPE to 0b1111

    val TOP_SHAPE = FaceShape.BIT_SHAPE to 0b11_00
    val BOTTOM_SHAPE = FaceShape.BIT_SHAPE to 0b00_11
    val RIGHT_SHAPE = FaceShape.BIT_SHAPE to 0b01_01
    val LEFT_SHAPE = FaceShape.BIT_SHAPE to 0b10_10

    val TL_SHAPE = FaceShape.BIT_SHAPE to 0b01_11
    val TR_SHAPE = FaceShape.BIT_SHAPE to 0b10_11

    val BL_SHAPE = FaceShape.BIT_SHAPE to 0b11_01
    val BR_SHAPE = FaceShape.BIT_SHAPE to 0b11_10

    fun getFaceShape(state: BlockState, dir: Direction): Pair<FaceShape, Int> {
        return when (state.block) {
            is SlabBlock -> slabShape(state, dir)
            is StairBlock -> stairShape(state, dir)
            is HalfTransparentBlock -> FULL_SHAPE
            is WallBlock -> FaceShape.WALL to 0b0000
            else -> FaceShape.UNKNOW to 0b0000
        }
    }

    fun slabShape(slab: BlockState, dir: Direction): Pair<FaceShape, Int> {
        return when (slab.getValue(SlabBlock.TYPE)) {
            SlabType.TOP -> when (dir) {
                Direction.DOWN -> EMPTY_SHAPE
                Direction.UP -> FULL_SHAPE
                else -> TOP_SHAPE
            }

            SlabType.BOTTOM -> when (dir) {
                Direction.DOWN -> FULL_SHAPE
                Direction.UP -> EMPTY_SHAPE
                else -> BOTTOM_SHAPE
            }

            SlabType.DOUBLE -> FULL_SHAPE
        }

    }

    // region Stair Logic
    private fun stairShape(stair: BlockState, dir: Direction): Pair<FaceShape, Int> {
        val facing = stair.getValue(StairBlock.FACING)
        val receiving = dir.counterClockWise(facing.get2DDataValue())
        return when (stair.getValue(StairBlock.HALF)) {
            Half.TOP -> when (dir) {
                Direction.UP -> FULL_SHAPE
                Direction.DOWN -> when (facing) {
                    Direction.SOUTH -> BOTTOM_SHAPE
                    Direction.WEST -> LEFT_SHAPE
                    Direction.EAST -> RIGHT_SHAPE
                    else -> TOP_SHAPE
                }

                else -> stairsTop(stair, receiving)
            }

            Half.BOTTOM -> when (dir) {
                Direction.DOWN -> FULL_SHAPE
                Direction.UP -> when (facing) {
                    Direction.SOUTH -> BOTTOM_SHAPE
                    Direction.WEST -> LEFT_SHAPE
                    Direction.EAST -> RIGHT_SHAPE
                    else -> TOP_SHAPE
                }

                else -> stairsBottom(stair, receiving)
            }
        }
    }

    fun stairsBottom(stair: BlockState, receiving: Direction): Pair<FaceShape, Int> {
        return when (stair.getValue(StairBlock.SHAPE)) {
            StairsShape.STRAIGHT ->
                when (receiving) {
                    Direction.NORTH -> BOTTOM_SHAPE
                    Direction.EAST -> TR_SHAPE
                    Direction.SOUTH -> FULL_SHAPE
                    Direction.WEST -> TL_SHAPE
                    else -> FULL_SHAPE
                }

            StairsShape.INNER_LEFT -> stairBottomInner(receiving)
            StairsShape.INNER_RIGHT -> stairBottomInner(receiving.counterClockWise)
            StairsShape.OUTER_LEFT -> stairBottomOuter(receiving)
            StairsShape.OUTER_RIGHT -> stairBottomOuter(receiving.counterClockWise)
        }
    }

    fun stairBottomInner(receiving: Direction): Pair<FaceShape, Int> = when (receiving) {
        Direction.NORTH -> TR_SHAPE
        Direction.EAST -> FULL_SHAPE
        Direction.SOUTH -> FULL_SHAPE
        Direction.WEST -> TL_SHAPE
        else -> FULL_SHAPE
    }

    fun stairBottomOuter(receiving: Direction): Pair<FaceShape, Int> = when (receiving) {
        Direction.NORTH -> BOTTOM_SHAPE
        Direction.EAST -> TR_SHAPE
        Direction.SOUTH -> TL_SHAPE
        Direction.WEST -> BOTTOM_SHAPE
        else -> FULL_SHAPE
    }

    fun stairsTop(stair: BlockState, receiving: Direction): Pair<FaceShape, Int> {
        return when (stair.getValue(StairBlock.SHAPE)) {
            StairsShape.STRAIGHT ->
                when (receiving) {
                    Direction.NORTH -> TOP_SHAPE
                    Direction.EAST -> BR_SHAPE
                    Direction.SOUTH -> FULL_SHAPE
                    Direction.WEST -> BL_SHAPE
                    else -> FULL_SHAPE
                }

            StairsShape.INNER_LEFT -> stairTopInner(receiving)
            StairsShape.INNER_RIGHT -> stairTopInner(receiving.counterClockWise)
            StairsShape.OUTER_LEFT -> stairTopOuter(receiving)
            StairsShape.OUTER_RIGHT -> stairTopOuter(receiving.counterClockWise)
        }
    }

    fun stairTopInner(receiving: Direction): Pair<FaceShape, Int> = when (receiving) {
        Direction.NORTH -> BR_SHAPE
        Direction.EAST -> FULL_SHAPE
        Direction.SOUTH -> FULL_SHAPE
        Direction.WEST -> BL_SHAPE
        else -> FULL_SHAPE
    }

    fun stairTopOuter(receiving: Direction): Pair<FaceShape, Int> = when (receiving) {
        Direction.NORTH -> TOP_SHAPE
        Direction.EAST -> BR_SHAPE
        Direction.SOUTH -> BL_SHAPE
        Direction.WEST -> TOP_SHAPE
        else -> FULL_SHAPE
    }
    // endregion

    fun wallCheck(state: BlockState, direction: Direction, neighborState: BlockState): Boolean {
        return direction.axis.isVertical || state.getWallId(direction) <= neighborState.getWallId(direction.opposite)
    }

    fun BlockState.getWallId(direction: Direction): Int = getValue(DIR_TO_WALL[direction.ordinal - 2]).ordinal

    val DIR_TO_WALL = arrayOf(
        WallBlock.NORTH_WALL,
        WallBlock.SOUTH_WALL,
        WallBlock.WEST_WALL,
        WallBlock.EAST_WALL,
    )

}