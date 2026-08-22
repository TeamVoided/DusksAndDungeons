package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.entity.RaccoonVariant
import org.teamvoided.dusks_and_dungeons.item.ThrownItemDefinition

// TODO rename to DnDRegistries
object DnDRegistryKeys {

    val RACCOON_VARIANT = key<RaccoonVariant>("raccoon_variant")
    val THROWN_ITEM_DEFINITION = key<ThrownItemDefinition>("thrown_item_definition")

    fun init() {
        DynamicRegistries.registerSynced(RACCOON_VARIANT, RaccoonVariant.CODEC)
        DynamicRegistries.registerSynced(THROWN_ITEM_DEFINITION, ThrownItemDefinition.DIRECT_CODEC)
    }

    fun <T> key(id: String): ResourceKey<Registry<T>> = ResourceKey.createRegistryKey(id(id))

}