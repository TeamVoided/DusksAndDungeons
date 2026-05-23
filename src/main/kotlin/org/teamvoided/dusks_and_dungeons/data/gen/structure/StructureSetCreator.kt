package org.teamvoided.dusks_and_dungeons.data.gen.structure

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType
import net.minecraft.world.level.levelgen.structure.StructureSet
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureFeatures
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureSets

object StructureSetCreator {

    // StructureSets
    fun bootstrap(c: BootstrapContext<StructureSet>) {
        val structures = c.lookup(Registries.STRUCTURE)

        c.register(
            DnDStructureSets.AUTUMN_RUINS,
            StructureSet(
                listOf(
                    StructureSet.entry(structures.getOrThrow(DnDStructureFeatures.AUTUMN_RUINS))
                ),
                RandomSpreadStructurePlacement(16, 4, RandomSpreadType.LINEAR, 1875259856)
            )
        )
    }
}
