package org.teamvoided.dusk_autumn.data

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskItemTags {

    fun create(id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, id(id))
}