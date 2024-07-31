package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.item.DuskItemLists
import kotlin.jvm.optionals.getOrNull


object DuskItemGroups {
    val DUSK_AUTUMN_TAB: ItemGroup = register("dusk_items",
        FabricItemGroup.builder()
            .icon { ItemStack(DuskBlocks.CASCADE_SAPLING.asItem()) }
            .name(Text.translatable("itemGroup.dusk_autumn.dusk_items"))
            .entries { _, entries ->
                entries.addItems(
                    DuskItemLists.cascadeWood +
                            DuskItemLists.cascadeSigns +
                            DuskItemLists.pineWood +
                            listOf(
                                DuskBlocks.CASCADE_SAPLING.asItem(),
                                DuskBlocks.CASCADE_LEAVES.asItem(),
                                DuskBlocks.GOLDEN_BIRCH_SAPLING.asItem(),
                                DuskBlocks.GOLDEN_BIRCH_LEAVES.asItem(),
                                DuskBlocks.BLUE_PETALS.asItem(),
                            ) +
                            DuskItemLists.bigItems +
                            listOf(DuskBlocks.BRICK_FENCE.asItem()) +
                            DuskItemLists.netherrackStuff +
                            DuskItemLists.netherBrickStuff +
                            listOf(DuskBlocks.CRACKED_RED_NETHER_BRICKS.asItem()) +
                            DuskItemLists.redNetherBrickStuff +
                            DuskItemLists.mixedNetherBrickStuff +
                            DuskItemLists.blueNetherBrickStuff +
                            DuskItemLists.mixedBlueNetherBrickStuff +
                            DuskItemLists.grayNetherBrickStuff +
                            DuskItemLists.mixedGrayNetherBrickStuff +
                            DuskItemLists.blackstoneTools +
                            DuskItemLists.overgrownCobblestone +
                            DuskItemLists.overgrownStoneBricks +
                            listOf(DuskBlocks.ROOT_BLOCK.asItem()) +
                            DuskItemLists.moonberry +
                            listOf(
                                DuskItems.FARMERS_HAT,
                                DuskItems.WILD_WHEAT,
                                DuskItems.GOLDEN_BEETROOT,
                                DuskItems.BLUE_DOOR
                            ) +
                            DuskItemLists.logPiles +
                            DuskItemLists.leafPiles +
                            DuskItemLists.overlayBlocks
                )
            }.build()
    )

    fun init() {
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.CHERRY_BUTTON, DuskItemLists.cascadeWood)
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.CHAIN, listOf(DuskBlocks.BIG_CHAIN.asItem()))
        registerInVanillaTab(ItemGroups.COLORED_BLOCKS, Items.PINK_CANDLE, DuskItemLists.candles)
        registerInVanillaTab(ItemGroups.FUNCTIONAL_BLOCKS, Items.PINK_CANDLE, DuskItemLists.candles)
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.BRICK_WALL, DuskBlocks.BRICK_FENCE.asItem())
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.NETHERRACK, DuskItemLists.netherrackStuff)
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.CHISELED_NETHER_BRICKS, DuskItemLists.netherBrickStuff)
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.RED_NETHER_BRICKS,
            DuskBlocks.CRACKED_RED_NETHER_BRICKS.asItem()
        )
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.RED_NETHER_BRICK_WALL,
            DuskItemLists.redNetherBrickStuff + DuskItemLists.mixedNetherBrickStuff + DuskItemLists.blueNetherBrickStuff + DuskItemLists.mixedBlueNetherBrickStuff + DuskItemLists.grayNetherBrickStuff + DuskItemLists.mixedGrayNetherBrickStuff
        )
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.SOUL_LANTERN,
            listOf(DuskBlocks.BIG_LANTERN.asItem(), DuskBlocks.BIG_SOUL_LANTERN.asItem())
        )
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.MOSSY_COBBLESTONE_WALL,
            DuskItemLists.overgrownCobblestone
        )
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.MOSSY_STONE_BRICK_WALL,
            DuskItemLists.overgrownStoneBricks
        )
        registerInVanillaTab(
            ItemGroups.NATURAL_BLOCKS, Items.FLOWERING_AZALEA_LEAVES, listOf(
                DuskBlocks.CASCADE_LEAVES.asItem(),
                DuskBlocks.GOLDEN_BIRCH_LEAVES.asItem()
            ) + DuskItemLists.leafPiles
        )
        registerInVanillaTab(
            ItemGroups.NATURAL_BLOCKS, Items.FLOWERING_AZALEA, listOf(
                DuskBlocks.CASCADE_SAPLING.asItem(),
                DuskBlocks.GOLDEN_BIRCH_SAPLING.asItem()
            )
        )
        registerInVanillaTab(ItemGroups.NATURAL_BLOCKS, Items.VINE, DuskItemLists.moonberry)
        registerInVanillaTab(ItemGroups.COMBAT, Items.STONE_SWORD, DuskItems.BLACKSTONE_SWORD)
        registerInVanillaTab(ItemGroups.COMBAT, Items.STONE_AXE, DuskItems.BLACKSTONE_AXE)
        registerInVanillaTab(
            ItemGroups.COMBAT,
            Items.STONE_AXE,
            listOf(
                DuskItems.BLACKSTONE_SHOVEL,
                DuskItems.BLACKSTONE_PICKAXE,
                DuskItems.BLACKSTONE_AXE,
                DuskItems.BLACKSTONE_HOE,
            )
        )
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

    fun registerInVanillaTab(itemGroup: RegistryKey<ItemGroup>, itemBefore: Item, item: Item) {
        ItemGroupEvents.modifyEntriesEvent(itemGroup)
            .register(ItemGroupEvents.ModifyEntries {
                it.addAfter(
                    itemBefore,
                    item,
                )
            })
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