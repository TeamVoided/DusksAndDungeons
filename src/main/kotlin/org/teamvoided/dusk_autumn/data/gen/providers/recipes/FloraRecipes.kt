package org.teamvoided.dusk_autumn.data.gen.providers.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
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
        ShapelessRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, Blocks.HANGING_ROOTS, 4)
            .ingredient(DnDFloraBlocks.ROOT_BLOCK)
            .criterion(DnDFloraBlocks.ROOT_BLOCK).offerTo(e)
        e.cobbled()
    }
}