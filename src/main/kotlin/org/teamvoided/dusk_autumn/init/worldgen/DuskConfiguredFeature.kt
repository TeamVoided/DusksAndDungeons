package org.teamvoided.dusk_autumn.init.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.*
import net.minecraft.fluid.Fluids
import net.minecraft.registry.*
import net.minecraft.registry.tag.BlockTags
import net.minecraft.unmapped.C_cxbmzbuz
import net.minecraft.unmapped.C_cxbmzbuz.C_pkkqenbk
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.Direction
import net.minecraft.util.math.int_provider.BiasedToBottomIntProvider
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import net.minecraft.world.gen.foliage.*
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import org.teamvoided.dusk_autumn.DuskAutumns
import org.teamvoided.dusk_autumn.block.LeafPileBlock
import org.teamvoided.dusk_autumn.data.DuskBlockTags
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.world.gen.configured_feature.config.FarmlandConfig
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterOnGroundTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AttachedToTrunkTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.BeehiveBigTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.trunk.ThreeWideTrunkPlacer
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
object DuskConfiguredFeature {
    val COBBLESTONE_ROCK = create("cobblestone_rock")
    val CASCADE_TREE = create("cascade_tree")
    val CASCADE_TREE_BEES = create("cascade_tree_bees")
    val GOLDEN_BIRCH_TALL = create("golden_birch_tall")
    val GOLDEN_BIRCH_TALL_BEES = create("golden_birch_tall_bees")
    val DARK_OAK_AUTUMN = create("dark_oak_autumn")
    val ACACIA_AUTUMN = create("acacia_autumn")
    val ACACIA_BUSH_AUTUMN = create("acacia_bush_autumn")
    val DISK_PODZOL = create("disk_podzol")
    val PATCH_PUMPKIN_EXTRA = create("patch_pumpkin_extra")
    val AUTUMN_WOODS_VEGETATION = create("autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("autumn_pastures_vegetation")
    val FLOWER_AUTUMN = create("flower_autumn")
    val PATCH_ROSEBUSH = create("patch_rosebush")
    val BLUE_PETALS = create("blue_petals")
    val AUTUMN_FARMLAND = create("autumn_farmland")
    val CROPS_WILD_WHEAT = create("crops/wild_wheat")
    val AUTUMN_FARMLAND_CROPS = create("crops/autumn_farmland_crops")
    val CROPS_WHEAT = create("crops/wheat")
    val CROPS_CARROTS = create("crops/carrots")
    val CROPS_POTATOES = create("crops/potatoes")
    val CROPS_PUMPKIN = create("crops/pumpkins")
    val CROPS_BEETROOTS = create("crops/beetroots")
    val CROPS_GOLDEN_BEETROOTS = create("crops/golden_beetroots")


    fun init() {}

    fun bootstrapConfiguredFeatures(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = c.lookup(RegistryKeys.BLOCK)
        val configuredFeatures = c.lookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures = c.lookup(RegistryKeys.PLACED_FEATURE)

        val petalFlowerBuilder = DataPool.builder<BlockState>()
        (1..4).forEach { count ->
            Direction.Type.HORIZONTAL.forEach { direction ->
                petalFlowerBuilder.method_34975(
                    DuskBlocks.BLUE_PETALS.defaultState
                        .with(PinkPetalsBlock.AMOUNT, count).with(PinkPetalsBlock.FACING, direction),
                    1
                )
            }
        }
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, COBBLESTONE_ROCK, Feature.FOREST_ROCK, SingleStateFeatureConfig(Blocks.COBBLESTONE.defaultState)
        )
        val cascadeTree = TreeFeatureConfig.Builder(
            BlockStateProvider.of(DuskBlocks.CASCADE_LOG),
            ThreeWideTrunkPlacer(9, 2, 1),
            BlockStateProvider.of(DuskBlocks.CASCADE_LEAVES),
            RandomSpreadFoliagePlacer(
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
        ConfiguredFeatureUtil.registerConfiguredFeature(
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
        ConfiguredFeatureUtil.registerConfiguredFeature(
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
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, GOLDEN_BIRCH_TALL, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 20,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    AttachedToTrunkTreeDecorator(
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
                    ),
                    AttachedToTrunkTreeDecorator(
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
                    ),
                    leafPiles(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, GOLDEN_BIRCH_TALL_BEES, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.02F),
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 20,
                        blockTags.getTagOrThrow(BlockTags.DIRT)
                    ),
                    AttachedToTrunkTreeDecorator(
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
                    ),
                    AttachedToTrunkTreeDecorator(
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
                    ),
                    leafPiles(DuskBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
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
        ConfiguredFeatureUtil.registerConfiguredFeature(
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
        ConfiguredFeatureUtil.registerConfiguredFeature<TreeFeatureConfig, Feature<TreeFeatureConfig>>(
            c, ACACIA_BUSH_AUTUMN, Feature.TREE,
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG),
                StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.ACACIA_LEAVES),
                AcaciaFoliagePlacer(UniformIntProvider.create(1, 2), UniformIntProvider.create(1, 2)),
                TwoLayersFeatureSize(0, 0, 0)
            ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<DiskFeatureConfig, Feature<DiskFeatureConfig>>(
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
                BlockPredicate.matchingBlocks(listOf(Blocks.DIRT, Blocks.GRASS_BLOCK)),
                UniformIntProvider.create(2, 6), 2
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, PATCH_PUMPKIN_EXTRA, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    WeightedBlockStateProvider(
                        DataPool.builder<BlockState>()
                            .method_34975(Blocks.PUMPKIN.defaultState, 64)
                            .method_34975(Blocks.CARVED_PUMPKIN.defaultState, 8)
                            .method_34975(
                                Blocks.CARVED_PUMPKIN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.SOUTH), 8
                            )
                            .method_34975(
                                Blocks.CARVED_PUMPKIN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.EAST), 8
                            )
                            .method_34975(
                                Blocks.CARVED_PUMPKIN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.WEST), 8
                            )
                            .method_34975(Blocks.JACK_O_LANTERN.defaultState, 1)
                            .method_34975(
                                Blocks.JACK_O_LANTERN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.SOUTH), 1
                            )
                            .method_34975(
                                Blocks.JACK_O_LANTERN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.EAST), 1
                            )
                            .method_34975(
                                Blocks.JACK_O_LANTERN.defaultState
                                    .with(HorizontalFacingBlock.FACING, Direction.WEST), 1
                            )
                    )
                ),
                listOf(Blocks.GRASS_BLOCK, Blocks.FARMLAND, Blocks.PODZOL, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN)
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
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
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, AUTUMN_PASTURES_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.ACACIA_AUTUMN), 0.4f),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.ACACIA_BUSH_AUTUMN), 0.2f),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.CASCADE_TREE_BEES), 0.2f)
                ), placedFeatures.getHolderOrThrow(DuskPlacedFeature.GOLDEN_BIRCH_TALL_BEES)
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, FLOWER_AUTUMN, Feature.FLOWER, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                64,
                PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                        WeightedBlockStateProvider(
                            DataPool.builder<BlockState>()
                                .method_34975(Blocks.CORNFLOWER.defaultState, 5)
                                .method_34975(Blocks.POPPY.defaultState, 5)
                                .method_34975(DuskBlocks.CASCADE_SAPLING.defaultState, 1)
                        )
                    )
                )
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, PATCH_ROSEBUSH, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.ROSE_BUSH))
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, BLUE_PETALS, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2,
                PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(WeightedBlockStateProvider(petalFlowerBuilder))
                )
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, AUTUMN_FARMLAND, DuskFeatures.FARMLAND, FarmlandConfig(
                BlockTags.DIRT,
                DuskBlockTags.FARMLAND_PLACES_UNDER,
                BlockStateProvider.of(Blocks.FARMLAND.defaultState.with(FarmlandBlock.MOISTURE, 7)),
                0.9f,
                BiasedToBottomIntProvider.create(2, 16),
                4,
                BlockStateProvider.of(Blocks.DARK_OAK_FENCE),
                0.9f,
                BiasedToBottomIntProvider.create(1, 24),
                BlockStateProvider.of(Blocks.WATER),
                0.3f,
                PlacedFeatureUtil.placedInline(
                    configuredFeatures.getHolderOrThrow(AUTUMN_FARMLAND_CROPS),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                0.05f,
                true,
                listOf()
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, AUTUMN_FARMLAND_CROPS, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(CROPS_WHEAT),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.175f
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
                            configuredFeatures.getHolderOrThrow(CROPS_BEETROOTS),
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
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, CROPS_WILD_WHEAT, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(
                    BlockStateProvider.of(
                        DuskBlocks.WILD_WHEAT.defaultState
                    )
                ), ImmutableList.of(Blocks.AIR), 32
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, CROPS_WHEAT, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(
                    BlockStateProvider.of(
                        Blocks.WHEAT.defaultState.with(
                            CropBlock.AGE, 7
                        )
                    )
                ),
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, CROPS_CARROTS, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    BlockStateProvider.of(
                        Blocks.CARROTS.defaultState.with(
                            CropBlock.AGE, 7
                        )
                    )
                )
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, CROPS_POTATOES, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    BlockStateProvider.of(
                        Blocks.POTATOES.defaultState.with(
                            CropBlock.AGE, 7
                        )
                    )
                )
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, CROPS_PUMPKIN, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    BlockStateProvider.of(
                        Blocks.PUMPKIN_STEM.defaultState.with(
                            CropBlock.AGE, 7
                        )
                    )
                )
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, CROPS_BEETROOTS, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    BlockStateProvider.of(
                        Blocks.BEETROOTS.defaultState.with(
                            BeetrootsBlock.AGE, 3
                        )
                    )
                )
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            c, CROPS_GOLDEN_BEETROOTS, Feature.RANDOM_PATCH, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    BlockStateProvider.of(
                        DuskBlocks.GOLDEN_BEETROOTS.defaultState.with(
                            BeetrootsBlock.AGE, 3
                        )
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

    fun leafPiles(leafPile: Block, blockTags: HolderProvider<Block>): AlterOnGroundTreeDecorator {
        return AlterOnGroundTreeDecorator(
            WeightedBlockStateProvider(
                DataPool.builder<BlockState>()
                    .method_34975(leafPile.defaultState, 9)
                    .method_34975(leafPile.defaultState.with(LeafPileBlock.PILE_LAYERS, 2), 4)
                    .method_34975(leafPile.defaultState.with(LeafPileBlock.PILE_LAYERS, 3), 1)
            ),
            3, 10, 20,
            blockTags.getTagOrThrow(DuskBlockTags.LEAF_PILES_PLACE_ON)
        )
    }

    fun create(id: String): RegistryKey<ConfiguredFeature<*, *>?> =
        RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, DuskAutumns.id(id))

}