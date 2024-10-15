package org.teamvoided.dusk_autumn.entity.projectile

import net.minecraft.block.BlockState
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ExplosiveProjectileEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.particle.BlockStateParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
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
            super(
                DnDEntities.FLYING_PUMPKIN,
                owner,
                world,
                DnDFloraBlocks.SMALL_CARVED_PUMPKIN.asItem().defaultStack,
                weapon
            )

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)
        if (!world.isClient) {
            particles(world)
            this.discard()
        }
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

    override fun getHitSound(): SoundEvent = SoundEvents.BLOCK_WOOD_BREAK
    override fun getDefaultItemStack(): ItemStack = DnDFloraBlocks.SMALL_CARVED_PUMPKIN.asItem().defaultStack
    override fun getState(): BlockState = DnDFloraBlocks.SMALL_CARVED_PUMPKIN.defaultState
}
