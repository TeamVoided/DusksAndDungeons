package org.teamvoided.dusks_and_dungeons.entity

import net.minecraft.world.level.block.Blocks
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.VariantHolder
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.core.Holder
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.entity.goal.FindBarrelGoal
import org.teamvoided.dusks_and_dungeons.entity.goal.PickBerriesGoal
import org.teamvoided.dusks_and_dungeons.entity.goal.WashFoodGoal
import org.teamvoided.dusks_and_dungeons.init.DnDAttachmentTypes
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys

class RaccoonEntity(type: EntityType<out Animal>, world: Level) : Animal(type, world),
    VariantHolder<Holder<RaccoonVariant>> {

    override fun registerGoals() {
        goalSelector.addGoal(1, FloatGoal(this))
        goalSelector.addGoal(2, BreedGoal(this, 1.0))
        goalSelector.addGoal(7, FindBarrelGoal(this, 1.2, 12))
        goalSelector.addGoal(7, WashFoodGoal(this, 1.2, 12))
        goalSelector.addGoal(8, PickBerriesGoal(this, 1.2, 12, 1))
        goalSelector.addGoal(9, WaterAvoidingRandomStrollGoal(this, 1.0))
        goalSelector.addGoal(10, LookAtPlayerGoal(this, Player::class.java, 8F))
        goalSelector.addGoal(10, RandomLookAroundGoal(this))
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(BARREL_POS, BlockPos.ZERO)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        nbt.putInt("barrel_x", barrelPos.x)
        nbt.putInt("barrel_y", barrelPos.y)
        nbt.putInt("barrel_z", barrelPos.z)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        barrelPos = BlockPos(nbt.getInt("barrel_x"), nbt.getInt("barrel_y"), nbt.getInt("barrel_z"))
    }

    override fun tick() {
        super.tick()
        if (!level().getBlockState(barrelPos).`is`(Blocks.BARREL)) {
            barrelPos = BlockPos.ZERO
        }
    }

    override fun isFood(stack: ItemStack): Boolean {
        return false
    }

    override fun getBreedOffspring(world: ServerLevel, entity: AgeableMob): AgeableMob? {
        TODO("Not yet implemented")
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
    }

}