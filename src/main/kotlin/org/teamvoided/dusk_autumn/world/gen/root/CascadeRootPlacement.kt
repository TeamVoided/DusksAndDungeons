package org.teamvoided.dusk_autumn.world.gen.root

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Block
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryCodecs
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.stateprovider.BlockStateProvider

@JvmRecord
data class CascadeRootPlacement(
    val canGrowThrough: HolderSet<Block>,
    val muddyRootsIn: HolderSet<Block>,
    val muddyRootsProvider: BlockStateProvider,
    val maxRootWidth: Int,
    val maxRootLength: Int,
    val randomSkewChance: Float
) {
    fun canGrowThrough(): HolderSet<Block> {
        return this.canGrowThrough
    }

    fun muddyRootsIn(): HolderSet<Block> {
        return this.muddyRootsIn
    }

    fun muddyRootsProvider(): BlockStateProvider {
        return this.muddyRootsProvider
    }

    fun maxRootWidth(): Int {
        return this.maxRootWidth
    }

    fun maxRootLength(): Int {
        return this.maxRootLength
    }

    fun randomSkewChance(): Float {
        return this.randomSkewChance
    }

    companion object {
        val CODEC: Codec<CascadeRootPlacement> = RecordCodecBuilder.create {
            it.group(
                RegistryCodecs.homogeneousList(RegistryKeys.BLOCK)
                    .fieldOf("can_grow_through").forGetter { placement -> placement.canGrowThrough },
                RegistryCodecs.homogeneousList(RegistryKeys.BLOCK).fieldOf("muddy_roots_in")
                    .forGetter { placement -> placement.muddyRootsIn },
                BlockStateProvider.TYPE_CODEC.fieldOf("muddy_roots_provider")
                    .forGetter { placement -> placement.muddyRootsProvider },
                Codec.intRange(1, 12).fieldOf("max_root_width")
                    .forGetter { placement -> placement.maxRootWidth },
                Codec.intRange(1, 64).fieldOf("max_root_length")
                    .forGetter { placement -> placement.maxRootLength },
                Codec.floatRange(0.0f, 1.0f).fieldOf("random_skew_chance")
                    .forGetter { placement -> placement.randomSkewChance },
            ).apply(it, ::CascadeRootPlacement)
        }
    }
}