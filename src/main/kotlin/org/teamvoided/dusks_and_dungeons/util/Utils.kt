package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.block.Blocks
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.particle.ParticleEffect
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.ModifiableWorld

const val pi = 3.1415927f
const val degToRad = 0.017453292f
const val radToDeg = 57.29578f
const val rotate45 = 0.785f
const val rotate90 = 1.571f
const val rotate135 = 2.356f
const val rotate180 = 3.142f
const val rotate225 = 3.927f
const val rotate270 = 4.712f
const val rotate315 = 5.498f
const val rotate360 = 6.28319f

fun Vec3d.blockPos(): BlockPos {
    return BlockPos(this.x.toInt(), this.y.toInt(), this.z.toInt())
}

fun setCount(x: Number, y: Number) = SetCountLootFunction.builder(uniformNum(x, y))

fun uniformNum(x: Number, y: Number): UniformLootNumberProvider =
    UniformLootNumberProvider.create(x.toFloat(), y.toFloat())

fun ModifiableWorld.placeDebug(pos: BlockPos, block: Int) {
    val state = when (block) {
        0 -> Blocks.GLASS
        1 -> Blocks.WHITE_STAINED_GLASS
        2 -> Blocks.LIGHT_GRAY_STAINED_GLASS
        3 -> Blocks.GRAY_STAINED_GLASS
        4 -> Blocks.BLACK_STAINED_GLASS
        5 -> Blocks.BROWN_STAINED_GLASS
        6 -> Blocks.RED_STAINED_GLASS
        7 -> Blocks.ORANGE_STAINED_GLASS
        8 -> Blocks.YELLOW_STAINED_GLASS
        9 -> Blocks.LIME_STAINED_GLASS
        10 -> Blocks.GREEN_STAINED_GLASS
        11 -> Blocks.CYAN_STAINED_GLASS
        12 -> Blocks.LIGHT_BLUE_STAINED_GLASS
        13 -> Blocks.BLUE_STAINED_GLASS
        14 -> Blocks.PURPLE_STAINED_GLASS
        15 -> Blocks.MAGENTA_STAINED_GLASS
        16 -> Blocks.PINK_STAINED_GLASS
        else -> Blocks.TINTED_GLASS
    }.defaultState
    this.setBlockState(pos, state, 2)
}

fun ProjectileEntity.setShootVelocity(pitch: Float, yaw: Float, roll: Float, speed: Float, modifierXYZ: Float) {
    val f = -MathHelper.sin(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
    val g = -MathHelper.sin((pitch + roll) * (Math.PI.toFloat() / 180))
    val h = MathHelper.cos(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
    this.setVelocity(f.toDouble(), g.toDouble(), h.toDouble(), speed, modifierXYZ)
}

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
    for (i in 0 until rotations)
        directionReturn = nextHorizontalDirection(directionReturn)
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
