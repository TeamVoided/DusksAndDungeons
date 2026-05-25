package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.fabricmc.fabric.api.loot.v3.LootTableSource
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.entity.npc.VillagerTrades
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.storage.loot.BuiltInLootTables.*
import net.minecraft.world.level.storage.loot.LootTable
import org.teamvoided.dusks_and_dungeons.data.DnDLootTables.BARTERING_ADD_VIVIONS
import org.teamvoided.dusks_and_dungeons.data.DnDLootTables.SIMPLE_DUNGEON_ADD_SPOOKY
import org.teamvoided.dusks_and_dungeons.data.DnDLootTables.SNIFFER_ADD_MOONBERRY
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.voidlib.helpers.mc.*

@Suppress("FunctionName")
fun InitializeFabricEvents() {
    LootTableEvents.MODIFY.register(::modifyLootTables)
    TradeOfferHelper.registerWanderingTraderOffers(1, ::addCommonWanderingTrades)
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, ::addLevel2FarmerTrades)
    compostItems()
    createFuels()
}

@Suppress("UNUSED_PARAMETER")
fun modifyLootTables(
    key: ResourceKey<LootTable>, builder: LootTable.Builder, src: LootTableSource, lookup: HolderLookup.Provider,
): Any = when (key) {
    PIGLIN_BARTERING -> addToExistingPools(builder, BARTERING_ADD_VIVIONS)
    SNIFFER_DIGGING -> addToExistingPools(builder, SNIFFER_ADD_MOONBERRY)
    SIMPLE_DUNGEON -> addNewPool(builder, SIMPLE_DUNGEON_ADD_SPOOKY)
    else -> Unit
}


fun addCommonWanderingTrades(trades: MutableList<VillagerTrades.ItemListing>) {
    trades.addSell(DnDBlocks.CASCADE_SAPLING, 5, 1, 8)
    trades.addSell(DnDBlocks.GOLDEN_BIRCH_SAPLING, 5, 1, 8)

    trades.add1for1(DnDItems.LANTERN_PUMPKIN_SEEDS, 12)
    trades.add1for1(DnDItems.MOSSKIN_PUMPKIN_SEEDS, 12)
    trades.add1for1(DnDItems.PALE_PUMPKIN_SEEDS, 12)
    trades.add1for1(DnDItems.GLOOM_PUMPKIN_SEEDS, 12)

    trades.add1for1(DnDBlocks.LANTERN_PUMPKIN, 4)
    trades.add1for1(DnDBlocks.MOSSKIN_PUMPKIN, 4)
    trades.add1for1(DnDBlocks.PALE_PUMPKIN, 4)
    trades.add1for1(DnDBlocks.GLOOM_PUMPKIN, 4)

    trades.add1for1(DnDItems.CORN_KERNELS, 12)
    trades.add1for1(DnDItems.CORN, 4)

    trades.add1for1(DnDBlocks.WILD_WHEAT, 16)
//    trades.addSell(DnDBlocks.GOLDEN_MUSHROOM, 1, 1, 12)

    DnDBlockLists.flowerbedBlocks.forEach { flowerbed ->
        trades.addSell(flowerbed, 3, 1, 8)
    }
    trades.addSell(Blocks.PINK_PETALS, 3, 1, 8)
}

fun addLevel2FarmerTrades(trades: MutableList<VillagerTrades.ItemListing>) {
    trades.buyFor1(DnDBlocks.LANTERN_PUMPKIN, 6, 12, 10)
    trades.buyFor1(DnDBlocks.MOSSKIN_PUMPKIN, 6, 12, 10)
    trades.buyFor1(DnDBlocks.GLOOM_PUMPKIN, 6, 12, 10)
    trades.buyFor1(DnDBlocks.PALE_PUMPKIN, 6, 12, 10)
    // Add to Voided delight
//    SellItemFactory(Items.PUMPKIN_PIE, 1, 4, 5),
}

fun compostItems() {
    compost(DnDBlocks.ROOT_BLOCK, 0.65)
    DnDBlockLists.flowerbedBlocks.forEach { compost(it, 0.3) }

    compost(DnDBlocks.GOLDEN_MUSHROOM, 0.65)
    compost(DnDBlocks.GOLDEN_MUSHROOM_BLOCK, 0.85)
    compost(DnDBlocks.GOLDEN_MUSHROOM_STEM_BLOCK, 0.65)

    compost(DnDItems.LANTERN_PUMPKIN_SEEDS, 0.3)
    compost(DnDItems.MOSSKIN_PUMPKIN_SEEDS, 0.3)
    compost(DnDItems.GLOOM_PUMPKIN_SEEDS, 0.3)
    compost(DnDItems.PALE_PUMPKIN_SEEDS, 0.3)

    compost(DnDBlocks.LANTERN_PUMPKIN, 0.65)
    compost(DnDBlocks.MOSSKIN_PUMPKIN, 0.65)
    compost(DnDBlocks.GLOOM_PUMPKIN, 0.65)
    compost(DnDBlocks.PALE_PUMPKIN, 0.65)

    compost(DnDBlocks.CARVED_LANTERN_PUMPKIN, 0.65)
    compost(DnDBlocks.CARVED_MOSSKIN_PUMPKIN, 0.65)
    compost(DnDBlocks.CARVED_GLOOM_PUMPKIN, 0.65)
    compost(DnDBlocks.CARVED_PALE_PUMPKIN, 0.65)

    compost(DnDBlocks.SMALL_PUMPKIN, 0.45)
    compost(DnDBlocks.SMALL_LANTERN_PUMPKIN, 0.45)
    compost(DnDBlocks.SMALL_MOSSKIN_PUMPKIN, 0.45)
    compost(DnDBlocks.SMALL_GLOOM_PUMPKIN, 0.45)
    compost(DnDBlocks.SMALL_PALE_PUMPKIN, 0.45)

    compost(DnDBlocks.SMALL_CARVED_PUMPKIN, 0.45)
    compost(DnDBlocks.SMALL_CARVED_LANTERN_PUMPKIN, 0.45)
    compost(DnDBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN, 0.45)
    compost(DnDBlocks.SMALL_CARVED_GLOOM_PUMPKIN, 0.45)
    compost(DnDBlocks.SMALL_CARVED_PALE_PUMPKIN, 0.45)
}

fun createFuels() {
    fuel(DnDItems.BIG_SCAFFOLDING, 300)
    fuel(DnDBlocks.MOLTEN_LAVASPONGE, 20000)
    fuel(DnDItems.GLOWING_LAVASPONGE, 20000)
}

