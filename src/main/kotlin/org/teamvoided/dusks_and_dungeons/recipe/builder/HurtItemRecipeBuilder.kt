package org.teamvoided.dusks_and_dungeons.recipe.builder

import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.Criterion
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.damagesource.DamageType
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import org.teamvoided.dusks_and_dungeons.recipe.HurtItemRecipe

class HurtItemRecipeBuilder(
    private val ingredient: Ingredient,
    private val damageTypeTag: TagKey<DamageType>,
    itemLike: ItemLike,
) : RecipeBuilder {

    private val result: Item = itemLike.asItem()
    private val criteria: MutableMap<String, Criterion<*>> = LinkedHashMap()
    private var group: String? = null
    private var invulnerableTime = 20

    override fun unlockedBy(string: String, criterion: Criterion<*>): HurtItemRecipeBuilder {
        criteria[string] = criterion
        return this
    }

    override fun group(string: String?): HurtItemRecipeBuilder {
        group = string
        return this
    }

    fun invulnerableTime(ticks: Int): HurtItemRecipeBuilder {
        invulnerableTime = ticks
        return this
    }

    override fun getResult(): Item = result

    override fun save(output: RecipeOutput, id: ResourceLocation) {
        ensureValid(id)
        val advancement = output.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
            .rewards(AdvancementRewards.Builder.recipe(id))
            .requirements(AdvancementRequirements.Strategy.OR)
        criteria.forEach { (string: String, criterion: Criterion<*>) -> advancement.addCriterion(string, criterion) }
        val recipe = HurtItemRecipe(group ?: "", ingredient, damageTypeTag, invulnerableTime, ItemStack(result))
        output.accept(id, recipe, advancement.build(id.withPrefix("recipes/$FOLDER/")))
    }

    fun ensureValid(id: ResourceLocation) {
        check(criteria.isNotEmpty()) { "No way of obtaining recipe $id" }
    }

    companion object {

        const val FOLDER = "hurt_item"

        fun hurtItem(input: Ingredient, damageTypeTag: TagKey<DamageType>, output: ItemLike): HurtItemRecipeBuilder {
            return HurtItemRecipeBuilder(input, damageTypeTag, output)
        }

    }
}