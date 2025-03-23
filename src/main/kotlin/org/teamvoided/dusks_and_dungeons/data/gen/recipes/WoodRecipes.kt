package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.datagen.criterion

object WoodRecipes {
    fun generateWoodRecipes(e: RecipeExporter) {
        cascade(e)
    }

    private fun cascade(e: RecipeExporter) {
        FabricRecipeProvider.offerPlanksRecipe(e, DnDBlocks.CASCADE_PLANKS, DnDItemTags.CASCADE_LOGS, 4)
        FabricRecipeProvider.offerBarkBlockRecipe(e, DnDBlocks.CASCADE_WOOD, DnDBlocks.CASCADE_LOG)
        FabricRecipeProvider.offerBarkBlockRecipe(e, DnDBlocks.STRIPPED_CASCADE_WOOD, DnDBlocks.STRIPPED_CASCADE_LOG)
        FabricRecipeProvider.offerHangingSignRecipe(e, DnDItems.CASCADE_HANGING_SIGN, DnDBlocks.STRIPPED_CASCADE_LOG)
        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, DnDBlocks.BLUE_DOOR, 3)
            .ingredient('#', Ingredient.ofItems(DnDBlocks.CASCADE_PLANKS.asItem()))
            .ingredient('@', Ingredient.ofItems(Items.GOLD_NUGGET))
            .pattern("## ")
            .pattern("##@")
            .pattern("## ")
            .criterion(DnDBlocks.CASCADE_PLANKS.asItem())
            .offerTo(e)
    }
}