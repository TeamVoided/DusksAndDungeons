package org.teamvoided.dusks_and_dungeons.data.tags.c

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object CBlockTags {

    @JvmField
    val SCAFFOLDING = c("scaffolding")

    fun c(id: String): TagKey<Block> = TagKey.create(Registries.BLOCK, id("c", id))

}