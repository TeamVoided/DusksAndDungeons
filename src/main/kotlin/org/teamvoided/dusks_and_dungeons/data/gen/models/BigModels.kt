package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.data.models.BlockModelGenerators
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object BigModels {
    fun register(gen: BlockModelGenerators) {
        gen.registerBigChain(DnDBlocks.BIG_CHAIN)
        gen.registerBigLantern(DnDBlocks.BIG_LANTERN)
        gen.registerBigLantern(DnDBlocks.BIG_SOUL_LANTERN)

        DnDBlockLists.bigCandles.forEach(gen::registerBigCandle)
        DnDBlockLists.soulCandles.forEach(gen::registerCandle2)
        DnDBlockLists.bigSoulCandles.forEach(gen::registerBigCandle)

        DnDBlockLists.candelabras.forEach(gen::registerCandelabra)
        DnDBlockLists.soulCandelabras.forEach(gen::registerDnDCandelabra)
    }
}
