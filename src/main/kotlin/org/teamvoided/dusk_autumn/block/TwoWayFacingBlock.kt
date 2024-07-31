package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.Direction

open class TwoWayFacingBlock(settings: Settings) : Block(settings) {
    public override fun getCodec(): MapCodec<out TwoWayFacingBlock> {
        return CODEC
    }

    init {
        this.defaultState = defaultState.with(AXIS, Direction.Axis.X)
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return changeRotation(state, rotation)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(AXIS)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
//        alternative that does the same as the column block
//        val placementState = if (ctx.side.axis == Direction.Axis.Y) {
//            defaultState.with(AXIS, getPlayerHorizontalFacingAxis(ctx))
//        } else defaultState.with(AXIS, ctx.side.axis)
        return defaultState.with(AXIS, getPlayerHorizontalFacingAxis(ctx))
    }

    //there's probably already a function that does the exact same thing, but I couldn't find it
    fun getPlayerHorizontalFacingAxis(ctx: ItemPlacementContext): Direction.Axis {
        return if (ctx.player != null &&
            (ctx.player!!.horizontalFacing == Direction.EAST || ctx.player!!.horizontalFacing == Direction.WEST)
        ) Direction.Axis.Z
        else Direction.Axis.X
    }

    companion object {
        val CODEC: MapCodec<TwoWayFacingBlock> = createCodec { settings: Settings ->
            TwoWayFacingBlock(
                settings
            )
        }
        val AXIS: EnumProperty<Direction.Axis> = Properties.HORIZONTAL_AXIS

        fun changeRotation(state: BlockState, rotation: BlockRotation?): BlockState {
            return when (rotation) {
                BlockRotation.COUNTERCLOCKWISE_90, BlockRotation.CLOCKWISE_90 -> when (state.get(AXIS)) {
                    Direction.Axis.Z -> state.with(AXIS, Direction.Axis.X)
                    Direction.Axis.X -> state.with(AXIS, Direction.Axis.Z)
                    else -> state
                }

                else -> state
            }
        }
        fun InvertAxis(state: BlockState, axis: Direction.Axis?): BlockState {
            return when (axis) {
                Direction.Axis.Z -> state.with(AXIS, Direction.Axis.X)
                Direction.Axis.X -> state.with(AXIS, Direction.Axis.Z)
                else -> state
            }
        }
    }
}