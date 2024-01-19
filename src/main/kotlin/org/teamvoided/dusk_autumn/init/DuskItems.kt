package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DuskAutumns

object DuskItems {

    val BLUE_PLANKS = register("blue_planks", BlockItem(DuskBlocks.BLUE_PLANKS, Item.Settings()))
    val STRIPPED_BLUE_LOG = register("stripped_blue_log", BlockItem(DuskBlocks.STRIPPED_BLUE_LOG, Item.Settings()))
    val BLUE_LOG = register("blue_log", BlockItem(DuskBlocks.BLUE_LOG, Item.Settings()))

    val BLUE_PETALS = register("blue_petals", BlockItem(DuskBlocks.BLUE_PETALS, Item.Settings()))
    val VIOLET_DAISY = register("violet_daisy", BlockItem(DuskBlocks.VIOLET_DAISY, Item.Settings()))

    val GOLDEN_BIRCH_LEAVES = register("golden_birch_leaves", BlockItem(DuskBlocks.GOLDEN_BIRCH_LEAVES, Item.Settings()))
    val GOLDEN_BIRCH_SAPLING = register("golden_birch_sapling", BlockItem(DuskBlocks.GOLDEN_BIRCH_SAPLING, Item.Settings()))
    val RED_LEAVES = register("red_leaves", BlockItem(DuskBlocks.RED_LEAVES, Item.Settings()))

    val RED_LEAF_PILE = register("red_leaf_pile", BlockItem(DuskBlocks.RED_LEAF_PILE, Item.Settings()))
    val OAK_LEAF_PILE = register("oak_leaf_pile", BlockItem(DuskBlocks.OAK_LEAF_PILE, Item.Settings()))
    val BIRCH_LEAF_PILE = register("birch_leaf_pile", BlockItem(DuskBlocks.BIRCH_LEAF_PILE, Item.Settings()))
    val SPRUCE_LEAF_PILE = register("spruce_leaf_pile", BlockItem(DuskBlocks.SPRUCE_LEAF_PILE, Item.Settings()))
    val JUNGLE_LEAF_PILE = register("jungle_leaf_pile", BlockItem(DuskBlocks.JUNGLE_LEAF_PILE, Item.Settings()))
    val ACACIA_LEAF_PILE = register("acacia_leaf_pile", BlockItem(DuskBlocks.ACACIA_LEAF_PILE, Item.Settings()))
    val DARK_OAK_LEAF_PILE = register("dark_oak_leaf_pile", BlockItem(DuskBlocks.DARK_OAK_LEAF_PILE, Item.Settings()))
    val MANGROVE_LEAF_PILE = register("mangrove_leaf_pile", BlockItem(DuskBlocks.MANGROVE_LEAF_PILE, Item.Settings()))
    val CHERRY_LEAF_PILE = register("cherry_leaf_pile", BlockItem(DuskBlocks.CHERRY_LEAF_PILE, Item.Settings()))
    val AZALEA_LEAF_PILE = register("azalea_leaf_pile", BlockItem(DuskBlocks.AZALEA_LEAF_PILE, Item.Settings()))
    val FLOWERING_AZALEA_LEAF_PILE = register("flowering_azalea_leaf_pile", BlockItem(DuskBlocks.FLOWERING_AZALEA_LEAF_PILE, Item.Settings()))
    val GOLDEN_BIRCH_LEAF_PILE = register("golden_birch_leaf_pile", BlockItem(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, Item.Settings()))

    fun init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL_BLOCKS)
            .register(ItemGroupEvents.ModifyEntries {
                it.addAfter(
                    Items.FLOWERING_AZALEA_LEAVES,
                    GOLDEN_BIRCH_LEAVES,
                    OAK_LEAF_PILE,
                    SPRUCE_LEAF_PILE,
                    BIRCH_LEAF_PILE,
                    JUNGLE_LEAF_PILE,
                    ACACIA_LEAF_PILE,
                    DARK_OAK_LEAF_PILE,
                    MANGROVE_LEAF_PILE,
                    CHERRY_LEAF_PILE,
                    AZALEA_LEAF_PILE,
                    FLOWERING_AZALEA_LEAF_PILE,
                    GOLDEN_BIRCH_LEAF_PILE,
                    GOLDEN_BIRCH_SAPLING
                )
            })
    }

    fun initClient() {
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getColor(0.8, 0.4) },
            *arrayOf<Item>(
                OAK_LEAF_PILE,
                JUNGLE_LEAF_PILE,
                ACACIA_LEAF_PILE,
                DARK_OAK_LEAF_PILE,
                MANGROVE_LEAF_PILE
            )
        )
        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getSpruceColor()}, SPRUCE_LEAF_PILE)
        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getBirchColor()}, BIRCH_LEAF_PILE)
        ColorProviderRegistry.ITEM.register({ _, _ -> DuskBlocks.GOLDEN_BIRCH_COLOR }, *arrayOf(GOLDEN_BIRCH_LEAVES,GOLDEN_BIRCH_LEAF_PILE))
        ColorProviderRegistry.ITEM.register({ _, _ -> DuskBlocks.RED_LEAF_COLOR }, *arrayOf(RED_LEAVES, RED_LEAF_PILE))
    }

    fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, DuskAutumns.id(id), item)
    }
}