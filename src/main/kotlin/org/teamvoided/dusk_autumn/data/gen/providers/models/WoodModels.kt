package org.teamvoided.dusk_autumn.data.gen.providers.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.TexturedModel
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.util.*

object WoodModels {
    fun woodModels(gen: BlockStateModelGenerator) {
        gen.registerFlowerPotPlant(
            DnDWoodBlocks.CASCADE_SAPLING,
            DnDWoodBlocks.POTTED_CASCADE_SAPLING,
            BlockStateModelGenerator.TintType.NOT_TINTED
        )
        gen.registerLog(DnDWoodBlocks.CASCADE_LOG)
            .log(DnDWoodBlocks.CASCADE_LOG)
            .wood(DnDWoodBlocks.CASCADE_WOOD)
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

        gen.genPsudoFamily(
            DnDWoodBlocks.OAK_WOOD_STAIRS, DnDWoodBlocks.OAK_WOOD_SLAB, DnDWoodBlocks.OAK_WOOD_WALL,
            Blocks.OAK_LOG, Blocks.OAK_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.SPRUCE_WOOD_STAIRS, DnDWoodBlocks.SPRUCE_WOOD_SLAB, DnDWoodBlocks.SPRUCE_WOOD_WALL,
            Blocks.SPRUCE_LOG, Blocks.SPRUCE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.BIRCH_WOOD_STAIRS, DnDWoodBlocks.BIRCH_WOOD_SLAB, DnDWoodBlocks.BIRCH_WOOD_WALL,
            Blocks.BIRCH_LOG, Blocks.BIRCH_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.JUNGLE_WOOD_STAIRS, DnDWoodBlocks.JUNGLE_WOOD_SLAB, DnDWoodBlocks.JUNGLE_WOOD_WALL,
            Blocks.JUNGLE_LOG, Blocks.JUNGLE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.ACACIA_WOOD_STAIRS, DnDWoodBlocks.ACACIA_WOOD_SLAB, DnDWoodBlocks.ACACIA_WOOD_WALL,
            Blocks.ACACIA_LOG, Blocks.ACACIA_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.DARK_OAK_WOOD_STAIRS, DnDWoodBlocks.DARK_OAK_WOOD_SLAB, DnDWoodBlocks.DARK_OAK_WOOD_WALL,
            Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.MANGROVE_WOOD_STAIRS, DnDWoodBlocks.MANGROVE_WOOD_SLAB, DnDWoodBlocks.MANGROVE_WOOD_WALL,
            Blocks.MANGROVE_LOG, Blocks.MANGROVE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.CHERRY_WOOD_STAIRS, DnDWoodBlocks.CHERRY_WOOD_SLAB, DnDWoodBlocks.CHERRY_WOOD_WALL,
            Blocks.CHERRY_LOG, Blocks.CHERRY_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.CASCADE_WOOD_STAIRS, DnDWoodBlocks.CASCADE_WOOD_SLAB, DnDWoodBlocks.CASCADE_WOOD_WALL,
            DnDWoodBlocks.CASCADE_LOG, DnDWoodBlocks.CASCADE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.GALLERY_MAPLE_WOOD_STAIRS,
            DnDWoodBlocks.GALLERY_MAPLE_WOOD_SLAB,
            DnDWoodBlocks.GALLERY_MAPLE_WOOD_WALL,
            DnDWoodBlocks.GALLERY_MAPLE_LOG,
            DnDWoodBlocks.GALLERY_MAPLE_WOOD
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.CRIMSON_HYPHAE_STAIRS, DnDWoodBlocks.CRIMSON_HYPHAE_SLAB, DnDWoodBlocks.CRIMSON_HYPHAE_WALL,
            Blocks.CRIMSON_STEM, Blocks.CRIMSON_HYPHAE
        )
        gen.genPsudoFamily(
            DnDWoodBlocks.WARPED_HYPHAE_STAIRS, DnDWoodBlocks.WARPED_HYPHAE_SLAB, DnDWoodBlocks.WARPED_HYPHAE_WALL,
            Blocks.WARPED_STEM, Blocks.WARPED_HYPHAE
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