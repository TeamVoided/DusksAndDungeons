package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.item.DnDItemLists
import kotlin.jvm.optionals.getOrNull


object DnDItemGroups {
    val DUSK_AUTUMN_TAB: ItemGroup = register("dusk_items",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDBlocks.CASCADE_SAPLING.asItem()) }
            .name(Text.translatable("itemGroup.dusk_autumn.dusk_items"))
            .entries { _, entries ->
                entries.addItems(
                    DnDItemLists.cascadeWood +
                            DnDItemLists.cascadeSigns +
                            DnDItemLists.pineWood +
                            listOf(
                                DnDBlocks.CASCADE_SAPLING.asItem(),
                                DnDBlocks.CASCADE_LEAVES.asItem(),
                                DnDBlocks.GOLDEN_BIRCH_SAPLING.asItem(),
                                DnDBlocks.GOLDEN_BIRCH_LEAVES.asItem(),
                                DnDBlocks.BLUE_PETALS.asItem(),
                            ) +
                            DnDItemLists.bigItems +
                            listOf(DnDBlocks.BRICK_FENCE.asItem()) +
                            DnDItemLists.netherrackStuff +
                            DnDItemLists.netherBrickStuff +
                            listOf(DnDBlocks.CRACKED_RED_NETHER_BRICKS.asItem()) +
                            DnDItemLists.redNetherBrickStuff +
                            DnDItemLists.mixedNetherBrickStuff +
                            DnDItemLists.blueNetherBrickStuff +
                            DnDItemLists.mixedBlueNetherBrickStuff +
                            DnDItemLists.grayNetherBrickStuff +
                            DnDItemLists.mixedGrayNetherBrickStuff +
                            DnDItemLists.blackstoneTools +
                            DnDItemLists.overgrownCobblestone +
                            DnDItemLists.overgrownStoneBricks +
                            listOf(DnDBlocks.ROOT_BLOCK.asItem()) +
                            DnDItemLists.moonberry +
                            listOf(
                                DnDItems.FARMERS_HAT,
                                DnDItems.WILD_WHEAT,
                                DnDItems.GOLDEN_BEETROOT,
                                DnDItems.BLUE_DOOR
                            ) +
                            DnDItemLists.logPiles +
                            DnDItemLists.leafPiles +
                            DnDItemLists.overlayBlocks
                )
            }.build()
    )

    fun init() {
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.CHERRY_BUTTON, DnDItemLists.cascadeWood)
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.CHAIN, listOf(DnDBlocks.BIG_CHAIN.asItem()))
        registerInVanillaTab(ItemGroups.COLORED_BLOCKS, Items.PINK_CANDLE, DnDItemLists.candles)
        registerInVanillaTab(ItemGroups.FUNCTIONAL_BLOCKS, Items.PINK_CANDLE, DnDItemLists.candles)
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.BRICK_WALL, DnDBlocks.BRICK_FENCE.asItem())
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.NETHERRACK, DnDItemLists.netherrackStuff)
        registerInVanillaTab(ItemGroups.BUILDING_BLOCKS, Items.CHISELED_NETHER_BRICKS, DnDItemLists.netherBrickStuff)
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.RED_NETHER_BRICKS,
            DnDBlocks.CRACKED_RED_NETHER_BRICKS.asItem()
        )
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.RED_NETHER_BRICK_WALL,
            DnDItemLists.redNetherBrickStuff + DnDItemLists.mixedNetherBrickStuff + DnDItemLists.blueNetherBrickStuff + DnDItemLists.mixedBlueNetherBrickStuff + DnDItemLists.grayNetherBrickStuff + DnDItemLists.mixedGrayNetherBrickStuff
        )
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.SOUL_LANTERN,
            listOf(DnDBlocks.BIG_LANTERN.asItem(), DnDBlocks.BIG_SOUL_LANTERN.asItem())
        )
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.MOSSY_COBBLESTONE_WALL,
            DnDItemLists.overgrownCobblestone
        )
        registerInVanillaTab(
            ItemGroups.BUILDING_BLOCKS,
            Items.MOSSY_STONE_BRICK_WALL,
            DnDItemLists.overgrownStoneBricks
        )
        registerInVanillaTab(
            ItemGroups.NATURAL_BLOCKS, Items.FLOWERING_AZALEA_LEAVES, listOf(
                DnDBlocks.CASCADE_LEAVES.asItem(),
                DnDBlocks.GOLDEN_BIRCH_LEAVES.asItem()
            ) + DnDItemLists.leafPiles
        )
        registerInVanillaTab(
            ItemGroups.NATURAL_BLOCKS, Items.FLOWERING_AZALEA, listOf(
                DnDBlocks.CASCADE_SAPLING.asItem(),
                DnDBlocks.GOLDEN_BIRCH_SAPLING.asItem()
            )
        )
        registerInVanillaTab(ItemGroups.NATURAL_BLOCKS, Items.VINE, DnDItemLists.moonberry)
        registerInVanillaTab(ItemGroups.COMBAT, Items.STONE_SWORD, DnDItems.BLACKSTONE_SWORD)
        registerInVanillaTab(ItemGroups.COMBAT, Items.STONE_AXE, DnDItems.BLACKSTONE_AXE)
        registerInVanillaTab(
            ItemGroups.COMBAT,
            Items.STONE_AXE,
            listOf(
                DnDItems.BLACKSTONE_SHOVEL,
                DnDItems.BLACKSTONE_PICKAXE,
                DnDItems.BLACKSTONE_AXE,
                DnDItems.BLACKSTONE_HOE,
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