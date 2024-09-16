package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.component.type.DyedColorComponent
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks

object DnDItemsClient {
    fun init() {
        ColorProviderRegistry.ITEM.register(
            { _, _ -> GrassColors.getDefault() },
            *DnDBlocks.GRASS_TINT_BLOCKS.map { it.asItem() }.toTypedArray()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getDefaultColor() },
            DnDWoodBlocks.OAK_LEAF_PILE.asItem(),
            DnDWoodBlocks.JUNGLE_LEAF_PILE.asItem(),
            DnDWoodBlocks.ACACIA_LEAF_PILE.asItem(),
            DnDWoodBlocks.DARK_OAK_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getSpruceColor() },
            DnDWoodBlocks.SPRUCE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getBirchColor() },
            DnDWoodBlocks.BIRCH_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { _, _ -> FoliageColors.getMangroveColor() },
            DnDWoodBlocks.MANGROVE_LEAF_PILE.asItem()
        )
        ColorProviderRegistry.ITEM.register(
            { stack, _ -> DyedColorComponent.getColorOrDefault(stack, 0xffffff) }, DnDItems.FARMERS_HAT
        )
    }
}