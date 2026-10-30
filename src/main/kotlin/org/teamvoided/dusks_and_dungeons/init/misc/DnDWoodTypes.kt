package org.teamvoided.dusks_and_dungeons.init.misc

import net.fabricmc.fabric.api.`object`.builder.v1.block.type.WoodTypeBuilder
import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id


object DnDWoodTypes {

    val CASCADE_WOOD_TYPE = register("cascade", WoodType.CHERRY, DnDBlockSetTypes.CASCADE_BLOCK_SET_TYPE)
    val SYPIA_WOOD_TYPE = register("sypia", WoodType.BIRCH, DnDBlockSetTypes.SYPIA_BLOCK_SET_TYPE)

    @JvmField
    val VERDANT_WOOD_TYPE = register("verdant", WoodType.OAK, DnDBlockSetTypes.VERDANT_BLOCK_SET_TYPE)

    fun init() = Unit

    fun register(name: String, type: WoodType, set: BlockSetType): WoodType {
        return WoodTypeBuilder.copyOf(type).register(id(name), set)
    }

}