package org.teamvoided.dusk_autumn.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import org.teamvoided.dusk_autumn.data.gen.tags.EntityTypeTagsProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import org.teamvoided.dusk_autumn.DuskAutumns.LOGGER
import org.teamvoided.dusk_autumn.data.gen.providers.BlockLootTableProvider
import org.teamvoided.dusk_autumn.data.gen.providers.EnglishTranslationProvider
import org.teamvoided.dusk_autumn.data.gen.providers.ModelProvider
import org.teamvoided.dusk_autumn.data.gen.providers.RecipesProvider
import org.teamvoided.dusk_autumn.data.gen.tags.BiomeTagsProvider
import org.teamvoided.dusk_autumn.data.gen.tags.BlockTagsProvider
import org.teamvoided.dusk_autumn.init.worldgen.DuskBiomes
import org.teamvoided.dusk_autumn.init.worldgen.DuskConfiguredFeature
import org.teamvoided.dusk_autumn.init.worldgen.DuskPlacedFeature

class DuskAutumnsData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        LOGGER.info("Hello from DataGen")
        val pack = gen.createPack()

        pack.addProvider(::DuskAutumnsWorldGenerator)
        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::BlockTagsProvider)
        pack.addProvider(::EntityTypeTagsProvider)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::EnglishTranslationProvider)
        pack.addProvider(::RecipesProvider)
        pack.addProvider(::BlockLootTableProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(RegistryKeys.BIOME, DuskBiomes::boostrap)
        gen.add(RegistryKeys.CONFIGURED_FEATURE, DuskConfiguredFeature::bootstrapConfiguredFeatures)
        gen.add(RegistryKeys.PLACED_FEATURE, DuskPlacedFeature::bootstrapPlacedFeatures)
    }
}