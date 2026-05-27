package org.teamvoided.dusks_and_dungeons.compat

import dev.emi.emi.api.EmiPlugin
import dev.emi.emi.api.EmiRegistry
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.isDev
import org.teamvoided.dusks_and_dungeons.init.DnDItems.EVIL_ITEMS

object DnDEmiPlugin : EmiPlugin {
    override fun register(reg: EmiRegistry) {
        handleExperimentalItems(reg)
    }

    fun handleExperimentalItems(reg: EmiRegistry) {
        if (isDev()) return
        reg.removeEmiStacks { it.itemStack.item in EVIL_ITEMS }
    }
}