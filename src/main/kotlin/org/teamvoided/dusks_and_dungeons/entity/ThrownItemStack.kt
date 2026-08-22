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
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import org.teamvoided.dusks_and_dungeons.data.registry.DnDThrownItemDefinitions
import org.teamvoided.dusks_and_dungeons.init.DnDEntities
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrownItemDefinition
import org.teamvoided.dusks_and_dungeons.util.key
import kotlin.jvm.optionals.getOrNull

class ThrownItemStack : ThrowableItemProjectile {

    constructor(type: EntityType<out ThrownItemStack>, level: Level) : super(type, level)

    constructor(level: Level, owner: LivingEntity) : super(DnDEntities.THROWN_ITEM, owner, level)

    constructor(level: Level, x: Double, y: Double, z: Double) : super(DnDEntities.THROWN_ITEM, x, y, z, level)

    override fun getDefaultItem(): Item = Items.BRICK

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(THROW_ID, DnDThrownItemDefinitions.EMPTY.location().toString())
    }

    private var throwId: ResourceKey<ThrownItemDefinition>
        get() = DnDRegistryKeys.THROWN_ITEM_DEFINITION.key(ResourceLocation.parse(entityData.get(THROW_ID)))
        set(value) = entityData.set(THROW_ID, value.location().toString())

    private var definitionHolder: Holder<ThrownItemDefinition>? = null

    fun getDefinition(): Holder<ThrownItemDefinition> {
        if (definitionHolder == null || definitionHolder!!.unwrapKey().get() != throwId) {
            definitionHolder = level().holderLookup(DnDRegistryKeys.THROWN_ITEM_DEFINITION)
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

    override fun handleEntityEvent(id: Byte) {
        if (id == BREAK_ID) {
            val options = particle
            for (i in 0..7) {
                level().addParticle(options, x, y, z, 0.0, 0.0, 0.0)
            }
        }
    }

    override fun onHitEntity(hit: EntityHitResult) {
        super.onHitEntity(hit)
        val definition = getDefinition().value()
        hit.entity.hurt(
            damageSources().source(definition.damageType.key(), this, owner), definition.damage
        )
    }

    override fun onHitBlock(hit: BlockHitResult) {
        super.onHitBlock(hit)
        if (level().isClientSide) {
            return
        }

        val definition = getDefinition().value()

        val pos = hit.blockPos
        val state = level().getBlockState(pos)

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
            discard()
        }
    }

    companion object {

        const val BREAK_ID: Byte = 3

        val THROW_ID: EntityDataAccessor<String> =
            SynchedEntityData.defineId(ThrownItemStack::class.java, EntityDataSerializers.STRING)

        fun getEmpty(level: Level): Holder.Reference<ThrownItemDefinition> {
            return level.holderLookup(DnDRegistryKeys.THROWN_ITEM_DEFINITION).getOrThrow(
                DnDThrownItemDefinitions.EMPTY
            )
        }

    }
}