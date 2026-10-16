package org.teamvoided.dusks_and_dungeons.init.events

import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.fabricmc.fabric.api.loot.v3.LootTableSource
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.BuiltInLootTables.*
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.CompositeEntryBase
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.config
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.data.DnDLootTables
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.*
import org.teamvoided.voidlib.helpers.mc.addNewPool
import org.teamvoided.voidlib.helpers.mc.addToExistingPools


fun initLootTables() {
    LootTableEvents.MODIFY.register(::modifyLootTables)
}

@Suppress("UNUSED_PARAMETER")
fun modifyLootTables(
    table: ResourceKey<LootTable>, builder: LootTable.Builder, src: LootTableSource, lookup: HolderLookup.Provider,
) {
    when (table) {
        PIGLIN_BARTERING -> addToExistingPools(builder, DnDLootTables.BARTERING_ADD_VIVIONS)
        SNIFFER_DIGGING -> addToExistingPools(builder, DnDLootTables.SNIFFER_ADD_MOONBERRY)
        SIMPLE_DUNGEON -> addNewPool(builder, DnDLootTables.SIMPLE_DUNGEON_ADD_SPOOKY)
        ABANDONED_MINESHAFT -> addNewPool(builder, DnDLootTables.ADD_DND_SEEDS)
        VILLAGE_PLAINS_HOUSE -> addNewPool(builder, DnDLootTables.ADD_CORN)
    }

    if (config.lootTablesThatHaveTintedGlassBottles.contains(table.location())) {
        tintPotions(builder, table)
    }
}


@Suppress("DEPRECATION")
fun tintPotions(builder: LootTable.Builder, table: ResourceKey<LootTable>) {
    val potion = DnDItems.TINTED_POTION.asItem().builtInRegistryHolder()
    val splash = DnDItems.TINTED_SPLASH_POTION.asItem().builtInRegistryHolder()
    val lingering = DnDItems.TINTED_LINGERING_POTION.asItem().builtInRegistryHolder()
    var modified = false
    builder.modifyPools { pool ->
        pool.modifyEntries { entry ->
            modified = replaceItem(
                entry,
                Items.POTION to potion,
                Items.SPLASH_POTION to splash,
                Items.LINGERING_POTION to lingering
            ) || modified
        }
    }
    if (modified && isDev()) {
        log.info("Added Tinted Potions in loot table: {}", table.location())
    }
}

fun replaceItem(entry: LootPoolEntryContainer, vararg replacements: Pair<Item, Holder<Item>>): Boolean {
    when (entry) {
        is CompositeEntryBase -> {
            var modified = false
            for (container in entry.getChildren()) {
                modified = replaceItem(container, *replacements) || modified
            }
            return modified
        }

        is LootItem -> {
            for ((old, new) in replacements) {
                if (ItemStack(entry.getItem()).`is`(old)) {
                    entry.setItem(new)
                    return true
                }

            }
        }
    }
    return false
}