package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.recipe.HurtItemRecipe
import org.teamvoided.dusks_and_dungeons.recipe.StrictShapedRecipe
import org.teamvoided.dusks_and_dungeons.util.register

object DnDRecipes {

    @JvmField
    val HURT_ITEM = type<HurtItemRecipe>("hurt_item")
    val HURT_ITEM_SERIALIZER = serializer("hurt_item", HurtItemRecipe.Serializer())

    val STRICT_CRAFTING_SHAPED_SERIALIZER = serializer("strict_crafting_shaped", StrictShapedRecipe.Serializer())

    fun init() = Unit

    fun <T : Recipe<*>> serializer(id: String, serializer: RecipeSerializer<T>): RecipeSerializer<T> {
        return BuiltInRegistries.RECIPE_SERIALIZER.register(id(id), serializer)
    }

    fun <T : Recipe<*>> type(name: String): RecipeType<T> {
        val id = id(name)
        val type = object : RecipeType<T> {
            override fun toString(): String = id.toString()
        }
        return BuiltInRegistries.RECIPE_TYPE.register(id, type)
    }

}