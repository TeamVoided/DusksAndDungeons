package org.teamvoided.dusks_and_dungeons.data.structure

import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.structure.StructureSet
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDStructureSets {
    val AUTUMN_RUINS = create("autumn_ruins")

    fun create(id: String): ResourceKey<StructureSet> =
        ResourceKey.create(Registries.STRUCTURE_SET, id(id))
}
