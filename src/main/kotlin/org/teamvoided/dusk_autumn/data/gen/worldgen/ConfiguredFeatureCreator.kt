package org.teamvoided.dusk_autumn.data.gen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.*
import net.minecraft.fluid.Fluids
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.rule.BlockMatchRuleTest
import net.minecraft.structure.rule.TagMatchRuleTest
import net.minecraft.unmapped.C_cxbmzbuz
import net.minecraft.unmapped.C_cxbmzbuz.C_pkkqenbk
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.Direction
import net.minecraft.util.math.int_provider.BiasedToBottomIntProvider
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.IntProvider
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
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.data.tags.DnDBlockTags
import org.teamvoided.dusk_autumn.data.worldgen.DnDConfiguredFeature
import org.teamvoided.dusk_autumn.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusk_autumn.init.DnDBlocks
import org.teamvoided.dusk_autumn.init.worldgen.DnDFeatures
import org.teamvoided.dusk_autumn.world.gen.configured_feature.BoulderFeature
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.BoulderConfig
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FallenTreeConfig
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FarmlandConfig
import org.teamvoided.dusk_autumn.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusk_autumn.world.gen.root.CascadeRootConfig
import org.teamvoided.dusk_autumn.world.gen.root.CascadeRootPlacer
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterOnGroundTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AttachedToTrunkTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.BeehiveBigTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.trunk.ThreeWideTrunkPlacer
import java.util.*

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object ConfiguredFeatureCreator {

    @Suppress("LongMethod")
    fun bootstrap(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = c.getRegistryLookup(RegistryKeys.BLOCK)
        val configuredFeatures = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures = c.getRegistryLookup(RegistryKeys.PLACED_FEATURE)

        val cascadeTree = TreeFeatureConfig.Builder(
            BlockStateProvider.of(DnDBlocks.CASCADE_LOG),
            ThreeWideTrunkPlacer(9, 2, 1),
            BlockStateProvider.of(DnDBlocks.CASCADE_LEAVES),
            CascadeFoliagePlacer(
                ConstantIntProvider.create(3),
                ConstantIntProvider.create(0),
                ConstantIntProvider.create(2),
                100
            ),
            Optional.of(
                CascadeRootPlacer(
                    BiasedToBottomIntProvider.create(0, 2),
                    BlockStateProvider.of(DnDBlocks.CASCADE_LOG),
                    Optional.empty(),
                    CascadeRootConfig(
                        blockTags.getTagOrThrow(BlockTags.REPLACEABLE_BY_TREES),
                        3,
                        BiasedToBottomIntProvider.create(1, 3),
                        6,
                    )
                )
            ),
            ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
        )
        val goldenBirchTree = builder(Blocks.BIRCH_LOG, DnDBlocks.GOLDEN_BIRCH_LEAVES, 5, 2, 6, 2)
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
        c.registerConfiguredFeature(
            DnDConfiguredFeature.OVERGROWN_COBBLESTONE_BOULDER,
            DnDFeatures.BOULDER,
            BoulderConfig(
                BlockStateProvider.of(DnDBlocks.OVERGROWN_COBBLESTONE.defaultState),
                UniformIntProvider.create(2, 5),
                UniformIntProvider.create(1, 4),
                UniformIntProvider.create(1, 2),
                UniformIntProvider.create(2, 4)
            )
        )

        c.registerConfiguredFeature(
            DnDConfiguredFeature.CASCADE_TREE, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(leafPiles(DnDBlocks.CASCADE_LEAF_PILE, blockTags))
            ).build()
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.CASCADE_TREE_BEES, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(BeehiveBigTreeDecorator(0.1F), leafPiles(DnDBlocks.CASCADE_LEAF_PILE, blockTags))
            ).build()
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.GOLDEN_BIRCH_TALL, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DnDBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.GOLDEN_BIRCH_TALL_BEES, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.1F),
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DnDBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.CASCADE_TREE_AUTUMN, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.02F),
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL),
                        2, 5,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    leafPiles(DnDBlocks.CASCADE_LEAF_PILE, blockTags)
                )
            ).build()
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.GOLDEN_BIRCH_TALL_AUTUMN, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.02F),
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 20,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DnDBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.DARK_OAK_AUTUMN, Feature.TREE, TreeFeatureConfig.Builder(
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
                        leafPiles(DnDBlocks.DARK_OAK_LEAF_PILE, blockTags)
                    )
                ).build()
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.ACACIA_AUTUMN, Feature.TREE, TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG),
                StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.of(Blocks.ACACIA_LEAVES),
                BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                TwoLayersFeatureSize(1, 0, 1)
            )
                .forceDirt().ignoreVines().decorators(
                    ImmutableList.of<TreeDecorator>(leafPiles(DnDBlocks.ACACIA_LEAF_PILE, blockTags))
                ).build()
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.ACACIA_BUSH_AUTUMN, Feature.TREE,
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG),
                StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.ACACIA_LEAVES),
                AcaciaFoliagePlacer(UniformIntProvider.create(1, 2), UniformIntProvider.create(0, 1)),
                TwoLayersFeatureSize(0, 0, 0)
            ).build()
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.DISK_PODZOL, Feature.DISK, DiskFeatureConfig(
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
        c.registerConfiguredFeature(
            DnDConfiguredFeature.DISK_MUD, Feature.DISK, DiskFeatureConfig(
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

        c.registerConfiguredFeature(
            DnDConfiguredFeature.DISK_RED_SAND, Feature.DISK, DiskFeatureConfig(
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
        val pumpkins = DataPool.builder<BlockState>()
        Direction.Type.HORIZONTAL.forEach {
            pumpkins
                .addWeighted(
                    Blocks.CARVED_PUMPKIN.defaultState
                        .with(HorizontalFacingBlock.FACING, it), 8
                )
                .addWeighted(
                    Blocks.JACK_O_LANTERN.defaultState
                        .with(HorizontalFacingBlock.FACING, it), 1
                )
        }
        c.registerConfiguredFeature(
            DnDConfiguredFeature.PATCH_PUMPKIN_EXTRA,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    WeightedBlockStateProvider(
                        pumpkins.addWeighted(Blocks.PUMPKIN.defaultState, 64)
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
        c.registerConfiguredFeature(
            DnDConfiguredFeature.AUTUMN_WOODS_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
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
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DnDPlacedFeature.DARK_OAK_AUTUMN), 0.425f),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DnDPlacedFeature.CASCADE_TREE_AUTUMN), 0.425f)
                ), placedFeatures.getHolderOrThrow(DnDPlacedFeature.GOLDEN_BIRCH_TALL_AUTUMN)
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.AUTUMN_PASTURES_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DnDPlacedFeature.ACACIA_BUSH_AUTUMN), 0.3f),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(DnDPlacedFeature.GOLDEN_BIRCH_TALL_AUTUMN),
                        0.05f
                    ),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DnDPlacedFeature.CASCADE_TREE_AUTUMN), 0.01f)
                ), placedFeatures.getHolderOrThrow(DnDPlacedFeature.ACACIA_AUTUMN)
            )
        )
//        c.registerConfiguredFeature(
//            DnDConfiguredFeature.AUTUMN_WETLANDS_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
//                listOf(
//                    WeightedPlacedFeature(
//                        PlacedFeatureUtil.placedInline(
//                            configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM),
//                            *arrayOfNulls(0)
//                        ), 0.0025f
//                    ),
//                    WeightedPlacedFeature(
//                        PlacedFeatureUtil.placedInline(
//                            configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.HUGE_RED_MUSHROOM),
//                            *arrayOfNulls(0)
//                        ), 0.005f
//                    ),
//                    WeightedPlacedFeature(
//                        placedFeatures.getHolderOrThrow(DnDPlacedFeature.DARK_OAK_AUTUMN_WETLANDS),
//                        0.425f
//                    ),
//                    WeightedPlacedFeature(
//                        placedFeatures.getHolderOrThrow(DnDPlacedFeature.CASCADE_TREE_WETLANDS),
//                        0.425f
//                    )
//                ), placedFeatures.getHolderOrThrow(DnDPlacedFeature.GOLDEN_BIRCH_TALL_WETLANDS)
//            )
//        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.FLOWER_AUTUMN, Feature.FLOWER, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                64,
                PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                        WeightedBlockStateProvider(
                            DataPool.builder<BlockState>()
                                .addWeighted(Blocks.CORNFLOWER.defaultState, 5)
                                .addWeighted(Blocks.POPPY.defaultState, 5)
                                .addWeighted(DnDBlocks.CASCADE_SAPLING.defaultState, 1)
                        )
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.PATCH_ROSEBUSH,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.ROSE_BUSH))
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.BLUE_PETALS, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2,
                PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockFeatureConfig(WeightedBlockStateProvider(petalBuilder(DnDBlocks.BLUE_PETALS)))
                )
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.AUTUMN_FARMLAND, DnDFeatures.FARMLAND, FarmlandConfig(
                BlockTags.DIRT,
                DnDBlockTags.FARMLAND_PLACES_UNDER,
                BlockStateProvider.of(Blocks.FARMLAND.defaultState.with(FarmlandBlock.MOISTURE, 7)),
                0.85f,
                BiasedToBottomIntProvider.create(3, 16),
                4,
                BlockStateProvider.of(Blocks.DARK_OAK_FENCE),
                0.9f,
                BiasedToBottomIntProvider.create(1, 24),
                BlockStateProvider.of(Blocks.WATER),
                0.9f,
                PlacedFeatureUtil.placedInline(
                    configuredFeatures.getHolderOrThrow(DnDConfiguredFeature.AUTUMN_FARMLAND_CROPS),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                0.1f,
                true,
                listOf()
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.AUTUMN_FARMLAND_CROPS, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(DnDConfiguredFeature.CROPS_WHEAT),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.25f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(DnDConfiguredFeature.CROPS_CARROTS),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(DnDConfiguredFeature.CROPS_POTATOES),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(DnDConfiguredFeature.CROPS_PUMPKIN),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(DnDConfiguredFeature.CROPS_BEETROOTS),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(DnDConfiguredFeature.CROPS_GOLDEN_BEETROOTS),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.05f
                    )
                ), PlacedFeatureUtil.placedInline(
                    configuredFeatures.getHolderOrThrow(DnDConfiguredFeature.CROPS_WILD_WHEAT),
                    *arrayOfNulls<PlacementModifier>(0)
                )
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_WILD_WHEAT,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(
                    BlockStateProvider.of(
                        DnDBlocks.WILD_WHEAT.defaultState
                    )
                ), ImmutableList.of(Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.FARMLAND), 32
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_WHEAT,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.WHEAT)
                ),
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_CARROTS,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.CARROTS)
                )
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_POTATOES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.POTATOES)
                )
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_PUMPKIN,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.PUMPKIN_STEM)
                )
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_BEETROOTS,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
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
        c.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_GOLDEN_BEETROOTS,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    WeightedBlockStateProvider(
                        DataPool.builder<BlockState>()
                            .addWeighted(DnDBlocks.GOLDEN_BEETROOTS.defaultState, 1)
                            .addWeighted(DnDBlocks.GOLDEN_BEETROOTS.defaultState.with(BeetrootsBlock.AGE, 2), 1)
                            .addWeighted(Blocks.BEETROOTS.defaultState.with(BeetrootsBlock.AGE, 3), 1)
                    )
                )
            )
        )
        fallenTrees(c)
        overlayOres(c)
    }

    fun fallenTrees(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val mushrooms = WeightedBlockStateProvider(
            DataPool.builder<BlockState>()
                .addWeighted(Blocks.BROWN_MUSHROOM.defaultState, 1)
                .addWeighted(Blocks.RED_MUSHROOM.defaultState, 1)
        )
        val vine = BlockStateProvider.of(Blocks.VINE)
        createFallenTree(
            c,
            DnDConfiguredFeature.OAK_FALLEN_TREE,
            Blocks.OAK_LOG,
            DnDBlocks.HOLLOW_OAK_LOG,
            5,
            3,
            mushrooms,
            vine
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.SPRUCE_FALLEN_TREE,
            Blocks.SPRUCE_LOG,
            DnDBlocks.HOLLOW_SPRUCE_LOG,
            5,
            5,
            mushrooms,
            vine,
            null, null,
            UniformIntProvider.create(2, 5)
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.BIRCH_FALLEN_TREE,
            Blocks.BIRCH_LOG,
            DnDBlocks.HOLLOW_BIRCH_LOG,
            5,
            null,
            mushrooms
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.BIRCH_TALL_FALLEN_TREE,
            Blocks.BIRCH_LOG,
            DnDBlocks.HOLLOW_BIRCH_LOG,
            5,
            null,
            mushrooms,
            null,
            null,
            null,
            UniformIntProvider.create(3, 6)
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.JUNGLE_FALLEN_TREE,
            Blocks.JUNGLE_LOG,
            DnDBlocks.HOLLOW_JUNGLE_LOG,
            5,
            0,
            mushrooms,
            vine,
            null,
            null,
            UniformIntProvider.create(2, 6)
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.ACACIA_FALLEN_TREE,
            Blocks.ACACIA_LOG,
            DnDBlocks.HOLLOW_ACACIA_LOG,
            5,
            5,
            mushrooms,
            vine
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.DARK_OAK_FALLEN_TREE,
            Blocks.DARK_OAK_LOG,
            DnDBlocks.HOLLOW_DARK_OAK_LOG,
            5,
            3,
            mushrooms,
            vine,
            2
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.CHERRY_FALLEN_TREE,
            Blocks.CHERRY_LOG,
            DnDBlocks.HOLLOW_CHERRY_LOG
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.CASCADE_FALLEN_TREE,
            DnDBlocks.CASCADE_LOG,
            DnDBlocks.HOLLOW_CASCADE_LOG,
            5,
            3,
            mushrooms,
            vine,
            3
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.CRIMSON_FALLEN_STEM,
            Blocks.CRIMSON_STEM,
            DnDBlocks.HOLLOW_CRIMSON_STEM,
            16,
            null,
            mushrooms,
            null,
            null,
            null,
            UniformIntProvider.create(2, 6)
        )
        createFallenTree(
            c,
            DnDConfiguredFeature.WARPED_FALLEN_STEM,
            Blocks.WARPED_STEM,
            DnDBlocks.HOLLOW_WARPED_STEM,
            16,
            null,
            mushrooms,
            null,
            null,
            null,
            UniformIntProvider.create(2, 6)
        )
    }

    fun createFallenTree(
        c: BootstrapContext<ConfiguredFeature<*, *>>,
        feature: RegistryKey<ConfiguredFeature<*, *>>,
        log: Block,
        hollowLog: Block,
        topperChance: Int? = null,
        sidesChance: Int? = null,
        topper: BlockStateProvider? = null,
        sides: BlockStateProvider? = null,
        width: Int? = null,
        stumpHeight: IntProvider? = null,
        trunkLength: IntProvider? = null,
        trunkDistanceFromStump: IntProvider? = null,
        trunkVerticalRange: Int? = null,
    ) {
        //the values are null because the thing below yells at me, so I moved the default values down here
        c.registerConfiguredFeature(
            feature, DnDFeatures.FALLEN_TREE, FallenTreeConfig(
                BlockStateProvider.of(log),
                BlockStateProvider.of(hollowLog),
                DnDBlockTags.FALLEN_TREE_REPLACEABLE,
                topperChance ?: -1,
                sidesChance ?: -1,
                topper ?: BlockStateProvider.of(Blocks.AIR),
                sides ?: BlockStateProvider.of(Blocks.AIR),
                width ?: 1,
                stumpHeight ?: BiasedToBottomIntProvider.create(1, 3),
                trunkLength ?: UniformIntProvider.create(2, 4),
                trunkDistanceFromStump ?: UniformIntProvider.create(0, 2),
                trunkVerticalRange ?: 16
            )
        )

    }

    fun overlayOres(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        c.registerConfiguredFeature(
            DnDConfiguredFeature.ROCKY_OVERWORLD_ORE,
            Feature.ORE,
            OreFeatureConfig(
                listOf<OreFeatureConfig.Target>(
                    OreFeatureConfig.createTarget(
                        TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES),
                        DnDBlocks.ROCKY_DIRT.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.DIRT),
                        DnDBlocks.ROCKY_DIRT.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.GRASS_BLOCK),
                        DnDBlocks.ROCKY_GRASS.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.PODZOL),
                        DnDBlocks.ROCKY_PODZOL.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.GRAVEL),
                        DnDBlocks.ROCKY_GRAVEL.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.DIRT_PATH),
                        DnDBlocks.ROCKY_DIRT_PATH.defaultState
                    )
                ), 33
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.SLATED_OVERWORLD_ORE,
            Feature.ORE,
            OreFeatureConfig(
                listOf<OreFeatureConfig.Target>(
                    OreFeatureConfig.createTarget(
                        TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        DnDBlocks.SLATED_DIRT.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.GRAVEL),
                        DnDBlocks.SLATED_GRAVEL.defaultState
                    ),
                ), 33
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.BLACKSTONE_NETHER_ORE,
            Feature.ORE,
            OreFeatureConfig(
                listOf<OreFeatureConfig.Target>(
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.NETHERRACK),
                        DnDBlocks.BLACKSTONE_SOUL_SAND.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.SOUL_SAND),
                        DnDBlocks.BLACKSTONE_SOUL_SAND.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.SOUL_SOIL),
                        DnDBlocks.BLACKSTONE_SOUL_SOIL.defaultState
                    ),
                ), 33
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

    fun petalBuilder(flower: Block): DataPool.Builder<BlockState> {
        val petalFlowerBuilder = DataPool.builder<BlockState>()
        (1..4).forEach { count ->
            Direction.Type.HORIZONTAL.forEach { direction ->
                petalFlowerBuilder.addWeighted(
                    flower.defaultState
                        .with(PinkPetalsBlock.AMOUNT, count).with(PinkPetalsBlock.FACING, direction),
                    1
                )
            }
        }
        return petalFlowerBuilder
    }

    fun leafPiles(leafPile: Block, blockTags: HolderProvider<Block>): TreeDecorator {
        return AlterOnGroundTreeDecorator(
            WeightedBlockStateProvider(
                DataPool.builder<BlockState>()
                    .addWeighted(leafPile.defaultState, 9)
                    .addWeighted(leafPile.defaultState.with(LeafPileBlock.PILE_LAYERS, 2), 4)
                    .addWeighted(leafPile.defaultState.with(LeafPileBlock.PILE_LAYERS, 3), 1)
            ),
            3, 10, 20,
            blockTags.getTagOrThrow(DnDBlockTags.LEAF_PILES_PLACE_ON)
        )
    }

    private fun <FC : FeatureConfig, F : Feature<FC>> BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: RegistryKey<ConfiguredFeature<*, *>>,
        feature: F,
        featureConfig: FC
    ): Any = this.register(registryKey, ConfiguredFeature(feature, featureConfig))

    @Suppress("unused")
    private fun BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: RegistryKey<ConfiguredFeature<*, *>>, feature: Feature<DefaultFeatureConfig>
    ) = this.registerConfiguredFeature(registryKey, feature, FeatureConfig.DEFAULT)
}
