package org.teamvoided.dusk_autumn.item

import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems

object DuskItemLists {
    val cascadeWood = listOf(
        DuskBlocks.CASCADE_LOG.asItem(),
        DuskBlocks.CASCADE_WOOD.asItem(),
        DuskBlocks.STRIPPED_CASCADE_LOG.asItem(),
        DuskBlocks.STRIPPED_CASCADE_WOOD.asItem(),
        DuskBlocks.CASCADE_PLANKS.asItem(),
        DuskBlocks.CASCADE_STAIRS.asItem(),
        DuskBlocks.CASCADE_SLAB.asItem(),
        DuskBlocks.CASCADE_FENCE.asItem(),
        DuskBlocks.CASCADE_FENCE_GATE.asItem(),
        DuskItems.CASCADE_DOOR,
        DuskBlocks.CASCADE_TRAPDOOR.asItem(),
        DuskBlocks.CASCADE_PRESSURE_PLATE.asItem(),
        DuskBlocks.CASCADE_BUTTON.asItem()
    )
    val pineWood = listOf(
//        DuskBlocks.PINE_LOG.asItem(),
//        DuskBlocks.PINE_WOOD.asItem(),
//        DuskBlocks.STRIPPED_PINE_LOG.asItem(),
//        DuskBlocks.STRIPPED_PINE_WOOD.asItem(),
        DuskBlocks.PINE_PLANKS.asItem(),
        DuskBlocks.PINE_STAIRS.asItem(),
        DuskBlocks.PINE_SLAB.asItem(),
        DuskBlocks.PINE_FENCE.asItem(),
        DuskBlocks.PINE_FENCE_GATE.asItem(),
//        DuskItems.PINE_DOOR,
//        DuskBlocks.PINE_TRAPDOOR.asItem(),
//        DuskBlocks.PINE_PRESSURE_PLATE.asItem(),
//        DuskBlocks.PINE_BUTTON.asItem()
    )
    val cascadeSigns = listOf(
        DuskItems.CASCADE_SIGN,
        DuskItems.CASCADE_HANGING_SIGN
    )
    val bigCandles = listOf(
        DuskBlocks.BIG_CANDLE.asItem(),
        DuskBlocks.BIG_WHITE_CANDLE.asItem(),
        DuskBlocks.BIG_LIGHT_GRAY_CANDLE.asItem(),
        DuskBlocks.BIG_GRAY_CANDLE.asItem(),
        DuskBlocks.BIG_BLACK_CANDLE.asItem(),
        DuskBlocks.BIG_BROWN_CANDLE.asItem(),
        DuskBlocks.BIG_RED_CANDLE.asItem(),
        DuskBlocks.BIG_ORANGE_CANDLE.asItem(),
        DuskBlocks.BIG_YELLOW_CANDLE.asItem(),
        DuskBlocks.BIG_LIME_CANDLE.asItem(),
        DuskBlocks.BIG_GREEN_CANDLE.asItem(),
        DuskBlocks.BIG_CYAN_CANDLE.asItem(),
        DuskBlocks.BIG_BLUE_CANDLE.asItem(),
        DuskBlocks.BIG_LIGHT_BLUE_CANDLE.asItem(),
        DuskBlocks.BIG_PURPLE_CANDLE.asItem(),
        DuskBlocks.BIG_MAGENTA_CANDLE.asItem(),
        DuskBlocks.BIG_PINK_CANDLE.asItem()
    )
    val soulCandles = listOf(
        DuskBlocks.SOUL_CANDLE.asItem(),
        DuskBlocks.WHITE_SOUL_CANDLE.asItem(),
        DuskBlocks.LIGHT_GRAY_SOUL_CANDLE.asItem(),
        DuskBlocks.GRAY_SOUL_CANDLE.asItem(),
        DuskBlocks.BLACK_SOUL_CANDLE.asItem(),
        DuskBlocks.BROWN_SOUL_CANDLE.asItem(),
        DuskBlocks.RED_SOUL_CANDLE.asItem(),
        DuskBlocks.ORANGE_SOUL_CANDLE.asItem(),
        DuskBlocks.YELLOW_SOUL_CANDLE.asItem(),
        DuskBlocks.LIME_SOUL_CANDLE.asItem(),
        DuskBlocks.GREEN_SOUL_CANDLE.asItem(),
        DuskBlocks.CYAN_SOUL_CANDLE.asItem(),
        DuskBlocks.BLUE_SOUL_CANDLE.asItem(),
        DuskBlocks.LIGHT_BLUE_SOUL_CANDLE.asItem(),
        DuskBlocks.PURPLE_SOUL_CANDLE.asItem(),
        DuskBlocks.MAGENTA_SOUL_CANDLE.asItem(),
        DuskBlocks.PINK_SOUL_CANDLE.asItem()
    )
    val bigSoulCandles = listOf(
        DuskBlocks.BIG_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_WHITE_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_GRAY_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_BLACK_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_BROWN_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_RED_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_ORANGE_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_YELLOW_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_LIME_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_GREEN_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_CYAN_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_BLUE_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_PURPLE_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_MAGENTA_SOUL_CANDLE.asItem(),
        DuskBlocks.BIG_PINK_SOUL_CANDLE.asItem()
    )
    val candles = bigCandles + soulCandles + bigSoulCandles
    val bigItems =listOf(
        DuskBlocks.BIG_CHAIN.asItem(),
        DuskBlocks.BIG_LANTERN.asItem(),
        DuskBlocks.BIG_SOUL_LANTERN.asItem()
    ) + candles
    val netherrackStuff = listOf(
        DuskBlocks.NETHERRACK_STAIRS.asItem(),
        DuskBlocks.NETHERRACK_SLAB.asItem(),
        DuskBlocks.NETHERRACK_WALL.asItem()
    )
    val netherBrickStuff = listOf(
        DuskBlocks.NETHER_BRICK_PILLAR.asItem(),
        DuskBlocks.POLISHED_NETHER_BRICKS.asItem(),
        DuskBlocks.POLISHED_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.POLISHED_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.POLISHED_NETHER_BRICK_WALL.asItem(),
    )
    val redNetherBrickStuff = listOf(
        DuskBlocks.RED_NETHER_BRICK_FENCE.asItem(),
        DuskBlocks.CHISELED_RED_NETHER_BRICKS.asItem(),
        DuskBlocks.RED_NETHER_BRICK_PILLAR.asItem(),
        DuskBlocks.POLISHED_RED_NETHER_BRICKS.asItem(),
        DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL.asItem()
    )
    val mixedNetherBrickStuff = listOf(
        DuskBlocks.MIXED_NETHER_BRICKS.asItem(),
        DuskBlocks.CRACKED_MIXED_NETHER_BRICKS.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_WALL.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_FENCE.asItem(),
        DuskBlocks.CHISELED_MIXED_NETHER_BRICKS.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_PILLAR.asItem()
    )
    val blueNetherBrickStuff = listOf(
        DuskBlocks.BLUE_NETHER_BRICKS.asItem(),
        DuskBlocks.CRACKED_BLUE_NETHER_BRICKS.asItem(),
        DuskBlocks.BLUE_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.BLUE_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.BLUE_NETHER_BRICK_WALL.asItem(),
        DuskBlocks.BLUE_NETHER_BRICK_FENCE.asItem(),
        DuskBlocks.CHISELED_BLUE_NETHER_BRICKS.asItem(),
        DuskBlocks.BLUE_NETHER_BRICK_PILLAR.asItem(),
        DuskBlocks.POLISHED_BLUE_NETHER_BRICKS.asItem(),
        DuskBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.POLISHED_BLUE_NETHER_BRICK_WALL.asItem()
    )
    val mixedBlueNetherBrickStuff = listOf(
        DuskBlocks.MIXED_BLUE_NETHER_BRICKS.asItem(),
        DuskBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS.asItem(),
        DuskBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.MIXED_BLUE_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.MIXED_BLUE_NETHER_BRICK_WALL.asItem(),
        DuskBlocks.MIXED_BLUE_NETHER_BRICK_FENCE.asItem(),
        DuskBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS.asItem(),
        DuskBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR.asItem()
    )
    val grayNetherBrickStuff = listOf(
        DuskBlocks.GRAY_NETHER_BRICKS.asItem(),
        DuskBlocks.CRACKED_GRAY_NETHER_BRICKS.asItem(),
        DuskBlocks.GRAY_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.GRAY_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.GRAY_NETHER_BRICK_WALL.asItem(),
        DuskBlocks.GRAY_NETHER_BRICK_FENCE.asItem(),
        DuskBlocks.CHISELED_GRAY_NETHER_BRICKS.asItem(),
        DuskBlocks.GRAY_NETHER_BRICK_PILLAR.asItem(),
        DuskBlocks.POLISHED_GRAY_NETHER_BRICKS.asItem(),
        DuskBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.POLISHED_GRAY_NETHER_BRICK_WALL.asItem()
    )
    val mixedGrayNetherBrickStuff = listOf(
        DuskBlocks.MIXED_GRAY_NETHER_BRICKS.asItem(),
        DuskBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS.asItem(),
        DuskBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.MIXED_GRAY_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.MIXED_GRAY_NETHER_BRICK_WALL.asItem(),
        DuskBlocks.MIXED_GRAY_NETHER_BRICK_FENCE.asItem(),
        DuskBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS.asItem(),
        DuskBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR.asItem()
    )
    val blackstoneTools = listOf(
        DuskItems.BLACKSTONE_SWORD,
        DuskItems.BLACKSTONE_PICKAXE,
        DuskItems.BLACKSTONE_AXE,
        DuskItems.BLACKSTONE_SHOVEL,
        DuskItems.BLACKSTONE_HOE
    )
    val overgrownCobblestone = listOf(
        DuskBlocks.OVERGROWN_COBBLESTONE.asItem(),
        DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS.asItem(),
        DuskBlocks.OVERGROWN_COBBLESTONE_SLAB.asItem(),
        DuskBlocks.OVERGROWN_COBBLESTONE_WALL.asItem(),
    )
    val overgrownStoneBricks = listOf(
        DuskBlocks.OVERGROWN_STONE_BRICKS.asItem(),
        DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS.asItem(),
        DuskBlocks.OVERGROWN_STONE_BRICK_SLAB.asItem(),
        DuskBlocks.OVERGROWN_STONE_BRICK_WALL.asItem(),
    )
    val logPiles = listOf(
        DuskBlocks.OAK_LOG_PILE.asItem(),
        DuskBlocks.SPRUCE_LOG_PILE.asItem(),
        DuskBlocks.BIRCH_LOG_PILE.asItem(),
        DuskBlocks.JUNGLE_LOG_PILE.asItem(),
        DuskBlocks.ACACIA_LOG_PILE.asItem(),
        DuskBlocks.DARK_OAK_LOG_PILE.asItem(),
        DuskBlocks.MANGROVE_LOG_PILE.asItem(),
        DuskBlocks.CHERRY_LOG_PILE.asItem(),
        DuskBlocks.CASCADE_LOG_PILE.asItem(),
    )
    val leafPiles = listOf(
        DuskBlocks.OAK_LEAF_PILE.asItem(),
        DuskBlocks.SPRUCE_LEAF_PILE.asItem(),
        DuskBlocks.BIRCH_LEAF_PILE.asItem(),
        DuskBlocks.JUNGLE_LEAF_PILE.asItem(),
        DuskBlocks.ACACIA_LEAF_PILE.asItem(),
        DuskBlocks.DARK_OAK_LEAF_PILE.asItem(),
        DuskBlocks.MANGROVE_LEAF_PILE.asItem(),
        DuskBlocks.CHERRY_LEAF_PILE.asItem(),
        DuskBlocks.AZALEA_LEAF_PILE.asItem(),
        DuskBlocks.FLOWERING_AZALEA_LEAF_PILE.asItem(),
        DuskBlocks.CASCADE_LEAF_PILE.asItem(),
        DuskBlocks.GOLDEN_BIRCH_LEAF_PILE.asItem(),
    )
    val moonberry = listOf(
        DuskItems.MOONBERRY_VINELET,
        DuskBlocks.MOONBERRY_VINE.asItem(),
        DuskItems.MOONBERRIES
    )
    val overlayBlocks = listOf(
        DuskBlocks.ROCKY_GRASS.asItem(),
        DuskBlocks.ROCKY_PODZOL.asItem(),
        DuskBlocks.ROCKY_MYCELIUM.asItem(),
        DuskBlocks.ROCKY_DIRT_PATH.asItem(),
        DuskBlocks.ROCKY_DIRT.asItem(),
        DuskBlocks.ROCKY_COARSE_DIRT.asItem(),
        DuskBlocks.ROCKY_MUD.asItem(),
        DuskBlocks.ROCKY_SNOW.asItem(),
        DuskBlocks.ROCKY_GRAVEL.asItem(),
        DuskBlocks.ROCKY_SAND.asItem(),
        DuskBlocks.ROCKY_RED_SAND.asItem(),
        DuskBlocks.ROCKY_SOUL_SAND.asItem(),
        DuskBlocks.ROCKY_SOUL_SOIL.asItem(),

        DuskBlocks.SLATED_GRASS.asItem(),
        DuskBlocks.SLATED_PODZOL.asItem(),
        DuskBlocks.SLATED_MYCELIUM.asItem(),
        DuskBlocks.SLATED_DIRT_PATH.asItem(),
        DuskBlocks.SLATED_DIRT.asItem(),
        DuskBlocks.SLATED_COARSE_DIRT.asItem(),
        DuskBlocks.SLATED_MUD.asItem(),
        DuskBlocks.SLATED_SNOW.asItem(),
        DuskBlocks.SLATED_GRAVEL.asItem(),
        DuskBlocks.SLATED_SAND.asItem(),
        DuskBlocks.SLATED_RED_SAND.asItem(),
        DuskBlocks.SLATED_SOUL_SAND.asItem(),
        DuskBlocks.SLATED_SOUL_SOIL.asItem(),

        DuskBlocks.BLACKSTONE_GRASS.asItem(),
        DuskBlocks.BLACKSTONE_PODZOL.asItem(),
        DuskBlocks.BLACKSTONE_MYCELIUM.asItem(),
        DuskBlocks.BLACKSTONE_DIRT_PATH.asItem(),
        DuskBlocks.BLACKSTONE_DIRT.asItem(),
        DuskBlocks.BLACKSTONE_COARSE_DIRT.asItem(),
        DuskBlocks.BLACKSTONE_MUD.asItem(),
        DuskBlocks.BLACKSTONE_SNOW.asItem(),
        DuskBlocks.BLACKSTONE_GRAVEL.asItem(),
        DuskBlocks.BLACKSTONE_SAND.asItem(),
        DuskBlocks.BLACKSTONE_RED_SAND.asItem(),
        DuskBlocks.BLACKSTONE_SOUL_SAND.asItem(),
        DuskBlocks.BLACKSTONE_SOUL_SOIL.asItem()
    )
}