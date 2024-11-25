package org.teamvoided.dusks_and_dungeons.data.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id

object DnDStructurePools {

    val AUTUMN_RUINS_SINGLE = create("autumn_ruins/single")

    fun create(id: String): RegistryKey<StructurePool> =
        RegistryKey.of(RegistryKeys.STRUCTURE_POOL, id(id))
}
