package org.teamvoided.dusks_and_dungeons.recipe.builder

import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.Criterion
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.dusks_and_dungeons.recipe.UseOnBlockRecipe
import org.teamvoided.dusks_and_dungeons.recipe.UseOnBlockRecipe.Companion.asItemOrEmpty

class UseOnBlockRecipeBuilder(
    private val ingredient: Ingredient,
    private val inputState: BlockState,
    private val resultState: BlockState,
) : RecipeBuilder {

    private val criteria: MutableMap<String, Criterion<*>> = LinkedHashMap()
    private var group: String? = null

    override fun unlockedBy(string: String, criterion: Criterion<*>): UseOnBlockRecipeBuilder {
        criteria[string] = criterion
        return this
    }

    override fun group(string: String?): UseOnBlockRecipeBuilder {
        group = string
        return this
    }

    override fun getResult(): Item = resultState.asItemOrEmpty().item

    override fun save(output: RecipeOutput) {
        save(output, RecipeBuilder.getDefaultRecipeId(resultState.block).withSuffix("_from_use"))
    }

    override fun save(output: RecipeOutput, id: ResourceLocation) {
        ensureValid(id)
        val advancement = output.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
            .rewards(AdvancementRewards.Builder.recipe(id))
            .requirements(AdvancementRequirements.Strategy.OR)
        criteria.forEach { (string: String, criterion: Criterion<*>) -> advancement.addCriterion(string, criterion) }
        val recipe = UseOnBlockRecipe(group ?: "", ingredient, inputState, resultState)
        output.accept(id, recipe, advancement.build(id.withPrefix("recipes/$FOLDER/")))
    }

    fun ensureValid(id: ResourceLocation) {
        check(criteria.isNotEmpty()) { "No way of obtaining recipe $id" }
    }

    companion object {

        const val FOLDER = "use_on_block"

        fun useOnBlock(input: Ingredient, inputState: BlockState, outputState: BlockState): UseOnBlockRecipeBuilder {
            return UseOnBlockRecipeBuilder(input, inputState, outputState)
        }

    }
}