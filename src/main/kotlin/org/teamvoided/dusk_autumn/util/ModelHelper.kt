package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.BlockStateModelGenerator.*
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumn.DuskAutumns.id
import java.util.*


val ALL_KRY: TextureKey = TextureKey.of("all")

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

fun BlockStateModelGenerator.cubeWithTintedOverlay(block: Block, reference: Block, overlay: String) {
    val texture = Texture()
        .put(TextureKey.ALL, Texture.getId(reference))
        .put(TextureKey.DIRT, id("block/$overlay"))
    val model = block(
        "parent/cube_with_tinted_overlay",
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

fun BlockStateModelGenerator.stairsWithTintedOverlay(block: Block, baseBlock: Block, overlay: String) =
    this.stairsWithTintedOverlay(block, baseBlock, baseBlock, baseBlock, overlay, overlay, overlay)

fun BlockStateModelGenerator.stairsWithTintedOverlay(
    block: Block,
    bottom: Block,
    side: Block,
    top: Block,
    down: String,
    north: String,
    up: String
) {
    val texture: Texture = Texture.texture(block)
        .put(TextureKey.BOTTOM, Texture.getId(bottom))
        .put(TextureKey.SIDE, Texture.getId(side))
        .put(TextureKey.TOP, Texture.getId(top))
        .put(TextureKey.DOWN, id("block/$down"))
        .put(TextureKey.NORTH, id("block/$north"))
        .put(TextureKey.UP, id("block/$up"))

    val inner: Identifier = slabOrStairWithOverlayModel("parent/stairs_inner_with_tinted_overlay")
        .upload(block, "_inner", texture, this.modelCollector)
    val er: Identifier = slabOrStairWithOverlayModel("parent/stairs_with_tinted_overlay")
        .upload(block, texture, this.modelCollector)
    val outer: Identifier = slabOrStairWithOverlayModel("parent/stairs_outer_with_tinted_overlay")
        .upload(block, "_outer", texture, this.modelCollector)

    this.blockStateCollector.accept(createStairsBlockState(block, inner, er, outer))
    this.registerParentedItemModel(block, er)
}

fun BlockStateModelGenerator.slabWithTintedOverlay(slab: Block, slabOf: Identifier, baseBlock: Block, overlay: String) =
    this.slabWithTintedOverlay(slab, slabOf, baseBlock, baseBlock, baseBlock, overlay, overlay, overlay)

fun BlockStateModelGenerator.slabWithTintedOverlay(
    slab: Block,
    slabOf: Identifier,
    bottom: Block,
    side: Block,
    top: Block,
    down: String,
    north: String,
    up: String
) {
    val texture: Texture = Texture.texture(slab)
        .put(TextureKey.BOTTOM, Texture.getId(bottom))
        .put(TextureKey.SIDE, Texture.getId(side))
        .put(TextureKey.TOP, Texture.getId(top))
        .put(TextureKey.DOWN, id("block/$down"))
        .put(TextureKey.NORTH, id("block/$north"))
        .put(TextureKey.UP, id("block/$up"))

    val bottomModel: Identifier = slabOrStairWithOverlayModel("parent/slab_with_tinted_overlay")
        .upload(slab, texture, this.modelCollector)
    val topModel: Identifier = slabOrStairWithOverlayModel("parent/slab_top_with_tinted_overlay")
        .upload(slab, "_top", texture, this.modelCollector)

    this.blockStateCollector.accept(createSlabBlockState(slab, bottomModel, topModel, slabOf))
    this.registerParentedItemModel(slab, bottomModel)
}

fun BlockStateModelGenerator.wallWithTintedOverlay(wall: Block, wallOf: Block, overlay: String) {
    val texture = Texture.texture(wall)
        .put(TextureKey.WALL, Texture.getId(wallOf))
        .put(TextureKey.DIRT, id("block/$overlay"))

    val post = block(
        "parent/wall_post_with_tinted_overlay",
        "_post",
        TextureKey.WALL,
        TextureKey.DIRT
    ).upload(wall, texture, this.modelCollector)
    val side = block(
        "parent/wall_side_with_tinted_overlay",
        "_side",
        TextureKey.WALL,
        TextureKey.DIRT
    ).upload(wall, texture, this.modelCollector)
    val sideTall = block(
        "parent/wall_side_tall_with_tinted_overlay",
        "_side_tall",
        TextureKey.WALL,
        TextureKey.DIRT
    ).upload(wall, texture, this.modelCollector)
    this.blockStateCollector.accept(createWallBlockState(wall, post, side, sideTall))
    this.registerParentedItemModel(
        wall, block(
            "parent/wall_inventory_with_tinted_overlay",
            "_inventory",
            TextureKey.WALL,
            TextureKey.DIRT
        ).upload(wall, texture, this.modelCollector)
    )
}

fun BlockStateModelGenerator.rockyGrassOverlay(block: Block, overlay: String) {
    val dirtTexture = Texture.getId(Blocks.DIRT)
    val grassTexture = Texture.getId(Blocks.GRASS_BLOCK)
    val texture: Texture = Texture()
        .put(TextureKey.BOTTOM, dirtTexture).inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
        .put(TextureKey.TOP, grassTexture.suffix("_top"))
        .put(TextureKey.LAYER0, grassTexture.suffix("_side"))
        .put(TextureKey.LAYER1, id("block/$overlay"))
        .put(TextureKey.LAYER2, grassTexture.suffix("_side_overlay"))
    val textureSnow = Texture()
        .put(TextureKey.BOTTOM, dirtTexture).inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
        .put(TextureKey.TOP, grassTexture.suffix("_top"))
        .put(TextureKey.LAYER0, grassTexture.suffix("_snow"))
        .put(TextureKey.LAYER1, id("block/$overlay"))
    val blockModel = block(
        "parent/top_soil_with_overlay",
        TextureKey.BOTTOM,
        TextureKey.TOP,
        TextureKey.LAYER0,
        TextureKey.LAYER1
    )
    var offsetInt = 0
    var suffix = ""
    repeat(4) {
        BlockStateVariant.create().put(
            VariantSettings.MODEL,
            block(
                "parent/top_soil_tinted_with_overlay$suffix",
                TextureKey.BOTTOM,
                TextureKey.TOP,
                TextureKey.LAYER0,
                TextureKey.LAYER1,
                TextureKey.LAYER2
            ).upload(block.modelSuffix(suffix), texture, this.modelCollector)
        )
        offsetInt += 90
        suffix = "_$offsetInt"
    }
    val snowModel = BlockStateVariant.create().put(
        VariantSettings.MODEL,
        blockModel.upload(
            block,
            "_snow",
            textureSnow,
            this.modelCollector
        )
    )
    this.registerRockyTopSoil(block, ModelIds.getBlockModelId(block), snowModel)
}

fun BlockStateModelGenerator.rockyTopSoilsOverlay(block: Block, reference: Block, snowy: Block, overlay: String) {
    val dirtTexture = Texture.getId(Blocks.DIRT)
    val texture: Texture = Texture()
        .put(TextureKey.BOTTOM, dirtTexture).inherit(TextureKey.BOTTOM, TextureKey.PARTICLE)
        .put(TextureKey.TOP, Texture.getSubId(reference, "_top"))
        .put(TextureKey.LAYER0, Texture.getSubId(reference, "_side"))
        .put(TextureKey.LAYER1, id("block/$overlay"))
    var offsetInt = 0
    var suffix = ""
    repeat(4) {
        BlockStateVariant.create().put(
            VariantSettings.MODEL,
            block(
                "parent/top_soil_with_overlay$suffix",
                TextureKey.BOTTOM,
                TextureKey.TOP,
                TextureKey.LAYER0,
                TextureKey.LAYER1
            ).upload(block.modelSuffix(suffix), texture, this.modelCollector)
        )
        offsetInt += 90
        suffix = "_$offsetInt"
    }

    val snowModel = BlockStateVariant().put(VariantSettings.MODEL, snowy.modelSuffix("_snow"))
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
    var offsetInt = 90
    var suffix = "_90"
    repeat(3) {
        BlockStateVariant.create().put(
            VariantSettings.MODEL,
            block(
                "parent/path_with_overlay$suffix",
                TextureKey.TOP,
                TextureKey.SIDE,
                TextureKey.BOTTOM,
                TextureKey.PARTICLE,
                TextureKey.DIRT
            ).upload(block.modelSuffix(suffix), texture, this.modelCollector)
        )
        offsetInt += 90
        suffix = "_$offsetInt"
    }
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
    var offsetInt = 90
    var suffix = "_90"
    repeat(3) {
        BlockStateVariant.create().put(
            VariantSettings.MODEL,
            block(
                "parent/cube_with_overlay$suffix",
                TextureKey.PARTICLE,
                TextureKey.ALL,
                TextureKey.DIRT
            ).upload(block.modelSuffix(suffix), texture, this.modelCollector)
        )
        offsetInt += 90
        suffix = "_$offsetInt"
    }
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

fun slabOrStairWithOverlayModel(parent: String): Model {
    return block(
        parent,
        TextureKey.BOTTOM,
        TextureKey.SIDE,
        TextureKey.TOP,
        TextureKey.DOWN,
        TextureKey.NORTH,
        TextureKey.UP
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