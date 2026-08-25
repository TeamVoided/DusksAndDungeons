package org.teamvoided.dusks_and_dungeons.datagen.old.util.block_model

import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.data.models.model.TextureSlot.*
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import org.teamvoided.dusks_and_dungeons.datagen.old.util.CUBE_COLUMN_HORIZONTAL_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.CUBE_COLUMN_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.FENCE_GATE_CLOSED_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.FENCE_GATE_OPEN_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.FENCE_GATE_WALL_CLOSED_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.FENCE_GATE_WALL_OPEN_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.FENCE_INVENTORY_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.FENCE_POST_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.FENCE_SIDE_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.INNER
import org.teamvoided.dusks_and_dungeons.datagen.old.util.SLAB_BOTTOM_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.SLAB_TOP_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.STAIRS_INNER_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.STAIRS_OUTER_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.STAIRS_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.WALL_INVENTORY_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.WALL_LOW_SIDE_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.WALL_POST_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.WALL_TALL_SIDE_TINTED
import org.teamvoided.dusks_and_dungeons.datagen.old.util.block
import org.teamvoided.dusks_and_dungeons.datagen.old.util.model
import org.teamvoided.dusks_and_dungeons.datagen.assets.model.helpers.DnDModels
import org.teamvoided.voidlib.consortium.block.set.AbstractBlockSet

val TINTED: TextureSlot = create("tinted")

fun BlockModelGenerators.planksTinted(
    planks: Block, stairs: Block, slab: Block, wall: Block, fence: Block, fenceGate: Block, button: Block, plate: Block,
) {
    createTrivialBlock(planks, TexturedModel.LEAVES)
    stairsTinted(stairs, planks)
    slabTinted(slab, planks)
    wallTinted(wall, planks)
    fenceTinted(fence, planks)
    fenceGateTinted(fenceGate, planks)
    buttonTinted(button, planks)
    pressurePlateTinted(plate, planks)
}

fun BlockModelGenerators.strippedTinted(strippedLog: Block, strippedWoodSet: AbstractBlockSet) = this.strippedTinted(
    strippedLog,
    strippedWoodSet.parent,
    strippedWoodSet.stairs,
    strippedWoodSet.slab,
    strippedWoodSet.wall
)

fun BlockModelGenerators.strippedTinted(
    strippedLog: Block, strippedWood: Block, stairs: Block, slab: Block, wall: Block,
) {
    this.columnWithHorizontalTinted(strippedLog)
    this.columnTinted(strippedWood, strippedLog)
    this.stairsTinted(stairs, strippedLog)
    this.slabTinted(slab, strippedLog)
    this.wallTinted(wall, strippedLog)
}

fun BlockModelGenerators.stairsTinted(block: Block, bottom: Block, side: Block = bottom, top: Block = bottom) =
    stairsTinted(block, bottom.model(), side.model(), top.model())

fun BlockModelGenerators.stairsTinted(
    block: Block, bottom: ResourceLocation, side: ResourceLocation, top: ResourceLocation,
) {
    val texture: TextureMapping = TextureMapping()
        .put(BOTTOM, bottom)
        .put(SIDE, side)
        .put(TOP, top)
    val id: ResourceLocation = STAIRS_INNER_TINTED.create(block, texture, this.modelOutput)
    val id2: ResourceLocation = STAIRS_TINTED.create(block, texture, this.modelOutput)
    val id3: ResourceLocation = STAIRS_OUTER_TINTED.create(block, texture, this.modelOutput)

    this.blockStateOutput.accept(BlockModelGenerators.createStairs(block, id, id2, id3))
    this.delegateItemModel(block, id2)
}


fun BlockModelGenerators.slabTinted(
    block: Block,
    bottom: Block,
    side: Block = bottom,
    top: Block = bottom,
    full: Block = side,
) = slabTinted(block, bottom.model(), side.model(), top.model(), full)

fun BlockModelGenerators.slabTinted(
    block: Block, bottom: ResourceLocation, side: ResourceLocation, top: ResourceLocation, full: Block,
) {
    val texture: TextureMapping = TextureMapping()
        .put(BOTTOM, bottom)
        .put(SIDE, side)
        .put(TOP, top)
    val id = SLAB_BOTTOM_TINTED.create(block, texture, this.modelOutput)
    val id2 = SLAB_TOP_TINTED.create(block, texture, this.modelOutput)
    val id3 = full.model()
    this.blockStateOutput.accept(BlockModelGenerators.createSlab(block, id, id2, id3))
    this.delegateItemModel(block, id)
}

fun BlockModelGenerators.wallTinted(block: Block, texture: Block) = wallTinted(block, texture.model())

fun BlockModelGenerators.wallTinted(wallBlock: Block, inId: ResourceLocation) {
    val texture = TextureMapping.defaultTexture(wallBlock.model()).put(WALL, inId)
    val id = WALL_POST_TINTED.create(wallBlock, texture, this.modelOutput)
    val id2 = WALL_LOW_SIDE_TINTED.create(wallBlock, texture, this.modelOutput)
    val id3 = WALL_TALL_SIDE_TINTED.create(wallBlock, texture, this.modelOutput)
    this.blockStateOutput.accept(BlockModelGenerators.createWall(wallBlock, id, id2, id3))
    this.delegateItemModel(wallBlock, WALL_INVENTORY_TINTED.create(wallBlock, texture, this.modelOutput))
}

fun BlockModelGenerators.fenceTinted(fenceBlock: Block, reference: Block) {
    val texture = TextureMapping.defaultTexture(reference)
    val id = FENCE_POST_TINTED.create(fenceBlock, texture, this.modelOutput)
    val id2 = FENCE_SIDE_TINTED.create(fenceBlock, texture, this.modelOutput)
    val id3 = FENCE_INVENTORY_TINTED.create(fenceBlock, texture, this.modelOutput)
    this.blockStateOutput.accept(BlockModelGenerators.createFence(fenceBlock, id, id2))
    this.delegateItemModel(fenceBlock, id3)
}


fun BlockModelGenerators.fenceGateTinted(fenceGateBlock: Block, reference: Block) {
    val texture = TextureMapping.defaultTexture(reference)
    val id1 = FENCE_GATE_OPEN_TINTED.create(fenceGateBlock, texture, this.modelOutput)
    val id2 = FENCE_GATE_CLOSED_TINTED.create(fenceGateBlock, texture, this.modelOutput)
    val id3 = FENCE_GATE_WALL_OPEN_TINTED.create(fenceGateBlock, texture, this.modelOutput)
    val id4 = FENCE_GATE_WALL_CLOSED_TINTED.create(fenceGateBlock, texture, this.modelOutput)
    this.blockStateOutput.accept(BlockModelGenerators.createFenceGate(fenceGateBlock, id1, id2, id3, id4, true))
    this.delegateItemModel(fenceGateBlock, id2)
}

fun BlockModelGenerators.buttonTinted(button: Block, reference: Block) {
    val texture = TextureMapping.defaultTexture(reference)
    val base = DnDModels.BUTTON_TINTED.create(button, texture, modelOutput)
    val pressed = DnDModels.BUTTON_PRESSED_TINTED.create(button, texture, modelOutput)
    blockStateOutput.accept(BlockModelGenerators.createButton(button, base, pressed))
    val inventory = DnDModels.BUTTON_INVENTORY_TINTED.create(button, texture, modelOutput)
    delegateItemModel(button, inventory)
}

fun BlockModelGenerators.pressurePlateTinted(plate: Block, reference: Block) {
    val texture = TextureMapping.defaultTexture(reference)
    val up = DnDModels.PRESSURE_PLATE_UP_TINTED.create(plate, texture, modelOutput)
    val down = DnDModels.PRESSURE_PLATE_DOWN_TINTED.create(plate, texture, modelOutput)
    blockStateOutput.accept(BlockModelGenerators.createPressurePlate(plate, up, down))
}

fun BlockModelGenerators.createTintedOrientableTrapdoor(block: Block) {
    val texture = TextureMapping.defaultTexture(block)
    val top = DnDModels.ORIENTABLE_TRAPDOOR_TOP_TINTED.create(block, texture, modelOutput)
    val bottom = DnDModels.ORIENTABLE_TRAPDOOR_BOTTOM_TINTED.create(block, texture, modelOutput)
    val open = DnDModels.ORIENTABLE_TRAPDOOR_OPEN_TINTED.create(block, texture, modelOutput)
    blockStateOutput.accept(BlockModelGenerators.createOrientableTrapdoor(block, top, bottom, open))
    delegateItemModel(block, bottom)
}

fun BlockModelGenerators.createTintedDoor(block: Block) {
    val texture = TextureMapping.door(block)
    val bottomLeft = DnDModels.DOOR_BOTTOM_LEFT_TINTED.create(block, texture, modelOutput)
    val bottomLeftOpen = DnDModels.DOOR_BOTTOM_LEFT_OPEN_TINTED.create(block, texture, modelOutput)
    val bottomRight = DnDModels.DOOR_BOTTOM_RIGHT_TINTED.create(block, texture, modelOutput)
    val bottomRightOpen = DnDModels.DOOR_BOTTOM_RIGHT_OPEN_TINTED.create(block, texture, modelOutput)
    val topLeft = DnDModels.DOOR_TOP_LEFT_TINTED.create(block, texture, modelOutput)
    val topLeftOpen = DnDModels.DOOR_TOP_LEFT_OPEN_TINTED.create(block, texture, modelOutput)
    val topRight = DnDModels.DOOR_TOP_RIGHT_TINTED.create(block, texture, modelOutput)
    val topRightOpen = DnDModels.DOOR_TOP_RIGHT_OPEN_TINTED.create(block, texture, modelOutput)
    createSimpleFlatItemModel(block.asItem())
    blockStateOutput.accept(
        BlockModelGenerators.createDoor(
            block,
            bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen,
            topLeft, topLeftOpen, topRight, topRightOpen
        )
    )
}

fun BlockModelGenerators.columnWithHorizontalTinted(block: Block) {
    val texture = TextureMapping.logColumn(block)
    val resourceLocation = CUBE_COLUMN_TINTED.create(block, texture, this.modelOutput)
    val resourceLocation2 = CUBE_COLUMN_HORIZONTAL_TINTED.create(block, texture, this.modelOutput)
    this.blockStateOutput.accept(
        BlockModelGenerators.createRotatedPillarWithHorizontalVariant(
            block,
            resourceLocation,
            resourceLocation2
        )
    )
}

fun BlockModelGenerators.columnTinted(block: Block, texture: Block) {
    val texture = TextureMapping.cube(texture)
    val resourceLocation = CUBE_COLUMN_TINTED.create(block, texture, this.modelOutput)
    this.blockStateOutput.accept(
        BlockModelGenerators.createAxisAlignedPillarBlock(
            block,
            resourceLocation
        )
    )
}

fun BlockModelGenerators.hollowTintedLog(
    hollowLog: Block,
    log: Block,
    stripped: Block,
) {
    val texture: TextureMapping = TextureMapping.defaultTexture(hollowLog)
        .put(SIDE, log.model())
        .put(END, log.model("_edge"))
        .put(TINTED, log.model("_top"))
        .put(INNER, stripped.model())
    Direction.Plane.HORIZONTAL.forEach {
        block("parent/tint/hollow_log_$it", SIDE, END, INNER)
            .createWithSuffix(hollowLog, "_$it", texture, this.modelOutput)
    }
    this.hollowBlock(hollowLog)
    this.delegateItemModel(
        hollowLog, block("parent/tint/hollow_log", SIDE, END, INNER)
            .create(hollowLog, texture, this.modelOutput)
    )
}

fun BlockModelGenerators.hollowTintedStrippedLog(
    hollowLog: Block,
    strippedLog: Block,
) {
    val texture: TextureMapping = TextureMapping.defaultTexture(hollowLog)
        .put(SIDE, strippedLog.model())
        .put(END, strippedLog.model("_top"))
        .put(INNER, strippedLog.model())
    Direction.Plane.HORIZONTAL.forEach {
        block("parent/tint/stripped_hollow_log_$it", SIDE, END, INNER)
            .createWithSuffix(hollowLog, "_$it", texture, this.modelOutput)
    }
    this.hollowBlock(hollowLog)
    this.delegateItemModel(
        hollowLog, block("parent/tint/stripped_hollow_log", SIDE, END, INNER)
            .create(hollowLog, texture, this.modelOutput)
    )
}