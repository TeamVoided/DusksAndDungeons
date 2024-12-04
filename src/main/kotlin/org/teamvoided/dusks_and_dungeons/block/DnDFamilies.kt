package org.teamvoided.dusks_and_dungeons.block

import net.minecraft.block.Blocks
import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks


object DnDFamilies {
   private val CASCADE_FAMILY: BlockFamily =
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
    private val GALLERY_MAPLE_FAMILY: BlockFamily =
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
    private val BONEWOOD_FAMILY: BlockFamily =
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
    private val WITHERING_BONEWOOD_FAMILY: BlockFamily =
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

    private val OAK_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.OAK_WOOD)
            .stairs(DnDWoodBlocks.OAK_WOOD_STAIRS)
            .slab(DnDWoodBlocks.OAK_WOOD_SLAB)
            .wall(DnDWoodBlocks.OAK_WOOD_WALL)
            .build()
    private val SPRUCE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.SPRUCE_WOOD)
            .stairs(DnDWoodBlocks.SPRUCE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.SPRUCE_WOOD_SLAB)
            .wall(DnDWoodBlocks.SPRUCE_WOOD_WALL)
            .build()
    private val BIRCH_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.BIRCH_WOOD)
            .stairs(DnDWoodBlocks.BIRCH_WOOD_STAIRS)
            .slab(DnDWoodBlocks.BIRCH_WOOD_SLAB)
            .wall(DnDWoodBlocks.BIRCH_WOOD_WALL)
            .build()
    private val JUNGLE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.JUNGLE_WOOD)
            .stairs(DnDWoodBlocks.JUNGLE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.JUNGLE_WOOD_SLAB)
            .wall(DnDWoodBlocks.JUNGLE_WOOD_WALL)
            .build()
    private val ACACIA_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.ACACIA_WOOD)
            .stairs(DnDWoodBlocks.ACACIA_WOOD_STAIRS)
            .slab(DnDWoodBlocks.ACACIA_WOOD_SLAB)
            .wall(DnDWoodBlocks.ACACIA_WOOD_WALL)
            .build()
    private val DARK_OAK_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.DARK_OAK_WOOD)
            .stairs(DnDWoodBlocks.DARK_OAK_WOOD_STAIRS)
            .slab(DnDWoodBlocks.DARK_OAK_WOOD_SLAB)
            .wall(DnDWoodBlocks.DARK_OAK_WOOD_WALL)
            .build()
    private val MANGROVE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.MANGROVE_WOOD)
            .stairs(DnDWoodBlocks.MANGROVE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.MANGROVE_WOOD_SLAB)
            .wall(DnDWoodBlocks.MANGROVE_WOOD_WALL)
            .build()
    private val CHERRY_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.CHERRY_WOOD)
            .stairs(DnDWoodBlocks.CHERRY_WOOD_STAIRS)
            .slab(DnDWoodBlocks.CHERRY_WOOD_SLAB)
            .wall(DnDWoodBlocks.CHERRY_WOOD_WALL)
            .build()
    private val CASCADE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDWoodBlocks.CASCADE_WOOD)
            .stairs(DnDWoodBlocks.CASCADE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.CASCADE_WOOD_SLAB)
            .wall(DnDWoodBlocks.CASCADE_WOOD_WALL)
            .build()
    private val GALLERY_MAPLE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDWoodBlocks.GALLERY_MAPLE_WOOD)
            .stairs(DnDWoodBlocks.GALLERY_MAPLE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.GALLERY_MAPLE_WOOD_SLAB)
            .wall(DnDWoodBlocks.GALLERY_MAPLE_WOOD_WALL)
            .build()
    private val CRIMSON_HYPHAE_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.CRIMSON_HYPHAE)
            .stairs(DnDWoodBlocks.CRIMSON_HYPHAE_STAIRS)
            .slab(DnDWoodBlocks.CRIMSON_HYPHAE_SLAB)
            .wall(DnDWoodBlocks.CRIMSON_HYPHAE_WALL)
            .build()
    private val WARPED_HYPHAE_FAMILY: BlockFamily =
        BlockFamilies.register(Blocks.WARPED_HYPHAE)
            .stairs(DnDWoodBlocks.WARPED_HYPHAE_STAIRS)
            .slab(DnDWoodBlocks.WARPED_HYPHAE_SLAB)
            .wall(DnDWoodBlocks.WARPED_HYPHAE_WALL)
            .build()


    val modelsBlockFamilies = listOf(
        CASCADE_FAMILY,
        BONEWOOD_FAMILY,
        WITHERING_BONEWOOD_FAMILY,
        GALLERY_MAPLE_FAMILY,
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
    )

    fun init() = Unit
}