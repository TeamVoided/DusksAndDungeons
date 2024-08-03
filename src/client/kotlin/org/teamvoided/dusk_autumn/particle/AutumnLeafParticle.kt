package org.teamvoided.dusk_autumn.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteBillboardParticle
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld
import org.joml.Vector3f
import java.awt.Color
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin

@Environment(EnvType.CLIENT)
open class AutumnLeafParticle protected constructor(
    world: ClientWorld,
    x: Double,
    y: Double,
    z: Double,
    spriteProvider: SpriteProvider
) :
    SpriteBillboardParticle(world, x, y, z) {
    private var rotationSpeed: Float
    private val fallingCurveAngleFactor: Float
    private val rotationAcceleration: Float
    private val color1: Color = Color(0xD1004A)
    private val color2: Color = Color(0xFFDD59)

    init {
        this.setSprite(spriteProvider.getSprite(this.random.nextInt(12), 12))
        this.rotationSpeed = Math.toRadians(if (this.random.nextBoolean()) -30.0 else 30.0).toFloat()
        this.fallingCurveAngleFactor = this.random.nextFloat()
        this.rotationAcceleration = Math.toRadians(if (this.random.nextBoolean()) -5.0 else 5.0).toFloat()
        this.maxAge = (this.random.nextInt(400) + BASE_LIFE_TIME)
        this.gravityStrength = 7.5E-4f
        chooseColor()
        val scaleAndBounds = if (this.random.nextBoolean()) 0.05f else 0.075f
        this.scale = scaleAndBounds
        this.setBoundingBoxSpacing(scaleAndBounds, scaleAndBounds)
        this.velocityMultiplier = 1.0f
    }

    private fun chooseColor() {
        val choice = (Math.random().toFloat())
        val colorOption1 = Vector3f(color1.red / 255f, color1.green / 255f, color1.blue / 255f)
        val colorOption2 = Vector3f(color2.red / 255f, color2.green / 255f, color2.blue / 255f)
        val colorChoice: Vector3f = colorOption1.lerp(colorOption2, choice)
        this.colorRed = colorChoice.x()
        this.colorGreen = colorChoice.y()
        this.colorBlue = colorChoice.z()
    }

    override fun getType(): ParticleTextureSheet = ParticleTextureSheet.PARTICLE_SHEET_OPAQUE

    override fun tick() {
        this.prevPosX = this.x
        this.prevPosY = this.y
        this.prevPosZ = this.z
        if (this.maxAge-- <= 0) {
            this.markDead()
        }
        if (!this.dead) {
            val f: Float = (300 - this.maxAge).toFloat()
            val windExtra = min((f / 300.0f).toDouble(), 1.0).toFloat()
            val velX: Double =
                cos(Math.toRadians((this.fallingCurveAngleFactor * 60.0f).toDouble())) *
                        WIND_VELOCITY_FACTOR *
                        windExtra.toDouble().pow(FALL_ACCELERATION)
            val velZ: Double =
                sin(Math.toRadians((this.fallingCurveAngleFactor * 60.0f).toDouble())) *
                        WIND_VELOCITY_FACTOR *
                        windExtra.toDouble().pow(FALL_ACCELERATION)
            this.velocityX += velX * ACCELERATION_FACTOR
            this.velocityZ += velZ * ACCELERATION_FACTOR
            this.velocityY -= this.gravityStrength.toDouble()
            this.rotationSpeed += this.rotationAcceleration / 20.0f
            this.prevAngle = this.angle
            this.angle += this.rotationSpeed / 20.0f
            this.move(this.velocityX, this.velocityY, this.velocityZ)
            if (this.onGround || this.maxAge < 299 && (this.velocityX == 0.0 || this.velocityZ == 0.0)) {
//                this.markDead()
                this.age -= 2
            }
            if (!this.dead) {
                this.velocityX *= this.velocityMultiplier.toDouble()
                this.velocityY *= this.velocityMultiplier.toDouble()
                this.velocityZ *= this.velocityMultiplier.toDouble()
            }
        }
    }

    companion object {
        private const val ACCELERATION_FACTOR = 0.0025f
        private const val BASE_LIFE_TIME = 300
        private const val CURVE_ENDPOINT_TIME = 300
        private const val FALL_ACCELERATION = 1.25
        private const val WIND_VELOCITY_FACTOR = 2.0f
    }
}