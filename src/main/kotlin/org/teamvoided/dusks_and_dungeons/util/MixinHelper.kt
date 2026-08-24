package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import org.teamvoided.dusks_and_dungeons.init.DnDRecipes
import org.teamvoided.dusks_and_dungeons.mixin.accessors.AbstractCandleBlockAccessor
import java.util.function.BiConsumer
import kotlin.jvm.optionals.getOrNull


fun Level.spawnCandleParticles(vec3d: Vec3, random: RandomSource) =
    AbstractCandleBlockAccessor.dnd_addParticlesAndSound(this, vec3d, random)

fun processRecipe(
    source: DamageSource, level: Level, input: SingleRecipeInput, resultMaker: BiConsumer<ItemStack, Int>,
) {
    val recipe = level.recipeManager.getRecipeFor(DnDRecipes.HURT_ITEM, input, level).getOrNull()?.value() ?: return
    val tag = level.getTag(recipe.damageTypeTag) ?: return
    if (tag.contains(source.typeHolder())) {
        resultMaker.accept(recipe.getResultItem(level.registryAccess()), recipe.invulnerableTime)
    }
}

