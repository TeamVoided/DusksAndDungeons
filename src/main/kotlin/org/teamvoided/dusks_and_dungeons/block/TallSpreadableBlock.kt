package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BonemealableBlock
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader

class TallSpreadableBlock(settings: Properties) : DoublePlantBlock(settings), BonemealableBlock {
    companion object {
        val CODEC: MapCodec<TallSpreadableBlock> = simpleCodec(::TallSpreadableBlock)
    }

    override fun codec(): MapCodec<TallSpreadableBlock> = CODEC
    override fun isValidBonemealTarget(world: LevelReader, usePos: BlockPos, state: BlockState): Boolean {
        val pos = if (state.getValue(HALF) == DoubleBlockHalf.UPPER) usePos.below() else usePos
        for (dir in Direction.Plane.HORIZONTAL) {
            if (
                world.getBlockState(pos.relative(dir).above()).canBeReplaced()
                && canSurvive(defaultBlockState(), world, pos.relative(dir))
            ) {
                return true
            }
        }
        return false
    }

    override fun isBonemealSuccess(world: Level, random: RandomSource, usePos: BlockPos, state: BlockState): Boolean =
        true

    override fun performBonemeal(world: ServerLevel, random: RandomSource, usePos: BlockPos, state: BlockState) {
        val pos = if (state.getValue(HALF) == DoubleBlockHalf.UPPER) usePos.below() else usePos
        for (dir in Direction.Plane.HORIZONTAL) {
            val offsetPos = pos.relative(dir)
            if (
                world.getBlockState(offsetPos.above()).canBeReplaced()
                && canSurvive(defaultBlockState(), world, offsetPos)
            ) {
                val airState = if (world.isWaterAt(offsetPos)) Blocks.WATER.defaultBlockState() else Blocks.AIR.defaultBlockState()
                world.setBlock(offsetPos.above(), airState, UPDATE_ALL or UPDATE_IMMEDIATE or UPDATE_KNOWN_SHAPE)
                // place block
                world.setBlock(offsetPos, copyWaterloggedFrom(world, offsetPos, defaultBlockState()), UPDATE_ALL)
                world.setBlock(
                    offsetPos.above(),
                    copyWaterloggedFrom(world, offsetPos.above(), defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)),
                    UPDATE_ALL
                )

                return
            }
        }
    }
}
