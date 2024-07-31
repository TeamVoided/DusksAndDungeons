package org.teamvoided.dusk_autumn.data.gen.structure

import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.RandomSpreadStructurePlacement
import net.minecraft.structure.RandomSpreadType
import net.minecraft.world.gen.structure.StructureSet
import org.teamvoided.dusk_autumn.data.structure.DnDStructureFeatures
import org.teamvoided.dusk_autumn.data.structure.DnDStructureSets

object StructureSetCreator {

    // StructureSets
    fun bootstrap(c: BootstrapContext<StructureSet>) {
        val structures = c.getRegistryLookup(RegistryKeys.STRUCTURE_FEATURE)

        c.register(
            DnDStructureSets.AUTUMN_RUINS,
            StructureSet(
                listOf(
                    StructureSet.entry(structures.getHolderOrThrow(DnDStructureFeatures.AUTUMN_RUINS))
                ),
                RandomSpreadStructurePlacement(16, 4, RandomSpreadType.LINEAR, 1875259856)
            )
        )
    }
}
