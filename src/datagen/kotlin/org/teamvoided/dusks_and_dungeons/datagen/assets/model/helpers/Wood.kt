package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.BlockModelGenerators.createHorizontalFacingDispatch
import net.minecraft.data.models.BlockModelGenerators.createSimpleBlock
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.blockstates.Variant
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.blockstates.VariantProperties.MODEL
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.block.SconceBlock


fun BlockModelGenerators.bookshelf(bookshelf: Block, top: Block) {
    val texture = TextureMapping.column(
        TextureMapping.getBlockTexture(bookshelf),
        TextureMapping.getBlockTexture(top)
    )
    val model = ModelTemplates.CUBE_COLUMN.create(bookshelf, texture, modelOutput)

    blockStateOutput.accept(createSimpleBlock(bookshelf, model))
}

fun BlockModelGenerators.scone(block: Block) {
    val texture = TextureMapping()
        .put(SIDE, TextureMapping.getBlockTexture(block))
        .put(TOP, TextureMapping.getBlockTexture(block, "_top"))
    val baseModel = DnDModels.SCONCE.create(block, texture, modelOutput)

    val properties = PropertyDispatch.properties(HorizontalDirectionalBlock.FACING, SconceBlock.HANGING)
    val rot = listOf(Rotation.R90, Rotation.R180, Rotation.R270, Rotation.R0)
    listOf(
        Direction.EAST,
        Direction.SOUTH,
        Direction.WEST,
        Direction.NORTH,
    ).forEachIndexed { idx, dir ->
        listOf(false, true).forEach { hang ->
            val variant = Variant.variant()
            if (hang) variant.with(VariantProperties.X_ROT, Rotation.R180)
            if ((idx != 3 && !hang) || (idx != 1 && hang))
                variant.with(VariantProperties.Y_ROT, rot[if (hang) (2 + idx) % 4 else idx])
            properties.select(dir, hang, variant)
        }
    }

    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block, Variant.variant().with(MODEL, baseModel)).with(properties)
    )

    //I was so proud of this but it didnt work T-T
    //blockStateOutput.accept(
    //    MultiVariantGenerator
    //        .multiVariant(block, Variant.variant().with(MODEL, baseModel))
    //        .with(verticalFlip(BlockStateProperties.HANGING))
    //        .with(createHorizontalFacingDispatch())
    //)
}
