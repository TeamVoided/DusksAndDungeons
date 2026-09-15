package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.blockstates.PropertyDispatch.property
import net.minecraft.data.models.blockstates.Variant.variant
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.blockstates.VariantProperties.MODEL
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.data.models.model.DelegatedModel
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.SconceBlock
import org.teamvoided.dusks_and_dungeons.block.big.BigRedstoneLanternBlock
import org.teamvoided.dusks_and_dungeons.block.candelabra.EmptyCandelabraBlock
import org.teamvoided.dusks_and_dungeons.client.init.DnDItemsClient
import java.util.function.Supplier


fun BlockModelGenerators.createBigChain(block: Block) {
    val texture = TextureMapping()
        .put(PARTICLE, TextureMapping.getBlockTexture(block))
        .put(ALL, TextureMapping.getBlockTexture(block))
    val model = DnDModels.BIG_CHAIN.create(block, texture, modelOutput)

    createAxisAlignedPillarBlockCustomModel(block, model)
    createSimpleFlatItemModel(block.asItem())
}

fun BlockModelGenerators.createBigLantern(block: Block, hasOffVariant: Boolean = false) {
    createBigLantern(block, id("block/big_lantern_bottom"), hasOffVariant)
}

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
        MultiVariantGenerator.multiVariant(block, variant().with(MODEL, model))
            .with(createUpFacing())

    if (hasOffVariant) {
        val textureOff = TextureMapping()
            .put(SIDE, TextureMapping.getBlockTexture(block, "_off"))
            .put(END, bottom)
            .copySlot(SIDE, PARTICLE)
        val modelOff = DnDModels.BIG_LANTERN.createWithSuffix(block, "_off", textureOff, modelOutput)

        multiGenerator.with(
            property(BigRedstoneLanternBlock.LIT)
                .select(true, variant())
                .select(false, variant().with(MODEL, modelOff))
        )
    }

    blockStateOutput.accept(multiGenerator)
}

fun BlockModelGenerators.createBigScaffolding(scaffolding: Block) {
    // TODO(1.0) make this be parented models instead
    val stable = ModelLocationUtils.getModelLocation(scaffolding, "_stable")
    val unstable = ModelLocationUtils.getModelLocation(scaffolding, "_unstable")
    delegateItemModel(scaffolding, stable)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(scaffolding)
            .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.BOTTOM, unstable, stable))
    )
}

// region Candelabra

fun BlockModelGenerators.createCandelabra(emptyCandelabra: Block, candelabra: Block) {
    val texture = TextureMapping.defaultTexture(candelabra)
        .put(TEXTURE, modelId(candelabra))
    val models = listOf(
        DnDModels.CANDELABRA_1,
        DnDModels.CANDELABRA_2,
        DnDModels.CANDELABRA_3,
        DnDModels.CANDELABRA_4,
        DnDModels.CANDELABRA_5
    ).map { it.create(candelabra, texture, modelOutput) }

    blockStateOutput.accept(candelabraProperties(candelabra, models))
    blockStateOutput.accept(candelabraProperties(emptyCandelabra, models))

    modelOutput.accept(
        ModelLocationUtils.getModelLocation(candelabra.asItem()),
        createCandelabraItemModels(models.first(), models)
    )
}

fun candelabraProperties(candelabra: Block, models: List<ResourceLocation>): MultiVariantGenerator {
    return MultiVariantGenerator.multiVariant(candelabra)
        .with(BlockModelGenerators.createHorizontalFacingDispatch())
        .with(
            property(EmptyCandelabraBlock.CANDLES)
                .generate { variant().with(MODEL, models[it - 1]) }
        )
}

fun createCandelabraItemModels(baseModel: ResourceLocation, models: List<ResourceLocation>): Supplier<JsonElement> {
    val modelObj = DelegatedModel(baseModel).get().asJsonObject
    val jsonArray = JsonArray()

    for ((idx, model) in models.drop(1).withIndex()) {
        val predicate = JsonObject()
        predicate.addProperty(DnDItemsClient.CANDELABRA_PREDICATE_ID.toString(), (idx + 1) / (models.size - 1.0))
        val modelObj = JsonObject()
        modelObj.add("predicate", predicate)
        modelObj.addProperty("model", model.toString())
        jsonArray.add(modelObj)
    }

    modelObj.add("overrides", jsonArray)
    return Supplier { modelObj }
}

// endregion


// region Sconce

fun BlockModelGenerators.sconce(block: Block) {
    val texture = TextureMapping()
        .put(SIDE, TextureMapping.getBlockTexture(block))
        .put(TOP, TextureMapping.getBlockTexture(block, "_top"))
    val model = DnDModels.SCONCE.create(block, texture, modelOutput)

    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block, variant(model))
            .with(createSconceDispatch())
    )
}

fun createSconceDispatch(): PropertyDispatch {
    return PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, SconceBlock.HANGING)
        .generate { facing, hanging ->
            val rotation = when (facing) {
                Direction.EAST -> Rotation.R90
                Direction.SOUTH -> Rotation.R180
                Direction.WEST -> Rotation.R270
                else -> Rotation.R0
            }
            if (hanging)
                variant()
                    .with(VariantProperties.X_ROT, Rotation.R180)
                    .with(VariantProperties.Y_ROT, rotation.opposite())
            else
                variant().with(VariantProperties.Y_ROT, rotation)
        }
}

// endregion