package org.teamvoided.dusk_autumn.event

import net.minecraft.village.TradeOffers
import net.minecraft.village.TradeOffers.SellItemFactory
import org.teamvoided.dusk_autumn.init.DuskBlocks
import java.util.*

object DuskTrades {
    // this + Profession -> Map of trades for that profession, Map + Trade level -> List of trades of that level
    val WANDERING_TRADES: MutableMap<Int, LinkedList<TradeOffers.Factory>> = HashMap()

    private fun addWandering(trade: TradeOffers.Factory) {
        addWandering(1, trade)
    }
    private fun addWandering(level: Int, trade: TradeOffers.Factory) {
        WANDERING_TRADES.putIfAbsent(level, LinkedList())
        WANDERING_TRADES[level]!!.add(trade)
    }
    fun registerTrades() {
        //itemLike, price, count, max uses, xp=
        addWandering(SellItemFactory(DuskBlocks.CASCADE_SAPLING, 5, 1, 8, 1))
        addWandering(SellItemFactory(DuskBlocks.GOLDEN_BIRCH_SAPLING, 5, 1, 8, 1))
    }
}