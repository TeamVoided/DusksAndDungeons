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

@Suppress("MemberVisibilityCanBePrivate")
class LavaSpongeBlock(
    settings: Properties,
    val maxDepth: Int,
    val maxIterations: Int,
    val turnInTo: Block,
) : Block(settings) {
    override fun onPlace(state: BlockState, world: Level, pos: BlockPos, oldState: BlockState, notify: Boolean) {
        if (!oldState.`is`(state.block)) this.update(world, pos)
    }

    override fun neighborChanged(
        state: BlockState, world: Level, pos: BlockPos, block: Block, fromPos: BlockPos, notify: Boolean,
    ) {
        this.update(world, pos)
        super.neighborChanged(state, world, pos, block, fromPos, notify)
    }

    fun update(world: Level, pos: BlockPos) {
        if (this.absorbLava(world, pos)) {
            world.setBlock(pos, turnInTo.defaultBlockState(), 2)
            world.playSound(null, pos, SoundEvents.SPONGE_ABSORB, SoundSource.BLOCKS, 1.0f, 1.0f)
        }
    }

    fun absorbLava(world: Level, pos: BlockPos): Boolean {
        return BlockPos.breadthFirstTraversal(pos, maxDepth, maxIterations + 1, { blockPos, consumer ->
            for (direction in Direction.entries) {
                consumer.accept(blockPos.relative(direction))
            }
        }, { checkedPos ->
            if (checkedPos == pos) return@breadthFirstTraversal true
            else {
                val blockState = world.getBlockState(checkedPos)
                val fluidState = world.getFluidState(checkedPos)
                if (!fluidState.`is`(FluidTags.LAVA)) {
                    return@breadthFirstTraversal false
                } else {
                    val block = blockState.block
                    if (block is BucketPickup) {
                        if (!block.pickupBlock(null, world, checkedPos, blockState).isEmpty)
                            return@breadthFirstTraversal true
                    }
                    if (block is LiquidBlock) world.setBlock(checkedPos, Blocks.AIR.defaultBlockState(), 3)
                    else {
                        val blockEntity = if (blockState.hasBlockEntity()) world.getBlockEntity(checkedPos) else null
                        dropResources(blockState, world, checkedPos, blockEntity)
                        world.setBlock(checkedPos, Blocks.AIR.defaultBlockState(), 3)
                    }

                    return@breadthFirstTraversal true
                }
            }
        }) > 1
    }
}
