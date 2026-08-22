package org.teamvoided.dusks_and_dungeons.init.events

import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.Snowball
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

fun initItemEvents() {
    UseItemCallback.EVENT.register(::useItemEvent)
}


fun useItemEvent(player: Player, level: Level, hand: InteractionHand): InteractionResultHolder<ItemStack> {
    val stack = player.getItemInHand(hand)
    val result = doThrowableStackLogic(player, level, stack)
    if (result.result.consumesAction()) {
        return result
    }
    return InteractionResultHolder.pass(stack)
}


fun doThrowableStackLogic(player: Player, level: Level, stack: ItemStack): InteractionResultHolder<ItemStack> {
    if (!stack.`is`(ConventionalItemTags.BRICKS)) {
        return InteractionResultHolder.pass(stack)
    }

    level.playSound(
        null, player.x, player.y, player.z,
        SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL,
        0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f)
    )
    if (!level.isClientSide) {
        val thrownItem = Snowball(level, player)
        thrownItem.item = stack
        thrownItem.shootFromRotation(player, player.xRot, player.yRot, 0.0f, 1.5f, 1.0f)
        player.cooldowns.addCooldown(stack.item, 0)
        level.addFreshEntity(thrownItem)
    }

    player.awardStat(Stats.ITEM_USED.get(stack.item))
    stack.consume(1, player)
    return InteractionResultHolder.sidedSuccess(stack, level.isClientSide)
}