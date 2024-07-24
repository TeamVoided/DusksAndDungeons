package org.teamvoided.dusk_autumn.block

import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import org.teamvoided.dusk_autumn.init.DuskBlocks


object DuskBlockFamilies {
    val CASCADE_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.CASCADE_PLANKS)
            .stairs(DuskBlocks.CASCADE_STAIRS)
            .slab(DuskBlocks.CASCADE_SLAB)
            .fence(DuskBlocks.CASCADE_FENCE)
            .door(DuskBlocks.CASCADE_DOOR)
            .trapdoor(DuskBlocks.CASCADE_TRAPDOOR)
            .fenceGate(DuskBlocks.CASCADE_FENCE_GATE)
            .button(DuskBlocks.CASCADE_BUTTON)
            .pressurePlate(DuskBlocks.CASCADE_PRESSURE_PLATE)
            .sign(DuskBlocks.CASCADE_SIGN, DuskBlocks.CASCADE_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build()
    val allBlockFamilies = listOf(CASCADE_FAMILY)

    fun init() {}
}