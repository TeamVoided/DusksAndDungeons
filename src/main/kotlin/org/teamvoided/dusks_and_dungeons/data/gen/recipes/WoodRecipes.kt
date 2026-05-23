package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.world.level.block.Blocks
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.data.recipes.RecipeCategory
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.criterion
import org.teamvoided.dusks_and_dungeons.util.datagen.recipe.woodWall

object WoodRecipes {
    fun generateWoodRecipes(e: RecipeOutput) {
        e.cascade()
        e.woodWalls()
    }

    private fun RecipeOutput.cascade() {
        FabricRecipeProvider.planksFromLogs(this, DnDBlocks.CASCADE_PLANKS, DnDItemTags.CASCADE_LOGS, 4)
        FabricRecipeProvider.woodFromLogs(this, DnDBlocks.CASCADE_WOOD, DnDBlocks.CASCADE_LOG)
        FabricRecipeProvider.woodFromLogs(this, DnDBlocks.STRIPPED_CASCADE_WOOD, DnDBlocks.STRIPPED_CASCADE_LOG)
        FabricRecipeProvider.hangingSign(this, DnDItems.CASCADE_HANGING_SIGN, DnDBlocks.STRIPPED_CASCADE_LOG)
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, DnDBlocks.BLUE_DOOR, 3)
            .define('#', Ingredient.of(DnDBlocks.CASCADE_PLANKS.asItem()))
            .define('@', Ingredient.of(Items.GOLD_NUGGET))
            .pattern("## ")
            .pattern("##@")
            .pattern("## ")
            .criterion(DnDBlocks.CASCADE_PLANKS.asItem())
            .save(this)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DnDBlocks.BIG_SCAFFOLDING, 6)
            .define('|', Ingredient.of(Items.BAMBOO_PLANKS))
            .define('~', Ingredient.of(Items.STRING))
            .pattern("|~|")
            .pattern("| |")
            .pattern("| |")
            .criterion(Items.BAMBOO_PLANKS)
            .save(this)
    }

    private fun RecipeOutput.woodWalls() {
        DnDBlockLists.plankWalls.forEachIndexed { idx, it ->
            this.woodWall(it, DnDBlockLists.planks[idx], DnDBlockLists.plankSlabs[idx])
        }
        this.woodWall(DnDBlocks.BAMBOO_WALL, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB)
        this.woodWall(DnDBlocks.BAMBOO_MOSAIC_WALL, Blocks.BAMBOO_MOSAIC, Blocks.BAMBOO_MOSAIC_SLAB)
    }
}