package org.teamvoided.dusks_and_dungeons.data.gen

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.DetectedVersion
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.metadata.PackMetadataGenerator
import net.minecraft.network.chat.Component
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.metadata.pack.PackMetadataSection
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.id
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.data.gen.assets.lang.EnLangProvider
import org.teamvoided.dusks_and_dungeons.data.gen.data.AdvancementsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.data.litho.BiomeInjectors
import org.teamvoided.dusks_and_dungeons.data.gen.data.litho.worldgen_modifiers.WorldgenModifiers
import org.teamvoided.dusks_and_dungeons.data.gen.data.loot.BlockInteractLootTablesProvider
import org.teamvoided.dusks_and_dungeons.data.gen.data.loot.BlockLootTableProvider
import org.teamvoided.dusks_and_dungeons.data.gen.data.registry.DamageTypes
import org.teamvoided.dusks_and_dungeons.data.gen.data.registry.ThrownItems
import org.teamvoided.dusks_and_dungeons.data.gen.fancy_name_pack.FancyNameTranslationProvider
import org.teamvoided.dusks_and_dungeons.data.gen.models.ModelProvider
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
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.*
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider
import java.util.*

class DnDData : DataGeneratorEntrypoint {

    override fun getEffectiveModId(): String = DusksAndDungeons.MODID

    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Running \"${gen.modContainer.metadata.name}\" Datagen!")
        val pack = gen.createPack()

        //   -=- Assets -=-

        //   -=-  Data -=-
        pack.addProvider(::DnDDynProvider)
        // Loot Tables
        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::BlockInteractLootTablesProvider)
        // Misc
        pack.addProvider(::AdvancementsProvider)


        // Not Updated
        pack.addProvider(::ModelProvider)
        pack.addProvider(::EnLangProvider)
        pack.addProvider(::RecipesProvider)
        val blockTags = pack.addProvider(::BlockTagsProvider)
        pack.addProvider { o, p -> ItemTagsProvider(o, p, blockTags) }
        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::EntityTypeTagsProvider)

        //TODO move to pack folder
        val fancyNamePack = gen.createBuiltinResourcePack(id("fancy_names"))
        fancyNamePack.addProvider(::FancyNameTranslationProvider)
        fancyNamePack.addProvider { o -> createResource(o, Component.literal("Better Nether Brick Names")) }
//        val fancyNamePackVanilla = gen.createBuiltinResourcePack(mc("fancy_names"))
//        fancyNamePackVanilla.addProvider(::FancyNameVanillaTranslationProvider)

    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        // Word Gen
        gen.add(Registries.NOISE, NoiseCreator::bootstrap)
        gen.add(Registries.DENSITY_FUNCTION, DensityFunctionCreator::bootstrap)
        gen.add(Registries.BIOME, BiomeCreator::boostrap)
        gen.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(Registries.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)
        // Structures
        gen.add(Registries.PROCESSOR_LIST, StructureProcessorCreator::bootstrap)
        gen.add(Registries.TEMPLATE_POOL, StructurePoolCreator::bootstrap)
        gen.add(Registries.STRUCTURE, StructureFeatureCreator::bootstrap)
        gen.add(Registries.STRUCTURE_SET, StructureSetCreator::bootstrap)
        // Lithostitched
        gen.add(LithostitchedRegistries.WORLDGEN_MODIFIER, WorldgenModifiers::init)
        gen.add(LithostitchedRegistries.BIOME_INJECTOR, BiomeInjectors::init)
        // Entity Variants
        gen.add(Registries.WOLF_VARIANT, WolfVariants::bootstrap)
        // Misc
        gen.add(DnDRegistryKeys.THROWN_ITEM_DEFINITION, ThrownItems::init)
        gen.add(Registries.DAMAGE_TYPE, DamageTypes::bootstrap)
    }

    class DnDDynProvider(o: FabricOutput, p: FutureProvider) : FabricDynamicRegistryProvider(o, p) {

        override fun getName(): String = "DnD Dynamic"

        override fun configure(provider: HolderLookup.Provider, entires: Entries) {
            // Word Gen
            entires.addAll(provider.lookupOrThrow(Registries.NOISE))
            entires.addAll(provider.lookupOrThrow(Registries.DENSITY_FUNCTION))
            entires.addAll(provider.lookupOrThrow(Registries.BIOME))
            entires.addAll(provider.lookupOrThrow(Registries.PLACED_FEATURE))
            entires.addAll(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE))
            // Structures
            entires.addAll(provider.lookupOrThrow(Registries.PROCESSOR_LIST))
            entires.addAll(provider.lookupOrThrow(Registries.TEMPLATE_POOL))
            entires.addAll(provider.lookupOrThrow(Registries.STRUCTURE))
            entires.addAll(provider.lookupOrThrow(Registries.STRUCTURE_SET))
            // Lithostitched
            entires.addAll(provider.lookupOrThrow(LithostitchedRegistries.WORLDGEN_MODIFIER))
            entires.addAll(provider.lookupOrThrow(LithostitchedRegistries.BIOME_INJECTOR))
            // Entity Variants
            entires.addAll(provider.lookupOrThrow(Registries.WOLF_VARIANT))
            // Misc
            entires.addAll(provider.lookupOrThrow(DnDRegistryKeys.THROWN_ITEM_DEFINITION))
            entires.addAll(provider.lookupOrThrow(Registries.DAMAGE_TYPE))
        }

    }

    //TODO move to pack folder
    fun createResource(o: PackOutput, description: Component): PackMetadataGenerator {
        return PackMetadataGenerator(o).add(
            PackMetadataSection.TYPE, PackMetadataSection(
                description,
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES), Optional.empty()
            )
        )
    }

}