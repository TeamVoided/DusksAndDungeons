@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.mc

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey

fun modifyTab(itemGroup: RegistryKey<ItemGroup>, modifyEntries: FabricItemGroupEntries.() -> Unit) =
    ItemGroupEvents.modifyEntriesEvent(itemGroup).register(modifyEntries)

fun ItemGroup.ItemStackCollector.addItems(vararg items: ItemConvertible) = this.addItems(items.toList())
fun ItemGroup.ItemStackCollector.addItems(list: Collection<ItemConvertible>) =
    this.addStacks(list.map { it.asItem().defaultStack })

fun ItemGroup.ItemStackCollector.addLists(vararg lists: Collection<ItemConvertible>) =
    this.addStacks(lists.flatMap { it.map { it.asItem().defaultStack } })

fun FabricItemGroupEntries.addAfter(item: ItemConvertible, list: Collection<ItemConvertible>) =
    this.addAfter(item.asItem(), list.map { it.asItem().defaultStack })

fun FabricItemGroupEntries.addBefore(item: ItemConvertible, list: Collection<ItemConvertible>) =
    this.addBefore(item.asItem(), list.map { it.asItem().defaultStack })
