package org.teamvoided.dusks_and_dungeons.util

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.minecraft.item.Items
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDBigBlocks
import org.teamvoided.voidlib.helpers.mc.addAfter

fun FabricItemGroupEntries.addWoodStuffAndLeafPiles(leaves: Boolean = true) {
    DnDBlockLists.logsAndStrippedLogs.forEachIndexed { idx, (_, _) ->
//        addAfter(log, DnDBlockLists.hollowLogs[idx])
//        addAfter(stripped, DnDBlockLists.hollowStrippedLogs[idx])
        addAfter(
            DnDBlockLists.woodAndStrippedWood[idx].first,
            DnDItemLists.woodLists[idx] + DnDBlockLists.logPiles[idx]
        )
    }
//    addAfter(Blocks.BAMBOO_BLOCK, DnDWoodBlocks.HOLLOW_BAMBOO_BLOCK)
//    addAfter(Blocks.STRIPPED_BAMBOO_BLOCK, DnDWoodBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK)
    if (!leaves) return
    DnDBlockLists.leafPiles.forEachIndexed { idx, leafPile ->
        addAfter(DnDBlockLists.leaves[idx], leafPile)
    }
}


fun FabricItemGroupEntries.addCandles() {
    addAfter(
        Items.CANDLE, DnDBigBlocks.BIG_CANDLE,
        DnDBigBlocks.SOUL_CANDLE, DnDBigBlocks.BIG_SOUL_CANDLE,
        DnDBigBlocks.CANDELABRA, DnDBigBlocks.SOUL_CANDELABRA
    )
    addAfter(
        Items.WHITE_CANDLE, DnDBigBlocks.BIG_WHITE_CANDLE,
        DnDBigBlocks.WHITE_SOUL_CANDLE, DnDBigBlocks.BIG_WHITE_SOUL_CANDLE,
        DnDBigBlocks.WHITE_CANDELABRA, DnDBigBlocks.WHITE_SOUL_CANDELABRA
    )
    addAfter(
        Items.LIGHT_GRAY_CANDLE, DnDBigBlocks.BIG_LIGHT_GRAY_CANDLE,
        DnDBigBlocks.LIGHT_GRAY_SOUL_CANDLE, DnDBigBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE,
        DnDBigBlocks.LIGHT_GRAY_CANDELABRA, DnDBigBlocks.LIGHT_GRAY_SOUL_CANDELABRA
    )
    addAfter(
        Items.GRAY_CANDLE, DnDBigBlocks.BIG_GRAY_CANDLE,
        DnDBigBlocks.GRAY_SOUL_CANDLE, DnDBigBlocks.BIG_GRAY_SOUL_CANDLE,
        DnDBigBlocks.GRAY_CANDELABRA, DnDBigBlocks.GRAY_SOUL_CANDELABRA
    )
    addAfter(
        Items.BLACK_CANDLE, DnDBigBlocks.BIG_BLACK_CANDLE,
        DnDBigBlocks.BLACK_SOUL_CANDLE, DnDBigBlocks.BIG_BLACK_SOUL_CANDLE,
        DnDBigBlocks.BLACK_CANDELABRA, DnDBigBlocks.BLACK_SOUL_CANDELABRA
    )
    addAfter(
        Items.BROWN_CANDLE, DnDBigBlocks.BIG_BROWN_CANDLE,
        DnDBigBlocks.BROWN_SOUL_CANDLE, DnDBigBlocks.BIG_BROWN_SOUL_CANDLE,
        DnDBigBlocks.BROWN_CANDELABRA, DnDBigBlocks.BROWN_SOUL_CANDELABRA
    )
    addAfter(
        Items.RED_CANDLE, DnDBigBlocks.BIG_RED_CANDLE,
        DnDBigBlocks.RED_SOUL_CANDLE, DnDBigBlocks.BIG_RED_SOUL_CANDLE,
        DnDBigBlocks.RED_CANDELABRA, DnDBigBlocks.RED_SOUL_CANDELABRA
    )
    addAfter(
        Items.ORANGE_CANDLE, DnDBigBlocks.BIG_ORANGE_CANDLE,
        DnDBigBlocks.ORANGE_SOUL_CANDLE, DnDBigBlocks.BIG_ORANGE_SOUL_CANDLE,
        DnDBigBlocks.ORANGE_CANDELABRA, DnDBigBlocks.ORANGE_SOUL_CANDELABRA
    )
    addAfter(
        Items.YELLOW_CANDLE, DnDBigBlocks.BIG_YELLOW_CANDLE,
        DnDBigBlocks.YELLOW_SOUL_CANDLE, DnDBigBlocks.BIG_YELLOW_SOUL_CANDLE,
        DnDBigBlocks.YELLOW_CANDELABRA, DnDBigBlocks.YELLOW_SOUL_CANDELABRA
    )
    addAfter(
        Items.LIME_CANDLE, DnDBigBlocks.BIG_LIME_CANDLE,
        DnDBigBlocks.LIME_SOUL_CANDLE, DnDBigBlocks.BIG_LIME_SOUL_CANDLE,
        DnDBigBlocks.LIME_CANDELABRA, DnDBigBlocks.LIME_SOUL_CANDELABRA
    )
    addAfter(
        Items.GREEN_CANDLE, DnDBigBlocks.BIG_GREEN_CANDLE,
        DnDBigBlocks.GREEN_SOUL_CANDLE, DnDBigBlocks.BIG_GREEN_SOUL_CANDLE,
        DnDBigBlocks.GREEN_CANDELABRA, DnDBigBlocks.GREEN_SOUL_CANDELABRA
    )
    addAfter(
        Items.CYAN_CANDLE, DnDBigBlocks.BIG_CYAN_CANDLE,
        DnDBigBlocks.CYAN_SOUL_CANDLE, DnDBigBlocks.BIG_CYAN_SOUL_CANDLE,
        DnDBigBlocks.CYAN_CANDELABRA, DnDBigBlocks.CYAN_SOUL_CANDELABRA
    )
    addAfter(
        Items.BLUE_CANDLE, DnDBigBlocks.BIG_BLUE_CANDLE,
        DnDBigBlocks.BLUE_SOUL_CANDLE, DnDBigBlocks.BIG_BLUE_SOUL_CANDLE,
        DnDBigBlocks.BLUE_CANDELABRA, DnDBigBlocks.BLUE_SOUL_CANDELABRA
    )
    addAfter(
        Items.LIGHT_BLUE_CANDLE, DnDBigBlocks.BIG_LIGHT_BLUE_CANDLE,
        DnDBigBlocks.LIGHT_BLUE_SOUL_CANDLE, DnDBigBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE,
        DnDBigBlocks.LIGHT_BLUE_CANDELABRA, DnDBigBlocks.LIGHT_BLUE_SOUL_CANDELABRA
    )
    addAfter(
        Items.PURPLE_CANDLE, DnDBigBlocks.BIG_PURPLE_CANDLE,
        DnDBigBlocks.PURPLE_SOUL_CANDLE, DnDBigBlocks.BIG_PURPLE_SOUL_CANDLE,
        DnDBigBlocks.PURPLE_CANDELABRA, DnDBigBlocks.PURPLE_SOUL_CANDELABRA
    )
    addAfter(
        Items.MAGENTA_CANDLE, DnDBigBlocks.BIG_MAGENTA_CANDLE,
        DnDBigBlocks.MAGENTA_SOUL_CANDLE, DnDBigBlocks.BIG_MAGENTA_SOUL_CANDLE,
        DnDBigBlocks.MAGENTA_CANDELABRA, DnDBigBlocks.MAGENTA_SOUL_CANDELABRA
    )
    addAfter(
        Items.PINK_CANDLE, DnDBigBlocks.BIG_PINK_CANDLE,
        DnDBigBlocks.PINK_SOUL_CANDLE, DnDBigBlocks.BIG_PINK_SOUL_CANDLE,
        DnDBigBlocks.PINK_CANDELABRA, DnDBigBlocks.PINK_SOUL_CANDELABRA
    )
}
