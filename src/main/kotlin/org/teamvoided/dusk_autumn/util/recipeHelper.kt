package org.teamvoided.dusk_autumn.util

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.CookingRecipeJsonFactory
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumn.init.DnDBlocks

fun RecipeExporter.createBigLantern(
    block: ItemConvertible,
    torch: ItemConvertible,
    smallLantern: ItemConvertible?
) {
    val criteriaItem = smallLantern ?: torch
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, block)
        .ingredient('#', Ingredient.ofItems(torch))
        .ingredient('O', Ingredient.ofItems(Items.IRON_INGOT))
        .ingredient('X', Ingredient.ofItems(Items.IRON_NUGGET))
        .pattern("XOX")
        .pattern("O#O")
        .pattern("XOX")
        .criterion(FabricRecipeProvider.hasItem(criteriaItem), FabricRecipeProvider.conditionsFromItem(criteriaItem))
        .offerTo(this)
}

fun RecipeExporter.createCandle(
    candle: ItemConvertible,
    soul: TagKey<Item>? = null
) {
    if (soul == null) {
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, candle, 1)
            .ingredient('S', Ingredient.ofItems(Items.STRING))
            .ingredient('H', Ingredient.ofItems(Items.HONEYCOMB_BLOCK))
            .pattern("S")
            .pattern("H")
            .criterion("has_string", FabricRecipeProvider.conditionsFromItem(Items.STRING))
            .criterion("has_honeycomb", FabricRecipeProvider.conditionsFromItem(Items.HONEYCOMB_BLOCK))
            .offerTo(this)
    } else {
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, candle, 1)
            .ingredient('S', Ingredient.ofItems(Items.STRING))
            .ingredient('H', Ingredient.ofItems(Items.HONEYCOMB_BLOCK))
            .ingredient('#', Ingredient.ofTag(soul))
            .pattern("S")
            .pattern("H")
            .pattern("#")
            .criterion("has_soul", FabricRecipeProvider.conditionsFromTag(soul))
            .offerTo(this)
    }
}

fun RecipeExporter.createDyed(
    block: ItemConvertible,
    dye: ItemConvertible
) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, block)
        .ingredient(Ingredient.ofItems(block))
        .ingredient(Ingredient.ofItems(dye))
        .criterion(FabricRecipeProvider.hasItem(dye), FabricRecipeProvider.conditionsFromItem(dye))
        .offerTo(this)
}

fun RecipeExporter.smeltDefault(
    output: ItemConvertible,
    input: ItemConvertible
) {
    CookingRecipeJsonFactory.createSmelting(
        Ingredient.ofItems(input),
        RecipeCategory.BUILDING_BLOCKS, output.asItem(), 0.1f, 200
    ).criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input)).offerTo(this)
}

fun RecipeExporter.createOvergrown(
    block: ItemConvertible,
    stone: ItemConvertible
) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, block)
        .ingredient(Ingredient.ofItems(stone))
        .ingredient(Ingredient.ofTag(ItemTags.LEAVES))
        .criterion(FabricRecipeProvider.hasItem(stone), FabricRecipeProvider.conditionsFromItem(stone))
        .offerTo(this)
}

fun RecipeExporter.createStonecuttedSet(
    input: List<Block>,
    polish: ItemConvertible?,
    stair: ItemConvertible?,
    slab: ItemConvertible?,
    wall: ItemConvertible?,
    extra: List<ItemConvertible>? = null
) {
    input.forEach {
        if (polish != null && polish.asItem().name.string != it.asItem().name.string)
            FabricRecipeProvider.createStonecuttingRecipe(this, RecipeCategory.BUILDING_BLOCKS, polish, it)
        if (stair != null) FabricRecipeProvider.createStonecuttingRecipe(
            this,
            RecipeCategory.BUILDING_BLOCKS,
            stair,
            it
        )
        if (slab != null) FabricRecipeProvider.createStonecuttingRecipe(
            this,
            RecipeCategory.BUILDING_BLOCKS,
            slab,
            it,
            2
        )
        if (wall != null) FabricRecipeProvider.createStonecuttingRecipe(this, RecipeCategory.DECORATIONS, wall, it)
        extra?.forEach { special ->
            if (special.asItem().name.string != it.asItem().name.string) {
                FabricRecipeProvider.createStonecuttingRecipe(
                    this,
                    RecipeCategory.BUILDING_BLOCKS,
                    special,
                    it
                )
            }
        }
    }
}

fun RecipeExporter.createStackedCraft(output: ItemConvertible, block: ItemConvertible, itemTag: TagKey<Item>) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', block)
        .pattern("#")
        .pattern("#")
        .criterion("has_" + itemTag.id.path, FabricRecipeProvider.conditionsFromTag(itemTag)).offerTo(this)
}

fun RecipeExporter.createStackedCraft(output: ItemConvertible, block: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', block)
        .pattern("#")
        .pattern("#")
        .criterion(FabricRecipeProvider.hasItem(block), FabricRecipeProvider.conditionsFromItem(block))
        .offerTo(this)
}

fun RecipeExporter.createStair(output: ItemConvertible, block: ItemConvertible) {
    createStair(output, block, block)
}

fun RecipeExporter.createSlab(output: ItemConvertible, block: ItemConvertible) {
    createSlab(output, block, block)
}

fun RecipeExporter.createwall(output: ItemConvertible, block: ItemConvertible) {
    createwall(output, block, block)
}

fun RecipeExporter.createFence(output: ItemConvertible, block: ItemConvertible) {
    createFence(output, block, block)
}

fun RecipeExporter.createStair(output: ItemConvertible, block: ItemConvertible, item: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 3)
        .ingredient('#', block)
        .pattern("#  ")
        .pattern("## ")
        .pattern("###")
        .criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item)).offerTo(this)
}

fun RecipeExporter.createSlab(output: ItemConvertible, block: ItemConvertible, item: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', block)
        .pattern("###")
        .criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item)).offerTo(this)
}

fun RecipeExporter.createwall(output: ItemConvertible, block: ItemConvertible, item: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 6)
        .ingredient('#', block)
        .pattern("###")
        .pattern("###")
        .criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item)).offerTo(this)
}

fun RecipeExporter.createFence(output: ItemConvertible, block: ItemConvertible, item: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', block)
        .ingredient('+', item)
        .pattern("#+#")
        .pattern("#+#")
        .criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item)).offerTo(this)
}

fun RecipeExporter.createDiagonalRecipe(
    output: ItemConvertible,
    primary: ItemConvertible,
    secondary: ItemConvertible,
) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', primary)
        .ingredient('%', secondary)
        .pattern("#%")
        .pattern("%#")
        .criterion(FabricRecipeProvider.hasItem(primary), FabricRecipeProvider.conditionsFromItem(primary))
        .offerTo(this)
}

fun RecipeExporter.createDiagonalRecipe(
    output: ItemConvertible,
    primary: ItemConvertible,
    secondary: TagKey<Item>
) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', primary)
        .ingredient('%', secondary)
        .pattern("#%")
        .pattern("%#")
        .criterion(FabricRecipeProvider.hasItem(primary), FabricRecipeProvider.conditionsFromItem(primary))
        .offerTo(this)
}

fun RecipeExporter.createPiles(output: ItemConvertible, input: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 8)
        .ingredient('#', Ingredient.ofItems(input))
        .pattern("##")
        .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input)).offerTo(this)
}

fun RecipeExporter.cobbled() {
    var cobble = Blocks.COBBLESTONE
    this.createDiagonalRecipe(DnDBlocks.ROCKY_GRASS, Blocks.GRASS_BLOCK, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_PODZOL, Blocks.PODZOL, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_MYCELIUM, Blocks.MYCELIUM, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_DIRT, Blocks.DIRT, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_MUD, Blocks.MUD, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_SNOW, Blocks.SNOW_BLOCK, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_GRAVEL, Blocks.GRAVEL, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_SAND, Blocks.SAND, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_RED_SAND, Blocks.RED_SAND, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_SOUL_SAND, Blocks.SOUL_SAND, cobble)
    this.createDiagonalRecipe(DnDBlocks.ROCKY_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
    cobble = Blocks.COBBLED_DEEPSLATE
    this.createDiagonalRecipe(DnDBlocks.SLATED_GRASS, Blocks.GRASS_BLOCK, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_PODZOL, Blocks.PODZOL, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_MYCELIUM, Blocks.MYCELIUM, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_DIRT, Blocks.DIRT, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_MUD, Blocks.MUD, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_SNOW, Blocks.SNOW_BLOCK, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_GRAVEL, Blocks.GRAVEL, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_SAND, Blocks.SAND, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_RED_SAND, Blocks.RED_SAND, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_SOUL_SAND, Blocks.SOUL_SAND, cobble)
    this.createDiagonalRecipe(DnDBlocks.SLATED_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
    cobble = Blocks.BLACKSTONE
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_GRASS, Blocks.GRASS_BLOCK, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_PODZOL, Blocks.PODZOL, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_MYCELIUM, Blocks.MYCELIUM, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_DIRT, Blocks.DIRT, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_MUD, Blocks.MUD, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_SNOW, Blocks.SNOW_BLOCK, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_GRAVEL, Blocks.GRAVEL, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_SAND, Blocks.SAND, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_RED_SAND, Blocks.RED_SAND, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_SOUL_SAND, Blocks.SOUL_SAND, cobble)
    this.createDiagonalRecipe(DnDBlocks.BLACKSTONE_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
}