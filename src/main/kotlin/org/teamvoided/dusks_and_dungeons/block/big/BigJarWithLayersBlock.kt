package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.core.BlockPos
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty

class BigJarWithLayersBlock(settings: Properties) : BigJarBlock(settings) {

    init {
        registerDefaultState(stateDefinition.any().setValue(LEVEL_1_6, 1))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        super.createBlockStateDefinition(builder)
        builder.add(LEVEL_1_6)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        val fromItem = 1
        return super.getStateForPlacement(ctx)?.setValue(LEVEL_1_6, fromItem)
    }

    override fun isFull(state: BlockState): Boolean = state.getValue(LEVEL_1_6) == MAX_LEVEL

    override fun getFluidHeight(state: BlockState): Double = (1 + 2 * state.getValue(LEVEL_1_6)) / 16.0

    override fun hasAnalogOutputSignal(state: BlockState): Boolean = true

    override fun getAnalogOutputSignal(state: BlockState, level: Level, blockPos: BlockPos): Int {
        return state.getValue(LEVEL_1_6)
    }

    companion object {
        const val MAX_LEVEL = 6
        val LEVEL_1_6: IntegerProperty = IntegerProperty.create("level", 1, MAX_LEVEL)
    }
}
