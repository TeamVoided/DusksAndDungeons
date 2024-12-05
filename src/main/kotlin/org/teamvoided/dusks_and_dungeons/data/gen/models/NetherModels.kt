package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.TexturedModel
import net.minecraft.state.property.Properties
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.*
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object NetherModels {
    fun netherModels(gen: BlockStateModelGenerator){
        DnDItemLists.blackstoneTools.forEach { gen.registerHandheldItem(it) }

        gen.registerCropWithParent(
            DnDBlocks.WARPED_WART,
            DusksAndDungeons.id("block/parent/crop"), Properties.AGE_3, 0, 1, 1, 2)
        gen.registerAxisRotated(
            DnDBlocks.NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )

        gen.registerSimpleCubeAll(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CRACKED_MIXED_RED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CRACKED_BLUE_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CRACKED_GRAY_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS)

        gen.registerSimpleCubeAll(DnDBlocks.CHISELED_RED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CHISELED_MIXED_RED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CHISELED_BLUE_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CHISELED_GRAY_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS)

        gen.fence(DnDBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS)
        gen.fence(DnDBlocks.MIXED_RED_NETHER_BRICK_FENCE, DnDBlocks.MIXED_RED_NETHER_BRICKS.parent)
        gen.registerMixedNetherBrickPillar(DnDBlocks.MIXED_RED_NETHER_BRICK_PILLAR, DnDBlocks.RED_NETHER_BRICK_PILLAR)
        gen.registerAxisRotated(
            DnDBlocks.RED_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDBlocks.BLUE_NETHER_BRICK_FENCE, DnDBlocks.BLUE_NETHER_BRICKS.parent)
        gen.registerAxisRotated(
            DnDBlocks.BLUE_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDBlocks.MIXED_BLUE_NETHER_BRICKS.parent)
        gen.registerMixedNetherBrickPillar(DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR, DnDBlocks.BLUE_NETHER_BRICK_PILLAR)
        gen.fence(DnDBlocks.GRAY_NETHER_BRICK_FENCE, DnDBlocks.GRAY_NETHER_BRICKS.parent)
        gen.registerAxisRotated(
            DnDBlocks.GRAY_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDBlocks.MIXED_GRAY_NETHER_BRICKS.parent)
        gen.registerMixedNetherBrickPillar(DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR, DnDBlocks.GRAY_NETHER_BRICK_PILLAR)
    }
}