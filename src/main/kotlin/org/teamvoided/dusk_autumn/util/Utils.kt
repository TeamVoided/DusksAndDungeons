package org.teamvoided.dusk_autumn.util

import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.UniformLootNumberProvider

object Utils {
    fun setCount(x: Number, y: Number) = SetCountLootFunction.builder(uniformNum(x, y))

    fun uniformNum(x: Number, y: Number): UniformLootNumberProvider =
        UniformLootNumberProvider.create(x.toFloat(), y.toFloat())
}