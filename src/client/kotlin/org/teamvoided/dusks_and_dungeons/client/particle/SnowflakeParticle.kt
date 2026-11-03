package org.teamvoided.dusks_and_dungeons.client.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.particles.ParticleGroup
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.util.Mth
import net.minecraft.util.Mth.lerp
import java.util.*
import kotlin.math.max

class SnowflakeParticle(
    level: ClientLevel, x: Double, y: Double, z: Double, velX: Double, velY: Double, velZ: Double,
) : TextureSheetParticle(level, x, y, z, velX, velY, velZ) {

    private val rotationSpeed1: Float
    private val rotationSpeed2: Float
    private var lerp: Float
    private var lerpSpeed: Float

    init {
        friction = 1.0f
        xd = velX
        yd = velY
        zd = velZ
        quadSize = 0.1f * (random.nextFloat() * random.nextFloat() * 1.0f + 1.0f)
        rotationSpeed1 = (Math.random().toFloat() - 0.5f) * 0.1f
        rotationSpeed2 = (Math.random().toFloat() - 0.5f) * 0.1f
        lerp = Math.random().toFloat()
        lerpSpeed = -0.01f
        lifetime = (random.nextFloat() * 900).toInt() + 600
    }

    override fun getRenderType(): ParticleRenderType = ParticleRenderType.PARTICLE_SHEET_OPAQUE

    override fun getParticleGroup(): Optional<ParticleGroup> = OPT_SNOWFLAKE_GROUP

    override fun tick() {
        xo = x
        yo = y
        zo = z
        if (age++ >= lifetime) {
            remove()
        } else {
            if (onGround) {
                oRoll = roll
                age += 4
            } else {
                oRoll = roll
                if (lerp >= 1) {
                    lerpSpeed = -0.05f * Math.random().toFloat()
                } else if (lerp <= 0) {
                    lerpSpeed = 0.05f * Math.random().toFloat()
                }
                lerp += lerpSpeed
                roll += Mth.PI * lerp(lerp, rotationSpeed1, rotationSpeed2) * 2.0f
            }
            move(xd, yd, zd)
            xd *= 0.9
            zd *= 0.9
            yd = max((yd - 0.002) * 0.9, -0.04)
        }
    }

    class Factory(private val spriteProvider: SpriteSet) : ParticleProvider<SimpleParticleType> {

        override fun createParticle(
            options: SimpleParticleType, level: ClientLevel,
            x: Double, y: Double, z: Double,
            velX: Double, velY: Double, velZ: Double,
        ): Particle {
            val particle = SnowflakeParticle(level, x, y, z, velX, velY, velZ)
            particle.pickSprite(spriteProvider)
            return particle
        }

    }

    companion object {

        val SNOWFLAKE_GROUP = ParticleGroup(32768)
        val OPT_SNOWFLAKE_GROUP = Optional.of(SNOWFLAKE_GROUP)

    }
}