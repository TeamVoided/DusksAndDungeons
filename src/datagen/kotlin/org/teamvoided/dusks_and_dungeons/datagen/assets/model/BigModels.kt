package org.teamvoided.dusks_and_dungeons.datagen.assets.model

import net.minecraft.data.models.BlockModelGenerators
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.createBigChain
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.createBigLantern
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.createBigScaffolding
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.datagen.old.util.registerBigCandle
import org.teamvoided.dusks_and_dungeons.datagen.old.util.registerCandelabra
import org.teamvoided.dusks_and_dungeons.datagen.old.util.registerCandle2
import org.teamvoided.dusks_and_dungeons.datagen.old.util.registerDnDCandelabra

object BigModels {
    fun register(gen: BlockModelGenerators) {
        gen.createBigChain(DnDBlocks.BIG_CHAIN)
        gen.createBigLantern(DnDBlocks.BIG_LANTERN)
        gen.createBigLantern(DnDBlocks.BIG_SOUL_LANTERN)
        gen.createBigLantern(DnDBlocks.BIG_REDSTONE_LANTERN, true)

        DnDBlockLists.bigCandles.forEach(gen::registerBigCandle)
        DnDBlockLists.soulCandles.forEach(gen::registerCandle2) //TODO(1.0) move this out?
        DnDBlockLists.bigSoulCandles.forEach(gen::registerBigCandle)

        DnDBlockLists.candelabras.forEach(gen::registerCandelabra) //TODO(1.0) move this out?
        DnDBlockLists.soulCandelabras.forEach(gen::registerDnDCandelabra) //TODO(1.0) move this out?

        gen.createBigScaffolding(DnDBlocks.BIG_SCAFFOLDING)
    }
}
