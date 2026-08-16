package org.teamvoided.dusks_and_dungeons.util.datagen.block_model

import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.*
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.WallSide
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.LogPileBlock
import org.teamvoided.dusks_and_dungeons.util.datagen.block
import org.teamvoided.dusks_and_dungeons.util.datagen.model
import org.teamvoided.dusks_and_dungeons.util.datagen.suffix
import java.util.*


fun BlockModelGenerators.createLogPile(logPile: Block, log: Block, prefix: String = "log") {
    val layer1 = this.parentedLogPileModel(logPile, log, prefix, "_1")
    val layer2 = this.parentedLogPileModel(logPile, log, prefix, "_2")
    val layer3 = this.parentedLogPileModel(logPile, log, prefix, "_3")
    val layer4 = this.parentedLogPileModel(logPile, log, prefix, "_4")
    val full = this.parentedLogPileModel(logPile, log, prefix)
    val model = MultiPartGenerator.multiPart(logPile)
    listOf(Direction.Axis.X, Direction.Axis.Z).forEach { dir ->
        val rot = if (dir == Direction.Axis.X) Rotation.R0 else Rotation.R90
        model.with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 4)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot).with(VariantProperties.MODEL, full)
        )

        //layer 1
        model.with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 1)
                .term(BlockStateProperties.HANGING, false)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer1)
        ).with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 2)
                .term(BlockStateProperties.HANGING, false)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer1)
        ).with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 3)
                .term(BlockStateProperties.HANGING, false)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer1)
        )

        //layer 2
        model.with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 2)
                .term(BlockStateProperties.HANGING, false)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer2)
        ).with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 3)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer2)
        )
        //layer 3
        model.with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 2)
                .term(BlockStateProperties.HANGING, true)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer3)
        ).with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 3)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer3)
        )

        //layer 4
        model.with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 1)
                .term(BlockStateProperties.HANGING, true)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer4)
        ).with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 2)
                .term(BlockStateProperties.HANGING, true)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer4)
        ).with(
            Condition.condition()
                .term(LogPileBlock.PILE_LAYERS, 3)
                .term(BlockStateProperties.HANGING, true)
                .term(BlockStateProperties.HORIZONTAL_AXIS, dir),
            Variant.variant().with(VariantProperties.Y_ROT, rot)
                .with(VariantProperties.MODEL, layer4)
        )
    }
    this.delegateItemModel(logPile, this.parentedLogPileModel(logPile, log, prefix, "_inventory"))
    this.blockStateOutput.accept(model)
}


fun createWall(
    block: Block,
    resourceLocation: ResourceLocation,
    resourceLocation2: ResourceLocation,
    resourceLocation3: ResourceLocation
): BlockStateGenerator {
    return MultiPartGenerator.multiPart(block).with(
        Condition.condition().term(BlockStateProperties.UP, true),
        Variant.variant().with(VariantProperties.MODEL, resourceLocation)
    ).with(
        Condition.condition().term(BlockStateProperties.NORTH_WALL, WallSide.LOW),
        Variant.variant().with(VariantProperties.MODEL, resourceLocation2)
            .with(VariantProperties.UV_LOCK, true)
    ).with(
        Condition.condition().term(BlockStateProperties.EAST_WALL, WallSide.LOW),
        Variant.variant().with(VariantProperties.MODEL, resourceLocation2)
            .with(VariantProperties.Y_ROT, Rotation.R90).with(VariantProperties.UV_LOCK, true)
    ).with(
        Condition.condition().term(BlockStateProperties.SOUTH_WALL, WallSide.LOW),
        Variant.variant().with(VariantProperties.MODEL, resourceLocation2)
            .with(VariantProperties.Y_ROT, Rotation.R180).with(VariantProperties.UV_LOCK, true)
    ).with(
        Condition.condition().term(BlockStateProperties.WEST_WALL, WallSide.LOW),
        Variant.variant().with(VariantProperties.MODEL, resourceLocation2)
            .with(VariantProperties.Y_ROT, Rotation.R270).with(VariantProperties.UV_LOCK, true)
    ).with(
        Condition.condition().term(BlockStateProperties.NORTH_WALL, WallSide.TALL),
        Variant.variant().with(VariantProperties.MODEL, resourceLocation3)
            .with(VariantProperties.UV_LOCK, true)
    ).with(
        Condition.condition().term(BlockStateProperties.EAST_WALL, WallSide.TALL),
        Variant.variant().with(VariantProperties.MODEL, resourceLocation3)
            .with(VariantProperties.Y_ROT, Rotation.R90).with(VariantProperties.UV_LOCK, true)
    ).with(
        Condition.condition().term(BlockStateProperties.SOUTH_WALL, WallSide.TALL),
        Variant.variant().with(VariantProperties.MODEL, resourceLocation3)
            .with(VariantProperties.Y_ROT, Rotation.R180).with(VariantProperties.UV_LOCK, true)
    ).with(
        Condition.condition().term(BlockStateProperties.WEST_WALL, WallSide.TALL),
        Variant.variant().with(VariantProperties.MODEL, resourceLocation3)
            .with(VariantProperties.Y_ROT, Rotation.R270).with(VariantProperties.UV_LOCK, true)
    )
}

fun BlockModelGenerators.parentedLogPileModel(
    block: Block,
    textBlock: Block,
    prefix: String = "log",
    parent: String = ""
): ResourceLocation {
    val pileModel = id("block/parent/$prefix" + "_pile")
    return ModelTemplate(pileModel.suffix(parent).myb, Optional.empty(), TextureSlot.SIDE, TextureSlot.END)
        .create(
            block.model(parent), TextureMapping()
                .put(TextureSlot.SIDE, textBlock.model())
                .put(TextureSlot.END, textBlock.model("_top")),
            this.modelOutput
        )
}

fun BlockModelGenerators.overgrowthBush(block: Block) {
    val texture = TextureMapping()
        .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
        .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"))
        .put(TextureSlot.PLANT, TextureMapping.getBlockTexture(block, "_plant"))
        .put(TextureSlot.STEM, TextureMapping.getBlockTexture(block, "_plant_stem"))
    val identifier =
        block(
            "parent/foliage/template_tinted_bush",
            TextureSlot.TOP,
            TextureSlot.SIDE,
            TextureSlot.PLANT,
            TextureSlot.STEM
        )
            .create(block, texture, this.modelOutput)
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier))
            .with(createDownDefaultRotationStates())
    )
}

fun BlockModelGenerators.hangingOvergrowth(block: Block) {
    val full: ResourceLocation =
        this.createSuffixedVariant(block, "_full", ModelTemplates.TINTED_CROSS) { TextureMapping.cross(it) }
    val tip: ResourceLocation =
        this.createSuffixedVariant(block, "_tip", ModelTemplates.TINTED_CROSS) { TextureMapping.cross(it) }
    this.createSimpleFlatItemModel(block, "_full")
    this.blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block)
            .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.BOTTOM, tip, full))
    )
}


fun createDownDefaultRotationStates(): PropertyDispatch {
    return PropertyDispatch.property(BlockStateProperties.FACING)
        .select(Direction.DOWN, Variant.variant())
        .select(Direction.UP, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
        .select(
            Direction.NORTH, Variant.variant()
                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
        )
        .select(Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
        .select(
            Direction.WEST, Variant.variant()
                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
        )
        .select(
            Direction.EAST, Variant.variant()
                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
        )
}

private val <T : Any?> T.myb get() = Optional.ofNullable(this)