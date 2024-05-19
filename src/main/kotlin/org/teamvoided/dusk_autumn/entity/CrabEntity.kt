package org.teamvoided.dusk_autumn.entity


import net.minecraft.block.BlockState
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.AbstractSkeletonEntity
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.passive.org.teamvoided.dusk_autumn.data.DuskEntityTypeTags
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.EntityTypeTags
import net.minecraft.registry.tag.ItemTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.TimeHelper
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.init.DuskEntities
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.*


class CrabEntity : AnimalEntity, Angerable, GeoEntity {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    private val fallDamageReduction = 5
    private val TARGET_ENTITY_ID: TrackedData<OptionalInt>? = null
    private var targetUuid: UUID? = null

    init {
        this.addPathfindingPenalty(PathNodeType.WATER, 0.0f)
        this.addPathfindingPenalty(PathNodeType.TRAPDOOR, -1.0f)
        this.moveControl = AquaticMoveControl(this, 85, 20, swimSpeed, landSpeed, false)
    }

    constructor(entityType: EntityType<out AnimalEntity>, world: World) : super(entityType, world)
    constructor(world: World) : super(DuskEntities.CRAB, world)

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(
                this, "wave", 5
            ) { state ->
                state.setAndContinue(
                    RawAnimation.begin().thenPlay("wave")
                )
            }
        )
    }

    override fun initGoals() {
//        goalSelector.add(1, SwimGoal(this))
//        goalSelector.add(1, WolfEscapeDangerGoal(this, 1.5))
//        goalSelector.add(
//            3, AvoidLlamaGoal(
//                this,
//                LlamaEntity::class.java, 24.0f, 1.5, 1.5
//            )
//        )
        goalSelector.add(4, PounceAtTargetGoal(this, 0.4f))
        goalSelector.add(5, MeleeAttackGoal(this, 1.0, true))
        goalSelector.add(7, AnimalMateGoal(this, 1.0))
        goalSelector.add(8, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(
            10, LookAtEntityGoal(
                this,
                PlayerEntity::class.java, 8.0f
            )
        )
        goalSelector.add(10, LookAroundGoal(this))
        targetSelector.add(
            1, RevengeGoal(this, *arrayOfNulls(0)).setGroupRevenge(*arrayOfNulls(0))
        )
        targetSelector.add(
            2, TargetGoal(
                this,
                PlayerEntity::class.java, 10, true, false
            ) { entity: LivingEntity ->
                val entityType = entity.type
                entityType.isIn(DuskEntityTypeTags.CRAB_ATTACKS)
            }
        )
        targetSelector.add(3, UniversalAngerGoal(this, true))
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(ANGER_TIME, 0)
    }

    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? = null

    override fun isBreedingItem(stack: ItemStack): Boolean = stack.isIn(ItemTags.BOATS)



    override fun computeFallDamage(fallDistance: Float, damageMultiplier: Float): Int {
        return super.computeFallDamage(fallDistance, damageMultiplier) - fallDamageReduction
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?
    ): EntityData? {
        return super.initialize(world, difficulty, spawnReason, entityData)
    }

    override fun getAmbientSound(): SoundEvent = SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT
    override fun playStepSound(pos: BlockPos, state: BlockState) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15f, 1.0f)
    }
    override fun getHurtSound(source: DamageSource): SoundEvent = SoundEvents.ENTITY_WITHER_SKELETON_HURT
    override fun getDeathSound(): SoundEvent = SoundEvents.ENTITY_WITHER_SKELETON_DEATH

    override fun isPushedByFluids(): Boolean = false
    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = cache
    override fun getAngerTime(): Int {
        return dataTracker.get(ANGER_TIME)
    }

    override fun setAngerTime(ticks: Int) {
        dataTracker.set(ANGER_TIME, ticks)
    }

    override fun chooseRandomAngerTime() {
        this.angerTime = ANGER_TIME_RANGE[random]
    }

    override fun getAngryAt(): UUID? {
        return this.targetUuid
    }

    override fun setAngryAt(uuid: UUID?) {
        this.targetUuid = uuid
    }

    companion object {
        fun createAttributes(): DefaultAttributeContainer.Builder {
            return MobEntity.createAttributes()
                .add(EntityAttributes.GENERIC_ARMOR, 4.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 7.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.0)
        }
        private val landSpeed = 0.6f
        private val swimSpeed = 0.02f
        private val ANGER_TIME: TrackedData<Int> =
            DataTracker.registerData(CrabEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        private val ANGER_TIME_RANGE: UniformIntProvider = TimeHelper.betweenSeconds(20, 39)
    }
}
