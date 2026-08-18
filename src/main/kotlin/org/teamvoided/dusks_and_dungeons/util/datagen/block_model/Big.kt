package org.teamvoided.dusks_and_dungeons.util.datagen.block_model

import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.blockstates.Variant
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.big.BigRedstoneLanternBlock
import org.teamvoided.dusks_and_dungeons.util.datagen.block


fun BlockModelGenerators.registerBigChain(block: Block) {
    this.createSimpleFlatItemModel(block.asItem())
    val texture = TextureMapping()
        .put(PARTICLE, TextureMapping.getBlockTexture(block))
        .put(ALL, TextureMapping.getBlockTexture(block))
    val model = block(
        "parent/big_chain",
        PARTICLE,
        ALL
    ).create(block, texture, this.modelOutput)
    this.createAxisAlignedPillarBlockCustomModel(block, model)
}

fun BlockModelGenerators.registerBigLantern(block: Block, redstone: Boolean = false) =
    registerBigLantern(block, id("block/big_lantern_bottom"), redstone)

fun BlockModelGenerators.registerBigLantern(
    block: Block,
    bottom: ResourceLocation, //for copper lanterns
    redstone: Boolean = false
) {
    this.createSimpleFlatItemModel(block)
    val texture = TextureMapping()
        .put(SIDE, TextureMapping.getBlockTexture(block))
        .put(END, bottom)
        .copySlot(SIDE, PARTICLE)
    val model = block("parent/big_lantern", PARTICLE, SIDE, END)
        .create(block, texture, this.modelOutput)

    val multiGenerator = MultiVariantGenerator.multiVariant(block,Variant.variant().with(VariantProperties.MODEL, model)).with(upDefaultFacing())
    if (redstone) {
        val textureUnlit = TextureMapping()
            .put(SIDE, TextureMapping.getBlockTexture(block, "_off"))
            .put(END, bottom)
            .copySlot(SIDE, PARTICLE)
        val modelUnlit = block("parent/big_lantern", PARTICLE, SIDE, END)
            .createWithSuffix(block, "_off", textureUnlit, this.modelOutput)

        multiGenerator.with(
            PropertyDispatch.property(BigRedstoneLanternBlock.LIT)
                .select(true, Variant.variant())
                .select(false, Variant.variant().with(VariantProperties.MODEL, modelUnlit))
        )
    }
    this.blockStateOutput.accept(multiGenerator)

}

fun upDefaultFacing(): PropertyDispatch {
    return PropertyDispatch.property(BlockStateProperties.FACING)
        .select(Direction.UP, Variant.variant())
        .select(Direction.DOWN, Variant.variant().with(VariantProperties.X_ROT, Rotation.R180))
        .select(
            Direction.NORTH, Variant.variant()
                .with(VariantProperties.X_ROT, Rotation.R90)
        )
        .select(
            Direction.SOUTH, Variant.variant()
                .with(VariantProperties.X_ROT, Rotation.R90)
                .with(VariantProperties.Y_ROT, Rotation.R180)
        )
        .select(
            Direction.WEST, Variant.variant()
                .with(VariantProperties.X_ROT, Rotation.R90)
                .with(VariantProperties.Y_ROT, Rotation.R270)
        )
        .select(
            Direction.EAST, Variant.variant()
                .with(VariantProperties.X_ROT, Rotation.R90)
                .with(VariantProperties.Y_ROT, Rotation.R90)
        )
}