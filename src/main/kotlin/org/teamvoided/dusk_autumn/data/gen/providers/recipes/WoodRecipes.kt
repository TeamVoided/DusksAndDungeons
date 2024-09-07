package org.teamvoided.dusk_autumn.data.gen.providers.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusk_autumn.data.tags.DnDItemTags
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.util.*

object WoodRecipes {
    fun generateWoodRecipes(e: RecipeExporter) {
        cascade(e)
    }

    private fun cascade(e: RecipeExporter) {
        FabricRecipeProvider.offerPlanksRecipe(e, DnDWoodBlocks.CASCADE_PLANKS, DnDItemTags.CASCADE_LOGS, 4)
        FabricRecipeProvider.offerBarkBlockRecipe(e, DnDWoodBlocks.CASCADE_WOOD, DnDWoodBlocks.CASCADE_LOG)
        FabricRecipeProvider.offerBarkBlockRecipe(e, DnDWoodBlocks.STRIPPED_CASCADE_WOOD, DnDWoodBlocks.STRIPPED_CASCADE_LOG)
        FabricRecipeProvider.offerHangingSignRecipe(e, DnDItems.CASCADE_HANGING_SIGN, DnDWoodBlocks.STRIPPED_CASCADE_LOG)
        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, DnDWoodBlocks.BLUE_DOOR, 3)
            .ingredient('#', Ingredient.ofItems(DnDWoodBlocks.CASCADE_PLANKS.asItem()))
            .ingredient('@', Ingredient.ofItems(Items.GOLD_NUGGET))
            .pattern("## ")
            .pattern("##@")
            .pattern("## ")
            .criterion(DnDWoodBlocks.CASCADE_PLANKS.asItem())
            .offerTo(e)
    }
}