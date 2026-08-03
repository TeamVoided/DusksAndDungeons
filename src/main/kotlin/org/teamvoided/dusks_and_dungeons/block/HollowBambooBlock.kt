package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.core.Direction
import net.minecraft.world.phys.shapes.VoxelShape

class HollowBambooBlock(settings: Properties) : CuttableHollowLogBlock(settings) {

    override val shapeMap: Map<Direction.Axis, Array<VoxelShape>> =
        crateShapeMap(NORTH_BAMBOO_SHAPE, EAST_BAMBOO_SHAPE, SOUTH_BAMBOO_SHAPE, WEST_BAMBOO_SHAPE)

    override fun getParticleDensity(): Double = 0.3

    companion object {
        val NORTH_BAMBOO_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 16.0, 16.0, 4.0)
        val SOUTH_BAMBOO_SHAPE: VoxelShape = box(0.0, 0.0, 12.0, 16.0, 16.0, 16.0)
        val EAST_BAMBOO_SHAPE: VoxelShape = box(12.0, 0.0, 0.0, 16.0, 16.0, 16.0)
        val WEST_BAMBOO_SHAPE: VoxelShape = box(0.0, 0.0, 0.0, 4.0, 16.0, 16.0)
    }

}