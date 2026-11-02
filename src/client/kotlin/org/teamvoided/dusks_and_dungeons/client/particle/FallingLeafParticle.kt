package org.teamvoided.dusks_and_dungeons.client.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.CherryParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.core.particles.SimpleParticleType

class FallingLeafParticle(level: ClientLevel, x: Double, y: Double, z: Double, sprites: SpriteSet) :
    CherryParticle(level, x, y, z, sprites) {

    class FallingLeafFactory(private val spriteProvider: SpriteSet) : ParticleProvider<SimpleParticleType> {

        override fun createParticle(
            options: SimpleParticleType, level: ClientLevel,
            x: Double, y: Double, z: Double,
            velX: Double, velY: Double, velZ: Double,
        ): Particle {
            return FallingLeafParticle(level, x, y, z, spriteProvider)
        }

    }

}