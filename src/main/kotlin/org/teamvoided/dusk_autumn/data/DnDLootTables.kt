package org.teamvoided.dusk_autumn.data

import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DnDLootTables {
    private val LOOT_TABLES: MutableSet<Identifier> = mutableSetOf()

    val SNIFFER_ADD_MOONBERRY: Identifier = register("inject/add_moonberries")
    val BARTERING_ADD_VIVIONS: Identifier = register("inject/add_vivions")
    val COOL_CHEST: Identifier = register("chests/cool_chest")
    val COOL_ARCHAEOLOGY: Identifier = register("archaeology/cool_archaeology")

    private fun register(id: String) = register(id(id))
    private fun register(id: Identifier): Identifier {
        if (LOOT_TABLES.add(id)) return id
        throw IllegalArgumentException("$id is already a registered built-in loot table")
    }
}