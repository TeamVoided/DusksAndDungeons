package org.teamvoided.dusk_autumn.datagen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeJsonFactory
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.DuskItems
import java.util.function.Consumer

class RecipesProvider(o: FabricDataOutput) : FabricRecipeProvider(o) {
    override fun generateRecipes(exporter: Consumer<RecipeJsonProvider>) {
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
        createLeafPilesRecipe(DuskBlocks.OAK_LEAF_PILE, Items.OAK_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.SPRUCE_LEAF_PILE, Items.SPRUCE_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.BIRCH_LEAF_PILE, Items.BIRCH_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.JUNGLE_LEAF_PILE, Items.JUNGLE_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.ACACIA_LEAF_PILE, Items.ACACIA_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.DARK_OAK_LEAF_PILE, Items.DARK_OAK_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.MANGROVE_LEAF_PILE, Items.MANGROVE_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.AZALEA_LEAF_PILE, Items.AZALEA_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.FLOWERING_AZALEA_LEAF_PILE, Items.FLOWERING_AZALEA_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.CHERRY_LEAF_PILE, Items.CHERRY_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.CASCADE_LEAF_PILE, DuskItems.CASCADE_LEAVES).offerTo(exporter)
        createLeafPilesRecipe(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, DuskItems.GOLDEN_BIRCH_LEAVES).offerTo(exporter)
    }

    fun createLeafPilesRecipe(output: ItemConvertible, input: ItemConvertible): RecipeJsonFactory {
        return ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 8)
            .ingredient('#', Ingredient.ofItems(input))
            .pattern("##")
            .criterion(
                hasItem(input), conditionsFromItem(
                    input
                )
            )
    }
}