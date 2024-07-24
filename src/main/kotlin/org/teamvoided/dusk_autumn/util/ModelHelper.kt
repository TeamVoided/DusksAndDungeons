package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.VariantSettings.Rotation
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id
import java.util.*


val ALL_KRY: TextureKey = TextureKey.of("all")
fun BlockStateModelGenerator.rockyTopSoilsOverlay(block: Block, reference: Block, cobbleOverlay: String) {
    val dirtTexture = Texture.getId(Blocks.DIRT)
    val texture = Texture()
        .put(TextureKey.BOTTOM, dirtTexture).inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
        .put(TextureKey.TOP, Texture.getSubId(reference, "_top"))
        .put(TextureKey.SIDE, Texture.getSubId(reference, "_snow"))
        .put(TextureKey.DIRT, id("block/$cobbleOverlay"))
    val blockModel = block(
        "parent/top_soil_with_overlay",
        TextureKey.BOTTOM,
        TextureKey.TOP,
        TextureKey.SIDE,
        TextureKey.DIRT
    )
    val snowModel = BlockStateVariant.create().put(
        VariantSettings.MODEL,
        blockModel.upload(
            block,
            "_snow",
            texture,
            this.modelCollector
        )
    )
    BlockStateVariant.create().put(
        VariantSettings.MODEL,
        blockModel.upload(block, texture, this.modelCollector)
    )
    BlockStateVariant.create().put(
        VariantSettings.MODEL,
        block(
            "parent/top_soil_with_overlay_90",
            TextureKey.BOTTOM,
            TextureKey.TOP,
            TextureKey.SIDE,
            TextureKey.DIRT
        ).upload(block.modelSuffix("_90"), texture, this.modelCollector)
    )
    BlockStateVariant.create().put(
        VariantSettings.MODEL,
        block(
            "parent/top_soil_with_overlay_180",
            TextureKey.BOTTOM,
            TextureKey.TOP,
            TextureKey.SIDE,
            TextureKey.DIRT
        ).upload(block.modelSuffix("_180"), texture, this.modelCollector)
    )
    BlockStateVariant.create().put(
        VariantSettings.MODEL,
        block(
            "parent/top_soil_with_overlay_270",
            TextureKey.BOTTOM,
            TextureKey.TOP,
            TextureKey.SIDE,
            TextureKey.DIRT
        ).upload(block.modelSuffix("_270"), texture, this.modelCollector)
    )
    this.registerRockyTopSoil(block, ModelIds.getBlockModelId(block), snowModel)
}

fun BlockStateModelGenerator.registerRockyTopSoil(
    topSoil: Block,
    modelId: Identifier,
    snowyVariant: BlockStateVariant
) {
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(topSoil).coordinate(
            BlockStateVariantMap.create(Properties.SNOWY)
                .register(true, snowyVariant)
                .register(false, listOf(*cobbledModelRandomRotation(modelId)))
        )
    )
}

fun BlockStateModelGenerator.cubeWithOverlay(block: Block, reference: Block, overlay: String) {
    val texture = Texture()
        .put(TextureKey.ALL, Texture.getId(reference))
        .put(TextureKey.DIRT, id("block/$overlay"))
    val model = block(
        "parent/cube_with_overlay",
        TextureKey.PARTICLE,
        TextureKey.ALL,
        TextureKey.DIRT
    ).upload(block, texture, this.modelCollector)
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(
            block,
            BlockStateVariant.create().put(VariantSettings.MODEL, model)
        )
    )
}

fun BlockStateModelGenerator.pathWithOverlay(
    block: Block,
    referencePath: Block,
    referenceDirt: Block,
    overlay: String
) {
    val texture = Texture()
        .put(TextureKey.TOP, Texture.getSubId(referencePath, "_top"))
        .put(TextureKey.SIDE, Texture.getSubId(referencePath, "_side"))
        .put(TextureKey.BOTTOM, Texture.getId(referenceDirt))
        .put(TextureKey.PARTICLE, Texture.getId(referenceDirt))
        .put(TextureKey.DIRT, id("block/$overlay"))
    val notRotatedModel = block(
        "parent/path_with_overlay",
        TextureKey.TOP,
        TextureKey.SIDE,
        TextureKey.BOTTOM,
        TextureKey.PARTICLE,
        TextureKey.DIRT
    ).upload(block, texture, this.modelCollector)
    block(
        "parent/path_with_overlay_90",
        TextureKey.TOP,
        TextureKey.SIDE,
        TextureKey.BOTTOM,
        TextureKey.PARTICLE,
        TextureKey.DIRT
    ).upload(block.modelSuffix("_90"), texture, this.modelCollector)
    block(
        "parent/path_with_overlay_180",
        TextureKey.TOP,
        TextureKey.SIDE,
        TextureKey.BOTTOM,
        TextureKey.PARTICLE,
        TextureKey.DIRT
    ).upload(block.modelSuffix("_180"), texture, this.modelCollector)
    block(
        "parent/path_with_overlay_270",
        TextureKey.TOP,
        TextureKey.SIDE,
        TextureKey.BOTTOM,
        TextureKey.PARTICLE,
        TextureKey.DIRT
    ).upload(block.modelSuffix("_270"), texture, this.modelCollector)
    this.blockStateCollector.accept(
        createBlockStateWithRandomHorizontalRotations(block, notRotatedModel)
    )
}

fun BlockStateModelGenerator.rotatableCubeWithOverlay(block: Block, reference: Block, overlay: String) {
    val texture = Texture()
        .put(TextureKey.ALL, Texture.getId(reference))
        .put(TextureKey.DIRT, id("block/$overlay"))
    val model = block(
        "parent/cube_with_overlay",
        TextureKey.PARTICLE,
        TextureKey.ALL,
        TextureKey.DIRT
    ).upload(block, texture, this.modelCollector)
    block(
        "parent/cube_with_overlay_90",
        TextureKey.PARTICLE,
        TextureKey.ALL,
        TextureKey.DIRT
    ).upload(block.modelSuffix("_90"), texture, this.modelCollector)
    block(
        "parent/cube_with_overlay_180",
        TextureKey.PARTICLE,
        TextureKey.ALL,
        TextureKey.DIRT
    ).upload(block.modelSuffix("_180"), texture, this.modelCollector)
    block(
        "parent/cube_with_overlay_270",
        TextureKey.PARTICLE,
        TextureKey.ALL,
        TextureKey.DIRT
    ).upload(block.modelSuffix("_270"), texture, this.modelCollector)
    this.blockStateCollector.accept(
        createBlockStateWithRandomHorizontalRotations(
            block,
            model
        )
    )
}

fun createBlockStateWithRandomHorizontalRotations(block: Block, modelId: Identifier): VariantsBlockStateSupplier {
    return VariantsBlockStateSupplier.create(block, *cobbledModelRandomRotation(modelId))
}

fun cobbledModelRandomRotation(modelId: Identifier): Array<BlockStateVariant> {
    return arrayOf(
        BlockStateVariant.create()
            .put(VariantSettings.MODEL, modelId),
        BlockStateVariant.create()
            .put(VariantSettings.MODEL, modelId.suffix("_90")),
        BlockStateVariant.create()
            .put(VariantSettings.MODEL, modelId.suffix("_180")),
        BlockStateVariant.create()
            .put(VariantSettings.MODEL, modelId.suffix("_270"))
    )
}


fun parentedItemModel(id: Identifier) = Model(Optional.of(id.withPrefix("item/")), Optional.empty())
fun BlockStateModelGenerator.registerParentedItemModel(block: Block) =
    this.registerParentedItemModel(block, block.model())

fun block(parent: String, vararg requiredTextures: TextureKey): Model {
    return Model(
        Optional.of(
            id("block/$parent")
        ), Optional.empty(), *requiredTextures
    )
}

fun block(parent: String, variant: String, vararg requiredTextures: TextureKey): Model {
    return Model(
        Optional.of(
            id("block/$parent")
        ), Optional.of(variant), *requiredTextures
    )
}

fun BlockStateModelGenerator.parentedModel(
    block: Block,
    textBlock: Block,
    parent: Identifier
): Identifier =
    Model(parent.myb, Optional.empty(), ALL_KRY)
        .upload(block.model(), Texture().put(ALL_KRY, textBlock.model()), this.modelCollector)

fun BlockStateModelGenerator.parentedModel(
    block: Identifier,
    textBlock: Block,
    parent: Identifier
): Identifier =
    Model(parent.myb, Optional.empty(), ALL_KRY)
        .upload(block, Texture().put(ALL_KRY, textBlock.model()), this.modelCollector)


private
val <T : Any?> T.myb get() = Optional.ofNullable(this)

fun Block.modelSuffix(str: String) = this.model().suffix(str)

fun Identifier.suffix(str: String) = Identifier(this.namespace, "${this.path}$str")
fun Block.model(): Identifier = ModelIds.getBlockModelId(this)