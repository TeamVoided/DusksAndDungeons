package org.teamvoided.dusks_and_dungeons.datagen.old.models

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers.fence
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.DnDItemLists
import org.teamvoided.dusks_and_dungeons.util.datagen.registerCropWithParent
import org.teamvoided.dusks_and_dungeons.util.datagen.registerHandheldItem

object NetherModels {
    fun netherModels(gen: BlockModelGenerators) {
        DnDItemLists.blackstoneTools.forEach { gen.registerHandheldItem(it) }

        gen.registerCropWithParent(
            DnDBlocks.WARPED_WART,
            DusksAndDungeons.id("block/parent/crop"), BlockStateProperties.AGE_3, 0, 1, 1, 2
        )
        gen.createRotatedPillarWithHorizontalVariant(
            DnDBlocks.NETHER_BRICK_PILLAR,
            TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT
        )

        gen.createTrivialCube(DnDBlocks.CRACKED_RED_NETHER_BRICKS)
        gen.createTrivialCube(DnDBlocks.CRACKED_BLUE_NETHER_BRICKS)
        gen.createTrivialCube(DnDBlocks.CRACKED_GRAY_NETHER_BRICKS)

        gen.createTrivialCube(DnDBlocks.CHISELED_RED_NETHER_BRICKS)
        gen.createTrivialCube(DnDBlocks.CHISELED_BLUE_NETHER_BRICKS)
        gen.createTrivialCube(DnDBlocks.CHISELED_GRAY_NETHER_BRICKS)

        gen.fence(DnDBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS)
        gen.createRotatedPillarWithHorizontalVariant(
            DnDBlocks.RED_NETHER_BRICK_PILLAR,
            TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT
        )
        gen.fence(DnDBlocks.BLUE_NETHER_BRICK_FENCE, DnDBlocks.BLUE_NETHER_BRICKS.parent)
        gen.createRotatedPillarWithHorizontalVariant(
            DnDBlocks.BLUE_NETHER_BRICK_PILLAR,
            TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT
        )
        gen.fence(DnDBlocks.GRAY_NETHER_BRICK_FENCE, DnDBlocks.GRAY_NETHER_BRICKS.parent)
        gen.createRotatedPillarWithHorizontalVariant(
            DnDBlocks.GRAY_NETHER_BRICK_PILLAR,
            TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT
        )

        gen.createTrivialCube(DnDBlocks.MOLTEN_LAVASPONGE)
        gen.createTrivialCube(DnDBlocks.BRITTLE_LAVASPONGE)
        gen.createTrivialCube(DnDBlocks.FUSED_LAVASPONGE)
        gen.createTrivialCube(DnDBlocks.GLOWING_LAVASPONGE)
        gen.createTrivialCube(DnDBlocks.LAVASPONGE)
    }
}