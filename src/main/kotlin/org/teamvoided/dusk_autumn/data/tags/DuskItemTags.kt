package org.teamvoided.dusk_autumn.data.tags

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.init.DuskBlocks

object DuskItemTags {
    val ITEM_TAGS = mutableSetOf<TagKey<Item>>()

    val CASCADE_LOGS = create("cascade_logs")
    val BIG_CANDLES = create("big_candles")
    val SOUL_CANDLES = create("soul_candles")
    val BIG_SOUL_CANDLES = create("big_soul_candles")
    val NETHER_BRICKS = create("nether_bricks")
    val POLISHED_NETHER_BRICKS = create("polished_nether_bricks")
    val CRACKED_NETHER_BRICKS = create("cracked_nether_bricks")
    val CRAFTS_ASHEN_NETHER_BRICKS = create("crafts_ashen_nether_bricks")
    val LEAF_PILES = create("leaf_piles")

    fun create(id: String): TagKey<Item> {
        val regTag = TagKey.of(RegistryKeys.ITEM, id(id))
        ITEM_TAGS.add(regTag)
        return regTag
    }
}