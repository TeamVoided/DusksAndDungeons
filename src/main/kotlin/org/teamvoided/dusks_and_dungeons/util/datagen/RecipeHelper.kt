package org.teamvoided.dusks_and_dungeons.util.datagen

import net.minecraft.data.recipes.*
import net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId
import net.minecraft.data.recipes.RecipeProvider.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.CandelabraBlock
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecutting

// TODO fix name to be inline with mojmaps
fun RecipeBuilder.criterion(item: ItemLike): RecipeBuilder =
    this.unlockedBy(getHasName(item), has(item))

fun RecipeBuilder.criterion(tag: TagKey<Item>): RecipeBuilder =
    this.unlockedBy("has_${tag.location.path}", has(tag))

// TODO move to big blocks
fun RecipeOutput.createBigLantern(
    block: ItemLike,
    torch: ItemLike,
    smallLantern: ItemLike? = null
) {
    val criteriaItem = smallLantern ?: torch
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
        .define('#', Ingredient.of(torch))
        .define('O', Ingredient.of(Items.IRON_INGOT))
        .define('X', Ingredient.of(Items.IRON_NUGGET))
        .pattern("XOX")
        .pattern("O#O")
        .pattern("XOX")
        .criterion(criteriaItem)
        .save(this)
}

fun RecipeOutput.createCandle(
    candle: ItemLike,
    honeycomb: ItemLike,
    soul: TagKey<Item>? = null
) {
    if (soul == null) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, candle, 1)
            .define('S', Ingredient.of(Items.STRING))
            .define('H', Ingredient.of(honeycomb))
            .pattern("S")
            .pattern("H")
            .criterion(Items.STRING)
            .criterion(honeycomb)
            .save(this)
    } else {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, candle, 1)
            .define('S', Ingredient.of(Items.STRING))
            .define('H', Ingredient.of(honeycomb))
            .define('#', Ingredient.of(soul))
            .pattern("S")
            .pattern("H")
            .pattern("#")
            .criterion(soul)
            .save(this)
    }
}

fun RecipeOutput.createCandelabra(candelabra: Block) {
    if (candelabra !is CandelabraBlock) error("Block provided isn't a CandelabraBlock!")

    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, candelabra, 1)
        .pattern("NCN")
        .pattern(" N ")
        .define('C', Ingredient.of(candelabra.candle))
        .define('N', Ingredient.of(Items.IRON_NUGGET))
        .criterion(candelabra.candle)
        .save(this)

}

fun RecipeOutput.createDyed(
    dyedBlock: ItemLike,
    input: ItemLike,
    dye: ItemLike,
    sufixed: Boolean = false
) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, dyedBlock)
        .requires(Ingredient.of(input))
        .requires(Ingredient.of(dye))
        .criterion(dye)
        .save(this, if (sufixed) id("${dyedBlock.id.path}_dyed") else dyedBlock.id)
}

fun RecipeOutput.createGravestones(
    gravestone: ItemLike, smallGravestone: ItemLike, block: ItemLike
) {
    this.createGravestone(gravestone, block)
    this.createStonecutting(smallGravestone, gravestone)
//    this.createStonecutting(hauntedSmallGravestone, hauntedGravestone)
}

fun RecipeOutput.createGravestone(output: ItemLike, input: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output)
        .define('#', input)
        .define('0', ItemTags.SOUL_FIRE_BASE_BLOCKS)
        .pattern("###")
        .pattern("#0#")
        .criterion(input)
        .criterion(ItemTags.SOUL_FIRE_BASE_BLOCKS)
        .save(this)
}

fun RecipeOutput.createPumpkinStuffs(
    pumpkin: ItemLike,
    carvedPumpkin: ItemLike,
    glowingPumpkin: ItemLike,
    smallPumpkin: ItemLike,
    smallCarvedPumpkin: ItemLike,
    smallGlowingPumpkin: ItemLike,
    seeds: ItemLike
) {
    this.createStackedCraft(glowingPumpkin, carvedPumpkin, Items.TORCH)
    this.createStackedCraft(smallGlowingPumpkin, smallCarvedPumpkin, Items.TORCH)
    stonecutterResultFromBase(
        this, RecipeCategory.BUILDING_BLOCKS,
        smallPumpkin,
        pumpkin,
        4
    )
    this.create1to4(seeds, pumpkin)
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, seeds, 2)
        .requires(smallPumpkin)
        .criterion(smallPumpkin)
        .save(this, seeds.id.suffix("_from_small"))
}

fun RecipeOutput.smeltDefault(
    output: ItemLike,
    input: ItemLike
) {
    SimpleCookingRecipeBuilder.smelting(
        Ingredient.of(input),
        RecipeCategory.BUILDING_BLOCKS, output.asItem(), 0.1f, 200
    )
        .criterion(input)
        .save(this, ResourceLocation.parse(getSimpleRecipeName(output)).toString() + "_smelt")
}

fun RecipeOutput.createOvergrown(
    output: ItemLike,
    input: ItemLike
) {
    createTwoPiece(output, input, ItemTags.LEAVES, "_leaves")
}

fun RecipeOutput.createTwoPiece(
    output: ItemLike,
    input1: ItemLike,
    input2: ItemLike,
    suffix: String = "",
    id: ResourceLocation = output.id.withSuffix(suffix)
) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output)
        .requires(Ingredient.of(input1))
        .requires(Ingredient.of(input2))
        .criterion(input1)
        .criterion(input2)
        .save(this, id)
}

fun RecipeOutput.createTwoPiece(
    output: ItemLike,
    input1: ItemLike,
    input2: TagKey<Item>,
    suffix: String = "",
    id: ResourceLocation = output.id.withSuffix(suffix)
) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output)
        .requires(Ingredient.of(input1))
        .requires(Ingredient.of(input2))
        .criterion(input1)
        .criterion(input2)
        .save(this, id)
}

fun RecipeOutput.createSmallSquare(output: ItemLike, input: ItemLike, count: Int = 1) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
        .define('#', input)
        .pattern("##")
        .pattern("##")
        .criterion(input)
        .save(this)
}

fun RecipeOutput.createFullSquare(output: ItemLike, input: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
        .define('#', input)
        .pattern("##")
        .pattern("##")
        .criterion(input)
        .save(this)
}

fun RecipeOutput.createCount(output: ItemLike, input: ItemLike, countOutput: Int) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, countOutput)
        .requires(input)
        .criterion(input)
        .save(this)
}


fun RecipeOutput.offerReversibleCompactingRecipes4(
    reverseCategory: RecipeCategory,
    baseItem: ItemLike,
    compactingCategory: RecipeCategory,
    compactedItem: ItemLike
) {
    offerReversibleCompactingRecipes4(
        reverseCategory,
        baseItem,
        compactingCategory,
        compactedItem,
        getSimpleRecipeName(compactedItem),
        null as String?,
        getSimpleRecipeName(baseItem),
        null as String?
    )
}

fun RecipeOutput.offerReversibleCompactingRecipes4(
    reverseCategory: RecipeCategory,
    baseItem: ItemLike,
    compactingCategory: RecipeCategory,
    compactedItem: ItemLike,
    compactingId: String,
    compactingGroup: String?,
    reverseId: String,
    reverseGroup: String?
) {
    ShapelessRecipeBuilder.shapeless(reverseCategory, baseItem, 4)
        .requires(compactedItem)
        .group(reverseGroup)
        .unlockedBy(getHasName(compactedItem), has(compactedItem))
        .save(this, ResourceLocation.parse(reverseId))
    ShapedRecipeBuilder.shaped(compactingCategory, compactedItem)
        .define('#', baseItem)
        .pattern("##")
        .pattern("##")
        .group(compactingGroup)
        .unlockedBy(getHasName(baseItem), has(baseItem))
        .save(this, ResourceLocation.parse(compactingId))
}

fun RecipeOutput.createStonecuttedSet(
    input: List<Block>,
    polish: ItemLike?,
    stair: ItemLike?,
    slab: ItemLike?,
    wall: ItemLike?,
    extra: List<ItemLike>? = null
) {
    input.forEach {
        if (polish != null && polish.asItem().description.string != it.asItem().description.string)
            stonecutterResultFromBase(this, RecipeCategory.BUILDING_BLOCKS, polish, it)
        if (stair != null) stonecutterResultFromBase(
            this,
            RecipeCategory.BUILDING_BLOCKS,
            stair,
            it
        )
        if (slab != null) stonecutterResultFromBase(
            this,
            RecipeCategory.BUILDING_BLOCKS,
            slab,
            it,
            2
        )
        if (wall != null) stonecutterResultFromBase(this, RecipeCategory.DECORATIONS, wall, it)
        extra?.forEach { special ->
            if (special.asItem().description.string != it.asItem().description.string) {
                stonecutterResultFromBase(
                    this,
                    RecipeCategory.BUILDING_BLOCKS,
                    special,
                    it
                )
            }
        }
    }
}

fun RecipeOutput.createStonecuttedFromList(output: ItemLike, vararg input: Block) =
    this.createStonecuttedFromList(input.toList(), output)

fun RecipeOutput.createStonecuttedFromList(
    input: List<Block>,
    output: ItemLike
) {
    input.forEach {
        stonecutterResultFromBase(this, RecipeCategory.BUILDING_BLOCKS, output, it)
    }
}

fun RecipeOutput.createStackedCraft(output: ItemLike, block: ItemLike, itemTag: TagKey<Item>) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .define('#', block)
        .pattern("#")
        .pattern("#")
        .criterion(itemTag)
        .save(this)
}

fun RecipeOutput.createStackedCraft(output: ItemLike, block1: ItemLike, block2: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 1)
        .define('#', block1)
        .define('%', block2)
        .pattern("#")
        .pattern("%")
        .criterion(block1)
        .save(this)
}

fun RecipeOutput.createStackedCraft(output: ItemLike, block: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .define('#', block)
        .pattern("#")
        .pattern("#")
        .criterion(block)
        .save(this)
}

fun RecipeOutput.createDoubleCraft(output: ItemLike, input1: ItemLike, input2: ItemLike) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .requires(input1)
        .requires(input2)
        .criterion(input1)
        .save(this)
}

fun RecipeOutput.create1to4(output: ItemLike, input1: ItemLike) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, output, 4)
        .requires(input1)
        .criterion(input1)
        .save(this)
}

fun RecipeOutput.createStair(output: ItemLike, block: ItemLike) {
    createStair(output, block, block)
}

fun RecipeOutput.createSlab(output: ItemLike, block: ItemLike) {
    createSlab(output, block, block)
}

fun RecipeOutput.createWall(output: ItemLike, block: ItemLike) {
    createWall(output, block, block)
}

fun RecipeOutput.createFence(output: ItemLike, block: ItemLike) {
    createFence(output, block, block)
}

fun RecipeOutput.createStair(output: ItemLike, block: ItemLike, item: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
        .define('#', block)
        .pattern("#  ")
        .pattern("## ")
        .pattern("###")
        .criterion(item)
        .save(this)
}

fun RecipeOutput.createSlab(output: ItemLike, block: ItemLike, item: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
        .define('#', block)
        .pattern("###")
        .criterion(item)
        .save(this)
}

fun RecipeOutput.createWall(output: ItemLike, block: ItemLike, item: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
        .define('#', block)
        .pattern("###")
        .pattern("###")
        .criterion(item)
        .save(this)
}

fun RecipeOutput.createFence(output: ItemLike, block: ItemLike, item: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .define('#', block)
        .define('+', item)
        .pattern("#+#")
        .pattern("#+#")
        .criterion(item)
        .save(this)
}

fun RecipeOutput.createDiagonalRecipe(
    output: ItemLike,
    primary: ItemLike,
    secondary: ItemLike,
) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .define('#', primary)
        .define('%', secondary)
        .pattern("#%")
        .pattern("%#")
        .criterion(primary)
        .save(this)
}

fun RecipeOutput.createDiagonalRecipe(
    output: ItemLike,
    primary: TagKey<Item>,
    secondary: ItemLike
) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
        .define('#', primary)
        .define('%', secondary)
        .pattern("#%")
        .pattern("%#")
        .criterion(primary)
        .save(this)
}

fun RecipeOutput.createPiles(output: ItemLike, input: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output, 8)
        .define('#', Ingredient.of(input))
        .pattern("##")
        .criterion(input)
        .save(this)
}

// TODO stuff for VV

fun RecipeOutput.createStoneWall(wall: Block, source: Block) {
    createWall(wall, source)
    createStonecutting(wall, source)
}


fun RecipeOutput.createStoneStairs(stairs: Block, source: Block) {
    createStair(stairs, source)
    createStonecutting(stairs, source)
}

fun RecipeOutput.create2x2(output: ItemLike, input: ItemLike, count: Int = 4) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, count)
        .pattern("##")
        .pattern("##")
        .define('#', input)
        .criterion(input)
        .save(this)
}

fun RecipeOutput.compositeBlock(full: ItemLike, part: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, full, 1)
        .pattern("###")
        .pattern("# #")
        .pattern("###")
        .define('#', part)
        .criterion(part)
        .save(this)
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, part, 8)
        .requires(full)
        .criterion(full)
        .save(this)
}

fun RecipeOutput.lantern(lantern: ItemLike, torch: ItemLike) {
    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, lantern)
        .pattern("XXX")
        .pattern("X#X")
        .pattern("XXX")
        .define('#', torch)
        .define('X', Items.IRON_NUGGET)
        .criterion(Items.IRON_NUGGET)
        .criterion(Items.IRON_INGOT)
        .save(this)
}

fun RecipeOutput.bookshelf(
    bookshelf: ItemLike, planks: ItemLike, id: ResourceLocation = getDefaultRecipeId(bookshelf),
) {
    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf)
        .pattern("###")
        .pattern("XXX")
        .pattern("###")
        .define('#', planks)
        .define('X', Items.BOOK)
        .criterion(Items.BOOK)
        .save(this, id)
}

fun RecipeOutput.carpetPlate(plate: ItemLike, carpet: ItemLike) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, plate)
        .requires(ItemTags.WOODEN_PRESSURE_PLATES)
        .requires(carpet)
        .criterion(ItemTags.WOODEN_PRESSURE_PLATES)
        .save(this)
}
