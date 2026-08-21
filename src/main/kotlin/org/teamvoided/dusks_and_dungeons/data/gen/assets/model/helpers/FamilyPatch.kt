package org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.world.level.block.Block

fun BlockModelGenerators.createSign(particle: Block, sign: Block, wallSign: Block) {
    val mapping = TextureMapping.particle(particle)
    val model = ModelTemplates.PARTICLE_ONLY.create(sign, mapping, modelOutput)

    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(sign, model))
    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSign, model))
    createSimpleFlatItemModel(sign.asItem())
    skipAutoItemBlock(wallSign)
}
