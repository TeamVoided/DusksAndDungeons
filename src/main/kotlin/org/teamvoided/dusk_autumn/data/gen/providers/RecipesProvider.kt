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

        temporaryRecipes(e)

        ShapedRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.FARMERS_HAT)
            .ingredient('#', Ingredient.ofItems(Items.WHEAT))
            .ingredient('@', Ingredient.ofItems(Items.STRING))
            .ingredient('%', Ingredient.ofItems(Items.LEATHER))
            .pattern("###")
            .pattern("@%@")
            .pattern("# #")
            .criterion(DnDItems.FARMERS_HAT).offerTo(e)

        e.createPumpkinPie(
            DnDItems.LANTERN_PUMPKIN_PIE,
            DnDFloraBlocks.LANTERN_PUMPKIN,
            DnDFloraBlocks.CARVED_LANTERN_PUMPKIN
        )
        e.createPumpkinPie(
            DnDItems.PALE_PUMPKIN_PIE,
            DnDFloraBlocks.PALE_PUMPKIN,
            DnDFloraBlocks.CARVED_PALE_PUMPKIN
        )
        e.createPumpkinPie(
            DnDItems.GLOOM_PUMPKIN_PIE,
            DnDFloraBlocks.GLOOM_PUMPKIN,
            DnDFloraBlocks.CARVED_GLOOM_PUMPKIN
        )
    }

    private fun temporaryRecipes(e: RecipeExporter) {
        createStonecuttingRecipe(
            e, RecipeCategory.BUILDING_BLOCKS,
            Blocks.PUMPKIN,
            DnDFloraBlocks.SMALL_PUMPKIN
        )
        createStonecuttingRecipe(
            e, RecipeCategory.BUILDING_BLOCKS,
            DnDFloraBlocks.LANTERN_PUMPKIN,
            DnDFloraBlocks.SMALL_LANTERN_PUMPKIN
        )
        createStonecuttingRecipe(
            e, RecipeCategory.BUILDING_BLOCKS,
            DnDFloraBlocks.PALE_PUMPKIN,
            DnDFloraBlocks.SMALL_PALE_PUMPKIN
        )
        createStonecuttingRecipe(
            e, RecipeCategory.BUILDING_BLOCKS,
            DnDFloraBlocks.GLOOM_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOOM_PUMPKIN
        )
    }


//    private fun generateWinterRecipes(e: RecipeExporter) {
//        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.CHILL_CHARGE, 4)
//            .ingredient(DnDItems.FREEZE_ROD)
//            .criterion(DnDItems.FREEZE_ROD).offerTo(e)
//    }

}