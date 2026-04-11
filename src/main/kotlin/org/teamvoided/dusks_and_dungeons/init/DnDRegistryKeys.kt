package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant

object DnDRegistryKeys {

    val RACCOON_VARIANT: RegistryKey<Registry<RaccoonVariant>> = createRegistryKey("raccoon_variant")

    fun init() {
        DynamicRegistries.registerSynced(RACCOON_VARIANT, RaccoonVariant.CODEC)
    }

    private fun <T> createRegistryKey(id: String): RegistryKey<Registry<T>> = RegistryKey.ofRegistry(DusksAndDungeons.id(id));
}
