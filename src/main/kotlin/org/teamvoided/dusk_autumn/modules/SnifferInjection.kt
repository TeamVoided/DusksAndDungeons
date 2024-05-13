package org.teamvoided.dusk_autumn.modules

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id

object SnifferInjection {
    private val ADD_MOONBERRIES = id("inject/add_moonberries")
    fun init() {
        LootTableEvents.MODIFY.register { _, _, id, supplier, _ ->
            lootLoad(id, supplier)
        }
    }

    private fun lootLoad(id: Identifier, builder: LootTable.Builder) {
        if (id == LootTables.SNIFFER_DIGGING_GAMEPLAY)
            builder.pool(LootPool.builder().with(LootTableEntry.builder(ADD_MOONBERRIES)))
    }
}
