package org.teamvoided.dusk_autumn.data.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.StructureFeature
import org.teamvoided.dusk_autumn.DuskAutumns.id

object DuskStructureFeatures {
    val AUTUMN_RUINS = create("autumn_ruins")
    private fun create(id: String): RegistryKey<StructureFeature> =
        RegistryKey.of(RegistryKeys.STRUCTURE_FEATURE, id(id))
}
