package org.teamvoided.dusk_autumn.event

import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.block.Blocks
import net.minecraft.village.TradeOffers
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.util.DnDBlockLists

object AddWanderingTrades {
    fun init() {
        TradeOfferHelper.registerWanderingTraderOffers(1) {
            it.add(TradeOffers.SellItemFactory(DnDWoodBlocks.CASCADE_SAPLING, 5, 1, 8, 1))
            it.add(TradeOffers.SellItemFactory(DnDWoodBlocks.GOLDEN_BIRCH_SAPLING, 5, 1, 8, 1))
            DnDBlockLists.flowerbedBlocks.forEach { flowerbed ->
                it.add(TradeOffers.SellItemFactory(flowerbed, 3, 1, 8, 1))
            }
            it.add(TradeOffers.SellItemFactory(Blocks.PINK_PETALS, 3, 1, 8, 1))
        }
    }
}