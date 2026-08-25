package org.teamvoided.dusks_and_dungeons.data.gen

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_and_dungeons.init.DnDRegistryKeys
import java.util.concurrent.CompletableFuture

class DnDWorldGenerator(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricDynamicRegistryProvider(o, r) {

    override fun getName(): String = "Data Gen"

    override fun configure(reg: HolderLookup.Provider, e: Entries) {
        e.addAll(reg.lookupOrThrow(Registries.NOISE))
        e.addAll(reg.lookupOrThrow(Registries.BIOME))
        e.addAll(reg.lookupOrThrow(Registries.PLACED_FEATURE))
        e.addAll(reg.lookupOrThrow(Registries.CONFIGURED_FEATURE))
        e.addAll(reg.lookupOrThrow(Registries.DENSITY_FUNCTION))

        e.addAll(reg.lookupOrThrow(Registries.PROCESSOR_LIST))
        e.addAll(reg.lookupOrThrow(Registries.TEMPLATE_POOL))
        e.addAll(reg.lookupOrThrow(Registries.STRUCTURE_SET))
        e.addAll(reg.lookupOrThrow(Registries.STRUCTURE))

        e.addAll(reg.lookupOrThrow(Registries.WOLF_VARIANT))
        e.addAll(reg.lookupOrThrow(DnDRegistryKeys.THROWN_ITEM_DEFINITION))

        e.addAll(reg.lookupOrThrow(Registries.DAMAGE_TYPE))

        e.addAll(reg.lookupOrThrow(LithostitchedRegistries.WORLDGEN_MODIFIER))
        e.addAll(reg.lookupOrThrow(LithostitchedRegistries.BIOME_INJECTOR))
    }

}
