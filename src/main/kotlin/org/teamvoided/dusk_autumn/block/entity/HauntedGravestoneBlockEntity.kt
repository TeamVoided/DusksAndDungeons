package org.teamvoided.dusk_autumn.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.EntityDetector
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.block.HauntedGravestoneBlock
import org.teamvoided.dusk_autumn.init.DnDBlockEntities
import kotlin.math.cos
import kotlin.math.sin

class HauntedGravestoneBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(DnDBlockEntities.HAUNTED_GRAVESTONE_BLOCK, pos, state) {
    var cursedPlayer: PlayerEntity? = null
    var cursePos: Vec3d = pos.ofCenter()
    var curseTime = 0
    var curseVelocity: Vec3d = Vec3d(0.0, 0.0, 0.0)

    override fun onSyncedBlockEvent(type: Int, data: Int): Boolean {
        when (type) {
            0 -> {
                cursedPlayer = null
                cursePos = Vec3d.ZERO
                curseTime = 0
                curseVelocity = Vec3d(0.0, 0.0, 0.0)
                return true
            }

            1 -> {
                cursedPlayer =
                    world?.getClosestPlayer(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), RANGE, false)
                cursePos = pos.ofCenter()
                return true
            }
        }
        return super.onSyncedBlockEvent(type, data)
    }

    companion object {
        var RANGE = 9.0

        fun serverTick(world: World, pos: BlockPos, state: BlockState, blockEntity: HauntedGravestoneBlockEntity) {
            if (world.isClient) {
                if (blockEntity.cursedPlayer == null) {
                    return
                }

                val random = world.random
                val oldPos: Vec3d = blockEntity.cursePos
                val playerPos: Vec3d = blockEntity.cursedPlayer!!.pos
                val distanceVec = Vec3d(
                    playerPos.x - oldPos.x,
                    playerPos.y - oldPos.y,
                    playerPos.z - oldPos.z
                )
                val distance = distanceVec.length()
                blockEntity.curseVelocity = if (distance < 0.5) {
                    blockEntity.curseVelocity.multiply(0.5)
                } else {
                    blockEntity.curseVelocity.add(distanceVec.multiply((0.05 / distance)))
                }
                blockEntity.cursePos = blockEntity.cursePos.add(blockEntity.curseVelocity)
                blockEntity.curseTime++
                val spinner: Double = blockEntity.curseTime / 10.0
                val xSin = sin(spinner)
                val ySin = sin(spinner / 7)
                val zCos = cos(spinner)
                val velocityX = (random.nextDouble() - random.nextDouble()) + xSin
                val velocityY = (random.nextDouble() - random.nextDouble()) + ySin
                val velocityZ = (random.nextDouble() - random.nextDouble()) + zCos

                world.addParticle(
                    ParticleTypes.SOUL,
                    blockEntity.cursePos.x,
                    blockEntity.cursePos.y,
                    blockEntity.cursePos.z,
                    velocityX,
                    velocityY,
                    velocityZ
                )
                return
            }

            if ((pos.asLong() + world.time) % 20L != 0L) {
                val players = EntityDetector.NON_SPECTATING_PLAYERS.detect(
                    world as ServerWorld?,
                    EntityDetector.EntitySelector.WORLD_ENTITY_SELECTOR,
                    pos,
                    RANGE,
                    true
                )

                val isActive = state.get(HauntedGravestoneBlock.IS_ACTIVE)
                if ((isActive && players.isEmpty()) || (!isActive && players.isNotEmpty())) {
                    world.setBlockState(pos, state.with(HauntedGravestoneBlock.IS_ACTIVE, !isActive))
                    world.addSyncedBlockEvent(pos, state.block, if (isActive) 0 else 1, 0)
                }
            }
        }
    }
}