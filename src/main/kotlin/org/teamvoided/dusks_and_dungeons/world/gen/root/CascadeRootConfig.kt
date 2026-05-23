package org.teamvoided.dusks_and_dungeons.world.gen.root

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.block.Block
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.Registries
import net.minecraft.util.valueproviders.IntProvider

@JvmRecord
data class CascadeRootConfig(
    val canGrowThrough: HolderSet<Block>,
    val chanceForRoot: Int,
    val maxDistanceFromTrunk: IntProvider,
    val maxRootLength: Int,
) {
    companion object {
        val CODEC: Codec<CascadeRootConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_grow_through")
                    .forGetter { it.canGrowThrough },
                Codec.intRange(1, 100).fieldOf("chance_for_root").forGetter { it.chanceForRoot },
                IntProvider.codec(1, 16).fieldOf("max_distance_from_trunk")
                    .forGetter { it.maxDistanceFromTrunk },
                Codec.intRange(1, 32).fieldOf("max_root_length").forGetter { it.maxRootLength },
            ).apply(instance, ::CascadeRootConfig)
        }
    }
}
