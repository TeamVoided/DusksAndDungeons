package org.teamvoided.dusks_and_dungeons.entity.goal

import net.minecraft.world.entity.EntityEvent
import org.teamvoided.dusks_and_dungeons.entity.RaccoonEntity

class ClaimBarrelGoal(raccoon: RaccoonEntity, speed: Double, range: Int) :
    MoveToBarrelGoal(raccoon, speed, range) {

    override fun onTargetReached() {
        raccoon.barrelPos = blockPos
        raccoon.level().broadcastEntityEvent(raccoon, EntityEvent.VILLAGER_HAPPY)
    }

    override fun canUse(): Boolean {
        return raccoon.barrelPos == RaccoonEntity.DEFAULT_BARREL_POS && super.canUse()
    }
}
