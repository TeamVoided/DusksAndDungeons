package org.teamvoided.dusk_autumn.item

import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.util.toItems

object DnDItemLists {
    val cascadeWood = listOf(
        DnDBlocks.CASCADE_LOG,
        DnDBlocks.CASCADE_WOOD,
        DnDBlocks.STRIPPED_CASCADE_LOG,
        DnDBlocks.STRIPPED_CASCADE_WOOD,
        DnDBlocks.CASCADE_PLANKS,
        DnDBlocks.CASCADE_STAIRS,
        DnDBlocks.CASCADE_SLAB,
        DnDBlocks.CASCADE_FENCE,
        DnDBlocks.CASCADE_FENCE_GATE,
        DnDItems.CASCADE_DOOR,
        DnDBlocks.CASCADE_TRAPDOOR,
        DnDBlocks.CASCADE_PRESSURE_PLATE,
        DnDBlocks.CASCADE_BUTTON
    ).toItems()

    val pineWood = listOf(
//        DuskBlocks.PINE_LOG.asItem(),
//        DuskBlocks.PINE_WOOD.asItem(),
//        DuskBlocks.STRIPPED_PINE_LOG.asItem(),
//        DuskBlocks.STRIPPED_PINE_WOOD.asItem(),
        DnDBlocks.PINE_PLANKS.asItem(),
        DnDBlocks.PINE_STAIRS.asItem(),
        DnDBlocks.PINE_SLAB.asItem(),
        DnDBlocks.PINE_FENCE.asItem(),
        DnDBlocks.PINE_FENCE_GATE.asItem(),
//        DuskItems.PINE_DOOR,
//        DuskBlocks.PINE_TRAPDOOR.asItem(),
//        DuskBlocks.PINE_PRESSURE_PLATE.asItem(),
//        DuskBlocks.PINE_BUTTON.asItem()
    )
    val cascadeSigns = listOf(
        DnDItems.CASCADE_SIGN,
        DnDItems.CASCADE_HANGING_SIGN
    )
    val bigCandles = listOf(
        DnDBlocks.BIG_CANDLE.asItem(),
        DnDBlocks.BIG_WHITE_CANDLE.asItem(),
        DnDBlocks.BIG_LIGHT_GRAY_CANDLE.asItem(),
        DnDBlocks.BIG_GRAY_CANDLE.asItem(),
        DnDBlocks.BIG_BLACK_CANDLE.asItem(),
        DnDBlocks.BIG_BROWN_CANDLE.asItem(),
        DnDBlocks.BIG_RED_CANDLE.asItem(),
        DnDBlocks.BIG_ORANGE_CANDLE.asItem(),
        DnDBlocks.BIG_YELLOW_CANDLE.asItem(),
        DnDBlocks.BIG_LIME_CANDLE.asItem(),
        DnDBlocks.BIG_GREEN_CANDLE.asItem(),
        DnDBlocks.BIG_CYAN_CANDLE.asItem(),
        DnDBlocks.BIG_BLUE_CANDLE.asItem(),
        DnDBlocks.BIG_LIGHT_BLUE_CANDLE.asItem(),
        DnDBlocks.BIG_PURPLE_CANDLE.asItem(),
        DnDBlocks.BIG_MAGENTA_CANDLE.asItem(),
        DnDBlocks.BIG_PINK_CANDLE.asItem()
    )
    val soulCandles = listOf(
        DnDBlocks.SOUL_CANDLE.asItem(),
        DnDBlocks.WHITE_SOUL_CANDLE.asItem(),
        DnDBlocks.LIGHT_GRAY_SOUL_CANDLE.asItem(),
        DnDBlocks.GRAY_SOUL_CANDLE.asItem(),
        DnDBlocks.BLACK_SOUL_CANDLE.asItem(),
        DnDBlocks.BROWN_SOUL_CANDLE.asItem(),
        DnDBlocks.RED_SOUL_CANDLE.asItem(),
        DnDBlocks.ORANGE_SOUL_CANDLE.asItem(),
        DnDBlocks.YELLOW_SOUL_CANDLE.asItem(),
        DnDBlocks.LIME_SOUL_CANDLE.asItem(),
        DnDBlocks.GREEN_SOUL_CANDLE.asItem(),
        DnDBlocks.CYAN_SOUL_CANDLE.asItem(),
        DnDBlocks.BLUE_SOUL_CANDLE.asItem(),
        DnDBlocks.LIGHT_BLUE_SOUL_CANDLE.asItem(),
        DnDBlocks.PURPLE_SOUL_CANDLE.asItem(),
        DnDBlocks.MAGENTA_SOUL_CANDLE.asItem(),
        DnDBlocks.PINK_SOUL_CANDLE.asItem()
    )
    val bigSoulCandles = listOf(
        DnDBlocks.BIG_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_WHITE_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_GRAY_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_BLACK_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_BROWN_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_RED_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_ORANGE_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_YELLOW_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_LIME_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_GREEN_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_CYAN_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_BLUE_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_PURPLE_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_MAGENTA_SOUL_CANDLE.asItem(),
        DnDBlocks.BIG_PINK_SOUL_CANDLE.asItem()
    )
    val candles = bigCandles + soulCandles + bigSoulCandles
    val bigItems = listOf(
        DnDBlocks.BIG_CHAIN.asItem(),
        DnDBlocks.BIG_LANTERN.asItem(),
        DnDBlocks.BIG_SOUL_LANTERN.asItem()
    ) + candles
    val netherrackStuff = listOf(
        DnDBlocks.NETHERRACK_STAIRS.asItem(),
        DnDBlocks.NETHERRACK_SLAB.asItem(),
        DnDBlocks.NETHERRACK_WALL.asItem()
    )
    val netherBrickStuff = listOf(
        DnDBlocks.NETHER_BRICK_PILLAR.asItem(),
        DnDBlocks.POLISHED_NETHER_BRICKS.asItem(),
        DnDBlocks.POLISHED_NETHER_BRICK_STAIRS.asItem(),
        DnDBlocks.POLISHED_NETHER_BRICK_SLAB.asItem(),
        DnDBlocks.POLISHED_NETHER_BRICK_WALL.asItem(),
    )
    val redNetherBrickStuff = listOf(
        DnDBlocks.RED_NETHER_BRICK_FENCE.asItem(),
        DnDBlocks.CHISELED_RED_NETHER_BRICKS.asItem(),
        DnDBlocks.RED_NETHER_BRICK_PILLAR.asItem(),
        DnDBlocks.POLISHED_RED_NETHER_BRICKS.asItem(),
        DnDBlocks.POLISHED_RED_NETHER_BRICK_STAIRS.asItem(),
        DnDBlocks.POLISHED_RED_NETHER_BRICK_SLAB.asItem(),
        DnDBlocks.POLISHED_RED_NETHER_BRICK_WALL.asItem()
    )
    val mixedNetherBrickStuff = listOf(
        DnDBlocks.MIXED_NETHER_BRICKS.asItem(),
        DnDBlocks.CRACKED_MIXED_NETHER_BRICKS.asItem(),
        DnDBlocks.MIXED_NETHER_BRICK_STAIRS.asItem(),
        DnDBlocks.MIXED_NETHER_BRICK_SLAB.asItem(),
        DnDBlocks.MIXED_NETHER_BRICK_WALL.asItem(),
        DnDBlocks.MIXED_NETHER_BRICK_FENCE.asItem(),
        DnDBlocks.CHISELED_MIXED_NETHER_BRICKS.asItem(),
        DnDBlocks.MIXED_NETHER_BRICK_PILLAR.asItem()
    )
    val blueNetherBrickStuff = listOf(
        DnDBlocks.BLUE_NETHER_BRICKS.asItem(),
        DnDBlocks.CRACKED_BLUE_NETHER_BRICKS.asItem(),
        DnDBlocks.BLUE_NETHER_BRICK_STAIRS.asItem(),
        DnDBlocks.BLUE_NETHER_BRICK_SLAB.asItem(),
        DnDBlocks.BLUE_NETHER_BRICK_WALL.asItem(),
        DnDBlocks.BLUE_NETHER_BRICK_FENCE.asItem(),
        DnDBlocks.CHISELED_BLUE_NETHER_BRICKS.asItem(),
        DnDBlocks.BLUE_NETHER_BRICK_PILLAR.asItem(),
        DnDBlocks.POLISHED_BLUE_NETHER_BRICKS.asItem(),
        DnDBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS.asItem(),
        DnDBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB.asItem(),
        DnDBlocks.POLISHED_BLUE_NETHER_BRICK_WALL.asItem()
    )
    val mixedBlueNetherBrickStuff = listOf(
        DnDBlocks.MIXED_BLUE_NETHER_BRICKS.asItem(),
        DnDBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS.asItem(),
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS.asItem(),
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_SLAB.asItem(),
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_WALL.asItem(),
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE.asItem(),
        DnDBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS.asItem(),
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR.asItem()
    )
    val grayNetherBrickStuff = listOf(
        DnDBlocks.GRAY_NETHER_BRICKS.asItem(),
        DnDBlocks.CRACKED_GRAY_NETHER_BRICKS.asItem(),
        DnDBlocks.GRAY_NETHER_BRICK_STAIRS.asItem(),
        DnDBlocks.GRAY_NETHER_BRICK_SLAB.asItem(),
        DnDBlocks.GRAY_NETHER_BRICK_WALL.asItem(),
        DnDBlocks.GRAY_NETHER_BRICK_FENCE.asItem(),
        DnDBlocks.CHISELED_GRAY_NETHER_BRICKS.asItem(),
        DnDBlocks.GRAY_NETHER_BRICK_PILLAR.asItem(),
        DnDBlocks.POLISHED_GRAY_NETHER_BRICKS.asItem(),
        DnDBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS.asItem(),
        DnDBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB.asItem(),
        DnDBlocks.POLISHED_GRAY_NETHER_BRICK_WALL.asItem()
    )
    val mixedGrayNetherBrickStuff = listOf(
        DnDBlocks.MIXED_GRAY_NETHER_BRICKS.asItem(),
        DnDBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS.asItem(),
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS.asItem(),
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_SLAB.asItem(),
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_WALL.asItem(),
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE.asItem(),
        DnDBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS.asItem(),
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR.asItem()
    )
    val blackstoneTools = listOf(
        DnDItems.BLACKSTONE_SWORD,
        DnDItems.BLACKSTONE_PICKAXE,
        DnDItems.BLACKSTONE_AXE,
        DnDItems.BLACKSTONE_SHOVEL,
        DnDItems.BLACKSTONE_HOE
    )
    val overgrownCobblestone = listOf(
        DnDBlocks.OVERGROWN_COBBLESTONE.asItem(),
        DnDBlocks.OVERGROWN_COBBLESTONE_STAIRS.asItem(),
        DnDBlocks.OVERGROWN_COBBLESTONE_SLAB.asItem(),
        DnDBlocks.OVERGROWN_COBBLESTONE_WALL.asItem(),
    )
    val overgrownStoneBricks = listOf(
        DnDBlocks.OVERGROWN_STONE_BRICKS.asItem(),
        DnDBlocks.OVERGROWN_STONE_BRICK_STAIRS.asItem(),
        DnDBlocks.OVERGROWN_STONE_BRICK_SLAB.asItem(),
        DnDBlocks.OVERGROWN_STONE_BRICK_WALL.asItem(),
    )
    val logPiles = listOf(
        DnDBlocks.OAK_LOG_PILE.asItem(),
        DnDBlocks.SPRUCE_LOG_PILE.asItem(),
        DnDBlocks.BIRCH_LOG_PILE.asItem(),
        DnDBlocks.JUNGLE_LOG_PILE.asItem(),
        DnDBlocks.ACACIA_LOG_PILE.asItem(),
        DnDBlocks.DARK_OAK_LOG_PILE.asItem(),
        DnDBlocks.MANGROVE_LOG_PILE.asItem(),
        DnDBlocks.CHERRY_LOG_PILE.asItem(),
        DnDBlocks.CASCADE_LOG_PILE.asItem(),
    )
    val leafPiles = listOf(
        DnDBlocks.OAK_LEAF_PILE.asItem(),
        DnDBlocks.SPRUCE_LEAF_PILE.asItem(),
        DnDBlocks.BIRCH_LEAF_PILE.asItem(),
        DnDBlocks.JUNGLE_LEAF_PILE.asItem(),
        DnDBlocks.ACACIA_LEAF_PILE.asItem(),
        DnDBlocks.DARK_OAK_LEAF_PILE.asItem(),
        DnDBlocks.MANGROVE_LEAF_PILE.asItem(),
        DnDBlocks.CHERRY_LEAF_PILE.asItem(),
        DnDBlocks.AZALEA_LEAF_PILE.asItem(),
        DnDBlocks.FLOWERING_AZALEA_LEAF_PILE.asItem(),
        DnDBlocks.CASCADE_LEAF_PILE.asItem(),
        DnDBlocks.GOLDEN_BIRCH_LEAF_PILE.asItem(),
    )
    val moonberry = listOf(
        DnDItems.MOONBERRY_VINELET,
        DnDBlocks.MOONBERRY_VINE.asItem(),
        DnDItems.MOONBERRIES
    )
    val overlayBlocks = listOf(
        DnDBlocks.ROCKY_GRASS,
        DnDBlocks.ROCKY_PODZOL,
        DnDBlocks.ROCKY_MYCELIUM,
        DnDBlocks.ROCKY_DIRT_PATH,
        DnDBlocks.ROCKY_DIRT,
        DnDBlocks.ROCKY_COARSE_DIRT,
        DnDBlocks.ROCKY_MUD,
        DnDBlocks.ROCKY_SNOW,
        DnDBlocks.ROCKY_GRAVEL,
        DnDBlocks.ROCKY_SAND,
        DnDBlocks.ROCKY_RED_SAND,
        DnDBlocks.ROCKY_SOUL_SAND,
        DnDBlocks.ROCKY_SOUL_SOIL,

        DnDBlocks.SLATED_GRASS,
        DnDBlocks.SLATED_PODZOL,
        DnDBlocks.SLATED_MYCELIUM,
        DnDBlocks.SLATED_DIRT_PATH,
        DnDBlocks.SLATED_DIRT,
        DnDBlocks.SLATED_COARSE_DIRT,
        DnDBlocks.SLATED_MUD,
        DnDBlocks.SLATED_SNOW,
        DnDBlocks.SLATED_GRAVEL,
        DnDBlocks.SLATED_SAND,
        DnDBlocks.SLATED_RED_SAND,
        DnDBlocks.SLATED_SOUL_SAND,
        DnDBlocks.SLATED_SOUL_SOIL,

        DnDBlocks.BLACKSTONE_GRASS,
        DnDBlocks.BLACKSTONE_PODZOL,
        DnDBlocks.BLACKSTONE_MYCELIUM,
        DnDBlocks.BLACKSTONE_DIRT_PATH,
        DnDBlocks.BLACKSTONE_DIRT,
        DnDBlocks.BLACKSTONE_COARSE_DIRT,
        DnDBlocks.BLACKSTONE_MUD,
        DnDBlocks.BLACKSTONE_SNOW,
        DnDBlocks.BLACKSTONE_GRAVEL,
        DnDBlocks.BLACKSTONE_SAND,
        DnDBlocks.BLACKSTONE_RED_SAND,
        DnDBlocks.BLACKSTONE_SOUL_SAND,
        DnDBlocks.BLACKSTONE_SOUL_SOIL
    ).toItems()
}
