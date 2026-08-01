package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.core.Direction
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
import net.minecraft.world.level.block.BeetrootBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FarmBlock
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
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
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import net.minecraft.world.level.material.Fluids
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
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FallenTreeConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FarmlandConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.MushroomFeatureConfig
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootConfig
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.AttachedToTrunkTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.BeehiveBigTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.ThreeWideTrunkPlacer
import java.util.*

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object ConfiguredFeatureCreator {

    @Suppress("LongMethod")
    fun bootstrap(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = c.lookup(Registries.BLOCK)
        val configuredFeatures = c.lookup(Registries.CONFIGURED_FEATURE)
        val placedFeatures = c.lookup(Registries.PLACED_FEATURE)

        //sort by folder structure in DnDConfiguredFeature
        c.trees()
        c.fallenTrees()
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
                    configuredFeatures.getOrThrow(DnDConfiguredFeature.AUTUMN_FARMLAND_CROPS),

                    ),
                0.1f,
                true,
                listOf()
            )
        )

        c.registerConfiguredFeature(
            DnDConfiguredFeature.HUGE_GOLD_MUSHROOM,
            DnDFeatures.HUGE_GOLDEN_MUSHROOM,
            MushroomFeatureConfig(
                BlockTags.REPLACEABLE,
                BlockTags.REPLACEABLE,
                BlockStateProvider.simple(DnDBlocks.GOLDEN_MUSHROOM_STEM_BLOCK),
                BiasedToBottomInt.of(3, 6),
                BlockStateProvider.simple(DnDBlocks.GOLDEN_MUSHROOM_BLOCK),
                BiasedToBottomInt.of(1, 7),
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
        val goldenBirchTree = treeBuilder(Blocks.BIRCH_LOG, DnDBlocks.GOLDEN_BIRCH_LEAVES, 5, 2, 6, 2)
        val birchDecorator1 = AttachedToTrunkTreeDecorator(
            0.14f,
            1,
            1,
            BlockStateProvider.simple(
                Blocks.BIRCH_LOG.defaultBlockState().setValue(
                    RotatedPillarBlock.AXIS,
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
            BlockStateProvider.simple(
                Blocks.BIRCH_LOG.defaultBlockState().setValue(
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
            DnDConfiguredFeature.GOLDEN_BIRCH_TALL, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DnDBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.GOLDEN_BIRCH_TALL_BEES, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.1F),
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DnDBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
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
            DnDConfiguredFeature.GOLDEN_BIRCH_TALL_AUTUMN, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.02F),
//                    AlterGroundRadiusTreeDecorator(
//                        BlockStateProvider.of(Blocks.PODZOL), 2, 20,
//                        blockTags.getTagOrThrow(BlockTags.DIRT)
//                    ),
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DnDBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
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
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.fallenTrees() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.OAK_FALLEN_TREE, DnDFeatures.FALLEN_TREE, FallenTreeConfig(
                BlockStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
                BlockStateProvider.simple(DnDBlocks.HOLLOW_OAK_LOG.defaultBlockState())
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
        val configuredFeatures = this.lookup(Registries.CONFIGURED_FEATURE)
        val placedFeatures = this.lookup(Registries.PLACED_FEATURE)

        this.registerConfiguredFeature(
            DnDConfiguredFeature.AUTUMN_WOODS_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfiguration(
                listOf(
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(
                            configuredFeatures.getOrThrow(TreeFeatures.HUGE_BROWN_MUSHROOM),
                            *arrayOfNulls(0)
                        ), 0.0025f
                    ),
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(
                            configuredFeatures.getOrThrow(TreeFeatures.HUGE_RED_MUSHROOM),
                            *arrayOfNulls(0)
                        ), 0.005f
                    ),
                    WeightedPlacedFeature(placedFeatures.getOrThrow(DnDPlacedFeature.DARK_OAK_AUTUMN), 0.425f),
                    WeightedPlacedFeature(placedFeatures.getOrThrow(DnDPlacedFeature.CASCADE_TREE_AUTUMN), 0.425f)
                ), placedFeatures.getOrThrow(DnDPlacedFeature.GOLDEN_BIRCH_TALL_AUTUMN)
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.AUTUMN_PASTURES_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfiguration(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getOrThrow(DnDPlacedFeature.ACACIA_BUSH_AUTUMN), 0.3f),
                    WeightedPlacedFeature(
                        placedFeatures.getOrThrow(DnDPlacedFeature.GOLDEN_BIRCH_TALL_AUTUMN),
                        0.05f
                    ),
                    WeightedPlacedFeature(placedFeatures.getOrThrow(DnDPlacedFeature.CASCADE_TREE_AUTUMN), 0.01f)
                ), placedFeatures.getOrThrow(DnDPlacedFeature.ACACIA_AUTUMN)
            )
        )

        this.registerConfiguredFeature(
            DnDConfiguredFeature.GOLDEN_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfiguration(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getOrThrow(DnDPlacedFeature.GOLDEN_BIRCH_TALL), 0.5f)
                ), placedFeatures.getOrThrow(DnDPlacedFeature.GOLDEN_BIRCH_TALL_BEES)
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
                DnDBlockTags.FALLEN_TREE_REPLACEABLE,
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
