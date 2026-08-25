package org.teamvoided.dusks_and_dungeons.data.gen.data.structure

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.structure.StructureSet
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType
import org.teamvoided.dusks_and_dungeons.data.gen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureFeatures
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureSets

object StructureSetCreator : RegistryBootstrapper<StructureSet> {

    override fun BootstrapContext<StructureSet>.init() {
        val structures = lookup(Registries.STRUCTURE)

        register(// convert to abandoned farmstead?
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