package org.teamvoided.dusk_autumn.util

import com.mojang.datafixers.util.Pair
import net.minecraft.block.*
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.BlockStateModelGenerator.*
import net.minecraft.data.client.model.When.PropertyCondition
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import net.minecraft.util.Util
import net.minecraft.util.math.Direction
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.block.MoonberryVineBlock
import java.util.*
import java.util.function.Function


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

fun BlockStateModelGenerator.createLeafPile(leafPile: Block, leaves: Block) {
    val layer1 = this.parentedModel(leafPile, leaves, leafPile())
    val layer2 = this.parentedModel(leafPile.modelSuffix("_8"), leaves, leafPile("8"))
    val layer3 = this.parentedModel(leafPile.modelSuffix("_12"), leaves, leafPile("12"))
    val hanging1 = this.parentedModel(leafPile.modelSuffix("_hanging"), leaves, leafPileHanging())
    val hanging2 = this.parentedModel(leafPile.modelSuffix("_hanging_8"), leaves, leafPileHanging("8"))
    val hanging3 = this.parentedModel(leafPile.modelSuffix("_hanging_12"), leaves, leafPileHanging("12"))
    val full = this.parentedModel(leafPile.modelSuffix("_full"), leaves, leafPile("full"))
    this.blockStateCollector.accept(
        MultipartBlockStateSupplier.create(leafPile)
            .with(
                When.create().set(LeafPileBlock.PILE_LAYERS, 1).set(Properties.HANGING, false),
                BlockStateVariant.create().put(VariantSettings.MODEL, layer1)
            ).with(
                When.create().set(LeafPileBlock.PILE_LAYERS, 2).set(Properties.HANGING, false),
                BlockStateVariant.create().put(VariantSettings.MODEL, layer2)
            ).with(
                When.create().set(LeafPileBlock.PILE_LAYERS, 3).set(Properties.HANGING, false),
                BlockStateVariant.create().put(VariantSettings.MODEL, layer3)
            ).with(
                When.create().set(LeafPileBlock.PILE_LAYERS, 1).set(Properties.HANGING, true),
                BlockStateVariant.create().put(VariantSettings.MODEL, hanging1)
            ).with(
                When.create().set(LeafPileBlock.PILE_LAYERS, 2).set(Properties.HANGING, true),
                BlockStateVariant.create().put(VariantSettings.MODEL, hanging2)
            ).with(
                When.create().set(LeafPileBlock.PILE_LAYERS, 3).set(Properties.HANGING, true),
                BlockStateVariant.create().put(VariantSettings.MODEL, hanging3)
            ).with(
                When.create().set(LeafPileBlock.PILE_LAYERS, 4),
                BlockStateVariant.create().put(VariantSettings.MODEL, full)
            )
    )
}

private fun leafPile(num: String? = null): Identifier =
    id("block/parent/leaf_pile${if (num != null) "_$num" else ""}")

private fun leafPileHanging(num: String? = null): Identifier =
    id("block/parent/leaf_pile_hanging${if (num != null) "_$num" else ""}")

fun BlockStateModelGenerator.createMoonberryVine(block: Block) {
    excludeFromSimpleItemModelGeneration(block)
    val allDirectionFalse = When.create()
        .set(AbstractLichenBlock.getProperty(Direction.NORTH), false)
        .set(AbstractLichenBlock.getProperty(Direction.SOUTH), false)
        .set(AbstractLichenBlock.getProperty(Direction.EAST), false)
        .set(AbstractLichenBlock.getProperty(Direction.WEST), false)
        .set(AbstractLichenBlock.getProperty(Direction.DOWN), false)
        .set(AbstractLichenBlock.getProperty(Direction.UP), false)
    val model = MultipartBlockStateSupplier.create(block)
    val directions = listOf(
        (Direction.NORTH to VariantSettings.Rotation.R0),
        (Direction.EAST to VariantSettings.Rotation.R90),
        (Direction.SOUTH to VariantSettings.Rotation.R180),
        (Direction.WEST to VariantSettings.Rotation.R270),
        (Direction.DOWN to VariantSettings.Rotation.R90),
        (Direction.UP to VariantSettings.Rotation.R270)
    )
    var modelId: Identifier
    var axis: VariantSetting<VariantSettings.Rotation>
    var variantRotation: VariantSettings.Rotation

    directions.forEach { (direction, rotation) ->
        axis = VariantSettings.Y
        for (berries in 0..2) {
            modelId = id("block/parent/moonberry_vine_$berries")
            variantRotation = rotation
            if (direction == Direction.DOWN || direction == Direction.UP) {
                if (berries == 2) {
                    modelId = id("block/parent/moonberry_vine_3")
                    variantRotation =
                        if (direction == Direction.DOWN) {
                            VariantSettings.Rotation.R0
                        } else {
                            VariantSettings.Rotation.R180
                        }
                }
                axis = VariantSettings.X
            }
            model.with(
                When.create().set(AbstractLichenBlock.getProperty(direction), true)
                    .set(MoonberryVineBlock.BERRIES, berries),
                BlockStateVariant.create()
                    .put(
                        VariantSettings.MODEL,
                        modelId
                    ).put(axis, variantRotation)
            )
        }
        model.with(
            allDirectionFalse,
            BlockStateVariant.create()
                .put(
                    VariantSettings.MODEL,
                    id("block/parent/moonberry_vine_0")
                ).put(axis, rotation)
        )
    }
    this.blockStateCollector.accept(model)
//    this.blockStateCollector.accept(
//        MultipartBlockStateSupplier.create(block)
//            .with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.NORTH), true)
//                    .set(MoonberryVineBlock.BERRIES, 0),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    )
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.NORTH), true)
//                    .set(MoonberryVineBlock.BERRIES, 1),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries")
//                    )
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.NORTH), true)
//                    .set(MoonberryVineBlock.BERRIES, 2),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries_wall_extra")
//                    )
//            ).with(
//                allDirectionFalse,
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    )
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.EAST), true)
//                    .set(MoonberryVineBlock.BERRIES, 0),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R90)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.EAST), true)
//                    .set(MoonberryVineBlock.BERRIES, 1),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R90)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.EAST), true)
//                    .set(MoonberryVineBlock.BERRIES, 2),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries_wall_extra")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R90)
//            ).with(
//                allDirectionFalse,
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R90)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.SOUTH), true)
//                    .set(MoonberryVineBlock.BERRIES, 0),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R180)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.SOUTH), true)
//                    .set(MoonberryVineBlock.BERRIES, 1),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R180)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.SOUTH), true)
//                    .set(MoonberryVineBlock.BERRIES, 2),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries_wall_extra")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R180)
//            ).with(
//                allDirectionFalse,
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R180)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.WEST), true)
//                    .set(MoonberryVineBlock.BERRIES, 0),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R270)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.WEST), true)
//                    .set(MoonberryVineBlock.BERRIES, 1),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R270)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.WEST), true)
//                    .set(MoonberryVineBlock.BERRIES, 2),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries_wall_extra")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R270)
//            ).with(
//                allDirectionFalse,
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.Y, VariantSettings.Rotation.R270)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.UP), true)
//                    .set(MoonberryVineBlock.BERRIES, 0),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.X, VariantSettings.Rotation.R270)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.UP), true)
//                    .set(MoonberryVineBlock.BERRIES, 1),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries")
//                    ).put(VariantSettings.X, VariantSettings.Rotation.R270)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.UP), true)
//                    .set(MoonberryVineBlock.BERRIES, 2),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries_floor_extra")
//                    ).put(VariantSettings.X, VariantSettings.Rotation.R180)
//            ).with(
//                allDirectionFalse,
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.X, VariantSettings.Rotation.R90)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.DOWN), true)
//                    .set(MoonberryVineBlock.BERRIES, 0),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.X, VariantSettings.Rotation.R90)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.DOWN), true)
//                    .set(MoonberryVineBlock.BERRIES, 1),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries")
//                    ).put(VariantSettings.X, VariantSettings.Rotation.R90)
//            ).with(
//                When.create().set(AbstractLichenBlock.getProperty(Direction.DOWN), true)
//                    .set(MoonberryVineBlock.BERRIES, 2),
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine_berries_floor_extra")
//                    )
//            ).with(
//                allDirectionFalse,
//                BlockStateVariant.create()
//                    .put(
//                        VariantSettings.MODEL,
//                        id("block/moonberry_vine")
//                    ).put(VariantSettings.X, VariantSettings.Rotation.R90)
//            )
//    )
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