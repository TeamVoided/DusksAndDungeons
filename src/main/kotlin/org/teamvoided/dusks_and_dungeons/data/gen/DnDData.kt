package org.teamvoided.dusks_and_dungeons.data.gen

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
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.data.gen.fancy_name_pack.FancyNameTranslationProvider
import org.teamvoided.dusks_and_dungeons.data.gen.models.ModelProvider
import org.teamvoided.dusks_and_dungeons.data.gen.providers.*
import org.teamvoided.dusks_and_dungeons.data.gen.recipes.RecipesProvider
import org.teamvoided.dusks_and_dungeons.data.gen.structure.StructureFeatureCreator
import org.teamvoided.dusks_and_dungeons.data.gen.structure.StructurePoolCreator
import org.teamvoided.dusks_and_dungeons.data.gen.structure.StructureProcessorCreator
import org.teamvoided.dusks_and_dungeons.data.gen.structure.StructureSetCreator
import org.teamvoided.dusks_and_dungeons.data.gen.tags.BiomeTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.BlockTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.EntityTypeTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.ItemTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.variants.WolfVariants
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.BiomeCreator
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.ConfiguredFeatureCreator
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.NoiseCreator
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.PlacedFeatureCreator
import java.util.*

@Suppress("unused")
class DnDData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()

        pack.addProvider(::AdvancementsProvider)

        pack.addProvider(::DnDWorldGenerator)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::EnLangProvider)
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

        pack.addProvider(::DnDMappingsProvider)
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
