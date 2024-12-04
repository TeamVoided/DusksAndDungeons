package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecutting
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecuttingSet

object NetherRecipes {
    fun generateNetherRecipes(e: RecipeExporter) {
        generateMiscNetherRecipes(e)
        generateNetherBrickRecipes(e)
    }

    private fun generateMiscNetherRecipes(e: RecipeExporter) {
    }

    private fun generateNetherBrickRecipes(e: RecipeExporter) {
        e.createStackedCraft(DnDBlocks.NETHER_BRICK_PILLAR, Blocks.NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)

        e.createStonecuttingSet(DnDBlocks.POLISHED_NETHER_BRICKS, Blocks.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.NETHER_BRICKS),
            DnDBlocks.NETHER_BRICK_PILLAR, null, null,
            Blocks.NETHER_BRICK_FENCE
        )
        FabricRecipeProvider.offerCrackingRecipe(e, DnDBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DnDBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK)
        FabricRecipeProvider.createChiseledBlockRecipe(
            RecipeCategory.BUILDING_BLOCKS,
            DnDBlocks.CHISELED_RED_NETHER_BRICKS,
            Ingredient.ofItems(Blocks.RED_NETHER_BRICK_SLAB)
        ).criterion(Blocks.RED_NETHER_BRICKS).offerTo(e)
        e.createStackedCraft(DnDBlocks.RED_NETHER_BRICK_PILLAR, Blocks.RED_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.RED_NETHER_BRICKS),
            DnDBlocks.RED_NETHER_BRICK_PILLAR,
            DnDBlocks.CHISELED_RED_NETHER_BRICKS, null,
            DnDBlocks.RED_NETHER_BRICK_FENCE
        )
        e.createDiagonalRecipe(DnDBlocks.MIXED_RED_NETHER_BRICKS, Blocks.NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DnDBlocks.MIXED_RED_NETHER_BRICK_FENCE, DnDBlocks.MIXED_RED_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.MIXED_RED_NETHER_BRICK_PILLAR, DnDBlocks.MIXED_RED_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS
        )

        e.createStonecuttingSet(DnDBlocks.POLISHED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)

        e.createStonecutting(DnDBlocks.MIXED_RED_NETHER_BRICK_FENCE, DnDBlocks.MIXED_RED_NETHER_BRICKS)
        e.createStonecutting(DnDBlocks.MIXED_RED_NETHER_BRICK_PILLAR, DnDBlocks.MIXED_RED_NETHER_BRICKS)

        e.createDiagonalRecipe(
            DnDBlocks.BLUE_NETHER_BRICKS, DnDItemTags.CRAFTS_WARPED_NETHER_BRICKS, Blocks.NETHER_BRICKS
        )
        e.createFence(DnDBlocks.BLUE_NETHER_BRICK_FENCE, DnDBlocks.BLUE_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.BLUE_NETHER_BRICK_PILLAR, DnDBlocks.BLUE_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS
        )
        e.createStonecutting(DnDBlocks.BLUE_NETHER_BRICK_FENCE, DnDBlocks.BLUE_NETHER_BRICKS)
        e.createStonecutting(DnDBlocks.BLUE_NETHER_BRICK_PILLAR, DnDBlocks.BLUE_NETHER_BRICKS)

        e.createStonecuttingSet(DnDBlocks.POLISHED_BLUE_NETHER_BRICKS, DnDBlocks.BLUE_NETHER_BRICKS.parent)

        e.createDiagonalRecipe(DnDBlocks.MIXED_BLUE_NETHER_BRICKS, DnDBlocks.BLUE_NETHER_BRICKS, Blocks.NETHER_BRICKS)
        e.createFence(DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDBlocks.MIXED_BLUE_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR, DnDBlocks.MIXED_BLUE_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS
        )
        e.createStonecutting(DnDBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDBlocks.MIXED_BLUE_NETHER_BRICKS)
        e.createStonecutting(DnDBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR, DnDBlocks.MIXED_BLUE_NETHER_BRICKS)

        e.createDiagonalRecipe(
            DnDBlocks.GRAY_NETHER_BRICKS, DnDItemTags.CRAFTS_ASHEN_NETHER_BRICKS, Blocks.NETHER_BRICKS
        )
        e.createFence(DnDBlocks.GRAY_NETHER_BRICK_FENCE, DnDBlocks.GRAY_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.GRAY_NETHER_BRICK_PILLAR, DnDBlocks.GRAY_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS
        )

        e.createStonecutting(DnDBlocks.GRAY_NETHER_BRICK_FENCE, DnDBlocks.GRAY_NETHER_BRICKS)
        e.createStonecutting(DnDBlocks.GRAY_NETHER_BRICK_PILLAR, DnDBlocks.GRAY_NETHER_BRICKS)

        e.createStonecuttingSet(DnDBlocks.POLISHED_GRAY_NETHER_BRICKS, DnDBlocks.GRAY_NETHER_BRICKS.parent)

        e.createDiagonalRecipe(DnDBlocks.MIXED_GRAY_NETHER_BRICKS, Blocks.NETHER_BRICKS, DnDBlocks.GRAY_NETHER_BRICKS)
        e.createFence(DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDBlocks.MIXED_GRAY_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR, DnDBlocks.MIXED_GRAY_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS
        )
        e.createStonecutting(DnDBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDBlocks.MIXED_GRAY_NETHER_BRICKS)
        e.createStonecutting(DnDBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR, DnDBlocks.MIXED_GRAY_NETHER_BRICKS)

        ShapedRecipeJsonFactory.create(RecipeCategory.COMBAT, DnDItems.BLACKSTONE_SWORD)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("X")
            .pattern("X")
            .pattern("#")
            .criterion(Items.BLACKSTONE).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_PICKAXE)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("XXX")
            .pattern(" # ")
            .pattern(" # ")
            .criterion(Items.BLACKSTONE).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_AXE)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("XX")
            .pattern("X#")
            .pattern(" #")
            .criterion(Items.BLACKSTONE).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_SHOVEL)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("X")
            .pattern("#").pattern("#")
            .criterion(Items.BLACKSTONE).offerTo(e)
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_HOE)
            .ingredient('#', Items.STICK)
            .ingredient('X', Items.BLACKSTONE)
            .pattern("XX")
            .pattern(" #")
            .pattern(" #")
            .criterion(Items.BLACKSTONE).offerTo(e)
    }

}