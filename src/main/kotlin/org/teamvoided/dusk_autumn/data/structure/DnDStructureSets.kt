package org.teamvoided.dusk_autumn.data.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.structure.StructureSet
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DnDStructureSets {
    val AUTUMN_RUINS = create("autumn_ruins")

    fun create(id: String): RegistryKey<StructureSet> =
        RegistryKey.of(RegistryKeys.STRUCTURE_SET, id(id))
}
