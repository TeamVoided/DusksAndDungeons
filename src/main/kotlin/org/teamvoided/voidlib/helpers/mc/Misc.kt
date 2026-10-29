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

fun InteractionHand.toSlot(): EquipmentSlot {
    return if (this == InteractionHand.MAIN_HAND) EquipmentSlot.MAINHAND else EquipmentSlot.OFFHAND
}

fun ItemStack.hasEnchantment(enchantment: ResourceKey<Enchantment>): Boolean {
    return enchantments.keySet().any { it.`is`(enchantment) }
}

fun Iterable<Vec3>.rotateFlat90(times: Int): Iterable<Vec3> = map { it.rotateFlat90(times) }

fun Vec3.rotateFlat90(times: Int): Vec3 {
    val times = times % 4 // make sure the op only runs 3 times at max
    if (times == 0) return this
    var vector = this
    repeat(times) { vector = Vec3(1 - vector.z, y, vector.x) }
    return vector
}

// TODO i dont know if this is CW it might be CCW
fun Vec3.rotateCW(times: Int): Vec3 {
    val times = times % 4 // make sure the op only runs 3 times at max
    if (times == 0) return this
    var vector = this
    repeat(times) { vector = Vec3(vector.z, y, 1 - vector.x) }
    return vector
}

fun Vec3.rotateOnAxis(axis: Direction.Axis): Vec3 = when (axis) {
    Direction.Axis.X -> Vec3(y, 1 - x, z)
    Direction.Axis.Y -> Vec3(1 - z, y, x)
    Direction.Axis.Z -> Vec3(x, 1 - z, y)
}

fun Direction.Axis.opposite(): Direction.Axis = when (this) {
    Direction.Axis.X -> Direction.Axis.Z
    Direction.Axis.Y -> Direction.Axis.Y
    Direction.Axis.Z -> Direction.Axis.X
}

fun Direction.Axis.isX(): Boolean = this == Direction.Axis.X
fun Direction.Axis.isY(): Boolean = this == Direction.Axis.Y
fun Direction.Axis.isZ(): Boolean = this == Direction.Axis.Z

fun Vec3.asString(): String = "(x:${x}, z:${z})"

fun ServerLevel.getLootTable(key: ResourceKey<LootTable>): LootTable {
    return server.reloadableRegistries().getLootTable(key)
}
