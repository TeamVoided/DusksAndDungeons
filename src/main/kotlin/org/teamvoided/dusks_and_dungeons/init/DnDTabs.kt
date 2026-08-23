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
            addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)
            addAfter(Items.NETHERRACK, DnDItemLists.netherrackStuff)
            addAfter(Items.CHISELED_NETHER_BRICKS, DnDItemLists.netherBrickStuff)
            addAfter(Items.RED_NETHER_BRICKS, DnDBlocks.CRACKED_RED_NETHER_BRICKS)
            addAfter(
                Items.RED_NETHER_BRICK_WALL,
                DnDItemLists.redNetherBrickStuff + DnDItemLists.blueNetherBrickStuff + DnDItemLists.grayNetherBrickStuff
            )
            addAfter(Items.MOSSY_COBBLESTONE_WALL, DnDItemLists.overgrownCobblestone)
            addAfter(Items.MOSSY_STONE_BRICK_WALL, DnDItemLists.overgrownStoneBricks)
            addWoodStuffAndLeafPiles(false)
        }

        modifyTab(CreativeModeTabs.COLORED_BLOCKS) { addCandles() }

        modifyTab(CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            addBefore(
                Items.CHAIN,
                DnDBlocks.BIG_LANTERN,
                DnDBlocks.BIG_SOUL_LANTERN,
                DnDBlocks.BIG_REDSTONE_LANTERN
            )

            addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)

            addAfter(Items.SCAFFOLDING, DnDBlocks.BIG_SCAFFOLDING)

            addAfter(Items.SUSPICIOUS_SAND, DnDBlocks.SUSPICIOUS_RED_SAND)

            addAfter(Items.CHERRY_HANGING_SIGN, DnDItems.CASCADE_SIGN, DnDItems.CASCADE_HANGING_SIGN)

            addCandles()
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

    fun mainTab(params: CreativeModeTab.ItemDisplayParameters, output: CreativeModeTab.Output) {
        output.addLists(
            DnDItemLists.cascadeWood,
            DnDItemLists.cascadeSigns,
            DnDItemLists.sypiaWood,
            DnDItemLists.sypiaSigns
        )
        output.addItems(
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
        output.addLists(
            DnDBlockLists.flowerbedBlocks,
            DnDBlockLists.vivionbedBlocks,
        )
        output.addItems(
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
        output.addLists(
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
        output.addItems(
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

        output.addLists(
            DnDItemLists.leafPiles,
            DnDBlocks.POLISHED_STONE,
            DnDBlocks.MOSSY_POLISHED_STONE,
            DnDBlocks.OVERGROWN_POLISHED_STONE,
            DnDBlocks.OVERGROWN_COBBLESTONE,
            DnDBlocks.OVERGROWN_STONE_BRICKS,
            DnDItemLists.ice
        )
        output.addItems(
            DnDBlocks.ROOT_BLOCK,
            DnDBlocks.STONE_PILLAR,
            DnDBlocks.DEEPSLATE_PILLAR,
            DnDBlocks.STONE_GRAVESTONE,
            DnDBlocks.SMALL_STONE_GRAVESTONE,
            DnDBlocks.DEEPSLATE_GRAVESTONE,
            DnDBlocks.SMALL_DEEPSLATE_GRAVESTONE,
            DnDBlocks.TUFF_GRAVESTONE,
            DnDBlocks.SMALL_TUFF_GRAVESTONE,
            DnDBlocks.BLACKSTONE_GRAVESTONE,
            DnDBlocks.SMALL_BLACKSTONE_GRAVESTONE,

            DnDBlocks.HEADSTONE,

            DnDBlocks.BIG_CHAIN,
            DnDBlocks.BIG_LANTERN,
            DnDBlocks.BIG_SOUL_LANTERN,
            DnDBlocks.BIG_REDSTONE_LANTERN,
            DnDBlocks.BIG_SCAFFOLDING
        )
        output.addItems( // This adds the candles in a nice way
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
        output.accept(DnDBlocks.WARPED_WART)
        output.addLists(DnDItemLists.netherrackStuff, DnDItemLists.netherBrickStuff)
        output.accept(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
        output.addLists(
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
