package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.Blocks
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumn.DuskAutumns

object DuskItems {

    val CASCADE_SAPLING = register("cascade_sapling", BlockItem(DuskBlocks.CASCADE_SAPLING, Item.Settings()))
    val CASCADE_LOG = register("cascade_log", BlockItem(DuskBlocks.CASCADE_LOG, Item.Settings()))
    val STRIPPED_CASCADE_LOG =
        register("stripped_cascade_log", BlockItem(DuskBlocks.STRIPPED_CASCADE_LOG, Item.Settings()))
    val CASCADE_PLANKS = register("cascade_planks", BlockItem(DuskBlocks.CASCADE_PLANKS, Item.Settings()))
    val CASCADE_DOOR = net.minecraft.item.Items.register(
        (TallBlockItem(
            DuskBlocks.CASCADE_DOOR,
            net.minecraft.item.Item.Settings()
        ))
    )
    val BLUE_DOOR = net.minecraft.item.Items.register(
        (TallBlockItem(
            DuskBlocks.BLUE_DOOR,
            net.minecraft.item.Item.Settings()
        ))
    )
    val CASCADE_LEAVES = register("cascade_leaves", BlockItem(DuskBlocks.CASCADE_LEAVES, Item.Settings()))
    val CASCADE_SIGN = register(
        "cascade_sign",
        SignItem(
            (Item.Settings()).maxCount(16),
            DuskBlocks.CASCADE_SIGN,
            DuskBlocks.CASCADE_WALL_SIGN
        )
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        HangingSignItem(
            DuskBlocks.CASCADE_HANGING_SIGN,
            DuskBlocks.CASCADE_WALL_HANGING_SIGN,
            (Item.Settings()).maxCount(16)
        )
    )

    val BLUE_PETALS = register("blue_petals", BlockItem(DuskBlocks.BLUE_PETALS, Item.Settings()))
    val VIOLET_DAISY = register("violet_daisy", BlockItem(DuskBlocks.VIOLET_DAISY, Item.Settings()))

    val GOLDEN_BIRCH_LEAVES =
        register("golden_birch_leaves", BlockItem(DuskBlocks.GOLDEN_BIRCH_LEAVES, Item.Settings()))
    val GOLDEN_BIRCH_SAPLING =
        register("golden_birch_sapling", BlockItem(DuskBlocks.GOLDEN_BIRCH_SAPLING, Item.Settings()))

    val CASCADE_LEAF_PILE = register("cascade_leaf_pile", BlockItem(DuskBlocks.CASCADE_LEAF_PILE, Item.Settings()))
    val OAK_LEAF_PILE = register("oak_leaf_pile", BlockItem(DuskBlocks.OAK_LEAF_PILE, Item.Settings()))
    val BIRCH_LEAF_PILE = register("birch_leaf_pile", BlockItem(DuskBlocks.BIRCH_LEAF_PILE, Item.Settings()))
    val SPRUCE_LEAF_PILE = register("spruce_leaf_pile", BlockItem(DuskBlocks.SPRUCE_LEAF_PILE, Item.Settings()))
    val JUNGLE_LEAF_PILE = register("jungle_leaf_pile", BlockItem(DuskBlocks.JUNGLE_LEAF_PILE, Item.Settings()))
    val ACACIA_LEAF_PILE = register("acacia_leaf_pile", BlockItem(DuskBlocks.ACACIA_LEAF_PILE, Item.Settings()))
    val DARK_OAK_LEAF_PILE = register("dark_oak_leaf_pile", BlockItem(DuskBlocks.DARK_OAK_LEAF_PILE, Item.Settings()))
    val MANGROVE_LEAF_PILE = register("mangrove_leaf_pile", BlockItem(DuskBlocks.MANGROVE_LEAF_PILE, Item.Settings()))
    val CHERRY_LEAF_PILE = register("cherry_leaf_pile", BlockItem(DuskBlocks.CHERRY_LEAF_PILE, Item.Settings()))
    val AZALEA_LEAF_PILE = register("azalea_leaf_pile", BlockItem(DuskBlocks.AZALEA_LEAF_PILE, Item.Settings()))
    val FLOWERING_AZALEA_LEAF_PILE =
        register("flowering_azalea_leaf_pile", BlockItem(DuskBlocks.FLOWERING_AZALEA_LEAF_PILE, Item.Settings()))
    val GOLDEN_BIRCH_LEAF_PILE =
        register("golden_birch_leaf_pile", BlockItem(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, Item.Settings()))

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
        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getSpruceColor() }, SPRUCE_LEAF_PILE)
        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getBirchColor() }, BIRCH_LEAF_PILE)
        ColorProviderRegistry.ITEM.register(
            { _, _ -> DuskBlocks.CASCADE_LEAF_COLOR },
            *arrayOf(CASCADE_LEAVES, CASCADE_LEAF_PILE)
        )
    }

    fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, DuskAutumns.id(id), item)
    }
}