package org.teamvoided.dusks_and_dungeons.block.candelabra

import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape

class WallCandelabraBlock(properties: Properties) : CandelabraBlock(properties) {

    override fun getStaticShape(state: BlockState): VoxelShape = Candelabra.getWallShape(state)

    override fun getCandleOffsets(): Array<Array<Vec3>> = Candelabra.WALL_OFFSETS

}