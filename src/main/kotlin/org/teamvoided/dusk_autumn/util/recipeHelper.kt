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
import org.teamvoided.dusk_autumn.init.DuskBlocks

fun RecipeExporter.createBigLantern(
    block: ItemConvertible,
    torch: ItemConvertible,
    smallLantern: ItemConvertible?
) {
    val criteriaItem = smallLantern ?: torch
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, block, 1)
        .ingredient('#', Ingredient.ofItems(torch))
        .ingredient('O', Ingredient.ofItems(Items.IRON_INGOT))
        .ingredient('X', Ingredient.ofItems(Items.IRON_NUGGET))
        .pattern("XOX")
        .pattern("O#O")
        .pattern("XOX")
        .criterion(FabricRecipeProvider.hasItem(criteriaItem), FabricRecipeProvider.conditionsFromItem(criteriaItem))
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
    ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, block, 1)
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
    secondary: ItemConvertible
) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', primary)
        .ingredient('%', secondary)
        .pattern("#%")
        .pattern("%#")
        .criterion(FabricRecipeProvider.hasItem(primary), FabricRecipeProvider.conditionsFromItem(primary)).offerTo(this)
}

fun RecipeExporter.createPiles(output: ItemConvertible, input: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 8)
        .ingredient('#', Ingredient.ofItems(input))
        .pattern("##")
        .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input)).offerTo(this)
}

fun RecipeExporter.cobbled() {
    var cobble = Blocks.COBBLESTONE
    this.createDiagonalRecipe(DuskBlocks.ROCKY_GRASS, Blocks.GRASS_BLOCK, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_PODZOL, Blocks.PODZOL, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_MYCELIUM, Blocks.MYCELIUM, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_DIRT, Blocks.DIRT, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_MUD, Blocks.MUD, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_SNOW, Blocks.SNOW_BLOCK, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_GRAVEL, Blocks.GRAVEL, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_SAND, Blocks.SAND, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_RED_SAND, Blocks.RED_SAND, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_SOUL_SAND, Blocks.SOUL_SAND, cobble)
    this.createDiagonalRecipe(DuskBlocks.ROCKY_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
    cobble = Blocks.COBBLED_DEEPSLATE
    this.createDiagonalRecipe(DuskBlocks.SLATED_GRASS, Blocks.GRASS_BLOCK, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_PODZOL, Blocks.PODZOL, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_MYCELIUM, Blocks.MYCELIUM, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_DIRT, Blocks.DIRT, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_MUD, Blocks.MUD, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_SNOW, Blocks.SNOW_BLOCK, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_GRAVEL, Blocks.GRAVEL, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_SAND, Blocks.SAND, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_RED_SAND, Blocks.RED_SAND, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_SOUL_SAND, Blocks.SOUL_SAND, cobble)
    this.createDiagonalRecipe(DuskBlocks.SLATED_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
    cobble = Blocks.BLACKSTONE
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_GRASS, Blocks.GRASS_BLOCK, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_PODZOL, Blocks.PODZOL, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_MYCELIUM, Blocks.MYCELIUM, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_DIRT, Blocks.DIRT, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_MUD, Blocks.MUD, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_SNOW, Blocks.SNOW_BLOCK, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_GRAVEL, Blocks.GRAVEL, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_SAND, Blocks.SAND, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_RED_SAND, Blocks.RED_SAND, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_SOUL_SAND, Blocks.SOUL_SAND, cobble)
    this.createDiagonalRecipe(DuskBlocks.BLACKSTONE_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
}