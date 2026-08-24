package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.Blocks.BOOKSHELF
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.mc
import org.teamvoided.dusks_and_dungeons.block.DnDFamilies.recipesBlockFamilies
import org.teamvoided.dusks_and_dungeons.data.gen.data.recipe.StoneRecipes
import org.teamvoided.dusks_and_dungeons.data.gen.data.recipe.WoodRecipes
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.voidlib.consortium.block.color.VanillaColorCollections
import org.teamvoided.voidlib.devin.extensions.recipe.createSet
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecutting
import org.teamvoided.voidlib.devin.extensions.recipe.createStonecuttingSet
import java.util.concurrent.CompletableFuture

class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun buildRecipes(e: RecipeOutput) {
        recipesBlockFamilies.forEach { generateRecipes(e, it, FeatureFlags.DEFAULT_FLAGS) }
        SETS.forEach(e::createSet)

        WoodRecipes.build(e)
        BigRecipes.build(e)
        StoneRecipes.generateStoneRecipes(e)
        NetherRecipes.generateNetherRecipes(e)
        FloraRecipes.generateFloraRecipes(e)

        MinecraftRecipeOverrides.generate(e)

        temporaryRecipes(e)

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DnDItems.FARMERS_HAT)
            .define('#', Ingredient.of(Items.WHEAT))
            .define('@', Ingredient.of(Items.STRING))
            .define('%', Ingredient.of(Items.LEATHER))
            .pattern("###")
            .pattern("@%@")
            .pattern("# #")
            .criterion(DnDItems.FARMERS_HAT)
            .save(e)

        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, DnDItems.TINTED_GLASS_BOTTLE, 3)
            .pattern("# #")
            .pattern(" # ")
            .define('#', Blocks.TINTED_GLASS)
            .criterion(Blocks.TINTED_GLASS)
            .save(e)

        // TODO sort VV recipes

        // Missing Sets
        e.createStonecuttingSet(DnDBlocks.QUARTZ_BRICK_SET, Blocks.QUARTZ_BLOCK)

        e.createStonecuttingSet(DnDBlocks.ROUGH_SANDSTONE, Blocks.SANDSTONE)
        e.createStonecuttingSet(DnDBlocks.ROUGH_RED_SANDSTONE, Blocks.RED_SANDSTONE)

        e.create2x2(DnDBlocks.POLISHED_SANDSTONE, Blocks.CUT_SANDSTONE)
        e.createStonecuttingSet(DnDBlocks.POLISHED_SANDSTONE, Blocks.SANDSTONE)
        e.createStonecuttingSet(DnDBlocks.POLISHED_RED_SANDSTONE, Blocks.RED_SANDSTONE)
        e.create2x2(DnDBlocks.POLISHED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE)
        e.createStonecuttingSet(DnDBlocks.POLISHED_SANDSTONE, Blocks.CUT_SANDSTONE)
        e.createStonecuttingSet(DnDBlocks.POLISHED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE)

        // Pairs
        e.createStoneStairs(DnDBlocks.SMOOTH_STONE_STAIR, Blocks.SMOOTH_STONE)
        e.createStoneWall(DnDBlocks.SMOOTH_STONE_WALL, Blocks.SMOOTH_STONE)
        e.createStoneStairs(DnDBlocks.CUT_SANDSTONE_STAIR, Blocks.CUT_SANDSTONE)
        e.createStoneWall(DnDBlocks.CUT_SANDSTONE_WALL, Blocks.CUT_SANDSTONE)
        e.createStoneStairs(DnDBlocks.CUT_RED_SANDSTONE_STAIR, Blocks.CUT_RED_SANDSTONE)
        e.createStoneWall(DnDBlocks.CUT_RED_SANDSTONE_WALL, Blocks.CUT_RED_SANDSTONE)
        // Walls
        e.createStoneWall(DnDBlocks.STONE_WALL, Blocks.STONE)
        e.createStoneWall(DnDBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE)
        e.createStoneWall(DnDBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE)
        e.createStoneWall(DnDBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE)
        e.createStoneWall(DnDBlocks.SMOOTH_SANDSTONE_WALL, Blocks.SMOOTH_SANDSTONE)
        e.createStoneWall(DnDBlocks.SMOOTH_RED_SANDSTONE_WALL, Blocks.SMOOTH_RED_SANDSTONE)
        e.createStoneWall(DnDBlocks.PRISMARINE_BRICKS_WALL, Blocks.PRISMARINE_BRICKS)
        e.createStoneWall(DnDBlocks.DARK_PRISMARINE_WALL, Blocks.DARK_PRISMARINE)
        e.createStoneWall(DnDBlocks.PURPUR_WALL, Blocks.PURPUR_BLOCK)
        e.createStoneWall(DnDBlocks.QUARTZ_WALL, Blocks.QUARTZ_BLOCK)
        e.createStoneWall(DnDBlocks.SMOOTH_QUARTZ_WALL, Blocks.SMOOTH_QUARTZ)
        // other
        e.createFence(DnDBlocks.BRICK_FENCE, Blocks.BRICKS, Items.BRICK)
        e.createStonecutting(DnDBlocks.BRICK_FENCE, Blocks.BRICKS)
        e.compositeBlock(DnDBlocks.HEAVY_CUBE, Blocks.HEAVY_CORE)

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DnDBlocks.TINTED_GLASS_PANE, 16)
            .define('#', Blocks.TINTED_GLASS)
            .pattern("###")
            .pattern("###")
            .unlockedBy("has_glass", has(Blocks.TINTED_GLASS))
            .save(e)

        e.lantern(DnDBlocks.REDSTONE_LANTERN, Items.REDSTONE_TORCH)

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Blocks.LAPIS_BLOCK), RecipeCategory.BUILDING_BLOCKS, DnDBlocks.SMOOTH_LAPIS, 0.1f, 200
        )
            .criterion(Blocks.LAPIS_BLOCK)
            .save(e)

        // Bookshelf
        e.bookshelf(BOOKSHELF, Blocks.OAK_PLANKS, mc("oak_bookshelf")) // TODO(maybe) make this gen under mc for real
        e.bookshelf(DnDBlocks.SPRUCE_BOOKSHELF, Blocks.SPRUCE_PLANKS)
        e.bookshelf(DnDBlocks.BIRCH_BOOKSHELF, Blocks.BIRCH_PLANKS)
        e.bookshelf(DnDBlocks.JUNGLE_BOOKSHELF, Blocks.JUNGLE_PLANKS)
        e.bookshelf(DnDBlocks.ACACIA_BOOKSHELF, Blocks.ACACIA_PLANKS)
        e.bookshelf(DnDBlocks.DARK_OAK_BOOKSHELF, Blocks.DARK_OAK_PLANKS)
        e.bookshelf(DnDBlocks.MANGROVE_BOOKSHELF, Blocks.MANGROVE_PLANKS)
        e.bookshelf(DnDBlocks.CHERRY_BOOKSHELF, Blocks.CHERRY_PLANKS)
        e.bookshelf(DnDBlocks.BAMBOO_BOOKSHELF, Blocks.BAMBOO_PLANKS)
        e.bookshelf(DnDBlocks.CRIMSON_BOOKSHELF, Blocks.CRIMSON_PLANKS)
        e.bookshelf(DnDBlocks.WARPED_BOOKSHELF, Blocks.WARPED_PLANKS)
        // Carpet Plate
        for ((idx, block) in DnDBlocks.WOOL_CARPET_PLATE.withIndex()) {
            e.carpetPlate(block, VanillaColorCollections.WOOL.list[idx])
        }
        e.carpetPlate(DnDBlocks.MOSS_CARPET_PLATE, Blocks.MOSS_CARPET)
    }

    private fun temporaryRecipes(e: RecipeOutput) {
        /* ShapelessRecipeJsonFactory(RecipeCategory.MISC, DnDBlocks.CHEST_O_SOULS, 1)
             .ingredient(Items.CHEST)
             .ingredient(Items.SOUL_LANTERN)
             .criterion(DnDBlocks.CHEST_O_SOULS).offerTo(e)*/

        stonecutterResultFromBase(
            e, RecipeCategory.BUILDING_BLOCKS,
            DnDBlocks.SMALL_PUMPKIN,
            Blocks.PUMPKIN,
            4
        )
    }


//    private fun generateWinterRecipes(e: RecipeExporter) {
//        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.CHILL_CHARGE, 4)
//            .ingredient(DnDItems.FREEZE_ROD)
//            .criterion(DnDItems.FREEZE_ROD).offerTo(e)
//    }

}