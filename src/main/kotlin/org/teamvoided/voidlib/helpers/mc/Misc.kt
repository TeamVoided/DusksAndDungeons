@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.mc

import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.loot.LootTable
import net.minecraft.registry.RegistryKey
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Hand
import net.minecraft.util.math.Vec3d

fun Hand.toSlot() = if (this == Hand.MAIN_HAND) EquipmentSlot.MAINHAND else EquipmentSlot.OFFHAND

fun ItemStack.hasEnchantment(enchantment: RegistryKey<Enchantment>): Boolean =
    this.enchantments.enchantments.any { it.isRegistryKey(enchantment) }

fun Iterable<Vec3d>.rotateFlat90(times: Int): Iterable<Vec3d> =
    this.map { it.rotateFlat90(times) }

fun Vec3d.rotateFlat90(times: Int): Vec3d {
    if (times == 0) return this
    var vector = this
    repeat(times) { vector = Vec3d(1 - vector.z, y, vector.x) }
    return vector
}

fun ServerWorld.getLootTable(key: RegistryKey<LootTable>): LootTable = this.server.method_58576().getLootTable(key)