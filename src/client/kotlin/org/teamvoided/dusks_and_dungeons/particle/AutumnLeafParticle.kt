package org.teamvoided.dusks_and_dungeons.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.Entity
import net.minecraft.fluid.Fluids
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.MathHelper.lerp
import net.minecraft.util.math.Vec3d
import org.joml.Vector3f
import org.teamvoided.dusks_and_dungeons.util.blockPos
import org.teamvoided.dusks_and_dungeons.util.rotate360
import kotlin.math.*

@Environment(EnvType.CLIENT)
open class AutumnLeafParticle(
    world: ClientWorld,
    x: Double,
    y: Double,
    z: Double,
    velX: Double,
    velY: Double,
    velZ: Double
) : SpriteBillboardParticle(world, x, y, z, velX, velY, velZ) {
    private val rotationSpeed: Float
    private val initialVelocity: Vec3d
    private var isOnSurface: Boolean = false
    private var timeOnGround: Int
    private val lerpRate: Double
    private var lerp: Double

    init {
        this.rotationSpeed = (random.nextFloat() - 0.5f) * 0.1f * rotate360
        this.maxAge = (this.random.nextInt(100) + 20)
        this.timeOnGround = (this.random.nextInt(20) + 20)
        lerpRate = 0.0
        lerp = 0.0
        val scaleAndBounds = this.random.nextFloat() * 0.025f + 0.05f
        this.scale = scaleAndBounds
        this.setBoundingBoxSpacing(scaleAndBounds, scaleAndBounds)
        val velXZ = ((world.timeOfDay.toFloat() / 24000) + (random.nextFloat() - 0.5) * 0.1) * rotate360
        velocityX = velX + cos(velXZ) * 0.1
        velocityY = velY
        velocityZ = velZ + sin(velXZ) * 0.1
        initialVelocity = Vec3d(velocityX, velocityY, velocityZ)
        this.velocityMultiplier = 0.5f
        this.gravityStrength = random.nextFloat() * 0.05f + 0.015f
    }

    fun gravity(): Double {
        if (lerp < 1.5 && lerp > 0.5) {
            velocityMultiplier = lerp(lerp.toFloat() - 0.5f, 0.5f, 0.2f)
        }
        return if (lerp <= 1) {
            lerp(lerp, 0.0, gravityStrength.toDouble())
        } else if (lerp <= 2) {
            lerp(lerp - 1, gravityStrength.toDouble(), -gravityStrength.toDouble())
        } else -gravityStrength.toDouble()
    }

    override fun getType(): ParticleTextureSheet = ParticleTextureSheet.PARTICLE_SHEET_OPAQUE

    override fun tick() {
        this.prevPosX = this.x
        this.prevPosY = this.y
        this.prevPosZ = this.z
        this.prevAngle = angle
        val isWater = world.getFluidState(Vec3d(x, y, z).blockPos()).isOf(Fluids.WATER)
        if (age++ >= maxAge && ((this.onGround || isWater || this.y < world.bottomY || this.y > world.topY) && timeOnGround-- <= 0)) {
            this.markDead()
        } else {
            lerp = age.toDouble() / maxAge
            if (!(isOnSurface || isWater)) {
                this.angle += rotationSpeed
            }
            velocityX += initialVelocity.x
            velocityY += if (isWater) abs(gravity()) else gravity()
            velocityZ += initialVelocity.z
            velocityX *= velocityMultiplier
            velocityY *= velocityMultiplier
            velocityZ *= velocityMultiplier
            this.move(velocityX, velocityY, velocityZ)
        }
    }

    override fun move(dx: Double, dy: Double, dz: Double) {
        var dx = dx
        var dy = dy
        var dz = dz
        val x = dx
        val y = dy
        val z = dz
        if (this.collidesWithWorld && (dx != 0.0 || dy != 0.0 || dz != 0.0) && (dx * dx + dy * dy + dz * dz < 10000)) {
            val vec3d = Entity.adjustSingleAxisMovementForCollisions(
                null as Entity?,
                Vec3d(dx, dy, dz),
                this.boundingBox,
                this.world, listOf()
            )
            dx = vec3d.x
            dy = vec3d.y
            dz = vec3d.z
        }
        if (dx != 0.0 || dy != 0.0 || dz != 0.0) {
            this.boundingBox = boundingBox.offset(dx, dy, dz)
            this.repositionFromBoundingBox()
        }
        this.onGround = y != dy && y < 0
        if (x != dx) this.velocityX = 0.0
        if (y != dy) this.velocityY = 0.0
        if (z != dz) this.velocityZ = 0.0
        val old = isOnSurface
        isOnSurface = onGround || velocityX == 0.0 || velocityZ == 0.0 || velocityY == 0.0
        if (!isOnSurface && old) {
            this.lerp = 1.0
        }
    }

    class Factory(private val spriteProvider: SpriteProvider) : ParticleFactory<DefaultParticleType> {
        override fun createParticle(
            defaultParticleType: DefaultParticleType, world: ClientWorld,
            x: Double, y: Double, z: Double,
            velX: Double, velY: Double, velZ: Double
        ): Particle {
            val particle = AutumnLeafParticle(world, x, y, z, velX, velY, velZ)
            particle.setSprite(spriteProvider)
            return particle
        }
    }
}