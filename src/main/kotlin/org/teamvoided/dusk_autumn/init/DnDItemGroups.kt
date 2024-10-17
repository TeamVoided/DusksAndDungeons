package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.DusksAndDungeons.isDev
import org.teamvoided.dusk_autumn.init.DnDItems.EVIL_ITEMS
import org.teamvoided.dusk_autumn.init.blocks.DnDStoneBlocks
import org.teamvoided.dusk_autumn.util.addLists
import kotlin.jvm.optionals.getOrNull


object DnDItemGroups {
//    val DUSK_AUTUMN_TAB: ItemGroup = register("dusk_items",
//        FabricItemGroup.builder()
//            .icon { ItemStack(DnDBlocks.CASCADE_SAPLING.asItem()) }
//            .name(Text.translatable("itemGroup.dusk_autumn.dusk_items"))
//            .entries { _, entries ->
//                entries.addLists(DnDItemLists.cascadeWood, DnDItemLists.cascadeSigns)
//                entries.addItem(
//                    DnDItems.BLUE_DOOR,
//                    DnDBlocks.CASCADE_SAPLING,
//                    DnDBlocks.CASCADE_LEAVES,
//                    DnDBlocks.GOLDEN_BIRCH_SAPLING,
//                    DnDBlocks.GOLDEN_BIRCH_LEAVES,
//                    DnDItems.FARMERS_HAT,
//                    DnDItems.WILD_WHEAT,
//                    DnDItems.GOLDEN_BEETROOT,
//                )
//                entries.addLists(
//                    DnDItemLists.moonberry,
//                    DnDBlockLists.flowerbedBlocks,
//                    DnDBlockLists.vivionbedBlocks,
////                    DnDItemLists.pineWood,
//                    DnDItemLists.bonewoodWood,
//                    DnDItemLists.woodStuff,
//                    DnDItemLists.logPiles,
//                    DnDItemLists.leafPiles,
//                    DnDItemLists.polishedStone,
//                    DnDItemLists.mossyPolishedStone,
//                    DnDItemLists.overgrownCobblestone,
//                    DnDItemLists.overgrownStoneBricks,
//                    DnDItemLists.snowyStoneBricks,
//                    DnDItemLists.ice
//                )
//                entries.addItem(
//                    DnDBlocks.ROOT_BLOCK,
//                    DnDBlocks.STONE_PILLAR,
//                    DnDBlocks.DEEPSLATE_PILLAR,
//                    DnDBlocks.TALL_REDSTONE_CRYSTAL,
//                )
//                entries.addItem(
//                    // big items but not candles
//                    DnDBlocks.BIG_CHAIN,
//                    DnDBlocks.BIG_LANTERN,
//                    DnDBlocks.BIG_SOUL_LANTERN,
//                )
//                entries.addItem( // This add the candles in a nice way
//                    DnDItemLists.bigCandles.flatMapIndexed { idx, item ->
//                        listOf(item, DnDItemLists.soulCandles[idx], DnDItemLists.bigSoulCandles[idx])
//                    }
//                )
//                entries.addItem(DnDBlocks.WARPED_WART)
//                entries.addLists(DnDItemLists.netherrackStuff, DnDItemLists.netherBrickStuff)
//                entries.addItem(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
//                entries.addLists(
//                    DnDItemLists.redNetherBrickStuff,
//                    DnDItemLists.mixedRedNetherBrickStuff,
//                    DnDItemLists.blueNetherBrickStuff,
//                    DnDItemLists.mixedBlueNetherBrickStuff,
//                    DnDItemLists.grayNetherBrickStuff,
//                    DnDItemLists.mixedGrayNetherBrickStuff,
//                    DnDItemLists.blackstoneTools,
//                )
//                entries.addItem(
//                    DnDItems.CHILL_CHARGE,
//                    DnDItems.FREEZE_ROD,
////                    DnDItems.ICE_SWORD
//                )
//            }.build()
//    )
//    val OVERLAY_BLOCKS_TAB: ItemGroup = register("overlay_blocks",
//        FabricItemGroup.builder()
//            .icon { ItemStack(DnDOverlayBlocks.ROCKY_GRASS.asItem()) }
//            .name(Text.translatable("itemGroup.dusk_autumn.overlay_blocks"))
//            .entries { _, entries ->
//                entries.addLists(
//                    DnDItemLists.overlayBlocks
//                )
//            }.build()
//    )
    val DUSKS_AND_DUNGEONS_EXCEPT_DEBUG: ItemGroup = register("dnd_everything",
        FabricItemGroup.builder()
            .icon { ItemStack(DnDStoneBlocks.STONE_PILLAR.asItem()) }
            .name(Text.translatable("itemGroup.dusk_autumn.everything"))
            .entries { _, entries -> entries.addLists(DnDItems.ITEMS.filterNot(EVIL_ITEMS::contains)) }
            .build()
    )

    fun init() {
        if (isDev()) register("dnd_debug",
            FabricItemGroup.builder()
                .icon { ItemStack(DnDItems.GALLERY_MAPLE_DOOR) }
                .name(Text.literal("DnD Debug Items"))
                .entries { _, entries -> entries.addLists(EVIL_ITEMS) }
                .build()
        )


        /*  addToTab(ItemGroups.BUILDING_BLOCKS) {
              it.addAfter(Items.CHERRY_BUTTON, DnDItemLists.cascadeWood)
              it.addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)
              it.addAfter(Items.NETHERRACK, DnDItemLists.netherrackStuff)
              it.addAfter(Items.CHISELED_NETHER_BRICKS, DnDItemLists.netherBrickStuff)
              it.addAfter(Items.RED_NETHER_BRICKS, DnDBlocks.CRACKED_RED_NETHER_BRICKS)
              it.addAfter(
                  Items.RED_NETHER_BRICK_WALL,
                  DnDItemLists.redNetherBrickStuff + DnDItemLists.mixedRedNetherBrickStuff + DnDItemLists.blueNetherBrickStuff +
                          DnDItemLists.mixedBlueNetherBrickStuff + DnDItemLists.grayNetherBrickStuff + DnDItemLists.mixedGrayNetherBrickStuff
              )
              it.addAfter(Items.MOSSY_COBBLESTONE_WALL, DnDItemLists.overgrownCobblestone)
              it.addAfter(Items.MOSSY_STONE_BRICK_WALL, DnDItemLists.overgrownStoneBricks + DnDItemLists.snowyStoneBricks)
              it.addWoodStuffAndLeafPiles()
          }

          addToTab(ItemGroups.COLORED_BLOCKS) { it.addCandles() }

          addToTab(ItemGroups.FUNCTIONAL_BLOCKS) {
              it.addAfter(Items.CHAIN, DnDBlocks.BIG_CHAIN)
              it.addAfter(Items.LANTERN, DnDBlocks.BIG_LANTERN)
              it.addAfter(Items.SOUL_LANTERN, DnDBlocks.BIG_SOUL_LANTERN)

              it.addAfter(Items.CHERRY_HANGING_SIGN, DnDItems.CASCADE_SIGN, DnDItems.CASCADE_HANGING_SIGN)

              it.addCandles()
          }

          addToTab(ItemGroups.NATURAL_BLOCKS) {
              it.addAfter(Items.CHERRY_LOG, DnDBlocks.CASCADE_LOG)
              it.addBefore(Items.PINK_PETALS, DnDBlockLists.flowerbedBlocks)
              it.addAfter(Items.PINK_PETALS, DnDBlockLists.vivionbedBlocks)

              it.addAfter(
                  Items.FLOWERING_AZALEA_LEAVES,
                  listOf(DnDBlocks.CASCADE_LEAVES, DnDBlocks.GOLDEN_BIRCH_LEAVES)
              )
              it.addAfter(Items.FLOWERING_AZALEA, DnDBlocks.CASCADE_SAPLING, DnDBlocks.GOLDEN_BIRCH_SAPLING)
              it.addAfter(Items.VINE, DnDItemLists.moonberry)
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
          }*/
    }

    @Suppress("SameParameterValue")
    fun register(name: String, itemGroup: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(name), itemGroup)
    }

    fun getKey(itemGroup: ItemGroup): RegistryKey<ItemGroup>? {
        return Registries.ITEM_GROUP.getKey(itemGroup)?.getOrNull()
    }
}


