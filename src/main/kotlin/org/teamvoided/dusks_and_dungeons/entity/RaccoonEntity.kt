package org.teamvoided.dusks_and_dungeons.entity

import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.VariantProvider
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.Holder
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.teamvoided.dusks_and_dungeons.entity.goal.FindBarrelGoal
import org.teamvoided.dusks_and_dungeons.entity.goal.PickBerriesGoal
import org.teamvoided.dusks_and_dungeons.entity.goal.WashFoodGoal
import org.teamvoided.dusks_and_dungeons.init.DnDAttachmentTypes
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys

class RaccoonEntity(type: EntityType<out AnimalEntity>, world: World) : AnimalEntity(type, world),
    VariantProvider<Holder<RaccoonVariant>> {

    override fun initGoals() {
        goalSelector.add(1, SwimGoal(this))
        goalSelector.add(2, AnimalMateGoal(this, 1.0))
        goalSelector.add(7, FindBarrelGoal(this, 1.2, 12))
        goalSelector.add(7, WashFoodGoal(this, 1.2, 12))
        goalSelector.add(8, PickBerriesGoal(this, 1.2, 12, 1))
        goalSelector.add(9, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(10, LookAtEntityGoal(this, PlayerEntity::class.java, 8F))
        goalSelector.add(10, LookAroundGoal(this))
    }

    override fun initDataTracker(builder: DataTracker.Builder) {
        super.initDataTracker(builder)
        builder.add(BARREL_POS, BlockPos.ORIGIN)
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt("barrel_x", barrelPos.x)
        nbt.putInt("barrel_y", barrelPos.y)
        nbt.putInt("barrel_z", barrelPos.z)
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        barrelPos = BlockPos(nbt.getInt("barrel_x"), nbt.getInt("barrel_y"), nbt.getInt("barrel_z"))
    }

    override fun tick() {
        super.tick()
        if (!world.getBlockState(barrelPos).isOf(Blocks.BARREL)) {
            barrelPos = BlockPos.ORIGIN
        }
    }

    override fun isBreedingItem(stack: ItemStack?): Boolean {
        return false
    }

    override fun createChild(world: ServerWorld?, entity: PassiveEntity?): PassiveEntity? {
        TODO("Not yet implemented")
    }

    @Suppress("UnstableApiUsage")
    override fun setVariant(variant: Holder<RaccoonVariant>) {
        setAttached(DnDAttachmentTypes.RACCOON_VARIANT, variant.key.get())
    }

    @Suppress("UnstableApiUsage")
    override fun getVariant(): Holder<RaccoonVariant> {
        return world.registryManager.getLookupOrThrow(DnDRegistryKeys.RACCOON_VARIANT)
            .getHolderOrThrow(getAttachedOrCreate(DnDAttachmentTypes.RACCOON_VARIANT))
    }

    var barrelPos: BlockPos
        get() = dataTracker.get(BARREL_POS)
        set(value) = dataTracker.set(BARREL_POS, value)


    companion object {
        val BARREL_POS: TrackedData<BlockPos> =
            DataTracker.registerData(RaccoonEntity::class.java, TrackedDataHandlerRegistry.BLOCK_POS)
    }

}