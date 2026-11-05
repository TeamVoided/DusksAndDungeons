package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf

class TallSpreadableBlock(settings: Properties) : DoublePlantBlock(settings), BonemealableBlock {

    override fun codec(): MapCodec<TallSpreadableBlock> = CODEC

    override fun isValidBonemealTarget(level: LevelReader, pos: BlockPos, state: BlockState): Boolean {
        val bottomPos = if (state.getValue(HALF) == DoubleBlockHalf.UPPER) pos.below() else pos
        for (dir in Direction.Plane.HORIZONTAL) {
            if (canSpreadToPos(level, bottomPos.relative(dir))) {
                return true
            }
        }
        return false
    }

    override fun isBonemealSuccess(level: Level, random: RandomSource, pos: BlockPos, state: BlockState): Boolean = true

    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        val bottomPos = if (state.getValue(HALF) == DoubleBlockHalf.UPPER) pos.below() else pos
        for (dir in Direction.Plane.HORIZONTAL) {
            val spreadPos = bottomPos.relative(dir)
            if (canSpreadToPos(level, spreadPos)) {
                val upPos = spreadPos.above()
                // clear up pos
                val fluidState = level.getFluidState(upPos)
                val remainderState =
                    if (!fluidState.isEmpty) fluidState.type.defaultFluidState().createLegacyBlock()
                    else Blocks.AIR.defaultBlockState()
                level.setBlock(upPos, remainderState, UPDATE_ALL)
                // place tall block
                level.setBlock(spreadPos, copyWaterloggedFrom(level, spreadPos, defaultBlockState()), UPDATE_ALL)
                level.setBlock(
                    upPos,
                    copyWaterloggedFrom(level, upPos, defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)),
                    UPDATE_ALL
                )
                return
            }
        }
    }

    fun canSpreadToPos(level: LevelReader, pos: BlockPos): Boolean {
        return level.getBlockState(pos.above()).`is`(BlockTags.REPLACEABLE)
                && canSurvive(defaultBlockState(), level, pos)
    }

    companion object {

        val CODEC: MapCodec<TallSpreadableBlock> = simpleCodec(::TallSpreadableBlock)

    }
}
