package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.level.block.HalfTransparentBlock
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.PrimedTnt
import net.minecraft.world.entity.vehicle.AbstractMinecart
import net.minecraft.world.entity.vehicle.Boat
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import kotlin.math.abs

class CornSyrupBlock(settings: Properties) : HalfTransparentBlock(settings) {
    public override fun codec(): MapCodec<CornSyrupBlock> = CODEC

    override fun getCollisionShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext)
            : VoxelShape = SHAPE

    override fun fallOn(world: Level, state: BlockState, pos: BlockPos, entity: Entity, fallDistance: Float) {
        entity.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 1.0f, 1.0f)
        if (!world.isClientSide) addRichParticles(entity)

        if (entity.causeFallDamage(fallDistance, 0.2f, world.damageSources().fall())) {
            entity.playSound(
                soundType.fallSound,
                soundType.getVolume() * 0.5f, soundType.getPitch() * 0.75f
            )
        }
    }

    override fun entityInside(state: BlockState, world: Level, pos: BlockPos, entity: Entity) {
        if (this.isSliding(pos, entity)) {
            this.triggerAdvancement(entity, pos)
            this.updateSlidingVelocity(entity)
            this.addCollisionEffects(world, entity)
        }

        super.entityInside(state, world, pos, entity)
    }

    private fun isSliding(pos: BlockPos, entity: Entity): Boolean {
        return if (entity.onGround()) false
        else if (entity.y > pos.y + 0.9375 - 1.0E-7) false
        else if (entity.deltaMovement.y >= -MIN_FALL_SPEED_TO_SLIDE) false
        else {
            val x = abs(pos.x + 0.5 - entity.x)
            val z = abs(pos.z + 0.5 - entity.z)
            val f = 0.4375 + (entity.bbWidth / 2.0f)
            x + 1.0E-7 > f || z + 1.0E-7 > f
        }
    }

    private fun triggerAdvancement(entity: Entity, pos: BlockPos) {
        if (entity is ServerPlayer && entity.level().gameTime % TICKS_PER_SECOND.toLong() == 0L) {
            CriteriaTriggers.HONEY_BLOCK_SLIDE.trigger(entity, entity.level().getBlockState(pos))
        }
    }

    private fun updateSlidingVelocity(entity: Entity) {
        val vec3d = entity.deltaMovement
        entity.setDeltaMovement(
            if (vec3d.y < -START_SLIDING_SPEED) {
                val d = -THROTTLE_SLIDE_SPEED / vec3d.y
                Vec3(vec3d.x * d, -THROTTLE_SLIDE_SPEED, vec3d.z * d)
            } else Vec3(vec3d.x, -THROTTLE_SLIDE_SPEED, vec3d.z)
        )

        entity.resetFallDistance()
    }

    private fun addCollisionEffects(world: Level, entity: Entity) {
        if (hasHoneyBlockEffects(entity)) {
            if (world.random.nextInt(5) == 0) entity.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 1.0f, 1.0f)
            if (!world.isClientSide && world.random.nextInt(5) == 0) addRegularParticles(entity)
        }
    }

    companion object {
        val CODEC: MapCodec<CornSyrupBlock> = simpleCodec(::CornSyrupBlock)

        private const val START_SLIDING_SPEED = 0.13
        private const val MIN_FALL_SPEED_TO_SLIDE = 0.08
        private const val THROTTLE_SLIDE_SPEED = 0.05
        private const val TICKS_PER_SECOND = 20
        val SHAPE: VoxelShape = box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0)

        private fun hasHoneyBlockEffects(entity: Entity): Boolean =
            entity is LivingEntity || entity is AbstractMinecart || entity is PrimedTnt || entity is Boat

        fun addRegularParticles(entity: Entity) = addParticles(entity, 5)
        fun addRichParticles(entity: Entity) = addParticles(entity, 10)
        private fun addParticles(entity: Entity, count: Int) {
            if (entity.level() is ServerLevel) {
                val blockState = DnDBlocks.CORN_SYRUP_BLOCK.defaultBlockState()
                (entity.level() as ServerLevel).sendParticles(
                    BlockParticleOption(ParticleTypes.BLOCK, blockState),
                    entity.x, entity.y, entity.z, count, 0.0, 0.0, 0.0, 0.0
                )
            }
        }
    }
}