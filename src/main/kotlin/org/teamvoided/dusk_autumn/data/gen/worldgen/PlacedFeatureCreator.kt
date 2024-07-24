package org.teamvoided.dusk_autumn.data.gen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.Fluids
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.Holder
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.int_provider.ClampedIntProvider
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.decorator.*
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.ACACIA_AUTUMN
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.ACACIA_BUSH_AUTUMN
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.AUTUMN_FARMLANDS
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.AUTUMN_PASTURES_VEGETATION
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.AUTUMN_WETLANDS_VEGETATION
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.AUTUMN_WOODS_VEGETATION
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.BLUE_PETALS
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.CASCADE_TREE
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.CASCADE_TREE_BEES
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.CASCADE_TREE_WETLANDS
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.COBBLESTONE_ROCK
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.CROPS_WILD_WHEAT
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.DARK_OAK_AUTUMN
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.DARK_OAK_AUTUMN_WETLANDS
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.DISK_MUD
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.DISK_PODZOL
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.DISK_RED_SAND
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.FLOWER_AUTUMN
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.GOLDEN_BIRCH_TALL
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.GOLDEN_BIRCH_TALL_BEES
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.GOLDEN_BIRCH_TALL_WETLANDS
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.ORE_LAPIS_EXTRA
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.PATCH_GRASS_AUTUMN_PLAIN
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.PATCH_PUMPKIN_EXTRA
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.PATCH_ROSEBUSH
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature.PATCH_TALL_GRASS_AUTUMN_PLAIN
import org.teamvoided.dusk_autumn.init.DuskBlocks

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber", "LongMethod")
object PlacedFeatureCreator {

    fun bootstrap(c: BootstrapContext<PlacedFeature>) {
        val holderProvider = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)

        val cascadeSapling = PlacedFeatureUtil.createWouldSurvivePlacementModifier(DuskBlocks.CASCADE_SAPLING)
        val goldenBirchSapling = PlacedFeatureUtil.createWouldSurvivePlacementModifier(DuskBlocks.GOLDEN_BIRCH_SAPLING)
        val darkOakSapling = PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.DARK_OAK_SAPLING)
        val acaciaSapling = PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.ACACIA_SAPLING)

        c.register(
            COBBLESTONE_ROCK, holderProvider.getHolderOrThrow(DuskConfiguredFeature.COBBLESTONE_ROCK),
            RarityFilterPlacementModifier.create(5),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(1)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            ORE_LAPIS_EXTRA, holderProvider.getHolderOrThrow(OreConfiguredFeatures.ORE_LAPIS),
            commonOrePlacementModifiers(
                20,
                HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(80))
            )
        )
        c.register(
            CASCADE_TREE, holderProvider.getHolderOrThrow(DuskConfiguredFeature.CASCADE_TREE),
            cascadeSapling
        )
        c.register(
            CASCADE_TREE_BEES, holderProvider.getHolderOrThrow(DuskConfiguredFeature.CASCADE_TREE_BEES),
            cascadeSapling
        )
        c.register(
            CASCADE_TREE_WETLANDS, holderProvider.getHolderOrThrow(DuskConfiguredFeature.CASCADE_TREE_WETLANDS),
            cascadeSapling
        )
        c.register(
            GOLDEN_BIRCH_TALL, holderProvider.getHolderOrThrow(DuskConfiguredFeature.GOLDEN_BIRCH_TALL),
            goldenBirchSapling
        )
        c.register(
            GOLDEN_BIRCH_TALL_BEES, holderProvider.getHolderOrThrow(DuskConfiguredFeature.GOLDEN_BIRCH_TALL_BEES),
            goldenBirchSapling
        )
        c.register(
            GOLDEN_BIRCH_TALL_WETLANDS,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.GOLDEN_BIRCH_TALL_WETLANDS),
            goldenBirchSapling
        )
        c.register(
            DARK_OAK_AUTUMN, holderProvider.getHolderOrThrow(DuskConfiguredFeature.DARK_OAK_AUTUMN),
            darkOakSapling
        )
        c.register(
            DARK_OAK_AUTUMN_WETLANDS, holderProvider.getHolderOrThrow(DuskConfiguredFeature.DARK_OAK_AUTUMN_WETLANDS),
            darkOakSapling
        )
        c.register(
            ACACIA_AUTUMN, holderProvider.getHolderOrThrow(DuskConfiguredFeature.ACACIA_AUTUMN),
            acaciaSapling
        )
        c.register(
            ACACIA_BUSH_AUTUMN, holderProvider.getHolderOrThrow(DuskConfiguredFeature.ACACIA_BUSH_AUTUMN),
            acaciaSapling
        )
        c.register(
            PATCH_GRASS_AUTUMN_PLAIN, holderProvider.getHolderOrThrow(VegetationConfiguredFeatures.PATCH_GRASS),
            NoiseThresholdCountPlacementModifier.create(-0.8, 5, 10),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.matchingBlocks(BlockPos.ORIGIN.down(), *arrayOf(Blocks.GRASS_BLOCK))
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            PATCH_TALL_GRASS_AUTUMN_PLAIN,
            holderProvider.getHolderOrThrow(VegetationConfiguredFeatures.PATCH_TALL_GRASS),
            NoiseThresholdCountPlacementModifier.create(-0.8, 0, 7),
            RarityFilterPlacementModifier.create(32),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.matchingBlocks(BlockPos.ORIGIN.down(), *arrayOf(Blocks.GRASS_BLOCK))
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            PATCH_PUMPKIN_EXTRA, holderProvider.getHolderOrThrow(DuskConfiguredFeature.PATCH_PUMPKIN_EXTRA),
            RarityFilterPlacementModifier.create(50),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DISK_PODZOL, holderProvider.getHolderOrThrow(DuskConfiguredFeature.DISK_PODZOL),
            RarityFilterPlacementModifier.create(40),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)),
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.matchingBlocks(*arrayOf(Blocks.DIRT), (Blocks.GRASS_BLOCK), (Blocks.PODZOL))
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DISK_MUD, holderProvider.getHolderOrThrow(DuskConfiguredFeature.DISK_MUD),
            RarityFilterPlacementModifier.create(10),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)),
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.matchingBlocks(*arrayOf(Blocks.DIRT), (Blocks.GRASS_BLOCK), (Blocks.PODZOL))
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DISK_RED_SAND, holderProvider.getHolderOrThrow(DuskConfiguredFeature.DISK_RED_SAND),
            CountPlacementModifier.create(3),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.matchingFluids(*arrayOf<Fluid>(Fluids.WATER))
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            AUTUMN_WOODS_VEGETATION, holderProvider.getHolderOrThrow(DuskConfiguredFeature.AUTUMN_WOODS_VEGETATION),
            CountPlacementModifier.create(14),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            AUTUMN_PASTURES_VEGETATION,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.AUTUMN_PASTURES_VEGETATION),
            RarityFilterPlacementModifier.create(2),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            AUTUMN_WETLANDS_VEGETATION,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.AUTUMN_WETLANDS_VEGETATION),
            PlacedFeatureUtil.createCountExtraModifier(3, 0.25f, 1),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(3),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )

        c.register(
            FLOWER_AUTUMN, holderProvider.getHolderOrThrow(DuskConfiguredFeature.FLOWER_AUTUMN),
            NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4),
            RarityFilterPlacementModifier.create(14),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            BLUE_PETALS, holderProvider.getHolderOrThrow(DuskConfiguredFeature.BLUE_PETALS),
            NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4),
            RarityFilterPlacementModifier.create(18),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            PATCH_ROSEBUSH, holderProvider.getHolderOrThrow(DuskConfiguredFeature.PATCH_ROSEBUSH),
            RarityFilterPlacementModifier.create(7),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            CountPlacementModifier.create(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            AUTUMN_FARMLANDS, holderProvider.getHolderOrThrow(DuskConfiguredFeature.AUTUMN_FARMLAND),
            RarityFilterPlacementModifier.create(28),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
            CountPlacementModifier.create(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            CROPS_WILD_WHEAT, holderProvider.getHolderOrThrow(DuskConfiguredFeature.CROPS_WILD_WHEAT),
            RarityFilterPlacementModifier.create(9),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )

//        VegetationPlacedFeatures.class
//        MiscPlacedFeatures.class
    }

    fun orePlacementModifiers(
        firstModifier: PlacementModifier, secondModifier: PlacementModifier
    ): List<PlacementModifier> {
        return listOf(
            firstModifier, InSquarePlacementModifier.getInstance(),
            secondModifier, BiomePlacementModifier.getInstance()
        )
    }

    fun commonOrePlacementModifiers(count: Int, modifier: PlacementModifier): List<PlacementModifier> {
        return orePlacementModifiers(CountPlacementModifier.create(count), modifier)
    }

    private fun treePlacementModifiersBase(modifier: PlacementModifier): ImmutableList.Builder<PlacementModifier> {
        return ImmutableList.builder<PlacementModifier>().add(modifier).add(InSquarePlacementModifier.getInstance())
            .add(SurfaceWaterDepthFilterPlacementModifier.create(0)).add(PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP)
            .add(BiomePlacementModifier.getInstance())
    }


    fun BootstrapContext<PlacedFeature>.register(
        registryKey: RegistryKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        vararg placementModifiers: PlacementModifier
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers.toList()))

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: RegistryKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        placementModifiers: List<PlacementModifier>
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers))
}