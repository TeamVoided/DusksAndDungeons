package org.teamvoided.dusks_and_dungeons.data.structure

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.structure.StructureSet
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDStructureSets {

    val AUTUMN_RUINS = key("autumn_ruins")

    fun key(id: String): ResourceKey<StructureSet> = Registries.STRUCTURE_SET.key(id(id))

}