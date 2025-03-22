package org.teamvoided.dusks_and_dungeons.block

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
    private val GALLERY_MAPLE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDWoodBlocks.GALLERY_MAPLE_WOOD)
            .stairs(DnDWoodBlocks.GALLERY_MAPLE_WOOD_STAIRS)
            .slab(DnDWoodBlocks.GALLERY_MAPLE_WOOD_SLAB)
            .wall(DnDWoodBlocks.GALLERY_MAPLE_WOOD_WALL)
            .build()

    val modelsBlockFamilies = listOf<BlockFamily>(
        CASCADE_FAMILY,
        BONEWOOD_FAMILY,
        WITHERING_BONEWOOD_FAMILY,
        GALLERY_MAPLE_FAMILY,
    )
    val recipesBlockFamilies = modelsBlockFamilies + listOf(GALLERY_MAPLE_WOOD_FAMILY,)
    fun init() = Unit
}