package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DuskAutumns.id
import kotlin.jvm.optionals.getOrNull


object DuskItemGroups {
    val cascadeWood = listOf(
        DuskBlocks.CASCADE_LOG.asItem(),
        DuskBlocks.CASCADE_WOOD.asItem(),
        DuskBlocks.STRIPPED_CASCADE_LOG.asItem(),
        DuskBlocks.STRIPPED_CASCADE_WOOD.asItem(),
        DuskBlocks.CASCADE_PLANKS.asItem(),
        DuskBlocks.CASCADE_STAIRS.asItem(),
        DuskBlocks.CASCADE_SLAB.asItem(),
        DuskBlocks.CASCADE_FENCE.asItem(),
        DuskBlocks.CASCADE_FENCE_GATE.asItem(),
        DuskItems.CASCADE_DOOR,
        DuskBlocks.CASCADE_TRAPDOOR.asItem(),
        DuskBlocks.CASCADE_PRESSURE_PLATE.asItem(),
        DuskBlocks.CASCADE_BUTTON.asItem()
    )
    val cascadeSigns = listOf(
        DuskItems.CASCADE_SIGN,
        DuskItems.CASCADE_HANGING_SIGN
    )
    val netherBrickStuff = listOf(
        DuskBlocks.NETHER_BRICK_PILLAR.asItem(),
        DuskBlocks.POLISHED_NETHER_BRICKS.asItem(),
        DuskBlocks.POLISHED_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.POLISHED_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.POLISHED_NETHER_BRICK_WALL.asItem(),
    )
    val mixedNetherBrickStuff = listOf(
        DuskBlocks.MIXED_NETHER_BRICKS.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_WALL.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_FENCE.asItem(),
        DuskBlocks.MIXED_NETHER_BRICK_PILLAR.asItem()
    )
    val redNetherBrickStuff = listOf(
        DuskBlocks.RED_NETHER_BRICK_FENCE.asItem(),
        DuskBlocks.CHISELED_RED_NETHER_BRICKS.asItem(),
        DuskBlocks.RED_NETHER_BRICK_PILLAR.asItem(),
        DuskBlocks.POLISHED_RED_NETHER_BRICKS.asItem(),
        DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS.asItem(),
        DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB.asItem(),
        DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL.asItem()
    )
    val overgrownCobblestone = listOf(
        DuskBlocks.OVERGROWN_COBBLESTONE.asItem(),
        DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS.asItem(),
        DuskBlocks.OVERGROWN_COBBLESTONE_SLAB.asItem(),
        DuskBlocks.OVERGROWN_COBBLESTONE_WALL.asItem(),
    )
    val overgrownStoneBricks = listOf(
        DuskBlocks.OVERGROWN_STONE_BRICKS.asItem(),
        DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS.asItem(),
        DuskBlocks.OVERGROWN_STONE_BRICK_SLAB.asItem(),
        DuskBlocks.OVERGROWN_STONE_BRICK_WALL.asItem(),
    )
    val leafPiles = listOf(
        DuskBlocks.OAK_LEAF_PILE.asItem(),
        DuskBlocks.SPRUCE_LEAF_PILE.asItem(),
        DuskBlocks.BIRCH_LEAF_PILE.asItem(),
        DuskBlocks.JUNGLE_LEAF_PILE.asItem(),
        DuskBlocks.ACACIA_LEAF_PILE.asItem(),
        DuskBlocks.DARK_OAK_LEAF_PILE.asItem(),
        DuskBlocks.MANGROVE_LEAF_PILE.asItem(),
        DuskBlocks.CHERRY_LEAF_PILE.asItem(),
        DuskBlocks.AZALEA_LEAF_PILE.asItem(),
        DuskBlocks.FLOWERING_AZALEA_LEAF_PILE.asItem(),
        DuskBlocks.CASCADE_LEAF_PILE.asItem(),
        DuskBlocks.GOLDEN_BIRCH_LEAF_PILE.asItem(),
    )
    val moonberry = listOf(
        DuskItems.MOONBERRY_VINELET,
        DuskBlocks.MOONBERRY_VINE.asItem(),
        DuskItems.MOONBERRIES
    )
    val overlayBlocks = listOf(
        DuskBlocks.ROCKY_GRASS.asItem(),
        DuskBlocks.ROCKY_PODZOL.asItem(),
        DuskBlocks.ROCKY_MYCELIUM.asItem(),
        DuskBlocks.ROCKY_DIRT_PATH.asItem(),
        DuskBlocks.ROCKY_DIRT.asItem(),
        DuskBlocks.ROCKY_COARSE_DIRT.asItem(),
        DuskBlocks.ROCKY_MUD.asItem(),
        DuskBlocks.ROCKY_SNOW.asItem(),
        DuskBlocks.ROCKY_GRAVEL.asItem(),
        DuskBlocks.ROCKY_SAND.asItem(),
        DuskBlocks.ROCKY_RED_SAND.asItem(),
        DuskBlocks.ROCKY_SOUL_SAND.asItem(),
        DuskBlocks.ROCKY_SOUL_SOIL.asItem(),

        DuskBlocks.SLATED_GRASS.asItem(),
        DuskBlocks.SLATED_PODZOL.asItem(),
        DuskBlocks.SLATED_MYCELIUM.asItem(),
        DuskBlocks.SLATED_DIRT_PATH.asItem(),
        DuskBlocks.SLATED_DIRT.asItem(),
        DuskBlocks.SLATED_COARSE_DIRT.asItem(),
        DuskBlocks.SLATED_MUD.asItem(),
        DuskBlocks.SLATED_SNOW.asItem(),
        DuskBlocks.SLATED_GRAVEL.asItem(),
        DuskBlocks.SLATED_SAND.asItem(),
        DuskBlocks.SLATED_RED_SAND.asItem(),
        DuskBlocks.SLATED_SOUL_SAND.asItem(),
        DuskBlocks.SLATED_SOUL_SOIL.asItem(),

        DuskBlocks.BLACKSTONE_GRASS.asItem(),
        DuskBlocks.BLACKSTONE_PODZOL.asItem(),
        DuskBlocks.BLACKSTONE_MYCELIUM.asItem(),
        DuskBlocks.BLACKSTONE_DIRT_PATH.asItem(),
        DuskBlocks.BLACKSTONE_DIRT.asItem(),
        DuskBlocks.BLACKSTONE_COARSE_DIRT.asItem(),
        DuskBlocks.BLACKSTONE_MUD.asItem(),
        DuskBlocks.BLACKSTONE_SNOW.asItem(),
        DuskBlocks.BLACKSTONE_GRAVEL.asItem(),
        DuskBlocks.BLACKSTONE_SAND.asItem(),
        DuskBlocks.BLACKSTONE_RED_SAND.asItem(),
        DuskBlocks.BLACKSTONE_SOUL_SAND.asItem(),
        DuskBlocks.BLACKSTONE_SOUL_SOIL.asItem()
    )
    val DUSK_AUTUMN_TAB: ItemGroup = register("dusk_items",
        FabricItemGroup.builder()
            .icon { ItemStack(DuskBlocks.CASCADE_SAPLING.asItem()) }
            .name(Text.translatable("itemGroup.dusk_autumn.dusk_items"))
            .entries { _, entries ->
                entries.addItems(
                    cascadeWood +
                            cascadeSigns +
                            listOf(
                                DuskBlocks.CASCADE_SAPLING.asItem(),
                                DuskBlocks.CASCADE_LEAVES.asItem(),
                                DuskBlocks.GOLDEN_BIRCH_SAPLING.asItem(),
                                DuskBlocks.GOLDEN_BIRCH_LEAVES.asItem(),
                                DuskBlocks.BLUE_PETALS.asItem(),
                                DuskBlocks.BIG_CHAIN.asItem(),
                                DuskBlocks.BIG_LANTERN.asItem(),
                                DuskBlocks.BIG_SOUL_LANTERN.asItem()
                            ) +
                            netherBrickStuff +
                            redNetherBrickStuff +
                            mixedNetherBrickStuff +
                            overgrownCobblestone +
                            overgrownStoneBricks +
                            listOf(DuskBlocks.ROOT_BLOCK.asItem()) +
                            leafPiles +
                            moonberry +
                            listOf(
                                DuskItems.FARMERS_HAT,
                                DuskItems.WILD_WHEAT,
                                DuskItems.GOLDEN_BEETROOT,
                                DuskItems.BLUE_DOOR
                            ) + overlayBlocks
                )
            }
            .build()
    )

    fun init() {
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.CHERRY_BUTTON, cascadeWood)
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.MOSSY_COBBLESTONE_WALL, overgrownCobblestone)
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.MOSSY_STONE_BRICK_WALL, overgrownStoneBricks)
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.CHISELED_NETHER_BRICKS, netherBrickStuff)
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.RED_NETHER_BRICK_WALL,
            redNetherBrickStuff + mixedNetherBrickStuff
        )
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.CHAIN, listOf(DuskBlocks.BIG_CHAIN.asItem()))
        registerInVanillaTab(
            ItemGroups.NATURAL_BLOCKS, Items.FLOWERING_AZALEA_LEAVES, listOf(
                DuskBlocks.CASCADE_LEAVES.asItem(),
                DuskBlocks.GOLDEN_BIRCH_LEAVES.asItem()
            ) + leafPiles
        )
        registerInVanillaTab(
            ItemGroups.NATURAL_BLOCKS, Items.FLOWERING_AZALEA, listOf(
                DuskBlocks.CASCADE_SAPLING.asItem(),
                DuskBlocks.GOLDEN_BIRCH_SAPLING.asItem()
            )
        )
        registerInVanillaTab(ItemGroups.NATURAL_BLOCKS, Items.VINE, moonberry)
    }


    @Suppress("SameParameterValue")
    private fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }

    fun ItemGroup.ItemStackCollector.addItems(list: Collection<Item>) {
        this.addStacks(list.map(Item::getDefaultStack))
    }

    fun registerInVanillaTab(itemGroup: RegistryKey<ItemGroup>, itemBefore: Item, list: Collection<Item>) {
        ItemGroupEvents.modifyEntriesEvent(itemGroup)
            .register(ItemGroupEvents.ModifyEntries {
                it.addAfter(
                    itemBefore,
                    addItems(list),
                )
            })
    }

    fun addItems(list: Collection<Item>): List<ItemStack> {
        return list.map(Item::getDefaultStack)
    }
}