package org.teamvoided.dusk_autumn.particle

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleType
import org.teamvoided.dusk_autumn.init.DnDParticles
import java.awt.Color

class ColorableParticleEffect(
    val color: Color
) : ParticleEffect {
    constructor(
        color: Int
    ) : this(Color(color))

    override fun getType(): ParticleType<ColorableParticleEffect> =
        DnDParticles.COLORABLE_OMINOUS_PARTICLE

    companion object {
        val CODEC: MapCodec<ColorableParticleEffect> =
            RecordCodecBuilder.mapCodec { instance ->
                instance.group(
                    Codec.INT.fieldOf("color").forGetter { it.color.rgb }
                ).apply(instance, ::ColorableParticleEffect)
            }
        val PACKET_CODEC: PacketCodec<RegistryByteBuf, ColorableParticleEffect> =
            PacketCodec.tuple(
                PacketCodecs.INT, { it.color.rgb },
                ::ColorableParticleEffect
            )
    }
}

