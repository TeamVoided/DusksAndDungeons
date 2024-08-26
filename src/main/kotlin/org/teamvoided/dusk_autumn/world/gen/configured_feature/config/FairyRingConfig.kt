package org.teamvoided.dusk_autumn.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.registry.Holder
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class FairyRingConfig(
    val block: BlockStateProvider,
    val replaceable: TagKey<Block>,
    val verticalRange: Int,
    val size: IntProvider,
//    val feature: Holder<PlacedFeature>,
) : FeatureConfig {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FairyRingConfig> ->
                instance.group(
                    BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter { it.block },
                    TagKey.createHashedCodec(RegistryKeys.BLOCK).fieldOf("replaceable")
                        .forGetter { it.replaceable },
                    Codec.intRange(0, 32).fieldOf("vertical_range").orElse(0).forGetter { it.verticalRange },
                    IntProvider.method_35004(1, 3).fieldOf("size").forGetter { it.size },
//                    PlacedFeature.REGISTRY_CODEC.fieldOf("feature").forGetter { it.feature },
                ).apply(instance, ::FairyRingConfig)
            }
    }
}
