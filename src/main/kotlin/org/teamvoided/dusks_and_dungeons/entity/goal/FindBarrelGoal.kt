package org.teamvoided.dusks_and_dungeons.entity.goal

import net.minecraft.world.level.block.Blocks
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal
import net.minecraft.core.BlockPos
import net.minecraft.world.level.LevelReader
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class FindBarrelGoal(private val raccoon: RaccoonEntity, speed: Double, range: Int) :
    MoveToBlockGoal(raccoon, speed, range) {

    override fun isValidTarget(world: LevelReader, pos: BlockPos): Boolean {
        return world.getBlockState(pos).`is`(Blocks.BARREL)
    }

    override fun acceptedDistance(): Double = 5.0

    override fun tick() {
        if (isReachedTarget) {
            if (raccoon.level().getBlockState(blockPos).`is`(Blocks.BARREL)) {
                raccoon.barrelPos = blockPos
            }
        }
        super.tick()
    }

    override fun canUse(): Boolean {
        return !raccoon.isSleeping && raccoon.barrelPos == BlockPos.ZERO && super.canUse()
    }
}
