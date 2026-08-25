package org.teamvoided.dusks_and_dungeons.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.init.misc.DnDBlockSetTypes


object DnDWoodTypes {

    val CASCADE_WOOD_TYPE = registerWoodType("cascade", WoodType.CHERRY, DnDBlockSetTypes.CASCADE_BLOCK_SET_TYPE)
    val SYPIA_WOOD_TYPE = registerWoodType("sypia", WoodType.BIRCH, DnDBlockSetTypes.SYPIA_BLOCK_SET_TYPE)

    @JvmField
    val VERDANT_WOOD_TYPE: WoodType = registerWoodType("verdant", WoodType.OAK, DnDBlockSetTypes.VERDANT_BLOCK_SET_TYPE)

    fun init() = Unit

    fun registerWoodType(name: String, type: WoodType, set: BlockSetType): WoodType {
        return WoodTypeBuilder.copyOf(type).register(id(name), set)
    }

}