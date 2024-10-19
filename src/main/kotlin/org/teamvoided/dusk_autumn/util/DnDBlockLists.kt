package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.Items
import org.teamvoided.dusk_autumn.init.blocks.DnDBigBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks

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
    val bigCandles = listOf(
        (DnDBigBlocks.BIG_CANDLE to DnDBigBlocks.BIG_CANDLE_CAKE),
        (DnDBigBlocks.BIG_WHITE_CANDLE to DnDBigBlocks.BIG_WHITE_CANDLE_CAKE),
        (DnDBigBlocks.BIG_LIGHT_GRAY_CANDLE to DnDBigBlocks.BIG_LIGHT_GRAY_CANDLE_CAKE),
        (DnDBigBlocks.BIG_GRAY_CANDLE to DnDBigBlocks.BIG_GRAY_CANDLE_CAKE),
        (DnDBigBlocks.BIG_BLACK_CANDLE to DnDBigBlocks.BIG_BLACK_CANDLE_CAKE),
        (DnDBigBlocks.BIG_BROWN_CANDLE to DnDBigBlocks.BIG_BROWN_CANDLE_CAKE),
        (DnDBigBlocks.BIG_RED_CANDLE to DnDBigBlocks.BIG_RED_CANDLE_CAKE),
        (DnDBigBlocks.BIG_ORANGE_CANDLE to DnDBigBlocks.BIG_ORANGE_CANDLE_CAKE),
        (DnDBigBlocks.BIG_YELLOW_CANDLE to DnDBigBlocks.BIG_YELLOW_CANDLE_CAKE),
        (DnDBigBlocks.BIG_LIME_CANDLE to DnDBigBlocks.BIG_LIME_CANDLE_CAKE),
        (DnDBigBlocks.BIG_GREEN_CANDLE to DnDBigBlocks.BIG_GREEN_CANDLE_CAKE),
        (DnDBigBlocks.BIG_CYAN_CANDLE to DnDBigBlocks.BIG_CYAN_CANDLE_CAKE),
        (DnDBigBlocks.BIG_LIGHT_BLUE_CANDLE to DnDBigBlocks.BIG_LIGHT_BLUE_CANDLE_CAKE),
        (DnDBigBlocks.BIG_BLUE_CANDLE to DnDBigBlocks.BIG_BLUE_CANDLE_CAKE),
        (DnDBigBlocks.BIG_PURPLE_CANDLE to DnDBigBlocks.BIG_PURPLE_CANDLE_CAKE),
        (DnDBigBlocks.BIG_MAGENTA_CANDLE to DnDBigBlocks.BIG_MAGENTA_CANDLE_CAKE),
        (DnDBigBlocks.BIG_PINK_CANDLE to DnDBigBlocks.BIG_PINK_CANDLE_CAKE),
    )
    val soulCandles = listOf(
        (DnDBigBlocks.SOUL_CANDLE to DnDBigBlocks.SOUL_CANDLE_CAKE),
        (DnDBigBlocks.WHITE_SOUL_CANDLE to DnDBigBlocks.WHITE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.LIGHT_GRAY_SOUL_CANDLE to DnDBigBlocks.LIGHT_GRAY_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.GRAY_SOUL_CANDLE to DnDBigBlocks.GRAY_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BLACK_SOUL_CANDLE to DnDBigBlocks.BLACK_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BROWN_SOUL_CANDLE to DnDBigBlocks.BROWN_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.RED_SOUL_CANDLE to DnDBigBlocks.RED_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.ORANGE_SOUL_CANDLE to DnDBigBlocks.ORANGE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.YELLOW_SOUL_CANDLE to DnDBigBlocks.YELLOW_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.LIME_SOUL_CANDLE to DnDBigBlocks.LIME_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.GREEN_SOUL_CANDLE to DnDBigBlocks.GREEN_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.CYAN_SOUL_CANDLE to DnDBigBlocks.CYAN_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.LIGHT_BLUE_SOUL_CANDLE to DnDBigBlocks.LIGHT_BLUE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BLUE_SOUL_CANDLE to DnDBigBlocks.BLUE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.PURPLE_SOUL_CANDLE to DnDBigBlocks.PURPLE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.MAGENTA_SOUL_CANDLE to DnDBigBlocks.MAGENTA_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.PINK_SOUL_CANDLE to DnDBigBlocks.PINK_SOUL_CANDLE_CAKE),
    )
    val bigSoulCandles = listOf(
        (DnDBigBlocks.BIG_SOUL_CANDLE to DnDBigBlocks.BIG_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_WHITE_SOUL_CANDLE to DnDBigBlocks.BIG_WHITE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE to DnDBigBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_GRAY_SOUL_CANDLE to DnDBigBlocks.BIG_GRAY_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_BLACK_SOUL_CANDLE to DnDBigBlocks.BIG_BLACK_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_BROWN_SOUL_CANDLE to DnDBigBlocks.BIG_BROWN_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_RED_SOUL_CANDLE to DnDBigBlocks.BIG_RED_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_ORANGE_SOUL_CANDLE to DnDBigBlocks.BIG_ORANGE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_YELLOW_SOUL_CANDLE to DnDBigBlocks.BIG_YELLOW_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_LIME_SOUL_CANDLE to DnDBigBlocks.BIG_LIME_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_GREEN_SOUL_CANDLE to DnDBigBlocks.BIG_GREEN_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_CYAN_SOUL_CANDLE to DnDBigBlocks.BIG_CYAN_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE to DnDBigBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_BLUE_SOUL_CANDLE to DnDBigBlocks.BIG_BLUE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_PURPLE_SOUL_CANDLE to DnDBigBlocks.BIG_PURPLE_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_MAGENTA_SOUL_CANDLE to DnDBigBlocks.BIG_MAGENTA_SOUL_CANDLE_CAKE),
        (DnDBigBlocks.BIG_PINK_SOUL_CANDLE to DnDBigBlocks.BIG_PINK_SOUL_CANDLE_CAKE),
    )
    val bigTallCandles = listOf<Block>(
      /*  DnDBigBlocks.BIG_TALL_CANDLE,
        DnDBigBlocks.BIG_TALL_WHITE_CANDLE,
        DnDBigBlocks.BIG_TALL_LIGHT_GRAY_CANDLE,
        DnDBigBlocks.BIG_TALL_GRAY_CANDLE,
        DnDBigBlocks.BIG_TALL_BLACK_CANDLE,
        DnDBigBlocks.BIG_TALL_BROWN_CANDLE,
        DnDBigBlocks.BIG_TALL_RED_CANDLE,
        DnDBigBlocks.BIG_TALL_ORANGE_CANDLE,
        DnDBigBlocks.BIG_TALL_YELLOW_CANDLE ,
        DnDBigBlocks.BIG_TALL_LIME_CANDLE ,
        DnDBigBlocks.BIG_TALL_GREEN_CANDLE,
        DnDBigBlocks.BIG_TALL_CYAN_CANDLE ,
        DnDBigBlocks.BIG_TALL_LIGHT_BLUE_CANDLE,
        DnDBigBlocks.BIG_TALL_BLUE_CANDLE,
        DnDBigBlocks.BIG_TALL_PURPLE_CANDLE,
        DnDBigBlocks.BIG_TALL_MAGENTA_CANDLE,
        DnDBigBlocks.BIG_TALL_PINK_CANDLE*/
    )
    val bigTallSoulCandles = listOf<Block>(
      /*  DnDBigBlocks.BIG_TALL_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_WHITE_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_LIGHT_GRAY_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_GRAY_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_BLACK_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_BROWN_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_RED_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_ORANGE_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_YELLOW_SOUL_CANDLE ,
        DnDBigBlocks.BIG_TALL_LIME_SOUL_CANDLE ,
        DnDBigBlocks.BIG_TALL_GREEN_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_CYAN_SOUL_CANDLE ,
        DnDBigBlocks.BIG_TALL_LIGHT_BLUE_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_BLUE_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_PURPLE_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_MAGENTA_SOUL_CANDLE,
        DnDBigBlocks.BIG_TALL_PINK_SOUL_CANDLE*/
    )
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
        DnDWoodBlocks.OAK_LEAF_PILE ,
        DnDWoodBlocks.SPRUCE_LEAF_PILE ,
        DnDWoodBlocks.BIRCH_LEAF_PILE ,
        DnDWoodBlocks.JUNGLE_LEAF_PILE ,
        DnDWoodBlocks.ACACIA_LEAF_PILE ,
        DnDWoodBlocks.DARK_OAK_LEAF_PILE,
        DnDWoodBlocks.MANGROVE_LEAF_PILE ,
        DnDWoodBlocks.CHERRY_LEAF_PILE,
        DnDWoodBlocks.AZALEA_LEAF_PILE,
        DnDWoodBlocks.FLOWERING_AZALEA_LEAF_PILE ,
        DnDWoodBlocks.CASCADE_LEAF_PILE,
//        DnDWoodBlocks.GALLERY_MAPLE_LEAF_PILE,
        DnDWoodBlocks.GOLDEN_BIRCH_LEAF_PILE
    )
    val flowerbedBlocks = listOf(
        DnDFloraBlocks.WILD_PETALS,
        DnDFloraBlocks.WHITE_PETALS,
        DnDFloraBlocks.RED_PETALS,
        DnDFloraBlocks.ORANGE_PETALS,
        DnDFloraBlocks.BLUE_PETALS
    )
    val vivionbedBlocks = listOf(
        DnDFloraBlocks.CRIMSON_VIVIONS,
        DnDFloraBlocks.WARPED_VIVIONS
    )
}
