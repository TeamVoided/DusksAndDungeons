package org.teamvoided.dusk_autumn.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import org.teamvoided.dusk_autumn.DuskAutumns.LOGGER
import org.teamvoided.dusk_autumn.data.gen.providers.BlockLootTableProvider
import org.teamvoided.dusk_autumn.data.gen.providers.EnglishTranslationProvider
import org.teamvoided.dusk_autumn.data.gen.providers.ModelProvider
import org.teamvoided.dusk_autumn.data.gen.providers.RecipesProvider
import org.teamvoided.dusk_autumn.data.gen.structure.StructureFeatureCreator
import org.teamvoided.dusk_autumn.data.gen.structure.StructurePoolCreator
import org.teamvoided.dusk_autumn.data.gen.structure.StructureProcessorCreator
import org.teamvoided.dusk_autumn.data.gen.structure.StructureSetCreator
import org.teamvoided.dusk_autumn.data.gen.tags.BiomeTagsProvider
import org.teamvoided.dusk_autumn.data.gen.tags.BlockTagsProvider
import org.teamvoided.dusk_autumn.data.gen.tags.EntityTypeTagsProvider
import org.teamvoided.dusk_autumn.data.gen.tags.ItemTagsProvider
import org.teamvoided.dusk_autumn.data.gen.worldgen.BiomeCreator
import org.teamvoided.dusk_autumn.data.gen.worldgen.ConfiguredFeatureCreator
import org.teamvoided.dusk_autumn.data.gen.worldgen.PlacedFeatureCreator

class DuskAutumnsData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        LOGGER.info("Hello from DataGen")
        val pack = gen.createPack()

        pack.addProvider(::DuskAutumnsWorldGenerator)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::EnglishTranslationProvider)
        pack.addProvider(::RecipesProvider)
        pack.addProvider(::BlockLootTableProvider)

        pack.addProvider(::ItemTagsProvider)
        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::BlockTagsProvider)
        pack.addProvider(::EntityTypeTagsProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(RegistryKeys.BIOME, BiomeCreator::boostrap)
        gen.add(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(RegistryKeys.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_PROCESSOR_LIST, StructureProcessorCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_POOL, StructurePoolCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_SET, StructureSetCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_FEATURE, StructureFeatureCreator::bootstrap)
    }
}
