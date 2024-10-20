package org.teamvoided.dusk_autumn.block.entity

import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import java.util.UUID
import kotlin.math.cos
import kotlin.math.sin

class HauntedGravestoneBlockEntity(pos: BlockPos, state: BlockState) : HauntedBlockEntity(pos, state) {
    var players: MutableList<UUID> = mutableListOf()
    var cursedPlayer: PlayerEntity? = null
    var cursePos: Vec3d? = null
    var curseTime = 0
    var curseType: Int = 0
    var curseVelocity: Vec3d = Vec3d(0.0, 0.0, 0.0)

    override fun onSyncedBlockEvent(type: Int, data: Int): Boolean {
        when (type) {
            0 -> {
                end()
                return true
            }

            1 -> {
                curseType = 1
                cursedPlayer = world?.getPlayerByUuid(players.random())
                return true
            }

            else -> return super.onSyncedBlockEvent(type, data)
        }
    }

    fun activate() {
        val blockPos: BlockPos = this.pos
//        if (this.ringing) {
//            this.ringTicks = 0
//        } else {
//            this.ringing = true
//        }

        this.world?.addSyncedBlockEvent(blockPos, this.cachedState.block, 1, 0)
    }

    fun end() {
        cursedPlayer = null
        cursePos = null
        curseTime = 0
        curseType = 0
        curseVelocity = Vec3d(0.0, 0.0, 0.0)
    }

    fun addFollowingParticles() {
        if (cursedPlayer != null) {
            if (cursePos == null) {
                cursePos = pos.ofCenter()
            }
            val oldPos: Vec3d = cursePos ?: pos.ofCenter()
            val playerPos: Vec3d = cursedPlayer?.pos ?: oldPos

            val distanceVec = Vec3d(
                playerPos.x - oldPos.x,
                playerPos.y - oldPos.y,
                playerPos.z - oldPos.z
            )
            val distance = distanceVec.length()
            if (distance < 0.5) {
                curseVelocity = curseVelocity.multiply(0.5)
            } else {
                curseVelocity = curseVelocity.add(distanceVec.multiply((0.05 / distance)))
            }
            oldPos.add(curseVelocity)
            val random = world?.random
            if (world != null && random != null && cursePos != null) {
                val spinner: Double = curseTime / 10.0
                val xSin = sin(spinner)
                val ySin = sin(spinner / 7)
                val zCos = cos(spinner)
                val velocityX = (random.nextDouble() - random.nextDouble()) + xSin
                val velocityy = (random.nextDouble() - random.nextDouble()) + ySin
                val velocityZ = (random.nextDouble() - random.nextDouble()) + zCos
                world?.addParticle(
                    ParticleTypes.SOUL,
                    cursePos!!.x + velocityX,
                    cursePos!!.y + velocityy,
                    cursePos!!.z + velocityZ,
                    velocityX,
                    velocityy,
                    velocityZ
                )
            }
        }
        curseTime++
    }
}