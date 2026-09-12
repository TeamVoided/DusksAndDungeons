package org.teamvoided.dusks_and_dungeons

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.mc
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

object DnDGui {
    val LANTERN_PUMPKIN_BLUR: ResourceLocation = misc("lantern_pumpkin_blur")
    val MOSSKIN_PUMPKIN_BLUR: ResourceLocation = misc("mosskin_pumpkin_blur")
    val GLOOM_PUMPKIN_BLUR: ResourceLocation = misc("gloom_pumpkin_blur")
    val PALE_PUMPKIN_BLUR: ResourceLocation = mc("textures/misc/pumpkinblur.png")

    @JvmStatic
    fun carvedOverlay(item: Item): ResourceLocation? {//toss the peasants this slop (no need to backport)
        return when (item) {
            DnDBlocks.CARVED_LANTERN_PUMPKIN.asItem() -> LANTERN_PUMPKIN_BLUR
            DnDBlocks.CARVED_MOSSKIN_PUMPKIN.asItem() -> MOSSKIN_PUMPKIN_BLUR
            DnDBlocks.CARVED_GLOOM_PUMPKIN.asItem() -> GLOOM_PUMPKIN_BLUR
            DnDBlocks.CARVED_PALE_PUMPKIN.asItem() -> PALE_PUMPKIN_BLUR
            else -> null
        }
    }

    private fun misc(path: String) = id("textures/misc/$path.png")
}