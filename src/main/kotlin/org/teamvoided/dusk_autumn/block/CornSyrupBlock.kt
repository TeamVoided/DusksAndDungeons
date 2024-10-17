package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.advancement.criterion.Criteria
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.ShapeContext
import net.minecraft.block.TransparentBlock
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.TntEntity
import net.minecraft.entity.vehicle.AbstractMinecartEntity
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.particle.BlockStateParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import kotlin.math.abs

class CornSyrupBlock(settings: Settings) : TransparentBlock(settings) {
    public override fun getCodec(): MapCodec<CornSyrupBlock> {
        return CODEC
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape = SHAPE

    override fun onLandedUpon(world: World, state: BlockState, pos: BlockPos, entity: Entity, fallDistance: Float) {
        entity.playSound(SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, 1.0f, 1.0f)
        if (!world.isClient) {
            addRichParticles(entity)
        }

        if (entity.handleFallDamage(fallDistance, 0.2f, world.damageSources.fall())) {
            entity.playSound(
                soundGroup.fallSound,
                soundGroup.getVolume() * 0.5f, soundGroup.getPitch() * 0.75f
            )
        }
    }

    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (this.isSliding(pos, entity)) {
            this.triggerAdvancement(entity, pos)
            this.updateSlidingVelocity(entity)
            this.addCollisionEffects(world, entity)
        }

        super.onEntityCollision(state, world, pos, entity)
    }

    private fun isSliding(pos: BlockPos, entity: Entity): Boolean {
        if (entity.isOnGround) {
            return false
        } else if (entity.y > pos.y + 0.9375 - 1.0E-7) {
            return false
        } else if (entity.velocity.y >= -MIN_FALL_SPEED_TO_SLIDE) {
            return false
        } else {
            val x = abs(pos.x + 0.5 - entity.x)
            val z = abs(pos.z + 0.5 - entity.z)
            val f = 0.4375 + (entity.width / 2.0f)
            return x + 1.0E-7 > f || z + 1.0E-7 > f
        }
    }

    private fun triggerAdvancement(entity: Entity, pos: BlockPos) {
        if (entity is ServerPlayerEntity && entity.getWorld().time % TICKS_PER_SECOND.toLong() == 0L) {
            Criteria.SLIDE_DOWN_BLOCK.trigger(entity, entity.getWorld().getBlockState(pos))
        }
    }

    private fun updateSlidingVelocity(entity: Entity) {
        val vec3d = entity.velocity
        if (vec3d.y < -START_SLIDING_SPEED) {
            val d = -THROTTLE_SLIDE_SPEED / vec3d.y
            entity.velocity = Vec3d(vec3d.x * d, -THROTTLE_SLIDE_SPEED, vec3d.z * d)
        } else {
            entity.velocity = Vec3d(vec3d.x, -THROTTLE_SLIDE_SPEED, vec3d.z)
        }

        entity.resetFallDistance()
    }

    private fun addCollisionEffects(world: World, entity: Entity) {
        if (hasHoneyBlockEffects(entity)) {
            if (world.random.nextInt(5) == 0) {
                entity.playSound(SoundEvents.BLOCK_HONEY_BLOCK_SLIDE, 1.0f, 1.0f)
            }

            if (!world.isClient && world.random.nextInt(5) == 0) {
                addRegularParticles(entity)
            }
        }
    }

    companion object {
        val CODEC: MapCodec<CornSyrupBlock> = createCodec { settings: Settings ->
            CornSyrupBlock(
                settings
            )
        }
        private const val START_SLIDING_SPEED = 0.13
        private const val MIN_FALL_SPEED_TO_SLIDE = 0.08
        private const val THROTTLE_SLIDE_SPEED = 0.05
        private const val TICKS_PER_SECOND = 20
        val SHAPE: VoxelShape = createCuboidShape(1.0, 0.0, 1.0, 15.0, 15.0, 15.0)

        private fun hasHoneyBlockEffects(entity: Entity): Boolean {
            return entity is LivingEntity || entity is AbstractMinecartEntity || entity is TntEntity || entity is BoatEntity
        }

        fun addRegularParticles(entity: Entity) {
            addParticles(entity, 5)
        }

        fun addRichParticles(entity: Entity) {
            addParticles(entity, 10)
        }

        private fun addParticles(entity: Entity, count: Int) {
            if (entity.world.isClient) {
                val blockState = Blocks.HONEY_BLOCK.defaultState
                for (i in 0 until count) {
                    entity.world.addParticle(
                        BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                        entity.x,
                        entity.y,
                        entity.z,
                        0.0,
                        0.0,
                        0.0
                    )
                }
            }
        }
    }
}