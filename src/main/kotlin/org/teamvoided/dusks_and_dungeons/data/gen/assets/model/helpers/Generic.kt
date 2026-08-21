package org.teamvoided.dusks_and_dungeons.data.gen.assets.model.helpers

import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.blockstates.Variant
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties

/**
 * Creates a property map for blocks with `BlockStateProperties.FACING` propery with the default state being `Direction.UP`
 */
fun createUpFacing(): PropertyDispatch {
    return PropertyDispatch.property(BlockStateProperties.FACING)
        .select(
            Direction.DOWN, Variant.variant()
                .with(VariantProperties.X_ROT, Rotation.R180)
        )
        .select(Direction.UP, Variant.variant())
        .select(
            Direction.NORTH, Variant.variant()
                .with(VariantProperties.X_ROT, Rotation.R90)
        )
        .select(
            Direction.SOUTH, Variant.variant()
                .with(VariantProperties.X_ROT, Rotation.R270)
        )
        .select(
            Direction.WEST, Variant.variant()
                .with(VariantProperties.X_ROT, Rotation.R270)
                .with(VariantProperties.Y_ROT, Rotation.R90)
        )
        .select(
            Direction.EAST, Variant.variant()
                .with(VariantProperties.X_ROT, Rotation.R90)
                .with(VariantProperties.Y_ROT, Rotation.R90)
        )
}

fun BlockModelGenerators.createOrientable(block: Block) {
    val model = ModelLocationUtils.getModelLocation(block)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block).with(
            PropertyDispatch.property(BlockStateProperties.ORIENTATION)
                .generate { orientation -> applyRotation(orientation, Variant.variant().with(VariantProperties.MODEL, model)) }
        )
    )
}

/**
 * Create an item model that references a block model with the provided block's id.
 */
fun BlockModelGenerators.createItemModel(block: Block) {
    delegateItemModel(block, model(block))
}

fun model(block: Block): ResourceLocation = ModelLocationUtils.getModelLocation(block)

