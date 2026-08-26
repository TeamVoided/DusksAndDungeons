package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.tag

object DnDBlockTags {

    // Wood
    @JvmField
    val WOODEN_WALLS = key("wooden_walls")

    val WOOD_STAIRS = key("wood_stairs")
    val WOOD_STAIRS_THAT_BURN = key("wood_stairs_that_burn")
    val WOOD_SLABS = key("wood_slabs")
    val WOOD_SLABS_THAT_BURN = key("wood_slabs_that_burn")
    val WOOD_WALLS = key("wood_walls")
    val WOOD_WALLS_THAT_BURN = key("wood_walls_that_burn")

    val HOLLOW_LOGS = key("hollow_logs")
    val HOLLOW_LOGS_THAT_BURN = key("hollow_logs_that_burn")

    val LOG_PILES = key("log_piles")
    val LOG_PILES_THAT_BURN = key("log_piles_that_burn")

    val PLANK_WALLS = key("plank_walls")
    val PLANK_WALLS_THAT_BURN = key("plank_walls_that_burn")

    val BOOKSHELVES = key("bookshelves")
    val BOOKSHELVES_THAT_BURN = key("bookshelves_that_burn")

    val FLAMMABLE_PLANKS = key("flammable/planks")
    val FLAMMABLE_LOGS = key("flammable/logs")
    val FLAMMABLE_LEAVES = key("flammable/leaves")

    val CASCADE_LOGS = key("cascade_logs")
    val SYPIA_LOGS = key("sypia_logs")
    val VERDANT_LOGS = key("verdant_logs")

    // Flora
    val LEAF_PILES = key("leaf_piles")
    val FLOWERBEDS = key("flowerbeds")
    val VIVIONBEDS = key("vivionbeds")

    val PUMPKINS_BLOCKS = pumpkins("blocks")
    val PUMPKINS_EIGHTHS = pumpkins("eighths")

    val PUMPKINS_STEMS = pumpkins("stems")

    val PUMPKINS = pumpkins("pumpkins")
    val PUMPKINS_CARVED = pumpkins("carved")
    val PUMPKINS_GLOWING = pumpkins("glowing")

    val PUMPKINS_SMALL = pumpkins("small")
    val PUMPKINS_SMALL_CARVED = pumpkins("small_carved")
    val PUMPKINS_SMALL_GLOWING = pumpkins("small_glowing")

    // Supports
    val SUPPORTS_VIVIONBED = supports("vivionbed")
    val SUPPORTS_WARPED_NETHER_WART = supports("warped_nether_wart")
    val SUPPORTS_MOONBERRY = supports("moonberry")

    // Categories
    val BIG_CANDLES = key("big_candles")
    val SOUL_CANDLES = key("soul_candles")
    val BIG_SOUL_CANDLES = key("big_soul_candles")
    val CANDELABRAS = key("candelabras")
    val SOUL_CANDELABRAS = key("soul_candelabras")

    val GRAVESTONES = key("gravestones")
    val SMALL_GRAVESTONES = key("small_gravestones")
    val HEADSTONES = key("headstones")
    val CARPET_PLATES = key("carpet_plates")
    val CARPET_PLATES_WOOL = key("carpet_plates/wool")

    // Nether Bricks
    val NETHER_BRICKS = key("nether_bricks")
    val POLISHED_NETHER_BRICKS = key("polished_nether_bricks")
    val CRACKED_NETHER_BRICKS = key("cracked_nether_bricks")

    // Feature
    val REPLACEABLE_OR_DIRT = key("replaceable_or_dirt")
    val VEGETATION_REPLACEABLE = key("vegetation_replaceable")
    val FARMLAND_PLACES_UNDER = key("farmland_places_under")
    val GOLD_MUSH_GROW_ON = key("golden_mushroom_grows_on")
    val LEAF_PILES_PLACE_ON = key("leaf_piles_place_on")
    val PUMPKIN_PATCH_PLACE_ON = key("pumpkin_patch_place_on")

    // Misc
    val EMPTY = key("empty")
    val ICE_BLOCK_TRANSLUCENT = key("ice_block_translucent")
    val THROWN_BRICK_BREAK = key("thrown_brick_break")
    val SCULK_SPREAD_SEARCH = key("sculk_spread_search")


    fun supports(id: String) = key("supports/$id")
    fun pumpkins(id: String) = key("pumpkins/$id")

    fun key(id: String) = Registries.BLOCK.tag(id(id))

}