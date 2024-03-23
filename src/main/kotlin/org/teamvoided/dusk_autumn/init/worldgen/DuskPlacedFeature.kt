package org.teamvoided.dusk_autumn.init.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.registry.Holder
import net.minecraft.registry.HolderProvider
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

object DuskPlacedFeature {
    val ORE_LAPIS_EXTRA = create("ore_lapis_extra")

    //    val GOLDEN_BIRCH_TALL = create("golden_birch_tall")
//    val GOLDEN_BIRCH_TALL_BEES = create("golden_birch_tall_bees")
    val GOLDEN_BIRCH_TALL_CHECKED = create("golden_birch_tall_checked")

    //    val AUTUMN_TREE = create("autumn_tree")
//    val AUTUMN_TREE_BEES = create ("autumn_tree_bees")
    val AUTUMN_TREE_CHECKED = create("autumn_tree_checked")
    val DARK_OAK_AUTUMN = create("dark_oak_autumn")
    val COBBLESTONE_ROCK_FOREST = create("cobblestone_rock_forest")
    val COBBLESTONE_ROCK_PLAINS = create("cobblestone_rock_plains")
    val DISK_PODZOL = create("disk_podzol")
    val AUTUMN_WOODS_VEGETATION = create("autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("autumn_pastures_vegetation")
//    val AUTUMN_WOODS_FLOWER = create("autumn_woods_flower")
    val PATCH_ROSEBUSH = create("patch_rosebush")
    val BLUE_PETALS = create("blue_petals")

    fun create(id: String) = RegistryKey.of(RegistryKeys.PLACED_FEATURE, DuskAutumns.id(id))

    fun init() {}

    fun bootstrapPlacedFeatures(c: BootstrapContext<PlacedFeature>) {
        val holderProvider: HolderProvider<ConfiguredFeature<*, *>> = c.lookup(RegistryKeys.CONFIGURED_FEATURE)
        fun orePlacementModifiers(
            firstModifier: PlacementModifier,
            secondModifier: PlacementModifier
        ): List<PlacementModifier> {
            return listOf(
                firstModifier,
                InSquarePlacementModifier.getInstance(),
                secondModifier,
                BiomePlacementModifier.getInstance()
            )
        }

        fun commonOrePlacementModifiers(count: Int, modifier: PlacementModifier): List<PlacementModifier> {
            return orePlacementModifiers(CountPlacementModifier.create(count), modifier)
        }

        c.register(
            ORE_LAPIS_EXTRA,
            holderProvider.getHolderOrThrow(OreConfiguredFeatures.ORE_LAPIS),
            commonOrePlacementModifiers(
                20,
                HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(80))
            )
        )
        c.register(
            GOLDEN_BIRCH_TALL_CHECKED,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.GOLDEN_BIRCH_TALL_BEES),
            *arrayOf<PlacementModifier>(PlacedFeatureUtil.createWouldSurvivePlacementModifier(DuskBlocks.GOLDEN_BIRCH_SAPLING))
        )
        c.register(
            AUTUMN_TREE_CHECKED,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.AUTUMN_TREE),
            *arrayOf<PlacementModifier>(PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.DARK_OAK_SAPLING))
        )
        c.register(
            DARK_OAK_AUTUMN,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.DARK_OAK_AUTUMN),
            *arrayOf<PlacementModifier>(PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.DARK_OAK_SAPLING))
        )
        c.register(
            COBBLESTONE_ROCK_FOREST,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.COBBLESTONE_ROCK),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(2),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.getInstance()
            )
        )
        c.register(
            COBBLESTONE_ROCK_PLAINS,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.COBBLESTONE_ROCK),
            *arrayOf<PlacementModifier>(
                PlacedFeatureUtil.createCountExtraModifier(0, 0.2f, 1),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.getInstance()
            )
        )
        c.register(
            DISK_PODZOL,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.DISK_PODZOL),
            *arrayOf<PlacementModifier>(
                PlacedFeatureUtil.createCountExtraModifier(0, 0.05f, 1),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
                RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)),
                BlockPredicateFilterPlacementModifier.create(
                    BlockPredicate.matchingBlocks(*arrayOf<Block>(Blocks.DIRT), (Blocks.GRASS_BLOCK))
                )
            ),
            BiomePlacementModifier.getInstance()
        )


        c.register(
            AUTUMN_WOODS_VEGETATION,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.AUTUMN_WOODS_VEGETATION),
            CountPlacementModifier.create(14),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            AUTUMN_PASTURES_VEGETATION,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.AUTUMN_PASTURES_VEGETATION),
            PlacedFeatureUtil.createCountExtraModifier(0, 0.1f, 1),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(0),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            BLUE_PETALS,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.BLUE_PETALS),
            *arrayOf<PlacementModifier>(
                NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4),
                RarityFilterPlacementModifier.create(28),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.getInstance()
            )
        )
        c.register(
            PATCH_ROSEBUSH,
            holderProvider.getHolderOrThrow(DuskConfiguredFeature.PATCH_ROSEBUSH),
            RarityFilterPlacementModifier.create(7),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            CountPlacementModifier.create(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)),
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
}