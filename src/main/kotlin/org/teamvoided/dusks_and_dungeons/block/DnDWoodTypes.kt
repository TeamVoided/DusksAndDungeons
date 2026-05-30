package org.teamvoided.dusks_and_dungeons.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.type.BlockSetTypeBuilder
import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id


object DnDWoodTypes {
    fun init() = Unit

    val CASCADE_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(id("cascade"))
    val CASCADE_WOOD_TYPE = registerWoodType("cascade", WoodType.CHERRY, CASCADE_BLOCK_SET_TYPE)

    val VERDANT_BLOCK_SET_TYPE: BlockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(id("verdant"))
    val VERDANT_WOOD_TYPE: WoodType = registerWoodType("verdant", WoodType.OAK, VERDANT_BLOCK_SET_TYPE)

    @Suppress("SameParameterValue")
    private fun registerWoodType(id: String, woodType: WoodType, blockSet: BlockSetType): WoodType =
        WoodTypeBuilder.copyOf(woodType).register(id(id), blockSet)
}