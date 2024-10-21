package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Blocks
import net.minecraft.data.client.model.VariantSettings
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemStack
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.world.StructureWorldAccess

val pi = 3.1415927f
val degToRad = 0.017453292f
val radToDeg = 57.29578f
val rotate45 = 0.785f
val rotate90 = 1.571f
val rotate135 = 2.356f
val rotate180 = 3.142f
val rotate225 = 3.927f
val rotate270 = 4.712f
val rotate315 = 5.498f

val modelDirectionRotation = listOf(
    (Direction.NORTH to VariantSettings.Rotation.R0),
    (Direction.EAST to VariantSettings.Rotation.R90),
    (Direction.SOUTH to VariantSettings.Rotation.R180),
    (Direction.WEST to VariantSettings.Rotation.R270)
)

fun setCount(x: Number, y: Number) = SetCountLootFunction.builder(uniformNum(x, y))

fun uniformNum(x: Number, y: Number): UniformLootNumberProvider =
    UniformLootNumberProvider.create(x.toFloat(), y.toFloat())

fun StructureWorldAccess.placeDebug(
    pos: BlockPos,
    block: Int
) {
    val x = when (block) {
        0 -> Blocks.GLASS
        1 -> Blocks.WHITE_STAINED_GLASS
        2 -> Blocks.LIGHT_GRAY_STAINED_GLASS
        3 -> Blocks.GRAY_STAINED_GLASS
        4 -> Blocks.BLACK_STAINED_GLASS
        5 -> Blocks.BROWN_STAINED_GLASS
        6 -> Blocks.RED_STAINED_GLASS
        7 -> Blocks.ORANGE_STAINED_GLASS
        8 -> Blocks.YELLOW_STAINED_GLASS
        9 -> Blocks.LIME_STAINED_GLASS
        10 -> Blocks.GREEN_STAINED_GLASS
        11 -> Blocks.CYAN_STAINED_GLASS
        12 -> Blocks.LIGHT_BLUE_STAINED_GLASS
        13 -> Blocks.BLUE_STAINED_GLASS
        14 -> Blocks.PURPLE_STAINED_GLASS
        15 -> Blocks.MAGENTA_STAINED_GLASS
        16 -> Blocks.PINK_STAINED_GLASS
        else -> Blocks.TINTED_GLASS
    }.defaultState
    this.setBlockState(pos, x, 2)
}

fun Collection<ItemConvertible>.toItems() = this.map(ItemConvertible::asItem)
fun Collection<ItemConvertible>.toStacks() = this.toItems().map(Item::getDefaultStack)


fun Hand.toSlot() = if (this == Hand.MAIN_HAND) EquipmentSlot.MAINHAND else EquipmentSlot.OFFHAND

fun ProjectileEntity.setShootVelocity(pitch: Float, yaw: Float, roll: Float, speed: Float, modifierXYZ: Float) {
    val f = -MathHelper.sin(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
    val g = -MathHelper.sin((pitch + roll) * (Math.PI.toFloat() / 180))
    val h = MathHelper.cos(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
    this.setVelocity(f.toDouble(), g.toDouble(), h.toDouble(), speed, modifierXYZ)
}

fun ItemStack.hasEnchantment(enchantment: RegistryKey<Enchantment>): Boolean =
    this.enchantments.enchantments.any { it.isRegistryKey(enchantment) }
