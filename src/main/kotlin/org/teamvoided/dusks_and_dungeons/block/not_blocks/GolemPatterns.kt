package org.teamvoided.dusks_and_dungeons.block.not_blocks

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.pattern.BlockPattern
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder
import net.minecraft.world.level.block.state.pattern.BlockInWorld
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate
import java.util.function.Predicate

object GolemPatterns {
    var snowGolemDispenserPattern: BlockPattern? = null
        get() {
            if (field == null) {
                field = BlockPatternBuilder.start().aisle(" ", "#", "#")
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK)))
                    .build()
            }
            return field
        }
    var snowGolemPattern: BlockPattern? = null
        get() {
            if (field == null) {
                field = BlockPatternBuilder.start().aisle("^", "#", "#")
                    .where('^', BlockInWorld.hasState(IS_ORANGE_CARVED_PUMPKIN_PREDICATE))
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK)))
                    .build()
            }
            return field
        }
    var ironGolemDispenserPattern: BlockPattern? = null
        get() {
            if (field == null) {
                field = BlockPatternBuilder.start().aisle("~ ~", "###", "~#~")
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK)))
                    .where('~') { block: BlockInWorld -> block.state.isAir }
                    .build()
            }
            return field
        }
    var ironGolemPattern: BlockPattern? = null
        get() {
            if (field == null) {
                field = BlockPatternBuilder.start().aisle("~^~", "###", "~#~")
                    .where('^', BlockInWorld.hasState(IS_ORANGE_CARVED_PUMPKIN_PREDICATE))
                    .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK)))
                    .where('~') { block: BlockInWorld -> block.state.isAir }
                    .build()
            }
            return field
        }

    private val IS_ORANGE_CARVED_PUMPKIN_PREDICATE =
        Predicate { state: BlockState? ->
            state != null &&
                    (state.`is`(Blocks.CARVED_PUMPKIN) || state.`is`(Blocks.JACK_O_LANTERN))
        }
}