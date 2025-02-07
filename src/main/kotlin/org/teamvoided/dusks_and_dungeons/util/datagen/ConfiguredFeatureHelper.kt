package org.teamvoided.dusks_and_dungeons.util.datagen

import net.minecraft.block.*
import net.minecraft.registry.Holder
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.Direction
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
import net.minecraft.world.gen.treedecorator.TreeDecorator
import org.teamvoided.dusks_and_dungeons.block.LeafPileBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.AlterOnGroundTreeDecorator


fun addPumpkins(pumpkin: Block, carved: Block, lantern: Block): WeightedBlockStateProvider {
    val pumpkins = DataPool.builder<BlockState>()
    pumpkins.addWeighted(pumpkin.defaultState, 64)
    Direction.Type.HORIZONTAL.forEach {
        pumpkins
            .addWeighted(
                carved.defaultState
                    .with(HorizontalFacingBlock.FACING, it), 8
            )
            .addWeighted(
                lantern.defaultState
                    .with(HorizontalFacingBlock.FACING, it), 1
            )
    }
    return WeightedBlockStateProvider(pumpkins)
}

fun <FC : FeatureConfig, F : Feature<FC>> createRandomPatchFeatureConfig(
    feature: F,
    featureConfig: FC,
    tag: TagKey<Block>,
    tries: Int = 96
): RandomPatchFeatureConfig {
    val predicate = BlockPredicate.bothOf(
        BlockPredicate.IS_AIR, BlockPredicate.matchingBlockTags(Direction.DOWN.vector, tag)
    )
    return ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
        tries,
        PlacedFeatureUtil.filtered(feature, featureConfig, predicate)
    )
}

fun basicCropAges(crop: Block): WeightedBlockStateProvider {
    val crops = DataPool.builder<BlockState>()
    (1..7).forEach { age ->
        crops.addWeighted(crop.defaultState.with(CropBlock.AGE, age), 7 - age + 1)
    }
    return WeightedBlockStateProvider(crops)
}

fun petalBuilder(flower: Block): DataPool.Builder<BlockState> {
    val petalFlowerBuilder = DataPool.builder<BlockState>()
    (1..4).forEach { count ->
        Direction.Type.HORIZONTAL.forEach { direction ->
            petalFlowerBuilder.addWeighted(
                flower.defaultState
                    .with(PinkPetalsBlock.AMOUNT, count)
                    .with(PinkPetalsBlock.FACING, direction),
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



