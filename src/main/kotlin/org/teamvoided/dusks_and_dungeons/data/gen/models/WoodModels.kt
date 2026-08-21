package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers.createItemModel
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers.createSign
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.block_model.*
import org.teamvoided.dusks_and_dungeons.util.datagen.createLeafPile
import org.teamvoided.dusks_and_dungeons.util.datagen.createWood
import org.teamvoided.dusks_and_dungeons.util.datagen.wall

object WoodModels {
    fun woodModels(gen: BlockModelGenerators) {
        //CASCADE
        gen.createPlant(
            DnDBlocks.CASCADE_SAPLING,
            DnDBlocks.POTTED_CASCADE_SAPLING,
            BlockModelGenerators.TintState.NOT_TINTED
        )
        gen.createTrivialBlock(DnDBlocks.CASCADE_LEAVES, TexturedModel.LEAVES)
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
        gen.createDoor(DnDBlocks.BLUE_DOOR)

        // Sypia
        gen.createPlant(
            DnDBlocks.SYPIA_SAPLING,
            DnDBlocks.POTTED_SYPIA_SAPLING,
            BlockModelGenerators.TintState.NOT_TINTED
        )
        gen.createTrivialBlock(DnDBlocks.SYPIA_LEAVES, TexturedModel.LEAVES)
        gen.woodProvider(DnDBlocks.SYPIA_LOG)
            .logWithHorizontal(DnDBlocks.SYPIA_LOG)
            .wood(DnDBlocks.SYPIA_WOOD.parent)

        gen.woodProvider(DnDBlocks.STRIPPED_SYPIA_LOG)
            .logWithHorizontal(DnDBlocks.STRIPPED_SYPIA_LOG)
            .wood(DnDBlocks.STRIPPED_SYPIA_WOOD.parent)
        gen.createHangingSign(
            DnDBlocks.STRIPPED_SYPIA_LOG,
            DnDBlocks.SYPIA_HANGING_SIGN,
            DnDBlocks.SYPIA_WALL_HANGING_SIGN
        )

        // Verdant
        gen.createItemModel(DnDBlocks.VERDANT_LOG)
        gen.woodProvider(DnDBlocks.VERDANT_LOG).wood(DnDBlocks.VERDANT_WOOD.parent)
        gen.strippedTinted(DnDBlocks.STRIPPED_VERDANT_LOG, DnDBlocks.STRIPPED_VERDANT_WOOD)
        gen.planksTinted(
            DnDBlocks.VERDANT_PLANKS,
            DnDBlocks.VERDANT_STAIRS,
            DnDBlocks.VERDANT_SLAB,
            DnDBlocks.VERDANT_WALL,
            DnDBlocks.VERDANT_FENCE,
            DnDBlocks.VERDANT_FENCE_GATE,
            DnDBlocks.VERDANT_BUTTON,
            DnDBlocks.VERDANT_PRESSURE_PLATE,
//            DnDBlocks.VERDANT_,
        )

        gen.createHangingSign(
            DnDBlocks.STRIPPED_VERDANT_LOG,
            DnDBlocks.VERDANT_HANGING_SIGN, DnDBlocks.VERDANT_WALL_HANGING_SIGN
        )
        gen.createSign(DnDBlocks.VERDANT_PLANKS, DnDBlocks.VERDANT_SIGN, DnDBlocks.VERDANT_WALL_SIGN)

        //ASSORTED ADDITIONS
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
        gen.createWood(DnDBlocks.SYPIA_WOOD, DnDBlocks.SYPIA_LOG)
        gen.createWood(DnDBlocks.VERDANT_WOOD, DnDBlocks.VERDANT_LOG)

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
        gen.createWood(DnDBlocks.STRIPPED_SYPIA_WOOD, DnDBlocks.STRIPPED_SYPIA_LOG)

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
        gen.wall(DnDBlocks.SYPIA_WALL, DnDBlocks.SYPIA_PLANKS)

        DnDBlockLists.hollowLogs.forEachIndexed { idx, hollowLog ->
            val log = DnDBlockLists.logsAndStrippedLogs[idx].first
            val strippedLog = DnDBlockLists.logsAndStrippedLogs[idx].second
            gen.hollowLog(hollowLog, log, strippedLog)
            gen.hollowLog(DnDBlockLists.hollowStrippedLogs[idx], strippedLog)
        }
        gen.hollowBambooBlock(DnDBlocks.HOLLOW_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK)
        gen.hollowBambooBlock(DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
        gen.hollowTintedLog(DnDBlocks.HOLLOW_VERDANT_LOG, DnDBlocks.VERDANT_LOG, DnDBlocks.STRIPPED_VERDANT_LOG)
        gen.hollowTintedStrippedLog(DnDBlocks.HOLLOW_STRIPPED_VERDANT_LOG, DnDBlocks.STRIPPED_VERDANT_LOG)
        DnDBlockLists.logPiles.forEachIndexed { idx, pile ->
            gen.createLogPile(pile, DnDBlockLists.logsAndStrippedLogs[idx].first)
        }
        DnDBlockLists.stripedLogPiles.forEachIndexed { idx, pile ->
            gen.createLogPile(pile, DnDBlockLists.logsAndStrippedLogs[idx].second)
        }
        gen.createLogPile(DnDBlocks.VERDANT_LOG_PILE, DnDBlocks.VERDANT_LOG, "tint/log")
        gen.createLogPile(DnDBlocks.STRIPPED_VERDANT_LOG_PILE, DnDBlocks.STRIPPED_VERDANT_LOG, "tint/stripped_log")
        gen.createLogPile(DnDBlocks.BAMBOO_PILE, Blocks.BAMBOO_BLOCK, "bamboo")
        gen.createLogPile(DnDBlocks.STRIPPED_BAMBOO_PILE, Blocks.STRIPPED_BAMBOO_BLOCK, "bamboo")
        DnDBlockLists.leafPiles.forEachIndexed { idx, pile -> gen.createLeafPile(pile, DnDBlockLists.leaves[idx]) }
    }
}