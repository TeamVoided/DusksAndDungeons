package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.ChatFormatting
import net.minecraft.client.color.item.ItemColor
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.component.DyedItemColor
import net.minecraft.world.level.FoliageColor
import net.minecraft.world.level.GrassColor
import org.teamvoided.dusks_and_dungeons.util.block.GRASS_TINT_BLOCKS

object DnDItemsClient {
    fun init() {
        registerTint(
            { _, _ -> GrassColor.getDefaultColor() }, *GRASS_TINT_BLOCKS.map { it.asItem() }.toTypedArray()
        )
        registerTint(
            FoliageColor.getDefaultColor(),
            DnDBlocks.OAK_LEAF_PILE.asItem(),
            DnDBlocks.JUNGLE_LEAF_PILE.asItem(),
            DnDBlocks.ACACIA_LEAF_PILE.asItem(),
            DnDBlocks.DARK_OAK_LEAF_PILE.asItem()
        )
        registerTint(FoliageColor.getEvergreenColor(), DnDBlocks.SPRUCE_LEAF_PILE.asItem())
        registerTint(FoliageColor.getBirchColor(), DnDBlocks.BIRCH_LEAF_PILE.asItem())
        registerTint(FoliageColor.getMangroveColor(), DnDBlocks.MANGROVE_LEAF_PILE.asItem())
        registerTint({ stack, _ -> DyedItemColor.getOrDefault(stack, 0xffffff) }, DnDItems.FARMERS_HAT)

        ItemTooltipCallback.EVENT.register { stack, _, _, lines ->
            if (DnDItems.EVIL_ITEMS.contains(stack.item)) lines.addLast(
                Component.literal("Experimental! May corrupt your worlds or disappear in future updates!").withStyle(ChatFormatting.RED)
            )
        }
    }

    fun registerTint(provider: ItemColor, vararg items: Item) =
        ColorProviderRegistry.ITEM.register(provider, *items)

    fun registerTint(tint: Int, vararg items: Item) = registerTint({ _, _ -> tint }, *items)

}