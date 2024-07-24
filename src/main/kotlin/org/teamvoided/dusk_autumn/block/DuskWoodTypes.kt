package org.teamvoided.dusk_autumn.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.block.BlockSetType
import net.minecraft.block.WoodType
import org.teamvoided.dusk_autumn.DuskAutumns.id


object DuskWoodTypes {
    fun init() = Unit
    val CASCADE = registerWoodType("cascade", WoodType.OAK, BlockSetType.OAK)


    private fun registerWoodType(id: String, woodType: WoodType, blockSet: BlockSetType): WoodType =
        WoodTypeBuilder.copyOf(woodType).register(id(id), blockSet)
}