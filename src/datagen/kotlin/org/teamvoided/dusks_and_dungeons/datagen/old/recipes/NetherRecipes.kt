package org.teamvoided.dusks_and_dungeons.datagen.old.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider.getItemName
import net.minecraft.data.recipes.RecipeProvider.has
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecutting
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecuttingSet

object NetherRecipes {
    fun generateNetherRecipes(o: RecipeOutput) {
        generateNetherBrickRecipes(o)

        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(Items.NETHERITE_SCRAP),
            Ingredient.of(DnDBlocks.BRITTLE_LAVASPONGE),
            Ingredient.of(),
            RecipeCategory.BUILDING_BLOCKS,
            DnDItems.LAVASPONGE
        )
            .unlocks("has_netherite_scrap", has(Items.NETHERITE_SCRAP))
            .save(o, getItemName(DnDItems.LAVASPONGE) + "_smithing")
    }

    fun generateNetherBrickRecipes(e: RecipeOutput) {
        e.createStackedCraft(DnDBlocks.NETHER_BRICK_PILLAR, Blocks.NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)

        e.createStonecuttingSet(DnDBlocks.POLISHED_NETHER_BRICKS, Blocks.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.NETHER_BRICKS),
            DnDBlocks.NETHER_BRICK_PILLAR, null, null,
            Blocks.NETHER_BRICK_FENCE
        )
        FabricRecipeProvider.smeltingResultFromBase(e, DnDBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)
        e.createFence(DnDBlocks.RED_NETHER_BRICK_FENCE, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK)
        FabricRecipeProvider.chiseledBuilder(
            RecipeCategory.BUILDING_BLOCKS,
            DnDBlocks.CHISELED_RED_NETHER_BRICKS,
            Ingredient.of(Blocks.RED_NETHER_BRICK_SLAB)
        ).criterion(Blocks.RED_NETHER_BRICKS).save(e)
        e.createStackedCraft(DnDBlocks.RED_NETHER_BRICK_PILLAR, Blocks.RED_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS)
        e.createStonecuttedSet(
            listOf(Blocks.RED_NETHER_BRICKS),
            DnDBlocks.RED_NETHER_BRICK_PILLAR,
            DnDBlocks.CHISELED_RED_NETHER_BRICKS, null,
            DnDBlocks.RED_NETHER_BRICK_FENCE
        )

        e.createStonecuttingSet(DnDBlocks.POLISHED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS)

        e.createDiagonalRecipe(
            DnDBlocks.BLUE_NETHER_BRICKS, DnDItemTags.WARPED_NETHER_BRICK_MATERIALS, Blocks.NETHER_BRICKS
        )
        e.createFence(DnDBlocks.BLUE_NETHER_BRICK_FENCE, DnDBlocks.BLUE_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.BLUE_NETHER_BRICK_PILLAR, DnDBlocks.BLUE_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS
        )
        e.createStonecutting(DnDBlocks.BLUE_NETHER_BRICK_FENCE, DnDBlocks.BLUE_NETHER_BRICKS)
        e.createStonecutting(DnDBlocks.BLUE_NETHER_BRICK_PILLAR, DnDBlocks.BLUE_NETHER_BRICKS)

        e.createStonecuttingSet(DnDBlocks.POLISHED_BLUE_NETHER_BRICKS, DnDBlocks.BLUE_NETHER_BRICKS.parent)

        e.createDiagonalRecipe(
            DnDBlocks.GRAY_NETHER_BRICKS, DnDItemTags.ASHEN_NETHER_BRICK_MATERIALS, Blocks.NETHER_BRICKS
        )
        e.createFence(DnDBlocks.GRAY_NETHER_BRICK_FENCE, DnDBlocks.GRAY_NETHER_BRICKS, Items.NETHER_BRICK)
        e.createStackedCraft(
            DnDBlocks.GRAY_NETHER_BRICK_PILLAR, DnDBlocks.GRAY_NETHER_BRICKS, DnDItemTags.NETHER_BRICKS
        )

        e.createStonecutting(DnDBlocks.GRAY_NETHER_BRICK_FENCE, DnDBlocks.GRAY_NETHER_BRICKS)
        e.createStonecutting(DnDBlocks.GRAY_NETHER_BRICK_PILLAR, DnDBlocks.GRAY_NETHER_BRICKS)

        e.createStonecuttingSet(DnDBlocks.POLISHED_GRAY_NETHER_BRICKS, DnDBlocks.GRAY_NETHER_BRICKS.parent)

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DnDItems.BLACKSTONE_SWORD)
            .define('#', Items.STICK)
            .define('X', Items.BLACKSTONE)
            .pattern("X")
            .pattern("X")
            .pattern("#")
            .criterion(Items.BLACKSTONE).save(e)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_PICKAXE)
            .define('#', Items.STICK)
            .define('X', Items.BLACKSTONE)
            .pattern("XXX")
            .pattern(" # ")
            .pattern(" # ")
            .criterion(Items.BLACKSTONE).save(e)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_AXE)
            .define('#', Items.STICK)
            .define('X', Items.BLACKSTONE)
            .pattern("XX")
            .pattern("X#")
            .pattern(" #")
            .criterion(Items.BLACKSTONE).save(e)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_SHOVEL)
            .define('#', Items.STICK)
            .define('X', Items.BLACKSTONE)
            .pattern("X")
            .pattern("#").pattern("#")
            .criterion(Items.BLACKSTONE).save(e)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DnDItems.BLACKSTONE_HOE)
            .define('#', Items.STICK)
            .define('X', Items.BLACKSTONE)
            .pattern("XX")
            .pattern(" #")
            .pattern(" #")
            .criterion(Items.BLACKSTONE).save(e)
    }

}