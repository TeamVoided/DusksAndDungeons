package org.teamvoided.dusks_and_dungeons.init

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry.PendingParticleFactory
import net.minecraft.client.particle.FlameParticle
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import org.teamvoided.dusks_and_dungeons.particle.AutumnLeafParticle
import org.teamvoided.dusks_and_dungeons.particle.ColorableOminousParticle
import org.teamvoided.dusks_and_dungeons.particle.FallingLeafParticle
import org.teamvoided.dusks_and_dungeons.particle.SnowflakeParticle

object DnDParticlesClient {
    fun init() {
        register(DnDParticles.AUTUMN_LEAF_PARTICLE, AutumnLeafParticle::Factory)
        register(DnDParticles.CASCADE_LEAF_PARTICLE, FallingLeafParticle.Companion::FallingLeafFactory)
        register(DnDParticles.SMALL_SOUL_FLAME_PARTICLE, FlameParticle::SmallFlameProvider)
        register(DnDParticles.SNOWFLAKE, SnowflakeParticle::Factory)
        register(DnDParticles.COLORABLE_OMINOUS_PARTICLE, ColorableOminousParticle::Factory)
    }

    fun <T : ParticleOptions> register(type: ParticleType<T>, constructor: PendingParticleFactory<T>) =
        ParticleFactoryRegistry.getInstance().register(type, constructor)
}
