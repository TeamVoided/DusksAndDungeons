package org.teamvoided.dusk_autumn.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class FrozenSpringConfig(
    val iceBlock: BlockState,
    var allowedReplacement: TagKey<Block>,
    var allowedPlacement: TagKey<Block>,
    val spreadRange: Int,
    val horizontalRange: Int,
    val emptyFacesRequirement: Int,
    val hasExposedDownFace: Boolean,
) : FeatureConfig {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FrozenSpringConfig> ->
                instance.group(
                    BlockState.CODEC.fieldOf("ice_block").forGetter { it.iceBlock },
                    TagKey.createHashedCodec(RegistryKeys.BLOCK).fieldOf("allowed_replacement").forGetter { it.allowedReplacement },
                    TagKey.createHashedCodec(RegistryKeys.BLOCK).fieldOf("allowed_placement").forGetter { it.allowedPlacement },
                    Codec.intRange(1, 16).fieldOf("spread_range").forGetter { it.spreadRange },
                    Codec.intRange(1, 16).fieldOf("horizontal_range").orElse(1).forGetter { it.horizontalRange },
                    Codec.intRange(0, 5).fieldOf("empty_faces_requirement").orElse(1).forGetter { it.emptyFacesRequirement },
                    Codec.BOOL.fieldOf("has_exposed_down_face").orElse(false).forGetter { it.hasExposedDownFace }
                ).apply(instance, ::FrozenSpringConfig)
            }
    }
}
