package org.teamvoided.dusks_and_dungeons.init.tab

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Items
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.voidlib.helpers.mc.addItems

object WoodsTab {

    fun init(params: CreativeModeTab.ItemDisplayParameters, output: CreativeModeTab.Output) {
        output.oak()
        output.spruce()
        output.mangrove()
    }

    fun CreativeModeTab.Output.oak() {
        addItems(
            // Log
            Items.OAK_LOG,
            DnDBlocks.HOLLOW_OAK_LOG,
            Items.OAK_WOOD,
            DnDBlocks.OAK_WOOD.stairs,
            DnDBlocks.OAK_WOOD.slab,
            DnDBlocks.OAK_WOOD.wall,
            DnDBlocks.OAK_LOG_PILE,
            // Stripped Log
            Items.STRIPPED_OAK_LOG,
            DnDBlocks.HOLLOW_STRIPPED_OAK_LOG,
            Items.STRIPPED_OAK_WOOD,
            DnDBlocks.STRIPPED_OAK_WOOD.stairs,
            DnDBlocks.STRIPPED_OAK_WOOD.slab,
            DnDBlocks.STRIPPED_OAK_WOOD.wall,
            DnDBlocks.STRIPPED_OAK_LOG_PILE,
            // Log Extra
            // Flora
            Items.OAK_LEAVES,
            DnDBlocks.OAK_LEAF_PILE,
            Items.OAK_SAPLING,
            // Plank
            Items.OAK_PLANKS,
            Items.OAK_STAIRS,
            Items.OAK_SLAB,
            DnDBlocks.OAK_WALL,
            Items.OAK_FENCE,
            Items.OAK_FENCE_GATE,
            Items.OAK_DOOR,
            Items.OAK_TRAPDOOR,
            Items.OAK_PRESSURE_PLATE,
            Items.OAK_BUTTON,
//            DnDBlocks.OAK_SCONCE,
            Items.BOOKSHELF,
            Items.OAK_SIGN,
            Items.OAK_HANGING_SIGN,
            Items.OAK_BOAT,
            Items.OAK_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.spruce() {
        addItems(
            // Log
            Items.SPRUCE_LOG,
            DnDBlocks.HOLLOW_SPRUCE_LOG,
            Items.SPRUCE_WOOD,
            DnDBlocks.SPRUCE_WOOD.stairs,
            DnDBlocks.SPRUCE_WOOD.slab,
            DnDBlocks.SPRUCE_WOOD.wall,
            DnDBlocks.SPRUCE_LOG_PILE,
            // Stripped Log
            Items.STRIPPED_SPRUCE_LOG,
            DnDBlocks.HOLLOW_STRIPPED_SPRUCE_LOG,
            Items.STRIPPED_SPRUCE_WOOD,
            DnDBlocks.STRIPPED_SPRUCE_WOOD.stairs,
            DnDBlocks.STRIPPED_SPRUCE_WOOD.slab,
            DnDBlocks.STRIPPED_SPRUCE_WOOD.wall,
            DnDBlocks.STRIPPED_SPRUCE_LOG_PILE,
            // Log Extra
            // Flora
            Items.SPRUCE_LEAVES,
            DnDBlocks.SPRUCE_LEAF_PILE,
            Items.SPRUCE_SAPLING,
            // Plank
            Items.SPRUCE_PLANKS,
            Items.SPRUCE_STAIRS,
            Items.SPRUCE_SLAB,
            DnDBlocks.SPRUCE_WALL,
            Items.SPRUCE_FENCE,
            Items.SPRUCE_FENCE_GATE,
            Items.SPRUCE_DOOR,
            Items.SPRUCE_TRAPDOOR,
            Items.SPRUCE_PRESSURE_PLATE,
            Items.SPRUCE_BUTTON,
            DnDBlocks.SPRUCE_SCONCE,
            DnDBlocks.SPRUCE_BOOKSHELF,
            Items.SPRUCE_SIGN,
            Items.SPRUCE_HANGING_SIGN,
            Items.SPRUCE_BOAT,
            Items.SPRUCE_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.mangrove() {
        addItems(
            // Log
            Items.MANGROVE_LOG,
            DnDBlocks.HOLLOW_MANGROVE_LOG,
            Items.MANGROVE_WOOD,
            DnDBlocks.MANGROVE_WOOD.stairs,
            DnDBlocks.MANGROVE_WOOD.slab,
            DnDBlocks.MANGROVE_WOOD.wall,
            DnDBlocks.MANGROVE_LOG_PILE,
            // Stripped Log
            Items.STRIPPED_MANGROVE_LOG,
            DnDBlocks.HOLLOW_STRIPPED_MANGROVE_LOG,
            Items.STRIPPED_MANGROVE_WOOD,
            DnDBlocks.STRIPPED_MANGROVE_WOOD.stairs,
            DnDBlocks.STRIPPED_MANGROVE_WOOD.slab,
            DnDBlocks.STRIPPED_MANGROVE_WOOD.wall,
            DnDBlocks.STRIPPED_MANGROVE_LOG_PILE,
            // Log Extra
            Items.MANGROVE_ROOTS,
            Items.MUDDY_MANGROVE_ROOTS,
            // Flora
            Items.MANGROVE_LEAVES,
            DnDBlocks.MANGROVE_LEAF_PILE,
            Items.MANGROVE_PROPAGULE,
            // Plank
            Items.MANGROVE_PLANKS,
            Items.MANGROVE_STAIRS,
            Items.MANGROVE_SLAB,
            DnDBlocks.MANGROVE_WALL,
            Items.MANGROVE_FENCE,
            Items.MANGROVE_FENCE_GATE,
            Items.MANGROVE_DOOR,
            Items.MANGROVE_TRAPDOOR,
            Items.MANGROVE_PRESSURE_PLATE,
            Items.MANGROVE_BUTTON,
//            DnDBlocks.MANGROVE_SCONCE,
            DnDBlocks.MANGROVE_BOOKSHELF,
            Items.MANGROVE_SIGN,
            Items.MANGROVE_HANGING_SIGN,
            Items.MANGROVE_BOAT,
            Items.MANGROVE_CHEST_BOAT,
        )
    }

}