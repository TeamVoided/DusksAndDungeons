package org.teamvoided.dusks_and_dungeons.entity

import net.minecraft.core.Holder
import net.minecraft.core.particles.ItemParticleOption
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import org.teamvoided.dusks_and_dungeons.data.registry.DnDThrownItemDefinitions
import org.teamvoided.dusks_and_dungeons.init.DnDEntityTypes
import org.teamvoided.dusks_and_dungeons.init.DnDRegistries
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrownItemDefinition
import org.teamvoided.dusks_and_dungeons.util.key
import kotlin.jvm.optionals.getOrNull

class ThrownItemStack : ThrowableItemProjectile {

    constructor(type: EntityType<out ThrownItemStack>, level: Level) : super(type, level)

    constructor(level: Level, owner: LivingEntity) : super(DnDEntityTypes.THROWN_ITEM, owner, level)

    constructor(level: Level, x: Double, y: Double, z: Double) : super(DnDEntityTypes.THROWN_ITEM, x, y, z, level)

    override fun getDefaultItem(): Item = Items.BRICK

    var inGround = false
    var lastState: BlockState? = null

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(THROW_ID, DnDThrownItemDefinitions.EMPTY.location().toString())
    }

    private var throwId: ResourceKey<ThrownItemDefinition>
        get() = DnDRegistries.THROWN_ITEM_DEFINITION.key(ResourceLocation.parse(entityData.get(THROW_ID)))
        set(value) = entityData.set(THROW_ID, value.location().toString())

    private var definitionHolder: Holder<ThrownItemDefinition>? = null

    fun getDefinition(): Holder<ThrownItemDefinition> {
        if (definitionHolder == null || definitionHolder!!.unwrapKey().get() != throwId) {
            definitionHolder = level().holderLookup(DnDRegistries.THROWN_ITEM_DEFINITION)
                .get(throwId).getOrNull() ?: getEmpty(level())
        }
        return definitionHolder!!
    }

    fun setDefinition(holder: Holder<ThrownItemDefinition>) {
        definitionHolder = holder
        throwId = holder.unwrapKey().getOrNull()!!
    }

    val particle: ParticleOptions
        get() {
            return ItemParticleOption(
                ParticleTypes.ITEM,
                if (!item.isEmpty) item else defaultItem.defaultInstance
            )
        }

    override fun tick() {
        val velocity = deltaMovement
        if (xRotO == 0f && yRotO == 0f) {
            val d = velocity.horizontalDistance()
            yRot = (Mth.atan2(velocity.x, velocity.z) * (180f / Math.PI)).toFloat()
            xRot = (Mth.atan2(velocity.y, d) * (180f / Math.PI)).toFloat()
            yRotO = yRot
            xRotO = xRot
        }

        val isNoclip = noPhysics

        val blockPos = blockPosition()
        val state = level().getBlockState(blockPos)

        if (!state.isAir && !isNoclip) {
            val shape = state.getCollisionShape(level(), blockPos)
            if (!shape.isEmpty) {
                val pos = position()
                for (aabb in shape.toAabbs()) {
                    if (aabb.move(blockPos).contains(pos)) {
                        inGround = true
                        break
                    }
                }
            }
        }

        if (inGround && !isNoclip) {
            if (lastState !== state && shouldFall()) {
                startFalling()
            }
        } else {
            super.tick()
        }
    }

    fun shouldFall(): Boolean {
        return inGround && level().noCollision(AABB(position(), position()).inflate(0.06))
    }

    fun startFalling() {
        inGround = false
        deltaMovement = deltaMovement.multiply(
            random.nextDouble() * 0.2,
            random.nextDouble() * 0.2,
            random.nextDouble() * 0.2,
        )
    }

    override fun handleEntityEvent(id: Byte) {
        if (id == BREAK_ID) {
            val options = particle
            repeat(7) {
                level().addParticle(
                    options,
                    x, y, z,
                    (random.nextDouble() * 2) - 1,
                    (random.nextDouble() * 2) - 1,
                    (random.nextDouble() * 2) - 1
                )
            }
        }
    }

    override fun onHitEntity(hit: EntityHitResult) {
        super.onHitEntity(hit)
        val definition = getDefinition().value()
        hit.entity.hurt(damageSources().source(definition.damageType.key(), this, owner), definition.damage)
    }

    override fun onHitBlock(hit: BlockHitResult) {
        super.onHitBlock(hit)
        val pos = hit.blockPos
        val state = level().getBlockState(pos)
        lastState = state

        if (level().isClientSide) {
            return
        }

        val definition = getDefinition().value()
        if (mayBreak(level()) && state.`is`(definition.blockBreakTag)) {
            level().destroyBlock(pos, shouldDropBlocks(owner), owner)
        }
    }

    override fun mayBreak(level: Level): Boolean = level.gameRules.getBoolean(GameRules.RULE_PROJECTILESCANBREAKBLOCKS)

    private fun shouldDropBlocks(owner: Entity?): Boolean {
        return owner !is Player || !owner.isCreative
    }

    override fun onHit(hitResult: HitResult) {
        super.onHit(hitResult)
        if (!level().isClientSide) {
            level().broadcastEntityEvent(this, BREAK_ID)
            deltaMovement = deltaMovement.multiply(
                random.nextFloat() * 0.2,
                random.nextFloat() * 0.2,
                random.nextFloat() * 0.2
            )

            //discard()
        }
    }

    companion object {

        const val BREAK_ID: Byte = 3

        val THROW_ID: EntityDataAccessor<String> =
            SynchedEntityData.defineId(ThrownItemStack::class.java, EntityDataSerializers.STRING)

        fun getEmpty(level: Level): Holder.Reference<ThrownItemDefinition> {
            return level.holderLookup(DnDRegistries.THROWN_ITEM_DEFINITION).getOrThrow(
                DnDThrownItemDefinitions.EMPTY
            )
        }

    }
}