package org.teamvoided.dusk_autumn.block

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

class HollowLogBlockWithExperiment(settings: Settings) : HollowLogBlock(settings) {
    fun getSquare(world: World, pos: BlockPos, blockAxis: Direction.Axis) {
        val directionEntries = listOf(
            if (blockAxis == Direction.Axis.X) {
                (Direction.NORTH to Direction.NORTH)
            } else if (blockAxis == Direction.Axis.Z) {
                (Direction.NORTH to Direction.NORTH)
            } else {
                (Direction.NORTH to Direction.EAST)
                (Direction.EAST to Direction.SOUTH)
                (Direction.SOUTH to Direction.WEST)
                (Direction.WEST to Direction.NORTH)
            }
        )

        directionEntries.forEachIndexed { idx, it ->

        }
    }
}