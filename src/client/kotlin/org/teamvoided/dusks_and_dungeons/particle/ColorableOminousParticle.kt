package org.teamvoided.dusks_and_dungeons.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.util.FastColor.ARGB32
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.util.Mth
import java.awt.Color

@Environment(EnvType.CLIENT)
class ColorableOminousParticle internal constructor(
    world: ClientLevel,
    xPos: Double, yPos: Double, zPos: Double,
    xVel: Double, yVel: Double, zVel: Double,
    startColor: Color, endColor: Color
) : TextureSheetParticle(world, xPos, yPos, zPos) {
    private val positionX: Double
    private val positionY: Double
    private val positionZ: Double
    private val startColor: Color
    private val endColor: Color

    init {
        this.xd = xVel
        this.yd = yVel
        this.zd = zVel
        this.positionX = xPos
        this.positionY = yPos
        this.positionZ = zPos
        this.xo = xPos + xVel
        this.yo = yPos + yVel
        this.zo = zPos + zVel
        this.x = this.xo
        this.y = this.yo
        this.z = this.zo
        this.quadSize = 0.1f * (random.nextFloat() * 0.5f + 0.2f)
        this.hasPhysics = false
        this.lifetime = (Math.random() * 5.0).toInt() + 25
        this.startColor = startColor
        this.endColor = endColor
    }

    override fun getRenderType(): ParticleRenderType {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE
    }

    override fun move(dx: Double, dy: Double, dz: Double) {}

    public override fun getLightColor(tint: Float): Int = 240

    override fun tick() {
        this.xo = this.x
        this.yo = this.y
        this.zo = this.z
        if (age++ >= this.lifetime) {
            this.remove()
        } else {
            val f = age.toFloat() / lifetime.toFloat()
            val g = 1.0f - f
            this.x = this.positionX + this.xd * g.toDouble()
            this.y = this.positionY + this.yd * g.toDouble()
            this.z = this.positionZ + this.zd * g.toDouble()
            val color = ARGB32.lerp(f, this.startColor.rgb, this.endColor.rgb)
            this.setColor(
                ARGB32.red(color).toFloat() / 255.0f,
                ARGB32.green(color).toFloat() / 255.0f,
                ARGB32.blue(color).toFloat() / 255.0f
            )
            this.setAlpha(ARGB32.alpha(color).toFloat() / 255.0f)
        }
    }

    @Environment(EnvType.CLIENT)
    class Factory(private val spriteProvider: SpriteSet) : ParticleProvider<ColorableParticleEffect> {
        override fun createParticle(
            type: ColorableParticleEffect, world: ClientLevel,
            xPos: Double, yPos: Double, zPos: Double,
            xVel: Double, yVel: Double, zVel: Double
        ): Particle {
            val particle = ColorableOminousParticle(world, xPos, yPos, zPos, xVel, yVel, zVel, type.color, Color.white)
            particle.scale(Mth.randomBetween(world.getRandom(), 3.0f, 5.0f))
            particle.pickSprite(this.spriteProvider)
            return particle
        }
    }
}