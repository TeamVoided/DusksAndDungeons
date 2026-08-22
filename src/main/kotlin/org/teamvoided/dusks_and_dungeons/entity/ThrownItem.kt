package org.teamvoided.dusks_and_dungeons.entity

import net.minecraft.core.particles.ItemParticleOption
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.ThrowableItemProjectile
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDEntities

class ThrownItem : ThrowableItemProjectile {

    constructor(type: EntityType<out ThrownItem>, level: Level) : super(type, level)

    constructor(level: Level, owner: LivingEntity) : super(DnDEntities.THROWN_ITEM, owner, level)

    constructor(level: Level, x: Double, y: Double, z: Double) : super(DnDEntities.THROWN_ITEM, x, y, z, level)

    override fun getDefaultItem(): Item = Items.BRICK

    private val particle: ParticleOptions
        get() {
            return ItemParticleOption(
                ParticleTypes.ITEM,
                if (!item.isEmpty) item else defaultItem.defaultInstance
            )
        }

    override fun handleEntityEvent(b: Byte) {
        if (b == 3.toByte()) {
            val options = particle

            for (i in 0..7) {
                level().addParticle(options, x, y, z, 0.0, 0.0, 0.0)
            }
        }
    }

    override fun onHitEntity(hit: EntityHitResult) {
        super.onHitEntity(hit)
        val hitEntity = hit.entity
        val damage = 2 // ha ha blaze damage check
        hitEntity.hurt(damageSources().thrown(this, owner), damage.toFloat())
    }

    override fun onHitBlock(hit: BlockHitResult) {
        super.onHitBlock(hit)
        if (level().isClientSide) {
            return
        }

        val pos = hit.blockPos
        val state = level().getBlockState(pos)

        if (!level().gameRules.getBoolean(GameRules.RULE_PROJECTILESCANBREAKBLOCKS)) {
            return
        }

        if (state.`is`(DnDBlockTags.THROWN_BRICK_BREAK)) {
            level().removeBlock(pos, false)
        }
    }

    override fun onHit(hitResult: HitResult) {
        super.onHit(hitResult)
        if (!level().isClientSide) {
            level().broadcastEntityEvent(this, 3.toByte())
            discard()
        }
    }

}