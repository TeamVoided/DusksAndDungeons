package org.teamvoided.dusks_and_dungeons.data.tags.c

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags

object CItemTags {

    @JvmField
    val SCAFFOLDING = c("scaffolding")

    val SANDSTONE_WALLS = c("sandstone/walls")
    val UNCOLORED_SANDSTONE_WALLS = c("sandstone/uncolored_walls")
    val RED_SANDSTONE_WALLS = c("sandstone/red_walls")

    val TINTED_SANDS = c("sands/tinted")
    val TINTED_SANDSTONE_BLOCKS = c("sandstone/tinted_blocks")
    val TINTED_SANDSTONE_STAIRS = c("sandstone/tinted_stairs")
    val TINTED_SANDSTONE_SLABS = c("sandstone/tinted_slabs")
    val TINTED_SANDSTONE_WALLS = c("sandstone/tinted_walls")

    val CORN_STORAGE = c("storage_blocks/corn")

    val GLASS_PANES_TINTED = c("glass_panes/tinted")

    val SEEDS_CORN = c("seeds/corn")

    val DRINKS_CORN_SYRUP = c("drinks/corn_syrup")

    fun c(id: String): TagKey<Item> = DnDItemTags.tag(id("c", id))

}