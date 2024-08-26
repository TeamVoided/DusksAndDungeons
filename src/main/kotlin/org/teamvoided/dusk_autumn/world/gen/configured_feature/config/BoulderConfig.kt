package org.teamvoided.dusk_autumn.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class BoulderConfig(
    val block: BlockStateProvider,
    val size: IntProvider,
    val boulderCount: IntProvider,
    val weirdness: IntProvider,
    val otherBoulderOffset: IntProvider,
    val moveDownIfReplaceable: Boolean = true
) : FeatureConfig {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<BoulderConfig> ->
                instance.group(
                    BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter { it.block },
                    IntProvider.method_35004(1, 16).fieldOf("size").forGetter { it.size },
                    IntProvider.method_35004(1, 8).fieldOf("boulder_count").forGetter { it.boulderCount },
                    IntProvider.method_35004(1, 16).fieldOf("weirdness").forGetter { it.weirdness },
                    IntProvider.method_35004(0, 16).fieldOf("other_boulder_offset")
                        .forGetter { it.otherBoulderOffset },
                    Codec.BOOL.fieldOf("move_Down_If_Replaceable").orElse(true).forGetter { it.moveDownIfReplaceable }
                ).apply(instance, ::BoulderConfig)
            }
    }
}
