package org.teamvoided.dusks_and_dungeons.data.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.processor.StructureProcessorList
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDStructureProcessorLists {

    val AUTUMN_RUINS_DEFAULT = create("autumn_ruins/default")

    fun create(id: String): RegistryKey<StructureProcessorList> =
        RegistryKey.of(RegistryKeys.STRUCTURE_PROCESSOR_LIST, id(id))

}
