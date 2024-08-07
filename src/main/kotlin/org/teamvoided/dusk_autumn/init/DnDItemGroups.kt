package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.minecraft.entity.projectile.org.teamvoided.dusk_autumn.util.DnDItemLists
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.util.addAfter
import org.teamvoided.dusk_autumn.util.addItem
import org.teamvoided.dusk_autumn.util.addLists
import org.teamvoided.dusk_autumn.util.addToTab
import kotlin.jvm.optionals.getOrNull


object DnDItemGroups {
    val DUSK_AUTUMN_TAB: ItemGroup = register("dusk_items",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDBlocks.CASCADE_SAPLING.asItem()) }
            .name(Text.translatable("itemGroup.dusk_autumn.dusk_items"))
            .entries { _, entries ->
                entries.addLists(DnDItemLists.cascadeWood, DnDItemLists.cascadeSigns)
                entries.addItem(
                    DnDItems.BLUE_DOOR,
                    DnDBlocks.CASCADE_SAPLING,
                    DnDBlocks.CASCADE_LEAVES,
                    DnDBlocks.GOLDEN_BIRCH_SAPLING,
                    DnDBlocks.GOLDEN_BIRCH_LEAVES,
                    DnDBlocks.BLUE_PETALS,
                    DnDBlocks.ROOT_BLOCK,
                    DnDItems.FARMERS_HAT,
                    DnDItems.WILD_WHEAT,
                    DnDItems.GOLDEN_BEETROOT,
                )
                entries.addLists(
                    DnDItemLists.moonberry,
//                    DnDItemLists.pineWood,
                    DnDItemLists.bonewoodWood,
                    DnDItemLists.logPiles,
                    DnDItemLists.leafPiles,
                    DnDItemLists.overgrownCobblestone,
                    DnDItemLists.overgrownStoneBricks,
                    DnDItemLists.snowyStoneBricks,
                )
                entries.addItem(
                    DnDBlocks.STONE_PILLAR,
                    DnDBlocks.DEEPSLATE_PILLAR,
                    DnDBlocks.TALL_REDSTONE_CRYSTAL,
                )
                entries.addItem(
                    // big items but not candles
                    DnDBlocks.BIG_CHAIN,
                    DnDBlocks.BIG_LANTERN,
                    DnDBlocks.BIG_SOUL_LANTERN,
                )
                entries.addItem( // This add the candles in a nice way
                    DnDItemLists.bigCandles.flatMapIndexed { idx, item ->
                        listOf(item, DnDItemLists.soulCandles[idx], DnDItemLists.bigSoulCandles[idx])
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
                entries.addItem(
                    DnDItems.CHILL_CHARGE,
                    DnDItems.FREEZE_ROD,
                    DnDItems.ICE_SWORD
                )
            }.build()
    )
    val OVERLAY_BLOCKS_TAB: ItemGroup = register("overlay_blocks",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDBlocks.ROCKY_GRASS.asItem()) }
            .name(Text.translatable("itemGroup.dusk_autumn.overlay_blocks"))
            .entries { _, entries ->
                entries.addLists(
                    DnDItemLists.overlayBlocks
                )
            }.build()
    )

    fun init() {

        addToTab(ItemGroups.BUILDING_BLOCKS) {
            it.addAfter(Items.CHERRY_BUTTON, DnDItemLists.cascadeWood)
            it.addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)
            it.addAfter(Items.NETHERRACK, DnDItemLists.netherrackStuff)
            it.addAfter(Items.CHISELED_NETHER_BRICKS, DnDItemLists.netherBrickStuff)
            it.addAfter(Items.RED_NETHER_BRICKS, DnDBlocks.CRACKED_RED_NETHER_BRICKS)
            it.addAfter(
                Items.RED_NETHER_BRICK_WALL,
                DnDItemLists.redNetherBrickStuff + DnDItemLists.mixedRedNetherBrickStuff + DnDItemLists.blueNetherBrickStuff +
                        DnDItemLists.mixedBlueNetherBrickStuff + DnDItemLists.grayNetherBrickStuff + DnDItemLists.mixedGrayNetherBrickStuff
            )
            it.addAfter(Items.MOSSY_COBBLESTONE_WALL, DnDItemLists.overgrownCobblestone)
            it.addAfter(Items.MOSSY_STONE_BRICK_WALL, DnDItemLists.overgrownStoneBricks + DnDItemLists.snowyStoneBricks)
        }

        addToTab(ItemGroups.COLORED_BLOCKS) { it.addCandles() }

        addToTab(ItemGroups.FUNCTIONAL_BLOCKS) {
            it.addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)
            it.addAfter(Items.LANTERN, DnDBlocks.BIG_LANTERN)
            it.addAfter(Items.SOUL_LANTERN, DnDBlocks.BIG_SOUL_LANTERN)

            it.addAfter(Items.CHERRY_HANGING_SIGN, DnDItems.CASCADE_SIGN, DnDItems.CASCADE_HANGING_SIGN)

            it.addCandles()
        }

        addToTab(ItemGroups.NATURAL_BLOCKS) {
            it.addAfter(Items.CHERRY_LOG, DnDBlocks.CASCADE_LOG)

            it.addAfter(
                Items.FLOWERING_AZALEA_LEAVES,
                listOf(DnDBlocks.CASCADE_LEAVES, DnDBlocks.GOLDEN_BIRCH_LEAVES) + DnDItemLists.leafPiles
            )
            it.addAfter(Items.FLOWERING_AZALEA, DnDBlocks.CASCADE_SAPLING, DnDBlocks.GOLDEN_BIRCH_SAPLING)
            it.addAfter(Items.VINE, DnDItemLists.moonberry)
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
    }

    @Suppress("SameParameterValue")
    private fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }

    private fun FabricItemGroupEntries.addCandles() {
        this.addAfter(Items.CANDLE, DnDBlocks.BIG_CANDLE, DnDBlocks.SOUL_CANDLE, DnDBlocks.BIG_SOUL_CANDLE)
        this.addAfter(
            Items.WHITE_CANDLE, DnDBlocks.BIG_WHITE_CANDLE, DnDBlocks.WHITE_SOUL_CANDLE, DnDBlocks.BIG_WHITE_SOUL_CANDLE
        )
        this.addAfter(
            Items.LIGHT_GRAY_CANDLE,
            DnDBlocks.BIG_LIGHT_GRAY_CANDLE, DnDBlocks.LIGHT_GRAY_SOUL_CANDLE, DnDBlocks.BIG_LIGHT_GRAY_SOUL_CANDLE
        )
        this.addAfter(
            Items.GRAY_CANDLE, DnDBlocks.BIG_GRAY_CANDLE, DnDBlocks.GRAY_SOUL_CANDLE, DnDBlocks.BIG_GRAY_SOUL_CANDLE
        )
        this.addAfter(
            Items.BLACK_CANDLE, DnDBlocks.BIG_BLACK_CANDLE, DnDBlocks.BLACK_SOUL_CANDLE, DnDBlocks.BIG_BLACK_SOUL_CANDLE
        )
        this.addAfter(
            Items.BROWN_CANDLE, DnDBlocks.BIG_BROWN_CANDLE, DnDBlocks.BROWN_SOUL_CANDLE, DnDBlocks.BIG_BROWN_SOUL_CANDLE
        )
        this.addAfter(
            Items.RED_CANDLE, DnDBlocks.BIG_RED_CANDLE, DnDBlocks.RED_SOUL_CANDLE, DnDBlocks.BIG_RED_SOUL_CANDLE
        )
        this.addAfter(
            Items.ORANGE_CANDLE,
            DnDBlocks.BIG_ORANGE_CANDLE, DnDBlocks.ORANGE_SOUL_CANDLE, DnDBlocks.BIG_ORANGE_SOUL_CANDLE
        )
        this.addAfter(
            Items.YELLOW_CANDLE,
            DnDBlocks.BIG_YELLOW_CANDLE, DnDBlocks.YELLOW_SOUL_CANDLE, DnDBlocks.BIG_YELLOW_SOUL_CANDLE
        )
        this.addAfter(
            Items.LIME_CANDLE, DnDBlocks.BIG_LIME_CANDLE, DnDBlocks.LIME_SOUL_CANDLE, DnDBlocks.BIG_LIME_SOUL_CANDLE
        )
        this.addAfter(
            Items.GREEN_CANDLE, DnDBlocks.BIG_GREEN_CANDLE, DnDBlocks.GREEN_SOUL_CANDLE, DnDBlocks.BIG_GREEN_SOUL_CANDLE
        )
        this.addAfter(
            Items.CYAN_CANDLE, DnDBlocks.BIG_CYAN_CANDLE, DnDBlocks.CYAN_SOUL_CANDLE, DnDBlocks.BIG_CYAN_SOUL_CANDLE
        )
        this.addAfter(
            Items.BLUE_CANDLE, DnDBlocks.BIG_BLUE_CANDLE, DnDBlocks.BLUE_SOUL_CANDLE, DnDBlocks.BIG_BLUE_SOUL_CANDLE
        )
        this.addAfter(
            Items.LIGHT_BLUE_CANDLE,
            DnDBlocks.BIG_LIGHT_BLUE_CANDLE, DnDBlocks.LIGHT_BLUE_SOUL_CANDLE, DnDBlocks.BIG_LIGHT_BLUE_SOUL_CANDLE
        )
        this.addAfter(
            Items.PURPLE_CANDLE,
            DnDBlocks.BIG_PURPLE_CANDLE, DnDBlocks.PURPLE_SOUL_CANDLE, DnDBlocks.BIG_PURPLE_SOUL_CANDLE
        )
        this.addAfter(
            Items.MAGENTA_CANDLE,
            DnDBlocks.BIG_MAGENTA_CANDLE, DnDBlocks.MAGENTA_SOUL_CANDLE, DnDBlocks.BIG_MAGENTA_SOUL_CANDLE
        )
        this.addAfter(
            Items.PINK_CANDLE, DnDBlocks.BIG_PINK_CANDLE, DnDBlocks.PINK_SOUL_CANDLE, DnDBlocks.BIG_PINK_SOUL_CANDLE
        )
    }
}


