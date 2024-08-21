package org.teamvoided.dusk_autumn.world.gen.root

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryCodecs
import net.minecraft.registry.RegistryKeys

@JvmRecord
data class CascadeRootConfig(
    val canGrowThrough: HolderSet<Block>,
    val chanceForRoot: Int,
    val maxDistanceFromTrunk: Int,
    val maxRootLength: Int,
) {
    companion object {
        val CODEC: Codec<CascadeRootConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                RegistryCodecs.homogeneousList(RegistryKeys.BLOCK).fieldOf("can_grow_through")
                    .forGetter { it.canGrowThrough },
                Codec.intRange(1, 100).fieldOf("chance_for_root").forGetter { it.chanceForRoot },
                Codec.intRange(1, 16).fieldOf("max_distance_from_trunk").forGetter { it.maxDistanceFromTrunk },
                Codec.intRange(1, 32).fieldOf("max_root_length").forGetter { it.maxRootLength },
            ).apply(instance, ::CascadeRootConfig)
        }
    }
}
