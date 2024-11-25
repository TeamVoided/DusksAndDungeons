package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.MODID
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusks_and_dungeons.init.blocks.*
import org.teamvoided.dusks_and_dungeons.util.*
import kotlin.jvm.optionals.getOrNull


@Suppress("unused")
object DnDTabs {
    val DUSKS_AND_DUNGEONS: ItemGroup = register(MODID, FabricItemGroup.builder()
        .icon { ItemStack(DnDWoodBlocks.CASCADE_SAPLING) }
        .name(Text.translatable("itemGroup.dusks_and_dungeons.$MODID"))
        .entries { _, entries ->
            entries.addLists(DnDItemLists.cascadeWood, DnDItemLists.cascadeSigns)
            entries.addItem(
                DnDItems.BLUE_DOOR,
                DnDWoodBlocks.CASCADE_SAPLING,
                DnDWoodBlocks.CASCADE_LEAVES,
                DnDWoodBlocks.GOLDEN_BIRCH_SAPLING,
                DnDWoodBlocks.GOLDEN_BIRCH_LEAVES,
                DnDItems.FARMERS_HAT,
                DnDItems.WILD_WHEAT,
                DnDItems.GOLDEN_BEETROOT,
            )
            entries.addItem(DnDItems.MOONBERRY_VINELET, DnDFloraBlocks.MOONBERRY_VINE, DnDItems.MOONBERRIES)
            entries.addLists(
                DnDBlockLists.flowerbedBlocks,
                DnDBlockLists.vivionbedBlocks,
            )
            entries.addItem(
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
            entries.addItem(
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
            entries.addItem( // This adds the candles in a nice way
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
    val OVERLAY_BLOCKS: ItemGroup = register("overlay_blocks",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDOverlayBlocks.ROCKY_GRASS) }
            .name(Text.translatable("itemGroup.dusks_and_dungeons.overlay_blocks"))
            .entries { _, entries -> entries.addLists(DnDItemLists.overlayBlocks) }
            .build()
    )

    // Dev Tabs
    val DUSKS_AND_DUNGEONS_EXCEPT_DEBUG: ItemGroup = register("dnd_everything",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDStoneBlocks.STONE_PILLAR.asItem()) }
            .name(Text.translatable("Dusk's and Dungeons Except Debug"))
            .entries { params, entries ->
                if (isDev() && params.hasPermissions) entries.addLists(
                    DnDItems.ITEMS.filterNot(EVIL_ITEMS::contains).filterNot(SECRET_ITEMS::contains)
                )
            }
            .build()
    )
    val DND_EXPERIMENTAL: ItemGroup = register("dnd_experimental",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDItems.GALLERY_MAPLE_DOOR) }
            .name(Text.literal("DnD Experimental"))
            .entries { params, entries -> if (isDev() && params.hasPermissions) entries.addLists(EVIL_ITEMS) }
            .build()
    )

    fun init() {
        addToTab(ItemGroups.BUILDING_BLOCKS) {
            it.addAfter(Items.CHERRY_BUTTON, DnDItemLists.cascadeWood)
            it.addAfter(Items.CHAIN, DnDBigBlocks.BIG_CHAIN)
            it.addAfter(Items.NETHERRACK, DnDItemLists.netherrackStuff)
            it.addAfter(Items.CHISELED_NETHER_BRICKS, DnDItemLists.netherBrickStuff)
            it.addAfter(Items.RED_NETHER_BRICKS, DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS)
            it.addAfter(
                Items.RED_NETHER_BRICK_WALL,
                DnDItemLists.redNetherBrickStuff + DnDItemLists.mixedRedNetherBrickStuff + DnDItemLists.blueNetherBrickStuff +
                        DnDItemLists.mixedBlueNetherBrickStuff + DnDItemLists.grayNetherBrickStuff + DnDItemLists.mixedGrayNetherBrickStuff
            )
            it.addAfter(Items.MOSSY_COBBLESTONE_WALL, DnDItemLists.overgrownCobblestone)
            it.addAfter(Items.MOSSY_STONE_BRICK_WALL, DnDItemLists.overgrownStoneBricks)
            it.addWoodStuffAndLeafPiles(false)
        }

        addToTab(ItemGroups.COLORED_BLOCKS) { it.addCandles() }

        addToTab(ItemGroups.FUNCTIONAL_BLOCKS) {
            it.addAfter(Items.CHAIN, DnDBigBlocks.BIG_CHAIN)
            it.addAfter(Items.LANTERN, DnDBigBlocks.BIG_LANTERN)
            it.addAfter(Items.SOUL_LANTERN, DnDBigBlocks.BIG_SOUL_LANTERN)

            it.addAfter(Items.CHERRY_HANGING_SIGN, DnDItems.CASCADE_SIGN, DnDItems.CASCADE_HANGING_SIGN)

            it.addCandles()
        }

        addToTab(ItemGroups.NATURAL_BLOCKS) {
            it.addAfter(Items.CHERRY_LOG, DnDWoodBlocks.CASCADE_LOG)
            it.addBefore(Items.PINK_PETALS, DnDBlockLists.flowerbedBlocks)
            it.addAfter(Items.PINK_PETALS, DnDBlockLists.vivionbedBlocks)
            it.addAfter(
                Items.FLOWERING_AZALEA_LEAVES,
                listOf(DnDWoodBlocks.CASCADE_LEAVES, DnDWoodBlocks.GOLDEN_BIRCH_LEAVES)
            )
            it.addAfter(Items.FLOWERING_AZALEA, DnDWoodBlocks.CASCADE_SAPLING, DnDWoodBlocks.GOLDEN_BIRCH_SAPLING)
            it.addAfter(Items.VINE, DnDItems.MOONBERRY_VINELET, DnDFloraBlocks.MOONBERRY_VINE, DnDItems.MOONBERRIES)
            DnDBlockLists.leafPiles.forEachIndexed { idx, leafPile ->
                it.addAfter(DnDBlockLists.leaves[idx], leafPile)
            }
            it.addAfter(Items.PEONY, DnDItems.CORN_STALK)
            it.addAfter(
                Items.PUMPKIN_SEEDS,
                DnDItems.LANTERN_PUMPKIN_SEEDS,
                DnDItems.MOSSKIN_PUMPKIN_SEEDS,
                DnDItems.PALE_PUMPKIN_SEEDS,
                DnDItems.GLOOM_PUMPKIN_SEEDS,
            )
            it.addAfter(Items.BEETROOT_SEEDS, DnDItems.CORN_KERNELS)
            it.addAfter(
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
            it.addAfter(Items.HONEY_BLOCK, DnDFloraBlocks.CORN_SYRUP_BLOCK)
        }

        addToTab(ItemGroups.COMBAT) {
            it.addAfter(Items.STONE_SWORD, DnDItems.BLACKSTONE_SWORD)
            it.addAfter(Items.STONE_AXE, DnDItems.BLACKSTONE_AXE)
        }

        addToTab(ItemGroups.TOOLS_AND_UTILITIES) {
            it.addAfter( // this is what you should have done dusk >:( // L plus M N O P =)
                Items.STONE_HOE,
                DnDItems.BLACKSTONE_SHOVEL, DnDItems.BLACKSTONE_PICKAXE,
                DnDItems.BLACKSTONE_AXE, DnDItems.BLACKSTONE_HOE
            )
        }

        addToTab(ItemGroups.FOOD_AND_DRINKS) {
            it.addAfter(Items.SWEET_BERRIES, DnDItems.MOONBERRIES)
            it.addAfter(Items.GOLDEN_CARROT, DnDItems.CORN)
            it.addAfter(Items.BEETROOT, DnDItems.GOLDEN_BEETROOT)
            it.addAfter(Items.HONEY_BOTTLE, DnDItems.CORN_SYRUP_BOTTLE)
        }
        addToTab(ItemGroups.REDSTONE_BLOCKS) {
            it.addAfter(Items.HONEY_BLOCK, DnDFloraBlocks.CORN_SYRUP_BLOCK)
        }
    }

    @Suppress("SameParameterValue")
    fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }
}


