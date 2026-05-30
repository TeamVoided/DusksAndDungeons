package org.teamvoided.dusks_and_dungeons.entity.goal

import net.minecraft.world.level.block.Blocks
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal
import net.minecraft.core.BlockPos
import net.minecraft.world.level.LevelReader
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class WashFoodGoal(val raccoon: RaccoonEntity, speed: Double, range: Int) : MoveToBlockGoal(raccoon, speed, range) {

    private var timer: Int = 0

    override fun isValidTarget(world: LevelReader, pos: BlockPos): Boolean {
        return world.getBlockState(pos).`is`(Blocks.WATER)
    }

    override fun tick() {
        if (isReachedTarget) {
            timer++
            // TODO start anim
            if (timer >= 200) {
                // TODO end anim
            }
        }
        super.tick()
    }

    override fun canUse(): Boolean = !raccoon.isSleeping && raccoon.isFood(raccoon.getHeldItem()) && super.canUse()

    override fun start() {
        timer = 0
        super.start()
    }
}
