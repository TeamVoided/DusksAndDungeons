package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeCategory
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.criterion

object WoodRecipes {
    fun generateWoodRecipes(e: RecipeExporter) {
        e.cascade()
        e.woodWalls()
    }

    private fun RecipeExporter.cascade() {
        FabricRecipeProvider.offerPlanksRecipe(this, DnDBlocks.CASCADE_PLANKS, DnDItemTags.CASCADE_LOGS, 4)
        FabricRecipeProvider.offerBarkBlockRecipe(this, DnDBlocks.CASCADE_WOOD, DnDBlocks.CASCADE_LOG)
        FabricRecipeProvider.offerBarkBlockRecipe(this, DnDBlocks.STRIPPED_CASCADE_WOOD, DnDBlocks.STRIPPED_CASCADE_LOG)
        FabricRecipeProvider.offerHangingSignRecipe(this, DnDItems.CASCADE_HANGING_SIGN, DnDBlocks.STRIPPED_CASCADE_LOG)
        this.woodWall(DnDBlocks.CASCADE_WALL, DnDBlocks.CASCADE_PLANKS)
        ShapedRecipeJsonFactory.create(RecipeCategory.REDSTONE, DnDBlocks.BLUE_DOOR, 3)
            .ingredient('#', Ingredient.ofItems(DnDBlocks.CASCADE_PLANKS.asItem()))
            .ingredient('@', Ingredient.ofItems(Items.GOLD_NUGGET))
            .pattern("## ")
            .pattern("##@")
            .pattern("## ")
            .criterion(DnDBlocks.CASCADE_PLANKS.asItem())
            .offerTo(this)
    }

    private fun RecipeExporter.woodWalls() {
        this.woodWall(DnDBlocks.OAK_WALL, Blocks.OAK_PLANKS)
        this.woodWall(DnDBlocks.SPRUCE_WALL, Blocks.SPRUCE_PLANKS)
        this.woodWall(DnDBlocks.BIRCH_WALL, Blocks.BIRCH_PLANKS)
        this.woodWall(DnDBlocks.JUNGLE_WALL, Blocks.JUNGLE_PLANKS)
        this.woodWall(DnDBlocks.ACACIA_WALL, Blocks.ACACIA_PLANKS)
        this.woodWall(DnDBlocks.DARK_OAK_WALL, Blocks.DARK_OAK_PLANKS)
        this.woodWall(DnDBlocks.MANGROVE_WALL, Blocks.MANGROVE_PLANKS)
        this.woodWall(DnDBlocks.CHERRY_WALL, Blocks.CHERRY_PLANKS)
        this.woodWall(DnDBlocks.CRIMSON_WALL, Blocks.CRIMSON_PLANKS)
        this.woodWall(DnDBlocks.WARPED_WALL, Blocks.WARPED_PLANKS)
        this.woodWall(DnDBlocks.BAMBOO_WALL, Blocks.BAMBOO_PLANKS)
    }

    private fun RecipeExporter.woodWall(wall: ItemConvertible, plank: ItemConvertible) {
        return ShapedRecipeJsonFactory
            .create(RecipeCategory.DECORATIONS, wall, 6)
            .ingredient('#', plank)
            .pattern("###")
            .pattern("# #")
            .criterion(plank)
            .offerTo(this)
    }
}