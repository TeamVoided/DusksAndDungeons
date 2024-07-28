package org.teamvoided.dusk_autumn.event

import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.village.TradeOffers
import org.teamvoided.dusk_autumn.init.DuskBlocks

object AddWanderingTrades {
    fun init() {
        TradeOfferHelper.registerWanderingTraderOffers(1) {
            it.add(TradeOffers.SellItemFactory(DuskBlocks.CASCADE_SAPLING, 5, 1, 8, 1))
            it.add(TradeOffers.SellItemFactory(DuskBlocks.GOLDEN_BIRCH_SAPLING, 5, 1, 8, 1))
        }
    }
}