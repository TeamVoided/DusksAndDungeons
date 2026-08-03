package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.world.item.Item
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDItemTags {
    val ITEM_TAGS = mutableSetOf<TagKey<Item>>()

    val HOLLOW_LOGS = create("hollow_logs")
    val HOLLOW_LOGS_THAT_BURN = create("hollow_logs_that_burn")

    // wood
    val WOODEN_WALLS = create("wooden_walls")

    val WOOD_STAIRS = create("wood_stairs")
    val WOOD_SLABS = create("wood_slabs")
    val WOOD_WALLS = create("wood_walls")

    val WOOD_STAIRS_THAT_BURN = create("wood_stairs_that_burn")
    val WOOD_SLABS_THAT_BURN = create("wood_slabs_that_burn")
    val WOOD_WALLS_THAT_BURN = create("wood_walls_that_burn")

    val CASCADE_LOGS = create("cascade_logs")
    val LEAF_PILES = create("leaf_piles")
    val LOG_PILES = create("log_piles")
    val LOG_PILES_THAT_BURN = create("log_piles_that_burn")
    val FLOWERBEDS = create("flowerbeds")
    val VIVIONBEDS = create("vivionbeds")
    val SCARECROW_WOOD_ITEMS = create("scarecrow/wood")
    val SCARECROW_BALE_ITEMS = create("scarecrow/bale")
    val SCARECROW_HEAD_ITEMS = create("scarecrow/head")
    val SCARECROW_CLOTHES_ITEMS = create("scarecrow/clothes")
    val BIG_CANDLES = create("big_candles")
    val SOUL_CANDLES = create("soul_candles")
    val BIG_SOUL_CANDLES = create("big_soul_candles")
    val GRAVESTONES = create("gravestones")
    val SMALL_GRAVESTONES = create("small_gravestones")
    val HEADSTONES = create("headstones")
    val NETHER_BRICKS = create("nether_bricks")
    val POLISHED_NETHER_BRICKS = create("polished_nether_bricks")
    val CRACKED_NETHER_BRICKS = create("cracked_nether_bricks")
    val CRAFTS_WARPED_NETHER_BRICKS = create("crafts_warped_nether_bricks")
    val CRAFTS_ASHEN_NETHER_BRICKS = create("crafts_ashen_nether_bricks")

    val CORN_STORAGE = create("storage_blocks/corn")
    val PUMPKINS = create("pumpkins")
    val CARVED_PUMPKINS = create("carved_pumpkins")
    val GLOWING_PUMPKINS = create("glowing_pumpkins")
    val SMALL_PUMPKINS = create("small_pumpkins")
    val SMALL_CARVED_PUMPKINS = create("small_carved_pumpkins")
    val SMALL_GLOWING_PUMPKINS = create("small_glowing_pumpkins")

    val CANDELABRAS = create("candelabras")
    val SOUL_CANDELABRAS = create("soul_candelabras")

    val PUMPKIN_SEEDS = create("pumpkin_seeds")

    val RACCOON_FOOD = create("raccoon_food")

    fun create(id: String): TagKey<Item> {
        val regTag = TagKey.create(Registries.ITEM, id(id))
        ITEM_TAGS.add(regTag)
        return regTag
    }
}
