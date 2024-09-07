package org.teamvoided.dusk_autumn.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.util.ColorUtil.Argb32
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.MathHelper

@Environment(EnvType.CLIENT)
class ColorableOminousParticle internal constructor(
    world: ClientWorld,
    xPos: Double,
    yPos: Double,
    zPos: Double,
    xVel: Double,
    yVel: Double,
    zVel: Double,
    startColor: Int,
    endColor: Int
) :
    SpriteBillboardParticle(world, xPos, yPos, zPos) {
    private val positionX: Double
    private val positionY: Double
    private val positionZ: Double
    private val startColor: Int
    private val endColor: Int

    init {
        this.velocityX = xVel
        this.velocityY = yVel
        this.velocityZ = zVel
        this.positionX = xPos
        this.positionY = yPos
        this.positionZ = zPos
        this.prevPosX = xPos + xVel
        this.prevPosY = yPos + yVel
        this.prevPosZ = zPos + zVel
        this.x = this.prevPosX
        this.y = this.prevPosY
        this.z = this.prevPosZ
        this.scale = 0.1f * (random.nextFloat() * 0.5f + 0.2f)
        this.collidesWithWorld = false
        this.maxAge = (Math.random() * 5.0).toInt() + 25
        this.startColor = startColor
        this.endColor = endColor
    }

    override fun getType(): ParticleTextureSheet {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE
    }

    override fun move(dx: Double, dy: Double, dz: Double) {}

    public override fun getBrightness(tint: Float): Int = 240

    override fun tick() {
        this.prevPosX = this.x
        this.prevPosY = this.y
        this.prevPosZ = this.z
        if (age++ >= this.maxAge) {
            this.markDead()
        } else {
            val f = age.toFloat() / maxAge.toFloat()
            val g = 1.0f - f
            this.x = this.positionX + this.velocityX * g.toDouble()
            this.y = this.positionY + this.velocityY * g.toDouble()
            this.z = this.positionZ + this.velocityZ * g.toDouble()
            val color = Argb32.lerp(f, this.startColor, this.endColor)
            this.setColor(
                Argb32.getRed(color).toFloat() / 255.0f,
                Argb32.getGreen(color).toFloat() / 255.0f,
                Argb32.getBlue(color).toFloat() / 255.0f
            )
            this.setColorAlpha(Argb32.getAlpha(color).toFloat() / 255.0f)
        }
    }

    class ColorableFloatingParticleFactory(private val spriteProvider: SpriteProvider) :
        ParticleFactory<DefaultParticleType> {
        override fun createParticle(
            defaultParticleType: DefaultParticleType,
            world: ClientWorld,
            xPos: Double,
            yPos: Double,
            zPos: Double,
            xVel: Double,
            yVel: Double,
            zVel: Double
        ): Particle {
            val particle = ColorableOminousParticle(world, xPos, yPos, zPos, xVel, yVel, zVel, -12210434, -1)
            particle.scale(MathHelper.nextBetween(world.getRandom(), 3.0f, 5.0f))
            particle.setSprite(this.spriteProvider)
            return particle
        }
    }
}