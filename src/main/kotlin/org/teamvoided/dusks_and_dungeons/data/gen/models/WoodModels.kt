package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.world.level.block.Blocks
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.model.TexturedModel
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object WoodModels {
    fun woodModels(gen: BlockModelGenerators) {
        gen.createPlant(
            DnDBlocks.CASCADE_SAPLING,
            DnDBlocks.POTTED_CASCADE_SAPLING,
            BlockModelGenerators.TintState.NOT_TINTED
        )
        gen.woodProvider(DnDBlocks.CASCADE_LOG)
            .logWithHorizontal(DnDBlocks.CASCADE_LOG)
            .wood(DnDBlocks.CASCADE_WOOD.parent)

        gen.woodProvider(DnDBlocks.STRIPPED_CASCADE_LOG)
            .logWithHorizontal(DnDBlocks.STRIPPED_CASCADE_LOG)
            .wood(DnDBlocks.STRIPPED_CASCADE_WOOD.parent)
        gen.createHangingSign(
            DnDBlocks.STRIPPED_CASCADE_LOG,
            DnDBlocks.CASCADE_HANGING_SIGN,
            DnDBlocks.CASCADE_WALL_HANGING_SIGN
        )
        gen.createTrivialBlock(DnDBlocks.CASCADE_LEAVES, TexturedModel.LEAVES)
        gen.createDoor(DnDBlocks.BLUE_DOOR)
        gen.createPlant(
            DnDBlocks.GOLDEN_BIRCH_SAPLING,
            DnDBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
            BlockModelGenerators.TintState.NOT_TINTED
        )
        gen.createTrivialBlock(DnDBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES)

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

        gen.createWood(DnDBlocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_OAK_LOG)
        gen.createWood(DnDBlocks.STRIPPED_SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_LOG)
        gen.createWood(DnDBlocks.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_BIRCH_LOG)
        gen.createWood(DnDBlocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_LOG)
        gen.createWood(DnDBlocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_ACACIA_LOG)
        gen.createWood(DnDBlocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_LOG)
        gen.createWood(DnDBlocks.STRIPPED_MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_LOG)
        gen.createWood(DnDBlocks.STRIPPED_CHERRY_WOOD, Blocks.STRIPPED_CHERRY_LOG)
        gen.createWood(DnDBlocks.STRIPPED_CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_STEM)
        gen.createWood(DnDBlocks.STRIPPED_WARPED_HYPHAE, Blocks.STRIPPED_WARPED_STEM)
        gen.createWood(DnDBlocks.STRIPPED_CASCADE_WOOD, DnDBlocks.STRIPPED_CASCADE_LOG)

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
        DnDBlockLists.logPiles.forEachIndexed { idx, pile ->
            gen.createLogPile(pile, DnDBlockLists.logsAndStrippedLogs[idx].first)
        }
        DnDBlockLists.stripedLogPiles.forEachIndexed { idx, pile ->
            gen.createLogPile(pile, DnDBlockLists.logsAndStrippedLogs[idx].second)
        }
        gen.createLogPile(DnDBlocks.BAMBOO_PILE, Blocks.BAMBOO_BLOCK, true)
        gen.createLogPile(DnDBlocks.STRIPPED_BAMBOO_PILE, Blocks.STRIPPED_BAMBOO_BLOCK, true)
        DnDBlockLists.leafPiles.forEachIndexed { idx, pile -> gen.createLeafPile(pile, DnDBlockLists.leaves[idx]) }
    }
}