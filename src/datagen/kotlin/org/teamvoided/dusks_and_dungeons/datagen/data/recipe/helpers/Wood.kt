package org.teamvoided.dusks_and_dungeons.datagen.data.recipe.helpers

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.datagen.old.util.criterion
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet


fun RecipeOutput.planksFromLogs(planks: Block, logTag: TagKey<Item>, count: Int = 4) = FabricRecipeProvider.planksFromLogs(this, planks, logTag, count)
fun RecipeOutput.woodFromLogs(wood: AbstractBlockSet, log: Block) = FabricRecipeProvider.woodFromLogs(this, wood, log)
fun RecipeOutput.hangingSign(sign: Item, stripedLog: Block) = FabricRecipeProvider.hangingSign(this, sign, stripedLog)

fun RecipeOutput.woodWall(wall: ItemLike, plank: ItemLike, slab: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, wall, 6)
        .define('#', plank)
        .define('=', slab)
        .pattern("#=#")
        .pattern("###")
        .criterion(plank)
        .save(this)
}

fun RecipeOutput.hollowLog(hollowLog: Block, log: Block) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, hollowLog, 4)
        .define('#', Ingredient.of(log))
        .pattern(" # ")
        .pattern("# #")
        .pattern(" # ")
        .criterion(log)
        .save(this)
}
