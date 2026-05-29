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
import net.minecraft.world.level.block.state.properties.WallSide
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.util.counterClockWise

object Meltable {

    // region Melting Logic
    val waterState: BlockState get() = Blocks.WATER.defaultBlockState()

    fun meltAfterBreak(world: Level, pos: BlockPos, stack: ItemStack) {
        if (!EnchantmentHelper.hasTag(stack, EnchantmentTags.PREVENTS_ICE_MELTING)) {
            meltWithCheck(world, pos)
        }
    }

    fun meltFromLight(state: BlockState, world: Level, pos: BlockPos) {
        if (world.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(world, pos)) {
            meltWithAlwaysWater(world, pos)
        }
    }

    @Suppress("DEPRECATION")
    fun meltWithCheck(world: Level, pos: BlockPos) {
        if (world.dimensionType().ultraWarm()) {
            world.removeBlock(pos, false)
            return
        }
        val blockState = world.getBlockState(pos.below())
        if (blockState.blocksMotion() || blockState.liquid()) {
            world.setBlockAndUpdate(pos, waterState)
        }
    }

    fun meltWithAlwaysWater(world: Level, pos: BlockPos) {
        if (world.dimensionType().ultraWarm()) {
            world.removeBlock(pos, false)
        } else {
            world.setBlockAndUpdate(pos, waterState)
            world.neighborChanged(pos, waterState.block, pos)
        }
    }
    // endregion

    @JvmStatic
    fun shouldCullFace(state: BlockState, neighborState: BlockState, direction: Direction): Boolean {
        if (neighborState.`is`(DnDBlockTags.ICE_BLOCK_TRANSLUCENT)) {
            val shape = getShape(state, direction) ?: return false
            val neighborShape = getShape(neighborState, direction.opposite) ?: return false


            if (shape.full() && !neighborShape.full())
                return false



            return neighborShape.full()
                    || (shape.isSlab() && shape.halfMatches(neighborShape))
                    || (shape.isWall() && neighborShape.isWall())
                    || (stairCullingLogic(state, neighborState, direction, shape, neighborShape))
                    || (!shape.isStair() && shape == neighborShape)
        } else {
            return false
        }
    }

    fun stairCullingLogic(
        state: BlockState, neighborState: BlockState, direction: Direction, shape: SideShape, neighborShape: SideShape,
    ): Boolean {
        if (state.block !is StairBlock || neighborState.block !is StairBlock) return false

        val facing = state.getValue(StairBlock.FACING)
        val neighborFacing = neighborState.getValue(StairBlock.FACING)

        if (shape == neighborShape && facing == neighborFacing) {
            return true
        }

        return false
    }


    fun getShape(state: BlockState, dir: Direction): SideShape? {
        return when (state.block) {
            is SlabBlock -> when (state.getValue(SlabBlock.TYPE)) {
                SlabType.TOP -> when (dir) {
                    Direction.UP -> SideShape.FULL
                    Direction.DOWN -> null
                    else -> SideShape.TOP_SLAB
                }

                SlabType.BOTTOM -> when (dir) {
                    Direction.DOWN -> SideShape.FULL
                    Direction.UP -> null
                    else -> SideShape.BOTTOM_SLAB
                }

                SlabType.DOUBLE -> SideShape.FULL
            }

            is StairBlock -> getStairShape(state, dir)
            is WallBlock -> getWallShape(state, dir)
            is HalfTransparentBlock -> SideShape.FULL
            else -> null
        }
    }

    var PROP_DIR_MAP = mapOf(
        Direction.EAST to WallBlock.EAST_WALL,
        Direction.NORTH to WallBlock.NORTH_WALL,
        Direction.SOUTH to WallBlock.SOUTH_WALL,
        Direction.WEST to WallBlock.WEST_WALL,
    )

    fun getWallShape(state: BlockState, dir: Direction): SideShape? {
        val shape = when (PROP_DIR_MAP[dir]?.let(state::getValue)) {
            WallSide.LOW -> SideShape.WALL_SHORT
            WallSide.TALL -> SideShape.WALL_TALL
            else -> null
        }

        if (dir.axis.isVertical) {
            return if (state.getValue(WallBlock.UP)) {
                SideShape.WALL_MIDDLE
            } else {
                for (wall in PROP_DIR_MAP.values) {
                    if (state.getValue(wall) == WallSide.TALL) {
                        return SideShape.WALL_TALL
                    }
                }
                null
            }
        }

        return shape
    }

    private fun getStairShape(state: BlockState, dir: Direction): SideShape {
        val half = state.getValue(StairBlock.HALF)
        when (dir) {
            Direction.DOWN -> if (half == Half.BOTTOM) return SideShape.FULL
            Direction.UP -> if (half == Half.TOP) return SideShape.FULL
            else -> Unit
        }

        val shape = state.getValue(StairBlock.SHAPE)
        val facing = state.getValue(StairBlock.FACING)

        when (shape) {
            StairsShape.STRAIGHT -> return when (dir.counterClockWise(facing.get2DDataValue())) {
                Direction.NORTH, Direction.UP -> if (half == Half.BOTTOM) SideShape.BOTTOM_SLAB else SideShape.TOP_SLAB
                Direction.SOUTH -> SideShape.FULL
                Direction.DOWN -> SideShape.TOP_SLAB
                Direction.WEST, Direction.EAST -> if (half == Half.BOTTOM) SideShape.STAIR_SIDE_BOTTOM else SideShape.STAIR_SIDE_TOP
            }

            StairsShape.INNER_LEFT -> Unit
            StairsShape.INNER_RIGHT -> Unit
            StairsShape.OUTER_LEFT -> Unit
            StairsShape.OUTER_RIGHT -> Unit
        }


        return if (half == Half.TOP) SideShape.TOP_SLAB else SideShape.BOTTOM_SLAB
    }

    enum class SideShape {
        FULL,
        TOP_SLAB, BOTTOM_SLAB,
        STAIR_SIDE_BOTTOM, STAIR_SIDE_TOP, STAIR_TOP,
        WALL_MIDDLE, WALL_SHORT, WALL_TALL;

        fun getHalf() = when (this) {
            STAIR_SIDE_TOP, TOP_SLAB -> Half.TOP
            STAIR_SIDE_BOTTOM, BOTTOM_SLAB -> Half.BOTTOM
            else -> null
        }

        fun halfMatches(shape: SideShape): Boolean {
            return (getHalf() ?: return false) == (shape.getHalf() ?: return false)
        }

        fun full() = this == FULL

        fun isSlab() = this == TOP_SLAB || this == BOTTOM_SLAB

        fun isStair() = when (this) {
            STAIR_SIDE_BOTTOM, STAIR_SIDE_TOP, STAIR_TOP -> true
            else -> false
        }

        fun isWall() = when (this) {
            WALL_MIDDLE, WALL_SHORT, WALL_TALL -> true
            else -> false
        }

    }

}