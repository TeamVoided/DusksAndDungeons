package org.teamvoided.dusk_autumn.modules

import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumn.data.DnDLootTables

object SimpleDungeonInjection {
    private val ADD_SPOOK: RegistryKey<LootTable> =
        RegistryKey.of(RegistryKeys.LOOT_TABLE, DnDLootTables.SIMPLE_DUNGEON_ADD_SPOOKY)

    fun init() {
        LootTableEvents.MODIFY.register { key, tableBuilder, _, _ ->
            if (key == LootTables.SIMPLE_DUNGEON_CHEST)
                tableBuilder.pool(LootPool.builder().with(LootTableEntry.method_428(ADD_SPOOK)).build())
        }
    }
}
