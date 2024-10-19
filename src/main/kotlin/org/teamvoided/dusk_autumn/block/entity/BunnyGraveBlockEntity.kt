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
    var dustBunnies: List<LivingEntity>? = null
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
            val entity: DustBunnyEntity = DustBunnyEntity(DnDEntities.DUST_BUNNY, world!!)
            entity.refreshPositionAndAngles(blockPos, 0f, 0f)
            entity.summonedPos = blockPos
            entity.initialize(
                world as ServerWorld,
                this.world?.getLocalDifficulty(blockPos),
                SpawnReason.MOB_SUMMONED,
                null
            )
            world!!.spawnEntity(entity)
            this.timeSinceLastBunny = 0
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
            val dustState = state.get(BunnyGraveBlock.DUST)
            val dustBunniesAmount = blockEntity.dustBunnies?.size ?: 0
            if (dustBunniesAmount < bunniesAmount(dustState)) {
                if (world.random.nextFloat() < dustState * 0.7) {
                    blockEntity.summonBunny()
                }
            } else if (dustBunniesAmount > bunniesAmount(dustState) && world.time % 20 == 0L) {
                val entity = blockEntity.dustBunnies?.first()
                entity?.damage(entity.damageSources.starve(), 1.0f)
            }
        }

        fun serverTick(world: World, pos: BlockPos, state: BlockState, blockEntity: BunnyGraveBlockEntity) {
            tick(world, pos, state, blockEntity)
        }
    }
}