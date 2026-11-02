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

class ColorableParticleOption(val color: Color) : ParticleOptions {

    constructor(color: Int) : this(Color(color))

    override fun getType(): ParticleType<ColorableParticleOption> = COLORABLE_OMINOUS_PARTICLE

    companion object {

        val CODEC: MapCodec<ColorableParticleOption> = RecordCodecBuilder.mapCodec { inst ->
            inst
                .group(Codec.INT.fieldOf("color").forGetter { it.color.rgb })
                .apply(inst, ::ColorableParticleOption)
        }

        val PACKET_CODEC: StreamCodec<ByteBuf, ColorableParticleOption> =
            StreamCodec.composite(ByteBufCodecs.INT, { it.color.rgb }, ::ColorableParticleOption)

    }
}

