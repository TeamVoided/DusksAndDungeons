package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.BlockModelGenerators.createSimpleBlock
import net.minecraft.data.models.blockstates.MultiVariantGenerator
import net.minecraft.data.models.blockstates.PropertyDispatch
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.datagen.old.util.block_model.slabTinted
import org.teamvoided.dusks_and_dungeons.datagen.old.util.block_model.stairsTinted
import org.teamvoided.dusks_and_dungeons.datagen.old.util.block_model.wallTinted
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet

fun BlockModelGenerators.tintedBookshelf(bookshelf: Block, top: Block) {
    val texture = TextureMapping()
        .put(TextureSlot.SIDE, modelId(bookshelf))
        .put(TextureSlot.END, modelId(top))
        .put(DnDTextureSlots.OVERLAY, modelId(bookshelf, "_overlay"))
    val model = DnDModels.BOOKSHELF_TINTED.create(bookshelf, texture, modelOutput)

    blockStateOutput.accept(createSimpleBlock(bookshelf, model))
}

fun BlockModelGenerators.tintedCarpetPlate(plate: Block, wool: Block) {
    val up = DnDTexturedModels.TINTED_CARPET.get(wool).createWithSuffix(plate, "_up", modelOutput)
    val down = DnDTexturedModels.TINTED_CARPET_DOWN.get(wool).createWithSuffix(plate, "_down", modelOutput)
    delegateItemModel(plate, up)
    blockStateOutput.accept(BlockModelGenerators.createPressurePlate(plate, up, down))
}

fun BlockModelGenerators.tintedBrushableBlock(block: Block) {
    blockStateOutput.accept(
        MultiVariantGenerator.multiVariant(block).with(
            PropertyDispatch.property(BlockStateProperties.DUSTED).generate { dusted ->
                val suffix = "_$dusted"
                variant(
                    DnDModels.CUBE_ALL_TINTED.createWithSuffix(
                        block, suffix, TextureMapping().put(TextureSlot.ALL, modelId(block, suffix)), modelOutput
                    )
                )
            }
        )
    )
    delegateItemModel(block, modelId(block, "_0"))
}

fun BlockModelGenerators.tintedSet(set: AbstractBlockSet) {
    createTrivialBlock(set.parent, DnDTexturedModels.TINTED_CUBE)
    stairsTinted(set.stairs, set.parent)
    slabTinted(set.slab, set.parent)
    wallTinted(set.wall, set.parent)
}

fun BlockModelGenerators.tintedSet(set: AbstractBlockSet, texture: ResourceLocation) {
    val baseModel = DnDModels.CUBE_ALL_TINTED.create(set.parent, TextureMapping.cube(texture), modelOutput)
    blockStateOutput.accept(createSimpleBlock(set.parent, baseModel))
    stairsTinted(set.stairs, texture, texture, texture)
    slabTinted(set.slab, texture, texture, texture, set.parent)
    wallTinted(set.wall, texture)
}

fun BlockModelGenerators.tintedChiseledSandstone(chiseled: Block, endTexture: ResourceLocation) {
    val texture = TextureMapping()
        .put(TextureSlot.SIDE, modelId(chiseled))
        .put(TextureSlot.END, endTexture)
    val modelId = DnDModels.CUBE_COLUMN_TINTED.create(chiseled, texture, modelOutput)
    blockStateOutput.accept(createSimpleBlock(chiseled, modelId))
}

fun BlockModelGenerators.tintedSandstoneSet(set: AbstractBlockSet) {
    val block = set.parent
    val sideTexture = modelId(block)
    val topTexture = modelId(block, "_top")
    val bottomTexture = modelId(block, "_bottom")

    createTrivialBlock(block, DnDTexturedModels.TINTED_TOP_BOTTOM_WITH_WALL)
    stairsTinted(set.stairs, bottomTexture, sideTexture, topTexture)
    slabTinted(set.slab, bottomTexture, sideTexture, topTexture, block)
    wallTinted(set.wall, sideTexture)
}

fun BlockModelGenerators.tintedCutSandstoneSet(set: AbstractBlockSet, sandstone: Block) {
    val block = set.parent
    val sideTexture = modelId(block)
    val topTexture = modelId(sandstone, "_top")

    val texture = TextureMapping()
        .put(TextureSlot.SIDE, sideTexture)
        .put(TextureSlot.END, topTexture)
    val modelId = DnDModels.CUBE_COLUMN_TINTED.create(block, texture, modelOutput)
    blockStateOutput.accept(createSimpleBlock(block, modelId))
    stairsTinted(set.stairs, topTexture, sideTexture, topTexture)
    slabTinted(set.slab, topTexture, sideTexture, topTexture, block)
    wallTinted(set.wall, sideTexture)
}