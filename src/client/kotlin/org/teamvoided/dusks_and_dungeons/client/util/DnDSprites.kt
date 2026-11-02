package org.teamvoided.dusks_and_dungeons.client.util

import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDSprites {

    @JvmField
    val SILVER_TEXTURE = entity("fox/silver_fox.png")

    @JvmField
    val SLEEPING_SILVER_TEXTURE = entity("fox/silver_fox_sleep.png")


    fun entity(path: String): ResourceLocation = id("textures/entity/$path")

}