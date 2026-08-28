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

fun BlockModelGenerators.tintedCarpetPlate(plate: Block, wool: Block) {
    val up = DnDTexturedModels.TINTED_CARPET.get(wool).createWithSuffix(plate, "_up", modelOutput)
    val down = DnDTexturedModels.TINTED_CARPET_DOWN.get(wool).createWithSuffix(plate, "_down", modelOutput)
    delegateItemModel(plate, up)
    blockStateOutput.accept(BlockModelGenerators.createPressurePlate(plate, up, down))
}