package org.teamvoided.voidlib.helpers.item

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun ItemStack.isShears() = `is`(ConventionalItemTags.SHEAR_TOOLS)

// TODO(del) Update Voidlib Delete this
@Deprecated("Use Voidlib")
fun Player.giveItem(stack: ItemStack) {
    if (!addItem(stack)) {
        drop(stack, false)
    }
}
