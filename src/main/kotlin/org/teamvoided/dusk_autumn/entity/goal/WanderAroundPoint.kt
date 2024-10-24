package org.teamvoided.dusk_autumn.entity.goal

import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import org.teamvoided.dusk_autumn.entity.PifflingPumpkinEntity

open class WanderAroundPoint(
    mob: PifflingPumpkinEntity,
    val pos: BlockPos?,
    speed: Double,
    probability: Int = CHANCE
) : WanderAroundGoal(mob, speed, probability) {

    override fun getWanderTarget(): Vec3d? {
        return pos?.ofBottomCenter()
    }

    companion object {
        const val CHANCE = 120
    }
}