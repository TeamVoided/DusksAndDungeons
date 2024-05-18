////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//package net.minecraft.entity.mob
//
//import net.minecraft.entity.*
//import net.minecraft.entity.ai.goal.SkeletonHorseTrapTriggerGoal
//import net.minecraft.entity.attribute.DefaultAttributeContainer
//import net.minecraft.entity.attribute.EntityAttributes
//import net.minecraft.entity.damage.DamageSource
//import net.minecraft.entity.passive.AbstractHorseEntity
//import net.minecraft.entity.passive.AnimalEntity
//import net.minecraft.entity.passive.PassiveEntity
//import net.minecraft.entity.player.PlayerEntity
//import net.minecraft.nbt.NbtCompound
//import net.minecraft.registry.tag.FluidTags
//import net.minecraft.server.world.ServerWorld
//import net.minecraft.sound.SoundEvent
//import net.minecraft.sound.SoundEvents
//import net.minecraft.util.ActionResult
//import net.minecraft.util.Hand
//import net.minecraft.util.math.BlockPos
//import net.minecraft.util.random.RandomGenerator
//import net.minecraft.world.World
//import net.minecraft.world.WorldAccess
//import java.util.*
//import kotlin.math.min
//
//class SkeletonHorseEntity(entityType: EntityType<out SkeletonHorseEntity?>?, world: World?) :
//    AbstractHorseEntity(entityType, world) {
//    private val trapTriggerGoal = SkeletonHorseTrapTriggerGoal(this)
//    var isTrapped: Boolean = false
//        set(trapped) {
//            if (trapped != field) {
//                field = trapped
//                if (trapped) {
//                    goalSelector.add(1, this.trapTriggerGoal)
//                } else {
//                    goalSelector.remove(this.trapTriggerGoal)
//                }
//            }
//        }
//    private var trapTime = 0
//
//    override fun initAttributes(random: RandomGenerator) {
//        val var10000 = this.getAttributeInstance(EntityAttributes.GENERIC_JUMP_STRENGTH)
//        Objects.requireNonNull(random)
//        var10000!!.baseValue = generateJumpStrengthBonus { random.nextDouble() }
//    }
//
//    override fun initCustomGoals() {
//    }
//
//    override fun getAmbientSound(): SoundEvent? {
//        return if (this.isSubmergedIn(FluidTags.WATER)) SoundEvents.ENTITY_SKELETON_HORSE_AMBIENT_WATER else SoundEvents.ENTITY_SKELETON_HORSE_AMBIENT
//    }
//
//    override fun getDeathSound(): SoundEvent? {
//        return SoundEvents.ENTITY_SKELETON_HORSE_DEATH
//    }
//
//    override fun getHurtSound(source: DamageSource): SoundEvent? {
//        return SoundEvents.ENTITY_SKELETON_HORSE_HURT
//    }
//
//    override fun getSwimSound(): SoundEvent {
//        if (this.isOnGround) {
//            if (!this.hasPassengers()) {
//                return SoundEvents.ENTITY_SKELETON_HORSE_STEP_WATER
//            }
//
//            ++this.soundTicks
//            if (this.soundTicks > 5 && this.soundTicks % 3 == 0) {
//                return SoundEvents.ENTITY_SKELETON_HORSE_GALLOP_WATER
//            }
//
//            if (this.soundTicks <= 5) {
//                return SoundEvents.ENTITY_SKELETON_HORSE_STEP_WATER
//            }
//        }
//
//        return SoundEvents.ENTITY_SKELETON_HORSE_SWIM
//    }
//
//    override fun playSwimSound(volume: Float) {
//        if (this.isOnGround) {
//            super.playSwimSound(0.3f)
//        } else {
//            super.playSwimSound(min(0.1, (volume * 25.0f).toDouble()).toFloat())
//        }
//    }
//
//    override fun playJumpSound() {
//        if (this.isTouchingWater) {
//            this.playSound(SoundEvents.ENTITY_SKELETON_HORSE_JUMP_WATER, 0.4f, 1.0f)
//        } else {
//            super.playJumpSound()
//        }
//    }
//
//    public override fun getDefaultDimensions(pose: EntityPose): EntityDimensions {
//        return if (this.isBaby) BABY_DIMENSIONS else super.getDefaultDimensions(pose)
//    }
//
//    override fun tickMovement() {
//        super.tickMovement()
//        if (this.isTrapped && trapTime++ >= 18000) {
//            this.discard()
//        }
//    }
//
//    override fun writeCustomDataToNbt(nbt: NbtCompound) {
//        super.writeCustomDataToNbt(nbt)
//        nbt.putBoolean("SkeletonTrap", this.isTrapped)
//        nbt.putInt("SkeletonTrapTime", this.trapTime)
//    }
//
//    override fun readCustomDataFromNbt(nbt: NbtCompound) {
//        super.readCustomDataFromNbt(nbt)
//        this.isTrapped = nbt.getBoolean("SkeletonTrap")
//        this.trapTime = nbt.getInt("SkeletonTrapTime")
//    }
//
//    override fun getBaseMovementSpeedMultiplier(): Float {
//        return 0.96f
//    }
//
//    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? {
//        return EntityType.SKELETON_HORSE.create(world)
//    }
//
//    override fun interactMob(player: PlayerEntity, hand: Hand): ActionResult {
//        return if (!this.isTame) ActionResult.PASS else super.interactMob(player, hand)
//    }
//
//    companion object {
//        private const val DESPAWN_AGE = 18000
//        private val BABY_DIMENSIONS: EntityDimensions =
//            EntityType.SKELETON_HORSE.dimensions.withAttachments(
//                EntityAttachments.builder()
//                    .attach(EntityAttachmentType.PASSENGER, 0.0f, EntityType.SKELETON_HORSE.height - 0.03125f, 0.0f)
//            ).scaled(0.5f)
//
//        fun createAttributes(): DefaultAttributeContainer.Builder {
//            return createBaseAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0)
//                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224)
//        }
//
//        fun canSpawn(
//            type: EntityType<out AnimalEntity?>?,
//            world: WorldAccess?,
//            reason: SpawnReason?,
//            pos: BlockPos?,
//            random: RandomGenerator?
//        ): Boolean {
//            return if (!SpawnReason.isSpawner(reason)) {
//                isValidNaturalSpawn(type, world, reason, pos, random)
//            } else {
//                SpawnReason.isTrialSpawner(reason) || isBrightEnoughForNaturalSpawn(world, pos)
//            }
//        }
//    }
//}