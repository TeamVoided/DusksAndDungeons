package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

class TripleTallBlockItem(block: Block, settings: Properties) : BlockItem(block, settings) {
    override fun placeBlock(context: BlockPlaceContext, state: BlockState): Boolean {
        val world = context.level
        setBlockState(world, context.clickedPos.above())
        setBlockState(world, context.clickedPos.above(2))
        return super.placeBlock(context, state)
    }

    fun setBlockState(world: Level, pos: BlockPos) {
        val blockState = if (world.isWaterAt(pos)) Blocks.WATER.defaultBlockState() else Blocks.AIR.defaultBlockState()
        world.setBlock(pos, blockState, 27)
    }
}