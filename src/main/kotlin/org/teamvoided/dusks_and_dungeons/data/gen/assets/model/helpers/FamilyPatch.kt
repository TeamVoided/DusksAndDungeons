package org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.world.level.block.Block

fun BlockModelGenerators.fence(fenceBlock: Block, reference: Block) {
    val texture = TextureMapping.defaultTexture(reference)
    val post = ModelTemplates.FENCE_POST.create(fenceBlock, texture, modelOutput)
    val side = ModelTemplates.FENCE_SIDE.create(fenceBlock, texture, modelOutput)
    val inventory = ModelTemplates.FENCE_INVENTORY.create(fenceBlock, texture, modelOutput)

    blockStateOutput.accept(BlockModelGenerators.createFence(fenceBlock, post, side))
    delegateItemModel(fenceBlock, inventory)
}

fun BlockModelGenerators.createSign(particle: Block, sign: Block, wallSign: Block) {
    val mapping = TextureMapping.particle(particle)
    val model = ModelTemplates.PARTICLE_ONLY.create(sign, mapping, modelOutput)

    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(sign, model))
    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSign, model))
    createSimpleFlatItemModel(sign.asItem())
    skipAutoItemBlock(wallSign)
}
