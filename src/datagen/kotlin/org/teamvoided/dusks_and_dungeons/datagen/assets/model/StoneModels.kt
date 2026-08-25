package org.teamvoided.dusks_and_dungeons.datagen.assets.model

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.datagen.old.util.cubeAllWithTintedOverlay
import org.teamvoided.dusks_and_dungeons.datagen.old.util.registerTintedOverlay
import org.teamvoided.dusks_and_dungeons.datagen.old.util.slabWithTintedOverlay
import org.teamvoided.dusks_and_dungeons.datagen.old.util.stairsWithTintedOverlay
import org.teamvoided.dusks_and_dungeons.datagen.old.util.wallWithTintedOverlay
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.registerGravestones
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.registerHeadstone
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

object StoneModels {

    fun stoneModels(gen: BlockModelGenerators) {
        // Pillars
        gen.createRotatedPillarWithHorizontalVariant(
            DnDBlocks.STONE_PILLAR,
            TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT
        )
        gen.createRotatedPillarWithHorizontalVariant(
            DnDBlocks.DEEPSLATE_PILLAR,
            TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT
        )

        // Overlays?
        val mossyPolish = DusksAndDungeons.id("block/overgrown/polished_overlay")
        val mossyCobble = DusksAndDungeons.id("block/overgrown/cobblestone_overlay")
        val mossyBrick = DusksAndDungeons.id("block/overgrown/bricks_overlay")
        gen.registerTintedOverlay(mossyPolish)
        gen.registerTintedOverlay(mossyCobble)
        gen.registerTintedOverlay(mossyBrick)

        //Polished Stone
        gen.cubeAllWithTintedOverlay(
            DnDBlocks.OVERGROWN_POLISHED_STONE.parent, DnDBlocks.MOSSY_POLISHED_STONE.parent, mossyPolish
        )
        gen.stairsWithTintedOverlay(
            DnDBlocks.OVERGROWN_POLISHED_STONE.stairs, DnDBlocks.MOSSY_POLISHED_STONE.parent, mossyPolish
        )
        gen.slabWithTintedOverlay(
            DnDBlocks.OVERGROWN_POLISHED_STONE.slab, DnDBlocks.MOSSY_POLISHED_STONE.parent, mossyPolish
        )
        gen.wallWithTintedOverlay(
            DnDBlocks.OVERGROWN_POLISHED_STONE.wall, DnDBlocks.MOSSY_POLISHED_STONE.parent, mossyPolish
        )

        // Overgrown
        gen.cubeAllWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE.parent, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.stairsWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE.stairs, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.slabWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE.slab, Blocks.MOSSY_COBBLESTONE, mossyCobble)
        gen.wallWithTintedOverlay(DnDBlocks.OVERGROWN_COBBLESTONE.wall, Blocks.MOSSY_COBBLESTONE, mossyCobble)

        gen.cubeAllWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICKS.parent, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.stairsWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICKS.stairs, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.slabWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICKS.slab, Blocks.MOSSY_STONE_BRICKS, mossyBrick)
        gen.wallWithTintedOverlay(DnDBlocks.OVERGROWN_STONE_BRICKS.wall, Blocks.MOSSY_STONE_BRICKS, mossyBrick)

        // Bricks
        gen.createTrivialCube(DnDBlocks.CHISELED_BRICKS)

        // Gravestones
        gen.registerGravestones(DnDBlocks.STONE_BRICK_GRAVESTONE, DnDBlocks.SMALL_STONE_BRICK_GRAVESTONE)
        gen.registerGravestones(DnDBlocks.DEEPSLATE_BRICK_GRAVESTONE, DnDBlocks.SMALL_DEEPSLATE_BRICK_GRAVESTONE)
        gen.registerGravestones(DnDBlocks.TUFF_BRICK_GRAVESTONE, DnDBlocks.SMALL_TUFF_BRICK_GRAVESTONE)
        gen.registerGravestones(DnDBlocks.BLACKSTONE_BRICK_GRAVESTONE, DnDBlocks.SMALL_BLACKSTONE_BRICK_GRAVESTONE)
        gen.registerHeadstone(DnDBlocks.IRON_HEADSTONE)
    }

}