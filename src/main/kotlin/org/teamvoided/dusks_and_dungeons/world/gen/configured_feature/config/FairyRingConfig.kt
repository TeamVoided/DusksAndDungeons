package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.block.Block
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class FairyRingConfig(
    val block: BlockStateProvider,
    val replaceable: TagKey<Block>,
    val verticalRange: Int,
    val size: IntProvider = UniformInt.of(1, 3),
//    val feature: Holder<PlacedFeature>,
) : FeatureConfiguration {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FairyRingConfig> ->
                instance.group(
                    BlockStateProvider.CODEC.fieldOf("block").forGetter { it.block },
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("replaceable")
                        .forGetter { it.replaceable },
                    Codec.intRange(0, 32).fieldOf("vertical_range").orElse(0).forGetter { it.verticalRange },
                    IntProvider.codec(1, 3).fieldOf("size").forGetter { it.size },
//                    PlacedFeature.REGISTRY_CODEC.fieldOf("feature").forGetter { it.feature },
                ).apply(instance, ::FairyRingConfig)
            }
    }
}
