package org.teamvoided.dusks_and_dungeons.block.rocky

import com.mojang.serialization.MapCodec
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock
import net.minecraft.server.level.ServerLevel
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource

abstract class RockySpreadableBlock(val spreadBlock: Block, val dirt: Block, settings: Properties) :
    SpreadingSnowyDirtBlock(settings) {
    val canSpreadTo = Blocks.DIRT
    abstract override fun codec(): MapCodec<out SpreadingSnowyDirtBlock>
    override fun randomTick(state: BlockState?, world: ServerLevel, pos: BlockPos, random: RandomSource) {
        if (!canBeGrass(state, world, pos)) world.setBlockAndUpdate(pos, dirt.defaultBlockState())
        else if (world.getMaxLocalRawBrightness(pos.above()) >= 9) {
            val spreadBlock = spreadBlock.defaultBlockState()
            for (i in 0..3) {
                val blockPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1)
                if (world.getBlockState(blockPos).`is`(canSpreadTo) && canPropagate(spreadBlock, world, blockPos)) {
                    world.setBlockAndUpdate(
                        blockPos,
                        spreadBlock.setValue(SNOWY, world.getBlockState(blockPos.above()).`is`(Blocks.SNOW))
                    )
                }
            }
        }

    }
}