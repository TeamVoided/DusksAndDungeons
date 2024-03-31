package org.teamvoided.dusk_autumn.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.init.DuskItems


object DuskItemGroups {
    fun init() {
        val duskAutumn: ItemGroup = FabricItemGroup.builder()
            .icon { ItemStack(DuskItems.CASCADE_SAPLING) }
            .name(Text.translatable("itemGroup.dusk_autumn.dusk_items"))
            .entries { context, entries ->
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
                        ItemStack(DuskItems.GOLDEN_BIRCH_LEAF_PILE),
                        ItemStack(DuskItems.FARMERS_HAT),
                        ItemStack(DuskItems.WILD_WHEAT),
                        ItemStack(DuskItems.GOLDEN_BEETROOT),
                        ItemStack(DuskItems.BLUE_DOOR)
                        )
                )
            }
            .build()

        Registry.register<ItemGroup, ItemGroup>(
            Registries.ITEM_GROUP,
            Identifier("dusk_autumn", "test_group"),
            duskAutumn
        )
    }

    private fun createRegistryKey(name: String): RegistryKey<ItemGroup> {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier(name))
    }
}