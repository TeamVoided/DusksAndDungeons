package org.teamvoided.dusk_autumn.item

import net.minecraft.block.CarvedPumpkinBlock
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Equippable
import net.minecraft.item.Item

class FarmersHat(settings: Item.Settings) : Item(settings), Equippable {
    override fun getPreferredSlot(): EquipmentSlot {
        return EquipmentSlot.HEAD
    }
}