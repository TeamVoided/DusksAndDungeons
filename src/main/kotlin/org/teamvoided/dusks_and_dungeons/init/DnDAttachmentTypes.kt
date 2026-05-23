package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant

@Suppress("UnstableApiUsage")
object DnDAttachmentTypes {

    val RACCOON_VARIANT: AttachmentType<ResourceKey<RaccoonVariant>> =
        AttachmentRegistry.create(id("raccoon_variant")) { builder ->
            builder
                .initializer(DnDRaccoonVariants::DEFAULT)
                .persistent(ResourceKey.codec(DnDRegistryKeys.RACCOON_VARIANT))
                .syncWith(ResourceKey.streamCodec(DnDRegistryKeys.RACCOON_VARIANT), AttachmentSyncPredicate.all())
        }

    fun init() = Unit

}
