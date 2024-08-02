//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package net.minecraft.entity.projectile

import com.google.common.collect.Lists
import net.minecraft.block.Blocks
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.FlyingItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.EntityTypeTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.property.Properties
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraft.world.explosion.ExplosionBehavior
import net.minecraft.world.explosion.SimpleExplosionBehavior
import org.teamvoided.dusk_autumn.data.tags.DnDEntityTypeTags
import java.util.*
import java.util.function.Function
import kotlin.math.max

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
        return if (entity.type.isIn(DnDEntityTypeTags.CHILL_CHARGE_GOES_THROUGH)) false
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
            this.freeze(world, 5)
        }
    }

    private fun freeze(world: World, radius: Int) {
        var posListLight: MutableList<BlockPos> = Lists.newArrayList<BlockPos>()
        var posListIce: MutableList<BlockPos> = Lists.newArrayList<BlockPos>()
        for (x in -radius..radius) {
            for (y in -radius..radius) {
                for (z in -radius..radius) {
                    val block = blockPos
                        .offset(Direction.Axis.X, x)
                        .offset(Direction.Axis.Y, y)
                        .offset(Direction.Axis.Z, z)
                    val blockstate = world.getBlockState(block)
                    if ((blockstate.isOf(Blocks.WATER) && blockstate.get(Properties.LEVEL_15) == 0) &&
                        (world.height > block.y + 1 || world.getBlockState(block.up()).isIn(BlockTags.AIR))
                    ) posListIce.add(block)
                    else if (world.getBlockState(block).contains(Properties.LIT))
                        posListLight.add(block)
                }
            }
        }
        posListIce.forEach {
            world.setBlockState(it, Blocks.FROSTED_ICE.defaultState)
        }
        posListLight.forEach {
            world.setBlockState(it, world.getBlockState(it).with(Properties.LIT, false))
        }
        freezeEntities(world, radius)
    }
    fun freezeEntities(world: World, radius: Int) {
        val entitiesNearby = world.getOtherEntities(
            this, Box(
                this.x - radius,
                this.y - radius,
                this.z - radius,
                this.x + radius,
                this.y + radius,
                this.z + radius
            )
        ) { obj: Entity -> obj.isAlive && !obj.type.isIn(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES) }

        return entitiesNearby.forEach {
            it.damage(this.damageSources.freeze(), 4f)
            it.frozenTicks = max(it.frozenTicks, random.rangeInclusive(350, 500))
        }
    }

    override fun addVelocity(deltaX: Double, deltaY: Double, deltaZ: Double) {}

    override fun onBlockHit(blockHitResult: BlockHitResult) {
        super.onBlockHit(blockHitResult)
        if (!world.isClient) {
            this.freeze(world, 5)
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
            this.freeze(world, 5)
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