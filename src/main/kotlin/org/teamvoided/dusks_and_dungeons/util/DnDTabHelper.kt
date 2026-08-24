package org.teamvoided.dusks_and_dungeons.util

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDItemLists.stripedWoodLists
import org.teamvoided.voidlib.consortium.block.color.ColorConsortium
import org.teamvoided.voidlib.helpers.mc.addAfter

fun FabricItemGroupEntries.addWoodStuffAndLeafPiles(leaves: Boolean = true) {
    DnDBlockLists.logsAndStrippedLogs.forEachIndexed { idx, (log, strippedLog) ->
        addAfter(log, DnDBlockLists.hollowLogs[idx])
        addAfter(strippedLog, DnDBlockLists.hollowStrippedLogs[idx])
        addAfter(
            DnDBlockLists.woodAndStrippedWood[idx].first,
            DnDItemLists.woodLists[idx] + DnDBlockLists.logPiles[idx]
        )
        addAfter(
            DnDBlockLists.woodAndStrippedWood[idx].second,
            stripedWoodLists[idx] + DnDBlockLists.stripedLogPiles[idx]
        )
        addAfter(
            DnDBlockLists.plankSlabs[idx],
            DnDBlockLists.plankWalls[idx]
        )
    }

    addAfter(Blocks.BAMBOO_BLOCK, DnDBlocks.BAMBOO_PILE)
    addAfter(Blocks.STRIPPED_BAMBOO_BLOCK, DnDBlocks.STRIPPED_BAMBOO_PILE)
    addAfter(Blocks.BAMBOO_MOSAIC_SLAB, DnDBlocks.BAMBOO_WALL, DnDBlocks.BAMBOO_MOSAIC_WALL)

    addAfter(Blocks.BAMBOO_BLOCK, DnDBlocks.HOLLOW_BAMBOO_BLOCK)
    addAfter(Blocks.STRIPPED_BAMBOO_BLOCK, DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK)
    if (!leaves) return
    DnDBlockLists.leafPiles.forEachIndexed { idx, leafPile ->
        addAfter(DnDBlockLists.leaves[idx], leafPile)
    }
}

fun FabricItemGroupEntries.addColors(item: ItemLike, color: ColorConsortium<*>) {
    addAfter(
        item,
        color.white,
        color.lightGray,
        color.gray,
        color.black,
        color.brown,
        color.red,
        color.orange,
        color.yellow,
        color.lime,
        color.green,
        color.cyan,
        color.lightBlue,
        color.blue,
        color.purple,
        color.magenta,
        color.pink
    )
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
