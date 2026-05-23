package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.world.level.block.Blocks
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.level.LevelWriter

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

fun Vec3.blockPos(): BlockPos {
    return BlockPos(this.x.toInt(), this.y.toInt(), this.z.toInt())
}

fun setCount(x: Number, y: Number) = SetItemCountFunction.setCount(uniformNum(x, y))

fun uniformNum(x: Number, y: Number): UniformGenerator =
    UniformGenerator.between(x.toFloat(), y.toFloat())

fun LevelWriter.placeDebug(pos: BlockPos, block: Int) {
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
    }.defaultBlockState()
    this.setBlock(pos, state, 2)
}

fun Projectile.setShootVelocity(pitch: Float, yaw: Float, roll: Float, speed: Float, modifierXYZ: Float) {
    val f = -Mth.sin(yaw * (Math.PI.toFloat() / 180)) * Mth.cos(pitch * (Math.PI.toFloat() / 180))
    val g = -Mth.sin((pitch + roll) * (Math.PI.toFloat() / 180))
    val h = Mth.cos(yaw * (Math.PI.toFloat() / 180)) * Mth.cos(pitch * (Math.PI.toFloat() / 180))
    this.shoot(f.toDouble(), g.toDouble(), h.toDouble(), speed, modifierXYZ)
}

fun ServerLevel.spawnParticles(particle: ParticleOptions, pos: Vec3, velocity: Vec3) =
    this.sendParticles(particle, pos.x, pos.y, pos.z, 0, velocity.x, velocity.y, velocity.z, 1.0)

fun getPropertyFromDirection(direction: Direction): BooleanProperty {
    return when (direction) {
        Direction.NORTH -> BlockStateProperties.NORTH
        Direction.SOUTH -> BlockStateProperties.SOUTH
        Direction.EAST -> BlockStateProperties.EAST
        Direction.WEST -> BlockStateProperties.WEST
        Direction.UP -> BlockStateProperties.UP
        Direction.DOWN -> BlockStateProperties.DOWN
        else -> BlockStateProperties.NORTH
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
