package org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.blockstates.Variant
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.big.BigRedstoneLanternBlock


fun BlockModelGenerators.createBigChain(block: Block) {
    val texture = TextureMapping()
        .put(PARTICLE, TextureMapping.getBlockTexture(block))
        .put(ALL, TextureMapping.getBlockTexture(block))
    val model = DnDModels.BIG_CHAIN.create(block, texture, modelOutput)

    createAxisAlignedPillarBlockCustomModel(block, model)
    createSimpleFlatItemModel(block.asItem())
}

fun BlockModelGenerators.createBigLantern(block: Block, hasOffVariant: Boolean = false) =
    createBigLantern(block, id("block/big_lantern_bottom"), hasOffVariant)

/**
 * Generate Big Lantern models
 * @param block The block it is generated for
 * @param bottom Texture of the lantern bottom (added for copper lanterns and mod compat)
 * @param hasOffVariant Weather the big lantern has an off variant, e.g. Big redstone lantern
 */
fun BlockModelGenerators.createBigLantern(block: Block, bottom: ResourceLocation, hasOffVariant: Boolean = false) {
    createSimpleFlatItemModel(block)
    val texture = TextureMapping()
        .put(SIDE, TextureMapping.getBlockTexture(block))
        .put(END, bottom)
        .copySlot(SIDE, PARTICLE)
    val model = DnDModels.BIG_LANTERN.create(block, texture, modelOutput)

    val multiGenerator =
        MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, model))
            .with(createUpFacing())

    if (hasOffVariant) {
        val textureOff = TextureMapping()
            .put(SIDE, TextureMapping.getBlockTexture(block, "_off"))
            .put(END, bottom)
            .copySlot(SIDE, PARTICLE)
        val modelOff = DnDModels.BIG_LANTERN.createWithSuffix(block, "_off", textureOff, modelOutput)

        multiGenerator.with(
            PropertyDispatch.property(BigRedstoneLanternBlock.LIT)
                .select(true, Variant.variant())
                .select(false, Variant.variant().with(VariantProperties.MODEL, modelOff))
        )
    }

    blockStateOutput.accept(multiGenerator)
}

fun BlockModelGenerators.createBigScaffolding(scaffolding: Block) {
    // TODO make this be parented models instead
    val stable = ModelLocationUtils.getModelLocation(scaffolding, "_stable")
    val unstable = ModelLocationUtils.getModelLocation(scaffolding, "_unstable")
    delegateItemModel(scaffolding, stable)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(scaffolding)
            .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.BOTTOM, unstable, stable))
    )
}
