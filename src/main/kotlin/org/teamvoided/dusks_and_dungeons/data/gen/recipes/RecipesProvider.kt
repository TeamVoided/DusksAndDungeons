package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.block.DnDFamilies.recipesBlockFamilies
import org.teamvoided.dusks_and_dungeons.data.gen.data.recipe.WoodRecipes
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.CASCADE_WOOD
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SETS
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.STRIPPED_CASCADE_WOOD
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.cobbled
import org.teamvoided.dusks_and_dungeons.util.datagen.criterion
import org.teamvoided.voidlib.devin.extensions.recipe.createSet
import java.util.concurrent.CompletableFuture

class RecipesProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricRecipeProvider(o, r) {
    override fun buildRecipes(e: RecipeOutput) {
        recipesBlockFamilies.forEach { generateRecipes(e, it, FeatureFlags.DEFAULT_FLAGS) }
        SETS.filterNot { it == CASCADE_WOOD || it == STRIPPED_CASCADE_WOOD }.forEach(e::createSet)

        WoodRecipes.build(e)
        BigRecipes.build(e)
        StoneRecipes.generateStoneRecipes(e)
        NetherRecipes.generateNetherRecipes(e)
        FloraRecipes.generateFloraRecipes(e)
        e.cobbled()

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