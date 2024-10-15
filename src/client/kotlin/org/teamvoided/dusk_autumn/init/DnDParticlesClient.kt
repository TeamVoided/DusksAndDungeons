package org.teamvoided.dusk_autumn.init

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.minecraft.client.particle.FlameParticle
import org.teamvoided.dusk_autumn.particle.*

object DnDParticlesClient {
    fun init() {
        ParticleFactoryRegistry.getInstance().register(DnDParticles.CASCADE_LEAF_PARTICLE,
            FallingLeafParticle.Companion::FallingLeafFactory
        )
        ParticleFactoryRegistry.getInstance().register(
            DnDParticles.SMALL_SOUL_FLAME_PARTICLE, FlameParticle::SmallFactory
        )
        ParticleFactoryRegistry.getInstance().register(
            DnDParticles.COLORABLE_OMINOUS_PARTICLE, ColorableOminousParticle::Factory
        )
        ParticleFactoryRegistry.getInstance().register(DnDParticles.SPIDERLILY, SpiderlilyPetalParticle::Factory)
        ParticleFactoryRegistry.getInstance().register(DnDParticles.SNOWFLAKE, SnowflakeParticle::Factory)
        ParticleFactoryRegistry.getInstance().register(DnDParticles.MUSHROOM_LAUNCH, MushroomLaunchParticle::Factory)

        ParticleFactoryRegistry.getInstance().register(DnDParticles.SPIRAL, SpiralParticle::Factory)
    }
}