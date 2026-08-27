package org.teamvoided.dusks_and_dungeons.datagen.data.structure

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep.Decoration
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructures
import org.teamvoided.dusks_and_dungeons.data.structure.DnDTemplatePool
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags
import org.teamvoided.dusks_and_dungeons.datagen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.world.gen.structures.DnDMineshaftStructure

object ModStructures : RegistryBootstrapper<Structure> {

    override fun BootstrapContext<Structure>.init() {
        val biomes = lookup(Registries.BIOME)
        val templatePool = lookup(Registries.TEMPLATE_POOL)

        register(
            DnDStructures.AUTUMN_RUINS,
            JigsawStructure(
                settings(
                    DnDBiomeTags.HAS_STRUCTURE_AUTUMN_RUINS,
                    Decoration.SURFACE_STRUCTURES,
                    TerrainAdjustment.BEARD_THIN
                ),
                templatePool.getOrThrow(DnDTemplatePool.AUTUMN_RUINS_SINGLE),
                7,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.OCEAN_FLOOR_WG
            )
        )

        register(
            DnDStructures.VERDANT_MINESHAFT,
            DnDMineshaftStructure(
                StructureSettings.Builder(biomes.getOrThrow(DnDBiomeTags.HAS_STRUCTURE_VERDANT_MINESHAFT))
                    .generationStep(Decoration.UNDERGROUND_STRUCTURES)
                    .build(),
                DnDMineshaftStructure.Type.VERDANT
            )
        )
    }

    fun BootstrapContext<Structure>.settings(
        biomes: TagKey<Biome>, step: Decoration, adaptation: TerrainAdjustment,
    ): StructureSettings {
        return StructureSettings(lookup(Registries.BIOME).getOrThrow(biomes), mapOf(), step, adaptation)
    }

}