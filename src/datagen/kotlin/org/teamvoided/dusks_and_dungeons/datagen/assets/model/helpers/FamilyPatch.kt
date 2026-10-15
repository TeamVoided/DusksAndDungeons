/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.BlockStateGenerator
import net.minecraft.data.models.blockstates.Condition
import net.minecraft.data.models.blockstates.MultiPartGenerator
import net.minecraft.data.models.blockstates.VariantProperties
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot.WALL
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.WallSide

fun BlockModelGenerators.fence(fenceBlock: Block, reference: Block) {
    val texture = TextureMapping.defaultTexture(reference)
    val post = ModelTemplates.FENCE_POST.create(fenceBlock, texture, modelOutput)
    val side = ModelTemplates.FENCE_SIDE.create(fenceBlock, texture, modelOutput)
    val inventory = ModelTemplates.FENCE_INVENTORY.create(fenceBlock, texture, modelOutput)

    blockStateOutput.accept(BlockModelGenerators.createFence(fenceBlock, post, side))
    delegateItemModel(fenceBlock, inventory)
}

fun BlockModelGenerators.createSign(particle: Block, sign: Block, wallSign: Block) {
    val mapping = TextureMapping.particle(particle)
    val model = ModelTemplates.PARTICLE_ONLY.create(sign, mapping, modelOutput)

    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(sign, model))
    blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSign, model))
    createSimpleFlatItemModel(sign.asItem())
    skipAutoItemBlock(wallSign)
}

// region Transparent
fun BlockModelGenerators.transparentWall(wall: Block, texture: Block) = transparentWall(wall, blockId(texture))

fun BlockModelGenerators.transparentWall(wall: Block, textureId: ResourceLocation = blockId(wall)) {
    val texture = TextureMapping().put(WALL, textureId)

    val center = DnDModels.TRANSPARENT_WALL_CENTER.create(wall, texture, modelOutput)
    val centerTall = DnDModels.TRANSPARENT_WALL_CENTER_TALL.create(wall, texture, modelOutput)
    val post = DnDModels.TRANSPARENT_WALL_POST.create(wall, texture, modelOutput)
    val side = DnDModels.TRANSPARENT_WALL_SIDE.create(wall, texture, modelOutput)
    val sideTall = DnDModels.TRANSPARENT_WALL_SIDE_TALL.create(wall, texture, modelOutput)
    val inventory = DnDModels.TRANSPARENT_WALL_INVENTORY.create(wall, texture, modelOutput)

    blockStateOutput.accept(createWallWithCenter(wall, center, centerTall, post, side, sideTall))
    delegateItemModel(wall, inventory)
}
// endregion


fun createWallWithCenter(
    block: Block,
    center: ResourceLocation, centerTall: ResourceLocation,
    post: ResourceLocation,
    side: ResourceLocation, sideTall: ResourceLocation,
): BlockStateGenerator {
    return MultiPartGenerator.multiPart(block)
        .with(
            Condition.and(
                Condition.condition().term(BlockStateProperties.UP, false),
                Condition.or(
                    Condition.condition().term(BlockStateProperties.NORTH_WALL, WallSide.TALL),
                    Condition.condition().term(BlockStateProperties.EAST_WALL, WallSide.TALL),
                    Condition.condition().term(BlockStateProperties.SOUTH_WALL, WallSide.TALL),
                    Condition.condition().term(BlockStateProperties.WEST_WALL, WallSide.TALL),
                )
            ),
            variant(centerTall)
        )
        .with(
            Condition.and(
                Condition.condition().term(BlockStateProperties.UP, false),
                Condition.condition().negatedTerm(BlockStateProperties.NORTH_WALL, WallSide.TALL),
                Condition.condition().negatedTerm(BlockStateProperties.EAST_WALL, WallSide.TALL),
                Condition.condition().negatedTerm(BlockStateProperties.SOUTH_WALL, WallSide.TALL),
                Condition.condition().negatedTerm(BlockStateProperties.WEST_WALL, WallSide.TALL),
            ),
            variant(center)
        )

        .with(
            Condition.condition().term(BlockStateProperties.UP, true),
            variant(post)
        )
        .with(
            Condition.condition().term(BlockStateProperties.NORTH_WALL, WallSide.LOW),
            variant(side)
                .with(VariantProperties.UV_LOCK, true)
        )
        .with(
            Condition.condition().term(BlockStateProperties.EAST_WALL, WallSide.LOW),
            variant(side)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                .with(VariantProperties.UV_LOCK, true)
        )
        .with(
            Condition.condition().term(BlockStateProperties.SOUTH_WALL, WallSide.LOW),
            variant(side)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                .with(VariantProperties.UV_LOCK, true)
        )
        .with(
            Condition.condition().term(BlockStateProperties.WEST_WALL, WallSide.LOW),
            variant(side)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                .with(VariantProperties.UV_LOCK, true)
        )
        .with(
            Condition.condition().term(BlockStateProperties.NORTH_WALL, WallSide.TALL),
            variant(sideTall)
                .with(VariantProperties.UV_LOCK, true)
        )
        .with(
            Condition.condition().term(BlockStateProperties.EAST_WALL, WallSide.TALL),
            variant(sideTall)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                .with(VariantProperties.UV_LOCK, true)
        )
        .with(
            Condition.condition().term(BlockStateProperties.SOUTH_WALL, WallSide.TALL),
            variant(sideTall)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                .with(VariantProperties.UV_LOCK, true)
        )
        .with(
            Condition.condition().term(BlockStateProperties.WEST_WALL, WallSide.TALL),
            variant(sideTall)
                .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                .with(VariantProperties.UV_LOCK, true)
        )
}