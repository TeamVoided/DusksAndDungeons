package org.teamvoided.dusk_autumn.datagen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems

class RecipesProvider(o: FabricDataOutput) : FabricRecipeProvider(o) {
    override fun generateRecipes(exporter: RecipeExporter) {
        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, DuskBlocks.BLUE_DOOR, 3)
            .ingredient('#', Ingredient.ofItems(DuskItems.CASCADE_PLANKS))
            .ingredient('@', Ingredient.ofItems(Items.GOLD_NUGGET))
            .pattern("## ")
            .pattern("##@")
            .pattern("## ")
            .criterion(
                "has_cascade_planks", conditionsFromItem(
                    DuskItems.CASCADE_PLANKS
                )
            ).offerTo(exporter)

        createLeafPilesRecipe(DuskBlocks.OAK_LEAF_PILE, Items.OAK_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.SPRUCE_LEAF_PILE, Items.SPRUCE_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.BIRCH_LEAF_PILE, Items.BIRCH_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.JUNGLE_LEAF_PILE, Items.JUNGLE_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.ACACIA_LEAF_PILE, Items.ACACIA_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.DARK_OAK_LEAF_PILE, Items.DARK_OAK_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.MANGROVE_LEAF_PILE, Items.MANGROVE_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.AZALEA_LEAF_PILE, Items.AZALEA_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.FLOWERING_AZALEA_LEAF_PILE, Items.FLOWERING_AZALEA_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.CHERRY_LEAF_PILE, Items.CHERRY_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.CASCADE_LEAF_PILE, DuskItems.CASCADE_LEAVES, exporter)
        createLeafPilesRecipe(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, DuskItems.GOLDEN_BIRCH_LEAVES, exporter)
    }

    fun createLeafPilesRecipe(output: ItemConvertible, input: ItemConvertible, exporter: RecipeExporter) {
        ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 8)
            .ingredient('#', Ingredient.ofItems(input))
            .pattern("##")
            .criterion(
                hasItem(input), conditionsFromItem(
                    input
                )
            ).offerTo(exporter)
    }
}