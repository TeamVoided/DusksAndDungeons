package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Blocks
import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import org.teamvoided.dusk_autumn.init.DnDBlocks


object DnDFamilies {
    val CASCADE_FAMILY: BlockFamily =
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

    //    val PINE_FAMILY: BlockFamily =
//        BlockFamilies.register(DnDBlocks.PINE_PLANKS)
//            .stairs(DnDBlocks.PINE_STAIRS)
//            .slab(DnDBlocks.PINE_SLAB)
//            .fence(DnDBlocks.PINE_FENCE)
//            .fenceGate(DnDBlocks.PINE_FENCE_GATE)
////            .door(DnDBlocks.PINE_DOOR)
////            .trapdoor(DnDBlocks.PINE_TRAPDOOR)
////            .button(DnDBlocks.PINE_BUTTON)
////            .pressurePlate(DnDBlocks.PINE_PRESSURE_PLATE)
////            .sign(DnDBlocks.PINE_SIGN, DnDBlocks.PINE_WALL_SIGN)
//            .group("wooden")
//            .unlockCriterionName("has_planks")
//            .build()
    val BONEWOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.BONEWOOD_PLANKS)
            .stairs(DnDBlocks.BONEWOOD_STAIRS)
            .slab(DnDBlocks.BONEWOOD_SLAB)
            .fence(DnDBlocks.BONEWOOD_FENCE)
            .fenceGate(DnDBlocks.BONEWOOD_FENCE_GATE)
            .door(DnDBlocks.BONEWOOD_DOOR)
            .trapdoor(DnDBlocks.BONEWOOD_TRAPDOOR)
//            .button(DnDBlocks.BONEWOOD_BUTTON)
//            .pressurePlate(DnDBlocks.BONEWOOD_PRESSURE_PLATE)
//            .sign(DnDBlocks.BONEWOOD_SIGN, DnDBlocks.BONEWOOD_WALL_SIGN)
            .group("bonewood")
            .unlockCriterionName("has_bonewood")
            .build()
    val WITHERING_BONEWOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.WITHERING_BONEWOOD_PLANKS)
            .stairs(DnDBlocks.WITHERING_BONEWOOD_STAIRS)
            .slab(DnDBlocks.WITHERING_BONEWOOD_SLAB)
            .fence(DnDBlocks.WITHERING_BONEWOOD_FENCE)
            .fenceGate(DnDBlocks.WITHERING_BONEWOOD_FENCE_GATE)
            .door(DnDBlocks.WITHERING_BONEWOOD_DOOR)
            .trapdoor(DnDBlocks.WITHERING_BONEWOOD_TRAPDOOR)
//            .button(DnDBlocks.WITHERING_BONEWOOD_BUTTON)
//            .pressurePlate(DnDBlocks.WITHERING_BONEWOOD_PRESSURE_PLATE)
//            .sign(DnDBlocks.WITHERING_BONEWOOD_SIGN, DnDBlocks.WITHERING_BONEWOOD_WALL_SIGN)
            .group("bonewood")
            .unlockCriterionName("has_bonewood")
            .build()

    val OAK_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.OAK_WOOD)
            .stairs(DnDBlocks.OAK_WOOD_STAIRS)
            .slab(DnDBlocks.OAK_WOOD_SLAB)
            .wall(DnDBlocks.OAK_WOOD_WALL)
            .build()
    val SPRUCE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.SPRUCE_WOOD)
            .stairs(DnDBlocks.SPRUCE_WOOD_STAIRS)
            .slab(DnDBlocks.SPRUCE_WOOD_SLAB)
            .wall(DnDBlocks.SPRUCE_WOOD_WALL)
            .build()
    val BIRCH_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.BIRCH_WOOD)
            .stairs(DnDBlocks.BIRCH_WOOD_STAIRS)
            .slab(DnDBlocks.BIRCH_WOOD_SLAB)
            .wall(DnDBlocks.BIRCH_WOOD_WALL)
            .build()
    val JUNGLE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.JUNGLE_WOOD)
            .stairs(DnDBlocks.JUNGLE_WOOD_STAIRS)
            .slab(DnDBlocks.JUNGLE_WOOD_SLAB)
            .wall(DnDBlocks.JUNGLE_WOOD_WALL)
            .build()
    val ACACIA_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.ACACIA_WOOD)
            .stairs(DnDBlocks.ACACIA_WOOD_STAIRS)
            .slab(DnDBlocks.ACACIA_WOOD_SLAB)
            .wall(DnDBlocks.ACACIA_WOOD_WALL)
            .build()
    val DARK_OAK_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.DARK_OAK_WOOD)
            .stairs(DnDBlocks.DARK_OAK_WOOD_STAIRS)
            .slab(DnDBlocks.DARK_OAK_WOOD_SLAB)
            .wall(DnDBlocks.DARK_OAK_WOOD_WALL)
            .build()
    val MANGROVE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.MANGROVE_WOOD)
            .stairs(DnDBlocks.MANGROVE_WOOD_STAIRS)
            .slab(DnDBlocks.MANGROVE_WOOD_SLAB)
            .wall(DnDBlocks.MANGROVE_WOOD_WALL)
            .build()
    val CHERRY_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.CHERRY_WOOD)
            .stairs(DnDBlocks.CHERRY_WOOD_STAIRS)
            .slab(DnDBlocks.CHERRY_WOOD_SLAB)
            .wall(DnDBlocks.CHERRY_WOOD_WALL)
            .build()
    val CASCADE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.CASCADE_WOOD)
            .stairs(DnDBlocks.CASCADE_WOOD_STAIRS)
            .slab(DnDBlocks.CASCADE_WOOD_SLAB)
            .wall(DnDBlocks.CASCADE_WOOD_WALL)
            .build()
    val CRIMSON_HYPHAE_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.CRIMSON_HYPHAE)
            .stairs(DnDBlocks.CRIMSON_HYPHAE_STAIRS)
            .slab(DnDBlocks.CRIMSON_HYPHAE_SLAB)
            .wall(DnDBlocks.CRIMSON_HYPHAE_WALL)
            .build()
    val WARPED_HYPHAE_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.WARPED_HYPHAE)
            .stairs(DnDBlocks.WARPED_HYPHAE_STAIRS)
            .slab(DnDBlocks.WARPED_HYPHAE_SLAB)
            .wall(DnDBlocks.WARPED_HYPHAE_WALL)
            .build()

    val POLISHED_STONE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.POLISHED_STONE)
            .stairs(DnDBlocks.POLISHED_STONE_STAIRS)
            .slab(DnDBlocks.POLISHED_STONE_SLAB)
            .wall(DnDBlocks.POLISHED_STONE_WALL)
            .build()
    val MOSSY_POLISHED_STONE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.MOSSY_POLISHED_STONE)
            .stairs(DnDBlocks.MOSSY_POLISHED_STONE_STAIRS)
            .slab(DnDBlocks.MOSSY_POLISHED_STONE_SLAB)
            .wall(DnDBlocks.MOSSY_POLISHED_STONE_WALL)
            .build()
    val OVERGROWN_POLISHED_STONE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.OVERGROWN_POLISHED_STONE)
            .stairs(DnDBlocks.OVERGROWN_POLISHED_STONE_STAIRS)
            .slab(DnDBlocks.OVERGROWN_POLISHED_STONE_SLAB)
            .wall(DnDBlocks.OVERGROWN_POLISHED_STONE_WALL)
            .build()
    val OVERGROWN_COBBLESTONE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.OVERGROWN_COBBLESTONE)
            .stairs(DnDBlocks.OVERGROWN_COBBLESTONE_STAIRS)
            .slab(DnDBlocks.OVERGROWN_COBBLESTONE_SLAB)
            .wall(DnDBlocks.OVERGROWN_COBBLESTONE_WALL)
            .build()
    val OVERGROWN_STONE_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.OVERGROWN_STONE_BRICKS)
            .stairs(DnDBlocks.OVERGROWN_STONE_BRICK_STAIRS)
            .slab(DnDBlocks.OVERGROWN_STONE_BRICK_SLAB)
            .wall(DnDBlocks.OVERGROWN_STONE_BRICK_WALL)
            .build()
    val SNOWY_STONE_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.SNOWY_STONE_BRICKS)
            .stairs(DnDBlocks.SNOWY_STONE_BRICK_STAIRS)
            .slab(DnDBlocks.SNOWY_STONE_BRICK_SLAB)
            .wall(DnDBlocks.SNOWY_STONE_BRICK_WALL)
            .build()

    val ICE_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.ICE_BRICKS)
            .stairs(DnDBlocks.ICE_BRICK_STAIRS)
            .slab(DnDBlocks.ICE_BRICK_SLAB)
            .wall(DnDBlocks.ICE_BRICK_WALL)
            .build()
    val ICE_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.ICE)
            .stairs(DnDBlocks.ICE_STAIRS)
            .slab(DnDBlocks.ICE_SLAB)
            .wall(DnDBlocks.ICE_WALL)
            .build()
    val PACKED_ICE_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.PACKED_ICE_BRICKS)
            .stairs(DnDBlocks.PACKED_ICE_BRICK_STAIRS)
            .slab(DnDBlocks.PACKED_ICE_BRICK_SLAB)
            .wall(DnDBlocks.PACKED_ICE_BRICK_WALL)
            .build()
    val PACKED_ICE_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.PACKED_ICE)
            .stairs(DnDBlocks.PACKED_ICE_STAIRS)
            .slab(DnDBlocks.PACKED_ICE_SLAB)
            .wall(DnDBlocks.PACKED_ICE_WALL)
            .build()
    val BLUE_ICE_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.BLUE_ICE_BRICKS)
            .stairs(DnDBlocks.BLUE_ICE_BRICK_STAIRS)
            .slab(DnDBlocks.BLUE_ICE_BRICK_SLAB)
            .wall(DnDBlocks.BLUE_ICE_BRICK_WALL)
            .build()
    val BLUE_ICE_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.BLUE_ICE)
            .stairs(DnDBlocks.BLUE_ICE_STAIRS)
            .slab(DnDBlocks.BLUE_ICE_SLAB)
            .wall(DnDBlocks.BLUE_ICE_WALL)
            .build()

    val NETHERRACK_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.NETHERRACK)
            .stairs(DnDBlocks.NETHERRACK_STAIRS)
            .slab(DnDBlocks.NETHERRACK_SLAB)
            .wall(DnDBlocks.NETHERRACK_WALL)
            .build()
    val POLISHED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.POLISHED_NETHER_BRICKS)
            .stairs(DnDBlocks.POLISHED_NETHER_BRICK_STAIRS)
            .slab(DnDBlocks.POLISHED_NETHER_BRICK_SLAB)
            .wall(DnDBlocks.POLISHED_NETHER_BRICK_WALL)
            .build()
    val MIXED_RED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.MIXED_NETHER_BRICKS)
            .cracked(DnDBlocks.CRACKED_MIXED_NETHER_BRICKS)
            .stairs(DnDBlocks.MIXED_NETHER_BRICK_STAIRS)
            .slab(DnDBlocks.MIXED_NETHER_BRICK_SLAB)
            .wall(DnDBlocks.MIXED_NETHER_BRICK_WALL)
            .chiseled(DnDBlocks.CHISELED_MIXED_NETHER_BRICKS)
            .build()
    val POLISHED_RED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.POLISHED_RED_NETHER_BRICKS)
            .stairs(DnDBlocks.POLISHED_RED_NETHER_BRICK_STAIRS)
            .slab(DnDBlocks.POLISHED_RED_NETHER_BRICK_SLAB)
            .wall(DnDBlocks.POLISHED_RED_NETHER_BRICK_WALL)
            .build()
    val BLUE_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.BLUE_NETHER_BRICKS)
            .cracked(DnDBlocks.CRACKED_BLUE_NETHER_BRICKS)
            .stairs(DnDBlocks.BLUE_NETHER_BRICK_STAIRS)
            .slab(DnDBlocks.BLUE_NETHER_BRICK_SLAB)
            .wall(DnDBlocks.BLUE_NETHER_BRICK_WALL)
            .polished(DnDBlocks.POLISHED_BLUE_NETHER_BRICKS)
            .chiseled(DnDBlocks.CHISELED_BLUE_NETHER_BRICKS)
            .build()
    val MIXED_BLUE_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.MIXED_BLUE_NETHER_BRICKS)
            .cracked(DnDBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS)
            .stairs(DnDBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS)
            .slab(DnDBlocks.MIXED_BLUE_NETHER_BRICK_SLAB)
            .wall(DnDBlocks.MIXED_BLUE_NETHER_BRICK_WALL)
            .chiseled(DnDBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS)
            .build()
    val POLISHED_BLUE_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.POLISHED_BLUE_NETHER_BRICKS)
            .stairs(DnDBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS)
            .slab(DnDBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB)
            .wall(DnDBlocks.POLISHED_BLUE_NETHER_BRICK_WALL)
            .build()
    val GRAY_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.GRAY_NETHER_BRICKS)
            .cracked(DnDBlocks.CRACKED_GRAY_NETHER_BRICKS)
            .stairs(DnDBlocks.GRAY_NETHER_BRICK_STAIRS)
            .slab(DnDBlocks.GRAY_NETHER_BRICK_SLAB)
            .wall(DnDBlocks.GRAY_NETHER_BRICK_WALL)
            .polished(DnDBlocks.POLISHED_GRAY_NETHER_BRICKS)
            .chiseled(DnDBlocks.CHISELED_GRAY_NETHER_BRICKS)
            .build()
    val MIXED_GRAY_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.MIXED_GRAY_NETHER_BRICKS)
            .cracked(DnDBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS)
            .stairs(DnDBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS)
            .slab(DnDBlocks.MIXED_GRAY_NETHER_BRICK_SLAB)
            .wall(DnDBlocks.MIXED_GRAY_NETHER_BRICK_WALL)
            .chiseled(DnDBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS)
            .build()
    val POLISHED_GRAY_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.POLISHED_GRAY_NETHER_BRICKS)
            .stairs(DnDBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS)
            .slab(DnDBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB)
            .wall(DnDBlocks.POLISHED_GRAY_NETHER_BRICK_WALL)
            .build()

    val modelsBlockFamilies = listOf(
        CASCADE_FAMILY,
//        PINE_FAMILY,
        BONEWOOD_FAMILY,
        WITHERING_BONEWOOD_FAMILY,
        POLISHED_STONE_FAMILY,
        MOSSY_POLISHED_STONE_FAMILY,
        SNOWY_STONE_BRICKS_FAMILY,
        PACKED_ICE_BRICKS_FAMILY,
        BLUE_ICE_BRICKS_FAMILY,
        POLISHED_NETHER_BRICKS_FAMILY,
        MIXED_RED_NETHER_BRICKS_FAMILY,
        POLISHED_RED_NETHER_BRICKS_FAMILY,
        BLUE_NETHER_BRICKS_FAMILY,
        MIXED_BLUE_NETHER_BRICKS_FAMILY,
        POLISHED_BLUE_NETHER_BRICKS_FAMILY,
        GRAY_NETHER_BRICKS_FAMILY,
        MIXED_GRAY_NETHER_BRICKS_FAMILY,
        POLISHED_GRAY_NETHER_BRICKS_FAMILY,
    )
    val recipesBlockFamilies = modelsBlockFamilies + listOf(
        OAK_WOOD_FAMILY,
        SPRUCE_WOOD_FAMILY,
        BIRCH_WOOD_FAMILY,
        JUNGLE_WOOD_FAMILY,
        ACACIA_WOOD_FAMILY,
        DARK_OAK_WOOD_FAMILY,
        MANGROVE_WOOD_FAMILY,
        CHERRY_WOOD_FAMILY,
        CASCADE_WOOD_FAMILY,
        CRIMSON_HYPHAE_FAMILY,
        WARPED_HYPHAE_FAMILY,
        ICE_BRICKS_FAMILY,
        ICE_FAMILY,
        PACKED_ICE_FAMILY,
        BLUE_ICE_FAMILY,
        NETHERRACK_FAMILY,
        OVERGROWN_POLISHED_STONE_FAMILY,
        OVERGROWN_COBBLESTONE_FAMILY,
        OVERGROWN_STONE_BRICKS_FAMILY
    )

    fun init() {}
}