package org.teamvoided.dusks_and_dungeons.entity.goal

import net.minecraft.block.Blocks
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldView

class WashFoodGoal(mob: PathAwareEntity?, speed: Double, range: Int) : MoveToTargetPosGoal(mob, speed, range) {

    private var timer: Int = 0

    override fun isTargetPos(world: WorldView, pos: BlockPos?): Boolean {
        return world.getBlockState(pos).isOf(Blocks.WATER)
    }

    override fun tick() {
        if (hasReached()) {
            timer++
            // TODO start anim
            if (timer >= 200) {
                // TODO end anim
            }
        }
        super.tick()
    }

    override fun canStart(): Boolean = !mob.isSleeping && !mob.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty && super.canStart()

    override fun start() {
        timer = 0
        super.start()
    }
}
