package org.teamvoided.dusk_autumn.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class FallenTreeConfig(
    val logBlock: BlockStateProvider,
    var allowedPlacement: TagKey<Block>,
    val logTopper: BlockStateProvider,
    val stumpSides: BlockStateProvider,
    val treeWidth: Int,
    val stumpHeight: IntProvider,
    val trunkLength: IntProvider,
    val trunkDistanceFromStump: IntProvider,
    val trunkVerticalRange: Int
) : FeatureConfig {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FallenTreeConfig> ->
                instance.group(
                    BlockStateProvider.TYPE_CODEC.fieldOf("log_block").forGetter { it.logBlock },
                    TagKey.createHashedCodec(RegistryKeys.BLOCK).fieldOf("allowed_placenemt")
                        .forGetter { it.allowedPlacement },
                    BlockStateProvider.TYPE_CODEC.fieldOf("log_topper").forGetter { it.logTopper },
                    BlockStateProvider.TYPE_CODEC.fieldOf("stump_sides").forGetter { it.stumpSides },
                    Codec.intRange(1, 3).fieldOf("tree_width").forGetter { it.treeWidth },
                    IntProvider.method_35004(0, 32).fieldOf("stump_height").forGetter { it.stumpHeight },
                    IntProvider.method_35004(0, 32).fieldOf("trunk_length").forGetter { it.trunkLength },
                    IntProvider.method_35004(0, 16).fieldOf("trunk_distance_from_stump")
                        .forGetter { it.trunkDistanceFromStump },
                    Codec.intRange(0, 64).fieldOf("trunk_vertical_range").forGetter { it.trunkVerticalRange }
                ).apply(instance, ::FallenTreeConfig)
            }
    }
}