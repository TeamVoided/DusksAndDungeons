package org.teamvoided.dusks_and_dungeons.init.misc

import net.minecraft.data.BlockFamilies.familyBuilder
import net.minecraft.data.BlockFamily
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks


object DnDFamilies {

    val CASCADE_FAMILY: BlockFamily =
        familyBuilder(DnDBlocks.CASCADE_PLANKS)
            .stairs(DnDBlocks.CASCADE_STAIRS)
            .slab(DnDBlocks.CASCADE_SLAB)
            .wall(DnDBlocks.CASCADE_WALL)
            .fence(DnDBlocks.CASCADE_FENCE)
            .fenceGate(DnDBlocks.CASCADE_FENCE_GATE)
            .door(DnDBlocks.CASCADE_DOOR)
            .trapdoor(DnDBlocks.CASCADE_TRAPDOOR)
            .button(DnDBlocks.CASCADE_BUTTON)
            .pressurePlate(DnDBlocks.CASCADE_PRESSURE_PLATE)
            .sign(DnDBlocks.CASCADE_SIGN, DnDBlocks.CASCADE_WALL_SIGN)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .build()


    val SYPIA_FAMILY =
        familyBuilder(DnDBlocks.SYPIA_PLANKS)
            .stairs(DnDBlocks.SYPIA_STAIRS)
            .slab(DnDBlocks.SYPIA_SLAB)
            .wall(DnDBlocks.SYPIA_WALL)
            .fence(DnDBlocks.SYPIA_FENCE)
            .fenceGate(DnDBlocks.SYPIA_FENCE_GATE)
            .door(DnDBlocks.SYPIA_DOOR)
            .trapdoor(DnDBlocks.SYPIA_TRAPDOOR)
            .button(DnDBlocks.SYPIA_BUTTON)
            .pressurePlate(DnDBlocks.SYPIA_PRESSURE_PLATE)
            .sign(DnDBlocks.SYPIA_SIGN, DnDBlocks.SYPIA_WALL_SIGN)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .build()

    val VERDANT_FAMILY =
        familyBuilder(DnDBlocks.VERDANT_PLANKS)
            .stairs(DnDBlocks.VERDANT_STAIRS)
            .slab(DnDBlocks.VERDANT_SLAB)
            .wall(DnDBlocks.VERDANT_WALL)
            .fence(DnDBlocks.VERDANT_FENCE)
            .fenceGate(DnDBlocks.VERDANT_FENCE_GATE)
            .door(DnDBlocks.VERDANT_DOOR)
            .trapdoor(DnDBlocks.VERDANT_TRAPDOOR)
            .button(DnDBlocks.VERDANT_BUTTON)
            .pressurePlate(DnDBlocks.VERDANT_PRESSURE_PLATE)
            .sign(DnDBlocks.VERDANT_SIGN, DnDBlocks.VERDANT_WALL_SIGN)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .build()

    val SMOOTH_STONE =
        familyBuilder(Blocks.SMOOTH_STONE)
            .stairs(DnDBlocks.SMOOTH_STONE_STAIR)
            .slab(Blocks.SMOOTH_STONE_SLAB)
            .wall(DnDBlocks.SMOOTH_STONE_WALL)
            .build()

    val modelsBlockFamilies = listOf(CASCADE_FAMILY, SYPIA_FAMILY)
    val recipesBlockFamilies = listOf(CASCADE_FAMILY, SYPIA_FAMILY, VERDANT_FAMILY)

    fun init() {
    }

    @Suppress("UsePropertyAccessSyntax")
    fun BlockFamily.Builder.build(): BlockFamily = getFamily()

}