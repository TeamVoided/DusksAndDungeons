package org.teamvoided.dusk_autumn.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class FallenTreeConfig(
    val stumpBlock: BlockStateProvider,
    val logBlock: BlockStateProvider,
    val replaceable: TagKey<Block>,
    val logTopperChance: Int,
    val stumpSidesChance: Int,
    val logTopper: BlockStateProvider,    //mushrooms
    val stumpSides: BlockStateProvider,   //vines
    val treeWidth: Int,                   //oak, dark oak, cascade
    val stumpHeight: IntProvider,
    val trunkLength: IntProvider,
    val trunkDistanceFromStump: IntProvider,
    val trunkVerticalRange: Int
) : FeatureConfig {
    companion object {
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
