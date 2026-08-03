package org.teamvoided.dusks_and_dungeons.util

import net.minecraft.core.BlockPos
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.ProjectileUtil
import net.minecraft.world.level.ClipContext
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.HitResult
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.EntityCollisionContext
import kotlin.math.max
import kotlin.math.sqrt


fun Player.getHitResult(): HitResult = getHitResult(blockInteractionRange(), entityInteractionRange())

fun Entity.getHitResult(blockRange: Double, entityRange: Double, partialTicks: Float = 0f): HitResult {
    var maxDistance = max(blockRange, entityRange)
    var maxDistanceSq = Mth.square(maxDistance)
    val from = getEyePosition(partialTicks)
    val blockHitResult = pick(maxDistance, partialTicks, false)
    val blockDistanceSq = blockHitResult.getLocation().distanceToSqr(from)
    if (blockHitResult.type != HitResult.Type.MISS) {
        maxDistanceSq = blockDistanceSq
        maxDistance = sqrt(blockDistanceSq)
    }

    val direction = getViewVector(partialTicks)
    val to = from.add(direction.x * maxDistance, direction.y * maxDistance, direction.z * maxDistance)
    val overlap = 1.0
    val box = boundingBox.expandTowards(direction.scale(maxDistance)).inflate(overlap)
    val entityHitResult =
        ProjectileUtil.getEntityHitResult(this, from, to, box, CAN_BE_PICKED, maxDistanceSq)
    return if (entityHitResult != null && entityHitResult.getLocation().distanceToSqr(from) < blockDistanceSq)
        filterHitResult(entityHitResult, from, entityRange)
    else
        filterHitResult(blockHitResult, from, blockRange)
}

fun filterHitResult(hitResult: HitResult, from: Vec3, maxRange: Double): HitResult {
    val hitLocation = hitResult.getLocation()
    if (!hitLocation.closerThan(from, maxRange)) {
        val location = hitResult.getLocation()
        val direction = getApproximateNearest(location.x - from.x, location.y - from.y, location.z - from.z)
        return BlockHitResult.miss(location, direction, BlockPos.containing(location))
    } else {
        return hitResult
    }
}


fun Player.getNonRecursiveHitResult(): HitResult =
    getNonRecursiveHitResult(blockInteractionRange(), entityInteractionRange())

fun Entity.getNonRecursiveHitResult(blockRange: Double, entityRange: Double, partialTicks: Float = 0f): HitResult {
    var maxDistance = max(blockRange, entityRange)
    var maxDistanceSq = Mth.square(maxDistance)
    val from = getEyePosition(partialTicks)
    val blockHitResult = nonRecursivePick(maxDistance, partialTicks, false)
    val blockDistanceSq = blockHitResult.getLocation().distanceToSqr(from)
    if (blockHitResult.type != HitResult.Type.MISS) {
        maxDistanceSq = blockDistanceSq
        maxDistance = sqrt(blockDistanceSq)
    }

    val direction = getViewVector(partialTicks)
    val to = from.add(direction.x * maxDistance, direction.y * maxDistance, direction.z * maxDistance)
    val overlap = 1.0
    val box = boundingBox.expandTowards(direction.scale(maxDistance)).inflate(overlap)
    val entityHitResult =
        ProjectileUtil.getEntityHitResult(this, from, to, box, CAN_BE_PICKED, maxDistanceSq)
    return if (entityHitResult != null && entityHitResult.getLocation().distanceToSqr(from) < blockDistanceSq)
        filterHitResult(entityHitResult, from, entityRange)
    else
        filterHitResult(blockHitResult, from, blockRange)
}

fun Entity.nonRecursivePick(range: Double, a: Float, withLiquids: Boolean): HitResult {
    val from = getEyePosition(a)
    val viewVec = getViewVector(a)
    val to = from.add(viewVec.x * range, viewVec.y * range, viewVec.z * range)
    val ctx = CollisionContext.of(this) as EntityCollisionContext
    ctx.setRecursive(true)
    return this.level().clip(
        ClipContext(
            from, to, ClipContext.Block.OUTLINE, if (withLiquids) ClipContext.Fluid.ANY else ClipContext.Fluid.NONE, ctx
        )
    )
}
