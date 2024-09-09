package org.teamvoided.dusk_autumn.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import kotlin.math.cos
import kotlin.math.sin

@Environment(EnvType.CLIENT)
class SpiralParticle internal constructor(
    world: ClientWorld,
    xPos: Double,
    yPos: Double,
    zPos: Double,
    xVel: Double,
    yVel: Double,
    zVel: Double
) : SpriteBillboardParticle(world, xPos, yPos, zPos) {
    val scaleBase: Float

    init {
        this.velocityX = xVel
        this.velocityY = yVel + 0.0005
        this.velocityZ = zVel
        this.x = xPos
        this.y = yPos
        this.z = zPos
        this.colorRed = 0.5f
        this.colorGreen = 0f
        this.colorBlue = 0f
        this.scaleBase = (random.nextFloat() * 0.2f + 0.1f)
        this.scale = this.scaleBase
        this.collidesWithWorld = false
        this.maxAge = 6000 + (random.nextFloat() * 1000).toInt()
    }

    override fun getType(): ParticleTextureSheet {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT
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
            val g = (age.toDouble() / maxAge)
            val strength = g / 75
            val math = age / 200.0
            this.velocityX = cos(math)
            this.velocityZ = sin(math)
//            this.x = this.positionX + this.velocityX * f
//            this.y = this.positionY + this.velocityY * f
//            this.z = this.positionZ + this.velocityZ * f
            this.x += this.velocityX * strength
            this.y += this.velocityY * strength + 0.001
            this.z += this.velocityZ * strength
            this.scale = this.scaleBase + (g.toFloat() * 0.2f)
            this.colorRed = 0.5f - (g.toFloat() / 2.0f)
            this.colorBlue = (g.toFloat() / 2.0f) - 0.5f
        }
    }

    @Environment(EnvType.CLIENT)
    class Factory(private val spriteProvider: SpriteProvider) :
        ParticleFactory<DefaultParticleType> {
        override fun createParticle(
            type: DefaultParticleType,
            world: ClientWorld,
            xPos: Double,
            yPos: Double,
            zPos: Double,
            xVel: Double,
            yVel: Double,
            zVel: Double
        ): Particle {
            val particle = SpiralParticle(world, xPos, yPos, zPos, xVel, yVel, zVel)
            particle.setSprite(this.spriteProvider)
            return particle
        }
    }
}