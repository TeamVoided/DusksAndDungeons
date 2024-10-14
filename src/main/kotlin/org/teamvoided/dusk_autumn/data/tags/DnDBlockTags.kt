package org.teamvoided.dusk_autumn.data.tags

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.DusksAndDungeons.id

object DnDBlockTags {
    val HOLLOW_LOGS = create("hollow_logs")
    val HOLLOW_LOGS_THAT_BURN = create("hollow_logs_that_burn")
    val CASCADE_LOGS = create("cascade_logs")
    val FLAMMABLE_PLANKS = create("flammable/planks")
    val FLAMMABLE_LOGS = create("flammable/logs")
    val FLAMMABLE_LEAVES = create("flammable/leaves")
    val WOOD_STAIRS = create("wood_stairs")
    val WOOD_STAIRS_THAT_BURN = create("wood_stairs_that_burn")
    val WOOD_SLABS = create("wood_slabs")
    val WOOD_SLABS_THAT_BURN = create("wood_slabs_that_burn")
    @JvmField
    val WOODEN_WALLS = create("wooden_walls")
    val WOODEN_WALLS_THAT_BURN = create("wooden_walls_that_burn")
    val LOG_PILES = create("log_piles")
    val LOG_PILES_THAT_BURN = create("log_piles_that_burn")
    val LEAF_PILES = create("leaf_piles")
    val LEAF_PILES_PLACE_ON = create("leaf_piles_place_on")
    val FLOWERBEDS = create("flowerbeds")
    val VIVIONBEDS = create("vivionbeds")
    val VIVIONBED_PLACEABLE = create("vivionbed_placeable")
    val ICE_BLOCK_TRANSLUCENT = create("ice_block_translucent")
    val BIG_CANDLES = create("big_candles")
    val SOUL_CANDLES = create("soul_candles")
    val BIG_SOUL_CANDLES = create("big_soul_candles")
    val NETHER_BRICKS = create("nether_bricks")
    val POLISHED_NETHER_BRICKS = create("polished_nether_bricks")
    val CRACKED_NETHER_BRICKS = create("cracked_nether_bricks")
    val WARPED_NETHER_WART_PLACEABLE = create("warped_nether_wart_placeable")
    val CHILL_CHARGE_AFFECTS = create("chill_charge_affects")
    val FARMLAND_PLACES_UNDER = create("farmland_places_under")
    val MOONBERRY_CAN_PLACE_ON = create("moonberry_can_place_on")
    val REPLACEABLE_OR_DIRT = create("replaceable_or_dirt")
    val FALLEN_TREE_REPLACEABLE = create("fallen_tree_replaceable")

    @JvmField
    val BLOCKS_CANNOT_CONNECT_TO = create("blocks_cannot_connect_to")

    fun create(id: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id(id))
}