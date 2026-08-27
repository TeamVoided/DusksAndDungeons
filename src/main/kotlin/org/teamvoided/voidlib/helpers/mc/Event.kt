package org.teamvoided.voidlib.helpers.mc

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.npc.VillagerTrades
import net.minecraft.world.entity.npc.VillagerTrades.EmeraldForItems
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer
import net.minecraft.world.level.storage.loot.entries.NestedLootTable

/*
     Put all this in Voidlib
*/

//Trader
typealias TradeList = MutableList<VillagerTrades.ItemListing>

fun TradeList.add1for1(item: ItemLike, maxUses: Int) = addSell(item, 1, 1, maxUses)

fun TradeList.addSell(item: ItemLike, price: Int, count: Int, maxUses: Int, experience: Int = 1) {
    add(VillagerTrades.ItemsForEmeralds(item.asItem(), price, count, maxUses, experience))
}

fun TradeList.buyFor1(item: ItemLike, price: Int, maxUses: Int, experience: Int = 1) {
    add(EmeraldForItems(item, price, maxUses, experience))
}

// Loot Table modification
fun addToExistingPools(tableBuilder: LootTable.Builder, key: ResourceKey<LootTable>): LootTable.Builder {
    return tableBuilder.modifyPools { it.add(refTable(key)).build() }
}

fun addNewPool(tableBuilder: LootTable.Builder, key: ResourceKey<LootTable>): LootTable.Builder {
    return tableBuilder.pool(LootPool.lootPool().add(refTable(key)).build())
}

fun refTable(key: ResourceKey<LootTable>): LootPoolSingletonContainer.Builder<*> {
    return NestedLootTable.lootTableReference(key)
}

// Compositing
fun compost(item: ItemLike, chance: Double = 1.0) = CompostingChanceRegistry.INSTANCE.add(item, chance.toFloat())

// Fuel Registry
fun fuel(item: ItemLike, time: Int) = FuelRegistry.INSTANCE.add(item, time)
fun fuel(tag: TagKey<Item>, time: Int) = FuelRegistry.INSTANCE.add(tag, time)
fun removeFuel(item: ItemLike) = FuelRegistry.INSTANCE.remove(item)
fun removeFuel(tag: TagKey<Item>) = FuelRegistry.INSTANCE.remove(tag)
