package org.teamvoided.dusks_and_dungeons.datagen.data.recipe.helpers

import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider.*
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.data.recipes.SingleItemRecipeBuilder
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.datagen.old.util.criterion

// region Chiseled
fun RecipeOutput.createChiseled(chiseled: Block, slab: Block, source: Block) {
    chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, chiseled, Ingredient.of(slab))
        .criterion(chiseled)
        .criterion(source)
        .save(this)
}

fun RecipeOutput.cutChiseled(chiseled: Block, source: Block) {
    SingleItemRecipeBuilder.stonecutting(Ingredient.of(source), RecipeCategory.BUILDING_BLOCKS, chiseled)
        .unlockedBy(getHasName(source), has(source))
        .save(this, conversionName(source, chiseled))
}
// endregion

// region Cracked
fun RecipeOutput.smeltCracked(cracked: ItemLike, source: ItemLike) {
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(source), RecipeCategory.BUILDING_BLOCKS, cracked, 0.1f, 200)
        .unlockedBy(getHasName(source), has(source))
        .save(this)
}
// endregion


fun conversionName(from: Block, to: Block) = id(getConversionRecipeName(to, from))
