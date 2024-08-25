package org.teamvoided.dusk_autumn.data.gen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.Fluids
import net.minecraft.registry.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.int_provider.ClampedIntProvider
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.decorator.*
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import org.teamvoided.dusk_autumn.data.worldgen.DnDConfiguredFeature
import org.teamvoided.dusk_autumn.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusk_autumn.init.DnDBlocks

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber", "LongMethod")
object PlacedFeatureCreator {
    val cascadeSapling: BlockPredicateFilterPlacementModifier =
        PlacedFeatureUtil.createWouldSurvivePlacementModifier(DnDBlocks.CASCADE_SAPLING)
    val goldenBirchSapling: BlockPredicateFilterPlacementModifier =
        PlacedFeatureUtil.createWouldSurvivePlacementModifier(DnDBlocks.GOLDEN_BIRCH_SAPLING)
    val darkOakSapling: BlockPredicateFilterPlacementModifier =
        PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.DARK_OAK_SAPLING)
    val acaciaSapling: BlockPredicateFilterPlacementModifier =
        PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.ACACIA_SAPLING)

    fun bootstrap(c: BootstrapContext<PlacedFeature>) {
        val configuredFeatureProvider = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        c.register(
            DnDPlacedFeature.OVERGROWN_COBBLESTONE_BOULDER,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.OVERGROWN_COBBLESTONE_BOULDER),
            RarityFilterPlacementModifier.create(7),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DnDPlacedFeature.PATCH_PUMPKIN_EXTRA,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.PATCH_PUMPKIN_EXTRA),
            RarityFilterPlacementModifier.create(50),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DnDPlacedFeature.DISK_PODZOL, configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.DISK_PODZOL),
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
            DnDPlacedFeature.DISK_MUD, configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.DISK_MUD),
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
            DnDPlacedFeature.DISK_RED_SAND,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.DISK_RED_SAND),
            CountPlacementModifier.create(3),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.matchingFluids(*arrayOf<Fluid>(Fluids.WATER))
            ),
            BiomePlacementModifier.getInstance()
        )
        saplingFeatures(c, configuredFeatureProvider)
        autumnBiomeFeatures(c, configuredFeatureProvider)
        rockyOres(c, configuredFeatureProvider)
    }

    fun saplingFeatures(
        c: BootstrapContext<PlacedFeature>,
        configuredFeatureProvider: HolderProvider<ConfiguredFeature<*, *>>
    ) {
        c.register(
            DnDPlacedFeature.CASCADE_TREE,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.CASCADE_TREE),
            cascadeSapling
        )
        c.register(
            DnDPlacedFeature.CASCADE_TREE_BEES,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.CASCADE_TREE_BEES),
            cascadeSapling
        )
        c.register(
            DnDPlacedFeature.GOLDEN_BIRCH_TALL,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.GOLDEN_BIRCH_TALL),
            goldenBirchSapling
        )
        c.register(
            DnDPlacedFeature.GOLDEN_BIRCH_TALL_BEES,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.GOLDEN_BIRCH_TALL_BEES),
            goldenBirchSapling
        )
    }

    fun autumnBiomeFeatures(
        c: BootstrapContext<PlacedFeature>,
        configuredFeatureProvider: HolderProvider<ConfiguredFeature<*, *>>
    ) {
        c.register(
            DnDPlacedFeature.ORE_LAPIS_EXTRA,
            configuredFeatureProvider.getHolderOrThrow(OreConfiguredFeatures.ORE_LAPIS),
            commonOrePlacementModifiers(
                20,
                HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(80))
            )
        )
        c.register(
            DnDPlacedFeature.CASCADE_TREE_AUTUMN,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.CASCADE_TREE_AUTUMN),
            cascadeSapling
        )
        c.register(
            DnDPlacedFeature.GOLDEN_BIRCH_TALL_AUTUMN,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.GOLDEN_BIRCH_TALL_AUTUMN),
            goldenBirchSapling
        )
        c.register(
            DnDPlacedFeature.DARK_OAK_AUTUMN,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.DARK_OAK_AUTUMN),
            darkOakSapling
        )
        c.register(
            DnDPlacedFeature.ACACIA_AUTUMN,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.ACACIA_AUTUMN),
            acaciaSapling
        )
        c.register(
            DnDPlacedFeature.ACACIA_BUSH_AUTUMN,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.ACACIA_BUSH_AUTUMN),
            acaciaSapling
        )
        c.register(
            DnDPlacedFeature.PATCH_GRASS_AUTUMN_PLAIN,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.PATCH_GRASS),
            NoiseThresholdCountPlacementModifier.create(-0.8, 5, 10),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.matchingBlocks(
                    BlockPos.ORIGIN.down(),
                    *arrayOf(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT, Blocks.COARSE_DIRT)
                )
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DnDPlacedFeature.PATCH_TALL_GRASS_AUTUMN_PLAIN,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.PATCH_TALL_GRASS),
            NoiseThresholdCountPlacementModifier.create(-0.8, 0, 7),
            RarityFilterPlacementModifier.create(32),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.matchingBlocks(
                    BlockPos.ORIGIN.down(),
                    *arrayOf(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT, Blocks.COARSE_DIRT)
                )
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DnDPlacedFeature.AUTUMN_WOODS_VEGETATION,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.AUTUMN_WOODS_VEGETATION),
            CountPlacementModifier.create(14),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DnDPlacedFeature.AUTUMN_PASTURES_VEGETATION,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.AUTUMN_PASTURES_VEGETATION),
            RarityFilterPlacementModifier.create(2),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
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
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.FLOWER_AUTUMN),
            NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4),
            RarityFilterPlacementModifier.create(14),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DnDPlacedFeature.BLUE_PETALS, configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.BLUE_PETALS),
            NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4),
            RarityFilterPlacementModifier.create(18),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DnDPlacedFeature.PATCH_ROSEBUSH,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.PATCH_ROSEBUSH),
            RarityFilterPlacementModifier.create(7),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            CountPlacementModifier.create(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DnDPlacedFeature.AUTUMN_FARMLANDS,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.AUTUMN_FARMLAND),
            RarityFilterPlacementModifier.create(28),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
            CountPlacementModifier.create(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DnDPlacedFeature.CROPS_WILD_WHEAT,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.CROPS_WILD_WHEAT),
            RarityFilterPlacementModifier.create(9),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
    }

    fun rockyOres(
        c: BootstrapContext<PlacedFeature>,
        configuredFeatureProvider: HolderProvider<ConfiguredFeature<*, *>>
    ) {
        c.register(
            DnDPlacedFeature.ROCKY_ORE_UPPER,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.ROCKY_OVERWORLD_ORE),
            OrePlacedFeatures.rareOrePlacementModifiers(
                16,
                HeightRangePlacementModifier.createUniform(YOffset.fixed(64), YOffset.fixed(128))
            )
        )
        c.register(
            DnDPlacedFeature.ROCKY_ORE_LOWER,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.ROCKY_OVERWORLD_ORE),
            OrePlacedFeatures.rareOrePlacementModifiers(
                8,
                HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(60))
            )
        )
        c.register(
            DnDPlacedFeature.SLATED_ORE,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.SLATED_OVERWORLD_ORE),
            OrePlacedFeatures.rareOrePlacementModifiers(
                8,
                HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(0))
            )
        )
        c.register(
            DnDPlacedFeature.BLACKSTONED_ORE,
            configuredFeatureProvider.getHolderOrThrow(DnDConfiguredFeature.BLACKSTONE_NETHER_ORE),
            OrePlacedFeatures.rareOrePlacementModifiers(
                16,
                HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.belowTop(128))
            )
        )
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