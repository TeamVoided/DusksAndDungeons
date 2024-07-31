package org.teamvoided.dusk_autumn.data.tags

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskItemTags {
    val CASCADE_LOGS = create("cascade_logs")
    val BIG_CANDLES = create("big_candles")
    val SOUL_CANDLES = create("soul_candles")
    val BIG_SOUL_CANDLES = create("big_soul_candles")
    val NETHER_BRICKS = create("nether_bricks")
    val POLISHED_NETHER_BRICKS = create("polished_nether_bricks")
    val CRACKED_NETHER_BRICKS = create("cracked_nether_bricks")
    val CRAFTS_GREY_NETHER_BRICKS = create("crafts_grey_nether_bricks")
    val LEAF_PILES = create("leaf_piles")
    fun create(id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, id(id))
}