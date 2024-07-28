package org.teamvoided.dusk_autumn.event

import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.village.TradeOffers
import java.util.*

object TradingEventHandler {
    fun init(){
        DuskTrades.registerTrades()
        registerTrades()
    }
    fun registerTrades() {
        DuskTrades.WANDERING_TRADES.forEach { (level: Int, trades: LinkedList<TradeOffers.Factory>) ->
            TradeOfferHelper.registerWanderingTraderOffers(level)
            { oldTrades: MutableList<TradeOffers.Factory> -> oldTrades.addAll(trades) }
        }
    }
}