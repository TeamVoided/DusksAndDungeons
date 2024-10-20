package org.teamvoided.dusk_autumn.data.gen.providers.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.RecipesProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeJsonFactory.getItemId
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.RecipeCategory
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks
import org.teamvoided.dusk_autumn.util.*

object FloraRecipes {
    fun generateFloraRecipes(e: RecipeExporter) {
        e.createPiles(DnDWoodBlocks.BAMBOO_PILE, Blocks.BAMBOO_BLOCK)
        e.createPiles(DnDWoodBlocks.STRIPPED_BAMBOO_PILE, Blocks.STRIPPED_BAMBOO_BLOCK)
        (DnDBlockLists.logPiles).forEachIndexed { idx, pile ->
            e.createPiles(pile, DnDBlockLists.logsAndStrippedLogs[idx].first)
        }
        (DnDBlockLists.leafPiles).forEachIndexed { idx, pile ->
            e.createPiles(pile, DnDBlockLists.leaves[idx])
        }
        FabricRecipeProvider.offerShapelessRecipe(e, Items.BLUE_DYE, DnDFloraBlocks.BLUE_PETALS, "blue_dye")
        FabricRecipeProvider.offerShapelessRecipe(e, Items.PURPLE_DYE, DnDItems.MOONBERRIES, "purple_dye")

        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDFloraBlocks.ROOT_BLOCK)
            .ingredient('#', Blocks.HANGING_ROOTS)
            .pattern("##")
            .pattern("##")
            .criterion(Blocks.HANGING_ROOTS).offerTo(e)
        e.createCount(Blocks.HANGING_ROOTS, DnDFloraBlocks.ROOT_BLOCK, 4)

        e.createDoubleCraft(
            DnDFloraBlocks.SMALL_GLOWING_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_PUMPKIN,
            Items.TORCH
        )
        e.createPumpkinStuffs(
            DnDFloraBlocks.LANTERN_PUMPKIN,
            DnDFloraBlocks.CARVED_LANTERN_PUMPKIN,
            DnDFloraBlocks.GLOWING_LANTERN_PUMPKIN,
            DnDFloraBlocks.SMALL_LANTERN_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_LANTERN_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_LANTERN_PUMPKIN,
            DnDItems.LANTERN_PUMPKIN_PIE
        )
        e.createPumpkinStuffs(
            DnDFloraBlocks.MOSSKIN_PUMPKIN,
            DnDFloraBlocks.CARVED_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.GLOWING_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.SMALL_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN,
            DnDItems.MOSSKIN_PUMPKIN_PIE
        )
        e.createPumpkinStuffs(
            DnDFloraBlocks.PALE_PUMPKIN,
            DnDFloraBlocks.CARVED_PALE_PUMPKIN,
            DnDFloraBlocks.GLOWING_PALE_PUMPKIN,
            DnDFloraBlocks.SMALL_PALE_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_PALE_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_PALE_PUMPKIN,
            DnDItems.PALE_PUMPKIN_PIE
        )
        e.createPumpkinStuffs(
            DnDFloraBlocks.GLOOM_PUMPKIN,
            DnDFloraBlocks.CARVED_GLOOM_PUMPKIN,
            DnDFloraBlocks.GLOWING_GLOOM_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOOM_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_GLOOM_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_GLOOM_PUMPKIN,
            DnDItems.GLOOM_PUMPKIN_PIE
        )
        e.createCount(DnDItems.CORN_KERNELS, DnDItems.CORN, 1)
        e.smeltDefault(DnDFloraBlocks.CORN_SYRUP_BLOCK, DnDFloraBlocks.CORN_BLOCK)
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, DnDItems.CORN, 6)
            .ingredient(DnDFloraBlocks.CORN)
            .criterion(DnDFloraBlocks.CORN)
            .offerTo(e, getItemId(DnDItems.CORN).suffix("_from_plant"))
        RecipesProvider.offerReversibleCompactingRecipes(
            e,
            RecipeCategory.MISC, DnDItems.CORN,
            RecipeCategory.BUILDING_BLOCKS, DnDFloraBlocks.CORN_BLOCK
        )
    }
}