package org.teamvoided.dusk_autumn.data

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskItemTags {
    val CRAB_FOOD = create("crab_food")

    fun create(id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, id(id))
}