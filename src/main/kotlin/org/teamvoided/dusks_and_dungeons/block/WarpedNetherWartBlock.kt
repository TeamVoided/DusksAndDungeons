package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.NetherWartBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

class WarpedNetherWartBlock(properties: Properties) : NetherWartBlock(properties) {

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, ctx: CollisionContext): VoxelShape {
        return AGE_TO_SHAPE[(state.getValue(AGE))]
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return level.getBlockState(pos.above()).`is`(DnDBlockTags.SUPPORTS_WARPED_NETHER_WART)
    }

    override fun getCloneItemStack(level: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(DnDBlocks.WARPED_WART)
    }

    companion object {

        fun wartShape(size: Int): VoxelShape = box(0.0, size.toDouble(), 0.0, 16.0, 16.0, 16.0)

        val AGE_TO_SHAPE = arrayOf(
            wartShape(11),
            wartShape(8),
            wartShape(5),
            wartShape(2)
        )

    }
}