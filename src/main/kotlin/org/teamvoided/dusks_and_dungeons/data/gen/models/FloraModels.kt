package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object FloraModels {

    fun register(gen: BlockModelGenerators) {
        gen.registerGoldenMushroomPlant(DnDBlocks.GOLDEN_MUSHROOM)
        gen.registerMushroomBlockDiffInside(DnDBlocks.GOLDEN_MUSHROOM_BLOCK)
        gen.registerMushroomBlockDiffInside(
            DnDBlocks.GOLDEN_MUSHROOM_STEM_BLOCK, DnDBlocks.GOLDEN_MUSHROOM_BLOCK.model().suffix("_inside")
        )

        gen.rotatedLikeNetherrack(DnDBlocks.OVERGROWTH_BLOCK, TexturedModel.LEAVES)
        gen.createTrivialBlock(DnDBlocks.OVERGROWTH_LEAVES, TexturedModel.LEAVES)
        gen.hangingFlora(DnDBlocks.HANGING_OVERGROWTH,BlockModelGenerators.TintState.TINTED)

        gen.registerFlowerbed2(DnDBlocks.WHITE_PETALS, false)
        gen.registerFlowerbed2(DnDBlocks.RED_PETALS, false)
        gen.registerFlowerbed2(DnDBlocks.ORANGE_PETALS, false)
        gen.registerFlowerbed2(DnDBlocks.BLUE_PETALS, false)
        gen.registerFlowerbed2(DnDBlocks.COLD_WILDFLOWER, false, DusksAndDungeons.id("block/parent/wildflowerbed"))
        gen.createFlowerBed(DnDBlocks.CRIMSON_VIVIONS)
        gen.createFlowerBed(DnDBlocks.WARPED_VIVIONS)

        gen.createDoublePlant(DnDBlocks.WILD_WHEAT, BlockModelGenerators.TintState.NOT_TINTED)
        gen.createCropBlock(DnDBlocks.GOLDEN_BEETROOTS, BlockStateProperties.AGE_3, 0, 1, 2, 3)
        gen.registerCropWithParent(
            DnDBlocks.MOONBERRY_VINELET, DusksAndDungeons.id("block/parent/floor_plant"),
            BlockStateProperties.AGE_2, 0, 1, 2
        )
        gen.createMoonberryVine(DnDBlocks.MOONBERRY_VINE)
        gen.createSimpleFlatItemModel(DnDItems.MOONBERRIES)

        gen.registerSmallPumpkins(
            DnDBlocks.SMALL_PUMPKIN, DnDBlocks.SMALL_CARVED_PUMPKIN, DnDBlocks.SMALL_GLOWING_PUMPKIN, Blocks.PUMPKIN
        )

        gen.registerPumpkinSet(
            DnDBlocks.LANTERN_PUMPKIN, DnDBlocks.CARVED_LANTERN_PUMPKIN, DnDBlocks.GLOWING_LANTERN_PUMPKIN,
            DnDBlocks.SMALL_LANTERN_PUMPKIN, DnDBlocks.SMALL_CARVED_LANTERN_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_LANTERN_PUMPKIN, DnDBlocks.LANTERN_PUMPKIN_STEM
        )
        gen.registerPumpkinSet(
            DnDBlocks.MOSSKIN_PUMPKIN, DnDBlocks.CARVED_MOSSKIN_PUMPKIN, DnDBlocks.GLOWING_MOSSKIN_PUMPKIN,
            DnDBlocks.SMALL_MOSSKIN_PUMPKIN, DnDBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN, DnDBlocks.MOSSKIN_PUMPKIN_STEM
        )
        gen.registerPumpkinSet(
            DnDBlocks.PALE_PUMPKIN, DnDBlocks.CARVED_PALE_PUMPKIN, DnDBlocks.GLOWING_PALE_PUMPKIN,
            DnDBlocks.SMALL_PALE_PUMPKIN, DnDBlocks.SMALL_CARVED_PALE_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_PALE_PUMPKIN, DnDBlocks.PALE_PUMPKIN_STEM
        )
        gen.registerPumpkinSet(
            DnDBlocks.GLOOM_PUMPKIN, DnDBlocks.CARVED_GLOOM_PUMPKIN, DnDBlocks.GLOWING_GLOOM_PUMPKIN,
            DnDBlocks.SMALL_GLOOM_PUMPKIN, DnDBlocks.SMALL_CARVED_GLOOM_PUMPKIN,
            DnDBlocks.SMALL_GLOWING_GLOOM_PUMPKIN, DnDBlocks.GLOOM_PUMPKIN_STEM
        )
        gen.registerCornCrop(DnDBlocks.CORN_CROP)
        gen.registerCorn(DnDBlocks.CORN, DnDItems.CORN_STALK)
        gen.createRotatedPillarWithHorizontalVariant(
            DnDBlocks.CORN_BLOCK,
            TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT
        )
        gen.registerParentedItemModel(DnDBlocks.CORN_SYRUP_BLOCK)

        gen.createTrivialBlock(
            DnDBlocks.ROOT_BLOCK,
            TexturedModel.createDefault(TextureMapping::cube, block("parent/cube_in_eighths", TextureSlot.ALL))
        )
    }
}
