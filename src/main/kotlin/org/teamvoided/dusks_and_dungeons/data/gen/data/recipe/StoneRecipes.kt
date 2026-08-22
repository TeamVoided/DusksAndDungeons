package org.teamvoided.dusks_and_dungeons.data.gen.data.recipe

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.data.gen.data.recipe.helpers.createChiseled
import org.teamvoided.dusks_and_dungeons.data.gen.data.recipe.helpers.cutChiseled
import org.teamvoided.dusks_and_dungeons.data.gen.data.recipe.helpers.smeltCracked
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecutting
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecuttingSet

object StoneRecipes {

    fun generateStoneRecipes(output: RecipeOutput) {
        // Pillars
        output.createStackedCraft(DnDBlocks.STONE_PILLAR, Blocks.STONE_BRICKS, ItemTags.STONE_BRICKS)
        output.createStonecuttedFromList(
            DnDBlocks.STONE_PILLAR,
            Blocks.STONE, Blocks.STONE_BRICKS, DnDBlocks.POLISHED_STONE.parent
        )
        output.createStackedCraft(DnDBlocks.DEEPSLATE_PILLAR, Blocks.DEEPSLATE_BRICKS)
        output.createStonecuttedFromList(
            DnDBlocks.DEEPSLATE_PILLAR,
            Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE_BRICKS, Blocks.POLISHED_DEEPSLATE,
        )

        // Polished
        output.createSmallSquare(DnDBlocks.POLISHED_STONE, Blocks.STONE, 4)

        output.createSmallSquare(Blocks.MOSSY_STONE_BRICKS, DnDBlocks.MOSSY_POLISHED_STONE, 4)
        output.createSmallSquare(DnDBlocks.OVERGROWN_STONE_BRICKS, DnDBlocks.OVERGROWN_POLISHED_STONE, 4)

        output.createTwoPiece(DnDBlocks.MOSSY_POLISHED_STONE, DnDBlocks.POLISHED_STONE, Items.MOSS_BLOCK, "_from_moss")
        output.createTwoPiece(DnDBlocks.MOSSY_POLISHED_STONE, DnDBlocks.POLISHED_STONE, Items.VINE, "_from_vine")

        output.createStonecuttingSet(DnDBlocks.POLISHED_STONE, Blocks.STONE)
        output.cutVariants(
            DnDBlocks.POLISHED_STONE,
            Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_WALL
        )
        output.cutVariants(
            DnDBlocks.MOSSY_POLISHED_STONE,
            Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_WALL
        )

        // Overgrown
        output.createOvergrown(DnDBlocks.OVERGROWN_POLISHED_STONE, DnDBlocks.POLISHED_STONE)
        output.createOvergrown(DnDBlocks.OVERGROWN_COBBLESTONE, Blocks.COBBLESTONE)
        output.createOvergrown(DnDBlocks.OVERGROWN_STONE_BRICKS, Blocks.STONE_BRICKS)
        output.cutVariants(
            DnDBlocks.OVERGROWN_POLISHED_STONE,
            DnDBlocks.OVERGROWN_STONE_BRICKS.stairs,
            DnDBlocks.OVERGROWN_STONE_BRICKS.slab,
            DnDBlocks.OVERGROWN_STONE_BRICKS.wall
        )

        // Bricks
        output.createChiseled(DnDBlocks.CHISELED_BRICKS, Blocks.BRICK_SLAB, Blocks.BRICKS)
        output.cutChiseled(DnDBlocks.CHISELED_BRICKS, Blocks.BRICKS)
        output.smeltCracked(DnDBlocks.CRACKED_BRICKS.parent, Blocks.BRICKS)

        // Gravestones
        output.createGravestones(DnDBlocks.STONE_GRAVESTONE, DnDBlocks.SMALL_STONE_GRAVESTONE, Blocks.STONE_BRICKS)
        output.createGravestones(
            DnDBlocks.DEEPSLATE_GRAVESTONE, DnDBlocks.SMALL_DEEPSLATE_GRAVESTONE, Blocks.DEEPSLATE_BRICKS
        )
        output.createGravestones(DnDBlocks.TUFF_GRAVESTONE, DnDBlocks.SMALL_TUFF_GRAVESTONE, Blocks.TUFF_BRICKS)
        output.createGravestones(
            DnDBlocks.BLACKSTONE_GRAVESTONE, DnDBlocks.SMALL_BLACKSTONE_GRAVESTONE, Blocks.POLISHED_BLACKSTONE_BRICKS
        )
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DnDBlocks.HEADSTONE)
            .define('#', Items.IRON_BARS)
            .define('%', Items.IRON_NUGGET)
            .pattern("%#%")
            .pattern("###")
            .pattern("%#%")
            .criterion(Items.IRON_BARS)
            .criterion(DnDItemTags.GRAVESTONES)
            .save(output)
    }

    fun RecipeOutput.cutVariants(input: ItemLike, stair: Block, slab: Block, wall: Block) {
        createStonecutting(stair, input)
        createStonecutting(slab, input, 2)
        createStonecutting(wall, input)
    }

}