package org.teamvoided.dusks_and_dungeons.recipe.builder

import com.mojang.datafixers.util.Pair
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.Criterion
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import org.teamvoided.dusks_and_dungeons.recipe.MixedRecipePattern
import org.teamvoided.dusks_and_dungeons.recipe.MixedShapedRecipe


class MixedRecipeBuilder(
    private val category: RecipeCategory,
    result: ItemLike,
    private val count: Int,
) : RecipeBuilder {

    private val result = result.asItem()
    private val rows = mutableListOf<String>()
    private val key = mutableMapOf<Char, Pair<Boolean, Ingredient>>()
    private val criteria = mutableMapOf<String, Criterion<*>>()
    private var group: String? = null
    private var showNotification = true

    fun define(character: Char, tag: TagKey<Item>, enforceMixed: Boolean = false): MixedRecipeBuilder {
        return define(character, Ingredient.of(tag), enforceMixed)
    }

    fun define(character: Char, item: ItemLike, enforceMixed: Boolean = false): MixedRecipeBuilder {
        return define(character, Ingredient.of(item), enforceMixed)
    }

    fun define(character: Char, ingredient: Ingredient, enforceMixed: Boolean = false): MixedRecipeBuilder {
        require(!key.containsKey(character)) { "Symbol '$character' is already defined!" }

        require(character != ' ') { "Symbol ' ' (whitespace) is reserved and cannot be defined" }

        key[character] = Pair.of(enforceMixed, ingredient)
        return this
    }

    fun pattern(string: String): MixedRecipeBuilder {
        require(!(rows.isNotEmpty() && string.length != rows[0].length)) { "Pattern must be the same width on every line!" }

        rows.add(string)
        return this
    }

    override fun unlockedBy(string: String, criterion: Criterion<*>): MixedRecipeBuilder {
        criteria[string] = criterion
        return this
    }

    override fun group(string: String?): MixedRecipeBuilder {
        group = string
        return this
    }

    fun showNotification(bl: Boolean): MixedRecipeBuilder {
        showNotification = bl
        return this
    }

    override fun getResult(): Item {
        return result
    }

    override fun save(recipeOutput: RecipeOutput, id: ResourceLocation) {
        val pattern = ensureValid(id)
        val advancement = recipeOutput.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
            .rewards(AdvancementRewards.Builder.recipe(id))
            .requirements(AdvancementRequirements.Strategy.OR)
        criteria.forEach { (name, criterion) -> advancement.addCriterion(name, criterion) }
        val recipe = MixedShapedRecipe(
            group ?: "",
            RecipeBuilder.determineBookCategory(category),
            pattern,
            ItemStack(result, count),
            showNotification
        )
        recipeOutput.accept(id, recipe, advancement.build(id.withPrefix("recipes/${category.folderName}/")))
    }

    private fun ensureValid(id: ResourceLocation): MixedRecipePattern {
        check(criteria.isNotEmpty()) { "No way of obtaining recipe $id" }
        return MixedRecipePattern.of(key, rows)
    }

    companion object {

        @JvmOverloads
        fun mixed(category: RecipeCategory, result: ItemLike, i: Int = 1): MixedRecipeBuilder {
            return MixedRecipeBuilder(category, result, i)
        }

    }
}