package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.Rotation
import net.minecraft.core.Direction

open class TwoWayFacingBlock(settings: Properties) : Block(settings) {
    init {
        this.registerDefaultState(stateDefinition.any().setValue(AXIS, Direction.Axis.X))
    }

    public override fun codec() = CODEC
    override fun rotate(state: BlockState, rotation: Rotation): BlockState = changeRotation(state, rotation)

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(AXIS)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState {
//        alternative that does the same as the column block
//        val placementState = if (ctx.side.axis == Direction.Axis.Y) {
//            defaultState.with(AXIS, getPlayerHorizontalFacingAxis(ctx))
//        } else defaultState.with(AXIS, ctx.side.axis)
        return defaultBlockState().setValue(AXIS, getPlayerHorizontalFacingAxis(ctx))
    }

    //there's probably already a function that does the exact same thing, but I couldn't find it
    fun getPlayerHorizontalFacingAxis(ctx: BlockPlaceContext): Direction.Axis =
        if (ctx.player != null && ctx.player!!.direction.axis == Direction.Axis.X)
            Direction.Axis.Z
        else
            Direction.Axis.X

    companion object {
        val CODEC: MapCodec<TwoWayFacingBlock> = simpleCodec(::TwoWayFacingBlock)
        val AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.HORIZONTAL_AXIS

        fun changeRotation(state: BlockState, rotation: Rotation?): BlockState = when (rotation) {
            Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 -> rotate(state)
            else -> state
        }


        fun rotate(state: BlockState): BlockState = when (state.getValue(AXIS)) {
            Direction.Axis.Z -> state.setValue(AXIS, Direction.Axis.X)
            Direction.Axis.X -> state.setValue(AXIS, Direction.Axis.Z)
            else -> state
        }
    }
}