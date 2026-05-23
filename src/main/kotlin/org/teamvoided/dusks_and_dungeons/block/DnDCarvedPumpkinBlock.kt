package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.CarvedPumpkinBlock
import net.minecraft.world.level.block.state.pattern.BlockPattern
import net.minecraft.world.entity.Entity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader

class DnDCarvedPumpkinBlock(settings: Properties) : CarvedPumpkinBlock(settings) {
    override fun onPlace(state: BlockState, world: Level, pos: BlockPos, oldState: BlockState, notify: Boolean) {
        if (!oldState.`is`(state.block)) trySpawnGolem(world, pos)
    }

    override fun canSpawnGolem(world: LevelReader, pos: BlockPos): Boolean {
        return false
//        return GolemPatterns.snowGolemDispenserPattern?.searchAround(world, pos) != null ||
//                GolemPatterns.ironGolemDispenserPattern?.searchAround(world, pos) != null
    }

    fun trySpawnGolem(world: Level, pos: BlockPos) {

    }

    companion object {
        private fun spawnGolem(world: Level, result: BlockPattern.BlockPatternMatch, entity: Entity, pos: BlockPos) {
            clearPatternBlocks(world, result)
            entity.moveTo(
                pos.x.toDouble() + 0.5,
                pos.y.toDouble() + 0.05,
                pos.z.toDouble() + 0.5,
                0.0f,
                0.0f
            )
            world.addFreshEntity(entity)
            val playersInArea = world.getEntitiesOfClass(
                ServerPlayer::class.java, entity.boundingBox.inflate(5.0)
            ).iterator()

            while (playersInArea.hasNext()) {
                val serverPlayerEntity = playersInArea.next() as ServerPlayer
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity)
            }

            updatePatternBlocks(world, result)
        }
    }
}