package org.teamvoided.dusk_autumn.data.tags

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskItemTags {
    val CASCADE_LOGS = create("cascade_logs")
    val LEAF_PILES = create("leaf_piles")
    val NETHER_BRICKS = create("nether_bricks")
    fun create(id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, id(id))
}