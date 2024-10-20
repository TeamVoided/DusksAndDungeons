package org.teamvoided.dusk_autumn.entity.projectile

import net.minecraft.block.BlockState
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ExplosiveProjectileEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.particle.BlockStateParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.entity.DiceEntity
import org.teamvoided.dusk_autumn.entity.FlyingBlockItemEntity
import org.teamvoided.dusk_autumn.init.DnDEntities
import org.teamvoided.dusk_autumn.init.DnDParticles
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.util.spawnParticles

open class FlyingPumpkinProjectile : PersistentProjectileEntity, FlyingBlockItemEntity {
    constructor(entityType: EntityType<out FlyingPumpkinProjectile>, world: World) : super(entityType, world)
    constructor(owner: PlayerEntity, world: World, stack: ItemStack, weapon: ItemStack?) :
            super(DnDEntities.FLYING_PUMPKIN, owner, world, stack, weapon) {
        state = stack
    }

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)
        if (!world.isClient) {
            particles(world)
            this.discard()
        }
    }

    override fun onRemoved() {
        world.playSound(
            pos.x,
            pos.y,
            pos.z,
            SoundEvents.BLOCK_SLIME_BLOCK_BREAK,
            SoundCategory.NEUTRAL,
            1f,
            random.nextFloat() * 0.3f,
            false
        )
        super.onRemoved()
    }

    fun particles(world: World) {
        if (!world.isClient) {
            val serverWorld = world as ServerWorld
            repeat(90) {
                serverWorld.spawnParticles(
                    BlockStateParticleEffect(ParticleTypes.BLOCK, getState()),
                    pos,
                    Vec3d(
                        (random.nextDouble() * 2.0 - 1.0),
                        (random.nextDouble() * 2.0 - 1.0),
                        (random.nextDouble() * 2.0 - 1.0)
                    ).normalize().multiply(random.nextDouble() * 0.5)
                )
            }
        }
    }

    override fun tryPickup(player: PlayerEntity?): Boolean {
        return false
    }

    override fun getHitSound(): SoundEvent? = null
    override fun getDefaultItemStack(): ItemStack = DnDFloraBlocks.SMALL_CARVED_PUMPKIN.asItem().defaultStack
    override fun getState(): BlockState {
        return if (state.item is BlockItem) {
            (state.item as BlockItem).block.defaultState
        } else DnDFloraBlocks.SMALL_CARVED_PUMPKIN.defaultState
    }

    companion object {
        var state: ItemStack = DnDFloraBlocks.SMALL_CARVED_PUMPKIN.asItem().defaultStack
    }
}
