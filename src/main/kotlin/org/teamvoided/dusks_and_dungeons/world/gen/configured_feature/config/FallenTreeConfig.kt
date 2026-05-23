package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.Blocks
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.util.valueproviders.BiasedToBottomInt
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import org.teamvoided.dusks_and_dungeons.data.tags.DnDBlockTags

data class FallenTreeConfig(
    val stumpBlock: BlockStateProvider,
    val logBlock: BlockStateProvider,
    val replaceable: TagKey<Block> = DEFAULT.replaceable,
    val logTopperChance: Int = DEFAULT.logTopperChance,
    val stumpSidesChance: Int = DEFAULT.stumpSidesChance,
    val logTopper: BlockStateProvider = DEFAULT.logTopper,    //mushrooms
    val stumpSides: BlockStateProvider = DEFAULT.stumpSides,  //vines
    val treeWidth: Int = DEFAULT.treeWidth,                   //oak, dark oak, cascade
    val stumpHeight: IntProvider = DEFAULT.stumpHeight,
    val trunkLength: IntProvider = DEFAULT.trunkLength,
    val trunkDistanceFromStump: IntProvider = DEFAULT.trunkDistanceFromStump,
    val trunkVerticalRange: Int = DEFAULT.trunkVerticalRange
) : FeatureConfiguration {


    companion object {
        private val mushrooms = WeightedStateProvider(
            SimpleWeightedRandomList.builder<BlockState>()
                .add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1)
                .add(Blocks.RED_MUSHROOM.defaultBlockState(), 1)
        )
        private val vine = BlockStateProvider.simple(Blocks.VINE)

        val DEFAULT = FallenTreeConfig(
            BlockStateProvider.simple(Blocks.OAK_LOG),
            BlockStateProvider.simple(Blocks.OAK_LOG),
            DnDBlockTags.FALLEN_TREE_REPLACEABLE,
            -1,
            -1,
            BlockStateProvider.simple(Blocks.AIR),
            BlockStateProvider.simple(Blocks.AIR),
            1,
            BiasedToBottomInt.of(1, 3),
            UniformInt.of(2, 4),
            UniformInt.of(0, 2),
            16
        )
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FallenTreeConfig> ->
                instance.group(
                    BlockStateProvider.CODEC.fieldOf("stump_block").forGetter { it.stumpBlock },
                    BlockStateProvider.CODEC.fieldOf("log_block").forGetter { it.logBlock },
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("replaceable")
                        .forGetter { it.replaceable },
                    Codec.intRange(-1, 48).fieldOf("log_topper_chance").forGetter { it.logTopperChance },
                    Codec.intRange(-1, 48).fieldOf("stump_sides_chance").forGetter { it.stumpSidesChance },
                    BlockStateProvider.CODEC.fieldOf("log_topper").orElse(BlockStateProvider.simple(Blocks.AIR))
                        .forGetter { it.logTopper },
                    BlockStateProvider.CODEC.fieldOf("stump_sides").orElse(BlockStateProvider.simple(Blocks.AIR))
                        .forGetter { it.stumpSides },
                    Codec.intRange(1, 3).fieldOf("tree_width").orElse(1).forGetter { it.treeWidth },
                    IntProvider.codec(1, 32).fieldOf("stump_height").forGetter { it.stumpHeight },
                    IntProvider.codec(1, 32).fieldOf("trunk_length").forGetter { it.trunkLength },
                    IntProvider.codec(0, 16).fieldOf("trunk_distance_from_stump")
                        .forGetter { it.trunkDistanceFromStump },
                    Codec.intRange(0, 64).fieldOf("trunk_vertical_range").forGetter { it.trunkVerticalRange }
                ).apply(instance, ::FallenTreeConfig)
            }
    }
}
