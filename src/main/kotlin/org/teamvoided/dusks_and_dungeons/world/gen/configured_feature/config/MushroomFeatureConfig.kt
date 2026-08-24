package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

open class MushroomFeatureConfig(
    val replaceable: TagKey<Block>,
    val ignores: TagKey<Block>,
    val canPlaceOn: TagKey<Block>,
    val stemBlock: BlockStateProvider,
    val stemHeight: IntProvider,
    val capBlock: BlockStateProvider,
    val capHeight: IntProvider,
) : FeatureConfiguration {
    companion object {
        val MAP_CODEC: MapCodec<MushroomFeatureConfig> =
            RecordCodecBuilder.mapCodec { instance ->
                instance.group(
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("replaceable").forGetter { it.replaceable },
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("ignores").forGetter { it.ignores },
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("can_place_on").forGetter { it.canPlaceOn },
                    BlockStateProvider.CODEC.fieldOf("stem_block").forGetter { it.stemBlock },
                    IntProvider.codec(1, 32).fieldOf("stem_height").forGetter { it.stemHeight },
                    BlockStateProvider.CODEC.fieldOf("cap_block").forGetter { it.capBlock },
                    IntProvider.codec(1, 16).fieldOf("cap_height").forGetter { it.capHeight },
                ).apply(instance, ::MushroomFeatureConfig)
            }
        val CODEC: Codec<MushroomFeatureConfig> = MAP_CODEC.codec()
    }
}
