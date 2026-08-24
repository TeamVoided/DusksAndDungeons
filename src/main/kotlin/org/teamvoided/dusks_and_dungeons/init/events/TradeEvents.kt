package org.teamvoided.dusks_and_dungeons.init.events

import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.entity.npc.VillagerTrades
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.voidlib.helpers.mc.add1for1
import org.teamvoided.voidlib.helpers.mc.addSell
import org.teamvoided.voidlib.helpers.mc.buyFor1

fun initTrades() {
    TradeOfferHelper.registerWanderingTraderOffers(1, ::addCommonWanderingTrades)
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, ::addLevel2FarmerTrades)
}

fun addCommonWanderingTrades(trades: MutableList<VillagerTrades.ItemListing>) {
    trades.addSell(DnDBlocks.CASCADE_SAPLING, 5, 1, 8)
    trades.addSell(DnDBlocks.SYPIA_SAPLING, 5, 1, 8)

    // TODO(1.0) add overgrown moss

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
