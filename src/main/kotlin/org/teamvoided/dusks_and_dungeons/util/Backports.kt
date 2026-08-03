package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.core.Direction
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySelector
import java.util.function.Predicate


// All functions here have been backported from future versions and will become redundant on update

val CAN_BE_PICKED: Predicate<Entity> = EntitySelector.NO_SPECTATORS.and(Entity::isPickable)

fun getApproximateNearest(dx: Double, dy: Double, dz: Double): Direction {
    return getApproximateNearest(dx.toFloat(), dy.toFloat(), dz.toFloat())
}

fun getApproximateNearest(dx: Float, dy: Float, dz: Float): Direction {
    var result = Direction.NORTH
    var highestDot = Float.MIN_VALUE

    var dot: Float
    for (direction in Direction.entries) {
        dot = dx * direction.normal.x + dy * direction.normal.y + dz * direction.normal.z
        if (dot > highestDot) {
            highestDot = dot
            result = direction
        }
    }

    return result
}