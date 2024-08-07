package org.teamvoided.dusk_autumn.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.MinecraftVersion
import net.minecraft.data.DataPackOutput
import net.minecraft.data.PackMetadataProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import net.minecraft.resource.ResourceType
import net.minecraft.resource.pack.metadata.PackResourceMetadataSection
import net.minecraft.text.Text
import net.minecraft.world.gen.feature.org.teamvoided.dusk_autumn.data.gen.worldgen.NoiseCreator
import org.teamvoided.dusk_autumn.DuskAutumns.LOGGER
import org.teamvoided.dusk_autumn.DuskAutumns.id
import org.teamvoided.dusk_autumn.data.gen.fancy_name_pack.FancyNameTranslationProvider
import org.teamvoided.dusk_autumn.data.gen.providers.*
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
import java.util.*

@Suppress("unused")
class DuskAutumnsData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        LOGGER.info("Hello from DataGen")
        val pack = gen.createPack()

        pack.addProvider(::AdvancementsProvider)

        pack.addProvider(::DuskAutumnsWorldGenerator)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::EnglishTranslationProvider)
        pack.addProvider(::RecipesProvider)
        pack.addProvider(::BlockLootTableProvider)
        val blockTags = pack.addProvider(::BlockTagsProvider)
        pack.addProvider { o, r -> ItemTagsProvider(o, r, blockTags) }
        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::EntityTypeTagsProvider)


        val fancyNamePack = gen.createBuiltinResourcePack(id("fancy_names"))
        fancyNamePack.addProvider(::FancyNameTranslationProvider)
        fancyNamePack.addProvider { o -> createResource(o, Text.literal("Better Nether Brick Names")) }
//        val fancyNamePackVanilla = gen.createBuiltinResourcePack(mc("fancy_names"))
//        fancyNamePackVanilla.addProvider(::FancyNameVanillaTranslationProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(RegistryKeys.NOISE_PARAMETERS, NoiseCreator::bootstrap)
        gen.add(RegistryKeys.BIOME, BiomeCreator::boostrap)
        gen.add(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(RegistryKeys.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_PROCESSOR_LIST, StructureProcessorCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_POOL, StructurePoolCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_SET, StructureSetCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_FEATURE, StructureFeatureCreator::bootstrap)
        gen.add(RegistryKeys.WOLF_VARIANT, WolfVariants::bootstrap)
    }

    private fun createResource(o: DataPackOutput, description: Text): PackMetadataProvider {
        return PackMetadataProvider(o).add(
            PackResourceMetadataSection.TYPE, PackResourceMetadataSection(
                description,
                MinecraftVersion.GAME_VERSION.getResourceVersion(ResourceType.CLIENT_RESOURCES), Optional.empty()
            )
        )
    }
}
