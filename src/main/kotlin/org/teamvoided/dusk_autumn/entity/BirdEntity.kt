package org.teamvoided.dusk_autumn.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.control.FlightMoveControl
import net.minecraft.entity.ai.goal.SwimGoal
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.ParrotEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World

class BirdEntity(entityType: EntityType<out BirdEntity>, world: World) : AnimalEntity(entityType, world) {

    init {
        this.moveControl = FlightMoveControl(this, 10, false)
        this.addPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0f)
        this.addPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -1.0f)
    }


    private var flapProgress: Float = 0f
    private var maxWingDeviation: Float = 0f
    private var prevMaxWingDeviation: Float = 0f
    private var prevFlapProgress: Float = 0f
    private var flapSpeed = 1.0f
    private var nextFlap = 1.0f

    override fun initGoals() {
        goalSelector.add(0, SwimGoal(this))
    }


    private fun flapWings() {
        this.prevFlapProgress = this.flapProgress
        this.prevMaxWingDeviation = this.maxWingDeviation
        this.maxWingDeviation += (if (!this.isOnGround && !this.hasVehicle()) 4 else -1).toFloat() * 0.3f
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0f, 1.0f)
        if (!this.isOnGround && this.flapSpeed < 1.0f) {
            this.flapSpeed = 1.0f
        }

        this.flapSpeed *= 0.9f
        val vec3d = this.velocity
        if (!this.isOnGround && vec3d.y < 0.0) {
            this.velocity = vec3d.multiply(1.0, 0.6, 1.0)
        }

        this.flapProgress += this.flapSpeed * 2.0f
    }

    override fun hasWings(): Boolean {
        return this.flyDistance > this.nextFlap
    }

    override fun addFlapEffects() {
        this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15f, 1.0f)
        this.nextFlap = this.flyDistance + this.maxWingDeviation / 2.0f
    }

    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? {
        return null
    }

    override fun isBreedingItem(stack: ItemStack): Boolean {
        return false
    }

    fun isInAir(): Boolean = !this.isOnGround

    companion object {

    }
}