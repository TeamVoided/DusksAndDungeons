package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.TexturedModel
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object WoodModels {
    fun woodModels(gen: BlockStateModelGenerator) {
        gen.registerFlowerPotPlant(
            DnDWoodBlocks.CASCADE_SAPLING,
            DnDWoodBlocks.POTTED_CASCADE_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerLog(DnDWoodBlocks.CASCADE_LOG)
            .log(DnDWoodBlocks.CASCADE_LOG)
            .wood(DnDWoodBlocks.CASCADE_WOOD.parent)

        gen.registerLog(DnDWoodBlocks.STRIPPED_CASCADE_LOG)
            .log(DnDWoodBlocks.STRIPPED_CASCADE_LOG)
            .log(DnDWoodBlocks.STRIPPED_CASCADE_WOOD)
        gen.registerHangingSign(
            DnDWoodBlocks.STRIPPED_CASCADE_LOG,
            DnDWoodBlocks.CASCADE_HANGING_SIGN,
            DnDWoodBlocks.CASCADE_WALL_HANGING_SIGN
        )
        gen.registerSingleton(DnDWoodBlocks.CASCADE_LEAVES, TexturedModel.LEAVES)
        gen.registerDoor(DnDWoodBlocks.BLUE_DOOR)
        gen.registerFlowerPotPlant(
            DnDWoodBlocks.GOLDEN_BIRCH_SAPLING,
            DnDWoodBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerSingleton(DnDWoodBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES)

        gen.registerFlowerPotPlant(
            DnDWoodBlocks.GALLERY_MAPLE_SAPLING,
            DnDWoodBlocks.POTTED_GALLERY_MAPLE_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerLog(DnDWoodBlocks.GALLERY_MAPLE_LOG)
            .log(DnDWoodBlocks.GALLERY_MAPLE_LOG)
            .wood(DnDWoodBlocks.GALLERY_MAPLE_WOOD)
        gen.registerLog(DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_LOG)
            .log(DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_LOG)
            .wood(DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_WOOD)
        gen.registerHangingSign(
            DnDWoodBlocks.STRIPPED_GALLERY_MAPLE_LOG,
            DnDWoodBlocks.GALLERY_MAPLE_HANGING_SIGN,
            DnDWoodBlocks.GALLERY_MAPLE_WALL_HANGING_SIGN
        )
        gen.registerSingleton(DnDWoodBlocks.GALLERY_MAPLE_LEAVES, TexturedModel.LEAVES)

        gen.createWood(DnDWoodBlocks.OAK_WOOD, Blocks.OAK_LOG)
        gen.createWood(DnDWoodBlocks.SPRUCE_WOOD, Blocks.SPRUCE_LOG)
        gen.createWood(DnDWoodBlocks.BIRCH_WOOD, Blocks.BIRCH_LOG)
        gen.createWood(DnDWoodBlocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG)
        gen.createWood(DnDWoodBlocks.ACACIA_WOOD, Blocks.ACACIA_LOG)
        gen.createWood(DnDWoodBlocks.DARK_OAK_WOOD, Blocks.DARK_OAK_LOG)
        gen.createWood(DnDWoodBlocks.MANGROVE_WOOD, Blocks.MANGROVE_LOG)
        gen.createWood(DnDWoodBlocks.CHERRY_WOOD, Blocks.CHERRY_LOG)
        gen.createWood(DnDWoodBlocks.CRIMSON_HYPHAE, Blocks.CRIMSON_STEM)
        gen.createWood(DnDWoodBlocks.WARPED_HYPHAE, Blocks.WARPED_STEM)
        gen.createWood(DnDWoodBlocks.CASCADE_WOOD, DnDWoodBlocks.CASCADE_LOG)

        gen.genPsudoFamily( // delete this when done
            DnDWoodBlocks.GALLERY_MAPLE_WOOD_STAIRS,
            DnDWoodBlocks.GALLERY_MAPLE_WOOD_SLAB,
            DnDWoodBlocks.GALLERY_MAPLE_WOOD_WALL,
            DnDWoodBlocks.GALLERY_MAPLE_LOG,
            DnDWoodBlocks.GALLERY_MAPLE_WOOD
        )

        DnDBlockLists.hollowLogs.forEachIndexed { idx, hollowLog ->
            val log = DnDBlockLists.logsAndStrippedLogs[idx].first
            val strippedLog = DnDBlockLists.logsAndStrippedLogs[idx].second
            gen.hollowLog(hollowLog, log, strippedLog)
            gen.hollowLog(DnDBlockLists.hollowStrippedLogs[idx], strippedLog)
        }
        gen.hollowBambooBlock(DnDWoodBlocks.HOLLOW_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK)
        gen.hollowBambooBlock(DnDWoodBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
        gen.createLogPile(DnDWoodBlocks.BAMBOO_PILE, Blocks.BAMBOO_BLOCK, true)
        gen.createLogPile(DnDWoodBlocks.STRIPPED_BAMBOO_PILE, Blocks.STRIPPED_BAMBOO_BLOCK, true)
        DnDBlockLists.logPiles.forEachIndexed { idx, pile ->
            gen.createLogPile(pile, DnDBlockLists.logsAndStrippedLogs[idx].first)
        }
        DnDBlockLists.leafPiles.forEachIndexed { idx, pile -> gen.createLeafPile(pile, DnDBlockLists.leaves[idx]) }
    }
}