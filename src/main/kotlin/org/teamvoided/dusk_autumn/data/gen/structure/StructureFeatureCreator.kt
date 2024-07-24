package org.teamvoided.dusk_autumn.data.gen.structure

import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import net.minecraft.world.Heightmap
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider
import net.minecraft.world.gen.structure.TerrainAdjustment
import org.teamvoided.dusk_autumn.data.structure.DuskStructureFeatures
import org.teamvoided.dusk_autumn.data.structure.DuskStructurePools
import org.teamvoided.dusk_autumn.data.tags.DuskBiomeTags

@Suppress("MagicNumber")
object StructureFeatureCreator {
    fun bootstrap(c: BootstrapContext<StructureFeature>) {
        val biomeTags: HolderProvider<Biome> = c.getRegistryLookup(RegistryKeys.BIOME)
        val structurePools: HolderProvider<StructurePool> = c.getRegistryLookup(RegistryKeys.STRUCTURE_POOL)


        c.register(
            DuskStructureFeatures.AUTUMN_RUINS,
            JigsawFeature(
                structureSettings(
                    biomeTags.getTagOrThrow(DuskBiomeTags.HAS_STRUCTURE_AUTUMN_RUINS),
                    GenerationStep.Feature.SURFACE_STRUCTURES,
                    TerrainAdjustment.STRUCTURE_WEIGHT_THIN
                ),
                structurePools.getHolderOrThrow(DuskStructurePools.AUTUMN_RUINS_SINGLE),
                1,
                ConstantHeightProvider.create(YOffset.fixed(0)),
                false,
                Heightmap.Type.OCEAN_FLOOR_WG
            )
        )

    }

    private fun structureSettings(
        biomes: HolderSet<Biome>,
        step: GenerationStep.Feature,
        terrainAdaptation: TerrainAdjustment
    ) = StructureFeature.StructureSettings(biomes, mapOf(), step, terrainAdaptation)
}
