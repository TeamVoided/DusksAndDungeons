package org.teamvoided.dusk_autumn.data.gen.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.feature_flags.FeatureFlags
import net.minecraft.recipe.RecipeCategory
import net.minecraft.registry.HolderLookup
import org.teamvoided.dusk_autumn.block.DnDFamilies.recipesBlockFamilies
import org.teamvoided.dusk_autumn.data.gen.providers.recipes.AutumnRecipes
import org.teamvoided.dusk_autumn.data.gen.providers.recipes.BigRecipes
import org.teamvoided.dusk_autumn.data.gen.providers.recipes.NetherRecipes
import org.teamvoided.dusk_autumn.data.gen.providers.recipes.OtherStoneRecipes
import org.teamvoided.dusk_autumn.data.gen.providers.recipes.WoodRecipes
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.util.*
import java.util.concurrent.CompletableFuture

class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun generateRecipes(e: RecipeExporter) {
        recipesBlockFamilies.forEach { generateFamily(e, it, FeatureFlags.VANILLA_SET) }

        WoodRecipes.generateWoodRecipes(e)
        BigRecipes.generateBigRecipes(e)
        OtherStoneRecipes.generateOtherStoneRecipes(e)
        NetherRecipes.generateNetherRecipes(e)
        AutumnRecipes.generateAutumnRecipes(e)

        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDFloraBlocks.ROOT_BLOCK, 1)
            .ingredient('#', Blocks.HANGING_ROOTS)
            .pattern("##")
            .pattern("##")
            .criterion(Blocks.HANGING_ROOTS).offerTo(e)
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, Blocks.HANGING_ROOTS, 4)
            .ingredient(DnDFloraBlocks.ROOT_BLOCK, 1)
            .criterion(DnDFloraBlocks.ROOT_BLOCK).offerTo(e)
        e.cobbled()
    }



//    private fun generateWinterRecipes(e: RecipeExporter) {
//        ShapelessRecipeJsonFactory.create(RecipeCategory.MISC, DnDItems.CHILL_CHARGE, 4)
//            .ingredient(DnDItems.FREEZE_ROD)
//            .criterion(DnDItems.FREEZE_ROD).offerTo(e)
//    }

}