package org.teamvoided.dusks_and_dungeons.util.datagen.block_model

import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.blockstates.Variant
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.util.datagen.block

fun BlockModelGenerators.overgrowthBush(block: Block) {
    val texture = TextureMapping()
        .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
        .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"))
        .put(TextureSlot.PLANT, TextureMapping.getBlockTexture(block, "_plant"))
        .put(TextureSlot.STEM, TextureMapping.getBlockTexture(block, "_plant_stem"))
    val identifier =
        block(
            "parent/foliage/template_tinted_bush",
            TextureSlot.TOP,
            TextureSlot.SIDE,
            TextureSlot.PLANT,
            TextureSlot.STEM
        )
            .create(block, texture, this.modelOutput)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier))
            .with(createDownDefaultRotationStates())
    )
}

fun BlockModelGenerators.hangingOvergrowth(block: Block) {
    val full: ResourceLocation =
        this.createSuffixedVariant(block, "_full", ModelTemplates.TINTED_CROSS) { TextureMapping.cross(it) }
    val tip: ResourceLocation =
        this.createSuffixedVariant(block, "_tip", ModelTemplates.TINTED_CROSS) { TextureMapping.cross(it) }
    this.createSimpleFlatItemModel(block, "_full")
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block)
            .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.BOTTOM, tip, full))
    )
}


fun createDownDefaultRotationStates(): PropertyDispatch {
    return PropertyDispatch.property(BlockStateProperties.FACING)
        .select(Direction.DOWN, Variant.variant())
        .select(Direction.UP, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
        .select(
            Direction.NORTH, Variant.variant()
                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
        )
        .select(Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
        .select(
            Direction.WEST, Variant.variant()
                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
        )
        .select(
            Direction.EAST, Variant.variant()
                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
        )
}