package org.teamvoided.dusks_and_dungeons.datagen.data.recipe.helpers

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.SingleItemRecipeBuilder
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike


fun RecipeOutput.candelabra(candelabra: ItemLike, ingot: ItemLike, nuget: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, candelabra, 4)
        .define('|', Ingredient.of(ingot))
        .define('~', Ingredient.of(nuget))
        .pattern("~~~")
        .pattern(" | ")
        .unlockedBy(ingot)
        .unlockedBy(candelabra)
        .save(this)
    SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingot), RecipeCategory.BUILDING_BLOCKS, candelabra, 3)
        .unlockedBy(ingot)
        .save(this, conversionName(ingot, candelabra))
}

fun RecipeOutput.sconce(sconce: ItemLike, ingot: ItemLike, nuget: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, sconce)
        .define('#', Ingredient.of(ingot))
        .define('.', Ingredient.of(nuget))
        .pattern("##")
        .pattern("#.")
        .unlockedBy(ingot)
        .unlockedBy(sconce)
        .save(this)
}