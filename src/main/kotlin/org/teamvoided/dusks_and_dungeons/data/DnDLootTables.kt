package org.teamvoided.dusks_and_dungeons.data

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.storage.loot.LootTable
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDLootTables {

    val LOOT_TABLES = mutableSetOf<ResourceKey<LootTable>>()

    val SNIFFER_ADD_MOONBERRY = key("inject/add_moonberries")
    val BARTERING_ADD_VIVIONS = key("inject/add_vivions")
    val SIMPLE_DUNGEON_ADD_SPOOKY = key("inject/add_spooky")
    val ADD_DND_SEEDS = key("inject/add_dnd_seeds")

    val COOL_CHEST = key("chests/cool_chest")
    val COOL_ARCHAEOLOGY = key("archaeology/cool_archaeology")

    fun key(id: String) = key(id(id))
    fun key(id: ResourceLocation): ResourceKey<LootTable> {

        val key = Registries.LOOT_TABLE.key(id)

        if (LOOT_TABLES.add(key)) {
            return key
        }
        throw IllegalArgumentException("$id is already a registered built-in loot table")
    }

}