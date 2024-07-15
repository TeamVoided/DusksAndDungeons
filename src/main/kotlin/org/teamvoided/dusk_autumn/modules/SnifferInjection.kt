package org.teamvoided.dusk_autumn.modules

import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumn.DuskAutumns.id

object SnifferInjection {
    val ADD_MOONBERRIES = RegistryKey.of(RegistryKeys.LOOT_TABLE, id("inject/add_moonberries"))

    fun init() {
        LootTableEvents.MODIFY.register { key, tableBuilder, _, registries ->
            if (key == LootTables.SNIFFER_DIGGING_GAMEPLAY)
                tableBuilder.modifyPools { it.with(LootTableEntry.method_428(ADD_MOONBERRIES)).build() }
        }
    }
}
