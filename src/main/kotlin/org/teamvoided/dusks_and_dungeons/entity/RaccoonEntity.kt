package org.teamvoided.dusks_and_dungeons.entity

import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.entity.goal.*
import org.teamvoided.dusks_and_dungeons.init.DnDAttachmentTypes
import org.teamvoided.dusks_and_dungeons.init.DnDEntities
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys

class RaccoonEntity(type: EntityType<out Animal>, world: Level) : Animal(type, world),
    VariantHolder<Holder<RaccoonVariant>> {

    var ticksSinceEaten: Int = 0

    init {
        setCanPickUpLoot(true)
    }

    override fun registerGoals() {
        goalSelector.addGoal(1, FloatGoal(this))
        goalSelector.addGoal(2, BreedGoal(this, 1.0))
        goalSelector.addGoal(7, ClaimBarrelGoal(this, 1.2, 12))
        goalSelector.addGoal(7, WashFoodGoal(this, 1.2, 12))
        goalSelector.addGoal(8, PickBerriesGoal(this, 1.2, 12, 1))
        goalSelector.addGoal(9, WaterAvoidingRandomStrollGoal(this, 1.0))
        goalSelector.addGoal(9, RaccoonSearchForItemsGoal(this))
        goalSelector.addGoal(9, StoreItemsGoal(this, 1.2, 12))
        goalSelector.addGoal(10, LookAtPlayerGoal(this, Player::class.java, 8F))
        goalSelector.addGoal(10, RandomLookAroundGoal(this))
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(BARREL_POS, DEFAULT_BARREL_POS)
    }

    override fun addAdditionalSaveData(tag: CompoundTag) {
        super.addAdditionalSaveData(tag)
        if (barrelPos != DEFAULT_BARREL_POS) {
            val barrelPosTag = CompoundTag()
            barrelPosTag.putInt("x", barrelPos.x)
            barrelPosTag.putInt("y", barrelPos.y)
            barrelPosTag.putInt("z", barrelPos.z)
            tag.put("barrel_pos", barrelPosTag)
        }
    }

    override fun readAdditionalSaveData(tag: CompoundTag) {
        super.readAdditionalSaveData(tag)
        if (tag.contains("barrel_pos", Tag.TAG_COMPOUND.toInt())) {
            val barrelPosTag = tag.getCompound("barrel_pos")
            barrelPos = BlockPos(barrelPosTag.getInt("x"), barrelPosTag.getInt("y"), barrelPosTag.getInt("z"))
        }
    }

    override fun tick() {
        super.tick()
        if (!level().isClientSide) {
            if (barrelPos != DEFAULT_BARREL_POS && !level().getBlockState(barrelPos).`is`(Blocks.BARREL)) {
                barrelPos = DEFAULT_BARREL_POS
                level().broadcastEntityEvent(this, EntityEvent.VILLAGER_ANGRY)
            }
        }
    }

    override fun handleEntityEvent(b: Byte) {
        if (b == EntityEvent.VILLAGER_HAPPY) {
            for (i in 0..5) {
                level().addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    getRandomX(1.0),
                    randomY,
                    getRandomZ(1.0),
                    random.nextGaussian() * 0.02,
                    random.nextGaussian() * 0.02,
                    random.nextGaussian() * 0.02
                )
            }
        } else if (b == EntityEvent.VILLAGER_ANGRY) {
            for (i in 0..5) {
                level().addParticle(
                    ParticleTypes.ANGRY_VILLAGER,
                    getRandomX(1.0),
                    randomY,
                    getRandomZ(1.0),
                    random.nextGaussian() * 0.02,
                    random.nextGaussian() * 0.02,
                    random.nextGaussian() * 0.02
                )
            }
        }
    }

    fun getHeldItem() = getItemBySlot(EquipmentSlot.MAINHAND)!!

    override fun canTakeItem(stack: ItemStack): Boolean {
        val slot = getEquipmentSlotForItem(stack)
        if (!canPickup(stack)) {
            return false
        }
        return slot == EquipmentSlot.MAINHAND && super.canTakeItem(stack)
    }

    override fun canHoldItem(stack: ItemStack): Boolean {
        val heldStack = getHeldItem()
        return heldStack.isEmpty ||
                (heldStack.count < heldStack.maxStackSize && ItemStack.isSameItemSameComponents(heldStack, stack))
                || (ticksSinceEaten > 0 && stack.has(DataComponents.FOOD) && !heldStack.has(DataComponents.FOOD))
    }

    fun dropItemStack(stack: ItemStack) {
        val itemEntity = ItemEntity(level(), x, y, z, stack)
        level().addFreshEntity(itemEntity)
    }

    override fun pickUpItem(itemEntity: ItemEntity) {
        val stack = itemEntity.item
        if (canHoldItem(stack)) {
            var heldStack = getHeldItem()
            if (!heldStack.isEmpty) {
                val newStack = stack.split(heldStack.maxStackSize - heldStack.count)
                heldStack.count += newStack.count
                dropItemStack(stack)
            } else {
                heldStack = stack
            }

            onItemPickup(itemEntity)
            setItemSlot(EquipmentSlot.MAINHAND, heldStack)
            setGuaranteedDrop(EquipmentSlot.MAINHAND)
            take(itemEntity, stack.count)
            itemEntity.discard()
            ticksSinceEaten = 0
        }
    }

    fun canPickup(stack: ItemStack): Boolean = stack.isEmpty || stack.count < stack.maxStackSize

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(DnDItemTags.RACCOON_FOOD)
    }

    fun canMove(): Boolean {
        return !isSleeping // TODO add `not sitting` check
    }

    override fun getBreedOffspring(level: ServerLevel, entity: AgeableMob): AgeableMob? {
        val baby = DnDEntities.RACCOON.create(level)
        if (baby != null) {
            baby.variant = if (random.nextBoolean()) variant else (entity as RaccoonEntity).variant
        }

        return baby
    }

    @Suppress("UnstableApiUsage")
    override fun setVariant(variant: Holder<RaccoonVariant>) {
        setAttached(DnDAttachmentTypes.RACCOON_VARIANT, variant.unwrapKey().get())
    }

    @Suppress("UnstableApiUsage")
    override fun getVariant(): Holder<RaccoonVariant> {
        return level().registryAccess().lookupOrThrow(DnDRegistryKeys.RACCOON_VARIANT)
            .getOrThrow(getAttachedOrCreate(DnDAttachmentTypes.RACCOON_VARIANT))
    }

    var barrelPos: BlockPos
        get() = entityData.get(BARREL_POS)
        set(value) = entityData.set(BARREL_POS, value)

    companion object {
        val BARREL_POS: EntityDataAccessor<BlockPos> =
            SynchedEntityData.defineId(RaccoonEntity::class.java, EntityDataSerializers.BLOCK_POS)
        val DEFAULT_BARREL_POS = BlockPos(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE)
    }
}