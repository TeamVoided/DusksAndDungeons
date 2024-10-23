package org.teamvoided.dusk_autumn.data.gen.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusk_autumn.data.tags.DnDItemTags
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDNetherBrickBlocks
import org.teamvoided.dusk_autumn.util.*

object NetherRecipes {
    fun generateNetherRecipes(e:RecipeExporter){
        generateMiscNetherRecipes(e)
        generateNetherBrickRecipes(e)
    }
    private fun generateMiscNetherRecipes(e:RecipeExporter){
    }
    private fun generateNetherBrickRecipes(e: RecipeExporter) {
        e.createStackedCraft(DnDNetherBrickBlocks.NETHER_BRICK_PILLAR, Blocks.NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.NETHERRACK),
            null,
            DnDNetherBrickBlocks.NETHERRACK_STAIRS,
            DnDNetherBrickBlocks.NETHERRACK_SLAB,
            DnDNetherBrickBlocks.NETHERRACK_WALL
        )
        e.createStonecuttedSet(
            listOf(Blocks.NETHER_BRICKS, DnDNetherBrickBlocks.POLISHED_NETHER_BRICKS),
            DnDNetherBrickBlocks.POLISHED_NETHER_BRICKS,
            DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_STAIRS,
            DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_SLAB,
            DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_WALL,
        )
        e.createStonecuttedSet(
            listOf(Blocks.NETHER_BRICKS),
            DnDNetherBrickBlocks.NETHER_BRICK_PILLAR, null, null,
            Blocks.NETHER_BRICK_FENCE
        )
        FabricRecipeProvider.offerCrackingRecipe(e, DnDNetherBrickBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DnDNetherBrickBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK)
        FabricRecipeProvider.createChiseledBlockRecipe(
            RecipeCategory.BUILDING_BLOCKS,
            DnDNetherBrickBlocks.CHISELED_RED_NETHER_BRICKS,
            Ingredient.ofItems(Blocks.RED_NETHER_BRICK_SLAB)
        ).criterion(Blocks.RED_NETHER_BRICKS).offerTo(e)
        e.createStackedCraft(DnDNetherBrickBlocks.RED_NETHER_BRICK_PILLAR, Blocks.RED_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.RED_NETHER_BRICKS),
            DnDNetherBrickBlocks.RED_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.CHISELED_RED_NETHER_BRICKS, null,
            DnDNetherBrickBlocks.RED_NETHER_BRICK_FENCE
        )
        e.createDiagonalRecipe(DnDNetherBrickBlocks.MIXED_NETHER_BRICKS, Blocks.NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDNetherBrickBlocks.MIXED_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.MIXED_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(Blocks.RED_NETHER_BRICKS, DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICKS),
            DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICKS,
            DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_STAIRS,
            DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_SLAB,
            DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_WALL
        )
        e.createStonecuttedSet(
            listOf(DnDNetherBrickBlocks.MIXED_NETHER_BRICKS),
            null,
            DnDNetherBrickBlocks.MIXED_NETHER_BRICK_STAIRS,
            DnDNetherBrickBlocks.MIXED_NETHER_BRICK_SLAB,
            DnDNetherBrickBlocks.MIXED_NETHER_BRICK_WALL,
            listOf(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_NETHER_BRICK_PILLAR)
        )
        e.createDiagonalRecipe(
            DnDNetherBrickBlocks.BLUE_NETHER_BRICKS,
            DnDItemTags.CRAFTS_WARPED_NETHER_BRICKS,
            Blocks.NETHER_BRICKS
        )
        e.createFence(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.BLUE_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDNetherBrickBlocks.BLUE_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.BLUE_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DnDNetherBrickBlocks.BLUE_NETHER_BRICKS),
            null,
            DnDNetherBrickBlocks.BLUE_NETHER_BRICK_STAIRS,
            DnDNetherBrickBlocks.BLUE_NETHER_BRICK_SLAB,
            DnDNetherBrickBlocks.BLUE_NETHER_BRICK_WALL,
            listOf(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.BLUE_NETHER_BRICK_PILLAR)
        )
        e.createStonecuttedSet(
            listOf(DnDNetherBrickBlocks.BLUE_NETHER_BRICKS, DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICKS),
            DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICKS,
            DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS,
            DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB,
            DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_WALL
        )
        e.createDiagonalRecipe(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS, DnDNetherBrickBlocks.BLUE_NETHER_BRICKS, Blocks.NETHER_BRICKS)
        e.createFence(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS),
            null,
            DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS,
            DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_SLAB,
            DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_WALL,
            listOf(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_PILLAR)
        )
        e.createDiagonalRecipe(
            DnDNetherBrickBlocks.GRAY_NETHER_BRICKS,
            DnDItemTags.CRAFTS_ASHEN_NETHER_BRICKS,
            Blocks.NETHER_BRICKS

        )
        e.createFence(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.GRAY_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDNetherBrickBlocks.GRAY_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.GRAY_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DnDNetherBrickBlocks.GRAY_NETHER_BRICKS),
            null,
            DnDNetherBrickBlocks.GRAY_NETHER_BRICK_STAIRS,
            DnDNetherBrickBlocks.GRAY_NETHER_BRICK_SLAB,
            DnDNetherBrickBlocks.GRAY_NETHER_BRICK_WALL,
            listOf(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.GRAY_NETHER_BRICK_PILLAR)
        )
        e.createStonecuttedSet(
            listOf(DnDNetherBrickBlocks.GRAY_NETHER_BRICKS, DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICKS),
            DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICKS,
            DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS,
            DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB,
            DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_WALL
        )
        e.createDiagonalRecipe(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS, Blocks.NETHER_BRICKS, DnDNetherBrickBlocks.GRAY_NETHER_BRICKS)
        e.createFence(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR,
            DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS,
            DnDItemTags.NETHER_BRICKS
        )
        e.createStonecuttedSet(
            listOf(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS),
            null,
            DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS,
            DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_SLAB,
            DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_WALL,
            listOf(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_FENCE, DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_PILLAR)
        )
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