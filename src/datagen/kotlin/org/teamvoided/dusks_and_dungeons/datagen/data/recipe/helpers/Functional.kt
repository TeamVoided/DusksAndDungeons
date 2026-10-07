package org.teamvoided.dusks_and_dungeons.datagen.data.recipe.helpers

import net.minecraft.data.recipes.*
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import org.teamvoided.dusks_and_dungeons.block.big.BigChainBlock


fun RecipeOutput.bigChain(chain: BigChainBlock, ingot: Item, nugget: Item) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, chain)
        .pattern("I")
        .pattern("N")
        .pattern("I")
        .define('I', Ingredient.of(ingot))
        .define('N', Ingredient.of(nugget))
        .unlockedBy(ingot)
        .unlockedBy(nugget)
        .save(this)
}

fun RecipeOutput.lantern(lantern: ItemLike, torch: ItemLike, nugget: Item, ingot: Item) {
    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, lantern)
        .pattern("XXX")
        .pattern("X#X")
        .pattern("XXX")
        .define('#', torch)
        .define('X', nugget)
        .unlockedBy(nugget)
        .unlockedBy(ingot)
        .save(this)
}

fun RecipeOutput.bigIronLantern(block: ItemLike, torch: ItemLike, smallLantern: ItemLike) {
    bigLantern(block, torch, smallLantern, Items.IRON_INGOT, Items.IRON_NUGGET)
}

fun RecipeOutput.bigLantern(
    block: ItemLike, torch: ItemLike, smallLantern: ItemLike, ingot: Item, nugget: Item,
) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
        .pattern("XOX")
        .pattern("O#O")
        .pattern("XOX")
        .define('#', Ingredient.of(torch))
        .define('O', Ingredient.of(ingot))
        .define('X', Ingredient.of(nugget))
        .unlockedBy(torch)
        .unlockedBy(smallLantern)
        .save(this)
}

fun RecipeOutput.candle(candle: ItemLike, honeycomb: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, candle)
        .pattern("S")
        .pattern("H")
        .define('S', Ingredient.of(Items.STRING))
        .define('H', Ingredient.of(honeycomb))
        .unlockedBy(Items.STRING)
        .unlockedBy(honeycomb)
        .save(this)

}

fun RecipeOutput.reagentCandle(candle: ItemLike, honeycomb: ItemLike, reagent: TagKey<Item>) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, candle)
        .pattern("S")
        .pattern("#")
        .pattern("H")
        .define('S', Ingredient.of(Items.STRING))
        .define('H', Ingredient.of(honeycomb))
        .define('#', Ingredient.of(reagent))
        .unlockedBy(reagent)
        .save(this)
}


fun RecipeOutput.shapelessDying(dyedItem: ItemLike, item: ItemLike, dye: ItemLike) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, dyedItem)
        .requires(item)
        .requires(dye)
        .unlockedBy(dye)
        .save(this)
}

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