package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.item.ProjectileItem
import net.minecraft.registry.tag.ItemTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.ItemInteractionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.BlockView
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.init.DnDParticles
import org.teamvoided.dusk_autumn.util.radToDeg

class MushroomLaunchBlock(settings: Settings) : Block(settings) {
    override fun onLandedUpon(world: World, state: BlockState, pos: BlockPos, entity: Entity, fallDistance: Float) {
        if (entity.bypassesLandingEffects() && !entity.isSneaking) {
            super.onLandedUpon(world, state, pos, entity, fallDistance)
        } else {
            entity.handleFallDamage(fallDistance, 0f, world.damageSources.fall())
            playBounce(entity.velocity.y.toFloat(), world, entity.blockPos)
        }
    }

    override fun onEntityLand(world: BlockView, entity: Entity) {
        if (entity.bypassesLandingEffects() && !entity.isSneaking) {
            super.onEntityLand(world, entity)
        } else {
            this.bounce(entity)
            if (entity is PlayerEntity && !entity.mainHandStack.isEmpty && entity.getAttackCooldownProgress(0f) >= 1) {
                entity.resetLastAttackedTicks()
            }
        }
    }

    override fun onProjectileHit(world: World, state: BlockState, hit: BlockHitResult, projectile: ProjectileEntity) {
        super.onProjectileHit(world, state, hit, projectile)
    }

    override fun onInteract(
        stack: ItemStack,
        state: BlockState,
        world: World,
        pos: BlockPos,
        entity: PlayerEntity,
        hand: Hand,
        hitResult: BlockHitResult
    ): ItemInteractionResult {
        if (stack.item !is BlockItem && stack.item !is ProjectileItem) {
            if (entity.velocity.y < 0.1) {
                launch(stack, state, world, pos, entity)
                return ItemInteractionResult.SUCCESS
            }
            entity.resetLastAttackedTicks()
        }
        return super.onInteract(stack, state, world, pos, entity, hand, hitResult)
    }

    override fun onBlockBreakStart(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity) {
        val stack = player.mainHandStack
        if (stack.isIn(ItemTags.WEAPON_ENCHANTABLE) || stack.isEmpty) {
            launch(stack, state, world, pos, player)
            player.resetLastAttackedTicks()
        }
        super.onBlockBreakStart(state, world, pos, player)
    }

    fun launch(stack: ItemStack, state: BlockState, world: World, pos: BlockPos, entity: PlayerEntity) {
        val cooldown = entity.getAttackCooldownProgress(0.5f)
        val mult: Float = if (cooldown > 0.9f) getAttackDamageWith(entity, stack).toFloat() * 0.2f
        else 0.001f
        playBounce(mult - 0.1f, world, pos)
        launchFromFacing(entity, -(mult + 0.5))
        onLandedUpon(world, state, pos, entity, entity.fallDistance)
        if (mult > 3) {
            explodeBlock(world, pos)
        } else if (mult > 0.75) {
            launchParticles(world, pos, world.random.nextInt((mult * 50).toInt()), mult.toDouble())
        }
        entity.resetLastAttackedTicks()
    }

    private fun getAttackDamageWith(entity: LivingEntity, weapon: ItemStack): Double {
        val attributeModifiersComponent = weapon.getOrDefault(
            DataComponentTypes.ATTRIBUTE_MODIFIERS,
            AttributeModifiersComponent.DEFAULT
        )
        val extra = //literally just the mace
            weapon.item.method_58403(entity, 0f, entity.damageSources.mobAttack(entity)) // .playerAttack(entity)
        return attributeModifiersComponent.compute(
            entity.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE),
            EquipmentSlot.MAINHAND
        ) + extra
    }

    private fun bounce(entity: Entity) {
        val vec3d = entity.velocity
        if (vec3d.y < 0.0) {
            val mult = if (entity is LivingEntity) 1.0 else 0.8
            entity.setVelocity(vec3d.x, -vec3d.y * mult, vec3d.z)
        }
    }

    private fun launchFromFacing(entity: Entity, mult: Double) {
        val pitchSin: Double = MathHelper.sin(entity.pitch * radToDeg).toDouble()
        val pitchCos: Double = MathHelper.cos(entity.pitch * radToDeg).toDouble()
        val yawSin: Double = MathHelper.sin(entity.yaw * radToDeg).toDouble()
        val yawCos: Double = MathHelper.cos(entity.yaw * radToDeg).toDouble()
        entity.addVelocity(
            -yawSin * pitchCos * mult,
            -pitchSin * mult,
            yawCos * pitchCos * mult
        )
    }

    fun explodeBlock(world: World, pos: BlockPos) {
        val rand = world.random
        if (rand.nextInt(5) == 0) world.breakBlock(pos, rand.nextInt(5) != 0)
        launchParticles(world, pos, rand.nextInt(50) + 50)
    }

    fun launchParticles(world: World, pos: BlockPos, count: Int, multiplier: Double = 1.0) {
        val rand = world.random
        val centerBlock: Vec3d = pos.ofCenter()
        repeat(count) {
            val velocity: Vec3d = Vec3d(
                (rand.nextDouble() - rand.nextDouble()) * multiplier,
                (rand.nextDouble() - rand.nextDouble()) * multiplier,
                (rand.nextDouble() - rand.nextDouble()) * multiplier,
            )
            world.addParticle(
                DnDParticles.MUSHROOM_LAUNCH,
                centerBlock.x,
                centerBlock.y,
                centerBlock.z,
                velocity.x,
                velocity.y,
                velocity.z,
            )
        }
    }

    fun playBounce(pitch: Float, world: World, pos: BlockPos) {
        world.playSound(
            null as PlayerEntity?,
            pos,
            SoundEvents.BLOCK_SHROOMLIGHT_BREAK,
            SoundCategory.BLOCKS,
            1.0f,
            pitch
        )
    }
}