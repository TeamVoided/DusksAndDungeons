package org.teamvoided.dusk_autumn.util

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.Blocks
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import org.teamvoided.dusk_autumn.init.DnDBlocks

fun ItemGroup.ItemStackCollector.addItem(vararg list: ItemConvertible) = this.addItem(list.toList())
fun ItemGroup.ItemStackCollector.addItem(list: Collection<ItemConvertible>) = this.addStacks(list.toStacks())
fun ItemGroup.ItemStackCollector.addLists(vararg lists: Collection<ItemConvertible>) =
    this.addStacks(lists.flatMap { it.toStacks() })

fun addToTab(itemGroup: RegistryKey<ItemGroup>, itemBefore: ItemGroupEvents.ModifyEntries) =
    ItemGroupEvents.modifyEntriesEvent(itemGroup).register(itemBefore)

fun FabricItemGroupEntries.addAfter(item: ItemConvertible, list: Collection<ItemConvertible>) =
    this.addAfter(item.asItem(), list.map { it.asItem().defaultStack })
fun FabricItemGroupEntries.addBefore(item: ItemConvertible, list: Collection<ItemConvertible>) =
    this.addBefore(item.asItem(), list.map { it.asItem().defaultStack })

fun FabricItemGroupEntries.addWoodStuffAndLeafPiles() {
    DnDBlockLists.logsAndStrippedLogs.forEachIndexed { idx, (log, stripped) ->
        this.addAfter(log, DnDBlockLists.hollowLogs[idx])
        this.addAfter(stripped, DnDBlockLists.hollowStrippedLogs[idx])
        this.addAfter(
            DnDBlockLists.woodAndStrippedWood[idx].first,
            DnDItemLists.woodLists[idx] + DnDBlockLists.logPiles[idx]
        )
    }
    this.addAfter(Blocks.BAMBOO_BLOCK, DnDBlocks.HOLLOW_BAMBOO_BLOCK)
    this.addAfter(Blocks.STRIPPED_BAMBOO_BLOCK, DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK)
    DnDBlockLists.leafPiles.forEachIndexed { idx, leafPile ->
        this.addAfter(DnDBlockLists.leaves[idx], leafPile)
    }
}

fun FabricItemGroupEntries.addCandles() {
    this.addAfter(Items.CANDLE, DnDBlocks.BIG_CANDLE, DnDBlocks.SOUL_CANDLE, DnDBlocks.BIG_SOUL_CANDLE)
    this.addAfter(
        Items.WHITE_CANDLE, DnDBlocks.BIG_WHITE_CANDLE, DnDBlocks.WHITE_SOUL_CANDLE, DnDBlocks.BIG_WHITE_SOUL_CANDLE
    )
    this.addAfter(
        Items.LIGHT_GRAY_CANDLE,
        DnDBlocks.BIG_LIGHT_GRAY_CANDLE, DnDBlocks.LIGHT_GRAY_SOUL_CANDLE, DnDBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE
    )
    this.addAfter(
        Items.GRAY_CANDLE, DnDBlocks.BIG_GRAY_CANDLE, DnDBlocks.GRAY_SOUL_CANDLE, DnDBlocks.BIG_GRAY_SOUL_CANDLE
    )
    this.addAfter(
        Items.BLACK_CANDLE, DnDBlocks.BIG_BLACK_CANDLE, DnDBlocks.BLACK_SOUL_CANDLE, DnDBlocks.BIG_BLACK_SOUL_CANDLE
    )
    this.addAfter(
        Items.BROWN_CANDLE, DnDBlocks.BIG_BROWN_CANDLE, DnDBlocks.BROWN_SOUL_CANDLE, DnDBlocks.BIG_BROWN_SOUL_CANDLE
    )
    this.addAfter(
        Items.RED_CANDLE, DnDBlocks.BIG_RED_CANDLE, DnDBlocks.RED_SOUL_CANDLE, DnDBlocks.BIG_RED_SOUL_CANDLE
    )
    this.addAfter(
        Items.ORANGE_CANDLE,
        DnDBlocks.BIG_ORANGE_CANDLE, DnDBlocks.ORANGE_SOUL_CANDLE, DnDBlocks.BIG_ORANGE_SOUL_CANDLE
    )
    this.addAfter(
        Items.YELLOW_CANDLE,
        DnDBlocks.BIG_YELLOW_CANDLE, DnDBlocks.YELLOW_SOUL_CANDLE, DnDBlocks.BIG_YELLOW_SOUL_CANDLE
    )
    this.addAfter(
        Items.LIME_CANDLE, DnDBlocks.BIG_LIME_CANDLE, DnDBlocks.LIME_SOUL_CANDLE, DnDBlocks.BIG_LIME_SOUL_CANDLE
    )
    this.addAfter(
        Items.GREEN_CANDLE, DnDBlocks.BIG_GREEN_CANDLE, DnDBlocks.GREEN_SOUL_CANDLE, DnDBlocks.BIG_GREEN_SOUL_CANDLE
    )
    this.addAfter(
        Items.CYAN_CANDLE, DnDBlocks.BIG_CYAN_CANDLE, DnDBlocks.CYAN_SOUL_CANDLE, DnDBlocks.BIG_CYAN_SOUL_CANDLE
    )
    this.addAfter(
        Items.BLUE_CANDLE, DnDBlocks.BIG_BLUE_CANDLE, DnDBlocks.BLUE_SOUL_CANDLE, DnDBlocks.BIG_BLUE_SOUL_CANDLE
    )
    this.addAfter(
        Items.LIGHT_BLUE_CANDLE,
        DnDBlocks.BIG_LIGHT_BLUE_CANDLE, DnDBlocks.LIGHT_BLUE_SOUL_CANDLE, DnDBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE
    )
    this.addAfter(
        Items.PURPLE_CANDLE,
        DnDBlocks.BIG_PURPLE_CANDLE, DnDBlocks.PURPLE_SOUL_CANDLE, DnDBlocks.BIG_PURPLE_SOUL_CANDLE
    )
    this.addAfter(
        Items.MAGENTA_CANDLE,
        DnDBlocks.BIG_MAGENTA_CANDLE, DnDBlocks.MAGENTA_SOUL_CANDLE, DnDBlocks.BIG_MAGENTA_SOUL_CANDLE
    )
    this.addAfter(
        Items.PINK_CANDLE, DnDBlocks.BIG_PINK_CANDLE, DnDBlocks.PINK_SOUL_CANDLE, DnDBlocks.BIG_PINK_SOUL_CANDLE
    )
}
