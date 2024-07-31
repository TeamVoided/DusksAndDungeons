package org.teamvoided.dusk_autumn.block.big

import com.google.common.collect.ImmutableList
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.CandleCakeBlock
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

open class BigCandleCakeBlock(candle: Block, settings: Settings) : CandleCakeBlock(candle, settings) {
    override fun getParticleOffsets(state: BlockState?): Iterable<Vec3d> {
        return BIG_CANDLE_PARTICLE_OFFSETS
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE
    }
    companion object{
        val CANDLE_SHAPE = Block.createCuboidShape(6.0, 8.0, 6.0, 10.0, 20.0, 10.0)
        val SHAPE = VoxelShapes.union(CAKE_SHAPE, this.CANDLE_SHAPE)
        val BIG_CANDLE_PARTICLE_OFFSETS = ImmutableList.of(Vec3d(0.5, 1.375, 0.5))
    }
}