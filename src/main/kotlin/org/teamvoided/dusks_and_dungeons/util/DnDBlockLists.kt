package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.block.Blocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks

object DnDBlockLists {

    val logsAndStrippedLogs = listOf(
        (Blocks.OAK_LOG to Blocks.STRIPPED_OAK_LOG),
        (Blocks.SPRUCE_LOG to Blocks.STRIPPED_SPRUCE_LOG),
        (Blocks.BIRCH_LOG to Blocks.STRIPPED_BIRCH_LOG),
        (Blocks.JUNGLE_LOG to Blocks.STRIPPED_JUNGLE_LOG),
        (Blocks.ACACIA_LOG to Blocks.STRIPPED_ACACIA_LOG),
        (Blocks.DARK_OAK_LOG to Blocks.STRIPPED_DARK_OAK_LOG),
        (Blocks.MANGROVE_LOG to Blocks.STRIPPED_MANGROVE_LOG),
        (Blocks.CHERRY_LOG to Blocks.STRIPPED_CHERRY_LOG),
        (DnDWoodBlocks.CASCADE_LOG to DnDWoodBlocks.STRIPPED_CASCADE_LOG),
//        (DnDWoodBlocks.GALLERY_MAPLE_LOG to DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_LOG),
        (Blocks.CRIMSON_STEM to Blocks.STRIPPED_CRIMSON_STEM),
        (Blocks.WARPED_STEM to Blocks.STRIPPED_WARPED_STEM)
    )
    val woodAndStrippedWood = listOf(
        (Blocks.OAK_WOOD to Blocks.STRIPPED_OAK_WOOD),
        (Blocks.SPRUCE_WOOD to Blocks.STRIPPED_SPRUCE_WOOD),
        (Blocks.BIRCH_WOOD to Blocks.STRIPPED_BIRCH_WOOD),
        (Blocks.JUNGLE_WOOD to Blocks.STRIPPED_JUNGLE_WOOD),
        (Blocks.ACACIA_WOOD to Blocks.STRIPPED_ACACIA_WOOD),
        (Blocks.DARK_OAK_WOOD to Blocks.STRIPPED_DARK_OAK_WOOD),
        (Blocks.MANGROVE_WOOD to Blocks.STRIPPED_MANGROVE_WOOD),
        (Blocks.CHERRY_WOOD to Blocks.STRIPPED_CHERRY_WOOD),
        (DnDWoodBlocks.CASCADE_WOOD to DnDWoodBlocks.STRIPPED_CASCADE_WOOD),
//        (DnDWoodBlocks.GALLERY_MAPLE_WOOD to DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_WOOD),
        (Blocks.CRIMSON_HYPHAE to Blocks.STRIPPED_CRIMSON_HYPHAE),
        (Blocks.WARPED_HYPHAE to Blocks.STRIPPED_WARPED_HYPHAE)
    )
    val leaves = listOf(
        Blocks.OAK_LEAVES,
        Blocks.SPRUCE_LEAVES,
        Blocks.BIRCH_LEAVES,
        Blocks.JUNGLE_LEAVES,
        Blocks.ACACIA_LEAVES,
        Blocks.DARK_OAK_LEAVES,
        Blocks.MANGROVE_LEAVES,
        Blocks.CHERRY_LEAVES,
        Blocks.AZALEA_LEAVES,
        Blocks.FLOWERING_AZALEA_LEAVES,
        DnDWoodBlocks.CASCADE_LEAVES,
//        DnDWoodBlocks.GALLERY_MAPLE_LEAVES,
        DnDWoodBlocks.GOLDEN_BIRCH_LEAVES
    )
    val bigCandles = DnDBlocks.BIG_CANDLES.zip(DnDBlocks.BIG_CANDLE_CAKES)
    val soulCandles = DnDBlocks.SOUL_CANDLES.zip(DnDBlocks.SOUL_CANDLE_CAKES)
    val bigSoulCandles = DnDBlocks.BIG_SOUL_CANDLES.zip(DnDBlocks.BIG_SOUL_CANDLE_CAKES)
    val candelabras = DnDBlocks.CANDELABRAS.toList()
    val soulCandelabras = DnDBlocks.SOUL_CANDELABRAS.toList()
    val allCandelabras = candelabras + soulCandelabras

    val hollowLogs = listOf(
        DnDWoodBlocks.HOLLOW_OAK_LOG,
        DnDWoodBlocks.HOLLOW_SPRUCE_LOG,
        DnDWoodBlocks.HOLLOW_BIRCH_LOG,
        DnDWoodBlocks.HOLLOW_JUNGLE_LOG,
        DnDWoodBlocks.HOLLOW_ACACIA_LOG,
        DnDWoodBlocks.HOLLOW_DARK_OAK_LOG,
        DnDWoodBlocks.HOLLOW_MANGROVE_LOG,
        DnDWoodBlocks.HOLLOW_CHERRY_LOG,
        DnDWoodBlocks.HOLLOW_CASCADE_LOG,
//        DnDWoodBlocks.HOLLOW_GALLERY_MAPLE_LOG,
        DnDWoodBlocks.HOLLOW_CRIMSON_STEM,
        DnDWoodBlocks.HOLLOW_WARPED_STEM
    )
    val hollowStrippedLogs = listOf(
        DnDWoodBlocks.HOLLOW_STRIPPED_OAK_LOG,
        DnDWoodBlocks.HOLLOW_STRIPPED_SPRUCE_LOG,
        DnDWoodBlocks.HOLLOW_STRIPPED_BIRCH_LOG,
        DnDWoodBlocks.HOLLOW_STRIPPED_JUNGLE_LOG,
        DnDWoodBlocks.HOLLOW_STRIPPED_ACACIA_LOG,
        DnDWoodBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG,
        DnDWoodBlocks.HOLLOW_STRIPPED_MANGROVE_LOG,
        DnDWoodBlocks.HOLLOW_STRIPPED_CHERRY_LOG,
        DnDWoodBlocks.HOLLOW_STRIPPED_CASCADE_LOG,
//        DnDWoodBlocks.HOLLOW_STRIPPED_GALLERY_MAPLE_LOG,
        DnDWoodBlocks.HOLLOW_STRIPPED_CRIMSON_STEM,
        DnDWoodBlocks.HOLLOW_STRIPPED_WARPED_STEM
    )
    val logPiles = listOf(
        DnDWoodBlocks.OAK_LOG_PILE,
        DnDWoodBlocks.SPRUCE_LOG_PILE,
        DnDWoodBlocks.BIRCH_LOG_PILE,
        DnDWoodBlocks.JUNGLE_LOG_PILE,
        DnDWoodBlocks.ACACIA_LOG_PILE,
        DnDWoodBlocks.DARK_OAK_LOG_PILE,
        DnDWoodBlocks.MANGROVE_LOG_PILE,
        DnDWoodBlocks.CHERRY_LOG_PILE,
        DnDWoodBlocks.CASCADE_LOG_PILE,
//        DnDWoodBlocks.GALLERY_MAPLE_LOG_PILE,
        DnDWoodBlocks.CRIMSON_STEM_PILE,
        DnDWoodBlocks.WARPED_STEM_PILE
    )
    val leafPiles = listOf(
        DnDWoodBlocks.OAK_LEAF_PILE,
        DnDWoodBlocks.SPRUCE_LEAF_PILE,
        DnDWoodBlocks.BIRCH_LEAF_PILE,
        DnDWoodBlocks.JUNGLE_LEAF_PILE,
        DnDWoodBlocks.ACACIA_LEAF_PILE,
        DnDWoodBlocks.DARK_OAK_LEAF_PILE,
        DnDWoodBlocks.MANGROVE_LEAF_PILE,
        DnDWoodBlocks.CHERRY_LEAF_PILE,
        DnDWoodBlocks.AZALEA_LEAF_PILE,
        DnDWoodBlocks.FLOWERING_AZALEA_LEAF_PILE,
        DnDWoodBlocks.CASCADE_LEAF_PILE,
//        DnDWoodBlocks.GALLERY_MAPLE_LEAF_PILE,
        DnDWoodBlocks.GOLDEN_BIRCH_LEAF_PILE
    )
    val flowerbedBlocks = listOf(
        DnDBlocks.WILD_PETALS,
        DnDBlocks.WHITE_PETALS,
        DnDBlocks.RED_PETALS,
        DnDBlocks.ORANGE_PETALS,
        DnDBlocks.BLUE_PETALS
    )
    val vivionbedBlocks = listOf(
        DnDBlocks.CRIMSON_VIVIONS,
        DnDBlocks.WARPED_VIVIONS
    )
}
