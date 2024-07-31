package org.teamvoided.dusk_autumn.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.biome.Biome
import org.teamvoided.dusk_autumn.data.tags.DnDBiomeTags
import org.teamvoided.dusk_autumn.init.worldgen.DnDBiomes
import java.util.concurrent.CompletableFuture

class BiomeTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Biome>(o, RegistryKeys.BIOME, r) {
    override fun configure(arg: HolderLookup.Provider) {
        duskTags()
        vanillaTags()
        conventionTags()
    }

    fun duskTags() {
        getOrCreateTagBuilder(DnDBiomeTags.IS_AUTUMN)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.AUTUMN_PASTURES)
            .add(DnDBiomes.AUTUMN_CASCADES)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(DnDBiomeTags.HAS_STRUCTURE_AUTUMN_RUINS)
//            .addOptionalTag(DuskBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(DnDBiomeTags.SPAWNS_SILVER_FOXES)
            .addOptionalTag(DnDBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(DnDBiomeTags.SPAWNS_AUTUMN_WOLVES)
            .addOptionalTag(DnDBiomeTags.IS_AUTUMN)
    }

    fun vanillaTags() {
        getOrCreateTagBuilder(BiomeTags.OVERWORLD)
            .addOptionalTag(DnDBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(BiomeTags.FOREST)
            .add(DnDBiomes.AUTUMN_WOODS)
        getOrCreateTagBuilder(BiomeTags.RIVER)
            .add(DnDBiomes.AUTUMN_CASCADES)
        getOrCreateTagBuilder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.HAS_CLOSER_WATER_FOG)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.INCREASED_FIRE_BURNOUT)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.WATER_ON_MAP_OUTLINES)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.TRAIL_RUINS_HAS_STRUCTURE)
            .add(DnDBiomes.AUTUMN_WOODS)
        getOrCreateTagBuilder(BiomeTags.HAS_MINESHAFT_STRUCTURE)
            .addOptionalTag(DnDBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(BiomeTags.HAS_VILLAGE_TAIGA_STRUCTURE)
            .add(DnDBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.HAS_PILLAGER_OUTPOST_STRUCTURE)
            .add(DnDBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.HAS_SWAMP_HUT_STRUCTURE)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_STANDARD_STRUCTURE)
            .add(DnDBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_SWAMP_STRUCTURE)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(BiomeTags.STRONGHOLD_BIASED_TO)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_COLD_TYPED_FROGS)
            .addOptionalTag(DnDBiomeTags.IS_AUTUMN)
    }

    fun conventionTags() {
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_COLD_OVERWORLD)
            .addOptionalTag(DnDBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_DECIDUOUS_TREE)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_VEGETATION_DENSE_OVERWORLD)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_VEGETATION_SPARSE_OVERWORLD)
            .add(DnDBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_FOREST)
            .add(DnDBiomes.AUTUMN_WOODS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_PLAINS)
            .add(DnDBiomes.AUTUMN_PASTURES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_RIVER)
            .add(DnDBiomes.AUTUMN_CASCADES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_SWAMP)
            .add(DnDBiomes.AUTUMN_WETLANDS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_WET_OVERWORLD)
            .add(DnDBiomes.AUTUMN_WETLANDS)
    }
}