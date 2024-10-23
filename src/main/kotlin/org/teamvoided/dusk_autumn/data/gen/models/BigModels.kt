package org.teamvoided.dusk_autumn.data.gen.models

import net.minecraft.data.client.model.BlockStateModelGenerator
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDBigBlocks
import org.teamvoided.dusk_autumn.util.*

object BigModels {
    fun register(gen: BlockStateModelGenerator) {
        gen.registerBigChain(DnDBigBlocks.BIG_CHAIN)
        gen.registerBigLantern(DnDBigBlocks.BIG_LANTERN)
        gen.registerBigLantern(DnDBigBlocks.BIG_REDSTONE_LANTERN, true)
        gen.registerBigLantern(DnDBigBlocks.BIG_SOUL_LANTERN)
        gen.registerBigChain(DnDBigBlocks.BIG_CELESTAL_CHAIN)
        val mLB = "block/big_celestal_lantern_bottom"
        gen.registerBigLantern(DnDBigBlocks.BIG_MOON_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_EARTH_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_COMET_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_SUN_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_STAR_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_NEBULAE_LANTERN, false, mLB)
        gen.registerBigLantern(DnDBigBlocks.BIG_ECLIPSE_LANTERN, false, mLB)
        DnDBlockLists.bigCandles.forEach(gen::registerBigCandle)
        DnDBlockLists.soulCandles.forEach(gen::registerCandle2)
        DnDBlockLists.bigSoulCandles.forEach(gen::registerBigCandle)
        gen.registerBell(DnDBlocks.CELESTAL_BELL)

        DnDBlockLists.candelabras.forEach(gen::registerCandelabra)
        DnDBlockLists.soulCandelabras.forEach(gen::registerDnDCandelabra)
    }
}
