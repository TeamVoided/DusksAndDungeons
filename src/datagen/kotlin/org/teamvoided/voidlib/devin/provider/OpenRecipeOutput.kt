package org.teamvoided.voidlib.devin.provider

import net.fabricmc.fabric.api.datagen.v1.recipe.FabricRecipeExporter
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Recipe

class OpenRecipeOutput(val output: RecipeOutput) : RecipeOutput, FabricRecipeExporter {

    override fun accept(id: ResourceLocation, recipe: Recipe<*>, advancement: AdvancementHolder?) {
        return output.accept(id, recipe, advancement)
    }

    override fun advancement(): Advancement.Builder = output.advancement()

    override fun getRecipeIdentifier(recipeId: ResourceLocation): ResourceLocation = recipeId

}