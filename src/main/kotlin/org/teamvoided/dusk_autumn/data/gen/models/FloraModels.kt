package org.teamvoided.dusk_autumn.data.gen.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.*
import net.minecraft.state.property.Properties
import org.teamvoided.dusk_autumn.DusksAndDungeons
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.util.*

object FloraModels {
    fun register(gen: BlockStateModelGenerator) {
        gen.registerGalleryRose(DnDFloraBlocks.PAINTED_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerGoldenMushroomPlant(DnDFloraBlocks.GOLDEN_MUSHROOM)

        gen.registerSpiderlilly(DnDFloraBlocks.SPIDERLILY, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerFlowerbed2(DnDFloraBlocks.WHITE_PETALS, false)
        gen.registerFlowerbed2(DnDFloraBlocks.RED_PETALS, false)
        gen.registerFlowerbed2(DnDFloraBlocks.ORANGE_PETALS, false)
        gen.registerFlowerbed2(DnDFloraBlocks.BLUE_PETALS, false)
        gen.registerFlowerbed2(DnDFloraBlocks.WILD_PETALS, false, DusksAndDungeons.id("block/parent/wildflowerbed"))
        gen.registerFlowerbed(DnDFloraBlocks.CRIMSON_VIVIONS)
        gen.registerFlowerbed(DnDFloraBlocks.WARPED_VIVIONS)
        gen.registerTreeMushroom(DnDFloraBlocks.BROWN_TREE_FUNGUS, "parent/brown_tree_fungus")

        gen.registerDoubleBlock(DnDFloraBlocks.WILD_WHEAT, BlockStateModelGenerator.TintType.NOT_TINTED)
        gen.registerCrop(DnDFloraBlocks.GOLDEN_BEETROOTS, Properties.AGE_3, 0, 1, 2, 3)
        gen.registerCropWithParent(
            DnDFloraBlocks.MOONBERRY_VINELET, DusksAndDungeons.id("block/parent/floor_plant"),
            Properties.AGE_2, 0, 1, 2
        )
        gen.createMoonberryVine(DnDFloraBlocks.MOONBERRY_VINE)
        gen.registerItemModel(DnDItems.MOONBERRIES)

        gen.registerSmallPumpkins(
            DnDFloraBlocks.SMALL_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_PUMPKIN,
            Blocks.PUMPKIN
        )

        gen.registerPumpkinSet(
            DnDFloraBlocks.LANTERN_PUMPKIN,
            DnDFloraBlocks.CARVED_LANTERN_PUMPKIN,
            DnDFloraBlocks.GLOWING_LANTERN_PUMPKIN,
            DnDFloraBlocks.SMALL_LANTERN_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_LANTERN_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_LANTERN_PUMPKIN,
            DnDFloraBlocks.LANTERN_PUMPKIN_STEM
        )
        gen.registerPumpkinSet(
            DnDFloraBlocks.MOSSKIN_PUMPKIN,
            DnDFloraBlocks.CARVED_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.GLOWING_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.SMALL_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_MOSSKIN_PUMPKIN,
            DnDFloraBlocks.MOSSKIN_PUMPKIN_STEM
        )
        gen.registerPumpkinSet(
            DnDFloraBlocks.PALE_PUMPKIN,
            DnDFloraBlocks.CARVED_PALE_PUMPKIN,
            DnDFloraBlocks.GLOWING_PALE_PUMPKIN,
            DnDFloraBlocks.SMALL_PALE_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_PALE_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_PALE_PUMPKIN,
            DnDFloraBlocks.PALE_PUMPKIN_STEM
        )
        gen.registerPumpkinSet(
            DnDFloraBlocks.GLOOM_PUMPKIN,
            DnDFloraBlocks.CARVED_GLOOM_PUMPKIN,
            DnDFloraBlocks.GLOWING_GLOOM_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOOM_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_GLOOM_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_GLOOM_PUMPKIN,
            DnDFloraBlocks.GLOOM_PUMPKIN_STEM
        )
        gen.registerCornCrop(DnDFloraBlocks.CORN_CROP)
        gen.registerCorn(DnDFloraBlocks.CORN, DnDItems.CORN_STALK)
        gen.registerAxisRotated(
            DnDFloraBlocks.CORN_BLOCK,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN,
            TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL
        )
        gen.registerParentedItemModel(DnDFloraBlocks.CORN_SYRUP_BLOCK)

        gen.registerSingleton(
            DnDFloraBlocks.JOUNCESHROOM_BLOCK,
            TexturedModel.makeFactory(Texture::sideEnd, Models.CUBE_COLUMN)
        )

        gen.registerSingleton(
            DnDFloraBlocks.ROOT_BLOCK,
            TexturedModel.makeFactory(Texture::all, block("parent/cube_in_eighths", TextureKey.ALL))
        )
    }
}
