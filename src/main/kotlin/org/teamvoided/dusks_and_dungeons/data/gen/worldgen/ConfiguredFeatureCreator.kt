package org.teamvoided.dusks_and_dungeons.data.gen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.*
import net.minecraft.fluid.Fluids
import net.minecraft.registry.BootstrapContext
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
import org.teamvoided.dusks_and_dungeons.data.gen.worldgen.ConfiguredFeatureCreator.fairyRings
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDConfiguredFeature
import org.teamvoided.dusks_and_dungeons.data.worldgen.DnDPlacedFeature
import org.teamvoided.dusks_and_dungeons.init.DnDBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDOverlayBlocks
import org.teamvoided.dusks_and_dungeons.init.blocks.DnDWoodBlocks
import org.teamvoided.dusks_and_dungeons.init.worldgen.DnDFeatures
import org.teamvoided.dusks_and_dungeons.util.datagen.*
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.BoulderConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FairyRingConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FallenTreeConfig
import org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config.FarmlandConfig
import org.teamvoided.dusks_and_dungeons.world.gen.foliage.CascadeFoliagePlacer
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootConfig
import org.teamvoided.dusks_and_dungeons.world.gen.root.CascadeRootPlacer
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.AlterGroundRadiusTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.AttachedToTrunkTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.BeehiveBigTreeDecorator
import org.teamvoided.dusks_and_dungeons.world.gen.trunk.ThreeWideTrunkPlacer
import java.util.*

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object ConfiguredFeatureCreator {

    @Suppress("LongMethod")
    fun bootstrap(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = c.getRegistryLookup(RegistryKeys.BLOCK)
        val configuredFeatures = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures = c.getRegistryLookup(RegistryKeys.PLACED_FEATURE)

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
                BlockStateProvider.of(DnDBlocks.OVERGROWN_COBBLESTONE.get().defaultState),
                UniformIntProvider.create(2, 5),
                UniformIntProvider.create(1, 4),
                UniformIntProvider.create(1, 2),
                UniformIntProvider.create(2, 4)
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
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.trees() {
        val blockTags = this.getRegistryLookup(RegistryKeys.BLOCK)

        val cascadeTree = TreeFeatureConfig.Builder(
            BlockStateProvider.of(DnDWoodBlocks.CASCADE_LOG),
            ThreeWideTrunkPlacer(9, 2, 1),
            BlockStateProvider.of(DnDWoodBlocks.CASCADE_LEAVES),
            CascadeFoliagePlacer(
                ConstantIntProvider.create(3),
                ConstantIntProvider.create(0),
                ConstantIntProvider.create(2),
                100
            ),
            Optional.of(
                CascadeRootPlacer(
                    BiasedToBottomIntProvider.create(0, 2),
                    BlockStateProvider.of(DnDWoodBlocks.CASCADE_LOG),
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
        val goldenBirchTree = treeBuilder(Blocks.BIRCH_LOG, DnDWoodBlocks.GOLDEN_BIRCH_LEAVES, 5, 2, 6, 2)
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
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CASCADE_TREE, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(leafPiles(DnDWoodBlocks.CASCADE_LEAF_PILE, blockTags))
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CASCADE_TREE_BEES, Feature.TREE, cascadeTree.forceDirt().ignoreVines().decorators(
                ImmutableList.of(BeehiveBigTreeDecorator(0.1F), leafPiles(DnDWoodBlocks.CASCADE_LEAF_PILE, blockTags))
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.GOLDEN_BIRCH_TALL, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DnDWoodBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.GOLDEN_BIRCH_TALL_BEES, Feature.TREE, goldenBirchTree.ignoreVines().decorators(
                ImmutableList.of(
                    BeehiveBigTreeDecorator(0.1F),
                    birchDecorator1,
                    birchDecorator2,
                    leafPiles(DnDWoodBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
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
                    leafPiles(DnDWoodBlocks.CASCADE_LEAF_PILE, blockTags)
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
                    leafPiles(DnDWoodBlocks.GOLDEN_BIRCH_LEAF_PILE, blockTags)
                )
            ).build()
        )
        this.registerConfiguredFeature(
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
//                        AlterGroundRadiusTreeDecorator(
//                            BlockStateProvider.of(Blocks.PODZOL), 2, 5,
//                            blockTags.getTagOrThrow(BlockTags.DIRT)
//                        ),
                        leafPiles(DnDWoodBlocks.DARK_OAK_LEAF_PILE, blockTags)
                    )
                ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.ACACIA_AUTUMN, Feature.TREE, TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG),
                StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.of(Blocks.ACACIA_LEAVES),
                BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                TwoLayersFeatureSize(1, 0, 1)
            )
                .forceDirt().ignoreVines().decorators(
                    ImmutableList.of<TreeDecorator>(leafPiles(DnDWoodBlocks.ACACIA_LEAF_PILE, blockTags))
                ).build()
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.ACACIA_BUSH_AUTUMN, Feature.TREE,
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG),
                StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.ACACIA_LEAVES),
                AcaciaFoliagePlacer(UniformIntProvider.create(1, 2), UniformIntProvider.create(0, 1)),
                TwoLayersFeatureSize(0, 0, 0)
            ).build()
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.fallenTrees() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.OAK_FALLEN_TREE, DnDFeatures.FALLEN_TREE, FallenTreeConfig(
                BlockStateProvider.of(Blocks.OAK_LOG.defaultState),
                BlockStateProvider.of(DnDWoodBlocks.HOLLOW_OAK_LOG.defaultState)
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.flowers() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.FLOWER_AUTUMN, Feature.FLOWER, ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                64,
                PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                        WeightedBlockStateProvider(
                            DataPool.builder<BlockState>()
                                .addWeighted(Blocks.CORNFLOWER.defaultState, 5)
                                .addWeighted(Blocks.POPPY.defaultState, 5)
                                .addWeighted(DnDWoodBlocks.CASCADE_SAPLING.defaultState, 1)
                        )
                    )
                )
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.PATCH_ROSEBUSH,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.ROSE_BUSH))
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.BLUE_PETALS, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2,
                PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockFeatureConfig(WeightedBlockStateProvider(petalBuilder(DnDBlocks.BLUE_PETALS)))
                )
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.fairyRings() {
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_WHITE, DnDBlocks.WHITE_PETALS)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_RED, DnDBlocks.RED_PETALS)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_BLUE, DnDBlocks.BLUE_PETALS)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_ORANGE, DnDBlocks.ORANGE_PETALS)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_COLD_WILDFLOWER, DnDBlocks.WILD_PETALS)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_CRIMSON, DnDBlocks.CRIMSON_VIVIONS, 5)
        this.fairyRing(DnDConfiguredFeature.FAIRY_RING_WARPED, DnDBlocks.WARPED_VIVIONS, 5)
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.vegetation() {
        val configuredFeatures = this.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)

        this.registerConfiguredFeature(
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
        this.registerConfiguredFeature(
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

        this.registerConfiguredFeature(
            DnDConfiguredFeature.GOLDEN_VEGETATION, Feature.RANDOM_SELECTOR, RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DnDPlacedFeature.GOLDEN_BIRCH_TALL), 0.5f)
                ), placedFeatures.getHolderOrThrow(DnDPlacedFeature.GOLDEN_BIRCH_TALL_BEES)
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
        val configuredFeatures = this.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        this.registerConfiguredFeature(
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
        this.registerConfiguredFeature(
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
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_WHEAT,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.WHEAT)
                ),
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_CARROTS,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.CARROTS)
                )
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_POTATOES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.POTATOES)
                )
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.CROPS_PUMPKIN,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                    basicCropAges(Blocks.PUMPKIN_STEM)
                )
            )
        )
        this.registerConfiguredFeature(
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
        this.registerConfiguredFeature(
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
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.disks() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.DISK_PODZOL, Feature.DISK, DiskFeatureConfig(
                C_cxbmzbuz(
                    BlockStateProvider.of(Blocks.DIRT), listOf(
                        C_pkkqenbk(
                            BlockPredicate.not(
                                BlockPredicate.eitherOf(
                                    BlockPredicate.solid(Direction.UP.vector),
                                    BlockPredicate.matchingFluids(Direction.UP.vector, Fluids.WATER)
                                )
                            ), BlockStateProvider.of(Blocks.PODZOL)
                        )
                    )
                ),
                BlockPredicate.matchingBlocks(listOf(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.STONE)),
                UniformIntProvider.create(2, 6), 2
            )
        )
        this.registerConfiguredFeature(
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

        this.registerConfiguredFeature(
            DnDConfiguredFeature.DISK_RED_SAND, Feature.DISK, DiskFeatureConfig(
                C_cxbmzbuz(
                    BlockStateProvider.of(Blocks.RED_SAND), listOf(
                        C_pkkqenbk(
                            BlockPredicate.matchingBlocks(Direction.DOWN.vector, Blocks.AIR),
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
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.overlayOres() {
        this.registerConfiguredFeature(
            DnDConfiguredFeature.ROCKY_OVERWORLD_ORE,
            Feature.ORE,
            OreFeatureConfig(
                listOf<OreFeatureConfig.Target>(
                    OreFeatureConfig.createTarget(
                        TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES),
                        DnDOverlayBlocks.ROCKY_DIRT.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.DIRT),
                        DnDOverlayBlocks.ROCKY_DIRT.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.GRASS_BLOCK),
                        DnDOverlayBlocks.ROCKY_GRASS.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.PODZOL),
                        DnDOverlayBlocks.ROCKY_PODZOL.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.GRAVEL),
                        DnDOverlayBlocks.ROCKY_GRAVEL.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.DIRT_PATH),
                        DnDOverlayBlocks.ROCKY_DIRT_PATH.defaultState
                    )
                ), 33
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.SLATED_OVERWORLD_ORE,
            Feature.ORE,
            OreFeatureConfig(
                listOf<OreFeatureConfig.Target>(
                    OreFeatureConfig.createTarget(
                        TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                        DnDOverlayBlocks.SLATED_DIRT.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.GRAVEL),
                        DnDOverlayBlocks.SLATED_GRAVEL.defaultState
                    ),
                ), 33
            )
        )
        this.registerConfiguredFeature(
            DnDConfiguredFeature.BLACKSTONE_NETHER_ORE,
            Feature.ORE,
            OreFeatureConfig(
                listOf<OreFeatureConfig.Target>(
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.NETHERRACK),
                        DnDOverlayBlocks.BLACKSTONE_SOUL_SAND.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.SOUL_SAND),
                        DnDOverlayBlocks.BLACKSTONE_SOUL_SAND.defaultState
                    ),
                    OreFeatureConfig.createTarget(
                        BlockMatchRuleTest(Blocks.SOUL_SOIL),
                        DnDOverlayBlocks.BLACKSTONE_SOUL_SOIL.defaultState
                    ),
                ), 33
            )
        )
    }

    fun treeBuilder(
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

    fun BootstrapContext<ConfiguredFeature<*, *>>.fairyRing(
        feature: RegistryKey<ConfiguredFeature<*, *>>,
        block: Block,
        verticalRange: Int = 3
    ) {
        this.registerConfiguredFeature(
            feature,
            DnDFeatures.FAIRY_RING,
            FairyRingConfig(
                BlockStateProvider.of(block.defaultState),
                DnDBlockTags.FALLEN_TREE_REPLACEABLE,
                verticalRange
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.pumpkinPatch(
        feature: RegistryKey<ConfiguredFeature<*, *>>,
        block: BlockStateProvider
    ) {
        this.registerConfiguredFeature(
            feature,
            Feature.RANDOM_PATCH,
            createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                SimpleBlockFeatureConfig(block),
                DnDBlockTags.PUMPKIN_PATCH_PLACE_ON
            )
        )
    }

    fun BootstrapContext<ConfiguredFeature<*, *>>.pumpkinPatch(
        feature: RegistryKey<ConfiguredFeature<*, *>>,
        block: Block
    ) {
        this.pumpkinPatch(feature, BlockStateProvider.of(block))
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
