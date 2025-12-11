package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.block.Blocks
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.Items
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.DnDItemLists
import org.teamvoided.dusks_and_dungeons.util.addCandles
import org.teamvoided.dusks_and_dungeons.util.addWoodStuffAndLeafPiles
import org.teamvoided.voidlib.helpers.mc.*


@Suppress("unused")
object DnDTabs {
    val DUSKS_AND_DUNGEONS = register(
        MODID, FabricItemGroup.builder()
            .icon(DnDBlocks.CASCADE_SAPLING).translation("itemGroup.$MODID.$MODID")
            .entries { _, entries ->
                entries.addLists(DnDItemLists.cascadeWood, DnDItemLists.cascadeSigns)
                entries.addItems(
                    DnDItems.BLUE_DOOR,
                    DnDBlocks.CASCADE_SAPLING,
                    DnDBlocks.CASCADE_LEAVES,
                    DnDBlocks.GOLDEN_BIRCH_SAPLING,
                    DnDBlocks.GOLDEN_BIRCH_LEAVES,
                    DnDItems.FARMERS_HAT,
                    DnDItems.WILD_WHEAT,
                    DnDItems.GOLDEN_BEETROOT,
                )
                entries.addItems(DnDItems.MOONBERRY_VINELET, DnDBlocks.MOONBERRY_VINE, DnDItems.MOONBERRIES)
                entries.addLists(
                    DnDBlockLists.flowerbedBlocks,
                    DnDBlockLists.vivionbedBlocks,
                )
                entries.addItems(
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
                )
                entries.addLists(
                    DnDItemLists.woodStuff,
                    DnDItemLists.logPiles,
                    DnDItemLists.leafPiles,
                    DnDBlocks.POLISHED_STONE,
                    DnDBlocks.MOSSY_POLISHED_STONE,
                    DnDBlocks.OVERGROWN_POLISHED_STONE,
                    DnDBlocks.OVERGROWN_COBBLESTONE,
                    DnDBlocks.OVERGROWN_STONE_BRICKS,
                    DnDItemLists.ice
                )
                entries.addItems(
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
                )
                entries.addItems( // This adds the candles in a nice way
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
                entries.addItem(DnDBlocks.WARPED_WART)
                entries.addLists(DnDItemLists.netherrackStuff, DnDItemLists.netherBrickStuff)
                entries.addItem(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
                entries.addLists(
                    DnDItemLists.redNetherBrickStuff,
                    DnDItemLists.mixedRedNetherBrickStuff,
                    DnDItemLists.blueNetherBrickStuff,
                    DnDItemLists.mixedBlueNetherBrickStuff,
                    DnDItemLists.grayNetherBrickStuff,
                    DnDItemLists.mixedGrayNetherBrickStuff,
                    DnDItemLists.blackstoneTools,
                )
            }
    )
    val OVERLAY_BLOCKS = register(
        "overlay_blocks", FabricItemGroup.builder()
            .icon(DnDBlocks.ROCKY_BLOCKS.grass).translation("itemGroup.$MODID.overlay_blocks")
            .entries { _, entries -> entries.addLists(DnDItemLists.overlayBlocks) }
    )

    // Dev Tabs
    val DND_EVERYTHING = register(
        "dnd_everything", FabricItemGroup.builder()
            .icon(DnDBlocks.STONE_PILLAR).name("DnD Everything")
            .entries { _, entries ->
                if (isDev()) entries.addLists(DnDItems.ITEMS)
            }
    )
    val DND_EXPERIMENTAL = register(
        "dnd_experimental", FabricItemGroup.builder()
            .icon(Blocks.BARRIER).name("DnD Experimental")
            .entries { _, entries -> if (isDev()) entries.addLists(EVIL_ITEMS) }
    )

    fun init() {
        modifyTab(ItemGroups.BUILDING_BLOCKS) {
            addAfter(Items.CHERRY_BUTTON, DnDItemLists.cascadeWood)
            addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)
            addAfter(Items.NETHERRACK, DnDItemLists.netherrackStuff)
            addAfter(Items.CHISELED_NETHER_BRICKS, DnDItemLists.netherBrickStuff)
            addAfter(Items.RED_NETHER_BRICKS, DnDBlocks.CRACKED_RED_NETHER_BRICKS)
            addAfter(
                Items.RED_NETHER_BRICK_WALL,
                DnDItemLists.redNetherBrickStuff + DnDItemLists.mixedRedNetherBrickStuff +
                        DnDItemLists.blueNetherBrickStuff + DnDItemLists.mixedBlueNetherBrickStuff +
                        DnDItemLists.grayNetherBrickStuff + DnDItemLists.mixedGrayNetherBrickStuff
            )
            addAfter(Items.MOSSY_COBBLESTONE_WALL, DnDItemLists.overgrownCobblestone)
            addAfter(Items.MOSSY_STONE_BRICK_WALL, DnDItemLists.overgrownStoneBricks)
            addWoodStuffAndLeafPiles(false)
        }

        modifyTab(ItemGroups.COLORED_BLOCKS) { addCandles() }

        modifyTab(ItemGroups.FUNCTIONAL_BLOCKS) {
            addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)
            addAfter(Items.LANTERN, DnDBlocks.BIG_LANTERN)
            addAfter(Items.SOUL_LANTERN, DnDBlocks.BIG_SOUL_LANTERN)

            addAfter(Items.CHERRY_HANGING_SIGN, DnDItems.CASCADE_SIGN, DnDItems.CASCADE_HANGING_SIGN)

            addCandles()
        }

        modifyTab(ItemGroups.NATURAL_BLOCKS) {
            addAfter(Items.CHERRY_LOG, DnDBlocks.CASCADE_LOG)
            addBefore(Items.PINK_PETALS, DnDBlockLists.flowerbedBlocks)
            addAfter(Items.PINK_PETALS, DnDBlockLists.vivionbedBlocks)
            addAfter(
                Items.FLOWERING_AZALEA_LEAVES,
                listOf(DnDBlocks.CASCADE_LEAVES, DnDBlocks.GOLDEN_BIRCH_LEAVES)
            )
            addAfter(Items.FLOWERING_AZALEA, DnDBlocks.CASCADE_SAPLING, DnDBlocks.GOLDEN_BIRCH_SAPLING)
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

        modifyTab(ItemGroups.COMBAT) {
            addAfter(Items.STONE_SWORD, DnDItems.BLACKSTONE_SWORD)
            addAfter(Items.STONE_AXE, DnDItems.BLACKSTONE_AXE)
        }

        modifyTab(ItemGroups.TOOLS_AND_UTILITIES) {
            addAfter( // this is what you should have done dusk >:( // L plus M N O P =)
                Items.STONE_HOE,
                DnDItems.BLACKSTONE_SHOVEL, DnDItems.BLACKSTONE_PICKAXE,
                DnDItems.BLACKSTONE_AXE, DnDItems.BLACKSTONE_HOE
            )
        }

        modifyTab(ItemGroups.FOOD_AND_DRINKS) {
            addAfter(Items.SWEET_BERRIES, DnDItems.MOONBERRIES)
            addAfter(Items.GOLDEN_CARROT, DnDItems.CORN)
            addAfter(Items.BEETROOT, DnDItems.GOLDEN_BEETROOT)
            addAfter(Items.HONEY_BOTTLE, DnDItems.CORN_SYRUP_BOTTLE)
        }
        modifyTab(ItemGroups.REDSTONE_BLOCKS) {
            addAfter(Items.HONEY_BLOCK, DnDBlocks.CORN_SYRUP_BLOCK)
        }
    }

    fun register(name: String, itemGroup: ItemGroup.Builder): Holder.Reference<ItemGroup> =
        Registry.registerHolder(Registries.ITEM_GROUP, id(name), itemGroup.build())
}
