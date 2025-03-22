package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
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
    val polishedStone = DnDBlocks.POLISHED_STONE.list
    val mossyPolishedStone = DnDBlocks.MOSSY_POLISHED_STONE.list
    val overgrownCobblestone = DnDBlocks.OVERGROWN_COBBLESTONE.list
    val overgrownStoneBricks = DnDBlocks.OVERGROWN_STONE_BRICKS.list
    val snowyStoneBricks = DnDBlocks.SNOWY_STONE_BRICKS.list
    val ice = DnDBlocks.ICE_SET.list + DnDBlocks.PACKED_ICE_SET.list + DnDBlocks.BLUE_ICE_SET.list
//            DnDBlocks.ICE_BRICKS.list + DnDBlocks.PACKED_ICE_BRICKS.list + DnDBlocks.BLUE_ICE_BRICKS.list

    val bigCandles = DnDBlocks.BIG_CANDLES.toList()
    val soulCandles = DnDBlocks.SOUL_CANDLES.toList()
    val bigSoulCandles = DnDBlocks.BIG_SOUL_CANDLES.toList()
    val netherrackStuff = DnDBlocks.NETHERRACK_SET.list
    val netherBrickStuff = listOf(DnDBlocks.NETHER_BRICK_PILLAR) + DnDBlocks.POLISHED_NETHER_BRICKS.list
    val redNetherBrickStuff = listOf(
        DnDBlocks.RED_NETHER_BRICK_FENCE,
        DnDBlocks.CHISELED_RED_NETHER_BRICKS,
        DnDBlocks.RED_NETHER_BRICK_PILLAR
    ) + DnDBlocks.POLISHED_RED_NETHER_BRICKS.list
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
    ) + DnDBlocks.POLISHED_BLUE_NETHER_BRICKS.list

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
    ) + DnDBlocks.POLISHED_GRAY_NETHER_BRICKS.list
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
    val oakWoodStuff = DnDWoodBlocks.OAK_WOOD.headless()
    val spruceWoodStuff = DnDWoodBlocks.SPRUCE_WOOD.headless()
    val birchWoodStuff = DnDWoodBlocks.BIRCH_WOOD.headless()
    val jungleWoodStuff = DnDWoodBlocks.JUNGLE_WOOD.headless()
    val acaciaWoodStuff = DnDWoodBlocks.ACACIA_WOOD.headless()
    val darkOakWoodStuff = DnDWoodBlocks.DARK_OAK_WOOD.headless()
    val mangroveWoodStuff = DnDWoodBlocks.MANGROVE_WOOD.headless()
    val cherryWoodStuff = DnDWoodBlocks.CHERRY_WOOD.headless()
    val cascadeWoodStuff = DnDWoodBlocks.CASCADE_WOOD.headless()
    val crimsonHyphaeStuff = DnDWoodBlocks.CRIMSON_HYPHAE.headless()
    val warpedHyphaeStuff = DnDWoodBlocks.WARPED_HYPHAE.headless()

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
    val overlayBlocks = DnDBlocks.OVERLAYS.flatMap { it.list }
}