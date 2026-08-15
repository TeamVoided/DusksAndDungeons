package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

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
    val planks = listOf(
        Blocks.OAK_PLANKS,
        Blocks.SPRUCE_PLANKS,
        Blocks.BIRCH_PLANKS,
        Blocks.JUNGLE_PLANKS,
        Blocks.ACACIA_PLANKS,
        Blocks.DARK_OAK_PLANKS,
        Blocks.MANGROVE_PLANKS,
        Blocks.CHERRY_PLANKS,
        DnDBlocks.CASCADE_PLANKS,
        Blocks.CRIMSON_PLANKS,
        Blocks.WARPED_PLANKS,
    )
    val plankSlabs = listOf(
        Blocks.OAK_SLAB,
        Blocks.SPRUCE_SLAB,
        Blocks.BIRCH_SLAB,
        Blocks.JUNGLE_SLAB,
        Blocks.ACACIA_SLAB,
        Blocks.DARK_OAK_SLAB,
        Blocks.MANGROVE_SLAB,
        Blocks.CHERRY_SLAB,
        DnDBlocks.CASCADE_SLAB,
        Blocks.CRIMSON_SLAB,
        Blocks.WARPED_SLAB,
    )
    val plankWalls = listOf(
        DnDBlocks.OAK_WALL,
        DnDBlocks.SPRUCE_WALL,
        DnDBlocks.BIRCH_WALL,
        DnDBlocks.JUNGLE_WALL,
        DnDBlocks.ACACIA_WALL,
        DnDBlocks.DARK_OAK_WALL,
        DnDBlocks.MANGROVE_WALL,
        DnDBlocks.CHERRY_WALL,
        DnDBlocks.CASCADE_WALL,
        DnDBlocks.CRIMSON_WALL,
        DnDBlocks.WARPED_WALL,
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
        DnDBlocks.GOLDEN_BIRCH_LEAVES,
        DnDBlocks.OVERGROWTH_LEAVES
    )
    val bigCandles = DnDBlocks.BIG_CANDLES.zip(DnDBlocks.BIG_CANDLE_CAKES)
    val soulCandles = DnDBlocks.SOUL_CANDLES.zip(DnDBlocks.SOUL_CANDLE_CAKES)
    val bigSoulCandles = DnDBlocks.BIG_SOUL_CANDLES.zip(DnDBlocks.BIG_SOUL_CANDLE_CAKES)
    val candelabras = DnDBlocks.CANDELABRAS.toList()
    val soulCandelabras = DnDBlocks.SOUL_CANDELABRAS.toList()
    val allCandelabras = candelabras + soulCandelabras

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
    val stripedLogPiles = listOf(
        DnDBlocks.STRIPPED_OAK_LOG_PILE,
        DnDBlocks.STRIPPED_SPRUCE_LOG_PILE,
        DnDBlocks.STRIPPED_BIRCH_LOG_PILE,
        DnDBlocks.STRIPPED_JUNGLE_LOG_PILE,
        DnDBlocks.STRIPPED_ACACIA_LOG_PILE,
        DnDBlocks.STRIPPED_DARK_OAK_LOG_PILE,
        DnDBlocks.STRIPPED_MANGROVE_LOG_PILE,
        DnDBlocks.STRIPPED_CHERRY_LOG_PILE,
        DnDBlocks.STRIPPED_CASCADE_LOG_PILE,
        DnDBlocks.STRIPPED_CRIMSON_STEM_PILE,
        DnDBlocks.STRIPPED_WARPED_STEM_PILE
    )
    val leafPiles = listOf(
        DnDBlocks.OAK_LEAF_PILE,
        DnDBlocks.SPRUCE_LEAF_PILE,
        DnDBlocks.BIRCH_LEAF_PILE,
        DnDBlocks.JUNGLE_LEAF_PILE,
        DnDBlocks.ACACIA_LEAF_PILE,
        DnDBlocks.DARK_OAK_LEAF_PILE,
        DnDBlocks.MANGROVE_LEAF_PILE,
        DnDBlocks.CHERRY_LEAF_PILE,
        DnDBlocks.AZALEA_LEAF_PILE,
        DnDBlocks.FLOWERING_AZALEA_LEAF_PILE,
        DnDBlocks.CASCADE_LEAF_PILE,
        DnDBlocks.GOLDEN_BIRCH_LEAF_PILE,
        DnDBlocks.OVERGROWTH_LEAF_PILE
    )
    val flowerbedBlocks = listOf(
        DnDBlocks.COLD_WILDFLOWER,
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
