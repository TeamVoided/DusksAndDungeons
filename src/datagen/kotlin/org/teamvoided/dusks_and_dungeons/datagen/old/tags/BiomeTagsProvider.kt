package org.teamvoided.dusks_and_dungeons.datagen.old.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.tags.BiomeTags
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBiomeTags
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDBiomes
import java.util.concurrent.CompletableFuture

class BiomeTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Biome>(o, Registries.BIOME, r) {
    override fun addTags(arg: HolderLookup.Provider) {
        duskTags()
        vanillaTags()
        conventionTags()
    }

    fun duskTags() {
        getOrCreateTagBuilder(DnDBiomeTags.AUTUMN_WOODS)
            .forceAddTag(ConventionalBiomeTags.IS_FOREST)
            .add(Biomes.TAIGA)
            .remove(Biomes.GROVE)
        getOrCreateTagBuilder(DnDBiomeTags.AUTUMN_PASTURES)
            .forceAddTag(ConventionalBiomeTags.IS_PLAINS)
            .add(Biomes.MEADOW)
            .add(Biomes.CHERRY_GROVE)
        getOrCreateTagBuilder(DnDBiomeTags.AUTUMN_RIVERS)
            .add(Biomes.RIVER)
        getOrCreateTagBuilder(DnDBiomeTags.IS_AUTUMN) // use the list in the AdvancementsProvider class?
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.GOLDEN_WOODS)
            .add(DnDBiomes.AUTUMN_PASTURES)
            .add(DnDBiomes.GOLDEN_PASTURES)
            .add(DnDBiomes.AUTUMN_CASCADES)

        getOrCreateTagBuilder(DnDBiomeTags.HAS_STRUCTURE_AUTUMN_RUINS)
//            .addOptionalTag(DuskBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(DnDBiomeTags.HAS_STRUCTURE_VERDANT_MINESHAFT)
            .add(DnDBiomes.OVERGROWN_GROTTO)

        getOrCreateTagBuilder(DnDBiomeTags.IS_CAVE)
            .add(DnDBiomes.OVERGROWN_GROTTO)

        getOrCreateTagBuilder(DnDBiomeTags.SPAWNS_SILVER_FOXES)
            .forceAddTag(DnDBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(DnDBiomeTags.SPAWNS_AUTUMN_WOLVES)
            .forceAddTag(DnDBiomeTags.IS_AUTUMN)// remove the golden ones eventually

        getOrCreateTagBuilder(DnDBiomeTags.GOLD_MUSHROOMS_CAVE)
            .forceAddTag(ConventionalBiomeTags.IS_OVERWORLD)
        getOrCreateTagBuilder(DnDBiomeTags.GOLD_MUSHROOMS_SURFACE)
            .forceAddTag(ConventionalBiomeTags.IS_FOREST)
            .forceAddTag(ConventionalBiomeTags.IS_TAIGA)
        getOrCreateTagBuilder(DnDBiomeTags.GOLD_MUSHROOMS_HUGE)
            .forceAddTag(ConventionalBiomeTags.IS_MUSHROOM)
            .add(DnDBiomes.OVERGROWN_GROTTO)
            .add(Biomes.DARK_FOREST)

        getOrCreateTagBuilder(DnDBiomeTags.MOSSKIN_PUMPKINS_CAVE)
            .add(DnDBiomes.OVERGROWN_GROTTO)
            .add(Biomes.LUSH_CAVES)
        getOrCreateTagBuilder(DnDBiomeTags.GLOOM_PUMPKINS_EXTRA)
            .add(Biomes.DARK_FOREST)

        getOrCreateTagBuilder(DnDBiomeTags.CRIMSON_WART)
            .add(Biomes.CRIMSON_FOREST)
            .add(Biomes.SOUL_SAND_VALLEY)
            .add(Biomes.NETHER_WASTES)
        getOrCreateTagBuilder(DnDBiomeTags.WARPED_WART)
            .add(Biomes.WARPED_FOREST)
    }

    fun vanillaTags() {
        getOrCreateTagBuilder(BiomeTags.IS_OVERWORLD)
            .forceAddTag(DnDBiomeTags.IS_AUTUMN)
            .forceAddTag(DnDBiomeTags.IS_CAVE)
        getOrCreateTagBuilder(BiomeTags.IS_FOREST)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.GOLDEN_WOODS)
        getOrCreateTagBuilder(BiomeTags.IS_RIVER)
            .add(DnDBiomes.AUTUMN_CASCADES)
        getOrCreateTagBuilder(BiomeTags.HAS_TRIAL_CHAMBERS)
            .forceAddTag(DnDBiomeTags.IS_AUTUMN)
            .forceAddTag(DnDBiomeTags.IS_CAVE)
        getOrCreateTagBuilder(BiomeTags.HAS_TRAIL_RUINS)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.GOLDEN_WOODS)
        getOrCreateTagBuilder(BiomeTags.HAS_MINESHAFT)
            .forceAddTag(DnDBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(BiomeTags.HAS_VILLAGE_TAIGA)
            .add(DnDBiomes.AUTUMN_PASTURES)
            .add(DnDBiomes.GOLDEN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.HAS_PILLAGER_OUTPOST)
            .add(DnDBiomes.AUTUMN_PASTURES)
            .add(DnDBiomes.GOLDEN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_STANDARD)
            .forceAddTag(DnDBiomeTags.IS_CAVE)
            .add(DnDBiomes.AUTUMN_PASTURES)
            .add(DnDBiomes.GOLDEN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.STRONGHOLD_BIASED_TO)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.GOLDEN_WOODS)
            .add(DnDBiomes.AUTUMN_PASTURES)
            .add(DnDBiomes.GOLDEN_PASTURES)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_COLD_VARIANT_FROGS)
            .forceAddTag(DnDBiomeTags.IS_AUTUMN)
    }

    fun conventionTags() {
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_COLD_OVERWORLD)
            .forceAddTag(DnDBiomeTags.IS_AUTUMN)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_BIRCH_FOREST)
            .add(DnDBiomes.GOLDEN_WOODS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_DECIDUOUS_TREE)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.GOLDEN_WOODS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_VEGETATION_DENSE_OVERWORLD)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.GOLDEN_WOODS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_VEGETATION_SPARSE_OVERWORLD)
            .add(DnDBiomes.AUTUMN_PASTURES)
            .add(DnDBiomes.GOLDEN_PASTURES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_FOREST)
            .add(DnDBiomes.AUTUMN_WOODS)
            .add(DnDBiomes.GOLDEN_WOODS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_PLAINS)
            .add(DnDBiomes.AUTUMN_PASTURES)
            .add(DnDBiomes.GOLDEN_PASTURES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_RIVER)
            .add(DnDBiomes.AUTUMN_CASCADES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_CAVE)
            .forceAddTag(DnDBiomeTags.IS_CAVE)
    }
}