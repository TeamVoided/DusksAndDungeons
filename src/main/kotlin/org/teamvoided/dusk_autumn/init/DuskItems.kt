package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.block.Block
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns
import org.teamvoided.dusk_autumn.item.DuskFoodComponents
import org.teamvoided.dusk_autumn.item.FarmersHatItem


@Suppress("unused", "MemberVisibilityCanBePrivate")
object DuskItems {
    val CASCADE_SAPLING = register("cascade_sapling", BlockItem(DuskBlocks.CASCADE_SAPLING))
    val CASCADE_LEAVES = register("cascade_leaves", BlockItem(DuskBlocks.CASCADE_LEAVES))
    val CASCADE_LOG = register("cascade_log", BlockItem(DuskBlocks.CASCADE_LOG))
    val STRIPPED_CASCADE_LOG = register("stripped_cascade_log", BlockItem(DuskBlocks.STRIPPED_CASCADE_LOG))
    val CASCADE_PLANKS = register("cascade_planks", BlockItem(DuskBlocks.CASCADE_PLANKS))
    val CASCADE_DOOR = register("cascade_door", TallBlockItem(DuskBlocks.CASCADE_DOOR, Item.Settings()))
    val BLUE_DOOR = register("blue_door", TallBlockItem(DuskBlocks.BLUE_DOOR, Item.Settings()))
    val CASCADE_TRAPDOOR = register("cascade_trapdoor", BlockItem(DuskBlocks.CASCADE_TRAPDOOR))
    val CASCADE_SIGN = register(
        "cascade_sign",
        SignItem(Item.Settings().maxCount(16), DuskBlocks.CASCADE_SIGN, DuskBlocks.CASCADE_WALL_SIGN)
    )
    val CASCADE_HANGING_SIGN = register(
        "cascade_hanging_sign",
        HangingSignItem(
            DuskBlocks.CASCADE_HANGING_SIGN, DuskBlocks.CASCADE_WALL_HANGING_SIGN,
            Item.Settings().maxCount(16)
        )
    )
    val BLUE_PETALS = register("blue_petals", BlockItem(DuskBlocks.BLUE_PETALS))
    val VIOLET_DAISY = register("violet_daisy", BlockItem(DuskBlocks.VIOLET_DAISY))

    val GOLDEN_BIRCH_SAPLING = register("golden_birch_sapling", BlockItem(DuskBlocks.GOLDEN_BIRCH_SAPLING))
    val GOLDEN_BIRCH_LEAVES = register("golden_birch_leaves", BlockItem(DuskBlocks.GOLDEN_BIRCH_LEAVES))

    val OAK_LEAF_PILE = register("oak_leaf_pile", BlockItem(DuskBlocks.OAK_LEAF_PILE))
    val BIRCH_LEAF_PILE = register("birch_leaf_pile", BlockItem(DuskBlocks.BIRCH_LEAF_PILE))
    val SPRUCE_LEAF_PILE = register("spruce_leaf_pile", BlockItem(DuskBlocks.SPRUCE_LEAF_PILE))
    val JUNGLE_LEAF_PILE = register("jungle_leaf_pile", BlockItem(DuskBlocks.JUNGLE_LEAF_PILE))
    val ACACIA_LEAF_PILE = register("acacia_leaf_pile", BlockItem(DuskBlocks.ACACIA_LEAF_PILE))
    val DARK_OAK_LEAF_PILE = register("dark_oak_leaf_pile", BlockItem(DuskBlocks.DARK_OAK_LEAF_PILE))
    val MANGROVE_LEAF_PILE = register("mangrove_leaf_pile", BlockItem(DuskBlocks.MANGROVE_LEAF_PILE))
    val CHERRY_LEAF_PILE = register("cherry_leaf_pile", BlockItem(DuskBlocks.CHERRY_LEAF_PILE))
    val AZALEA_LEAF_PILE = register("azalea_leaf_pile", BlockItem(DuskBlocks.AZALEA_LEAF_PILE))
    val FLOWERING_AZALEA_LEAF_PILE =
        register("flowering_azalea_leaf_pile", BlockItem(DuskBlocks.FLOWERING_AZALEA_LEAF_PILE))
    val CASCADE_LEAF_PILE = register("cascade_leaf_pile", BlockItem(DuskBlocks.CASCADE_LEAF_PILE))
    val GOLDEN_BIRCH_LEAF_PILE = register("golden_birch_leaf_pile", BlockItem(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE))

    val FARMERS_HAT = register(
        "farmers_hat",
        FarmersHatItem(
            Item.Settings().maxCount(1).component(DataComponentTypes.DYED_COLOR, DyedColorComponent(0xb26c20, true))
        )
    )
    val WILD_WHEAT = register("wild_wheat", TallBlockItem(DuskBlocks.WILD_WHEAT, Item.Settings()))
    val GOLDEN_BEETROOT = register(
        "golden_beetroot",
        AliasedBlockItem(
            DuskBlocks.GOLDEN_BEETROOTS, Item.Settings().food(DuskFoodComponents.GOLDEN_BEETROOT)
        )
    )
    val MOONBERRY_VINE = register("moonberry_vine", BlockItem(DuskBlocks.MOONBERRY_VINE))
    val MOONBERRY_VINELET =
        register("moonberry_vinelet", AliasedBlockItem(DuskBlocks.MOONBERRY_VINELET, Item.Settings()))
    val MOONBERRIES = register("moonberries", Item((Item.Settings()).food(DuskFoodComponents.MOONBERRIES)))


    val VOLCANIC_SAND = register("volcanic_sand", BlockItem(DuskBlocks.VOLCANIC_SAND))
    val SUSPICIOUS_VOLCANIC_SAND = register("suspicious_volcanic_sand", BlockItem(DuskBlocks.SUSPICIOUS_VOLCANIC_SAND))
    val VOLCANIC_SANDSTONE = register("volcanic_sandstone", BlockItem(DuskBlocks.VOLCANIC_SANDSTONE))
    val VOLCANIC_SANDSTONE_STAIRS = register("volcanic_sandstone_stairs", BlockItem(DuskBlocks.VOLCANIC_SANDSTONE_STAIRS))
    val VOLCANIC_SANDSTONE_SLAB = register("volcanic_sandstone_slab", BlockItem(DuskBlocks.VOLCANIC_SANDSTONE_SLAB))
    val VOLCANIC_SANDSTONE_WALL = register("volcanic_sandstone_wall", BlockItem(DuskBlocks.VOLCANIC_SANDSTONE_WALL))
    val CUT_VOLCANIC_SANDSTONE = register("cut_volcanic_sandstone", BlockItem(DuskBlocks.CUT_VOLCANIC_SANDSTONE))
    val CUT_VOLCANIC_SANDSTONE_SLAB = register("cut_volcanic_sandstone_slab", BlockItem(DuskBlocks.CUT_VOLCANIC_SANDSTONE_SLAB))
    val CHISELED_VOLCANIC_SANDSTONE = register("chiseled_volcanic_sandstone", BlockItem(DuskBlocks.CHISELED_VOLCANIC_SANDSTONE))
    val SMOOTH_VOLCANIC_SANDSTONE = register("smooth_volcanic_sandstone", BlockItem(DuskBlocks.SMOOTH_VOLCANIC_SANDSTONE))
    val SMOOTH_VOLCANIC_SANDSTONE_STAIRS = register("smooth_volcanic_sandstone_stairs", BlockItem(DuskBlocks.SMOOTH_VOLCANIC_SANDSTONE_STAIRS))
    val SMOOTH_VOLCANIC_SANDSTONE_SLAB = register("smooth_volcanic_sandstone_slab", BlockItem(DuskBlocks.SMOOTH_VOLCANIC_SANDSTONE_SLAB))
//add void util compat


    fun init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL_BLOCKS)
            .register(ModifyEntries {
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
            { _, _ -> FoliageColors.getDefaultColor() },
            OAK_LEAF_PILE,
            JUNGLE_LEAF_PILE,
            ACACIA_LEAF_PILE,
            DARK_OAK_LEAF_PILE,
            MANGROVE_LEAF_PILE
        )

        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getSpruceColor() }, SPRUCE_LEAF_PILE)
        ColorProviderRegistry.ITEM.register({ _, _ -> FoliageColors.getBirchColor() }, BIRCH_LEAF_PILE)
        ColorProviderRegistry.ITEM.register(
            { _, _ -> DuskBlocks.CASCADE_LEAF_COLOR },
            CASCADE_LEAVES,
            CASCADE_LEAF_PILE
        )
        ColorProviderRegistry.ITEM.register({ stack, _ -> stack.get(DataComponentTypes.DYED_COLOR)?.rgb ?: 0xffffff })
    }

    fun register(id: String, item: Item): Item = Registry.register(Registries.ITEM, DuskAutumns.id(id), item)

    fun BlockItem(block: Block) = BlockItem(block, Item.Settings())
    private fun createRegistryKey(name: String): RegistryKey<ItemGroup> {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier(name))
    }
}