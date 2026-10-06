package org.teamvoided.dusks_and_dungeons.datagen.old.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.Blocks.BOOKSHELF
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.DnDFamilies.recipesBlockFamilies
import org.teamvoided.dusks_and_dungeons.datagen.data.recipe.FunctionalRecipes
import org.teamvoided.dusks_and_dungeons.datagen.data.recipe.StoneRecipes
import org.teamvoided.dusks_and_dungeons.datagen.data.recipe.WoodRecipes
import org.teamvoided.dusks_and_dungeons.datagen.old.util.*
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.voidlib.consortium.block.color.VanillaColorCollections
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import org.teamvoided.voidlib.devin.extensions.recipe.createSet
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecutting
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecuttingSet

class RecipesProvider(o: FabricOutput, p: FutureProvider) : FabricRecipeProvider(o, p) {

    override fun buildRecipes(output: RecipeOutput) {
        recipesBlockFamilies.forEach { generateRecipes(output, it, FeatureFlags.DEFAULT_FLAGS) }
        SETS.forEach(output::createSet)

        WoodRecipes.build(output)
        FunctionalRecipes.build(output)
        StoneRecipes.generateStoneRecipes(output)
        NetherRecipes.generateNetherRecipes(output)
        FloraRecipes.generateFloraRecipes(output)

        MinecraftRecipeOverrides.generate(output)

        temporaryRecipes(output)

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DnDItems.FARMERS_HAT)
            .define('#', Ingredient.of(Items.WHEAT))
            .define('@', Ingredient.of(Items.STRING))
            .define('%', Ingredient.of(Items.LEATHER))
            .pattern("###")
            .pattern("@%@")
            .pattern("# #")
            .unlockedBy(DnDItems.FARMERS_HAT)
            .save(output)

        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, DnDItems.TINTED_GLASS_BOTTLE, 3)
            .pattern("# #")
            .pattern(" # ")
            .define('#', Blocks.TINTED_GLASS)
            .unlockedBy(Blocks.TINTED_GLASS)
            .save(output)

        // TODO(1.0) sort VV recipes

        // Missing Sets
        output.createStonecuttingSet(DnDBlocks.QUARTZ_BRICK_SET, Blocks.QUARTZ_BLOCK)

        output.createStonecuttingSet(DnDBlocks.ROUGH_SANDSTONE, Blocks.SANDSTONE)
        output.createStonecuttingSet(DnDBlocks.ROUGH_RED_SANDSTONE, Blocks.RED_SANDSTONE)

        output.create2x2(DnDBlocks.POLISHED_SANDSTONE, Blocks.CUT_SANDSTONE)
        output.createStonecuttingSet(DnDBlocks.POLISHED_SANDSTONE, Blocks.SANDSTONE)
        output.createStonecuttingSet(DnDBlocks.POLISHED_RED_SANDSTONE, Blocks.RED_SANDSTONE)
        output.create2x2(DnDBlocks.POLISHED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE)
        output.createStonecuttingSet(DnDBlocks.POLISHED_SANDSTONE, Blocks.CUT_SANDSTONE)
        output.createStonecuttingSet(DnDBlocks.POLISHED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE)

        // Pairs
        output.createStoneStairs(DnDBlocks.SMOOTH_STONE_STAIR, Blocks.SMOOTH_STONE)
        output.createStoneWall(DnDBlocks.SMOOTH_STONE_WALL, Blocks.SMOOTH_STONE)
        output.createStoneStairs(DnDBlocks.CUT_SANDSTONE_STAIR, Blocks.CUT_SANDSTONE)
        output.createStoneWall(DnDBlocks.CUT_SANDSTONE_WALL, Blocks.CUT_SANDSTONE)
        output.createStoneStairs(DnDBlocks.CUT_RED_SANDSTONE_STAIR, Blocks.CUT_RED_SANDSTONE)
        output.createStoneWall(DnDBlocks.CUT_RED_SANDSTONE_WALL, Blocks.CUT_RED_SANDSTONE)
        // Walls
        output.createStoneWall(DnDBlocks.STONE_WALL, Blocks.STONE)
        output.createStoneWall(DnDBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE)
        output.createStoneWall(DnDBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE)
        output.createStoneWall(DnDBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE)
        output.createStoneWall(DnDBlocks.SMOOTH_SANDSTONE_WALL, Blocks.SMOOTH_SANDSTONE)
        output.createStoneWall(DnDBlocks.SMOOTH_RED_SANDSTONE_WALL, Blocks.SMOOTH_RED_SANDSTONE)
        output.createStoneWall(DnDBlocks.PRISMARINE_BRICKS_WALL, Blocks.PRISMARINE_BRICKS)
        output.createStoneWall(DnDBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE)
        output.createStoneWall(DnDBlocks.PURPUR_WALL, Blocks.PURPUR_BLOCK)
        output.createStoneWall(DnDBlocks.QUARTZ_WALL, Blocks.QUARTZ_BLOCK)
        output.createStoneWall(DnDBlocks.SMOOTH_QUARTZ_WALL, Blocks.SMOOTH_QUARTZ)
        // other
        output.createFence(DnDBlocks.BRICK_FENCE, Blocks.BRICKS, Items.BRICK)
        output.createStonecutting(DnDBlocks.BRICK_FENCE, Blocks.BRICKS)
        output.compositeBlock(DnDBlocks.HEAVY_CUBE, Blocks.HEAVY_CORE)

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Blocks.LAPIS_BLOCK), RecipeCategory.BUILDING_BLOCKS, DnDBlocks.SMOOTH_LAPIS, 0.1f, 200
        )
            .unlockedBy(Blocks.LAPIS_BLOCK)
            .save(output)

        // Bookshelf
        output.bookshelf(BOOKSHELF, Blocks.OAK_PLANKS, id("oak_bookshelf"))
        output.bookshelf(DnDBlocks.SPRUCE_BOOKSHELF, Blocks.SPRUCE_PLANKS)
        output.bookshelf(DnDBlocks.BIRCH_BOOKSHELF, Blocks.BIRCH_PLANKS)
        output.bookshelf(DnDBlocks.JUNGLE_BOOKSHELF, Blocks.JUNGLE_PLANKS)
        output.bookshelf(DnDBlocks.ACACIA_BOOKSHELF, Blocks.ACACIA_PLANKS)
        output.bookshelf(DnDBlocks.DARK_OAK_BOOKSHELF, Blocks.DARK_OAK_PLANKS)
        output.bookshelf(DnDBlocks.MANGROVE_BOOKSHELF, Blocks.MANGROVE_PLANKS)
        output.bookshelf(DnDBlocks.CHERRY_BOOKSHELF, Blocks.CHERRY_PLANKS)
        output.bookshelf(DnDBlocks.BAMBOO_BOOKSHELF, Blocks.BAMBOO_PLANKS)
        output.bookshelf(DnDBlocks.CRIMSON_BOOKSHELF, Blocks.CRIMSON_PLANKS)
        output.bookshelf(DnDBlocks.WARPED_BOOKSHELF, Blocks.WARPED_PLANKS)
        output.bookshelf(DnDBlocks.CASCADE_BOOKSHELF, DnDBlocks.CASCADE_PLANKS)
        output.bookshelf(DnDBlocks.SYPIA_BOOKSHELF, DnDBlocks.SYPIA_PLANKS)
        output.bookshelf(DnDBlocks.VERDANT_BOOKSHELF, DnDBlocks.VERDANT_PLANKS)
        // Carpet Plate
        for ((idx, block) in DnDBlocks.WOOL_CARPET_PLATE.withIndex()) {
            output.carpetPlate(block, VanillaColorCollections.WOOL.list[idx])
        }
        output.carpetPlate(DnDBlocks.MOSS_CARPET_PLATE, Blocks.MOSS_BLOCK)
        output.carpetPlate(DnDBlocks.OVERGROWTH_CARPET_PLATE, DnDBlocks.OVERGROWTH_BLOCK)

        output.sandRecipes()
    }

    fun RecipeOutput.sandRecipes() {
        create2x2(DnDBlocks.AZURINE_SANDSTONE.parent, DnDBlocks.AZURINE_SAND, 1)

        createStackedCraft(DnDBlocks.CHISELED_AZURINE_SANDSTONE, DnDBlocks.AZURINE_SANDSTONE.slab, 1)
        createStonecutting(DnDBlocks.CHISELED_AZURINE_SANDSTONE, DnDBlocks.AZURINE_SANDSTONE.parent)

        smeltDefault(DnDBlocks.SMOOTH_AZURINE_SANDSTONE, DnDBlocks.AZURINE_SANDSTONE.parent)

        create2x2(DnDBlocks.CUT_AZURINE_SANDSTONE.parent, DnDBlocks.AZURINE_SANDSTONE.parent)
        createStonecuttingSet(DnDBlocks.CUT_AZURINE_SANDSTONE, DnDBlocks.AZURINE_SANDSTONE.parent)

        create2x2(DnDBlocks.POLISHED_AZURINE_SANDSTONE, DnDBlocks.AZURINE_SANDSTONE.parent)
        createStonecuttingSet(DnDBlocks.POLISHED_AZURINE_SANDSTONE, DnDBlocks.AZURINE_SANDSTONE.parent)
        createStonecuttingSet(DnDBlocks.POLISHED_AZURINE_SANDSTONE, DnDBlocks.CUT_AZURINE_SANDSTONE.parent)

        createStonecuttingSet(DnDBlocks.ROUGH_AZURINE_SANDSTONE, DnDBlocks.AZURINE_SANDSTONE.parent)
    }

    private fun temporaryRecipes(output: RecipeOutput) {
        /* ShapelessRecipeJsonFactory(RecipeCategory.MISC, DnDBlocks.CHEST_O_SOULS, 1)
             .ingredient(Items.CHEST)
             .ingredient(Items.SOUL_LANTERN)
             .criterion(DnDBlocks.CHEST_O_SOULS).offerTo(e)*/

        stonecutterResultFromBase(
            output, RecipeCategory.BUILDING_BLOCKS,
            DnDBlocks.SMALL_PUMPKIN,
            Blocks.PUMPKIN,
            4
        )

        /* HurtItemRecipeBuilder.hurtItem(Ingredient.of(Items.APPLE), DamageTypeTags.IS_FIRE, Items.DIAMOND)
             .invulnerableTime(25)
             .criterion(Items.APPLE)
             .save(output, id("crushing_ur_balls"))*/
    }


//    private fun generateWinterRecipes(e: RecipeExporter) {
//        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.CHILL_CHARGE, 4)
//            .ingredient(DnDItems.FREEZE_ROD)
//            .criterion(DnDItems.FREEZE_ROD).offerTo(e)
//    }

}