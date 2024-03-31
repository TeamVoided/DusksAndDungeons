package org.teamvoided.dusk_autumn.util

import net.minecraft.block.Blocks
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.math.BlockPos
import net.minecraft.world.StructureWorldAccess

object Utils {
    fun setCount(x: Number, y: Number) = SetCountLootFunction.builder(uniformNum(x, y))

    fun uniformNum(x: Number, y: Number): UniformLootNumberProvider =
        UniformLootNumberProvider.create(x.toFloat(), y.toFloat())

    fun StructureWorldAccess.placeDebug(
        pos: BlockPos,
        block: Int
    ) {
        val x = when (block) {
            1 -> Blocks.GREEN_STAINED_GLASS
            2 -> Blocks.YELLOW_STAINED_GLASS
            3 -> Blocks.RED_STAINED_GLASS
            else -> Blocks.BLUE_STAINED_GLASS
        }.defaultState

        this.setBlockState(pos, x, 2)
    }
}