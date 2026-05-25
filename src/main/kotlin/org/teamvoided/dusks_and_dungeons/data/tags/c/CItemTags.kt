package org.teamvoided.dusks_and_dungeons.data.tags.c

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object CItemTags {

    @JvmField
    val SCAFFOLDING = c("scaffolding")

    fun c(id: String): TagKey<Item> = TagKey.create(Registries.ITEM, id("c", id))

}