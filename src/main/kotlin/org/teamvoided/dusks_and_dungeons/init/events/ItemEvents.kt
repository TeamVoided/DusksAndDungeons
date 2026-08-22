package org.teamvoided.dusks_and_dungeons.init.events

import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.entity.ThrownItemStack
import org.teamvoided.dusks_and_dungeons.item.ThrownItemDefinition

fun initItemEvents() {
    UseItemCallback.EVENT.register(::useItemEvent)
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
        val thrownItemStack = ThrownItemStack(level, player)
        thrownItemStack.item = stack
        thrownItemStack.setDefinition(thrownId)
        thrownItemStack.shootFromRotation(player, player.xRot, player.yRot, 0.0f, thrownDef.power, thrownDef.uncertainty)
        player.cooldowns.addCooldown(stack.item, thrownDef.cooldown)
        level.addFreshEntity(thrownItemStack)
    }

    player.awardStat(Stats.ITEM_USED.get(stack.item))
    stack.consume(1, player)
    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide)
}