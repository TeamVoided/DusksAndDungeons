/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.blockstates.Variant
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties


fun blockId(block: Block): ResourceLocation = ModelLocationUtils.getModelLocation(block)
fun blockId(block: Block, suffix: String): ResourceLocation = ModelLocationUtils.getModelLocation(block, suffix)

fun itemId(item: Item): ResourceLocation = ModelLocationUtils.getModelLocation(item)
fun itemId(item: Item, suffix: String): ResourceLocation = ModelLocationUtils.getModelLocation(item, suffix)


fun variant(model: ResourceLocation): Variant = Variant.variant().with(VariantProperties.MODEL, model)

/**
 * Create an item model that references a block model with the provided block's id.
 */
fun BlockModelGenerators.createItemModel(block: Block) {
    delegateItemModel(block, blockId(block))
}

fun Rotation.opposite(): Rotation {
    return when (this) {
        Rotation.R0 -> Rotation.R180
        Rotation.R90 -> Rotation.R270
        Rotation.R180 -> Rotation.R0
        Rotation.R270 -> Rotation.R90
    }
}

// region PropertyDispatchers
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
// endregion

fun BlockModelGenerators.createOrientable(block: Block) {
    val model = ModelLocationUtils.getModelLocation(block)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block).with(
            PropertyDispatch.property(BlockStateProperties.ORIENTATION)
                .generate { orientation -> applyRotation(orientation, variant(model)) }
        )
    )
}
