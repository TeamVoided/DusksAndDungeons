package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.data.models.model.TextureSlot

/**
 * Object for all DnD `TextureSlot`'s
 */
object DnDTextureSlots {

    val OVERLAY = create("overlay")

    fun create(name: String): TextureSlot = TextureSlot.create(name)
    fun create(name: String, default: TextureSlot): TextureSlot = TextureSlot.create(name, default)

}