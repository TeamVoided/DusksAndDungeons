package org.teamvoided.dusks_and_dungeons.block

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
    init {
        this.defaultState = stateManager.defaultState.with(AXIS, Direction.Axis.X)
    }

    public override fun getCodec() = CODEC
    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState = changeRotation(state, rotation)

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
    fun getPlayerHorizontalFacingAxis(ctx: ItemPlacementContext): Direction.Axis =
        if (ctx.player != null && ctx.player!!.horizontalFacing.axis == Direction.Axis.X)
            Direction.Axis.Z
        else
            Direction.Axis.X

    companion object {
        val CODEC: MapCodec<TwoWayFacingBlock> = createCodec(::TwoWayFacingBlock)
        val AXIS: EnumProperty<Direction.Axis> = Properties.HORIZONTAL_AXIS

        fun changeRotation(state: BlockState, rotation: BlockRotation?): BlockState = when (rotation) {
            BlockRotation.COUNTERCLOCKWISE_90, BlockRotation.CLOCKWISE_90 -> rotate(state)
            else -> state
        }


        fun rotate(state: BlockState): BlockState = when (state.get(AXIS)) {
            Direction.Axis.Z -> state.with(AXIS, Direction.Axis.X)
            Direction.Axis.X -> state.with(AXIS, Direction.Axis.Z)
            else -> state
        }
    }
}