package org.teamvoided.dusk_autumn.data.gen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.*
import net.minecraft.fluid.Fluids
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.rule.TagMatchRuleTest
import net.minecraft.unmapped.C_cxbmzbuz
import net.minecraft.unmapped.C_cxbmzbuz.C_pkkqenbk
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.Direction
import net.minecraft.util.math.int_provider.BiasedToBottomIntProvider
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.data.tags.DuskBlockTags
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.ACACIA_AUTUMN
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.ACACIA_BUSH_AUTUMN
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.AUTUMN_FARMLAND
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.AUTUMN_FARMLAND_CROPS
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.AUTUMN_PASTURES_VEGETATION
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.AUTUMN_WETLANDS_VEGETATION
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.AUTUMN_WOODS_VEGETATION
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.BLUE_PETALS
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CASCADE_TREE
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CASCADE_TREE_BEES
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CASCADE_TREE_WETLANDS
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.COBBLESTONE_ROCK
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CROPS_BEETROOTS
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CROPS_CARROTS
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CROPS_GOLDEN_BEETROOTS
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CROPS_POTATOES
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CROPS_PUMPKIN
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CROPS_WHEAT
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.CROPS_WILD_WHEAT
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.DARK_OAK_AUTUMN
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.DARK_OAK_AUTUMN_WETLANDS
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.DISK_MUD
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.DISK_PODZOL
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.DISK_RED_SAND
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.FLOWER_AUTUMN
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.GOLDEN_BIRCH_TALL
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.GOLDEN_BIRCH_TALL_BEES
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.GOLDEN_BIRCH_TALL_WETLANDS
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.PATCH_PUMPKIN_EXTRA
import org.teamvoided.dusk_autumn.data.worldgen.DuskConfiguredFeature.PATCH_ROSEBUSH
import org.teamvoided.dusk_autumn.data.worldgen.DuskPlacedFeature
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.init.worldgen.DuskFeatures
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FarmlandConfig
import org.teamvoided.dusk_autumn.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterOnGroundTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AttachedToTrunkTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.BeehiveBigTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.trunk.ThreeWideTrunkPlacer
import java.util.*

object ConfiguredFeatureCreator {

    fun bootstrap(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = c.getRegistryLookup(RegistryKeys.BLOCK)
        val configuredFeatures = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures = c.getRegistryLookup(RegistryKeys.PLACED_FEATURE)

        val petalFlowerBuilder = DataPool.builder<BlockState>()
        (1..4).forEach { count ->
            Direction.Type.HORIZONTAL.forEach { direction ->
                petalFlowerBuilder.addWeighted(
                    DuskBlocks.BLUE_PETALS.defaultState
                        .with(PinkPetalsBlock.AMOUNT, count).with(PinkPetalsBlock.FACING, direction),
                    1
                )
            }
        }
        val cascadeTree = TreeFeatureConfig.Builder(
            BlockStateProvider.of(DuskBlocks.CASCADE_LOG),
            ThreeWideTrunkPlacer(9, 2, 1),
            BlockStateProvider.of(DuskBlocks.CASCADE_LEAVES),
            CascadeFoliagePlacer(
                ConstantIntProvider.create(3),
                ConstantIntProvider.create(0),
                ConstantIntProvider.create(2),
                100
            ),
//            Optional.of(
//                CascadeRootPlacer(
//                    UniformIntProvider.create(3, 7),
//                    BlockStateProvider.of(DuskBlocks.CASCADE_LOG),
//                    Optional.empty(),
//                    CascadeRootConfig(
//                        blockTags.getTagOrThrow(BlockTags.REPLACEABLE_BY_TREES),
//                        HolderSet.createDirect(
//                            { it.builtInRegistryHolder },
//                            *arrayOf(Blocks.DIRT)
//                        ),
//                        BlockStateProvider.of(Blocks.ROOTED_DIRT),
//                        8,
//                        15,
//                        0.2f
//                    )
//                )
//            ),
            ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
        )
        val goldenBirchTree = builder(Blocks.BIRCH_LOG, DuskBlocks.GOLDEN_BIRCH_LEAVES, 5, 2, 6, 2)
        val birchDecorator1 = AttachedToTrunkTreeDecorator(
            0.14f,
            1,
            1,
            BlockStateProvider.of(
                Blocks.BIRCH_LOG.defaultState.with(
                    PillarBlock.AXIS,
                    Direction.Axis.X
                )
            ),
            2,
            listOf(Direction.EAST, Direction.WEST)
        )
        val birchDecorator2 = AttachedToTrunkTreeDecorator(
            0.14f,
            1,
            1,
            BlockStateProvider.of(
                Blocks.BIRCH_LOG.defaultState.with(
                    PillarBlock.AXIS,
                    Direction.Axis.Z
                )
            ),
            2,
            listOf(Direction.NORTH, Direction.SOUTH)
        )

//        ConfiguredFeatureUtil.registerConfiguredFeature(
//            c, COBBLESTONE_ROCK, Feature.FOREST_ROCK, SingleStateFeatureConfig(Blocks.COBBLESTONE.defaultState)
//        )
        ConfiguredFeatureUtil.method_39708<OreFeatureConfig, Feature<OreFeatureConfig>>(
            c,
            COBBLESTONE_ROCK,
            Feature.ORE,
            OreFeatureConfig(TagMatchRuleTest(DuskBlockTags.REPLACEABLE_OR_DIRT), Blocks.COBBLESTONE.defaultState, 33)
        )

        ConfiguredFeatureUtil.method_39708(
            c, CASCADE_TREE, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 5,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    leafPiles(DuskBlocks.CASCADE_LEAF_PILE, blockTags)
                )
            ).build()
        )
        ConfiguredFeatureUtil.method_39708(
            c, CASCADE_TREE_BEES, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.02F),
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL),
                        2, 5,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    leafPiles(DuskBlocks.CASCADE_LEAF_PILE, blockTags)
                )
            ).build()
        )
        ConfiguredFeatureUtil.method_39708(
            c, CASCADE_TREE_WETLANDS, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(
                    LeavesVineTreeDecorator(0.25F),
                    BeehiveBigTreeDecorator(0.02F),
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 5,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    leafPiles(DuskBlocks.CASCADE_LEAF_PILE, blockTags)
                )
            ).build()
        )
        ConfiguredFeatureUtil.method_39708(
            c, GOLDEN_BIRCH_TALL, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 20,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        ConfiguredFeatureUtil.method_39708(
            c, GOLDEN_BIRCH_TALL_BEES, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.02F),
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 20,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        ConfiguredFeatureUtil.method_39708(
            c,
            GOLDEN_BIRCH_TALL_WETLANDS,
            Feature.TREE,
            builder(Blocks.BIRCH_LOG, DuskBlocks.GOLDEN_BIRCH_LEAVES, 5, 2, 6, 3).ignoreVines().decorators(
                ImmutableList.of(
                    LeavesVineTreeDecorator(0.25F),
                    BeehiveBigTreeDecorator(0.02F),
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 20,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        ConfiguredFeatureUtil.method_39708(
            c, DARK_OAK_AUTUMN, Feature.TREE, TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.DARK_OAK_LOG),
                DarkOakTrunkPlacer(6, 3, 1),
                BlockStateProvider.of(Blocks.DARK_OAK_LEAVES),
                DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
            )
                .forceDirt().ignoreVines().decorators(
                    ImmutableList.of(
                        BeehiveBigTreeDecorator(0.02F),
                        AlterGroundRadiusTreeDecorator(
                            BlockStateProvider.of(Blocks.PODZOL), 2, 5,
                            blockTags.getTagOrThrow(BlockTags.DIRT)
                        ),
                        leafPiles(DuskBlocks.DARK_OAK_LEAF_PILE, blockTags)
                    )
                ).build()
        )
        ConfiguredFeatureUtil.method_39708(
            c, DARK_OAK_AUTUMN_WETLANDS, Feature.TREE, TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.DARK_OAK_LOG),
                DarkOakTrunkPlacer(6, 3, 1),
                BlockStateProvider.of(Blocks.DARK_OAK_LEAVES),
                DarkOakFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
                ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
            )
                .forceDirt().ignoreVines().decorators(
                    ImmutableList.of(
                        LeavesVineTreeDecorator(0.25F),
                        BeehiveBigTreeDecorator(0.02F),
                        AlterGroundRadiusTreeDecorator(
                            BlockStateProvider.of(Blocks.PODZOL), 2, 5,
                            blockTags.getTagOrThrow(BlockTags.DIRT)
                        ),
                        leafPiles(DuskBlocks.DARK_OAK_LEAF_PILE, blockTags)
                    )
                ).build()
        )
        ConfiguredFeatureUtil.method_39708(
            c, ACACIA_AUTUMN, Feature.TREE, TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG),
                StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.of(Blocks.ACACIA_LEAVES),
                BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                TwoLayersFeatureSize(1, 0, 1)
            )
                .forceDirt().ignoreVines().decorators(
                    ImmutableList.of<TreeDecorator>(leafPiles(DuskBlocks.ACACIA_LEAF_PILE, blockTags))
                ).build()
        )
        ConfiguredFeatureUtil.method_39708<TreeFeatureConfig, Feature<TreeFeatureConfig>>(
            c, ACACIA_BUSH_AUTUMN, Feature.TREE,
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG),
                StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.ACACIA_LEAVES),
                AcaciaFoliagePlacer(UniformIntProvider.create(1, 2), UniformIntProvider.create(0, 1)),
                TwoLayersFeatureSize(0, 0, 0)
            ).build()
        )
        ConfiguredFeatureUtil.method_39708<DiskFeatureConfig, Feature<DiskFeatureConfig>>(
            c, DISK_PODZOL, Feature.DISK, DiskFeatureConfig(
                C_cxbmzbuz(
                    BlockStateProvider.of(Blocks.DIRT), listOf(
                        C_pkkqenbk(
                            BlockPredicate.not(
                                BlockPredicate.eitherOf(
                                    BlockPredicate.solid(Direction.UP.vector),
                                    BlockPredicate.matchingFluids(Direction.UP.vector, *arrayOf(Fluids.WATER))
                                )
                            ), BlockStateProvider.of(Blocks.PODZOL)
                        )
                    )
                ),
                BlockPredicate.matchingBlocks(listOf(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.STONE)),
                UniformIntProvider.create(2, 6), 2
            )
        )
        ConfiguredFeatureUtil.method_39708<DiskFeatureConfig, Feature<DiskFeatureConfig>>(
            c, DISK_MUD, Feature.DISK, DiskFeatureConfig(
                C_cxbmzbuz.method_43312(Blocks.MUD), BlockPredicate.matchingBlocks(
                    listOf(
                        Blocks.DIRT,
                        Blocks.GRASS_BLOCK,
                        Blocks.MYCELIUM,
                        Blocks.PODZOL,
                        Blocks.GRAVEL,
                        Blocks.SAND,
                        Blocks.MUD
                    )
                ), UniformIntProvider.create(2, 6), 1
            )
        )

        ConfiguredFeatureUtil.method_39708<DiskFeatureConfig, Feature<DiskFeatureConfig>>(
            c, DISK_RED_SAND, Feature.DISK, DiskFeatureConfig(
                C_cxbmzbuz(
                    BlockStateProvider.of(Blocks.RED_SAND), listOf(
                        C_pkkqenbk(
                            BlockPredicate.matchingBlocks(Direction.DOWN.vector, *arrayOf<Block>(Blocks.AIR)),
                            BlockStateProvider.of(Blocks.RED_SANDSTONE)
                        )
                    )
                ), BlockPredicate.matchingBlocks(
                    listOf(
                        Blocks.DIRT,
                        Blocks.GRASS_BLOCK,
                        Blocks.MYCELIUM,
                        Blocks.PODZOL,
                        Blocks.GRAVEL,
                        Blocks.SAND,
                        Blocks.MUD
                    )
                ), UniformIntProvider.create(2, 6), 2
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, PATCH_PUMPKIN_EXTRA, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    WeightedBlockStateProvider(
                        DataPool.builder<BlockState>()
                            .addWeighted(Blocks.PUMPKIN.defaultState, 64)
                            .addWeighted(Blocks.CARVED_PUMPKIN.defaultState, 8)
                            .addWeighted(
                                Blocks.CARVED_PUMPKIN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.SOUTH), 8
                            )
                            .addWeighted(
                                Blocks.CARVED_PUMPKIN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.EAST), 8
                            )
                            .addWeighted(
                                Blocks.CARVED_PUMPKIN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.WEST), 8
                            )
                            .addWeighted(Blocks.JACK_O_LANTERN.defaultState, 1)
                            .addWeighted(
                                Blocks.JACK_O_LANTERN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.SOUTH), 1
                            )
                            .addWeighted(
                                Blocks.JACK_O_LANTERN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.EAST), 1
                            )
                            .addWeighted(
                                Blocks.JACK_O_LANTERN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.WEST), 1
                            )
                    )
                ),
                listOf(
                    Blocks.GRASS_BLOCK,
                    Blocks.FARMLAND,
                    Blocks.PODZOL,
                    Blocks.COBBLESTONE,
                    Blocks.PUMPKIN,
                    Blocks.CARVED_PUMPKIN,
                    Blocks.JACK_O_LANTERN
                )
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, AUTUMN_WOODS_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM),
                            *arrayOfNulls(0)
                        ), 0.0025f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.HUGE_RED_MUSHROOM),
                            *arrayOfNulls(0)
                        ), 0.005f
                    ),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.DARK_OAK_AUTUMN), 0.425f),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.CASCADE_TREE_BEES), 0.425f)
                ), placedFeatures.getHolderOrThrow(DuskPlacedFeature.GOLDEN_BIRCH_TALL_BEES)
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, AUTUMN_PASTURES_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.ACACIA_BUSH_AUTUMN), 0.3f),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(DuskPlacedFeature.GOLDEN_BIRCH_TALL_BEES),
                        0.2f
                    ),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.CASCADE_TREE_BEES), 0.2f)
                ), placedFeatures.getHolderOrThrow(DuskPlacedFeature.ACACIA_AUTUMN)
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, AUTUMN_WETLANDS_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM),
                            *arrayOfNulls(0)
                        ), 0.0025f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.HUGE_RED_MUSHROOM),
                            *arrayOfNulls(0)
                        ), 0.005f
                    ),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(DuskPlacedFeature.DARK_OAK_AUTUMN_WETLANDS),
                        0.425f
                    ),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(DuskPlacedFeature.CASCADE_TREE_WETLANDS),
                        0.425f
                    )
                ), placedFeatures.getHolderOrThrow(DuskPlacedFeature.GOLDEN_BIRCH_TALL_WETLANDS)
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, FLOWER_AUTUMN, Feature.FLOWER, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                64,
                PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                        WeightedBlockStateProvider(
                            DataPool.builder<BlockState>()
                                .addWeighted(Blocks.CORNFLOWER.defaultState, 5)
                                .addWeighted(Blocks.POPPY.defaultState, 5)
                                .addWeighted(DuskBlocks.CASCADE_SAPLING.defaultState, 1)
                        )
                    )
                )
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, PATCH_ROSEBUSH, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.ROSE_BUSH))
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, BLUE_PETALS, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2,
                PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(WeightedBlockStateProvider(petalFlowerBuilder))
                )
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, AUTUMN_FARMLAND, DuskFeatures.FARMLAND, FarmlandConfig(
                BlockTags.DIRT,
                DuskBlockTags.FARMLAND_PLACES_UNDER,
                BlockStateProvider.of(Blocks.FARMLAND.defaultState.with(FarmlandBlock.MOISTURE, 7)),
                0.85f,
                BiasedToBottomIntProvider.create(2, 16),
                4,
                BlockStateProvider.of(Blocks.DARK_OAK_FENCE),
                0.9f,
                BiasedToBottomIntProvider.create(1, 24),
                BlockStateProvider.of(Blocks.WATER),
                0.9f,
                PlacedFeatureUtil.placedInline(
                    configuredFeatures.getHolderOrThrow(AUTUMN_FARMLAND_CROPS),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                0.05f,
                true,
                listOf()
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, AUTUMN_FARMLAND_CROPS, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(CROPS_WHEAT),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.25f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(CROPS_CARROTS),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(CROPS_POTATOES),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(CROPS_PUMPKIN),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(CROPS_BEETROOTS),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(CROPS_GOLDEN_BEETROOTS),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.05f
                    )
                ), PlacedFeatureUtil.placedInline(
                    configuredFeatures.getHolderOrThrow(CROPS_WILD_WHEAT),
                    *arrayOfNulls<PlacementModifier>(0)
                )
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, CROPS_WILD_WHEAT, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(
                    BlockStateProvider.of(
                        DuskBlocks.WILD_WHEAT.defaultState
                    )
                ), ImmutableList.of(Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.FARMLAND), 32
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, CROPS_WHEAT, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.WHEAT)
                ),
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, CROPS_CARROTS, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.CARROTS)
                )
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, CROPS_POTATOES, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.POTATOES)
                )
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, CROPS_PUMPKIN, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.PUMPKIN_STEM)
                )
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, CROPS_BEETROOTS, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    WeightedBlockStateProvider(
                        DataPool.builder<BlockState>()
                            .addWeighted(Blocks.BEETROOTS.defaultState, 3)
                            .addWeighted(Blocks.BEETROOTS.defaultState.with(BeetrootsBlock.AGE, 2), 2)
                            .addWeighted(Blocks.BEETROOTS.defaultState.with(BeetrootsBlock.AGE, 3), 1)
                    )
                )
            )
        )
        ConfiguredFeatureUtil.method_39708(
            c, CROPS_GOLDEN_BEETROOTS, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    WeightedBlockStateProvider(
                        DataPool.builder<BlockState>()
                            .addWeighted(DuskBlocks.GOLDEN_BEETROOTS.defaultState, 1)
                            .addWeighted(DuskBlocks.GOLDEN_BEETROOTS.defaultState.with(BeetrootsBlock.AGE, 2), 1)
                            .addWeighted(Blocks.BEETROOTS.defaultState.with(BeetrootsBlock.AGE, 3), 1)
                    )
                )
            )
        )
    }

    fun builder(
        trunk: Block, foliage: Block, baseHeight: Int, firstRandomHeight: Int,
        secondRandomHeight: Int, foliageRadius: Int
    ): TreeFeatureConfig.Builder {
        return TreeFeatureConfig.Builder(
            BlockStateProvider.of(trunk),
            StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight),
            BlockStateProvider.of(foliage),
            BlobFoliagePlacer(ConstantIntProvider.create(foliageRadius), ConstantIntProvider.create(0), 3),
            TwoLayersFeatureSize(1, 0, 1)
        )
    }

    //    val petalFlowerBuilder = DataPool.builder<BlockState>()
//    (1..4).forEach { count ->
//        Direction.Type.HORIZONTAL.forEach { direction ->
//            petalFlowerBuilder.addWeighted(
//                DuskBlocks.BLUE_PETALS.defaultState
//                    .with(PinkPetalsBlock.AMOUNT, count).with(PinkPetalsBlock.FACING, direction),
//                1
//            )
//        }
//    }
    fun basicCropAges(crop: Block): WeightedBlockStateProvider {
        val crops = DataPool.builder<BlockState>()
        (1..7).forEach { age ->
            crops.addWeighted(crop.defaultState.with(CropBlock.AGE, age), 7 - age + 1)
        }
        return WeightedBlockStateProvider(
            crops
        )
    }

    fun leafPiles(leafPile: Block, blockTags: HolderProvider<Block>): AlterOnGroundTreeDecorator {
        return AlterOnGroundTreeDecorator(
            WeightedBlockStateProvider(
                DataPool.builder<BlockState>()
                    .addWeighted(leafPile.defaultState, 9)
                    .addWeighted(leafPile.defaultState.with(LeafPileBlock.PILE_LAYERS, 2), 4)
                    .addWeighted(leafPile.defaultState.with(LeafPileBlock.PILE_LAYERS, 3), 1)
            ),
            3, 10, 20,
            blockTags.getTagOrThrow(DuskBlockTags.LEAF_PILES_PLACE_ON)
        )
    }
}