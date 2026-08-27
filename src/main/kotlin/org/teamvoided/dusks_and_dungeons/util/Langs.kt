package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.ChatFormatting
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import org.teamvoided.dusks_and_dungeons.DnDConst.MODID

const val HEAVY_CUBE_TOOLTIP = "block.$MODID.heavy_cube.tooltip"
const val TINTED_TOOLTIP = "$MODID.tinted_potion.tooltip"

fun appendTintedTooltip(stack: ItemStack, tooltips: MutableList<Component>, flag: TooltipFlag): Boolean {
    if (!flag.isCreative) {
        stack.get(DataComponents.POTION_CONTENTS)?.let {
            tooltips.add(
                Component.translatable(TINTED_TOOLTIP).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GRAY)
            )
        }
    }
    return flag.isCreative
}
