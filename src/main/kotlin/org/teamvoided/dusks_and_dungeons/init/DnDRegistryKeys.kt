package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant

object DnDRegistryKeys {

    val RACCOON_VARIANT = key<RaccoonVariant>("raccoon_variant")

    fun init() {
        DynamicRegistries.registerSynced(RACCOON_VARIANT, RaccoonVariant.CODEC)
    }

    fun <T> key(id: String): ResourceKey<Registry<T>> = ResourceKey.createRegistryKey(id(id))

}
