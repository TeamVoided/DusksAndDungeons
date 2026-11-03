package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.core.BlockPos
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState

class TripleTallBlockItem(block: Block, properties: Properties) : BlockItem(block, properties) {

    override fun placeBlock(context: BlockPlaceContext, state: BlockState): Boolean {
        val level = context.level
        setBlockState(level, context.clickedPos.above())
        setBlockState(level, context.clickedPos.above(2))
        return super.placeBlock(context, state)
    }

    fun setBlockState(level: Level, pos: BlockPos) {
        val state = if (level.isWaterAt(pos)) Blocks.WATER.defaultBlockState() else Blocks.AIR.defaultBlockState()
        level.setBlock(pos, state, 27)
    }

}