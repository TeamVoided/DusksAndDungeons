package org.teamvoided.dusks_and_dungeons.block.pumpkin

import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.CarvedPumpkinBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.pattern.BlockPattern

class DnDCarvedPumpkinBlock(properties: Properties) : CarvedPumpkinBlock(properties) {

    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, oldState: BlockState, notify: Boolean) {
        if (!oldState.`is`(state.block)) {
            trySpawnGolem(level, pos)
        }
    }

    override fun canSpawnGolem(level: LevelReader, pos: BlockPos): Boolean {
        return false
        // TODO (1.0) add golems?
//        return GolemPatterns.snowGolemDispenserPattern?.searchAround(world, pos) != null ||
//                GolemPatterns.ironGolemDispenserPattern?.searchAround(world, pos) != null
    }

    fun trySpawnGolem(level: Level, pos: BlockPos) {

    }

    companion object {

        fun spawnGolem(level: Level, result: BlockPattern.BlockPatternMatch, entity: Entity, pos: BlockPos) {
            clearPatternBlocks(level, result)
            entity.moveTo(
                pos.x.toDouble() + 0.5,
                pos.y.toDouble() + 0.05,
                pos.z.toDouble() + 0.5,
                0.0f,
                0.0f
            )
            level.addFreshEntity(entity)
            val playersInArea =
                level.getEntitiesOfClass(ServerPlayer::class.java, entity.boundingBox.inflate(5.0)).iterator()

            for (player in playersInArea) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(player, entity)
            }

            updatePatternBlocks(level, result)
        }
    }

}