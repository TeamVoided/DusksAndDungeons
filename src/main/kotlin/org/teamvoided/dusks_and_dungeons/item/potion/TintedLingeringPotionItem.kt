package org.teamvoided.dusks_and_dungeons.item.potion

import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.LingeringPotionItem
import net.minecraft.world.item.TooltipFlag
import org.teamvoided.dusks_and_dungeons.util.appendTintedTooltip

class TintedLingeringPotionItem(properties: Properties) : LingeringPotionItem(properties) {

    override fun getDescriptionId(stack: ItemStack): String = super.getDescriptionId()

    override fun appendHoverText(
        stack: ItemStack, ctx: TooltipContext, tooltips: MutableList<Component>, flag: TooltipFlag,
    ) {
        if (appendTintedTooltip(stack, tooltips, flag)) {
            super.appendHoverText(stack, ctx, tooltips, flag)
        }
    }

}