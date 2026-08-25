package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.NetherWartBlock
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.item.ItemStack
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

class WarpedNetherWartBlock(settings: Properties) : NetherWartBlock(settings) {
    override fun canSurvive(state: BlockState, world: LevelReader, pos: BlockPos): Boolean =
        world.getBlockState(pos.above()).`is`(DnDBlockTags.SUPPORTS_WARPED_NETHER_WART)

    override fun getShape(state: BlockState, world: BlockGetter, pos: BlockPos, context: CollisionContext)
            : VoxelShape = AGE_TO_SHAPE[(state.getValue(AGE))]

    override fun getCloneItemStack(world: LevelReader, pos: BlockPos, state: BlockState): ItemStack =
        ItemStack(DnDBlocks.WARPED_WART)

    companion object {
        val AGE_TO_SHAPE = arrayOf(
            warpedWartShape(11),
            warpedWartShape(8),
            warpedWartShape(5),
            warpedWartShape(2)
        )

        private fun warpedWartShape(size: Int): VoxelShape =
            box(0.0, size.toDouble(), 0.0, 16.0, 16.0, 16.0)
    }
}