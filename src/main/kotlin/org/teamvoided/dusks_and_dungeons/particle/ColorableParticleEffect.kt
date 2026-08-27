package org.teamvoided.dusks_and_dungeons.particle

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.netty.buffer.ByteBuf
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import org.teamvoided.dusks_and_dungeons.init.DnDParticles.COLORABLE_OMINOUS_PARTICLE
import java.awt.Color

class ColorableParticleEffect(val color: Color) : ParticleOptions {

    constructor(color: Int) : this(Color(color))

    override fun getType(): ParticleType<ColorableParticleEffect> = COLORABLE_OMINOUS_PARTICLE

    companion object {

        val CODEC: MapCodec<ColorableParticleEffect> = RecordCodecBuilder.mapCodec { inst ->
            inst
                .group(Codec.INT.fieldOf("color").forGetter { it.color.rgb })
                .apply(inst, ::ColorableParticleEffect)
        }

        val PACKET_CODEC: StreamCodec<ByteBuf, ColorableParticleEffect> =
            StreamCodec.composite(ByteBufCodecs.INT, { it.color.rgb }, ::ColorableParticleEffect)

    }
}

