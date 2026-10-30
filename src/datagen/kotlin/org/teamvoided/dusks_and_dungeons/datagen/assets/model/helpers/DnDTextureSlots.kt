package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.data.models.model.TextureSlot

/**
 * Object for all DnD `TextureSlot`'s
 */
object DnDTextureSlots {

    val TINTED = slot("tinted")
    val OVERLAY = slot("overlay")

    fun slot(name: String): TextureSlot = TextureSlot.create(name)
    fun slot(name: String, default: TextureSlot): TextureSlot = TextureSlot.create(name, default)

}