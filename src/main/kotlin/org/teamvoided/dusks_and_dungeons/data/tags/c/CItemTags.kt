package org.teamvoided.dusks_and_dungeons.data.tags.c

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags

object CItemTags {

    @JvmField
    val SCAFFOLDING = c("scaffolding")

    fun c(id: String): TagKey<Item> = DnDItemTags.tag(id("c", id))

}