package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.world.level.ItemLike
import net.minecraft.world.item.Items
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems

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
    val cascadeWood = listOf<ItemLike>(
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
    )

    val cascadeSigns = listOf(
        DnDItems.CASCADE_SIGN,
        DnDItems.CASCADE_HANGING_SIGN
    )
    val overgrownCobblestone = DnDBlocks.OVERGROWN_COBBLESTONE.list
    val overgrownStoneBricks = DnDBlocks.OVERGROWN_STONE_BRICKS.list
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
    val oakWoodStuff = DnDBlocks.OAK_WOOD.headless()
    val spruceWoodStuff = DnDBlocks.SPRUCE_WOOD.headless()
    val birchWoodStuff = DnDBlocks.BIRCH_WOOD.headless()
    val jungleWoodStuff = DnDBlocks.JUNGLE_WOOD.headless()
    val acaciaWoodStuff = DnDBlocks.ACACIA_WOOD.headless()
    val darkOakWoodStuff = DnDBlocks.DARK_OAK_WOOD.headless()
    val mangroveWoodStuff = DnDBlocks.MANGROVE_WOOD.headless()
    val cherryWoodStuff = DnDBlocks.CHERRY_WOOD.headless()
    val cascadeWoodStuff = DnDBlocks.CASCADE_WOOD.headless()
    val crimsonHyphaeStuff = DnDBlocks.CRIMSON_HYPHAE.headless()
    val warpedHyphaeStuff = DnDBlocks.WARPED_HYPHAE.headless()

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

    val stripedWoodLists = listOf(
        DnDBlocks.STRIPPED_OAK_WOOD.headless(),
        DnDBlocks.STRIPPED_SPRUCE_WOOD.headless(),
        DnDBlocks.STRIPPED_BIRCH_WOOD.headless(),
        DnDBlocks.STRIPPED_JUNGLE_WOOD.headless(),
        DnDBlocks.STRIPPED_ACACIA_WOOD.headless(),
        DnDBlocks.STRIPPED_DARK_OAK_WOOD.headless(),
        DnDBlocks.STRIPPED_MANGROVE_WOOD.headless(),
        DnDBlocks.STRIPPED_CHERRY_WOOD.headless(),
        DnDBlocks.STRIPPED_CASCADE_WOOD.headless(),
        DnDBlocks.STRIPPED_CRIMSON_HYPHAE.headless(),
        DnDBlocks.STRIPPED_WARPED_HYPHAE.headless(),
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
    )
    val overlayBlocks = DnDBlocks.OVERLAYS.flatMap { it.list }
}