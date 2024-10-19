package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.feature_flags.FeatureFlags
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import org.teamvoided.dusk_autumn.block.DnDFamilies.recipesBlockFamilies
import org.teamvoided.dusk_autumn.data.gen.providers.recipes.*
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.util.*
import java.util.concurrent.CompletableFuture

class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {
        recipesBlockFamilies.forEach { generateFamily(e, it, FeatureFlags.VANILLA_SET) }

        WoodRecipes.generateWoodRecipes(e)
        BigRecipes.generateBigRecipes(e)
        StoneRecipes.generateStoneRecipes(e)
        NetherRecipes.generateNetherRecipes(e)
        FloraRecipes.generateFloraRecipes(e)
        e.cobbled()

        temporaryRecipes(e)

        ShapedRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.FARMERS_HAT)
            .ingredient('#', Ingredient.ofItems(Items.WHEAT))
            .ingredient('@', Ingredient.ofItems(Items.STRING))
            .ingredient('%', Ingredient.ofItems(Items.LEATHER))
            .pattern("###")
            .pattern("@%@")
            .pattern("# #")
            .criterion(DnDItems.FARMERS_HAT).offerTo(e)
    }

    private fun temporaryRecipes(e: RecipeExporter) {
        ShapelessRecipeJsonFactory(RecipeCategory.MISC, DnDBlocks.CHEST_O_SOULS, 1)
            .ingredient(Items.CHEST)
            .ingredient(Items.SOUL_LANTERN)
            .criterion(DnDBlocks.CHEST_O_SOULS).offerTo(e)

        createStonecuttingRecipe(
            e, RecipeCategory.BUILDING_BLOCKS,
            DnDFloraBlocks.SMALL_PUMPKIN,
            Blocks.PUMPKIN,
            8
        )
        createStonecuttingRecipe(
            e, RecipeCategory.BUILDING_BLOCKS,
            DnDFloraBlocks.SMALL_LANTERN_PUMPKIN,
            DnDFloraBlocks.LANTERN_PUMPKIN,
            8
        )
        createStonecuttingRecipe(
            e, RecipeCategory.BUILDING_BLOCKS,
            DnDFloraBlocks.SMALL_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.MOSSKIN_PUMPKIN,
            8
        )
        createStonecuttingRecipe(
            e, RecipeCategory.BUILDING_BLOCKS,
            DnDFloraBlocks.SMALL_PALE_PUMPKIN,
            DnDFloraBlocks.PALE_PUMPKIN,
            8
        )
        createStonecuttingRecipe(
            e, RecipeCategory.BUILDING_BLOCKS,
            DnDFloraBlocks.SMALL_GLOOM_PUMPKIN,
            DnDFloraBlocks.GLOOM_PUMPKIN,
            8
        )
    }


//    private fun generateWinterRecipes(e: RecipeExporter) {
//        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.CHILL_CHARGE, 4)
//            .ingredient(DnDItems.FREEZE_ROD)
//            .criterion(DnDItems.FREEZE_ROD).offerTo(e)
//    }

}