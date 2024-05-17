package org.teamvoided.dusk_autumn.entity


import net.minecraft.block.BlockState
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.control.AquaticMoveControl
import net.minecraft.entity.ai.pathing.PathNodeType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import org.teamvoided.dusk_autumn.init.DuskEntities
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.*


class CrabEntity : AnimalEntity, GeoEntity {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    private val FALL_DAMAGE_REDUCTION = 5
    private val TARGET_ENTITY_ID: TrackedData<OptionalInt>? = null

    init {
//        this.lookControl = LookControl(this)
        this.addPathfindingPenalty(PathNodeType.WATER, 4.0f)
        this.addPathfindingPenalty(PathNodeType.TRAPDOOR, -1.0f)
        this.moveControl = AquaticMoveControl(this, 85, 10, 0.02f, 0.1f, true)
    }

    constructor(entityType: EntityType<out AnimalEntity>, world: World) : super(entityType, world)
    constructor(world: World) : super(DuskEntities.CRAB, world)


//    private inner class LookControl(entity: MobEntity) : net.minecraft.entity.ai.control.LookControl(entity) {
//        override fun shouldStayHorizontal(): Boolean {
//            return targetEntity().isEmpty
//        }
//    }
//
//    fun targetEntity(): Optional<Entity?> {
//        val var10000 = dataTracker.get(TARGET_ENTITY_ID).stream()
//        val var10001 = this.world
//        Objects.requireNonNull(var10001)
//        return var10000.mapToObj { id: Int ->
//            var10001.getEntityById(
//                id
//            )
//        }.filter { obj: Entity? ->
//            Objects.nonNull(
//                obj
//            )
//        }.findFirst()
//    }

    override fun createChild(world: ServerWorld, entity: PassiveEntity): PassiveEntity? = null

    override fun isBreedingItem(stack: ItemStack?): Boolean = false

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(
                this, "wave", 5
            ) { state ->
                state.setAndContinue(
                    DefaultAnimations.IDLE
                )
            }
        )
    }

    override fun computeFallDamage(fallDistance: Float, damageMultiplier: Float): Int {
        return super.computeFallDamage(fallDistance, damageMultiplier) - FALL_DAMAGE_REDUCTION
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?
    ): EntityData? {
//        initializeMemories(this, world.random)
        return super.initialize(world, difficulty, spawnReason, entityData)
    }

    override fun getAmbientSound(): SoundEvent = SoundEvents.ENTITY_PARROT_IMITATE_SPIDER
    override fun getHurtSound(source: DamageSource): SoundEvent = SoundEvents.ENTITY_IRON_GOLEM_HURT
    override fun getDeathSound(): SoundEvent = SoundEvents.ENTITY_WITHER_DEATH
    override fun playStepSound(pos: BlockPos, state: BlockState) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15f, 1.0f)
    }

    override fun isPushedByFluids(): Boolean = false

//    override fun getTarget(): LivingEntity? = this.attackTarget

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = cache

}
