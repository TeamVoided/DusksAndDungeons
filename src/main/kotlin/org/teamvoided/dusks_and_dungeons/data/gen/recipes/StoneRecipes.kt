package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecutting
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecuttingSet

object StoneRecipes {
    fun generateStoneRecipes(e: RecipeOutput) {
        e.createGragestones(DnDBlocks.STONE_GRAVESTONE, DnDBlocks.SMALL_STONE_GRAVESTONE, Blocks.STONE_BRICKS)
        e.createGragestones(
            DnDBlocks.DEEPSLATE_GRAVESTONE, DnDBlocks.SMALL_DEEPSLATE_GRAVESTONE, Blocks.DEEPSLATE_BRICKS
        )
        e.createGragestones(DnDBlocks.TUFF_GRAVESTONE, DnDBlocks.SMALL_TUFF_GRAVESTONE, Blocks.TUFF_BRICKS)
        e.createGragestones(
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
            .save(e)

        e.createStackedCraft(DnDBlocks.STONE_PILLAR, Blocks.STONE_BRICKS, ItemTags.STONE_BRICKS)
        e.createStackedCraft(DnDBlocks.DEEPSLATE_PILLAR, Blocks.DEEPSLATE_BRICKS)
        e.createStonecuttedFromList(
            DnDBlocks.STONE_PILLAR,
            Blocks.STONE, Blocks.STONE_BRICKS, DnDBlocks.POLISHED_STONE.parent
        )
        e.createStonecuttedFromList(
            DnDBlocks.DEEPSLATE_PILLAR,
            Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE_BRICKS, Blocks.POLISHED_DEEPSLATE,
        )
        e.createSmallSquare(DnDBlocks.POLISHED_STONE, Blocks.STONE, 4)

        e.createSmallSquare(Blocks.MOSSY_STONE_BRICKS, DnDBlocks.MOSSY_POLISHED_STONE, 4)
        e.createSmallSquare(DnDBlocks.OVERGROWN_STONE_BRICKS, DnDBlocks.OVERGROWN_POLISHED_STONE, 4)

        e.createTwoPiece(DnDBlocks.MOSSY_POLISHED_STONE, DnDBlocks.POLISHED_STONE, Items.MOSS_BLOCK, "_from_moss")
        e.createTwoPiece(DnDBlocks.MOSSY_POLISHED_STONE, DnDBlocks.POLISHED_STONE, Items.VINE, "_from_vine")

        e.createOvergrown(DnDBlocks.OVERGROWN_POLISHED_STONE, DnDBlocks.POLISHED_STONE)
        e.createOvergrown(DnDBlocks.OVERGROWN_COBBLESTONE, Blocks.COBBLESTONE)
        e.createOvergrown(DnDBlocks.OVERGROWN_STONE_BRICKS, Blocks.STONE_BRICKS)

        e.createStonecuttingSet(DnDBlocks.POLISHED_STONE, Blocks.STONE)

        e.cutVariants(
            DnDBlocks.POLISHED_STONE,
            Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_WALL
        )

        e.cutVariants(
            DnDBlocks.OVERGROWN_POLISHED_STONE,
            DnDBlocks.OVERGROWN_STONE_BRICKS.stairs,
            DnDBlocks.OVERGROWN_STONE_BRICKS.slab,
            DnDBlocks.OVERGROWN_STONE_BRICKS.wall
        )

        e.cutVariants(
            DnDBlocks.MOSSY_POLISHED_STONE,
            Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_WALL
        )
//        e.createStonecuttingSet(DnDBlocks.MOSSY_POLISHED_STONE, Blocks.MOSSY_STONE_BRICKS)
//        e.createStonecuttingSet(DnDBlocks.OVERGROWN_POLISHED_STONE, DnDBlocks.OVERGROWN_STONE_BRICKS.parent)
    }

    fun RecipeOutput.cutVariants(input: ItemLike, stair: Block, slab: Block, wall: Block) {
        createStonecutting(stair, input)
        createStonecutting(slab, input, 2)
        createStonecutting(wall, input)
    }
}