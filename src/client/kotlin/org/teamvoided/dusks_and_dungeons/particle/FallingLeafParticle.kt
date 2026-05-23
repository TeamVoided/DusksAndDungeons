package org.teamvoided.dusks_and_dungeons.particle

import net.minecraft.client.particle.CherryParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.core.particles.SimpleParticleType

class FallingLeafParticle(world: ClientLevel, x: Double, y: Double, z: Double, spriteProvider: SpriteSet) :
    CherryParticle(world, x, y, z, spriteProvider) {
    companion object {
        class FallingLeafFactory(private val spriteProvider: SpriteSet) : ParticleProvider<SimpleParticleType> {
            override fun createParticle(
                defaultParticleType: SimpleParticleType, world: ClientLevel,
                x: Double, y: Double, z: Double, velX: Double, velY: Double, velZ: Double
            ): Particle = FallingLeafParticle(world, x, y, z, spriteProvider)
        }
    }
}