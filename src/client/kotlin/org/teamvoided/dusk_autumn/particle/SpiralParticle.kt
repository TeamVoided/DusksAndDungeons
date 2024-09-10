package org.teamvoided.dusk_autumn.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.MathHelper.lerp
import org.joml.Vector3f
import java.awt.Color
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
    val offsetterXZ: Float
    val yVelocity2: Double

    init {
        velocityX = xVel
        velocityY = yVel
        velocityZ = zVel
        yVelocity2 = 0.0001 + (random.nextFloat() - random.nextFloat()) * 0.0000075
        x = xPos
        y = yPos
        z = zPos
        colorRed = 1f
        colorGreen = 1f
        colorBlue = 1f
        colorAlpha = 0f
        scaleBase = (random.nextFloat() * 0.2f + 0.1f)
        offsetterXZ = (random.nextFloat()) * 0.005f
        scale = scaleBase
        prevAngle = random.nextFloat()
        angle = prevAngle
        collidesWithWorld = false
        val age2 = 2500
        maxAge = age2 + (random.nextFloat() * (age2 / 3f)).toInt()
    }

    override fun getType(): ParticleTextureSheet {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT
    }

    override fun move(dx: Double, dy: Double, dz: Double) {}

    public override fun getBrightness(tint: Float): Int {
        return 240
    }

    override fun tick() {
        prevPosX = x
        prevPosY = y
        prevPosZ = z
        if (age++ >= maxAge) {
            colorAlpha += -0.01f
            if (colorAlpha < 0)
                markDead()
        } else if (colorAlpha < 1) {
            colorAlpha += 0.002f
        }
        val frac = (age.toFloat() / maxAge)
        val speed = 50.0
        val strength = (frac + offsetterXZ) / speed
        val fhte = age / (speed * 2.5)
        val width = 3
//            val fhte = age / (speed * 1.5)
//            val width = (fhte / 2) * 0.4
        velocityX = cos(fhte) * width
//        velocityY = sin(fhte * 0.35)
        velocityY = sin(fhte * 3.5) * 0.5
        velocityZ = sin(fhte) * width
        x += velocityX * strength
        y += velocityY * strength + yVelocity2 * speed
        z += velocityZ * strength
        scale += 0.0001f
        colorLerp(frac)
    }

    fun colorLerp(frac: Float) {
        val color1 = Color(0xFFFFFF)
        val color2 = Color(0x24CADA)
        val color3 = Color(0x52D973)
        val colorOption1 = Vector3f(color1.red / 255f, color1.green / 255f, color1.blue / 255f)
        val colorOption2 = Vector3f(color2.red / 255f, color2.green / 255f, color2.blue / 255f)
        val colorOption3 = Vector3f(color3.red / 255f, color3.green / 255f, color3.blue / 255f)
        val colorChoice: Vector3f =
            if (frac < 1f / 5f) colorOption1.lerp(colorOption2, frac * 5f)
            else if (frac < 1f / 2f) colorOption2
            else colorOption2.lerp(colorOption3, 2f * (frac - (1f / 2f)))
        colorRed = colorChoice.x()
        colorGreen = colorChoice.y()
        colorBlue = colorChoice.z()
    }

//    fun colorLerp(frac: Float) {
//        val color1 = Color(0xD11950)
//        val color2 = Color(0xCA3E84)
//        val colorOption1 = Vector3f(color1.red / 255f, color1.green / 255f, color1.blue / 255f)
//        val colorOption2 = Vector3f(color2.red / 255f, color2.green / 255f, color2.blue / 255f)
//        val colorChoice: Vector3f = colorOption1.lerp(colorOption2, frac)
//        colorRed = colorChoice.x()
//        colorGreen = colorChoice.y()
//        colorBlue = colorChoice.z()
//    }

    @Environment(EnvType.CLIENT)
    class Factory(private val spriteProvider: SpriteProvider) : ParticleFactory<DefaultParticleType> {
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