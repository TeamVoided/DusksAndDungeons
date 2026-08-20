package org.teamvoided.dusks_and_dungeons.data.gen

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.DetectedVersion
import net.minecraft.data.PackOutput
import net.minecraft.data.metadata.PackMetadataGenerator
import net.minecraft.core.registries.Registries
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.metadata.pack.PackMetadataSection
import net.minecraft.network.chat.Component
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.data.gen.data.litho.BiomeInjectors
import org.teamvoided.dusks_and_dungeons.data.gen.data.litho.worldgen_modifiers.WorldgenModifiers
import org.teamvoided.dusks_and_dungeons.data.gen.data.loot.BlockInteractLootTablesProvider
import org.teamvoided.dusks_and_dungeons.data.gen.fancy_name_pack.FancyNameTranslationProvider
import org.teamvoided.dusks_and_dungeons.data.gen.models.ModelProvider
import org.teamvoided.dusks_and_dungeons.data.gen.providers.AdvancementsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.providers.BlockLootTableProvider
import org.teamvoided.dusks_and_dungeons.data.gen.providers.EnLangProvider
import org.teamvoided.dusks_and_dungeons.data.gen.recipes.RecipesProvider
import org.teamvoided.dusks_and_dungeons.data.gen.structure.StructureFeatureCreator
import org.teamvoided.dusks_and_dungeons.data.gen.structure.StructurePoolCreator
import org.teamvoided.dusks_and_dungeons.data.gen.structure.StructureProcessorCreator
import org.teamvoided.dusks_and_dungeons.data.gen.structure.StructureSetCreator
import org.teamvoided.dusks_and_dungeons.data.gen.tags.BiomeTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.BlockTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.EntityTypeTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.ItemTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.variants.RaccoonVariants
import org.teamvoided.dusks_and_dungeons.data.gen.variants.WolfVariants
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.BiomeCreator
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.ConfiguredFeatureCreator
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.DensityFunctionCreator
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.NoiseCreator
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.PlacedFeatureCreator
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys
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
        pack.addProvider(::BlockInteractLootTablesProvider)
        val blockTags = pack.addProvider(::BlockTagsProvider)
        pack.addProvider { o, r -> ItemTagsProvider(o, r, blockTags) }
        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::EntityTypeTagsProvider)


        val fancyNamePack = gen.createBuiltinResourcePack(id("fancy_names"))
        fancyNamePack.addProvider(::FancyNameTranslationProvider)
        fancyNamePack.addProvider { o -> createResource(o, Component.literal("Better Nether Brick Names")) }
//        val fancyNamePackVanilla = gen.createBuiltinResourcePack(mc("fancy_names"))
//        fancyNamePackVanilla.addProvider(::FancyNameVanillaTranslationProvider)

    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(Registries.NOISE, NoiseCreator::bootstrap)
        gen.add(Registries.BIOME, BiomeCreator::boostrap)
        gen.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(Registries.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)
        gen.add(Registries.DENSITY_FUNCTION, DensityFunctionCreator::bootstrap)

        gen.add(Registries.PROCESSOR_LIST, StructureProcessorCreator::bootstrap)
        gen.add(Registries.TEMPLATE_POOL, StructurePoolCreator::bootstrap)
        gen.add(Registries.STRUCTURE_SET, StructureSetCreator::bootstrap)
        gen.add(Registries.STRUCTURE, StructureFeatureCreator::bootstrap)

        gen.add(Registries.WOLF_VARIANT, WolfVariants::bootstrap)
        gen.add(DnDRegistryKeys.RACCOON_VARIANT, RaccoonVariants::bootstrap)

        gen.add(LithostitchedRegistries.WORLDGEN_MODIFIER, WorldgenModifiers::init)
        gen.add(LithostitchedRegistries.BIOME_INJECTOR, BiomeInjectors::init)
    }

    private fun createResource(o: PackOutput, description: Component): PackMetadataGenerator {
        return PackMetadataGenerator(o).add(
            PackMetadataSection.TYPE, PackMetadataSection(
                description,
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES), Optional.empty()
            )
        )
    }

}
