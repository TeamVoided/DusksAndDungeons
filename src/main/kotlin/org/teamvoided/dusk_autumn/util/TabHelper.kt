package org.teamvoided.dusk_autumn.util

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.Blocks
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDBigBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks

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
//        this.addAfter(log, DnDBlockLists.hollowLogs[idx])
//        this.addAfter(stripped, DnDBlockLists.hollowStrippedLogs[idx])
        this.addAfter(
            DnDBlockLists.woodAndStrippedWood[idx].first,
            DnDItemLists.woodLists[idx] + DnDBlockLists.logPiles[idx]
        )
    }
    println()
    this.addAfter(Blocks.BAMBOO_BLOCK, DnDWoodBlocks.HOLLOW_BAMBOO_BLOCK)
    this.addAfter(Blocks.STRIPPED_BAMBOO_BLOCK, DnDWoodBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK)
    DnDBlockLists.leafPiles.forEachIndexed { idx, leafPile ->
        this.addAfter(DnDBlockLists.leaves[idx], leafPile)
    }
}


fun FabricItemGroupEntries.addCandles() {
    this.addAfter(Items.CANDLE, DnDBigBlocks.BIG_CANDLE, DnDBigBlocks.SOUL_CANDLE, DnDBigBlocks.BIG_SOUL_CANDLE)
    this.addAfter(
        Items.WHITE_CANDLE, DnDBigBlocks.BIG_WHITE_CANDLE, DnDBigBlocks.WHITE_SOUL_CANDLE, DnDBigBlocks.BIG_WHITE_SOUL_CANDLE
    )
    this.addAfter(
        Items.LIGHT_GRAY_CANDLE,
        DnDBigBlocks.BIG_LIGHT_GRAY_CANDLE, DnDBigBlocks.LIGHT_GRAY_SOUL_CANDLE, DnDBigBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE
    )
    this.addAfter(
        Items.GRAY_CANDLE, DnDBigBlocks.BIG_GRAY_CANDLE, DnDBigBlocks.GRAY_SOUL_CANDLE, DnDBigBlocks.BIG_GRAY_SOUL_CANDLE
    )
    this.addAfter(
        Items.BLACK_CANDLE, DnDBigBlocks.BIG_BLACK_CANDLE, DnDBigBlocks.BLACK_SOUL_CANDLE, DnDBigBlocks.BIG_BLACK_SOUL_CANDLE
    )
    this.addAfter(
        Items.BROWN_CANDLE, DnDBigBlocks.BIG_BROWN_CANDLE, DnDBigBlocks.BROWN_SOUL_CANDLE, DnDBigBlocks.BIG_BROWN_SOUL_CANDLE
    )
    this.addAfter(
        Items.RED_CANDLE, DnDBigBlocks.BIG_RED_CANDLE, DnDBigBlocks.RED_SOUL_CANDLE, DnDBigBlocks.BIG_RED_SOUL_CANDLE
    )
    this.addAfter(
        Items.ORANGE_CANDLE,
        DnDBigBlocks.BIG_ORANGE_CANDLE, DnDBigBlocks.ORANGE_SOUL_CANDLE, DnDBigBlocks.BIG_ORANGE_SOUL_CANDLE
    )
    this.addAfter(
        Items.YELLOW_CANDLE,
        DnDBigBlocks.BIG_YELLOW_CANDLE, DnDBigBlocks.YELLOW_SOUL_CANDLE, DnDBigBlocks.BIG_YELLOW_SOUL_CANDLE
    )
    this.addAfter(
        Items.LIME_CANDLE, DnDBigBlocks.BIG_LIME_CANDLE, DnDBigBlocks.LIME_SOUL_CANDLE, DnDBigBlocks.BIG_LIME_SOUL_CANDLE
    )
    this.addAfter(
        Items.GREEN_CANDLE, DnDBigBlocks.BIG_GREEN_CANDLE, DnDBigBlocks.GREEN_SOUL_CANDLE, DnDBigBlocks.BIG_GREEN_SOUL_CANDLE
    )
    this.addAfter(
        Items.CYAN_CANDLE, DnDBigBlocks.BIG_CYAN_CANDLE, DnDBigBlocks.CYAN_SOUL_CANDLE, DnDBigBlocks.BIG_CYAN_SOUL_CANDLE
    )
    this.addAfter(
        Items.BLUE_CANDLE, DnDBigBlocks.BIG_BLUE_CANDLE, DnDBigBlocks.BLUE_SOUL_CANDLE, DnDBigBlocks.BIG_BLUE_SOUL_CANDLE
    )
    this.addAfter(
        Items.LIGHT_BLUE_CANDLE,
        DnDBigBlocks.BIG_LIGHT_BLUE_CANDLE, DnDBigBlocks.LIGHT_BLUE_SOUL_CANDLE, DnDBigBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE
    )
    this.addAfter(
        Items.PURPLE_CANDLE,
        DnDBigBlocks.BIG_PURPLE_CANDLE, DnDBigBlocks.PURPLE_SOUL_CANDLE, DnDBigBlocks.BIG_PURPLE_SOUL_CANDLE
    )
    this.addAfter(
        Items.MAGENTA_CANDLE,
        DnDBigBlocks.BIG_MAGENTA_CANDLE, DnDBigBlocks.MAGENTA_SOUL_CANDLE, DnDBigBlocks.BIG_MAGENTA_SOUL_CANDLE
    )
    this.addAfter(
        Items.PINK_CANDLE, DnDBigBlocks.BIG_PINK_CANDLE, DnDBigBlocks.PINK_SOUL_CANDLE, DnDBigBlocks.BIG_PINK_SOUL_CANDLE
    )
}
