package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.OreFeatures
import net.minecraft.data.worldgen.features.VegetationFeatures
import net.minecraft.data.worldgen.placement.OrePlacements
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.util.valueproviders.ClampedInt
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*
import net.minecraft.world.level.levelgen.placement.RarityFilter
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.Fluids
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber", "LongMethod")
object PlacedFeatureCreator {
    val cascadeSapling: BlockPredicateFilter =
        PlacementUtils.filteredByBlockSurvival(DnDBlocks.CASCADE_SAPLING)
    val sypiaSapling: BlockPredicateFilter =
        PlacementUtils.filteredByBlockSurvival(DnDBlocks.SYPIA_SAPLING)
    val darkOakSapling: BlockPredicateFilter =
        PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING)
    val acaciaSapling: BlockPredicateFilter =
        PlacementUtils.filteredByBlockSurvival(Blocks.ACACIA_SAPLING)

    fun bootstrap(c: BootstrapContext<PlacedFeature>) {
        val cfgLookup = c.lookup(Registries.CONFIGURED_FEATURE)
        c.register(
            DnDPlacedFeature.OVERGROWN_COBBLESTONE_BOULDER,
            cfgLookup.getOrThrow(DnDConfiguredFeature.OVERGROWN_COBBLESTONE_BOULDER),
            RarityFilter.onAverageOnceEvery(7),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BiomeFilter.biome()
        )
        c.pumpkin(DnDPlacedFeature.PATCH_PUMPKIN_EXTRA, DnDConfiguredFeature.PATCH_PUMPKIN_EXTRA)
        c.pumpkin(DnDPlacedFeature.PATCH_LANTERN_PUMPKIN_EXTRA, DnDConfiguredFeature.PATCH_PUMPKIN_LANTERN_EXTRA)
        c.pumpkin(DnDPlacedFeature.PATCH_MOSSKIN_PUMPKIN_EXTRA, DnDConfiguredFeature.PATCH_PUMPKIN_MOSSKIN_EXTRA)
        c.pumpkin(DnDPlacedFeature.PATCH_GLOOM_PUMPKIN_EXTRA, DnDConfiguredFeature.PATCH_PUMPKIN_GLOOM_EXTRA)
        c.register(
            DnDPlacedFeature.DISK_PODZOL, cfgLookup.getOrThrow(DnDConfiguredFeature.DISK_PODZOL),
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
            DnDPlacedFeature.DISK_MUD, cfgLookup.getOrThrow(DnDConfiguredFeature.DISK_MUD),
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
            cfgLookup.getOrThrow(DnDConfiguredFeature.DISK_RED_SAND),
            CountPlacement.of(3),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_TOP_SOLID,
            BlockPredicateFilter.forPredicate(
                BlockPredicate.matchesFluids(*arrayOf<Fluid>(Fluids.WATER))
            ),
            BiomeFilter.biome()
        )
        saplingFeatures(c, cfgLookup)
        autumnBiomeFeatures(c, cfgLookup)


        // Golden Mushrooms
        c.register(//places in caves in biomes
            DnDPlacedFeature.GOLDEN_MUSHROOM_CAVE,
            cfgLookup.getOrThrow(DnDConfiguredFeature.PATCH_GOLDEN_MUSHROOM),
            RarityFilter.onAverageOnceEvery(4),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            InSquarePlacement.spread(),
            SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Int.MIN_VALUE, -13),
            BiomeFilter.biome()
        )
        c.register(//places on surface in biomes
            DnDPlacedFeature.GOLDEN_MUSHROOM_SURFACE,
            cfgLookup.getOrThrow(DnDConfiguredFeature.PATCH_GOLDEN_MUSHROOM),
            RarityFilter.onAverageOnceEvery(16),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        )
        c.cavePlacement(//places in caves and surfaces with huge mushrooms
            DnDPlacedFeature.GOLDEN_MUSHROOM_HUGE_PATCH,
            DnDConfiguredFeature.PATCH_GOLDEN_MUSHROOM_WITH_HUGE,
            1,
            Direction.DOWN
        )

        c.cavePlacement(
            DnDPlacedFeature.OVERGROWTH_TREE_ROOTED,
            DnDConfiguredFeature.OVERGROWTH_TREE_ROOTED,
            1,
            Direction.UP
        )
        //c.cavePlacement(
        //    CavePlacements.CAVE_VINES,
        //    holder9,
        //    188,
        //    Direction.UP
        //)
        c.cavePlacement(
            DnDPlacedFeature.OVERGROWTH_CAVES_FLOOR_VEGETATION,
            DnDConfiguredFeature.OVERGROWTH_PATCH_FLOOR,
            125,
            Direction.DOWN
        )
        c.cavePlacement(
            DnDPlacedFeature.OVERGROWTH_CAVES_CEILING_VEGETATION,
            DnDConfiguredFeature.OVERGROWTH_PATCH_CEILING,
            125,
            Direction.UP
        )
    }

    fun BootstrapContext<PlacedFeature>.pumpkin(
        place: ResourceKey<PlacedFeature>,
        conf: ResourceKey<ConfiguredFeature<*, *>>,
        rarity: Int = 50
    ) {
        this.register(
            place,
            this.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(conf),
            RarityFilter.onAverageOnceEvery(rarity),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        )
    }

    fun BootstrapContext<PlacedFeature>.cavePlacement(
        place: ResourceKey<PlacedFeature>,
        conf: ResourceKey<ConfiguredFeature<*, *>>,
        count: Int,
        direction: Direction
    ) {

        this.register(
            place,
            this.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(conf),
            CountPlacement.of(count),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                direction,
                BlockPredicate.solid(),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(if (direction == Direction.UP) -1 else 1)),
            BiomeFilter.biome()
        )
    }

    fun saplingFeatures(
        c: BootstrapContext<PlacedFeature>,
        configuredFeatureProvider: HolderGetter<ConfiguredFeature<*, *>>,
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
            DnDPlacedFeature.SYPIA_TALL,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.SYPIA_TALL),
            sypiaSapling
        )
        c.register(
            DnDPlacedFeature.SYPIA_TALL_BEES,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.SYPIA_TALL_BEES),
            sypiaSapling
        )
    }

    fun autumnBiomeFeatures(
        c: BootstrapContext<PlacedFeature>,
        configuredFeatureProvider: HolderGetter<ConfiguredFeature<*, *>>,
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
            DnDPlacedFeature.SYPIA_TALL_AUTUMN,
            configuredFeatureProvider.getOrThrow(DnDConfiguredFeature.SYPIA_TALL_AUTUMN),
            sypiaSapling
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
            RarityFilter.onAverageOnceEvery(21),
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
        firstModifier: PlacementModifier, secondModifier: PlacementModifier,
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
        vararg placementModifiers: PlacementModifier,
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers.toList()))

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: ResourceKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        placementModifiers: List<PlacementModifier>,
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers))
}