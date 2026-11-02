package org.teamvoided.dusks_and_dungeons.init.misc

import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.block.SoundType

object DnDSoundTypes {

    val BIG_CHAIN = SoundType(
        1.0F, 0.8F,
        SoundEvents.CHAIN_BREAK,
        SoundEvents.CHAIN_STEP,
        SoundEvents.CHAIN_PLACE,
        SoundEvents.CHAIN_HIT,
        SoundEvents.CHAIN_FALL
    )

    val BIG_LANTERN = SoundType(
        1.0F, 0.8F,
        SoundEvents.LANTERN_BREAK,
        SoundEvents.LANTERN_STEP,
        SoundEvents.LANTERN_PLACE,
        SoundEvents.LANTERN_HIT,
        SoundEvents.LANTERN_FALL
    )

    val BIG_CANDLE = SoundType(
        1.0F, 0.8F,
        SoundEvents.CANDLE_BREAK,
        SoundEvents.CANDLE_STEP,
        SoundEvents.CANDLE_PLACE,
        SoundEvents.CANDLE_HIT,
        SoundEvents.CANDLE_FALL
    )

    val CORN_SYRUP_BLOCK = SoundType(
        1f, 0.5f,
        SoundEvents.HONEY_BLOCK_BREAK,
        SoundEvents.HONEY_BLOCK_STEP,
        SoundEvents.HONEY_BLOCK_PLACE,
        SoundEvents.HONEY_BLOCK_HIT,
        SoundEvents.HONEY_BLOCK_FALL
    )

    val ROOT_BLOCK = SoundType(
        1f, 0.8f,
        SoundEvents.HANGING_ROOTS_BREAK,
        SoundEvents.HANGING_ROOTS_STEP,
        SoundEvents.HANGING_ROOTS_PLACE,
        SoundEvents.HANGING_ROOTS_HIT,
        SoundEvents.HANGING_ROOTS_FALL
    )

}