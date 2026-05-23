package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class BoulderConfig(
    val block: BlockStateProvider,
    val size: IntProvider,
    val boulderCount: IntProvider,
    val weirdness: IntProvider,
    val otherBoulderOffset: IntProvider,
    val moveDownIfReplaceable: Boolean = true
) : FeatureConfiguration {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<BoulderConfig> ->
                instance.group(
                    BlockStateProvider.CODEC.fieldOf("block").forGetter { it.block },
                    IntProvider.codec(1, 16).fieldOf("size").forGetter { it.size },
                    IntProvider.codec(1, 8).fieldOf("boulder_count").forGetter { it.boulderCount },
                    IntProvider.codec(1, 16).fieldOf("weirdness").forGetter { it.weirdness },
                    IntProvider.codec(0, 16).fieldOf("other_boulder_offset")
                        .forGetter { it.otherBoulderOffset },
                    Codec.BOOL.fieldOf("move_Down_If_Replaceable").orElse(true).forGetter { it.moveDownIfReplaceable }
                ).apply(instance, ::BoulderConfig)
            }
    }
}
