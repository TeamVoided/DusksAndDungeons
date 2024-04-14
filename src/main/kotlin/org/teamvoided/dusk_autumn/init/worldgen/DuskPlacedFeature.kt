package org.teamvoided.dusk_autumn.init.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.Fluids
import net.minecraft.registry.Holder
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.int_provider.ClampedIntProvider
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.decorator.*
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import org.teamvoided.dusk_autumn.DuskAutumns
import org.teamvoided.dusk_autumn.init.DuskBlocks

@Suppress("HasPlatformType", "MemberVisibilityCanBePrivate")
object DuskPlacedFeature {
    val COBBLESTONE_ROCK = create("cobblestone_rock")
    val ORE_LAPIS_EXTRA = create("ore_lapis_extra")
    val CASCADE_TREE = create("cascade_tree")
    val CASCADE_TREE_BEES = create("cascade_tree_bees")
    val CASCADE_TREE_WETLANDS = create("cascade_tree_wetlands")
    val GOLDEN_BIRCH_TALL = create("golden_birch_tall")
    val GOLDEN_BIRCH_TALL_BEES = create("golden_birch_tall_bees")
    val GOLDEN_BIRCH_TALL_WETLANDS = create("golden_birch_tall_wetlands")
    val DARK_OAK_AUTUMN = create("dark_oak_autumn")
    val DARK_OAK_AUTUMN_WETLANDS = create("dark_oak_autumn_wetlands")
    val ACACIA_AUTUMN = create("acacia_autumn")
    val ACACIA_BUSH_AUTUMN = create("acacia_bush_autumn")
    val PATCH_PUMPKIN_EXTRA = create("patch_pumpkin_extra")
    val DISK_PODZOL = create("disk_podzol")
    val DISK_MUD = create("disk_mud")
    val DISK_RED_SAND = create("disk_red_sand")
    val AUTUMN_WOODS_VEGETATION = create("autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("autumn_pastures_vegetation")
    val AUTUMN_WETLANDS_VEGETATION = create("autumn_wetlands_vegetation")
    val FLOWER_AUTUMN = create("flower_autumn")
    val PATCH_ROSEBUSH = create("patch_rosebush")
    val BLUE_PETALS = create("blue_petals")
    val AUTUMN_FARMLANDS = create("autumn_farmlands")
    val CROPS_WILD_WHEAT = create("crops/wild_wheat")


    fun init() {}

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

    fun bootstrapPlacedFeatures(c: BootstrapContext<PlacedFeature>) {
        val holderProvider = c.lookup(RegistryKeys.CONFIGURED_FEATURE)

        val cascadeSapling = PlacedFeatureUtil.createWouldSurvivePlacementModifier(DuskBlocks.CASCADE_SAPLING)
        val goldenBirchSapling = PlacedFeatureUtil.createWouldSurvivePlacementModifier(DuskBlocks.GOLDEN_BIRCH_SAPLING)
        val darkOakSapling = PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.DARK_OAK_SAPLING)
        val acaciaSapling = PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.ACACIA_SAPLING)

        c.register(
            COBBLESTONE_ROCK, holderProvider.getHolderOrThrow(DuskConfiguredFeature.COBBLESTONE_ROCK),
            RarityFilterPlacementModifier.create(5),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
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
            PATCH_PUMPKIN_EXTRA, holderProvider.getHolderOrThrow(DuskConfiguredFeature.PATCH_PUMPKIN_EXTRA),
            RarityFilterPlacementModifier.create(50),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DISK_PODZOL, holderProvider.getHolderOrThrow(DuskConfiguredFeature.DISK_PODZOL),
            PlacedFeatureUtil.createCountExtraModifier(1, 0.025f, 0),
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
            RarityFilterPlacementModifier.create(3),
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
            NoiseBasedCountPlacementModifier.create(20, 80.0, 0.0),
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
            RarityFilterPlacementModifier.create(21),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            BLUE_PETALS, holderProvider.getHolderOrThrow(DuskConfiguredFeature.BLUE_PETALS),
            NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4),
            RarityFilterPlacementModifier.create(14),
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

    private fun treePlacementModifiersBase(modifier: PlacementModifier): ImmutableList.Builder<PlacementModifier> {
        return ImmutableList.builder<PlacementModifier>().add(modifier).add(InSquarePlacementModifier.getInstance())
            .add(SurfaceWaterDepthFilterPlacementModifier.create(0)).add(PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP)
            .add(BiomePlacementModifier.getInstance())
    }

//    PlacedFeatureUtil.register(
//    c,

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: RegistryKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        vararg placementModifiers: PlacementModifier
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers.toList()))

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: RegistryKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        placementModifiers: List<PlacementModifier>
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers))


    fun create(id: String) = RegistryKey.of(RegistryKeys.PLACED_FEATURE, DuskAutumns.id(id))
}