package org.teamvoided.dusk_autumn.block.not_blocks

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.pattern.BlockPattern
import net.minecraft.block.pattern.BlockPatternBuilder
import net.minecraft.block.pattern.CachedBlockPosition
import net.minecraft.predicate.block.BlockStatePredicate
import java.util.function.Predicate

object GolemPatterns {
    var snowGolemDispenserPattern: BlockPattern? = null
        get() {
            if (field == null) {
                field = BlockPatternBuilder.start().aisle(" ", "#", "#")
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK)))
                    .build()
            }
            return field
        }
    var snowGolemPattern: BlockPattern? = null
        get() {
            if (field == null) {
                field = BlockPatternBuilder.start().aisle("^", "#", "#")
                    .where('^', CachedBlockPosition.matchesBlockState(IS_ORANGE_CARVED_PUMPKIN_PREDICATE))
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK)))
                    .build()
            }
            return field
        }
    var ironGolemDispenserPattern: BlockPattern? = null
        get() {
            if (field == null) {
                field = BlockPatternBuilder.start().aisle("~ ~", "###", "~#~")
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK)))
                    .where('~') { block: CachedBlockPosition -> block.blockState.isAir }
                    .build()
            }
            return field
        }
    var ironGolemPattern: BlockPattern? = null
        get() {
            if (field == null) {
                field = BlockPatternBuilder.start().aisle("~^~", "###", "~#~")
                    .where('^', CachedBlockPosition.matchesBlockState(IS_ORANGE_CARVED_PUMPKIN_PREDICATE))
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK)))
                    .where('~') { block: CachedBlockPosition -> block.blockState.isAir }
                    .build()
            }
            return field
        }

    private val IS_ORANGE_CARVED_PUMPKIN_PREDICATE =
        Predicate { state: BlockState? ->
            state != null &&
                    (state.isOf(Blocks.CARVED_PUMPKIN) || state.isOf(Blocks.JACK_O_LANTERN))
        }
}