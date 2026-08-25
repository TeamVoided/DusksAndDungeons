package org.teamvoided.dusks_and_dungeons.data.gen.data.structure

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep.Decoration
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure
import org.teamvoided.dusks_and_dungeons.data.gen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructureFeatures
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructurePools
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags

object StructureFeatureCreator : RegistryBootstrapper<Structure> {

    override fun BootstrapContext<Structure>.init() {
//        val biomes = lookup(Registries.BIOME)
        val templatePool = lookup(Registries.TEMPLATE_POOL)

        register(
            DnDStructureFeatures.AUTUMN_RUINS,
            JigsawStructure(
                settings(
                    DnDBiomeTags.HAS_STRUCTURE_AUTUMN_RUINS,
                    Decoration.SURFACE_STRUCTURES,
                    TerrainAdjustment.BEARD_THIN
                ),
                templatePool.getOrThrow(DnDStructurePools.AUTUMN_RUINS_SINGLE),
                7,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.OCEAN_FLOOR_WG
            )
        )
    }

    fun BootstrapContext<Structure>.settings(
        biomes: TagKey<Biome>, step: Decoration, adaptation: TerrainAdjustment,
    ): Structure.StructureSettings {
        return Structure.StructureSettings(lookup(Registries.BIOME).getOrThrow(biomes), mapOf(), step, adaptation)
    }

}