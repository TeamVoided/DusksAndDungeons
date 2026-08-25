package org.teamvoided.voidlib.devin.extensions.recipe

import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.world.level.ItemLike
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider.stonecutterResultFromBase

fun RecipeOutput.createStonecutting(output: ItemLike, input: ItemLike, count: Int = 1) =
    stonecutterResultFromBase(this, RecipeCategory.BUILDING_BLOCKS, output, input, count)
