package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusks_and_dungeons.init.blocks.*
import org.teamvoided.dusks_and_dungeons.util.*
import org.teamvoided.voidlib.helpers.mc.*


@Suppress("unused")
object DnDTabs {
    val DUSKS_AND_DUNGEONS = register(MODID, FabricItemGroup.builder()
        .icon { ItemStack(DnDWoodBlocks.CASCADE_SAPLING) }
        .name(Text.translatable("itemGroup.dusks_and_dungeons.$MODID"))
        .entries { _, entries ->
            entries.addLists(DnDItemLists.cascadeWood, DnDItemLists.cascadeSigns)
            entries.addItems(
                DnDItems.BLUE_DOOR,
                DnDWoodBlocks.CASCADE_SAPLING,
                DnDWoodBlocks.CASCADE_LEAVES,
                DnDWoodBlocks.GOLDEN_BIRCH_SAPLING,
                DnDWoodBlocks.GOLDEN_BIRCH_LEAVES,
                DnDItems.FARMERS_HAT,
                DnDItems.WILD_WHEAT,
                DnDItems.GOLDEN_BEETROOT,
            )
            entries.addItems(DnDItems.MOONBERRY_VINELET, DnDFloraBlocks.MOONBERRY_VINE, DnDItems.MOONBERRIES)
            entries.addLists(
                DnDBlockLists.flowerbedBlocks,
                DnDBlockLists.vivionbedBlocks,
            )
            entries.addItems(
                DnDItems.CORN_STALK,
                DnDItems.CORN_KERNELS,
                DnDItems.CORN,
                DnDFloraBlocks.CORN_BLOCK,
                DnDFloraBlocks.CORN_SYRUP_BLOCK,
                DnDItems.CORN_SYRUP_BOTTLE,

                DnDFloraBlocks.SMALL_PUMPKIN,
                DnDFloraBlocks.SMALL_CARVED_PUMPKIN,
                DnDFloraBlocks.SMALL_GLOWING_PUMPKIN,

                DnDItems.LANTERN_PUMPKIN_SEEDS,
                DnDFloraBlocks.LANTERN_PUMPKIN,
                DnDFloraBlocks.CARVED_LANTERN_PUMPKIN,
                DnDFloraBlocks.GLOWING_LANTERN_PUMPKIN,
                DnDFloraBlocks.SMALL_LANTERN_PUMPKIN,
                DnDFloraBlocks.SMALL_CARVED_LANTERN_PUMPKIN,
                DnDFloraBlocks.SMALL_GLOWING_LANTERN_PUMPKIN,

                DnDItems.MOSSKIN_PUMPKIN_SEEDS,
                DnDFloraBlocks.MOSSKIN_PUMPKIN,
                DnDFloraBlocks.CARVED_MOSSKIN_PUMPKIN,
                DnDFloraBlocks.GLOWING_MOSSKIN_PUMPKIN,
                DnDFloraBlocks.SMALL_MOSSKIN_PUMPKIN,
                DnDFloraBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN,
                DnDFloraBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN,

                DnDItems.GLOOM_PUMPKIN_SEEDS,
                DnDFloraBlocks.GLOOM_PUMPKIN,
                DnDFloraBlocks.CARVED_GLOOM_PUMPKIN,
                DnDFloraBlocks.GLOWING_GLOOM_PUMPKIN,
                DnDFloraBlocks.SMALL_GLOOM_PUMPKIN,
                DnDFloraBlocks.SMALL_CARVED_GLOOM_PUMPKIN,
                DnDFloraBlocks.SMALL_GLOWING_GLOOM_PUMPKIN,

                DnDItems.PALE_PUMPKIN_SEEDS,
                DnDFloraBlocks.PALE_PUMPKIN,
                DnDFloraBlocks.CARVED_PALE_PUMPKIN,
                DnDFloraBlocks.GLOWING_PALE_PUMPKIN,
                DnDFloraBlocks.SMALL_PALE_PUMPKIN,
                DnDFloraBlocks.SMALL_CARVED_PALE_PUMPKIN,
                DnDFloraBlocks.SMALL_GLOWING_PALE_PUMPKIN,
            )
            entries.addLists(
//                    DnDItemLists.pineWood,
                DnDItemLists.woodStuff,
                DnDItemLists.logPiles,
                DnDItemLists.leafPiles,
                DnDItemLists.polishedStone,
                DnDItemLists.mossyPolishedStone,
                DnDItemLists.overgrownCobblestone,
                DnDItemLists.overgrownStoneBricks,
//                DnDItemLists.snowyStoneBricks,
                DnDItemLists.ice
            )
            entries.addItems(
                DnDFloraBlocks.ROOT_BLOCK,
                DnDStoneBlocks.STONE_PILLAR,
                DnDStoneBlocks.DEEPSLATE_PILLAR,
//                DnDBlocks.TALL_REDSTONE_CRYSTAL,
                DnDStoneBlocks.GRAVESTONE,
                DnDStoneBlocks.SMALL_GRAVESTONE,
                DnDStoneBlocks.DEEPSLATE_GRAVESTONE,
                DnDStoneBlocks.SMALL_DEEPSLATE_GRAVESTONE,
                DnDStoneBlocks.TUFF_GRAVESTONE,
                DnDStoneBlocks.SMALL_TUFF_GRAVESTONE,
                DnDStoneBlocks.BLACKSTONE_GRAVESTONE,
                DnDStoneBlocks.SMALL_BLACKSTONE_GRAVESTONE,

                DnDStoneBlocks.HEADSTONE,

                DnDBigBlocks.BIG_CHAIN,
                DnDBigBlocks.BIG_LANTERN,
                DnDBigBlocks.BIG_SOUL_LANTERN,
            )
            entries.addItems( // This adds the candles in a nice way
                DnDItemLists.bigCandles.flatMapIndexed { idx, item ->
                    listOf(
                        item,
                        DnDItemLists.soulCandles[idx],
                        DnDItemLists.bigSoulCandles[idx],
                        DnDBlockLists.candelabras[idx],
                        DnDBlockLists.soulCandelabras[idx],
                    )
                }
            )
            entries.addItem(DnDFloraBlocks.WARPED_WART)
            entries.addLists(DnDItemLists.netherrackStuff, DnDItemLists.netherBrickStuff)
            entries.addItem(DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS)
            entries.addLists(
                DnDItemLists.redNetherBrickStuff,
                DnDItemLists.mixedRedNetherBrickStuff,
                DnDItemLists.blueNetherBrickStuff,
                DnDItemLists.mixedBlueNetherBrickStuff,
                DnDItemLists.grayNetherBrickStuff,
                DnDItemLists.mixedGrayNetherBrickStuff,
                DnDItemLists.blackstoneTools,
            )
        }.build()
    )
    val OVERLAY_BLOCKS = register("overlay_blocks",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDOverlayBlocks.ROCKY_GRASS) }
            .name(Text.translatable("itemGroup.dusks_and_dungeons.overlay_blocks"))
            .entries { _, entries -> entries.addLists(DnDItemLists.overlayBlocks) }
            .build()
    )

    // Dev Tabs
    val DUSKS_AND_DUNGEONS_EXCEPT_DEBUG = register("dnd_everything",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDStoneBlocks.STONE_PILLAR.asItem()) }
            .name(Text.translatable("DnD Everything"))
            .entries { params, entries ->
                if (isDev())
                    entries.addLists(DnDItems.ITEMS.filterNot(EVIL_ITEMS::contains).filterNot(SECRET_ITEMS::contains))
            }
            .build()
    )
    val DND_EXPERIMENTAL = register("dnd_experimental",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDItems.GALLERY_MAPLE_DOOR) }
            .name(Text.literal("DnD Experimental"))
            .entries { params, entries -> if (isDev()) entries.addLists(EVIL_ITEMS) }
            .build()
    )

    fun init() {
        modifyTab(ItemGroups.BUILDING_BLOCKS) {
            addAfter(Items.CHERRY_BUTTON, DnDItemLists.cascadeWood)
            addAfter(Items.CHAIN, DnDBigBlocks.BIG_CHAIN)
            addAfter(Items.NETHERRACK, DnDItemLists.netherrackStuff)
            addAfter(Items.CHISELED_NETHER_BRICKS, DnDItemLists.netherBrickStuff)
            addAfter(Items.RED_NETHER_BRICKS, DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS)
            addAfter(
                Items.RED_NETHER_BRICK_WALL,
                DnDItemLists.redNetherBrickStuff + DnDItemLists.mixedRedNetherBrickStuff + DnDItemLists.blueNetherBrickStuff +
                        DnDItemLists.mixedBlueNetherBrickStuff + DnDItemLists.grayNetherBrickStuff + DnDItemLists.mixedGrayNetherBrickStuff
            )
            addAfter(Items.MOSSY_COBBLESTONE_WALL, DnDItemLists.overgrownCobblestone)
            addAfter(Items.MOSSY_STONE_BRICK_WALL, DnDItemLists.overgrownStoneBricks)
            addWoodStuffAndLeafPiles(false)
        }

        modifyTab(ItemGroups.COLORED_BLOCKS) { addCandles() }

        modifyTab(ItemGroups.FUNCTIONAL_BLOCKS) {
            addAfter(Items.CHAIN, DnDBigBlocks.BIG_CHAIN)
            addAfter(Items.LANTERN, DnDBigBlocks.BIG_LANTERN)
            addAfter(Items.SOUL_LANTERN, DnDBigBlocks.BIG_SOUL_LANTERN)

            addAfter(Items.CHERRY_HANGING_SIGN, DnDItems.CASCADE_SIGN, DnDItems.CASCADE_HANGING_SIGN)

            addCandles()
        }

        modifyTab(ItemGroups.NATURAL_BLOCKS) {
            addAfter(Items.CHERRY_LOG, DnDWoodBlocks.CASCADE_LOG)
            addBefore(Items.PINK_PETALS, DnDBlockLists.flowerbedBlocks)
            addAfter(Items.PINK_PETALS, DnDBlockLists.vivionbedBlocks)
            addAfter(
                Items.FLOWERING_AZALEA_LEAVES,
                listOf(DnDWoodBlocks.CASCADE_LEAVES, DnDWoodBlocks.GOLDEN_BIRCH_LEAVES)
            )
            addAfter(Items.FLOWERING_AZALEA, DnDWoodBlocks.CASCADE_SAPLING, DnDWoodBlocks.GOLDEN_BIRCH_SAPLING)
            addAfter(Items.VINE, DnDItems.MOONBERRY_VINELET, DnDFloraBlocks.MOONBERRY_VINE, DnDItems.MOONBERRIES)
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
                DnDFloraBlocks.LANTERN_PUMPKIN,
                DnDFloraBlocks.CARVED_LANTERN_PUMPKIN,
                DnDFloraBlocks.GLOWING_LANTERN_PUMPKIN,

                DnDFloraBlocks.MOSSKIN_PUMPKIN,
                DnDFloraBlocks.CARVED_MOSSKIN_PUMPKIN,
                DnDFloraBlocks.GLOWING_MOSSKIN_PUMPKIN,

                DnDFloraBlocks.GLOOM_PUMPKIN,
                DnDFloraBlocks.CARVED_GLOOM_PUMPKIN,
                DnDFloraBlocks.GLOWING_GLOOM_PUMPKIN,

                DnDFloraBlocks.PALE_PUMPKIN,
                DnDFloraBlocks.CARVED_PALE_PUMPKIN,
                DnDFloraBlocks.GLOWING_PALE_PUMPKIN,
            )
            addAfter(Items.HONEY_BLOCK, DnDFloraBlocks.CORN_SYRUP_BLOCK)
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
            addAfter(Items.HONEY_BLOCK, DnDFloraBlocks.CORN_SYRUP_BLOCK)
        }
    }

    fun register(name: String, itemGroup: ItemGroup): Holder.Reference<ItemGroup> =
        Registry.registerHolder(Registries.ITEM_GROUP, id(name), itemGroup)
}
