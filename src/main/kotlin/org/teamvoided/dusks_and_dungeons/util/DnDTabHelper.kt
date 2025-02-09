package org.teamvoided.dusks_and_dungeons.util

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.minecraft.item.Items
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
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
    val candles = listOf(
        DnDBlocks.BIG_CANDLES, DnDBlocks.CANDELABRAS,
        DnDBlocks.SOUL_CANDLES, DnDBlocks.BIG_SOUL_CANDLES, DnDBlocks.SOUL_CANDELABRAS
    )
    addAfter(Items.CANDLE, candles.map { it.uncolored })
    addAfter(Items.WHITE_CANDLE, candles.map { it.white })
    addAfter(Items.LIGHT_GRAY_CANDLE, candles.map { it.lightGray })
    addAfter(Items.GRAY_CANDLE, candles.map { it.gray })
    addAfter(Items.BLACK_CANDLE, candles.map { it.black })
    addAfter(Items.BROWN_CANDLE, candles.map { it.brown })
    addAfter(Items.RED_CANDLE, candles.map { it.red })
    addAfter(Items.ORANGE_CANDLE, candles.map { it.orange })
    addAfter(Items.YELLOW_CANDLE, candles.map { it.yellow })
    addAfter(Items.LIME_CANDLE, candles.map { it.lime })
    addAfter(Items.GREEN_CANDLE, candles.map { it.green })
    addAfter(Items.CYAN_CANDLE, candles.map { it.cyan })
    addAfter(Items.LIGHT_BLUE_CANDLE, candles.map { it.lightBlue })
    addAfter(Items.BLUE_CANDLE, candles.map { it.blue })
    addAfter(Items.PURPLE_CANDLE, candles.map { it.purple })
    addAfter(Items.MAGENTA_CANDLE, candles.map { it.magenta })
    addAfter(Items.PINK_CANDLE, candles.map { it.pink })
}
