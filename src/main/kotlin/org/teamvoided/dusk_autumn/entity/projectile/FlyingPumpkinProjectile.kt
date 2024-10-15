package org.teamvoided.dusk_autumn.entity.projectile

import net.minecraft.block.BlockState
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.projectile.ExplosiveProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.entity.FlyingBlockItemEntity
import org.teamvoided.dusk_autumn.init.DnDEntities
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks

open class FlyingPumpkinProjectile : ExplosiveProjectileEntity, FlyingBlockItemEntity {
    init {
        this.field_51893 = 0.5
    }

    constructor(entityType: EntityType<out ExplosiveProjectileEntity>, world: World) : super(entityType, world)
    constructor(world: World) : this(DnDEntities.FLYING_PUMPKIN, world)
    constructor(pos: Vec3d, world: World) : super(DnDEntities.FLYING_PUMPKIN, pos.x, pos.y, pos.z, world)
    constructor(owner: LivingEntity, pos: Vec3d, world: World) : this(pos, world) {
        this.owner = owner
    }

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)
        if (blockHitResult.type != HitResult.Type.MISS) {
            world.addBlockBreakParticles(blockPos, getState())
            world.addBlockBreakParticles(blockPos, getState())
            world.addBlockBreakParticles(blockPos, getState())
            world.addBlockBreakParticles(blockPos, getState())
            world.addBlockBreakParticles(blockPos, getState())
            this.kill()
        }
    }

    override fun initDataTracker(builder: DataTracker.Builder) = Unit
    override fun isBurning(): Boolean = false
    override fun getStack(): ItemStack = DnDFloraBlocks.SMALL_CARVED_PUMPKIN.asItem().defaultStack
    override fun getState(): BlockState = DnDFloraBlocks.SMALL_CARVED_PUMPKIN.defaultState
}
