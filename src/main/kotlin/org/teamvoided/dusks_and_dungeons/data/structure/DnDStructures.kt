package org.teamvoided.dusks_and_dungeons.data.structure

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.structure.Structure
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDStructures {

    val AUTUMN_RUINS = create("autumn_ruins")
    val VERDANT_MINESHAFT = create("verdant_mineshaft")

    fun create(id: String): ResourceKey<Structure> = Registries.STRUCTURE.key(id(id))

}
