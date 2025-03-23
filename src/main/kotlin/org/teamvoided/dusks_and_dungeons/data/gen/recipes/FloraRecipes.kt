package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.RecipesProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeJsonFactory.getItemId
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object FloraRecipes {
    fun generateFloraRecipes(e: RecipeExporter) {
        e.createPiles(DnDBlocks.BAMBOO_PILE, Blocks.BAMBOO_BLOCK)
        e.createPiles(DnDBlocks.STRIPPED_BAMBOO_PILE, Blocks.STRIPPED_BAMBOO_BLOCK)
        DnDBlockLists.logPiles.forEachIndexed { idx, pile ->
            e.createPiles(pile, DnDBlockLists.logsAndStrippedLogs[idx].first)
        }
        DnDBlockLists.leafPiles.forEachIndexed { idx, pile ->
            e.createPiles(pile, DnDBlockLists.leaves[idx])
        }
        FabricRecipeProvider.offerShapelessRecipe(e, Items.BLUE_DYE, DnDBlocks.BLUE_PETALS, "blue_dye")
        FabricRecipeProvider.offerShapelessRecipe(e, Items.PURPLE_DYE, DnDItems.MOONBERRIES, "purple_dye")

        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDBlocks.ROOT_BLOCK)
            .ingredient('#', Blocks.HANGING_ROOTS)
            .pattern("##")
            .pattern("##")
            .criterion(Blocks.HANGING_ROOTS).offerTo(e)
        e.createCount(Blocks.HANGING_ROOTS, DnDBlocks.ROOT_BLOCK, 4)

        pumpkins(e)
        corn(e)
    }

    fun pumpkins(e: RecipeExporter) {
        e.createDoubleCraft(DnDBlocks.SMALL_GLOWING_PUMPKIN, DnDBlocks.SMALL_CARVED_PUMPKIN, Items.TORCH)
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, Items.PUMPKIN_SEEDS, 2)
            .ingredient(DnDBlocks.SMALL_PUMPKIN)
            .criterion(DnDBlocks.SMALL_PUMPKIN)
            .offerTo(e)

        e.createPumpkinStuffs(
            DnDBlocks.LANTERN_PUMPKIN, DnDBlocks.CARVED_LANTERN_PUMPKIN, DnDBlocks.GLOWING_LANTERN_PUMPKIN,
            DnDBlocks.SMALL_LANTERN_PUMPKIN, DnDBlocks.SMALL_CARVED_LANTERN_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_LANTERN_PUMPKIN, DnDItems.LANTERN_PUMPKIN_SEEDS
        )
        e.createPumpkinStuffs(
            DnDBlocks.MOSSKIN_PUMPKIN, DnDBlocks.CARVED_MOSSKIN_PUMPKIN, DnDBlocks.GLOWING_MOSSKIN_PUMPKIN,
            DnDBlocks.SMALL_MOSSKIN_PUMPKIN, DnDBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN, DnDItems.MOSSKIN_PUMPKIN_SEEDS
        )
        e.createPumpkinStuffs(
            DnDBlocks.PALE_PUMPKIN, DnDBlocks.CARVED_PALE_PUMPKIN, DnDBlocks.GLOWING_PALE_PUMPKIN,
            DnDBlocks.SMALL_PALE_PUMPKIN, DnDBlocks.SMALL_CARVED_PALE_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_PALE_PUMPKIN, DnDItems.PALE_PUMPKIN_SEEDS,
        )
        e.createPumpkinStuffs(
            DnDBlocks.GLOOM_PUMPKIN, DnDBlocks.CARVED_GLOOM_PUMPKIN, DnDBlocks.GLOWING_GLOOM_PUMPKIN,
            DnDBlocks.SMALL_GLOOM_PUMPKIN, DnDBlocks.SMALL_CARVED_GLOOM_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_GLOOM_PUMPKIN, DnDItems.GLOOM_PUMPKIN_SEEDS,
        )
    }

    fun corn(e: RecipeExporter) {
        e.createCount(DnDItems.CORN_KERNELS, DnDItems.CORN, 1)

        e.smeltDefault(DnDBlocks.CORN_SYRUP_BLOCK, DnDBlocks.CORN_BLOCK)
        ShapelessRecipeJsonFactory.create(RecipeCategory.FOOD, DnDItems.CORN_SYRUP_BOTTLE, 4)
            .ingredient(DnDBlocks.CORN_SYRUP_BLOCK)
            .ingredient(Items.GLASS_BOTTLE, 4)
            .criterion(DnDBlocks.CORN_SYRUP_BLOCK)
            .offerTo(e)
        RecipesProvider.offerTwoByTwoCompactingRecipe(
            e,
            RecipeCategory.REDSTONE,
            DnDBlocks.CORN_SYRUP_BLOCK,
            DnDItems.CORN_SYRUP_BOTTLE
        )

        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDItems.CORN, 6)
            .ingredient(DnDBlocks.CORN)
            .criterion(DnDBlocks.CORN)
            .offerTo(e, getItemId(DnDItems.CORN).suffix("_from_plant"))

        RecipesProvider.offerReversibleCompactingRecipes(
            e,
            RecipeCategory.MISC, DnDItems.CORN,
            RecipeCategory.BUILDING_BLOCKS, DnDBlocks.CORN_BLOCK
        )
    }
}