package org.teamvoided.dusks_and_dungeons.particle

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import org.teamvoided.dusks_and_dungeons.init.DnDParticles

class ShriekDirectionalParticleEffect(val direction: Direction, val delay: Int) : ParticleOptions {

    override fun getType(): ParticleType<ShriekDirectionalParticleEffect> = DnDParticles.SHRIEK_DIRECTIONAL

    companion object {

        val CODEC: MapCodec<ShriekDirectionalParticleEffect> = RecordCodecBuilder.mapCodec { inst ->
            inst
                .group(
                    Direction.CODEC.fieldOf("direction").orElse(Direction.UP).forGetter { it.direction },
                    Codec.INT.fieldOf("delay").orElse(0).forGetter { it.delay }
                )
                .apply(inst, ::ShriekDirectionalParticleEffect)
        }

        val PACKET_CODEC: StreamCodec<RegistryFriendlyByteBuf, ShriekDirectionalParticleEffect> = StreamCodec.composite(
            Direction.STREAM_CODEC, { it.direction },
            ByteBufCodecs.VAR_INT, { it.delay },
            ::ShriekDirectionalParticleEffect
        )

    }
}