package org.teamvoided.dusk_autumn.datagen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.biome.Biome
import org.teamvoided.dusk_autumn.data.DuskBiomeTags
import org.teamvoided.dusk_autumn.init.worldgen.DuskBiomes
import java.util.concurrent.CompletableFuture

class BiomeTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Biome>(o, RegistryKeys.BIOME, r)  {
    override fun configure(arg: HolderLookup.Provider) {

        getOrCreateTagBuilder(DuskBiomeTags.IS_AUTUMN)
            .add(DuskBiomes.AUTUMN_WOODS)
            .add(DuskBiomes.AUTUMN_PASTURES)
            .add(DuskBiomes.AUTUMN_CASCADES)
            .add(DuskBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(DuskBiomeTags.SPAWNS_SILVER_FOXES)
            .forceAddTag(DuskBiomeTags.IS_AUTUMN)
//Vanilla
        getOrCreateTagBuilder(BiomeTags.OVERWORLD)
            .forceAddTag(DuskBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(BiomeTags.FOREST)
            .add(DuskBiomes.AUTUMN_WOODS)
        getOrCreateTagBuilder(BiomeTags.RIVER)
            .add(DuskBiomes.AUTUMN_CASCADES)
        getOrCreateTagBuilder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
            .add(DuskBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.HAS_CLOSER_WATER_FOG)
            .add(DuskBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.INCREASED_FIRE_BURNOUT)
            .add(DuskBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.WATER_ON_MAP_OUTLINES)
            .add(DuskBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.TRAIL_RUINS_HAS_STRUCTURE)
            .add(DuskBiomes.AUTUMN_WOODS)
        getOrCreateTagBuilder(BiomeTags.HAS_MINESHAFT_STRUCTURE)
            .forceAddTag(DuskBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(BiomeTags.HAS_VILLAGE_TAIGA_STRUCTURE)
            .add(DuskBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.HAS_PILLAGER_OUTPOST_STRUCTURE)
            .add(DuskBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.HAS_SWAMP_HUT_STRUCTURE)
            .add(DuskBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_STANDARD_STRUCTURE)
            .add(DuskBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_SWAMP_STRUCTURE)
            .add(DuskBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.STRONGHOLD_BIASED_TO)
            .forceAddTag(DuskBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_COLD_TYPED_FROGS)
            .forceAddTag(DuskBiomeTags.IS_AUTUMN)
//Fabric
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_COLD_OVERWORLD)
            .forceAddTag(DuskBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_DECIDUOUS_TREE)
            .add(DuskBiomes.AUTUMN_WOODS)
            .add(DuskBiomes.AUTUMN_PASTURES)
            .add(DuskBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_VEGETATION_SPARSE)
            .add(DuskBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_FOREST)
            .add(DuskBiomes.AUTUMN_WOODS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_PLAINS)
            .add(DuskBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_RIVER)
            .add(DuskBiomes.AUTUMN_CASCADES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_SWAMP)
            .add(DuskBiomes.AUTUMN_WETLANDS)
    }
}