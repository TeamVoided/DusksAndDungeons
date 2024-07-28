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
    val MIXED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.MIXED_NETHER_BRICKS)
            .stairs(DuskBlocks.MIXED_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.MIXED_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.MIXED_NETHER_BRICK_WALL)
            .fence(DuskBlocks.MIXED_NETHER_BRICK_FENCE)
            .build()
    val POLISHED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.POLISHED_NETHER_BRICKS)
            .stairs(DuskBlocks.POLISHED_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.POLISHED_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.POLISHED_NETHER_BRICK_WALL)
            .build()
    val POLISHED_RED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.POLISHED_RED_NETHER_BRICKS)
            .stairs(DuskBlocks.POLISHED_RED_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.POLISHED_RED_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.POLISHED_RED_NETHER_BRICK_WALL)
            .build()
    val OVERGROWN_COBBLESTONE_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.OVERGROWN_COBBLESTONE)
            .stairs(DuskBlocks.OVERGROWN_COBBLESTONE_STAIRS)
            .slab(DuskBlocks.OVERGROWN_COBBLESTONE_SLAB)
            .wall(DuskBlocks.OVERGROWN_COBBLESTONE_WALL)
            .build()
    val OVERGROWN_STONE_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.OVERGROWN_STONE_BRICKS)
            .stairs(DuskBlocks.OVERGROWN_STONE_BRICK_STAIRS)
            .slab(DuskBlocks.OVERGROWN_STONE_BRICK_SLAB)
            .wall(DuskBlocks.OVERGROWN_STONE_BRICK_WALL)
            .build()
    val modelsBlockFamilies = listOf(
        CASCADE_FAMILY,
        MIXED_NETHER_BRICKS_FAMILY,
        POLISHED_NETHER_BRICKS_FAMILY,
        POLISHED_RED_NETHER_BRICKS_FAMILY
    )
    val recipesBlockFamilies = modelsBlockFamilies + listOf(
        OVERGROWN_COBBLESTONE_FAMILY,
        OVERGROWN_STONE_BRICKS_FAMILY
    )

    fun init() {}
}