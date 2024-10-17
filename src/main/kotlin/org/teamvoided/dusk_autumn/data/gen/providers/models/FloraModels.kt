package org.teamvoided.dusk_autumn.data.gen.providers.models

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.*
import net.minecraft.state.property.Properties
import org.teamvoided.dusk_autumn.DusksAndDungeons
import org.teamvoided.dusk_autumn.init.DnDItems
import org.teamvoided.dusk_autumn.init.blocks.DnDFloraBlocks
import org.teamvoided.dusk_autumn.util.*

object FloraModels {
    fun floraModels(gen: BlockStateModelGenerator) {
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

        gen.registerPumpkins(
            DnDFloraBlocks.LANTERN_PUMPKIN,
            DnDFloraBlocks.CARVED_LANTERN_PUMPKIN,
            DnDFloraBlocks.GLOWING_LANTERN_PUMPKIN
        )
        gen.registerPumpkins(
            DnDFloraBlocks.PALE_PUMPKIN,
            DnDFloraBlocks.CARVED_PALE_PUMPKIN,
            DnDFloraBlocks.GLOWING_PALE_PUMPKIN
        )
        gen.registerPumpkins(
            DnDFloraBlocks.GLOOM_PUMPKIN,
            DnDFloraBlocks.CARVED_GLOOM_PUMPKIN,
            DnDFloraBlocks.GLOWING_GLOOM_PUMPKIN
        )
        gen.registerSmallPumpkins(
            DnDFloraBlocks.SMALL_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_PUMPKIN,
            Blocks.PUMPKIN
        )
        gen.registerSmallPumpkins(
            DnDFloraBlocks.SMALL_LANTERN_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_LANTERN_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_LANTERN_PUMPKIN,
            DnDFloraBlocks.LANTERN_PUMPKIN
        )
        gen.registerSmallPumpkins(
            DnDFloraBlocks.SMALL_PALE_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_PALE_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_PALE_PUMPKIN,
            DnDFloraBlocks.PALE_PUMPKIN
        )
        gen.registerSmallPumpkins(
            DnDFloraBlocks.SMALL_GLOOM_PUMPKIN,
            DnDFloraBlocks.SMALL_CARVED_GLOOM_PUMPKIN,
            DnDFloraBlocks.SMALL_GLOWING_GLOOM_PUMPKIN,
            DnDFloraBlocks.GLOOM_PUMPKIN
        )
        gen.registerCorn(DnDFloraBlocks.CORN, DnDItems.CORN_STALK)
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