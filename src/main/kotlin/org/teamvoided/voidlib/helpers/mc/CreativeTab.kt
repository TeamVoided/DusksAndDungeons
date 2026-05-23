@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.mc

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.world.level.ItemLike
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.resources.ResourceKey
import net.minecraft.network.chat.Component

fun modifyTab(itemGroup: ResourceKey<CreativeModeTab>, modifyEntries: FabricItemGroupEntries.() -> Unit) =
    ItemGroupEvents.modifyEntriesEvent(itemGroup).register(modifyEntries)

fun CreativeModeTab.Output.addItems(vararg items: ItemLike) = this.addItems(items.toList())
fun CreativeModeTab.Output.addItems(list: Collection<ItemLike>) =
    this.acceptAll(list.map { it.asItem().defaultInstance })

fun CreativeModeTab.Output.addLists(vararg lists: Collection<ItemLike>) =
    this.acceptAll(lists.flatMap { it.map { it.asItem().defaultInstance } })

fun FabricItemGroupEntries.addAfter(item: ItemLike, list: Collection<ItemLike>) =
    this.addAfter(item.asItem(), list.map { it.asItem().defaultInstance })

fun FabricItemGroupEntries.addBefore(item: ItemLike, list: Collection<ItemLike>) =
    this.addBefore(item.asItem(), list.map { it.asItem().defaultInstance })

fun CreativeModeTab.Builder.icon(item: ItemLike): CreativeModeTab.Builder = this.icon { ItemStack(item) }
fun CreativeModeTab.Builder.translation(translation: String): CreativeModeTab.Builder = this.title(Component.translatable(translation))
fun CreativeModeTab.Builder.name(name: String): CreativeModeTab.Builder = this.title(Component.literal(name))
