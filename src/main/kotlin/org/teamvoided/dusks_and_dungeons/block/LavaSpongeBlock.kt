package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.FluidTags
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.BucketPickup
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.state.BlockState
import java.util.function.Consumer

class LavaSpongeBlock(
    properties: Properties,
    val maxDepth: Int,
    val maxIterations: Int,
    val turnInTo: Block,
) : Block(properties) {

    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, oldState: BlockState, notify: Boolean) {
        if (!oldState.`is`(state.block)) {
            checkIfCanAbsorb(level, pos)
        }
    }

    override fun neighborChanged(
        state: BlockState, level: Level, pos: BlockPos, block: Block, fromPos: BlockPos, notify: Boolean,
    ) {
        checkIfCanAbsorb(level, pos)
        super.neighborChanged(state, level, pos, block, fromPos, notify)
    }

    fun checkIfCanAbsorb(level: Level, pos: BlockPos) {
        if (absorbLava(level, pos)) {
            level.setBlock(pos, turnInTo.defaultBlockState(), 2)
            level.playSound(null, pos, SoundEvents.SPONGE_ABSORB, SoundSource.BLOCKS, 1.0f, 1.0f)
        }
    }

    fun absorbLava(level: Level, startPos: BlockPos): Boolean {
        val drainedBlocks =
            BlockPos.breadthFirstTraversal(startPos, maxDepth, maxIterations + 1, ::collectAllDirections) {
                scanPosition(level, startPos, it)
            }
        return drainedBlocks > 1
    }

    fun scanPosition(level: Level, startPos: BlockPos, currentPos: BlockPos): Boolean {
        if (currentPos == startPos) {
            return true
        }
        val state = level.getBlockState(currentPos)
        val fluid = level.getFluidState(currentPos)
        if (!fluid.`is`(FluidTags.LAVA)) {
            return false
        }
        val block = state.block
        if (block is BucketPickup && !block.pickupBlock(null, level, currentPos, state).isEmpty) {
            return true
        }
        if (block is LiquidBlock) {
            level.setBlock(currentPos, Blocks.AIR.defaultBlockState(), 3)
        } else {
            val blockEntity = if (state.hasBlockEntity()) level.getBlockEntity(currentPos) else null
            dropResources(state, level, currentPos, blockEntity)
            level.setBlock(currentPos, Blocks.AIR.defaultBlockState(), 3)
        }
        return true
    }

    companion object {

        fun collectAllDirections(pos: BlockPos, consumer: Consumer<BlockPos>) {
            for (direction in Direction.entries) {
                consumer.accept(pos.relative(direction))
            }
        }

    }
}