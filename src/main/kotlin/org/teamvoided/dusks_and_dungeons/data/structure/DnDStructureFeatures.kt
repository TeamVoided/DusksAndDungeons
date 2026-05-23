package org.teamvoided.dusks_and_dungeons.data.structure

import net.minecraft.resources.ResourceKey
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.structure.Structure
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDStructureFeatures {
    val AUTUMN_RUINS = create("autumn_ruins")
    private fun create(id: String): ResourceKey<Structure> =
        ResourceKey.create(Registries.STRUCTURE, id(id))
}
