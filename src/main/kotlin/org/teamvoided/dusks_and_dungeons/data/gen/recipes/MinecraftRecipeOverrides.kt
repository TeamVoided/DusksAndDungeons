package org.teamvoided.dusks_and_dungeons.data.gen.recipes

import net.fabricmc.fabric.api.datagen.v1.recipe.FabricRecipeExporter
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.util.datagen.criterion

object MinecraftRecipeOverrides {

    fun generate(dndOut: RecipeOutput) {
        val o = OpenRecipeOutput(dndOut)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.STONE_BRICKS, 4)
            .define('#', DnDBlocks.POLISHED_STONE)
            .pattern("##")
            .pattern("##")
            .criterion(DnDBlocks.POLISHED_STONE)
            .save(o)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.SNOW, 8)
            .define('#', Blocks.SNOW_BLOCK)
            .pattern("##")
            .criterion(Blocks.SNOW_BLOCK)
            .criterion(Blocks.SNOW)
            .save(o)

    }

}

class OpenRecipeOutput(val output: RecipeOutput) : RecipeOutput, FabricRecipeExporter {
    override fun accept(id: ResourceLocation, recipe: Recipe<*>, advancement: AdvancementHolder?) =
        output.accept(id, recipe, advancement)

    override fun advancement(): Advancement.Builder = output.advancement()

    override fun getRecipeIdentifier(recipeId: ResourceLocation): ResourceLocation = recipeId

}
