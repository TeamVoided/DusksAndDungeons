package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.TexturedModel
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object WoodModels {
    fun woodModels(gen: BlockStateModelGenerator) {
        gen.registerFlowerPotPlant(
            DnDBlocks.CASCADE_SAPLING,
            DnDBlocks.POTTED_CASCADE_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerLog(DnDBlocks.CASCADE_LOG)
            .log(DnDBlocks.CASCADE_LOG)
            .wood(DnDBlocks.CASCADE_WOOD.parent)

        gen.registerLog(DnDBlocks.STRIPPED_CASCADE_LOG)
            .log(DnDBlocks.STRIPPED_CASCADE_LOG)
            .log(DnDBlocks.STRIPPED_CASCADE_WOOD)
        gen.registerHangingSign(
            DnDBlocks.STRIPPED_CASCADE_LOG,
            DnDBlocks.CASCADE_HANGING_SIGN,
            DnDBlocks.CASCADE_WALL_HANGING_SIGN
        )
        gen.registerSingleton(DnDBlocks.CASCADE_LEAVES, TexturedModel.LEAVES)
        gen.registerDoor(DnDBlocks.BLUE_DOOR)
        gen.registerFlowerPotPlant(
            DnDBlocks.GOLDEN_BIRCH_SAPLING,
            DnDBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerSingleton(DnDBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES)

        gen.createWood(DnDBlocks.OAK_WOOD, Blocks.OAK_LOG)
        gen.createWood(DnDBlocks.SPRUCE_WOOD, Blocks.SPRUCE_LOG)
        gen.createWood(DnDBlocks.BIRCH_WOOD, Blocks.BIRCH_LOG)
        gen.createWood(DnDBlocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG)
        gen.createWood(DnDBlocks.ACACIA_WOOD, Blocks.ACACIA_LOG)
        gen.createWood(DnDBlocks.DARK_OAK_WOOD, Blocks.DARK_OAK_LOG)
        gen.createWood(DnDBlocks.MANGROVE_WOOD, Blocks.MANGROVE_LOG)
        gen.createWood(DnDBlocks.CHERRY_WOOD, Blocks.CHERRY_LOG)
        gen.createWood(DnDBlocks.CRIMSON_HYPHAE, Blocks.CRIMSON_STEM)
        gen.createWood(DnDBlocks.WARPED_HYPHAE, Blocks.WARPED_STEM)
        gen.createWood(DnDBlocks.CASCADE_WOOD, DnDBlocks.CASCADE_LOG)

        gen.wall(DnDBlocks.OAK_WALL, Blocks.OAK_PLANKS)
        gen.wall(DnDBlocks.SPRUCE_WALL, Blocks.SPRUCE_PLANKS)
        gen.wall(DnDBlocks.BIRCH_WALL, Blocks.BIRCH_PLANKS)
        gen.wall(DnDBlocks.JUNGLE_WALL, Blocks.JUNGLE_PLANKS)
        gen.wall(DnDBlocks.ACACIA_WALL, Blocks.ACACIA_PLANKS)
        gen.wall(DnDBlocks.DARK_OAK_WALL, Blocks.DARK_OAK_PLANKS)
        gen.wall(DnDBlocks.MANGROVE_WALL, Blocks.MANGROVE_PLANKS)
        gen.wall(DnDBlocks.CHERRY_WALL, Blocks.CHERRY_PLANKS)
        gen.wall(DnDBlocks.CRIMSON_WALL, Blocks.CRIMSON_PLANKS)
        gen.wall(DnDBlocks.WARPED_WALL, Blocks.WARPED_PLANKS)
        gen.wall(DnDBlocks.BAMBOO_WALL, Blocks.BAMBOO_PLANKS)
        gen.wall(DnDBlocks.BAMBOO_MOSAIC_WALL, Blocks.BAMBOO_MOSAIC)
        gen.wall(DnDBlocks.CASCADE_WALL, DnDBlocks.CASCADE_PLANKS)

        DnDBlockLists.hollowLogs.forEachIndexed { idx, hollowLog ->
            val log = DnDBlockLists.logsAndStrippedLogs[idx].first
            val strippedLog = DnDBlockLists.logsAndStrippedLogs[idx].second
            gen.hollowLog(hollowLog, log, strippedLog)
            gen.hollowLog(DnDBlockLists.hollowStrippedLogs[idx], strippedLog)
        }
        gen.hollowBambooBlock(DnDBlocks.HOLLOW_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK)
        gen.hollowBambooBlock(DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
        gen.createLogPile(DnDBlocks.BAMBOO_PILE, Blocks.BAMBOO_BLOCK, true)
        gen.createLogPile(DnDBlocks.STRIPPED_BAMBOO_PILE, Blocks.STRIPPED_BAMBOO_BLOCK, true)
        DnDBlockLists.logPiles.forEachIndexed { idx, pile ->
            gen.createLogPile(pile, DnDBlockLists.logsAndStrippedLogs[idx].first)
        }
        DnDBlockLists.leafPiles.forEachIndexed { idx, pile -> gen.createLeafPile(pile, DnDBlockLists.leaves[idx]) }
    }
}