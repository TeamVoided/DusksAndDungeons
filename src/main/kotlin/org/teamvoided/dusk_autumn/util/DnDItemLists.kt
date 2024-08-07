package net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util

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
    val cascadeSigns = listOf(
        DnDItems.CASCADE_SIGN,
        DnDItems.CASCADE_HANGING_SIGN
    )

    //    val pineWood = listOf(
////        DuskBlocks.PINE_LOG,
////        DuskBlocks.PINE_WOOD,
////        DuskBlocks.STRIPPED_PINE_LOG,
////        DuskBlocks.STRIPPED_PINE_WOOD,
//        DnDBlocks.PINE_PLANKS,
//        DnDBlocks.PINE_STAIRS,
//        DnDBlocks.PINE_SLAB,
//        DnDBlocks.PINE_FENCE,
//        DnDBlocks.PINE_FENCE_GATE,
////        DuskItems.PINE_DOOR,
////        DuskBlocks.PINE_TRAPDOOR,
////        DuskBlocks.PINE_PRESSURE_PLATE,
////        DuskBlocks.PINE_BUTTON
//    ).toItems()
    val bonewoodWood = listOf(
        DnDBlocks.BONEWOOD_PLANKS,
        DnDBlocks.BONEWOOD_STAIRS,
        DnDBlocks.BONEWOOD_SLAB,
        DnDBlocks.BONEWOOD_FENCE,
        DnDBlocks.BONEWOOD_FENCE_GATE,
        DnDBlocks.BONEWOOD_DOOR,
        DnDBlocks.BONEWOOD_TRAPDOOR,
        DnDBlocks.WITHERING_BONEWOOD_PLANKS,
        DnDBlocks.WITHERING_BONEWOOD_STAIRS,
        DnDBlocks.WITHERING_BONEWOOD_SLAB,
        DnDBlocks.WITHERING_BONEWOOD_FENCE,
        DnDBlocks.WITHERING_BONEWOOD_FENCE_GATE,
        DnDBlocks.WITHERING_BONEWOOD_DOOR,
        DnDBlocks.WITHERING_BONEWOOD_TRAPDOOR,
    ).toItems()
    val polishedStone = listOf(
        DnDBlocks.POLISHED_STONE,
        DnDBlocks.POLISHED_STONE_STAIRS,
        DnDBlocks.POLISHED_STONE_SLAB,
        DnDBlocks.POLISHED_STONE_WALL,
    ).toItems()
    val mossyPolishedStone = listOf(
        DnDBlocks.MOSSY_POLISHED_STONE,
        DnDBlocks.MOSSY_POLISHED_STONE_STAIRS,
        DnDBlocks.MOSSY_POLISHED_STONE_SLAB,
        DnDBlocks.MOSSY_POLISHED_STONE_WALL,
    ).toItems()
    val overgrownCobblestone = listOf(
        DnDBlocks.OVERGROWN_COBBLESTONE,
        DnDBlocks.OVERGROWN_COBBLESTONE_STAIRS,
        DnDBlocks.OVERGROWN_COBBLESTONE_SLAB,
        DnDBlocks.OVERGROWN_COBBLESTONE_WALL,
    ).toItems()
    val overgrownStoneBricks = listOf(
        DnDBlocks.OVERGROWN_STONE_BRICKS,
        DnDBlocks.OVERGROWN_STONE_BRICK_STAIRS,
        DnDBlocks.OVERGROWN_STONE_BRICK_SLAB,
        DnDBlocks.OVERGROWN_STONE_BRICK_WALL,
    ).toItems()
    val snowyStoneBricks = listOf(
        DnDBlocks.SNOWY_STONE_BRICKS,
        DnDBlocks.SNOWY_STONE_BRICK_STAIRS,
        DnDBlocks.SNOWY_STONE_BRICK_SLAB,
        DnDBlocks.SNOWY_STONE_BRICK_WALL,
    ).toItems()
    val bigCandles = listOf(
        DnDBlocks.BIG_CANDLE,
        DnDBlocks.BIG_WHITE_CANDLE,
        DnDBlocks.BIG_LIGHT_GRAY_CANDLE,
        DnDBlocks.BIG_GRAY_CANDLE,
        DnDBlocks.BIG_BLACK_CANDLE,
        DnDBlocks.BIG_BROWN_CANDLE,
        DnDBlocks.BIG_RED_CANDLE,
        DnDBlocks.BIG_ORANGE_CANDLE,
        DnDBlocks.BIG_YELLOW_CANDLE,
        DnDBlocks.BIG_LIME_CANDLE,
        DnDBlocks.BIG_GREEN_CANDLE,
        DnDBlocks.BIG_CYAN_CANDLE,
        DnDBlocks.BIG_BLUE_CANDLE,
        DnDBlocks.BIG_LIGHT_BLUE_CANDLE,
        DnDBlocks.BIG_PURPLE_CANDLE,
        DnDBlocks.BIG_MAGENTA_CANDLE,
        DnDBlocks.BIG_PINK_CANDLE
    ).toItems()
    val soulCandles = listOf(
        DnDBlocks.SOUL_CANDLE,
        DnDBlocks.WHITE_SOUL_CANDLE,
        DnDBlocks.LIGHT_GRAY_SOUL_CANDLE,
        DnDBlocks.GRAY_SOUL_CANDLE,
        DnDBlocks.BLACK_SOUL_CANDLE,
        DnDBlocks.BROWN_SOUL_CANDLE,
        DnDBlocks.RED_SOUL_CANDLE,
        DnDBlocks.ORANGE_SOUL_CANDLE,
        DnDBlocks.YELLOW_SOUL_CANDLE,
        DnDBlocks.LIME_SOUL_CANDLE,
        DnDBlocks.GREEN_SOUL_CANDLE,
        DnDBlocks.CYAN_SOUL_CANDLE,
        DnDBlocks.BLUE_SOUL_CANDLE,
        DnDBlocks.LIGHT_BLUE_SOUL_CANDLE,
        DnDBlocks.PURPLE_SOUL_CANDLE,
        DnDBlocks.MAGENTA_SOUL_CANDLE,
        DnDBlocks.PINK_SOUL_CANDLE
    ).toItems()
    val bigSoulCandles = listOf(
        DnDBlocks.BIG_SOUL_CANDLE,
        DnDBlocks.BIG_WHITE_SOUL_CANDLE,
        DnDBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE,
        DnDBlocks.BIG_GRAY_SOUL_CANDLE,
        DnDBlocks.BIG_BLACK_SOUL_CANDLE,
        DnDBlocks.BIG_BROWN_SOUL_CANDLE,
        DnDBlocks.BIG_RED_SOUL_CANDLE,
        DnDBlocks.BIG_ORANGE_SOUL_CANDLE,
        DnDBlocks.BIG_YELLOW_SOUL_CANDLE,
        DnDBlocks.BIG_LIME_SOUL_CANDLE,
        DnDBlocks.BIG_GREEN_SOUL_CANDLE,
        DnDBlocks.BIG_CYAN_SOUL_CANDLE,
        DnDBlocks.BIG_BLUE_SOUL_CANDLE,
        DnDBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE,
        DnDBlocks.BIG_PURPLE_SOUL_CANDLE,
        DnDBlocks.BIG_MAGENTA_SOUL_CANDLE,
        DnDBlocks.BIG_PINK_SOUL_CANDLE
    ).toItems()
    val candles = bigCandles + soulCandles + bigSoulCandles
    val bigItems = listOf(
        DnDBlocks.BIG_CHAIN,
        DnDBlocks.BIG_LANTERN,
        DnDBlocks.BIG_SOUL_LANTERN
    ).toItems() + candles
    val netherrackStuff = listOf(
        DnDBlocks.NETHERRACK_STAIRS,
        DnDBlocks.NETHERRACK_SLAB,
        DnDBlocks.NETHERRACK_WALL
    ).toItems()
    val netherBrickStuff = listOf(
        DnDBlocks.NETHER_BRICK_PILLAR,
        DnDBlocks.POLISHED_NETHER_BRICKS,
        DnDBlocks.POLISHED_NETHER_BRICK_STAIRS,
        DnDBlocks.POLISHED_NETHER_BRICK_SLAB,
        DnDBlocks.POLISHED_NETHER_BRICK_WALL,
    ).toItems()
    val redNetherBrickStuff = listOf(
        DnDBlocks.RED_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_RED_NETHER_BRICKS,
        DnDBlocks.RED_NETHER_BRICK_PILLAR,
        DnDBlocks.POLISHED_RED_NETHER_BRICKS,
        DnDBlocks.POLISHED_RED_NETHER_BRICK_STAIRS,
        DnDBlocks.POLISHED_RED_NETHER_BRICK_SLAB,
        DnDBlocks.POLISHED_RED_NETHER_BRICK_WALL
    ).toItems()
    val mixedRedNetherBrickStuff = listOf(
        DnDBlocks.MIXED_NETHER_BRICKS,
        DnDBlocks.CRACKED_MIXED_NETHER_BRICKS,
        DnDBlocks.MIXED_NETHER_BRICK_STAIRS,
        DnDBlocks.MIXED_NETHER_BRICK_SLAB,
        DnDBlocks.MIXED_NETHER_BRICK_WALL,
        DnDBlocks.MIXED_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_NETHER_BRICKS,
        DnDBlocks.MIXED_NETHER_BRICK_PILLAR
    ).toItems()
    val blueNetherBrickStuff = listOf(
        DnDBlocks.BLUE_NETHER_BRICKS,
        DnDBlocks.CRACKED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICK_STAIRS,
        DnDBlocks.BLUE_NETHER_BRICK_SLAB,
        DnDBlocks.BLUE_NETHER_BRICK_WALL,
        DnDBlocks.BLUE_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICK_PILLAR,
        DnDBlocks.POLISHED_BLUE_NETHER_BRICKS,
        DnDBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS,
        DnDBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB,
        DnDBlocks.POLISHED_BLUE_NETHER_BRICK_WALL
    ).toItems()
    val mixedBlueNetherBrickStuff = listOf(
        DnDBlocks.MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_SLAB,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_WALL,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR
    ).toItems()
    val grayNetherBrickStuff = listOf(
        DnDBlocks.GRAY_NETHER_BRICKS,
        DnDBlocks.CRACKED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_STAIRS,
        DnDBlocks.GRAY_NETHER_BRICK_SLAB,
        DnDBlocks.GRAY_NETHER_BRICK_WALL,
        DnDBlocks.GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_PILLAR,
        DnDBlocks.POLISHED_GRAY_NETHER_BRICKS,
        DnDBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS,
        DnDBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB,
        DnDBlocks.POLISHED_GRAY_NETHER_BRICK_WALL
    ).toItems()
    val mixedGrayNetherBrickStuff = listOf(
        DnDBlocks.MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_SLAB,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_WALL,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR
    ).toItems()
    val blackstoneTools = listOf(
        DnDItems.BLACKSTONE_SWORD,
        DnDItems.BLACKSTONE_PICKAXE,
        DnDItems.BLACKSTONE_AXE,
        DnDItems.BLACKSTONE_SHOVEL,
        DnDItems.BLACKSTONE_HOE
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
        DnDBlocks.WARPED_STEM_PILE,
    ).toItems()
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
    ).toItems()
    val moonberry = listOf(
        DnDItems.MOONBERRY_VINELET,
        DnDBlocks.MOONBERRY_VINE,
        DnDItems.MOONBERRIES
    ).toItems()
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
