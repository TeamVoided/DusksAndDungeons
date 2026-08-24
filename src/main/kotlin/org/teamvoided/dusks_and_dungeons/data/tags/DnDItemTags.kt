package org.teamvoided.dusks_and_dungeons.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.tags.c.CItemTags
import org.teamvoided.dusks_and_dungeons.util.tag

object DnDItemTags {
    val ITEM_TAGS = mutableSetOf<TagKey<Item>>()

    val HOLLOW_LOGS = tag("hollow_logs")
    val HOLLOW_LOGS_THAT_BURN = tag("hollow_logs_that_burn")

    // wood
    val WOODEN_WALLS = tag("wooden_walls")

    val PLANK_WALLS = tag("plank_walls")
    val PLANK_WALLS_THAT_BURN = tag("plank_walls_that_burn")

    val WOOD_STAIRS = tag("wood_stairs")
    val WOOD_SLABS = tag("wood_slabs")
    val WOOD_WALLS = tag("wood_walls")

    val WOOD_STAIRS_THAT_BURN = tag("wood_stairs_that_burn")
    val WOOD_SLABS_THAT_BURN = tag("wood_slabs_that_burn")
    val WOOD_WALLS_THAT_BURN = tag("wood_walls_that_burn")

    val CASCADE_LOGS = tag("cascade_logs")
    val SYPIA_LOGS = tag("sypia_logs")
    val VERDANT_LOGS = tag("verdant_logs")
    val LEAF_PILES = tag("leaf_piles")
    val LOG_PILES = tag("log_piles")
    val LOG_PILES_THAT_BURN = tag("log_piles_that_burn")
    val FLOWERBEDS = tag("flowerbeds")
    val VIVIONBEDS = tag("vivionbeds")
    val SCARECROW_WOOD_ITEMS = tag("scarecrow/wood")
    val SCARECROW_BALE_ITEMS = tag("scarecrow/bale")
    val SCARECROW_HEAD_ITEMS = tag("scarecrow/head")
    val SCARECROW_CLOTHES_ITEMS = tag("scarecrow/clothes")
    val BIG_CANDLES = tag("big_candles")
    val SOUL_CANDLES = tag("soul_candles")
    val BIG_SOUL_CANDLES = tag("big_soul_candles")
    val GRAVESTONES = tag("gravestones")
    val SMALL_GRAVESTONES = tag("small_gravestones")
    val HEADSTONES = tag("headstones")
    val NETHER_BRICKS = tag("nether_bricks")
    val POLISHED_NETHER_BRICKS = tag("polished_nether_bricks")
    val CRACKED_NETHER_BRICKS = tag("cracked_nether_bricks")
    val CRAFTS_WARPED_NETHER_BRICKS = tag("crafts_warped_nether_bricks")
    val CRAFTS_ASHEN_NETHER_BRICKS = tag("crafts_ashen_nether_bricks")

    val CORN_STORAGE = tag("storage_blocks/corn")
    val PUMPKINS = tag("pumpkins")
    val CARVED_PUMPKINS = tag("carved_pumpkins")
    val GLOWING_PUMPKINS = tag("glowing_pumpkins")
    val SMALL_PUMPKINS = tag("small_pumpkins")
    val SMALL_CARVED_PUMPKINS = tag("small_carved_pumpkins")
    val SMALL_GLOWING_PUMPKINS = tag("small_glowing_pumpkins")

    val CANDELABRAS = tag("candelabras")
    val SOUL_CANDELABRAS = tag("soul_candelabras")

    val PUMPKIN_SEEDS = tag("pumpkin_seeds")

    val BLACKSTONE_TOOL_MATERIALS = tag("blackstone_tool_materials")

    val RACCOON_FOOD = tag("raccoon_food")

    val CARPET_PLATES = tag("carpet_plates")
    val CARPET_PLATES_WOOL = tag("carpet_plates/wool")

    val EMPTY = tag("empty")

    init {
        // here to init all item tags
        CItemTags.SCAFFOLDING
    }

    fun tag(id: String) = tag(id(id))

    fun tag(id: ResourceLocation): TagKey<Item> {
        val tag = Registries.ITEM.tag(id)
        ITEM_TAGS.add(tag)
        return tag
    }

}
