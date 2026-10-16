package org.teamvoided.dusks_and_dungeons.client

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.mc
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

object DnDGui {

    val LANTERN_PUMPKIN_BLUR = misc("lantern_pumpkin_blur")
    val MOSSKIN_PUMPKIN_BLUR = misc("mosskin_pumpkin_blur")
    val GLOOM_PUMPKIN_BLUR = misc("gloom_pumpkin_blur")
    val PALE_PUMPKIN_BLUR = mc("textures/misc/pumpkinblur.png")

    val OVERLAY_MAP = mapOf(
        DnDBlocks.CARVED_LANTERN_PUMPKIN.asItem() to LANTERN_PUMPKIN_BLUR,
        DnDBlocks.CARVED_MOSSKIN_PUMPKIN.asItem() to MOSSKIN_PUMPKIN_BLUR,
        DnDBlocks.CARVED_GLOOM_PUMPKIN.asItem() to GLOOM_PUMPKIN_BLUR,
        DnDBlocks.CARVED_PALE_PUMPKIN.asItem() to PALE_PUMPKIN_BLUR,
    )

    //toss the peasants this slop (no need to backport)
    @JvmStatic
    fun carvedOverlay(item: Item): ResourceLocation? = OVERLAY_MAP[item]

    fun misc(path: String) = id("textures/misc/$path.png")

}