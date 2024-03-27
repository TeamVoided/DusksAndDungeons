package org.teamvoided.dusk_autumn.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.registry.HolderSet
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryCodecs
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class FarmlandConfig(
    var canReplace: HolderSet<Block>,
    val farmSize: IntProvider,
    val farmlandBlock: BlockStateProvider,
    val farmlandChance: Float,
    val fenceAmount: IntProvider,
    val fenceBlock: BlockStateProvider,
    val waterBlock: BlockStateProvider,
    val cropBlock: BlockStateProvider,
    val scarecrow: List<EntityType<*>>?
) : FeatureConfig {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FarmlandConfig> ->
                instance.group(
                    RegistryCodecs.homogeneousList(RegistryKeys.BLOCK).fieldOf("can_replace")
                        .forGetter { it.canReplace },
                    IntProvider.create(1, 28).fieldOf("farm_size").forGetter { it.farmSize },
                    BlockStateProvider.TYPE_CODEC.fieldOf("farmland_block").forGetter { it.farmlandBlock },
                    Codec.floatRange(0.0f, 1.0f)
                        .fieldOf("farmland_chance").forGetter { it.farmlandChance },
                    IntProvider.create(0, 128).fieldOf("fence_amount").forGetter { it.fenceAmount },
                    BlockStateProvider.TYPE_CODEC.fieldOf("fence_block").forGetter { it.fenceBlock },
                    BlockStateProvider.TYPE_CODEC.fieldOf("water_block").forGetter { it.waterBlock },
                    BlockStateProvider.TYPE_CODEC.fieldOf("crop_block").forGetter { it.cropBlock },
                    Registries.ENTITY_TYPE.codec.listOf().fieldOf("entity_type").forGetter { it.scarecrow }
                ).apply(instance, ::FarmlandConfig)
            }
    }
}