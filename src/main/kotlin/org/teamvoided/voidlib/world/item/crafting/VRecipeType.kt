package org.teamvoided.voidlib.world.item.crafting

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType

class VRecipeType<T : Recipe<*>>(val id: ResourceLocation) : RecipeType<T> {

    override fun toString(): String = id.toString()

}