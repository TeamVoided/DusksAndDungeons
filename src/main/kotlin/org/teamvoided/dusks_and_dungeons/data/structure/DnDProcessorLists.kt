package org.teamvoided.dusks_and_dungeons.data.structure

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.util.key

object DnDProcessorLists {

    val AUTUMN_RUINS_DEFAULT = create("autumn_ruins/default")

    fun create(id: String): ResourceKey<StructureProcessorList> = Registries.PROCESSOR_LIST.key(id(id))

}
