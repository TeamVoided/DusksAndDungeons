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
            .fenceGate(DuskBlocks.CASCADE_FENCE_GATE)
            .door(DuskBlocks.CASCADE_DOOR)
            .trapdoor(DuskBlocks.CASCADE_TRAPDOOR)
            .button(DuskBlocks.CASCADE_BUTTON)
            .pressurePlate(DuskBlocks.CASCADE_PRESSURE_PLATE)
            .sign(DuskBlocks.CASCADE_SIGN, DuskBlocks.CASCADE_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build()
    val PINE_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.PINE_PLANKS)
            .stairs(DuskBlocks.PINE_STAIRS)
            .slab(DuskBlocks.PINE_SLAB)
            .fence(DuskBlocks.PINE_FENCE)
            .fenceGate(DuskBlocks.PINE_FENCE_GATE)
//            .door(DuskBlocks.PINE_DOOR)
//            .trapdoor(DuskBlocks.PINE_TRAPDOOR)
//            .button(DuskBlocks.PINE_BUTTON)
//            .pressurePlate(DuskBlocks.PINE_PRESSURE_PLATE)
//            .sign(DuskBlocks.PINE_SIGN, DuskBlocks.PINE_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
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
    val MIXED_RED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.MIXED_NETHER_BRICKS)
            .cracked(DuskBlocks.CRACKED_MIXED_NETHER_BRICKS)
            .stairs(DuskBlocks.MIXED_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.MIXED_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.MIXED_NETHER_BRICK_WALL)
            .chiseled(DuskBlocks.CHISELED_MIXED_NETHER_BRICKS)
            .build()
    val BLUE_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.BLUE_NETHER_BRICKS)
            .cracked(DuskBlocks.CRACKED_BLUE_NETHER_BRICKS)
            .stairs(DuskBlocks.BLUE_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.BLUE_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.BLUE_NETHER_BRICK_WALL)
            .polished(DuskBlocks.POLISHED_BLUE_NETHER_BRICKS)
            .chiseled(DuskBlocks.CHISELED_BLUE_NETHER_BRICKS)
            .build()
    val POLISHED_BLUE_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.POLISHED_BLUE_NETHER_BRICKS)
            .stairs(DuskBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.POLISHED_BLUE_NETHER_BRICK_WALL)
            .build()
    val MIXED_BLUE_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.MIXED_BLUE_NETHER_BRICKS)
            .cracked(DuskBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS)
            .stairs(DuskBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.MIXED_BLUE_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.MIXED_BLUE_NETHER_BRICK_WALL)
            .chiseled(DuskBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS)
            .build()
    val GREY_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.GREY_NETHER_BRICKS)
            .cracked(DuskBlocks.CRACKED_GREY_NETHER_BRICKS)
            .stairs(DuskBlocks.GREY_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.GREY_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.GREY_NETHER_BRICK_WALL)
            .polished(DuskBlocks.POLISHED_GREY_NETHER_BRICKS)
            .chiseled(DuskBlocks.CHISELED_GREY_NETHER_BRICKS)
            .build()
    val POLISHED_GREY_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.POLISHED_GREY_NETHER_BRICKS)
            .stairs(DuskBlocks.POLISHED_GREY_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.POLISHED_GREY_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.POLISHED_GREY_NETHER_BRICK_WALL)
            .build()
    val MIXED_GREY_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DuskBlocks.MIXED_GREY_NETHER_BRICKS)
            .cracked(DuskBlocks.CRACKED_MIXED_GREY_NETHER_BRICKS)
            .stairs(DuskBlocks.MIXED_GREY_NETHER_BRICK_STAIRS)
            .slab(DuskBlocks.MIXED_GREY_NETHER_BRICK_SLAB)
            .wall(DuskBlocks.MIXED_GREY_NETHER_BRICK_WALL)
            .chiseled(DuskBlocks.CHISELED_MIXED_GREY_NETHER_BRICKS)
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
        PINE_FAMILY,
        POLISHED_NETHER_BRICKS_FAMILY,
        POLISHED_RED_NETHER_BRICKS_FAMILY,
        MIXED_RED_NETHER_BRICKS_FAMILY,
        BLUE_NETHER_BRICKS_FAMILY,
        POLISHED_BLUE_NETHER_BRICKS_FAMILY,
        MIXED_BLUE_NETHER_BRICKS_FAMILY,
        GREY_NETHER_BRICKS_FAMILY,
        POLISHED_GREY_NETHER_BRICKS_FAMILY,
        MIXED_GREY_NETHER_BRICKS_FAMILY
    )
    val recipesBlockFamilies = modelsBlockFamilies + listOf(
        OVERGROWN_COBBLESTONE_FAMILY,
        OVERGROWN_STONE_BRICKS_FAMILY
    )

    fun init() {}
}