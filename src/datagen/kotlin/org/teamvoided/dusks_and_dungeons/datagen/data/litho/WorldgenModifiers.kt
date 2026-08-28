package org.teamvoided.dusks_and_dungeons.datagen.data.litho

import com.mojang.datafixers.util.Pair
import dev.worldgen.lithostitched.api.util.InjectionType
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.data.worldgen.ProcessorLists
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets
import net.minecraft.world.level.levelgen.structure.StructureSet
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import org.teamvoided.dusks_and_dungeons.data.litho.DnDWorldgenModifiers
import org.teamvoided.dusks_and_dungeons.data.structure.DnDStructures
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.datagen.data.RegistryBootstrapper
import org.teamvoided.dusks_and_dungeons.datagen.data.litho.modifiers.DnDSurfaceRules
import org.teamvoided.dusks_and_dungeons.datagen.data.structure.ModProcessorLists.chanceReplaceRule
import org.teamvoided.dusks_and_dungeons.datagen.data.structure.ModProcessorLists.ruleProcessor
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

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

        val procLists = lookup(Registries.PROCESSOR_LIST)

        register(
            DnDWorldgenModifiers.ADD_CORN,
            WorldgenModifier.builder()
                .addProcessorListProcessors(
                    procLists.getOrThrow(ProcessorLists.FARM_PLAINS),
                    ruleProcessor(
                        chanceReplaceRule(Blocks.WHEAT, 0.5f, DnDBlocks.CORN_CROP)
                    )
                )
        )

        val pools = lookup(Registries.TEMPLATE_POOL)
        val placedFeatures = lookup(Registries.PLACED_FEATURE)

        register(
            DnDWorldgenModifiers.ADD_CORN_PILE,
            WorldgenModifier.builder()
                .addTemplatePoolElements(
                    pools.getOrThrow(Pools.createKey("village/plains/decor")),
                    Pair(
                        StructurePoolElement.feature(placedFeatures.getOrThrow(DnDPlacedFeature.PILE_CORN))
                            .apply(StructureTemplatePool.Projection.RIGID), 1
                    )
                )
        )
    }

}