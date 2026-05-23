package org.teamvoided.dusks_and_dungeons.data

import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDLootTables {
    private val LOOT_TABLES: MutableSet<ResourceLocation> = mutableSetOf()

    val SNIFFER_ADD_MOONBERRY: ResourceLocation = register("inject/add_moonberries")
    val BARTERING_ADD_VIVIONS: ResourceLocation = register("inject/add_vivions")
    val SIMPLE_DUNGEON_ADD_SPOOKY: ResourceLocation = register("inject/add_spooky")
    val COOL_CHEST: ResourceLocation = register("chests/cool_chest")
    val COOL_ARCHAEOLOGY: ResourceLocation = register("archaeology/cool_archaeology")

    private fun register(id: String) = register(id(id))
    private fun register(id: ResourceLocation): ResourceLocation {
        if (LOOT_TABLES.add(id)) return id
        throw IllegalArgumentException("$id is already a registered built-in loot table")
    }
}