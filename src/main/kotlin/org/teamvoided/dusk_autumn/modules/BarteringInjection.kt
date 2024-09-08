package org.teamvoided.dusk_autumn.modules

import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumn.data.DnDLootTables

object BarteringInjection {
    private val ADD_VIVIONS: RegistryKey<LootTable> =
        RegistryKey.of(RegistryKeys.LOOT_TABLE, DnDLootTables.BARTERING_ADD_VIVIONS)

    fun init() {
        LootTableEvents.MODIFY.register { key, tableBuilder, _, _ ->
            if (key == LootTables.PIGLIN_BARTERING_GAMEPLAY)
                tableBuilder.modifyPools { it.with(LootTableEntry.method_428(ADD_VIVIONS)).build() }
        }
    }
}
