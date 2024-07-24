package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor
import org.teamvoided.dusk_autumn.DuskAutumns.id
import kotlin.jvm.optionals.getOrNull


object DuskItemGroups {
    val DUSK_AUTUMN_TAB: ItemGroup = register("dusk_items",
        FabricItemGroup.builder()
            .icon { ItemStack(DuskBlocks.CASCADE_SAPLING.asItem()) }
            .name(Text.translatable("itemGroup.dusk_autumn.dusk_items"))
            .entries { _, entries ->
                entries.addStacks(
                    mutableSetOf(
                        ItemStack(DuskBlocks.CASCADE_SAPLING),
                        ItemStack(DuskBlocks.CASCADE_LEAVES),
                        ItemStack(DuskBlocks.CASCADE_LOG),
                        ItemStack(DuskBlocks.STRIPPED_CASCADE_LOG),
                        ItemStack(DuskBlocks.CASCADE_PLANKS),
                        ItemStack(DuskBlocks.CASCADE_STAIRS),
                        ItemStack(DuskBlocks.CASCADE_SLAB),
                        ItemStack(DuskBlocks.CASCADE_FENCE),
                        ItemStack(DuskBlocks.CASCADE_FENCE_GATE),
                        ItemStack(DuskItems.CASCADE_DOOR),
                        ItemStack(DuskBlocks.CASCADE_TRAPDOOR),
                        ItemStack(DuskItems.CASCADE_SIGN),
                        ItemStack(DuskItems.CASCADE_HANGING_SIGN),
                        ItemStack(DuskBlocks.CASCADE_PRESSURE_PLATE),
                        ItemStack(DuskBlocks.CASCADE_BUTTON),
                        ItemStack(DuskBlocks.BLUE_PETALS),
                        ItemStack(DuskBlocks.GOLDEN_BIRCH_LEAVES),
                        ItemStack(DuskBlocks.GOLDEN_BIRCH_SAPLING),
                        ItemStack(DuskBlocks.OAK_LEAF_PILE),
                        ItemStack(DuskBlocks.SPRUCE_LEAF_PILE),
                        ItemStack(DuskBlocks.BIRCH_LEAF_PILE),
                        ItemStack(DuskBlocks.JUNGLE_LEAF_PILE),
                        ItemStack(DuskBlocks.ACACIA_LEAF_PILE),
                        ItemStack(DuskBlocks.DARK_OAK_LEAF_PILE),
                        ItemStack(DuskBlocks.MANGROVE_LEAF_PILE),
                        ItemStack(DuskBlocks.CHERRY_LEAF_PILE),
                        ItemStack(DuskBlocks.AZALEA_LEAF_PILE),
                        ItemStack(DuskBlocks.FLOWERING_AZALEA_LEAF_PILE),
                        ItemStack(DuskBlocks.CASCADE_LEAF_PILE),
                        ItemStack(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE),
                        ItemStack(DuskBlocks.ROCKY_GRASS),
                        ItemStack(DuskBlocks.ROCKY_PODZOL),
                        ItemStack(DuskBlocks.ROCKY_MYCELIUM),
                        ItemStack(DuskBlocks.ROCKY_DIRT_PATH),
                        ItemStack(DuskBlocks.ROCKY_DIRT),
                        ItemStack(DuskBlocks.ROCKY_COARSE_DIRT),
                        ItemStack(DuskBlocks.ROCKY_SAND),
                        ItemStack(DuskBlocks.ROCKY_RED_SAND),
                        ItemStack(DuskBlocks.ROCKY_SOUL_SAND),
                        ItemStack(DuskBlocks.ROCKY_SOUL_SOIL),
                        ItemStack(DuskItems.FARMERS_HAT),
                        ItemStack(DuskItems.WILD_WHEAT),
                        ItemStack(DuskItems.GOLDEN_BEETROOT),
                        ItemStack(DuskItems.BLUE_DOOR),
                        ItemStack(DuskItems.MOONBERRY_VINELET),
                        ItemStack(DuskBlocks.MOONBERRY_VINE),
                        ItemStack(DuskItems.MOONBERRIES),
                    )
                )
            }
            .build()
    )

    fun init() = Unit


    @Suppress("SameParameterValue")
    private fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }
}