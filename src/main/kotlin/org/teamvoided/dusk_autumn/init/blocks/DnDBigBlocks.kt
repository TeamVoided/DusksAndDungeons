package org.teamvoided.dusk_autumn.init.blocks

import net.minecraft.block.AbstractBlock.Settings.copy
import net.minecraft.block.Blocks.*
import net.minecraft.block.MapColor
import net.minecraft.sound.BlockSoundGroup
import org.teamvoided.dusk_autumn.block.big.BigChainBlock
import org.teamvoided.dusk_autumn.block.big.BigLanternBlock
import org.teamvoided.dusk_autumn.block.big.BigRedstoneLanternBlock
import org.teamvoided.dusk_autumn.block.big.BigLanternWithSpiralBlock
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.util.*

object DnDBigBlocks {
    val BIG_CHAIN = DnDBlocks.register("big_chain", BigChainBlock(copy(CHAIN).sounds(bigChainSound)).cutout().pickaxe())
    val BIG_LANTERN =
        DnDBlocks.register("big_lantern", BigLanternBlock(copy(LANTERN).sounds(bigLanternSound)).pickaxe())
    val BIG_REDSTONE_LANTERN = DnDBlocks.register(
        "big_redstone_lantern",
        BigRedstoneLanternBlock(copy(LANTERN).sounds(bigLanternSound).luminance(luminanceOf(8))).pickaxe()
    )
    val BIG_SOUL_LANTERN =
        DnDBlocks.register("big_soul_lantern", BigLanternBlock(copy(SOUL_LANTERN).sounds(bigLanternSound)).pickaxe())
    val BIG_CELESTAL_CHAIN = DnDBlocks.register(
        "big_celestal_chain",
        BigChainBlock(copy(CHAIN).sounds(BlockSoundGroup.BLOCK_VAULT_BREAK)).cutout().pickaxe()
    )
    val BIG_MOON_LANTERN = DnDBlocks.register(
        "big_moon_lantern",
        BigLanternWithSpiralBlock(
            0xE01638,
            0xCC3D78,
            copy(BIG_SOUL_LANTERN).sounds(BlockSoundGroup.BLOCK_TRIAL_SPAWNER_BREAK)
        ).pickaxe()
    )
    val BIG_EARTH_LANTERN = DnDBlocks.register(
        "big_earth_lantern",
        BigLanternWithSpiralBlock(
            0xE5AE16,
            0xE5B816,
            copy(BIG_MOON_LANTERN)
        ).pickaxe()
    )
    val BIG_COMET_LANTERN = DnDBlocks.register(
        "big_comet_lantern",
        BigLanternWithSpiralBlock(
            0xE57716,
            0xCC6C28,
            copy(BIG_MOON_LANTERN)
        ).pickaxe()
    )
    val BIG_SUN_LANTERN = DnDBlocks.register(
        "big_sun_lantern",
        BigLanternWithSpiralBlock(
            0x16E5E5,
            0x1470CC,
            copy(BIG_MOON_LANTERN)
        ).pickaxe()
    )
    val BIG_STAR_LANTERN = DnDBlocks.register(
        "big_star_lantern",
        BigLanternWithSpiralBlock(
            0x7E16E5,
            0xE52DE5,
            copy(BIG_MOON_LANTERN)
        ).pickaxe()
    )
    val BIG_NEBULAE_LANTERN = DnDBlocks.register(
        "big_nebulae_lantern",
        BigLanternWithSpiralBlock(
            0x24CADA,
            0x52D973,
            copy(BIG_MOON_LANTERN)
        ).pickaxe()
    )
    val BIG_ECLIPSE_LANTERN = DnDBlocks.register(
        "big_eclipse_lantern",
        BigLanternWithSpiralBlock(
            0xE5E5E5,
            0xBFBFBF,
            copy(BIG_MOON_LANTERN)
        ).pickaxe()
    )

    val BIG_CANDLE = DnDBlocks.register("big_candle", bigCandleOf(MapColor.SAND))
    val BIG_WHITE_CANDLE = DnDBlocks.register("big_white_candle", bigCandleOf(MapColor.WOOL))
    val BIG_LIGHT_GRAY_CANDLE = DnDBlocks.register("big_light_gray_candle", bigCandleOf(MapColor.LIGHT_GRAY))
    val BIG_GRAY_CANDLE = DnDBlocks.register("big_gray_candle", bigCandleOf(MapColor.GRAY))
    val BIG_BLACK_CANDLE = DnDBlocks.register("big_black_candle", bigCandleOf(MapColor.BLACK))
    val BIG_BROWN_CANDLE = DnDBlocks.register("big_brown_candle", bigCandleOf(MapColor.BROWN))
    val BIG_RED_CANDLE = DnDBlocks.register("big_red_candle", bigCandleOf(MapColor.RED))
    val BIG_ORANGE_CANDLE = DnDBlocks.register("big_orange_candle", bigCandleOf(MapColor.ORANGE))
    val BIG_YELLOW_CANDLE = DnDBlocks.register("big_yellow_candle", bigCandleOf(MapColor.YELLOW))
    val BIG_LIME_CANDLE = DnDBlocks.register("big_lime_candle", bigCandleOf(MapColor.LIME))
    val BIG_GREEN_CANDLE = DnDBlocks.register("big_green_candle", bigCandleOf(MapColor.GREEN))
    val BIG_CYAN_CANDLE = DnDBlocks.register("big_cyan_candle", bigCandleOf(MapColor.CYAN))
    val BIG_LIGHT_BLUE_CANDLE = DnDBlocks.register("big_light_blue_candle", bigCandleOf(MapColor.LIGHT_BLUE))
    val BIG_BLUE_CANDLE = DnDBlocks.register("big_blue_candle", bigCandleOf(MapColor.BLUE))
    val BIG_PURPLE_CANDLE = DnDBlocks.register("big_purple_candle", bigCandleOf(MapColor.PURPLE))
    val BIG_MAGENTA_CANDLE = DnDBlocks.register("big_magenta_candle", bigCandleOf(MapColor.MAGENTA))
    val BIG_PINK_CANDLE = DnDBlocks.register("big_pink_candle", bigCandleOf(MapColor.PINK))

    // soul candles
    val SOUL_CANDLE = DnDBlocks.register("soul_candle", soulCandleOf(MapColor.SAND))
    val WHITE_SOUL_CANDLE = DnDBlocks.register("white_soul_candle", soulCandleOf(MapColor.WOOL))
    val LIGHT_GRAY_SOUL_CANDLE = DnDBlocks.register("light_gray_soul_candle", soulCandleOf(MapColor.LIGHT_GRAY))
    val GRAY_SOUL_CANDLE = DnDBlocks.register("gray_soul_candle", soulCandleOf(MapColor.GRAY))
    val BLACK_SOUL_CANDLE = DnDBlocks.register("black_soul_candle", soulCandleOf(MapColor.BLACK))
    val BROWN_SOUL_CANDLE = DnDBlocks.register("brown_soul_candle", soulCandleOf(MapColor.BROWN))
    val RED_SOUL_CANDLE = DnDBlocks.register("red_soul_candle", soulCandleOf(MapColor.RED))
    val ORANGE_SOUL_CANDLE = DnDBlocks.register("orange_soul_candle", soulCandleOf(MapColor.ORANGE))
    val YELLOW_SOUL_CANDLE = DnDBlocks.register("yellow_soul_candle", soulCandleOf(MapColor.YELLOW))
    val LIME_SOUL_CANDLE = DnDBlocks.register("lime_soul_candle", soulCandleOf(MapColor.LIME))
    val GREEN_SOUL_CANDLE = DnDBlocks.register("green_soul_candle", soulCandleOf(MapColor.GREEN))
    val CYAN_SOUL_CANDLE = DnDBlocks.register("cyan_soul_candle", soulCandleOf(MapColor.CYAN))
    val LIGHT_BLUE_SOUL_CANDLE = DnDBlocks.register("light_blue_soul_candle", soulCandleOf(MapColor.LIGHT_BLUE))
    val BLUE_SOUL_CANDLE = DnDBlocks.register("blue_soul_candle", soulCandleOf(MapColor.BLUE))
    val PURPLE_SOUL_CANDLE = DnDBlocks.register("purple_soul_candle", soulCandleOf(MapColor.PURPLE))
    val MAGENTA_SOUL_CANDLE = DnDBlocks.register("magenta_soul_candle", soulCandleOf(MapColor.MAGENTA))
    val PINK_SOUL_CANDLE = DnDBlocks.register("pink_soul_candle", soulCandleOf(MapColor.PINK))
    val BIG_SOUL_CANDLE = DnDBlocks.register("big_soul_candle", bigSoulCandleOf(MapColor.SAND))
    val BIG_WHITE_SOUL_CANDLE = DnDBlocks.register("big_white_soul_candle", bigSoulCandleOf(MapColor.WOOL))
    val BIG_LIGHT_GRAY_SOUL_CANDLE =
        DnDBlocks.register("big_light_gray_soul_candle", bigSoulCandleOf(MapColor.LIGHT_GRAY))
    val BIG_GRAY_SOUL_CANDLE = DnDBlocks.register("big_gray_soul_candle", bigSoulCandleOf(MapColor.GRAY))
    val BIG_BLACK_SOUL_CANDLE = DnDBlocks.register("big_black_soul_candle", bigSoulCandleOf(MapColor.BLACK))
    val BIG_BROWN_SOUL_CANDLE = DnDBlocks.register("big_brown_soul_candle", bigSoulCandleOf(MapColor.BROWN))
    val BIG_RED_SOUL_CANDLE = DnDBlocks.register("big_red_soul_candle", bigSoulCandleOf(MapColor.RED))
    val BIG_ORANGE_SOUL_CANDLE = DnDBlocks.register("big_orange_soul_candle", bigSoulCandleOf(MapColor.ORANGE))
    val BIG_YELLOW_SOUL_CANDLE = DnDBlocks.register("big_yellow_soul_candle", bigSoulCandleOf(MapColor.YELLOW))
    val BIG_LIME_SOUL_CANDLE = DnDBlocks.register("big_lime_soul_candle", bigSoulCandleOf(MapColor.LIME))
    val BIG_GREEN_SOUL_CANDLE = DnDBlocks.register("big_green_soul_candle", bigSoulCandleOf(MapColor.GREEN))
    val BIG_CYAN_SOUL_CANDLE = DnDBlocks.register("big_cyan_soul_candle", bigSoulCandleOf(MapColor.CYAN))
    val BIG_LIGHT_BLUE_SOUL_CANDLE =
        DnDBlocks.register("big_light_blue_soul_candle", bigSoulCandleOf(MapColor.LIGHT_BLUE))
    val BIG_BLUE_SOUL_CANDLE = DnDBlocks.register("big_blue_soul_candle", bigSoulCandleOf(MapColor.BLUE))
    val BIG_PURPLE_SOUL_CANDLE = DnDBlocks.register("big_purple_soul_candle", bigSoulCandleOf(MapColor.PURPLE))
    val BIG_MAGENTA_SOUL_CANDLE = DnDBlocks.register("big_magenta_soul_candle", bigSoulCandleOf(MapColor.MAGENTA))
    val BIG_PINK_SOUL_CANDLE = DnDBlocks.register("big_pink_soul_candle", bigSoulCandleOf(MapColor.PINK))

    val BIG_CANDLE_CAKE = DnDBlocks.registerNoItem("big_candle_cake", bigCandleCakeOf(BIG_CANDLE, CANDLE_CAKE))
    val BIG_WHITE_CANDLE_CAKE = DnDBlocks.registerNoItem("big_white_candle_cake", bigCandleCakeOf(BIG_WHITE_CANDLE))
    val BIG_LIGHT_GRAY_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_light_gray_candle_cake", bigCandleCakeOf(BIG_LIGHT_GRAY_CANDLE))
    val BIG_GRAY_CANDLE_CAKE = DnDBlocks.registerNoItem("big_gray_candle_cake", bigCandleCakeOf(BIG_GRAY_CANDLE))
    val BIG_BLACK_CANDLE_CAKE = DnDBlocks.registerNoItem("big_black_candle_cake", bigCandleCakeOf(BIG_BLACK_CANDLE))
    val BIG_BROWN_CANDLE_CAKE = DnDBlocks.registerNoItem("big_brown_candle_cake", bigCandleCakeOf(BIG_BROWN_CANDLE))
    val BIG_RED_CANDLE_CAKE = DnDBlocks.registerNoItem("big_red_candle_cake", bigCandleCakeOf(BIG_RED_CANDLE))
    val BIG_ORANGE_CANDLE_CAKE = DnDBlocks.registerNoItem("big_orange_candle_cake", bigCandleCakeOf(BIG_ORANGE_CANDLE))
    val BIG_YELLOW_CANDLE_CAKE = DnDBlocks.registerNoItem("big_yellow_candle_cake", bigCandleCakeOf(BIG_YELLOW_CANDLE))
    val BIG_LIME_CANDLE_CAKE = DnDBlocks.registerNoItem("big_lime_candle_cake", bigCandleCakeOf(BIG_LIME_CANDLE))
    val BIG_GREEN_CANDLE_CAKE = DnDBlocks.registerNoItem("big_green_candle_cake", bigCandleCakeOf(BIG_GREEN_CANDLE))
    val BIG_CYAN_CANDLE_CAKE = DnDBlocks.registerNoItem("big_cyan_candle_cake", bigCandleCakeOf(BIG_CYAN_CANDLE))
    val BIG_LIGHT_BLUE_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_light_blue_candle_cake", bigCandleCakeOf(BIG_LIGHT_BLUE_CANDLE))
    val BIG_BLUE_CANDLE_CAKE = DnDBlocks.registerNoItem("big_blue_candle_cake", bigCandleCakeOf(BIG_BLUE_CANDLE))
    val BIG_PURPLE_CANDLE_CAKE = DnDBlocks.registerNoItem("big_purple_candle_cake", bigCandleCakeOf(BIG_PURPLE_CANDLE))
    val BIG_MAGENTA_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_magenta_candle_cake", bigCandleCakeOf(BIG_MAGENTA_CANDLE))
    val BIG_PINK_CANDLE_CAKE = DnDBlocks.registerNoItem("big_pink_candle_cake", bigCandleCakeOf(BIG_PINK_CANDLE))
    val SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("soul_candle_cake", soulCandleCakeOf(SOUL_CANDLE, CANDLE_CAKE))
    val WHITE_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("white_soul_candle_cake", soulCandleCakeOf(WHITE_SOUL_CANDLE))
    val LIGHT_GRAY_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("light_gray_soul_candle_cake", soulCandleCakeOf(LIGHT_GRAY_SOUL_CANDLE))
    val GRAY_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("gray_soul_candle_cake", soulCandleCakeOf(GRAY_SOUL_CANDLE))
    val BLACK_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("black_soul_candle_cake", soulCandleCakeOf(BLACK_SOUL_CANDLE))
    val BROWN_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("brown_soul_candle_cake", soulCandleCakeOf(BROWN_SOUL_CANDLE))
    val RED_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("red_soul_candle_cake", soulCandleCakeOf(RED_SOUL_CANDLE))
    val ORANGE_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("orange_soul_candle_cake", soulCandleCakeOf(ORANGE_SOUL_CANDLE))
    val YELLOW_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("yellow_soul_candle_cake", soulCandleCakeOf(YELLOW_SOUL_CANDLE))
    val LIME_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("lime_soul_candle_cake", soulCandleCakeOf(LIME_SOUL_CANDLE))
    val GREEN_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("green_soul_candle_cake", soulCandleCakeOf(GREEN_SOUL_CANDLE))
    val CYAN_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("cyan_soul_candle_cake", soulCandleCakeOf(CYAN_SOUL_CANDLE))
    val LIGHT_BLUE_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("light_blue_soul_candle_cake", soulCandleCakeOf(LIGHT_BLUE_SOUL_CANDLE))
    val BLUE_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("blue_soul_candle_cake", soulCandleCakeOf(BLUE_SOUL_CANDLE))
    val PURPLE_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("purple_soul_candle_cake", soulCandleCakeOf(PURPLE_SOUL_CANDLE))
    val MAGENTA_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("magenta_soul_candle_cake", soulCandleCakeOf(MAGENTA_SOUL_CANDLE))
    val PINK_SOUL_CANDLE_CAKE = DnDBlocks.registerNoItem("pink_soul_candle_cake", soulCandleCakeOf(PINK_SOUL_CANDLE))
    val BIG_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_soul_candle_cake", bigSoulCandleCakeOf(BIG_SOUL_CANDLE, SOUL_CANDLE_CAKE))
    val BIG_WHITE_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_white_soul_candle_cake", bigSoulCandleCakeOf(BIG_WHITE_SOUL_CANDLE))
    val BIG_LIGHT_GRAY_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_light_gray_soul_candle_cake", bigSoulCandleCakeOf(BIG_LIGHT_GRAY_SOUL_CANDLE))
    val BIG_GRAY_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_gray_soul_candle_cake", bigSoulCandleCakeOf(BIG_GRAY_SOUL_CANDLE))
    val BIG_BLACK_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_black_soul_candle_cake", bigSoulCandleCakeOf(BIG_BLACK_SOUL_CANDLE))
    val BIG_BROWN_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_brown_soul_candle_cake", bigSoulCandleCakeOf(BIG_BROWN_SOUL_CANDLE))
    val BIG_RED_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_red_soul_candle_cake", bigSoulCandleCakeOf(BIG_RED_SOUL_CANDLE))
    val BIG_ORANGE_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_orange_soul_candle_cake", bigSoulCandleCakeOf(BIG_ORANGE_SOUL_CANDLE))
    val BIG_YELLOW_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_yellow_soul_candle_cake", bigSoulCandleCakeOf(BIG_YELLOW_SOUL_CANDLE))
    val BIG_LIME_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_lime_soul_candle_cake", bigSoulCandleCakeOf(BIG_LIME_SOUL_CANDLE))
    val BIG_GREEN_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_green_soul_candle_cake", bigSoulCandleCakeOf(BIG_GREEN_SOUL_CANDLE))
    val BIG_CYAN_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_cyan_soul_candle_cake", bigSoulCandleCakeOf(BIG_CYAN_SOUL_CANDLE))
    val BIG_LIGHT_BLUE_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_light_blue_soul_candle_cake", bigSoulCandleCakeOf(BIG_LIGHT_BLUE_SOUL_CANDLE))
    val BIG_BLUE_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_blue_soul_candle_cake", bigSoulCandleCakeOf(BIG_BLUE_SOUL_CANDLE))
    val BIG_PURPLE_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_purple_soul_candle_cake", bigSoulCandleCakeOf(BIG_PURPLE_SOUL_CANDLE))
    val BIG_MAGENTA_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_magenta_soul_candle_cake", bigSoulCandleCakeOf(BIG_MAGENTA_SOUL_CANDLE))
    val BIG_PINK_SOUL_CANDLE_CAKE =
        DnDBlocks.registerNoItem("big_pink_soul_candle_cake", bigSoulCandleCakeOf(BIG_PINK_SOUL_CANDLE))
}