package org.teamvoided.dusk_autumn.data.gen.providers.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.TexturedModel
import net.minecraft.state.property.Properties
import org.teamvoided.dusk_autumn.DusksAndDungeons
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDNetherBrickBlocks
import org.teamvoided.dusk_autumn.util.*

object NetherModels {
    fun netherModels(gen: BlockStateModelGenerator){
        DnDItemLists.blackstoneTools.forEach {
            gen.registerHandheldItem(it)
        }
        gen.genPsudoFamily(
            DnDNetherBrickBlocks.NETHERRACK_STAIRS,
            DnDNetherBrickBlocks.NETHERRACK_SLAB,
            DnDNetherBrickBlocks.NETHERRACK_WALL,
            Blocks.NETHERRACK
        )
        gen.registerCropWithParent(
            DnDFloraBlocks.WARPED_WART,
            DusksAndDungeons.id("block/parent/crop"), Properties.AGE_3, 0, 1, 1, 2)
        gen.registerAxisRotated(
            DnDNetherBrickBlocks.NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerSimpleCubeAll(DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS)
        gen.registerSimpleCubeAll(DnDNetherBrickBlocks.CHISELED_RED_NETHER_BRICKS)
        gen.fence(DnDNetherBrickBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS)
        gen.fence(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar(
            DnDNetherBrickBlocks.MIXED_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.RED_NETHER_BRICK_PILLAR
        )
        gen.registerAxisRotated(
            DnDNetherBrickBlocks.RED_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.BLUE_NETHER_BRICKS)
        gen.registerAxisRotated(
            DnDNetherBrickBlocks.BLUE_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar(
            DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.BLUE_NETHER_BRICK_PILLAR
        )
        gen.fence(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.GRAY_NETHER_BRICKS)
        gen.registerAxisRotated(
            DnDNetherBrickBlocks.GRAY_NETHER_BRICK_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.fence(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS)
        gen.registerMixedNetherBrickPillar(
            DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.GRAY_NETHER_BRICK_PILLAR
        )
    }
}