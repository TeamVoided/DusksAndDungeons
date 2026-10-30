package org.teamvoided.dusks_and_dungeons.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.recipe.CandelabraContentsRecipe
import org.teamvoided.dusks_and_dungeons.recipe.HurtItemRecipe
import org.teamvoided.dusks_and_dungeons.recipe.MixedShapedRecipe
import org.teamvoided.dusks_and_dungeons.recipe.UseOnBlockRecipe
import org.teamvoided.dusks_and_dungeons.util.register
import org.teamvoided.voidlib.world.item.crafting.VRecipeType

object DnDRecipes {

    @JvmField
    val HURT_ITEM = registerType<HurtItemRecipe>("hurt_item")
    val HURT_ITEM_SERIALIZER = registerSerializer("hurt_item", HurtItemRecipe.Serializer())

    @JvmField
    val USE_ON_BLOCK = registerType<UseOnBlockRecipe>("use_on_block")
    val USE_ON_BLOCK_SERIALIZER = registerSerializer("use_on_block", UseOnBlockRecipe.Serializer())

    val MIXED_CRAFTING_SHAPED_SERIALIZER = registerSerializer("mixed_crafting_shaped", MixedShapedRecipe.Serializer())

    val CANDELABRA_CONTENTS = registerSerializer(
        "crafting_special_candelabra_contents", SimpleCraftingRecipeSerializer(::CandelabraContentsRecipe)
    )

    fun init() = Unit

    fun <T : Recipe<*>> registerSerializer(id: String, serializer: RecipeSerializer<T>): RecipeSerializer<T> {
        return BuiltInRegistries.RECIPE_SERIALIZER.register(id(id), serializer)
    }

    fun <T : Recipe<*>> registerType(name: String): RecipeType<T> {
        val id = id(name)
        return BuiltInRegistries.RECIPE_TYPE.register(id, VRecipeType(id))
    }

}