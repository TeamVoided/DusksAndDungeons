package org.teamvoided.dusk_autumn.modules

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id
import java.util.function.Consumer

object SnifferInjection {
    private val ADD_MOONBERRIES = id("inject/add_moonberries")
    fun init() {
        LootTableEvents.MODIFY.register { _, _, id, supplier, _ ->
            lootLoad(id, supplier::pool)
        }
    }

    private fun lootLoad(id: Identifier, addPool: Consumer<in LootPool.Builder>) {
        if (id == LootTables.SNIFFER_DIGGING_GAMEPLAY)
            addPool.accept(LootPool.builder().with(LootTableEntry.builder(ADD_MOONBERRIES)))

    }
}