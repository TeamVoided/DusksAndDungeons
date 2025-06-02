package org.teamvoided.dusks_and_dungeons.util.datagen.recipe

import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.ItemConvertible
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusks_and_dungeons.util.datagen.criterion

fun RecipeExporter.woodWall(wall: ItemConvertible, plank: ItemConvertible, slab: ItemConvertible) {
    return ShapedRecipeJsonFactory
        .create(RecipeCategory.DECORATIONS, wall, 6)
        .ingredient('#', plank)
        .ingredient('=', slab)
        .pattern("#=#")
        .pattern("###")
        .criterion(plank)
        .offerTo(this)
}