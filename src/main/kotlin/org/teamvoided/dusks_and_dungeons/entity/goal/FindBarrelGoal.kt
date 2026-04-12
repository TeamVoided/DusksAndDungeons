package org.teamvoided.dusks_and_dungeons.entity.goal

import net.minecraft.block.Blocks
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldView
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class FindBarrelGoal(private val raccoon: RaccoonEntity, speed: Double, range: Int) :
    MoveToTargetPosGoal(raccoon, speed, range) {

    override fun isTargetPos(world: WorldView, pos: BlockPos?): Boolean {
        return world.getBlockState(pos).isOf(Blocks.BARREL)
    }

    override fun getDesiredSquaredDistanceToTarget(): Double = 5.0

    override fun tick() {
        if (hasReached()) {
            if (raccoon.world.getBlockState(targetPos).isOf(Blocks.BARREL)) {
                raccoon.barrelPos = targetPos
            }
        }
        super.tick()
    }

    override fun canStart(): Boolean {
        return !raccoon.isSleeping && raccoon.barrelPos == BlockPos.ORIGIN && super.canStart()
    }

}
