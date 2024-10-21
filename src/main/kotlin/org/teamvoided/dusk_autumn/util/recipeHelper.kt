package org.teamvoided.dusk_autumn.util

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.server.RecipesProvider
import net.minecraft.data.server.RecipesProvider.*
import net.minecraft.data.server.recipe.*
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDOverlayBlocks

fun RecipeJsonFactory.criterion(item: ItemConvertible): RecipeJsonFactory =
    this.criterion(hasItem(item), conditionsFromItem(item))

fun RecipeJsonFactory.criterion(tag: TagKey<Item>): RecipeJsonFactory =
    this.criterion("has_${tag.id.path}", conditionsFromTag(tag))

fun RecipeExporter.createBigLantern(
    block: ItemConvertible,
    torch: ItemConvertible,
    smallLantern: ItemConvertible? = null
) {
    val criteriaItem = smallLantern ?: torch
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, block)
        .ingredient('#', Ingredient.ofItems(torch))
        .ingredient('O', Ingredient.ofItems(Items.IRON_INGOT))
        .ingredient('X', Ingredient.ofItems(Items.IRON_NUGGET))
        .pattern("XOX")
        .pattern("O#O")
        .pattern("XOX")
        .criterion(criteriaItem)
        .offerTo(this)
}

fun RecipeExporter.createCandle(
    candle: ItemConvertible,
    honeycomb: ItemConvertible,
    soul: TagKey<Item>? = null
) {
    if (soul == null) {
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, candle, 1)
            .ingredient('S', Ingredient.ofItems(Items.STRING))
            .ingredient('H', Ingredient.ofItems(honeycomb))
            .pattern("S")
            .pattern("H")
            .criterion(Items.STRING)
            .criterion(honeycomb)
            .offerTo(this)
    } else {
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, candle, 1)
            .ingredient('S', Ingredient.ofItems(Items.STRING))
            .ingredient('H', Ingredient.ofItems(honeycomb))
            .ingredient('#', Ingredient.ofTag(soul))
            .pattern("S")
            .pattern("H")
            .pattern("#")
            .criterion(soul)
            .offerTo(this)
    }
}

fun RecipeExporter.createDyed(
    dyedBlock: ItemConvertible,
    input: ItemConvertible,
    dye: ItemConvertible
) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, dyedBlock)
        .ingredient(Ingredient.ofItems(input))
        .ingredient(Ingredient.ofItems(dye))
        .criterion(dye)
        .offerTo(this)
}

fun RecipeExporter.createGragestones(
    gravestone: ItemConvertible,
    smallGravestone: ItemConvertible,
    hauntedGravestone: ItemConvertible,
    hauntedSmallGravestone: ItemConvertible,
    block: ItemConvertible
) {
    this.createGravestone(gravestone, block)
    FabricRecipeProvider.createStonecuttingRecipe(
        this,
        RecipeCategory.BUILDING_BLOCKS,
        gravestone,
        smallGravestone
    )
    FabricRecipeProvider.createStonecuttingRecipe(
        this,
        RecipeCategory.BUILDING_BLOCKS,
        hauntedGravestone,
        hauntedSmallGravestone
    )
}

fun RecipeExporter.createGravestone(output: ItemConvertible, input: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output)
        .ingredient('#', input)
        .ingredient('0', ItemTags.SOUL_FIRE_BASE_BLOCKS)
        .pattern("###")
        .pattern("#0#")
        .criterion(input)
        .criterion(ItemTags.SOUL_FIRE_BASE_BLOCKS)
        .offerTo(this)
}

fun RecipeExporter.createPumpkinPie(output: ItemConvertible, pumpkin: ItemConvertible, carvedPumpkin: ItemConvertible) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.FOOD, output)
        .ingredient(pumpkin)
        .ingredient(Items.SUGAR)
        .ingredient(Items.EGG)
        .criterion(carvedPumpkin)
        .criterion(pumpkin)
        .offerTo(this)
}

fun RecipeExporter.createPumpkinStuffs(
    pumpkin: ItemConvertible,
    carvedPumpkin: ItemConvertible,
    glowingPumpkin: ItemConvertible,
    smallPumpkin: ItemConvertible,
    smallCarvedPumpkin: ItemConvertible,
    smallGlowingPumpkin: ItemConvertible,
    pumpkinPie: ItemConvertible
) {
    this.createStackedCraft(
        glowingPumpkin,
        carvedPumpkin,
        Items.TORCH
    )
    this.createStackedCraft(
        smallGlowingPumpkin,
        smallCarvedPumpkin,
        Items.TORCH
    )
    this.createPumpkinPie(
        pumpkinPie,
        pumpkin,
        carvedPumpkin
    )
    createStonecuttingRecipe(
        this, RecipeCategory.BUILDING_BLOCKS,
        smallPumpkin,
        pumpkin,
        4
    )
}

fun RecipeExporter.smeltDefault(
    output: ItemConvertible,
    input: ItemConvertible
) {
    CookingRecipeJsonFactory.createSmelting(
        Ingredient.ofItems(input),
        RecipeCategory.BUILDING_BLOCKS, output.asItem(), 0.1f, 200
    )
        .criterion(input)
        .offerTo(this, Identifier.parse(getRecipeName(output)).toString() + "_smelt")
}

fun RecipeExporter.createOvergrown(
    output: ItemConvertible,
    input: ItemConvertible
) {
    this.createTwoPiece(output, input, ItemTags.LEAVES)
}

fun RecipeExporter.createTwoPiece(
    input1: ItemConvertible,
    input2: ItemConvertible,
    output: ItemConvertible
) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output)
        .ingredient(Ingredient.ofItems(input1))
        .ingredient(Ingredient.ofItems(input2))
        .criterion(input1)
        .criterion(input2)
        .offerTo(this)
}

fun RecipeExporter.createTwoPiece(
    output: ItemConvertible,
    input1: ItemConvertible,
    input2: TagKey<Item>
) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output)
        .ingredient(Ingredient.ofItems(input1))
        .ingredient(Ingredient.ofTag(input2))
        .criterion(input1)
        .criterion(input2)
        .offerTo(this)
}

fun RecipeExporter.createSmallSquare(output: ItemConvertible, input: ItemConvertible, count: Int = 1) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, count)
        .ingredient('#', input)
        .pattern("##")
        .pattern("##")
        .criterion(input)
        .offerTo(this)
}

fun RecipeExporter.createFullSquare(output: ItemConvertible, input: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 1)
        .ingredient('#', input)
        .pattern("##")
        .pattern("##")
        .criterion(input)
        .offerTo(this)
}

fun RecipeExporter.createCount(output: ItemConvertible, input: ItemConvertible, countOutput: Int) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, countOutput)
        .ingredient(input)
        .criterion(input)
        .offerTo(this)
}


fun RecipeExporter.offerReversibleCompactingRecipes4(
    reverseCategory: RecipeCategory,
    baseItem: ItemConvertible,
    compactingCategory: RecipeCategory,
    compactedItem: ItemConvertible
) {
    offerReversibleCompactingRecipes4(
        reverseCategory,
        baseItem,
        compactingCategory,
        compactedItem,
        getRecipeName(compactedItem),
        null as String?,
        getRecipeName(baseItem),
        null as String?
    )
}

fun RecipeExporter.offerReversibleCompactingRecipes4(
    reverseCategory: RecipeCategory,
    baseItem: ItemConvertible,
    compactingCategory: RecipeCategory,
    compactedItem: ItemConvertible,
    compactingId: String,
    compactingGroup: String?,
    reverseId: String,
    reverseGroup: String?
) {
    ShapelessRecipeJsonFactory.create(reverseCategory, baseItem, 4)
        .ingredient(compactedItem)
        .group(reverseGroup)
        .criterion(hasItem(compactedItem), conditionsFromItem(compactedItem))
        .offerTo(this, Identifier.parse(reverseId))
    ShapedRecipeJsonFactory.create(compactingCategory, compactedItem)
        .ingredient('#', baseItem)
        .pattern("##")
        .pattern("##")
        .group(compactingGroup)
        .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
        .offerTo(this, Identifier.parse(compactingId))
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

fun RecipeExporter.createStonecuttedFromList(
    input: List<Block>,
    output: ItemConvertible?
) {
    input.forEach {
        FabricRecipeProvider.createStonecuttingRecipe(this, RecipeCategory.BUILDING_BLOCKS, output, it)
    }
}

fun RecipeExporter.createStackedCraft(output: ItemConvertible, block: ItemConvertible, itemTag: TagKey<Item>) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', block)
        .pattern("#")
        .pattern("#")
        .criterion(itemTag)
        .offerTo(this)
}

fun RecipeExporter.createStackedCraft(output: ItemConvertible, block1: ItemConvertible, block2: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 1)
        .ingredient('#', block1)
        .ingredient('%', block2)
        .pattern("#")
        .pattern("%")
        .criterion(block1)
        .offerTo(this)
}

fun RecipeExporter.createStackedCraft(output: ItemConvertible, block: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', block)
        .pattern("#")
        .pattern("#")
        .criterion(block)
        .offerTo(this)
}

fun RecipeExporter.createDoubleCraft(output: ItemConvertible, input1: ItemConvertible, input2: ItemConvertible) {
    ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient(input1)
        .ingredient(input2)
        .criterion(input1)
        .offerTo(this)
}

fun RecipeExporter.createStair(output: ItemConvertible, block: ItemConvertible) {
    createStair(output, block, block)
}

fun RecipeExporter.createSlab(output: ItemConvertible, block: ItemConvertible) {
    createSlab(output, block, block)
}

fun RecipeExporter.createWall(output: ItemConvertible, block: ItemConvertible) {
    createWall(output, block, block)
}

fun RecipeExporter.createFence(output: ItemConvertible, block: ItemConvertible) {
    createFence(output, block, block)
}

fun RecipeExporter.createStair(output: ItemConvertible, block: ItemConvertible, item: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
        .ingredient('#', block)
        .pattern("#  ")
        .pattern("## ")
        .pattern("###")
        .criterion(item)
        .offerTo(this)
}

fun RecipeExporter.createSlab(output: ItemConvertible, block: ItemConvertible, item: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 6)
        .ingredient('#', block)
        .pattern("###")
        .criterion(item)
        .offerTo(this)
}

fun RecipeExporter.createWall(output: ItemConvertible, block: ItemConvertible, item: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 6)
        .ingredient('#', block)
        .pattern("###")
        .pattern("###")
        .criterion(item)
        .offerTo(this)
}

fun RecipeExporter.createFence(output: ItemConvertible, block: ItemConvertible, item: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', block)
        .ingredient('+', item)
        .pattern("#+#")
        .pattern("#+#")
        .criterion(item)
        .offerTo(this)
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
        .criterion(primary)
        .offerTo(this)
}

fun RecipeExporter.createDiagonalRecipe(
    output: ItemConvertible,
    primary: TagKey<Item>,
    secondary: ItemConvertible
) {
    ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .ingredient('#', primary)
        .ingredient('%', secondary)
        .pattern("#%")
        .pattern("%#")
        .criterion(primary)
        .offerTo(this)
}

fun RecipeExporter.createPiles(output: ItemConvertible, input: ItemConvertible) {
    ShapedRecipeJsonFactory.create(RecipeCategory.DECORATIONS, output, 8)
        .ingredient('#', Ingredient.ofItems(input))
        .pattern("##")
        .criterion(input)
        .offerTo(this)
}

fun RecipeExporter.cobbled() {
    var cobble = Blocks.COBBLESTONE
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_GRASS, Blocks.GRASS_BLOCK, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_PODZOL, Blocks.PODZOL, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_MYCELIUM, Blocks.MYCELIUM, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_DIRT, Blocks.DIRT, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_MUD, Blocks.MUD, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_SNOW, Blocks.SNOW_BLOCK, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_GRAVEL, Blocks.GRAVEL, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_SAND, Blocks.SAND, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_RED_SAND, Blocks.RED_SAND, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_SOUL_SAND, Blocks.SOUL_SAND, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.ROCKY_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
    cobble = Blocks.COBBLED_DEEPSLATE
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_GRASS, Blocks.GRASS_BLOCK, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_PODZOL, Blocks.PODZOL, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_MYCELIUM, Blocks.MYCELIUM, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_DIRT, Blocks.DIRT, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_MUD, Blocks.MUD, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_SNOW, Blocks.SNOW_BLOCK, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_GRAVEL, Blocks.GRAVEL, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_SAND, Blocks.SAND, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_RED_SAND, Blocks.RED_SAND, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_SOUL_SAND, Blocks.SOUL_SAND, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.SLATED_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
    cobble = Blocks.BLACKSTONE
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_GRASS, Blocks.GRASS_BLOCK, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_PODZOL, Blocks.PODZOL, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_MYCELIUM, Blocks.MYCELIUM, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_DIRT, Blocks.DIRT, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_COARSE_DIRT, Blocks.COARSE_DIRT, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_MUD, Blocks.MUD, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_SNOW, Blocks.SNOW_BLOCK, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_GRAVEL, Blocks.GRAVEL, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_SAND, Blocks.SAND, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_RED_SAND, Blocks.RED_SAND, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_SOUL_SAND, Blocks.SOUL_SAND, cobble)
    this.createDiagonalRecipe(DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL, Blocks.SOUL_SOIL, cobble)
}
