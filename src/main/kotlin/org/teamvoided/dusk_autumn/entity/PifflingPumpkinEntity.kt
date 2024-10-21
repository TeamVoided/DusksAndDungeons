package org.teamvoided.dusk_autumn.entity

import net.minecraft.entity.*
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.passive.GolemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import java.util.*
import kotlin.jvm.optionals.getOrNull


class PifflingPumpkinEntity(entityType: EntityType<out PifflingPumpkinEntity>, world: World) :
    GolemEntity(entityType, world) {
    var stateTicks: Int = 0
    val twitchAnimationState: AnimationState = AnimationState()
    val idleAnimationState: AnimationState = AnimationState()

    init {
        this.setCanPickUpLoot(true)
        Arrays.fill(this.handDropChances, 2f)
    }

    override fun initGoals() {
        goalSelector.add(0, EscapeDangerGoal(this, 2.0))
//        goalSelector.add(1, TuffGolemHome(this, 1.0))
        goalSelector.add(2, WanderAroundFarGoal(this, 1.0, 1f))
        goalSelector.add(4, LookAtEntityGoal(this, PlayerEntity::class.java, 6f))
        goalSelector.add(5, LookAroundGoal(this))
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?
    ): EntityData? {
        summonedPos = this.blockPos
        if (this.getEquippedStack(EquipmentSlot.HEAD) == ItemStack.EMPTY) {
            this.equipStack(EquipmentSlot.HEAD, DnDFloraBlocks.SMALL_CARVED_PUMPKIN.asItem().defaultStack)
        }
        return super.initialize(world, difficulty, spawnReason, entityData)
    }
//
//    private fun getLootTableHand(world: ServerWorld, golem: TuffGolemEntity) {
//        val states = world.getLootTable(NulliumLootTables.ENDERMAN_HOLDS)
//            .generateLoot(LootContextParameterSet())
//            .mapNotNull {
//                it
//            }
//
//        if (states.isNotEmpty()) {
//            states.random().let {
//                if (!it == ItemStack.EMPTY) golem.carriedBlock = it
//            }
//        }
//    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(SUMMON_POS, Optional.empty())
    }

//    override fun writeCustomDataToNbt(nbt: NbtCompound) {
//        super.writeCustomDataToNbt(nbt)
//    }
//
//    override fun readCustomDataFromNbt(nbt: NbtCompound) {
//        super.readCustomDataFromNbt(nbt)
//    }

    override fun tick() {
        if (world.isClient()) {
            this.updateAnimationStates()
        }
        super.tick()
    }

    override fun canEquip(stack: ItemStack): Boolean {
        val equipmentSlot = this.getPreferredEquipmentSlot(stack)
        return if (isHoldingItem()) {
            false
        } else {
            equipmentSlot == EquipmentSlot.MAINHAND && super.canEquip(stack)
        }
    }

    override fun canPickupItem(stack: ItemStack): Boolean = !isHoldingItem()

    fun isHoldingItem(): Boolean {
        return this.hasStackEquipped(EquipmentSlot.MAINHAND)
    }

    var summonedPos: BlockPos?
        get() = dataTracker[SUMMON_POS].getOrNull()
        set(summonedPos) {
            dataTracker[SUMMON_POS] = Optional.ofNullable(summonedPos)
        }

    override fun getHurtSound(source: DamageSource): SoundEvent? {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH
    }

    private fun updateAnimationStates() {
        if (this.stateTicks in 0..twitchLength) {
            this.stateTicks++
            twitchAnimationState.start(this.age)
        } else {
            twitchAnimationState.stop()
        }
        if (this.velocity.horizontalLength() == 0.0)
            idleAnimationState.start(this.age)
        else
            idleAnimationState.stop()
    }

    override fun isPersistent(): Boolean {
        return true
    }

    companion object {
        private val SUMMON_POS: TrackedData<Optional<BlockPos>> =
            DataTracker.registerData(
                PifflingPumpkinEntity::class.java,
                TrackedDataHandlerRegistry.OPTIONAL_BLOCK_POS
            )

        val twitchLength = 20

        fun createAttributes(): DefaultAttributeContainer.Builder {
            return MobEntity.createAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
        }
    }
}