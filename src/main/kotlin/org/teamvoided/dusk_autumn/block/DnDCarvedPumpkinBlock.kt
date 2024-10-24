package org.teamvoided.dusk_autumn.block

import net.minecraft.advancement.criterion.Criteria
import net.minecraft.block.BlockState
import net.minecraft.block.CarvedPumpkinBlock
import net.minecraft.block.pattern.BlockPattern
import net.minecraft.entity.Entity
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldView
import org.teamvoided.dusk_autumn.block.not_blocks.GolemPatterns

class DnDCarvedPumpkinBlock(settings: Settings) : CarvedPumpkinBlock(settings) {
    override fun onBlockAdded(state: BlockState, world: World, pos: BlockPos, oldState: BlockState, notify: Boolean) {
        if (!oldState.isOf(state.block)) {
            trySpawnGolem(world, pos)
        }
    }

    override fun canDispense(world: WorldView, pos: BlockPos): Boolean {
        return false
//        return GolemPatterns.snowGolemDispenserPattern?.searchAround(world, pos) != null ||
//                GolemPatterns.ironGolemDispenserPattern?.searchAround(world, pos) != null
    }

    fun trySpawnGolem(world: World, pos: BlockPos) {

    }

    companion object {
        private fun spawnGolem(world: World, result: BlockPattern.Result, entity: Entity, pos: BlockPos) {
            replaceBlocks(world, result)
            entity.refreshPositionAndAngles(
                pos.x.toDouble() + 0.5,
                pos.y.toDouble() + 0.05,
                pos.z.toDouble() + 0.5,
                0.0f,
                0.0f
            )
            world.spawnEntity(entity)
            val playersInArea = world.getNonSpectatingEntities(
                ServerPlayerEntity::class.java, entity.bounds.expand(5.0)
            ).iterator()

            while (playersInArea.hasNext()) {
                val serverPlayerEntity = playersInArea.next() as ServerPlayerEntity
                Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity)
            }

            updateNeighbors(world, result)
        }
    }
}