package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.BlockModelGenerators.createSimpleBlock
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.world.level.block.Block

fun BlockModelGenerators.tintedBookshelf(bookshelf: Block, top: Block) {
    val texture = TextureMapping()
        .put(TextureSlot.SIDE, modelId(bookshelf))
        .put(TextureSlot.END, modelId(top))
        .put(DnDTextureSlots.OVERLAY, modelId(bookshelf, "_overlay"))
    val model = DnDModels.BOOKSHELF_TINTED.create(bookshelf, texture, modelOutput)

    blockStateOutput.accept(createSimpleBlock(bookshelf, model))
}