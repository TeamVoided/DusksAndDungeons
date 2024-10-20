package org.teamvoided.dusk_autumn.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BellBlockEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.EntityDetector
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.block.HauntedGravestoneBlock
import org.teamvoided.dusk_autumn.init.DnDBlockEntities
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class HauntedGravestoneBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(DnDBlockEntities.HAUNTED_GRAVESTONE_BLOCK, pos, state) {
    var players: MutableList<UUID> = mutableListOf()
    var cursedPlayer: PlayerEntity? = null
    var cursePos: Vec3d = pos.ofCenter()
    var curseTime = 0
    var curseType: Int = 0
    var curseVelocity: Vec3d = Vec3d(0.0, 0.0, 0.0)

    override fun onSyncedBlockEvent(type: Int, data: Int): Boolean {
        when (type) {
            0 -> {
                println("the curse deactivated")
                end()
                return true
            }

            1 -> {
                println("the curse activated")
                curseType = 1
                cursedPlayer = world?.getPlayerByUuid(players.random())
                return true
            }

            else -> return super.onSyncedBlockEvent(type, data)
        }
    }

    fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: HauntedGravestoneBlockEntity) {
        if (blockEntity.curseType == 0) {
            if (state.get(HauntedGravestoneBlock.IS_ACTIVE) &&
                (world.time) % 20L != 0L &&
                blockEntity.players.isNotEmpty()
            ) {
                println("the curse activated tick")
                blockEntity.curseType = 1
                blockEntity.cursedPlayer = world.getPlayerByUuid(blockEntity.players.random())
            }
        } else if (blockEntity.curseType == 1) {
            addFollowingParticles(world)
        }
    }

    fun activate(type: Int = 1) {
//        if (this.ringing) {
//            this.ringTicks = 0
//        } else {
//            this.ringing = true
//        }

        this.world?.addSyncedBlockEvent(this.pos, this.cachedState.block, type, 0)
    }

    fun end() {
        println("end")
        cursedPlayer = null
        cursePos = pos.ofCenter()
        curseTime = 0
        curseType = 0
        curseVelocity = Vec3d(0.0, 0.0, 0.0)
    }

    fun addFollowingParticles(world: World) {
        val random = world.random
        if (!world.isClient) {
            if (cursedPlayer == null) {
                this.world?.addSyncedBlockEvent(pos, this.cachedState.block, 0, 0)
                return
            }
            val oldPos: Vec3d = cursePos
            val playerPos: Vec3d = cursedPlayer!!.pos
            val distanceVec = Vec3d(
                playerPos.x - oldPos.x,
                playerPos.y - oldPos.y,
                playerPos.z - oldPos.z
            )
            val distance = distanceVec.length()
            curseVelocity = if (distance < 0.5) {
                curseVelocity.multiply(0.5)
            } else {
                curseVelocity.add(distanceVec.multiply((0.05 / distance)))
            }
            cursePos = cursePos.add(curseVelocity)
            curseTime++
        } else {
            val spinner: Double = curseTime / 10.0
            val xSin = sin(spinner)
            val ySin = sin(spinner / 7)
            val zCos = cos(spinner)
            val velocityX = (random.nextDouble() - random.nextDouble()) + xSin
            val velocityY = (random.nextDouble() - random.nextDouble()) + ySin
            val velocityZ = (random.nextDouble() - random.nextDouble()) + zCos
            world.addParticle(
                ParticleTypes.SOUL,
                cursePos.x,
                cursePos.y,
                cursePos.z,
                velocityX,
                velocityY,
                velocityZ
            )
        }
    }

    companion object {
        fun serverTick(world: World, pos: BlockPos, state: BlockState, blockEntity: HauntedGravestoneBlockEntity) {
            if ((pos.asLong() + world.time) % 20L != 0L) {
                blockEntity.players = EntityDetector.NON_SPECTATING_PLAYERS.detect(
                    world as ServerWorld?,
                    EntityDetector.EntitySelector.WORLD_ENTITY_SELECTOR,
                    pos,
                    9.0,
                    true
                )

                val isActive = state.get(HauntedGravestoneBlock.IS_ACTIVE)
                if ((isActive && blockEntity.players.isEmpty()) || (!isActive && blockEntity.players.isNotEmpty())) {
                    world.setBlockState(pos, state.with(HauntedGravestoneBlock.IS_ACTIVE, !isActive))
                }
            }
            blockEntity.tick(world, pos, state, blockEntity)
        }

//        particles do not place in world
//        fun clientTick(world: World, pos: BlockPos, state: BlockState, blockEntity: HauntedGravestoneBlockEntity) {
//            blockEntity.tick(world, pos, state, blockEntity)
//        }
    }
}