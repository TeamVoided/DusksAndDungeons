package org.teamvoided.dusks_and_dungeons.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.VariantProvider
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.Holder
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import org.teamvoided.dusks_and_dungeons.entity.goal.PickBerriesGoal
import org.teamvoided.dusks_and_dungeons.init.DnDAttachmentTypes
import org.teamvoided.dusks_and_dungeons.init.DnDRaccoonVariants
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys

class RaccoonEntity(entityType: EntityType<out AnimalEntity>, world: World) : AnimalEntity(entityType, world),
    VariantProvider<Holder<RaccoonVariant>> {

    override fun initGoals() {
        goalSelector.add(1, SwimGoal(this))
        goalSelector.add(2, AnimalMateGoal(this, 1.0))
        goalSelector.add(8, PickBerriesGoal(this, 1.2, 12, 1))
        goalSelector.add(9, WanderAroundFarGoal(this, 1.0))
        goalSelector.add(10, LookAtEntityGoal(this, PlayerEntity::class.java, 8F))
        goalSelector.add(10, LookAroundGoal(this))
    }

    override fun isBreedingItem(stack: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    override fun createChild(
        world: ServerWorld?,
        entity: PassiveEntity?
    ): PassiveEntity? {
        TODO("Not yet implemented")
    }

    @Suppress("UnstableApiUsage")
    override fun setVariant(variant: Holder<RaccoonVariant>) {
        setAttached(DnDAttachmentTypes.RACCOON_VARIANT, variant.key.get())
    }

    @Suppress("UnstableApiUsage")
    override fun getVariant(): Holder<RaccoonVariant>? {
        return world.registryManager.getLookupOrThrow(DnDRegistryKeys.RACCOON_VARIANT).getHolderOrThrow(
            getAttachedOrElse(DnDAttachmentTypes.RACCOON_VARIANT, DnDRaccoonVariants.DEFAULT)
        )
    }
}