package org.teamvoided.dusk_autumn.util

import it.unimi.dsi.fastutil.ints.Int2ObjectFunction
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.block.AbstractLichenBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.SnowyBlock
import net.minecraft.block.enums.BlockHalf
import net.minecraft.block.enums.SlabType
import net.minecraft.block.enums.StairShape
import net.minecraft.block.enums.WallShape
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.BlockStateModelGenerator.*
import net.minecraft.data.client.model.VariantSettings.Rotation
import net.minecraft.state.property.Properties
import net.minecraft.state.property.Property
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.block.MoonberryVineBlock
import java.util.*


val ALL_KRY: TextureKey = TextureKey.of("all")


fun BlockStateModelGenerator.cubeOverlay(overlay: Identifier) {
    val texture = Texture().put(TextureKey.ALL, overlay)
    Models.CUBE_ALL.upload(overlay, texture, this.modelCollector)
}

fun BlockStateModelGenerator.tintedCubeOverlay(overlay: Identifier) {
    val texture = Texture().put(TextureKey.ALL, overlay)
    block("parent/cube_tinted_all", TextureKey.ALL).upload(overlay, texture, this.modelCollector)
}

fun BlockStateModelGenerator.cube15Overlay(overlay: Identifier) {
    val texture = Texture().put(TextureKey.ALL, overlay)
    block("parent/cube_15_all", TextureKey.ALL).upload(overlay.suffix("_15"), texture, this.modelCollector)
}

fun BlockStateModelGenerator.tintedStairAllOverlay(overlay: Identifier) {
    this.tintedStairOverlay(overlay, overlay, overlay, overlay)
}

fun BlockStateModelGenerator.tintedStairOverlay(
    overlay: Identifier,
    overlayTop: Identifier,
    overlayBottom: Identifier,
    overlaySide: Identifier
) {
    val texture: Texture = Texture()
        .put(TextureKey.TOP, overlayTop)
        .put(TextureKey.BOTTOM, overlayBottom)
        .put(TextureKey.SIDE, overlaySide)

    block("parent/stairs_with_tint", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE)
        .upload(overlay.suffix("_stairs"), texture, this.modelCollector)
    block("parent/stairs_inner_with_tint", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE)
        .upload(overlay.suffix("_stairs_inner"), texture, this.modelCollector)
    block("parent/stairs_outer_with_tint", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE)
        .upload(overlay.suffix("_stairs_outer"), texture, this.modelCollector)
}

fun BlockStateModelGenerator.tintedSlabAllOverlay(overlay: Identifier) {
    this.tintedSlabOverlay(overlay, overlay, overlay, overlay)
}

fun BlockStateModelGenerator.tintedSlabOverlay(
    overlay: Identifier,
    overlayTop: Identifier,
    overlayBottom: Identifier,
    overlaySide: Identifier
) {
    val texture = Texture()
        .put(TextureKey.TOP, overlayTop)
        .put(TextureKey.BOTTOM, overlayBottom)
        .put(TextureKey.SIDE, overlaySide)
    block("parent/slab_with_tint", "_post", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE)
        .upload(overlay.suffix("_slab"), texture, this.modelCollector)
    block("parent/slab_top_with_tint", "_side", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE)
        .upload(overlay.suffix("_slab_top"), texture, this.modelCollector)
}

fun BlockStateModelGenerator.tintedWallOverlay(overlay: Identifier) {
    val texture = Texture().put(TextureKey.WALL, overlay)
    block("parent/wall_post_with_tint", "_post", TextureKey.WALL)
        .upload(overlay.suffix("_wall_post"), texture, this.modelCollector)
    block("parent/wall_side_with_tint", "_side", TextureKey.WALL)
        .upload(overlay.suffix("_wall_side"), texture, this.modelCollector)
}

fun BlockStateModelGenerator.registerTintedOverlay(overlay: Identifier) {
    this.tintedCubeOverlay(overlay)
    this.tintedStairAllOverlay(overlay)
    this.tintedSlabAllOverlay(overlay)
    this.tintedWallOverlay(overlay)
}

fun BlockStateModelGenerator.cubeAllWithOverlay(block: Block, reference: Block, overlay: Identifier) {
    this.cubeWithOverlay(block, reference, overlay)
    this.cubeItemAllWithOverlay(block, reference, overlay)
}

fun BlockStateModelGenerator.cube15WithOverlay(block: Block, reference: Block, overlay: Identifier) {
    this.cubeWithOverlay(block, reference, overlay.suffix("_15"))
    this.cubeItem15WithOverlay(block, reference, overlay)
}

fun BlockStateModelGenerator.rotatableCubeAllWithOverlay(block: Block, reference: Block, overlay: Identifier) {
    this.rotatableCubeWithOverlay(block, reference, overlay)
    this.cubeItemAllWithOverlay(block, reference, overlay)
}

fun BlockStateModelGenerator.cubeSnowableColumnWithOverlay(block: Block, reference: Block, overlay: Identifier) {
    this.cubeSnowableColumnOverlay(block, reference, overlay)
    this.cubeSnowableColumnOverlayItem(block, reference, overlay)
}

fun BlockStateModelGenerator.grassWithOverlay(block: Block, reference: Block, overlay: Identifier) {
    this.cubeSnowableColumnOverlay(block, reference, overlay)
    val texture = Texture()
        .put(TextureKey.BOTTOM, Blocks.DIRT.model())
        .put(TextureKey.TOP, reference.model("_top"))
        .put(TextureKey.LAYER0, reference.model("_side"))
        .put(TextureKey.LAYER1, reference.model("_side_overlay"))
        .put(TextureKey.LAYER2, overlay)
    block(
        "parent/grass_block_with_overlay_inventory",
        TextureKey.BOTTOM,
        TextureKey.TOP,
        TextureKey.LAYER0,
        TextureKey.LAYER1,
        TextureKey.LAYER2
    ).upload(
        ModelIds.getItemModelId(block.asItem()), texture,
        this.modelCollector
    )
}

fun BlockStateModelGenerator.cubeItemAllWithOverlay(block: Block, reference: Block, overlay: Identifier) {
//    yes this is a lazy snow block edge case
    val texture = Texture()
        .put(
            TextureKey.ALL, if (reference == Blocks.SNOW_BLOCK) Identifier.of(
                reference.model().namespace,
                reference.model().path.removeSuffix("_block")
            ) else reference.model()
        )
        .put(TextureKey.DIRT, overlay)
    block("parent/cube_all_overlay_inventory", TextureKey.ALL, TextureKey.DIRT).upload(
        ModelIds.getItemModelId(block.asItem()), texture,
        this.modelCollector
    )
}

fun BlockStateModelGenerator.cubeItem15WithOverlay(block: Block, reference: Block, overlay: Identifier) {
    val texture = Texture()
        .put(TextureKey.BOTTOM, Blocks.DIRT.model())
        .put(TextureKey.TOP, reference.model("_top"))
        .put(TextureKey.SIDE, reference.model("_side"))
        .put(TextureKey.DIRT, overlay)
    block(
        "parent/cube_15_overlay_inventory",
        TextureKey.BOTTOM,
        TextureKey.TOP,
        TextureKey.SIDE,
        TextureKey.DIRT
    ).upload(
        ModelIds.getItemModelId(block.asItem()), texture,
        this.modelCollector
    )
}

fun BlockStateModelGenerator.cubeSnowableColumnOverlayItem(block: Block, reference: Block, overlay: Identifier) {
    val texture = Texture()
        .put(TextureKey.DIRT, overlay)
        .put(TextureKey.DOWN, Blocks.DIRT.model())
        .put(TextureKey.UP, reference.model("_top"))
        .put(TextureKey.SIDE, reference.model("_side"))
    block(
        "parent/cube_bottom_top_overlay_inventory",
        TextureKey.DIRT,
        TextureKey.DOWN,
        TextureKey.UP,
        TextureKey.SIDE
    ).upload(
        ModelIds.getItemModelId(block.asItem()), texture,
        this.modelCollector
    )
}

fun BlockStateModelGenerator.cubeWithOverlay(block: Block, reference: Block, overlay: Identifier) {
    val model = MultipartBlockStateSupplier.create(block)
    model.with(
        BlockStateVariant.create()
            .put(
                VariantSettings.MODEL,
                reference.model()
            )
    )
    model.with(
        BlockStateVariant.create()
            .put(
                VariantSettings.MODEL,
                overlay
            )
    )
    this.blockStateCollector.accept(model)
}

fun BlockStateModelGenerator.rotatableCubeWithOverlay(block: Block, reference: Block, overlay: Identifier) {
    val model = MultipartBlockStateSupplier.create(block)
    model.with(
        listOf(*createModelVariantWithRandomHorizontalRotations(reference.model()))
    )
    model.with(
        BlockStateVariant.create()
            .put(
                VariantSettings.MODEL,
                overlay
            )
    )
    this.blockStateCollector.accept(model)
}

fun BlockStateModelGenerator.cubeSnowableColumnOverlay(block: Block, reference: Block, overlay: Identifier) {
    val model = MultipartBlockStateSupplier.create(block)
    model.with(
        When.create().set(SnowyBlock.SNOWY, false),
        listOf(*createModelVariantWithRandomHorizontalRotations(reference.model()))
    )
    model.with(
        When.create().set(SnowyBlock.SNOWY, true),
        BlockStateVariant.create()
            .put(
                VariantSettings.MODEL,
                Blocks.GRASS_BLOCK.model("_snow")
            )
    )
    model.with(
        BlockStateVariant.create()
            .put(
                VariantSettings.MODEL,
                overlay
            )
    )
    this.blockStateCollector.accept(model)
}

fun BlockStateModelGenerator.stairsWithTintedOverlay(
    stairsBlock: Block, baseBlock: Block, overlay: Identifier
) {
    val regularModelId = "_stairs"
    val innerModelId = "_stairs_inner"
    val outerModelId = "_stairs_outer"
    val half = listOf(
        (BlockHalf.BOTTOM to Rotation.R0),
        (BlockHalf.TOP to Rotation.R180)
    )
    val directions = listOf(
        (Direction.EAST to Rotation.R0),
        (Direction.SOUTH to Rotation.R90),
        (Direction.WEST to Rotation.R180),
        (Direction.NORTH to Rotation.R270)
    )
    val stairShape = listOf(
        (StairShape.STRAIGHT to regularModelId),
        (StairShape.INNER_LEFT to innerModelId),
        (StairShape.INNER_RIGHT to innerModelId),
        (StairShape.OUTER_LEFT to outerModelId),
        (StairShape.OUTER_RIGHT to outerModelId)
    )

    val model = MultipartBlockStateSupplier.create(stairsBlock)
    var rotatY: Rotation

    half.forEach { (half, rotationX) ->
        directions.forEach { (direction, rotationY) ->
            stairShape.forEach { (shape, models) ->
                rotatY = if (shape == StairShape.INNER_LEFT || shape == StairShape.OUTER_LEFT) {
                    when (rotationY) {
                        Rotation.R0 -> Rotation.R270
                        Rotation.R90 -> Rotation.R0
                        Rotation.R180 -> Rotation.R90
                        else -> Rotation.R180
                    }
                } else {
                    rotationY
                }
                model.with(
                    When.create()
                        .set(Properties.HORIZONTAL_FACING, direction)
                        .set(Properties.BLOCK_HALF, half)
                        .set(Properties.STAIR_SHAPE, shape),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            stairTitle(baseBlock, models)
                        )
                        .put(VariantSettings.X, rotationX)
                        .put(VariantSettings.Y, rotatY)
                        .put(VariantSettings.UVLOCK, true)
                ).with(
                    When.create()
                        .set(Properties.HORIZONTAL_FACING, direction)
                        .set(Properties.BLOCK_HALF, half)
                        .set(Properties.STAIR_SHAPE, shape),
                    BlockStateVariant.create()
                        .put(
                            VariantSettings.MODEL,
                            overlay.suffix(models)
                        )
                        .put(VariantSettings.X, rotationX)
                        .put(VariantSettings.Y, rotatY)
                        .put(VariantSettings.UVLOCK, true)
                )
            }
        }
    }
    this.blockStateCollector.accept(model)
    val texture: Texture = Texture.texture(stairsBlock)
        .put(TextureKey.BOTTOM, Texture.getId(baseBlock))
        .put(TextureKey.SIDE, Texture.getId(baseBlock))
        .put(TextureKey.TOP, Texture.getId(baseBlock))
        .put(TextureKey.DOWN, overlay)
        .put(TextureKey.NORTH, overlay)
        .put(TextureKey.UP, overlay)
    this.registerParentedItemModel(
        stairsBlock,
        slabOrStairWithOverlayModel("parent/stairs_inventory_with_tinted_overlay")
            .upload(stairsBlock, texture, this.modelCollector)
    )
}

fun stairTitle(block: Block, suffix: String): Identifier {
    return Identifier.of(block.model().namespace, block.model().path.removeSuffix("s")).suffix(suffix)
}

fun BlockStateModelGenerator.slabWithTintedOverlay(
    slab: Block, baseBlock: Block, overlay: Identifier
) {
    val slabOfTexture = Identifier.of(baseBlock.model().namespace, baseBlock.model().path.removeSuffix("s"))
    val model = MultipartBlockStateSupplier.create(slab)
    val slabType = listOf(
        (SlabType.BOTTOM to "_slab"),
        (SlabType.TOP to "_slab_top"),
        (SlabType.DOUBLE to "")
    )
    slabType.forEach { (type, suffix) ->
        model.with(
            When.create().set(Properties.SLAB_TYPE, type),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, if (suffix == "") baseBlock.model() else slabOfTexture.suffix(suffix))
        ).with(
            When.create().set(Properties.SLAB_TYPE, type),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, overlay.suffix(suffix))
        )
    }
    this.blockStateCollector.accept(model)


    val texture: Texture = Texture.texture(slab)
        .put(TextureKey.BOTTOM, Texture.getId(baseBlock))
        .put(TextureKey.SIDE, Texture.getId(baseBlock))
        .put(TextureKey.TOP, Texture.getId(baseBlock))
        .put(TextureKey.DOWN, overlay)
        .put(TextureKey.NORTH, overlay)
        .put(TextureKey.UP, overlay)
    this.registerParentedItemModel(
        slab,
        slabOrStairWithOverlayModel("parent/slab_inventory_with_tinted_overlay")
            .upload(slab, texture, this.modelCollector)
    )
}


fun slabOrStairWithOverlayModel(parent: String): Model {
    return block(
        parent,
        TextureKey.BOTTOM,
        TextureKey.TOP,
        TextureKey.SIDE,
        TextureKey.DOWN,
        TextureKey.UP,
        TextureKey.NORTH
    )
}

fun BlockStateModelGenerator.wallWithOverlay(wall: Block, baseBlock: Block, overlay: Identifier) {
    val wallOf = Identifier.of(baseBlock.model().namespace, baseBlock.model().path.removeSuffix("s"))
    this.blockStateCollector.accept(
        createWallBlockStateWithOverlay(
            wall,
            wallOf.suffix("_wall_post"),
            overlay.suffix("_wall_post"),
            wallOf.suffix("_wall_side"),
            overlay.suffix("_wall_side"),
            wallOf.suffix("_wall_side_tall"),
            overlay.suffix("_wall_side_tall")
        )
    )
    val texture = Texture.texture(wall)
        .put(TextureKey.WALL, Texture.getId(baseBlock))
        .put(TextureKey.DIRT, overlay)
    this.registerParentedItemModel(
        wall, block(
            "parent/wall_inventory_with_tinted_overlay",
            "_inventory",
            TextureKey.WALL,
            TextureKey.DIRT
        ).upload(wall, texture, this.modelCollector)
    )
}

fun createWallBlockStateWithOverlay(
    wallBlock: Block,
    postModelId: Identifier,
    postOverlayModelId: Identifier,
    lowSideModelId: Identifier,
    lowSideOverlayModelId: Identifier,
    tallSideModelId: Identifier,
    tallSideOverlayModelId: Identifier
): BlockStateSupplier {
    val directions = listOf(
        (Properties.NORTH_WALL_SHAPE to Rotation.R0),
        (Properties.EAST_WALL_SHAPE to Rotation.R90),
        (Properties.SOUTH_WALL_SHAPE to Rotation.R180),
        (Properties.WEST_WALL_SHAPE to Rotation.R270)
    )
    val model = MultipartBlockStateSupplier.create(wallBlock)
    model.with(
        When.create().set(Properties.UP, true),
        BlockStateVariant.create().put(VariantSettings.MODEL, postModelId)
    ).with(
        When.create().set(Properties.UP, true),
        BlockStateVariant.create().put(VariantSettings.MODEL, postOverlayModelId)
    )
    directions.forEach { (shape, rotation) ->
        model.with(
            When.create().set(shape, WallShape.LOW),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, lowSideModelId)
                .put(VariantSettings.UVLOCK, true)
                .put(VariantSettings.Y, rotation)
        ).with(
            When.create().set(shape, WallShape.LOW),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, lowSideOverlayModelId)
                .put(VariantSettings.UVLOCK, true)
                .put(VariantSettings.Y, rotation)
        )
        model.with(
            When.create().set(shape, WallShape.TALL),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, tallSideModelId)
                .put(VariantSettings.UVLOCK, true)
                .put(VariantSettings.Y, rotation)
        ).with(
            When.create().set(shape, WallShape.TALL),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, tallSideOverlayModelId)
                .put(VariantSettings.UVLOCK, true)
                .put(VariantSettings.Y, rotation)
        )
    }
    return model
}

fun BlockStateModelGenerator.createLeafPile(leafPile: Block, leaves: Block) {
    val layer1 = this.parentedModel(leafPile, leaves, leafPile())
    val layer2 = this.parentedModel(leafPile.model("_8"), leaves, leafPile("8"))
    val layer3 = this.parentedModel(leafPile.model("_12"), leaves, leafPile("12"))
    val hanging1 = this.parentedModel(leafPile.model("_hanging"), leaves, leafPileHanging())
    val hanging2 = this.parentedModel(leafPile.model("_hanging_8"), leaves, leafPileHanging("8"))
    val hanging3 = this.parentedModel(leafPile.model("_hanging_12"), leaves, leafPileHanging("12"))
    val full = this.parentedModel(leafPile.model("_full"), leaves, leafPile("full"))
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

fun BlockStateModelGenerator.registerFloorPlant(
    crop: Block,
    ageProperty: Property<Int>,
    vararg ageTextureIndices: Int
) {
    require(ageProperty.values.size == ageTextureIndices.size)
    val int2ObjectMap: Int2ObjectMap<Identifier> = Int2ObjectOpenHashMap()
    val blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register { age: Int ->
        val i = ageTextureIndices[age]
        BlockStateVariant.create().put(
            VariantSettings.MODEL, int2ObjectMap.computeIfAbsent(i,
                Int2ObjectFunction { _: Int ->
                    this.createSubModel(
                        crop,
                        "_stage$i",
                        Models.CARPET
                    ) { id: Identifier ->
                        Texture.wool(
                            id
                        )
                    }
                })
        )
    }
    this.registerItemModel(crop.asItem())
    this.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop).coordinate(blockStateVariantMap))
}

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
fun Block.model(): Identifier = ModelIds.getBlockModelId(this)
fun Block.model(str: String) = this.model().suffix(str)

fun Identifier.suffix(str: String) = Identifier(this.namespace, "${this.path}$str")
