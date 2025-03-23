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
    private val GALLERY_MAPLE_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.GALLERY_MAPLE_PLANKS)
            .stairs(DnDBlocks.GALLERY_MAPLE_STAIRS)
            .slab(DnDBlocks.GALLERY_MAPLE_SLAB)
            .fence(DnDBlocks.GALLERY_MAPLE_FENCE)
            .fenceGate(DnDBlocks.GALLERY_MAPLE_FENCE_GATE)
            .door(DnDBlocks.GALLERY_MAPLE_DOOR)
            .trapdoor(DnDBlocks.GALLERY_MAPLE_TRAPDOOR)
            .button(DnDBlocks.GALLERY_MAPLE_BUTTON)
            .pressurePlate(DnDBlocks.GALLERY_MAPLE_PRESSURE_PLATE)
            .sign(DnDBlocks.GALLERY_MAPLE_SIGN, DnDBlocks.GALLERY_MAPLE_WALL_SIGN)
            .group("wooden")
            .unlockCriterionName("has_gallery_planks")
            .build()
    private val BONEWOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.BONEWOOD_PLANKS)
            .stairs(DnDBlocks.BONEWOOD_STAIRS)
            .slab(DnDBlocks.BONEWOOD_SLAB)
            .fence(DnDBlocks.BONEWOOD_FENCE)
            .fenceGate(DnDBlocks.BONEWOOD_FENCE_GATE)
            .door(DnDBlocks.BONEWOOD_DOOR)
            .trapdoor(DnDBlocks.BONEWOOD_TRAPDOOR)
//            .button(DnDWoodBlocks.BONEWOOD_BUTTON)
//            .pressurePlate(DnDWoodBlocks.BONEWOOD_PRESSURE_PLATE)
//            .sign(DnDWoodBlocks.BONEWOOD_SIGN, DnDWoodBlocks.BONEWOOD_WALL_SIGN)
            .group("bonewood")
            .unlockCriterionName("has_bonewood")
            .build()
    private val WITHERING_BONEWOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.WITHERING_BONEWOOD_PLANKS)
            .stairs(DnDBlocks.WITHERING_BONEWOOD_STAIRS)
            .slab(DnDBlocks.WITHERING_BONEWOOD_SLAB)
            .fence(DnDBlocks.WITHERING_BONEWOOD_FENCE)
            .fenceGate(DnDBlocks.WITHERING_BONEWOOD_FENCE_GATE)
            .door(DnDBlocks.WITHERING_BONEWOOD_DOOR)
            .trapdoor(DnDBlocks.WITHERING_BONEWOOD_TRAPDOOR)
//            .button(DnDWoodBlocks.WITHERING_BONEWOOD_BUTTON)
//            .pressurePlate(DnDWoodBlocks.WITHERING_BONEWOOD_PRESSURE_PLATE)
//            .sign(DnDWoodBlocks.WITHERING_BONEWOOD_SIGN, DnDWoodBlocks.WITHERING_BONEWOOD_WALL_SIGN)
            .group("bonewood")
            .unlockCriterionName("has_bonewood")
            .build()
    private val GALLERY_MAPLE_WOOD_FAMILY: BlockFamily =
        BlockFamilies.register(DnDBlocks.GALLERY_MAPLE_WOOD)
            .stairs(DnDBlocks.GALLERY_MAPLE_WOOD_STAIRS)
            .slab(DnDBlocks.GALLERY_MAPLE_WOOD_SLAB)
            .wall(DnDBlocks.GALLERY_MAPLE_WOOD_WALL)
            .build()

    val modelsBlockFamilies = listOf(
        CASCADE_FAMILY,
        BONEWOOD_FAMILY,
        WITHERING_BONEWOOD_FAMILY,
        GALLERY_MAPLE_FAMILY,
    )
    val recipesBlockFamilies = modelsBlockFamilies + listOf(GALLERY_MAPLE_WOOD_FAMILY,)
    fun init() = Unit
}