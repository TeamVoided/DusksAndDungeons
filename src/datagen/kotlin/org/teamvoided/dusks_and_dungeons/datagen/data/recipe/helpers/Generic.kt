package org.teamvoided.dusks_and_dungeons.datagen.data.recipe.helpers

import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.data.recipes.RecipeProvider.*
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

fun conversionName(from: ItemLike, to: ItemLike) = id(getConversionRecipeName(to, from))

fun RecipeBuilder.unlockedBy(item: ItemLike): RecipeBuilder = unlockedBy(getHasName(item), has(item))
fun RecipeBuilder.unlockedBy(tag: TagKey<Item>): RecipeBuilder = unlockedBy("has_${tag.location.path}", has(tag))