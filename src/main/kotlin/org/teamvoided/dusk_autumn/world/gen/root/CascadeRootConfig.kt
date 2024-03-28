package org.teamvoided.dusk_autumn.world.gen.root

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryCodecs
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.stateprovider.BlockStateProvider

@JvmRecord
data class CascadeRootConfig(
    val canGrowThrough: HolderSet<Block>,
    val muddyRootsIn: HolderSet<Block>,
    val muddyRootsProvider: BlockStateProvider,
    val maxRootWidth: Int,
    val maxRootLength: Int,
    val randomSkewChance: Float
) {
    companion object {
        val CODEC: Codec<CascadeRootConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                RegistryCodecs.homogeneousList(RegistryKeys.BLOCK).fieldOf("can_grow_through")
                    .forGetter { it.canGrowThrough },
                RegistryCodecs.homogeneousList(RegistryKeys.BLOCK).fieldOf("muddy_roots_in")
                    .forGetter { it.muddyRootsIn },
                BlockStateProvider.TYPE_CODEC.fieldOf("muddy_roots_provider").forGetter { it.muddyRootsProvider },
                Codec.intRange(1, 12).fieldOf("max_root_width").forGetter { it.maxRootWidth },
                Codec.intRange(1, 64).fieldOf("max_root_length").forGetter { it.maxRootLength },
                Codec.floatRange(0.0f, 1.0f).fieldOf("random_skew_chance").forGetter { it.randomSkewChance },
            ).apply(instance, ::CascadeRootConfig)
        }
    }
}