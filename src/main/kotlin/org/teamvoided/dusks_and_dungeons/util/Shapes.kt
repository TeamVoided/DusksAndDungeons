/*
 * TODO(cleanup)
 */

package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.core.Direction
import net.minecraft.world.level.block.Block
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape


fun symmetricalBox(xzMin: Double, yMin: Double, xzMax: Double, yMax: Double): VoxelShape {
    return Block.box(xzMin, yMin, xzMin, xzMax, yMax, xzMax)
}

fun symmetricalBoxY(xzMin: Double, yMin: Double, yMax: Double): VoxelShape {
    return Block.box(xzMin, yMin, xzMin, 16 - xzMin, yMax, 16 - xzMin)
}

fun symmetricalBoxZ(xyMin: Double, zMin: Double, zMax: Double): VoxelShape {
    return Block.box(xyMin, xyMin, zMin, 16 - xyMin, 16 - xyMin, zMax)
}

fun symmetricalBoxX(yzMin: Double, xMin: Double, xMax: Double): VoxelShape {
    return Block.box(xMin, yzMin, yzMin, xMax, 16 - yzMin, 16 - yzMin)
}

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
