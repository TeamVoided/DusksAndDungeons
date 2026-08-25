package org.teamvoided.dusks_and_dungeons.data.registry

import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.DnDRegistries
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrownItemDefinition
import org.teamvoided.dusks_and_dungeons.util.key

object DnDThrownItemDefinitions {

    val EMPTY = key("empty")
    val BRICKS = key("bricks")

    fun key(id: String): ResourceKey<ThrownItemDefinition> = DnDRegistries.THROWN_ITEM_DEFINITION.key(id(id))

}