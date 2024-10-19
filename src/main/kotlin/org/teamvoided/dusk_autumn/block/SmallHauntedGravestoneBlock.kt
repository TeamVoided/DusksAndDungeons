package org.teamvoided.dusk_autumn.block

import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import org.teamvoided.dusk_autumn.util.rotate

class SmallHauntedGravestoneBlock(settings: Settings) : HauntedGravestoneBlock(settings) {

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val rotations = state.get(FACING).horizontal
        val shape = if (state.get(CENTERED)) SmallGravestoneBlock.CENTER_SHAPE else SmallGravestoneBlock.SHAPE
        return shape.rotate(rotations)
    }
}