package org.teamvoided.dusk_autumn.data.gen.providers.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.TexturedModel
import org.teamvoided.dusk_autumn.DusksAndDungeons
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDStoneBlocks
import org.teamvoided.dusk_autumn.util.*

object StoneModels {
    fun stoneModels(gen: BlockStateModelGenerator) {

        gen.registerGravestone(DnDStoneBlocks.GRAVESTONE, DnDStoneBlocks.SMALL_GRAVESTONE)
        gen.registerGravestone(DnDStoneBlocks.DEEPSLATE_GRAVESTONE, DnDStoneBlocks.SMALL_DEEPSLATE_GRAVESTONE)
        gen.registerGravestone(DnDStoneBlocks.TUFF_GRAVESTONE, DnDStoneBlocks.SMALL_TUFF_GRAVESTONE)
        gen.registerGravestone(DnDStoneBlocks.BLACKSTONE_GRAVESTONE, DnDStoneBlocks.SMALL_BLACKSTONE_GRAVESTONE)
        gen.registerHeadstone(DnDStoneBlocks.HEADSTONE)
        gen.registerBunnyGrave(DnDStoneBlocks.BUNNY_GRAVE, Blocks.SMOOTH_STONE, Blocks.STONE)

        gen.registerAxisRotated(
            DnDStoneBlocks.STONE_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerAxisRotated(
            DnDStoneBlocks.DEEPSLATE_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        val mossyPolish = DusksAndDungeons.id("block/overgrown/polished_overlay")
        val mossyCobble = DusksAndDungeons.id("block/overgrown/cobblestone_overlay")
        val mossyBrick = DusksAndDungeons.id("block/overgrown/bricks_overlay")
        gen.registerTintedOverlay(mossyPolish)
        gen.registerTintedOverlay(mossyCobble)
        gen.registerTintedOverlay(mossyBrick)
        gen.cubeAllWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE,
            DnDStoneBlocks.MOSSY_POLISHED_STONE,
            mossyPolish
        )
        gen.stairsWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE_STAIRS,
            DnDStoneBlocks.MOSSY_POLISHED_STONE,
            mossyPolish
        )
        gen.slabWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE_SLAB,
            DnDStoneBlocks.MOSSY_POLISHED_STONE,
            mossyPolish
        )
        gen.wallWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE_WALL,
            DnDStoneBlocks.MOSSY_POLISHED_STONE,
            mossyPolish
        )
        gen.cubeAllWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.stairsWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.slabWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.wallWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.cubeAllWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.stairsWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.slabWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.wallWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICKS, mossyBrick)

    }
}