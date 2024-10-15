package org.teamvoided.dusk_autumn.util

import it.unimi.dsi.fastutil.ints.Int2ObjectFunction
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.block.*
import net.minecraft.block.enums.*
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.BlockStateModelGenerator.TintType
import net.minecraft.data.client.model.BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations
import net.minecraft.data.client.model.VariantSettings.Rotation
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.state.property.Properties
import net.minecraft.state.property.Property
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import org.teamvoided.dusk_autumn.DusksAndDungeons.id
import org.teamvoided.dusk_autumn.DusksAndDungeons.mc
import org.teamvoided.dusk_autumn.block.*
import org.teamvoided.dusk_autumn.block.not_blocks.TripleBlockSection
import org.teamvoided.dusk_autumn.init.blocks.DnDNetherBrickBlocks
import java.util.*

val ALL_KRY: TextureKey = TextureKey.of("all")
val INNER: TextureKey = TextureKey.of("inner")
val SMALL: TextureKey = TextureKey.of("small")

val BAR: TextureKey = TextureKey.of("bar")
val POST: TextureKey = TextureKey.of("post")

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
    block("parent/wall_side_tall_with_tint", "_side", TextureKey.WALL)
        .upload(overlay.suffix("_wall_side_tall"), texture, this.modelCollector)
}

fun BlockStateModelGenerator.registerTintedOverlay(overlay: Identifier) {
    this.tintedCubeOverlay(overlay)
    this.tintedStairAllOverlay(overlay)
    this.tintedSlabAllOverlay(overlay)
    this.tintedWallOverlay(overlay)
}

fun BlockStateModelGenerator.cubeAllWithTintedOverlay(block: Block, reference: Block, overlay: Identifier) {
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

fun BlockStateModelGenerator.wallWithTintedOverlay(wall: Block, baseBlock: Block, overlay: Identifier) {
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

fun getRandomYRotations(model: Identifier): Array<BlockStateVariant> {
    var array = arrayOf<BlockStateVariant>()
    VariantSettings.Rotation.entries.forEach {
        val variant = BlockStateVariant.create().put(VariantSettings.MODEL, model)
        if (it != Rotation.R0) variant.put(VariantSettings.Y, it)
        array += variant
    }
    return array
}

fun getRandomYXRotations(model: Identifier): Array<BlockStateVariant> {
    var array = arrayOf<BlockStateVariant>()
    VariantSettings.Rotation.entries.forEach { itY ->
        VariantSettings.Rotation.entries.forEach { itX ->
            val variant = BlockStateVariant.create().put(VariantSettings.MODEL, model)
            if (itY != Rotation.R0) variant.put(VariantSettings.Y, itY)
            if (itX != Rotation.R0) variant.put(VariantSettings.X, itX)
            array += variant
        }
    }
    return array
}

fun BlockStateModelGenerator.createVerdureGrowth(block: Block, top: Identifier, bottom: Identifier) {
    val texture = Texture.texture(block)
        .put(TextureKey.PARTICLE, Texture.getId(block))
        .put(TextureKey.TOP, top)
        .put(TextureKey.SIDE, Texture.getId(block))
        .put(TextureKey.BOTTOM, bottom)
    val model = Models.CUBE_BOTTOM_TOP.upload(block, texture, this.modelCollector)
    blockStateCollector.accept(
        BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(block, model)
    )
}

fun BlockStateModelGenerator.registerGalleryRose(block: Block, tintType: TintType) {
    this.registerItemModel(block, "_top")
    val top = this.createSubModel(block, "_top", tintType.crossModel, Texture::cross)
    val middle = this.createSubModel(block, "_middle", tintType.crossModel, Texture::cross)
    val bottom = this.createSubModel(block, "_bottom", tintType.crossModel, Texture::cross)
    blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block).coordinate(
            BlockStateVariantMap.create(
                PaintedRoseBlock.SECTION
            ).register(
                TripleBlockSection.TOP,
                BlockStateVariant.create().put(VariantSettings.MODEL, top)
            ).register(
                TripleBlockSection.MIDDLE,
                BlockStateVariant.create().put(VariantSettings.MODEL, middle)
            ).register(
                TripleBlockSection.BOTTOM,
                BlockStateVariant.create().put(VariantSettings.MODEL, bottom)
            )
        )
    )
}

fun BlockStateModelGenerator.registerGoldenMushroomPlant(block: Block) {
    this.registerItemModel(block, "_1")
    var array = arrayOf<BlockStateVariant>()
    var loop = 1
    repeat(3) {
        val texture = Texture.texture(block).put(TextureKey.CROSS, Texture.getSubId(block, "_$loop"))
        val model = Models.CROSS.upload(block, "_$loop", texture, this.modelCollector)
        array += BlockStateVariant.create().put(VariantSettings.MODEL, model)
        loop += 1
    }
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(
            block, *array
        )
    )
}

fun BlockStateModelGenerator.registerTreeMushroom(block: Block, parentModel: String) {
    this.registerItemModel(block)
    val texture = Texture.texture(block)
        .put(TextureKey.TOP, Texture.getSubId(block, "_top"))
        .put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom"))
        .put(SMALL, Texture.getSubId(block, "_small"))
    val model = block(
        parentModel,
        TextureKey.TOP,
        TextureKey.BOTTOM,
        SMALL
    )
    this.registerSingleton(block, texture, model)
}

fun BlockStateModelGenerator.registerSpiderlilly(doubleBlock: Block, tintType: TintType) {
    this.registerItemModel(doubleBlock, "_top")
    val top: Identifier = this.createSubModel(doubleBlock, "_top", tintType.crossModel, Texture::cross)
    val bottom: Identifier = this.createSubModel(doubleBlock, "_bottom", tintType.crossModel, Texture::cross)
    val topFalse: Identifier = this.createSubModel(doubleBlock, "_top_false", tintType.crossModel, Texture::cross)
    val bottomFalse: Identifier = this.createSubModel(doubleBlock, "_bottom_false", tintType.crossModel, Texture::cross)
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(doubleBlock).coordinate(
            BlockStateVariantMap.create(Properties.DOUBLE_BLOCK_HALF, SpiderlilyBlock.FLOWERING)
                .register(
                    DoubleBlockHalf.LOWER, true,
                    BlockStateVariant.create().put(VariantSettings.MODEL, bottom)
                )
                .register(
                    DoubleBlockHalf.UPPER, true,
                    BlockStateVariant.create().put(VariantSettings.MODEL, top)
                )
                .register(
                    DoubleBlockHalf.LOWER, false,
                    BlockStateVariant.create().put(VariantSettings.MODEL, bottomFalse)
                )
                .register(
                    DoubleBlockHalf.UPPER, false,
                    BlockStateVariant.create().put(VariantSettings.MODEL, topFalse)
                )
        )
    )
}

fun BlockStateModelGenerator.registerFlowerbed2(
    block: Block,
    useDefault: Boolean = true,
    parent: Identifier = mc("block/flowerbed")
) {
    this.registerItemModel(block.asItem())
    val texture = Texture()
        .put(TextureKey.FLOWERBED, Texture.getId(block))
        .put(TextureKey.STEM, if (useDefault) Texture.getSubId(block, "_stem") else id("block/petals_stem"))
    val identifier = block(parent.suffix("_1"), "_1", TextureKey.FLOWERBED, TextureKey.STEM)
        .upload(block, texture, this.modelCollector)
    val identifier2 = block(parent.suffix("_2"), "_2", TextureKey.FLOWERBED, TextureKey.STEM)
        .upload(block, texture, this.modelCollector)
    val identifier3 = block(parent.suffix("_3"), "_3", TextureKey.FLOWERBED, TextureKey.STEM)
        .upload(block, texture, this.modelCollector)
    val identifier4 = block(parent.suffix("_4"), "_4", TextureKey.FLOWERBED, TextureKey.STEM)
        .upload(block, texture, this.modelCollector)
    val flowerbed = MultipartBlockStateSupplier.create(block)
    val directionAndRotation = listOf(
        (Direction.NORTH to Rotation.R0),
        (Direction.EAST to Rotation.R90),
        (Direction.SOUTH to Rotation.R180),
        (Direction.WEST to Rotation.R270)
    )
    directionAndRotation.forEach { (direction, rotation) ->
        flowerbed.with(
            When.create()
                .set(Properties.FLOWER_AMOUNT, 1, 2, 3, 4)
                .set(Properties.HORIZONTAL_FACING, direction),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, identifier)
                .put(VariantSettings.Y, rotation)
        ).with(
            When.create()
                .set(Properties.FLOWER_AMOUNT, 2, 3, 4)
                .set(Properties.HORIZONTAL_FACING, direction),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, identifier2)
                .put(VariantSettings.Y, rotation)
        ).with(
            When.create()
                .set(Properties.FLOWER_AMOUNT, 3, 4)
                .set(Properties.HORIZONTAL_FACING, direction),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, identifier3)
                .put(VariantSettings.Y, rotation)
        ).with(
            When.create()
                .set(Properties.FLOWER_AMOUNT, 4)
                .set(Properties.HORIZONTAL_FACING, direction),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, identifier4)
                .put(VariantSettings.Y, rotation)
        )
    }
    this.blockStateCollector.accept(flowerbed)
}

fun BlockStateModelGenerator.registerTallCrystal(block: Block) {
    this.registerItemModel(block, "_top")
    val model = Models.CROSS
    val lowerHalfModelId: Identifier = this.createSubModel(block, "_top", model, Texture::cross)
    val upperHalfModelId: Identifier = this.createSubModel(block, "_bottom", model, Texture::cross)
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block).coordinate(
            BlockStateVariantMap.create(Properties.DOUBLE_BLOCK_HALF)
                .register(
                    DoubleBlockHalf.LOWER,
                    BlockStateVariant.create().put(VariantSettings.MODEL, lowerHalfModelId)
                )
                .register(
                    DoubleBlockHalf.UPPER,
                    BlockStateVariant.create().put(VariantSettings.MODEL, upperHalfModelId)
                )
        ).coordinate(this.createUpDefaultFacingVariantMap())
    )
}

fun BlockStateModelGenerator.registerWaterFern(block: Block) {
//    this.registerItemModel(block.asItem())
    val texture = Texture()
        .put(TextureKey.PARTICLE, Texture.getId(block))
        .put(TextureKey.TOP, Texture.getSubId(block, "_roots"))
        .put(TextureKey.PLANT, Texture.getSubId(block, "_roots"))
    val model = block(
        "parent/water_plant",
        TextureKey.PARTICLE,
        TextureKey.TOP,
        TextureKey.PLANT
    ).upload(block, texture, this.modelCollector)
    this.registerAxisRotated(block, model)
}

fun BlockStateModelGenerator.registerPumpkins(pumpkin: Block, carved: Block, glowing: Block) {
    val texture = Texture()
        .put(TextureKey.END, Texture.getSubId(pumpkin, "_top"))
        .put(TextureKey.SIDE, Texture.getSubId(pumpkin, "_side"))
    val model = Models.CUBE_COLUMN.upload(pumpkin, texture, this.modelCollector)
    Models.ORIENTABLE.upload(carved, texture.put(TextureKey.FRONT, Texture.getId(carved)), this.modelCollector)
    Models.ORIENTABLE.upload(glowing, texture.put(TextureKey.FRONT, Texture.getId(glowing)), this.modelCollector)

    blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(pumpkin, model))
    this.registerNorthDefaultHorizontalRotation(carved)
    this.registerNorthDefaultHorizontalRotation(glowing)
}

fun BlockStateModelGenerator.registerGravestone(gravestone: Block) {
    val texture = Texture()
        .put(TextureKey.FRONT, Texture.getSubId(gravestone, "_front"))
        .put(TextureKey.SIDE, Texture.getSubId(gravestone, "_side"))
    block(
        "parent/gravestone",
        TextureKey.FRONT,
        TextureKey.SIDE
    ).upload(gravestone, texture, this.modelCollector)
    block(
        "parent/gravestone_centered",
        TextureKey.FRONT,
        TextureKey.SIDE
    ).upload(gravestone, "_centered", texture, this.modelCollector)
    val variants = BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, GravestoneBlock.CENTERED)
    GravestoneBlock.CENTERED.values.forEach {
        Properties.HORIZONTAL_FACING.values.forEach { direction ->
            val string = if (it) "_centered" else ""
            val variant = BlockStateVariant.create()
                .put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(gravestone, string))
            val variant2 = when (direction) {
                Direction.NORTH -> variant.put(VariantSettings.Y, Rotation.R180)
                Direction.EAST -> variant.put(VariantSettings.Y, Rotation.R270)
                Direction.WEST -> variant.put(VariantSettings.Y, Rotation.R90)
                else -> variant
            }
            variants.register(
                direction,
                it,
                variant2
            )
        }
    }
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(gravestone).coordinate(
            variants
        )
    )
}

fun BlockStateModelGenerator.registerSmallPumpkins(pumpkin: Block, carved: Block, glowing: Block, particle: Block) {
    val texture = Texture()
        .put(TextureKey.PARTICLE, Texture.getSubId(particle, "_side"))
        .put(TextureKey.ALL, Texture.getId(pumpkin))
    val model = block(
        "parent/small_pumpkin",
        TextureKey.PARTICLE,
        TextureKey.ALL
    ).upload(pumpkin, texture, this.modelCollector)
    block(
        "parent/small_carved_pumpkin",
        TextureKey.PARTICLE,
        TextureKey.ALL
    ).upload(carved, texture, this.modelCollector)
    block(
        "parent/small_glowing_pumpkin",
        TextureKey.PARTICLE,
        TextureKey.ALL
    ).upload(glowing, texture, this.modelCollector)

    blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(pumpkin, model))
    this.registerNorthDefaultHorizontalRotation(carved)
    this.registerNorthDefaultHorizontalRotation(glowing)
}

fun BlockStateModelGenerator.registerBigChain(block: Block) {
    this.registerItemModel(block.asItem())
    val texture = Texture()
        .put(TextureKey.PARTICLE, Texture.getId(block))
        .put(TextureKey.ALL, Texture.getId(block))
    val model = block(
        "parent/big_chain",
        TextureKey.PARTICLE,
        TextureKey.ALL
    ).upload(block, texture, this.modelCollector)
    this.registerAxisRotated(block, model)
}

fun BlockStateModelGenerator.registerBigLantern(
    block: Block,
    redstone: Boolean = false,
    bottom: String = "block/big_lantern_bottom"
) {
    this.registerItemModel(block)
    val texture = Texture()
        .put(TextureKey.PARTICLE, Texture.getId(block))
        .put(TextureKey.SIDE, Texture.getId(block))
        .put(TextureKey.END, id(bottom))
    val model = block(
        "parent/big_lantern",
        TextureKey.PARTICLE,
        TextureKey.SIDE,
        TextureKey.END
    )
    val modelHanging = model.upload(block, "_hanging", texture, this.modelCollector)
    if (redstone) {
        val textureOff = Texture()
            .put(TextureKey.PARTICLE, Texture.getSubId(block, "_off"))
            .put(TextureKey.SIDE, Texture.getSubId(block, "_off"))
            .put(TextureKey.END, id(bottom))
        val modelOff = model.upload(block, "_off", textureOff, this.modelCollector)
        val modelHangingOff = model.upload(block, "_hanging_off", textureOff, this.modelCollector)
        this.blockStateCollector.accept(
            VariantsBlockStateSupplier.create(block).coordinate(
                BlockStateVariantMap.create(Properties.HANGING, Properties.LIT)
                    .register(
                        false, true, BlockStateVariant.create()
                            .put(VariantSettings.MODEL, model.upload(block, texture, this.modelCollector))
                    )
                    .register(
                        false, false, BlockStateVariant.create()
                            .put(VariantSettings.MODEL, modelOff)
                    )
                    .register(
                        true, true, BlockStateVariant.create()
                            .put(VariantSettings.X, Rotation.R180)
                            .put(VariantSettings.MODEL, modelHanging)
                    )
                    .register(
                        true, false, BlockStateVariant.create()
                            .put(VariantSettings.X, Rotation.R180)
                            .put(VariantSettings.MODEL, modelHangingOff)
                    )
            )
        )
    } else {
        this.blockStateCollector.accept(
            VariantsBlockStateSupplier.create(block).coordinate(
                BlockStateVariantMap.create(Properties.HANGING)
                    .register(
                        false, BlockStateVariant.create()
                            .put(VariantSettings.MODEL, model.upload(block, texture, this.modelCollector))
                    )
                    .register(
                        true, BlockStateVariant.create()
                            .put(VariantSettings.X, Rotation.R180)
                            .put(VariantSettings.MODEL, modelHanging)
                    )
            )
        )
    }
}

fun BlockStateModelGenerator.registerBigCandle(candle: Block, cake: Block?) {
    this.registerPrefixedItemModel(candle, "candle/")
    val texture = Texture.all(candle.prefixed("candle/"))
    val textureLit = Texture.all(candle.prefixed("candle/").suffix("_lit"))
    val oneCandle = bigCandleModel("1").upload(candle, "_one_candle", texture, this.modelCollector)
    val twoCandle = bigCandleModel("2").upload(candle, "_two_candles", texture, this.modelCollector)
    val threeCandle = bigCandleModel("3").upload(candle, "_three_candles", texture, this.modelCollector)
    val fourCandle = bigCandleModel("4").upload(candle, "_four_candles", texture, this.modelCollector)
    val oneCandleLit = bigCandleModel("1").upload(candle, "_one_candle_lit", textureLit, this.modelCollector)
    val twoCandleLit = bigCandleModel("2").upload(candle, "_two_candles_lit", textureLit, this.modelCollector)
    val threeCandleLit = bigCandleModel("3").upload(candle, "_three_candles_lit", textureLit, this.modelCollector)
    val fourCandleLit = bigCandleModel("4").upload(candle, "_four_candles_lit", textureLit, this.modelCollector)
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(candle)
            .coordinate(
                BlockStateVariantMap.create(Properties.CANDLES, Properties.LIT)
                    .register(1, false, BlockStateVariant.create().put(VariantSettings.MODEL, oneCandle))
                    .register(2, false, BlockStateVariant.create().put(VariantSettings.MODEL, twoCandle))
                    .register(3, false, BlockStateVariant.create().put(VariantSettings.MODEL, threeCandle))
                    .register(4, false, BlockStateVariant.create().put(VariantSettings.MODEL, fourCandle))
                    .register(1, true, BlockStateVariant.create().put(VariantSettings.MODEL, oneCandleLit))
                    .register(2, true, BlockStateVariant.create().put(VariantSettings.MODEL, twoCandleLit))
                    .register(3, true, BlockStateVariant.create().put(VariantSettings.MODEL, threeCandleLit))
                    .register(4, true, BlockStateVariant.create().put(VariantSettings.MODEL, fourCandleLit))
            )//.coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
    )
    if (cake != null) {
        val candleCake = bigCandleCakeModel().upload(
            cake, candleCake(candle, false),
            this.modelCollector
        )
        val candleCakeLit = bigCandleCakeModel().upload(
            cake, "_lit", candleCake(candle, true),
            this.modelCollector
        )
        this.blockStateCollector.accept(
            VariantsBlockStateSupplier.create(cake).coordinate(
                BlockStateModelGenerator.createBooleanModelMap(
                    Properties.LIT, candleCakeLit, candleCake
                )
            )
        )
    }
}

fun BlockStateModelGenerator.registerCandle2(candle: Block, cake: Block?) {
    this.registerPrefixedItemModel(candle, "candle/")
    val texture = Texture.all(candle.prefixed("candle/"))
    val textureLit = Texture.all(candle.prefixed("candle/").suffix("_lit"))
    val oneCandle = Models.TEMPLATE_CANDLE.upload(candle, "_one_candle", texture, this.modelCollector)
    val twoCandle = Models.TEMPLATE_TWO_CANDLES.upload(candle, "_two_candles", texture, this.modelCollector)
    val threeCandle = Models.TEMPLATE_THREE_CANDLES.upload(candle, "_three_candles", texture, this.modelCollector)
    val fourCandle = Models.TEMPLATE_FOUR_CANDLES.upload(candle, "_four_candles", texture, this.modelCollector)
    val oneCandleLit = Models.TEMPLATE_CANDLE.upload(candle, "_one_candle_lit", textureLit, this.modelCollector)
    val twoCandleLit = Models.TEMPLATE_TWO_CANDLES.upload(candle, "_two_candles_lit", textureLit, this.modelCollector)
    val threeCandleLit =
        Models.TEMPLATE_THREE_CANDLES.upload(candle, "_three_candles_lit", textureLit, this.modelCollector)
    val fourCandleLit =
        Models.TEMPLATE_FOUR_CANDLES.upload(candle, "_four_candles_lit", textureLit, this.modelCollector)
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(candle).coordinate(
            BlockStateVariantMap.create(
                Properties.CANDLES, Properties.LIT
            )
                .register(1, false, BlockStateVariant.create().put(VariantSettings.MODEL, oneCandle))
                .register(2, false, BlockStateVariant.create().put(VariantSettings.MODEL, twoCandle))
                .register(3, false, BlockStateVariant.create().put(VariantSettings.MODEL, threeCandle))
                .register(4, false, BlockStateVariant.create().put(VariantSettings.MODEL, fourCandle))
                .register(1, true, BlockStateVariant.create().put(VariantSettings.MODEL, oneCandleLit))
                .register(2, true, BlockStateVariant.create().put(VariantSettings.MODEL, twoCandleLit))
                .register(3, true, BlockStateVariant.create().put(VariantSettings.MODEL, threeCandleLit))
                .register(4, true, BlockStateVariant.create().put(VariantSettings.MODEL, fourCandleLit))
        )
    )
    if (cake != null) {
        val candleCake = Models.TEMPLATE_CAKE_WITH_CANDLE.upload(
            cake, candleCake(candle, false),
            this.modelCollector
        )
        val candleCakeLit = Models.TEMPLATE_CAKE_WITH_CANDLE.upload(
            cake, "_lit", candleCake(candle, true),
            this.modelCollector
        )
        this.blockStateCollector.accept(
            VariantsBlockStateSupplier.create(cake).coordinate(
                BlockStateModelGenerator.createBooleanModelMap(
                    Properties.LIT, candleCakeLit, candleCake
                )
            )
        )
    }
}

fun candleCake(block: Block, lit: Boolean): Texture {
    return Texture()
        .put(TextureKey.PARTICLE, Texture.getSubId(Blocks.CAKE, "_side"))
        .put(TextureKey.BOTTOM, Texture.getSubId(Blocks.CAKE, "_bottom"))
        .put(TextureKey.TOP, Texture.getSubId(Blocks.CAKE, "_top"))
        .put(TextureKey.SIDE, Texture.getSubId(Blocks.CAKE, "_side"))
        .put(TextureKey.CANDLE, block.prefixed("candle/").suffix(if (lit) "_lit" else ""))
}

fun BlockStateModelGenerator.registerBigTallCandle(candle: Block) {
    this.registerPrefixedItemModel(candle, "candle/")
    val texture = Texture.all(candle.prefixed("candle/"))
    val textureLit = Texture.all(candle.prefixed("candle/").suffix("_lit"))
    val oneCandle = bigTallCandleModel("1").upload(candle, "_one_candle", texture, this.modelCollector)
    val twoCandle = bigTallCandleModel("2").upload(candle, "_two_candles", texture, this.modelCollector)
    val threeCandle = bigTallCandleModel("3").upload(candle, "_three_candles", texture, this.modelCollector)
    val fourCandle = bigTallCandleModel("4").upload(candle, "_four_candles", texture, this.modelCollector)
    val oneCandleLit = bigTallCandleModel("1").upload(candle, "_one_candle_lit", textureLit, this.modelCollector)
    val twoCandleLit = bigTallCandleModel("2").upload(candle, "_two_candles_lit", textureLit, this.modelCollector)
    val threeCandleLit = bigTallCandleModel("3").upload(candle, "_three_candles_lit", textureLit, this.modelCollector)
    val fourCandleLit = bigTallCandleModel("4").upload(candle, "_four_candles_lit", textureLit, this.modelCollector)
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(candle)
            .coordinate(
                BlockStateVariantMap.create(Properties.CANDLES, Properties.LIT)
                    .register(1, false, BlockStateVariant.create().put(VariantSettings.MODEL, oneCandle))
                    .register(2, false, BlockStateVariant.create().put(VariantSettings.MODEL, twoCandle))
                    .register(3, false, BlockStateVariant.create().put(VariantSettings.MODEL, threeCandle))
                    .register(4, false, BlockStateVariant.create().put(VariantSettings.MODEL, fourCandle))
                    .register(1, true, BlockStateVariant.create().put(VariantSettings.MODEL, oneCandleLit))
                    .register(2, true, BlockStateVariant.create().put(VariantSettings.MODEL, twoCandleLit))
                    .register(3, true, BlockStateVariant.create().put(VariantSettings.MODEL, threeCandleLit))
                    .register(4, true, BlockStateVariant.create().put(VariantSettings.MODEL, fourCandleLit))
            )
    )
}

fun bigCandleModel(suffix: String): Model {
    val variant = if (suffix == "1") "" else "_$suffix"
    return block("parent/big_candle$variant", TextureKey.ALL, TextureKey.PARTICLE)
}

fun bigCandleCakeModel(): Model {
    return block("parent/cake_with_big_candle", TextureKey.CANDLE, TextureKey.PARTICLE)
}

fun bigTallCandleModel(suffix: String): Model {
    val variant = if (suffix == "1") "" else "_$suffix"
    return block("parent/big_tall_candle$variant", TextureKey.ALL, TextureKey.PARTICLE)
}

fun BlockStateModelGenerator.registerBell(
    block: Block,
    bar: Identifier = Texture.getId(Blocks.DARK_OAK_PLANKS),
    post: Identifier = Texture.getId(Blocks.STONE)
) {
    this.registerItemModel(block.asItem())
    val variants = BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.ATTACHMENT)
    val texture1 = Texture()
        .put(TextureKey.PARTICLE, Texture.getId(block))
        .put(BAR, bar)
    val texture2 = texture1.put(POST, post)

    Properties.ATTACHMENT.values.forEach { attachment ->
        val attach = attachment.toString().lowercase()
        if (attachment == Attachment.FLOOR) {
            block("parent/bell_$attach", TextureKey.PARTICLE, BAR, POST)
                .upload(block, "_$attach", texture2, this.modelCollector)
        } else {
            block("parent/bell_$attach", TextureKey.PARTICLE, BAR)
                .upload(block, "_$attach", texture1, this.modelCollector)
        }
        Properties.HORIZONTAL_FACING.values.forEach { direction ->
            val variant = BlockStateVariant.create()
                .put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(block, "_$attach"))
            val variant2 = when (direction) {
                Direction.EAST -> variant.put(VariantSettings.Y, Rotation.R90)
                Direction.SOUTH -> variant.put(VariantSettings.Y, Rotation.R180)
                Direction.WEST -> variant.put(VariantSettings.Y, Rotation.R270)
                else -> variant
            }
            variants.register(
                direction, attachment,
                variant2
            )
        }
    }
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block).coordinate(
            variants
        )
    )
}

fun BlockStateModelGenerator.registerMixedNetherBrickPillar(block: Block, mix: Block) {
    val texture1 = Texture()
        .put(TextureKey.SIDE, Texture.getId(block))
        .put(TextureKey.TOP, Texture.getSubId(DnDNetherBrickBlocks.NETHER_BRICK_PILLAR, "_top"))
        .put(TextureKey.BOTTOM, Texture.getSubId(mix, "_top"))
    val texture2 = Texture()
        .put(TextureKey.SIDE, Texture.getSubId(block, "_inverse"))
        .put(TextureKey.TOP, Texture.getSubId(mix, "_top"))
        .put(TextureKey.BOTTOM, Texture.getSubId(DnDNetherBrickBlocks.NETHER_BRICK_PILLAR, "_top"))
    val model1 = Models.CUBE_BOTTOM_TOP.upload(block, texture1, this.modelCollector)
    val model2 = Models.CUBE_BOTTOM_TOP.upload(block, "_inverse", texture2, this.modelCollector)
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(block).coordinate(
            BlockStateVariantMap.create(Properties.FACING)
                .register(
                    Direction.UP, BlockStateVariant.create()
                        .put(VariantSettings.MODEL, model1)
                )
                .register(
                    Direction.DOWN, BlockStateVariant.create()
                        .put(VariantSettings.MODEL, model2)
                )
                .register(
                    Direction.NORTH,
                    BlockStateVariant.create()
                        .put(VariantSettings.X, Rotation.R90)
                        .put(VariantSettings.MODEL, model1)
                )
                .register(
                    Direction.SOUTH, BlockStateVariant.create()
                        .put(VariantSettings.X, Rotation.R90)
                        .put(VariantSettings.MODEL, model2)
                )
                .register(
                    Direction.EAST,
                    BlockStateVariant.create()
                        .put(VariantSettings.X, Rotation.R90)
                        .put(VariantSettings.Y, Rotation.R90)
                        .put(VariantSettings.MODEL, model1)
                )
                .register(
                    Direction.WEST,
                    BlockStateVariant.create()
                        .put(VariantSettings.X, Rotation.R90)
                        .put(VariantSettings.Y, Rotation.R90)
                        .put(VariantSettings.MODEL, model2)
                )
        )
    )
}

fun BlockStateModelGenerator.iceStairs(
    block: Block,
    parent: Block
) {
    val texture: Texture = Texture.texture(parent)
        .put(TextureKey.BOTTOM, parent.model())
        .put(TextureKey.SIDE, parent.model())
        .put(TextureKey.TOP, parent.model())
    val ner: Identifier = block("parent/translucent_stairs", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
        .upload(block, texture, this.modelCollector)
    val inner: Identifier =
        block("parent/translucent_inner_stairs", "_inner", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
            .upload(block, texture, this.modelCollector)
    val outer: Identifier =
        block("parent/translucent_outer_stairs", "_outer", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE)
            .upload(block, texture, this.modelCollector)

    this.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(block, inner, ner, outer))
    this.registerParentedItemModel(block, ner)
}

//shamelessley stolen from voidUtils :)
fun BlockStateModelGenerator.stairs(block: Block) =
    stairs(block, block, block, block, block)

fun BlockStateModelGenerator.stairs(block: Block, texture: Block) =
    stairs(block, texture, texture, texture, texture)

fun BlockStateModelGenerator.stairs(block: Block, parent: Block, bottom: Block, side: Block, top: Block) =
    stairs(block, parent, bottom.model(), side.model(), top.model())

fun BlockStateModelGenerator.stairs(block: Block, ends: Identifier, side: Identifier) =
    stairs(block, block, ends, side, ends)

fun BlockStateModelGenerator.stairs(
    block: Block,
    parent: Block,
    bottom: Identifier,
    side: Identifier,
    top: Identifier,
) {
    val texture: Texture = Texture.texture(parent)
        .put(TextureKey.BOTTOM, bottom)
        .put(TextureKey.SIDE, side)
        .put(TextureKey.TOP, top)
    val id: Identifier = Models.INNER_STAIRS.upload(block, texture, this.modelCollector)
    val id2: Identifier = Models.STAIRS.upload(block, texture, this.modelCollector)
    val id3: Identifier = Models.OUTER_STAIRS.upload(block, texture, this.modelCollector)

    this.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(block, id, id2, id3))
    this.registerParentedItemModel(block, id2)
}

fun BlockStateModelGenerator.slab(block: Block) = slab(block, block)
fun BlockStateModelGenerator.slab(block: Block, texture: Block) =
    slab(block, texture, texture, texture, texture)

fun BlockStateModelGenerator.slab(block: Block, texture: Block, full: Block) =
    slab(block, slabTexture(texture), full)

fun BlockStateModelGenerator.slab(block: Block, bottom: Block, side: Block, top: Block, full: Block) =
    slab(
        block, Texture.texture(block.model())
            .put(TextureKey.BOTTOM, bottom.model())
            .put(TextureKey.SIDE, side.model())
            .put(TextureKey.TOP, top.model()),
        full
    )

fun BlockStateModelGenerator.slab(block: Block, texture: Texture, full: Block) {
    val id = Models.SLAB.upload(block, texture, this.modelCollector)
    val id2 = Models.SLAB_TOP.upload(block, texture, this.modelCollector)
    val id3 = full.model()
    this.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(block, id, id2, id3))
    this.registerParentedItemModel(block, id)
}

fun slabTexture(texture: Block): Texture = Texture.texture(texture.model())
    .put(TextureKey.BOTTOM, texture.model())
    .put(TextureKey.SIDE, texture.model())
    .put(TextureKey.TOP, texture.model())

fun BlockStateModelGenerator.wall(block: Block) = wall(block, block.model())
fun BlockStateModelGenerator.wall(block: Block, texture: Block) = wall(block, texture.model())

fun BlockStateModelGenerator.wall(wallBlock: Block, inId: Identifier) {
    val texture = Texture.texture(wallBlock.model()).put(TextureKey.WALL, inId)
    val id = Models.TEMPLATE_WALL_POST.upload(wallBlock, texture, this.modelCollector)
    val id2 = Models.TEMPLATE_WALL_SIDE.upload(wallBlock, texture, this.modelCollector)
    val id3 = Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, texture, this.modelCollector)
    this.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, id, id2, id3))
    this.registerParentedItemModel(wallBlock, Models.WALL_INVENTORY.upload(wallBlock, texture, this.modelCollector))
}

fun BlockStateModelGenerator.genPsudoFamily(stairs: Block, slab: Block, wall: Block, texture: Block) {
    this.stairs(stairs, texture)
    this.slab(slab, texture)
    this.wall(wall, texture)
}

fun BlockStateModelGenerator.genPsudoFamily(stairs: Block, slab: Block, wall: Block, texture: Block, fullSlab: Block) {
    this.stairs(stairs, texture)
    this.slab(slab, texture, fullSlab)
    this.wall(wall, texture)
}

fun BlockStateModelGenerator.fence(fenceBlock: Block, reference: Block) {
    val texture = Texture.texture(reference)
    val id = Models.FENCE_POST.upload(fenceBlock, texture, this.modelCollector)
    val id2 = Models.FENCE_SIDE.upload(fenceBlock, texture, this.modelCollector)
    val id3 = Models.FENCE_INVENTORY.upload(fenceBlock, texture, this.modelCollector)
    this.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, id, id2))
    this.registerParentedItemModel(fenceBlock, id3)
}

fun BlockStateModelGenerator.registerHandheldItem(item: Item) {
    Models.HANDHELD.upload(
        ModelIds.getItemModelId(item),
        Texture.layer0(item),
        this.modelCollector
    )
}

fun BlockStateModelGenerator.hollowLog(hollowLog: Block, log: Block) {
    this.hollowLog(hollowLog, log, log.model())
}

fun BlockStateModelGenerator.hollowLog(hollowLog: Block, log: Block, strippedLog: Block) {
    this.hollowLog(hollowLog, log, strippedLog.model())
}

fun BlockStateModelGenerator.hollowLog(hollowLog: Block, log: Block, innerTexture: Identifier) {
    val texture: Texture = Texture.texture(hollowLog)
        .put(TextureKey.SIDE, log.model())
        .put(TextureKey.END, log.model("_top"))
        .put(INNER, innerTexture)
    Direction.Type.HORIZONTAL.forEach {
        block("parent/hollow_log_$it", TextureKey.SIDE, TextureKey.END, INNER)
            .upload(hollowLog, "_$it", texture, this.modelCollector)
    }
    this.hollowBlock(hollowLog)
    this.registerParentedItemModel(
        hollowLog, block("parent/hollow_log", TextureKey.SIDE, TextureKey.END, INNER)
            .upload(hollowLog, texture, this.modelCollector)
    )
}

fun BlockStateModelGenerator.hollowBambooBlock(hollowBamboo: Block, bambooBlock: Block) {
    val texture: Texture = Texture.texture(hollowBamboo)
        .put(TextureKey.SIDE, bambooBlock.model())
        .put(TextureKey.END, bambooBlock.model("_top"))
    Direction.Type.HORIZONTAL.forEach {
        block("parent/hollow_bamboo_block_$it", TextureKey.SIDE, TextureKey.END)
            .upload(hollowBamboo, "_$it", texture, this.modelCollector)
    }
    this.hollowBlock(hollowBamboo)
    this.registerParentedItemModel(
        hollowBamboo, block("parent/hollow_bamboo_block", TextureKey.SIDE, TextureKey.END)
            .upload(hollowBamboo, texture, this.modelCollector)
    )
}

fun BlockStateModelGenerator.hollowBlock(block: Block) {
    val model = MultipartBlockStateSupplier.create(block)
    var modelId: Identifier
    val allDirectionFalse = When.create()
        .set(HollowLogWithCuttingBlock.NORTH, false)
        .set(HollowLogWithCuttingBlock.SOUTH, false)
        .set(HollowLogWithCuttingBlock.EAST, false)
        .set(HollowLogWithCuttingBlock.WEST, false)
    val directionsX = listOf(
        Direction.WEST,
        Direction.SOUTH,
        Direction.EAST,
        Direction.NORTH,
    )
    Direction.Type.HORIZONTAL.forEachIndexed { idx, it ->
        modelId = block.model("_$it")
        model.with(
            When.create()
                .set(PillarBlock.AXIS, Direction.Axis.X)
                .set(HollowLogWithCuttingBlock.getProperty(it), true),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, block.model("_" + directionsX[idx].toString()))
                .put(VariantSettings.Y, Rotation.R90)
        )
        model.with(
            allDirectionFalse,
            BlockStateVariant.create().put(VariantSettings.MODEL, modelId)
        )
        model.with(
            When.create()
                .set(PillarBlock.AXIS, Direction.Axis.Y)
                .set(HollowLogWithCuttingBlock.getProperty(it), true),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, modelId)
                .put(VariantSettings.X, Rotation.R270)
        )
        model.with(
            allDirectionFalse,
            BlockStateVariant.create().put(VariantSettings.MODEL, modelId)
        )
        model.with(
            When.create()
                .set(PillarBlock.AXIS, Direction.Axis.Z)
                .set(HollowLogWithCuttingBlock.getProperty(it), true),
            BlockStateVariant.create()
                .put(VariantSettings.MODEL, modelId)
        )
        model.with(
            allDirectionFalse,
            BlockStateVariant.create().put(VariantSettings.MODEL, modelId)
        )
    }
    this.blockStateCollector.accept(model)
}


fun BlockStateModelGenerator.createLogPile(logPile: Block, log: Block, bamboo: Boolean = false) {
    val layer1 = this.parentedLogPileModel(logPile, log, bamboo, "_1")
    val layer2 = this.parentedLogPileModel(logPile, log, bamboo, "_2")
    val layer3 = this.parentedLogPileModel(logPile, log, bamboo, "_3")
    val hanging1 = this.parentedLogPileModel(logPile, log, bamboo, "_hanging_1")
    val hanging2 = this.parentedLogPileModel(logPile, log, bamboo, "_hanging_2")
    val hanging3 = this.parentedLogPileModel(logPile, log, bamboo, "_hanging_3")
    val full = this.parentedLogPileModel(logPile, log, bamboo)
    this.registerParentedItemModel(logPile, layer2)
    this.blockStateCollector.accept(
        VariantsBlockStateSupplier.create(logPile)
            .coordinate(
                BlockStateVariantMap.create(
                    LogPileBlock.PILE_LAYERS,
                    Properties.HANGING
                ).register(
                    1, false,
                    BlockStateVariant.create().put(VariantSettings.MODEL, layer1)
                ).register(
                    2, false,
                    BlockStateVariant.create().put(VariantSettings.MODEL, layer2)
                ).register(
                    3, false,
                    BlockStateVariant.create().put(VariantSettings.MODEL, layer3)
                ).register(
                    1, true,
                    BlockStateVariant.create().put(VariantSettings.MODEL, hanging1)
                ).register(
                    2, true,
                    BlockStateVariant.create().put(VariantSettings.MODEL, hanging2)
                ).register(
                    3, true,
                    BlockStateVariant.create().put(VariantSettings.MODEL, hanging3)
                ).register(
                    4, false,
                    BlockStateVariant.create().put(VariantSettings.MODEL, full)
                ).register(
                    4, true,
                    BlockStateVariant.create().put(VariantSettings.MODEL, full)
                )
            ).coordinate(
                BlockStateVariantMap.create(Properties.HORIZONTAL_AXIS)
                    .register(Direction.Axis.X, BlockStateVariant.create())
                    .register(Direction.Axis.Z, BlockStateVariant.create().put(VariantSettings.Y, Rotation.R90))
            )
    )
}

fun BlockStateModelGenerator.parentedLogPileModel(
    block: Block,
    textBlock: Block,
    bamboo: Boolean,
    parent: String = ""
): Identifier {
    val pileModel = if (bamboo) id("block/parent/bamboo_pile") else id("block/parent/log_pile")
    return Model(pileModel.suffix(parent).myb, Optional.empty(), TextureKey.SIDE, TextureKey.END)
        .upload(
            block.model(parent), Texture()
                .put(TextureKey.SIDE, textBlock.model())
                .put(TextureKey.END, textBlock.model("_top")),
            this.modelCollector
        )
}

fun BlockStateModelGenerator.createLeafPile(leafPile: Block, leaves: Block) {
    val pileModel = id("block/parent/leaf_pile")
    val layer1 = this.parentedModel(leafPile, leaves, pileModel)
    val layer2 = this.parentedModel(leafPile.model("_8"), leaves, pileModel.suffix("_8"))
    val layer3 = this.parentedModel(leafPile.model("_12"), leaves, pileModel.suffix("_12"))
    val hanging1 = this.parentedModel(leafPile.model("_hanging"), leaves, pileModel.suffix("_hanging"))
    val hanging2 = this.parentedModel(leafPile.model("_hanging_8"), leaves, pileModel.suffix("_hanging_8"))
    val hanging3 = this.parentedModel(leafPile.model("_hanging_12"), leaves, pileModel.suffix("_hanging_12"))
    val full = this.parentedModel(leafPile.model("_full"), leaves, pileModel.suffix("_full"))
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

//for future reference since i still havent watched tutorials
//private fun leafPileHanging(num: String? = null): Identifier =
//    id("block/parent/leaf_pile_hanging${if (num != null) "_$num" else ""}")
fun BlockStateModelGenerator.registerCropWithParent(
    crop: Block,
    model: Identifier,
    ageProperty: Property<Int>,
    vararg ageTextureIndices: Int
) {
    require(ageProperty.values.size == ageTextureIndices.size)
    val int2ObjectMap: Int2ObjectMap<Identifier> = Int2ObjectOpenHashMap()
    val blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register { age: Int ->
        val stage = ageTextureIndices[age]
        val identifier = int2ObjectMap.computeIfAbsent(stage,
            Int2ObjectFunction { _: Int ->
                this.createSubModel(
                    crop,
                    "_stage$stage",
                    block(model, TextureKey.CROP)
                ) { id: Identifier ->
                    Texture.crop(
                        id
                    )
                }
            })
        BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
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
        (Direction.NORTH to Rotation.R0),
        (Direction.EAST to Rotation.R90),
        (Direction.SOUTH to Rotation.R180),
        (Direction.WEST to Rotation.R270),
        (Direction.DOWN to Rotation.R90),
        (Direction.UP to Rotation.R270)
    )
    var modelId: Identifier
    var axis: VariantSetting<Rotation>
    var variantRotation: Rotation

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
                            Rotation.R0
                        } else {
                            Rotation.R180
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
}

fun parentedItemModel(id: Identifier) = Model(Optional.of(id.withPrefix("item/")), Optional.empty())
fun BlockStateModelGenerator.registerParentedItemModel(block: Block) =
    this.registerParentedItemModel(block, block.model())

fun block(model: Identifier, vararg requiredTextures: TextureKey): Model {
    return Model(
        Optional.of(
            model
        ), Optional.empty(), *requiredTextures
    )
}

fun block(model: Identifier, variant: String, vararg requiredTextures: TextureKey): Model {
    return Model(
        Optional.of(
            model
        ), Optional.of(variant), *requiredTextures
    )
}

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

fun BlockStateModelGenerator.registerPrefixedItemModel(block: Block, prefix: String) {
    val item = block.asItem()
    Models.SINGLE_LAYER_ITEM.upload(
        ModelIds.getItemModelId(item), Texture.layer0(item.prefixed(prefix)),
        this.modelCollector
    )
}


private
val <T : Any?> T.myb get() = Optional.ofNullable(this)

fun Block.model(): Identifier = ModelIds.getBlockModelId(this)
fun Block.model(str: String) = this.model().suffix(str)

fun Block.prefixed(str: String): Identifier = this.id.withPrefix("block/$str")


fun Item.model(): Identifier = ModelIds.getItemModelId(this)
fun Item.model(str: String) = this.model().suffix(str)
fun Item.prefixed(str: String): Identifier = this.id.withPrefix("item/$str")

fun Identifier.toVariant(): BlockStateVariant = BlockStateVariant.create().put(VariantSettings.MODEL, this)

fun Identifier.suffix(str: String) = Identifier(this.namespace, "${this.path}$str")


val Item.id get() = Registries.ITEM.getId(this)
val Block.id get() = Registries.BLOCK.getId(this)
