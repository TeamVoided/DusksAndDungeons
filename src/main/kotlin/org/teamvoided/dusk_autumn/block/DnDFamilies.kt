package org.teamvoided.dusk_autumn.block

import net.minecraft.block.Blocks
import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDNetherBrickBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDStoneBlocks
import org.teamvoided.dusk_autumn.init.blocks.DnDWoodBlocks


object DnDFamilies {
    val CASCADE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDWoodBlocks.CASCADE_PLANKS)
            .stairs(DnDWoodBlocks.CASCADE_STAIRS)
            .slab(DnDWoodBlocks.CASCADE_SLAB)
            .fence(DnDWoodBlocks.CASCADE_FENCE)
            .fenceGate(DnDWoodBlocks.CASCADE_FENCE_GATE)
            .door(DnDWoodBlocks.CASCADE_DOOR)
            .trapdoor(DnDWoodBlocks.CASCADE_TRAPDOOR)
            .button(DnDWoodBlocks.CASCADE_BUTTON)
            .pressurePlate(DnDWoodBlocks.CASCADE_PRESSURE_PLATE)
            .sign(DnDWoodBlocks.CASCADE_SIGN, DnDWoodBlocks.CASCADE_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build()
    val GALLERY_MAPLE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDWoodBlocks.GALLERY_MAPLE_PLANKS)
            .stairs(DnDWoodBlocks.GALLERY_MAPLE_STAIRS)
            .slab(DnDWoodBlocks.GALLERY_MAPLE_SLAB)
            .fence(DnDWoodBlocks.GALLERY_MAPLE_FENCE)
            .fenceGate(DnDWoodBlocks.GALLERY_MAPLE_FENCE_GATE)
            .door(DnDWoodBlocks.GALLERY_MAPLE_DOOR)
            .trapdoor(DnDWoodBlocks.GALLERY_MAPLE_TRAPDOOR)
            .button(DnDWoodBlocks.GALLERY_MAPLE_BUTTON)
            .pressurePlate(DnDWoodBlocks.GALLERY_MAPLE_PRESSURE_PLATE)
            .sign(DnDWoodBlocks.GALLERY_MAPLE_SIGN, DnDWoodBlocks.GALLERY_MAPLE_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_gallery_planks")
            .build()

    //    val PINE_FAMILY: BlockFamily =
//        BlockFamilies.register(DnDWoodBlocks.PINE_PLANKS)
//            .stairs(DnDWoodBlocks.PINE_STAIRS)
//            .slab(DnDWoodBlocks.PINE_SLAB)
//            .fence(DnDWoodBlocks.PINE_FENCE)
//            .fenceGate(DnDWoodBlocks.PINE_FENCE_GATE)
////            .door(DnDWoodBlocks.PINE_DOOR)
////            .trapdoor(DnDWoodBlocks.PINE_TRAPDOOR)
////            .button(DnDWoodBlocks.PINE_BUTTON)
////            .pressurePlate(DnDWoodBlocks.PINE_PRESSURE_PLATE)
////            .sign(DnDWoodBlocks.PINE_SIGN, DnDWoodBlocks.PINE_WALL_SIGN)
//            .group("wooden")
//            .unlockCriterionName("has_planks")
//            .build()
    val BONEWOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDWoodBlocks.BONEWOOD_PLANKS)
            .stairs(DnDWoodBlocks.BONEWOOD_STAIRS)
            .slab(DnDWoodBlocks.BONEWOOD_SLAB)
            .fence(DnDWoodBlocks.BONEWOOD_FENCE)
            .fenceGate(DnDWoodBlocks.BONEWOOD_FENCE_GATE)
            .door(DnDWoodBlocks.BONEWOOD_DOOR)
            .trapdoor(DnDWoodBlocks.BONEWOOD_TRAPDOOR)
//            .button(DnDWoodBlocks.BONEWOOD_BUTTON)
//            .pressurePlate(DnDWoodBlocks.BONEWOOD_PRESSURE_PLATE)
//            .sign(DnDWoodBlocks.BONEWOOD_SIGN, DnDWoodBlocks.BONEWOOD_WALL_SIGN)
            .group("bonewood")
            .unlockCriterionName("has_bonewood")
            .build()
    val WITHERING_BONEWOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDWoodBlocks.WITHERING_BONEWOOD_PLANKS)
            .stairs(DnDWoodBlocks.WITHERING_BONEWOOD_STAIRS)
            .slab(DnDWoodBlocks.WITHERING_BONEWOOD_SLAB)
            .fence(DnDWoodBlocks.WITHERING_BONEWOOD_FENCE)
            .fenceGate(DnDWoodBlocks.WITHERING_BONEWOOD_FENCE_GATE)
            .door(DnDWoodBlocks.WITHERING_BONEWOOD_DOOR)
            .trapdoor(DnDWoodBlocks.WITHERING_BONEWOOD_TRAPDOOR)
//            .button(DnDWoodBlocks.WITHERING_BONEWOOD_BUTTON)
//            .pressurePlate(DnDWoodBlocks.WITHERING_BONEWOOD_PRESSURE_PLATE)
//            .sign(DnDWoodBlocks.WITHERING_BONEWOOD_SIGN, DnDWoodBlocks.WITHERING_BONEWOOD_WALL_SIGN)
            .group("bonewood")
            .unlockCriterionName("has_bonewood")
            .build()

    val OAK_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.OAK_WOOD)
            .stairs(DnDWoodBlocks.OAK_WOOD_STAIRS)
            .slab(DnDWoodBlocks.OAK_WOOD_SLAB)
            .wall(DnDWoodBlocks.OAK_WOOD_WALL)
            .build()
    val SPRUCE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.SPRUCE_WOOD)
            .stairs(DnDWoodBlocks.SPRUCE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.SPRUCE_WOOD_SLAB)
            .wall(DnDWoodBlocks.SPRUCE_WOOD_WALL)
            .build()
    val BIRCH_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.BIRCH_WOOD)
            .stairs(DnDWoodBlocks.BIRCH_WOOD_STAIRS)
            .slab(DnDWoodBlocks.BIRCH_WOOD_SLAB)
            .wall(DnDWoodBlocks.BIRCH_WOOD_WALL)
            .build()
    val JUNGLE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.JUNGLE_WOOD)
            .stairs(DnDWoodBlocks.JUNGLE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.JUNGLE_WOOD_SLAB)
            .wall(DnDWoodBlocks.JUNGLE_WOOD_WALL)
            .build()
    val ACACIA_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.ACACIA_WOOD)
            .stairs(DnDWoodBlocks.ACACIA_WOOD_STAIRS)
            .slab(DnDWoodBlocks.ACACIA_WOOD_SLAB)
            .wall(DnDWoodBlocks.ACACIA_WOOD_WALL)
            .build()
    val DARK_OAK_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.DARK_OAK_WOOD)
            .stairs(DnDWoodBlocks.DARK_OAK_WOOD_STAIRS)
            .slab(DnDWoodBlocks.DARK_OAK_WOOD_SLAB)
            .wall(DnDWoodBlocks.DARK_OAK_WOOD_WALL)
            .build()
    val MANGROVE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.MANGROVE_WOOD)
            .stairs(DnDWoodBlocks.MANGROVE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.MANGROVE_WOOD_SLAB)
            .wall(DnDWoodBlocks.MANGROVE_WOOD_WALL)
            .build()
    val CHERRY_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.CHERRY_WOOD)
            .stairs(DnDWoodBlocks.CHERRY_WOOD_STAIRS)
            .slab(DnDWoodBlocks.CHERRY_WOOD_SLAB)
            .wall(DnDWoodBlocks.CHERRY_WOOD_WALL)
            .build()
    val CASCADE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDWoodBlocks.CASCADE_WOOD)
            .stairs(DnDWoodBlocks.CASCADE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.CASCADE_WOOD_SLAB)
            .wall(DnDWoodBlocks.CASCADE_WOOD_WALL)
            .build()
    val GALLERY_MAPLE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDWoodBlocks.GALLERY_MAPLE_WOOD)
            .stairs(DnDWoodBlocks.GALLERY_MAPLE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.GALLERY_MAPLE_WOOD_SLAB)
            .wall(DnDWoodBlocks.GALLERY_MAPLE_WOOD_WALL)
            .build()
    val CRIMSON_HYPHAE_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.CRIMSON_HYPHAE)
            .stairs(DnDWoodBlocks.CRIMSON_HYPHAE_STAIRS)
            .slab(DnDWoodBlocks.CRIMSON_HYPHAE_SLAB)
            .wall(DnDWoodBlocks.CRIMSON_HYPHAE_WALL)
            .build()
    val WARPED_HYPHAE_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.WARPED_HYPHAE)
            .stairs(DnDWoodBlocks.WARPED_HYPHAE_STAIRS)
            .slab(DnDWoodBlocks.WARPED_HYPHAE_SLAB)
            .wall(DnDWoodBlocks.WARPED_HYPHAE_WALL)
            .build()

    val POLISHED_STONE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDStoneBlocks.POLISHED_STONE)
            .stairs(DnDStoneBlocks.POLISHED_STONE_STAIRS)
            .slab(DnDStoneBlocks.POLISHED_STONE_SLAB)
            .wall(DnDStoneBlocks.POLISHED_STONE_WALL)
            .build()
    val MOSSY_POLISHED_STONE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDStoneBlocks.MOSSY_POLISHED_STONE)
            .stairs(DnDStoneBlocks.MOSSY_POLISHED_STONE_STAIRS)
            .slab(DnDStoneBlocks.MOSSY_POLISHED_STONE_SLAB)
            .wall(DnDStoneBlocks.MOSSY_POLISHED_STONE_WALL)
            .build()
    val OVERGROWN_POLISHED_STONE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDStoneBlocks.OVERGROWN_POLISHED_STONE)
            .stairs(DnDStoneBlocks.OVERGROWN_POLISHED_STONE_STAIRS)
            .slab(DnDStoneBlocks.OVERGROWN_POLISHED_STONE_SLAB)
            .wall(DnDStoneBlocks.OVERGROWN_POLISHED_STONE_WALL)
            .build()
    val OVERGROWN_COBBLESTONE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDStoneBlocks.OVERGROWN_COBBLESTONE)
            .stairs(DnDStoneBlocks.OVERGROWN_COBBLESTONE_STAIRS)
            .slab(DnDStoneBlocks.OVERGROWN_COBBLESTONE_SLAB)
            .wall(DnDStoneBlocks.OVERGROWN_COBBLESTONE_WALL)
            .build()
    val OVERGROWN_STONE_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDStoneBlocks.OVERGROWN_STONE_BRICKS)
            .stairs(DnDStoneBlocks.OVERGROWN_STONE_BRICK_STAIRS)
            .slab(DnDStoneBlocks.OVERGROWN_STONE_BRICK_SLAB)
            .wall(DnDStoneBlocks.OVERGROWN_STONE_BRICK_WALL)
            .build()
    val SNOWY_STONE_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDStoneBlocks.SNOWY_STONE_BRICKS)
            .stairs(DnDStoneBlocks.SNOWY_STONE_BRICK_STAIRS)
            .slab(DnDStoneBlocks.SNOWY_STONE_BRICK_SLAB)
            .wall(DnDStoneBlocks.SNOWY_STONE_BRICK_WALL)
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
            .stairs(DnDNetherBrickBlocks.NETHERRACK_STAIRS)
            .slab(DnDNetherBrickBlocks.NETHERRACK_SLAB)
            .wall(DnDNetherBrickBlocks.NETHERRACK_WALL)
            .build()
    val POLISHED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDNetherBrickBlocks.POLISHED_NETHER_BRICKS)
            .stairs(DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_STAIRS)
            .slab(DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_SLAB)
            .wall(DnDNetherBrickBlocks.POLISHED_NETHER_BRICK_WALL)
            .build()
    val MIXED_RED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDNetherBrickBlocks.MIXED_NETHER_BRICKS)
            .cracked(DnDNetherBrickBlocks.CRACKED_MIXED_NETHER_BRICKS)
            .stairs(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_STAIRS)
            .slab(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_SLAB)
            .wall(DnDNetherBrickBlocks.MIXED_NETHER_BRICK_WALL)
            .chiseled(DnDNetherBrickBlocks.CHISELED_MIXED_NETHER_BRICKS)
            .build()
    val POLISHED_RED_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICKS)
            .stairs(DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_STAIRS)
            .slab(DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_SLAB)
            .wall(DnDNetherBrickBlocks.POLISHED_RED_NETHER_BRICK_WALL)
            .build()
    val BLUE_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDNetherBrickBlocks.BLUE_NETHER_BRICKS)
            .cracked(DnDNetherBrickBlocks.CRACKED_BLUE_NETHER_BRICKS)
            .stairs(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_STAIRS)
            .slab(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_SLAB)
            .wall(DnDNetherBrickBlocks.BLUE_NETHER_BRICK_WALL)
            .polished(DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICKS)
            .chiseled(DnDNetherBrickBlocks.CHISELED_BLUE_NETHER_BRICKS)
            .build()
    val MIXED_BLUE_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICKS)
            .cracked(DnDNetherBrickBlocks.CRACKED_MIXED_BLUE_NETHER_BRICKS)
            .stairs(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_STAIRS)
            .slab(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_SLAB)
            .wall(DnDNetherBrickBlocks.MIXED_BLUE_NETHER_BRICK_WALL)
            .chiseled(DnDNetherBrickBlocks.CHISELED_MIXED_BLUE_NETHER_BRICKS)
            .build()
    val POLISHED_BLUE_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICKS)
            .stairs(DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_STAIRS)
            .slab(DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_SLAB)
            .wall(DnDNetherBrickBlocks.POLISHED_BLUE_NETHER_BRICK_WALL)
            .build()
    val GRAY_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDNetherBrickBlocks.GRAY_NETHER_BRICKS)
            .cracked(DnDNetherBrickBlocks.CRACKED_GRAY_NETHER_BRICKS)
            .stairs(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_STAIRS)
            .slab(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_SLAB)
            .wall(DnDNetherBrickBlocks.GRAY_NETHER_BRICK_WALL)
            .polished(DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICKS)
            .chiseled(DnDNetherBrickBlocks.CHISELED_GRAY_NETHER_BRICKS)
            .build()
    val MIXED_GRAY_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICKS)
            .cracked(DnDNetherBrickBlocks.CRACKED_MIXED_GRAY_NETHER_BRICKS)
            .stairs(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_STAIRS)
            .slab(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_SLAB)
            .wall(DnDNetherBrickBlocks.MIXED_GRAY_NETHER_BRICK_WALL)
            .chiseled(DnDNetherBrickBlocks.CHISELED_MIXED_GRAY_NETHER_BRICKS)
            .build()
    val POLISHED_GRAY_NETHER_BRICKS_FAMILY: BlockFamily =
        BlockFamilies.register(DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICKS)
            .stairs(DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_STAIRS)
            .slab(DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_SLAB)
            .wall(DnDNetherBrickBlocks.POLISHED_GRAY_NETHER_BRICK_WALL)
            .build()

    val modelsBlockFamilies = listOf(
        CASCADE_FAMILY,
        GALLERY_MAPLE_FAMILY,
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
        GALLERY_MAPLE_WOOD_FAMILY,
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

    fun init() {
        modelsBlockFamilies.forEach{
            it.variants.forEach(::println)
        }

    }
}