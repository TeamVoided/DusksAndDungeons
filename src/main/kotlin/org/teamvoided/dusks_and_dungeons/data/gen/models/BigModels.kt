package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.data.client.model.BlockStateModelGenerator
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object BigModels {
    fun register(gen: BlockStateModelGenerator) {
        gen.registerBigChain(DnDBlocks.BIG_CHAIN)
        gen.registerBigLantern(DnDBlocks.BIG_LANTERN)
//        gen.registerBigLantern(DnDBlocks.BIG_REDSTONE_LANTERN, true)
        gen.registerBigLantern(DnDBlocks.BIG_SOUL_LANTERN)
        gen.registerBigChain(DnDBlocks.BIG_CELESTAL_CHAIN)
        val mLB = id("block/big_celestal_lantern_bottom")
        gen.registerBigLantern(DnDBlocks.BIG_MOON_LANTERN, mLB)
        gen.registerBigLantern(DnDBlocks.BIG_EARTH_LANTERN, mLB)
        gen.registerBigLantern(DnDBlocks.BIG_COMET_LANTERN, mLB)
        gen.registerBigLantern(DnDBlocks.BIG_SUN_LANTERN, mLB)
        gen.registerBigLantern(DnDBlocks.BIG_STAR_LANTERN, mLB)
        gen.registerBigLantern(DnDBlocks.BIG_NEBULAE_LANTERN, mLB)
        gen.registerBigLantern(DnDBlocks.BIG_ECLIPSE_LANTERN, mLB)
        DnDBlockLists.bigCandles.forEach(gen::registerBigCandle)
        DnDBlockLists.soulCandles.forEach(gen::registerCandle2)
        DnDBlockLists.bigSoulCandles.forEach(gen::registerBigCandle)
        gen.registerBell(DnDBlocks.CELESTAL_BELL)

        DnDBlockLists.candelabras.forEach(gen::registerCandelabra)
        DnDBlockLists.soulCandelabras.forEach(gen::registerDnDCandelabra)
    }
}
