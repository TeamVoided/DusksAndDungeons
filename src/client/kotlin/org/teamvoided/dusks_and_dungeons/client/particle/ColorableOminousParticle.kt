package org.teamvoided.dusks_and_dungeons.client.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.util.FastColor.ARGB32
import net.minecraft.util.Mth
import org.teamvoided.dusks_and_dungeons.particle.ColorableParticleOption
import java.awt.Color

class ColorableOminousParticle internal constructor(
    level: ClientLevel,
    xPos: Double, yPos: Double, zPos: Double,
    xVel: Double, yVel: Double, zVel: Double,
    private val startColor: Color, private val endColor: Color,
) : TextureSheetParticle(level, xPos, yPos, zPos) {
    private val positionX: Double = xPos
    private val positionY: Double = yPos
    private val positionZ: Double = zPos

    init {
        xd = xVel
        yd = yVel
        zd = zVel
        xo = xPos + xVel
        yo = yPos + yVel
        zo = zPos + zVel
        x = xo
        y = yo
        z = zo
        quadSize = 0.1f * (random.nextFloat() * 0.5f + 0.2f)
        hasPhysics = false
        lifetime = (Math.random() * 5.0).toInt() + 25

    }

    public override fun getLightColor(tint: Float): Int = 240

    override fun getRenderType(): ParticleRenderType = ParticleRenderType.PARTICLE_SHEET_OPAQUE

    override fun move(dx: Double, dy: Double, dz: Double) = Unit

    override fun tick() {
        xo = x
        yo = y
        zo = z
        if (age++ >= lifetime) {
            remove()
        } else {
            val duration = age / lifetime.toFloat()
            val inverseDuration = 1.0 - duration
            x = positionX + xd * inverseDuration
            y = positionY + yd * inverseDuration
            z = positionZ + zd * inverseDuration
            val color = ARGB32.lerp(duration, startColor.rgb, endColor.rgb)
            setColor(
                ARGB32.red(color).toFloat() / 255.0f,
                ARGB32.green(color).toFloat() / 255.0f,
                ARGB32.blue(color).toFloat() / 255.0f
            )
            setAlpha(ARGB32.alpha(color).toFloat() / 255.0f)
        }
    }

    @Environment(EnvType.CLIENT)
    class Factory(private val spriteProvider: SpriteSet) : ParticleProvider<ColorableParticleOption> {

        override fun createParticle(
            options: ColorableParticleOption, level: ClientLevel,
            xPos: Double, yPos: Double, zPos: Double,
            xVel: Double, yVel: Double, zVel: Double,
        ): Particle {
            val particle =
                ColorableOminousParticle(level, xPos, yPos, zPos, xVel, yVel, zVel, options.color, Color.white)
            particle.scale(Mth.randomBetween(level.getRandom(), 3.0f, 5.0f))
            particle.pickSprite(spriteProvider)
            return particle
        }

    }

}