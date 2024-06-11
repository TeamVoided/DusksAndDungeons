package org.teamvoided.dusk_autumn.block

import net.minecraft.data.family.BlockFamilies
import net.minecraft.data.family.BlockFamily
import org.teamvoided.dusk_autumn.init.DuskBlocks

object DuskBlockFamilies {
    val VOLCANIC_SANDSTONE: BlockFamily =
        BlockFamilies.register(DuskBlocks.VOLCANIC_SANDSTONE)
            .stairs(DuskBlocks.VOLCANIC_SANDSTONE_STAIRS)
            .slab(DuskBlocks.VOLCANIC_SANDSTONE_SLAB)
            .wall(DuskBlocks.VOLCANIC_SANDSTONE_WALL)
            .chiseled(DuskBlocks.CHISELED_VOLCANIC_SANDSTONE)
            .cut(DuskBlocks.CUT_VOLCANIC_SANDSTONE)
            .noGenerateRecipes().build()
    val CUT_VOLCANIC_SANDSTONE: BlockFamily =
        BlockFamilies.register(DuskBlocks.CUT_VOLCANIC_SANDSTONE)
            .slab(DuskBlocks.CUT_VOLCANIC_SANDSTONE_SLAB)
            .build()
    val SMOOTH_VOLCANIC_SANDSTONE: BlockFamily =
        BlockFamilies.register(DuskBlocks.SMOOTH_VOLCANIC_SANDSTONE)
            .stairs(DuskBlocks.SMOOTH_VOLCANIC_SANDSTONE_STAIRS)
            .slab(DuskBlocks.SMOOTH_VOLCANIC_SANDSTONE_SLAB)
            .build()

    fun init() {}
}