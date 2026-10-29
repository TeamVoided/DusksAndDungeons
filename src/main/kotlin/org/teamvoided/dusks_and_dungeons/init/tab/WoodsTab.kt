package org.teamvoided.dusks_and_dungeons.init.tab

import net.minecraft.ChatFormatting
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Items
import org.teamvoided.dusks_and_dungeons.DnDConst.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.voidlib.api.tab.TabSection
import org.teamvoided.voidlib.api.tab.TabSectionRegistry
import org.teamvoided.voidlib.helpers.mc.acceptAll

object WoodsTab {

    val LANG_KEYS = mutableMapOf<ResourceLocation, String>()

    fun registerSections(key: ResourceKey<CreativeModeTab>, vararg sections: TabSection) {
        for (sections in sections) {
            TabSectionRegistry.register(key, sections)
        }
    }

    fun section(name: String, itemAdder: CreativeModeTab.Output.() -> Unit): TabSection {
        val id = id(name)
        val lang = "section.$MODID.$name"
        LANG_KEYS[id] = lang
        return TabSection.build(id) {
            name(Component.translatable(lang).withStyle(ChatFormatting.DARK_GRAY))
            displayItems { _, output -> itemAdder.invoke(output) }
        }
    }

    fun init(tab: Holder.Reference<CreativeModeTab>) {
        registerSections(
            tab.key(),
            section("oak") { oak() },
            section("spruce") { spruce() },
            section("birch") { birch() },
            section("jungle") { jungle() },
            section("acacia") { acacia() },
            section("dark_oak") { darkOak() },
            section("mangrove") { mangrove() },
            section("cherry") { cherry() },
            section("cascade") { cascade() },
            section("sypia") { sypia() },
            section("verdant") { verdant() },
            section("bamboo") { bamboo() },
            section("crimson") { crimson() },
            section("warped") { warped() },
        )
    }

    fun CreativeModeTab.Output.oak() {
        acceptAll(
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
        acceptAll(
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

    fun CreativeModeTab.Output.birch() {
        acceptAll(
            // Log
            Items.BIRCH_LOG,
            DnDBlocks.HOLLOW_BIRCH_LOG,
            Items.BIRCH_WOOD,
            DnDBlocks.BIRCH_WOOD.stairs,
            DnDBlocks.BIRCH_WOOD.slab,
            DnDBlocks.BIRCH_WOOD.wall,
            DnDBlocks.BIRCH_LOG_PILE,
            // Stripped Log
            Items.STRIPPED_BIRCH_LOG,
            DnDBlocks.HOLLOW_STRIPPED_BIRCH_LOG,
            Items.STRIPPED_BIRCH_WOOD,
            DnDBlocks.STRIPPED_BIRCH_WOOD.stairs,
            DnDBlocks.STRIPPED_BIRCH_WOOD.slab,
            DnDBlocks.STRIPPED_BIRCH_WOOD.wall,
            DnDBlocks.STRIPPED_BIRCH_LOG_PILE,
            // Log Extra
            // Flora
            Items.BIRCH_LEAVES,
            DnDBlocks.BIRCH_LEAF_PILE,
            Items.BIRCH_SAPLING,
            // Plank
            Items.BIRCH_PLANKS,
            Items.BIRCH_STAIRS,
            Items.BIRCH_SLAB,
            DnDBlocks.BIRCH_WALL,
            Items.BIRCH_FENCE,
            Items.BIRCH_FENCE_GATE,
            Items.BIRCH_DOOR,
            Items.BIRCH_TRAPDOOR,
            Items.BIRCH_PRESSURE_PLATE,
            Items.BIRCH_BUTTON,
//            DnDBlocks.BIRCH_SCONCE,
            DnDBlocks.BIRCH_BOOKSHELF,
            Items.BIRCH_SIGN,
            Items.BIRCH_HANGING_SIGN,
            Items.BIRCH_BOAT,
            Items.BIRCH_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.jungle() {
        acceptAll(
            // Log
            Items.JUNGLE_LOG,
            DnDBlocks.HOLLOW_JUNGLE_LOG,
            Items.JUNGLE_WOOD,
            DnDBlocks.JUNGLE_WOOD.stairs,
            DnDBlocks.JUNGLE_WOOD.slab,
            DnDBlocks.JUNGLE_WOOD.wall,
            DnDBlocks.JUNGLE_LOG_PILE,
            // Stripped Log
            Items.STRIPPED_JUNGLE_LOG,
            DnDBlocks.HOLLOW_STRIPPED_JUNGLE_LOG,
            Items.STRIPPED_JUNGLE_WOOD,
            DnDBlocks.STRIPPED_JUNGLE_WOOD.stairs,
            DnDBlocks.STRIPPED_JUNGLE_WOOD.slab,
            DnDBlocks.STRIPPED_JUNGLE_WOOD.wall,
            DnDBlocks.STRIPPED_JUNGLE_LOG_PILE,
            // Log Extra
            // Flora
            Items.JUNGLE_LEAVES,
            DnDBlocks.JUNGLE_LEAF_PILE,
            Items.JUNGLE_SAPLING,
            // Plank
            Items.JUNGLE_PLANKS,
            Items.JUNGLE_STAIRS,
            Items.JUNGLE_SLAB,
            DnDBlocks.JUNGLE_WALL,
            Items.JUNGLE_FENCE,
            Items.JUNGLE_FENCE_GATE,
            Items.JUNGLE_DOOR,
            Items.JUNGLE_TRAPDOOR,
            Items.JUNGLE_PRESSURE_PLATE,
            Items.JUNGLE_BUTTON,
//            DnDBlocks.JUNGLE_SCONCE,
            DnDBlocks.JUNGLE_BOOKSHELF,
            Items.JUNGLE_SIGN,
            Items.JUNGLE_HANGING_SIGN,
            Items.JUNGLE_BOAT,
            Items.JUNGLE_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.acacia() {
        acceptAll(
            // Log
            Items.ACACIA_LOG,
            DnDBlocks.HOLLOW_ACACIA_LOG,
            Items.ACACIA_WOOD,
            DnDBlocks.ACACIA_WOOD.stairs,
            DnDBlocks.ACACIA_WOOD.slab,
            DnDBlocks.ACACIA_WOOD.wall,
            DnDBlocks.ACACIA_LOG_PILE,
            // Stripped Log
            Items.STRIPPED_ACACIA_LOG,
            DnDBlocks.HOLLOW_STRIPPED_ACACIA_LOG,
            Items.STRIPPED_ACACIA_WOOD,
            DnDBlocks.STRIPPED_ACACIA_WOOD.stairs,
            DnDBlocks.STRIPPED_ACACIA_WOOD.slab,
            DnDBlocks.STRIPPED_ACACIA_WOOD.wall,
            DnDBlocks.STRIPPED_ACACIA_LOG_PILE,
            // Log Extra
            // Flora
            Items.ACACIA_LEAVES,
            DnDBlocks.ACACIA_LEAF_PILE,
            Items.ACACIA_SAPLING,
            // Plank
            Items.ACACIA_PLANKS,
            Items.ACACIA_STAIRS,
            Items.ACACIA_SLAB,
            DnDBlocks.ACACIA_WALL,
            Items.ACACIA_FENCE,
            Items.ACACIA_FENCE_GATE,
            Items.ACACIA_DOOR,
            Items.ACACIA_TRAPDOOR,
            Items.ACACIA_PRESSURE_PLATE,
            Items.ACACIA_BUTTON,
//            DnDBlocks.ACACIA_SCONCE,
            DnDBlocks.ACACIA_BOOKSHELF,
            Items.ACACIA_SIGN,
            Items.ACACIA_HANGING_SIGN,
            Items.ACACIA_BOAT,
            Items.ACACIA_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.darkOak() {
        acceptAll(
            // Log
            Items.DARK_OAK_LOG,
            DnDBlocks.HOLLOW_DARK_OAK_LOG,
            Items.DARK_OAK_WOOD,
            DnDBlocks.DARK_OAK_WOOD.stairs,
            DnDBlocks.DARK_OAK_WOOD.slab,
            DnDBlocks.DARK_OAK_WOOD.wall,
            DnDBlocks.DARK_OAK_LOG_PILE,
            // Stripped Log
            Items.STRIPPED_DARK_OAK_LOG,
            DnDBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG,
            Items.STRIPPED_DARK_OAK_WOOD,
            DnDBlocks.STRIPPED_DARK_OAK_WOOD.stairs,
            DnDBlocks.STRIPPED_DARK_OAK_WOOD.slab,
            DnDBlocks.STRIPPED_DARK_OAK_WOOD.wall,
            DnDBlocks.STRIPPED_DARK_OAK_LOG_PILE,
            // Log Extra
            // Flora
            Items.DARK_OAK_LEAVES,
            DnDBlocks.DARK_OAK_LEAF_PILE,
            Items.DARK_OAK_SAPLING,
            // Plank
            Items.DARK_OAK_PLANKS,
            Items.DARK_OAK_STAIRS,
            Items.DARK_OAK_SLAB,
            DnDBlocks.DARK_OAK_WALL,
            Items.DARK_OAK_FENCE,
            Items.DARK_OAK_FENCE_GATE,
            Items.DARK_OAK_DOOR,
            Items.DARK_OAK_TRAPDOOR,
            Items.DARK_OAK_PRESSURE_PLATE,
            Items.DARK_OAK_BUTTON,
//            DnDBlocks.DARK_OAK_SCONCE,
            DnDBlocks.DARK_OAK_BOOKSHELF,
            Items.DARK_OAK_SIGN,
            Items.DARK_OAK_HANGING_SIGN,
            Items.DARK_OAK_BOAT,
            Items.DARK_OAK_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.mangrove() {
        acceptAll(
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

    fun CreativeModeTab.Output.cherry() {
        acceptAll(
            // Log
            Items.CHERRY_LOG,
            DnDBlocks.HOLLOW_CHERRY_LOG,
            Items.CHERRY_WOOD,
            DnDBlocks.CHERRY_WOOD.stairs,
            DnDBlocks.CHERRY_WOOD.slab,
            DnDBlocks.CHERRY_WOOD.wall,
            DnDBlocks.CHERRY_LOG_PILE,
            // Stripped Log
            Items.STRIPPED_CHERRY_LOG,
            DnDBlocks.HOLLOW_STRIPPED_CHERRY_LOG,
            Items.STRIPPED_CHERRY_WOOD,
            DnDBlocks.STRIPPED_CHERRY_WOOD.stairs,
            DnDBlocks.STRIPPED_CHERRY_WOOD.slab,
            DnDBlocks.STRIPPED_CHERRY_WOOD.wall,
            DnDBlocks.STRIPPED_CHERRY_LOG_PILE,
            // Log Extra
            // Flora
            Items.CHERRY_LEAVES,
            DnDBlocks.CHERRY_LEAF_PILE,
            Items.CHERRY_SAPLING,
            // Plank
            Items.CHERRY_PLANKS,
            Items.CHERRY_STAIRS,
            Items.CHERRY_SLAB,
            DnDBlocks.CHERRY_WALL,
            Items.CHERRY_FENCE,
            Items.CHERRY_FENCE_GATE,
            Items.CHERRY_DOOR,
            Items.CHERRY_TRAPDOOR,
            Items.CHERRY_PRESSURE_PLATE,
            Items.CHERRY_BUTTON,
//            DnDBlocks.CHERRY_SCONCE,
            DnDBlocks.CHERRY_BOOKSHELF,
            Items.CHERRY_SIGN,
            Items.CHERRY_HANGING_SIGN,
            Items.CHERRY_BOAT,
            Items.CHERRY_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.cascade() {
        acceptAll(
            // Log
            DnDBlocks.CASCADE_LOG,
            DnDBlocks.HOLLOW_CASCADE_LOG,
            DnDBlocks.CASCADE_WOOD,
            DnDBlocks.CASCADE_WOOD.stairs,
            DnDBlocks.CASCADE_WOOD.slab,
            DnDBlocks.CASCADE_WOOD.wall,
            DnDBlocks.CASCADE_LOG_PILE,
            // Stripped Log
            DnDBlocks.STRIPPED_CASCADE_LOG,
            DnDBlocks.HOLLOW_STRIPPED_CASCADE_LOG,
            DnDBlocks.STRIPPED_CASCADE_WOOD,
            DnDBlocks.STRIPPED_CASCADE_WOOD.stairs,
            DnDBlocks.STRIPPED_CASCADE_WOOD.slab,
            DnDBlocks.STRIPPED_CASCADE_WOOD.wall,
            DnDBlocks.STRIPPED_CASCADE_LOG_PILE,
            // Log Extra
            // Flora
            DnDBlocks.CASCADE_LEAVES,
            DnDBlocks.CASCADE_LEAF_PILE,
            DnDBlocks.CASCADE_SAPLING,
            // Plank
            DnDBlocks.CASCADE_PLANKS,
            DnDBlocks.CASCADE_STAIRS,
            DnDBlocks.CASCADE_SLAB,
            DnDBlocks.CASCADE_WALL,
            DnDBlocks.CASCADE_FENCE,
            DnDBlocks.CASCADE_FENCE_GATE,
            DnDBlocks.CASCADE_DOOR,
            DnDBlocks.CASCADE_TRAPDOOR,
            DnDBlocks.CASCADE_PRESSURE_PLATE,
            DnDBlocks.CASCADE_BUTTON,
//            DnDBlocks.CASCADE_SCONCE,
            DnDBlocks.CASCADE_BOOKSHELF,
            DnDItems.CASCADE_SIGN,
            DnDItems.CASCADE_HANGING_SIGN,
//            DnDItems.CASCADE_BOAT,
//            DnDItems.CASCADE_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.sypia() {
        acceptAll(
            // Log
            DnDBlocks.SYPIA_LOG,
            DnDBlocks.HOLLOW_SYPIA_LOG,
            DnDBlocks.SYPIA_WOOD,
            DnDBlocks.SYPIA_WOOD.stairs,
            DnDBlocks.SYPIA_WOOD.slab,
            DnDBlocks.SYPIA_WOOD.wall,
            DnDBlocks.SYPIA_LOG_PILE,
            // Stripped Log
            DnDBlocks.STRIPPED_SYPIA_LOG,
            DnDBlocks.HOLLOW_STRIPPED_SYPIA_LOG,
            DnDBlocks.STRIPPED_SYPIA_WOOD,
            DnDBlocks.STRIPPED_SYPIA_WOOD.stairs,
            DnDBlocks.STRIPPED_SYPIA_WOOD.slab,
            DnDBlocks.STRIPPED_SYPIA_WOOD.wall,
            DnDBlocks.STRIPPED_SYPIA_LOG_PILE,
            // Log Extra
            // Flora
            DnDBlocks.SYPIA_LEAVES,
            DnDBlocks.SYPIA_LEAF_PILE,
            DnDBlocks.SYPIA_SAPLING,
            // Plank
            DnDBlocks.SYPIA_PLANKS,
            DnDBlocks.SYPIA_STAIRS,
            DnDBlocks.SYPIA_SLAB,
            DnDBlocks.SYPIA_WALL,
            DnDBlocks.SYPIA_FENCE,
            DnDBlocks.SYPIA_FENCE_GATE,
            DnDBlocks.SYPIA_DOOR,
            DnDBlocks.SYPIA_TRAPDOOR,
            DnDBlocks.SYPIA_PRESSURE_PLATE,
            DnDBlocks.SYPIA_BUTTON,
//            DnDBlocks.SYPIA_SCONCE,
            DnDBlocks.SYPIA_BOOKSHELF,
            DnDItems.SYPIA_SIGN,
            DnDItems.SYPIA_HANGING_SIGN,
//            DnDItems.SYPIA_BOAT,
//            DnDItems.SYPIA_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.verdant() {
        acceptAll(
            // Log
            DnDBlocks.VERDANT_LOG,
            DnDBlocks.HOLLOW_VERDANT_LOG,
            DnDBlocks.VERDANT_WOOD,
            DnDBlocks.VERDANT_WOOD.stairs,
            DnDBlocks.VERDANT_WOOD.slab,
            DnDBlocks.VERDANT_WOOD.wall,
            DnDBlocks.VERDANT_LOG_PILE,
            // Stripped Log
            DnDBlocks.STRIPPED_VERDANT_LOG,
            DnDBlocks.HOLLOW_STRIPPED_VERDANT_LOG,
            DnDBlocks.STRIPPED_VERDANT_WOOD,
            DnDBlocks.STRIPPED_VERDANT_WOOD.stairs,
            DnDBlocks.STRIPPED_VERDANT_WOOD.slab,
            DnDBlocks.STRIPPED_VERDANT_WOOD.wall,
            DnDBlocks.STRIPPED_VERDANT_LOG_PILE,
            // Log Extra
            // Flora
            DnDBlocks.VERDANT_LEAVES,
            DnDBlocks.VERDANT_LEAF_PILE,
            DnDBlocks.OVERGROWTH_BUSH,
            // Plank
            DnDBlocks.VERDANT_PLANKS,
            DnDBlocks.VERDANT_STAIRS,
            DnDBlocks.VERDANT_SLAB,
            DnDBlocks.VERDANT_WALL,
            DnDBlocks.VERDANT_FENCE,
            DnDBlocks.VERDANT_FENCE_GATE,
            DnDBlocks.VERDANT_DOOR,
            DnDBlocks.VERDANT_TRAPDOOR,
            DnDBlocks.VERDANT_PRESSURE_PLATE,
            DnDBlocks.VERDANT_BUTTON,
//            DnDBlocks.VERDANT_SCONCE,
            DnDBlocks.VERDANT_BOOKSHELF,
            DnDItems.VERDANT_SIGN,
            DnDItems.VERDANT_HANGING_SIGN,
//            DnDItems.VERDANT_BOAT,
//            DnDItems.VERDANT_CHEST_BOAT,
        )
    }

    fun CreativeModeTab.Output.bamboo() {
        acceptAll(
            // Log
            Items.BAMBOO_BLOCK,
            DnDBlocks.HOLLOW_BAMBOO_BLOCK,
            DnDBlocks.BAMBOO_PILE,
            // Stripped Log
            Items.STRIPPED_BAMBOO_BLOCK,
            DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK,
            DnDBlocks.STRIPPED_BAMBOO_PILE,
            // Log Extra
            // Flora
            Items.BAMBOO,
            // Plank
            Items.BAMBOO_PLANKS,
            Items.BAMBOO_STAIRS,
            Items.BAMBOO_SLAB,
            DnDBlocks.BAMBOO_WALL,
            Items.BAMBOO_FENCE,
            Items.BAMBOO_FENCE_GATE,
            Items.BAMBOO_DOOR,
            Items.BAMBOO_TRAPDOOR,
            Items.BAMBOO_PRESSURE_PLATE,
            Items.BAMBOO_BUTTON,
//            DnDBlocks.BAMBOO_SCONCE,
            DnDBlocks.BAMBOO_BOOKSHELF,
            // mosaic
            Items.BAMBOO_MOSAIC,
            Items.BAMBOO_MOSAIC_STAIRS,
            Items.BAMBOO_MOSAIC_SLAB,
            Items.BAMBOO_SIGN,
            Items.BAMBOO_HANGING_SIGN,
            // Boat
            Items.BAMBOO_RAFT,
            Items.BAMBOO_CHEST_RAFT,
        )
    }

    fun CreativeModeTab.Output.crimson() {
        acceptAll(
            // Log
            Items.CRIMSON_STEM,
            DnDBlocks.HOLLOW_CRIMSON_STEM,
            Items.CRIMSON_HYPHAE,
            DnDBlocks.CRIMSON_HYPHAE.stairs,
            DnDBlocks.CRIMSON_HYPHAE.slab,
            DnDBlocks.CRIMSON_HYPHAE.wall,
            DnDBlocks.CRIMSON_STEM_PILE,
            // Stripped Log
            Items.STRIPPED_CRIMSON_STEM,
            DnDBlocks.HOLLOW_STRIPPED_CRIMSON_STEM,
            Items.STRIPPED_CRIMSON_HYPHAE,
            DnDBlocks.STRIPPED_CRIMSON_HYPHAE.stairs,
            DnDBlocks.STRIPPED_CRIMSON_HYPHAE.slab,
            DnDBlocks.STRIPPED_CRIMSON_HYPHAE.wall,
            DnDBlocks.STRIPPED_CRIMSON_STEM_PILE,
            // Log Extra
            // Flora
            Items.NETHER_WART_BLOCK,
//            DnDBlocks.CRIMSON_WART_PILE,
            Items.CRIMSON_FUNGUS,
            // Plank
            Items.CRIMSON_PLANKS,
            Items.CRIMSON_STAIRS,
            Items.CRIMSON_SLAB,
            DnDBlocks.CRIMSON_WALL,
            Items.CRIMSON_FENCE,
            Items.CRIMSON_FENCE_GATE,
            Items.CRIMSON_DOOR,
            Items.CRIMSON_TRAPDOOR,
            Items.CRIMSON_PRESSURE_PLATE,
            Items.CRIMSON_BUTTON,
//            DnDBlocks.CRIMSON_SCONCE,
            DnDBlocks.CRIMSON_BOOKSHELF,
            Items.CRIMSON_SIGN,
            Items.CRIMSON_HANGING_SIGN,
        )
    }

    fun CreativeModeTab.Output.warped() {
        acceptAll(
            // Log
            Items.WARPED_STEM,
            DnDBlocks.HOLLOW_WARPED_STEM,
            Items.WARPED_HYPHAE,
            DnDBlocks.WARPED_HYPHAE.stairs,
            DnDBlocks.WARPED_HYPHAE.slab,
            DnDBlocks.WARPED_HYPHAE.wall,
            DnDBlocks.WARPED_STEM_PILE,
            // Stripped Log
            Items.STRIPPED_WARPED_STEM,
            DnDBlocks.HOLLOW_STRIPPED_WARPED_STEM,
            Items.STRIPPED_WARPED_HYPHAE,
            DnDBlocks.STRIPPED_WARPED_HYPHAE.stairs,
            DnDBlocks.STRIPPED_WARPED_HYPHAE.slab,
            DnDBlocks.STRIPPED_WARPED_HYPHAE.wall,
            DnDBlocks.STRIPPED_WARPED_STEM_PILE,
            // Log Extra
            // Flora
            Items.WARPED_WART_BLOCK,
//            DnDBlocks.WARPED_WART_PILE,
            Items.WARPED_FUNGUS,
            // Plank
            Items.WARPED_PLANKS,
            Items.WARPED_STAIRS,
            Items.WARPED_SLAB,
            DnDBlocks.WARPED_WALL,
            Items.WARPED_FENCE,
            Items.WARPED_FENCE_GATE,
            Items.WARPED_DOOR,
            Items.WARPED_TRAPDOOR,
            Items.WARPED_PRESSURE_PLATE,
            Items.WARPED_BUTTON,
//            DnDBlocks.WARPED_SCONCE,
            DnDBlocks.WARPED_BOOKSHELF,
            Items.WARPED_SIGN,
            Items.WARPED_HANGING_SIGN,
        )
    }

}