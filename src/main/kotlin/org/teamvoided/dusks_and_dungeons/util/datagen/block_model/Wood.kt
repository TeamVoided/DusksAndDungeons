package org.teamvoided.dusks_and_dungeons.util.datagen.block_model

import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.*
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.data.models.model.TextureSlot.END
import net.minecraft.data.models.model.TextureSlot.SIDE
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.block.CuttableHollowLogBlock
import org.teamvoided.dusks_and_dungeons.block.LogPileBlock
import org.teamvoided.dusks_and_dungeons.util.datagen.INNER
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

fun BlockModelGenerators.parentedLogPileModel(
    block: Block,
    textBlock: Block,
    prefix: String = "log",
    parent: String = ""
): ResourceLocation {
    val pileModel = id("block/parent/$prefix" + "_pile")
    return ModelTemplate(pileModel.suffix(parent).myb, Optional.empty(), SIDE, END)
        .create(
            block.model(parent), TextureMapping()
                .put(SIDE, textBlock.model())
                .put(END, textBlock.model("_top")),
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
        .term(CuttableHollowLogBlock.NORTH, false)
        .term(CuttableHollowLogBlock.SOUTH, false)
        .term(CuttableHollowLogBlock.EAST, false)
        .term(CuttableHollowLogBlock.WEST, false)
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
                .term(CuttableHollowLogBlock.getProperty(it), true),
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
                .term(CuttableHollowLogBlock.getProperty(it), true),
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
                .term(CuttableHollowLogBlock.getProperty(it), true),
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