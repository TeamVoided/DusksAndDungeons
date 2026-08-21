package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.ChatFormatting
import net.minecraft.client.color.item.ItemColor
import net.minecraft.network.chat.Component
import net.minecraft.world.item.component.DyedItemColor
import net.minecraft.world.level.FoliageColor
import net.minecraft.world.level.GrassColor
import net.minecraft.world.level.ItemLike
import org.teamvoided.dusks_and_dungeons.util.block.GRASS_TINT_BLOCKS

object DnDItemsClient {

    fun init() {
        registerTint({ _, _ -> GrassColor.getDefaultColor() }, *GRASS_TINT_BLOCKS.toTypedArray())
        registerTint(
            FoliageColor.getDefaultColor(),
            DnDBlocks.OAK_LEAF_PILE,
            DnDBlocks.JUNGLE_LEAF_PILE,
            DnDBlocks.ACACIA_LEAF_PILE,
            DnDBlocks.DARK_OAK_LEAF_PILE
        )
        registerTint(FoliageColor.getEvergreenColor(), DnDBlocks.SPRUCE_LEAF_PILE)
        registerTint(FoliageColor.getBirchColor(), DnDBlocks.BIRCH_LEAF_PILE)
        registerTint(FoliageColor.getMangroveColor(), DnDBlocks.MANGROVE_LEAF_PILE)
        registerTint({ stack, _ -> DyedItemColor.getOrDefault(stack, 0xffffff) }, DnDItems.FARMERS_HAT)

        ItemTooltipCallback.EVENT.register { stack, _, _, lines ->
            if (DnDItems.EVIL_ITEMS.contains(stack.item)) {
                lines.add(Component.literal("Experimental!").withStyle(ChatFormatting.RED))
                lines.add(
                    Component.literal("May corrupt your worlds or disappear in future updates!")
                        .withStyle(ChatFormatting.RED)
                )
            }
        }
    }

    fun registerTint(tint: Int, vararg items: ItemLike) = registerTint({ _, _ -> tint }, *items)
    fun registerTint(provider: ItemColor, vararg items: ItemLike) {
        ColorProviderRegistry.ITEM.register(provider, *items)
    }

}