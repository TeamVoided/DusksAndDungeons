package org.teamvoided.dusks_and_dungeons.datagen.data.litho

import dev.worldgen.lithostitched.api.util.InjectionType
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets
import net.minecraft.world.level.levelgen.structure.StructureSet
import org.teamvoided.dusks_and_dungeons.data.litho.DnDWorldgenModifiers
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructures
import org.teamvoided.dusks_and_dungeons.datagen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.datagen.data.litho.modifiers.DnDSurfaceRules

object WorldgenModifiers : RegistryBootstrapper<WorldgenModifier> {

    override fun BootstrapContext<WorldgenModifier>.init() {
        register(
            DnDWorldgenModifiers.DUSKS_AND_DUNGEONS_BIOMES_RULES,
            WorldgenModifier.builder()
                .addSurfaceRule(Level.OVERWORLD, InjectionType.PREPEND, DnDSurfaceRules.overworld())
        )

        val structureSets = lookup(Registries.STRUCTURE_SET)
        val structures = lookup(Registries.STRUCTURE)

        register(
            DnDWorldgenModifiers.ADD_VERDANT_MINESHAFT,
            WorldgenModifier.builder()
                .addStructureSetEntries(
                    structureSets.getOrThrow(BuiltinStructureSets.MINESHAFTS),
                    StructureSet.StructureSelectionEntry(structures.getOrThrow(DnDStructures.VERDANT_MINESHAFT), 1)
                )
        )
    }

}