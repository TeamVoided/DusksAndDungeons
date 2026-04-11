package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.registry.RegistryKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant

@Suppress("UnstableApiUsage")
object DnDAttachmentTypes {

    val RACCOON_VARIANT: AttachmentType<RegistryKey<RaccoonVariant>> =
        AttachmentRegistry.create(DusksAndDungeons.id("raccoon_variant")) { builder:
            AttachmentRegistry.Builder<RegistryKey<RaccoonVariant>> ->
                builder
                    .initializer { null }
                    .persistent(RegistryKey.codec(DnDRegistryKeys.RACCOON_VARIANT))
                    .syncWith(RegistryKey.packetCodec(DnDRegistryKeys.RACCOON_VARIANT),
                        AttachmentSyncPredicate.all())
        }

    fun init() {}
}
