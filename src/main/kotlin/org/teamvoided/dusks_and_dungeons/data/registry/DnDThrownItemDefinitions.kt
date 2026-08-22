package org.teamvoided.dusks_and_dungeons.data.registry

import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys
import org.teamvoided.dusks_and_dungeons.util.key

object DnDThrownItemDefinitions {

    val BRICKS = key("bricks")

    fun key(id: String) = DnDRegistryKeys.THROWN_ITEM_DEFINITION.key(id(id))

}