package org.teamvoided.dusks_and_dungeons.util.datagen

import net.minecraft.core.HolderGetter
import net.minecraft.tags.TagKey
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.core.Direction
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.PinkPetalsBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator
import org.teamvoided.dusks_and_dungeons.block.LeafPileBlock
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags
import org.teamvoided.dusks_and_dungeons.world.gen.treedcorator.AlterOnGroundTreeDecorator


fun addPumpkins(pumpkin: Block, carved: Block, lantern: Block): WeightedStateProvider {
    val pumpkins = SimpleWeightedRandomList.builder<BlockState>()
    pumpkins.add(pumpkin.defaultBlockState(), 64)
    Direction.Plane.HORIZONTAL.forEach {
        pumpkins
            .add(
                carved.defaultBlockState()
                    .setValue(HorizontalDirectionalBlock.FACING, it), 8
            )
            .add(
                lantern.defaultBlockState()
                    .setValue(HorizontalDirectionalBlock.FACING, it), 1
            )
    }
    return WeightedStateProvider(pumpkins)
}

fun <FC : FeatureConfiguration, F : Feature<FC>> createRandomPatchFeatureConfig(
    feature: F,
    featureConfig: FC,
    tag: TagKey<Block>,
    tries: Int = 96
): RandomPatchConfiguration {
    val predicate = BlockPredicate.allOf(
        BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesTag(Direction.DOWN.normal, tag)
    )
    return FeatureUtils.simpleRandomPatchConfiguration(
        tries,
        PlacementUtils.filtered(feature, featureConfig, predicate)
    )
}

fun basicCropAges(crop: Block): WeightedStateProvider {
    val crops = SimpleWeightedRandomList.builder<BlockState>()
    (1..7).forEach { age ->
        crops.add(crop.defaultBlockState().setValue(CropBlock.AGE, age), 7 - age + 1)
    }
    return WeightedStateProvider(crops)
}

fun petalBuilder(flower: Block): SimpleWeightedRandomList.Builder<BlockState> {
    val petalFlowerBuilder = SimpleWeightedRandomList.builder<BlockState>()
    (1..4).forEach { count ->
        Direction.Plane.HORIZONTAL.forEach { direction ->
            petalFlowerBuilder.add(
                flower.defaultBlockState()
                    .setValue(PinkPetalsBlock.AMOUNT, count)
                    .setValue(PinkPetalsBlock.FACING, direction),
                1
            )
        }
    }
    return petalFlowerBuilder
}

fun leafPiles(leafPile: Block, blockTags: HolderGetter<Block>): TreeDecorator {
    return AlterOnGroundTreeDecorator(
        WeightedStateProvider(
            SimpleWeightedRandomList.builder<BlockState>()
                .add(leafPile.defaultBlockState(), 9)
                .add(leafPile.defaultBlockState().setValue(LeafPileBlock.PILE_LAYERS, 2), 4)
                .add(leafPile.defaultBlockState().setValue(LeafPileBlock.PILE_LAYERS, 3), 1)
        ),
        3, 10, 20,
        blockTags.getOrThrow(DnDBlockTags.LEAF_PILES_PLACE_ON)
    )
}



