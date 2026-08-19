package org.teamvoided.dusks_and_dungeons.data.gen.assets.model

import net.minecraft.data.models.BlockModelGenerators
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers.*
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object BigModels {
    fun register(gen: BlockModelGenerators) {
        gen.createBigChain(DnDBlocks.BIG_CHAIN)
        gen.createBigLantern(DnDBlocks.BIG_LANTERN)
        gen.createBigLantern(DnDBlocks.BIG_SOUL_LANTERN)
        gen.createBigLantern(DnDBlocks.BIG_REDSTONE_LANTERN, true)

        DnDBlockLists.bigCandles.forEach(gen::registerBigCandle)
        DnDBlockLists.soulCandles.forEach(gen::registerCandle2) //TODO move this out?
        DnDBlockLists.bigSoulCandles.forEach(gen::registerBigCandle)

        DnDBlockLists.candelabras.forEach(gen::registerCandelabra) //TODO move this out?
        DnDBlockLists.soulCandelabras.forEach(gen::registerDnDCandelabra) //TODO move this out?

        gen.createBigScaffolding(DnDBlocks.BIG_SCAFFOLDING)
    }
}
