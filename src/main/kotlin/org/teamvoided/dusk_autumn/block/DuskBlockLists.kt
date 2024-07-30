package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Blocks
import org.teamvoided.dusk_autumn.init.DuskBlocks

object DuskBlockLists {
    val candles = listOf(
        (DuskBlocks.BIG_CANDLE to null),
        (DuskBlocks.BIG_WHITE_CANDLE to null),
        (DuskBlocks.BIG_LIGHT_GRAY_CANDLE to null),
        (DuskBlocks.BIG_GRAY_CANDLE to null),
        (DuskBlocks.BIG_BLACK_CANDLE to null),
        (DuskBlocks.BIG_BROWN_CANDLE to null),
        (DuskBlocks.BIG_RED_CANDLE to null),
        (DuskBlocks.BIG_ORANGE_CANDLE to null),
        (DuskBlocks.BIG_YELLOW_CANDLE to null),
        (DuskBlocks.BIG_LIME_CANDLE to null),
        (DuskBlocks.BIG_GREEN_CANDLE to null),
        (DuskBlocks.BIG_CYAN_CANDLE to null),
        (DuskBlocks.BIG_LIGHT_BLUE_CANDLE to null),
        (DuskBlocks.BIG_BLUE_CANDLE to null),
        (DuskBlocks.BIG_PURPLE_CANDLE to null),
        (DuskBlocks.BIG_MAGENTA_CANDLE to null),
        (DuskBlocks.BIG_PINK_CANDLE to null),
    )
    val logPiles = listOf(
        (DuskBlocks.OAK_LOG_PILE to Blocks.OAK_LOG),
        (DuskBlocks.SPRUCE_LOG_PILE to Blocks.SPRUCE_LOG),
        (DuskBlocks.BIRCH_LOG_PILE to Blocks.BIRCH_LOG),
        (DuskBlocks.JUNGLE_LOG_PILE to Blocks.JUNGLE_LOG),
        (DuskBlocks.ACACIA_LOG_PILE to Blocks.ACACIA_LOG),
        (DuskBlocks.DARK_OAK_LOG_PILE to Blocks.DARK_OAK_LOG),
        (DuskBlocks.MANGROVE_LOG_PILE to Blocks.MANGROVE_LOG),
        (DuskBlocks.CHERRY_LOG_PILE to Blocks.CHERRY_LOG),
        (DuskBlocks.CASCADE_LOG_PILE to DuskBlocks.CASCADE_LOG)
    )
    val leafPiles = listOf(
        (DuskBlocks.OAK_LEAF_PILE to Blocks.OAK_LEAVES),
        (DuskBlocks.SPRUCE_LEAF_PILE to Blocks.SPRUCE_LEAVES),
        (DuskBlocks.BIRCH_LEAF_PILE to Blocks.BIRCH_LEAVES),
        (DuskBlocks.JUNGLE_LEAF_PILE to Blocks.JUNGLE_LEAVES),
        (DuskBlocks.ACACIA_LEAF_PILE to Blocks.ACACIA_LEAVES),
        (DuskBlocks.DARK_OAK_LEAF_PILE to Blocks.DARK_OAK_LEAVES),
        (DuskBlocks.MANGROVE_LEAF_PILE to Blocks.MANGROVE_LEAVES),
        (DuskBlocks.CHERRY_LEAF_PILE to Blocks.CHERRY_LEAVES),
        (DuskBlocks.AZALEA_LEAF_PILE to Blocks.AZALEA_LEAVES),
        (DuskBlocks.FLOWERING_AZALEA_LEAF_PILE to Blocks.FLOWERING_AZALEA_LEAVES),
        (DuskBlocks.CASCADE_LEAF_PILE to DuskBlocks.CASCADE_LEAVES),
        (DuskBlocks.GOLDEN_BIRCH_LEAF_PILE to DuskBlocks.GOLDEN_BIRCH_LEAVES)
    )
}