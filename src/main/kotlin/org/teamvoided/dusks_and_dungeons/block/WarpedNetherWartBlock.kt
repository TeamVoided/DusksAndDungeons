package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.block.BlockState
import net.minecraft.block.NetherWartBlock
import net.minecraft.block.ShapeContext
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldView
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

class WarpedNetherWartBlock(settings: Settings) : NetherWartBlock(settings) {
    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean =
        world.getBlockState(pos.up()).isIn(DnDBlockTags.WARPED_NETHER_WART_PLACEABLE)

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext)
            : VoxelShape = AGE_TO_SHAPE[(state.get(AGE))]

    override fun getPickStack(world: WorldView, pos: BlockPos, state: BlockState): ItemStack =
        ItemStack(DnDBlocks.WARPED_WART)

    companion object {
        val AGE_TO_SHAPE = arrayOf(
            warpedWartShape(11),
            warpedWartShape(8),
            warpedWartShape(5),
            warpedWartShape(2)
        )

        private fun warpedWartShape(size: Int): VoxelShape =
            createCuboidShape(0.0, size.toDouble(), 0.0, 16.0, 16.0, 16.0)
    }
}