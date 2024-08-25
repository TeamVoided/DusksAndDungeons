package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Blocks
import net.minecraft.item.Items
import org.teamvoided.dusk_autumn.init.DnDBlocks

object DnDBlockLists {
    val dye = listOf(
        Items.WHITE_DYE,
        Items.LIGHT_GRAY_DYE,
        Items.GRAY_DYE,
        Items.BLACK_DYE,
        Items.BROWN_DYE,
        Items.RED_DYE,
        Items.ORANGE_DYE,
        Items.YELLOW_DYE,
        Items.LIME_DYE,
        Items.GREEN_DYE,
        Items.CYAN_DYE,
        Items.LIGHT_BLUE_DYE,
        Items.BLUE_DYE,
        Items.PURPLE_DYE,
        Items.MAGENTA_DYE,
        Items.PINK_DYE
    )
    val logsAndStrippedLogs = listOf(
        (Blocks.OAK_LOG to Blocks.STRIPPED_OAK_LOG),
        (Blocks.SPRUCE_LOG to Blocks.STRIPPED_SPRUCE_LOG),
        (Blocks.BIRCH_LOG to Blocks.STRIPPED_BIRCH_LOG),
        (Blocks.JUNGLE_LOG to Blocks.STRIPPED_JUNGLE_LOG),
        (Blocks.ACACIA_LOG to Blocks.STRIPPED_ACACIA_LOG),
        (Blocks.DARK_OAK_LOG to Blocks.STRIPPED_DARK_OAK_LOG),
        (Blocks.MANGROVE_LOG to Blocks.STRIPPED_MANGROVE_LOG),
        (Blocks.CHERRY_LOG to Blocks.STRIPPED_CHERRY_LOG),
        (DnDBlocks.CASCADE_LOG to DnDBlocks.STRIPPED_CASCADE_LOG),
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
        (DnDBlocks.CASCADE_WOOD to DnDBlocks.STRIPPED_CASCADE_WOOD),
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
        DnDBlocks.CASCADE_LEAVES,
        DnDBlocks.GOLDEN_BIRCH_LEAVES
    )
    val bigCandles = listOf(
        (DnDBlocks.BIG_CANDLE to DnDBlocks.BIG_CANDLE_CAKE),
        (DnDBlocks.BIG_WHITE_CANDLE to DnDBlocks.BIG_WHITE_CANDLE_CAKE),
        (DnDBlocks.BIG_LIGHT_GRAY_CANDLE to DnDBlocks.BIG_LIGHT_GRAY_CANDLE_CAKE),
        (DnDBlocks.BIG_GRAY_CANDLE to DnDBlocks.BIG_GRAY_CANDLE_CAKE),
        (DnDBlocks.BIG_BLACK_CANDLE to DnDBlocks.BIG_BLACK_CANDLE_CAKE),
        (DnDBlocks.BIG_BROWN_CANDLE to DnDBlocks.BIG_BROWN_CANDLE_CAKE),
        (DnDBlocks.BIG_RED_CANDLE to DnDBlocks.BIG_RED_CANDLE_CAKE),
        (DnDBlocks.BIG_ORANGE_CANDLE to DnDBlocks.BIG_ORANGE_CANDLE_CAKE),
        (DnDBlocks.BIG_YELLOW_CANDLE to DnDBlocks.BIG_YELLOW_CANDLE_CAKE),
        (DnDBlocks.BIG_LIME_CANDLE to DnDBlocks.BIG_LIME_CANDLE_CAKE),
        (DnDBlocks.BIG_GREEN_CANDLE to DnDBlocks.BIG_GREEN_CANDLE_CAKE),
        (DnDBlocks.BIG_CYAN_CANDLE to DnDBlocks.BIG_CYAN_CANDLE_CAKE),
        (DnDBlocks.BIG_LIGHT_BLUE_CANDLE to DnDBlocks.BIG_LIGHT_BLUE_CANDLE_CAKE),
        (DnDBlocks.BIG_BLUE_CANDLE to DnDBlocks.BIG_BLUE_CANDLE_CAKE),
        (DnDBlocks.BIG_PURPLE_CANDLE to DnDBlocks.BIG_PURPLE_CANDLE_CAKE),
        (DnDBlocks.BIG_MAGENTA_CANDLE to DnDBlocks.BIG_MAGENTA_CANDLE_CAKE),
        (DnDBlocks.BIG_PINK_CANDLE to DnDBlocks.BIG_PINK_CANDLE_CAKE),
    )
    val soulCandles = listOf(
        (DnDBlocks.SOUL_CANDLE to DnDBlocks.SOUL_CANDLE_CAKE),
        (DnDBlocks.WHITE_SOUL_CANDLE to DnDBlocks.WHITE_SOUL_CANDLE_CAKE),
        (DnDBlocks.LIGHT_GRAY_SOUL_CANDLE to DnDBlocks.LIGHT_GRAY_SOUL_CANDLE_CAKE),
        (DnDBlocks.GRAY_SOUL_CANDLE to DnDBlocks.GRAY_SOUL_CANDLE_CAKE),
        (DnDBlocks.BLACK_SOUL_CANDLE to DnDBlocks.BLACK_SOUL_CANDLE_CAKE),
        (DnDBlocks.BROWN_SOUL_CANDLE to DnDBlocks.BROWN_SOUL_CANDLE_CAKE),
        (DnDBlocks.RED_SOUL_CANDLE to DnDBlocks.RED_SOUL_CANDLE_CAKE),
        (DnDBlocks.ORANGE_SOUL_CANDLE to DnDBlocks.ORANGE_SOUL_CANDLE_CAKE),
        (DnDBlocks.YELLOW_SOUL_CANDLE to DnDBlocks.YELLOW_SOUL_CANDLE_CAKE),
        (DnDBlocks.LIME_SOUL_CANDLE to DnDBlocks.LIME_SOUL_CANDLE_CAKE),
        (DnDBlocks.GREEN_SOUL_CANDLE to DnDBlocks.GREEN_SOUL_CANDLE_CAKE),
        (DnDBlocks.CYAN_SOUL_CANDLE to DnDBlocks.CYAN_SOUL_CANDLE_CAKE),
        (DnDBlocks.LIGHT_BLUE_SOUL_CANDLE to DnDBlocks.LIGHT_BLUE_SOUL_CANDLE_CAKE),
        (DnDBlocks.BLUE_SOUL_CANDLE to DnDBlocks.BLUE_SOUL_CANDLE_CAKE),
        (DnDBlocks.PURPLE_SOUL_CANDLE to DnDBlocks.PURPLE_SOUL_CANDLE_CAKE),
        (DnDBlocks.MAGENTA_SOUL_CANDLE to DnDBlocks.MAGENTA_SOUL_CANDLE_CAKE),
        (DnDBlocks.PINK_SOUL_CANDLE to DnDBlocks.PINK_SOUL_CANDLE_CAKE),
    )
    val bigSoulCandles = listOf(
        (DnDBlocks.BIG_SOUL_CANDLE to DnDBlocks.BIG_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_WHITE_SOUL_CANDLE to DnDBlocks.BIG_WHITE_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE to DnDBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_GRAY_SOUL_CANDLE to DnDBlocks.BIG_GRAY_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_BLACK_SOUL_CANDLE to DnDBlocks.BIG_BLACK_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_BROWN_SOUL_CANDLE to DnDBlocks.BIG_BROWN_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_RED_SOUL_CANDLE to DnDBlocks.BIG_RED_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_ORANGE_SOUL_CANDLE to DnDBlocks.BIG_ORANGE_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_YELLOW_SOUL_CANDLE to DnDBlocks.BIG_YELLOW_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_LIME_SOUL_CANDLE to DnDBlocks.BIG_LIME_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_GREEN_SOUL_CANDLE to DnDBlocks.BIG_GREEN_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_CYAN_SOUL_CANDLE to DnDBlocks.BIG_CYAN_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE to DnDBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_BLUE_SOUL_CANDLE to DnDBlocks.BIG_BLUE_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_PURPLE_SOUL_CANDLE to DnDBlocks.BIG_PURPLE_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_MAGENTA_SOUL_CANDLE to DnDBlocks.BIG_MAGENTA_SOUL_CANDLE_CAKE),
        (DnDBlocks.BIG_PINK_SOUL_CANDLE to DnDBlocks.BIG_PINK_SOUL_CANDLE_CAKE),
    )
    val hollowLogs = listOf(
        DnDBlocks.HOLLOW_OAK_LOG,
        DnDBlocks.HOLLOW_SPRUCE_LOG,
        DnDBlocks.HOLLOW_BIRCH_LOG,
        DnDBlocks.HOLLOW_JUNGLE_LOG,
        DnDBlocks.HOLLOW_ACACIA_LOG,
        DnDBlocks.HOLLOW_DARK_OAK_LOG,
        DnDBlocks.HOLLOW_MANGROVE_LOG,
        DnDBlocks.HOLLOW_CHERRY_LOG,
        DnDBlocks.HOLLOW_CASCADE_LOG,
        DnDBlocks.HOLLOW_CRIMSON_STEM,
        DnDBlocks.HOLLOW_WARPED_STEM
    )
    val hollowStrippedLogs = listOf(
        DnDBlocks.HOLLOW_STRIPPED_OAK_LOG,
        DnDBlocks.HOLLOW_STRIPPED_SPRUCE_LOG,
        DnDBlocks.HOLLOW_STRIPPED_BIRCH_LOG,
        DnDBlocks.HOLLOW_STRIPPED_JUNGLE_LOG,
        DnDBlocks.HOLLOW_STRIPPED_ACACIA_LOG,
        DnDBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG,
        DnDBlocks.HOLLOW_STRIPPED_MANGROVE_LOG,
        DnDBlocks.HOLLOW_STRIPPED_CHERRY_LOG,
        DnDBlocks.HOLLOW_STRIPPED_CASCADE_LOG,
        DnDBlocks.HOLLOW_STRIPPED_CRIMSON_STEM,
        DnDBlocks.HOLLOW_STRIPPED_WARPED_STEM
    )
    val logPiles = listOf(
        DnDBlocks.OAK_LOG_PILE,
        DnDBlocks.SPRUCE_LOG_PILE,
        DnDBlocks.BIRCH_LOG_PILE,
        DnDBlocks.JUNGLE_LOG_PILE,
        DnDBlocks.ACACIA_LOG_PILE,
        DnDBlocks.DARK_OAK_LOG_PILE,
        DnDBlocks.MANGROVE_LOG_PILE,
        DnDBlocks.CHERRY_LOG_PILE,
        DnDBlocks.CASCADE_LOG_PILE,
        DnDBlocks.CRIMSON_STEM_PILE,
        DnDBlocks.WARPED_STEM_PILE
    )
    val leafPiles = listOf(
        DnDBlocks.OAK_LEAF_PILE ,
        DnDBlocks.SPRUCE_LEAF_PILE ,
        DnDBlocks.BIRCH_LEAF_PILE ,
        DnDBlocks.JUNGLE_LEAF_PILE ,
        DnDBlocks.ACACIA_LEAF_PILE ,
        DnDBlocks.DARK_OAK_LEAF_PILE,
        DnDBlocks.MANGROVE_LEAF_PILE ,
        DnDBlocks.CHERRY_LEAF_PILE,
        DnDBlocks.AZALEA_LEAF_PILE,
        DnDBlocks.FLOWERING_AZALEA_LEAF_PILE ,
        DnDBlocks.CASCADE_LEAF_PILE,
        DnDBlocks.GOLDEN_BIRCH_LEAF_PILE
    )
    val flowerbedBlocks = listOf(
        DnDBlocks.WHITE_PETALS,
        DnDBlocks.RED_PETALS,
        DnDBlocks.ORANGE_PETALS,
        DnDBlocks.BLUE_PETALS,
        DnDBlocks.WILD_PETALS
    )
}