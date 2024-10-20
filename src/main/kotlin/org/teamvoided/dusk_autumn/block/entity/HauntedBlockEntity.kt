package org.teamvoided.dusk_autumn.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.EntityDetector
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.block.HauntedGravestoneBlock
import org.teamvoided.dusk_autumn.init.DnDBlockEntities

open class HauntedBlockEntity(pos: BlockPos?, state: BlockState?) :
    BlockEntity(DnDBlockEntities.HAUNTED_BLOCK, pos, state) {

    companion object {
        fun serverTick(world: World, pos: BlockPos, state: BlockState, blockEntity: HauntedBlockEntity) {
            if ((pos.asLong() + world.time) % 20L != 0L) {
                val players = EntityDetector.NON_SPECTATING_PLAYERS.detect(
                    world as ServerWorld?,
                    EntityDetector.EntitySelector.WORLD_ENTITY_SELECTOR,
                    pos,
                    9.0,
                    true
                )

                val isActive = state.get(HauntedGravestoneBlock.IS_ACTIVE)
                if ((isActive && players.isEmpty()) || (!isActive && players.isNotEmpty())) {
                    world.setBlockState(pos, state.with(HauntedGravestoneBlock.IS_ACTIVE, !isActive))
                }
            }
        }
    }
}