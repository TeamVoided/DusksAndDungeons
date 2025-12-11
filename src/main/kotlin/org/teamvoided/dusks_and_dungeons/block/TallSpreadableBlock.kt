package org.teamvoided.dusks_and_dungeons.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.Fertilizable
import net.minecraft.block.TallPlantBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World
import net.minecraft.world.WorldView

class TallSpreadableBlock(settings: Settings) : TallPlantBlock(settings), Fertilizable {
    companion object {
        val CODEC: MapCodec<TallSpreadableBlock> = createCodec(::TallSpreadableBlock)
    }

    override fun getCodec(): MapCodec<TallSpreadableBlock> = CODEC
    override fun isFertilizable(world: WorldView, usePos: BlockPos, state: BlockState): Boolean {
        val pos = if (state.get(HALF) == DoubleBlockHalf.UPPER) usePos.down() else usePos
        for (dir in Direction.Type.HORIZONTAL) {
            if (
                world.getBlockState(pos.offset(dir).up()).materialReplaceable()
                && canPlaceAt(defaultState, world, pos.offset(dir))
            ) {
                return true
            }
        }
        return false
    }

    override fun canFertilize(world: World, random: RandomGenerator, usePos: BlockPos, state: BlockState): Boolean =
        true

    override fun fertilize(world: ServerWorld, random: RandomGenerator, usePos: BlockPos, state: BlockState) {
        val pos = if (state.get(HALF) == DoubleBlockHalf.UPPER) usePos.down() else usePos
        for (dir in Direction.Type.HORIZONTAL) {
            val offsetPos = pos.offset(dir)
            if (
                world.getBlockState(offsetPos.up()).materialReplaceable()
                && canPlaceAt(defaultState, world, offsetPos)
            ) {
                val airState = if (world.isWater(offsetPos)) Blocks.WATER.defaultState else Blocks.AIR.defaultState
                world.setBlockState(offsetPos.up(), airState, NOTIFY_ALL or REDRAW_ON_MAIN_THREAD or FORCE_STATE)
                // place block
                world.setBlockState(offsetPos, withWaterloggedState(world, offsetPos, defaultState), NOTIFY_ALL)
                world.setBlockState(
                    offsetPos.up(),
                    withWaterloggedState(world, offsetPos.up(), defaultState.with(HALF, DoubleBlockHalf.UPPER)),
                    NOTIFY_ALL
                )

                return
            }
        }
    }
}
