package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot.CONTENT
import net.minecraft.data.models.model.TextureSlot.END
import net.minecraft.data.models.model.TextureSlot.SIDE
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.datagen.old.util.model

object DnDTextureMapping {
    fun jar(block: Block): TextureMapping = TextureMapping()
        .put(END,  block.model("_end"))
        .put(SIDE, block.model())
    fun jarWithLayers(block: Block, contents: ResourceLocation): TextureMapping = jar(block)
        .put(CONTENT, contents)
}