package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.int_provider.BiasedToBottomIntProvider
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
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
) : FeatureConfig {


    companion object {
        private val mushrooms = WeightedBlockStateProvider(
            DataPool.builder<BlockState>()
                .addWeighted(Blocks.BROWN_MUSHROOM.defaultState, 1)
                .addWeighted(Blocks.RED_MUSHROOM.defaultState, 1)
        )
        private val vine = BlockStateProvider.of(Blocks.VINE)

        val DEFAULT = FallenTreeConfig(
            BlockStateProvider.of(Blocks.OAK_LOG),
            BlockStateProvider.of(Blocks.OAK_LOG),
            DnDBlockTags.FALLEN_TREE_REPLACEABLE,
            -1,
            -1,
            BlockStateProvider.of(Blocks.AIR),
            BlockStateProvider.of(Blocks.AIR),
            1,
            BiasedToBottomIntProvider.create(1, 3),
            UniformIntProvider.create(2, 4),
            UniformIntProvider.create(0, 2),
            16
        )
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FallenTreeConfig> ->
                instance.group(
                    BlockStateProvider.TYPE_CODEC.fieldOf("stump_block").forGetter { it.stumpBlock },
                    BlockStateProvider.TYPE_CODEC.fieldOf("log_block").forGetter { it.logBlock },
                    TagKey.createHashedCodec(RegistryKeys.BLOCK).fieldOf("replaceable")
                        .forGetter { it.replaceable },
                    Codec.intRange(-1, 48).fieldOf("log_topper_chance").forGetter { it.logTopperChance },
                    Codec.intRange(-1, 48).fieldOf("stump_sides_chance").forGetter { it.stumpSidesChance },
                    BlockStateProvider.TYPE_CODEC.fieldOf("log_topper").orElse(BlockStateProvider.of(Blocks.AIR))
                        .forGetter { it.logTopper },
                    BlockStateProvider.TYPE_CODEC.fieldOf("stump_sides").orElse(BlockStateProvider.of(Blocks.AIR))
                        .forGetter { it.stumpSides },
                    Codec.intRange(1, 3).fieldOf("tree_width").orElse(1).forGetter { it.treeWidth },
                    IntProvider.method_35004(1, 32).fieldOf("stump_height").forGetter { it.stumpHeight },
                    IntProvider.method_35004(1, 32).fieldOf("trunk_length").forGetter { it.trunkLength },
                    IntProvider.method_35004(0, 16).fieldOf("trunk_distance_from_stump")
                        .forGetter { it.trunkDistanceFromStump },
                    Codec.intRange(0, 64).fieldOf("trunk_vertical_range").forGetter { it.trunkVerticalRange }
                ).apply(instance, ::FallenTreeConfig)
            }
    }
}
