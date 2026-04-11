package org.teamvoided.dusks_and_dungeons.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.VariantProvider
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.Holder
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import org.teamvoided.dusks_and_dungeons.init.DnDAttachmentTypes
import org.teamvoided.dusks_and_dungeons.init.DnDRaccoonVariants
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys

class RaccoonEntity(entityType: EntityType<out AnimalEntity>, world: World) : AnimalEntity(entityType, world),
    VariantProvider<Holder<RaccoonVariant>> {

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