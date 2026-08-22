package org.teamvoided.dusks_and_dungeons.item

import net.minecraft.core.dispenser.DispenseItemBehavior
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrowableItemStackDispenseBehavior
import org.teamvoided.dusks_and_dungeons.item.throwable.ThrownItemDefinition

object DnDDispenserBehaviour {

    fun init() = Unit

    @JvmStatic
    fun getCustomDispenseMethod(level: Level, stack: ItemStack): DispenseItemBehavior? {
        ThrownItemDefinition.getItemDefinition(stack) ?: return null
        return ThrowableItemStackDispenseBehavior
    }

}