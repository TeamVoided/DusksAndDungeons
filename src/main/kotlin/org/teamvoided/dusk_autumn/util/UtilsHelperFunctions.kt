package org.teamvoided.dusk_autumn.util

import net.minecraft.block.ConnectingBlock
import net.minecraft.particle.ParticleEffect
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes

fun ServerWorld.spawnParticles(particle: ParticleEffect, pos: Vec3d, velocity: Vec3d) =
    this.spawnParticles(particle, pos.x, pos.y, pos.z, 0, velocity.x, velocity.y, velocity.z, 1.0)

fun getPropertyFromDirection(direction: Direction): BooleanProperty {
    return when (direction) {
        Direction.NORTH -> Properties.NORTH
        Direction.SOUTH -> Properties.SOUTH
        Direction.EAST -> Properties.EAST
        Direction.WEST -> Properties.WEST
        Direction.UP -> Properties.UP
        Direction.DOWN -> Properties.DOWN
        else -> Properties.NORTH
    }
}

fun nextHorizontalDirection(direction: Direction, rotations: Int): Direction {
    var directionReturn = direction
        for (i in 0 until rotations) {
            directionReturn = nextHorizontalDirection(directionReturn)
        }
    return directionReturn
}

fun nextHorizontalDirection(direction: Direction): Direction {
    return when (direction) {
        Direction.NORTH -> Direction.EAST
        Direction.EAST -> Direction.SOUTH
        Direction.SOUTH -> Direction.WEST
        Direction.WEST -> Direction.NORTH
        else -> Direction.NORTH
    }
}

fun VoxelShape.rotate(times: Int): VoxelShape {
    val shapes = arrayOf(this, VoxelShapes.empty())
    for (i in 0 until times) {
        shapes[0].forEachBox { minX, minY, minZ, maxX, maxY, maxZ ->
            shapes[1] = VoxelShapes.union(
                shapes[1], VoxelShapes.cuboid(
                    1 - maxZ, minY, minX,
                    1 - minZ, maxY, maxX
                )
            )
        }
        shapes[0] = shapes[1]
        shapes[1] = VoxelShapes.empty()
    }
    return shapes[0]
}

fun VoxelShape.rotateColumn(axis: Direction.Axis): VoxelShape {
    val shapes = arrayOf(this, VoxelShapes.empty())

    if (axis == Direction.Axis.X) {
        shapes[0].forEachBox { minX, minY, minZ, maxX, maxY, maxZ ->
            shapes[1] = VoxelShapes.union(
                shapes[1], VoxelShapes.cuboid(
                    minY, minX, minZ,
                    maxY, maxX, maxZ
                )
            )
        }
        shapes[0] = shapes[1]
        shapes[1] = VoxelShapes.empty()
    } else if (axis == Direction.Axis.Z) {
        shapes[0].forEachBox { minX, minY, minZ, maxX, maxY, maxZ ->
            shapes[1] = VoxelShapes.union(
                shapes[1], VoxelShapes.cuboid(
                    minX, minZ, minY,
                    maxX, maxZ, maxY
                )
            )
        }
        shapes[0] = shapes[1]
        shapes[1] = VoxelShapes.empty()
    }

    return shapes[0]
}
