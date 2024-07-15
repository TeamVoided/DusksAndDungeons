package org.teamvoided.dusk_autumn.particle

import net.minecraft.client.particle.CherryLeafParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType

class FallingLeafParticle(world: ClientWorld, x: Double, y: Double, z: Double, spriteProvider: SpriteProvider) :
    CherryLeafParticle(world, x, y, z, spriteProvider) {


    companion object {
        class FallingLeafFactory(private val spriteProvider: SpriteProvider) : ParticleFactory<DefaultParticleType> {
            override fun createParticle(
                defaultParticleType: DefaultParticleType, world: ClientWorld,
                x: Double, y: Double, z: Double,
                g: Double, h: Double, i: Double
            ): Particle {
                return FallingLeafParticle(world, x, y, z, spriteProvider)
            }
        }
    }
}