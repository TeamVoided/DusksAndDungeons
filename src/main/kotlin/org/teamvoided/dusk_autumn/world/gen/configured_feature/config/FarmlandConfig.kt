package org.teamvoided.dusk_autumn.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class FarmlandConfig(
    var farmlandReplaceable: TagKey<Block>,
    var farmlandCanPlaceUnder: TagKey<Block>,
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
) : FeatureConfig {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FarmlandConfig> ->
                instance.group(
                    TagKey.createHashedCodec(RegistryKeys.BLOCK).fieldOf("replaceable")
                        .forGetter { it.farmlandReplaceable },
                    TagKey.createHashedCodec(RegistryKeys.BLOCK).fieldOf("can_place_under")
                        .forGetter { it.farmlandCanPlaceUnder },
                    BlockStateProvider.TYPE_CODEC.fieldOf("farmland_block").forGetter { it.farmlandBlock },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("farmland_chance").forGetter { it.farmlandChance },
                    IntProvider.method_35004(0, 64).fieldOf("farm_width").forGetter { it.farmWidth },
                    Codec.intRange(1, 256).fieldOf("farm_vertical_range").forGetter { it.farmVerticalRange },
                    BlockStateProvider.TYPE_CODEC.fieldOf("fence_block").forGetter { it.fenceBlock },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("fence_chance").forGetter { it.fenceChance },
                    IntProvider.method_35004(0, 24).fieldOf("fence_length").forGetter { it.fenceLength },
                    BlockStateProvider.TYPE_CODEC.fieldOf("water_block").forGetter { it.waterBlock },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("water_chance").forGetter { it.waterChance },
                    PlacedFeature.REGISTRY_CODEC.fieldOf("crop_feature").forGetter { it.cropFeature },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("crop_feature_chance").forGetter { it.cropFeatureChance },
                    Codec.BOOL.fieldOf("crop_guarantee").forGetter { it.cropGuarantee },
                    Registries.ENTITY_TYPE.codec.listOf().fieldOf("scarecrow").forGetter { it.scarecrow }
                ).apply(instance, ::FarmlandConfig)
            }
    }
}
