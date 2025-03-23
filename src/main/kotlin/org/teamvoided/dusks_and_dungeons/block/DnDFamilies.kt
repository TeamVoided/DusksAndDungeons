package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks


object DnDFamilies {
    private val CASCADE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.CASCADE_PLANKS)
            .stairs(DnDBlocks.CASCADE_STAIRS)
            .slab(DnDBlocks.CASCADE_SLAB)
            .fence(DnDBlocks.CASCADE_FENCE)
            .fenceGate(DnDBlocks.CASCADE_FENCE_GATE)
            .door(DnDBlocks.CASCADE_DOOR)
            .trapdoor(DnDBlocks.CASCADE_TRAPDOOR)
            .button(DnDBlocks.CASCADE_BUTTON)
            .pressurePlate(DnDBlocks.CASCADE_PRESSURE_PLATE)
            .sign(DnDBlocks.CASCADE_SIGN, DnDBlocks.CASCADE_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build()

    val modelsBlockFamilies = listOf(CASCADE_FAMILY)
    val recipesBlockFamilies = modelsBlockFamilies
    fun init() = Unit
}