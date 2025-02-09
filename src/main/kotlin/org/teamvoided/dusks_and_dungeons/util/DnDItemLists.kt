package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDOverlayBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks

object DnDItemLists {
    val dye = listOf(
        Items.WHITE_DYE,
        Items.ORANGE_DYE,
        Items.MAGENTA_DYE,
        Items.LIGHT_BLUE_DYE,
        Items.YELLOW_DYE,
        Items.LIME_DYE,
        Items.PINK_DYE,
        Items.GRAY_DYE,
        Items.LIGHT_GRAY_DYE,
        Items.CYAN_DYE,
        Items.PURPLE_DYE,
        Items.BLUE_DYE,
        Items.BROWN_DYE,
        Items.GREEN_DYE,
        Items.RED_DYE,
        Items.BLACK_DYE,
    )
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
    val polishedStone = DnDBlocks.POLISHED_STONE.collect()
    val mossyPolishedStone = DnDBlocks.MOSSY_POLISHED_STONE.collect()
    val overgrownCobblestone = DnDBlocks.OVERGROWN_COBBLESTONE.collect()
    val overgrownStoneBricks = DnDBlocks.OVERGROWN_STONE_BRICKS.collect()
    val snowyStoneBricks = DnDBlocks.SNOWY_STONE_BRICKS.collect()
    val ice = DnDBlocks.ICE_SET.collect() + DnDBlocks.PACKED_ICE_SET.collect() + DnDBlocks.BLUE_ICE_SET.collect()
//            DnDBlocks.ICE_BRICKS.collect() + DnDBlocks.PACKED_ICE_BRICKS.collect() + DnDBlocks.BLUE_ICE_BRICKS.collect()

    val bigCandles = DnDBlocks.BIG_CANDLES.toList()
    val soulCandles = DnDBlocks.SOUL_CANDLES.toList()
    val bigSoulCandles = DnDBlocks.BIG_SOUL_CANDLES.toList()
    val netherrackStuff = DnDBlocks.NETHERRACK_SET.collect()
    val netherBrickStuff = listOf(DnDBlocks.NETHER_BRICK_PILLAR) + DnDBlocks.POLISHED_NETHER_BRICKS.collect()
    val redNetherBrickStuff = listOf(
        DnDBlocks.RED_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_RED_NETHER_BRICKS,
        DnDBlocks.RED_NETHER_BRICK_PILLAR
    ) + DnDBlocks.POLISHED_RED_NETHER_BRICKS.collect()
    val mixedRedNetherBrickStuff = listOf(
        DnDBlocks.MIXED_RED_NETHER_BRICKS,
        DnDBlocks.CRACKED_MIXED_RED_NETHER_BRICKS,
        DnDBlocks.MIXED_RED_NETHER_BRICKS.stairs,
        DnDBlocks.MIXED_RED_NETHER_BRICKS.slab,
        DnDBlocks.MIXED_RED_NETHER_BRICKS.wall,
        DnDBlocks.MIXED_RED_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_RED_NETHER_BRICKS,
        DnDBlocks.MIXED_RED_NETHER_BRICK_PILLAR
    )
    val blueNetherBrickStuff = listOf(
        DnDBlocks.BLUE_NETHER_BRICKS,
        DnDBlocks.CRACKED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICKS.stairs,
        DnDBlocks.BLUE_NETHER_BRICKS.slab,
        DnDBlocks.BLUE_NETHER_BRICKS.wall,
        DnDBlocks.BLUE_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_BLUE_NETHER_BRICKS,
        DnDBlocks.BLUE_NETHER_BRICK_PILLAR
    ) + DnDBlocks.POLISHED_BLUE_NETHER_BRICKS.collect()

    val mixedBlueNetherBrickStuff = listOf(
        DnDBlocks.MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.MIXED_BLUE_NETHER_BRICKS.stairs,
        DnDBlocks.MIXED_BLUE_NETHER_BRICKS.slab,
        DnDBlocks.MIXED_BLUE_NETHER_BRICKS.wall,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS,
        DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR
    )
    val grayNetherBrickStuff = listOf(
        DnDBlocks.GRAY_NETHER_BRICKS,
        DnDBlocks.CRACKED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICKS.stairs,
        DnDBlocks.GRAY_NETHER_BRICKS.slab,
        DnDBlocks.GRAY_NETHER_BRICKS.wall,
        DnDBlocks.GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_GRAY_NETHER_BRICKS,
        DnDBlocks.GRAY_NETHER_BRICK_PILLAR
    ) + DnDBlocks.POLISHED_GRAY_NETHER_BRICKS.collect()
    val mixedGrayNetherBrickStuff = listOf(
        DnDBlocks.MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.MIXED_GRAY_NETHER_BRICKS.stairs,
        DnDBlocks.MIXED_GRAY_NETHER_BRICKS.slab,
        DnDBlocks.MIXED_GRAY_NETHER_BRICKS.wall,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS,
        DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR
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