package org.teamvoided.voidlib.api.tab

import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import org.teamvoided.voidlib.impl.tab.TabSectionRegistryImpl

object TabSectionRegistry {

    @JvmStatic
    fun register(id: ResourceLocation, section: TabSection) {
        TabSectionRegistryImpl.register(id, section)
    }

    @JvmStatic
    fun register(key: ResourceKey<CreativeModeTab>, section: TabSection) {
        register(key.location(), section)
    }

    @JvmStatic
    fun getSections(id: ResourceLocation): List<TabSection> {
        return TabSectionRegistryImpl.getSections(id)
    }

    @JvmStatic
    fun getSections(key: ResourceKey<CreativeModeTab>): List<TabSection> {
        return getSections(key.location())
    }

}