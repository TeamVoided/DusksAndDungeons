package org.teamvoided.dusks_and_dungeons.util

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack


fun ItemStack.isShears() = `is`(ConventionalItemTags.SHEAR_TOOLS)

fun Player.giveItem(stack: ItemStack) {
    if (!addItem(stack)) {
        drop(stack, false)
    }
}
