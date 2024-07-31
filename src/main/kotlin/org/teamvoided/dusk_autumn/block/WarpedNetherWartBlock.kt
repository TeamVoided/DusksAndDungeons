package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.NetherWartBlock
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldView
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags

class WarpedNetherWartBlock(settings: Settings) : NetherWartBlock(settings) {
    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val blockPos = pos.up()
        return world.getBlockState(blockPos).isIn(DnDBlockTags.WARPED_NETHER_WART_PLACEABLE)
    }
    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return AGE_TO_SHAPE[(state.get(AGE))]
    }

    companion object {
        val AGE_TO_SHAPE = arrayOf<VoxelShape>(
            createCuboidShape(0.0, 11.0, 0.0, 16.0, 16.0, 16.0),
            createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
            createCuboidShape(0.0, 5.0, 0.0, 16.0, 16.0, 16.0),
            createCuboidShape(0.0, 2.0, 0.0, 16.0, 16.0, 16.0)
        )

    }
}