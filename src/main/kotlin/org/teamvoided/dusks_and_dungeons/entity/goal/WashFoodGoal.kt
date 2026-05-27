package org.teamvoided.dusks_and_dungeons.entity.goal

import net.minecraft.world.level.block.Blocks
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.core.BlockPos
import net.minecraft.world.level.LevelReader

class WashFoodGoal(mob: PathfinderMob?, speed: Double, range: Int) : MoveToBlockGoal(mob, speed, range) {

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

    override fun canUse(): Boolean = !mob.isSleeping && !mob.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty && super.canUse()

    override fun start() {
        timer = 0
        super.start()
    }
}
