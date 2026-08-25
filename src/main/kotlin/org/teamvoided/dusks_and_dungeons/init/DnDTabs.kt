package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusks_and_dungeons.util.*
import org.teamvoided.voidlib.helpers.mc.*


object DnDTabs {

    val DUSKS_AND_DUNGEONS = register(
        MODID, FabricItemGroup.builder()
            .dndName(MODID)
            .icon(DnDBlocks.CASCADE_SAPLING)
            .displayItems(::mainTab)
    )

    val DND_DRINKS = register(
        "dnd_drinks", FabricItemGroup.builder()
            .dndName("dnd_drinks")
            .icon(DnDItems.TINTED_POTION)
            .displayItems { params, output ->
                output.addItems(
                    DnDItems.CORN_SYRUP_BOTTLE,
                    DnDItems.TINTED_GLASS_BOTTLE
                )
                output.addPotionEntries(params, DnDItems.TINTED_POTION)
                output.addPotionEntries(params, DnDItems.TINTED_SPLASH_POTION)
                output.addPotionEntries(params, DnDItems.TINTED_LINGERING_POTION)
            }
    )

    // Dev Tabs
    val DND_EVERYTHING = register(
        "dnd_everything", FabricItemGroup.builder()
            .dndName("dnd_everything")
            .icon(DnDBlocks.STONE_PILLAR)
            .displayItems { _, output -> if (isDev()) output.addLists(DnDItems.ITEMS) }
    )
    val DND_EXPERIMENTAL = register(
        "dnd_experimental", FabricItemGroup.builder()
            .dndName("dnd_experimental")
            .icon(Blocks.BARRIER)
            .displayItems { _, output -> if (isDev()) output.addLists(EVIL_ITEMS) }
    )

    fun init() {
        modifyTab(CreativeModeTabs.BUILDING_BLOCKS) {
            addAfter(
                Items.CHERRY_BUTTON,
                DnDItemLists.cascadeWood + DnDItemLists.sypiaWood
            )

            addWoodStuffAndLeafPiles(false)

            addBefore(
                Blocks.STONE,
                Blocks.SNOW_BLOCK,
                DnDBlocks.SNOW_SET.stairs,
                DnDBlocks.SNOW_SET.slab,
                DnDBlocks.SNOW_SET.wall
            )
            addAfter(Blocks.STONE_SLAB, DnDBlocks.STONE_WALL)

            addAfter(Blocks.SMOOTH_STONE, DnDBlocks.SMOOTH_STONE_STAIR)
            addAfter(Blocks.SMOOTH_STONE_SLAB, DnDBlocks.SMOOTH_STONE_WALL)

            addAfter(Items.MOSSY_COBBLESTONE_WALL, DnDItemLists.overgrownCobblestone)
            addAfter(Blocks.CRACKED_STONE_BRICKS, DnDBlocks.CRACKED_STONE_BRICK_SET.headless())
            addAfter(Items.MOSSY_STONE_BRICK_WALL, DnDItemLists.overgrownStoneBricks)

            addAfter(Blocks.POLISHED_GRANITE_SLAB, DnDBlocks.POLISHED_GRANITE_WALL)
            addAfter(Blocks.POLISHED_DIORITE_SLAB, DnDBlocks.POLISHED_DIORITE_WALL)
            addAfter(
                Blocks.POLISHED_ANDESITE_SLAB,
                DnDBlocks.POLISHED_ANDESITE_WALL,
                // More rocks
                Blocks.DRIPSTONE_BLOCK,
                DnDBlocks.DRIPSTONE_SET.stairs,
                DnDBlocks.DRIPSTONE_SET.slab,
                DnDBlocks.DRIPSTONE_SET.wall,
                Blocks.CALCITE,
                DnDBlocks.CALCITE_SET.stairs,
                DnDBlocks.CALCITE_SET.slab,
                DnDBlocks.CALCITE_SET.wall,
            )

            addAfter(Blocks.CRACKED_DEEPSLATE_BRICKS, DnDBlocks.CRACKED_DEEPSLATE_BRICK_SET.headless())
            addAfter(Blocks.CRACKED_DEEPSLATE_TILES, DnDBlocks.CRACKED_DEEPSLATE_TILE_SET.headless())

            addAfter(Blocks.BRICK_WALL, DnDBlocks.BRICK_FENCE)

            addAfter(Blocks.PACKED_MUD, DnDBlocks.PACKED_MUD_SET.headless())

            addAfter(Blocks.SMOOTH_SANDSTONE_SLAB, DnDBlocks.SMOOTH_SANDSTONE_WALL)

            addAfter(Blocks.CUT_SANDSTONE, DnDBlocks.CUT_SANDSTONE_STAIR)
            addAfter(Blocks.CUT_SANDSTONE_SLAB, DnDBlocks.CUT_SANDSTONE_WALL)
            addAfter(Blocks.SMOOTH_RED_SANDSTONE_SLAB, DnDBlocks.SMOOTH_RED_SANDSTONE_WALL)
            addAfter(Blocks.CUT_RED_SANDSTONE, DnDBlocks.CUT_RED_SANDSTONE_STAIR)
            addAfter(Blocks.CUT_RED_SANDSTONE_SLAB, DnDBlocks.CUT_RED_SANDSTONE_WALL)

            addAfter(Blocks.PRISMARINE_BRICK_SLAB, DnDBlocks.PRISMARINE_BRICKS_WALL)
            addAfter(Blocks.DARK_PRISMARINE_SLAB, DnDBlocks.DARK_PRISMARINE_WALL)

            addAfter(Items.NETHERRACK, DnDItemLists.netherrackStuff)
            addAfter(Blocks.CRACKED_NETHER_BRICKS, DnDBlocks.CRACKED_NETHER_BRICK_SET.headless())
            addAfter(Items.CHISELED_NETHER_BRICKS, DnDItemLists.netherBrickStuff)
            addAfter(Items.RED_NETHER_BRICKS, DnDBlocks.CRACKED_RED_NETHER_BRICKS)
            addAfter(
                Items.RED_NETHER_BRICK_WALL,
                DnDItemLists.redNetherBrickStuff + DnDItemLists.blueNetherBrickStuff + DnDItemLists.grayNetherBrickStuff
            )

            addAfter(Blocks.SMOOTH_BASALT, DnDBlocks.SMOOTH_BASALT_SET.headless())
            addAfter(
                Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, DnDBlocks.CRACKED_POLISHED_BLACKSTONE_BRICK_SET.headless(),
            )

            addBefore(
                Blocks.END_STONE,
                Blocks.OBSIDIAN,
                DnDBlocks.OBSIDIAN_SET.stairs,
                DnDBlocks.OBSIDIAN_SET.slab,
                DnDBlocks.OBSIDIAN_SET.wall,
                Blocks.CRYING_OBSIDIAN,
                DnDBlocks.CRYING_OBSIDIAN_SET.stairs,
                DnDBlocks.CRYING_OBSIDIAN_SET.slab,
                DnDBlocks.CRYING_OBSIDIAN_SET.wall,
            )
            addAfter(Blocks.END_STONE, DnDBlocks.END_STONE_SET.headless())
            addAfter(Blocks.PURPUR_SLAB, DnDBlocks.PURPUR_WALL)

            addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)

            addAfter(Blocks.QUARTZ_SLAB, DnDBlocks.QUARTZ_WALL)
            addAfter(Blocks.QUARTZ_BRICKS, DnDBlocks.QUARTZ_BRICK_SET.headless())
            addAfter(Blocks.SMOOTH_QUARTZ_SLAB, DnDBlocks.SMOOTH_QUARTZ_WALL)

        }

        modifyTab(CreativeModeTabs.COLORED_BLOCKS) {
            addColors(Blocks.PINK_CARPET, DnDBlocks.WOOL_CARPET_PLATE)
            addCandles()
        }

        modifyTab(CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            addBefore(
                Items.CHAIN,
                DnDBlocks.REDSTONE_LANTERN,
                DnDBlocks.BIG_LANTERN,
                DnDBlocks.BIG_SOUL_LANTERN,
                DnDBlocks.BIG_REDSTONE_LANTERN
            )

            addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)

            addAfter(Items.SCAFFOLDING, DnDBlocks.BIG_SCAFFOLDING)

            addAfter(Items.SUSPICIOUS_SAND, DnDBlocks.SUSPICIOUS_RED_SAND)

            addAfter(Items.CHERRY_HANGING_SIGN, DnDItems.CASCADE_SIGN, DnDItems.CASCADE_HANGING_SIGN)

            addCandles()

            addAfter(Blocks.INFESTED_COBBLESTONE, DnDBlocks.INFESTED_MOSSY_COBBLESTONE)
            addAfter(
                Blocks.INFESTED_DEEPSLATE,
                DnDBlocks.INFESTED_COBBLED_DEEPSLATE,
                DnDBlocks.INFESTED_POLISHED_DEEPSLATE,
                DnDBlocks.INFESTED_DEEPSLATE_BRICKS,
                DnDBlocks.INFESTED_CRACKED_DEEPSLATE_BRICKS,
                DnDBlocks.INFESTED_DEEPSLATE_TILES,
                DnDBlocks.INFESTED_CRACKED_DEEPSLATE_TILES,
            )

        }

        modifyTab(CreativeModeTabs.NATURAL_BLOCKS) {
            addAfter(Items.CHERRY_LOG, DnDBlocks.CASCADE_LOG)
            addBefore(Items.PINK_PETALS, DnDBlockLists.flowerbedBlocks)
            addAfter(Items.PINK_PETALS, DnDBlockLists.vivionbedBlocks)
            addAfter(
                Items.FLOWERING_AZALEA_LEAVES,
                listOf(DnDBlocks.CASCADE_LEAVES, DnDBlocks.SYPIA_LEAVES)
            )
            addAfter(Items.FLOWERING_AZALEA, DnDBlocks.CASCADE_SAPLING, DnDBlocks.SYPIA_SAPLING)
            addAfter(Items.VINE, DnDItems.MOONBERRY_VINELET, DnDBlocks.MOONBERRY_VINE, DnDItems.MOONBERRIES)
            DnDBlockLists.leafPiles.forEachIndexed { idx, leafPile ->
                addAfter(DnDBlockLists.leaves[idx], leafPile)
            }
            addAfter(Items.PEONY, DnDItems.CORN_STALK)
            addAfter(
                Items.PUMPKIN_SEEDS,
                DnDItems.LANTERN_PUMPKIN_SEEDS,
                DnDItems.MOSSKIN_PUMPKIN_SEEDS,
                DnDItems.PALE_PUMPKIN_SEEDS,
                DnDItems.GLOOM_PUMPKIN_SEEDS,
            )
            addAfter(Items.BEETROOT_SEEDS, DnDItems.CORN_KERNELS)
            addAfter(
                Items.JACK_O_LANTERN,
                DnDBlocks.LANTERN_PUMPKIN,
                DnDBlocks.CARVED_LANTERN_PUMPKIN,
                DnDBlocks.GLOWING_LANTERN_PUMPKIN,

                DnDBlocks.MOSSKIN_PUMPKIN,
                DnDBlocks.CARVED_MOSSKIN_PUMPKIN,
                DnDBlocks.GLOWING_MOSSKIN_PUMPKIN,

                DnDBlocks.GLOOM_PUMPKIN,
                DnDBlocks.CARVED_GLOOM_PUMPKIN,
                DnDBlocks.GLOWING_GLOOM_PUMPKIN,

                DnDBlocks.PALE_PUMPKIN,
                DnDBlocks.CARVED_PALE_PUMPKIN,
                DnDBlocks.GLOWING_PALE_PUMPKIN,
            )
            addAfter(Items.HONEY_BLOCK, DnDBlocks.CORN_SYRUP_BLOCK)
        }

        modifyTab(CreativeModeTabs.COMBAT) {
            addAfter(Items.STONE_SWORD, DnDItems.BLACKSTONE_SWORD)
            addAfter(Items.STONE_AXE, DnDItems.BLACKSTONE_AXE)
        }

        modifyTab(CreativeModeTabs.TOOLS_AND_UTILITIES) {
            addAfter( // this is what you should have done dusk >:( // L plus M N O P =)
                Items.STONE_HOE,
                DnDItems.BLACKSTONE_SHOVEL, DnDItems.BLACKSTONE_PICKAXE,
                DnDItems.BLACKSTONE_AXE, DnDItems.BLACKSTONE_HOE
            )
        }

        modifyTab(CreativeModeTabs.FOOD_AND_DRINKS) {
            addAfter(Items.SWEET_BERRIES, DnDItems.MOONBERRIES)
            addAfter(Items.GOLDEN_CARROT, DnDItems.CORN)
            addAfter(Items.BEETROOT, DnDItems.GOLDEN_BEETROOT)
            addAfter(Items.HONEY_BOTTLE, DnDItems.CORN_SYRUP_BOTTLE)
        }

        modifyTab(CreativeModeTabs.REDSTONE_BLOCKS) {
            addAfter(Items.HONEY_BLOCK, DnDBlocks.CORN_SYRUP_BLOCK)
        }
    }

    fun mainTab(params: CreativeModeTab.ItemDisplayParameters, output: CreativeModeTab.Output) = with(output) {
        addLists(
            DnDItemLists.cascadeWood,
            DnDItemLists.cascadeSigns,
            DnDItemLists.sypiaWood,
            DnDItemLists.sypiaSigns
        )
        addItems(
            DnDItems.BLUE_DOOR,
            DnDBlocks.CASCADE_SAPLING,
            DnDBlocks.CASCADE_LEAVES,
            DnDBlocks.SYPIA_SAPLING,
            DnDBlocks.SYPIA_LEAVES,

            DnDItems.FARMERS_HAT,
            DnDItems.WILD_WHEAT,
            DnDItems.GOLDEN_BEETROOT,

            DnDItems.MOONBERRY_VINELET,
            DnDBlocks.MOONBERRY_VINE,
            DnDItems.MOONBERRIES
        )
        addLists(
            DnDBlockLists.flowerbedBlocks,
            DnDBlockLists.vivionbedBlocks,
        )
        addItems(
            DnDBlocks.GOLDEN_MUSHROOM,
            DnDBlocks.GOLDEN_MUSHROOM_BLOCK,
            DnDBlocks.GOLDEN_MUSHROOM_STEM_BLOCK,

            DnDItems.CORN_STALK,
            DnDItems.CORN_KERNELS,
            DnDItems.CORN,
            DnDBlocks.CORN_BLOCK,
            DnDBlocks.CORN_SYRUP_BLOCK,
            DnDItems.CORN_SYRUP_BOTTLE,

            DnDBlocks.SMALL_PUMPKIN,
            DnDBlocks.SMALL_CARVED_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_PUMPKIN,

            DnDItems.LANTERN_PUMPKIN_SEEDS,
            DnDBlocks.LANTERN_PUMPKIN,
            DnDBlocks.CARVED_LANTERN_PUMPKIN,
            DnDBlocks.GLOWING_LANTERN_PUMPKIN,
            DnDBlocks.SMALL_LANTERN_PUMPKIN,
            DnDBlocks.SMALL_CARVED_LANTERN_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_LANTERN_PUMPKIN,

            DnDItems.MOSSKIN_PUMPKIN_SEEDS,
            DnDBlocks.MOSSKIN_PUMPKIN,
            DnDBlocks.CARVED_MOSSKIN_PUMPKIN,
            DnDBlocks.GLOWING_MOSSKIN_PUMPKIN,
            DnDBlocks.SMALL_MOSSKIN_PUMPKIN,
            DnDBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN,

            DnDItems.GLOOM_PUMPKIN_SEEDS,
            DnDBlocks.GLOOM_PUMPKIN,
            DnDBlocks.CARVED_GLOOM_PUMPKIN,
            DnDBlocks.GLOWING_GLOOM_PUMPKIN,
            DnDBlocks.SMALL_GLOOM_PUMPKIN,
            DnDBlocks.SMALL_CARVED_GLOOM_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_GLOOM_PUMPKIN,

            DnDItems.PALE_PUMPKIN_SEEDS,
            DnDBlocks.PALE_PUMPKIN,
            DnDBlocks.CARVED_PALE_PUMPKIN,
            DnDBlocks.GLOWING_PALE_PUMPKIN,
            DnDBlocks.SMALL_PALE_PUMPKIN,
            DnDBlocks.SMALL_CARVED_PALE_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_PALE_PUMPKIN,

            DnDBlocks.HOLLOW_OAK_LOG,
            DnDBlocks.HOLLOW_STRIPPED_OAK_LOG,
            DnDBlocks.HOLLOW_SPRUCE_LOG,
            DnDBlocks.HOLLOW_STRIPPED_SPRUCE_LOG,
            DnDBlocks.HOLLOW_BIRCH_LOG,
            DnDBlocks.HOLLOW_STRIPPED_BIRCH_LOG,
            DnDBlocks.HOLLOW_JUNGLE_LOG,
            DnDBlocks.HOLLOW_STRIPPED_JUNGLE_LOG,
            DnDBlocks.HOLLOW_ACACIA_LOG,
            DnDBlocks.HOLLOW_STRIPPED_ACACIA_LOG,
            DnDBlocks.HOLLOW_DARK_OAK_LOG,
            DnDBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG,
            DnDBlocks.HOLLOW_MANGROVE_LOG,
            DnDBlocks.HOLLOW_STRIPPED_MANGROVE_LOG,
            DnDBlocks.HOLLOW_CHERRY_LOG,
            DnDBlocks.HOLLOW_STRIPPED_CHERRY_LOG,
            DnDBlocks.HOLLOW_BAMBOO_BLOCK,
            DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK,
            DnDBlocks.HOLLOW_CRIMSON_STEM,
            DnDBlocks.HOLLOW_STRIPPED_CRIMSON_STEM,
            DnDBlocks.HOLLOW_WARPED_STEM,
            DnDBlocks.HOLLOW_STRIPPED_WARPED_STEM,
        )
        addLists(
            DnDItemLists.woodStuff,
            DnDBlocks.STRIPPED_OAK_WOOD,
            DnDBlocks.STRIPPED_SPRUCE_WOOD,
            DnDBlocks.STRIPPED_BIRCH_WOOD,
            DnDBlocks.STRIPPED_JUNGLE_WOOD,
            DnDBlocks.STRIPPED_ACACIA_WOOD,
            DnDBlocks.STRIPPED_DARK_OAK_WOOD,
            DnDBlocks.STRIPPED_MANGROVE_WOOD,
            DnDBlocks.STRIPPED_CHERRY_WOOD,
            DnDBlocks.STRIPPED_CRIMSON_HYPHAE,
            DnDBlocks.STRIPPED_WARPED_HYPHAE,
            DnDBlocks.STRIPPED_CASCADE_WOOD.headless()
        )
        addItems(
            DnDBlocks.OAK_LOG_PILE,
            DnDBlocks.SPRUCE_LOG_PILE,
            DnDBlocks.BIRCH_LOG_PILE,
            DnDBlocks.JUNGLE_LOG_PILE,
            DnDBlocks.ACACIA_LOG_PILE,
            DnDBlocks.DARK_OAK_LOG_PILE,
            DnDBlocks.MANGROVE_LOG_PILE,
            DnDBlocks.CHERRY_LOG_PILE,
            DnDBlocks.CASCADE_LOG_PILE,
            DnDBlocks.BAMBOO_PILE,
            DnDBlocks.CRIMSON_STEM_PILE,
            DnDBlocks.WARPED_STEM_PILE,
            //Striped
            DnDBlocks.STRIPPED_OAK_LOG_PILE,
            DnDBlocks.STRIPPED_SPRUCE_LOG_PILE,
            DnDBlocks.STRIPPED_BIRCH_LOG_PILE,
            DnDBlocks.STRIPPED_JUNGLE_LOG_PILE,
            DnDBlocks.STRIPPED_ACACIA_LOG_PILE,
            DnDBlocks.STRIPPED_DARK_OAK_LOG_PILE,
            DnDBlocks.STRIPPED_MANGROVE_LOG_PILE,
            DnDBlocks.STRIPPED_CHERRY_LOG_PILE,
            DnDBlocks.STRIPPED_CASCADE_LOG_PILE,
            DnDBlocks.STRIPPED_BAMBOO_PILE,
            DnDBlocks.STRIPPED_CRIMSON_STEM_PILE,
            DnDBlocks.STRIPPED_WARPED_STEM_PILE,
        )
        addLists(DnDItemLists.leafPiles)
        addItems(
            DnDBlocks.SPRUCE_BOOKSHELF,
            DnDBlocks.BIRCH_BOOKSHELF,
            DnDBlocks.JUNGLE_BOOKSHELF,
            DnDBlocks.ACACIA_BOOKSHELF,
            DnDBlocks.DARK_OAK_BOOKSHELF,
            DnDBlocks.MANGROVE_BOOKSHELF,
            DnDBlocks.CHERRY_BOOKSHELF,
            DnDBlocks.BAMBOO_BOOKSHELF,
            DnDBlocks.CRIMSON_BOOKSHELF,
            DnDBlocks.WARPED_BOOKSHELF,
        )
        addLists(
            DnDBlocks.POLISHED_STONE,
            DnDBlocks.MOSSY_POLISHED_STONE,
            DnDBlocks.OVERGROWN_POLISHED_STONE,
            DnDBlocks.OVERGROWN_COBBLESTONE,
            DnDBlocks.OVERGROWN_STONE_BRICKS,
            DnDItemLists.ice
        )
        addItems(
            DnDBlocks.ROOT_BLOCK,
            DnDBlocks.STONE_PILLAR,
            DnDBlocks.DEEPSLATE_PILLAR,
            DnDBlocks.CHISELED_BRICKS,
            DnDBlocks.BRICK_FENCE
        )
        addLists(
            DnDBlocks.CRACKED_BRICKS,
            DnDBlocks.ROUGH_SANDSTONE,
            DnDBlocks.POLISHED_SANDSTONE,
            DnDBlocks.ROUGH_RED_SANDSTONE,
            DnDBlocks.POLISHED_RED_SANDSTONE,
            DnDBlocks.SMOOTH_LAPIS
        )
        addItems(
            DnDBlocks.STONE_BRICK_GRAVESTONE,
            DnDBlocks.SMALL_STONE_BRICK_GRAVESTONE,
            DnDBlocks.DEEPSLATE_BRICK_GRAVESTONE,
            DnDBlocks.SMALL_DEEPSLATE_BRICK_GRAVESTONE,
            DnDBlocks.TUFF_BRICK_GRAVESTONE,
            DnDBlocks.SMALL_TUFF_BRICK_GRAVESTONE,
            DnDBlocks.BLACKSTONE_BRICK_GRAVESTONE,
            DnDBlocks.SMALL_BLACKSTONE_BRICK_GRAVESTONE,

            DnDBlocks.IRON_HEADSTONE,

            DnDBlocks.BIG_CHAIN,
            DnDBlocks.BIG_LANTERN,
            DnDBlocks.BIG_SOUL_LANTERN,
            DnDBlocks.BIG_REDSTONE_LANTERN,
            DnDBlocks.BIG_SCAFFOLDING
        )
        addLists(DnDBlocks.WOOL_CARPET_PLATE)
        addItems(DnDBlocks.MOSS_CARPET_PLATE)
        addItems(
            // This adds the candles in a nice way
            DnDItemLists.bigCandles.flatMapIndexed { idx, item ->
                listOf(
                    item,
                    DnDBlockLists.candelabras[idx],
                    DnDItemLists.soulCandles[idx],
                    DnDItemLists.bigSoulCandles[idx],
                    DnDBlockLists.soulCandelabras[idx],
                )
            }
        )
        addItems(
            DnDBlocks.HEAVY_CUBE,
            DnDBlocks.TINTED_GLASS_PANE
        )
        accept(DnDBlocks.WARPED_WART)
        addLists(DnDItemLists.netherrackStuff, DnDItemLists.netherBrickStuff)
        accept(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
        addLists(
            DnDItemLists.redNetherBrickStuff,
            DnDItemLists.blueNetherBrickStuff,
            DnDItemLists.grayNetherBrickStuff,
            DnDItemLists.blackstoneTools,
        )
    }

    fun register(name: String, tab: CreativeModeTab.Builder): Holder.Reference<CreativeModeTab> {
        return BuiltInRegistries.CREATIVE_MODE_TAB.registerHolder(id(name), tab.build())
    }

    fun CreativeModeTab.Builder.dndName(key: String): CreativeModeTab.Builder = translation("itemGroup.$MODID.$key")

}
