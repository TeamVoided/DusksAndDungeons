package org.teamvoided.dusk_autumn.init.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.*
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.Fluids
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.unmapped.C_cxbmzbuz
import net.minecraft.unmapped.C_cxbmzbuz.C_pkkqenbk
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.Direction
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import org.teamvoided.dusk_autumn.DuskAutumns
import org.teamvoided.dusk_autumn.data.DuskBlockTags
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterOnGroundTreeDecorator
import java.util.*
import java.util.List
import kotlin.collections.Iterator
import kotlin.collections.listOf

object DuskConfiguredFeature {
    val COBBLESTONE_ROCK = create("cobblestone_rock")
    val GOLDEN_BIRCH_TALL = create("golden_birch_tall")
    val GOLDEN_BIRCH_TALL_BEES = create("golden_birch_tall_bees")
    val CASCADE_TREE = create("cascade_tree")
    val CASCADE_TREE_BEES = create("cascade_tree_bees")
    val DARK_OAK_AUTUMN = create("dark_oak_autumn")
    val DISK_PODZOL = create("disk_podzol")
    val PATCH_PUMPKIN_EXTRA = create("patch_pumpkin_extra")
    val AUTUMN_WOODS_VEGETATION = create("autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("autumn_pastures_vegetation")
    val FLOWER_AUTUMN = create("flower_autumn")
    val PATCH_ROSEBUSH = create("patch_rosebush")
    val BLUE_PETALS = create("blue_petals")

    fun create(id: String) = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, DuskAutumns.id(id))

    fun init() {}

    fun bootstrapConfiguredFeatures(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = context.lookup(RegistryKeys.BLOCK)
        val configuredFeatures: HolderProvider<ConfiguredFeature<*, *>> =
            context.lookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures: HolderProvider<PlacedFeature> =
            context.lookup(RegistryKeys.PLACED_FEATURE)

        val petalFlowerBuilder = DataPool.builder<BlockState>()
        for (i in 1..4) {
            val var35: Iterator<*> = Direction.Type.HORIZONTAL.iterator()
            while (var35.hasNext()) {
                val direction = var35.next() as Direction
                petalFlowerBuilder.add(
                    (DuskBlocks.BLUE_PETALS.defaultState.with(PinkPetalsBlock.AMOUNT, i) as BlockState).with(
                        PinkPetalsBlock.FACING,
                        direction
                    ) as BlockState, 1
                )
            }
        }

        ConfiguredFeatureUtil.registerConfiguredFeature<SingleStateFeatureConfig, Feature<SingleStateFeatureConfig>>(
            context,
            COBBLESTONE_ROCK,
            Feature.FOREST_ROCK,
            SingleStateFeatureConfig(Blocks.COBBLESTONE.defaultState)
        )
        val goldenBirchTree = builder(Blocks.BIRCH_LOG, DuskBlocks.GOLDEN_BIRCH_LEAVES, 5, 2, 6, 2)
        val cascadeTree = TreeFeatureConfig.Builder(
            BlockStateProvider.of(DuskBlocks.CASCADE_LOG),
            DarkOakTrunkPlacer(7, 3, 2),
            BlockStateProvider.of(DuskBlocks.CASCADE_LEAVES),
            DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
            ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<TreeFeatureConfig, Feature<TreeFeatureConfig>>(
            context, GOLDEN_BIRCH_TALL,
            Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 20, blockTags.getTagOrThrow(
                            BlockTags.DIRT
                        )
                    ),
                    AlterOnGroundTreeDecorator(
                        BlockStateProvider.of(
                            DuskBlocks.GOLDEN_BIRCH_LEAF_PILE.defaultState
                        ),
                        2,
                        20,
                        40,
                        blockTags.getTagOrThrow(
                            DuskBlockTags.LEAF_PILES_PLACE_ON
                        )
                    )
                )
            ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<TreeFeatureConfig, Feature<TreeFeatureConfig>>(
            context, GOLDEN_BIRCH_TALL_BEES,
            Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of<TreeDecorator>(
//                        BeehiveTreeDecorator(0.02F),
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 20, blockTags.getTagOrThrow(
                            BlockTags.DIRT
                        )
                    ),
                    AlterOnGroundTreeDecorator(
                        BlockStateProvider.of(
                            DuskBlocks.GOLDEN_BIRCH_LEAF_PILE.defaultState
                        ),
                        2,
                        20,
                        40,
                        blockTags.getTagOrThrow(
                            DuskBlockTags.LEAF_PILES_PLACE_ON
                        )
                    )
                )
            ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            context, CASCADE_TREE, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of<TreeDecorator>(
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 5, blockTags.getTagOrThrow(
                            BlockTags.DIRT
                        )
                    ),
                    AlterOnGroundTreeDecorator(
                        BlockStateProvider.of(
                            DuskBlocks.CASCADE_LEAF_PILE.defaultState
                        ),
                        2,
                        10,
                        20,
                        blockTags.getTagOrThrow(
                            DuskBlockTags.LEAF_PILES_PLACE_ON
                        )
                    )
                )
            ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            context, CASCADE_TREE_BEES, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(
//                    BeehiveTreeDecorator(0.02F),
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 5, blockTags.getTagOrThrow(
                            BlockTags.DIRT
                        )
                    ),
                    AlterOnGroundTreeDecorator(
                        BlockStateProvider.of(
                            DuskBlocks.CASCADE_LEAF_PILE.defaultState
                        ),
                        2,
                        10,
                        20,
                        blockTags.getTagOrThrow(
                            DuskBlockTags.LEAF_PILES_PLACE_ON
                        )
                    )
                )
            ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            context, DARK_OAK_AUTUMN, Feature.TREE,
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.DARK_OAK_LOG),
                DarkOakTrunkPlacer(6, 2, 1),
                BlockStateProvider.of(Blocks.DARK_OAK_LEAVES),
                DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
            )
                .forceDirt().ignoreVines().decorators(
                    ImmutableList.of<TreeDecorator>(
                        AlterGroundRadiusTreeDecorator(
                            BlockStateProvider.of(Blocks.PODZOL), 2, 5, blockTags.getTagOrThrow(
                                BlockTags.DIRT
                            )
                        ),
                        AlterOnGroundTreeDecorator(
                            BlockStateProvider.of(
                                DuskBlocks.DARK_OAK_LEAF_PILE.defaultState
                            ), 2, 10, 20, blockTags.getTagOrThrow(
                                DuskBlockTags.LEAF_PILES_PLACE_ON
                            )
                        )
                    )
                ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<DiskFeatureConfig, Feature<DiskFeatureConfig>>(
            context, DISK_PODZOL, Feature.DISK, DiskFeatureConfig(
                C_cxbmzbuz(
                    BlockStateProvider.of(Blocks.DIRT), listOf(
                        C_pkkqenbk(
                            BlockPredicate.not(
                                BlockPredicate.eitherOf(
                                    BlockPredicate.solid(
                                        Direction.UP.vector
                                    ), BlockPredicate.matchingFluids(Direction.UP.vector, *arrayOf<Fluid>(Fluids.WATER))
                                )
                            ), BlockStateProvider.of(Blocks.PODZOL)
                        )
                    )
                ), BlockPredicate.matchingBlocks(
                    listOf<Block>(Blocks.DIRT, Blocks.GRASS_BLOCK)
                ), UniformIntProvider.create(2, 6), 2
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            context,
            PATCH_PUMPKIN_EXTRA,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(
                Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(
                    WeightedBlockStateProvider(
                        DataPool.builder<BlockState>()
                            .add(Blocks.PUMPKIN.defaultState, 16)
                            .add(Blocks.CARVED_PUMPKIN.defaultState, 1)
                            .add(
                                Blocks.CARVED_PUMPKIN.defaultState.with(
                                    HorizontalFacingBlock.FACING,
                                    Direction.SOUTH
                                ), 1
                            )
                            .add(
                                Blocks.CARVED_PUMPKIN.defaultState.with(
                                    HorizontalFacingBlock.FACING,
                                    Direction.EAST
                                ), 1
                            )
                            .add(
                                Blocks.CARVED_PUMPKIN.defaultState.with(
                                    HorizontalFacingBlock.FACING,
                                    Direction.WEST
                                ), 1
                            )
                    )
                ),
                listOf(Blocks.GRASS_BLOCK, Blocks.FARMLAND, Blocks.PODZOL, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN)
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomFeatureConfig, Feature<RandomFeatureConfig>>(
            context,
            AUTUMN_WOODS_VEGETATION,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.0025f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.HUGE_RED_MUSHROOM),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.005f
                    ),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.DARK_OAK_AUTUMN), 0.425f),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.CASCADE_TREE_BEES), 0.425f)
                ), placedFeatures.getHolderOrThrow(DuskPlacedFeature.GOLDEN_BIRCH_TALL_BEES)
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomFeatureConfig, Feature<RandomFeatureConfig>>(
            context, AUTUMN_PASTURES_VEGETATION,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.DARK_OAK_AUTUMN), 0.45f),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeature.CASCADE_TREE_BEES), 0.45f)
                ), placedFeatures.getHolderOrThrow(DuskPlacedFeature.GOLDEN_BIRCH_TALL_BEES)
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            context,
            FLOWER_AUTUMN,
            Feature.FLOWER,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                64,
                PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockFeatureConfig(
                        WeightedBlockStateProvider(
                            DataPool.builder<BlockState>().add(Blocks.CORNFLOWER.defaultState, 5)
                                .add(Blocks.POPPY.defaultState, 5)
                                .add(DuskBlocks.CASCADE_SAPLING.defaultState, 1)
                        )
                    )
                )
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            context,
            PATCH_ROSEBUSH,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.ROSE_BUSH))
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            context, BLUE_PETALS, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2, PlacedFeatureUtil.onlyWhenEmpty<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(WeightedBlockStateProvider(petalFlowerBuilder))
                )
            )
        )

    }

    fun builder(
        trunk: Block,
        foliage: Block,
        baseHeight: Int,
        firstRandomHeight: Int,
        secondRandomHeight: Int,
        foliageRadius: Int
    ): TreeFeatureConfig.Builder {
        return TreeFeatureConfig.Builder(
            BlockStateProvider.of(trunk),
            StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight),
            BlockStateProvider.of(foliage),
            BlobFoliagePlacer(ConstantIntProvider.create(foliageRadius), ConstantIntProvider.create(0), 3),
            TwoLayersFeatureSize(1, 0, 1)
        )
    }

}