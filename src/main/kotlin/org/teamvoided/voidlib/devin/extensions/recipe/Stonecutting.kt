package org.teamvoided.voidlib.devin.extensions.recipe

import net.minecraft.data.server.RecipesProvider.createStonecuttingRecipe
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.item.ItemConvertible
import net.minecraft.recipe.RecipeCategory

fun RecipeExporter.createStonecutting(output: ItemConvertible, input: ItemConvertible, count: Int = 1) =
    createStonecuttingRecipe(this, RecipeCategory.BUILDING_BLOCKS, output, input, count)
