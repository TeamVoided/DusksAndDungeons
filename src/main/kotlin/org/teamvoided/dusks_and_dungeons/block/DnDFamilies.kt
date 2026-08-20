package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.data.BlockFamilies
import net.minecraft.data.BlockFamily
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks


@Suppress("UsePropertyAccessSyntax")
object DnDFamilies {

    val CASCADE_FAMILY: BlockFamily =
        BlockFamilies.familyBuilder(DnDBlocks.CASCADE_PLANKS)
            .stairs(DnDBlocks.CASCADE_STAIRS)
            .slab(DnDBlocks.CASCADE_SLAB)
            .fence(DnDBlocks.CASCADE_FENCE)
            .fenceGate(DnDBlocks.CASCADE_FENCE_GATE)
            .door(DnDBlocks.CASCADE_DOOR)
            .trapdoor(DnDBlocks.CASCADE_TRAPDOOR)
            .button(DnDBlocks.CASCADE_BUTTON)
            .pressurePlate(DnDBlocks.CASCADE_PRESSURE_PLATE)
            .sign(DnDBlocks.CASCADE_SIGN, DnDBlocks.CASCADE_WALL_SIGN)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily()

    val VERDANT_FAMILY: BlockFamily =
        BlockFamilies.familyBuilder(DnDBlocks.VERDANT_PLANKS)
            .stairs(DnDBlocks.VERDANT_STAIRS)
            .slab(DnDBlocks.VERDANT_SLAB)
            .fence(DnDBlocks.VERDANT_FENCE)
            .fenceGate(DnDBlocks.VERDANT_FENCE_GATE)
            .door(DnDBlocks.VERDANT_DOOR)
            .trapdoor(DnDBlocks.VERDANT_TRAPDOOR)
            .button(DnDBlocks.VERDANT_BUTTON)
            .pressurePlate(DnDBlocks.VERDANT_PRESSURE_PLATE)
            .sign(DnDBlocks.VERDANT_SIGN, DnDBlocks.VERDANT_WALL_SIGN)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily()

    val modelsBlockFamilies = listOf(CASCADE_FAMILY)
    val recipesBlockFamilies =  listOf(CASCADE_FAMILY, VERDANT_FAMILY)

    fun init() = Unit

}