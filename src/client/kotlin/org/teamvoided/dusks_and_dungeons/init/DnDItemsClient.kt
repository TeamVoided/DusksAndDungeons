package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.client.color.world.FoliageColors
import net.minecraft.client.color.world.GrassColors
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.item.UnclampedModelPredicateProvider
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.item.Item
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks
import net.minecraft.util.Identifier.ofDefault as mc

object DnDItemsClient {
    fun init() {
        registerTint(
            { _, _ -> GrassColors.getDefault() }, *DnDBlocks.GRASS_TINT_BLOCKS.map { it.asItem() }.toTypedArray()
        )
        registerTint(
            FoliageColors.getDefaultColor(),
            DnDWoodBlocks.OAK_LEAF_PILE.asItem(),
            DnDWoodBlocks.JUNGLE_LEAF_PILE.asItem(),
            DnDWoodBlocks.ACACIA_LEAF_PILE.asItem(),
            DnDWoodBlocks.DARK_OAK_LEAF_PILE.asItem()
        )
        registerTint(FoliageColors.getSpruceColor(), DnDWoodBlocks.SPRUCE_LEAF_PILE.asItem())
        registerTint(FoliageColors.getBirchColor(), DnDWoodBlocks.BIRCH_LEAF_PILE.asItem())
        registerTint(FoliageColors.getMangroveColor(), DnDWoodBlocks.MANGROVE_LEAF_PILE.asItem())
        registerTint({ stack, _ -> DyedColorComponent.getColorOrDefault(stack, 0xffffff) }, DnDItems.FARMERS_HAT)


        modelPredicate(DnDItems.WEB_WEAVER, mc("pull")) { stack, _, entity, _ ->
            if (entity == null || entity.activeItem != stack) 0.0f
            else (stack.getUseTicks(entity) - entity.itemUseTimeLeft) / 20.0f
        }
        modelPredicate(DnDItems.WEB_WEAVER, mc("pulling")) { stack, _, entity, _ ->
            if (entity != null && entity.isUsingItem && entity.activeItem == stack) 1.0f else 0.0f
        }

        ItemTooltipCallback.EVENT.register { stack, _, _, lines ->
            if (DnDItems.EVIL_ITEMS.contains(stack.item)) lines.addLast(
                Text.literal("Experimental item, may disappear in future updates!").formatted(Formatting.RED)
            )
        }
    }

    fun modelPredicate(item: Item, id: Identifier, provider: UnclampedModelPredicateProvider) =
        ModelPredicateProviderRegistry.register(item, id, provider)

    fun registerTint(provider: ItemColorProvider, vararg items: Item) =
        ColorProviderRegistry.ITEM.register(provider, *items)

    fun registerTint(tint: Int, vararg items: Item) = registerTint({ _, _ -> tint }, *items)

}