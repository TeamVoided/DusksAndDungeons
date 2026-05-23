package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.Fluids
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.OreFeatures
import net.minecraft.data.worldgen.features.VegetationFeatures
import net.minecraft.data.worldgen.placement.OrePlacements
import net.minecraft.util.valueproviders.ClampedInt
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter
import net.minecraft.world.level.levelgen.placement.CountPlacement
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement
import net.minecraft.world.level.levelgen.placement.RarityFilter
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber", "LongMethod")
object PlacedFeatureCreator {
    val cascadeSapling: BlockPredicateFilter =
        PlacementUtils.filteredByBlockSurvival(DnDBlocks.CASCADE_SAPLING)
    val goldenBirchSapling: BlockPredicateFilter =
        PlacementUtils.filteredByBlockSurvival(DnDBlocks.GOLDEN_BIRCH_SAPLING)
    val darkOakSapling: BlockPredicateFilter =
        PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING)
    val acaciaSapling: BlockPredicateFilter =
        PlacementUtils.filteredByBlockSurvival(Blocks.ACACIA_SAPLING)

    fun bootstrap(c: BootstrapContext<PlacedFeature>) {
        val configuredFeatureProvider = c.lookup(Registries.CONFIGURED_FEATURE)
        c.register(
            DnDPlacedFeature.OVERGROWN_COBBLESTONE_BOULDER,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.OVERGROWN_COBBLESTONE_BOULDER),
            RarityFilter.onAverageOnceEvery(7),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.PATCH_PUMPKIN_EXTRA,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.PATCH_PUMPKIN_EXTRA),
            RarityFilter.onAverageOnceEvery(50),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.DISK_PODZOL, configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.DISK_PODZOL),
            RarityFilter.onAverageOnceEvery(40),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BlockPredicateFilter.forPredicate(
                BlockPredicate.matchesBlocks(*arrayOf(Blocks.DIRT), (Blocks.GRASS_BLOCK), (Blocks.PODZOL))
            ),
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.DISK_MUD, configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.DISK_MUD),
            RarityFilter.onAverageOnceEvery(10),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BlockPredicateFilter.forPredicate(
                BlockPredicate.matchesBlocks(*arrayOf(Blocks.DIRT), (Blocks.GRASS_BLOCK), (Blocks.PODZOL))
            ),
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.DISK_RED_SAND,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.DISK_RED_SAND),
            CountPlacement.of(3),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BlockPredicateFilter.forPredicate(
                BlockPredicate.matchesFluids(*arrayOf<Fluid>(Fluids.WATER))
            ),
            BiomeFilter.biome()
        )
        saplingFeatures(c, configuredFeatureProvider)
        autumnBiomeFeatures(c, configuredFeatureProvider)
        rockyOres(c, configuredFeatureProvider)
    }

    fun saplingFeatures(
        c: BootstrapContext<PlacedFeature>,
        configuredFeatureProvider: HolderGetter<ConfiguredFeature<*, *>>
    ) {
        c.register(
            DnDPlacedFeature.CASCADE_TREE,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.CASCADE_TREE),
            cascadeSapling
        )
        c.register(
            DnDPlacedFeature.CASCADE_TREE_BEES,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.CASCADE_TREE_BEES),
            cascadeSapling
        )
        c.register(
            DnDPlacedFeature.GOLDEN_BIRCH_TALL,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.GOLDEN_BIRCH_TALL),
            goldenBirchSapling
        )
        c.register(
            DnDPlacedFeature.GOLDEN_BIRCH_TALL_BEES,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.GOLDEN_BIRCH_TALL_BEES),
            goldenBirchSapling
        )
    }

    fun autumnBiomeFeatures(
        c: BootstrapContext<PlacedFeature>,
        configuredFeatureProvider: HolderGetter<ConfiguredFeature<*, *>>
    ) {
        c.register(
            DnDPlacedFeature.ORE_LAPIS_EXTRA,
            configuredFeatureProvider.getOrThrow(OreFeatures.ORE_LAPIS),
            commonOrePlacementModifiers(
                20,
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(80))
            )
        )
        c.register(
            DnDPlacedFeature.CASCADE_TREE_AUTUMN,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.CASCADE_TREE_AUTUMN),
            cascadeSapling
        )
        c.register(
            DnDPlacedFeature.GOLDEN_BIRCH_TALL_AUTUMN,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.GOLDEN_BIRCH_TALL_AUTUMN),
            goldenBirchSapling
        )
        c.register(
            DnDPlacedFeature.DARK_OAK_AUTUMN,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.DARK_OAK_AUTUMN),
            darkOakSapling
        )
        c.register(
            DnDPlacedFeature.ACACIA_AUTUMN,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.ACACIA_AUTUMN),
            acaciaSapling
        )
        c.register(
            DnDPlacedFeature.ACACIA_BUSH_AUTUMN,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.ACACIA_BUSH_AUTUMN),
            acaciaSapling
        )
        c.register(
            DnDPlacedFeature.PATCH_GRASS_AUTUMN_PLAIN,
            configuredFeatureProvider.getOrThrow(VegetationFeatures.PATCH_GRASS),
            NoiseThresholdCountPlacement.of(-0.8, 5, 10),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BlockPredicateFilter.forPredicate(
                BlockPredicate.matchesBlocks(
                    BlockPos.ZERO.below(),
                    *arrayOf(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT, Blocks.COARSE_DIRT)
                )
            ),
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.PATCH_TALL_GRASS_AUTUMN_PLAIN,
            configuredFeatureProvider.getOrThrow(VegetationFeatures.PATCH_TALL_GRASS),
            NoiseThresholdCountPlacement.of(-0.8, 0, 7),
            RarityFilter.onAverageOnceEvery(32),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BlockPredicateFilter.forPredicate(
                BlockPredicate.matchesBlocks(
                    BlockPos.ZERO.below(),
                    *arrayOf(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT, Blocks.COARSE_DIRT)
                )
            ),
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.AUTUMN_WOODS_VEGETATION,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.AUTUMN_WOODS_VEGETATION),
            CountPlacement.of(14),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.AUTUMN_PASTURES_VEGETATION),
            RarityFilter.onAverageOnceEvery(2),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.GOLDEN_WOODS_VEGETATION,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.GOLDEN_VEGETATION),
            PlacementUtils.countExtra(10, 0.1f, 1),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.GOLDEN_PASTURES_VEGETATION,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.GOLDEN_VEGETATION),
            RarityFilter.onAverageOnceEvery(2),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BiomeFilter.biome()
        )
//        c.register(
//            AUTUMN_WETLANDS_VEGETATION,
//            holderProvider.getHolderOrThrow(DnDConfiguredFeature.AUTUMN_WETLANDS_VEGETATION),
//            PlacedFeatureUtil.createCountExtraModifier(3, 0.25f, 1),
//            InSquarePlacementModifier.getInstance(),
//            SurfaceWaterDepthFilterPlacementModifier.create(3),
//            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
//            BiomePlacementModifier.getInstance()
//        )

        c.register(
            DnDPlacedFeature.FLOWER_AUTUMN,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.FLOWER_AUTUMN),
            noiseThresholdFlowerPlacement(14)
        )
        c.register(
            DnDPlacedFeature.BLUE_PETALS, configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.BLUE_PETALS),
            noiseThresholdFlowerPlacement(18)
        )
        c.register(
            DnDPlacedFeature.FAIRY_RING_RED,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.FAIRY_RING_RED),
            RarityFilter.onAverageOnceEvery(7),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.PATCH_ROSEBUSH,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.PATCH_ROSEBUSH),
            RarityFilter.onAverageOnceEvery(7),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(ClampedInt.of(UniformInt.of(-3, 1), 0, 1)),
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.AUTUMN_FARMLANDS,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.AUTUMN_FARMLAND),
            RarityFilter.onAverageOnceEvery(21),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()
        )
        c.register(
            DnDPlacedFeature.CROPS_WILD_WHEAT,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.CROPS_WILD_WHEAT),
            RarityFilter.onAverageOnceEvery(9),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        )
    }

    fun rockyOres(
        c: BootstrapContext<PlacedFeature>,
        configuredFeatureProvider: HolderGetter<ConfiguredFeature<*, *>>
    ) {
        c.register(
            DnDPlacedFeature.ROCKY_ORE_UPPER,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.ROCKY_OVERWORLD_ORE),
            OrePlacements.rareOrePlacement(
                16,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128))
            )
        )
        c.register(
            DnDPlacedFeature.ROCKY_ORE_LOWER,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.ROCKY_OVERWORLD_ORE),
            OrePlacements.rareOrePlacement(
                8,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))
            )
        )
        c.register(
            DnDPlacedFeature.SLATED_ORE,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.SLATED_OVERWORLD_ORE),
            OrePlacements.rareOrePlacement(
                8,
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0))
            )
        )
        c.register(
            DnDPlacedFeature.BLACKSTONED_ORE,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.BLACKSTONE_NETHER_ORE),
            OrePlacements.rareOrePlacement(
                16,
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.belowTop(128))
            )
        )
    }

    fun noiseThresholdFlowerPlacement(rarity: Int): List<PlacementModifier> {
        return listOf(
            NoiseThresholdCountPlacement.of(-0.8, 15, 4),
            RarityFilter.onAverageOnceEvery(rarity),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        )

    }

    fun orePlacementModifiers(
        firstModifier: PlacementModifier, secondModifier: PlacementModifier
    ): List<PlacementModifier> {
        return listOf(
            firstModifier, InSquarePlacement.spread(),
            secondModifier, BiomeFilter.biome()
        )
    }

    fun commonOrePlacementModifiers(count: Int, modifier: PlacementModifier): List<PlacementModifier> {
        return orePlacementModifiers(CountPlacement.of(count), modifier)
    }

    private fun treePlacementModifiersBase(modifier: PlacementModifier): ImmutableList.Builder<PlacementModifier> {
        return ImmutableList.builder<PlacementModifier>().add(modifier).add(InSquarePlacement.spread())
            .add(SurfaceWaterDepthFilter.forMaxDepth(0)).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
            .add(BiomeFilter.biome())
    }


    fun BootstrapContext<PlacedFeature>.register(
        registryKey: ResourceKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        vararg placementModifiers: PlacementModifier
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers.toList()))

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: ResourceKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        placementModifiers: List<PlacementModifier>
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers))
}