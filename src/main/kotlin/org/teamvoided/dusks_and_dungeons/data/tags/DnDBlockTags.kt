package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.tag

object DnDBlockTags {

    val HOLLOW_LOGS = key("hollow_logs")
    val HOLLOW_LOGS_THAT_BURN = key("hollow_logs_that_burn")

    val CASCADE_LOGS = key("cascade_logs")
    val SYPIA_LOGS = key("sypia_logs")
    val VERDANT_LOGS = key("verdant_logs")

    val FLAMMABLE_PLANKS = key("flammable/planks")
    val FLAMMABLE_LOGS = key("flammable/logs")
    val FLAMMABLE_LEAVES = key("flammable/leaves")

    @JvmField
    val WOODEN_WALLS = key("wooden_walls")

    val PLANK_WALLS = key("plank_walls")
    val PLANK_WALLS_THAT_BURN = key("plank_walls_that_burn")

    // Wood Block Set
    val WOOD_STAIRS = key("wood_stairs")
    val WOOD_SLABS = key("wood_slabs")
    val WOOD_WALLS = key("wood_walls")

    val WOOD_STAIRS_THAT_BURN = key("wood_stairs_that_burn")
    val WOOD_SLABS_THAT_BURN = key("wood_slabs_that_burn")
    val WOOD_WALLS_THAT_BURN = key("wood_walls_that_burn")

    // Other stuff
    val LOG_PILES = key("log_piles")
    val LOG_PILES_THAT_BURN = key("log_piles_that_burn")
    val LEAF_PILES = key("leaf_piles")
    val LEAF_PILES_PLACE_ON = key("leaf_piles_place_on")
    val FLOWERBEDS = key("flowerbeds")
    val VIVIONBEDS = key("vivionbeds")
    val VIVIONBED_PLACEABLE = po("vivionbed")
    val ICE_BLOCK_TRANSLUCENT = key("ice_block_translucent")
    val BIG_CANDLES = key("big_candles")
    val SOUL_CANDLES = key("soul_candles")
    val BIG_SOUL_CANDLES = key("big_soul_candles")
    val GRAVESTONES = key("gravestones")
    val SMALL_GRAVESTONES = key("small_gravestones")
    val HEADSTONES = key("headstones")
    val NETHER_BRICKS = key("nether_bricks")
    val POLISHED_NETHER_BRICKS = key("polished_nether_bricks")
    val CRACKED_NETHER_BRICKS = key("cracked_nether_bricks")


    val WARPED_NETHER_WART_PLACEABLE = po("warped_nether_wart")
    val CHILL_CHARGE_AFFECTS = key("chill_charge_affects")
    val FARMLAND_PLACES_UNDER = key("farmland_places_under")
    val MOONBERRY_CAN_PLACE_ON = bf("moonberry_can_place_on")
    val REPLACEABLE_OR_DIRT = key("replaceable_or_dirt")
    val VEGETATION_REPLACEABLE = key("vegetation_replaceable")
    val PUMPKIN_PATCH_PLACE_ON = key("pumpkin_patch_place_on")
    val GOLD_MUSH_GROW_ON = bf("golden_mushroom_grows_on")

    val CORN_STORAGE = bs("corn")
    val PUMPKIN_STEMS = key("pumpkins/stems")
    val PUMPKIN_BLOCKS = key("pumpkins/blocks")
    val PUMPKIN_EIGHTHS = key("pumpkins/small")
    val PUMPKINS = key("pumpkins/pumpkins")
    val CARVED_PUMPKINS = key("pumpkins/carved_pumpkins")
    val GLOWING_PUMPKINS = key("pumpkins/glowing_pumpkins")
    val SMALL_PUMPKINS = key("pumpkins/small_pumpkins")
    val SMALL_CARVED_PUMPKINS = key("pumpkins/small_carved_pumpkins")
    val SMALL_GLOWING_PUMPKINS = key("pumpkins/small_glowing_pumpkins")

    val CANDELABRAS = key("candelabras")
    val SOUL_CANDELABRAS = key("soul_candelabras")

    val THROWN_BRICK_BREAK = key("thrown_brick_break")

    val EMPTY = key("empty")

    fun po(id: String) = key("placeable_on/$id")
    fun bf(id: String) = key("block_feature/$id")
    fun bs(id: String) = key("storage_blocks/$id")
    //fun bg(id: String) = key("block_group/$id")
    fun key(id: String) = Registries.BLOCK.tag(id(id))
}
