package org.teamvoided.dusk_autumn.init.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.registry.Holder
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
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
    val GOLDEN_BIRCH_TALL = create("golden_birch_tall")
    val GOLDEN_BIRCH_TALL_BEES = create("golden_birch_tall_bees")
    val DARK_OAK_AUTUMN = create("dark_oak_autumn")
    val ACACIA_AUTUMN = create("acacia_autumn")
    val ACACIA_BUSH_AUTUMN = create("acacia_bush_autumn")
    val PATCH_PUMPKIN_EXTRA = create("patch_pumpkin_extra")
    val DISK_PODZOL = create("disk_podzol")
    val AUTUMN_WOODS_VEGETATION = create("autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("autumn_pastures_vegetation")
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

        c.register(
            COBBLESTONE_ROCK, holderProvider.getHolderOrThrow(DuskConfiguredFeature.COBBLESTONE_ROCK),
            RarityFilterPlacementModifier.create(3),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
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
            PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.DARK_OAK_SAPLING)
        )
        c.register(
            CASCADE_TREE_BEES, holderProvider.getHolderOrThrow(DuskConfiguredFeature.CASCADE_TREE_BEES),
            PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.DARK_OAK_SAPLING)
        )
        c.register(
            GOLDEN_BIRCH_TALL, holderProvider.getHolderOrThrow(DuskConfiguredFeature.GOLDEN_BIRCH_TALL),
            PlacedFeatureUtil.createWouldSurvivePlacementModifier(DuskBlocks.GOLDEN_BIRCH_SAPLING)
        )
        c.register(
            GOLDEN_BIRCH_TALL_BEES, holderProvider.getHolderOrThrow(DuskConfiguredFeature.GOLDEN_BIRCH_TALL_BEES),
            PlacedFeatureUtil.createWouldSurvivePlacementModifier(DuskBlocks.GOLDEN_BIRCH_SAPLING)
        )
        c.register(
            DARK_OAK_AUTUMN, holderProvider.getHolderOrThrow(DuskConfiguredFeature.DARK_OAK_AUTUMN),
            PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.DARK_OAK_SAPLING)
        )
        c.register(
            ACACIA_AUTUMN, holderProvider.getHolderOrThrow(DuskConfiguredFeature.ACACIA_AUTUMN),
            PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.ACACIA_SAPLING)
        )
        c.register(
            ACACIA_BUSH_AUTUMN, holderProvider.getHolderOrThrow(DuskConfiguredFeature.ACACIA_BUSH_AUTUMN),
            PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.ACACIA_SAPLING)
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
            PlacedFeatureUtil.createCountExtraModifier(0, 0.05f, 1),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)),
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.matchingBlocks(*arrayOf(Blocks.DIRT), (Blocks.GRASS_BLOCK))
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            AUTUMN_WOODS_VEGETATION, holderProvider.getHolderOrThrow(DuskConfiguredFeature.AUTUMN_WOODS_VEGETATION),
            CountPlacementModifier.create(14),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            AUTUMN_PASTURES_VEGETATION,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.AUTUMN_PASTURES_VEGETATION),
            PlacedFeatureUtil.createCountExtraModifier(0, 0.2f, 1),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            FLOWER_AUTUMN, holderProvider.getHolderOrThrow(DuskConfiguredFeature.FLOWER_AUTUMN),
            *arrayOf(
                NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4),
                RarityFilterPlacementModifier.create(21),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.getInstance()
            )
        )
        c.register(
            BLUE_PETALS, holderProvider.getHolderOrThrow(DuskConfiguredFeature.BLUE_PETALS),
            *arrayOf(
                NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4),
                RarityFilterPlacementModifier.create(14),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.getInstance()
            )
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
            RarityFilterPlacementModifier.create(7),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
            CountPlacementModifier.create(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            CROPS_WILD_WHEAT, holderProvider.getHolderOrThrow(DuskConfiguredFeature.CROPS_WILD_WHEAT),
            RarityFilterPlacementModifier.create(7),
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