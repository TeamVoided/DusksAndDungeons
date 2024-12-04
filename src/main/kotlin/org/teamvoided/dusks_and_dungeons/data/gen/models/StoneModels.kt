package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.TexturedModel
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDStoneBlocks
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object StoneModels {
    fun stoneModels(gen: BlockStateModelGenerator) {

        gen.registerGravestones(
            DnDStoneBlocks.STONE_GRAVESTONE,
            DnDStoneBlocks.SMALL_STONE_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_GRAVESTONE
        )
        gen.registerGravestones(
            DnDStoneBlocks.DEEPSLATE_GRAVESTONE,
            DnDStoneBlocks.SMALL_DEEPSLATE_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_DEEPSLATE_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_DEEPSLATE_GRAVESTONE
        )
        gen.registerGravestones(
            DnDStoneBlocks.TUFF_GRAVESTONE,
            DnDStoneBlocks.SMALL_TUFF_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_TUFF_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_TUFF_GRAVESTONE
        )
        gen.registerGravestones(
            DnDStoneBlocks.BLACKSTONE_GRAVESTONE,
            DnDStoneBlocks.SMALL_BLACKSTONE_GRAVESTONE,
            DnDStoneBlocks.HAUNTED_BLACKSTONE_GRAVESTONE,
            DnDStoneBlocks.SMALL_HAUNTED_BLACKSTONE_GRAVESTONE
        )

        gen.registerHeadstone(DnDStoneBlocks.HEADSTONE)
        gen.registerBunnyGrave(DnDStoneBlocks.BUNNY_GRAVE, Blocks.SMOOTH_STONE, Blocks.STONE)

        gen.registerAxisRotated(
            DnDStoneBlocks.STONE_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerAxisRotated(
            DnDStoneBlocks.DEEPSLATE_PILLAR,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        val mossyPolish = DusksAndDungeons.id("block/overgrown/polished_overlay")
        val mossyCobble = DusksAndDungeons.id("block/overgrown/cobblestone_overlay")
        val mossyBrick = DusksAndDungeons.id("block/overgrown/bricks_overlay")
        gen.registerTintedOverlay(mossyPolish)
        gen.registerTintedOverlay(mossyCobble)
        gen.registerTintedOverlay(mossyBrick)
        //Polished Stone
        gen.cubeAllWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE.parent, DnDStoneBlocks.MOSSY_POLISHED_STONE.parent, mossyPolish
        )
        gen.stairsWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE.stairs, DnDStoneBlocks.MOSSY_POLISHED_STONE.parent, mossyPolish
        )
        gen.slabWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE.slab, DnDStoneBlocks.MOSSY_POLISHED_STONE.parent, mossyPolish
        )
        gen.wallWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_POLISHED_STONE.wall, DnDStoneBlocks.MOSSY_POLISHED_STONE.parent, mossyPolish
        )
        //Cobblestone
        gen.cubeAllWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE.parent, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.stairsWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE.stairs, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.slabWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE.slab, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.wallWithTintedOverlay(DnDStoneBlocks.OVERGROWN_COBBLESTONE.wall, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        //Stone Bricks
        gen.cubeAllWithTintedOverlay(
            DnDStoneBlocks.OVERGROWN_STONE_BRICKS.parent, Blocks.MOSSY_STONE_BRICKS, mossyBrick
        )
        gen.stairsWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICKS.stairs, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.slabWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICKS.slab, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.wallWithTintedOverlay(DnDStoneBlocks.OVERGROWN_STONE_BRICKS.wall, Blocks.MOSSY_STONE_BRICKS, mossyBrick)

    }
}