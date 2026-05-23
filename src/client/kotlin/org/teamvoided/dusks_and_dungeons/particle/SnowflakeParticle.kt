package org.teamvoided.dusks_and_dungeons.particle

import net.minecraft.client.particle.*
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.core.particles.ParticleGroup
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.util.Mth.lerp
import org.teamvoided.dusks_and_dungeons.util.pi
import java.util.*
import kotlin.math.max

class SnowflakeParticle(
    world: ClientLevel, x: Double, y: Double, z: Double, velX: Double, velY: Double, velZ: Double
) : TextureSheetParticle(world, x, y, z, velX, velY, velZ) {

    //(ender) delet this?
    constructor(
        world: ClientLevel, x: Double, y: Double, z: Double
    ) : this(
        world,
        x,
        y,
        z,
        (Math.random() * 2.0 - 1.0) * 0.05,
        (Math.random() * 2.0 - 1.0) * 0.05,
        (Math.random() * 2.0 - 1.0) * 0.05
    )

    private val rotationSpeed1: Float
    private val rotationSpeed2: Float
    private var lerp: Float
    private var lerpSpeed: Float

    init {
        this.friction = 1.0f
        this.xd = velX
        this.yd = velY
        this.zd = velZ
        this.quadSize = 0.1f * (this.random.nextFloat() * this.random.nextFloat() * 1.0f + 1.0f)
        this.rotationSpeed1 = (Math.random().toFloat() - 0.5f) * 0.1f
        this.rotationSpeed2 = (Math.random().toFloat() - 0.5f) * 0.1f
        this.lerp = Math.random().toFloat()
        this.lerpSpeed = -0.01f
        this.lifetime = (this.random.nextFloat() * 900).toInt() + 600
    }

    override fun getRenderType(): ParticleRenderType = ParticleRenderType.PARTICLE_SHEET_OPAQUE
    override fun getParticleGroup(): Optional<ParticleGroup> {
        return Optional.of(SNOWFLAKE_PARTICLE_GROUP)
    }

    //    override fun tick() {
//        super.tick()
//        this.angle += 3.1415927f * this.rotationSpeed * 2.0f
//        this.velocityX *= 0.95
//        this.velocityY *= 0.9
//        this.velocityZ *= 0.95
//    }

    override fun tick() {
        this.xo = this.x
        this.yo = this.y
        this.zo = this.z
        if (this.age++ >= this.lifetime) {
            this.remove()
        } else {
            if (this.onGround) {
                this.oRoll = this.roll
                age += 4
            } else {
                this.oRoll = this.roll
                if (this.lerp >= 1) {
                    this.lerpSpeed = -0.05f * Math.random().toFloat()
                } else if (this.lerp <= 0) {
                    this.lerpSpeed = 0.05f * Math.random().toFloat()
                }
                lerp += lerpSpeed
                this.roll += pi * lerp(lerp, rotationSpeed1, rotationSpeed2) * 2.0f
            }
            this.move(this.xd, this.yd, this.zd)
            this.xd *= 0.9
            this.zd *= 0.9
            this.yd = max((this.yd - 0.002) * 0.9, -0.04)
        }
    }

    class Factory(private val spriteProvider: SpriteSet) : ParticleProvider<SimpleParticleType> {
        override fun createParticle(
            defaultParticleType: SimpleParticleType, world: ClientLevel,
            x: Double, y: Double, z: Double,
            velX: Double, velY: Double, velZ: Double
        ): Particle {
            val snowflakeParticle = SnowflakeParticle(world, x, y, z, velX, velY, velZ)
            snowflakeParticle.pickSprite(spriteProvider)
            return snowflakeParticle
        }
    }

    companion object {
        val SNOWFLAKE_PARTICLE_GROUP: ParticleGroup = ParticleGroup(32768)
    }
}