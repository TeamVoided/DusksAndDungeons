package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.world.level.block.Block
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDBlockTags {

    val HOLLOW_LOGS = create("hollow_logs")
    val HOLLOW_LOGS_THAT_BURN = create("hollow_logs_that_burn")
    val CASCADE_LOGS = create("cascade_logs")
    val VERDANT_LOGS = create("verdant_logs")
    val FLAMMABLE_PLANKS = create("flammable/planks")
    val FLAMMABLE_LOGS = create("flammable/logs")
    val FLAMMABLE_LEAVES = create("flammable/leaves")

    @JvmField
    val WOODEN_WALLS = create("wooden_walls")

    // Wood Block Set
    val WOOD_STAIRS = create("wood_stairs")
    val WOOD_SLABS = create("wood_slabs")
    val WOOD_WALLS = create("wood_walls")

    val WOOD_STAIRS_THAT_BURN = create("wood_stairs_that_burn")
    val WOOD_SLABS_THAT_BURN = create("wood_slabs_that_burn")
    val WOOD_WALLS_THAT_BURN = create("wood_walls_that_burn")

    // Other stuff
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
    val GRAVESTONES = create("gravestones")
    val SMALL_GRAVESTONES = create("small_gravestones")
    val HEADSTONES = create("headstones")
    val NETHER_BRICKS = create("nether_bricks")
    val POLISHED_NETHER_BRICKS = create("polished_nether_bricks")
    val CRACKED_NETHER_BRICKS = create("cracked_nether_bricks")


    val WARPED_NETHER_WART_PLACEABLE = create("warped_nether_wart_placeable")
    val CHILL_CHARGE_AFFECTS = create("chill_charge_affects")
    val FARMLAND_PLACES_UNDER = create("farmland_places_under")
    val MOONBERRY_CAN_PLACE_ON = create("moonberry_can_place_on")
    val REPLACEABLE_OR_DIRT = create("replaceable_or_dirt")
    val VEGETATION_REPLACEABLE = create("vegetation_replaceable")
    val PUMPKIN_PATCH_PLACE_ON = create("pumpkin_patch_place_on")

    val CORN_STORAGE = create("storage_blocks/corn")
    val PUMPKIN_STEMS = create("pumpkins/stems")
    val PUMPKIN_BLOCKS = create("pumpkins/blocks")
    val PUMPKIN_EIGHTHS = create("pumpkins/small")
    val PUMPKINS = create("pumpkins/pumpkins")
    val CARVED_PUMPKINS = create("pumpkins/carved_pumpkins")
    val GLOWING_PUMPKINS = create("pumpkins/glowing_pumpkins")
    val SMALL_PUMPKINS = create("pumpkins/small_pumpkins")
    val SMALL_CARVED_PUMPKINS = create("pumpkins/small_carved_pumpkins")
    val SMALL_GLOWING_PUMPKINS = create("pumpkins/small_glowing_pumpkins")

    val CANDELABRAS = create("candelabras")
    val SOUL_CANDELABRAS = create("soul_candelabras")

    fun create(id: String): TagKey<Block> = TagKey.create(Registries.BLOCK, id(id))
}
