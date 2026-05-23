package org.teamvoided.dusks_and_dungeons.world.gen.configured_feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration

data class FrozenSpringConfig(
    val iceBlock: BlockState,
    var allowedReplacement: TagKey<Block>,
    var allowedPlacement: TagKey<Block>,
    val spreadRange: Int,
    val horizontalRange: Int,
    val emptyFacesRequirement: Int,
    val hasExposedDownFace: Boolean,
) : FeatureConfiguration {
    companion object {
        val CODEC =
            RecordCodecBuilder.create { instance: RecordCodecBuilder.Instance<FrozenSpringConfig> ->
                instance.group(
                    BlockState.CODEC.fieldOf("ice_block").forGetter { it.iceBlock },
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("allowed_replacement")
                        .forGetter { it.allowedReplacement },
                    TagKey.hashedCodec(Registries.BLOCK).fieldOf("allowed_placement")
                        .forGetter { it.allowedPlacement },
                    Codec.intRange(1, 16).fieldOf("spread_range").forGetter { it.spreadRange },
                    Codec.intRange(1, 16).fieldOf("horizontal_range").orElse(1).forGetter { it.horizontalRange },
                    Codec.intRange(0, 5).fieldOf("empty_faces_requirement").orElse(1)
                        .forGetter { it.emptyFacesRequirement },
                    Codec.BOOL.fieldOf("has_exposed_down_face").orElse(false).forGetter { it.hasExposedDownFace }
                ).apply(instance, ::FrozenSpringConfig)
            }
    }
}
