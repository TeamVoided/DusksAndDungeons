package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DusksAndDungeons.MODID
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.DusksAndDungeons.isDev
import org.teamvoided.dusk_autumn.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusk_autumn.init.blocks.*
import org.teamvoided.dusk_autumn.util.*
import kotlin.jvm.optionals.getOrNull


@Suppress("unused")
object DnDTabs {
    val DUSKS_AND_DUNGEONS: ItemGroup = register(MODID, FabricItemGroup.builder()
        .icon { ItemStack(DnDWoodBlocks.CASCADE_SAPLING) }
        .name(Text.translatable("itemGroup.dusk_autumn.$MODID"))
        .entries { _, entries ->
            entries.addLists(DnDItemLists.cascadeWood, DnDItemLists.cascadeSigns)
            entries.addItem(
                DnDItems.BLUE_DOOR,
                DnDWoodBlocks.CASCADE_SAPLING,
                DnDWoodBlocks.CASCADE_LEAVES,
                DnDWoodBlocks.GOLDEN_BIRCH_SAPLING,
                DnDWoodBlocks.GOLDEN_BIRCH_LEAVES,
                DnDItems.FARMERS_HAT,
                DnDItems.WILD_WHEAT,
                DnDItems.GOLDEN_BEETROOT,
            )
            entries.addItem(DnDItems.MOONBERRY_VINELET, DnDFloraBlocks.MOONBERRY_VINE, DnDItems.MOONBERRIES)
            entries.addLists(
                DnDBlockLists.flowerbedBlocks,
                DnDBlockLists.vivionbedBlocks,
//                    DnDItemLists.pineWood,
                DnDItemLists.woodStuff,
                DnDItemLists.logPiles,
                DnDItemLists.leafPiles,
                DnDItemLists.polishedStone,
                DnDItemLists.mossyPolishedStone,
                DnDItemLists.overgrownCobblestone,
                DnDItemLists.overgrownStoneBricks,
//                DnDItemLists.snowyStoneBricks,
                DnDItemLists.ice
            )


            entries.addItem(
                DnDFloraBlocks.ROOT_BLOCK,
                DnDStoneBlocks.STONE_PILLAR,
                DnDStoneBlocks.DEEPSLATE_PILLAR,
//                DnDBlocks.TALL_REDSTONE_CRYSTAL,
            )
            entries.addItem(
                // big items but not candles
                DnDBigBlocks.BIG_CHAIN,
                DnDBigBlocks.BIG_LANTERN,
                DnDBigBlocks.BIG_SOUL_LANTERN,
            )

            entries.addItem( // This adds the candles in a nice way
                DnDItemLists.bigCandles.flatMapIndexed { idx, item ->
                    listOf(
                        item,
                        DnDItemLists.soulCandles[idx],
                        DnDItemLists.bigSoulCandles[idx],
                        DnDBlockLists.candelabras[idx],
                        DnDBlockLists.soulCandelabras[idx],
                    )
                }
            )
            entries.addItem(DnDFloraBlocks.WARPED_WART)
            entries.addLists(DnDItemLists.netherrackStuff, DnDItemLists.netherBrickStuff)
            entries.addItem(DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS)
            entries.addLists(
                DnDItemLists.redNetherBrickStuff,
                DnDItemLists.mixedRedNetherBrickStuff,
                DnDItemLists.blueNetherBrickStuff,
                DnDItemLists.mixedBlueNetherBrickStuff,
                DnDItemLists.grayNetherBrickStuff,
                DnDItemLists.mixedGrayNetherBrickStuff,
                DnDItemLists.blackstoneTools,
            )
        }.build()
    )
    val OVERLAY_BLOCKS: ItemGroup = register("overlay_blocks",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDOverlayBlocks.ROCKY_GRASS) }
            .name(Text.translatable("itemGroup.dusk_autumn.overlay_blocks"))
            .entries { _, entries -> entries.addLists(DnDItemLists.overlayBlocks) }
            .build()
    )

    // Dev Tabs
    val DUSKS_AND_DUNGEONS_EXCEPT_DEBUG: ItemGroup = register("dnd_everything",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDStoneBlocks.STONE_PILLAR.asItem()) }
            .name(Text.translatable("Dusk's and Dungeons Except Debug"))
            .entries { params, entries ->
                if (isDev() && params.hasPermissions) entries.addLists(
                    DnDItems.ITEMS.filterNot(EVIL_ITEMS::contains).filterNot(SECRET_ITEMS::contains)
                )
            }
            .build()
    )
    val DND_EXPERIMENTAL: ItemGroup = register("dnd_experimental",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDItems.GALLERY_MAPLE_DOOR) }
            .name(Text.literal("DnD Experimental"))
            .entries { params, entries -> if (isDev() && params.hasPermissions) entries.addLists(EVIL_ITEMS) }
            .build()
    )

    fun init() {
        addToTab(ItemGroups.BUILDING_BLOCKS) {
            it.addAfter(Items.CHERRY_BUTTON, DnDItemLists.cascadeWood)
            it.addAfter(Items.CHAIN, DnDBigBlocks.BIG_CHAIN)
            it.addAfter(Items.NETHERRACK, DnDItemLists.netherrackStuff)
            it.addAfter(Items.CHISELED_NETHER_BRICKS, DnDItemLists.netherBrickStuff)
            it.addAfter(Items.RED_NETHER_BRICKS, DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS)
            it.addAfter(
                Items.RED_NETHER_BRICK_WALL,
                DnDItemLists.redNetherBrickStuff + DnDItemLists.mixedRedNetherBrickStuff + DnDItemLists.blueNetherBrickStuff +
                        DnDItemLists.mixedBlueNetherBrickStuff + DnDItemLists.grayNetherBrickStuff + DnDItemLists.mixedGrayNetherBrickStuff
            )
            it.addAfter(Items.MOSSY_COBBLESTONE_WALL, DnDItemLists.overgrownCobblestone)
            it.addAfter(Items.MOSSY_STONE_BRICK_WALL, DnDItemLists.overgrownStoneBricks)
            it.addWoodStuffAndLeafPiles()
        }

        addToTab(ItemGroups.COLORED_BLOCKS) { it.addCandles() }

        addToTab(ItemGroups.FUNCTIONAL_BLOCKS) {
            it.addAfter(Items.CHAIN, DnDBigBlocks.BIG_CHAIN)
            it.addAfter(Items.LANTERN, DnDBigBlocks.BIG_LANTERN)
            it.addAfter(Items.SOUL_LANTERN, DnDBigBlocks.BIG_SOUL_LANTERN)

            it.addAfter(Items.CHERRY_HANGING_SIGN, DnDItems.CASCADE_SIGN, DnDItems.CASCADE_HANGING_SIGN)

            it.addCandles()
        }

        addToTab(ItemGroups.NATURAL_BLOCKS) {
            it.addAfter(Items.CHERRY_LOG, DnDWoodBlocks.CASCADE_LOG)
            it.addBefore(Items.PINK_PETALS, DnDBlockLists.flowerbedBlocks)
            it.addAfter(Items.PINK_PETALS, DnDBlockLists.vivionbedBlocks)

            it.addAfter(
                Items.FLOWERING_AZALEA_LEAVES,
                listOf(DnDWoodBlocks.CASCADE_LEAVES, DnDWoodBlocks.GOLDEN_BIRCH_LEAVES)
            )
            it.addAfter(Items.FLOWERING_AZALEA, DnDWoodBlocks.CASCADE_SAPLING, DnDWoodBlocks.GOLDEN_BIRCH_SAPLING)
            it.addAfter(Items.VINE, DnDItems.MOONBERRY_VINELET, DnDFloraBlocks.MOONBERRY_VINE, DnDItems.MOONBERRIES)
            DnDBlockLists.leafPiles.forEachIndexed { idx, leafPile ->
                it.addAfter(DnDBlockLists.leaves[idx], leafPile)
            }
        }

        addToTab(ItemGroups.COMBAT) {
            it.addAfter(Items.STONE_SWORD, DnDItems.BLACKSTONE_SWORD)
            it.addAfter(Items.STONE_AXE, DnDItems.BLACKSTONE_AXE)
        }

        addToTab(ItemGroups.TOOLS_AND_UTILITIES) {
            it.addAfter( // this is what you should have done dusk >:( // L plus M N O P =)
                Items.STONE_HOE,
                DnDItems.BLACKSTONE_SHOVEL, DnDItems.BLACKSTONE_PICKAXE,
                DnDItems.BLACKSTONE_AXE, DnDItems.BLACKSTONE_HOE
            )
        }
    }

    @Suppress("SameParameterValue")
    fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }
}


