package org.teamvoided.dusk_autumn.item

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemPlacementContext
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class TripleTallBlockItem(block: Block, settings: Settings) : BlockItem(block, settings) {
    override fun place(context: ItemPlacementContext, state: BlockState): Boolean {
        val world = context.world
        setBlockState(world, context.blockPos.up())
        setBlockState(world, context.blockPos.up(2))
        return super.place(context, state)
    }

    fun setBlockState(world: World, pos: BlockPos) {
        val blockState = if (world.isWater(pos)) Blocks.WATER.defaultState else Blocks.AIR.defaultState
        world.setBlockState(pos, blockState, 27)
    }
}