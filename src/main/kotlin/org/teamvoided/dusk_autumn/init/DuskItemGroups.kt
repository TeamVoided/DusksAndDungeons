package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DuskAutumns.id
import kotlin.jvm.optionals.getOrNull


object DuskItemGroups {
    val DUSK_AUTUMN_TAB: ItemGroup = register("dusk_items",
        FabricItemGroup.builder()
            .icon { ItemStack(DuskItems.CASCADE_SAPLING) }
            .name(Text.translatable("itemGroup.dusk_autumn.dusk_items"))
            .entries { _, entries ->
                entries.addStacks(
                    mutableSetOf(
                        ItemStack(DuskItems.CASCADE_SAPLING),
                        ItemStack(DuskItems.CASCADE_LEAVES),
                        ItemStack(DuskItems.CASCADE_LOG),
                        ItemStack(DuskItems.STRIPPED_CASCADE_LOG),
                        ItemStack(DuskItems.CASCADE_PLANKS),
                        ItemStack(DuskItems.CASCADE_DOOR),
                        ItemStack(DuskItems.CASCADE_TRAPDOOR),
                        ItemStack(DuskItems.CASCADE_SIGN),
                        ItemStack(DuskItems.CASCADE_HANGING_SIGN),
                        ItemStack(DuskItems.BLUE_PETALS),
                        ItemStack(DuskItems.GOLDEN_BIRCH_LEAVES),
                        ItemStack(DuskItems.GOLDEN_BIRCH_SAPLING),
                        ItemStack(DuskItems.OAK_LEAF_PILE),
                        ItemStack(DuskItems.SPRUCE_LEAF_PILE),
                        ItemStack(DuskItems.BIRCH_LEAF_PILE),
                        ItemStack(DuskItems.JUNGLE_LEAF_PILE),
                        ItemStack(DuskItems.ACACIA_LEAF_PILE),
                        ItemStack(DuskItems.DARK_OAK_LEAF_PILE),
                        ItemStack(DuskItems.MANGROVE_LEAF_PILE),
                        ItemStack(DuskItems.CHERRY_LEAF_PILE),
                        ItemStack(DuskItems.AZALEA_LEAF_PILE),
                        ItemStack(DuskItems.FLOWERING_AZALEA_LEAF_PILE),
                        ItemStack(DuskItems.CASCADE_LEAF_PILE),
                        ItemStack(DuskItems.GOLDEN_BIRCH_LEAF_PILE),
                        ItemStack(DuskItems.FARMERS_HAT),
                        ItemStack(DuskItems.WILD_WHEAT),
                        ItemStack(DuskItems.GOLDEN_BEETROOT),
                        ItemStack(DuskItems.BLUE_DOOR),
                        ItemStack(DuskItems.MOONBERRY_VINELET),
                        ItemStack(DuskItems.MOONBERRY_VINE),
                        ItemStack(DuskItems.MOONBERRIES)
                    )
                )
            }
            .build()
    )

    fun init() {}
    @Suppress("SameParameterValue")
    private fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }
}