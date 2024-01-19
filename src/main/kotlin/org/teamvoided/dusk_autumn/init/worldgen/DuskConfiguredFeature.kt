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
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import org.teamvoided.dusk_autumn.DuskAutumns
import org.teamvoided.dusk_autumn.init.DuskBlocks
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusk_autumn.world.gen.treedcorator.AlterOnGroundTreeDecorator
import java.util.*
import kotlin.collections.Iterator
import kotlin.collections.listOf

object DuskConfiguredFeature {
    val GOLDEN_BIRCH_TALL = create("golden_birch_tall")
    val GOLDEN_BIRCH_TALL_BEES = create("golden_birch_tall_bees")
    val AUTUMN_TREE = create("autumn_tree")
//    val AUTUMN_TREE_BEES = create("autumn_tree_bees")
    val DARK_OAK_AUTUMN = create("dark_oak_autumn")
    val COBBLESTONE_ROCK = create("cobblestone_rock")
    val DISK_PODZOL = create("disk_podzol")
    val AUTUMN_WOODS_VEGETATION = create("autumn_woods_vegetation")
    val AUTUMN_PASTURES_VEGETATION = create("autumn_pastures_vegetation")
    val AUTUMN_WOODS_FLOWER = create("autumn_woods_flower")

    fun create(id: String) = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, DuskAutumns.id(id))

    fun init() {}

    fun bootstrapConfiguredFeatures(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTagProvider = context.lookup(RegistryKeys.BLOCK)
        val configuredFeatureProvider: HolderProvider<ConfiguredFeature<*, *>> =
            context.lookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatureProvider: HolderProvider<PlacedFeature> =
            context.lookup(RegistryKeys.PLACED_FEATURE)



        ConfiguredFeatureUtil.registerConfiguredFeature<TreeFeatureConfig, Feature<TreeFeatureConfig>>(
            context, GOLDEN_BIRCH_TALL_BEES,
            Feature.TREE,
            builder(Blocks.BIRCH_LOG, DuskBlocks.GOLDEN_BIRCH_LEAVES, 5, 2, 6, 2).ignoreVines()
                .decorators(
                    ImmutableList.of<TreeDecorator>(
                        BeehiveTreeDecorator(0.02F),
                        AlterGroundRadiusTreeDecorator(
                            BlockStateProvider.of(Blocks.PODZOL), 2, 20, blockTagProvider.getTagOrThrow(
                                BlockTags.DIRT
                            )
                        ),
                        AlterOnGroundTreeDecorator(
                            BlockStateProvider.of(
                                DuskBlocks.GOLDEN_BIRCH_LEAF_PILE.defaultState.with(
                                    LeavesBlock.PERSISTENT,
                                    true
                                )
                            ),
                            2,
                            20,
                            40,
                            blockTagProvider.getTagOrThrow(
                                BlockTags.SCULK_REPLACEABLE_WORLD_GEN
                            )
                        )
                    )
                )
                .build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<TreeFeatureConfig, Feature<TreeFeatureConfig>>(
            context, GOLDEN_BIRCH_TALL,
            Feature.TREE,
            builder(Blocks.BIRCH_LOG, DuskBlocks.GOLDEN_BIRCH_LEAVES, 5, 2, 6, 2).ignoreVines()
                .decorators(
                    ImmutableList.of<TreeDecorator>(
                        AlterGroundRadiusTreeDecorator(
                            BlockStateProvider.of(Blocks.PODZOL), 2, 20, blockTagProvider.getTagOrThrow(
                                BlockTags.DIRT
                            )
                        ),
                        AlterOnGroundTreeDecorator(
                            BlockStateProvider.of(
                                DuskBlocks.GOLDEN_BIRCH_LEAF_PILE.defaultState.with(
                                    LeavesBlock.PERSISTENT,
                                    true
                                )
                            ),
                            2,
                            20,
                            40,
                            blockTagProvider.getTagOrThrow(
                                BlockTags.SCULK_REPLACEABLE_WORLD_GEN
                            )
                        )
                    )
                )
                .build()
        )
//        ConfiguredFeatureUtil.registerConfiguredFeature(
//            context, AUTUMN_TREE_BEES, Feature.TREE, TreeFeatureConfig.Builder(
//                BlockStateProvider.of(DuskBlocks.BLUE_LOG),
//                DarkOakTrunkPlacer(7, 3, 2),
//                BlockStateProvider.of(DuskBlocks.RED_LEAVES),
//                DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
//                ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
//            ).ignoreVines().decorators(
//                ImmutableList.of<TreeDecorator>(
//                    BeehiveTreeDecorator(0.02F),
//                    AlterGroundRadiusTreeDecorator(
//                        BlockStateProvider.of(Blocks.PODZOL), 2, 5, blockTagProvider.getTagOrThrow(
//                            BlockTags.DIRT
//                        )
//                    ),
//                    AlterOnGroundTreeDecorator(
//                        BlockStateProvider.of(DuskBlocks.RED_LEAF_PILE.defaultState.with(LeavesBlock.PERSISTENT, true)),
//                        2,
//                        10,
//                        20,
//                        blockTagProvider.getTagOrThrow(
//                            BlockTags.SCULK_REPLACEABLE_WORLD_GEN
//                        )
//                    )
//                )
//            ).build()
//        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            context, AUTUMN_TREE, Feature.TREE, TreeFeatureConfig.Builder(
                BlockStateProvider.of(DuskBlocks.BLUE_LOG),
                DarkOakTrunkPlacer(7, 3, 2),
                BlockStateProvider.of(DuskBlocks.RED_LEAVES),
                DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
                ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
            )
            .forceDirt().ignoreVines().decorators(
                ImmutableList.of<TreeDecorator>(
                    AlterGroundRadiusTreeDecorator(
                        BlockStateProvider.of(Blocks.PODZOL), 2, 5, blockTagProvider.getTagOrThrow(
                            BlockTags.DIRT
                        )
                    ),
                    AlterOnGroundTreeDecorator(
                        BlockStateProvider.of(DuskBlocks.RED_LEAF_PILE.defaultState.with(LeavesBlock.PERSISTENT, true)),
                        2,
                        10,
                        20,
                        blockTagProvider.getTagOrThrow(
                            BlockTags.SCULK_REPLACEABLE_WORLD_GEN
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
                        BlockStateProvider.of(Blocks.PODZOL), 2, 5, blockTagProvider.getTagOrThrow(
                            BlockTags.DIRT
                        )
                    ),
                    AlterOnGroundTreeDecorator(
                        BlockStateProvider.of(
                            DuskBlocks.DARK_OAK_LEAF_PILE.defaultState.with(
                                LeavesBlock.PERSISTENT,
                                true
                            )
                        ), 2, 10, 20, blockTagProvider.getTagOrThrow(
                            BlockTags.SCULK_REPLACEABLE_WORLD_GEN
                        )
                    )
                )
            ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            context, COBBLESTONE_ROCK, Feature.FOREST_ROCK,
            SingleStateFeatureConfig(Blocks.COBBLESTONE.defaultState)
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


        ConfiguredFeatureUtil.registerConfiguredFeature<RandomFeatureConfig, Feature<RandomFeatureConfig>>(
            context, AUTUMN_WOODS_VEGETATION,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf<WeightedPlacedFeature>(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatureProvider.getHolderOrThrow(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.005f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatureProvider.getHolderOrThrow(TreeConfiguredFeatures.HUGE_RED_MUSHROOM),
                            *arrayOfNulls<PlacementModifier>(0), *arrayOfNulls<PlacementModifier>(0)
                        ),
                        0.015f
                    ),
                    WeightedPlacedFeature(
                        placedFeatureProvider.getHolderOrThrow(DuskPlacedFeature.DARK_OAK_AUTUMN),
                        0.3f
                    ),
                    WeightedPlacedFeature(
                        placedFeatureProvider.getHolderOrThrow(DuskPlacedFeature.AUTUMN_TREE_CHECKED),
                        0.3f
                    ),
                    WeightedPlacedFeature(
                        placedFeatureProvider.getHolderOrThrow(DuskPlacedFeature.GOLDEN_BIRCH_TALL_CHECKED),
                        0.25f
                    ),
                    WeightedPlacedFeature(
                        placedFeatureProvider.getHolderOrThrow(TreePlacedFeatures.SPRUCE_CHECKED),
                        0.0075f
                    )
                ), PlacedFeatureUtil.placedInline(
                    configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeature.DISK_PODZOL),
                    *arrayOfNulls<PlacementModifier>(0), *arrayOfNulls<PlacementModifier>(0)
                )
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomFeatureConfig, Feature<RandomFeatureConfig>>(
            context, AUTUMN_PASTURES_VEGETATION,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf<WeightedPlacedFeature>(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatureProvider.getHolderOrThrow(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM),
                            *arrayOfNulls<PlacementModifier>(0)
                        ), 0.005f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatureProvider.getHolderOrThrow(TreeConfiguredFeatures.HUGE_RED_MUSHROOM),
                            *arrayOfNulls<PlacementModifier>(0), *arrayOfNulls<PlacementModifier>(0)
                        ),
                        0.015f
                    ),
                    WeightedPlacedFeature(
                        placedFeatureProvider.getHolderOrThrow(DuskPlacedFeature.DARK_OAK_AUTUMN),
                        0.3f
                    ),
                    WeightedPlacedFeature(
                        placedFeatureProvider.getHolderOrThrow(DuskPlacedFeature.AUTUMN_TREE_CHECKED),
                        0.3f
                    ),
                    WeightedPlacedFeature(
                        placedFeatureProvider.getHolderOrThrow(DuskPlacedFeature.GOLDEN_BIRCH_TALL_CHECKED),
                        0.25f
                    ),
                    WeightedPlacedFeature(
                        placedFeatureProvider.getHolderOrThrow(TreePlacedFeatures.SPRUCE_CHECKED),
                        0.0075f
                    )
                ), PlacedFeatureUtil.placedInline(
                    configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeature.DISK_PODZOL),
                    *arrayOfNulls<PlacementModifier>(0), *arrayOfNulls<PlacementModifier>(0)
                )
            )
        )
        val builder = DataPool.builder<BlockState>()

        for (i in 1..4) {
            val var35: Iterator<*> = Direction.Type.HORIZONTAL.iterator()

            while (var35.hasNext()) {
                val direction = var35.next() as Direction
                builder.add(
                    (DuskBlocks.BLUE_PETALS.defaultState.with(PinkPetalsBlock.AMOUNT, i) as BlockState).with(
                        PinkPetalsBlock.FACING,
                        direction
                    ) as BlockState, 1
                )
            }
        }
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            context, AUTUMN_WOODS_FLOWER, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2, PlacedFeatureUtil.onlyWhenEmpty<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(WeightedBlockStateProvider(builder))
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