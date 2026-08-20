package org.teamvoided.dusks_and_dungeons.data.gen.data.recipe

import net.minecraft.data.recipes.*
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.data.gen.data.recipe.helpers.*
import org.teamvoided.dusks_and_dungeons.data.tags.DnDItemTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.DnDBlockLists
import org.teamvoided.dusks_and_dungeons.util.datagen.criterion

object WoodRecipes {

    fun build(output: RecipeOutput) {
        output.cascade()
        output.sypia()
        output.verdant()
        output.woodWalls()
        output.hollowLogs()

        SimpleCookingRecipeBuilder
            .smelting(Ingredient.of(DnDItemTags.HOLLOW_LOGS_THAT_BURN), RecipeCategory.MISC, Items.CHARCOAL, 0.15f, 200)
            .unlockedBy("has_hollow_log", RecipeProvider.has(DnDItemTags.HOLLOW_LOGS_THAT_BURN))
            .save(output)

    }

    fun RecipeOutput.cascade() {
        planksFromLogs(DnDBlocks.CASCADE_PLANKS, DnDItemTags.CASCADE_LOGS)
        woodFromLogs(DnDBlocks.CASCADE_WOOD, DnDBlocks.CASCADE_LOG)
        woodFromLogs(DnDBlocks.STRIPPED_CASCADE_WOOD, DnDBlocks.STRIPPED_CASCADE_LOG)
        hangingSign(DnDItems.CASCADE_HANGING_SIGN, DnDBlocks.STRIPPED_CASCADE_LOG)
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, DnDBlocks.BLUE_DOOR, 3)
            .define('#', Ingredient.of(DnDBlocks.CASCADE_PLANKS.asItem()))
            .define('@', Ingredient.of(Items.GOLD_NUGGET))
            .pattern("## ")
            .pattern("##@")
            .pattern("## ")
            .criterion(DnDBlocks.CASCADE_PLANKS.asItem())
            .save(this)
    }

    fun RecipeOutput.sypia() {
        planksFromLogs(DnDBlocks.SYPIA_PLANKS, DnDItemTags.SYPIA_LOGS)
        woodFromLogs(DnDBlocks.SYPIA_WOOD, DnDBlocks.SYPIA_LOG)
        woodFromLogs(DnDBlocks.STRIPPED_SYPIA_WOOD, DnDBlocks.STRIPPED_SYPIA_LOG)
        hangingSign(DnDItems.SYPIA_HANGING_SIGN, DnDBlocks.STRIPPED_SYPIA_LOG)
    }

    fun RecipeOutput.verdant() {
        planksFromLogs(DnDBlocks.VERDANT_PLANKS, DnDItemTags.VERDANT_LOGS)
        woodFromLogs(DnDBlocks.VERDANT_WOOD, DnDBlocks.VERDANT_LOG)
        woodFromLogs(DnDBlocks.STRIPPED_VERDANT_WOOD, DnDBlocks.STRIPPED_VERDANT_LOG)
        hangingSign(DnDItems.VERDANT_HANGING_SIGN, DnDBlocks.STRIPPED_VERDANT_LOG)
    }

    fun RecipeOutput.woodWalls() {
        DnDBlockLists.plankWalls.forEachIndexed { idx, it ->
            woodWall(it, DnDBlockLists.planks[idx], DnDBlockLists.plankSlabs[idx])
        }
        woodWall(DnDBlocks.BAMBOO_WALL, Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB)
        woodWall(DnDBlocks.BAMBOO_MOSAIC_WALL, Blocks.BAMBOO_MOSAIC, Blocks.BAMBOO_MOSAIC_SLAB)
    }

    fun RecipeOutput.hollowLogs() {
        DnDBlockLists.hollowLogs.forEachIndexed { index, block ->
            hollowLog(block, DnDBlockLists.logsAndStrippedLogs[index].first)
        }
        hollowLog(DnDBlocks.HOLLOW_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK)
        DnDBlockLists.hollowStrippedLogs.forEachIndexed { index, block ->
            hollowLog(block, DnDBlockLists.logsAndStrippedLogs[index].second)
        }
        hollowLog(DnDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK)
    }

}