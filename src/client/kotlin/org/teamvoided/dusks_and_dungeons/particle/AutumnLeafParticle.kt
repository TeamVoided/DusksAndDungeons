package org.teamvoided.dusks_and_dungeons.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.util.Mth.lerp
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.Vec3
import org.teamvoided.dusks_and_dungeons.util.rotate360
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Environment(EnvType.CLIENT)
open class AutumnLeafParticle(
    world: ClientLevel,
    x: Double,
    y: Double,
    z: Double,
    velX: Double,
    velY: Double,
    velZ: Double
) : TextureSheetParticle(world, x, y, z, velX, velY, velZ) {
    private val rotationSpeed: Float
    private val initialVelocity: Vec3
    private var isOnSurface: Boolean = false
    private var timeOnGround: Int
    private val lerpRate: Double
    private var lerp: Double

    init {
        this.rotationSpeed = (random.nextFloat() - 0.5f) * 0.1f * rotate360
        this.lifetime = (this.random.nextInt(100) + 20)
        this.timeOnGround = (this.random.nextInt(20) + 20)
        lerpRate = 0.0
        lerp = 0.0
        val scaleAndBounds = this.random.nextFloat() * 0.025f + 0.05f
        this.quadSize = scaleAndBounds
        this.setSize(scaleAndBounds, scaleAndBounds)
        val velXZ = ((world.dayTime.toFloat() / 24000) + (random.nextFloat() - 0.5) * 0.1) * rotate360
        xd = velX + cos(velXZ) * 0.1
        yd = velY
        zd = velZ + sin(velXZ) * 0.1
        initialVelocity = Vec3(xd, yd, zd)
        this.friction = 0.5f
        this.gravity = random.nextFloat() * 0.05f + 0.015f
    }

    fun gravity(): Double {
        if (lerp < 1.5 && lerp > 0.5) {
            friction = lerp(lerp.toFloat() - 0.5f, 0.5f, 0.2f)
        }
        return if (lerp <= 1) {
            lerp(lerp, 0.0, gravity.toDouble())
        } else if (lerp <= 2) {
            lerp(lerp - 1, gravity.toDouble(), -gravity.toDouble())
        } else -gravity.toDouble()
    }

    override fun getRenderType(): ParticleRenderType = ParticleRenderType.PARTICLE_SHEET_OPAQUE

    override fun tick() {
        this.xo = this.x
        this.yo = this.y
        this.zo = this.z
        this.oRoll = roll
        val isWater = level.getFluidState(BlockPos(x.toInt(), y.toInt(), z.toInt())).`is`(Fluids.WATER)
        if (age++ >= lifetime && ((this.onGround || isWater || this.y < level.minBuildHeight || this.y > level.maxBuildHeight) && timeOnGround-- <= 0)) {
            this.remove()
        } else {
            lerp = age.toDouble() / lifetime
            if (!(isOnSurface || isWater)) {
                this.roll += rotationSpeed
            }
            xd += initialVelocity.x
            yd += if (isWater) abs(gravity()) else gravity()
            zd += initialVelocity.z
            xd *= friction
            yd *= friction
            zd *= friction
            this.move(xd, yd, zd)
        }
    }

    override fun move(dx: Double, dy: Double, dz: Double) {
        var dx = dx
        var dy = dy
        var dz = dz
        val x = dx
        val y = dy
        val z = dz
        if (this.hasPhysics && (dx != 0.0 || dy != 0.0 || dz != 0.0) && (dx * dx + dy * dy + dz * dz < 10000)) {
            val vec3d = Entity.collideBoundingBox(
                null as Entity?,
                Vec3(dx, dy, dz),
                this.boundingBox,
                this.level, listOf()
            )
            dx = vec3d.x
            dy = vec3d.y
            dz = vec3d.z
        }
        if (dx != 0.0 || dy != 0.0 || dz != 0.0) {
            this.boundingBox = boundingBox.move(dx, dy, dz)
            this.setLocationFromBoundingbox()
        }
        this.onGround = y != dy && y < 0
        if (x != dx) this.xd = 0.0
        if (y != dy) this.yd = 0.0
        if (z != dz) this.zd = 0.0
        val old = isOnSurface
        isOnSurface = onGround || xd == 0.0 || zd == 0.0 || yd == 0.0
        if (!isOnSurface && old) {
            this.lerp = 1.0
        }
    }

    class Factory(private val spriteProvider: SpriteSet) : ParticleProvider<SimpleParticleType> {
        override fun createParticle(
            defaultParticleType: SimpleParticleType, world: ClientLevel,
            x: Double, y: Double, z: Double,
            velX: Double, velY: Double, velZ: Double
        ): Particle {
            val particle = AutumnLeafParticle(world, x, y, z, velX, velY, velZ)
            particle.pickSprite(spriteProvider)
            return particle
        }
    }
}