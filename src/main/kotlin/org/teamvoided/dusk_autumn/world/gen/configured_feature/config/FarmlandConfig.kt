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
import net.minecraft.world.gen.feature.VegetationPatchFeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class FarmlandConfig(
    var replaceable: TagKey<Block>,
    var canPlaceUnder: TagKey<Block>,
    val farmSize: IntProvider,
    val farmVerticalRange: Int,
    val farmlandBlock: BlockStateProvider,
    val farmlandChance: Float,
    val fenceAmount: IntProvider,
    val fenceBlock: BlockStateProvider,
    val waterBlock: BlockStateProvider,
    val cropFeature: Holder<PlacedFeature>,
    val cropGuarantee: Boolean,
    val cropFeatureChance: Float,
    val scarecrow: List<EntityType<*>>
) : FeatureConfig {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FarmlandConfig> ->
                instance.group(
                    TagKey.createHashedCodec(RegistryKeys.BLOCK).fieldOf("replaceable").forGetter { it.replaceable },
                    TagKey.createHashedCodec(RegistryKeys.BLOCK).fieldOf("can_place_under").forGetter { it.canPlaceUnder },
                    IntProvider.create(0, 64).fieldOf("farm_size").forGetter { it.farmSize },
                    Codec.intRange(1, 256).fieldOf("farm_vertical_range").forGetter { it.farmVerticalRange },
                    BlockStateProvider.TYPE_CODEC.fieldOf("farmland_block").forGetter { it.farmlandBlock },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("farmland_chance").forGetter { it.farmlandChance },
                    IntProvider.create(0, 64).fieldOf("fence_amount").forGetter { it.fenceAmount },
                    BlockStateProvider.TYPE_CODEC.fieldOf("fence_block").forGetter { it.fenceBlock },
                    BlockStateProvider.TYPE_CODEC.fieldOf("water_block").forGetter { it.waterBlock },
                    PlacedFeature.REGISTRY_CODEC.fieldOf("crop_feature").forGetter { it.cropFeature },
                    Codec.BOOL.fieldOf("crop_guarantee").forGetter { it.cropGuarantee },
                    Codec.floatRange(0.0f, 1.0f).fieldOf("crop_feature_chance").forGetter { it.cropFeatureChance },
                    Registries.ENTITY_TYPE.codec.listOf().fieldOf("entity_type").forGetter { it.scarecrow }
                ).apply(instance, ::FarmlandConfig)
            }
    }
}