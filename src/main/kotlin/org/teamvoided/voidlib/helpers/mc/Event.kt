package org.teamvoided.voidlib.helpers.mc

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.npc.VillagerTrades
import net.minecraft.world.entity.npc.VillagerTrades.EmeraldForItems
import net.minecraft.world.item.Item

/*
     Put all this in Voidlib
*/

//Trader
fun MutableList<VillagerTrades.ItemListing>.add1for1(item: ItemLike, maxUses: Int) =
    addSell(item.asItem(), 1, 1, maxUses)

fun MutableList<VillagerTrades.ItemListing>.addSell(
    item: ItemLike, price: Int, count: Int, maxUses: Int, experience: Int = 1
) = add(VillagerTrades.ItemsForEmeralds(item.asItem(), price, count, maxUses, experience))

fun MutableList<VillagerTrades.ItemListing>.buyFor1(item: ItemLike, price: Int, maxUses: Int, experience: Int = 1) =
    add(EmeraldForItems(item, price, maxUses, experience))


// Loot Table modification
fun addToExistingPools(tableBuilder: LootTable.Builder, table: ResourceLocation): LootTable.Builder =
    tableBuilder.modifyPools { it.add(addTable(table)).build() }

fun addNewPool(tableBuilder: LootTable.Builder, table: ResourceLocation): LootTable.Builder =
    tableBuilder.pool(LootPool.lootPool().add(addTable(table)).build())

fun addTable(table: ResourceLocation): LootPoolSingletonContainer.Builder<*> =
    NestedLootTable.lootTableReference(ResourceKey.create(Registries.LOOT_TABLE, table))


// Compositing
fun compost(item: ItemLike, chance: Double = 1.0) = CompostingChanceRegistry.INSTANCE.add(item, chance.toFloat())

// Fuel Registry
fun fuel(item: ItemLike, time: Int) = FuelRegistry.INSTANCE.add(item, time)
fun fuel(tag: TagKey<Item>, time: Int) = FuelRegistry.INSTANCE.add(tag, time)
fun removeFuel(item: ItemLike) = FuelRegistry.INSTANCE.remove(item)
fun removeFuel(tag: TagKey<Item>) = FuelRegistry.INSTANCE.remove(tag)
