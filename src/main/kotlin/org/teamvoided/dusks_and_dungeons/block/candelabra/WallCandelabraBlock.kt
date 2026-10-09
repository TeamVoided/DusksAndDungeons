package org.teamvoided.dusks_and_dungeons.block.candelabra

import net.minecraft.core.BlockPos
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape

class WallCandelabraBlock(properties: Properties) : CandelabraBlock(properties) {

    override fun getStaticShape(state: BlockState): VoxelShape = Candelabra.getWallShape(state)

    override fun getCandleOffsets(): Array<Array<Vec3>> = Candelabra.WALL_OFFSETS

    override fun getCandleSlots(): Array<Array<Pair<Vec3, Int>>> = Candelabra.WALL_PLACEMENTS

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        var state = super.getStateForPlacement(ctx)
        if (ctx.clickedFace.axis.isHorizontal) {
            state = state?.setValue(FACING, ctx.clickedFace)
        }
        return state
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        val dir = state.getValue(FACING).opposite
        return canSupportCenter(level, pos.relative(dir), dir)
    }

}