/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.core.BlockPos
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.TorchBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.Vec3
import org.teamvoided.dusks_and_dungeons.init.DnDRecipes
import org.teamvoided.dusks_and_dungeons.mixin.accessors.AbstractCandleBlockAccessor
import org.teamvoided.dusks_and_dungeons.mixin.accessors.CropBlockAccessor
import org.teamvoided.dusks_and_dungeons.mixin.accessors.MultifaceBlockAccessor
import org.teamvoided.dusks_and_dungeons.mixin.accessors.TorchBlockAccessor
import java.util.function.BiConsumer
import kotlin.jvm.optionals.getOrNull


fun Level.spawnCandleParticles(vec3d: Vec3, random: RandomSource) {
    return AbstractCandleBlockAccessor.dnd_addParticlesAndSound(this, vec3d, random)
}

fun TorchBlock.getFlameParticle(): SimpleParticleType {
    return (this as TorchBlockAccessor).dnd_fameParticle()
}

fun cropHasSufficientLight(level: LevelReader, pos: BlockPos): Boolean {
    return CropBlockAccessor.dnd_hasSufficientLight(level, pos)
}

fun cropGetGrowthSpeed(block: Block, level: BlockGetter, pos: BlockPos): Float {
    return CropBlockAccessor.dnd_getGrowthSpeed(block, level, pos)
}

fun multifaceRemoveFace(state: BlockState, property: BooleanProperty): BlockState {
    return MultifaceBlockAccessor.dnd_removeFace(state, property)
}

fun processRecipe(
    source: DamageSource, level: Level, input: SingleRecipeInput, entity: Entity?,
    resultMaker: BiConsumer<ItemStack, Int>,
) {
    val recipeHolder = level.recipeManager.getRecipeFor(DnDRecipes.HURT_ITEM, input, level).getOrNull() ?: return
    val recipe = recipeHolder.value()
    val tag = level.getTag(recipe.damageTypeTag) ?: return
    if (tag.contains(source.typeHolder())) {
        (entity as? ServerPlayer)?.triggerRecipeCrafted(recipeHolder, listOf(input.item()))
        resultMaker.accept(recipe.assemble(input, level.registryAccess()), recipe.invulnerableTime)
    }
}

