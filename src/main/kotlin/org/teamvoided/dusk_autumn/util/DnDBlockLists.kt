package net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util

import net.minecraft.block.Blocks
import net.minecraft.item.Items
import org.teamvoided.dusk_autumn.init.DnDBlocks

object DnDBlockLists {
    val coloredBlocks = listOf(
        DnDBlocks.OVERGROWN_COBBLESTONE,
        DnDBlocks.OVERGROWN_COBBLESTONE_STAIRS,
        DnDBlocks.OVERGROWN_COBBLESTONE_SLAB,
        DnDBlocks.OVERGROWN_COBBLESTONE_WALL,
        DnDBlocks.OVERGROWN_STONE_BRICKS,
        DnDBlocks.OVERGROWN_STONE_BRICK_STAIRS,
        DnDBlocks.OVERGROWN_STONE_BRICK_SLAB,
        DnDBlocks.OVERGROWN_STONE_BRICK_WALL,
        DnDBlocks.ROCKY_GRASS,
        DnDBlocks.SLATED_GRASS,
        DnDBlocks.BLACKSTONE_GRASS
    )
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
    val logPiles = listOf(
        (DnDBlocks.OAK_LOG_PILE to Blocks.OAK_LOG),
        (DnDBlocks.SPRUCE_LOG_PILE to Blocks.SPRUCE_LOG),
        (DnDBlocks.BIRCH_LOG_PILE to Blocks.BIRCH_LOG),
        (DnDBlocks.JUNGLE_LOG_PILE to Blocks.JUNGLE_LOG),
        (DnDBlocks.ACACIA_LOG_PILE to Blocks.ACACIA_LOG),
        (DnDBlocks.DARK_OAK_LOG_PILE to Blocks.DARK_OAK_LOG),
        (DnDBlocks.MANGROVE_LOG_PILE to Blocks.MANGROVE_LOG),
        (DnDBlocks.CHERRY_LOG_PILE to Blocks.CHERRY_LOG),
        (DnDBlocks.CASCADE_LOG_PILE to DnDBlocks.CASCADE_LOG),
        (DnDBlocks.CRIMSON_STEM_PILE to Blocks.CRIMSON_STEM),
        (DnDBlocks.WARPED_STEM_PILE to Blocks.WARPED_STEM)
    )
    val leafPiles = listOf(
        (DnDBlocks.OAK_LEAF_PILE to Blocks.OAK_LEAVES),
        (DnDBlocks.SPRUCE_LEAF_PILE to Blocks.SPRUCE_LEAVES),
        (DnDBlocks.BIRCH_LEAF_PILE to Blocks.BIRCH_LEAVES),
        (DnDBlocks.JUNGLE_LEAF_PILE to Blocks.JUNGLE_LEAVES),
        (DnDBlocks.ACACIA_LEAF_PILE to Blocks.ACACIA_LEAVES),
        (DnDBlocks.DARK_OAK_LEAF_PILE to Blocks.DARK_OAK_LEAVES),
        (DnDBlocks.MANGROVE_LEAF_PILE to Blocks.MANGROVE_LEAVES),
        (DnDBlocks.CHERRY_LEAF_PILE to Blocks.CHERRY_LEAVES),
        (DnDBlocks.AZALEA_LEAF_PILE to Blocks.AZALEA_LEAVES),
        (DnDBlocks.FLOWERING_AZALEA_LEAF_PILE to Blocks.FLOWERING_AZALEA_LEAVES),
        (DnDBlocks.CASCADE_LEAF_PILE to DnDBlocks.CASCADE_LEAVES),
        (DnDBlocks.GOLDEN_BIRCH_LEAF_PILE to DnDBlocks.GOLDEN_BIRCH_LEAVES)
    )
}