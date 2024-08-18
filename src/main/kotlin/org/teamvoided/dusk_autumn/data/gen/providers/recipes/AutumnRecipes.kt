package org.teamvoided.dusk_autumn.data.gen.providers.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusk_autumn.data.tags.DnDItemTags
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.util.*

object AutumnRecipes {
    fun generateAutumnRecipes(e: RecipeExporter) {
        (DnDBlockLists.leafPiles + DnDBlockLists.logPiles).forEach { (pile, block) ->
            e.createPiles(pile, block)
        }
        FabricRecipeProvider.offerShapelessRecipe(e, Items.BLUE_DYE, DnDBlocks.BLUE_PETALS, "blue_dye")
        ShapedRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.FARMERS_HAT, 1)
            .ingredient('#', Ingredient.ofItems(Items.WHEAT))
            .ingredient('@', Ingredient.ofItems(Items.STRING))
            .ingredient('%', Ingredient.ofItems(Items.LEATHER))
            .pattern("###")
            .pattern("@%@")
            .pattern("# #")
            .criterion(DnDItems.FARMERS_HAT).offerTo(e)
        FabricRecipeProvider.offerShapelessRecipe(e, Items.PURPLE_DYE, DnDItems.MOONBERRIES, "purple_dye")
    }
}