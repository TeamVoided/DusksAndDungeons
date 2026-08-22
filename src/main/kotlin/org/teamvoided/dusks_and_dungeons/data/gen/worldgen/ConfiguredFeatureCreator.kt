package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

import com.google.common.collect.ImmutableList
import dev.worldgen.lithostitched.api.util.Weighted
import dev.worldgen.lithostitched.api.util.WeightedList
import dev.worldgen.lithostitched.api.worldgen.feature.LithostitchedFeatures
import dev.worldgen.lithostitched.worldgen.feature.config.WeightedSelectorConfig
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.features.TreeFeatures
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.util.valueproviders.BiasedToBottomInt
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
import net.minecraft.world.level.levelgen.feature.configurations.*
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.target
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter
import net.minecraft.world.level.levelgen.placement.CaveSurface
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.placement.PlacementModifier
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import net.minecraft.world.level.material.Fluids
import org.teamvoided.dusks_and_dungeons.block.HangingFloraBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.BLACKSTONE_BLOCKS
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.ROCKY_BLOCKS
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks.SLATE_BLOCKS
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDFeatures
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.BoulderConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FairyRingConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FarmlandConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.MushroomFeatureConfig
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.OvergrowthFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootConfig
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.AttachedToTrunkTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.BeehiveBigTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.FeatureAtBaseTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.FeatureOnLeavesTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.BentTrunkPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.ThreeWideTrunkPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.WallTrunkPlacer
import java.util.*

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object ConfiguredFeatureCreator {

    @Suppress("LongMethod")
    fun bootstrap(c: BootstrapContext<ConfiguredFeature<*, *>>) {
//        val blockTags = c.lookup(Registries.BLOCK)
        val cF = c.lookup(Registries.CONFIGURED_FEATURE)
        val pF = c.lookup(Registries.PLACED_FEATURE)

        //sort by folder structure in DnDConfiguredFeature
        c.trees()
        c.flowers()
        c.fairyRings()
        c.vegetation()
        c.pumpkinPatches()
        c.crops()
        c.disks()
        c.overlayOres()

        c.registerConfiguredFeature(
            DnDConfiguredFeature.OVERGROWN_COBBLESTONE_BOULDER,
            DnDFeatures.BOULDER,
            BoulderConfig(
                BlockStateProvider.simple(DnDBlocks.OVERGROWN_COBBLESTONE.get().defaultBlockState()),
                UniformInt.of(2, 5),
                UniformInt.of(1, 4),
                UniformInt.of(1, 2),
                UniformInt.of(2, 4)
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.AUTUMN_FARMLAND, DnDFeatures.FARMLAND, FarmlandConfig(
                BlockTags.DIRT,
                DnDBlockTags.FARMLAND_PLACES_UNDER,
                BlockStateProvider.simple(Blocks.FARMLAND.defaultBlockState().setValue(FarmBlock.MOISTURE, 7)),
                0.85f,
                BiasedToBottomInt.of(3, 16),
                4,
                BlockStateProvider.simple(Blocks.DARK_OAK_FENCE),
                0.9f,
                BiasedToBottomInt.of(1, 24),
                BlockStateProvider.simple(Blocks.WATER),
                0.9f,
                PlacementUtils.inlinePlaced(
                    cF.getOrThrow(DnDConfiguredFeature.AUTUMN_FARMLAND_CROPS),
                ),
                0.1f,
                true,
                listOf()
            )
        )

        c.registerConfiguredFeature(
            DnDConfiguredFeature.CRIMSON_WART_VEGETATION,
            Feature.VEGETATION_PATCH,
            VegetationPatchConfiguration(
                BlockTags.SCULK_REPLACEABLE,
                BlockStateProvider.simple(Blocks.SOUL_SAND),
                PlacementUtils.inlinePlaced(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(
                        WeightedStateProvider(
                            SimpleWeightedRandomList.builder<BlockState>()
                                .add(Blocks.NETHER_WART.defaultBlockState(), 3)
                                .add(Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 1), 2)
                                .add(Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 2))
                                .add(Blocks.NETHER_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 3))
                                .add(Blocks.SOUL_FIRE.defaultBlockState(), 4)
                        )
                    )
                ),
                CaveSurface.FLOOR,
                ConstantInt.of(2),
                0.3f,
                5,
                0.3f,
                UniformInt.of(4, 7),
                0.3f
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.WARPED_WART_VEGETATION,
            Feature.VEGETATION_PATCH,
            VegetationPatchConfiguration(
                BlockTags.SCULK_REPLACEABLE,
                BlockStateProvider.simple(Blocks.SOUL_SAND),
                PlacementUtils.inlinePlaced(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(
                        WeightedStateProvider(
                            SimpleWeightedRandomList.builder<BlockState>()
                                .add(DnDBlocks.WARPED_WART.defaultBlockState(), 3)
                                .add(DnDBlocks.WARPED_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 1), 2)
                                .add(DnDBlocks.WARPED_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 2))
                                .add(DnDBlocks.WARPED_WART.defaultBlockState().setValue(NetherWartBlock.AGE, 3))
                        )
                    )
                ),
                CaveSurface.CEILING,
                ConstantInt.of(2),
                0.3f,
                5,
                0.3f,
                UniformInt.of(3, 5),
                0.3f
            )
        )

        c.registerConfiguredFeature(
            DnDConfiguredFeature.HUGE_GOLDEN_MUSHROOM,
            DnDFeatures.HUGE_GOLDEN_MUSHROOM,
            MushroomFeatureConfig(
                DnDBlockTags.VEGETATION_REPLACEABLE,
                DnDBlockTags.VEGETATION_REPLACEABLE,
                BlockStateProvider.simple(DnDBlocks.GOLDEN_MUSHROOM_STEM_BLOCK),
                BiasedToBottomInt.of(3, 6),
                BlockStateProvider.simple(DnDBlocks.GOLDEN_MUSHROOM_BLOCK),
                BiasedToBottomInt.of(1, 7),
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.PATCH_GOLDEN_MUSHROOM,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                SimpleBlockConfiguration(BlockStateProvider.simple(DnDBlocks.GOLDEN_MUSHROOM)), listOf(), 32
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.PATCH_GOLDEN_MUSHROOM_WITH_HUGE,
            Feature.RANDOM_PATCH,
            RandomPatchConfiguration(
                64, 7, 3,
                PlacementUtils.filtered(
                    Feature.RANDOM_SELECTOR,
                    RandomFeatureConfiguration(
                        listOf(
                            WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(cF.getOrThrow(DnDConfiguredFeature.HUGE_GOLDEN_MUSHROOM)),
                                0.1f
                            )
                        ),
                        PlacementUtils.inlinePlaced(
                            Feature.SIMPLE_BLOCK,
                            SimpleBlockConfiguration(BlockStateProvider.simple(DnDBlocks.GOLDEN_MUSHROOM))
                        )
                    ),
                    BlockPredicate.allOf(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                        BlockPredicate.wouldSurvive(DnDBlocks.GOLDEN_MUSHROOM.defaultBlockState(), BlockPos.ZERO)
                    )
                )
            )
        )

        c.registerConfiguredFeature(
            DnDConfiguredFeature.OVERGROWTH_FLOOR_V,
            Feature.SIMPLE_BLOCK,
            SimpleBlockConfiguration(
                WeightedStateProvider(
                    SimpleWeightedRandomList.builder<BlockState>()
                        .add(DnDBlocks.OVERGROWTH_BUSH.defaultBlockState(), 2)
                        .add(DnDBlocks.OVERGROWTH_CARPET.defaultBlockState(), 5)
                        .add(Blocks.SHORT_GRASS.defaultBlockState(), 10)
                        .add(Blocks.TALL_GRASS.defaultBlockState(), 2)
                )
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.OVERGROWTH_CEILING_V,
            Feature.SIMPLE_BLOCK,
            SimpleBlockConfiguration(
                WeightedStateProvider(
                    SimpleWeightedRandomList.builder<BlockState>()
                        .add(
                            DnDBlocks.OVERGROWTH_BUSH.defaultBlockState()
                                .setValue(BlockStateProperties.FACING, Direction.UP), 1
                        )
                        .add(DnDBlocks.OVERGROWTH_BLOCK.defaultBlockState(), 10)
                )
            )
        )
        c.overgrowthPatch(DnDConfiguredFeature.OVERGROWTH_PATCH_FLOOR, CaveSurface.FLOOR)
        c.overgrowthPatch(DnDConfiguredFeature.OVERGROWTH_PATCH_FLOOR_B, CaveSurface.FLOOR, true)
        c.overgrowthPatch(DnDConfiguredFeature.OVERGROWTH_PATCH_CEILING, CaveSurface.CEILING)
        c.overgrowthPatch(DnDConfiguredFeature.OVERGROWTH_PATCH_CEILING_B, CaveSurface.CEILING, true)

        c.registerConfiguredFeature(
            DnDConfiguredFeature.OVERGROWTH_CARPET_PATCH,
            Feature.RANDOM_PATCH,
            RandomPatchConfiguration(
                30, 2, 3,
                PlacementUtils.inlinePlaced(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(BlockStateProvider.simple(DnDBlocks.OVERGROWTH_CARPET)),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(
                            BlockPredicate.ONLY_IN_AIR_PREDICATE,
                            BlockPredicate.hasSturdyFace(Direction.DOWN.normal, Direction.UP)
                        )
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DnDConfiguredFeature.OVERGROWTH_HANGING,
            Feature.BLOCK_COLUMN,
            BlockColumnConfiguration(
                listOf(
                    BlockColumnConfiguration.layer(
                        BiasedToBottomInt.of(0, 5),
                        BlockStateProvider.simple(DnDBlocks.HANGING_OVERGROWTH)
                    ),
                    BlockColumnConfiguration.layer(
                        ConstantInt.of(1),
                        BlockStateProvider.simple(
                            DnDBlocks.HANGING_OVERGROWTH.defaultBlockState().setValue(HangingFloraBlock.TIP, true)
                        )
                    )
                ),
                Direction.DOWN,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                true
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.trees() {
        val blockTags = this.lookup(Registries.BLOCK)

        val cascadeTree = TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(DnDBlocks.CASCADE_LOG),
            ThreeWideTrunkPlacer(9, 2, 1),
            BlockStateProvider.simple(DnDBlocks.CASCADE_LEAVES),
            CascadeFoliagePlacer(
                ConstantInt.of(3),
                ConstantInt.of(0),
                ConstantInt.of(2),
                100
            ),
            Optional.of(
                CascadeRootPlacer(
                    BiasedToBottomInt.of(0, 2),
                    BlockStateProvider.simple(DnDBlocks.CASCADE_LOG),
                    Optional.empty(),
                    CascadeRootConfig(
                        blockTags.getOrThrow(BlockTags.REPLACEABLE_BY_TREES),
                        3,
                        BiasedToBottomInt.of(1, 3),
                        6,
                    )
                )
            ),
            ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
        )
        val sypiaTree = treeBuilder(DnDBlocks.SYPIA_LOG, DnDBlocks.SYPIA_LEAVES, 5, 2, 6, 2)
        val sypiaDecorator1 = AttachedToTrunkTreeDecorator(
            0.14f,
            1,
            1,
            BlockStateProvider.simple(
                DnDBlocks.SYPIA_LOG.defaultBlockState().setValue(
                    RotatedPillarBlock.AXIS,
                    Direction.Axis.X
                )
            ),
            2,
            listOf(Direction.EAST, Direction.WEST)
        )
        val sypiaDecorator2 = AttachedToTrunkTreeDecorator(
            0.14f,
            1,
            1,
            BlockStateProvider.simple(
                DnDBlocks.SYPIA_LOG.defaultBlockState().setValue(
                    RotatedPillarBlock.AXIS,
                    Direction.Axis.Z
                )
            ),
            2,
            listOf(Direction.NORTH, Direction.SOUTH)
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CASCADE_TREE, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(leafPiles(DnDBlocks.CASCADE_LEAF_PILE, blockTags))
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CASCADE_TREE_BEES, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(BeehiveBigTreeDecorator(0.1F), leafPiles(DnDBlocks.CASCADE_LEAF_PILE, blockTags))
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.SYPIA_TALL, Feature.TREE, sypiaTree.ignoreVines().decorators(
                ImmutableList.of(
                    sypiaDecorator1,
                    sypiaDecorator2,
                    leafPiles(DnDBlocks.SYPIA_LEAF_PILE, blockTags)
                )
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.SYPIA_TALL_BEES, Feature.TREE, sypiaTree.ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.1F),
                    sypiaDecorator1,
                    sypiaDecorator2,
                    leafPiles(DnDBlocks.SYPIA_LEAF_PILE, blockTags)
                )
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CASCADE_TREE_AUTUMN, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.02F),
//                    AlterGroundRadiusTreeDecorator(
//                        BlockStateProvider.of(Blocks.PODZOL), 2, 5,
//                        blockTags.getTagOrThrow(BlockTags.DIRT)
//                    ),
                    leafPiles(DnDBlocks.CASCADE_LEAF_PILE, blockTags)
                )
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.SYPIA_TALL_AUTUMN, Feature.TREE, sypiaTree.ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.02F),
//                    AlterGroundRadiusTreeDecorator(
//                        BlockStateProvider.of(Blocks.PODZOL), 2, 20,
//                        blockTags.getTagOrThrow(BlockTags.DIRT)
//                    ),
                    sypiaDecorator1,
                    sypiaDecorator2,
                    leafPiles(DnDBlocks.SYPIA_LEAF_PILE, blockTags)
                )
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.DARK_OAK_AUTUMN, Feature.TREE, TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                DarkOakTrunkPlacer(6, 3, 1),
                BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())
            )
                .forceDirt().ignoreVines().decorators(
                    ImmutableList.of(
                        BeehiveBigTreeDecorator(0.02F),
//                        AlterGroundRadiusTreeDecorator(
//                            BlockStateProvider.of(Blocks.PODZOL), 2, 5,
//                            blockTags.getTagOrThrow(BlockTags.DIRT)
//                        ),
                        leafPiles(DnDBlocks.DARK_OAK_LEAF_PILE, blockTags)
                    )
                ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.ACACIA_AUTUMN, Feature.TREE, TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.ACACIA_LOG),
                StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(Blocks.ACACIA_LEAVES),
                BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                TwoLayersFeatureSize(1, 0, 1)
            )
                .forceDirt().ignoreVines().decorators(
                    ImmutableList.of<TreeDecorator>(leafPiles(DnDBlocks.ACACIA_LEAF_PILE, blockTags))
                ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.ACACIA_BUSH_AUTUMN, Feature.TREE,
            TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.ACACIA_LOG),
                StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.simple(Blocks.ACACIA_LEAVES),
                AcaciaFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 1)),
                TwoLayersFeatureSize(0, 0, 0)
            ).build()
        )

        this.overgrowthTree(DnDConfiguredFeature.OVERGROWTH_TREE_DOWN, Direction.DOWN)
        this.overgrowthTree(DnDConfiguredFeature.OVERGROWTH_TREE_NORTH, Direction.NORTH)
        this.overgrowthTree(DnDConfiguredFeature.OVERGROWTH_TREE_SOUTH, Direction.SOUTH)
        this.overgrowthTree(DnDConfiguredFeature.OVERGROWTH_TREE_EAST, Direction.EAST)
        this.overgrowthTree(DnDConfiguredFeature.OVERGROWTH_TREE_WEST, Direction.WEST)
        this.registerConfiguredFeature(
            DnDConfiguredFeature.OVERGROWTH_TREE_ROOTED,
            Feature.ROOT_SYSTEM,
            RootSystemConfiguration(
                PlacementUtils.inlinePlaced(
                    this.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(DnDConfiguredFeature.OVERGROWTH_TREE_DOWN)
                ),
                3,
                3,
                BlockTags.AZALEA_ROOT_REPLACEABLE,
                BlockStateProvider.simple(Blocks.ROOTED_DIRT),
                20,
                100,
                3,
                2,
                BlockStateProvider.simple(Blocks.HANGING_ROOTS),
                20,
                2,
                BlockPredicate.allOf(
                    BlockPredicate.matchesTag(DnDBlockTags.VEGETATION_REPLACEABLE),
                    BlockPredicate.matchesTag(Direction.DOWN.normal, BlockTags.AZALEA_GROWS_ON)
                )
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.flowers() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.FLOWER_AUTUMN, Feature.FLOWER, FeatureUtils.simpleRandomPatchConfiguration(
                64,
                PlacementUtils.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(
                        WeightedStateProvider(
                            SimpleWeightedRandomList.builder<BlockState>()
                                .add(Blocks.CORNFLOWER.defaultBlockState(), 5)
                                .add(Blocks.POPPY.defaultBlockState(), 5)
                                .add(DnDBlocks.CASCADE_SAPLING.defaultBlockState(), 1)
                        )
                    )
                )
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.PATCH_ROSEBUSH,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.ROSE_BUSH))
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.BLUE_PETALS, Feature.FLOWER, RandomPatchConfiguration(
                96, 6, 2,
                PlacementUtils.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(WeightedStateProvider(petalBuilder(DnDBlocks.BLUE_PETALS)))
                )
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.fairyRings() {
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_WHITE, DnDBlocks.WHITE_PETALS)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_RED, DnDBlocks.RED_PETALS)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_BLUE, DnDBlocks.BLUE_PETALS)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_ORANGE, DnDBlocks.ORANGE_PETALS)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_COLD_WILDFLOWER, DnDBlocks.COLD_WILDFLOWER)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_CRIMSON, DnDBlocks.CRIMSON_VIVIONS, 5)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_WARPED, DnDBlocks.WARPED_VIVIONS, 5)
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.vegetation() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.AUTUMN_WOODS_VEGETATION,
            LithostitchedFeatures.WEIGHTED_SELECTOR,
            WeightedSelectorConfig(
                WeightedList.builder<Holder<PlacedFeature>>()
                    .add(this, TreeFeatures.HUGE_BROWN_MUSHROOM)
                    .add(this, TreeFeatures.HUGE_RED_MUSHROOM)
                    .add(this, DnDPlacedFeature.DARK_OAK_AUTUMN, 5)
                    .add(this, DnDPlacedFeature.CASCADE_TREE_AUTUMN, 5)
                    .add(this, DnDPlacedFeature.SYPIA_TALL_AUTUMN, 15)
                    .build()
            )
        )
        this.weightedFeature(
            DnDConfiguredFeature.AUTUMN_PASTURES_VEGETATION,
            (DnDPlacedFeature.ACACIA_AUTUMN to 50),
            (DnDPlacedFeature.ACACIA_BUSH_AUTUMN to 30),
            (DnDPlacedFeature.SYPIA_TALL_AUTUMN to 7),
            (DnDPlacedFeature.CASCADE_TREE_AUTUMN to 1)
        )
        this.weightedFeature(
            DnDConfiguredFeature.GOLDEN_VEGETATION,
            (DnDPlacedFeature.SYPIA_TALL to 2),
            (DnDPlacedFeature.SYPIA_TALL_BEES to 1)
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.pumpkinPatches() {
        this.pumpkinPatch(DnDConfiguredFeature.PATCH_PUMPKIN_LANTERN, DnDBlocks.LANTERN_PUMPKIN)
        this.pumpkinPatch(DnDConfiguredFeature.PATCH_PUMPKIN_MOSSKIN, DnDBlocks.MOSSKIN_PUMPKIN)
        this.pumpkinPatch(DnDConfiguredFeature.PATCH_PUMPKIN_PALE, DnDBlocks.PALE_PUMPKIN)
        this.pumpkinPatch(DnDConfiguredFeature.PATCH_PUMPKIN_GLOOM, DnDBlocks.GLOOM_PUMPKIN)
        this.pumpkinPatch(
            DnDConfiguredFeature.PATCH_PUMPKIN_EXTRA,
            addPumpkins(Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN)
        )
        this.pumpkinPatch(
            DnDConfiguredFeature.PATCH_PUMPKIN_LANTERN_EXTRA,
            addPumpkins(DnDBlocks.LANTERN_PUMPKIN, DnDBlocks.CARVED_LANTERN_PUMPKIN, DnDBlocks.GLOWING_LANTERN_PUMPKIN)
        )
        this.pumpkinPatch(
            DnDConfiguredFeature.PATCH_PUMPKIN_MOSSKIN_EXTRA,
            addPumpkins(DnDBlocks.MOSSKIN_PUMPKIN, DnDBlocks.CARVED_MOSSKIN_PUMPKIN, DnDBlocks.GLOWING_MOSSKIN_PUMPKIN)
        )
        this.pumpkinPatch(
            DnDConfiguredFeature.PATCH_PUMPKIN_PALE_EXTRA,
            addPumpkins(DnDBlocks.PALE_PUMPKIN, DnDBlocks.CARVED_PALE_PUMPKIN, DnDBlocks.GLOWING_PALE_PUMPKIN)
        )
        this.pumpkinPatch(
            DnDConfiguredFeature.PATCH_PUMPKIN_GLOOM_EXTRA,
            addPumpkins(DnDBlocks.GLOOM_PUMPKIN, DnDBlocks.CARVED_GLOOM_PUMPKIN, DnDBlocks.GLOWING_GLOOM_PUMPKIN)
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.crops() {
        val configuredFeatures = this.lookup(Registries.CONFIGURED_FEATURE)
        this.registerConfiguredFeature(
            DnDConfiguredFeature.AUTUMN_FARMLAND_CROPS, Feature.RANDOM_SELECTOR, RandomFeatureConfiguration(
                listOf(
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(
                            configuredFeatures.getOrThrow(DnDConfiguredFeature.CROPS_WHEAT),
                        ), 0.25f
                    ),
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(
                            configuredFeatures.getOrThrow(DnDConfiguredFeature.CROPS_CARROTS),
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(
                            configuredFeatures.getOrThrow(DnDConfiguredFeature.CROPS_POTATOES)
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(
                            configuredFeatures.getOrThrow(DnDConfiguredFeature.CROPS_PUMPKIN),
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(
                            configuredFeatures.getOrThrow(DnDConfiguredFeature.CROPS_BEETROOTS),
                        ), 0.175f
                    ),
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(
                            configuredFeatures.getOrThrow(DnDConfiguredFeature.CROPS_GOLDEN_BEETROOTS),
                        ), 0.05f
                    )
                ), PlacementUtils.inlinePlaced(
                    configuredFeatures.getOrThrow(DnDConfiguredFeature.CROPS_WILD_WHEAT),
                )
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_WILD_WHEAT,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                SimpleBlockConfiguration(
                    BlockStateProvider.simple(DnDBlocks.WILD_WHEAT.defaultBlockState())
                ), ImmutableList.of(Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.FARMLAND), 32
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_WHEAT,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(basicCropAges(Blocks.WHEAT)),
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_CARROTS,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(basicCropAges(Blocks.CARROTS))
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_POTATOES,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(basicCropAges(Blocks.POTATOES))
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_PUMPKIN,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(basicCropAges(Blocks.PUMPKIN_STEM))
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_BEETROOTS,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(
                    WeightedStateProvider(
                        SimpleWeightedRandomList.builder<BlockState>()
                            .add(Blocks.BEETROOTS.defaultBlockState(), 3)
                            .add(Blocks.BEETROOTS.defaultBlockState().setValue(BeetrootBlock.AGE, 2), 2)
                            .add(Blocks.BEETROOTS.defaultBlockState().setValue(BeetrootBlock.AGE, 3), 1)
                    )
                )
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_GOLDEN_BEETROOTS,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(
                    WeightedStateProvider(
                        SimpleWeightedRandomList.builder<BlockState>()
                            .add(DnDBlocks.GOLDEN_BEETROOTS.defaultBlockState(), 1)
                            .add(DnDBlocks.GOLDEN_BEETROOTS.defaultBlockState().setValue(BeetrootBlock.AGE, 2), 1)
                            .add(Blocks.BEETROOTS.defaultBlockState().setValue(BeetrootBlock.AGE, 3), 1)
                    )
                )
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.disks() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.DISK_PODZOL, Feature.DISK, DiskConfiguration(
                RuleBasedBlockStateProvider(
                    BlockStateProvider.simple(Blocks.DIRT), listOf(
                        RuleBasedBlockStateProvider.Rule(
                            BlockPredicate.not(
                                BlockPredicate.anyOf(
                                    BlockPredicate.solid(Direction.UP.normal),
                                    BlockPredicate.matchesFluids(Direction.UP.normal, Fluids.WATER)
                                )
                            ), BlockStateProvider.simple(Blocks.PODZOL)
                        )
                    )
                ),
                BlockPredicate.matchesBlocks(listOf(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.STONE)),
                UniformInt.of(2, 6), 2
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.DISK_MUD, Feature.DISK, DiskConfiguration(
                RuleBasedBlockStateProvider.simple(Blocks.MUD), BlockPredicate.matchesBlocks(
                    listOf(
                        Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.MYCELIUM,
                        Blocks.PODZOL, Blocks.GRAVEL, Blocks.SAND, Blocks.MUD
                    )
                ), UniformInt.of(2, 6), 1
            )
        )

        this.registerConfiguredFeature(
            DnDConfiguredFeature.DISK_RED_SAND, Feature.DISK, DiskConfiguration(
                RuleBasedBlockStateProvider(
                    BlockStateProvider.simple(Blocks.RED_SAND), listOf(
                        RuleBasedBlockStateProvider.Rule(
                            BlockPredicate.matchesBlocks(Direction.DOWN.normal, Blocks.AIR),
                            BlockStateProvider.simple(Blocks.RED_SANDSTONE)
                        )
                    )
                ), BlockPredicate.matchesBlocks(
                    listOf(
                        Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.MYCELIUM,
                        Blocks.PODZOL, Blocks.GRAVEL, Blocks.SAND, Blocks.MUD
                    )
                ), UniformInt.of(2, 6), 2
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.overlayOres() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.ROCKY_OVERWORLD_ORE, Feature.ORE,
            OreConfiguration(
                listOf(
                    target(TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ROCKY_BLOCKS.dirt.defaultBlockState()),
                    target(BlockMatchTest(Blocks.DIRT), ROCKY_BLOCKS.dirt.defaultBlockState()),
                    target(BlockMatchTest(Blocks.GRASS_BLOCK), ROCKY_BLOCKS.grass.defaultBlockState()),
                    target(BlockMatchTest(Blocks.PODZOL), ROCKY_BLOCKS.podzol.defaultBlockState()),
                    target(BlockMatchTest(Blocks.GRAVEL), ROCKY_BLOCKS.gravel.defaultBlockState()),
                    target(BlockMatchTest(Blocks.DIRT_PATH), ROCKY_BLOCKS.path.defaultBlockState())
                ), 33
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.SLATED_OVERWORLD_ORE,
            Feature.ORE,
            OreConfiguration(
                listOf<OreConfiguration.TargetBlockState>(
                    target(
                        TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), SLATE_BLOCKS.dirt.defaultBlockState()
                    ),
                    target(BlockMatchTest(Blocks.GRAVEL), SLATE_BLOCKS.gravel.defaultBlockState()),
                ), 33
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.BLACKSTONE_NETHER_ORE,
            Feature.ORE,
            OreConfiguration(
                listOf<OreConfiguration.TargetBlockState>(
                    target(BlockMatchTest(Blocks.NETHERRACK), BLACKSTONE_BLOCKS.soulSand.defaultBlockState()),
                    target(BlockMatchTest(Blocks.SOUL_SAND), BLACKSTONE_BLOCKS.soulSand.defaultBlockState()),
                    target(BlockMatchTest(Blocks.SOUL_SOIL), BLACKSTONE_BLOCKS.soulSoil.defaultBlockState()),
                ), 33
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.overgrowthPatch(
        feature: ResourceKey<ConfiguredFeature<*, *>>,
        surface: CaveSurface,
        bonemeal: Boolean = false
    ) {
        this.registerConfiguredFeature(
            feature,
            Feature.VEGETATION_PATCH,
            VegetationPatchConfiguration(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.simple(DnDBlocks.OVERGROWTH_BLOCK),
                PlacementUtils.inlinePlaced(
                    this.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(
                        if (surface.ordinal == 1) DnDConfiguredFeature.OVERGROWTH_FLOOR_V
                        else DnDConfiguredFeature.OVERGROWTH_CEILING_V
                    ),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                surface,
                ConstantInt.of(1),
                0f,
                5,
                if (bonemeal) 0.6f else 0.8f,
                if (bonemeal) UniformInt.of(1, 2) else UniformInt.of(4, 7),
                if (bonemeal) 0.75f else 0.3f
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.overgrowthTree(
        cf: ResourceKey<ConfiguredFeature<*, *>>,
        dir: Direction
    ) {
        val trunk =
            if (dir.axis == Direction.Axis.Y)
                BentTrunkPlacer(5, 5, 0, 0.7f, UniformInt.of(1, 3), UniformInt.of(2, 4))
            else
                WallTrunkPlacer(5, 5, 0, 0.7f, UniformInt.of(1, 3), dir)

        val decorators = ImmutableList.of<TreeDecorator>(
            FeatureAtBaseTreeDecorator(
                PlacementUtils.inlinePlaced(
                    this.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(DnDConfiguredFeature.OVERGROWTH_CARPET_PATCH)
                ),
            ),
            FeatureOnLeavesTreeDecorator(
                PlacementUtils.inlinePlaced(
                    this.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(DnDConfiguredFeature.OVERGROWTH_HANGING)
                ),
                0.3f,
                2,
                listOf(Direction.DOWN)
            )
        )

        this.registerConfiguredFeature(
            cf, Feature.TREE, TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(DnDBlocks.VERDANT_LOG),
                trunk,
                BlockStateProvider.simple(DnDBlocks.VERDANT_LEAVES),
                OvergrowthFoliagePlacer(BiasedToBottomInt.of(2, 3), ConstantInt.of(0)),
                TwoLayersFeatureSize(1, 0, 1)
            ).dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).forceDirt().ignoreVines().decorators(decorators)
                .build()
        )
    }

    fun treeBuilder(
        trunk: Block, foliage: Block, baseHeight: Int, firstRandomHeight: Int,
        secondRandomHeight: Int, foliageRadius: Int,
    ): TreeConfiguration.TreeConfigurationBuilder {
        return TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(trunk),
            StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight),
            BlockStateProvider.simple(foliage),
            BlobFoliagePlacer(ConstantInt.of(foliageRadius), ConstantInt.of(0), 3),
            TwoLayersFeatureSize(1, 0, 1)
        )
    }


    fun BootstrapContext<ConfiguredFeature<*, *>>.weightedFeature(
        feature: ResourceKey<ConfiguredFeature<*, *>>,
        vararg places: Pair<ResourceKey<PlacedFeature>, Int>
    ) {
        val placedFeatures = this.lookup(Registries.PLACED_FEATURE)
        val weightedList = WeightedList.builder<Holder<PlacedFeature>>()
        places.forEach { weightedList.add(placedFeatures.getOrThrow(it.first), it.second) }
        this.registerConfiguredFeature(
            feature,
            LithostitchedFeatures.WEIGHTED_SELECTOR,
            WeightedSelectorConfig(weightedList.build())
        )
    }

    fun WeightedList.Builder<Holder<PlacedFeature>>.add(
        c: BootstrapContext<ConfiguredFeature<*, *>>,
        entry: ResourceKey<ConfiguredFeature<*, *>>,
        int: Int = 1
    ): WeightedList.Builder<Holder<PlacedFeature>> {
        this.add(PlacementUtils.inlinePlaced(c.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(entry)), int)
        return this
    }

    fun WeightedList.Builder<Holder<PlacedFeature>>.add(
        c: BootstrapContext<ConfiguredFeature<*, *>>,
        entry: ResourceKey<PlacedFeature>,
        int: Int = 1
    ): WeightedList.Builder<Holder<PlacedFeature>> {
        this.add(c.lookup(Registries.PLACED_FEATURE).getOrThrow(entry), int)
        return this
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.fairyRing(
        feature: ResourceKey<ConfiguredFeature<*, *>>,
        block: Block,
        verticalRange: Int = 3,
    ) {
        this.registerConfiguredFeature(
            feature,
            DnDFeatures.FAIRY_RING,
            FairyRingConfig(
                BlockStateProvider.simple(block.defaultBlockState()),
                DnDBlockTags.VEGETATION_REPLACEABLE,
                verticalRange
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.pumpkinPatch(
        feature: ResourceKey<ConfiguredFeature<*, *>>,
        block: BlockStateProvider,
    ) {
        this.registerConfiguredFeature(
            feature,
            Feature.RANDOM_PATCH,
            createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                SimpleBlockConfiguration(block),
                DnDBlockTags.PUMPKIN_PATCH_PLACE_ON
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.pumpkinPatch(
        feature: ResourceKey<ConfiguredFeature<*, *>>,
        block: Block,
    ) {
        this.pumpkinPatch(feature, BlockStateProvider.simple(block))
    }

    private fun <FC : FeatureConfiguration, F : Feature<FC>> BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: ResourceKey<ConfiguredFeature<*, *>>,
        feature: F,
        featureConfig: FC,
    ): Any = this.register(registryKey, ConfiguredFeature(feature, featureConfig))

    @Suppress("unused")
    private fun BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: ResourceKey<ConfiguredFeature<*, *>>, feature: Feature<NoneFeatureConfiguration>,
    ) = this.registerConfiguredFeature(registryKey, feature, FeatureConfiguration.NONE)
}
