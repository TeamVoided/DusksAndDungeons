package org.teamvoided.dusks_and_dungeons.data.tags.c

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object CBlockTags {

    @JvmField
    val SCAFFOLDING = c("scaffolding")

    val SANDSTONE_WALLS = c("sandstone/walls")
    val UNCOLORED_SANDSTONE_WALLS = c("sandstone/uncolored_walls")
    val RED_SANDSTONE_WALLS = c("sandstone/red_walls")

    val GLASS_PANES_TINTED = c("glass_panes/tinted")

    fun c(id: String): TagKey<Block> = TagKey.create(Registries.BLOCK, id("c", id))

}