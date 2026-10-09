package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.Variant
import net.minecraft.data.models.blockstates.VariantProperties.MODEL
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.data.models.model.TextureSlot.CROSS
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.block.flower.pot.FlowerPotWithSporesBlock

fun BlockModelGenerators.registerGoldenMushroomPlant(block: Block, pot: FlowerPotWithSporesBlock) {
    createSimpleFlatItemModel(block, "_1")
    val baseVariants = arrayOfNulls<Variant>(3)
    val potVariants = arrayOfNulls<Variant>(3)

    for (id in 0..2) {
        val modelId = "_${id + 1}"
        // Regular Models
        val texture = TextureMapping.defaultTexture(block)
            .put(CROSS, blockId(block, modelId))
        val model = ModelTemplates.CROSS.createWithSuffix(block, modelId, texture, modelOutput)
        baseVariants[id] = Variant.variant().with(MODEL, model)
        // Pot Models
        val plantTexture = TextureMapping.singleSlot(TextureSlot.PLANT, blockId(block, modelId))
        val potModel = DnDModels.FLOWER_POT_CROSS_NO_SHADE.createWithSuffix(pot, modelId, plantTexture, modelOutput)
        potVariants[id] = Variant.variant().with(MODEL, potModel)
    }

    blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, *baseVariants))
    blockStateOutput.accept(MultiVariantGenerator.multiVariant(pot, *potVariants))
}