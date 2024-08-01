//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package net.minecraft.entity.projectile

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.FlyingItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.registry.tag.BlockTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraft.world.explosion.ExplosionBehavior
import net.minecraft.world.explosion.SimpleExplosionBehavior
import java.util.*
import java.util.function.Function

class ChillCharge : ExplosiveProjectileEntity, FlyingItemEntity {
    constructor(entityType: EntityType<out ChillCharge>, world: World) : super(entityType, world) {
        this.field_51893 = 0.0
    }

    constructor(
        type: EntityType<out ChillCharge>,
        world: World,
        entity: Entity,
        x: Double,
        y: Double,
        z: Double
    ) : super(type, x, y, z, world) {
        this.owner = entity
        this.field_51893 = 0.0
    }

    internal constructor(
        entityType: EntityType<out ChillCharge>,
        d: Double,
        e: Double,
        f: Double,
        vec3d: Vec3d,
        world: World
    ) : super(entityType, d, e, f, vec3d, world) {
        this.field_51893 = 0.0
    }

    override fun calculateBoundingBox(): Box {
        val width = type.dimensions.width() / 2.0f
        val height = type.dimensions.height()
        val heightOffset = 0.15f
        return Box(
            pos.x - width.toDouble(),
            pos.y - heightOffset,
            pos.z - width.toDouble(),
            pos.x + width.toDouble(),
            pos.y - heightOffset + height.toDouble(),
            pos.z + width.toDouble()
        )
    }

    override fun collidesWith(other: Entity): Boolean {
        return if (other is ChillCharge) false else super.collidesWith(other)
    }

    override fun canHit(entity: Entity): Boolean {
        return if (entity is ChillCharge || entity.type === EntityType.END_CRYSTAL) false
        else super.canHit(entity)
    }

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        super.onEntityHit(entityHitResult)
        if (!world.isClient) {
            val owner = this.owner
            val var10000: LivingEntity? = if (owner is LivingEntity) {
                owner
            } else {
                null
            }
            val entity = entityHitResult.entity
            var10000?.onAttacking(entity)
            val damageSource = this.damageSources.windCharge(this, var10000)
            if (entity.damage(damageSource, 1.0f) && entity is LivingEntity) {
                EnchantmentHelper.onEntityDamaged(world as ServerWorld, entity, damageSource)
            }
            this.createExplosion(this.pos)
        }
    }

    override fun addVelocity(deltaX: Double, deltaY: Double, deltaZ: Double) {}

    private fun createExplosion(vec3d: Vec3d) {
        world.createExplosion(
            this,
            null as DamageSource?,
            chillExplosionBehavior,
            vec3d.getX(),
            vec3d.getY(),
            vec3d.getZ(),
            3.0f,
            false,
            World.ExplosionSourceType.TRIGGER,
            ParticleTypes.GUST_EMITTER_SMALL,
            ParticleTypes.GUST_EMITTER_LARGE,
            SoundEvents.ENTITY_BREEZE_WIND_BURST
        )
    }

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)
        if (!world.isClient) {
            val vec3i = blockHitResult.side.vector
            val vec3d = Vec3d.of(vec3i).multiply(explosionOffsetMult, explosionOffsetMult, explosionOffsetMult)
            val vec3d2 = blockHitResult.pos.add(vec3d)
            this.createExplosion(vec3d2)
            this.discard()
        }
    }

    override fun onCollision(hitResult: HitResult) {
        super.onCollision(hitResult)
        if (!world.isClient) {
            this.discard()
        }
    }

    override fun isBurning(): Boolean {
        return false
    }

    override fun getStack(): ItemStack {
        return ItemStack.EMPTY
    }

    override fun getDrag(): Float {
        return 1.0f
    }

    override fun drag(): Float {
        return this.drag
    }

    override fun getParticleType(): ParticleEffect? {
        return null
    }

    override fun tick() {
        if (!world.isClient && this.blockY > world.topY + 30) {
            this.createExplosion(this.pos)
            this.discard()
        } else {
            super.tick()
        }
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        return false
    }

    companion object {
        val chillExplosionBehavior: ExplosionBehavior =
            SimpleExplosionBehavior(
                true,
                false,
                Optional.empty(),
                Registries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
            )
        const val explosionOffsetMult: Double = 0.25
    }
}