package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.StructureWorldAccess

val pi = 3.1415927f
val rotate45 = 0.785f
val rotate90 = 1.571f
val rotate135 = 2.356f
val rotate180 = 3.142f
val rotate225 = 3.927f
val rotate270 = 4.712f
val rotate315 = 5.498f

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
