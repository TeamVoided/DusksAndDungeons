package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block.UPDATE_ALL
import net.minecraft.world.level.block.state.BlockState


fun Level.setBlockAndUpdateFluid(pos: BlockPos, blockState: BlockState, updateFlag: Int = UPDATE_ALL) {
    setBlock(pos, blockState, updateFlag)
    val fluid = getFluidState(pos)?.type
    if (fluid != null) {
        scheduleTick(pos, fluid, fluid.getTickDelay(this))
    }
}