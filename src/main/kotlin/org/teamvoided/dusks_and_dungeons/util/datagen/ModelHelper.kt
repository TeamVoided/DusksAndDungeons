package org.teamvoided.dusks_and_dungeons.util.datagen

import it.unimi.dsi.fastutil.ints.Int2ObjectFunction
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.world.level.block.state.properties.Half
import net.minecraft.world.level.block.state.properties.SlabType
import net.minecraft.world.level.block.state.properties.StairsShape
import net.minecraft.world.level.block.state.properties.WallSide
import net.minecraft.data.models.BlockModelGenerators.createRotatedVariants
import net.minecraft.data.models.model.TextureSlot.*
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.resources.ResourceLocation
import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.BlockStateGenerator
import net.minecraft.data.models.blockstates.Condition
import net.minecraft.data.models.blockstates.MultiPartGenerator
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.blockstates.Variant
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.blockstates.VariantProperty
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.MultifaceBlock
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.SnowyDirtBlock
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.mc
import org.teamvoided.dusks_and_dungeons.block.*
import org.teamvoided.dusks_and_dungeons.block.not_blocks.TripleBlockSection
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import java.util.*

val INNER: TextureSlot = create("inner")
val SMALL: TextureSlot = create("small")

fun BlockModelGenerators.cubeOverlay(overlay: ResourceLocation) {
    val texture = TextureMapping().put(ALL, overlay)
    ModelTemplates.CUBE_ALL.create(overlay, texture, this.modelOutput)
}

fun BlockModelGenerators.tintedCubeOverlay(overlay: ResourceLocation) {
    val texture = TextureMapping().put(ALL, overlay)
    block("parent/cube_tinted_all", ALL).create(overlay, texture, this.modelOutput)
}

fun BlockModelGenerators.cube15Overlay(overlay: ResourceLocation) {
    val texture = TextureMapping().put(ALL, overlay)
    block("parent/cube_15_all", ALL).create(overlay.suffix("_15"), texture, this.modelOutput)
}

fun BlockModelGenerators.tintedStairAllOverlay(overlay: ResourceLocation) {
    this.tintedStairOverlay(overlay, overlay, overlay, overlay)
}

fun BlockModelGenerators.tintedStairOverlay(
    overlay: ResourceLocation,
    overlayTop: ResourceLocation,
    overlayBottom: ResourceLocation,
    overlaySide: ResourceLocation
) {
    val texture: TextureMapping = TextureMapping()
        .put(TOP, overlayTop)
        .put(BOTTOM, overlayBottom)
        .put(SIDE, overlaySide)

    block("parent/stairs_with_tint", TOP, BOTTOM, SIDE)
        .create(overlay.suffix("_stairs"), texture, this.modelOutput)
    block("parent/stairs_inner_with_tint", TOP, BOTTOM, SIDE)
        .create(overlay.suffix("_stairs_inner"), texture, this.modelOutput)
    block("parent/stairs_outer_with_tint", TOP, BOTTOM, SIDE)
        .create(overlay.suffix("_stairs_outer"), texture, this.modelOutput)
}

fun BlockModelGenerators.tintedSlabAllOverlay(overlay: ResourceLocation) {
    this.tintedSlabOverlay(overlay, overlay, overlay, overlay)
}

fun BlockModelGenerators.tintedSlabOverlay(
    overlay: ResourceLocation,
    overlayTop: ResourceLocation,
    overlayBottom: ResourceLocation,
    overlaySide: ResourceLocation
) {
    val texture = TextureMapping()
        .put(TOP, overlayTop)
        .put(BOTTOM, overlayBottom)
        .put(SIDE, overlaySide)
    block("parent/slab_with_tint", "_post", TOP, BOTTOM, SIDE)
        .create(overlay.suffix("_slab"), texture, this.modelOutput)
    block("parent/slab_top_with_tint", "_side", TOP, BOTTOM, SIDE)
        .create(overlay.suffix("_slab_top"), texture, this.modelOutput)
}

fun BlockModelGenerators.tintedWallOverlay(overlay: ResourceLocation) {
    val texture = TextureMapping().put(WALL, overlay)
    block("parent/wall_post_with_tint", "_post", WALL)
        .create(overlay.suffix("_wall_post"), texture, this.modelOutput)
    block("parent/wall_side_with_tint", "_side", WALL)
        .create(overlay.suffix("_wall_side"), texture, this.modelOutput)
    block("parent/wall_side_tall_with_tint", "_side", WALL)
        .create(overlay.suffix("_wall_side_tall"), texture, this.modelOutput)
}

fun BlockModelGenerators.registerTintedOverlay(overlay: ResourceLocation) {
    this.tintedCubeOverlay(overlay)
    this.tintedStairAllOverlay(overlay)
    this.tintedSlabAllOverlay(overlay)
    this.tintedWallOverlay(overlay)
}

fun BlockModelGenerators.cubeAllWithTintedOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
    this.cubeWithOverlay(block, reference, overlay)
    this.cubeItemAllWithOverlay(block, reference, overlay)
}

fun BlockModelGenerators.cube15WithOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
    this.cubeWithOverlay(block, reference, overlay.suffix("_15"))
    this.cubeItem15WithOverlay(block, reference, overlay)
}

fun BlockModelGenerators.rotatableCubeAllWithOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
    this.rotatableCubeWithOverlay(block, reference, overlay)
    this.cubeItemAllWithOverlay(block, reference, overlay)
}

fun BlockModelGenerators.cubeSnowableColumnWithOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
    this.cubeSnowableColumnOverlay(block, reference, overlay)
    this.cubeSnowableColumnOverlayItem(block, reference, overlay)
}

fun BlockModelGenerators.grassWithOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
    this.cubeSnowableColumnOverlay(block, reference, overlay)
    val texture = TextureMapping()
        .put(BOTTOM, Blocks.DIRT.model())
        .put(TOP, reference.model("_top"))
        .put(LAYER0, reference.model("_side"))
        .put(LAYER1, reference.model("_side_overlay"))
        .put(LAYER2, overlay)
    block("parent/grass_block_with_overlay_inventory", BOTTOM, TOP, LAYER0, LAYER1, LAYER2)
        .create(ModelLocationUtils.getModelLocation(block.asItem()), texture, this.modelOutput)
}

fun BlockModelGenerators.cubeItemAllWithOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
//    yes this is a lazy snow block edge case
    val texture = TextureMapping()
        .put(
            ALL, if (reference == Blocks.SNOW_BLOCK) ResourceLocation.fromNamespaceAndPath(
                reference.model().namespace,
                reference.model().path.removeSuffix("_block")
            ) else reference.model()
        )
        .put(DIRT, overlay)
    block("parent/cube_all_overlay_inventory", ALL, DIRT).create(
        ModelLocationUtils.getModelLocation(block.asItem()), texture,
        this.modelOutput
    )
}

fun BlockModelGenerators.cubeItem15WithOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
    val texture = TextureMapping()
        .put(BOTTOM, Blocks.DIRT.model())
        .put(TOP, reference.model("_top"))
        .put(SIDE, reference.model("_side"))
        .put(DIRT, overlay)
    block("parent/cube_15_overlay_inventory", BOTTOM, TOP, SIDE, DIRT)
        .create(ModelLocationUtils.getModelLocation(block.asItem()), texture, this.modelOutput)
}

fun BlockModelGenerators.cubeSnowableColumnOverlayItem(block: Block, reference: Block, overlay: ResourceLocation) {
    val texture = TextureMapping()
        .put(DIRT, overlay)
        .put(DOWN, Blocks.DIRT.model())
        .put(UP, reference.model("_top"))
        .put(SIDE, reference.model("_side"))
    block("parent/cube_bottom_top_overlay_inventory", DIRT, DOWN, UP, SIDE)
        .create(ModelLocationUtils.getModelLocation(block.asItem()), texture, this.modelOutput)
}

fun BlockModelGenerators.cubeWithOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
    val model = MultiPartGenerator.multiPart(block)
    model.with(
        Variant.variant()
            .with(
                VariantProperties.MODEL,
                reference.model()
            )
    )
    model.with(
        Variant.variant()
            .with(
                VariantProperties.MODEL,
                overlay
            )
    )
    this.blockStateOutput.accept(model)
}

fun BlockModelGenerators.rotatableCubeWithOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
    val model = MultiPartGenerator.multiPart(block)
    model.with(
        listOf(*createRotatedVariants(reference.model()))
    )
    model.with(
        Variant.variant()
            .with(
                VariantProperties.MODEL,
                overlay
            )
    )
    this.blockStateOutput.accept(model)
}

fun BlockModelGenerators.cubeSnowableColumnOverlay(block: Block, reference: Block, overlay: ResourceLocation) {
    val model = MultiPartGenerator.multiPart(block)
    model.with(
        Condition.condition().term(SnowyDirtBlock.SNOWY, false),
        listOf(*createRotatedVariants(reference.model()))
    )
    model.with(
        Condition.condition().term(SnowyDirtBlock.SNOWY, true),
        Variant.variant()
            .with(
                VariantProperties.MODEL,
                Blocks.GRASS_BLOCK.model("_snow")
            )
    )
    model.with(
        Variant.variant()
            .with(
                VariantProperties.MODEL,
                overlay
            )
    )
    this.blockStateOutput.accept(model)
}

fun BlockModelGenerators.stairsWithTintedOverlay(
    stairsBlock: Block, baseBlock: Block, overlay: ResourceLocation
) {
    val regularModelId = "_stairs"
    val innerModelId = "_stairs_inner"
    val outerModelId = "_stairs_outer"
    val half = listOf(
        (Half.BOTTOM to Rotation.R0),
        (Half.TOP to Rotation.R180)
    )
    val directions = listOf(
        (Direction.EAST to Rotation.R0),
        (Direction.SOUTH to Rotation.R90),
        (Direction.WEST to Rotation.R180),
        (Direction.NORTH to Rotation.R270)
    )
    val stairShape = listOf(
        (StairsShape.STRAIGHT to regularModelId),
        (StairsShape.INNER_LEFT to innerModelId),
        (StairsShape.INNER_RIGHT to innerModelId),
        (StairsShape.OUTER_LEFT to outerModelId),
        (StairsShape.OUTER_RIGHT to outerModelId)
    )

    val model = MultiPartGenerator.multiPart(stairsBlock)
    var rotatY: Rotation

    half.forEach { (half, rotationX) ->
        directions.forEach { (direction, rotationY) ->
            stairShape.forEach { (shape, models) ->
                rotatY = if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
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
                    Condition.condition()
                        .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .term(BlockStateProperties.HALF, half)
                        .term(BlockStateProperties.STAIRS_SHAPE, shape),
                    Variant.variant()
                        .with(
                            VariantProperties.MODEL,
                            stairTitle(baseBlock, models)
                        )
                        .with(VariantProperties.X_ROT, rotationX)
                        .with(VariantProperties.Y_ROT, rotatY)
                        .with(VariantProperties.UV_LOCK, true)
                ).with(
                    Condition.condition()
                        .term(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .term(BlockStateProperties.HALF, half)
                        .term(BlockStateProperties.STAIRS_SHAPE, shape),
                    Variant.variant()
                        .with(
                            VariantProperties.MODEL,
                            overlay.suffix(models)
                        )
                        .with(VariantProperties.X_ROT, rotationX)
                        .with(VariantProperties.Y_ROT, rotatY)
                        .with(VariantProperties.UV_LOCK, true)
                )
            }
        }
    }
    this.blockStateOutput.accept(model)
    val texture: TextureMapping = TextureMapping.defaultTexture(stairsBlock)
        .put(BOTTOM, TextureMapping.getBlockTexture(baseBlock))
        .put(SIDE, TextureMapping.getBlockTexture(baseBlock))
        .put(TOP, TextureMapping.getBlockTexture(baseBlock))
        .put(DOWN, overlay)
        .put(NORTH, overlay)
        .put(UP, overlay)
    this.delegateItemModel(
        stairsBlock,
        slabOrStairWithOverlayModel("parent/stairs_inventory_with_tinted_overlay")
            .create(stairsBlock, texture, this.modelOutput)
    )
}

fun stairTitle(block: Block, suffix: String): ResourceLocation {
    return ResourceLocation.fromNamespaceAndPath(block.model().namespace, block.model().path.removeSuffix("s"))
        .suffix(suffix)
}

fun BlockModelGenerators.slabWithTintedOverlay(
    slab: Block, baseBlock: Block, overlay: ResourceLocation
) {
    val slabOfTexture =
        ResourceLocation.fromNamespaceAndPath(baseBlock.model().namespace, baseBlock.model().path.removeSuffix("s"))
    val model = MultiPartGenerator.multiPart(slab)
    val slabType = listOf(
        (SlabType.BOTTOM to "_slab"),
        (SlabType.TOP to "_slab_top"),
        (SlabType.DOUBLE to "")
    )
    slabType.forEach { (type, suffix) ->
        model.with(
            Condition.condition().term(BlockStateProperties.SLAB_TYPE, type),
            Variant.variant()
                .with(VariantProperties.MODEL, if (suffix == "") baseBlock.model() else slabOfTexture.suffix(suffix))
        ).with(
            Condition.condition().term(BlockStateProperties.SLAB_TYPE, type),
            Variant.variant()
                .with(VariantProperties.MODEL, overlay.suffix(suffix))
        )
    }
    this.blockStateOutput.accept(model)


    val texture: TextureMapping = TextureMapping.defaultTexture(slab)
        .put(BOTTOM, TextureMapping.getBlockTexture(baseBlock))
        .put(SIDE, TextureMapping.getBlockTexture(baseBlock))
        .put(TOP, TextureMapping.getBlockTexture(baseBlock))
        .put(DOWN, overlay)
        .put(NORTH, overlay)
        .put(UP, overlay)
    this.delegateItemModel(
        slab,
        slabOrStairWithOverlayModel("parent/slab_inventory_with_tinted_overlay")
            .create(slab, texture, this.modelOutput)
    )
}


fun slabOrStairWithOverlayModel(parent: String): ModelTemplate {
    return block(parent, BOTTOM, TOP, SIDE, DOWN, UP, NORTH)
}

fun BlockModelGenerators.wallWithTintedOverlay(wall: Block, baseBlock: Block, overlay: ResourceLocation) {
    val wallOf =
        ResourceLocation.fromNamespaceAndPath(baseBlock.model().namespace, baseBlock.model().path.removeSuffix("s"))
    this.blockStateOutput.accept(
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
    val texture = TextureMapping.defaultTexture(wall)
        .put(WALL, TextureMapping.getBlockTexture(baseBlock))
        .put(DIRT, overlay)
    this.delegateItemModel(
        wall, block("parent/wall_inventory_with_tinted_overlay", "_inventory", WALL, DIRT)
            .create(wall, texture, this.modelOutput)
    )
}

fun createWallBlockStateWithOverlay(
    wallBlock: Block,
    postModelId: ResourceLocation,
    postOverlayModelId: ResourceLocation,
    lowSideModelId: ResourceLocation,
    lowSideOverlayModelId: ResourceLocation,
    tallSideModelId: ResourceLocation,
    tallSideOverlayModelId: ResourceLocation
): BlockStateGenerator {
    val directions = listOf(
        (BlockStateProperties.NORTH_WALL to Rotation.R0),
        (BlockStateProperties.EAST_WALL to Rotation.R90),
        (BlockStateProperties.SOUTH_WALL to Rotation.R180),
        (BlockStateProperties.WEST_WALL to Rotation.R270)
    )
    val model = MultiPartGenerator.multiPart(wallBlock)
    model.with(
        Condition.condition().term(BlockStateProperties.UP, true),
        Variant.variant().with(VariantProperties.MODEL, postModelId)
    ).with(
        Condition.condition().term(BlockStateProperties.UP, true),
        Variant.variant().with(VariantProperties.MODEL, postOverlayModelId)
    )
    directions.forEach { (shape, rotation) ->
        model.with(
            Condition.condition().term(shape, WallSide.LOW),
            Variant.variant()
                .with(VariantProperties.MODEL, lowSideModelId)
                .with(VariantProperties.UV_LOCK, true)
                .with(VariantProperties.Y_ROT, rotation)
        ).with(
            Condition.condition().term(shape, WallSide.LOW),
            Variant.variant()
                .with(VariantProperties.MODEL, lowSideOverlayModelId)
                .with(VariantProperties.UV_LOCK, true)
                .with(VariantProperties.Y_ROT, rotation)
        )
        model.with(
            Condition.condition().term(shape, WallSide.TALL),
            Variant.variant()
                .with(VariantProperties.MODEL, tallSideModelId)
                .with(VariantProperties.UV_LOCK, true)
                .with(VariantProperties.Y_ROT, rotation)
        ).with(
            Condition.condition().term(shape, WallSide.TALL),
            Variant.variant()
                .with(VariantProperties.MODEL, tallSideOverlayModelId)
                .with(VariantProperties.UV_LOCK, true)
                .with(VariantProperties.Y_ROT, rotation)
        )
    }
    return model
}

fun getRandomYRotations(model: ResourceLocation): Array<Variant> {
    var array = arrayOf<Variant>()
    Rotation.entries.forEach {
        val variant = Variant.variant().with(VariantProperties.MODEL, model)
        if (it != Rotation.R0) variant.with(VariantProperties.Y_ROT, it)
        array += variant
    }
    return array
}

fun getRandomYXRotations(model: ResourceLocation): Array<Variant> {
    var array = arrayOf<Variant>()
    Rotation.entries.forEach { itY ->
        Rotation.entries.forEach { itX ->
            val variant = Variant.variant().with(VariantProperties.MODEL, model)
            if (itY != Rotation.R0) variant.with(VariantProperties.Y_ROT, itY)
            if (itX != Rotation.R0) variant.with(VariantProperties.X_ROT, itX)
            array += variant
        }
    }
    return array
}

fun BlockModelGenerators.createVerdureGrowth(block: Block, top: ResourceLocation, bottom: ResourceLocation) {
    val texture = TextureMapping.defaultTexture(block)
        .put(PARTICLE, TextureMapping.getBlockTexture(block))
        .put(TOP, top)
        .put(SIDE, TextureMapping.getBlockTexture(block))
        .put(BOTTOM, bottom)
    val model = ModelTemplates.CUBE_BOTTOM_TOP.create(block, texture, this.modelOutput)
    blockStateOutput.accept(
        BlockModelGenerators.createRotatedVariant(block, model)
    )
}

fun BlockModelGenerators.registerGoldenMushroomPlant(block: Block) {
    this.createSimpleFlatItemModel(block, "_1")
    var array = arrayOf<Variant>()
    var loop = 1
    repeat(3) {
        val texture = TextureMapping.defaultTexture(block).put(CROSS, TextureMapping.getBlockTexture(block, "_$loop"))
        val model = ModelTemplates.CROSS.createWithSuffix(block, "_$loop", texture, this.modelOutput)
        array += Variant.variant().with(VariantProperties.MODEL, model)
        loop += 1
    }
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(
            block, *array
        )
    )
}

fun BlockModelGenerators.registerMushroomBlockDiffInside(
    block: Block,
    insideTexture: ResourceLocation = block.model().suffix("_inside")
) {
    val texture = ModelTemplates.SINGLE_FACE.create(
        block, TextureMapping.defaultTexture(block),
        this.modelOutput
    )
    val blockstate: MultiPartGenerator = MultiPartGenerator.multiPart(block)
    listOf(true, false).forEach { loop ->
        listOf(
            BlockStateProperties.NORTH,
            BlockStateProperties.EAST,
            BlockStateProperties.SOUTH,
            BlockStateProperties.WEST,
            BlockStateProperties.DOWN,
            BlockStateProperties.UP
        ).forEach { direction ->
            val variant = Variant.variant().with(VariantProperties.MODEL, if (loop) texture else insideTexture)
            val variant2 = when (direction) {
                BlockStateProperties.EAST -> variant.with(VariantProperties.Y_ROT, Rotation.R90)
                BlockStateProperties.SOUTH -> variant.with(VariantProperties.Y_ROT, Rotation.R180)
                BlockStateProperties.WEST -> variant.with(VariantProperties.Y_ROT, Rotation.R270)
                BlockStateProperties.DOWN -> variant.with(VariantProperties.X_ROT, Rotation.R90)
                BlockStateProperties.UP -> variant.with(VariantProperties.X_ROT, Rotation.R270)
                else -> variant
            }
            blockstate.with(
                Condition.condition().term(direction, loop),
                variant2.with(VariantProperties.UV_LOCK, loop)
            )
        }
    }

    this.blockStateOutput.accept(blockstate)
    this.delegateItemModel(
        block, TexturedModel.CUBE.createWithSuffix(
            block, "_inventory",
            this.modelOutput
        )
    )
}

fun BlockModelGenerators.registerTreeMushroom(block: Block, parentModel: String) {
    this.createSimpleFlatItemModel(block)
    val texture = TextureMapping.defaultTexture(block)
        .put(TOP, TextureMapping.getBlockTexture(block, "_top"))
        .put(BOTTOM, TextureMapping.getBlockTexture(block, "_bottom"))
        .put(SMALL, TextureMapping.getBlockTexture(block, "_small"))
    val model = block(
        parentModel,
        TOP,
        BOTTOM,
        SMALL
    )
    this.createTrivialBlock(block, texture, model)
}


fun BlockModelGenerators.registerFlowerbed2(
    block: Block,
    useDefault: Boolean = true,
    parent: ResourceLocation = mc("block/flowerbed")
) {
    this.createSimpleFlatItemModel(block.asItem())
    val texture = TextureMapping()
        .put(FLOWERBED, TextureMapping.getBlockTexture(block))
        .put(STEM, if (useDefault) TextureMapping.getBlockTexture(block, "_stem") else id("block/petals_stem"))
    val identifier = block(parent.suffix("_1"), "_1", FLOWERBED, STEM)
        .create(block, texture, this.modelOutput)
    val identifier2 = block(parent.suffix("_2"), "_2", FLOWERBED, STEM)
        .create(block, texture, this.modelOutput)
    val identifier3 = block(parent.suffix("_3"), "_3", FLOWERBED, STEM)
        .create(block, texture, this.modelOutput)
    val identifier4 = block(parent.suffix("_4"), "_4", FLOWERBED, STEM)
        .create(block, texture, this.modelOutput)
    val flowerbed = MultiPartGenerator.multiPart(block)
    val directionAndRotation = listOf(
        (Direction.NORTH to Rotation.R0),
        (Direction.EAST to Rotation.R90),
        (Direction.SOUTH to Rotation.R180),
        (Direction.WEST to Rotation.R270)
    )
    directionAndRotation.forEach { (direction, rotation) ->
        flowerbed.with(
            Condition.condition()
                .term(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4)
                .term(BlockStateProperties.HORIZONTAL_FACING, direction),
            Variant.variant()
                .with(VariantProperties.MODEL, identifier)
                .with(VariantProperties.Y_ROT, rotation)
        ).with(
            Condition.condition()
                .term(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4)
                .term(BlockStateProperties.HORIZONTAL_FACING, direction),
            Variant.variant()
                .with(VariantProperties.MODEL, identifier2)
                .with(VariantProperties.Y_ROT, rotation)
        ).with(
            Condition.condition()
                .term(BlockStateProperties.FLOWER_AMOUNT, 3, 4)
                .term(BlockStateProperties.HORIZONTAL_FACING, direction),
            Variant.variant()
                .with(VariantProperties.MODEL, identifier3)
                .with(VariantProperties.Y_ROT, rotation)
        ).with(
            Condition.condition()
                .term(BlockStateProperties.FLOWER_AMOUNT, 4)
                .term(BlockStateProperties.HORIZONTAL_FACING, direction),
            Variant.variant()
                .with(VariantProperties.MODEL, identifier4)
                .with(VariantProperties.Y_ROT, rotation)
        )
    }
    this.blockStateOutput.accept(flowerbed)
}

fun BlockModelGenerators.registerPumpkinSet(
    pumpkin: Block,
    carved: Block,
    glowing: Block,
    smallPumpkin: Block,
    smallCarved: Block,
    smallGlowing: Block,
    stem: Block
) {
    this.registerPumpkins(pumpkin, carved, glowing)
    this.registerSmallPumpkins(smallPumpkin, smallCarved, smallGlowing, pumpkin)
//    this.registerSmallPumpkin(smallPumpkin, smallPumpkin, Texture.getSubId(pumpkin, "_side"))
//    this.registerSmallPumpkin(smallCarved, smallPumpkin, Texture.getId(carved), "carved_")
//    this.registerSmallPumpkin(smallGlowing, smallPumpkin, Texture.getId(glowing), "glowing_")
    this.pumpkinStem(stem)
}

fun BlockModelGenerators.registerPumpkins(pumpkin: Block, carved: Block, glowing: Block) {
    val texture = TextureMapping()
        .put(END, TextureMapping.getBlockTexture(pumpkin, "_top"))
        .put(SIDE, TextureMapping.getBlockTexture(pumpkin, "_side"))
    val model = ModelTemplates.CUBE_COLUMN.create(pumpkin, texture, this.modelOutput)
    ModelTemplates.CUBE_ORIENTABLE.create(
        carved,
        texture.put(FRONT, TextureMapping.getBlockTexture(carved)),
        this.modelOutput
    )
    ModelTemplates.CUBE_ORIENTABLE.create(
        glowing,
        texture.put(FRONT, TextureMapping.getBlockTexture(glowing)),
        this.modelOutput
    )

    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pumpkin, model))
    this.createNonTemplateHorizontalBlock(carved)
    this.createNonTemplateHorizontalBlock(glowing)
}

fun BlockModelGenerators.registerSmallPumpkins(pumpkin: Block, carved: Block, glowing: Block, particle: Block) {
    val texture = TextureMapping()
        .put(ALL, TextureMapping.getBlockTexture(pumpkin))
        .put(PARTICLE, TextureMapping.getBlockTexture(particle, "_side"))
    val model = block(
        "parent/small_pumpkin",
        PARTICLE,
        ALL
    ).create(pumpkin, texture, this.modelOutput)
    block(
        "parent/small_carved_pumpkin",
        PARTICLE,
        ALL
    ).create(carved, texture, this.modelOutput)
    block(
        "parent/small_glowing_pumpkin",
        PARTICLE,
        ALL
    ).create(glowing, texture, this.modelOutput)

    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pumpkin, model))
    this.createNonTemplateHorizontalBlock(carved)
    this.createNonTemplateHorizontalBlock(glowing)
}

fun BlockModelGenerators.registerSmallPumpkin(
    pumpkin: Block,
    texture: Block,
    particle: ResourceLocation,
    modelString: String = ""
) {
    val modelTexture = TextureMapping()
        .put(ALL, TextureMapping.getBlockTexture(texture))
        .put(PARTICLE, particle)
    val model = block(
        "parent/small_" + modelString + "pumpkin",
        PARTICLE,
        ALL
    ).create(pumpkin, modelTexture, this.modelOutput)
    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pumpkin, model))
}

fun BlockModelGenerators.pumpkinStem(block: Block) {
    val texture = TextureMapping.stem(block)
    val textureAttach =
        TextureMapping().put(STEM, TextureMapping.getBlockTexture(block)).put(UPPER_STEM, block.prefixed("attached_"))

    BlockStateProperties.AGE_7.possibleValues.forEach { age ->
        ModelTemplates.STEMS[age].create(block, texture, this.modelOutput)
    }
    ModelTemplates.ATTACHED_STEM.createWithSuffix(block, "_stage7_attached", textureAttach, this.modelOutput)
    println("you best do the " + block.model().path + " blockstate file because waaaaaaa")
}

fun BlockModelGenerators.registerGravestones(gravestone: Block, smallGravestone: Block) {
    this.registerGravestone(gravestone, gravestone.model())
    this.registerSmallGravestone(smallGravestone, gravestone.model())
}

fun BlockModelGenerators.registerGravestone(gravestone: Block, texture: ResourceLocation = gravestone.model()) {
    val texture = TextureMapping()
        .put(FRONT, texture.suffix("_front"))
        .put(SIDE, texture.suffix("_side"))
    block(
        "parent/gravestone",
        FRONT,
        SIDE
    ).create(gravestone, texture, this.modelOutput)
    val centerModel = block(
        "parent/gravestone_centered",
        FRONT,
        SIDE
    ).createWithSuffix(gravestone, "_centered", texture, this.modelOutput)

    this.delegateItemModel(gravestone, centerModel)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(gravestone).with(
            gravestoneBlockstates(gravestone)
        )
    )
}

fun BlockModelGenerators.registerSmallGravestone(
    gravestone: Block,
    textureId: ResourceLocation = gravestone.model(),
    hauntedGravestone: Block? = null
) {
    val texture = TextureMapping()
        .put(FRONT, textureId.suffix("_front"))
    block("parent/small_gravestone", FRONT)
        .create(gravestone, texture, this.modelOutput)
    val centerModel = block("parent/small_gravestone_centered", FRONT)
        .createWithSuffix(gravestone, "_centered", texture, this.modelOutput)
    this.delegateItemModel(gravestone, centerModel)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(gravestone).with(
            gravestoneBlockstates(gravestone)
        )
    )
    if (hauntedGravestone != null)
        this.registerHauntedGravestone(hauntedGravestone, gravestone, centerModel)
}

fun BlockModelGenerators.registerHauntedGravestone(
    hauntedGravestone: Block,
    gravestone: Block,
    centerModel: ResourceLocation
) {
    this.delegateItemModel(hauntedGravestone, centerModel)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(hauntedGravestone).with(
            gravestoneBlockstates(gravestone)
        )
    )
}

fun BlockModelGenerators.registerHeadstone(headstone: Block) {
    val texture = TextureMapping().put(ALL, TextureMapping.getBlockTexture(headstone))
    block("parent/headstone", ALL)
        .create(headstone, texture, this.modelOutput)
    block("parent/headstone_centered", ALL)
        .createWithSuffix(headstone, "_centered", texture, this.modelOutput)
    this.createSimpleFlatItemModel(headstone)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(headstone).with(
            gravestoneBlockstates(headstone)
        )
    )
}

fun gravestoneBlockstates(gravestone: Block): PropertyDispatch.C2<Direction, Boolean> {
    val variants = PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, GravestoneBlock.CENTERED)
    GravestoneBlock.CENTERED.possibleValues.forEach {
        BlockStateProperties.HORIZONTAL_FACING.possibleValues.forEach { direction ->
            val string = if (it) "_centered" else ""
            val variant = Variant.variant()
                .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(gravestone, string))
            val variant2 = when (direction) {
                Direction.NORTH -> variant.with(VariantProperties.Y_ROT, Rotation.R180)
                Direction.EAST -> variant.with(VariantProperties.Y_ROT, Rotation.R270)
                Direction.WEST -> variant.with(VariantProperties.Y_ROT, Rotation.R90)
                else -> variant
            }
            variants.select(direction, it, variant2)
        }
    }
    return variants
}

fun BlockModelGenerators.registerCorn(block: Block, item: Item) {
//    this.registerItemModel(block, "_top") //this one is for if the names are the same, they are not
    ModelTemplates.FLAT_ITEM.create(
        ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(TextureMapping.getBlockTexture(block, "_top")),
        this.modelOutput
    )
    val textureTop = TextureMapping().put(ALL, TextureMapping.getBlockTexture(block, "_top"))
    val textureMiddle = TextureMapping().put(ALL, TextureMapping.getBlockTexture(block, "_middle"))
    val textureBottom = TextureMapping().put(ALL, TextureMapping.getBlockTexture(block, "_bottom"))
    val model = block(
        "parent/corn",
        ALL
    )
    val top = model.createWithSuffix(block, "_top", textureTop, this.modelOutput)
    val middle = model.createWithSuffix(block, "_middle", textureMiddle, this.modelOutput)
    val bottom = model.createWithSuffix(block, "_bottom", textureBottom, this.modelOutput)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block).with(
            PropertyDispatch.property(
                TripleTallPlantBlock.SECTION
            ).select(
                TripleBlockSection.TOP,
                Variant.variant().with(VariantProperties.MODEL, top)
            ).select(
                TripleBlockSection.MIDDLE,
                Variant.variant().with(VariantProperties.MODEL, middle)
            ).select(
                TripleBlockSection.BOTTOM,
                Variant.variant().with(VariantProperties.MODEL, bottom)
            )
        )
    )
}

fun BlockModelGenerators.registerCornCrop(block: Block) {
    val model = block(
        "parent/corn_crop",
        CROP
    )
    val blockStateVariantMap = PropertyDispatch.properties(CornCropBlock.AGE, TripleTallPlantBlock.SECTION)
        .generate { age: Int, section: TripleBlockSection ->
            val suffix = "_$section" + "_stage_$age"
            val texture = TextureMapping().put(CROP, TextureMapping.getBlockTexture(block, suffix))
            if (section == TripleBlockSection.BOTTOM) {
                val modelBottom = model.createWithSuffix(block, suffix, texture, this.modelOutput)
                Variant.variant().with(
                    VariantProperties.MODEL,
                    modelBottom
                )
            } else if (age > 1 && section == TripleBlockSection.MIDDLE) {
                val modelMiddle = model.createWithSuffix(block, suffix, texture, this.modelOutput)
                Variant.variant().with(
                    VariantProperties.MODEL,
                    modelMiddle
                )
            } else if (age > 3 && section == TripleBlockSection.TOP) {
                val modelTop = model.createWithSuffix(block, suffix, texture, this.modelOutput)
                Variant.variant().with(
                    VariantProperties.MODEL,
                    modelTop
                )
            } else {
                Variant.variant().with(
                    VariantProperties.MODEL,
                    ModelLocationUtils.getModelLocation(Blocks.AIR)
                )
            }
        }
    this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(blockStateVariantMap))
}

fun BlockModelGenerators.registerBigChain(block: Block) {
    this.createSimpleFlatItemModel(block.asItem())
    val texture = TextureMapping()
        .put(PARTICLE, TextureMapping.getBlockTexture(block))
        .put(ALL, TextureMapping.getBlockTexture(block))
    val model = block(
        "parent/big_chain",
        PARTICLE,
        ALL
    ).create(block, texture, this.modelOutput)
    this.createAxisAlignedPillarBlockCustomModel(block, model)
}

fun BlockModelGenerators.registerBigLantern(
    block: Block,
    bottom: ResourceLocation = id("block/big_lantern_bottom")
) {
    this.createSimpleFlatItemModel(block)
    val texture = TextureMapping()
        .put(PARTICLE, TextureMapping.getBlockTexture(block))
        .put(SIDE, TextureMapping.getBlockTexture(block))
        .put(END, bottom)
    val model = block(
        "parent/big_lantern",
        PARTICLE,
        SIDE,
        END
    )
    val modelHanging = model.createWithSuffix(block, "_hanging", texture, this.modelOutput)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block).with(
            PropertyDispatch.property(BlockStateProperties.HANGING)
                .select(
                    false, Variant.variant()
                        .with(VariantProperties.MODEL, model.create(block, texture, this.modelOutput))
                )
                .select(
                    true, Variant.variant()
                        .with(VariantProperties.X_ROT, Rotation.R180)
                        .with(VariantProperties.MODEL, modelHanging)
                )
        )
    )
}

fun BlockModelGenerators.registerBigCandle(pair: Pair<Block, Block?>) =
    this.registerBigCandle(pair.first, pair.second)

fun BlockModelGenerators.registerBigCandle(candle: Block, cake: Block?) {
    this.registerPrefixedItemModel(candle, "candle/")
    val texture = TextureMapping.cube(candle.prefixed("candle/"))
    val textureLit = TextureMapping.cube(candle.prefixed("candle/").suffix("_lit"))
    val oneCandle = bigCandleModel("1").createWithSuffix(candle, "_one_candle", texture, this.modelOutput)
    val twoCandle = bigCandleModel("2").createWithSuffix(candle, "_two_candles", texture, this.modelOutput)
    val threeCandle = bigCandleModel("3").createWithSuffix(candle, "_three_candles", texture, this.modelOutput)
    val fourCandle = bigCandleModel("4").createWithSuffix(candle, "_four_candles", texture, this.modelOutput)
    val oneCandleLit = bigCandleModel("1").createWithSuffix(candle, "_one_candle_lit", textureLit, this.modelOutput)
    val twoCandleLit = bigCandleModel("2").createWithSuffix(candle, "_two_candles_lit", textureLit, this.modelOutput)
    val threeCandleLit =
        bigCandleModel("3").createWithSuffix(candle, "_three_candles_lit", textureLit, this.modelOutput)
    val fourCandleLit = bigCandleModel("4").createWithSuffix(candle, "_four_candles_lit", textureLit, this.modelOutput)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(candle)
            .with(BlockModelGenerators.createHorizontalFacingDispatchAlt())
            .with(
                PropertyDispatch.properties(BlockStateProperties.CANDLES, BlockStateProperties.LIT)
                    .select(1, false, Variant.variant().with(VariantProperties.MODEL, oneCandle))
                    .select(2, false, Variant.variant().with(VariantProperties.MODEL, twoCandle))
                    .select(3, false, Variant.variant().with(VariantProperties.MODEL, threeCandle))
                    .select(4, false, Variant.variant().with(VariantProperties.MODEL, fourCandle))
                    .select(1, true, Variant.variant().with(VariantProperties.MODEL, oneCandleLit))
                    .select(2, true, Variant.variant().with(VariantProperties.MODEL, twoCandleLit))
                    .select(3, true, Variant.variant().with(VariantProperties.MODEL, threeCandleLit))
                    .select(4, true, Variant.variant().with(VariantProperties.MODEL, fourCandleLit))
            )
    )
    if (cake != null) {
        val candleCake = bigCandleCakeModel().create(
            cake, candleCake(candle, false),
            this.modelOutput
        )
        val candleCakeLit = bigCandleCakeModel().createWithSuffix(
            cake, "_lit", candleCake(candle, true),
            this.modelOutput
        )
        this.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(cake).with(
                BlockModelGenerators.createBooleanModelDispatch(
                    BlockStateProperties.LIT, candleCakeLit, candleCake
                )
            )
        )
    }
}

fun BlockModelGenerators.registerCandle2(pair: Pair<Block, Block?>) = this.registerCandle2(pair.first, pair.second)
fun BlockModelGenerators.registerCandle2(candle: Block, cake: Block?) {
    this.registerPrefixedItemModel(candle, "candle/")
    val texture = TextureMapping.cube(candle.prefixed("candle/"))
    val textureLit = TextureMapping.cube(candle.prefixed("candle/").suffix("_lit"))
    val oneCandle = ModelTemplates.CANDLE.createWithSuffix(candle, "_one_candle", texture, this.modelOutput)
    val twoCandle = ModelTemplates.TWO_CANDLES.createWithSuffix(candle, "_two_candles", texture, this.modelOutput)
    val threeCandle = ModelTemplates.THREE_CANDLES.createWithSuffix(candle, "_three_candles", texture, this.modelOutput)
    val fourCandle = ModelTemplates.FOUR_CANDLES.createWithSuffix(candle, "_four_candles", texture, this.modelOutput)
    val oneCandleLit = ModelTemplates.CANDLE.createWithSuffix(candle, "_one_candle_lit", textureLit, this.modelOutput)
    val twoCandleLit =
        ModelTemplates.TWO_CANDLES.createWithSuffix(candle, "_two_candles_lit", textureLit, this.modelOutput)
    val threeCandleLit =
        ModelTemplates.THREE_CANDLES.createWithSuffix(candle, "_three_candles_lit", textureLit, this.modelOutput)
    val fourCandleLit =
        ModelTemplates.FOUR_CANDLES.createWithSuffix(candle, "_four_candles_lit", textureLit, this.modelOutput)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(candle).with(
            PropertyDispatch.properties(
                BlockStateProperties.CANDLES, BlockStateProperties.LIT
            )
                .select(1, false, Variant.variant().with(VariantProperties.MODEL, oneCandle))
                .select(2, false, Variant.variant().with(VariantProperties.MODEL, twoCandle))
                .select(3, false, Variant.variant().with(VariantProperties.MODEL, threeCandle))
                .select(4, false, Variant.variant().with(VariantProperties.MODEL, fourCandle))
                .select(1, true, Variant.variant().with(VariantProperties.MODEL, oneCandleLit))
                .select(2, true, Variant.variant().with(VariantProperties.MODEL, twoCandleLit))
                .select(3, true, Variant.variant().with(VariantProperties.MODEL, threeCandleLit))
                .select(4, true, Variant.variant().with(VariantProperties.MODEL, fourCandleLit))
        )
    )
    if (cake != null) {
        val candleCake = ModelTemplates.CANDLE_CAKE.create(
            cake, candleCake(candle, false),
            this.modelOutput
        )
        val candleCakeLit = ModelTemplates.CANDLE_CAKE.createWithSuffix(
            cake, "_lit", candleCake(candle, true),
            this.modelOutput
        )
        this.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(cake).with(
                BlockModelGenerators.createBooleanModelDispatch(
                    BlockStateProperties.LIT, candleCakeLit, candleCake
                )
            )
        )
    }
}

fun candleCake(block: Block, lit: Boolean): TextureMapping {
    return TextureMapping()
        .put(PARTICLE, TextureMapping.getBlockTexture(Blocks.CAKE, "_side"))
        .put(BOTTOM, TextureMapping.getBlockTexture(Blocks.CAKE, "_bottom"))
        .put(TOP, TextureMapping.getBlockTexture(Blocks.CAKE, "_top"))
        .put(SIDE, TextureMapping.getBlockTexture(Blocks.CAKE, "_side"))
        .put(CANDLE, block.prefixed("candle/").suffix(if (lit) "_lit" else ""))
}

fun BlockModelGenerators.registerBigTallCandle(candle: Block) {
    this.registerPrefixedItemModel(candle, "candle/")
    val texture = TextureMapping.cube(candle.prefixed("candle/"))
    val textureLit = TextureMapping.cube(candle.prefixed("candle/").suffix("_lit"))
    val oneCandle = bigTallCandleModel("1").createWithSuffix(candle, "_one_candle", texture, this.modelOutput)
    val twoCandle = bigTallCandleModel("2").createWithSuffix(candle, "_two_candles", texture, this.modelOutput)
    val threeCandle = bigTallCandleModel("3").createWithSuffix(candle, "_three_candles", texture, this.modelOutput)
    val fourCandle = bigTallCandleModel("4").createWithSuffix(candle, "_four_candles", texture, this.modelOutput)
    val oneCandleLit = bigTallCandleModel("1").createWithSuffix(candle, "_one_candle_lit", textureLit, this.modelOutput)
    val twoCandleLit =
        bigTallCandleModel("2").createWithSuffix(candle, "_two_candles_lit", textureLit, this.modelOutput)
    val threeCandleLit =
        bigTallCandleModel("3").createWithSuffix(candle, "_three_candles_lit", textureLit, this.modelOutput)
    val fourCandleLit =
        bigTallCandleModel("4").createWithSuffix(candle, "_four_candles_lit", textureLit, this.modelOutput)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(candle)
            .with(
                PropertyDispatch.properties(BlockStateProperties.CANDLES, BlockStateProperties.LIT)
                    .select(1, false, Variant.variant().with(VariantProperties.MODEL, oneCandle))
                    .select(2, false, Variant.variant().with(VariantProperties.MODEL, twoCandle))
                    .select(3, false, Variant.variant().with(VariantProperties.MODEL, threeCandle))
                    .select(4, false, Variant.variant().with(VariantProperties.MODEL, fourCandle))
                    .select(1, true, Variant.variant().with(VariantProperties.MODEL, oneCandleLit))
                    .select(2, true, Variant.variant().with(VariantProperties.MODEL, twoCandleLit))
                    .select(3, true, Variant.variant().with(VariantProperties.MODEL, threeCandleLit))
                    .select(4, true, Variant.variant().with(VariantProperties.MODEL, fourCandleLit))
            )
    )
}

fun bigCandleModel(suffix: String): ModelTemplate {
    val variant = if (suffix == "1") "" else "_$suffix"
    return block("parent/big_candle$variant", ALL, PARTICLE)
}

fun bigCandleCakeModel(): ModelTemplate {
    return block("parent/cake_with_big_candle", CANDLE, PARTICLE)
}

fun bigTallCandleModel(suffix: String): ModelTemplate {
    val variant = if (suffix == "1") "" else "_$suffix"
    return block("parent/big_tall_candle$variant", ALL, PARTICLE)
}

fun BlockModelGenerators.registerMixedNetherBrickPillar(block: Block, mix: Block) {
    val texture1 = TextureMapping()
        .put(SIDE, TextureMapping.getBlockTexture(block))
        .put(TOP, TextureMapping.getBlockTexture(DnDBlocks.NETHER_BRICK_PILLAR, "_top"))
        .put(BOTTOM, TextureMapping.getBlockTexture(mix, "_top"))
    val texture2 = TextureMapping()
        .put(SIDE, TextureMapping.getBlockTexture(block, "_inverse"))
        .put(TOP, TextureMapping.getBlockTexture(mix, "_top"))
        .put(BOTTOM, TextureMapping.getBlockTexture(DnDBlocks.NETHER_BRICK_PILLAR, "_top"))
    val model1 = ModelTemplates.CUBE_BOTTOM_TOP.create(block, texture1, this.modelOutput)
    val model2 = ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(block, "_inverse", texture2, this.modelOutput)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block).with(
            PropertyDispatch.property(BlockStateProperties.FACING)
                .select(
                    Direction.UP, Variant.variant()
                        .with(VariantProperties.MODEL, model1)
                )
                .select(
                    Direction.DOWN, Variant.variant()
                        .with(VariantProperties.MODEL, model2)
                )
                .select(
                    Direction.NORTH,
                    Variant.variant()
                        .with(VariantProperties.X_ROT, Rotation.R90)
                        .with(VariantProperties.MODEL, model1)
                )
                .select(
                    Direction.SOUTH, Variant.variant()
                        .with(VariantProperties.X_ROT, Rotation.R90)
                        .with(VariantProperties.MODEL, model2)
                )
                .select(
                    Direction.EAST,
                    Variant.variant()
                        .with(VariantProperties.X_ROT, Rotation.R90)
                        .with(VariantProperties.Y_ROT, Rotation.R90)
                        .with(VariantProperties.MODEL, model1)
                )
                .select(
                    Direction.WEST,
                    Variant.variant()
                        .with(VariantProperties.X_ROT, Rotation.R90)
                        .with(VariantProperties.Y_ROT, Rotation.R90)
                        .with(VariantProperties.MODEL, model2)
                )
        )
    )
}

fun BlockModelGenerators.iceStairs(
    block: Block,
    parent: Block
) {
    val texture: TextureMapping = TextureMapping.defaultTexture(parent)
        .put(BOTTOM, parent.model())
        .put(SIDE, parent.model())
        .put(TOP, parent.model())
    val ner: ResourceLocation = block("parent/translucent_stairs", BOTTOM, TOP, SIDE)
        .create(block, texture, this.modelOutput)
    val inner: ResourceLocation =
        block("parent/translucent_inner_stairs", "_inner", BOTTOM, TOP, SIDE)
            .create(block, texture, this.modelOutput)
    val outer: ResourceLocation =
        block("parent/translucent_outer_stairs", "_outer", BOTTOM, TOP, SIDE)
            .create(block, texture, this.modelOutput)

    this.blockStateOutput.accept(BlockModelGenerators.createStairs(block, inner, ner, outer))
    this.delegateItemModel(block, ner)
}

//shamelessley stolen from voidUtils :)
fun BlockModelGenerators.stairs(block: Block) =
    stairs(block, block, block, block)

fun BlockModelGenerators.stairs(block: Block, texture: Block) =
    stairs(block, texture, texture, texture)

fun BlockModelGenerators.stairs(block: Block, texture: Block, suffix: String) =
    stairs(block, texture.model(suffix), texture.model(suffix), texture.model(suffix))

fun BlockModelGenerators.stairs(block: Block, bottom: Block, side: Block, top: Block) =
    stairs(block, bottom.model(), side.model(), top.model())

fun BlockModelGenerators.stairs(block: Block, ends: ResourceLocation, side: ResourceLocation) =
    stairs(block, ends, side, ends)

fun BlockModelGenerators.stairs(
    block: Block,
    bottom: ResourceLocation,
    side: ResourceLocation,
    top: ResourceLocation,
) {
    val texture: TextureMapping = TextureMapping()
        .put(BOTTOM, bottom)
        .put(SIDE, side)
        .put(TOP, top)
    val id: ResourceLocation = ModelTemplates.STAIRS_INNER.create(block, texture, this.modelOutput)
    val id2: ResourceLocation = ModelTemplates.STAIRS_STRAIGHT.create(block, texture, this.modelOutput)
    val id3: ResourceLocation = ModelTemplates.STAIRS_OUTER.create(block, texture, this.modelOutput)

    this.blockStateOutput.accept(BlockModelGenerators.createStairs(block, id, id2, id3))
    this.delegateItemModel(block, id2)
}

fun BlockModelGenerators.slab(block: Block) = slab(block, block)
fun BlockModelGenerators.slab(block: Block, texture: Block) =
    slab(block, texture, texture, texture, texture)

fun BlockModelGenerators.slab(block: Block, texture: Block, full: Block) =
    slab(block, slabTexture(texture), full)

fun BlockModelGenerators.slab(block: Block, bottom: Block, side: Block, top: Block, full: Block) =
    slab(
        block, TextureMapping.defaultTexture(block.model())
            .put(BOTTOM, bottom.model())
            .put(SIDE, side.model())
            .put(TOP, top.model()),
        full
    )

fun BlockModelGenerators.slab(block: Block, texture: TextureMapping, full: Block) {
    val id = ModelTemplates.SLAB_BOTTOM.create(block, texture, this.modelOutput)
    val id2 = ModelTemplates.SLAB_TOP.create(block, texture, this.modelOutput)
    val id3 = full.model()
    this.blockStateOutput.accept(BlockModelGenerators.createSlab(block, id, id2, id3))
    this.delegateItemModel(block, id)
}

fun slabTexture(texture: Block): TextureMapping = TextureMapping.defaultTexture(texture.model())
    .put(BOTTOM, texture.model())
    .put(SIDE, texture.model())
    .put(TOP, texture.model())

fun BlockModelGenerators.wall(block: Block) = wall(block, block.model())
fun BlockModelGenerators.wall(block: Block, texture: Block) = wall(block, texture.model())

fun BlockModelGenerators.wall(wallBlock: Block, inId: ResourceLocation) {
    val texture = TextureMapping.defaultTexture(wallBlock.model()).put(WALL, inId)
    val id = ModelTemplates.WALL_POST.create(wallBlock, texture, this.modelOutput)
    val id2 = ModelTemplates.WALL_LOW_SIDE.create(wallBlock, texture, this.modelOutput)
    val id3 = ModelTemplates.WALL_TALL_SIDE.create(wallBlock, texture, this.modelOutput)
    this.blockStateOutput.accept(BlockModelGenerators.createWall(wallBlock, id, id2, id3))
    this.delegateItemModel(wallBlock, ModelTemplates.WALL_INVENTORY.create(wallBlock, texture, this.modelOutput))
}

fun BlockModelGenerators.fence(fenceBlock: Block, reference: Block) {
    val texture = TextureMapping.defaultTexture(reference)
    val id = ModelTemplates.FENCE_POST.create(fenceBlock, texture, this.modelOutput)
    val id2 = ModelTemplates.FENCE_SIDE.create(fenceBlock, texture, this.modelOutput)
    val id3 = ModelTemplates.FENCE_INVENTORY.create(fenceBlock, texture, this.modelOutput)
    this.blockStateOutput.accept(BlockModelGenerators.createFence(fenceBlock, id, id2))
    this.delegateItemModel(fenceBlock, id3)
}

fun BlockModelGenerators.registerHandheldItem(item: Item) {
    ModelTemplates.FLAT_HANDHELD_ITEM.create(
        ModelLocationUtils.getModelLocation(item),
        TextureMapping.layer0(item),
        this.modelOutput
    )
}

fun BlockModelGenerators.hollowLog(hollowLog: Block, log: Block) {
    this.hollowLog(hollowLog, log, log.model())
}

fun BlockModelGenerators.hollowLog(hollowLog: Block, log: Block, strippedLog: Block) {
    this.hollowLog(hollowLog, log, strippedLog.model())
}

fun BlockModelGenerators.hollowLog(hollowLog: Block, log: Block, innerTexture: ResourceLocation) {
    val texture: TextureMapping = TextureMapping.defaultTexture(hollowLog)
        .put(SIDE, log.model())
        .put(END, log.model("_top"))
        .put(INNER, innerTexture)
    Direction.Plane.HORIZONTAL.forEach {
        block("parent/hollow_log_$it", SIDE, END, INNER)
            .createWithSuffix(hollowLog, "_$it", texture, this.modelOutput)
    }
    this.hollowBlock(hollowLog)
    this.delegateItemModel(
        hollowLog, block("parent/hollow_log", SIDE, END, INNER)
            .create(hollowLog, texture, this.modelOutput)
    )
}

fun BlockModelGenerators.hollowBambooBlock(hollowBamboo: Block, bambooBlock: Block) {
    val texture: TextureMapping = TextureMapping.defaultTexture(hollowBamboo)
        .put(SIDE, bambooBlock.model())
        .put(END, bambooBlock.model("_top"))
    Direction.Plane.HORIZONTAL.forEach {
        block("parent/hollow_bamboo_block_$it", SIDE, END)
            .createWithSuffix(hollowBamboo, "_$it", texture, this.modelOutput)
    }
    this.hollowBlock(hollowBamboo)
    this.delegateItemModel(
        hollowBamboo, block("parent/hollow_bamboo_block", SIDE, END)
            .create(hollowBamboo, texture, this.modelOutput)
    )
}

fun BlockModelGenerators.hollowBlock(block: Block) {
    val model = MultiPartGenerator.multiPart(block)
    var modelId: ResourceLocation
    val allDirectionFalse = Condition.condition()
        .term(HollowLogWithCuttingBlock.NORTH, false)
        .term(HollowLogWithCuttingBlock.SOUTH, false)
        .term(HollowLogWithCuttingBlock.EAST, false)
        .term(HollowLogWithCuttingBlock.WEST, false)
    val directionsX = listOf(
        Direction.WEST,
        Direction.SOUTH,
        Direction.EAST,
        Direction.NORTH,
    )
    Direction.Plane.HORIZONTAL.forEachIndexed { idx, it ->
        modelId = block.model("_$it")
        model.with(
            Condition.condition()
                .term(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .term(HollowLogWithCuttingBlock.getProperty(it), true),
            Variant.variant()
                .with(VariantProperties.MODEL, block.model("_" + directionsX[idx].toString()))
                .with(VariantProperties.Y_ROT, Rotation.R90)
        )
        model.with(
            allDirectionFalse,
            Variant.variant().with(VariantProperties.MODEL, modelId)
        )
        model.with(
            Condition.condition()
                .term(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .term(HollowLogWithCuttingBlock.getProperty(it), true),
            Variant.variant()
                .with(VariantProperties.MODEL, modelId)
                .with(VariantProperties.X_ROT, Rotation.R270)
        )
        model.with(
            allDirectionFalse,
            Variant.variant().with(VariantProperties.MODEL, modelId)
        )
        model.with(
            Condition.condition()
                .term(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .term(HollowLogWithCuttingBlock.getProperty(it), true),
            Variant.variant()
                .with(VariantProperties.MODEL, modelId)
        )
        model.with(
            allDirectionFalse,
            Variant.variant().with(VariantProperties.MODEL, modelId)
        )
    }
    this.blockStateOutput.accept(model)
}


fun BlockModelGenerators.createLogPile(logPile: Block, log: Block, bamboo: Boolean = false) {
    val layer1 = this.parentedLogPileModel(logPile, log, bamboo, "_1")
    val layer2 = this.parentedLogPileModel(logPile, log, bamboo, "_2")
    val layer3 = this.parentedLogPileModel(logPile, log, bamboo, "_3")
    val hanging1 = this.parentedLogPileModel(logPile, log, bamboo, "_hanging_1")
    val hanging2 = this.parentedLogPileModel(logPile, log, bamboo, "_hanging_2")
    val hanging3 = this.parentedLogPileModel(logPile, log, bamboo, "_hanging_3")
    val full = this.parentedLogPileModel(logPile, log, bamboo)
    this.delegateItemModel(logPile, layer2)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(logPile)
            .with(
                PropertyDispatch.properties(
                    LogPileBlock.PILE_LAYERS,
                    BlockStateProperties.HANGING
                ).select(
                    1, false,
                    Variant.variant().with(VariantProperties.MODEL, layer1)
                ).select(
                    2, false,
                    Variant.variant().with(VariantProperties.MODEL, layer2)
                ).select(
                    3, false,
                    Variant.variant().with(VariantProperties.MODEL, layer3)
                ).select(
                    1, true,
                    Variant.variant().with(VariantProperties.MODEL, hanging1)
                ).select(
                    2, true,
                    Variant.variant().with(VariantProperties.MODEL, hanging2)
                ).select(
                    3, true,
                    Variant.variant().with(VariantProperties.MODEL, hanging3)
                ).select(
                    4, false,
                    Variant.variant().with(VariantProperties.MODEL, full)
                ).select(
                    4, true,
                    Variant.variant().with(VariantProperties.MODEL, full)
                )
            ).with(
                PropertyDispatch.property(BlockStateProperties.HORIZONTAL_AXIS)
                    .select(Direction.Axis.X, Variant.variant())
                    .select(Direction.Axis.Z, Variant.variant().with(VariantProperties.Y_ROT, Rotation.R90))
            )
    )
}

fun BlockModelGenerators.parentedLogPileModel(
    block: Block,
    textBlock: Block,
    bamboo: Boolean,
    parent: String = ""
): ResourceLocation {
    val pileModel = if (bamboo) id("block/parent/bamboo_pile") else id("block/parent/log_pile")
    return ModelTemplate(pileModel.suffix(parent).myb, Optional.empty(), SIDE, END)
        .create(
            block.model(parent), TextureMapping()
                .put(SIDE, textBlock.model())
                .put(END, textBlock.model("_top")),
            this.modelOutput
        )
}

fun BlockModelGenerators.createLeafPile(leafPile: Block, leaves: Block) {
    val pileModel = id("block/parent/leaf_pile")
    val layer1 = this.parentedModel(leafPile, leaves, pileModel)
    val layer2 = this.parentedModel(leafPile.model("_8"), leaves, pileModel.suffix("_8"))
    val layer3 = this.parentedModel(leafPile.model("_12"), leaves, pileModel.suffix("_12"))
    val hanging1 = this.parentedModel(leafPile.model("_hanging"), leaves, pileModel.suffix("_hanging"))
    val hanging2 = this.parentedModel(leafPile.model("_hanging_8"), leaves, pileModel.suffix("_hanging_8"))
    val hanging3 = this.parentedModel(leafPile.model("_hanging_12"), leaves, pileModel.suffix("_hanging_12"))
    val full = this.parentedModel(leafPile.model("_full"), leaves, pileModel.suffix("_full"))
    this.blockStateOutput.accept(
        MultiPartGenerator.multiPart(leafPile)
            .with(
                Condition.condition().term(LeafPileBlock.PILE_LAYERS, 1).term(BlockStateProperties.HANGING, false),
                Variant.variant().with(VariantProperties.MODEL, layer1)
            ).with(
                Condition.condition().term(LeafPileBlock.PILE_LAYERS, 2).term(BlockStateProperties.HANGING, false),
                Variant.variant().with(VariantProperties.MODEL, layer2)
            ).with(
                Condition.condition().term(LeafPileBlock.PILE_LAYERS, 3).term(BlockStateProperties.HANGING, false),
                Variant.variant().with(VariantProperties.MODEL, layer3)
            ).with(
                Condition.condition().term(LeafPileBlock.PILE_LAYERS, 1).term(BlockStateProperties.HANGING, true),
                Variant.variant().with(VariantProperties.MODEL, hanging1)
            ).with(
                Condition.condition().term(LeafPileBlock.PILE_LAYERS, 2).term(BlockStateProperties.HANGING, true),
                Variant.variant().with(VariantProperties.MODEL, hanging2)
            ).with(
                Condition.condition().term(LeafPileBlock.PILE_LAYERS, 3).term(BlockStateProperties.HANGING, true),
                Variant.variant().with(VariantProperties.MODEL, hanging3)
            ).with(
                Condition.condition().term(LeafPileBlock.PILE_LAYERS, 4),
                Variant.variant().with(VariantProperties.MODEL, full)
            )
    )
}

//for future reference since i still havent watched tutorials
//private fun leafPileHanging(num: String? = null): Identifier =
//    id("block/parent/leaf_pile_hanging${if (num != null) "_$num" else ""}")
fun BlockModelGenerators.registerCropWithParent(
    crop: Block,
    model: ResourceLocation,
    ageProperty: Property<Int>,
    vararg ageTextureIndices: Int
) {
    require(ageProperty.possibleValues.size == ageTextureIndices.size)
    val int2ObjectMap: Int2ObjectMap<ResourceLocation> = Int2ObjectOpenHashMap()
    val blockStateVariantMap = PropertyDispatch.property(ageProperty).generate { age: Int ->
        val stage = ageTextureIndices[age]
        val identifier = int2ObjectMap.computeIfAbsent(
            stage,
            Int2ObjectFunction { _: Int ->
                this.createSuffixedVariant(
                    crop,
                    "_stage$stage",
                    block(model, CROP)
                ) { id: ResourceLocation ->
                    TextureMapping.crop(
                        id
                    )
                }
            })
        Variant.variant().with(VariantProperties.MODEL, identifier)
    }
    this.createSimpleFlatItemModel(crop.asItem())
    this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(crop).with(blockStateVariantMap))
}

fun BlockModelGenerators.createMoonberryVine(block: Block) {
    skipAutoItemBlock(block)
    val allDirectionFalse = Condition.condition()
        .term(MultifaceBlock.getFaceProperty(Direction.NORTH), false)
        .term(MultifaceBlock.getFaceProperty(Direction.SOUTH), false)
        .term(MultifaceBlock.getFaceProperty(Direction.EAST), false)
        .term(MultifaceBlock.getFaceProperty(Direction.WEST), false)
        .term(MultifaceBlock.getFaceProperty(Direction.DOWN), false)
        .term(MultifaceBlock.getFaceProperty(Direction.UP), false)
    val model = MultiPartGenerator.multiPart(block)
    val directions = listOf(
        (Direction.NORTH to Rotation.R0),
        (Direction.EAST to Rotation.R90),
        (Direction.SOUTH to Rotation.R180),
        (Direction.WEST to Rotation.R270),
        (Direction.DOWN to Rotation.R90),
        (Direction.UP to Rotation.R270)
    )
    var modelId: ResourceLocation
    var axis: VariantProperty<Rotation>
    var variantRotation: Rotation

    directions.forEach { (direction, rotation) ->
        axis = VariantProperties.Y_ROT
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
                axis = VariantProperties.X_ROT
            }
            model.with(
                Condition.condition().term(MultifaceBlock.getFaceProperty(direction), true)
                    .term(MoonberryVineBlock.BERRIES, berries),
                Variant.variant()
                    .with(
                        VariantProperties.MODEL,
                        modelId
                    ).with(axis, variantRotation)
            )
        }
        model.with(
            allDirectionFalse,
            Variant.variant()
                .with(
                    VariantProperties.MODEL,
                    id("block/parent/moonberry_vine_0")
                ).with(axis, rotation)
        )
    }
    this.blockStateOutput.accept(model)
}

fun BlockModelGenerators.registerDnDCandelabra(candelabra: Block) =
    this.registerCandelabra(candelabra, true)

fun BlockModelGenerators.registerCandelabra(candelabra: Block, isDnD: Boolean = false) {
    if (candelabra !is CandelabraBlock) error("Provided blocks is not a CandelabraBlock!")
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(candelabra)
            .with(
                PropertyDispatch.property(BlockStateProperties.HORIZONTAL_AXIS)
                    .select(Direction.Axis.X, Variant.variant())
                    .select(Direction.Axis.Z, Variant.variant().with(VariantProperties.Y_ROT, Rotation.R90))
            )
            .with(this.candelabraStates(candelabra, isDnD))
    )
    this.delegateItemModel(candelabra, candelabra.model("_1"))
}

fun BlockModelGenerators.candelabraStates(
    candelabra: CandelabraBlock, isDnD: Boolean
): PropertyDispatch {
    val candle = candelabra.candle.prefixed(if (isDnD) "candle/" else "")

    val texture = TextureMapping.defaultTexture(candelabra)
        .put(CANDLE, candle)
        .put(TEXTURE, id("block/candelabra_iron"))
    val textureLit = TextureMapping.defaultTexture(candelabra)
        .put(CANDLE, candle.suffix("_lit"))
        .put(TEXTURE, id("block/candelabra_iron"))
    val models = listOf(CANDELABRA_1, CANDELABRA_2, CANDELABRA_3, CANDELABRA_4, CANDELABRA_5)

    return PropertyDispatch.properties(BlockStateProperties.LIT, CandelabraBlock.CANDLES).generate { isLit, candles ->
        val model = models[candles - 1]
        Variant.variant().with(
            VariantProperties.MODEL,
            if (isLit) model.createWithSuffix(candelabra, "_lit", textureLit, this.modelOutput)
            else model.create(candelabra, texture, this.modelOutput)
        )
    }
}

fun BlockModelGenerators.createBigScaffolding(scaffolding: Block) {
    // TODO make this be parented models instead
    val stable = ModelLocationUtils.getModelLocation(scaffolding, "_stable")
    val unstable = ModelLocationUtils.getModelLocation(scaffolding, "_unstable")
    delegateItemModel(scaffolding, stable)
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(scaffolding)
            .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.BOTTOM, unstable, stable))
    )
}

fun BlockModelGenerators.hangingFlora(block: Block, tinted: BlockModelGenerators.TintState){
    this.createSimpleFlatItemModel(block)
    val model = this.createSuffixedVariant(
        block,
        "_full",
        tinted.cross,
        TextureMapping::cross
    )
    val tip = this.createSuffixedVariant(
        block,
        "_tip",
        tinted.cross,
        TextureMapping::cross
    )
//    this.createDoubleBlock(block, model, tip)
}

fun BlockModelGenerators.rotatedLikeNetherrack(block: Block, modelProvider: TexturedModel.Provider) {
    val model = modelProvider.create(block, this.modelOutput)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(
            block, *arrayOf<Variant>(
                Variant.variant().with(VariantProperties.MODEL, model),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.X_ROT, Rotation.R90),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.X_ROT, Rotation.R180),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.X_ROT, Rotation.R270),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R90),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R90)
                    .with(VariantProperties.X_ROT, Rotation.R90),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R90)
                    .with(VariantProperties.X_ROT, Rotation.R180),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R90)
                    .with(VariantProperties.X_ROT, Rotation.R270),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R180),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R180)
                    .with(VariantProperties.X_ROT, Rotation.R90),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R180)
                    .with(VariantProperties.X_ROT, Rotation.R180),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R180)
                    .with(VariantProperties.X_ROT, Rotation.R270),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R270),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R270)
                    .with(VariantProperties.X_ROT, Rotation.R90),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R270)
                    .with(VariantProperties.X_ROT, Rotation.R180),
                Variant.variant().with(VariantProperties.MODEL, model)
                    .with(VariantProperties.Y_ROT, Rotation.R270)
                    .with(VariantProperties.X_ROT, Rotation.R270)
            )
        )
    )
}

fun BlockModelGenerators.createTrivialState(block: Block) {
    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, block.model()))
}

fun parentedItemModel(id: ResourceLocation) = ModelTemplate(Optional.of(id.withPrefix("item/")), Optional.empty())
fun BlockModelGenerators.registerParentedItemModel(block: Block) =
    this.delegateItemModel(block, block.model())

fun block(model: ResourceLocation, vararg requiredTextures: TextureSlot): ModelTemplate {
    return ModelTemplate(
        Optional.of(
            model
        ), Optional.empty(), *requiredTextures
    )
}

fun block(model: ResourceLocation, variant: String, vararg requiredTextures: TextureSlot): ModelTemplate {
    return ModelTemplate(
        Optional.of(
            model
        ), Optional.of(variant), *requiredTextures
    )
}

fun block(parent: String, vararg requiredTextures: TextureSlot): ModelTemplate {
    return ModelTemplate(
        Optional.of(
            id("block/$parent")
        ), Optional.empty(), *requiredTextures
    )
}

fun block(parent: String, variant: String, vararg requiredTextures: TextureSlot): ModelTemplate {
    return ModelTemplate(
        Optional.of(
            id("block/$parent")
        ), Optional.of(variant), *requiredTextures
    )
}

fun BlockModelGenerators.parentedModel(
    block: Block,
    textBlock: Block,
    parent: ResourceLocation
): ResourceLocation =
    ModelTemplate(parent.myb, Optional.empty(), ALL)
        .create(block.model(), TextureMapping().put(ALL, textBlock.model()), this.modelOutput)

fun BlockModelGenerators.parentedModel(
    block: ResourceLocation,
    textBlock: Block,
    parent: ResourceLocation
): ResourceLocation =
    ModelTemplate(parent.myb, Optional.empty(), ALL)
        .create(block, TextureMapping().put(ALL, textBlock.model()), this.modelOutput)

fun BlockModelGenerators.registerPrefixedItemModel(block: Block, prefix: String) {
    val item = block.asItem()
    ModelTemplates.FLAT_ITEM.create(
        ModelLocationUtils.getModelLocation(item), TextureMapping.layer0(item.prefixed(prefix)),
        this.modelOutput
    )
}

private
val <T : Any?> T.myb get() = Optional.ofNullable(this)

fun Block.model(): ResourceLocation = ModelLocationUtils.getModelLocation(this)
fun Block.model(str: String) = this.model().suffix(str)

fun Block.prefixed(str: String): ResourceLocation = this.id.withPrefix("block/$str")


fun Item.model(): ResourceLocation = ModelLocationUtils.getModelLocation(this)
fun Item.model(str: String) = this.model().suffix(str)
fun Item.prefixed(str: String): ResourceLocation = this.id.withPrefix("item/$str")

fun ResourceLocation.toVariant(): Variant = Variant.variant().with(VariantProperties.MODEL, this)

fun ResourceLocation.suffix(str: String) = ResourceLocation.fromNamespaceAndPath(this.namespace, "${this.path}$str")


val Item.id get() = BuiltInRegistries.ITEM.getKey(this)
val ItemLike.id get() = this.asItem().id
val Block.id get() = BuiltInRegistries.BLOCK.getKey(this)
