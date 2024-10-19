package org.teamvoided.dusk_autumn.util

import net.minecraft.item.ItemConvertible
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.*

object DnDItemLists {
    val cascadeWood = listOf<ItemConvertible>(
        DnDWoodBlocks.CASCADE_LOG,
        DnDWoodBlocks.CASCADE_WOOD,
        DnDWoodBlocks.STRIPPED_CASCADE_LOG,
        DnDWoodBlocks.STRIPPED_CASCADE_WOOD,
        DnDWoodBlocks.CASCADE_PLANKS,
        DnDWoodBlocks.CASCADE_STAIRS,
        DnDWoodBlocks.CASCADE_SLAB,
        DnDWoodBlocks.CASCADE_FENCE,
        DnDWoodBlocks.CASCADE_FENCE_GATE,
        DnDItems.CASCADE_DOOR,
        DnDWoodBlocks.CASCADE_TRAPDOOR,
        DnDWoodBlocks.CASCADE_PRESSURE_PLATE,
        DnDWoodBlocks.CASCADE_BUTTON
    )

    val cascadeSigns = listOf(
        DnDItems.CASCADE_SIGN,
        DnDItems.CASCADE_HANGING_SIGN
    )

    //
//    //    val pineWood = listOf(
//////        DuskBlocks.PINE_LOG,
//////        DuskBlocks.PINE_WOOD,
//////        DuskBlocks.STRIPPED_PINE_LOG,
//////        DuskBlocks.STRIPPED_PINE_WOOD,
////        DnDBlocks.PINE_PLANKS,
////        DnDBlocks.PINE_STAIRS,
////        DnDBlocks.PINE_SLAB,
////        DnDBlocks.PINE_FENCE,
////        DnDBlocks.PINE_FENCE_GATE,
//////        DuskItems.PINE_DOOR,
//////        DuskBlocks.PINE_TRAPDOOR,
//////        DuskBlocks.PINE_PRESSURE_PLATE,
//////        DuskBlocks.PINE_BUTTON
////    ).toItems()
//    val bonewoodWood = listOf(
//        DnDBlocks.BONEWOOD_PLANKS,
//        DnDBlocks.BONEWOOD_STAIRS,
//        DnDBlocks.BONEWOOD_SLAB,
//        DnDBlocks.BONEWOOD_FENCE,
//        DnDBlocks.BONEWOOD_FENCE_GATE,
//        DnDBlocks.BONEWOOD_DOOR,
//        DnDBlocks.BONEWOOD_TRAPDOOR,
//        DnDBlocks.WITHERING_BONEWOOD_PLANKS,
//        DnDBlocks.WITHERING_BONEWOOD_STAIRS,
//        DnDBlocks.WITHERING_BONEWOOD_SLAB,
//        DnDBlocks.WITHERING_BONEWOOD_FENCE,
//        DnDBlocks.WITHERING_BONEWOOD_FENCE_GATE,
//        DnDBlocks.WITHERING_BONEWOOD_DOOR,
//        DnDBlocks.WITHERING_BONEWOOD_TRAPDOOR,
//    ).toItems()
    val polishedStone = listOf(
        DnDStoneBlocks.POLISHED_STONE,
        DnDStoneBlocks.POLISHED_STONE_STAIRS,
        DnDStoneBlocks.POLISHED_STONE_SLAB,
        DnDStoneBlocks.POLISHED_STONE_WALL,
    )
    val mossyPolishedStone = listOf(
        DnDStoneBlocks.MOSSY_POLISHED_STONE,
        DnDStoneBlocks.MOSSY_POLISHED_STONE_STAIRS,
        DnDStoneBlocks.MOSSY_POLISHED_STONE_SLAB,
        DnDStoneBlocks.MOSSY_POLISHED_STONE_WALL,
    )
    val overgrownCobblestone = listOf(
        DnDStoneBlocks.OVERGROWN_COBBLESTONE,
        DnDStoneBlocks.OVERGROWN_COBBLESTONE_STAIRS,
        DnDStoneBlocks.OVERGROWN_COBBLESTONE_SLAB,
        DnDStoneBlocks.OVERGROWN_COBBLESTONE_WALL,
    )
    val overgrownStoneBricks = listOf(
        DnDStoneBlocks.OVERGROWN_STONE_BRICKS,
        DnDStoneBlocks.OVERGROWN_STONE_BRICK_STAIRS,
        DnDStoneBlocks.OVERGROWN_STONE_BRICK_SLAB,
        DnDStoneBlocks.OVERGROWN_STONE_BRICK_WALL,
    )

    val snowyStoneBricks = listOf(
        DnDStoneBlocks.SNOWY_STONE_BRICKS,
        DnDStoneBlocks.SNOWY_STONE_BRICK_STAIRS,
        DnDStoneBlocks.SNOWY_STONE_BRICK_SLAB,
        DnDStoneBlocks.SNOWY_STONE_BRICK_WALL,
    )

    val ice = listOf(
        DnDBlocks.ICE_STAIRS,
        DnDBlocks.ICE_SLAB,
        DnDBlocks.ICE_WALL,
//        DnDBlocks.ICE_BRICKS,
//        DnDBlocks.ICE_BRICK_STAIRS,
//        DnDBlocks.ICE_BRICK_SLAB,
//        DnDBlocks.ICE_BRICK_WALL,
        DnDBlocks.PACKED_ICE_STAIRS,
        DnDBlocks.PACKED_ICE_SLAB,
        DnDBlocks.PACKED_ICE_WALL,
//        DnDBlocks.PACKED_ICE_BRICKS,
//        DnDBlocks.PACKED_ICE_BRICK_STAIRS,
//        DnDBlocks.PACKED_ICE_BRICK_SLAB,
//        DnDBlocks.PACKED_ICE_BRICK_WALL,
        DnDBlocks.BLUE_ICE_STAIRS,
        DnDBlocks.BLUE_ICE_SLAB,
        DnDBlocks.BLUE_ICE_WALL,
//        DnDBlocks.BLUE_ICE_BRICKS,
//        DnDBlocks.BLUE_ICE_BRICK_STAIRS,
//        DnDBlocks.BLUE_ICE_BRICK_SLAB,
//        DnDBlocks.BLUE_ICE_BRICK_WALL,
    )

        val bigCandles = listOf(
        DnDBigBlocks.BIG_CANDLE,
        DnDBigBlocks.BIG_WHITE_CANDLE,
        DnDBigBlocks.BIG_LIGHT_GRAY_CANDLE,
        DnDBigBlocks.BIG_GRAY_CANDLE,
        DnDBigBlocks.BIG_BLACK_CANDLE,
        DnDBigBlocks.BIG_BROWN_CANDLE,
        DnDBigBlocks.BIG_RED_CANDLE,
        DnDBigBlocks.BIG_ORANGE_CANDLE,
        DnDBigBlocks.BIG_YELLOW_CANDLE,
        DnDBigBlocks.BIG_LIME_CANDLE,
        DnDBigBlocks.BIG_GREEN_CANDLE,
        DnDBigBlocks.BIG_CYAN_CANDLE,
        DnDBigBlocks.BIG_BLUE_CANDLE,
        DnDBigBlocks.BIG_LIGHT_BLUE_CANDLE,
        DnDBigBlocks.BIG_PURPLE_CANDLE,
        DnDBigBlocks.BIG_MAGENTA_CANDLE,
        DnDBigBlocks.BIG_PINK_CANDLE
    )
    val soulCandles = listOf(
        DnDBigBlocks.SOUL_CANDLE,
        DnDBigBlocks.WHITE_SOUL_CANDLE,
        DnDBigBlocks.LIGHT_GRAY_SOUL_CANDLE,
        DnDBigBlocks.GRAY_SOUL_CANDLE,
        DnDBigBlocks.BLACK_SOUL_CANDLE,
        DnDBigBlocks.BROWN_SOUL_CANDLE,
        DnDBigBlocks.RED_SOUL_CANDLE,
        DnDBigBlocks.ORANGE_SOUL_CANDLE,
        DnDBigBlocks.YELLOW_SOUL_CANDLE,
        DnDBigBlocks.LIME_SOUL_CANDLE,
        DnDBigBlocks.GREEN_SOUL_CANDLE,
        DnDBigBlocks.CYAN_SOUL_CANDLE,
        DnDBigBlocks.BLUE_SOUL_CANDLE,
        DnDBigBlocks.LIGHT_BLUE_SOUL_CANDLE,
        DnDBigBlocks.PURPLE_SOUL_CANDLE,
        DnDBigBlocks.MAGENTA_SOUL_CANDLE,
        DnDBigBlocks.PINK_SOUL_CANDLE
    )
    val bigSoulCandles = listOf(
        DnDBigBlocks.BIG_SOUL_CANDLE,
        DnDBigBlocks.BIG_WHITE_SOUL_CANDLE,
        DnDBigBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE,
        DnDBigBlocks.BIG_GRAY_SOUL_CANDLE,
        DnDBigBlocks.BIG_BLACK_SOUL_CANDLE,
        DnDBigBlocks.BIG_BROWN_SOUL_CANDLE,
        DnDBigBlocks.BIG_RED_SOUL_CANDLE,
        DnDBigBlocks.BIG_ORANGE_SOUL_CANDLE,
        DnDBigBlocks.BIG_YELLOW_SOUL_CANDLE,
        DnDBigBlocks.BIG_LIME_SOUL_CANDLE,
        DnDBigBlocks.BIG_GREEN_SOUL_CANDLE,
        DnDBigBlocks.BIG_CYAN_SOUL_CANDLE,
        DnDBigBlocks.BIG_BLUE_SOUL_CANDLE,
        DnDBigBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE,
        DnDBigBlocks.BIG_PURPLE_SOUL_CANDLE,
        DnDBigBlocks.BIG_MAGENTA_SOUL_CANDLE,
        DnDBigBlocks.BIG_PINK_SOUL_CANDLE
    )
//    val candles = bigCandles + soulCandles + bigSoulCandles
//    val bigItems = listOf(
//        DnDBlocks.BIG_CHAIN,
//        DnDBlocks.BIG_LANTERN,
//        DnDBlocks.BIG_SOUL_LANTERN
//    ).toItems()
    val netherrackStuff = listOf(
        DnDNetherBrickBlocks.NETHERRACK_STAIRS,
        DnDNetherBrickBlocks.NETHERRACK_SLAB,
        DnDNetherBrickBlocks.NETHERRACK_WALL
    )
    val netherBrickStuff = listOf(
        DnDNetherBrickBlocks.NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.POLISHED_NETHER_BRICKS,
        DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_WALL,
    )
    val redNetherBrickStuff = listOf(
        DnDNetherBrickBlocks.RED_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_RED_NETHER_BRICKS,
        DnDNetherBrickBlocks.RED_NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICKS,
        DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_WALL
    )
    val mixedRedNetherBrickStuff = listOf(
        DnDNetherBrickBlocks.MIXED_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_MIXED_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_MIXED_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_NETHER_BRICK_PILLAR
    )
    val blueNetherBrickStuff = listOf(
        DnDNetherBrickBlocks.BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.BLUE_NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_WALL
    )
    val mixedBlueNetherBrickStuff = listOf(
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR
    )
    val grayNetherBrickStuff = listOf(
        DnDNetherBrickBlocks.GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.GRAY_NETHER_BRICK_PILLAR,
        DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_WALL
    )
    val mixedGrayNetherBrickStuff = listOf(
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_SLAB,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_WALL,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_FENCE,
        DnDNetherBrickBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS,
        DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR
    )
    val blackstoneTools = listOf(
        DnDItems.BLACKSTONE_SWORD,
        DnDItems.BLACKSTONE_PICKAXE,
        DnDItems.BLACKSTONE_AXE,
        DnDItems.BLACKSTONE_SHOVEL,
        DnDItems.BLACKSTONE_HOE
    )
    val oakWoodStuff = listOf(
        DnDWoodBlocks.OAK_WOOD_STAIRS,
        DnDWoodBlocks.OAK_WOOD_SLAB,
        DnDWoodBlocks.OAK_WOOD_WALL
    )
    val spruceWoodStuff = listOf(
        DnDWoodBlocks.SPRUCE_WOOD_STAIRS,
        DnDWoodBlocks.SPRUCE_WOOD_SLAB,
        DnDWoodBlocks.SPRUCE_WOOD_WALL
    )
    val birchWoodStuff = listOf(
        DnDWoodBlocks.BIRCH_WOOD_STAIRS,
        DnDWoodBlocks.BIRCH_WOOD_SLAB,
        DnDWoodBlocks.BIRCH_WOOD_WALL
    )
    val jungleWoodStuff = listOf(
        DnDWoodBlocks.JUNGLE_WOOD_STAIRS,
        DnDWoodBlocks.JUNGLE_WOOD_SLAB,
        DnDWoodBlocks.JUNGLE_WOOD_WALL
    )
    val acaciaWoodStuff = listOf(
        DnDWoodBlocks.ACACIA_WOOD_STAIRS,
        DnDWoodBlocks.ACACIA_WOOD_SLAB,
        DnDWoodBlocks.ACACIA_WOOD_WALL
    )
    val darkOakWoodStuff = listOf(
        DnDWoodBlocks.DARK_OAK_WOOD_STAIRS,
        DnDWoodBlocks.DARK_OAK_WOOD_SLAB,
        DnDWoodBlocks.DARK_OAK_WOOD_WALL
    )
    val mangroveWoodStuff = listOf(
        DnDWoodBlocks.MANGROVE_WOOD_STAIRS,
        DnDWoodBlocks.MANGROVE_WOOD_SLAB,
        DnDWoodBlocks.MANGROVE_WOOD_WALL
    )
    val cherryWoodStuff = listOf(
        DnDWoodBlocks.CHERRY_WOOD_STAIRS,
        DnDWoodBlocks.CHERRY_WOOD_SLAB,
        DnDWoodBlocks.CHERRY_WOOD_WALL
    )
    val cascadeWoodStuff = listOf(
        DnDWoodBlocks.CASCADE_WOOD_STAIRS,
        DnDWoodBlocks.CASCADE_WOOD_SLAB,
        DnDWoodBlocks.CASCADE_WOOD_WALL
    )
    val crimsonHyphaeStuff = listOf(
        DnDWoodBlocks.CRIMSON_HYPHAE_STAIRS,
        DnDWoodBlocks.CRIMSON_HYPHAE_SLAB,
        DnDWoodBlocks.CRIMSON_HYPHAE_WALL
    )
    val warpedHyphaeStuff = listOf(
        DnDWoodBlocks.WARPED_HYPHAE_STAIRS,
        DnDWoodBlocks.WARPED_HYPHAE_SLAB,
        DnDWoodBlocks.WARPED_HYPHAE_WALL,
    )
    val woodLists = listOf(
        oakWoodStuff,
        spruceWoodStuff,
        birchWoodStuff,
        jungleWoodStuff,
        acaciaWoodStuff,
        darkOakWoodStuff,
        mangroveWoodStuff,
        cherryWoodStuff,
        cascadeWoodStuff,
        crimsonHyphaeStuff,
        warpedHyphaeStuff
    )

    val woodStuff =
        /*listOf(DnDWoodBlocks.HOLLOW_OAK_LOG, DnDWoodBlocks.HOLLOW_STRIPPED_OAK_LOG) +*/ oakWoodStuff +
            /* DnDBlocks.HOLLOW_SPRUCE_LOG + DnDBlocks.HOLLOW_STRIPPED_SPRUCE_LOG +*/ spruceWoodStuff +
            /*   DnDBlocks.HOLLOW_BIRCH_LOG + DnDBlocks.HOLLOW_STRIPPED_BIRCH_LOG +*/ birchWoodStuff +
            /*   DnDBlocks.HOLLOW_JUNGLE_LOG + DnDBlocks.HOLLOW_STRIPPED_JUNGLE_LOG +*/ jungleWoodStuff +
            /*    DnDBlocks.HOLLOW_ACACIA_LOG + DnDBlocks.HOLLOW_STRIPPED_ACACIA_LOG +*/ acaciaWoodStuff +
            /*     DnDBlocks.HOLLOW_DARK_OAK_LOG + DnDBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG +*/ darkOakWoodStuff +
            /*  DnDBlocks.HOLLOW_MANGROVE_LOG + DnDBlocks.HOLLOW_STRIPPED_MANGROVE_LOG +*/ mangroveWoodStuff +
            /*  DnDBlocks.HOLLOW_CHERRY_LOG + DnDBlocks.HOLLOW_STRIPPED_CHERRY_LOG +*/ cherryWoodStuff +
            /*  DnDBlocks.HOLLOW_CASCADE_LOG + DnDBlocks.HOLLOW_STRIPPED_CASCADE_LOG +*/ cascadeWoodStuff +
            /*  DnDBlocks.HOLLOW_BAMBOO_BLOCK + DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK +*/
            /*  DnDBlocks.HOLLOW_CRIMSON_STEM + DnDBlocks.HOLLOW_STRIPPED_CRIMSON_STEM +*/ crimsonHyphaeStuff +
            /*   DnDBlocks.HOLLOW_WARPED_STEM + DnDBlocks.HOLLOW_STRIPPED_WARPED_STEM +*/ warpedHyphaeStuff

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
        DnDWoodBlocks.BAMBOO_PILE,
        DnDWoodBlocks.STRIPPED_BAMBOO_PILE,
        DnDWoodBlocks.CRIMSON_STEM_PILE,
        DnDWoodBlocks.WARPED_STEM_PILE,
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
        DnDWoodBlocks.GOLDEN_BIRCH_LEAF_PILE,
    )

    /*  val moonberry = listOf(
          DnDItems.MOONBERRY_VINELET,
          DnDFloraBlocks.MOONBERRY_VINE,
          DnDItems.MOONBERRIES
      ).toItems()*/
    val overlayBlocks = listOf(
        DnDOverlayBlocks.ROCKY_GRASS,
        DnDOverlayBlocks.ROCKY_PODZOL,
        DnDOverlayBlocks.ROCKY_MYCELIUM,
        DnDOverlayBlocks.ROCKY_DIRT_PATH,
        DnDOverlayBlocks.ROCKY_DIRT,
        DnDOverlayBlocks.ROCKY_COARSE_DIRT,
        DnDOverlayBlocks.ROCKY_MUD,
        DnDOverlayBlocks.ROCKY_SNOW,
        DnDOverlayBlocks.ROCKY_GRAVEL,
        DnDOverlayBlocks.ROCKY_SAND,
        DnDOverlayBlocks.ROCKY_RED_SAND,
        DnDOverlayBlocks.ROCKY_SOUL_SAND,
        DnDOverlayBlocks.ROCKY_SOUL_SOIL,

        DnDOverlayBlocks.SLATED_GRASS,
        DnDOverlayBlocks.SLATED_PODZOL,
        DnDOverlayBlocks.SLATED_MYCELIUM,
        DnDOverlayBlocks.SLATED_DIRT_PATH,
        DnDOverlayBlocks.SLATED_DIRT,
        DnDOverlayBlocks.SLATED_COARSE_DIRT,
        DnDOverlayBlocks.SLATED_MUD,
        DnDOverlayBlocks.SLATED_SNOW,
        DnDOverlayBlocks.SLATED_GRAVEL,
        DnDOverlayBlocks.SLATED_SAND,
        DnDOverlayBlocks.SLATED_RED_SAND,
        DnDOverlayBlocks.SLATED_SOUL_SAND,
        DnDOverlayBlocks.SLATED_SOUL_SOIL,

        DnDOverlayBlocks.BLACKSTONE_GRASS,
        DnDOverlayBlocks.BLACKSTONE_PODZOL,
        DnDOverlayBlocks.BLACKSTONE_MYCELIUM,
        DnDOverlayBlocks.BLACKSTONE_DIRT_PATH,
        DnDOverlayBlocks.BLACKSTONE_DIRT,
        DnDOverlayBlocks.BLACKSTONE_COARSE_DIRT,
        DnDOverlayBlocks.BLACKSTONE_MUD,
        DnDOverlayBlocks.BLACKSTONE_SNOW,
        DnDOverlayBlocks.BLACKSTONE_GRAVEL,
        DnDOverlayBlocks.BLACKSTONE_SAND,
        DnDOverlayBlocks.BLACKSTONE_RED_SAND,
        DnDOverlayBlocks.BLACKSTONE_SOUL_SAND,
        DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL
    )
}