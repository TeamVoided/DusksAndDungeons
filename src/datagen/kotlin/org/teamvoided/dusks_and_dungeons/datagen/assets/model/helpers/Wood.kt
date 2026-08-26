package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.BlockModelGenerators.createSimpleBlock
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.world.level.block.Block


fun BlockModelGenerators.bookshelf(bookshelf: Block, top: Block) {
    val texture = TextureMapping.column(
        TextureMapping.getBlockTexture(bookshelf),
        TextureMapping.getBlockTexture(top)
    )
    val model = ModelTemplates.CUBE_COLUMN.create(bookshelf, texture, modelOutput)

    blockStateOutput.accept(createSimpleBlock(bookshelf, model))
}