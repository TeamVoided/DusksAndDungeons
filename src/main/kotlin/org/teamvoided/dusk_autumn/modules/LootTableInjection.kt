package org.teamvoided.dusk_autumn.modules

import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumn.data.DnDLootTables

object LootTableInjection {
    private val ADD_VIVIONS: RegistryKey<LootTable> =
        RegistryKey.of(RegistryKeys.LOOT_TABLE, DnDLootTables.BARTERING_ADD_VIVIONS)
    private val ADD_MOONBERRIES: RegistryKey<LootTable> =
        RegistryKey.of(RegistryKeys.LOOT_TABLE, DnDLootTables.SNIFFER_ADD_MOONBERRY)
    private val ADD_SPOOK: RegistryKey<LootTable> =
        RegistryKey.of(RegistryKeys.LOOT_TABLE, DnDLootTables.SIMPLE_DUNGEON_ADD_SPOOKY)

    fun init() {
        LootTableEvents.MODIFY.register { key, tableBuilder, _, _ ->
            when (key) {
                LootTables.PIGLIN_BARTERING_GAMEPLAY -> addToExistingPools(tableBuilder, ADD_VIVIONS)
                LootTables.SNIFFER_DIGGING_GAMEPLAY -> addToExistingPools(tableBuilder, ADD_MOONBERRIES)
                LootTables.SIMPLE_DUNGEON_CHEST -> addNewPool(tableBuilder, ADD_SPOOK)
            }
        }
    }

    private fun addToExistingPools(tableBuilder: LootTable.Builder, table: RegistryKey<LootTable>) {
        tableBuilder.modifyPools { it.with(LootTableEntry.method_428(table)).build() }
    }

    private fun addNewPool(tableBuilder: LootTable.Builder, table: RegistryKey<LootTable>) {
        tableBuilder.pool(LootPool.builder().with(LootTableEntry.method_428(table)).build())
    }

    //if (key == LootTables.PIGLIN_BARTERING_GAMEPLAY)
    //    tableBuilder.modifyPools { it.with(LootTableEntry.method_428(ADD_VIVIONS)).build() }
    //if (key == LootTables.SNIFFER_DIGGING_GAMEPLAY)
    //    tableBuilder.modifyPools { it.with(LootTableEntry.method_428(ADD_MOONBERRIES)).build() }
    //if (key == LootTables.SIMPLE_DUNGEON_CHEST)
    //    tableBuilder.pool(LootPool.builder().with(LootTableEntry.method_428(ADD_SPOOK)).build())
}