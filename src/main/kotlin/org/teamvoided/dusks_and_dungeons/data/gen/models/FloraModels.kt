package org.teamvoided.dusks_and_dungeons.data.gen.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.*
import net.minecraft.state.property.Properties
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDItems
import org.teamvoided.dusks_and_dungeons.util.datagen.*

object FloraModels {
    fun register(gen: BlockStateModelGenerator) {
        gen.registerGalleryRose(DnDBlocks.PAINTED_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerGoldenMushroomPlant(DnDBlocks.GOLDEN_MUSHROOM)
        gen.registerMushroomBlockDiffInside(DnDBlocks.GOLDEN_MUSHROOM_BLOCK)
        gen.registerMushroomBlockDiffInside(
            DnDBlocks.GOLDEN_MUSHROOM_STEM_BLOCK, DnDBlocks.GOLDEN_MUSHROOM_BLOCK.model().suffix("_inventory")
        )

        gen.registerSpiderlilly(DnDBlocks.SPIDERLILY, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerFlowerbed2(DnDBlocks.WHITE_PETALS, false)
        gen.registerFlowerbed2(DnDBlocks.RED_PETALS, false)
        gen.registerFlowerbed2(DnDBlocks.ORANGE_PETALS, false)
        gen.registerFlowerbed2(DnDBlocks.BLUE_PETALS, false)
        gen.registerFlowerbed2(DnDBlocks.WILD_PETALS, false, DusksAndDungeons.id("block/parent/wildflowerbed"))
        gen.registerFlowerbed(DnDBlocks.CRIMSON_VIVIONS)
        gen.registerFlowerbed(DnDBlocks.WARPED_VIVIONS)
        gen.registerTreeMushroom(DnDBlocks.BROWN_TREE_FUNGUS, "parent/brown_tree_fungus")

        gen.registerDoubleBlock(DnDBlocks.WILD_WHEAT, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerCrop(DnDBlocks.GOLDEN_BEETROOTS, Properties.AGE_3, 0, 1, 2, 3)
        gen.registerCropWithParent(
            DnDBlocks.MOONBERRY_VINELET, DusksAndDungeons.id("block/parent/floor_plant"),
            Properties.AGE_2, 0, 1, 2
        )
        gen.createMoonberryVine(DnDBlocks.MOONBERRY_VINE)
        gen.registerItemModel(DnDItems.MOONBERRIES)

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
        gen.registerAxisRotated(
            DnDBlocks.CORN_BLOCK,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerParentedItemModel(DnDBlocks.CORN_SYRUP_BLOCK)

        gen.registerSingleton(
            DnDBlocks.JOUNCESHROOM_BLOCK, TexturedModel.makeFactory(Texture::sideEnd, Models.CUBE_COLUMN)
        )

        gen.registerSingleton(
            DnDBlocks.ROOT_BLOCK,
            TexturedModel.makeFactory(Texture::all, block("parent/cube_in_eighths", TextureKey.ALL))
        )
    }
}
