package org.teamvoided.dusks_and_dungeons.client.gui.screens.inventory

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemStack.EMPTY
import org.teamvoided.voidlib.api.tab.TabSection
import org.teamvoided.voidlib.api.tab.TabSectionRegistry

object TabSectionController {

    @JvmField
    var sections = mutableMapOf<Int, TabSection>()

    @JvmField
    var hasSections = false

    @JvmField
    var maxRows = 0

    @JvmField
    var items = mutableListOf<ItemStack>()

    @JvmStatic
    fun updateTab(tab: CreativeModeTab) {
        val tabKey = BuiltInRegistries.CREATIVE_MODE_TAB
            .getResourceKey(tab)
            .orElseThrow { IllegalStateException("Unregistered creative tab: $this") }
        val tabSections = TabSectionRegistry.getSections(tabKey)
        items.clear()
        if (tabSections.isEmpty()) {
            hasSections = false
            maxRows = 0
        } else {
            hasSections = true
            sections.clear()
            var futureMaxRows = 0
            for (section in tabSections) {
                sections[futureMaxRows] = section
                futureMaxRows += section.rows() + 1

                val contents = section.contents().toList()
                for (y in 0..section.rows()) {
                    for (x in 0..8) {
                        if (y == 0) {
                            items.add(EMPTY)
                        } else {
                            items.add(contents.getOrNull(((y - 1) * 9) + x) ?: EMPTY)
                        }
                    }
                }
            }
            maxRows = futureMaxRows
        }
    }

    @JvmField
    var scrollOffset = 0

    @JvmStatic
    fun render(graphics: GuiGraphics, mouseX: Int, mouseY: Int, tickDelta: Float, scrollOffset: Int) {
        val font = Minecraft.getInstance().font

        TabSectionController.scrollOffset = scrollOffset
        for (row in 0..4) {
            val index = row + scrollOffset
            val section = sections[index] ?: continue

            val y = 18 * (row + 1)
            graphics.blitSprite(section.backgroundTexture, 8, y - 1, 18 * 9, 18)
            graphics.drawString(font, section.getDisplayName(), 16, 4 + y, -1, false)
        }
    }



}