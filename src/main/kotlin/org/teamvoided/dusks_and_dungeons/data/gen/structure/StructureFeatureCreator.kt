package org.teamvoided.dusks_and_dungeons.data.gen.structure

import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureFeatures
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructurePools
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags

@Suppress("MagicNumber")
object StructureFeatureCreator {
    fun bootstrap(c: BootstrapContext<Structure>) {
        val biomeTags: HolderGetter<Biome> = c.lookup(Registries.BIOME)
        val structurePools: HolderGetter<StructureTemplatePool> = c.lookup(Registries.TEMPLATE_POOL)


        c.register(
            DnDStructureFeatures.AUTUMN_RUINS,
            JigsawStructure(
                structureSettings(
                    biomeTags.getOrThrow(DnDBiomeTags.HAS_STRUCTURE_AUTUMN_RUINS),
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    TerrainAdjustment.BEARD_THIN
                ),
                structurePools.getOrThrow(DnDStructurePools.AUTUMN_RUINS_SINGLE),
                7,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.OCEAN_FLOOR_WG
            )
        )
    }

    private fun structureSettings(
        biomes: HolderSet<Biome>,
        step: GenerationStep.Decoration,
        terrainAdaptation: TerrainAdjustment
    ) = Structure.StructureSettings(biomes, mapOf(), step, terrainAdaptation)
}
