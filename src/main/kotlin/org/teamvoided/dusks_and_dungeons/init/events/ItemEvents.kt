package org.teamvoided.dusks_and_dungeons.init.events

import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import org.teamvoided.dusks_and_dungeons.block.CompositeBlock.Companion.POS_TO_CORNER
import org.teamvoided.dusks_and_dungeons.block.CompositeBlock.Companion.addToComposite
import org.teamvoided.dusks_and_dungeons.block.CompositeBlock.Companion.getCornerPosition
import org.teamvoided.dusks_and_dungeons.block.CompositeBlock.Companion.getOffset
import org.teamvoided.dusks_and_dungeons.entity.ThrownItemStack
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrownItemDefinition

fun initItemEvents() {
    UseItemCallback.EVENT.register(::useItemEvent)
    UseBlockCallback.EVENT.register(::addToCompositeFromCoreItem)
}

fun addToCompositeFromCoreItem(
    player: Player, level: Level, hand: InteractionHand, hit: BlockHitResult,
): InteractionResult {
    if (hit.type != HitResult.Type.BLOCK) return InteractionResult.PASS
    val stack = player.getItemInHand(hand)
    if (!stack.`is`(Items.HEAVY_CORE)) return InteractionResult.PASS

    val hitState = level.getBlockState(hit.blockPos)
    if (hitState.`is`(DnDBlocks.HEAVY_CUBE)) {
        val corner = POS_TO_CORNER[getCornerPosition(hit).add(hit.direction.getOffset().scale(-2.0))]
        if (corner != null && !hitState.getValue(corner)) return InteractionResult.PASS
    }

    val pos = hit.blockPos.relative(hit.direction)
    val state = level.getBlockState(pos)
    if (!state.`is`(DnDBlocks.HEAVY_CUBE)) return InteractionResult.PASS
    if (!level.mayInteract(player, pos)) return InteractionResult.PASS

    val clickedPos = getCornerPosition(BlockHitResult(hit.location, hit.direction.opposite, pos, hit.isInside))
    val cornerToBeAdded = POS_TO_CORNER[clickedPos] ?: return InteractionResult.PASS
    addToComposite(state, cornerToBeAdded, level, pos, player, stack)
    return InteractionResult.SUCCESS
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