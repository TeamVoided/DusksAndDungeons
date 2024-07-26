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

                        ItemStack(DuskBlocks.BIG_CHAIN),
                        ItemStack(DuskBlocks.MIXED_NETHER_BRICKS),
                        ItemStack(DuskBlocks.POLISHED_NETHER_BRICKS),
                        ItemStack(DuskBlocks.POLISHED_NETHER_BRICK_STAIRS),
                        ItemStack(DuskBlocks.POLISHED_NETHER_BRICK_SLAB),
                        ItemStack(DuskBlocks.POLISHED_NETHER_BRICK_WALL),
                        ItemStack(DuskBlocks.POLISHED_RED_NETHER_BRICKS),
                        ItemStack(DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS),
                        ItemStack(DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB),
                        ItemStack(DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL),

                        ItemStack(DuskBlocks.OVERGROWN_COBBLESTONE),
                        ItemStack(DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS),
                        ItemStack(DuskBlocks.OVERGROWN_COBBLESTONE_SLAB),
                        ItemStack(DuskBlocks.OVERGROWN_COBBLESTONE_WALL),
                        ItemStack(DuskBlocks.OVERGROWN_STONE_BRICKS),
                        ItemStack(DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS),
                        ItemStack(DuskBlocks.OVERGROWN_STONE_BRICK_SLAB),
                        ItemStack(DuskBlocks.OVERGROWN_STONE_BRICK_WALL),

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

                        ItemStack(DuskItems.FARMERS_HAT),
                        ItemStack(DuskItems.WILD_WHEAT),
                        ItemStack(DuskItems.GOLDEN_BEETROOT),
                        ItemStack(DuskItems.BLUE_DOOR),
                        ItemStack(DuskItems.MOONBERRY_VINELET),
                        ItemStack(DuskBlocks.MOONBERRY_VINE),
                        ItemStack(DuskItems.MOONBERRIES),

                        ItemStack(DuskBlocks.ROCKY_GRASS),
                        ItemStack(DuskBlocks.ROCKY_PODZOL),
                        ItemStack(DuskBlocks.ROCKY_MYCELIUM),
                        ItemStack(DuskBlocks.ROCKY_DIRT_PATH),
                        ItemStack(DuskBlocks.ROCKY_DIRT),
                        ItemStack(DuskBlocks.ROCKY_COARSE_DIRT),
                        ItemStack(DuskBlocks.ROCKY_MUD),
                        ItemStack(DuskBlocks.ROCKY_SNOW),
                        ItemStack(DuskBlocks.ROCKY_GRAVEL),
                        ItemStack(DuskBlocks.ROCKY_SAND),
                        ItemStack(DuskBlocks.ROCKY_RED_SAND),
                        ItemStack(DuskBlocks.ROCKY_SOUL_SAND),
                        ItemStack(DuskBlocks.ROCKY_SOUL_SOIL),

                        ItemStack(DuskBlocks.SLATED_GRASS),
                        ItemStack(DuskBlocks.SLATED_PODZOL),
                        ItemStack(DuskBlocks.SLATED_MYCELIUM),
                        ItemStack(DuskBlocks.SLATED_DIRT_PATH),
                        ItemStack(DuskBlocks.SLATED_DIRT),
                        ItemStack(DuskBlocks.SLATED_COARSE_DIRT),
                        ItemStack(DuskBlocks.SLATED_MUD),
                        ItemStack(DuskBlocks.SLATED_SNOW),
                        ItemStack(DuskBlocks.SLATED_GRAVEL),
                        ItemStack(DuskBlocks.SLATED_SAND),
                        ItemStack(DuskBlocks.SLATED_RED_SAND),
                        ItemStack(DuskBlocks.SLATED_SOUL_SAND),
                        ItemStack(DuskBlocks.SLATED_SOUL_SOIL),

                        ItemStack(DuskBlocks.BLACKSTONE_GRASS),
                        ItemStack(DuskBlocks.BLACKSTONE_PODZOL),
                        ItemStack(DuskBlocks.BLACKSTONE_MYCELIUM),
                        ItemStack(DuskBlocks.BLACKSTONE_DIRT_PATH),
                        ItemStack(DuskBlocks.BLACKSTONE_DIRT),
                        ItemStack(DuskBlocks.BLACKSTONE_COARSE_DIRT),
                        ItemStack(DuskBlocks.BLACKSTONE_MUD),
                        ItemStack(DuskBlocks.BLACKSTONE_SNOW),
                        ItemStack(DuskBlocks.BLACKSTONE_GRAVEL),
                        ItemStack(DuskBlocks.BLACKSTONE_SAND),
                        ItemStack(DuskBlocks.BLACKSTONE_RED_SAND),
                        ItemStack(DuskBlocks.BLACKSTONE_SOUL_SAND),
                        ItemStack(DuskBlocks.BLACKSTONE_SOUL_SOIL)
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