package org.teamvoided.dusks_and_dungeons.client.init

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.ChatFormatting
import net.minecraft.client.color.item.ItemColor
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.util.FastColor
import net.minecraft.world.item.Item
import net.minecraft.world.item.alchemy.PotionContents
import net.minecraft.world.item.component.DyedItemColor
import net.minecraft.world.level.FoliageColor
import net.minecraft.world.level.GrassColor
import net.minecraft.world.level.ItemLike
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDDataComponents.CANDELABRA_CONTENTS
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.block.GRASS_TINT_BLOCKS
import org.teamvoided.dusks_and_dungeons.util.block.WATER_TINT_BLOCKS

object DnDItemsClient {

    val CANDELABRA_PREDICATE_ID = id("candelabra")

    fun init() {
        registerTint({ _, _ -> GrassColor.getDefaultColor() }, *GRASS_TINT_BLOCKS.toTypedArray())
        registerTint({ _, _ -> DnDBlocksClient.DEFAULT_WATER_COLOR }, *WATER_TINT_BLOCKS.toTypedArray())
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
        registerTint(
            { stack, tintIdx ->
                if (tintIdx > 0)
                    -1
                else
                    FastColor.ARGB32.opaque(
                        FastColor.ARGB32.multiply(
                            stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).color,
                            0xFF_7F_7F_7F.toInt()
                        )
                    )
            },
            DnDItems.TINTED_POTION, DnDItems.TINTED_SPLASH_POTION, DnDItems.TINTED_LINGERING_POTION
        )

        ItemTooltipCallback.EVENT.register { stack, _, _, lines ->
            if (DnDItems.EVIL_ITEMS.contains(stack.item)) {
                lines.add(Component.literal("Experimental!").withStyle(ChatFormatting.RED))
                lines.add(
                    Component.literal("May corrupt your worlds or disappear in future updates!")
                        .withStyle(ChatFormatting.RED)
                )
            }
        }

        registerCandelabraPredicate(DnDItems.IRON_CANDELABRA)
    }

    fun registerCandelabraPredicate(item: Item) {
        ItemProperties.register(item, CANDELABRA_PREDICATE_ID) { stack, _, _, _ ->
            val slots = stack.get(CANDELABRA_CONTENTS)?.slots ?: return@register 0f
            if (slots == 1) 0f else slots / 5f
        }
    }

    fun registerTint(tint: Int, vararg items: ItemLike) = registerTint({ _, _ -> tint }, *items)
    fun registerTint(provider: ItemColor, vararg items: ItemLike) {
        ColorProviderRegistry.ITEM.register(provider, *items)
    }

}