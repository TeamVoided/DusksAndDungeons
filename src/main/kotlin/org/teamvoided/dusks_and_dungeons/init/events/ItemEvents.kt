/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.init.events

import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.SingleRecipeInput
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LevelEvent
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult.Type
import org.teamvoided.dusks_and_dungeons.block.CompositeBlock.Companion.POS_TO_MASK
import org.teamvoided.dusks_and_dungeons.block.CompositeBlock.Companion.SHAPE
import org.teamvoided.dusks_and_dungeons.block.CompositeBlock.Companion.addToComposite
import org.teamvoided.dusks_and_dungeons.block.CompositeBlock.Companion.getCornerPosition
import org.teamvoided.dusks_and_dungeons.block.CompositeBlock.Companion.getOffset
import org.teamvoided.dusks_and_dungeons.entity.ThrownItemStack
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDRecipes
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrownItemDefinition
import kotlin.jvm.optionals.getOrNull

fun initItemEvents() {
    UseItemCallback.EVENT.register(::useItemEvent)
    UseBlockCallback.EVENT.register { player, level, hand, hit ->
        val result = useOnBlockRecipe(player, level, hand, hit)
        if (result != null) {
            return@register result
        }
        addToCompositeFromCoreItem(player, level, hand, hit)
    }
}

fun useOnBlockRecipe(player: Player, level: Level, hand: InteractionHand, hit: BlockHitResult): InteractionResult? {
    if (hit.type == Type.MISS) {
        return null
    }
    val stack = player.getItemInHand(hand)
    if (stack.isEmpty) {
        return null
    }
    val pos = hit.blockPos
    val state = level.getBlockState(pos)
    if (state.isAir) {
        return null
    }

    val input = SingleRecipeInput(stack)

    val recipeHolder =
        level.recipeManager.getRecipeFor(DnDRecipes.USE_ON_BLOCK, input, level).getOrNull() ?: return null
    val recipe = recipeHolder.value()

    if (recipe.inputState != state) {
        return null
    }

    recipe.assemble(input, level.registryAccess())
    if (player is ServerPlayer) {
        player.triggerRecipeCrafted(recipeHolder, listOf(input.item()))
        level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(state))
        level.setBlockAndUpdate(pos, recipe.resultState)
    }
    return InteractionResult.SUCCESS
}

fun addToCompositeFromCoreItem(
    player: Player, level: Level, hand: InteractionHand, hit: BlockHitResult,
): InteractionResult {
    if (hit.type != Type.BLOCK) return InteractionResult.PASS
    val stack = player.getItemInHand(hand)
    if (!stack.`is`(Items.HEAVY_CORE)) return InteractionResult.PASS

    val hitState = level.getBlockState(hit.blockPos)
    if (hitState.`is`(DnDBlocks.HEAVY_CUBE)) {
        val cornerMask = POS_TO_MASK[getCornerPosition(hit).add(hit.direction.getOffset().scale(-2.0))]
        if (cornerMask != null && hitState.getValue(SHAPE) and cornerMask != 0) {
            return InteractionResult.PASS
        }
    }

    val pos = hit.blockPos.relative(hit.direction)
    val state = level.getBlockState(pos)
    if (!state.`is`(DnDBlocks.HEAVY_CUBE)) return InteractionResult.PASS
    if (!level.mayInteract(player, pos)) return InteractionResult.PASS

    val clickedPos = getCornerPosition(BlockHitResult(hit.location, hit.direction.opposite, pos, hit.isInside))
    val cornerToBeAdded = POS_TO_MASK[clickedPos] ?: return InteractionResult.PASS
    if (addToComposite(state, cornerToBeAdded, level, pos, player, stack)) {
        return InteractionResult.SUCCESS
    }
    return InteractionResult.PASS
}


fun useItemEvent(player: Player, level: Level, hand: InteractionHand): InteractionResultHolder<ItemStack> {
    val stack = player.getItemInHand(hand)
    if (player.cooldowns.isOnCooldown(stack.item)) {
        return InteractionResultHolder.pass(stack)
    }

    val result = doThrowableStackLogic(player, level, stack)
    if (result.result.consumesAction()) {
        return result
    }

    return InteractionResultHolder.pass(stack)
}


fun doThrowableStackLogic(player: Player, level: Level, stack: ItemStack): InteractionResultHolder<ItemStack> {

    val thrownId = ThrownItemDefinition.getItemDefinition(stack) ?: return InteractionResultHolder.pass(stack)

    level.playSound(
        null, player.x, player.y, player.z,
        SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL,
        0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f)
    )
    if (!level.isClientSide) {
        val thrownDef = thrownId.value()
        val projectile = ThrownItemStack(level, player)
        projectile.item = stack
        projectile.setDefinition(thrownId)
        projectile.shootFromRotation(player, player.xRot, player.yRot, 0.0f, thrownDef.power, thrownDef.uncertainty)
        player.cooldowns.addCooldown(stack.item, thrownDef.cooldown)
        level.addFreshEntity(projectile)
    }

    player.awardStat(Stats.ITEM_USED.get(stack.item))
    stack.consume(1, player)
    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide)
}