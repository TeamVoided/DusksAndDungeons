package org.teamvoided.dusk_autumn.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.block.BunnyGraveBlock
import org.teamvoided.dusk_autumn.entity.DustBunnyEntity
import org.teamvoided.dusk_autumn.init.DnDBlockEntities
import org.teamvoided.dusk_autumn.init.DnDEntities

open class BunnyGraveBlockEntity(pos: BlockPos?, state: BlockState?) :
    BlockEntity(DnDBlockEntities.BUNNY_GRAVE, pos, state) {
    var dustBunnies: MutableList<LivingEntity> = mutableListOf()
    private var timeSinceLastBunny = 0

    override fun onSyncedBlockEvent(type: Int, data: Int): Boolean {
        if (type == 1) {
            summonBunny()
            return true
        } else {
            return super.onSyncedBlockEvent(type, data)
        }
    }

    fun summonBunny() {
        val blockPos = this.getPos()
        if (world != null) {
            val entity = DustBunnyEntity(DnDEntities.DUST_BUNNY, world!!)
            entity.refreshPositionAndAngles(blockPos.ofCenter(), 0f, 0f)
            entity.summonedPos = blockPos
            entity.initialize(
                world as ServerWorld,
                this.world?.getLocalDifficulty(blockPos),
                SpawnReason.MOB_SUMMONED,
                null
            )
            world!!.spawnEntity(entity)
            dustBunnies.addLast(entity)
            this.timeSinceLastBunny = 0
            println(dustBunnies)
        }
    }

    fun getDustBunniesFromBlock(): MutableList<LivingEntity> {
        return dustBunnies
    }

    fun removeDustBunny(index: Int) {
        dustBunnies.removeAt(index)
    }

    fun removeDustBunny(entity: LivingEntity) {
        dustBunnies.forEachIndexed { idx, it ->
            println(it)
            if (it == entity)
                dustBunnies.removeAt(idx)
        }
    }

    companion object {
        fun bunniesAmount(dustState: Int): Int = dustState * 3
        fun tick(
            world: World,
            pos: BlockPos,
            state: BlockState,
            blockEntity: BunnyGraveBlockEntity
        ) {
            if (blockEntity.timeSinceLastBunny < 1200) {
                ++blockEntity.timeSinceLastBunny
            }
            if (blockEntity.timeSinceLastBunny % 20 == 0) {
                val dustState = state.get(BunnyGraveBlock.DUST)
                val dustBunniesAmount = blockEntity.dustBunnies.size
                if (dustBunniesAmount < bunniesAmount(dustState)) {
                    if (world.random.nextFloat() < dustState * 0.7) {
                        blockEntity.onSyncedBlockEvent(1, 1)
                    }
                } else if (dustBunniesAmount > bunniesAmount(dustState)) {
                    val entity = blockEntity.dustBunnies.first()
                    entity.damage(entity.damageSources.starve(), 1.0f)
                }
            }
        }

        fun serverTick(world: World, pos: BlockPos, state: BlockState, blockEntity: BunnyGraveBlockEntity) {
            tick(world, pos, state, blockEntity)
        }
    }
}