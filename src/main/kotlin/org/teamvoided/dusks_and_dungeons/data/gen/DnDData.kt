package org.teamvoided.dusks_and_dungeons.data.gen

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons
import org.teamvoided.dusks_and_dungeons.DusksAndDungeons.log
import org.teamvoided.dusks_and_dungeons.data.gen.assets.lang.EnLangProvider
import org.teamvoided.dusks_and_dungeons.data.gen.data.AdvancementsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.data.litho.BiomeInjectors
import org.teamvoided.dusks_and_dungeons.data.gen.data.litho.WorldgenModifiers
import org.teamvoided.dusks_and_dungeons.data.gen.data.loot.BlockInteractLootTablesProvider
import org.teamvoided.dusks_and_dungeons.data.gen.data.loot.BlockLootTableProvider
import org.teamvoided.dusks_and_dungeons.data.gen.data.registry.DamageTypes
import org.teamvoided.dusks_and_dungeons.data.gen.data.registry.ThrownItems
import org.teamvoided.dusks_and_dungeons.data.gen.data.registry.WolfVariants
import org.teamvoided.dusks_and_dungeons.data.gen.data.structure.StructureFeatureCreator
import org.teamvoided.dusks_and_dungeons.data.gen.data.structure.StructurePoolCreator
import org.teamvoided.dusks_and_dungeons.data.gen.data.structure.StructureProcessorCreator
import org.teamvoided.dusks_and_dungeons.data.gen.data.structure.StructureSetCreator
import org.teamvoided.dusks_and_dungeons.data.gen.models.ModelProvider
import org.teamvoided.dusks_and_dungeons.data.gen.packs.FancyNamesPack
import org.teamvoided.dusks_and_dungeons.data.gen.recipes.RecipesProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.BiomeTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.BlockTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.EntityTypeTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.tags.ItemTagsProvider
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.*
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
            addProvider(::EntityTypeTagsProvider)
        }

        FancyNamesPack.create(gen)
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
        gen.add(LithostitchedRegistries.WORLDGEN_MODIFIER, WorldgenModifiers::bootstrap)
        gen.add(LithostitchedRegistries.BIOME_INJECTOR, BiomeInjectors::bootstrap)
        // Entity Variants
        gen.add(Registries.WOLF_VARIANT, WolfVariants::bootstrap)
        // Misc
        gen.add(DnDRegistryKeys.THROWN_ITEM_DEFINITION, ThrownItems::bootstrap)
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