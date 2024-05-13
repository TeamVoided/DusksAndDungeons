package org.teamvoided.dusk_autumn.modules

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id

object SnifferInjection {
    private val ADD_MOONBERRIES = RegistryKey.of(RegistryKeys.LOOT_TABLE, id("inject/add_moonberries"))
    fun init() {
        LootTableEvents.MODIFY.register { id, builder, _ ->
            lootLoad(id.value, builder)
        }
    }

    private fun lootLoad(id: Identifier, builder: LootTable.Builder) {
        if (id == LootTables.SNIFFER_DIGGING_GAMEPLAY)
            builder.pool(LootPool.builder().with(LootTableEntry.method_428(ADD_MOONBERRIES)))
    }
}
