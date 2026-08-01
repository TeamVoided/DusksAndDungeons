@file:Suppress("unused")

package org.teamvoided.voidlib.helpers.mc

import net.minecraft.core.Direction
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.phys.Vec3

fun InteractionHand.toSlot() = if (this == InteractionHand.MAIN_HAND) EquipmentSlot.MAINHAND else EquipmentSlot.OFFHAND

fun ItemStack.hasEnchantment(enchantment: ResourceKey<Enchantment>): Boolean =
    this.enchantments.keySet().any { it.`is`(enchantment) }

fun Iterable<Vec3>.rotateFlat90(times: Int): Iterable<Vec3> = map { it.rotateFlat90(times) }

fun Vec3.rotateFlat90(times: Int): Vec3 {
    if (times == 0) return this
    var vector = this
    repeat(times) { vector = Vec3(1 - vector.z, y, vector.x) }
    return vector
}

fun Vec3.rotateOnAxis(axis: Direction.Axis): Vec3 = when (axis) {
    Direction.Axis.X -> Vec3(y, 1 - x, z)
    Direction.Axis.Y -> Vec3(1 - z, y, x)
    Direction.Axis.Z -> Vec3(x, 1 - z, y)
}

fun Direction.Axis.opposite() = when (this) {
    Direction.Axis.X -> Direction.Axis.Z
    Direction.Axis.Y -> Direction.Axis.Y
    Direction.Axis.Z -> Direction.Axis.X
}

fun Direction.Axis.isX() = this == Direction.Axis.X
fun Direction.Axis.isY() = this == Direction.Axis.Y
fun Direction.Axis.isZ() = this == Direction.Axis.Z

fun Vec3.asString(): String = "(x:${x}, z:${z})"

fun ServerLevel.getLootTable(key: ResourceKey<LootTable>): LootTable =
    this.server.reloadableRegistries().getLootTable(key)