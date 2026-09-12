package org.teamvoided.dusks_and_dungeons.util.mixin

import net.minecraft.core.Direction
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.block.GravestoneBlock
import org.teamvoided.dusks_and_dungeons.block.SconceBlock

object FenceGateWallConnection { //future block implementable interface?
    @JvmStatic
    fun wallsAndFencesConnect(state: BlockState, dir: Direction): Boolean {
        return GravestoneBlock.connectsToDirection(state, dir) || SconceBlock.connectsToDirection(state, dir)
    }
}