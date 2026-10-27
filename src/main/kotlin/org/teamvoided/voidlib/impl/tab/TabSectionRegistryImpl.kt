package org.teamvoided.voidlib.impl.tab

import net.minecraft.resources.ResourceLocation
import org.teamvoided.voidlib.api.tab.TabSection

object TabSectionRegistryImpl {

    internal val SECTIONS = mutableMapOf<ResourceLocation, MutableList<TabSection>>()

    internal fun register(id: ResourceLocation, section: TabSection) {
        SECTIONS.computeIfAbsent(id) { mutableListOf() }.add(section)
    }

    internal fun getSections(id: ResourceLocation): List<TabSection> {
        return SECTIONS[id]?.toList() ?: listOf()
    }

}