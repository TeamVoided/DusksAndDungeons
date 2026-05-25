package org.teamvoided.dusks_and_dungeons.block.big

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class BigJarWithLayersBlock(settings: Settings) : BigJarBlock(settings) {

    init {
        defaultState = stateManager.defaultState.with(LEVEL_1_6, 1)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(LEVEL_1_6)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fromItem = 1
        return defaultState.with(LEVEL_1_6, fromItem)
    }

    override fun isFull(state: BlockState): Boolean = state.get(LEVEL_1_6) == MAX_LEVEL

    override fun getFluidHeight(state: BlockState): Double = (1 + 2 * state.get(LEVEL_1_6)) / 16.0

    override fun hasComparatorOutput(state: BlockState): Boolean = true

    override fun getComparatorOutput(state: BlockState, world: World, pos: BlockPos): Int = state.get(LEVEL_1_6)

    companion object {
        const val MAX_LEVEL = 6
        val LEVEL_1_6: IntProperty = IntProperty.of("level", 1, MAX_LEVEL)
    }
}
