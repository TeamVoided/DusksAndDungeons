package org.teamvoided.dusk_autumn.data.tags

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskBlockTags {
    val CASCADE_LOGS = create("cascade_logs")
    val LOG_PILES = create("log_piles")
    val LEAF_PILES = create("leaf_piles")
    val LEAF_PILES_PLACE_ON = create("leaf_piles_place_on")
    val BIG_CANDLES = create("big_candles")
    val SOUL_CANDLES = create("soul_candles")
    val BIG_SOUL_CANDLES = create("big_soul_candles")
    val FARMLAND_PLACES_UNDER = create("farmland_places_under")
    val MOONBERRY_CAN_PLACE_ON = create("moonberry_can_place_on")
    val REPLACEABLE_OR_DIRT = create("replaceable_or_dirt")

    fun create(id: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id(id))
}