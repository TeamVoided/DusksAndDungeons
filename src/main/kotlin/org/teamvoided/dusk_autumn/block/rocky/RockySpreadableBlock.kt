package org.teamvoided.dusk_autumn.block.rocky

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.SpreadableBlock
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.random.RandomGenerator

abstract class RockySpreadableBlock(val spreadBlock: Block, val dirt: Block, settings: Settings) :
    SpreadableBlock(settings) {
        val canSpreadTo = Blocks.DIRT
    abstract override fun getCodec(): MapCodec<out SpreadableBlock>
    override fun randomTick(state: BlockState?, world: ServerWorld, pos: BlockPos, random: RandomGenerator) {
        if (!canSurvive(state, world, pos)) {
            world.setBlockState(pos, dirt.defaultState)
        } else {
            if (world.getLightLevel(pos.up()) >= 9) {
                val spreadBlock = spreadBlock.defaultState

                for (i in 0..3) {
                    val blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1)
                    if (world.getBlockState(blockPos).isOf(canSpreadTo) && canSpread(spreadBlock, world, blockPos)) {
                        world.setBlockState(
                            blockPos,
                            spreadBlock.with(SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)) as BlockState
                        )
                    }
                }
            }
        }
    }
}