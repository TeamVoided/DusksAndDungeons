/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.core.Direction
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

fun VoxelShape.rotate(times: Int): VoxelShape {
    val shapes = arrayOf(this, Shapes.empty())
    for (i in 0 until times) {
        shapes[0].forAllBoxes { minX, minY, minZ, maxX, maxY, maxZ ->
            shapes[1] = Shapes.or(
                shapes[1], Shapes.box(
                    1 - maxZ, minY, minX,
                    1 - minZ, maxY, maxX
                )
            )
        }
        shapes[0] = shapes[1]
        shapes[1] = Shapes.empty()
    }
    return shapes[0]
}

fun VoxelShape.rotateColumn(axis: Direction.Axis): VoxelShape {
    val shapes = arrayOf(this, Shapes.empty())

    if (axis == Direction.Axis.X) {
        shapes[0].forAllBoxes { minX, minY, minZ, maxX, maxY, maxZ ->
            shapes[1] = Shapes.or(
                shapes[1], Shapes.box(
                    minY, minX, minZ,
                    maxY, maxX, maxZ
                )
            )
        }
        shapes[0] = shapes[1]
        shapes[1] = Shapes.empty()
    } else if (axis == Direction.Axis.Z) {
        shapes[0].forAllBoxes { minX, minY, minZ, maxX, maxY, maxZ ->
            shapes[1] = Shapes.or(
                shapes[1], Shapes.box(
                    minX, minZ, minY,
                    maxX, maxZ, maxY
                )
            )
        }
        shapes[0] = shapes[1]
        shapes[1] = Shapes.empty()
    }

    return shapes[0]
}
