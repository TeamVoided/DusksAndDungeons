package org.teamvoided.dusks_and_dungeons.datagen

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderLookup.RegistryLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.datagen.old.models.ModelProvider
import org.teamvoided.dusks_and_dungeons.datagen.old.recipes.RecipesProvider
import org.teamvoided.dusks_and_dungeons.datagen.old.worldgen.BiomeCreator
import org.teamvoided.dusks_and_dungeons.datagen.old.worldgen.ConfiguredFeatureCreator
import org.teamvoided.dusks_and_dungeons.datagen.old.worldgen.PlacedFeatureCreator
import org.teamvoided.dusks_and_dungeons.datagen.assets.lang.EnLangProvider
import org.teamvoided.dusks_and_dungeons.datagen.data.AdvancementsProvider
import org.teamvoided.dusks_and_dungeons.datagen.data.litho.BiomeInjectors
import org.teamvoided.dusks_and_dungeons.datagen.data.litho.WorldgenModifiers
import org.teamvoided.dusks_and_dungeons.datagen.data.loot.BlockInteractLootTablesProvider
import org.teamvoided.dusks_and_dungeons.datagen.data.loot.BlockLootTableProvider
import org.teamvoided.dusks_and_dungeons.datagen.data.registry.ModDamageTypes
import org.teamvoided.dusks_and_dungeons.datagen.data.registry.ModThrownItemDefinitions
import org.teamvoided.dusks_and_dungeons.datagen.data.registry.ModWolfVariants
import org.teamvoided.dusks_and_dungeons.datagen.data.structure.ModProcessorLists
import org.teamvoided.dusks_and_dungeons.datagen.data.structure.ModStructureSets
import org.teamvoided.dusks_and_dungeons.datagen.data.structure.ModStructures
import org.teamvoided.dusks_and_dungeons.datagen.data.structure.ModTemplatePools
import org.teamvoided.dusks_and_dungeons.datagen.data.tag.EntityTypeTagProvider
import org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.ModDensityFunctions
import org.teamvoided.dusks_and_dungeons.datagen.data.worldgen.ModNoises
import org.teamvoided.dusks_and_dungeons.datagen.old.tags.BiomeTagsProvider
import org.teamvoided.dusks_and_dungeons.datagen.old.tags.BlockTagsProvider
import org.teamvoided.dusks_and_dungeons.datagen.old.tags.ItemTagsProvider
import org.teamvoided.dusks_and_dungeons.datagen.packs.FancyNamesPack
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys
import org.teamvoided.voidlib.devin.FabricOutput
import org.teamvoided.voidlib.devin.FutureProvider

object DnDData : DataGeneratorEntrypoint {

    override fun getEffectiveModId(): String = DusksAndDungeons.MODID

    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Running \"${gen.modContainer.metadata.name}\" Datagen!")
        gen.createPack {
            //   -=- Assets -=-

            //   -=-  Data -=-
            addProvider(::DnDDynProvider)
            // Loot Tables
            addProvider(::BlockLootTableProvider)
            addProvider(::BlockInteractLootTablesProvider)
            // Misc
            addProvider(::AdvancementsProvider)


            // Not Updated
            addProvider(::ModelProvider)
            addProvider(::EnLangProvider)
            addProvider(::RecipesProvider)
            val blockTags = addProvider(::BlockTagsProvider)
            addProvider { o, p -> ItemTagsProvider(o, p, blockTags) }
            addProvider(::BiomeTagsProvider)
            addProvider(::EntityTypeTagProvider)
        }

        FancyNamesPack.create(gen)
    }


    override fun buildRegistry(gen: RegistrySetBuilder) {
        // Word Gen
        gen.add(Registries.NOISE, ModNoises::bootstrap)
        gen.add(Registries.DENSITY_FUNCTION, ModDensityFunctions::bootstrap)
        gen.add(Registries.BIOME, BiomeCreator::boostrap)
        gen.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(Registries.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)
        // Structures
        gen.add(Registries.PROCESSOR_LIST, ModProcessorLists::bootstrap)
        gen.add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap)
        gen.add(Registries.STRUCTURE, ModStructures::bootstrap)
        gen.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
        // Lithostitched
        gen.add(LithostitchedRegistries.WORLDGEN_MODIFIER, WorldgenModifiers::bootstrap)
        gen.add(LithostitchedRegistries.BIOME_INJECTOR, BiomeInjectors::bootstrap)
        // Entity Variants
        gen.add(Registries.WOLF_VARIANT, ModWolfVariants::bootstrap)
        // Misc
        gen.add(DnDRegistryKeys.THROWN_ITEM_DEFINITION, ModThrownItemDefinitions::bootstrap)
        gen.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap)
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

        @Suppress("unused")
        fun <T : Any> Entries.addEverything(registry: RegistryLookup<T>): MutableList<Holder<T>> {
            return registry.listElementIds().map { add(registry, it) }.toList().toMutableList()
        }

    }

    fun FabricDataGenerator.createPack(block: FabricDataGenerator.Pack.() -> Unit): FabricDataGenerator.Pack {
        val pack = createPack()
        with(pack, block)
        return pack
    }

    fun FabricDataGenerator.createBuiltInPack(
        id: ResourceLocation, block: FabricDataGenerator.Pack.() -> Unit,
    ): FabricDataGenerator.Pack {
        val pack = createBuiltinResourcePack(id)
        with(pack, block)
        return pack
    }

}