package org.teamvoided.dusks_and_dungeons.datagen.assets.model

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.*
import org.teamvoided.dusks_and_dungeons.datagen.old.util.redstoneLantern
import org.teamvoided.dusks_and_dungeons.datagen.old.util.registerBigCandle
import org.teamvoided.dusks_and_dungeons.datagen.old.util.registerCandle2
import org.teamvoided.dusks_and_dungeons.datagen.old.util.tintedPane
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists

object FunctionalModels {

    fun create(gen: BlockModelGenerators) {
        gen.createBigChain(DnDBlocks.BIG_CHAIN)

        gen.createBigLantern(DnDBlocks.BIG_LANTERN)
        gen.createBigLantern(DnDBlocks.BIG_SOUL_LANTERN)

        gen.redstoneLantern(DnDBlocks.REDSTONE_LANTERN)
        gen.createBigLantern(DnDBlocks.BIG_REDSTONE_LANTERN, true)

        DnDBlockLists.bigCandles.forEach(gen::registerBigCandle)
        DnDBlockLists.soulCandles.forEach(gen::registerCandle2)
        DnDBlockLists.bigSoulCandles.forEach(gen::registerBigCandle)

        gen.createCandelabra(DnDBlocks.EMPTY_IRON_CANDELABRA, DnDBlocks.IRON_CANDELABRA)

        gen.createBigScaffolding(DnDBlocks.BIG_SCAFFOLDING)

//        gen.sconce(DnDBlocks.OAK_SCONCE)
        gen.sconce(DnDBlocks.SPRUCE_SCONCE)
//        gen.sconce(DnDBlocks.BIRCH_SCONCE)
//        gen.sconce(DnDBlocks.JUNGLE_SCONCE)
//        gen.sconce(DnDBlocks.ACACIA_SCONCE)
//        gen.sconce(DnDBlocks.DARK_OAK_SCONCE)
//        gen.sconce(DnDBlocks.MANGROVE_SCONCE)
//        gen.sconce(DnDBlocks.CHERRY_SCONCE)
//        gen.sconce(DnDBlocks.CASCADE_SCONCE)
//        gen.sconce(DnDBlocks.SYPIA_SCONCE)
//        gen.sconce(DnDBlocks.VERDANT_SCONCE)
//        gen.sconce(DnDBlocks.BAMBOO_SCONCE)
//        gen.sconce(DnDBlocks.CRIMSON_SCONCE)
//        gen.sconce(DnDBlocks.WARPED_SCONCE)

        gen.sconce(DnDBlocks.IRON_SCONCE)
//        gen.sconce(DnDBlocks.GOLD_SCONCE)

        gen.tintedPane(Blocks.TINTED_GLASS, DnDBlocks.TINTED_GLASS_PANE)
    }

}
