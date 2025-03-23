package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.item.Item
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.teamvoided.dusks_and_dungeons.util.block.GRASS_TINT_BLOCKS

object DnDItemsClient {
    fun init() {
        registerTint(
            { _, _ -> GrassColors.getDefault() }, *GRASS_TINT_BLOCKS.map { it.asItem() }.toTypedArray()
        )
        registerTint(
            FoliageColors.getDefaultColor(),
            DnDBlocks.OAK_LEAF_PILE.asItem(),
            DnDBlocks.JUNGLE_LEAF_PILE.asItem(),
            DnDBlocks.ACACIA_LEAF_PILE.asItem(),
            DnDBlocks.DARK_OAK_LEAF_PILE.asItem()
        )
        registerTint(FoliageColors.getSpruceColor(), DnDBlocks.SPRUCE_LEAF_PILE.asItem())
        registerTint(FoliageColors.getBirchColor(), DnDBlocks.BIRCH_LEAF_PILE.asItem())
        registerTint(FoliageColors.getMangroveColor(), DnDBlocks.MANGROVE_LEAF_PILE.asItem())
        registerTint({ stack, _ -> DyedColorComponent.getColorOrDefault(stack, 0xffffff) }, DnDItems.FARMERS_HAT)

        ItemTooltipCallback.EVENT.register { stack, _, _, lines ->
            if (DnDItems.EVIL_ITEMS.contains(stack.item)) lines.addLast(
                Text.literal("Experimental item, may disappear in future updates!").formatted(Formatting.RED)
            )
        }
    }

    fun registerTint(provider: ItemColorProvider, vararg items: Item) =
        ColorProviderRegistry.ITEM.register(provider, *items)

    fun registerTint(tint: Int, vararg items: Item) = registerTint({ _, _ -> tint }, *items)

}