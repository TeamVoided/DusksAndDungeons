package org.teamvoided.dusks_and_dungeons.entity.goal

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

abstract class MoveToBarrelGoal(val raccoon: RaccoonEntity, speed: Double, range: Int) :
    MoveToBlockGoal(raccoon, speed, range) {

    var timer = 0

    override fun isValidTarget(world: LevelReader, pos: BlockPos): Boolean {
        return world.getBlockState(pos).`is`(Blocks.BARREL)
    }

    override fun acceptedDistance(): Double = 5.0

    override fun tick() {
        if (isReachedTarget) {
            if (timer >= 40) {
                if (raccoon.level().getBlockState(blockPos).`is`(Blocks.BARREL)) {
                    onTargetReached()
                }
            }
            timer++
        }
        super.tick()
    }

    abstract fun onTargetReached()

    override fun canContinueToUse(): Boolean {
        return (!isReachedTarget || timer <= 40) && super.canContinueToUse()
    }

    override fun canUse(): Boolean {
        return !raccoon.isSleeping && super.canUse()
    }

    override fun start() {
        timer = 0
        super.start()
    }
}
