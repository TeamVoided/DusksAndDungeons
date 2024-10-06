package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.CarvedPumpkinBlock
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CarvedPumpkin2Block(settings: Settings) : CarvedPumpkinBlock(settings) {
    override fun onBlockAdded(
        state: BlockState,
        world: World,
        pos: BlockPos,
        oldState: BlockState,
        notify: Boolean
    ) {
        if (!oldState.isOf(state.block)) {
            trySpawnEntity2(world, pos)
        }
    }

    fun trySpawnEntity2(world: World, pos: BlockPos) {

    }

    companion object {

        val IS_GOLEM_HEAD_PREDICATE = java.util.function.Predicate<BlockState> { state: BlockState ->
            state.isIn(BlockTags.SAND)
        }
    }
}