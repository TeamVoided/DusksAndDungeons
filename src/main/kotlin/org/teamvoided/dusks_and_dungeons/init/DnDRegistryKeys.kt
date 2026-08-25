package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrownItemDefinition

// TODO(1.0) rename to DnDRegistries
object DnDRegistryKeys {

    val THROWN_ITEM_DEFINITION = key<ThrownItemDefinition>("thrown_item_definition")

    fun init() {
        DynamicRegistries.registerSynced(THROWN_ITEM_DEFINITION, ThrownItemDefinition.DIRECT_CODEC)
    }

    fun <T> key(id: String): ResourceKey<Registry<T>> = ResourceKey.createRegistryKey(id(id))

}