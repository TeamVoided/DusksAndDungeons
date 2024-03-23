package org.teamvoided.dusk_autumn.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import org.teamvoided.dusk_autumn.DuskAutumns.LOGGER
import org.teamvoided.dusk_autumn.datagen.tags.BiomeTagsProvider
import org.teamvoided.dusk_autumn.datagen.tags.BlockTagsProvider
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
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(RegistryKeys.BIOME, DuskBiomes::boostrap)
        gen.add(RegistryKeys.CONFIGURED_FEATURE, DuskConfiguredFeature::bootstrapConfiguredFeatures)
        gen.add(RegistryKeys.PLACED_FEATURE, DuskPlacedFeature::bootstrapPlacedFeatures)
    }
}