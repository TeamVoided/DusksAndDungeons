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
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.criterion
import org.teamvoided.dusks_and_dungeons.util.datagen.recipe.woodWall

object WoodRecipes {
    fun generateWoodRecipes(e: RecipeExporter) {
        e.cascade()
        e.woodWalls()
    }

    private fun RecipeExporter.cascade() {
        FabricRecipeProvider.offerPlanksRecipe(this, DnDBlocks.CASCADE_PLANKS, DnDItemTags.CASCADE_LOGS, 4)
        FabricRecipeProvider.offerBarkBlockRecipe(this, DnDBlocks.CASCADE_WOOD, DnDBlocks.CASCADE_LOG)
        FabricRecipeProvider.offerBarkBlockRecipe(this, DnDBlocks.STRIPPED_CASCADE_WOOD, DnDBlocks.STRIPPED_CASCADE_LOG)
        FabricRecipeProvider.offerHangingSignRecipe(this, DnDItems.CASCADE_HANGING_SIGN, DnDBlocks.STRIPPED_CASCADE_LOG)
        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, DnDBlocks.BLUE_DOOR, 3)
            .ingredient('#', Ingredient.ofItems(DnDBlocks.CASCADE_PLANKS.asItem()))
            .ingredient('@', Ingredient.ofItems(Items.GOLD_NUGGET))
            .pattern("## ")
            .pattern("##@")
            .pattern("## ")
            .criterion(DnDBlocks.CASCADE_PLANKS.asItem())
            .offerTo(this)
        ShapedRecipeJsonFactory.create(RecipeCategory.MISC, DnDBlocks.BIG_SCAFFOLDING, 6)
            .ingredient('|', Ingredient.ofItems(Items.BAMBOO_PLANKS))
            .ingredient('~', Ingredient.ofItems(Items.STRING))
            .pattern("|~|")
            .pattern("| |")
            .pattern("| |")
            .criterion(Items.BAMBOO_PLANKS)
            .offerTo(this)
    }

    private fun RecipeExporter.woodWalls() {
        DnDBlockLists.plankWalls.forEachIndexed { idx, it ->
            this.woodWall(it, DnDBlockLists.planks[idx], DnDBlockLists.plankSlabs[idx])
        }
        this.woodWall(DnDBlocks.BAMBOO_WALL, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB)
        this.woodWall(DnDBlocks.BAMBOO_MOSAIC_WALL, Blocks.BAMBOO_MOSAIC, Blocks.BAMBOO_MOSAIC_SLAB)
    }
}