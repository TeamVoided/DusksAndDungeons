package org.teamvoided.dusks_and_dungeons.init.worldgen

import com.mojang.serialization.MapCodec
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureType
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.register
import org.teamvoided.dusks_and_dungeons.world.gen.structures.MineStructure

object DnDStructureTypes {

    val MINE = register("mine", MineStructure.CODEC)

    fun init() = Unit

    fun <S : Structure> register(name: String, codec: MapCodec<S>): StructureType<S> {
        return BuiltInRegistries.STRUCTURE_TYPE.register(id(name), StructureType { codec })
    }

}