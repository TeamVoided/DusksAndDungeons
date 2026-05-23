package org.teamvoided.dusks_and_dungeons.util.datagen.recipe

import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.world.level.ItemLike
import net.minecraft.data.recipes.RecipeCategory
import org.teamvoided.dusks_and_dungeons.util.datagen.criterion

fun RecipeOutput.woodWall(wall: ItemLike, plank: ItemLike, slab: ItemLike) {
    return ShapedRecipeBuilder
        .shaped(RecipeCategory.DECORATIONS, wall, 6)
        .define('#', plank)
        .define('=', slab)
        .pattern("#=#")
        .pattern("###")
        .criterion(plank)
        .save(this)
}