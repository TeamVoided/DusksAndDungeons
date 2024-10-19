package org.teamvoided.dusk_autumn.block

import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.registry.tag.FluidTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

@Suppress("MemberVisibilityCanBePrivate")
class LavaSpongeBlock(settings: Settings, val maxDepth: Int, val maxIterations: Int, val turnInTo: Block) :
    Block(settings) {
    public override fun getCodec(): MapCodec<LavaSpongeBlock> = CODEC
    override fun onBlockAdded(state: BlockState, world: World, pos: BlockPos, oldState: BlockState, notify: Boolean) {
        if (!oldState.isOf(state.block)) this.update(world, pos)
    }

    override fun neighborUpdate(
        state: BlockState, world: World, pos: BlockPos, block: Block, fromPos: BlockPos, notify: Boolean
    ) {
        this.update(world, pos)
        super.neighborUpdate(state, world, pos, block, fromPos, notify)
    }

    fun update(world: World, pos: BlockPos) {
        if (this.absorbLava(world, pos)) {
            world.setBlockState(pos, turnInTo.defaultState, 2)
            world.playSound(null, pos, SoundEvents.BLOCK_SPONGE_ABSORB, SoundCategory.BLOCKS, 1.0f, 1.0f)
        }
    }

    fun absorbLava(world: World, pos: BlockPos): Boolean {
        return BlockPos.breadthFirstTraversal(pos, maxDepth, maxIterations + 1, { blockPos, consumer ->
            for (direction in Direction.entries) consumer.accept(blockPos.offset(direction))
        }, { checkedPos ->
            if (checkedPos == pos) return@breadthFirstTraversal true
            else {
                val blockState = world.getBlockState(checkedPos)
                val fluidState = world.getFluidState(checkedPos)
                if (!fluidState.isIn(FluidTags.LAVA)) {
                    return@breadthFirstTraversal false
                } else {
                    val block = blockState.block
                    if (block is FluidDrainable) {
                        if (!block.tryDrainFluid(null, world, checkedPos, blockState).isEmpty)
                            return@breadthFirstTraversal true
                    }
                    if (block is FluidBlock) world.setBlockState(checkedPos, Blocks.AIR.defaultState, 3)
                    else {
                        val blockEntity = if (blockState.hasBlockEntity()) world.getBlockEntity(checkedPos) else null
                        dropStacks(blockState, world, checkedPos, blockEntity)
                        world.setBlockState(checkedPos, Blocks.AIR.defaultState, 3)
                    }

                    return@breadthFirstTraversal true
                }
            }
        }) > 1
    }

    companion object {
        val CODEC: MapCodec<LavaSpongeBlock> = createCodec { LavaSpongeBlock(it, 6, 64, Blocks.BASALT) }
    }
}