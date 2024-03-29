package org.teamvoided.dusk_autumn.item

import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.DyeableItem
import net.minecraft.item.Equippable
import net.minecraft.item.Item

class FarmersHat(settings: Item.Settings) : Item(settings), Equippable, DyeableItem {
    override fun getPreferredSlot(): EquipmentSlot {
        return EquipmentSlot.HEAD
    }
}