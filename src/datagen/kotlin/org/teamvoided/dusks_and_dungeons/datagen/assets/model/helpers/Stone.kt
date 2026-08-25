package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

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
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import org.teamvoided.dusks_and_dungeons.block.GravestoneBlock
import org.teamvoided.dusks_and_dungeons.datagen.old.util.suffix

// region Gravestones
private const val CENTER = "_centered"

fun BlockModelGenerators.registerGravestones(gravestone: Block, smallGravestone: Block) {
    registerGravestone(gravestone)
    registerSmallGravestone(smallGravestone, modelId(gravestone))
}

fun BlockModelGenerators.registerGravestone(gravestone: Block, texture: ResourceLocation = modelId(gravestone)) {
    val texture = TextureMapping()
        .put(FRONT, texture.suffix("_front"))
        .put(SIDE, texture.suffix("_side"))
    val wallModel = DnDModels.GRAVESTONE.create(gravestone, texture, modelOutput)
    val centerModel = DnDModels.GRAVESTONE_CENTERED.createWithSuffix(gravestone, CENTER, texture, modelOutput)
    delegateItemModel(gravestone, centerModel)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(gravestone).with(dispatchGravestone(wallModel, centerModel))
    )
}

fun BlockModelGenerators.registerSmallGravestone(gravestone: Block, texture: ResourceLocation = modelId(gravestone)) {
    val texture = TextureMapping()
        .put(FRONT, texture.suffix("_front"))
    val wallModel = DnDModels.SMALL_GRAVESTONE.create(gravestone, texture, modelOutput)
    val centerModel =
        DnDModels.SMALL_GRAVESTONE_CENTERED.createWithSuffix(gravestone, CENTER, texture, modelOutput)
    delegateItemModel(gravestone, centerModel)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(gravestone).with(dispatchGravestone(wallModel, centerModel))
    )
}

@Suppress("unused") // TODO either remove this or add the funny graves back
fun BlockModelGenerators.registerHauntedGravestone(hauntedGravestone: Block, gravestone: Block) {
    val wallModel = modelId(gravestone)
    val centerModel = modelId(gravestone, CENTER)
    delegateItemModel(hauntedGravestone, centerModel)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(hauntedGravestone).with(dispatchGravestone(wallModel, centerModel))
    )
}

fun BlockModelGenerators.registerHeadstone(headstone: Block) {
    val texture = TextureMapping().put(ALL, modelId(headstone))
    val wallModel = DnDModels.HEADSTONE.create(headstone, texture, modelOutput)
    val centerModel = DnDModels.HEADSTONE_CENTERED.createWithSuffix(headstone, CENTER, texture, modelOutput)
    createSimpleFlatItemModel(headstone)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(headstone).with(dispatchGravestone(wallModel, centerModel))
    )
}

fun dispatchGravestone(wallModel: ResourceLocation, centeredModel: ResourceLocation): PropertyDispatch {
    return PropertyDispatch.properties(HorizontalDirectionalBlock.FACING, GravestoneBlock.CENTERED)
        .generate { dir, isCenter ->
            val variant = Variant.variant()
                .with(VariantProperties.MODEL, if (isCenter) centeredModel else wallModel)

            when (dir) {
                Direction.NORTH -> variant.with(VariantProperties.Y_ROT, Rotation.R180)
                Direction.EAST -> variant.with(VariantProperties.Y_ROT, Rotation.R270)
                Direction.WEST -> variant.with(VariantProperties.Y_ROT, Rotation.R90)
                else -> variant
            }
        }
}
// endregion