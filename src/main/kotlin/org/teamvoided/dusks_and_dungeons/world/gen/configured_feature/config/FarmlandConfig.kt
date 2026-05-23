package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.block.Block
import net.minecraft.world.entity.EntityType
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class FarmlandConfig(
    val farmlandReplaceable: TagKey<Block>,
    val farmlandCanPlaceUnder: TagKey<Block>,
    val farmlandBlock: BlockStateProvider,
    val farmlandChance: Float,
    val farmWidth: IntProvider,
    val farmVerticalRange: Int,
    val fenceBlock: BlockStateProvider,
    val fenceChance: Float,
    val fenceLength: IntProvider,
    val waterBlock: BlockStateProvider,
    val waterChance: Float,
    val cropFeature: Holder<PlacedFeature>,
    val cropFeatureChance: Float,
    val cropGuarantee: Boolean,
    val scarecrow: List<EntityType<*>>
) : FeatureConfiguration {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FarmlandConfig> ->
                instance.group(
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("replaceable")
                        .forGetter { it.farmlandReplaceable },
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("can_place_under")
                        .forGetter { it.farmlandCanPlaceUnder },
                    BlockStateProvider.CODEC.fieldOf("farmland_block").forGetter { it.farmlandBlock },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("farmland_chance").forGetter { it.farmlandChance },
                    IntProvider.codec(3, 64).fieldOf("farm_width").forGetter { it.farmWidth },
                    Codec.intRange(1, 256).fieldOf("farm_vertical_range").forGetter { it.farmVerticalRange },
                    BlockStateProvider.CODEC.fieldOf("fence_block").forGetter { it.fenceBlock },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("fence_chance").forGetter { it.fenceChance },
                    IntProvider.codec(0, 24).fieldOf("fence_length").forGetter { it.fenceLength },
                    BlockStateProvider.CODEC.fieldOf("water_block").forGetter { it.waterBlock },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("water_chance").forGetter { it.waterChance },
                    PlacedFeature.CODEC.fieldOf("crop_feature").forGetter { it.cropFeature },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("crop_feature_chance").forGetter { it.cropFeatureChance },
                    Codec.BOOL.fieldOf("crop_guarantee").forGetter { it.cropGuarantee },
                    BuiltInRegistries.ENTITY_TYPE.byNameCodec().listOf().fieldOf("scarecrow").forGetter { it.scarecrow }
                ).apply(instance, ::FarmlandConfig)
            }
    }
}
